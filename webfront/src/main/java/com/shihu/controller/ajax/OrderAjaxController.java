package com.shihu.controller.ajax;

import com.shihu.base.BaseController;
import com.shihu.entity.AjaxResult;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderProduct;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OrderVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.service.OrderService;
import com.shihu.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/ajax/order")
public class OrderAjaxController  extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequiresPermissions("order:ajax.addproduct")
    @RequestMapping("/addproduct/{id}")
    public AjaxResult addProduct(HttpSession session,HttpServletResponse response,@PathVariable("id") Long productId){
        AjaxResult ajaxResult=new AjaxResult();
        Object o=session.getAttribute("order");
        Order order;
        if(o!=null){
            order=(Order)o;
        }else {
            order=new Order();
            session.setAttribute("order",order);
        }
        boolean isExist=false;
        if(null==order.getOrderProductList()){
            order.setOrderProductList(new ArrayList<OrderProduct>());
        }else {
            for(OrderProduct orderProduct:order.getOrderProductList()){
                if(orderProduct.getProduct().getId().equals(productId)){
                    isExist=true;
                    break;
                }
            }
        }
        if(!isExist){
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setNum(1);
            ProductVO productVO=productService.get(productId);
            orderProduct.setProduct(new Product(productVO));
            orderProduct.setSell(true);
            order.getOrderProductList().add(orderProduct);
        }
        ajaxResult.setState(1);
        return ajaxResult;
    }

    @ResponseBody
    @RequiresPermissions("order:ajax.save")
    @RequestMapping("/save")
    public AjaxResult save(HttpSession session,HttpServletResponse response,Order order){
        AjaxResult ajaxResult=new AjaxResult();
        if(null!=order&&null!=order.getOrderProductList()){
            for(OrderProduct orderProduct:order.getOrderProductList()){
                orderProduct.setProduct(new Product(productService.get(orderProduct.getProduct().getId())));
            }
        }
        session.setAttribute("order",order);
        ajaxResult.setState(1);
        return  ajaxResult;
    }

    @ResponseBody
    @RequiresPermissions("order:ajax.add")
    @RequestMapping("/add")
    public AjaxResult add(HttpSession session,HttpServletResponse response,Order order){
        AjaxResult ajaxResult=new AjaxResult();
        PagePromptException pagePromptException=null;
        try {
            orderService.addOrder(order);
            session.removeAttribute("order");
            ajaxResult.setState(1);
        } catch (PagePromptException e) {
            pagePromptException=e;
            ajaxResult.setState(-1);
            ajaxResult.setInfo(e.getPagePrompt().getMessage());
        }
        return  ajaxResult;
    }

    @ResponseBody
    @RequiresPermissions("order:ajax.saveremarks")
    @RequestMapping("/saveremarks")
    public AjaxResult updateRemarks(HttpServletResponse response,OrderVO orderVO){
        AjaxResult ajaxResult=new AjaxResult();
        orderService.update(orderVO);
        ajaxResult.setState(1);
        return  ajaxResult;

    }
}
