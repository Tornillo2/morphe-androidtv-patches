package com.google.android.gms.internal.measurement;

import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import androidx.media3.container.MdtaMetadataEntry;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlm<T> implements zzlu<T> {
    public static final int[] zza = new int[0];
    public static final Unsafe zzb = zzmv.zzg();
    public final int[] zzc;
    public final Object[] zzd;
    public final int zze;
    public final int zzf;
    public final zzlj zzg;
    public final boolean zzh;
    public final boolean zzi;
    public final int[] zzj;
    public final int zzk;
    public final int zzl;
    public final zzkx zzm;
    public final zzml zzn;
    public final zzjp zzo;
    public final zzlo zzp;
    public final zzle zzq;

    public zzlm(int[] iArr, Object[] objArr, int i, int i2, zzlj zzljVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzjpVar != null && zzjpVar.zzc(zzljVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzloVar;
        this.zzm = zzkxVar;
        this.zzn = zzmlVar;
        this.zzo = zzjpVar;
        this.zzg = zzljVar;
        this.zzq = zzleVar;
    }

    public static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    public static long zzC(Object obj, long j) {
        return ((Long) zzmv.zzf(obj, j)).longValue();
    }

    public static Field zzG(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Field ", str, " for ", name, " not found. Known fields are ");
            sbM.append(string);
            throw new RuntimeException(sbM.toString());
        }
    }

    public static boolean zzQ(Object obj, int i, zzlu zzluVar) {
        return zzluVar.zzk(zzmv.zzf(obj, i & 1048575));
    }

    public static boolean zzS(Object obj, long j) {
        return ((Boolean) zzmv.zzf(obj, j)).booleanValue();
    }

    public static final void zzT(int i, Object obj, zznd zzndVar) throws IOException {
        if (obj instanceof String) {
            zzndVar.zzF(i, (String) obj);
        } else {
            zzndVar.zzd(i, (zzjb) obj);
        }
    }

    public static zzmm zzd(Object obj) {
        zzkc zzkcVar = (zzkc) obj;
        zzmm zzmmVar = zzkcVar.zzc;
        if (zzmmVar != zzmm.zza) {
            return zzmmVar;
        }
        zzmm zzmmVarZze = zzmm.zze();
        zzkcVar.zzc = zzmmVarZze;
        return zzmmVarZze;
    }

    public static zzlm zzl(Class cls, zzlg zzlgVar, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar) {
        if (zzlgVar instanceof zzlt) {
            return zzm((zzlt) zzlgVar, zzloVar, zzkxVar, zzmlVar, zzjpVar, zzleVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0395  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.measurement.zzlm zzm(com.google.android.gms.internal.measurement.zzlt r34, com.google.android.gms.internal.measurement.zzlo r35, com.google.android.gms.internal.measurement.zzkx r36, com.google.android.gms.internal.measurement.zzml r37, com.google.android.gms.internal.measurement.zzjp r38, com.google.android.gms.internal.measurement.zzle r39) {
        /*
            Method dump skipped, instruction units count: 1007
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlm.zzm(com.google.android.gms.internal.measurement.zzlt, com.google.android.gms.internal.measurement.zzlo, com.google.android.gms.internal.measurement.zzkx, com.google.android.gms.internal.measurement.zzml, com.google.android.gms.internal.measurement.zzjp, com.google.android.gms.internal.measurement.zzle):com.google.android.gms.internal.measurement.zzlm");
    }

    public static double zzn(Object obj, long j) {
        return ((Double) zzmv.zzf(obj, j)).doubleValue();
    }

    public static float zzo(Object obj, long j) {
        return ((Float) zzmv.zzf(obj, j)).floatValue();
    }

    public static int zzr(Object obj, long j) {
        return ((Integer) zzmv.zzf(obj, j)).intValue();
    }

    public final int zzB(int i) {
        return this.zzc[i + 1];
    }

    public final zzkg zzD(int i) {
        int i2 = i / 3;
        return (zzkg) this.zzd[i2 + i2 + 1];
    }

    public final zzlu zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzlu zzluVar = (zzlu) this.zzd[i3];
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu zzluVarZzb = zzlr.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzluVarZzb;
        return zzluVarZzb;
    }

    public final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    public final void zzH(Object obj, Object obj2, int i) {
        long jZzB = zzB(i) & 1048575;
        if (zzO(obj2, i)) {
            zzmu zzmuVar = zzmv.zzf;
            Object objZzm = zzmuVar.zzm(obj, jZzB);
            Object objZzm2 = zzmuVar.zzm(obj2, jZzB);
            if (objZzm != null && objZzm2 != null) {
                zzmv.zzs(obj, jZzB, zzkk.zzg(objZzm, objZzm2));
                zzJ(obj, i);
            } else if (objZzm2 != null) {
                zzmv.zzs(obj, jZzB, objZzm2);
                zzJ(obj, i);
            }
        }
    }

    public final void zzI(Object obj, Object obj2, int i) {
        int iZzB = zzB(i);
        int i2 = this.zzc[i];
        long j = iZzB & 1048575;
        if (zzR(obj2, i2, i)) {
            Object objZzm = zzR(obj, i2, i) ? zzmv.zzf.zzm(obj, j) : null;
            Object objZzm2 = zzmv.zzf.zzm(obj2, j);
            if (objZzm != null && objZzm2 != null) {
                zzmv.zzs(obj, j, zzkk.zzg(objZzm, objZzm2));
                zzK(obj, i2, i);
            } else if (objZzm2 != null) {
                zzmv.zzs(obj, j, objZzm2);
                zzK(obj, i2, i);
            }
        }
    }

    public final void zzJ(Object obj, int i) {
        int iZzy = zzy(i);
        long j = 1048575 & iZzy;
        if (j == 1048575) {
            return;
        }
        zzmv.zzq(obj, j, (1 << (iZzy >>> 20)) | zzmv.zzc(obj, j));
    }

    public final void zzK(Object obj, int i, int i2) {
        zzmv.zzq(obj, zzy(i2) & 1048575, i);
    }

    public final void zzL(Object obj, zznd zzndVar) throws IOException {
        int i;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i3 < length) {
            int iZzB = zzB(i3);
            int[] iArr = this.zzc;
            int i6 = iArr[i3];
            int iZzA = zzA(iZzB);
            if (iZzA <= 17) {
                int i7 = iArr[i3 + 2];
                int i8 = i7 & i2;
                if (i8 != i5) {
                    i4 = unsafe.getInt(obj, i8);
                    i5 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = iZzB & i2;
            switch (iZzA) {
                case 0:
                    if ((i4 & i) != 0) {
                        zzndVar.zzf(i6, zzmv.zzf.zza(obj, j));
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 1:
                    if ((i4 & i) != 0) {
                        zzndVar.zzo(i6, zzmv.zzf.zzb(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 2:
                    if ((i4 & i) != 0) {
                        zzndVar.zzt(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 3:
                    if ((i4 & i) != 0) {
                        zzndVar.zzJ(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 4:
                    if ((i4 & i) != 0) {
                        zzndVar.zzr(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 5:
                    if ((i4 & i) != 0) {
                        zzndVar.zzm(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 6:
                    if ((i4 & i) != 0) {
                        zzndVar.zzk(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 7:
                    if ((i4 & i) != 0) {
                        zzndVar.zzb(i6, zzmv.zzf.zzg(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 8:
                    if ((i4 & i) != 0) {
                        zzT(i6, unsafe.getObject(obj, j), zzndVar);
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 9:
                    if ((i4 & i) != 0) {
                        zzndVar.zzv(i6, unsafe.getObject(obj, j), zzE(i3));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 10:
                    if ((i4 & i) != 0) {
                        zzndVar.zzd(i6, (zzjb) unsafe.getObject(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 11:
                    if ((i4 & i) != 0) {
                        zzndVar.zzH(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 12:
                    if ((i4 & i) != 0) {
                        zzndVar.zzi(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 13:
                    if ((i4 & i) != 0) {
                        zzndVar.zzw(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 14:
                    if ((i4 & i) != 0) {
                        zzndVar.zzy(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 15:
                    if ((i4 & i) != 0) {
                        zzndVar.zzA(i6, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 16:
                    if ((i4 & i) != 0) {
                        zzndVar.zzC(i6, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 17:
                    if ((i4 & i) != 0) {
                        zzndVar.zzq(i6, unsafe.getObject(obj, j), zzE(i3));
                    } else {
                        continue;
                    }
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 18:
                    zzlw.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 19:
                    zzlw.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 20:
                    zzlw.zzQ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 21:
                    zzlw.zzY(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 22:
                    zzlw.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 23:
                    zzlw.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 24:
                    zzlw.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 25:
                    zzlw.zzH(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    i3 += 3;
                    i2 = 1048575;
                    break;
                case 26:
                    zzlw.zzW(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar);
                    break;
                case 27:
                    zzlw.zzR(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, zzE(i3));
                    break;
                case 28:
                    zzlw.zzI(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar);
                    break;
                case 29:
                    zzlw.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 30:
                    zzlw.zzK(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 31:
                    zzlw.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 32:
                    zzlw.zzT(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 33:
                    zzlw.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 34:
                    zzlw.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 35:
                    zzlw.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 36:
                    zzlw.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 37:
                    zzlw.zzQ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 38:
                    zzlw.zzY(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 39:
                    zzlw.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 40:
                    zzlw.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 41:
                    zzlw.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 42:
                    zzlw.zzH(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 43:
                    zzlw.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 44:
                    zzlw.zzK(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 45:
                    zzlw.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 46:
                    zzlw.zzT(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 47:
                    zzlw.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 48:
                    zzlw.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 49:
                    zzlw.zzO(this.zzc[i3], (List) unsafe.getObject(obj, j), zzndVar, zzE(i3));
                    break;
                case 50:
                    zzM(zzndVar, i6, unsafe.getObject(obj, j), i3);
                    break;
                case 51:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzf(i6, zzn(obj, j));
                    }
                    break;
                case 52:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzo(i6, zzo(obj, j));
                    }
                    break;
                case 53:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzt(i6, zzC(obj, j));
                    }
                    break;
                case 54:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzJ(i6, zzC(obj, j));
                    }
                    break;
                case 55:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzr(i6, zzr(obj, j));
                    }
                    break;
                case 56:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzm(i6, zzC(obj, j));
                    }
                    break;
                case 57:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzk(i6, zzr(obj, j));
                    }
                    break;
                case 58:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzb(i6, zzS(obj, j));
                    }
                    break;
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj, i6, i3)) {
                        zzT(i6, unsafe.getObject(obj, j), zzndVar);
                    }
                    break;
                case 60:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzv(i6, unsafe.getObject(obj, j), zzE(i3));
                    }
                    break;
                case 61:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzd(i6, (zzjb) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzH(i6, zzr(obj, j));
                    }
                    break;
                case 63:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzi(i6, zzr(obj, j));
                    }
                    break;
                case 64:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzw(i6, zzr(obj, j));
                    }
                    break;
                case 65:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzy(i6, zzC(obj, j));
                    }
                    break;
                case 66:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzA(i6, zzr(obj, j));
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzC(i6, zzC(obj, j));
                    }
                    break;
                case 68:
                    if (zzR(obj, i6, i3)) {
                        zzndVar.zzq(i6, unsafe.getObject(obj, j), zzE(i3));
                    }
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        zzmlVar.zzi(zzmlVar.zzc(obj), zzndVar);
    }

    public final void zzM(zznd zzndVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    public final boolean zzN(Object obj, Object obj2, int i) {
        return zzO(obj, i) == zzO(obj2, i);
    }

    public final boolean zzO(Object obj, int i) {
        int iZzy = zzy(i);
        long j = iZzy & 1048575;
        if (j != 1048575) {
            return (zzmv.zzf.zzj(obj, j) & (1 << (iZzy >>> 20))) != 0;
        }
        int iZzB = zzB(i);
        long j2 = iZzB & 1048575;
        switch (zzA(iZzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmv.zzf.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmv.zzf.zzb(obj, j2)) != 0;
            case 2:
                return zzmv.zzf.zzk(obj, j2) != 0;
            case 3:
                return zzmv.zzf.zzk(obj, j2) != 0;
            case 4:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 5:
                return zzmv.zzf.zzk(obj, j2) != 0;
            case 6:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 7:
                return zzmv.zzf.zzg(obj, j2);
            case 8:
                Object objZzm = zzmv.zzf.zzm(obj, j2);
                if (objZzm instanceof String) {
                    return !((String) objZzm).isEmpty();
                }
                if (objZzm instanceof zzjb) {
                    return !zzjb.zzb.equals(objZzm);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzmv.zzf.zzm(obj, j2) != null;
            case 10:
                return !zzjb.zzb.equals(zzmv.zzf.zzm(obj, j2));
            case 11:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 12:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 13:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 14:
                return zzmv.zzf.zzk(obj, j2) != 0;
            case 15:
                return zzmv.zzf.zzj(obj, j2) != 0;
            case 16:
                return zzmv.zzf.zzk(obj, j2) != 0;
            case 17:
                return zzmv.zzf.zzm(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean zzP(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzO(obj, i) : (i3 & i4) != 0;
    }

    public final boolean zzR(Object obj, int i, int i2) {
        return zzmv.zzc(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(Object obj) {
        int i;
        int iZzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzB = zzB(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzB;
            int iHashCode = 37;
            switch (zzA(iZzB)) {
                case 0:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(Double.doubleToLongBits(zzmv.zzf.zza(obj, j)));
                    i2 = iZzc + i;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzc = Float.floatToIntBits(zzmv.zzf.zzb(obj, j));
                    i2 = iZzc + i;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(zzmv.zzf.zzk(obj, j));
                    i2 = iZzc + i;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(zzmv.zzf.zzk(obj, j));
                    i2 = iZzc + i;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(zzmv.zzf.zzk(obj, j));
                    i2 = iZzc + i;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzc = zzkk.zza(zzmv.zzf.zzg(obj, j));
                    i2 = iZzc + i;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzc = ((String) zzmv.zzf.zzm(obj, j)).hashCode();
                    i2 = iZzc + i;
                    break;
                case 9:
                    Object objZzm = zzmv.zzf.zzm(obj, j);
                    if (objZzm != null) {
                        iHashCode = objZzm.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(zzmv.zzf.zzk(obj, j));
                    i2 = iZzc + i;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzj(obj, j);
                    i2 = iZzc + i;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzc = zzkk.zzc(zzmv.zzf.zzk(obj, j));
                    i2 = iZzc + i;
                    break;
                case 17:
                    Object objZzm2 = zzmv.zzf.zzm(obj, j);
                    if (objZzm2 != null) {
                        iHashCode = objZzm2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                    i2 = iZzc + i;
                    break;
                case 51:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        i2 = iZzc + i;
                    }
                    break;
                case 52:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = Float.floatToIntBits(zzo(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 53:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(zzC(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 54:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(zzC(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 55:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 56:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(zzC(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 57:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 58:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zza(zzS(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = ((String) zzmv.zzf.zzm(obj, j)).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 60:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 61:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
                case 62:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 63:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 64:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case 65:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(zzC(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 66:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = iZzc + i;
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzkk.zzc(zzC(obj, j));
                        i2 = iZzc + i;
                    }
                    break;
                case 68:
                    if (zzR(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzmv.zzf.zzm(obj, j).hashCode();
                        i2 = iZzc + i;
                    }
                    break;
            }
        }
        int iHashCode2 = this.zzn.zzc(obj).hashCode() + (i2 * 53);
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:0x04a0, code lost:
    
        if (r11 == r15) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x04a2, code lost:
    
        r20.putInt(r9, r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x04a8, code lost:
    
        r0 = r8.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x04ac, code lost:
    
        if (r0 >= r8.zzl) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x04ae, code lost:
    
        r1 = r8.zzj[r0];
        r2 = r8.zzc[r1];
        r2 = com.google.android.gms.internal.measurement.zzmv.zzf.zzm(r9, r8.zzB(r1) & r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x04c2, code lost:
    
        if (r2 != null) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x04c9, code lost:
    
        if (r8.zzD(r1) != null) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x04cb, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x04ce, code lost:
    
        r2 = (com.google.android.gms.internal.measurement.zzld) r2;
        r0 = (com.google.android.gms.internal.measurement.zzlc) r8.zzF(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x04d6, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x04d7, code lost:
    
        if (r7 != 0) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x04d9, code lost:
    
        if (r3 != r4) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x04e0, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkm.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x04e1, code lost:
    
        if (r3 > r4) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x04e3, code lost:
    
        if (r6 != r7) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x04e5, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x04ea, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkm.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzc(java.lang.Object r28, byte[] r29, int r30, int r31, int r32, com.google.android.gms.internal.measurement.zzio r33) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlm.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzio):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final Object zze() {
        return ((zzkc) this.zzg).zzl(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzf(Object obj) {
        int i;
        int i2 = this.zzk;
        while (true) {
            i = this.zzl;
            if (i2 >= i) {
                break;
            }
            long jZzB = zzB(this.zzj[i2]) & 1048575;
            Object objZzm = zzmv.zzf.zzm(obj, jZzB);
            if (objZzm != null) {
                ((zzld) objZzm).zzb = false;
                zzmv.zzs(obj, jZzB, objZzm);
            }
            i2++;
        }
        int length = this.zzj.length;
        while (i < length) {
            this.zzm.zza(obj, this.zzj[i]);
            i++;
        }
        this.zzn.zzg(obj);
        if (this.zzh) {
            this.zzo.zzb(obj);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzg(Object obj, Object obj2) {
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB = zzB(i);
            long j = 1048575 & iZzB;
            int i2 = this.zzc[i];
            switch (zzA(iZzB)) {
                case 0:
                    if (zzO(obj2, i)) {
                        zzmv.zzo(obj, j, zzmv.zzf.zza(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 1:
                    if (zzO(obj2, i)) {
                        zzmv.zzp(obj, j, zzmv.zzf.zzb(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 2:
                    if (zzO(obj2, i)) {
                        zzmv.zzr(obj, j, zzmv.zzf.zzk(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 3:
                    if (zzO(obj2, i)) {
                        zzmv.zzr(obj, j, zzmv.zzf.zzk(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 4:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 5:
                    if (zzO(obj2, i)) {
                        zzmv.zzr(obj, j, zzmv.zzf.zzk(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 6:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 7:
                    if (zzO(obj2, i)) {
                        zzmv.zzm(obj, j, zzmv.zzf.zzg(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 8:
                    if (zzO(obj2, i)) {
                        zzmv.zzs(obj, j, zzmv.zzf.zzm(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (zzO(obj2, i)) {
                        zzmv.zzs(obj, j, zzmv.zzf.zzm(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 11:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 12:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 13:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 14:
                    if (zzO(obj2, i)) {
                        zzmv.zzr(obj, j, zzmv.zzf.zzk(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 15:
                    if (zzO(obj2, i)) {
                        zzmv.zzq(obj, j, zzmv.zzf.zzj(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 16:
                    if (zzO(obj2, i)) {
                        zzmv.zzr(obj, j, zzmv.zzf.zzk(obj2, j));
                        zzJ(obj, i);
                    }
                    break;
                case 17:
                    zzH(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzlw.zzaa(this.zzq, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj2, i2, i)) {
                        zzmv.zzs(obj, j, zzmv.zzf.zzm(obj2, j));
                        zzK(obj, i2, i);
                    }
                    break;
                case 60:
                    zzI(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj2, i2, i)) {
                        zzmv.zzs(obj, j, zzmv.zzf.zzm(obj2, j));
                        zzK(obj, i2, i);
                    }
                    break;
                case 68:
                    zzI(obj, obj2, i);
                    break;
            }
        }
        zzlw.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            this.zzo.zza(obj2);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzio zzioVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i, i2, zzioVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzioVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzi(Object obj, zznd zzndVar) throws IOException {
        if (!this.zzi) {
            zzL(obj, zzndVar);
            return;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            int i2 = this.zzc[i];
            switch (zzA(iZzB)) {
                case 0:
                    if (zzO(obj, i)) {
                        zzndVar.zzf(i2, zzmv.zzf.zza(obj, iZzB & 1048575));
                    }
                    break;
                case 1:
                    if (zzO(obj, i)) {
                        zzndVar.zzo(i2, zzmv.zzf.zzb(obj, iZzB & 1048575));
                    }
                    break;
                case 2:
                    if (zzO(obj, i)) {
                        zzndVar.zzt(i2, zzmv.zzf.zzk(obj, iZzB & 1048575));
                    }
                    break;
                case 3:
                    if (zzO(obj, i)) {
                        zzndVar.zzJ(i2, zzmv.zzf.zzk(obj, iZzB & 1048575));
                    }
                    break;
                case 4:
                    if (zzO(obj, i)) {
                        zzndVar.zzr(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 5:
                    if (zzO(obj, i)) {
                        zzndVar.zzm(i2, zzmv.zzf.zzk(obj, iZzB & 1048575));
                    }
                    break;
                case 6:
                    if (zzO(obj, i)) {
                        zzndVar.zzk(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 7:
                    if (zzO(obj, i)) {
                        zzndVar.zzb(i2, zzmv.zzf.zzg(obj, iZzB & 1048575));
                    }
                    break;
                case 8:
                    if (zzO(obj, i)) {
                        zzT(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar);
                    }
                    break;
                case 9:
                    if (zzO(obj, i)) {
                        zzndVar.zzv(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 10:
                    if (zzO(obj, i)) {
                        zzndVar.zzd(i2, (zzjb) zzmv.zzf.zzm(obj, iZzB & 1048575));
                    }
                    break;
                case 11:
                    if (zzO(obj, i)) {
                        zzndVar.zzH(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 12:
                    if (zzO(obj, i)) {
                        zzndVar.zzi(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 13:
                    if (zzO(obj, i)) {
                        zzndVar.zzw(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 14:
                    if (zzO(obj, i)) {
                        zzndVar.zzy(i2, zzmv.zzf.zzk(obj, iZzB & 1048575));
                    }
                    break;
                case 15:
                    if (zzO(obj, i)) {
                        zzndVar.zzA(i2, zzmv.zzf.zzj(obj, iZzB & 1048575));
                    }
                    break;
                case 16:
                    if (zzO(obj, i)) {
                        zzndVar.zzC(i2, zzmv.zzf.zzk(obj, iZzB & 1048575));
                    }
                    break;
                case 17:
                    if (zzO(obj, i)) {
                        zzndVar.zzq(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 18:
                    zzlw.zzJ(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 19:
                    zzlw.zzN(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 20:
                    zzlw.zzQ(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 21:
                    zzlw.zzY(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 22:
                    zzlw.zzP(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 23:
                    zzlw.zzM(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 24:
                    zzlw.zzL(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 25:
                    zzlw.zzH(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 26:
                    zzlw.zzW(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar);
                    break;
                case 27:
                    zzlw.zzR(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, zzE(i));
                    break;
                case 28:
                    zzlw.zzI(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar);
                    break;
                case 29:
                    zzlw.zzX(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 30:
                    zzlw.zzK(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 31:
                    zzlw.zzS(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 32:
                    zzlw.zzT(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 33:
                    zzlw.zzU(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 34:
                    zzlw.zzV(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, false);
                    break;
                case 35:
                    zzlw.zzJ(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 36:
                    zzlw.zzN(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 37:
                    zzlw.zzQ(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 38:
                    zzlw.zzY(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 39:
                    zzlw.zzP(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 40:
                    zzlw.zzM(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 41:
                    zzlw.zzL(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 42:
                    zzlw.zzH(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 43:
                    zzlw.zzX(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 44:
                    zzlw.zzK(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 45:
                    zzlw.zzS(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 46:
                    zzlw.zzT(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 47:
                    zzlw.zzU(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 48:
                    zzlw.zzV(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, true);
                    break;
                case 49:
                    zzlw.zzO(i2, (List) zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar, zzE(i));
                    break;
                case 50:
                    zzM(zzndVar, i2, zzmv.zzf.zzm(obj, iZzB & 1048575), i);
                    break;
                case 51:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzf(i2, zzn(obj, iZzB & 1048575));
                    }
                    break;
                case 52:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzo(i2, zzo(obj, iZzB & 1048575));
                    }
                    break;
                case 53:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzt(i2, zzC(obj, iZzB & 1048575));
                    }
                    break;
                case 54:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzJ(i2, zzC(obj, iZzB & 1048575));
                    }
                    break;
                case 55:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzr(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case 56:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzm(i2, zzC(obj, iZzB & 1048575));
                    }
                    break;
                case 57:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzk(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case 58:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzb(i2, zzS(obj, iZzB & 1048575));
                    }
                    break;
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj, i2, i)) {
                        zzT(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzndVar);
                    }
                    break;
                case 60:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzv(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzE(i));
                    }
                    break;
                case 61:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzd(i2, (zzjb) zzmv.zzf.zzm(obj, iZzB & 1048575));
                    }
                    break;
                case 62:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzH(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case 63:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzi(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case 64:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzw(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case 65:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzy(i2, zzC(obj, iZzB & 1048575));
                    }
                    break;
                case 66:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzA(i2, zzr(obj, iZzB & 1048575));
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzC(i2, zzC(obj, iZzB & 1048575));
                    }
                    break;
                case 68:
                    if (zzR(obj, i2, i)) {
                        zzndVar.zzq(i2, zzmv.zzf.zzm(obj, iZzB & 1048575), zzE(i));
                    }
                    break;
            }
        }
        zzml zzmlVar = this.zzn;
        zzmlVar.zzi(zzmlVar.zzc(obj), zzndVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzZ;
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            if (i < length) {
                int iZzB = zzB(i);
                long j = iZzB & 1048575;
                switch (zzA(iZzB)) {
                    case 0:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar = zzmv.zzf;
                            if (Double.doubleToLongBits(zzmuVar.zza(obj, j)) == Double.doubleToLongBits(zzmuVar.zza(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 1:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar2 = zzmv.zzf;
                            if (Float.floatToIntBits(zzmuVar2.zzb(obj, j)) == Float.floatToIntBits(zzmuVar2.zzb(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 2:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar3 = zzmv.zzf;
                            if (zzmuVar3.zzk(obj, j) == zzmuVar3.zzk(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 3:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar4 = zzmv.zzf;
                            if (zzmuVar4.zzk(obj, j) == zzmuVar4.zzk(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 4:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar5 = zzmv.zzf;
                            if (zzmuVar5.zzj(obj, j) == zzmuVar5.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 5:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar6 = zzmv.zzf;
                            if (zzmuVar6.zzk(obj, j) == zzmuVar6.zzk(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 6:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar7 = zzmv.zzf;
                            if (zzmuVar7.zzj(obj, j) == zzmuVar7.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 7:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar8 = zzmv.zzf;
                            if (zzmuVar8.zzg(obj, j) == zzmuVar8.zzg(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 8:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar9 = zzmv.zzf;
                            if (zzlw.zzZ(zzmuVar9.zzm(obj, j), zzmuVar9.zzm(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 9:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar10 = zzmv.zzf;
                            if (zzlw.zzZ(zzmuVar10.zzm(obj, j), zzmuVar10.zzm(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 10:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar11 = zzmv.zzf;
                            if (zzlw.zzZ(zzmuVar11.zzm(obj, j), zzmuVar11.zzm(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 11:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar12 = zzmv.zzf;
                            if (zzmuVar12.zzj(obj, j) == zzmuVar12.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 12:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar13 = zzmv.zzf;
                            if (zzmuVar13.zzj(obj, j) == zzmuVar13.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 13:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar14 = zzmv.zzf;
                            if (zzmuVar14.zzj(obj, j) == zzmuVar14.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 14:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar15 = zzmv.zzf;
                            if (zzmuVar15.zzk(obj, j) == zzmuVar15.zzk(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 15:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar16 = zzmv.zzf;
                            if (zzmuVar16.zzj(obj, j) == zzmuVar16.zzj(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 16:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar17 = zzmv.zzf;
                            if (zzmuVar17.zzk(obj, j) == zzmuVar17.zzk(obj2, j)) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 17:
                        if (zzN(obj, obj2, i)) {
                            zzmu zzmuVar18 = zzmv.zzf;
                            if (zzlw.zzZ(zzmuVar18.zzm(obj, j), zzmuVar18.zzm(obj2, j))) {
                                continue;
                                i += 3;
                            }
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        zzmu zzmuVar19 = zzmv.zzf;
                        zZzZ = zzlw.zzZ(zzmuVar19.zzm(obj, j), zzmuVar19.zzm(obj2, j));
                        break;
                    case 50:
                        zzmu zzmuVar20 = zzmv.zzf;
                        zZzZ = zzlw.zzZ(zzmuVar20.zzm(obj, j), zzmuVar20.zzm(obj2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case GifHeaderParser.TRAILER /* 59 */:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    case 68:
                        long jZzy = zzy(i) & 1048575;
                        zzmu zzmuVar21 = zzmv.zzf;
                        if (zzmuVar21.zzj(obj, jZzy) == zzmuVar21.zzj(obj2, jZzy) && zzlw.zzZ(zzmuVar21.zzm(obj, j), zzmuVar21.zzm(obj2, j))) {
                            continue;
                            i += 3;
                        }
                        break;
                    default:
                        i += 3;
                        break;
                }
                if (zZzZ) {
                    i += 3;
                }
            } else if (this.zzn.zzc(obj).equals(this.zzn.zzc(obj2))) {
                if (!this.zzh) {
                    return true;
                }
                this.zzo.zza(obj);
                throw null;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.zzk) {
            int i7 = this.zzj[i6];
            int i8 = this.zzc[i7];
            int iZzB = zzB(i7);
            int i9 = this.zzc[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i4) {
                if (i10 != 1048575) {
                    i5 = zzb.getInt(obj, i10);
                }
                i2 = i7;
                i3 = i5;
                i = i10;
            } else {
                int i12 = i5;
                i = i4;
                i2 = i7;
                i3 = i12;
            }
            if ((268435456 & iZzB) == 0 || zzP(obj, i2, i, i3, i11)) {
                int iZzA = zzA(iZzB);
                if (iZzA != 9 && iZzA != 17) {
                    if (iZzA != 27) {
                        if (iZzA == 60 || iZzA == 68) {
                            if (!zzR(obj, i8, i2) || zzQ(obj, iZzB, zzE(i2))) {
                            }
                        } else if (iZzA != 49) {
                            if (iZzA != 50) {
                                continue;
                            } else {
                                if (!((zzld) zzmv.zzf.zzm(obj, iZzB & 1048575)).isEmpty()) {
                                    throw null;
                                }
                            }
                        }
                        i6++;
                        i4 = i;
                        i5 = i3;
                    }
                    List list = (List) zzmv.zzf.zzm(obj, iZzB & 1048575);
                    if (list.isEmpty()) {
                        continue;
                    } else {
                        zzlu zzluVarZzE = zzE(i2);
                        for (int i13 = 0; i13 < list.size(); i13++) {
                            if (zzluVarZzE.zzk(list.get(i13))) {
                            }
                        }
                    }
                    i6++;
                    i4 = i;
                    i5 = i3;
                } else if (!zzP(obj, i2, i, i3, i11) || zzQ(obj, iZzB, zzE(i2))) {
                    i6++;
                    i4 = i;
                    i5 = i3;
                }
            }
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int zzp(Object obj) {
        int i;
        int iZzA;
        int iZzB;
        int iZzA2;
        int iZzv;
        int iZzo;
        int iZzu;
        int iZzA3;
        int iZzB2;
        int iZzA4;
        int iZzv2;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int iM = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i3 < this.zzc.length) {
            int iZzB3 = zzB(i3);
            int[] iArr = this.zzc;
            int i6 = iArr[i3];
            int iZzA5 = zzA(iZzB3);
            if (iZzA5 <= 17) {
                int i7 = iArr[i3 + 2];
                int i8 = i7 & i2;
                i = 1 << (i7 >>> 20);
                if (i8 != i5) {
                    i4 = unsafe.getInt(obj, i8);
                    i5 = i8;
                }
            } else {
                i = 0;
            }
            long j = iZzB3 & i2;
            switch (iZzA5) {
                case 0:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 1:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 2:
                    if ((i4 & i) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzA = zzjj.zzA(i6 << 3);
                        iZzB = zzjj.zzB(j2);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 3:
                    if ((i4 & i) != 0) {
                        long j3 = unsafe.getLong(obj, j);
                        iZzA = zzjj.zzA(i6 << 3);
                        iZzB = zzjj.zzB(j3);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 4:
                    if ((i4 & i) != 0) {
                        int i9 = unsafe.getInt(obj, j);
                        iZzA2 = zzjj.zzA(i6 << 3);
                        iZzv = zzjj.zzv(i9);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 5:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 6:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 7:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 1, iM);
                    }
                    break;
                case 8:
                    if ((i4 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (!(object instanceof zzjb)) {
                            iZzA2 = zzjj.zzA(i6 << 3);
                            iZzv = zzjj.zzy((String) object);
                            iZzo = iZzv + iZzA2;
                            iM += iZzo;
                        } else {
                            int iZzA6 = zzjj.zzA(i6 << 3);
                            int iZzd = ((zzjb) object).zzd();
                            iM = zzlm$$ExternalSyntheticOutline1.m(iZzd, iZzd, iZzA6, iM);
                        }
                    }
                    break;
                case 9:
                    if ((i4 & i) != 0) {
                        iZzo = zzlw.zzo(i6, unsafe.getObject(obj, j), zzE(i3));
                        iM += iZzo;
                    }
                    break;
                case 10:
                    if ((i4 & i) != 0) {
                        zzjb zzjbVar = (zzjb) unsafe.getObject(obj, j);
                        int iZzA7 = zzjj.zzA(i6 << 3);
                        int iZzd2 = zzjbVar.zzd();
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzd2, iZzd2, iZzA7, iM);
                    }
                    break;
                case 11:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(unsafe.getInt(obj, j), zzjj.zzA(i6 << 3), iM);
                    }
                    break;
                case 12:
                    if ((i4 & i) != 0) {
                        int i10 = unsafe.getInt(obj, j);
                        iZzA2 = zzjj.zzA(i6 << 3);
                        iZzv = zzjj.zzv(i10);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 13:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 14:
                    if ((i4 & i) != 0) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 15:
                    if ((i4 & i) != 0) {
                        int i11 = unsafe.getInt(obj, j);
                        iM = zzlm$$ExternalSyntheticOutline0.m((i11 >> 31) ^ (i11 + i11), zzjj.zzA(i6 << 3), iM);
                    }
                    break;
                case 16:
                    if ((i4 & i) != 0) {
                        long j4 = unsafe.getLong(obj, j);
                        iZzA = zzjj.zzA(i6 << 3);
                        iZzB = zzjj.zzB((j4 >> 63) ^ (j4 + j4));
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 17:
                    if ((i4 & i) != 0) {
                        iZzo = zzjj.zzu(i6, (zzlj) unsafe.getObject(obj, j), zzE(i3));
                        iM += iZzo;
                    }
                    break;
                case 18:
                    iZzo = zzlw.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 19:
                    iZzo = zzlw.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 20:
                    iZzo = zzlw.zzm(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 21:
                    iZzo = zzlw.zzx(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 22:
                    iZzo = zzlw.zzk(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 23:
                    iZzo = zzlw.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 24:
                    iZzo = zzlw.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 25:
                    iZzo = zzlw.zza(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzo;
                    break;
                case 26:
                    iZzu = zzlw.zzu(i6, (List) unsafe.getObject(obj, j));
                    iM += iZzu;
                    break;
                case 27:
                    iZzu = zzlw.zzp(i6, (List) unsafe.getObject(obj, j), zzE(i3));
                    iM += iZzu;
                    break;
                case 28:
                    iZzu = zzlw.zzc(i6, (List) unsafe.getObject(obj, j));
                    iM += iZzu;
                    break;
                case 29:
                    iZzu = zzlw.zzv(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 30:
                    iZzu = zzlw.zzd(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 31:
                    iZzu = zzlw.zzf(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 32:
                    iZzu = zzlw.zzh(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 33:
                    iZzu = zzlw.zzq(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 34:
                    iZzu = zzlw.zzs(i6, (List) unsafe.getObject(obj, j), false);
                    iM += iZzu;
                    break;
                case 35:
                    int iZzi = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi, zzjj.zzz(i6), iZzi, iM);
                    }
                    break;
                case 36:
                    int iZzg = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg, zzjj.zzz(i6), iZzg, iM);
                    }
                    break;
                case 37:
                    int iZzn = zzlw.zzn((List) unsafe.getObject(obj, j));
                    if (iZzn > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzn, zzjj.zzz(i6), iZzn, iM);
                    }
                    break;
                case 38:
                    int iZzy = zzlw.zzy((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzy, zzjj.zzz(i6), iZzy, iM);
                    }
                    break;
                case 39:
                    int iZzl = zzlw.zzl((List) unsafe.getObject(obj, j));
                    if (iZzl > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzl, zzjj.zzz(i6), iZzl, iM);
                    }
                    break;
                case 40:
                    int iZzi2 = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi2 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi2, zzjj.zzz(i6), iZzi2, iM);
                    }
                    break;
                case 41:
                    int iZzg2 = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg2 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg2, zzjj.zzz(i6), iZzg2, iM);
                    }
                    break;
                case 42:
                    int iZzb = zzlw.zzb((List) unsafe.getObject(obj, j));
                    if (iZzb > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzb, zzjj.zzz(i6), iZzb, iM);
                    }
                    break;
                case 43:
                    int iZzw = zzlw.zzw((List) unsafe.getObject(obj, j));
                    if (iZzw > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzw, zzjj.zzz(i6), iZzw, iM);
                    }
                    break;
                case 44:
                    int iZze = zzlw.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZze, zzjj.zzz(i6), iZze, iM);
                    }
                    break;
                case 45:
                    int iZzg3 = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg3 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg3, zzjj.zzz(i6), iZzg3, iM);
                    }
                    break;
                case 46:
                    int iZzi3 = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi3 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi3, zzjj.zzz(i6), iZzi3, iM);
                    }
                    break;
                case 47:
                    int iZzr = zzlw.zzr((List) unsafe.getObject(obj, j));
                    if (iZzr > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzr, zzjj.zzz(i6), iZzr, iM);
                    }
                    break;
                case 48:
                    int iZzt = zzlw.zzt((List) unsafe.getObject(obj, j));
                    if (iZzt > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzt, zzjj.zzz(i6), iZzt, iM);
                    }
                    break;
                case 49:
                    iZzu = zzlw.zzj(i6, (List) unsafe.getObject(obj, j), zzE(i3));
                    iM += iZzu;
                    break;
                case 50:
                    zzle.zza(i6, unsafe.getObject(obj, j), zzF(i3));
                    break;
                case 51:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 52:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 53:
                    if (zzR(obj, i6, i3)) {
                        long jZzC = zzC(obj, j);
                        iZzA3 = zzjj.zzA(i6 << 3);
                        iZzB2 = zzjj.zzB(jZzC);
                        iZzu = iZzB2 + iZzA3;
                        iM += iZzu;
                    }
                    break;
                case 54:
                    if (zzR(obj, i6, i3)) {
                        long jZzC2 = zzC(obj, j);
                        iZzA3 = zzjj.zzA(i6 << 3);
                        iZzB2 = zzjj.zzB(jZzC2);
                        iZzu = iZzB2 + iZzA3;
                        iM += iZzu;
                    }
                    break;
                case 55:
                    if (zzR(obj, i6, i3)) {
                        int iZzr2 = zzr(obj, j);
                        iZzA4 = zzjj.zzA(i6 << 3);
                        iZzv2 = zzjj.zzv(iZzr2);
                        iZzu = iZzv2 + iZzA4;
                        iM += iZzu;
                    }
                    break;
                case 56:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 57:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 58:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 1, iM);
                    }
                    break;
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj, i6, i3)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzjb) {
                            int iZzA8 = zzjj.zzA(i6 << 3);
                            int iZzd3 = ((zzjb) object2).zzd();
                            iM = zzlm$$ExternalSyntheticOutline1.m(iZzd3, iZzd3, iZzA8, iM);
                        } else {
                            iZzA4 = zzjj.zzA(i6 << 3);
                            iZzv2 = zzjj.zzy((String) object2);
                            iZzu = iZzv2 + iZzA4;
                            iM += iZzu;
                        }
                    }
                    break;
                case 60:
                    if (zzR(obj, i6, i3)) {
                        iZzu = zzlw.zzo(i6, unsafe.getObject(obj, j), zzE(i3));
                        iM += iZzu;
                    }
                    break;
                case 61:
                    if (zzR(obj, i6, i3)) {
                        zzjb zzjbVar2 = (zzjb) unsafe.getObject(obj, j);
                        int iZzA9 = zzjj.zzA(i6 << 3);
                        int iZzd4 = zzjbVar2.zzd();
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzd4, iZzd4, iZzA9, iM);
                    }
                    break;
                case 62:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(zzr(obj, j), zzjj.zzA(i6 << 3), iM);
                    }
                    break;
                case 63:
                    if (zzR(obj, i6, i3)) {
                        int iZzr3 = zzr(obj, j);
                        iZzA4 = zzjj.zzA(i6 << 3);
                        iZzv2 = zzjj.zzv(iZzr3);
                        iZzu = iZzv2 + iZzA4;
                        iM += iZzu;
                    }
                    break;
                case 64:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 4, iM);
                    }
                    break;
                case 65:
                    if (zzR(obj, i6, i3)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i6 << 3, 8, iM);
                    }
                    break;
                case 66:
                    if (zzR(obj, i6, i3)) {
                        int iZzr4 = zzr(obj, j);
                        iM = zzlm$$ExternalSyntheticOutline0.m((iZzr4 >> 31) ^ (iZzr4 + iZzr4), zzjj.zzA(i6 << 3), iM);
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj, i6, i3)) {
                        long jZzC3 = zzC(obj, j);
                        iZzA3 = zzjj.zzA(i6 << 3);
                        iZzB2 = zzjj.zzB((jZzC3 >> 63) ^ (jZzC3 + jZzC3));
                        iZzu = iZzB2 + iZzA3;
                        iM += iZzu;
                    }
                    break;
                case 68:
                    if (zzR(obj, i6, i3)) {
                        iZzu = zzjj.zzu(i6, (zzlj) unsafe.getObject(obj, j), zzE(i3));
                        iM += iZzu;
                    }
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        int iZza = zzmlVar.zza(zzmlVar.zzc(obj)) + iM;
        if (!this.zzh) {
            return iZza;
        }
        this.zzo.zza(obj);
        throw null;
    }

    public final int zzq(Object obj) {
        int iZzA;
        int iZzB;
        int iZzA2;
        int iZzv;
        int iZzo;
        int iZzA3;
        int iZzB2;
        Unsafe unsafe = zzb;
        int iM = 0;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB3 = zzB(i);
            int iZzA4 = zzA(iZzB3);
            int[] iArr = this.zzc;
            int i2 = iArr[i];
            long j = iZzB3 & 1048575;
            if (iZzA4 >= zzju.zzJ.zzac && iZzA4 <= zzju.zzW.zzac) {
                int i3 = iArr[i + 2];
            }
            switch (iZzA4) {
                case 0:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 1:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 2:
                    if (zzO(obj, i)) {
                        long jZzk = zzmv.zzf.zzk(obj, j);
                        iZzA = zzjj.zzA(i2 << 3);
                        iZzB = zzjj.zzB(jZzk);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 3:
                    if (zzO(obj, i)) {
                        long jZzk2 = zzmv.zzf.zzk(obj, j);
                        iZzA = zzjj.zzA(i2 << 3);
                        iZzB = zzjj.zzB(jZzk2);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 4:
                    if (zzO(obj, i)) {
                        int iZzj = zzmv.zzf.zzj(obj, j);
                        iZzA2 = zzjj.zzA(i2 << 3);
                        iZzv = zzjj.zzv(iZzj);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 5:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 6:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 7:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 1, iM);
                    }
                    break;
                case 8:
                    if (zzO(obj, i)) {
                        Object objZzm = zzmv.zzf.zzm(obj, j);
                        if (objZzm instanceof zzjb) {
                            int iZzA5 = zzjj.zzA(i2 << 3);
                            int iZzd = ((zzjb) objZzm).zzd();
                            iM = zzlm$$ExternalSyntheticOutline1.m(iZzd, iZzd, iZzA5, iM);
                        } else {
                            iZzA2 = zzjj.zzA(i2 << 3);
                            iZzv = zzjj.zzy((String) objZzm);
                            iZzo = iZzv + iZzA2;
                            iM += iZzo;
                        }
                    }
                    break;
                case 9:
                    if (zzO(obj, i)) {
                        iZzo = zzlw.zzo(i2, zzmv.zzf.zzm(obj, j), zzE(i));
                        iM += iZzo;
                    }
                    break;
                case 10:
                    if (zzO(obj, i)) {
                        zzjb zzjbVar = (zzjb) zzmv.zzf.zzm(obj, j);
                        int iZzA6 = zzjj.zzA(i2 << 3);
                        int iZzd2 = zzjbVar.zzd();
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzd2, iZzd2, iZzA6, iM);
                    }
                    break;
                case 11:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(zzmv.zzf.zzj(obj, j), zzjj.zzA(i2 << 3), iM);
                    }
                    break;
                case 12:
                    if (zzO(obj, i)) {
                        int iZzj2 = zzmv.zzf.zzj(obj, j);
                        iZzA2 = zzjj.zzA(i2 << 3);
                        iZzv = zzjj.zzv(iZzj2);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 13:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 14:
                    if (zzO(obj, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 15:
                    if (zzO(obj, i)) {
                        int iZzj3 = zzmv.zzf.zzj(obj, j);
                        iM = zzlm$$ExternalSyntheticOutline0.m((iZzj3 >> 31) ^ (iZzj3 + iZzj3), zzjj.zzA(i2 << 3), iM);
                    }
                    break;
                case 16:
                    if (zzO(obj, i)) {
                        long jZzk3 = zzmv.zzf.zzk(obj, j);
                        iZzA3 = zzjj.zzA(i2 << 3);
                        iZzB2 = zzjj.zzB((jZzk3 >> 63) ^ (jZzk3 + jZzk3));
                        iZzo = iZzB2 + iZzA3;
                        iM += iZzo;
                    }
                    break;
                case 17:
                    if (zzO(obj, i)) {
                        iZzo = zzjj.zzu(i2, (zzlj) zzmv.zzf.zzm(obj, j), zzE(i));
                        iM += iZzo;
                    }
                    break;
                case 18:
                    iZzo = zzlw.zzh(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 19:
                    iZzo = zzlw.zzf(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 20:
                    iZzo = zzlw.zzm(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 21:
                    iZzo = zzlw.zzx(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 22:
                    iZzo = zzlw.zzk(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 23:
                    iZzo = zzlw.zzh(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 24:
                    iZzo = zzlw.zzf(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 25:
                    iZzo = zzlw.zza(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 26:
                    iZzo = zzlw.zzu(i2, (List) zzmv.zzf.zzm(obj, j));
                    iM += iZzo;
                    break;
                case 27:
                    iZzo = zzlw.zzp(i2, (List) zzmv.zzf.zzm(obj, j), zzE(i));
                    iM += iZzo;
                    break;
                case 28:
                    iZzo = zzlw.zzc(i2, (List) zzmv.zzf.zzm(obj, j));
                    iM += iZzo;
                    break;
                case 29:
                    iZzo = zzlw.zzv(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 30:
                    iZzo = zzlw.zzd(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 31:
                    iZzo = zzlw.zzf(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 32:
                    iZzo = zzlw.zzh(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 33:
                    iZzo = zzlw.zzq(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 34:
                    iZzo = zzlw.zzs(i2, (List) zzmv.zzf.zzm(obj, j), false);
                    iM += iZzo;
                    break;
                case 35:
                    int iZzi = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi, zzjj.zzz(i2), iZzi, iM);
                    }
                    break;
                case 36:
                    int iZzg = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg, zzjj.zzz(i2), iZzg, iM);
                    }
                    break;
                case 37:
                    int iZzn = zzlw.zzn((List) unsafe.getObject(obj, j));
                    if (iZzn > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzn, zzjj.zzz(i2), iZzn, iM);
                    }
                    break;
                case 38:
                    int iZzy = zzlw.zzy((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzy, zzjj.zzz(i2), iZzy, iM);
                    }
                    break;
                case 39:
                    int iZzl = zzlw.zzl((List) unsafe.getObject(obj, j));
                    if (iZzl > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzl, zzjj.zzz(i2), iZzl, iM);
                    }
                    break;
                case 40:
                    int iZzi2 = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi2 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi2, zzjj.zzz(i2), iZzi2, iM);
                    }
                    break;
                case 41:
                    int iZzg2 = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg2 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg2, zzjj.zzz(i2), iZzg2, iM);
                    }
                    break;
                case 42:
                    int iZzb = zzlw.zzb((List) unsafe.getObject(obj, j));
                    if (iZzb > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzb, zzjj.zzz(i2), iZzb, iM);
                    }
                    break;
                case 43:
                    int iZzw = zzlw.zzw((List) unsafe.getObject(obj, j));
                    if (iZzw > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzw, zzjj.zzz(i2), iZzw, iM);
                    }
                    break;
                case 44:
                    int iZze = zzlw.zze((List) unsafe.getObject(obj, j));
                    if (iZze > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZze, zzjj.zzz(i2), iZze, iM);
                    }
                    break;
                case 45:
                    int iZzg3 = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (iZzg3 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzg3, zzjj.zzz(i2), iZzg3, iM);
                    }
                    break;
                case 46:
                    int iZzi3 = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi3 > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzi3, zzjj.zzz(i2), iZzi3, iM);
                    }
                    break;
                case 47:
                    int iZzr = zzlw.zzr((List) unsafe.getObject(obj, j));
                    if (iZzr > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzr, zzjj.zzz(i2), iZzr, iM);
                    }
                    break;
                case 48:
                    int iZzt = zzlw.zzt((List) unsafe.getObject(obj, j));
                    if (iZzt > 0) {
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzt, zzjj.zzz(i2), iZzt, iM);
                    }
                    break;
                case 49:
                    iZzo = zzlw.zzj(i2, (List) zzmv.zzf.zzm(obj, j), zzE(i));
                    iM += iZzo;
                    break;
                case 50:
                    zzle.zza(i2, zzmv.zzf.zzm(obj, j), zzF(i));
                    break;
                case 51:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 52:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 53:
                    if (zzR(obj, i2, i)) {
                        long jZzC = zzC(obj, j);
                        iZzA = zzjj.zzA(i2 << 3);
                        iZzB = zzjj.zzB(jZzC);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 54:
                    if (zzR(obj, i2, i)) {
                        long jZzC2 = zzC(obj, j);
                        iZzA = zzjj.zzA(i2 << 3);
                        iZzB = zzjj.zzB(jZzC2);
                        iZzo = iZzB + iZzA;
                        iM += iZzo;
                    }
                    break;
                case 55:
                    if (zzR(obj, i2, i)) {
                        int iZzr2 = zzr(obj, j);
                        iZzA2 = zzjj.zzA(i2 << 3);
                        iZzv = zzjj.zzv(iZzr2);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 56:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 57:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 58:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 1, iM);
                    }
                    break;
                case GifHeaderParser.TRAILER /* 59 */:
                    if (zzR(obj, i2, i)) {
                        Object objZzm2 = zzmv.zzf.zzm(obj, j);
                        if (objZzm2 instanceof zzjb) {
                            int iZzA7 = zzjj.zzA(i2 << 3);
                            int iZzd3 = ((zzjb) objZzm2).zzd();
                            iM = zzlm$$ExternalSyntheticOutline1.m(iZzd3, iZzd3, iZzA7, iM);
                        } else {
                            iZzA2 = zzjj.zzA(i2 << 3);
                            iZzv = zzjj.zzy((String) objZzm2);
                            iZzo = iZzv + iZzA2;
                            iM += iZzo;
                        }
                    }
                    break;
                case 60:
                    if (zzR(obj, i2, i)) {
                        iZzo = zzlw.zzo(i2, zzmv.zzf.zzm(obj, j), zzE(i));
                        iM += iZzo;
                    }
                    break;
                case 61:
                    if (zzR(obj, i2, i)) {
                        zzjb zzjbVar2 = (zzjb) zzmv.zzf.zzm(obj, j);
                        int iZzA8 = zzjj.zzA(i2 << 3);
                        int iZzd4 = zzjbVar2.zzd();
                        iM = zzlm$$ExternalSyntheticOutline1.m(iZzd4, iZzd4, iZzA8, iM);
                    }
                    break;
                case 62:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(zzr(obj, j), zzjj.zzA(i2 << 3), iM);
                    }
                    break;
                case 63:
                    if (zzR(obj, i2, i)) {
                        int iZzr3 = zzr(obj, j);
                        iZzA2 = zzjj.zzA(i2 << 3);
                        iZzv = zzjj.zzv(iZzr3);
                        iZzo = iZzv + iZzA2;
                        iM += iZzo;
                    }
                    break;
                case 64:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 4, iM);
                    }
                    break;
                case 65:
                    if (zzR(obj, i2, i)) {
                        iM = zzlm$$ExternalSyntheticOutline0.m(i2 << 3, 8, iM);
                    }
                    break;
                case 66:
                    if (zzR(obj, i2, i)) {
                        int iZzr4 = zzr(obj, j);
                        iM = zzlm$$ExternalSyntheticOutline0.m((iZzr4 >> 31) ^ (iZzr4 + iZzr4), zzjj.zzA(i2 << 3), iM);
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (zzR(obj, i2, i)) {
                        long jZzC3 = zzC(obj, j);
                        iZzA3 = zzjj.zzA(i2 << 3);
                        iZzB2 = zzjj.zzB((jZzC3 >> 63) ^ (jZzC3 + jZzC3));
                        iZzo = iZzB2 + iZzA3;
                        iM += iZzo;
                    }
                    break;
                case 68:
                    if (zzR(obj, i2, i)) {
                        iZzo = zzjj.zzu(i2, (zzlj) zzmv.zzf.zzm(obj, j), zzE(i));
                        iM += iZzo;
                    }
                    break;
            }
        }
        zzml zzmlVar = this.zzn;
        return zzmlVar.zza(zzmlVar.zzc(obj)) + iM;
    }

    public final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzio zzioVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzF = zzF(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzld) object).zzb) {
            zzld zzldVarZzb = zzld.zza.zzb();
            zzle.zzb(zzldVarZzb, object);
            unsafe.putObject(obj, j, zzldVarZzb);
        }
        throw null;
    }

    public final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzio zzioVar) throws IOException {
        Object object;
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzip.zzn(bArr, i))));
                unsafe.putInt(obj, j2, i4);
                return i + 8;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzip.zzb(bArr, i))));
                unsafe.putInt(obj, j2, i4);
                return i + 4;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iZzm = zzip.zzm(bArr, i, zzioVar);
                unsafe.putObject(obj, j, Long.valueOf(zzioVar.zzb));
                unsafe.putInt(obj, j2, i4);
                return iZzm;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iZzj = zzip.zzj(bArr, i, zzioVar);
                unsafe.putObject(obj, j, Integer.valueOf(zzioVar.zza));
                unsafe.putInt(obj, j2, i4);
                return iZzj;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Long.valueOf(zzip.zzn(bArr, i)));
                unsafe.putInt(obj, j2, i4);
                return i + 8;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Integer.valueOf(zzip.zzb(bArr, i)));
                unsafe.putInt(obj, j2, i4);
                return i + 4;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iZzm2 = zzip.zzm(bArr, i, zzioVar);
                unsafe.putObject(obj, j, Boolean.valueOf(zzioVar.zzb != 0));
                unsafe.putInt(obj, j2, i4);
                return iZzm2;
            case GifHeaderParser.TRAILER /* 59 */:
                if (i5 != 2) {
                    return i;
                }
                int iZzj2 = zzip.zzj(bArr, i, zzioVar);
                int i9 = zzioVar.zza;
                if (i9 == 0) {
                    unsafe.putObject(obj, j, "");
                } else {
                    if ((i6 & 536870912) != 0 && !zzna.zzf(bArr, iZzj2, iZzj2 + i9)) {
                        throw zzkm.zzc();
                    }
                    unsafe.putObject(obj, j, new String(bArr, iZzj2, i9, zzkk.zzb));
                    iZzj2 += i9;
                }
                unsafe.putInt(obj, j2, i4);
                return iZzj2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iZzd = zzip.zzd(zzE(i8), bArr, i, i2, zzioVar);
                object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                if (object == null) {
                    unsafe.putObject(obj, j, zzioVar.zzc);
                } else {
                    unsafe.putObject(obj, j, zzkk.zzg(object, zzioVar.zzc));
                }
                unsafe.putInt(obj, j2, i4);
                return iZzd;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zzip.zza(bArr, i, zzioVar);
                unsafe.putObject(obj, j, zzioVar.zzc);
                unsafe.putInt(obj, j2, i4);
                return iZza;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iZzj3 = zzip.zzj(bArr, i, zzioVar);
                int i10 = zzioVar.zza;
                zzkg zzkgVarZzD = zzD(i8);
                if (zzkgVarZzD != null && !zzkgVarZzD.zza(i10)) {
                    zzd(obj).zzh(i3, Long.valueOf(i10));
                    return iZzj3;
                }
                unsafe.putObject(obj, j, Integer.valueOf(i10));
                unsafe.putInt(obj, j2, i4);
                return iZzj3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iZzj4 = zzip.zzj(bArr, i, zzioVar);
                unsafe.putObject(obj, j, Integer.valueOf(zzjf.zzb(zzioVar.zza)));
                unsafe.putInt(obj, j2, i4);
                return iZzj4;
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                if (i5 != 0) {
                    return i;
                }
                int iZzm3 = zzip.zzm(bArr, i, zzioVar);
                unsafe.putObject(obj, j, Long.valueOf(zzjf.zzc(zzioVar.zzb)));
                unsafe.putInt(obj, j2, i4);
                return iZzm3;
            case 68:
                if (i5 == 3) {
                    int iZzc = zzip.zzc(zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zzioVar);
                    object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zzioVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzkk.zzg(object, zzioVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzc;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x007f. Please report as an issue. */
    public final int zzu(Object obj, byte[] bArr, int i, int i2, zzio zzioVar) throws IOException {
        Object obj2;
        Unsafe unsafe;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        zzlm<T> zzlmVar = this;
        Object obj3 = obj;
        byte[] bArr2 = bArr;
        int i13 = i2;
        zzio zzioVar2 = zzioVar;
        Unsafe unsafe2 = zzb;
        int i14 = -1;
        int iZzm = i;
        int i15 = -1;
        int i16 = 0;
        int i17 = 1048575;
        int i18 = 0;
        while (iZzm < i13) {
            int iZzk = iZzm + 1;
            int i19 = bArr2[iZzm];
            if (i19 < 0) {
                iZzk = zzip.zzk(i19, bArr2, iZzk, zzioVar2);
                i19 = zzioVar2.zza;
            }
            int i20 = iZzk;
            int i21 = i19 >>> 3;
            int i22 = i19 & 7;
            int iZzx = i21 > i15 ? zzlmVar.zzx(i21, i16 / 3) : zzlmVar.zzw(i21);
            if (iZzx == i14) {
                obj2 = obj3;
                unsafe = unsafe2;
                i3 = i19;
                i4 = i21;
                i5 = 0;
            } else {
                int[] iArr = zzlmVar.zzc;
                int i23 = iArr[iZzx + 1];
                int iZzA = zzA(i23);
                int i24 = i19;
                int i25 = iZzx;
                long j = i23 & 1048575;
                if (iZzA <= 17) {
                    int i26 = iArr[i25 + 2];
                    int i27 = 1 << (i26 >>> 20);
                    int i28 = i26 & 1048575;
                    if (i28 != i17) {
                        int i29 = 1048575;
                        if (i17 != 1048575) {
                            unsafe2.putInt(obj3, i17, i18);
                            i29 = 1048575;
                        }
                        if (i28 != i29) {
                            i18 = unsafe2.getInt(obj3, i28);
                        }
                        i17 = i28;
                    }
                    switch (iZzA) {
                        case 0:
                            i12 = i25;
                            if (i22 != 1) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                zzmv.zzo(obj3, j, Double.longBitsToDouble(zzip.zzn(bArr2, i20)));
                                iZzm = i20 + 8;
                                i18 |= i27;
                                i13 = i2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 1:
                            i12 = i25;
                            if (i22 != 5) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                zzmv.zzp(obj3, j, Float.intBitsToFloat(zzip.zzb(bArr2, i20)));
                                iZzm = i20 + 4;
                                i18 |= i27;
                                i13 = i2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 2:
                        case 3:
                            i12 = i25;
                            if (i22 != 0) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                int iZzm2 = zzip.zzm(bArr2, i20, zzioVar2);
                                Unsafe unsafe3 = unsafe2;
                                Object obj4 = obj3;
                                unsafe3.putLong(obj4, j, zzioVar2.zzb);
                                unsafe2 = unsafe3;
                                obj3 = obj4;
                                i18 |= i27;
                                iZzm = iZzm2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                                i13 = i2;
                            }
                            break;
                        case 4:
                        case 11:
                            i12 = i25;
                            if (i22 != 0) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                int iZzj = zzip.zzj(bArr2, i20, zzioVar2);
                                unsafe2.putInt(obj3, j, zzioVar2.zza);
                                i18 |= i27;
                                i13 = i2;
                                iZzm = iZzj;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 5:
                        case 14:
                            i12 = i25;
                            if (i22 != 1) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                Unsafe unsafe4 = unsafe2;
                                Object obj5 = obj3;
                                unsafe4.putLong(obj5, j, zzip.zzn(bArr2, i20));
                                unsafe2 = unsafe4;
                                obj3 = obj5;
                                iZzm = i20 + 8;
                                i18 |= i27;
                                i13 = i2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 6:
                        case 13:
                            i12 = i25;
                            if (i22 != 5) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                unsafe2.putInt(obj3, j, zzip.zzb(bArr2, i20));
                                iZzm = i20 + 4;
                                i18 |= i27;
                                i13 = i2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 7:
                            i12 = i25;
                            if (i22 != 0) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = zzip.zzm(bArr2, i20, zzioVar2);
                                zzmv.zzm(obj3, j, zzioVar2.zzb != 0);
                                i18 |= i27;
                                i13 = i2;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 8:
                            i12 = i25;
                            if (i22 != 2) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = (536870912 & i23) == 0 ? zzip.zzg(bArr2, i20, zzioVar2) : zzip.zzh(bArr2, i20, zzioVar2);
                                unsafe2.putObject(obj3, j, zzioVar2.zzc);
                                i18 |= i27;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 9:
                            i12 = i25;
                            if (i22 != 2) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = zzip.zzd(zzlmVar.zzE(i12), bArr2, i20, i13, zzioVar2);
                                Object object = unsafe2.getObject(obj3, j);
                                if (object == null) {
                                    unsafe2.putObject(obj3, j, zzioVar2.zzc);
                                } else {
                                    unsafe2.putObject(obj3, j, zzkk.zzg(object, zzioVar2.zzc));
                                }
                                i18 |= i27;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 10:
                            i12 = i25;
                            if (i22 != 2) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = zzip.zza(bArr2, i20, zzioVar2);
                                unsafe2.putObject(obj3, j, zzioVar2.zzc);
                                i18 |= i27;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 12:
                            i12 = i25;
                            if (i22 != 0) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = zzip.zzj(bArr2, i20, zzioVar2);
                                unsafe2.putInt(obj3, j, zzioVar2.zza);
                                i18 |= i27;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 15:
                            i12 = i25;
                            if (i22 != 0) {
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                iZzm = zzip.zzj(bArr2, i20, zzioVar2);
                                unsafe2.putInt(obj3, j, zzjf.zzb(zzioVar2.zza));
                                i18 |= i27;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        case 16:
                            if (i22 != 0) {
                                i12 = i25;
                                obj2 = obj3;
                                unsafe = unsafe2;
                                i4 = i21;
                                i5 = i12;
                                i3 = i24 == true ? 1 : 0;
                            } else {
                                int iZzm3 = zzip.zzm(bArr2, i20, zzioVar2);
                                Unsafe unsafe5 = unsafe2;
                                Object obj6 = obj3;
                                i12 = i25;
                                unsafe5.putLong(obj6, j, zzjf.zzc(zzioVar2.zzb));
                                unsafe2 = unsafe5;
                                obj3 = obj6;
                                i18 |= i27;
                                iZzm = iZzm3;
                                i15 = i21;
                                i16 = i12;
                                i14 = -1;
                            }
                            break;
                        default:
                            i12 = i25;
                            obj2 = obj3;
                            unsafe = unsafe2;
                            i4 = i21;
                            i5 = i12;
                            i3 = i24 == true ? 1 : 0;
                            break;
                    }
                } else {
                    i5 = i25;
                    if (iZzA != 27) {
                        i6 = i20;
                        Unsafe unsafe6 = unsafe2;
                        if (iZzA <= 49) {
                            i7 = i18;
                            unsafe = unsafe6;
                            i10 = i17;
                            int iZzv = zzlmVar.zzv(obj, bArr, i6, i2, i24 == true ? 1 : 0, i21, i22, i5, i23, iZzA, j, zzioVar);
                            if (iZzv != i6) {
                                zzlmVar = this;
                                obj3 = obj;
                                zzioVar2 = zzioVar;
                                iZzm = iZzv;
                                i16 = i5;
                                i15 = i21;
                                i17 = i10;
                                i18 = i7;
                                unsafe2 = unsafe;
                                i14 = -1;
                                bArr2 = bArr;
                                i13 = i2;
                            } else {
                                obj2 = obj;
                                i20 = iZzv;
                                i4 = i21;
                                i3 = i24 == true ? 1 : 0;
                                i17 = i10;
                                i18 = i7;
                            }
                        } else {
                            i7 = i18;
                            unsafe = unsafe6;
                            i8 = i24 == true ? 1 : 0;
                            i9 = i5;
                            i10 = i17;
                            i11 = i21;
                            if (iZzA == 50) {
                                if (i22 == 2) {
                                    zzs(obj, bArr, i6, i2, i9, j, zzioVar);
                                    throw null;
                                }
                                obj2 = obj;
                                i4 = i11;
                                i3 = i8;
                                i5 = i9;
                                i17 = i10;
                                i18 = i7;
                                i20 = i6;
                            } else {
                                i4 = i11;
                                int iZzt = zzt(obj, bArr, i6, i2, i8 == true ? 1 : 0, i4, i22, i23, iZzA, j, i9, zzioVar);
                                obj2 = obj;
                                i3 = i8 == true ? 1 : 0;
                                i5 = i9;
                                if (iZzt != i6) {
                                    zzlmVar = this;
                                    zzioVar2 = zzioVar;
                                    i15 = i4;
                                    iZzm = iZzt;
                                    i16 = i5;
                                    obj3 = obj2;
                                    i17 = i10;
                                    i18 = i7;
                                    unsafe2 = unsafe;
                                    i14 = -1;
                                    bArr2 = bArr;
                                    i13 = i2;
                                } else {
                                    i20 = iZzt;
                                    i17 = i10;
                                    i18 = i7;
                                }
                            }
                        }
                    } else if (i22 == 2) {
                        zzkj zzkjVarZzd = (zzkj) unsafe2.getObject(obj3, j);
                        if (!zzkjVarZzd.zzc()) {
                            int size = zzkjVarZzd.size();
                            zzkjVarZzd = zzkjVarZzd.zzd(size == 0 ? 10 : size + size);
                            unsafe2.putObject(obj3, j, zzkjVarZzd);
                        }
                        int iZze = zzip.zze(zzlmVar.zzE(i5), i24 == true ? 1 : 0, bArr2, i20, i2, zzkjVarZzd, zzioVar2);
                        bArr2 = bArr;
                        zzioVar2 = zzioVar;
                        iZzm = iZze;
                        i16 = i5;
                        unsafe2 = unsafe2;
                        i15 = i21;
                        i14 = -1;
                        obj3 = obj;
                        i13 = i2;
                    } else {
                        i6 = i20;
                        i10 = i17;
                        i7 = i18;
                        unsafe = unsafe2;
                        i11 = i21;
                        i8 = i24 == true ? 1 : 0;
                        i9 = i5;
                        obj2 = obj;
                        i4 = i11;
                        i3 = i8;
                        i5 = i9;
                        i17 = i10;
                        i18 = i7;
                        i20 = i6;
                    }
                }
            }
            int iZzi = zzip.zzi(i3 == true ? 1 : 0, bArr, i20, i2, zzd(obj2), zzioVar);
            bArr2 = bArr;
            zzioVar2 = zzioVar;
            i15 = i4;
            i16 = i5;
            obj3 = obj2;
            unsafe2 = unsafe;
            i14 = -1;
            i13 = i2;
            iZzm = iZzi;
            zzlmVar = this;
        }
        Object obj7 = obj3;
        Unsafe unsafe7 = unsafe2;
        int i30 = i13;
        int i31 = i17;
        int i32 = i18;
        if (i31 != 1048575) {
            unsafe7.putInt(obj7, i31, i32);
        }
        if (iZzm == i30) {
            return iZzm;
        }
        throw zzkm.zze();
    }

    public final int zzv(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzio zzioVar) throws IOException {
        int iZzl;
        Unsafe unsafe = zzb;
        zzkj zzkjVarZzd = (zzkj) unsafe.getObject(obj, j2);
        if (!zzkjVarZzd.zzc()) {
            int size = zzkjVarZzd.size();
            zzkjVarZzd = zzkjVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzkjVarZzd);
        }
        zzkj zzkjVar = zzkjVarZzd;
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzjl zzjlVar = (zzjl) zzkjVar;
                    int iZzj = zzip.zzj(bArr, i, zzioVar);
                    int i8 = zzioVar.zza + iZzj;
                    while (iZzj < i8) {
                        zzjlVar.zze(Double.longBitsToDouble(zzip.zzn(bArr, iZzj)));
                        iZzj += 8;
                    }
                    if (iZzj == i8) {
                        return iZzj;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 1) {
                    zzjl zzjlVar2 = (zzjl) zzkjVar;
                    zzjlVar2.zze(Double.longBitsToDouble(zzip.zzn(bArr, i)));
                    int i9 = i + 8;
                    while (i9 < i2) {
                        int iZzj2 = zzip.zzj(bArr, i9, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return i9;
                        }
                        zzjlVar2.zze(Double.longBitsToDouble(zzip.zzn(bArr, iZzj2)));
                        i9 = iZzj2 + 8;
                    }
                    return i9;
                }
                return i;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzjv zzjvVar = (zzjv) zzkjVar;
                    int iZzj3 = zzip.zzj(bArr, i, zzioVar);
                    int i10 = zzioVar.zza + iZzj3;
                    while (iZzj3 < i10) {
                        zzjvVar.zze(Float.intBitsToFloat(zzip.zzb(bArr, iZzj3)));
                        iZzj3 += 4;
                    }
                    if (iZzj3 == i10) {
                        return iZzj3;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 5) {
                    zzjv zzjvVar2 = (zzjv) zzkjVar;
                    zzjvVar2.zze(Float.intBitsToFloat(zzip.zzb(bArr, i)));
                    int i11 = i + 4;
                    while (i11 < i2) {
                        int iZzj4 = zzip.zzj(bArr, i11, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return i11;
                        }
                        zzjvVar2.zze(Float.intBitsToFloat(zzip.zzb(bArr, iZzj4)));
                        i11 = iZzj4 + 4;
                    }
                    return i11;
                }
                return i;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzky zzkyVar = (zzky) zzkjVar;
                    int iZzj5 = zzip.zzj(bArr, i, zzioVar);
                    int i12 = zzioVar.zza + iZzj5;
                    while (iZzj5 < i12) {
                        iZzj5 = zzip.zzm(bArr, iZzj5, zzioVar);
                        zzkyVar.zzg(zzioVar.zzb);
                    }
                    if (iZzj5 == i12) {
                        return iZzj5;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 0) {
                    zzky zzkyVar2 = (zzky) zzkjVar;
                    int iZzm = zzip.zzm(bArr, i, zzioVar);
                    zzkyVar2.zzg(zzioVar.zzb);
                    while (iZzm < i2) {
                        int iZzj6 = zzip.zzj(bArr, iZzm, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzip.zzm(bArr, iZzj6, zzioVar);
                        zzkyVar2.zzg(zzioVar.zzb);
                    }
                    return iZzm;
                }
                return i;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzip.zzf(bArr, i, zzkjVar, zzioVar);
                }
                if (i5 == 0) {
                    return zzip.zzl(i3, bArr, i, i2, zzkjVar, zzioVar);
                }
                return i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzky zzkyVar3 = (zzky) zzkjVar;
                    int iZzj7 = zzip.zzj(bArr, i, zzioVar);
                    int i13 = zzioVar.zza + iZzj7;
                    while (iZzj7 < i13) {
                        zzkyVar3.zzg(zzip.zzn(bArr, iZzj7));
                        iZzj7 += 8;
                    }
                    if (iZzj7 == i13) {
                        return iZzj7;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 1) {
                    zzky zzkyVar4 = (zzky) zzkjVar;
                    zzkyVar4.zzg(zzip.zzn(bArr, i));
                    int i14 = i + 8;
                    while (i14 < i2) {
                        int iZzj8 = zzip.zzj(bArr, i14, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return i14;
                        }
                        zzkyVar4.zzg(zzip.zzn(bArr, iZzj8));
                        i14 = iZzj8 + 8;
                    }
                    return i14;
                }
                return i;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzkd zzkdVar = (zzkd) zzkjVar;
                    int iZzj9 = zzip.zzj(bArr, i, zzioVar);
                    int i15 = zzioVar.zza + iZzj9;
                    while (iZzj9 < i15) {
                        zzkdVar.zzh(zzip.zzb(bArr, iZzj9));
                        iZzj9 += 4;
                    }
                    if (iZzj9 == i15) {
                        return iZzj9;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 5) {
                    zzkd zzkdVar2 = (zzkd) zzkjVar;
                    zzkdVar2.zzh(zzip.zzb(bArr, i));
                    int i16 = i + 4;
                    while (i16 < i2) {
                        int iZzj10 = zzip.zzj(bArr, i16, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return i16;
                        }
                        zzkdVar2.zzh(zzip.zzb(bArr, iZzj10));
                        i16 = iZzj10 + 4;
                    }
                    return i16;
                }
                return i;
            case 25:
            case 42:
                if (i5 == 2) {
                    zziq zziqVar = (zziq) zzkjVar;
                    int iZzj11 = zzip.zzj(bArr, i, zzioVar);
                    int i17 = zzioVar.zza + iZzj11;
                    while (iZzj11 < i17) {
                        iZzj11 = zzip.zzm(bArr, iZzj11, zzioVar);
                        zziqVar.zze(zzioVar.zzb != 0);
                    }
                    if (iZzj11 == i17) {
                        return iZzj11;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 0) {
                    zziq zziqVar2 = (zziq) zzkjVar;
                    int iZzm2 = zzip.zzm(bArr, i, zzioVar);
                    zziqVar2.zze(zzioVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj12 = zzip.zzj(bArr, iZzm2, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzip.zzm(bArr, iZzj12, zzioVar);
                        zziqVar2.zze(zzioVar.zzb != 0);
                    }
                    return iZzm2;
                }
                return i;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj13 = zzip.zzj(bArr, i, zzioVar);
                        int i18 = zzioVar.zza;
                        if (i18 < 0) {
                            throw zzkm.zzd();
                        }
                        if (i18 == 0) {
                            zzkjVar.add("");
                        } else {
                            zzkjVar.add(new String(bArr, iZzj13, i18, zzkk.zzb));
                            iZzj13 += i18;
                        }
                        while (iZzj13 < i2) {
                            int iZzj14 = zzip.zzj(bArr, iZzj13, zzioVar);
                            if (i3 != zzioVar.zza) {
                                return iZzj13;
                            }
                            iZzj13 = zzip.zzj(bArr, iZzj14, zzioVar);
                            int i19 = zzioVar.zza;
                            if (i19 < 0) {
                                throw zzkm.zzd();
                            }
                            if (i19 == 0) {
                                zzkjVar.add("");
                            } else {
                                zzkjVar.add(new String(bArr, iZzj13, i19, zzkk.zzb));
                                iZzj13 += i19;
                            }
                        }
                        return iZzj13;
                    }
                    int iZzj15 = zzip.zzj(bArr, i, zzioVar);
                    int i20 = zzioVar.zza;
                    if (i20 < 0) {
                        throw zzkm.zzd();
                    }
                    if (i20 == 0) {
                        zzkjVar.add("");
                    } else {
                        int i21 = iZzj15 + i20;
                        if (!zzna.zzf(bArr, iZzj15, i21)) {
                            throw zzkm.zzc();
                        }
                        zzkjVar.add(new String(bArr, iZzj15, i20, zzkk.zzb));
                        iZzj15 = i21;
                    }
                    while (iZzj15 < i2) {
                        int iZzj16 = zzip.zzj(bArr, iZzj15, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzj15;
                        }
                        iZzj15 = zzip.zzj(bArr, iZzj16, zzioVar);
                        int i22 = zzioVar.zza;
                        if (i22 < 0) {
                            throw zzkm.zzd();
                        }
                        if (i22 == 0) {
                            zzkjVar.add("");
                        } else {
                            int i23 = iZzj15 + i22;
                            if (!zzna.zzf(bArr, iZzj15, i23)) {
                                throw zzkm.zzc();
                            }
                            zzkjVar.add(new String(bArr, iZzj15, i22, zzkk.zzb));
                            iZzj15 = i23;
                        }
                    }
                    return iZzj15;
                }
                return i;
            case 27:
                if (i5 == 2) {
                    return zzip.zze(zzE(i6), i3, bArr, i, i2, zzkjVar, zzioVar);
                }
                return i;
            case 28:
                if (i5 == 2) {
                    int iZzj17 = zzip.zzj(bArr, i, zzioVar);
                    int i24 = zzioVar.zza;
                    if (i24 < 0) {
                        throw zzkm.zzd();
                    }
                    if (i24 > bArr.length - iZzj17) {
                        throw zzkm.zzf();
                    }
                    if (i24 == 0) {
                        zzkjVar.add(zzjb.zzb);
                    } else {
                        zzkjVar.add(zzjb.zzl(bArr, iZzj17, i24));
                        iZzj17 += i24;
                    }
                    while (iZzj17 < i2) {
                        int iZzj18 = zzip.zzj(bArr, iZzj17, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzip.zzj(bArr, iZzj18, zzioVar);
                        int i25 = zzioVar.zza;
                        if (i25 < 0) {
                            throw zzkm.zzd();
                        }
                        if (i25 > bArr.length - iZzj17) {
                            throw zzkm.zzf();
                        }
                        if (i25 == 0) {
                            zzkjVar.add(zzjb.zzb);
                        } else {
                            zzkjVar.add(zzjb.zzl(bArr, iZzj17, i25));
                            iZzj17 += i25;
                        }
                    }
                    return iZzj17;
                }
                return i;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZzl = zzip.zzl(i3, bArr, i, i2, zzkjVar, zzioVar);
                    }
                    return i;
                }
                iZzl = zzip.zzf(bArr, i, zzkjVar, zzioVar);
                zzkc zzkcVar = (zzkc) obj;
                zzmm zzmmVar = zzkcVar.zzc;
                if (zzmmVar == zzmm.zza) {
                    zzmmVar = null;
                }
                Object objZzC = zzlw.zzC(i4, zzkjVar, zzD(i6), zzmmVar, this.zzn);
                if (objZzC == null) {
                    return iZzl;
                }
                zzkcVar.zzc = (zzmm) objZzC;
                return iZzl;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzkd zzkdVar3 = (zzkd) zzkjVar;
                    int iZzj19 = zzip.zzj(bArr, i, zzioVar);
                    int i26 = zzioVar.zza + iZzj19;
                    while (iZzj19 < i26) {
                        iZzj19 = zzip.zzj(bArr, iZzj19, zzioVar);
                        zzkdVar3.zzh(zzjf.zzb(zzioVar.zza));
                    }
                    if (iZzj19 == i26) {
                        return iZzj19;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 0) {
                    zzkd zzkdVar4 = (zzkd) zzkjVar;
                    int iZzj20 = zzip.zzj(bArr, i, zzioVar);
                    zzkdVar4.zzh(zzjf.zzb(zzioVar.zza));
                    while (iZzj20 < i2) {
                        int iZzj21 = zzip.zzj(bArr, iZzj20, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzj20;
                        }
                        iZzj20 = zzip.zzj(bArr, iZzj21, zzioVar);
                        zzkdVar4.zzh(zzjf.zzb(zzioVar.zza));
                    }
                    return iZzj20;
                }
                return i;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzky zzkyVar5 = (zzky) zzkjVar;
                    int iZzj22 = zzip.zzj(bArr, i, zzioVar);
                    int i27 = zzioVar.zza + iZzj22;
                    while (iZzj22 < i27) {
                        iZzj22 = zzip.zzm(bArr, iZzj22, zzioVar);
                        zzkyVar5.zzg(zzjf.zzc(zzioVar.zzb));
                    }
                    if (iZzj22 == i27) {
                        return iZzj22;
                    }
                    throw zzkm.zzf();
                }
                if (i5 == 0) {
                    zzky zzkyVar6 = (zzky) zzkjVar;
                    int iZzm3 = zzip.zzm(bArr, i, zzioVar);
                    zzkyVar6.zzg(zzjf.zzc(zzioVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj23 = zzip.zzj(bArr, iZzm3, zzioVar);
                        if (i3 != zzioVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzip.zzm(bArr, iZzj23, zzioVar);
                        zzkyVar6.zzg(zzjf.zzc(zzioVar.zzb));
                    }
                    return iZzm3;
                }
                return i;
            default:
                if (i5 == 3) {
                    zzlu zzluVarZzE = zzE(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzip.zzc(zzluVarZzE, bArr, i, i2, i28, zzioVar);
                    zzlu zzluVar = zzluVarZzE;
                    zzio zzioVar2 = zzioVar;
                    zzkjVar.add(zzioVar2.zzc);
                    while (iZzc < i2) {
                        int iZzj24 = zzip.zzj(bArr, iZzc, zzioVar2);
                        if (i3 != zzioVar2.zza) {
                            return iZzc;
                        }
                        zzlu zzluVar2 = zzluVar;
                        zzio zzioVar3 = zzioVar2;
                        iZzc = zzip.zzc(zzluVar2, bArr, iZzj24, i2, i28, zzioVar3);
                        zzkjVar.add(zzioVar3.zzc);
                        zzluVar = zzluVar2;
                        zzioVar2 = zzioVar3;
                    }
                    return iZzc;
                }
                return i;
        }
    }

    public final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    public final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    public final int zzy(int i) {
        return this.zzc[i + 2];
    }

    public final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
