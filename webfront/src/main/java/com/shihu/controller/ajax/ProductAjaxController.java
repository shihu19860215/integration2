package com.shihu.controller.ajax;

import com.shihu.base.BaseController;
import com.shihu.entity.AjaxResult;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/ajax/product")
public class ProductAjaxController extends BaseController {
    @Autowired
    private ProductService productService;


    @ResponseBody
    @RequiresPermissions("product:ajax.addcount")
    @RequestMapping("/add")
    public AjaxResult addNum(HttpServletResponse response, Long productId){
        AjaxResult ajaxResult=null;
        int count=productService.addProductNum(productId);
        ajaxResult=new AjaxResult();
        ajaxResult.setState(1);
        ajaxResult.setInfo(String.valueOf(count));
        return ajaxResult;
    }
    @ResponseBody
    @RequiresPermissions("product:ajax.subcount")
    @RequestMapping("/sub")
    public AjaxResult subNum(HttpServletResponse response,Long productId){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            int count=productService.subProductNum(productId);
            ajaxResult.setState(1);
            ajaxResult.setInfo(String.valueOf(count));
        }catch (PagePromptException ppe){
            ajaxResult.setState(-1);
            ajaxResult.setInfo(ppe.getPagePrompt().getMessage());
        }
        return ajaxResult;
    }

    @ResponseBody
    @RequiresPermissions("product:ajax.update")
    @RequestMapping("/update")
    public AjaxResult update(HttpServletResponse response, ProductVO productVO, String carIds){
        AjaxResult ajaxResult=new AjaxResult();
        String[] strs=carIds.split(",");
        productService.update(productVO,strs);
        ajaxResult.setState(1);
        return ajaxResult;
    }
    @ResponseBody
    @RequiresPermissions("product:ajax.delete")
    @RequestMapping("/del/{id}")
    public AjaxResult del(HttpServletResponse response,@PathVariable("id")Long id){
        AjaxResult ajaxResult=new AjaxResult();
        productService.delete(id);
        ajaxResult.setState(1);
        return ajaxResult;
    }
}
