package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.internal.ads.zzbee;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
public class MediationNativeAdConfiguration extends MediationAdConfiguration {

    @Nullable
    public final zzbee zza;

    public MediationNativeAdConfiguration(Context context, String str, Bundle bundle, Bundle bundle2, boolean z, @Nullable Location location, int i, int i2, @Nullable String str2, String str3, @Nullable zzbee zzbeeVar) {
        super(context, str, bundle, bundle2, z, location, i, i2, str2, str3);
        this.zza = zzbeeVar;
    }

    @NonNull
    public NativeAdOptions getNativeAdOptions() {
        return zzbee.zza(this.zza);
    }
}
