package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpf extends zzfpk {
    public final /* synthetic */ zzfpg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfpf(zzfpg zzfpgVar, zzfpm zzfpmVar, CharSequence charSequence) {
        super(zzfpmVar, charSequence);
        this.zza = zzfpgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfpk
    public final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.gms.internal.ads.zzfpk
    public final int zzd(int i) {
        zzfok zzfokVar = this.zza.zza;
        CharSequence charSequence = ((zzfpk) this).zzb;
        int length = charSequence.length();
        zzfoz.zzb(i, length, "index");
        while (i < length) {
            if (zzfokVar.zzb(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
