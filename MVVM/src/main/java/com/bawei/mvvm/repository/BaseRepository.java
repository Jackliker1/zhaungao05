package com.bawei.mvvm.repository;

import com.bawei.mvvm.common.InjectModel;
import com.bawei.mvvm.exception.MVVMException;
import com.bawei.mvvm.model.IModel;

import java.lang.reflect.Field;

public abstract class BaseRepository {

    public BaseRepository(){
        inject();
    }

    private void inject() {

        boolean flag = false;
        Field[] declaredFields = getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectModel annotation = field.getAnnotation(InjectModel.class);
            if(annotation != null){
                flag = true;
                field.setAccessible(true);
                String name = field.getType().getName();
                try {
                    Class<?> aClass = Class.forName(name);
                    Object o = aClass.newInstance();
                    field.set(this,o);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!flag){
            throw new MVVMException("not find field by InjectModel...");
        }
    }

}
