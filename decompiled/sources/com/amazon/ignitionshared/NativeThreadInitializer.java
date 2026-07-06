package com.amazon.ignitionshared;

import android.content.Context;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NativeThreadInitializer {
    public final Context context;

    @Inject
    public NativeThreadInitializer(@ApplicationContext Context context) {
        this.context = context;
    }

    @CalledFromNative
    public void setCurrentThreadClassLoader() {
        Thread.currentThread().setContextClassLoader(this.context.getClassLoader());
    }
}
