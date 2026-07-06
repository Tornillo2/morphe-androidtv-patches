package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zact extends com.google.android.gms.signin.internal.zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final Api.AbstractClientBuilder zaa = com.google.android.gms.signin.zad.zac;
    public final Context zab;
    public final Handler zac;
    public final Api.AbstractClientBuilder zad;
    public final Set zae;
    public final ClientSettings zaf;
    public com.google.android.gms.signin.zae zag;
    public zacs zah;

    @WorkerThread
    public zact(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        Api.AbstractClientBuilder abstractClientBuilder = zaa;
        this.zab = context;
        this.zac = handler;
        Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.zaf = clientSettings;
        this.zae = clientSettings.zab;
        this.zad = abstractClientBuilder;
    }

    public static void zad(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        ConnectionResult connectionResult = zakVar.zab;
        if (connectionResult.isSuccess()) {
            com.google.android.gms.common.internal.zav zavVar = zakVar.zac;
            Preconditions.checkNotNull(zavVar);
            ConnectionResult connectionResult2 = zavVar.zac;
            if (!connectionResult2.isSuccess()) {
                String strValueOf = String.valueOf(connectionResult2);
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(strValueOf), new Exception());
                zactVar.zah.zae(connectionResult2);
                zactVar.zag.disconnect();
                return;
            }
            zactVar.zah.zaf(zavVar.zab(), zactVar.zae);
        } else {
            zactVar.zah.zae(connectionResult);
        }
        zactVar.zag.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zag.zad(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zah.zae(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.zah.zag(i);
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zae
    @BinderThread
    public final void zab(com.google.android.gms.signin.internal.zak zakVar) {
        this.zac.post(new zacr(this, zakVar));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @WorkerThread
    public final void zae(zacs zacsVar) {
        com.google.android.gms.signin.zae zaeVar = this.zag;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
        this.zaf.zaj = Integer.valueOf(System.identityHashCode(this));
        Api.AbstractClientBuilder abstractClientBuilder = this.zad;
        Context context = this.zab;
        Handler handler = this.zac;
        ClientSettings clientSettings = this.zaf;
        this.zag = abstractClientBuilder.buildClient(context, handler.getLooper(), clientSettings, clientSettings.zai, (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.zah = zacsVar;
        Set set = this.zae;
        if (set == null || set.isEmpty()) {
            this.zac.post(new zacq(this));
        } else {
            this.zag.zab();
        }
    }

    public final void zaf() {
        com.google.android.gms.signin.zae zaeVar = this.zag;
        if (zaeVar != null) {
            zaeVar.disconnect();
        }
    }
}
