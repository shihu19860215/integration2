package com.shihu.entity;

import java.util.HashMap;
import java.util.Map;

public class PagePrompt {
    private static Map<Integer,PagePrompt> pagePromptManager=new HashMap<Integer, PagePrompt>();
    private Integer id;
    private String message;

    public static PagePrompt getPagePrompt(Integer id){
        return pagePromptManager.get(id);
    }

    private PagePrompt(Integer id,String message) {
        this.id=id;
        this.message=message;
    }

    public int getId() {
        return id;
    }

    public PagePrompt setId(int id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PagePrompt setMessage(String message) {
        this.message = message;
        return this;
    }


    private static Map<Integer,String> promptMessageMap;
    public final static Integer USER_PASSWORD_ERROR=1;
    public final static Integer ADD_INFO_NOT_EMPTY=2;
    public final static Integer ADD_INFO_REPART=3;
    public final static Integer CARTYPE_NOT_EMPTY=4;
    public final static Integer CAR_NOT_EMPTY=5;
    public final static Integer ADD_ORDER_ERROR_PRODCUT_INVAILD=6;
    public final static Integer ADD_ORDER_ERROR_PRODCUT_NUM_LESS=7;
    public final static Integer SUB_PRODCUT_ERROR_NUM_LESS=8;
    static {
        pagePromptManager.put(USER_PASSWORD_ERROR,new PagePrompt(USER_PASSWORD_ERROR,"用户名密码错误"));
        pagePromptManager.put(ADD_INFO_NOT_EMPTY,new PagePrompt(ADD_INFO_NOT_EMPTY,"添加信息不能为空"));
        pagePromptManager.put(ADD_INFO_REPART,new PagePrompt(ADD_INFO_REPART,"添加信息重复"));
        pagePromptManager.put(CARTYPE_NOT_EMPTY,new PagePrompt(CARTYPE_NOT_EMPTY,"该车型含有车信息不为空，无法删除"));
        pagePromptManager.put(CAR_NOT_EMPTY,new PagePrompt(CAR_NOT_EMPTY,"该车含有商品信息不为空，无法删除"));
        pagePromptManager.put(ADD_ORDER_ERROR_PRODCUT_INVAILD,new PagePrompt(ADD_ORDER_ERROR_PRODCUT_INVAILD,"增加订单失败，订单含有失效商品"));
        pagePromptManager.put(ADD_ORDER_ERROR_PRODCUT_NUM_LESS,new PagePrompt(ADD_ORDER_ERROR_PRODCUT_NUM_LESS,"增加订单失败，订单商品数大于实际商品数"));
        pagePromptManager.put(SUB_PRODCUT_ERROR_NUM_LESS,new PagePrompt(SUB_PRODCUT_ERROR_NUM_LESS,"减少商品数量失败，商品数量不能小于0"));
    }
}
