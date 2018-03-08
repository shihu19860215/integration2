package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.base.BaseController;
import com.shihu.entity.AjaxResult;
import com.shihu.model.common.VO.CarVO;
import com.shihu.service.CarService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/ajax/car")
public class CarAjaxController extends BaseController {
    @Autowired
    private CarService carService;

    @ResponseBody
    @RequiresPermissions("car:ajax.saerchcarbyname")
    @RequestMapping("/saerchcarbyname")
    public AjaxResult addNum(HttpServletResponse response, String str){
        AjaxResult ajaxResult=new AjaxResult();
        List<CarVO> cars=carService.listCarVOWithCarTypeNameIncludeStr(str);
        ajaxResult.setState(1);
        ajaxResult.setInfo(cars);
        return ajaxResult;
    }
}
