package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.ads.zzbzm;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdx {
    public final Date zza;
    public final String zzb;
    public final List zzc;
    public final int zzd;
    public final Set zze;
    public final Bundle zzf;
    public final Map zzg;
    public final String zzh;
    public final String zzi;

    @Nullable
    @NotOnlyInitialized
    public final SearchAdRequest zzj;
    public final int zzk;
    public final Set zzl;
    public final Bundle zzm;
    public final Set zzn;
    public final boolean zzo;
    public final String zzp;
    public final int zzq;

    public zzdx(zzdw zzdwVar, @Nullable SearchAdRequest searchAdRequest) {
        this.zza = zzdwVar.zzg;
        this.zzb = zzdwVar.zzh;
        this.zzc = zzdwVar.zzi;
        this.zzd = zzdwVar.zzj;
        this.zze = DesugarCollections.unmodifiableSet(zzdwVar.zza);
        this.zzf = zzdwVar.zzb;
        this.zzg = DesugarCollections.unmodifiableMap(zzdwVar.zzc);
        this.zzh = zzdwVar.zzk;
        this.zzi = zzdwVar.zzl;
        this.zzj = searchAdRequest;
        this.zzk = zzdwVar.zzm;
        this.zzl = DesugarCollections.unmodifiableSet(zzdwVar.zzd);
        this.zzm = zzdwVar.zze;
        this.zzn = DesugarCollections.unmodifiableSet(zzdwVar.zzf);
        this.zzo = zzdwVar.zzn;
        this.zzp = zzdwVar.zzo;
        this.zzq = zzdwVar.zzp;
    }

    @Deprecated
    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zzq;
    }

    public final int zzc() {
        return this.zzk;
    }

    @Nullable
    public final Bundle zzd(Class cls) {
        Bundle bundle = this.zzf.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final Bundle zze() {
        return this.zzm;
    }

    @Nullable
    public final Bundle zzf(Class cls) {
        return this.zzf.getBundle(cls.getName());
    }

    public final Bundle zzg() {
        return this.zzf;
    }

    @Nullable
    @Deprecated
    public final NetworkExtras zzh(Class cls) {
        return (NetworkExtras) this.zzg.get(cls);
    }

    @Nullable
    public final SearchAdRequest zzi() {
        return this.zzj;
    }

    @Nullable
    public final String zzj() {
        return this.zzp;
    }

    public final String zzk() {
        return this.zzb;
    }

    public final String zzl() {
        return this.zzh;
    }

    public final String zzm() {
        return this.zzi;
    }

    @Deprecated
    public final Date zzn() {
        return this.zza;
    }

    public final List zzo() {
        return new ArrayList(this.zzc);
    }

    public final Set zzp() {
        return this.zzn;
    }

    public final Set zzq() {
        return this.zze;
    }

    @Deprecated
    public final boolean zzr() {
        return this.zzo;
    }

    public final boolean zzs(Context context) {
        RequestConfiguration requestConfiguration = zzej.zzf().zzi;
        zzay.zzb();
        String strZzy = zzbzm.zzy(context);
        return this.zzl.contains(strZzy) || ((ArrayList) requestConfiguration.getTestDeviceIds()).contains(strZzy);
    }
}
