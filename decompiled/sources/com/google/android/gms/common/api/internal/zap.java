package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {
    public volatile boolean zaa;
    public final AtomicReference zab;
    public final GoogleApiAvailability zac;
    public final Handler zad;

    @VisibleForTesting
    public zap(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zab = new AtomicReference(null);
        this.zad = new com.google.android.gms.internal.base.zau(Looper.getMainLooper());
        this.zac = googleApiAvailability;
    }

    public static final int zae(@Nullable zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.zaa;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int i, int i2, Intent intent) {
        zam zamVar = (zam) this.zab.get();
        if (i != 1) {
            if (i == 2) {
                int iIsGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(getActivity());
                if (iIsGooglePlayServicesAvailable == 0) {
                    zad();
                    return;
                } else {
                    if (zamVar == null) {
                        return;
                    }
                    if (zamVar.zab.zzb == 18 && iIsGooglePlayServicesAvailable == 18) {
                        return;
                    }
                }
            }
        } else if (i2 == -1) {
            zad();
            return;
        } else if (i2 == 0) {
            if (zamVar != null) {
                zaa(new ConnectionResult(1, intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, zamVar.zab.toString()), zamVar.zaa);
                return;
            }
            return;
        }
        if (zamVar != null) {
            zaa(zamVar.zab, zamVar.zaa);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        ConnectionResult connectionResult = new ConnectionResult(13, null);
        zam zamVar = (zam) this.zab.get();
        zaa(connectionResult, zamVar == null ? -1 : zamVar.zaa);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.zab.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        zam zamVar = (zam) this.zab.get();
        if (zamVar == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", zamVar.zaa);
        bundle.putInt("failed_status", zamVar.zab.zzb);
        bundle.putParcelable("failed_resolution", zamVar.zab.zzc);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        this.zaa = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        this.zaa = false;
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zab.set(null);
        zab(connectionResult, i);
    }

    public abstract void zab(ConnectionResult connectionResult, int i);

    public abstract void zac();

    public final void zad() {
        this.zab.set(null);
        zac();
    }

    public final void zah(ConnectionResult connectionResult, int i) {
        AtomicReference atomicReference;
        zam zamVar = new zam(connectionResult, i);
        do {
            atomicReference = this.zab;
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, zamVar)) {
                this.zad.post(new zao(this, zamVar));
                return;
            }
        } while (atomicReference.get() == null);
    }
}
