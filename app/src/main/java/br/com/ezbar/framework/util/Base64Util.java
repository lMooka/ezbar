package br.com.ezbar.framework.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util {
    public static String encode(String data) throws UnsupportedEncodingException {
        return Base64.encodeToString(data.getBytes("UTF-8"), Base64.DEFAULT);
    }

    public static String decode(String data) throws UnsupportedEncodingException {
        return new String(Base64.decode(data, Base64.DEFAULT), "UTF-8");
    }
}
