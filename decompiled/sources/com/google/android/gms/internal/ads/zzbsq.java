package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbsq extends zzbsj {
    public final /* synthetic */ List zza;

    public zzbsq(zzbss zzbssVar, List list) {
        this.zza = list;
    }

    @Override // com.google.android.gms.internal.ads.zzbsk
    public final void zze(String str) {
        zzbzt.zzg("Error recording click: ".concat(String.valueOf(str)));
    }

    @Override // com.google.android.gms.internal.ads.zzbsk
    public final void zzf(List list) {
        zzbzt.zzi("Recorded click: ".concat(this.zza.toString()));
    }
}
