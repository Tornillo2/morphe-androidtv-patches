package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzhg {
    public static final zzhg zza = new zzhg(0, new int[0], new Object[0], false);
    public int zzb;
    public int[] zzc;
    public Object[] zzd;
    public int zze;
    public boolean zzf;

    public zzhg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzhg zzc() {
        return zza;
    }

    public static zzhg zze(zzhg zzhgVar, zzhg zzhgVar2) {
        int i = zzhgVar.zzb + zzhgVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzhgVar.zzc, i);
        System.arraycopy(zzhgVar2.zzc, 0, iArrCopyOf, zzhgVar.zzb, zzhgVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzhgVar.zzd, i);
        System.arraycopy(zzhgVar2.zzd, 0, objArrCopyOf, zzhgVar.zzb, zzhgVar2.zzb);
        return new zzhg(i, iArrCopyOf, objArrCopyOf, true);
    }

    public static zzhg zzf() {
        return new zzhg(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzhg)) {
            return false;
        }
        zzhg zzhgVar = (zzhg) obj;
        int i = this.zzb;
        if (i == zzhgVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzhgVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzhgVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = ((i2 * 31) + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int iZzC;
        int iZzD;
        int iZzC2;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzC3 = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 != 0) {
                if (i5 == 1) {
                    ((Long) this.zzd[i2]).getClass();
                    iZzC2 = zzen.zzC(i4 << 3) + 8;
                } else if (i5 == 2) {
                    int i6 = i4 << 3;
                    zzeg zzegVar = (zzeg) this.zzd[i2];
                    int iZzC4 = zzen.zzC(i6);
                    int iZzd = zzegVar.zzd();
                    iZzC3 = zzen.zzC(iZzd) + iZzd + iZzC4 + iZzC3;
                } else if (i5 == 3) {
                    int iZzC5 = zzen.zzC(i4 << 3);
                    iZzC = iZzC5 + iZzC5;
                    iZzD = ((zzhg) this.zzd[i2]).zza();
                } else {
                    if (i5 != 5) {
                        throw new IllegalStateException(new zzfn("Protocol message tag had invalid wire type."));
                    }
                    ((Integer) this.zzd[i2]).getClass();
                    iZzC2 = zzen.zzC(i4 << 3) + 4;
                }
                iZzC3 = iZzC2 + iZzC3;
            } else {
                int i7 = i4 << 3;
                long jLongValue = ((Long) this.zzd[i2]).longValue();
                iZzC = zzen.zzC(i7);
                iZzD = zzen.zzD(jLongValue);
            }
            iZzC3 = iZzD + iZzC + iZzC3;
        }
        this.zze = iZzC3;
        return iZzC3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iM = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2] >>> 3;
            zzeg zzegVar = (zzeg) this.zzd[i2];
            int iZzC = zzen.zzC(8);
            int iZzC2 = zzen.zzC(i3) + zzen.zzC(16);
            int iZzC3 = zzen.zzC(24);
            int iZzd = zzegVar.zzd();
            iM += iZzC + iZzC + iZzC2 + zzgm$$ExternalSyntheticOutline0.m(iZzd, iZzd, iZzC3);
        }
        this.zze = iM;
        return iM;
    }

    public final zzhg zzd(zzhg zzhgVar) {
        if (zzhgVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzhgVar.zzb;
        zzm(i);
        System.arraycopy(zzhgVar.zzc, 0, this.zzc, this.zzb, zzhgVar.zzb);
        System.arraycopy(zzhgVar.zzd, 0, this.zzd, this.zzb, zzhgVar.zzb);
        this.zzb = i;
        return this;
    }

    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzgl.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    public final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    public final void zzk(zzhs zzhsVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzhsVar.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzhs zzhsVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzhsVar.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzhsVar.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzhsVar.zzd(i4, (zzeg) obj);
                } else if (i3 == 3) {
                    zzhsVar.zzF(i4);
                    ((zzhg) obj).zzl(zzhsVar);
                    zzhsVar.zzh(i4);
                } else {
                    if (i3 != 5) {
                        throw new RuntimeException(new zzfn("Protocol message tag had invalid wire type."));
                    }
                    zzhsVar.zzk(i4, ((Integer) obj).intValue());
                }
            }
        }
    }

    public final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = (i2 / 2) + i2;
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public zzhg() {
        this(0, new int[8], new Object[8], true);
    }
}
