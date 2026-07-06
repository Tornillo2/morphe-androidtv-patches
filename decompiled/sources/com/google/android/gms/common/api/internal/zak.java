package com.google.android.gms.common.api.internal;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.GlideException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zak extends zap {
    public final SparseArray zad;

    public zak(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.zad = new SparseArray();
        lifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zak zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zak zakVar = (zak) fragment.getCallbackOrNull("AutoManageHelper", zak.class);
        return zakVar != null ? zakVar : new zak(fragment);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zajVarZai = zai(i);
            if (zajVarZai != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zajVarZai.zaa);
                printWriter.println(":");
                zajVarZai.zab.dump(String.valueOf(str).concat(GlideException.IndentedAppendable.INDENT), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        this.zaa = true;
        SparseArray sparseArray = this.zad;
        Log.d("AutoManageHelper", "onStart " + this.zaa + StringUtils.SPACE + String.valueOf(sparseArray));
        if (this.zab.get() == null) {
            for (int i = 0; i < this.zad.size(); i++) {
                zaj zajVarZai = zai(i);
                if (zajVarZai != null) {
                    zajVarZai.zab.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        this.zaa = false;
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zajVarZai = zai(i);
            if (zajVarZai != null) {
                zajVarZai.zab.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zab(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaj zajVar = (zaj) this.zad.get(i);
        if (zajVar != null) {
            zae(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zajVar.zac;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zac() {
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zajVarZai = zai(i);
            if (zajVarZai != null) {
                zajVarZai.zab.connect();
            }
        }
    }

    public final void zad(int i, GoogleApiClient googleApiClient, @Nullable GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.zad.indexOfKey(i) < 0, MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Already managing a GoogleApiClient with id ", i));
        zam zamVar = (zam) this.zab.get();
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + StringUtils.SPACE + this.zaa + StringUtils.SPACE + String.valueOf(zamVar));
        zaj zajVar = new zaj(this, i, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(zajVar);
        this.zad.put(i, zajVar);
        if (this.zaa && zamVar == null) {
            Log.d("AutoManageHelper", "connecting ".concat(googleApiClient.toString()));
            googleApiClient.connect();
        }
    }

    public final void zae(int i) {
        zaj zajVar = (zaj) this.zad.get(i);
        this.zad.remove(i);
        if (zajVar != null) {
            zajVar.zab.unregisterConnectionFailedListener(zajVar);
            zajVar.zab.disconnect();
        }
    }

    @Nullable
    public final zaj zai(int i) {
        if (this.zad.size() <= i) {
            return null;
        }
        SparseArray sparseArray = this.zad;
        return (zaj) sparseArray.get(sparseArray.keyAt(i));
    }
}
