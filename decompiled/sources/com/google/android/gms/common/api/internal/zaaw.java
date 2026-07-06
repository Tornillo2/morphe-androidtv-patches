package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaaw implements zabf {
    public final zabi zaa;
    public final Lock zab;
    public final Context zac;
    public final GoogleApiAvailabilityLight zad;

    @Nullable
    public ConnectionResult zae;
    public int zaf;
    public int zah;

    @Nullable
    public com.google.android.gms.signin.zae zak;
    public boolean zal;
    public boolean zam;
    public boolean zan;

    @Nullable
    public IAccountAccessor zao;
    public boolean zap;
    public boolean zaq;

    @Nullable
    public final ClientSettings zar;
    public final Map zas;

    @Nullable
    public final Api.AbstractClientBuilder zat;
    public int zag = 0;
    public final Bundle zai = new Bundle();
    public final Set zaj = new HashSet();
    public final ArrayList zau = new ArrayList();

    public zaaw(zabi zabiVar, @Nullable ClientSettings clientSettings, Map map, GoogleApiAvailabilityLight googleApiAvailabilityLight, @Nullable Api.AbstractClientBuilder abstractClientBuilder, Lock lock, Context context) {
        this.zaa = zabiVar;
        this.zar = clientSettings;
        this.zas = map;
        this.zad = googleApiAvailabilityLight;
        this.zat = abstractClientBuilder;
        this.zab = lock;
        this.zac = context;
    }

    public static final String zaJ(int i) {
        return i != 0 ? "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    public static Set zao(zaaw zaawVar) {
        ClientSettings clientSettings = zaawVar.zar;
        if (clientSettings == null) {
            return Collections.EMPTY_SET;
        }
        HashSet hashSet = new HashSet(clientSettings.zab);
        Map map = zaawVar.zar.zad;
        for (Api api : map.keySet()) {
            zabi zabiVar = zaawVar.zaa;
            if (!zabiVar.zab.containsKey(api.zab)) {
                hashSet.addAll(((com.google.android.gms.common.internal.zab) map.get(api)).zaa);
            }
        }
        return hashSet;
    }

    public static void zar(zaaw zaawVar, com.google.android.gms.signin.internal.zak zakVar) {
        if (zaawVar.zaG(0)) {
            ConnectionResult connectionResult = zakVar.zab;
            if (!connectionResult.isSuccess()) {
                if (!zaawVar.zaI(connectionResult)) {
                    zaawVar.zaD(connectionResult);
                    return;
                } else {
                    zaawVar.zaA();
                    zaawVar.zaF();
                    return;
                }
            }
            com.google.android.gms.common.internal.zav zavVar = zakVar.zac;
            Preconditions.checkNotNull(zavVar);
            ConnectionResult connectionResult2 = zavVar.zac;
            if (!connectionResult2.isSuccess()) {
                String strValueOf = String.valueOf(connectionResult2);
                Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: ".concat(strValueOf), new Exception());
                zaawVar.zaD(connectionResult2);
                return;
            }
            zaawVar.zan = true;
            IAccountAccessor iAccountAccessorZab = zavVar.zab();
            Preconditions.checkNotNull(iAccountAccessorZab);
            zaawVar.zao = iAccountAccessorZab;
            zaawVar.zap = zavVar.zad;
            zaawVar.zaq = zavVar.zae;
            zaawVar.zaF();
        }
    }

    @GuardedBy("lock")
    public final void zaA() {
        this.zam = false;
        this.zaa.zag.zad = Collections.EMPTY_SET;
        for (Api.AnyClientKey anyClientKey : this.zaj) {
            if (!this.zaa.zab.containsKey(anyClientKey)) {
                zabi zabiVar = this.zaa;
                zabiVar.zab.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    @GuardedBy("lock")
    public final void zaB(boolean z) {
        com.google.android.gms.signin.zae zaeVar = this.zak;
        if (zaeVar != null) {
            if (zaeVar.isConnected() && z) {
                zaeVar.zaa();
            }
            zaeVar.disconnect();
            Preconditions.checkNotNull(this.zar);
            this.zao = null;
        }
    }

    @GuardedBy("lock")
    public final void zaC() {
        this.zaa.zai();
        zabj.zaa().execute(new zaak(this));
        com.google.android.gms.signin.zae zaeVar = this.zak;
        if (zaeVar != null) {
            if (this.zap) {
                IAccountAccessor iAccountAccessor = this.zao;
                Preconditions.checkNotNull(iAccountAccessor);
                zaeVar.zac(iAccountAccessor, this.zaq);
            }
            zaB(false);
        }
        Iterator it = this.zaa.zab.keySet().iterator();
        while (it.hasNext()) {
            Api.Client client = (Api.Client) this.zaa.zaa.get((Api.AnyClientKey) it.next());
            Preconditions.checkNotNull(client);
            client.disconnect();
        }
        this.zaa.zah.zab(this.zai.isEmpty() ? null : this.zai);
    }

    @GuardedBy("lock")
    public final void zaD(ConnectionResult connectionResult) {
        zaz();
        zaB(!connectionResult.hasResolution());
        this.zaa.zak(connectionResult);
        this.zaa.zah.zaa(connectionResult);
    }

    @GuardedBy("lock")
    public final void zaE(ConnectionResult connectionResult, Api api, boolean z) {
        api.zaa.getClass();
        if ((!z || connectionResult.hasResolution() || this.zad.getErrorResolutionIntent(connectionResult.zzb) != null) && (this.zae == null || Integer.MAX_VALUE < this.zaf)) {
            this.zae = connectionResult;
            this.zaf = Integer.MAX_VALUE;
        }
        zabi zabiVar = this.zaa;
        zabiVar.zab.put(api.zab, connectionResult);
    }

    @GuardedBy("lock")
    public final void zaF() {
        if (this.zah != 0) {
            return;
        }
        if (!this.zam || this.zan) {
            ArrayList arrayList = new ArrayList();
            this.zag = 1;
            this.zah = this.zaa.zaa.size();
            for (Api.AnyClientKey anyClientKey : this.zaa.zaa.keySet()) {
                if (!this.zaa.zab.containsKey(anyClientKey)) {
                    arrayList.add((Api.Client) this.zaa.zaa.get(anyClientKey));
                } else if (zaH()) {
                    zaC();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.zau.add(zabj.zaa().submit(new zaap(this, arrayList)));
        }
    }

    @GuardedBy("lock")
    public final boolean zaG(int i) {
        if (this.zag == i) {
            return true;
        }
        Log.w("GACConnecting", this.zaa.zag.zaf());
        Log.w("GACConnecting", "Unexpected callback in ".concat(toString()));
        Log.w("GACConnecting", "mRemainingConnections=" + this.zah);
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("GoogleApiClient connecting is in step ", zaJ(this.zag), " but received callback for step ");
        sbM.append(zaJ(i));
        Log.e("GACConnecting", sbM.toString(), new Exception());
        zaD(new ConnectionResult(8, null));
        return false;
    }

    @GuardedBy("lock")
    public final boolean zaH() {
        int i = this.zah - 1;
        this.zah = i;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GACConnecting", this.zaa.zag.zaf());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zaD(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult = this.zae;
        if (connectionResult == null) {
            return true;
        }
        this.zaa.zaf = this.zaf;
        zaD(connectionResult);
        return false;
    }

    @GuardedBy("lock")
    public final boolean zaI(ConnectionResult connectionResult) {
        return this.zal && !connectionResult.hasResolution();
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zaa(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        this.zaa.zag.zaa.add(apiMethodImpl);
        return apiMethodImpl;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final BaseImplementation.ApiMethodImpl zab(BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.common.api.Api$Client, com.google.android.gms.signin.zae] */
    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("lock")
    public final void zad() {
        this.zaa.zab.clear();
        this.zam = false;
        zaas zaasVar = null;
        this.zae = null;
        this.zag = 0;
        this.zal = true;
        this.zan = false;
        this.zap = false;
        HashMap map = new HashMap();
        for (Api api : this.zas.keySet()) {
            zabi zabiVar = this.zaa;
            Api.Client client = (Api.Client) zabiVar.zaa.get(api.zab);
            Preconditions.checkNotNull(client);
            Api.Client client2 = client;
            api.zaa.getClass();
            boolean zBooleanValue = ((Boolean) this.zas.get(api)).booleanValue();
            if (client2.requiresSignIn()) {
                this.zam = true;
                if (zBooleanValue) {
                    this.zaj.add(api.zab);
                } else {
                    this.zal = false;
                }
            }
            map.put(client2, new zaal(this, api, zBooleanValue));
        }
        if (this.zam) {
            Preconditions.checkNotNull(this.zar);
            Preconditions.checkNotNull(this.zat);
            this.zar.zaj = Integer.valueOf(System.identityHashCode(this.zaa.zag));
            zaat zaatVar = new zaat(this, zaasVar);
            Api.AbstractClientBuilder abstractClientBuilder = this.zat;
            Context context = this.zac;
            zabi zabiVar2 = this.zaa;
            ClientSettings clientSettings = this.zar;
            this.zak = abstractClientBuilder.buildClient(context, zabiVar2.zag.zao, clientSettings, clientSettings.zai, (GoogleApiClient.ConnectionCallbacks) zaatVar, (GoogleApiClient.OnConnectionFailedListener) zaatVar);
        }
        this.zah = this.zaa.zaa.size();
        this.zau.add(zabj.zaa().submit(new zaao(this, map)));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("lock")
    public final void zag(@Nullable Bundle bundle) {
        if (zaG(1)) {
            if (bundle != null) {
                this.zai.putAll(bundle);
            }
            if (zaH()) {
                zaC();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("lock")
    public final void zah(ConnectionResult connectionResult, Api api, boolean z) {
        if (zaG(1)) {
            zaE(connectionResult, api, z);
            if (zaH()) {
                zaC();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("lock")
    public final void zai(int i) {
        zaD(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("lock")
    public final boolean zaj() {
        zaz();
        zaB(true);
        this.zaa.zak(null);
        return true;
    }

    public final void zaz() {
        ArrayList arrayList = this.zau;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Future) arrayList.get(i)).cancel(true);
        }
        this.zau.clear();
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
    }
}
