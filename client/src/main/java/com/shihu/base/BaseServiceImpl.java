package com.shihu.base;

import com.shihu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> baseDao;
    public T get(Long id) {
        return getBaseDao().get(id);
    }

    public Long insert(T t) {
        return getBaseDao().insert(t);
    }

    public Long update(T t) {
        return getBaseDao().update(t);
    }

    public List<T> listAll() {
        return getBaseDao().listAll();
    }

    public List<T> list(Page<T> t) {
        return getBaseDao().list(t);
    }

    public Long delete(Long id){
        return getBaseDao().delete(id);
    }

    public BaseDao<T> getBaseDao(){
        if(null==baseDao){
            try {
                String filedName=this.getClass().getSimpleName().replace("ServiceImpl","Dao");
                filedName= StringUtils.toLowerCaseFirstOne(filedName);
                Field field=this.getClass().getDeclaredField(filedName);
                field.setAccessible(true) ;
                baseDao= (BaseDao<T>)field.get(this);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return baseDao;
    }
}
