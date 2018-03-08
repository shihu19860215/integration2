package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.config.Global;
import com.shihu.entity.HomePageBean;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import com.shihu.service.ProductService;
import com.shihu.utils.UrlUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarService carService;

    @RequestMapping("/list")
    public ModelAndView list(Long carId,Long carTypeId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_LIST);
        modelAndView.addObject("homePageBean",homePageBean);

        List<Product> productList=productService.listProductByCarId(carId);
        modelAndView.addObject("productList",productList);
        CarTypeVO carTypeVO=carTypeService.get(carTypeId);
        modelAndView.addObject("carType",carTypeVO);
        CarVO carVO=carService.get(carId);
        modelAndView.addObject("car",carVO);

        return modelAndView;
    }

    @RequiresPermissions("product:toaddpage")
    @RequestMapping("/toaddpage/{id}")
    public ModelAndView toAddPage(@PathVariable("id")Long carId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_ADD);
        modelAndView.addObject("homePageBean",homePageBean);

        CarVO carVO=carService.get(carId);
        modelAndView.addObject("car",carVO);
        return modelAndView;
    }
    @RequiresPermissions("product:toupdatepage")
    @RequestMapping("/toupdatepage/{id}")
    public ModelAndView toUpdatePage(@PathVariable("id")Long id,Long carId){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_UPDATE);
        modelAndView.addObject("homePageBean",homePageBean);

        CarVO carVO=carService.get(carId);
        modelAndView.addObject("car",carVO);

        Product product=productService.getProductByIdWithCarTypeName(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @RequiresPermissions("product:add")
    public String add(ProductVO productVO, Long[] carIds,Long carId){
        List<CarVO> cars=new ArrayList<CarVO>();
        CarVO carVO=carService.get(carId);
        cars.add(carVO);
        if(null!=carIds&&carIds.length>0){
            for (Long cId:carIds){
                cars.add(carService.get(cId));
            }
        }
        productVO.setCarStr(cars);
        productService.addProduct(productVO,cars);

        return UrlUtils.getRedirectUrl(Global.REDIRECT_PRODUCT_LIST,"carId",carId,"carTypeId",cars.get(0).getCarTypeId());
    }
    @RequiresPermissions("product:update")
    @RequestMapping("/update")
    public String update(ProductVO productVO,Long[] carIds,Long carId){
        productService.update(productVO,carIds,carId);
        CarVO carVO=carService.get(carId);

        return UrlUtils.getRedirectUrl(Global.REDIRECT_PRODUCT_LIST,"carId",carId,"carTypeId",carVO.getCarTypeId());
    }
    @RequiresPermissions("product:delete")
    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id")Long id,Long carId,Long carTypeId){
        productService.delete(id);
        return UrlUtils.getRedirectUrl(Global.REDIRECT_PRODUCT_LIST,"carId",carId,"carTypeId",carTypeId);
    }

    @RequiresPermissions("product:toquickaddpage")
    @RequestMapping("/toquickaddpage")
    public ModelAndView toQuickAddPage(){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_QUICK_ADD);
        modelAndView.addObject("homePageBean",homePageBean);

        return modelAndView;
    }


    @RequiresPermissions("product:quickadd")
    @RequestMapping("/quickadd")
    public ModelAndView quickadd(ProductVO productVO, Long[] carIds){
        ModelAndView modelAndView=new ModelAndView(Global.FORWARD_PRODUCT_QUICK_ADD_PAGE);
        if(null!=carIds&&carIds.length>0){
            List<CarVO> cars=new ArrayList<CarVO>();
            for (Long cId:carIds){
                cars.add(carService.get(cId));
            }
            productVO.setCarStr(cars);
            productService.addProduct(productVO,cars);
        }
        return modelAndView;
    }

    /*修改数据库增加carstr字段
    @RequestMapping("/changesql")
    public String changeSql(){
        productService.changesql();
        return "redirect:/cartype/list";
    }
*/
}
