package com.topnotch.resty.consumer;

import com.topnotch.resty.util.common.ContentType;
import com.topnotch.resty.util.common.Encoding;
import com.topnotch.resty.util.common.Mapper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.topnotch.resty.util.Validator.checkNotNull;

/**
 * @author funmiayinde
 */
public class HttpConsumerRequest {

    private String restPath;
    private String encoding = Encoding.UTF_8.name();
    private Map<String, String> params = Mapper.of();
    private String jsonParams;
    private Map<String, String> headers = Mapper.of();
    private int connectionTimeout = 10000;
    private int readTimeout = 1000;
    private boolean overwrite = false;
    private String downloadFile;
    private Map<String, HttpConsumerFile> uploadFiles = Mapper.of();
    private String contentType = ContentType.FORM.getValue() + ";charset=" + encoding;
    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";

    public HttpConsumerRequest() {
        this("");
    }

    public HttpConsumerRequest(String restPath) {
        this(restPath, Encoding.UTF_8.name());
    }

    public HttpConsumerRequest(String restPath, String encoding) {
        this(restPath, encoding, Mapper.of());
    }

    public HttpConsumerRequest(String restPath, String encoding, Map<String, String> params) {
        this(restPath, encoding, params, Mapper.of());
    }

    public HttpConsumerRequest(String restPath, String encoding, Map<String, String> params, Map<String, String> headers) {
        this.restPath = checkNotNull(restPath);
        if (encoding != null) {
            this.encoding = encoding;
            this.contentType = ContentType.FORM.getValue() + ";charset=" + encoding;
        }
        this.params = params;
        this.headers = headers;
    }

    public String getRestPath() {
        return restPath;
    }

    public String getEncodedRestPath() throws UnsupportedEncodingException {
        String url = this.restPath;
        String tmp;
        Matcher matcher = Pattern.compile("[^a-zA-Z0-9\\/\\?#&=\\+\\*\\$@!%\\.\\|\\(\\)\\^\\{\\}\\[\\]]").matcher(url);
        while (matcher.find()) {
            tmp = matcher.group();
            url = url.replaceAll(tmp, URLEncoder.encode(tmp, encoding));
        }
        return url;
    }

    public String getEncoding() {
        return encoding;
    }

    public HttpConsumerRequest setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpConsumerRequest setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public HttpConsumerRequest setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public HttpConsumerRequest addParams(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public HttpConsumerRequest setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public HttpConsumerRequest setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getJsonParams() {
        return jsonParams;
    }

    public HttpConsumerRequest setJsonParams(String jsonParams) {
        setContentType(ContentType.JSON.getValue() + ";charset=" + encoding);
        this.jsonParams = checkNotNull(jsonParams, "Json params cannot be null");
        return this;
    }

    public String getEncodedJsonParams() throws UnsupportedEncodingException {
        return URLEncoder.encode(jsonParams, encoding);
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public HttpConsumerRequest setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public HttpConsumerRequest setDownloadFile(String downloadFile, boolean overwrite) {
        this.downloadFile = checkNotNull(downloadFile, "Download file cannot be null");
        this.overwrite = overwrite;
        return this;
    }

    public String getDownloadFile() {
        return downloadFile;
    }

    public HttpConsumerRequest setDownloadFile(String downloadFile) {
        setDownloadFile(downloadFile, false);
        return this;
    }

    public Map<String, HttpConsumerFile> getUploadFiles() {
        return uploadFiles;
    }

    public HttpConsumerRequest setUploadFiles(Map<String, HttpConsumerFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
        setContentType(ContentType.MULTIPART.getValue() + ";charset=" + encoding);
        return this;
    }

    public HttpConsumerRequest addUploadFiles(String name, String filepath) throws FileNotFoundException {
        this.uploadFiles.put(name, new HttpConsumerFile(filepath));
        setContentType(ContentType.MULTIPART.getValue() + ";charset=" + encoding);
        return this;
    }


    /**
     * @param name        param name
     * @param filename    file name
     * @param contentType
     * @param fileStream
     * @return
     */
    public HttpConsumerRequest addUploadFiles(String name, String filename, String contentType, InputStream fileStream) throws FileNotFoundException {
        this.uploadFiles.put(name, new HttpConsumerFile(filename, contentType, fileStream));
        setContentType(ContentType.MULTIPART.getValue() + ";charset=" + encoding);
        return this;
    }

    public String getEncodingParams() throws UnsupportedEncodingException {
        String encodedParams = "";
        if (!this.params.isEmpty()) {
            Set<String> paramsKey = this.params.keySet();
            boolean isFirstParams = true;
            String value = null;
            for (String key : paramsKey) {
                value = this.params.get(key);
                if (value == null)
                    continue;

                if (isFirstParams) {
                    encodedParams += key + "=" + URLEncoder.encode(value, this.getEncoding());
                    isFirstParams = false;
                } else {
                    encodedParams += "&" + key + "=" + URLEncoder.encode(value, this.getEncoding());
                }
            }
        }
        return encodedParams.trim();
    }

    public String getUnEncodedParams() {
        String params = "";
        if (!this.params.isEmpty()) {
            Set<String> paramKeys = this.params.keySet();
            boolean isFirstParam = true;
            String value = null;
            for (String key : paramKeys) {
                value = this.params.get(key);
                if (value == null) continue;
                if (isFirstParam) {
                    params += key + "=" + value;
                    isFirstParam = false;
                } else {
                    params += "&" + key + "=" + value;
                }
            }
        }

        return params.trim();
    }

    public String getEncodedUrl() throws UnsupportedEncodingException {
        String encodedUrl = this.getEncodedRestPath();
        if (!this.params.isEmpty()) {
            encodedUrl += "?";
            Set<String> paramKeys = this.params.keySet();
            boolean isFirstParam = true;
            String value = null;
            for (String key : paramKeys) {
                value = this.params.get(key);
                if (value == null) continue;
                if (isFirstParam) {
                    encodedUrl += key + "=" + URLEncoder.encode(value, this.getEncoding());
                    isFirstParam = false;
                } else {
                    encodedUrl += "&" + key + "=" + URLEncoder.encode(value, this.getEncoding());
                }
            }
        }
        return encodedUrl.trim();
    }

    public String getUnEncodedUrl() {
        String url = this.getRestPath();
        if (!this.params.isEmpty()) {
            url += "?";
            Set<String> paramKeys = this.params.keySet();
            boolean isFirstParam = true;
            String value = null;
            for (String key : paramKeys) {
                value = this.params.get(key);
                if (value == null) continue;
                if (isFirstParam) {
                    url += key + "=" + value;
                    isFirstParam = false;
                } else {
                    url += "&" + key + "=" + value;
                }
            }
        }
        return url.trim();
    }
}
