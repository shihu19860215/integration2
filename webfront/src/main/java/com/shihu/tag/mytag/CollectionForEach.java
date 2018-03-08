package com.shihu.tag.mytag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionForEach extends TagSupport {
    private List list;
    private int start=0;
    private int inc=1;
    private int index;
    private int size;
    private String itemId;
    private Object item;

    @Override
    public int doStartTag() throws JspException {
        if(size>0){
            index=start;
            if(index>=size)return SKIP_BODY;
            setCurrentItem();
            return EVAL_BODY_INCLUDE;
        }else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doAfterBody() throws JspException {
        index+=inc;
        if(index>=size)return SKIP_BODY;
        setCurrentItem();
        return EVAL_BODY_AGAIN;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    public void setVar(String var){
        this.itemId=var;
    }

    public void setItems(Collection collection){
        if(null==collection||collection.size()==0){
            list=new ArrayList();
        }else if (collection instanceof  List){
            list=(List)collection;
        }else {
            list=new ArrayList();
            Iterator it=collection.iterator();
            while(it.hasNext()){
                list.add(it.next());
            }
        }
        this.size=list.size();
    }

    private void setCurrentItem(){
        item=list.get(index);
        pageContext.setAttribute(itemId,item);
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
