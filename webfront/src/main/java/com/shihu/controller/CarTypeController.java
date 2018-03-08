package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.config.Global;
import com.shihu.entity.AjaxResult;
import com.shihu.entity.HomePageBean;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.service.CarTypeService;
import com.shihu.utils.JsonUtils;
import com.shihu.utils.WebUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartype")
public class CarTypeController extends BaseController {
    @Autowired
    private CarTypeService carTypeServer;

    @RequestMapping(value = {"/list"})
    public ModelAndView list(Integer pagePromptId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.CARTYPE_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        List<CarTypeVO> carTypeList=  carTypeServer.listAll();
        modelAndView.addObject("carTypeList",carTypeList);
        if(null!=pagePromptId){
            modelAndView.addObject("pagePrompt",PagePrompt.getPagePrompt(pagePromptId));
        }
        return modelAndView;
    }

    @RequiresPermissions("cartype:delete")
    @RequestMapping(value="/del/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView modelAndView=new ModelAndView(Global.REDIRECT_CARTYPE_LIST);
        try {
            carTypeServer.delete(id);
        } catch (PagePromptException e) {
            modelAndView.addObject("pagePromptId", e.getPagePrompt().getId());
        }
        return modelAndView;
    }
    @RequiresPermissions("cartype:add")
    @RequestMapping("/add")
    public ModelAndView add(CarTypeVO carTypeVO){
        ModelAndView modelAndView=new ModelAndView(Global.REDIRECT_CARTYPE_LIST);
        if(null!=carTypeVO.getName()&&carTypeVO.getName().length()>0){
            try {
                carTypeServer.insert(carTypeVO);
            } catch (PagePromptException e) {
                modelAndView.addObject("pagePromptId", e.getPagePrompt().getId());
            }
        }else {
            modelAndView.addObject("pagePromptId",PagePrompt.ADD_INFO_NOT_EMPTY);
        }
        return modelAndView;
    }


}
