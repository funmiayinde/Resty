package com.topnotch.resty.consumer;

import com.topnotch.resty.util.RestyProper;
import com.topnotch.resty.util.common.Itemize;

import java.util.Iterator;
import java.util.Properties;

import static com.topnotch.resty.util.Validator.validateArgument;

/**
 * @author funmiayinde
 */
public class HttpMimeTyper {

    private static final Properties mimeType;
    private static final String RFC_2616_TOKEN_SPECIAL_CHARS_REGEX = "[\\s\\(\\)<>@,;:\\\\\"/\\[\\]\\?=\\{\\}]";

    static {
        mimeType = RestyProper.use("mime-types.properties").getProperties();
        for (String prop : mimeType.stringPropertyNames()) {
            Iterable<String> types = Itemize.of(mimeType.getProperty(prop));
            Iterator<String> iterator = types.iterator();
            mimeType.setProperty(prop, iterator.hasNext() ? iterator.next() : "application/octet-streem");
        }
    }

    public static String getContentTypeFileName(String filename) {
        String ext = filename.substring(filename.lastIndexOf('.') + 1);
        return mimeType.getProperty(ext);
    }

    public static String getContentTypeFromExtension(String ext) {
        return mimeType.getProperty(ext);
    }

    public static boolean isTextContentType(String contentType) {
        return contentType.startsWith("text/")
                || contentType.startsWith("application/json")
                || contentType.startsWith("application/javascript")
                || contentType.startsWith("application/ecmascript")
                || contentType.startsWith("application/atom+xml")
                || contentType.startsWith("application/rss+xml")
                || contentType.startsWith("application/xhtml+xml")
                || contentType.startsWith("application/soap+xml")
                || contentType.startsWith("application/xml")
                || contentType.startsWith("application/x-authorware-bin");
    }

    public static String charsetFromContentType(String s) {
        if (!s.contains("charset=")) {
            return null;

        } else {
            return s.substring(s.indexOf("charset=") + "charset=".length());
        }
    }

    public static String headerTokenCompatible(String s, String specialCharsReplacement) {
        validateArgument(specialCharsReplacement.replaceAll(RFC_2616_TOKEN_SPECIAL_CHARS_REGEX, "blah").equals(specialCharsReplacement),
                "specialCharsReplacement `%s` is not itself compatible with rfc 2616 !",
                specialCharsReplacement);

        return s.replaceAll(RFC_2616_TOKEN_SPECIAL_CHARS_REGEX, specialCharsReplacement);
    }

}
