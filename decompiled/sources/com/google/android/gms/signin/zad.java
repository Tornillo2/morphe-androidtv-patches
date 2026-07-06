package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zad {
    public static final Api.ClientKey zaa;

    @ShowFirstParty
    public static final Api.ClientKey zab;
    public static final Api.AbstractClientBuilder zac;
    public static final Api.AbstractClientBuilder zad;
    public static final Scope zae;
    public static final Scope zaf;
    public static final Api zag;
    public static final Api zah;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zaa = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        zab = clientKey2;
        zaa zaaVar = new zaa();
        zac = zaaVar;
        zab zabVar = new zab();
        zad = zabVar;
        zae = new Scope(1, Scopes.PROFILE);
        zaf = new Scope(1, "email");
        zag = new Api("SignIn.API", zaaVar, clientKey);
        zah = new Api("SignIn.INTERNAL_API", zabVar, clientKey2);
    }
}
