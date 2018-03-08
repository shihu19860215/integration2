package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.config.Global;
import com.shihu.entity.HomePageBean;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarVO;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController {
    private final int index=1;
    @Autowired
    private CarService carService;
    @Autowired
    private CarTypeService carTypeService;


    @RequestMapping("/list")
    public ModelAndView listByCarTypeId(Long carTypeId,Integer pagePromptId) {
        ModelAndView modelAndView = new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.CAR_LIST);
        modelAndView.addObject("homePageBean", homePageBean);

        CarType carType = carTypeService.getCarTypeById(carTypeId);
        modelAndView.addObject("carType", carType);


        if(null!=pagePromptId){
            modelAndView.addObject("pagePrompt",PagePrompt.getPagePrompt(pagePromptId));
        }
        return modelAndView;
    }

    @RequiresPermissions("car:delete")
    @RequestMapping(value = "/del/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Long carTypeId) {
        ModelAndView modelAndView=new ModelAndView(Global.REDIRECT_CAR_LIST+"?carTypeId=" + carTypeId);
        try {
            carService.delete(id);
        } catch (PagePromptException e) {
            modelAndView.addObject("pagePromptId",e.getPagePrompt().getId());
        }
        return modelAndView;
    }

    @RequiresPermissions("car:add")
    @RequestMapping("/add")
    public ModelAndView add(CarVO carVO) {
        ModelAndView modelAndView=new ModelAndView(Global.REDIRECT_CAR_LIST+"?carTypeId=" + carVO.getCarTypeId());
        if(null!=carVO.getName()&&carVO.getName().length()>0){
            try {
                carService.insert(carVO);
            } catch (PagePromptException e) {
                modelAndView.addObject("pagePromptId", e.getPagePrompt().getId());
            }
        }else {
            modelAndView.addObject("pagePromptId", PagePrompt.ADD_INFO_NOT_EMPTY);
        }
        return modelAndView;
    }
}
