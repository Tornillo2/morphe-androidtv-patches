package com.amazon.minerva.client.thirdparty.utils;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BackgroundThreadFactory implements ThreadFactory {
    public static final int BACKGROUND_THREAD_PRIORITY = 4;
    public final String mThreadName;

    public BackgroundThreadFactory(String str) {
        this.mThreadName = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(4);
        thread.setName(this.mThreadName);
        return thread;
    }
}
