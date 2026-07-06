package com.android.volley.toolbox;

import android.content.Context;
import androidx.annotation.NonNull;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Volley {
    public static final String DEFAULT_CACHE_DIR = "volley";

    @NonNull
    public static RequestQueue newRequestQueue(Context context, BaseHttpStack baseHttpStack) {
        return newRequestQueue(context, baseHttpStack == null ? new BasicNetwork((BaseHttpStack) new HurlStack(null, null)) : new BasicNetwork(baseHttpStack));
    }

    @NonNull
    @Deprecated
    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        if (httpStack == null) {
            return newRequestQueue(context, (BaseHttpStack) null);
        }
        return newRequestQueue(context, new BasicNetwork(httpStack));
    }

    @NonNull
    public static RequestQueue newRequestQueue(Context context, Network network) {
        final Context applicationContext = context.getApplicationContext();
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new DiskBasedCache.FileSupplier() { // from class: com.android.volley.toolbox.Volley.1
            public File cacheDir = null;

            @Override // com.android.volley.toolbox.DiskBasedCache.FileSupplier
            public File get() {
                if (this.cacheDir == null) {
                    this.cacheDir = new File(applicationContext.getCacheDir(), Volley.DEFAULT_CACHE_DIR);
                }
                return this.cacheDir;
            }
        }), network, 4);
        requestQueue.start();
        return requestQueue;
    }

    @NonNull
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, (BaseHttpStack) null);
    }
}
