package com.topnotch.resty.Constant;

import com.topnotch.resty.log.Logger;
import com.topnotch.resty.util.RestyProp;
import com.topnotch.resty.util.RestyProper;
import com.topnotch.resty.util.common.Encoding;

import java.io.File;

/**
 * @author funmiayinde
 */
public final class RestyConstant {

    public final static String CONNECTOR = "::";
    public final static String encoding;//coding
    public final static boolean developmentMode; // Whether to use development mode
    public final static boolean oneParamParse; // Single parameter does not pass parameter name
    public final static String uploadDirectory; // File upload default directory
    public final static Integer uploadMaxSize; // File upload max size 10 mega
    public final static String[] uploadDenieds; // Set file content type e.g text/xml File
    public final static String fileModifier; // File upload rename class
    public final static boolean showRoute; // Print route matching information when requesting
    public final static String apiPrefix; // The api development flag such as /api/v1.0/xxx start prefix
    // /api is a flag (when the api request does not match the route,
    // it will return 404 status) If it is a separate domain name,
    // you can not configure the item so the url is api Access the url of the foward when the non-api type request is
    // not matched to the route (inconsistent with the processing when the api request)
    public final static String expectionHandler; // exception deal with
    public static final String[] xForwardedSupport;

    public static final String oauthSignInUrl;
    public static final String oauthErrorUrl;
    public static final int oauthExpires;

    private final static Logger logger = Logger.getLogger(RestyConstant.class);

    static {
        RestyProp prop = null;
        try {
            prop = RestyProper.use("application.properties");
        } catch (Exception e) {
            logger.w(e.getMessage(), e);
        }

        if (prop == null) {
            encoding = Encoding.UTF_8.toString();
            developmentMode = false;
            oneParamParse = false;
            uploadDirectory = File.separator + "upload" + File.separator;
            uploadMaxSize = 1024 * 1024 * 10;
            uploadDenieds = new String[]{};
            fileModifier = null;
            showRoute = false;
            apiPrefix = null;
            expectionHandler = null;
            xForwardedSupport = new String[]{"127.0.0.1"};
            oauthSignInUrl = "";
            oauthErrorUrl = "";
            oauthExpires = 0;

        } else {
            encoding = prop.get("resty.encoding", Encoding.UTF_8.name());
            developmentMode = prop.getBoolean("resty.developmentMode", false);
            oneParamParse = prop.getBoolean("resty.oneParamParse", false);
            uploadDirectory = prop.get("resty.uploadDirectory", File.separator + "upload" + File.separator);
            uploadMaxSize = prop.getInt("resty.uploadMaxSize", 1024 * 1024 * 10);
            String uploadDeinedStr = prop.get("resty.uploadDeined");
            if (uploadDeinedStr == null) {
                uploadDenieds = new String[]{};
            } else {
                uploadDenieds = uploadDeinedStr.split(",");
            }

            fileModifier = prop.get("resty.fileModifier");
            showRoute = prop.getBoolean("resty.showRoute", false);
            apiPrefix = prop.get("resty.apiPrefix");
            expectionHandler = prop.get("resty.exceptionHanlder");
            String xForwardedSupportStr = prop.get("resty.xForwardedSupport");
            if (xForwardedSupportStr == null) {
                xForwardedSupport = new String[]{"127.0.0.1"};
            } else {
                xForwardedSupport = xForwardedSupportStr.split(",");
            }
            oauthErrorUrl = prop.get("resty.oauthErrorUrl");
            oauthExpires = prop.getInt("resty.oauthExpires", 0);
            oauthSignInUrl = prop.get("resty.oauthSignInUrl");

        }

    }


}
