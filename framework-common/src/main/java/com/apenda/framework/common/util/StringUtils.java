package com.apenda.framework.common.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rui.zhou
 * @date 2021/05/26 14:56
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 空值判断
     *
     * @param str
     * @return
     */
    public static boolean isBlankOrEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str) || org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    /**
     * string转Set
     *
     * @param str
     * @param regex
     * @return
     */
    public static Set<String> stringToSet(String str, String regex) {
        String[] split = StringUtils.defaultIfBlank(str,StringUtils.EMPTY).split(regex);
        Set<String> hashSet = new HashSet<>();
        Collections.addAll(hashSet, split);
        return hashSet;
    }

}
