package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.base.Page;
import com.shihu.entity.HomePageBean;
import com.shihu.model.common.Order;
import com.shihu.model.common.VO.OrderVO;
import com.shihu.service.CustomerService;
import com.shihu.service.OrderService;
import com.shihu.service.ProductService;
import com.shihu.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController  extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    @RequiresPermissions("order:toaddpage")
    @RequestMapping("/toaddpage")
    public ModelAndView toAddPage(HttpSession session){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.ORDER_ADD);
        modelAndView.addObject("homePageBean",homePageBean);
        Object o=session.getAttribute("order");
        if(o!=null){
            Order order=(Order)o;
            modelAndView.addObject("order",order);
        }
        return modelAndView;
    }

    @RequiresPermissions("order:display")
    @RequestMapping("/display/{id}")
    public ModelAndView display(@PathVariable("id") Long id){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.ORDER_DISPLAY);
        modelAndView.addObject("homePageBean",homePageBean);
        Order order=orderService.getOrderById(id);
        modelAndView.addObject("order",order);
        return modelAndView;
    }

    @RequiresPermissions("order:search")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView search(Page<OrderVO> page){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.ORDER_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        String startDate=null,endDate=null;
        LinkedHashMap<String,Object> map=page.getParm();
        Object o;
        o=map.get("startDate");
        if(null!=o)startDate=(String)o;
        o=map.get("endDate");
        if(null!=o)endDate=(String)o;

        if(DateUtils.validateDateString(startDate)){
            map.put("start",DateUtils.parse(startDate,DateUtils.DATE_PARSE_yyyyMMdd));
        }
        if(DateUtils.validateDateString(endDate)){
            Date end=DateUtils.parse(endDate,DateUtils.DATE_PARSE_yyyyMMdd);
            Calendar c = Calendar.getInstance();
            c.setTime(end);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            end=c.getTime();
            map.put("end",end);
        }


        List<OrderVO> orderVOList=orderService.list(page);
        modelAndView.addObject("orderVOList",orderVOList);
        int count=orderService.count(page);
        page.setCount(count);
        modelAndView.addObject("page",page);

        return modelAndView;
    }


    @RequiresPermissions("order:search")
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.ORDER_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        Page<OrderVO> page=new Page<OrderVO>();
        List<OrderVO> orderVOList=orderService.list(page);
        modelAndView.addObject("orderVOList",orderVOList);
        int count=orderService.count(page);
        page.setCount(count);
        modelAndView.addObject("page",page);

        return modelAndView;

    }
}
