package com.topnotch.resty.util.common;

import com.topnotch.resty.util.exception.SerializationException;

import java.io.*;

/**
 * @author funmiayinde
 */
public class Serializer {

    /**
     * Serializer
     *
     * @param object Object
     * @return byte[]
     **/
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            if (object != null) {
                outputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(object);
                return outputStream.toByteArray();
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

    /**
     * Deserializer
     *
     * @param bytes Byte data
     * @return Object
     **/
    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            if (bytes != null) {
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream outputStream = new ObjectInputStream(byteArrayInputStream);
                return outputStream.readObject();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }
}
