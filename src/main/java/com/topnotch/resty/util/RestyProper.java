package com.topnotch.resty.util;

import com.topnotch.resty.Constant.RestyConstant;
import com.topnotch.resty.Resty;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author funmiayinde
 */
public class RestyProper {

    private static final Map<String, RestyProp> map = new ConcurrentHashMap<>();
    private static RestyProp prop = null;


    private RestyProper() {
    }

    /**
     * Using the properties file. It will loading the properties file if not loading
     *
     * @see #use(String, String)
     **/
    public static RestyProp use(String fileName) {
        return use(fileName, RestyConstant.encoding);
    }

    /**
     * Using the properties file. It will loading the properties file if not loading
     * <p/>
     * Example: <br/>
     * RestyProper.use("config.txt", "UTF-8")<br><br>
     * RestyProper.use("other_config.txt", "UTF-8")<br><br>
     * String username = RestyProper.get("username"); <br>
     * String password = RestyProper.get("password"); <br>
     * <p/>
     * username = RestyProper.use("other_config.txt").get("username");
     * password = RestyProper.use("other_config.txt").get("password")
     * <p/>
     *
     * @param filename the properties file's name in the classpath or the sub directory of classpath
     * @param encoding the encoding
     */
    public static RestyProp use(String filename, String encoding) {
        RestyProp prop = map.get(filename);
        if (prop == null) {
            prop = new RestyProp(filename, encoding);
            map.put(filename, prop);
            if (RestyProper.prop == null)
                RestyProper.prop = prop;
        }
        return prop;
    }

    /**
     * Using the properties file. It will loading the properties file if not loading
     *
     * @see #use(File, String)
     */
    public static RestyProp use(File file) {
        return use(file, RestyConstant.encoding);
    }

    /**
     * Using the properties file. It will loading the properties file if not loading
     * <p>
     * Example: <br>
     * RestyProper.use(new File("/var/config/config.txt", "UTF-8")
     * String username = RestyProper.use("/var/config/config.txt", "UTF-8");
     *
     * @param file     the properties File Object
     * @param encoding the encoding
     */
    public static RestyProp use(File file, String encoding) {
        RestyProp prop = map.get(file.getName());
        if (prop == null) {
            prop = new RestyProp(file, encoding);
            map.put(file.getName(), prop);

            if (RestyProper.prop == null) {
                RestyProper.prop = prop;
            }
        }
        return prop;
    }

    private static void clear() {
        prop = null;
        map.clear();
    }

    public static RestyProp getProp() {
        if (prop == null)
            throw new IllegalStateException("Load properties file invoking the RestyProper.use(String filename) method first");
        return prop;
    }

    public static RestyProp getProp(String filename) {
        return map.get(filename);
    }

    public static String get(String key) {
        return getProp().get(key);
    }

    public static String get(String key, String defaultValue) {
        return getProp().get(key, defaultValue);
    }

    public static Integer getInt(String key) {
        return getProp().getInt(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return getProp().getInt(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return getProp().getBoolean(key);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return getProp().getBoolean(key, defaultValue);
    }

    public static Long getLong(String key) {
        return getProp().getLong(key);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getProp().getLong(key, defaultValue);
    }

    public static boolean containKey(String key){
        return getProp().containKey(key);
    }
}

