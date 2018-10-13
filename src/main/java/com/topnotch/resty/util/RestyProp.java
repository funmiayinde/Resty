package com.topnotch.resty.util;

import com.topnotch.resty.Constant.RestyConstant;
import com.topnotch.resty.log.Logger;

import java.io.*;
import java.util.Properties;

/**
 * @author funmiayinde
 */
public class RestyProp {

    private final static Logger logger = Logger.getLogger(RestyProp.class);

    private Properties properties = null;

    private static String rootClassPath = null;

    /**
     * RestyProp constructor
     *
     * @see #RestyProp(String, String)
     */
    public RestyProp(String fileName) {
        this(fileName, RestyConstant.encoding);
    }

    /**
     * RestyProp Constructor
     * <p/>
     * Example <br>
     * RestyProp restyProp = new RestyProp("my_config.txt","UTF-8");
     * String userName = restyProp.get("username");<br><br>
     * <p/>
     *
     * @param fileName the Properties file's name in classpath or the sub directory of classpath
     * @param encoding the encoding
     */
    public RestyProp(String fileName, String encoding) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        load(fileName, inputStream, encoding);
    }

    /**
     * RestyProp Constructor
     *
     * @see #RestyProp(File, String)
     */
    public RestyProp(File file) {
        this(file, RestyConstant.encoding);
    }

    /**
     * RestyProp Constuctor
     * <p/>
     * <p>
     * Example: <br/>
     * RestyProp prop = new RestyProp(new File("/home/config/config.txt", "UTF-8");
     * String username = prop.get("username");
     *
     * @param file     the properties of the File Object
     * @param encoding the encoding
     */
    public RestyProp(File file, String encoding) {
        if (file == null)
            throw new IllegalArgumentException("File cannot be empty or null");

        String fileName = file.getName();
        if (!file.isFile())
            throw new IllegalArgumentException("Not a file: " + file);

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            load(fileName, inputStream, encoding);
        } catch (FileNotFoundException e) {
            logger.w(e.getMessage(), e);
        }
    }

    public void load(String fileName, InputStream inputStream, String encoding) {
        if (inputStream == null)
            throw new IllegalArgumentException("Properities file not found in classpath: " + fileName);

        try {
            properties = new Properties();
            properties.load(new InputStreamReader(inputStream, encoding == null ? "UTF-8" : encoding));
        } catch (IOException e) {
            throw new RuntimeException("Error loading properities file:", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.w(e.getMessage(), e);
            }
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        String value = get(key);
        return (value != null) ? value : defaultValue;
    }

    public Integer getInt(String key) {
        String value = get(key);
        return (value != null) ? Integer.parseInt(value) : null;
    }

    public Integer getInt(String key, Integer defaultValue) {
        String value = get(key);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    public Boolean getBoolean(String key) {
        String value = get(key);
        return (value != null) ? Boolean.parseBoolean(value) : null;
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = get(key);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public Long getLong(String key) {
        String value = get(key);
        return (value != null) ? Long.parseLong(value) : null;
    }

    public Long getLong(String key, Long defaultValue) {
        String value = get(key);
        return (value != null) ? Long.parseLong(value) : defaultValue;
    }

    public boolean containKey(String key) {
        return properties.containsKey(key);
    }

    public Properties getProperties(){
        return properties;
    }

    public static String getRootClassPath() {
        if (rootClassPath != null) {
            try {
                String path = RestyProp.class.getClassLoader().getResource("").toURI().getPath();
                rootClassPath = new File(path).getAbsolutePath();
            } catch (Exception e) {
                String path = RestyProp.class.getClassLoader().getResource("").getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }
        }
        return rootClassPath;
    }
}
