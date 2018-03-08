package com.shihu.entity;

import com.shihu.base.Page;

import java.util.HashMap;
import java.util.Map;

public class HomePageBean{
    private int index;
    private String includePage;
    private HomePageBean(int index,String includePage){
        this.index=index;
        this.includePage=includePage;
    }

    public int getIndex() {
        return index;
    }

    public String getIncludePage() {
        return includePage;
    }


    private static Map<String,HomePageBean> homePageBeanFactory;

    private static final int PIN_PAI_CHE_XIN = 1;
    private static final int SHANG_PIN_SOU_SHUO = 2;
    private static final int KUAI_SU_TIAN_JIA= 3;
    private static final int DANG_QIAN_DING_DAN = 4;
    private static final int DING_DAN_GUAN_LI = 11;
    private static final int KE_HU_GUAN_LI = 12;
    private static final int CHA_KAN_RI_ZHI = 13;
    public static final String CARTYPE_LIST= "cartype";
    public static final String CAR_LIST= "car";
    public static final String PRODUCT_LIST= "product";
    public static final String PRODUCT_ADD= "product_add";
    public static final String PRODUCT_UPDATE= "product_update";
    public static final String PRODUCT_QUICK_ADD= "product_quick_add";
    public static final String PRODUCT_SEARCH_LIST= "product_search";
    public static final String PRODUCT_SEARCH_UPDATE= "product_search_update";
    public static final String ORDER_ADD= "order_add";
    public static final String ORDER_DISPLAY= "order_display";
    public static final String ORDER_LIST= "order";
    public static final String CUSTOMER_LIST= "customer";
    public static final String CUSTOMER_ADD= "customer_add";
    public static final String CUSTOMER_UPDATE= "customer_update";
    public static final String LOG_LIST= "log";

    static {
        homePageBeanFactory=new HashMap<String, HomePageBean>();
        //1
        homePageBeanFactory.put(CARTYPE_LIST,new HomePageBean(PIN_PAI_CHE_XIN,CARTYPE_LIST));
        homePageBeanFactory.put(CAR_LIST,new HomePageBean(PIN_PAI_CHE_XIN,CAR_LIST));
        homePageBeanFactory.put(PRODUCT_LIST,new HomePageBean(PIN_PAI_CHE_XIN,PRODUCT_LIST));
        homePageBeanFactory.put(PRODUCT_ADD,new HomePageBean(PIN_PAI_CHE_XIN,PRODUCT_ADD));
        homePageBeanFactory.put(PRODUCT_UPDATE,new HomePageBean(PIN_PAI_CHE_XIN,PRODUCT_UPDATE));

        //2
        homePageBeanFactory.put(PRODUCT_SEARCH_LIST,new HomePageBean(SHANG_PIN_SOU_SHUO,PRODUCT_SEARCH_LIST));
        homePageBeanFactory.put(PRODUCT_SEARCH_UPDATE,new HomePageBean(SHANG_PIN_SOU_SHUO,PRODUCT_SEARCH_UPDATE));

        //3
        homePageBeanFactory.put(PRODUCT_QUICK_ADD,new HomePageBean(KUAI_SU_TIAN_JIA,PRODUCT_QUICK_ADD));

        //4
        homePageBeanFactory.put(ORDER_ADD,new HomePageBean(DANG_QIAN_DING_DAN,ORDER_ADD));

        //11
        homePageBeanFactory.put(ORDER_DISPLAY,new HomePageBean(DING_DAN_GUAN_LI,ORDER_DISPLAY));
        homePageBeanFactory.put(ORDER_LIST,new HomePageBean(DING_DAN_GUAN_LI,ORDER_LIST));

        //12
        homePageBeanFactory.put(CUSTOMER_LIST,new HomePageBean(KE_HU_GUAN_LI,CUSTOMER_LIST));
        homePageBeanFactory.put(CUSTOMER_ADD,new HomePageBean(KE_HU_GUAN_LI,CUSTOMER_ADD));
        homePageBeanFactory.put(CUSTOMER_UPDATE,new HomePageBean(KE_HU_GUAN_LI,CUSTOMER_UPDATE));


        //13
        homePageBeanFactory.put(LOG_LIST,new HomePageBean(CHA_KAN_RI_ZHI,LOG_LIST));
    }

    public static HomePageBean getHomePageBean(String key){
        return homePageBeanFactory.get(key);
    }

}
