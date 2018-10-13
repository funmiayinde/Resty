package com.topnotch.resty.util;

import com.topnotch.resty.Constant.RestyConstant;
import org.fusesource.jansi.Ansi;

/**
 * @author funmiayinde
 */
public class Painter {

    public static String black(String value) {
        return paint("black", value);
    }

    public static String red(String value) {
        return paint("red", value);
    }

    public static String cyan(String value) {
        return paint("cyan", value);
    }

    public static String white(String value) {
        return paint("white", value);
    }

    public static String blue(String value) {
        return paint("blue", value);
    }

    public static String green(String value) {
        return paint("green", value);
    }

    public static String yellow(String value) {
        return paint("yellow", value);
    }

    public static String paint(String color, String value) {
        if (RestyConstant.developmentMode) {
            return String.valueOf(Ansi.ansi().eraseScreen().render("@|" + color + " " + value + "@|"));
        } else {
            return value;
        }
    }
}
