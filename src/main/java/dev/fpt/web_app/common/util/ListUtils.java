package dev.fpt.web_app.common.util;

import java.util.List;

public class ListUtils {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}