package com.amazon.ignitionshared;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Singleton<T> {
    public final Factory<T> factory;
    public volatile T instance;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory<T> {
        T createInstance(Context context);
    }

    public Singleton(Factory<T> factory) {
        this.factory = factory;
    }

    public T getInstance(Context context) {
        T tCreateInstance;
        T t = this.instance;
        if (t != null) {
            return t;
        }
        synchronized (this) {
            try {
                tCreateInstance = this.instance;
                if (tCreateInstance == null) {
                    tCreateInstance = this.factory.createInstance(context.getApplicationContext());
                    this.instance = tCreateInstance;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tCreateInstance;
    }
}
