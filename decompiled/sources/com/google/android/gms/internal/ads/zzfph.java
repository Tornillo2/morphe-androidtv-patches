package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfph extends zzfpk {
    public final /* synthetic */ zzfpi zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfph(zzfpi zzfpiVar, zzfpm zzfpmVar, CharSequence charSequence) {
        super(zzfpmVar, charSequence);
        this.zza = zzfpiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfpk
    public final int zzd(int i) {
        int i2 = i + 4000;
        if (i2 < ((zzfpk) this).zzb.length()) {
            return i2;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzfpk
    public final int zzc(int i) {
        return i;
    }
}
