package com.amazon.primevideo.advertising;

import android.content.Context;
import com.amazon.livingroom.deviceproperties.AdvertisingProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class GoogleAdvertisingProperties implements AdvertisingProperties {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG = "GoogleAdvertisingProperties";

    @NotNull
    public final Context context;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final String getLOG_TAG() {
            return GoogleAdvertisingProperties.LOG_TAG;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public GoogleAdvertisingProperties(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.livingroom.deviceproperties.AdvertisingProperties
    @NotNull
    public String getAdvertisingId() {
        try {
            String str = AdvertisingIdClient.getAdvertisingIdInfo(this.context).zzq;
            Intrinsics.checkNotNull(str);
            return str;
        } catch (Exception e) {
            Log.w(LOG_TAG, "The advertising ID could not be retrieved", e);
            return "";
        }
    }

    @Override // com.amazon.livingroom.deviceproperties.AdvertisingProperties
    public boolean isAdvertisingOptOut() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.context).zzr;
        } catch (Exception e) {
            Log.w(LOG_TAG, "Check for advertising opt out failed", e);
            return true;
        }
    }
}
