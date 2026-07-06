package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzip {
    public static int zza(byte[] bArr, int i, zzio zzioVar) throws zzkm {
        int iZzj = zzj(bArr, i, zzioVar);
        int i2 = zzioVar.zza;
        if (i2 < 0) {
            throw zzkm.zzd();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzkm.zzf();
        }
        if (i2 == 0) {
            zzioVar.zzc = zzjb.zzb;
            return iZzj;
        }
        zzioVar.zzc = zzjb.zzl(bArr, iZzj, i2);
        return iZzj + i2;
    }

    public static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int zzc(zzlu zzluVar, byte[] bArr, int i, int i2, int i3, zzio zzioVar) throws IOException {
        zzlm zzlmVar = (zzlm) zzluVar;
        Object objZze = zzlmVar.zze();
        int iZzc = zzlmVar.zzc(objZze, bArr, i, i2, i3, zzioVar);
        zzlmVar.zzf(objZze);
        zzioVar.zzc = objZze;
        return iZzc;
    }

    public static int zzd(zzlu zzluVar, byte[] bArr, int i, int i2, zzio zzioVar) throws IOException {
        int iZzk = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzk = zzk(i3, bArr, iZzk, zzioVar);
            i3 = zzioVar.zza;
        }
        int i4 = iZzk;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzkm.zzf();
        }
        Object objZze = zzluVar.zze();
        int i5 = i4 + i3;
        zzluVar.zzh(objZze, bArr, i4, i5, zzioVar);
        zzluVar.zzf(objZze);
        zzioVar.zzc = objZze;
        return i5;
    }

    public static int zze(zzlu zzluVar, int i, byte[] bArr, int i2, int i3, zzkj zzkjVar, zzio zzioVar) throws IOException {
        int iZzd = zzd(zzluVar, bArr, i2, i3, zzioVar);
        zzkjVar.add(zzioVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzioVar);
            if (i != zzioVar.zza) {
                break;
            }
            iZzd = zzd(zzluVar, bArr, iZzj, i3, zzioVar);
            zzkjVar.add(zzioVar.zzc);
        }
        return iZzd;
    }

    public static int zzf(byte[] bArr, int i, zzkj zzkjVar, zzio zzioVar) throws IOException {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int iZzj = zzj(bArr, i, zzioVar);
        int i2 = zzioVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzioVar);
            zzkdVar.zzh(zzioVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzkm.zzf();
    }

    public static int zzg(byte[] bArr, int i, zzio zzioVar) throws zzkm {
        int iZzj = zzj(bArr, i, zzioVar);
        int i2 = zzioVar.zza;
        if (i2 < 0) {
            throw zzkm.zzd();
        }
        if (i2 == 0) {
            zzioVar.zzc = "";
            return iZzj;
        }
        zzioVar.zzc = new String(bArr, iZzj, i2, zzkk.zzb);
        return iZzj + i2;
    }

    public static int zzh(byte[] bArr, int i, zzio zzioVar) throws zzkm {
        int iZzj = zzj(bArr, i, zzioVar);
        int i2 = zzioVar.zza;
        if (i2 < 0) {
            throw zzkm.zzd();
        }
        if (i2 == 0) {
            zzioVar.zzc = "";
            return iZzj;
        }
        zzioVar.zzc = zzna.zzd(bArr, iZzj, i2);
        return iZzj + i2;
    }

    public static int zzi(int i, byte[] bArr, int i2, int i3, zzmm zzmmVar, zzio zzioVar) throws zzkm {
        if ((i >>> 3) == 0) {
            throw zzkm.zzb();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzm = zzm(bArr, i2, zzioVar);
            zzmmVar.zzh(i, Long.valueOf(zzioVar.zzb));
            return iZzm;
        }
        if (i4 == 1) {
            zzmmVar.zzh(i, Long.valueOf(zzn(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzj = zzj(bArr, i2, zzioVar);
            int i5 = zzioVar.zza;
            if (i5 < 0) {
                throw zzkm.zzd();
            }
            if (i5 > bArr.length - iZzj) {
                throw zzkm.zzf();
            }
            if (i5 == 0) {
                zzmmVar.zzh(i, zzjb.zzb);
            } else {
                zzmmVar.zzh(i, zzjb.zzl(bArr, iZzj, i5));
            }
            return iZzj + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw zzkm.zzb();
            }
            zzmmVar.zzh(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzmm zzmmVarZze = zzmm.zze();
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzj2 = zzj(bArr, i2, zzioVar);
            i7 = zzioVar.zza;
            if (i7 == i6) {
                i2 = iZzj2;
                break;
            }
            i2 = zzi(i7, bArr, iZzj2, i3, zzmmVarZze, zzioVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzkm.zze();
        }
        zzmmVar.zzh(i, zzmmVarZze);
        return i2;
    }

    public static int zzj(byte[] bArr, int i, zzio zzioVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzioVar);
        }
        zzioVar.zza = b;
        return i2;
    }

    public static int zzk(int i, byte[] bArr, int i2, zzio zzioVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzioVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzioVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzioVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzioVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzioVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int zzl(int i, byte[] bArr, int i2, int i3, zzkj zzkjVar, zzio zzioVar) {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int iZzj = zzj(bArr, i2, zzioVar);
        zzkdVar.zzh(zzioVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzioVar);
            if (i != zzioVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzioVar);
            zzkdVar.zzh(zzioVar.zza);
        }
        return iZzj;
    }

    public static int zzm(byte[] bArr, int i, zzio zzioVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzioVar.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        zzioVar.zzb = j2;
        return i3;
    }

    public static long zzn(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }
}
