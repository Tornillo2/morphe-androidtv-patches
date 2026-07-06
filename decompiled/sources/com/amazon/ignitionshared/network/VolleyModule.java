package com.amazon.ignitionshared.network;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.android.volley.ExecutorDelivery;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BaseHttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.Volley;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module
public interface VolleyModule {

    /* JADX INFO: renamed from: com.amazon.ignitionshared.network.VolleyModule$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @ApplicationScope
        @Provides
        @Named(Names.BACKGROUND_DELIVERY)
        public static RequestQueue provideBackgroundDeliveryRequestQueue(@ApplicationContext Context context, HurlStack hurlStack) {
            HandlerThread handlerThread = new HandlerThread("VolleyBackgroundDelivery");
            handlerThread.start();
            RequestQueue requestQueue = new RequestQueue(new NoCache(), new BasicNetwork((BaseHttpStack) hurlStack), 2, new ExecutorDelivery(new Handler(handlerThread.getLooper())));
            requestQueue.start();
            return requestQueue;
        }

        @Provides
        public static HurlStack provideHurlStack(SecureSslSocketFactory secureSslSocketFactory) {
            return new HurlStack(null, secureSslSocketFactory);
        }

        @ApplicationScope
        @Provides
        public static RequestQueue provideRequestQueue(@ApplicationContext Context context, HurlStack hurlStack) {
            return Volley.newRequestQueue(context, (BaseHttpStack) hurlStack);
        }

        @ApplicationScope
        @Provides
        public static SecureSslSocketFactory provideSecureSslSocketFactory() {
            return SecureSslSocketFactory.createFromDefault();
        }
    }
}
