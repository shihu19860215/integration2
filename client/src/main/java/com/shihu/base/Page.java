package com.shihu.base;

import com.shihu.config.Global;
import com.shihu.utils.StringUtils;

import java.util.LinkedHashMap;

public class Page<T> {
    /**
     * 分页查询条件对象
     */
    private T entity;
    private LinkedHashMap<String,Object> parm;
    /**\
     * 当前页数
     */
    private int pageNum=1;
    /**
     * 页面显示开始页数
     */
    private int startPage;

    /**
     * 开始查询条数
     */
    private int startNum=0;
    /**
     * 页面显示结束页数
     */
    private int endPage;
    /**
     * 页面总页数
     */
    private int pageCount;
    /**
     * 数据总数
     */
    private int count;
    /**
     * 单页显示条数
     */
    private int pageSize=default_pageSize;
    private int maxPageCount=default_maxPageCount;


    public T getEntity() {
        return entity;
    }

    public Page setEntity(T entity) {
        this.entity = entity;
        return this;
    }

    public LinkedHashMap<String, Object> getParm() {
        return parm;
    }

    public Page setParm(LinkedHashMap<String, Object> parm) {
        this.parm = parm;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Page setPageNum(int pageNum) {
        this.pageNum = pageNum;
        startNum=(pageNum-1)*pageSize;
        return this;
    }

    public int getStartNum() {
        return startNum;
    }

    public Page setStartNum(int startNum) {
        this.startNum = startNum;
        return this;
    }
    public int getStartPage() {
        return startPage;
    }

    public Page setStartPage(int startPage) {
        this.startPage = startPage;
        return this;
    }

    public int getEndPage() {
        return endPage;
    }

    public Page setEndPage(int endPage) {
        this.endPage = endPage;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Page setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Page setCount(int count) {
        this.count = count;
        computePage();
        return this;
    }

    public void computePage(){
        if(count%pageSize>0){
            pageCount=count/pageSize+1;
        }else {
            pageCount=count/pageSize;
        }
        if(pageCount<=maxPageCount){
            startPage=1;
            endPage=pageCount;
        }else {
            if(pageNum<=maxPageCount/2){
                startPage=1;
                endPage=maxPageCount;
            }else if(pageNum>=pageCount-maxPageCount/2){
                startPage=pageCount-maxPageCount+1;
                endPage=startPage+maxPageCount-1;
            }else {
                startPage=pageNum-maxPageCount/2+1;
                endPage=startPage+maxPageCount-1;
            }
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getMaxPageCount() {
        return maxPageCount;
    }

    public Page setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
        return this;
    }

    public String toKey(){
        if(null!=parm){
            return pageNum+"_"+pageSize+"_"+parm.toString();
        }
        return pageNum+"_"+pageSize+"_";
    }

    public String toParmKey(){
        if(null!=parm){
            return parm.toString();
        }else {
            return StringUtils.EMPTY;
        }

    }
    /**
     * 默认单页显示条数
     */
    private static int default_pageSize= Global.getConfigToInt("pageSize");
    /**
     * 默认最大显示选着页数
     */
    private static int default_maxPageCount= Global.getConfigToInt("maxPageCount");

}
