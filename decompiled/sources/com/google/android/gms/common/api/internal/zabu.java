package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabu implements BaseGmsClient.ConnectionProgressReportCallbacks, zacs {
    public final /* synthetic */ GoogleApiManager zaa;
    public final Api.Client zab;
    public final ApiKey zac;

    @Nullable
    public IAccountAccessor zad = null;

    @Nullable
    public Set zae = null;
    public boolean zaf = false;

    public zabu(GoogleApiManager googleApiManager, Api.Client client, ApiKey apiKey) {
        this.zaa = googleApiManager;
        this.zab = client;
        this.zac = apiKey;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        this.zaa.zar.post(new zabt(this, connectionResult));
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    @WorkerThread
    public final void zae(ConnectionResult connectionResult) {
        zabq zabqVar = (zabq) this.zaa.zan.get(this.zac);
        if (zabqVar != null) {
            zabqVar.zas(connectionResult);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    @WorkerThread
    public final void zaf(@Nullable IAccountAccessor iAccountAccessor, @Nullable Set set) {
        if (iAccountAccessor == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zae(new ConnectionResult(4));
        } else {
            this.zad = iAccountAccessor;
            this.zae = set;
            zah();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    @WorkerThread
    public final void zag(int i) {
        zabq zabqVar = (zabq) this.zaa.zan.get(this.zac);
        if (zabqVar != null) {
            if (zabqVar.zaj) {
                zabqVar.zas(new ConnectionResult(17));
            } else {
                zabqVar.onConnectionSuspended(i);
            }
        }
    }

    @WorkerThread
    public final void zah() {
        IAccountAccessor iAccountAccessor;
        if (!this.zaf || (iAccountAccessor = this.zad) == null) {
            return;
        }
        this.zab.getRemoteService(iAccountAccessor, this.zae);
    }
}
