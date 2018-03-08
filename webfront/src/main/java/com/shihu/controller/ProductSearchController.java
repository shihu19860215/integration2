package com.shihu.controller;

import com.shihu.base.BaseController;
import com.shihu.entity.HomePageBean;
import com.shihu.model.common.Product;
import com.shihu.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search/product")
public class ProductSearchController extends BaseController {
    private final int searchProductIndex=2;
    @Autowired
    private ProductService productService;

    @RequestMapping("/search")
    public ModelAndView productSearch(String carName,String productName,String productVersion,String productRemark,String sort){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_SEARCH_LIST);
        modelAndView.addObject("homePageBean",homePageBean);
        if((null!=carName&&carName.length()>0)||
                (null!=productName&&productName.length()>0)||
                    (null!=productVersion&&productVersion.length()>0)){
            if(null!=carName&&carName.length()>0){
                modelAndView.addObject("carName",carName);
            }
            if(null!=productName&&productName.length()>0){
                modelAndView.addObject("productName",productName);
            }
            if(null!=productVersion&&productVersion.length()>0){
                modelAndView.addObject("productVersion",productVersion);
            }
            if(null!=productRemark&&productRemark.length()>0){
                modelAndView.addObject("productRemark",productRemark);
            }
            if(null!=sort&&sort.length()>0){
                modelAndView.addObject("sort",sort);
            }
            List<Product> products=productService.searchProduct(carName,productName,productVersion,productRemark,sort);
            modelAndView.addObject("products",products);
        }
        return  modelAndView;
    }

    @RequiresPermissions("product:searchtoupdatepage")
    @RequestMapping("/toupdatepage/{id}")
    public ModelAndView toUpdatePage(@PathVariable("id")Long id,String carName,String productName,String productVersion,String sort){
        ModelAndView modelAndView=new ModelAndView("home");
        HomePageBean homePageBean=HomePageBean.getHomePageBean(HomePageBean.PRODUCT_SEARCH_UPDATE);
        modelAndView.addObject("homePageBean",homePageBean);
        if(null!=carName&&carName.length()>0){
            modelAndView.addObject("carName",carName);
        }
        if(null!=productName&&productName.length()>0){
            modelAndView.addObject("productName",productName);
        }
        if(null!=productVersion&&productVersion.length()>0){
            modelAndView.addObject("productVersion",productVersion);
        }
        if(null!=sort&&sort.length()>0){
            modelAndView.addObject("sort",sort);
        }

        Product product=productService.getProductByIdWithCarTypeName(id);
        modelAndView.addObject("product",product);

        return modelAndView;
    }
}
