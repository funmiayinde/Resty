package com.topnotch.resty.consumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.topnotch.resty.util.Validator.checkNotNull;

/**
 * @author funmiayinde
 */
public class HttpConsumerFile {

    private String name;
    private String contentType;
    private InputStream inputStream;

    public HttpConsumerFile(String filepath) throws FileNotFoundException {
        this(new File(filepath));
    }

    public HttpConsumerFile(File file) throws FileNotFoundException {
        this.name = file.getName();
        this.contentType = HttpMimeTyper.getContentTypeFileName(name);
        this.inputStream = new FileInputStream(file);
    }

    public HttpConsumerFile(String name, String contentType, InputStream inputStream) {
        this.name = name;
        this.contentType = contentType;
        this.inputStream = checkNotNull(inputStream);
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
