package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ActivityRecognition {

    @NonNull
    public static final Api<Api.ApiOptions.NoOptions> API;

    @NonNull
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi;

    @NonNull
    public static final String CLIENT_NAME = "activity_recognition";
    public static final Api.ClientKey<com.google.android.gms.internal.location.zzaz> zza;
    public static final Api.AbstractClientBuilder<com.google.android.gms.internal.location.zzaz, Api.ApiOptions.NoOptions> zzb;

    static {
        Api.ClientKey<com.google.android.gms.internal.location.zzaz> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zza zzaVar = new zza();
        zzb = zzaVar;
        API = new Api<>("ActivityRecognition.API", zzaVar, clientKey);
        ActivityRecognitionApi = new com.google.android.gms.internal.location.zzg();
    }

    @NonNull
    public static ActivityRecognitionClient getClient(@NonNull Activity activity) {
        return new ActivityRecognitionClient(activity);
    }

    @NonNull
    public static ActivityRecognitionClient getClient(@NonNull Context context) {
        return new ActivityRecognitionClient(context);
    }
}
