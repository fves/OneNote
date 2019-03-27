package cn.fves24;

import cn.fves24.vo.Note;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存一些常量
 *
 * @author fves
 */
public class Constants {
    public static String key;
    public static boolean isLogin = false;
    public static String account;
    public static Map<String,Note> noteMap = new HashMap<>();
    public static MainView mainView = null;
}
