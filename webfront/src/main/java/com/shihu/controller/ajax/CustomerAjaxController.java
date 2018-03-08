package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.base.BaseController;
import com.shihu.entity.AjaxResult;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax/customer")
public class CustomerAjaxController extends BaseController {
    @Autowired
    private Gson gson;
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequiresPermissions("customer:ajax.saerchlikenametel")
    @RequestMapping("/saerchlikenametel")
    public AjaxResult searchLikeName(HttpServletResponse response, String customerNameOrTel){
        AjaxResult ajaxResult=new AjaxResult();
        List<CustomerVO> list=customerService.getCustomerVOListLikeNameOrTel(customerNameOrTel);
        if(null==list)list=new ArrayList<CustomerVO>(0);
        ajaxResult.setState(1);
        ajaxResult.setInfo(list);
        return ajaxResult;
    }
}
