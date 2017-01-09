package com.busnoseat.common.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type BeanConverterUtil.
 * @Description: bean转化工具
 * @author liheng
 * @Date 2016/3/10
 */
public class ConverterBeanUtil {

    private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap<String, BeanCopier>();

    /**
     * @param source
     *            源对象class
     * @param target
     *            目标对对象class
     * @param sourceObj
     *            复制的源对象
     * @param useConverter
     * @return
     * @throws Exception
     */
    public static <T> T copyBeanProperties(final Class source, final Class<T> target,
                                           final Object sourceObj, final boolean useConverter) {
        if (sourceObj == null) {
            return null;
        }
        T t;
        try {
            t = target.newInstance();
        } catch (Exception e) {
            return null;
        }
        String key = source.getSimpleName() + target.getSimpleName();
        BeanCopier copier = cache.get(key);
        if (copier == null) {
            copier = createBeanCopier(source, target, useConverter, key);
        }
        copier.copy(sourceObj, t, null);
        return t;
    }

    /**
     *
     *
     * @param sourceObj
     *            源对象
     * @param target
     *            目标对象
     * @return
     * @throws Exception
     */
    public static <T> T copyBeanProperties(final Object sourceObj, final T target) {
        return copyBeanProperties(sourceObj, target, false);
    }

    /**
     * @param sourceObj
     *            源对象
     * @param target
     *            目标对象
     * @param useConverter
     * @return
     * @throws Exception
     */
    public static <T> T copyBeanProperties(final Object sourceObj, final T target,
                                           final boolean useConverter) {
        if (sourceObj == null || target == null) {
            return null;
        }
        String key = sourceObj.getClass().getSimpleName()
                     + target.getClass().getSimpleName();
        BeanCopier copier = cache.get(key);
        if (copier == null) {
            copier = createBeanCopier(sourceObj.getClass(), target.getClass(),
                    useConverter, key);
        }
        copier.copy(sourceObj, target, null);
        return target;
    }

    /**
     *
     * @param sourceObjs
     * @param targets
     * @param targetType
     * @return
     */
    public static <T> List<T> copyListBeanPropertiesToList(final List<?> sourceObjs,
                                                           final List<T> targets, final Class<T> targetType) {
        if (sourceObjs == null || targets == null || targetType == null) {
            return null;
        }
        T t;
        for (Object o : sourceObjs) {
            try {
                t = targetType.newInstance();
                targets.add(copyBeanProperties(o, t, false));
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return targets;
    }

    @SuppressWarnings("unused")
    private static String getHashKey(final String str) {
        if (str == null) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    @SuppressWarnings({ "rawtypes" })
    private static BeanCopier createBeanCopier(final Class sourceClass,
                                               final Class targetClass, final boolean useConverter, final String cacheKey) {
        BeanCopier copier = BeanCopier.create(sourceClass, targetClass,
                useConverter);
        cache.putIfAbsent(cacheKey, copier);
        return copier;
    }
}

