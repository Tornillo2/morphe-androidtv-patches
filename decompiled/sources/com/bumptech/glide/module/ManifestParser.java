package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class ManifestParser {
    public static final String GLIDE_MODULE_VALUE = "GlideModule";
    public static final String TAG = "ManifestParser";
    public final Context context;

    public ManifestParser(Context context) {
        this.context = context;
    }

    public static GlideModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Object objNewInstance = cls.getDeclaredConstructor(null).newInstance(null);
                if (objNewInstance instanceof GlideModule) {
                    return (GlideModule) objNewInstance;
                }
                throw new RuntimeException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Expected instanceof GlideModule, but found: ", objNewInstance));
            } catch (IllegalAccessException e) {
                throwInstantiateGlideModuleException(cls, e);
                throw null;
            } catch (InstantiationException e2) {
                throwInstantiateGlideModuleException(cls, e2);
                throw null;
            } catch (NoSuchMethodException e3) {
                throwInstantiateGlideModuleException(cls, e3);
                throw null;
            } catch (InvocationTargetException e4) {
                throwInstantiateGlideModuleException(cls, e4);
                throw null;
            }
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    public static void throwInstantiateGlideModuleException(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }

    public List<GlideModule> parse() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Got app info metadata: " + applicationInfo.metaData);
                }
                for (String str : applicationInfo.metaData.keySet()) {
                    if (GLIDE_MODULE_VALUE.equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(parseModule(str));
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Loaded Glide module: " + str);
                        }
                    }
                }
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Finished loading Glide modules");
                }
            } else if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Got null app info metadata");
                return arrayList;
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }
}
