package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.base.Page;
import com.shihu.entity.HomePageBean;
import com.shihu.model.common.VO.LogVO;
import com.shihu.service.LogService;
import com.shihu.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @RequiresPermissions("log:search")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView search(Page<LogVO> page, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.LOG_LIST);
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
        List<LogVO> logList=logService.list(page);
        modelAndView.addObject("logList",logList);
        int count=logService.count(page);
        page.setCount(count);
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    @RequiresPermissions("log:search")
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.LOG_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        Page<LogVO> page=new Page<LogVO>();
        List<LogVO> logList=logService.list(page);
        modelAndView.addObject("logList",logList);
        int count=logService.count(page);
        page.setCount(count);
        modelAndView.addObject("page",page);
        return modelAndView;
    }
}
