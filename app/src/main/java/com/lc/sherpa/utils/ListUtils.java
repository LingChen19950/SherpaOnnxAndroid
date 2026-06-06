package com.lc.sherpa.utils;

import java.util.List;

public class ListUtils {

    /**
     * 判断列表是否为空
     * @param list
     * @return
     * @param <T>
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

}
