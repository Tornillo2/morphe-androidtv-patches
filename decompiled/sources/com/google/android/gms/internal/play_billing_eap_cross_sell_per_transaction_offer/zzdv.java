package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdv {
    public static final /* synthetic */ int zza = 0;
    public static volatile int zzb = 100;

    public static int zza(byte[] bArr, int i, zzdu zzduVar) throws zzfo {
        int iZzi = zzi(bArr, i, zzduVar);
        int i2 = zzduVar.zza;
        if (i2 < 0) {
            throw new zzfo("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i2 > bArr.length - iZzi) {
            throw new zzfo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (i2 == 0) {
            zzduVar.zzc = zzeg.zzb;
            return iZzi;
        }
        zzduVar.zzc = zzeg.zzj(bArr, iZzi, i2);
        return iZzi + i2;
    }

    public static int zzb(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    public static int zzc(zzgt zzgtVar, byte[] bArr, int i, int i2, int i3, zzdu zzduVar) throws IOException {
        Object objZze = zzgtVar.zze();
        int iZzm = zzm(objZze, zzgtVar, bArr, i, i2, i3, zzduVar);
        zzgtVar.zzf(objZze);
        zzduVar.zzc = objZze;
        return iZzm;
    }

    public static int zzd(zzgt zzgtVar, byte[] bArr, int i, int i2, zzdu zzduVar) throws IOException {
        Object objZze = zzgtVar.zze();
        int iZzn = zzn(objZze, zzgtVar, bArr, i, i2, zzduVar);
        zzgtVar.zzf(objZze);
        zzduVar.zzc = objZze;
        return iZzn;
    }

    public static int zze(zzgt zzgtVar, int i, byte[] bArr, int i2, int i3, zzfl zzflVar, zzdu zzduVar) throws IOException {
        int iZzd = zzd(zzgtVar, bArr, i2, i3, zzduVar);
        zzflVar.add(zzduVar.zzc);
        while (iZzd < i3) {
            int iZzi = zzi(bArr, iZzd, zzduVar);
            if (i != zzduVar.zza) {
                break;
            }
            iZzd = zzd(zzgtVar, bArr, iZzi, i3, zzduVar);
            zzflVar.add(zzduVar.zzc);
        }
        return iZzd;
    }

    public static int zzf(byte[] bArr, int i, zzfl zzflVar, zzdu zzduVar) throws IOException {
        zzfh zzfhVar = (zzfh) zzflVar;
        int iZzi = zzi(bArr, i, zzduVar);
        int i2 = zzduVar.zza + iZzi;
        while (iZzi < i2) {
            iZzi = zzi(bArr, iZzi, zzduVar);
            zzfhVar.zzg(zzduVar.zza);
        }
        if (iZzi == i2) {
            return iZzi;
        }
        throw new zzfo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public static int zzg(byte[] bArr, int i, zzdu zzduVar) throws zzfo {
        int i2;
        int iZzi = zzi(bArr, i, zzduVar);
        int i3 = zzduVar.zza;
        if (i3 < 0) {
            throw new zzfo("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i3 == 0) {
            zzduVar.zzc = "";
            return iZzi;
        }
        int i4 = zzhp.zza;
        int length = bArr.length;
        if ((((length - iZzi) - i3) | iZzi | i3) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(iZzi), Integer.valueOf(i3)));
        }
        int i5 = iZzi + i3;
        char[] cArr = new char[i3];
        int i6 = 0;
        while (iZzi < i5) {
            byte b = bArr[iZzi];
            if (b < 0) {
                break;
            }
            iZzi++;
            cArr[i6] = (char) b;
            i6++;
        }
        int i7 = i6;
        while (iZzi < i5) {
            int i8 = iZzi + 1;
            byte b2 = bArr[iZzi];
            if (b2 >= 0) {
                cArr[i7] = (char) b2;
                i7++;
                iZzi = i8;
                while (iZzi < i5) {
                    byte b3 = bArr[iZzi];
                    if (b3 >= 0) {
                        iZzi++;
                        cArr[i7] = (char) b3;
                        i7++;
                    }
                }
            } else {
                if (b2 < -32) {
                    if (i8 >= i5) {
                        throw new zzfo("Protocol message had invalid UTF-8.");
                    }
                    i2 = i7 + 1;
                    iZzi += 2;
                    zzhn.zzc(b2, bArr[i8], cArr, i7);
                } else if (b2 < -16) {
                    if (i8 >= i5 - 1) {
                        throw new zzfo("Protocol message had invalid UTF-8.");
                    }
                    i2 = i7 + 1;
                    int i9 = iZzi + 2;
                    iZzi += 3;
                    zzhn.zzb(b2, bArr[i8], bArr[i9], cArr, i7);
                } else {
                    if (i8 >= i5 - 2) {
                        throw new zzfo("Protocol message had invalid UTF-8.");
                    }
                    byte b4 = bArr[i8];
                    int i10 = iZzi + 3;
                    byte b5 = bArr[iZzi + 2];
                    iZzi += 4;
                    zzhn.zza(b2, b4, b5, bArr[i10], cArr, i7);
                    i7 += 2;
                }
                i7 = i2;
            }
        }
        zzduVar.zzc = new String(cArr, 0, i7);
        return i5;
    }

    public static int zzh(int i, byte[] bArr, int i2, int i3, zzhg zzhgVar, zzdu zzduVar) throws zzfo {
        if ((i >>> 3) == 0) {
            throw new zzfo("Protocol message contained an invalid tag (zero).");
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzl = zzl(bArr, i2, zzduVar);
            zzhgVar.zzj(i, Long.valueOf(zzduVar.zzb));
            return iZzl;
        }
        if (i4 == 1) {
            zzhgVar.zzj(i, Long.valueOf(zzo(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzi = zzi(bArr, i2, zzduVar);
            int i5 = zzduVar.zza;
            if (i5 < 0) {
                throw new zzfo("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (i5 > bArr.length - iZzi) {
                throw new zzfo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            if (i5 == 0) {
                zzhgVar.zzj(i, zzeg.zzb);
            } else {
                zzhgVar.zzj(i, zzeg.zzj(bArr, iZzi, i5));
            }
            return iZzi + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw new zzfo("Protocol message contained an invalid tag (zero).");
            }
            zzhgVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzhg zzhgVarZzf = zzhg.zzf();
        int i7 = zzduVar.zze + 1;
        zzduVar.zze = i7;
        zzp(i7);
        int i8 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzi2 = zzi(bArr, i2, zzduVar);
            int i9 = zzduVar.zza;
            if (i9 == i6) {
                i8 = i9;
                i2 = iZzi2;
                break;
            }
            i2 = zzh(i9, bArr, iZzi2, i3, zzhgVarZzf, zzduVar);
            i8 = i9;
        }
        zzduVar.zze--;
        if (i2 > i3 || i8 != i6) {
            throw new zzfo("Failed to parse the message.");
        }
        zzhgVar.zzj(i, zzhgVarZzf);
        return i2;
    }

    public static int zzi(byte[] bArr, int i, zzdu zzduVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzj(b, bArr, i2, zzduVar);
        }
        zzduVar.zza = b;
        return i2;
    }

    public static int zzj(int i, byte[] bArr, int i2, zzdu zzduVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzduVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & 127) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzduVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzduVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzduVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzduVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int zzk(int i, byte[] bArr, int i2, int i3, zzfl zzflVar, zzdu zzduVar) {
        zzfh zzfhVar = (zzfh) zzflVar;
        int iZzi = zzi(bArr, i2, zzduVar);
        zzfhVar.zzg(zzduVar.zza);
        while (iZzi < i3) {
            int iZzi2 = zzi(bArr, iZzi, zzduVar);
            if (i != zzduVar.zza) {
                break;
            }
            iZzi = zzi(bArr, iZzi2, zzduVar);
            zzfhVar.zzg(zzduVar.zza);
        }
        return iZzi;
    }

    public static int zzl(byte[] bArr, int i, zzdu zzduVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzduVar.zzb = j;
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
        zzduVar.zzb = j2;
        return i3;
    }

    public static int zzm(Object obj, zzgt zzgtVar, byte[] bArr, int i, int i2, int i3, zzdu zzduVar) throws IOException {
        int i4 = zzduVar.zze + 1;
        zzduVar.zze = i4;
        zzp(i4);
        int iZzc = ((zzgm) zzgtVar).zzc(obj, bArr, i, i2, i3, zzduVar);
        zzduVar.zze--;
        zzduVar.zzc = obj;
        return iZzc;
    }

    public static int zzn(Object obj, zzgt zzgtVar, byte[] bArr, int i, int i2, zzdu zzduVar) throws IOException {
        int iZzj = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzj = zzj(i3, bArr, iZzj, zzduVar);
            i3 = zzduVar.zza;
        }
        int i4 = iZzj;
        if (i3 < 0 || i3 > i2 - i4) {
            throw new zzfo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        int i5 = zzduVar.zze + 1;
        zzduVar.zze = i5;
        zzp(i5);
        int i6 = i4 + i3;
        zzgtVar.zzh(obj, bArr, i4, i6, zzduVar);
        zzduVar.zze--;
        zzduVar.zzc = obj;
        return i6;
    }

    public static long zzo(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    public static void zzp(int i) throws zzfo {
        if (i >= zzb) {
            throw new zzfo("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }
}
