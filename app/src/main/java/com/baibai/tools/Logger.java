package com.baibai.tools;

import android.util.Log;

public class Logger {

private static final String COMMON_TAG = "baibai";
	
	public static boolean DEBUG_STATE = true;

    public static void v(Object object, String msg) {
        if (DEBUG_STATE) {
        	Log.v(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg);
        }
    }
    
    public static void d(Object object, String msg) {
        if (DEBUG_STATE) {
        	Log.d(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg);
        }
    }
    
    public static void i(Object object, String msg) {
        if (DEBUG_STATE) {
        	Log.i(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg);
        }
    }
    
    public static void w(Object object, String msg) {
        Log.w(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg);
    }
    
    public static void e(Object object, String msg) {
        Log.e(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg);
    }
    
    public static void v(Object object, String msg, Throwable tr) {
        if (DEBUG_STATE) {
        	Log.v(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg, tr);
        }
    }

    public static void d(Object object, String msg, Throwable tr) {
        if (DEBUG_STATE) {
        	Log.d(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg, tr);
        }
    }

    public static void i(Object object, String msg, Throwable tr) {
        if (DEBUG_STATE) {
        	Log.i(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg, tr);
        }
    }

    public static void w(Object object, String msg, Throwable tr) {
        Log.w(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg, tr);
    }

    public static void e(Object object, String msg, Throwable tr) {
        Log.e(COMMON_TAG, "[" + object.getClass().getSimpleName() + "] " + msg, tr);
    }
    
}
