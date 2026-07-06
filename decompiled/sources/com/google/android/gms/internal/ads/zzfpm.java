package com.google.android.gms.internal.ads;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpm {
    public final zzfok zza;
    public final zzfpl zzb;

    public zzfpm(zzfpl zzfplVar) {
        zzfoj zzfojVar = zzfoj.zza;
        this.zzb = zzfplVar;
        this.zza = zzfojVar;
    }

    public static zzfpm zzb(int i) {
        return new zzfpm(new zzfpi());
    }

    public static zzfpm zzc(zzfok zzfokVar) {
        return new zzfpm(new zzfpg(zzfokVar));
    }

    public static Iterator zze(zzfpm zzfpmVar, CharSequence charSequence) {
        return zzfpmVar.zzb.zza(zzfpmVar, charSequence);
    }

    public final Iterable zzd(CharSequence charSequence) {
        charSequence.getClass();
        return new zzfpj(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        Iterator itZza = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (itZza.hasNext()) {
            arrayList.add((String) itZza.next());
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    public final Iterator zzg(CharSequence charSequence) {
        return this.zzb.zza(this, charSequence);
    }
}
