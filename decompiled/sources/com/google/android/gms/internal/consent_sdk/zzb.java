package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzb {
    public final Application zza;

    public zzb(Application application) {
        this.zza = application;
    }

    @Nullable
    public final zza zza() {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zza);
            return new zza(advertisingIdInfo.zzq, advertisingIdInfo.zzr);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
            Log.d("UserMessagingPlatform", "Failed to get ad id.", e);
            return null;
        }
    }
}
