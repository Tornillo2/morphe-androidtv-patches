package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzfpk extends zzfoe {
    public final CharSequence zzb;
    public final zzfok zzc;
    public int zzd = 0;
    public int zze = Integer.MAX_VALUE;

    public zzfpk(zzfpm zzfpmVar, CharSequence charSequence) {
        this.zzc = zzfpmVar.zza;
        this.zzb = charSequence;
    }

    @Override // com.google.android.gms.internal.ads.zzfoe
    @CheckForNull
    public final Object zza() {
        int iZzc;
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 == -1) {
                super.zzb = 3;
                return null;
            }
            int iZzd = zzd(i2);
            if (iZzd == -1) {
                iZzd = this.zzb.length();
                this.zzd = -1;
                iZzc = -1;
            } else {
                iZzc = zzc(iZzd);
                this.zzd = iZzc;
            }
            if (iZzc != i) {
                if (i < iZzd) {
                    this.zzb.charAt(i);
                }
                if (i < iZzd) {
                    this.zzb.charAt(iZzd - 1);
                }
                int i3 = this.zze;
                if (i3 == 1) {
                    iZzd = this.zzb.length();
                    this.zzd = -1;
                    if (iZzd > i) {
                        this.zzb.charAt(iZzd - 1);
                    }
                } else {
                    this.zze = i3 - 1;
                }
                return this.zzb.subSequence(i, iZzd).toString();
            }
            int i4 = iZzc + 1;
            this.zzd = i4;
            if (i4 > this.zzb.length()) {
                this.zzd = -1;
            }
        }
    }

    public abstract int zzc(int i);

    public abstract int zzd(int i);
}
