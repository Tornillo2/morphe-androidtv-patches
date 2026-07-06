package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.GlideException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabi implements zaca, zau {
    public final Map zaa;

    @Nullable
    public final ClientSettings zac;
    public final Map zad;

    @Nullable
    public final Api.AbstractClientBuilder zae;
    public int zaf;
    public final zabe zag;
    public final zabz zah;
    public final Lock zai;
    public final Condition zaj;
    public final Context zak;
    public final GoogleApiAvailabilityLight zal;
    public final zabh zam;

    @NotOnlyInitialized
    public volatile zabf zan;
    public final Map zab = new HashMap();

    @Nullable
    public ConnectionResult zao = null;

    public zabi(Context context, zabe zabeVar, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map map, @Nullable ClientSettings clientSettings, Map map2, @Nullable Api.AbstractClientBuilder abstractClientBuilder, ArrayList arrayList, zabz zabzVar) {
        this.zak = context;
        this.zai = lock;
        this.zal = googleApiAvailabilityLight;
        this.zaa = map;
        this.zac = clientSettings;
        this.zad = map2;
        this.zae = abstractClientBuilder;
        this.zag = zabeVar;
        this.zah = zabzVar;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((zat) arrayList.get(i)).zac = this;
        }
        this.zam = new zabh(this, looper);
        this.zaj = lock.newCondition();
        this.zan = new zaax(this);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        this.zai.lock();
        try {
            this.zan.zag(bundle);
        } finally {
            this.zai.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        this.zai.lock();
        try {
            this.zan.zai(i);
        } finally {
            this.zai.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zau
    public final void zaa(@NonNull ConnectionResult connectionResult, @NonNull Api api, boolean z) {
        this.zai.lock();
        try {
            this.zan.zah(connectionResult, api, z);
        } finally {
            this.zai.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final ConnectionResult zab() {
        zaq();
        while (this.zan instanceof zaaw) {
            try {
                this.zaj.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (this.zan instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zao;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final ConnectionResult zac(long j, TimeUnit timeUnit) {
        zaq();
        long nanos = timeUnit.toNanos(j);
        while (this.zan instanceof zaaw) {
            if (nanos <= 0) {
                zar();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zaj.awaitNanos(nanos);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (this.zan instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zao;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @Nullable
    @GuardedBy("lock")
    public final ConnectionResult zad(@NonNull Api api) {
        Map map = this.zaa;
        Api.ClientKey clientKey = api.zab;
        if (!map.containsKey(clientKey)) {
            return null;
        }
        if (((Api.Client) this.zaa.get(clientKey)).isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zab.containsKey(clientKey)) {
            return (ConnectionResult) this.zab.get(clientKey);
        }
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final BaseImplementation.ApiMethodImpl zae(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        this.zan.zaa(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final BaseImplementation.ApiMethodImpl zaf(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        return this.zan.zab(apiMethodImpl);
    }

    public final void zai() {
        this.zai.lock();
        try {
            this.zag.zak();
            this.zan = new zaaj(this);
            this.zan.zad();
            this.zaj.signalAll();
        } finally {
            this.zai.unlock();
        }
    }

    public final void zaj() throws Throwable {
        zabi zabiVar;
        this.zai.lock();
        try {
            zabiVar = this;
            try {
                zabiVar.zan = new zaaw(zabiVar, this.zac, this.zad, this.zal, this.zae, this.zai, this.zak);
                zabiVar.zan.zad();
                zabiVar.zaj.signalAll();
                zabiVar.zai.unlock();
            } catch (Throwable th) {
                th = th;
                zabiVar.zai.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zabiVar = this;
        }
    }

    public final void zak(@Nullable ConnectionResult connectionResult) {
        this.zai.lock();
        try {
            this.zao = connectionResult;
            this.zan = new zaax(this);
            this.zan.zad();
            this.zaj.signalAll();
        } finally {
            this.zai.unlock();
        }
    }

    public final void zal(zabg zabgVar) {
        zabh zabhVar = this.zam;
        zabhVar.sendMessage(zabhVar.obtainMessage(1, zabgVar));
    }

    public final void zam(RuntimeException runtimeException) {
        zabh zabhVar = this.zam;
        zabhVar.sendMessage(zabhVar.obtainMessage(2, runtimeException));
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final void zaq() {
        this.zan.zae();
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final void zar() {
        if (this.zan.zaj()) {
            this.zab.clear();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zas(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.append((CharSequence) str).append("mState=").println(this.zan);
        for (Api api : this.zad.keySet()) {
            String strValueOf = String.valueOf(str);
            printWriter.append((CharSequence) str).append((CharSequence) api.zac).println(":");
            Api.Client client = (Api.Client) this.zaa.get(api.zab);
            Preconditions.checkNotNull(client);
            client.dump(strValueOf.concat(GlideException.IndentedAppendable.INDENT), fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    @GuardedBy("lock")
    public final void zat() {
        if (this.zan instanceof zaaj) {
            ((zaaj) this.zan).zaf();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zaw() {
        return this.zan instanceof zaaj;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zax() {
        return this.zan instanceof zaaw;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final boolean zay(SignInConnectionListener signInConnectionListener) {
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zaca
    public final void zau() {
    }
}
