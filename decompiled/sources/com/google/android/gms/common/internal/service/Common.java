package com.google.android.gms.common.internal.service;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Common {

    @NonNull
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API;

    @NonNull
    @KeepForSdk
    public static final Api.ClientKey<zah> CLIENT_KEY;
    public static final zae zaa;
    public static final Api.AbstractClientBuilder zab;

    static {
        Api.ClientKey<zah> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zabVar = new zab();
        zab = zabVar;
        API = new Api<>("Common.API", zabVar, clientKey);
        zaa = new zae();
    }
}
