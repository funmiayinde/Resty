package com.topnotch.resty.util.common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author funmiayinde
 */
public class Itemize {

    public static <T> List<T> of(Object... objects) {
        if (objects == null || objects.length == 0)
            return new ArrayList<>();

        return (List<T>) Arrays.asList(objects);
    }

    public static <T> List<T> copyof(List<T> objects) {
        if (objects == null || objects.size() == 0)
            return new ArrayList<>();

        return (List<T>) Serializer.deserialize(Serializer.serialize(objects));
    }

    public static <T> List<T> copyOf(List<T> dist, List<T> source) {
        if (source == null || source.size() == 0)
            return dist;

        dist.addAll((List<T>) Serializer.deserialize(Serializer.serialize(source)));
        return dist;
    }


}
