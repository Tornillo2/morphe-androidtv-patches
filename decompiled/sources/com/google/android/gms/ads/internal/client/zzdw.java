package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbzt;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.time.GmtTimeZone;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdw {
    public Date zzg;
    public String zzh;
    public String zzk;
    public String zzl;
    public boolean zzn;
    public String zzo;
    public final HashSet zza = new HashSet();
    public final Bundle zzb = new Bundle();
    public final HashMap zzc = new HashMap();
    public final HashSet zzd = new HashSet();
    public final Bundle zze = new Bundle();
    public final HashSet zzf = new HashSet();
    public final List zzi = new ArrayList();
    public int zzj = -1;
    public int zzm = -1;
    public int zzp = GmtTimeZone.MILLISECONDS_PER_MINUTE;

    @Deprecated
    public final void zzA(int i) {
        this.zzj = i;
    }

    public final void zzB(int i) {
        this.zzp = i;
    }

    @Deprecated
    public final void zzC(boolean z) {
        this.zzn = z;
    }

    public final void zzD(List list) {
        this.zzi.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (TextUtils.isEmpty(str)) {
                zzbzt.zzj("neighboring content URL should not be null or empty");
            } else {
                this.zzi.add(str);
            }
        }
    }

    public final void zzE(String str) {
        this.zzk = str;
    }

    public final void zzF(String str) {
        this.zzl = str;
    }

    @Deprecated
    public final void zzG(boolean z) {
        this.zzm = z ? 1 : 0;
    }

    public final void zzp(String str) {
        this.zzf.add(str);
    }

    public final void zzq(Class cls, Bundle bundle) {
        if (this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzb.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        Bundle bundle2 = this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        Preconditions.checkNotNull(bundle2);
        bundle2.putBundle(cls.getName(), bundle);
    }

    public final void zzr(String str, String str2) {
        this.zze.putString(str, str2);
    }

    public final void zzs(String str) {
        this.zza.add(str);
    }

    public final void zzt(Class cls, @Nullable Bundle bundle) {
        this.zzb.putBundle(cls.getName(), bundle);
    }

    @Deprecated
    public final void zzu(NetworkExtras networkExtras) {
        this.zzc.put(networkExtras.getClass(), networkExtras);
    }

    public final void zzv(String str) {
        this.zzd.add(str);
    }

    public final void zzw(String str) {
        this.zzd.remove("B3EEABB8EE11C2BE770B684D95219ECB");
    }

    public final void zzx(String str) {
        this.zzo = str;
    }

    @Deprecated
    public final void zzy(Date date) {
        this.zzg = date;
    }

    public final void zzz(String str) {
        this.zzh = str;
    }
}
