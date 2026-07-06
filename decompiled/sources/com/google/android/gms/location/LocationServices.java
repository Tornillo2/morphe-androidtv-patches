package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LocationServices {

    @NonNull
    public static final Api<Api.ApiOptions.NoOptions> API;

    @NonNull
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi;

    @NonNull
    @Deprecated
    public static final GeofencingApi GeofencingApi;

    @NonNull
    @Deprecated
    public static final SettingsApi SettingsApi;
    public static final Api.ClientKey<com.google.android.gms.internal.location.zzaz> zza;
    public static final Api.AbstractClientBuilder<com.google.android.gms.internal.location.zzaz, Api.ApiOptions.NoOptions> zzb;

    static {
        Api.ClientKey<com.google.android.gms.internal.location.zzaz> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zzbh zzbhVar = new zzbh();
        zzb = zzbhVar;
        API = new Api<>("LocationServices.API", zzbhVar, clientKey);
        FusedLocationApi = new com.google.android.gms.internal.location.zzz();
        GeofencingApi = new com.google.android.gms.internal.location.zzaf();
        SettingsApi = new com.google.android.gms.internal.location.zzbi();
    }

    @NonNull
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    @NonNull
    public static GeofencingClient getGeofencingClient(@NonNull Activity activity) {
        return new GeofencingClient(activity);
    }

    @NonNull
    public static SettingsClient getSettingsClient(@NonNull Activity activity) {
        return new SettingsClient(activity);
    }

    public static com.google.android.gms.internal.location.zzaz zza(GoogleApiClient googleApiClient) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        com.google.android.gms.internal.location.zzaz zzazVar = (com.google.android.gms.internal.location.zzaz) googleApiClient.getClient(zza);
        Preconditions.checkState(zzazVar != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzazVar;
    }

    @NonNull
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context context) {
        return new FusedLocationProviderClient(context);
    }

    @NonNull
    public static GeofencingClient getGeofencingClient(@NonNull Context context) {
        return new GeofencingClient(context);
    }

    @NonNull
    public static SettingsClient getSettingsClient(@NonNull Context context) {
        return new SettingsClient(context);
    }
}
