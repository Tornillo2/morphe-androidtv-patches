package com.google.android.gms.ads.internal.client;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzbnv;
import com.google.android.gms.internal.ads.zzbny;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class LiteSdkInfo extends zzck {
    public LiteSdkInfo(@NonNull Context context) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzcl
    public zzbny getAdapterCreator() {
        return new zzbnv();
    }

    @Override // com.google.android.gms.ads.internal.client.zzcl
    public zzen getLiteSdkVersion() {
        return new zzen(231710100, 231700000, "22.2.0");
    }
}
