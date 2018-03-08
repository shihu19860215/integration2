package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.base.Page;
import com.shihu.config.Global;
import com.shihu.entity.HomePageBean;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    @Autowired
    private CustomerService customerService;

    @RequiresPermissions("customer:list")
    @RequestMapping("/list")
    public ModelAndView list(Page<CustomerVO> page){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.CUSTOMER_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        if(null==page){
            page=new Page<CustomerVO>();
        }

        List<CustomerVO> list=customerService.list(page);
        int count=customerService.count(page);
        page.setCount(count);
        modelAndView.addObject("page",page);
        modelAndView.addObject("list",list);


        return  modelAndView;
    }

    @RequiresPermissions("customer:toaddpage")
    @RequestMapping("/toaddpage")
    public ModelAndView toAddPage(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.CUSTOMER_ADD);
        modelAndView.addObject("homePageBean",homePageBean);
        return  modelAndView;

    }

    @RequiresPermissions("customer:toupdatepage")
    @RequestMapping("/toupdatepage/{id}")
    public ModelAndView toUpdatePage(@PathVariable("id")Long id){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.CUSTOMER_UPDATE);
        modelAndView.addObject("homePageBean",homePageBean);

        CustomerVO customerVO=customerService.get(id);
        modelAndView.addObject("customerVO",customerVO);
        return  modelAndView;
    }
    @RequiresPermissions("customer:delete")
    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id")Long id){
        customerService.delete(id);
        return Global.REDIRECT_CUSTOMER_LIST;
    }
    @RequiresPermissions("customer:add")
    @RequestMapping("/add")
    public String add(CustomerVO customerVO){
        if(null!=customerVO&&null!=customerVO.getName()&&customerVO.getName().length()>0){
            customerService.insert(customerVO);

        }
        return Global.REDIRECT_CUSTOMER_LIST;
    }

    @RequiresPermissions("customer:update")
    @RequestMapping("/update")
    public String update(CustomerVO customerVO){
        if(null!=customerVO&&null!=customerVO.getName()&&customerVO.getName().length()>0){
            customerService.update(customerVO);
        }

        return Global.REDIRECT_CUSTOMER_LIST;
    }
}
