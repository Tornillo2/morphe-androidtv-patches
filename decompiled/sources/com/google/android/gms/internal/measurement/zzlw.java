package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlw {
    public static final Class zza;
    public static final zzml zzb;
    public static final zzml zzc;
    public static final zzml zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzmn();
    }

    public static zzml zzA() {
        return zzc;
    }

    public static zzml zzB() {
        return zzd;
    }

    public static Object zzC(int i, List list, zzkg zzkgVar, Object obj, zzml zzmlVar) {
        if (zzkgVar == null) {
            return obj;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                if (!zzkgVar.zza(iIntValue)) {
                    obj = zzD(i, iIntValue, obj, zzmlVar);
                    it.remove();
                }
            }
            return obj;
        }
        int size = list.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            Integer num = (Integer) list.get(i3);
            int iIntValue2 = num.intValue();
            if (zzkgVar.zza(iIntValue2)) {
                if (i3 != i2) {
                    list.set(i2, num);
                }
                i2++;
            } else {
                obj = zzD(i, iIntValue2, obj, zzmlVar);
            }
        }
        if (i2 == size) {
            return obj;
        }
        list.subList(i2, size).clear();
        return obj;
    }

    public static Object zzD(int i, int i2, Object obj, zzml zzmlVar) {
        if (obj == null) {
            obj = zzmlVar.zze();
        }
        zzmlVar.zzf(obj, i, i2);
        return obj;
    }

    public static void zzE(zzjp zzjpVar, Object obj, Object obj2) {
        zzjpVar.zza(obj2);
        throw null;
    }

    public static void zzF(zzml zzmlVar, Object obj, Object obj2) {
        zzmlVar.zzh(obj, zzmlVar.zzd(zzmlVar.zzc(obj), zzmlVar.zzc(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzkc.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzc(i, list, z);
    }

    public static void zzI(int i, List list, zznd zzndVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zze(i, list);
    }

    public static void zzJ(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzg(i, list, z);
    }

    public static void zzK(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzj(i, list, z);
    }

    public static void zzL(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzl(i, list, z);
    }

    public static void zzM(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzn(i, list, z);
    }

    public static void zzN(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzp(i, list, z);
    }

    public static void zzO(int i, List list, zznd zzndVar, zzlu zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjk) zzndVar).zzq(i, list.get(i2), zzluVar);
        }
    }

    public static void zzP(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzs(i, list, z);
    }

    public static void zzQ(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzu(i, list, z);
    }

    public static void zzR(int i, List list, zznd zzndVar, zzlu zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjk) zzndVar).zzv(i, list.get(i2), zzluVar);
        }
    }

    public static void zzS(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzx(i, list, z);
    }

    public static void zzT(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzz(i, list, z);
    }

    public static void zzU(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzB(i, list, z);
    }

    public static void zzV(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzD(i, list, z);
    }

    public static void zzW(int i, List list, zznd zzndVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzG(i, list);
    }

    public static void zzX(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzI(i, list, z);
    }

    public static void zzY(int i, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzK(i, list, z);
    }

    public static boolean zzZ(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzA(i << 3) + 1) * size;
    }

    public static void zzaa(zzle zzleVar, Object obj, Object obj2, long j) {
        zzmv.zzs(obj, j, zzle.zzb(zzmv.zzf(obj, j), zzmv.zzf.zzm(obj2, j)));
    }

    public static zzml zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzml) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static int zzb(List list) {
        return list.size();
    }

    public static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjj.zzz(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzz += zzjj.zzt((zzjb) list.get(i2));
        }
        return iZzz;
    }

    public static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zze(list);
    }

    public static int zze(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzkd)) {
            int iZzv = 0;
            while (i < size) {
                iZzv += zzjj.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzv;
        }
        zzkd zzkdVar = (zzkd) list;
        int iZzv2 = 0;
        while (i < size) {
            iZzv2 += zzjj.zzv(zzkdVar.zze(i));
            i++;
        }
        return iZzv2;
    }

    public static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzA(i << 3) + 4) * size;
    }

    public static int zzg(List list) {
        return list.size() * 4;
    }

    public static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzA(i << 3) + 8) * size;
    }

    public static int zzi(List list) {
        return list.size() * 8;
    }

    public static int zzj(int i, List list, zzlu zzluVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzu = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzu += zzjj.zzu(i, (zzlj) list.get(i2), zzluVar);
        }
        return iZzu;
    }

    public static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zzl(list);
    }

    public static int zzl(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzkd)) {
            int iZzv = 0;
            while (i < size) {
                iZzv += zzjj.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzv;
        }
        zzkd zzkdVar = (zzkd) list;
        int iZzv2 = 0;
        while (i < size) {
            iZzv2 += zzjj.zzv(zzkdVar.zze(i));
            i++;
        }
        return iZzv2;
    }

    public static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * list.size()) + zzn(list);
    }

    public static int zzn(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzky)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzjj.zzB(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzB;
        }
        zzky zzkyVar = (zzky) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzjj.zzB(zzkyVar.zza(i));
            i++;
        }
        return iZzB2;
    }

    public static int zzo(int i, Object obj, zzlu zzluVar) {
        if (!(obj instanceof zzkp)) {
            return zzjj.zzx((zzlj) obj, zzluVar) + zzjj.zzA(i << 3);
        }
        int iZzA = zzjj.zzA(i << 3);
        int iZza = ((zzkp) obj).zza();
        return zzlm$$ExternalSyntheticOutline0.m(iZza, iZza, iZzA);
    }

    public static int zzp(int i, List list, zzlu zzluVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjj.zzz(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzkp) {
                int iZza = ((zzkp) obj).zza();
                iZzz = zzlm$$ExternalSyntheticOutline0.m(iZza, iZza, iZzz);
            } else {
                iZzz = zzjj.zzx((zzlj) obj, zzluVar) + iZzz;
            }
        }
        return iZzz;
    }

    public static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zzr(list);
    }

    public static int zzr(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzkd)) {
            int iZzA = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzA += zzjj.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
            return iZzA;
        }
        zzkd zzkdVar = (zzkd) list;
        int iZzA2 = 0;
        while (i < size) {
            int iZze = zzkdVar.zze(i);
            iZzA2 += zzjj.zzA((iZze >> 31) ^ (iZze + iZze));
            i++;
        }
        return iZzA2;
    }

    public static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zzt(list);
    }

    public static int zzt(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzky)) {
            int iZzB = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzB += zzjj.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
            return iZzB;
        }
        zzky zzkyVar = (zzky) list;
        int iZzB2 = 0;
        while (i < size) {
            long jZza = zzkyVar.zza(i);
            iZzB2 += zzjj.zzB((jZza >> 63) ^ (jZza + jZza));
            i++;
        }
        return iZzB2;
    }

    public static int zzu(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjj.zzz(i) * size;
        if (!(list instanceof zzkr)) {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzjb) {
                    int iZzd = ((zzjb) obj).zzd();
                    iZzz = zzlm$$ExternalSyntheticOutline0.m(iZzd, iZzd, iZzz);
                } else {
                    iZzz = zzjj.zzy((String) obj) + iZzz;
                }
                i2++;
            }
            return iZzz;
        }
        zzkr zzkrVar = (zzkr) list;
        while (i2 < size) {
            Object objZzf = zzkrVar.zzf(i2);
            if (objZzf instanceof zzjb) {
                int iZzd2 = ((zzjb) objZzf).zzd();
                iZzz = zzlm$$ExternalSyntheticOutline0.m(iZzd2, iZzd2, iZzz);
            } else {
                iZzz = zzjj.zzy((String) objZzf) + iZzz;
            }
            i2++;
        }
        return iZzz;
    }

    public static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zzw(list);
    }

    public static int zzw(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzkd)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzjj.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzA;
        }
        zzkd zzkdVar = (zzkd) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzjj.zzA(zzkdVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    public static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzjj.zzz(i) * size) + zzy(list);
    }

    public static int zzy(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzky)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzjj.zzB(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzB;
        }
        zzky zzkyVar = (zzky) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzjj.zzB(zzkyVar.zza(i));
            i++;
        }
        return iZzB2;
    }

    public static zzml zzz() {
        return zzb;
    }
}
