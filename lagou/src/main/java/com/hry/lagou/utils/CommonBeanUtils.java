package com.hry.lagou.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CommonBeanUtils {

    /**
     * 将 orig对象的属性复印到dest对象中
     * @param dest
     * @param orig
     */
    public static void copyProperties(Object dest, Object orig){
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
