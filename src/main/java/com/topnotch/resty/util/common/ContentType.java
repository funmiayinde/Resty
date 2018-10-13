package com.topnotch.resty.util.common;

public enum ContentType {

    TEXT("text", "text/plain"),
    HTML("html", "text/html"),
    XML("xml", "text/xml"),
    JSON("json", "application/json"),
    FORM("form", "application/x-www-form-urlencoded"),
    MULTIPART("multipart", "multipart/form-data"),
    FILE("file", "application/octet-stream"),
    PNG("png", "image/png"),
    JPEG("jpg", "image/jpeg"),
    GIF("gif", "image/gif");

    private String type;
    private String value;

    private ContentType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static ContentType typeOf(String type) {
        for (ContentType contentType : values()) {
            if (contentType.type.equals(type)) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Invalid content type:" + type);
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
