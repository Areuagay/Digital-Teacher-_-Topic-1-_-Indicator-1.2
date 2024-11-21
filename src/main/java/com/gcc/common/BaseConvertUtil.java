package com.gcc.common;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseConvertUtil {

    public static <K> K convert(Object source, Class<K> clazz, String... properties) {
        if (source == null) {
            return null;
        }
        K target = (K) BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(source, target, properties);
        return target;
    }

    public static <K> List<K> convertList(List<?> sourceList, Class<K> clazz, String... propertis) {
        List<K> list = new ArrayList<>();
        if (null == sourceList || sourceList.size() < 1) {
            return list;
        }
        for (Object source : sourceList) {
            if (null == source) {
                continue;
            }
            K target = convert(source, clazz, propertis);
            list.add(target);
        }
        return list;
    }

}