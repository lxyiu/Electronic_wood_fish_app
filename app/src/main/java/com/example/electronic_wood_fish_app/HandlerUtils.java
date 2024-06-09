package com.example.electronic_wood_fish_app;

import android.os.Handler;
import android.os.Looper;

public final class HandlerUtils {

    private final static Handler HANDLER = new Handler(Looper.getMainLooper());
    public static Handler getHandler() {
        return HANDLER;
    }
    public static void run(Runnable runnable) {
        HANDLER.post(runnable);
    }
    public static void delay(Runnable runnable, long delay) {
        HANDLER.postDelayed(runnable, delay);
    }
    public static void remove(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }
    public static void removeAll() {
        HANDLER.removeCallbacksAndMessages(null);
    }

}
