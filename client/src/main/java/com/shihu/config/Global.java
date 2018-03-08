package com.shihu.config;

import com.shihu.cache.CacheFactory;
import com.shihu.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.Cache;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {
    private static final String proFilePath="properties/pro.properties";
    /**
     * 保存全局属性值
     */
    private static Cache<String,String> cache;
    /**
     * 属性文件加载对象
     */
    private static  Properties props;
    static{
        init();
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = cache.get(key);
        if (value == null){
            value = props.getProperty(key);
            cache.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    public static Integer getConfigToInt(String key){
        String str=getConfig(key);
        if(StringUtils.isEmpty(str)){
            return 0;
        }else {
            return Integer.valueOf(str);
        }
    }

    private static void init(){
        initCache();
        initProperties();
    }
    private static void initCache(){
        cache= CacheFactory.getCache(Global.class.getName());
    }

    private static void initProperties(){
        props = new Properties();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream is = null;
        try {
            Resource resource = resourceLoader.getResource(proFilePath);
            is = resource.getInputStream();
            props.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static final String REDIRECT_HOME="redirect:/home";
    public static final String REDIRECT_CARTYPE_LIST="redirect:/cartype/list";
    public static final String REDIRECT_CAR_LIST="redirect:/car/list";
    public static final String REDIRECT_PRODUCT_LIST="redirect:/product/list";
    public static final String REDIRECT_CUSTOMER_LIST="redirect:/customer/list";

    public static final String FORWARD_PRODUCT_QUICK_ADD_PAGE="forward:/product/toquickaddpage";

}
