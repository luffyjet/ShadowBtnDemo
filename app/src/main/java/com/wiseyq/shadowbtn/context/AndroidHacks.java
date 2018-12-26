/*
 * Copyright (c) 2017. Kaede (kidhaibara@gmail.com) All Rights Reserved.
 */

package com.wiseyq.shadowbtn.context;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @author Kaede
 * @since 17/4/8
 */
@SuppressWarnings("WeakerAccess")
public class AndroidHacks {
    private static final String TAG = "Applications";
    private static Object sActivityThread;

    @NonNull
    public static Object getActivityThread() {
        if (sActivityThread == null) {
            synchronized (AndroidHacks.class) {
                if (sActivityThread == null) {
                    if (Looper.getMainLooper() == Looper.myLooper()) {
                        sActivityThread = getActivityThreadFromUIThread();
                        if (sActivityThread != null) {
                            return sActivityThread;
                        }
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    synchronized (AndroidHacks.class) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                sActivityThread = getActivityThreadFromUIThread();
                                synchronized (AndroidHacks.class) {
                                    AndroidHacks.class.notifyAll();
                                }
                            }
                        });
                        try {
                            while (sActivityThread == null) {
                                AndroidHacks.class.wait();
                            }
                        } catch (InterruptedException e) {
                            Log.w(TAG, "Waiting notification from UI thread error.", e);
                        }
                    }
                }
            }
        }
        return sActivityThread;
    }

    private static Object getActivityThreadFromUIThread() {
        Object activityThread = null;
        try {
            Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread");
            method.setAccessible(true);
            activityThread = method.invoke(null);
        } catch (final Exception e) {
            Log.w(TAG, "Failed to get ActivityThread from ActivityThread#currentActivityThread. " +
                    "In some case, this method return null in worker thread.", e);
        }
        return activityThread;
    }
}
