package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgv {
    public static final /* synthetic */ int zza = 0;
    public static final zzhf zzb;

    static {
        int i = zzgq.zza;
        zzb = new zzhh();
    }

    public static void zzA(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzC(i, list, z);
    }

    public static void zzB(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzE(i, list, z);
    }

    public static void zzC(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzJ(i, list, z);
    }

    public static void zzD(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzL(i, list, z);
    }

    public static boolean zzE(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int zza(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfh)) {
            int iZzD = 0;
            while (i < size) {
                iZzD += zzen.zzD(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzD;
        }
        zzfh zzfhVar = (zzfh) list;
        int iZzD2 = 0;
        while (i < size) {
            iZzD2 += zzen.zzD(zzfhVar.zze(i));
            i++;
        }
        return iZzD2;
    }

    public static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzen.zzC(i << 3) + 4) * size;
    }

    public static int zzc(List list) {
        return list.size() * 4;
    }

    public static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzen.zzC(i << 3) + 8) * size;
    }

    public static int zze(List list) {
        return list.size() * 8;
    }

    public static int zzf(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfh)) {
            int iZzD = 0;
            while (i < size) {
                iZzD += zzen.zzD(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzD;
        }
        zzfh zzfhVar = (zzfh) list;
        int iZzD2 = 0;
        while (i < size) {
            iZzD2 += zzen.zzD(zzfhVar.zze(i));
            i++;
        }
        return iZzD2;
    }

    public static int zzg(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfy)) {
            int iZzD = 0;
            while (i < size) {
                iZzD += zzen.zzD(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzD;
        }
        zzfy zzfyVar = (zzfy) list;
        int iZzD2 = 0;
        while (i < size) {
            iZzD2 += zzen.zzD(zzfyVar.zze(i));
            i++;
        }
        return iZzD2;
    }

    public static int zzh(int i, Object obj, zzgt zzgtVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzfu)) {
            return zzen.zzA((zzgj) obj, zzgtVar) + zzen.zzC(i2);
        }
        int iZzC = zzen.zzC(i2);
        int iZza = ((zzfu) obj).zza();
        return zzgm$$ExternalSyntheticOutline0.m(iZza, iZza, iZzC);
    }

    public static int zzi(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfh)) {
            int iZzC = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzC += zzen.zzC((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
            return iZzC;
        }
        zzfh zzfhVar = (zzfh) list;
        int iZzC2 = 0;
        while (i < size) {
            int iZze = zzfhVar.zze(i);
            iZzC2 += zzen.zzC((iZze >> 31) ^ (iZze + iZze));
            i++;
        }
        return iZzC2;
    }

    public static int zzj(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfy)) {
            int iZzD = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzD += zzen.zzD((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
            return iZzD;
        }
        zzfy zzfyVar = (zzfy) list;
        int iZzD2 = 0;
        while (i < size) {
            long jZze = zzfyVar.zze(i);
            iZzD2 += zzen.zzD((jZze >> 63) ^ (jZze + jZze));
            i++;
        }
        return iZzD2;
    }

    public static int zzk(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfh)) {
            int iZzC = 0;
            while (i < size) {
                iZzC += zzen.zzC(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzC;
        }
        zzfh zzfhVar = (zzfh) list;
        int iZzC2 = 0;
        while (i < size) {
            iZzC2 += zzen.zzC(zzfhVar.zze(i));
            i++;
        }
        return iZzC2;
    }

    public static int zzl(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfy)) {
            int iZzD = 0;
            while (i < size) {
                iZzD += zzen.zzD(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzD;
        }
        zzfy zzfyVar = (zzfy) list;
        int iZzD2 = 0;
        while (i < size) {
            iZzD2 += zzen.zzD(zzfyVar.zze(i));
            i++;
        }
        return iZzD2;
    }

    public static zzhf zzm() {
        return zzb;
    }

    public static Object zzn(Object obj, int i, int i2, Object obj2, zzhf zzhfVar) {
        if (obj2 == null) {
            obj2 = zzhfVar.zza(obj);
        }
        ((zzhg) obj2).zzj(i << 3, Long.valueOf(i2));
        return obj2;
    }

    public static void zzo(zzet zzetVar, Object obj, Object obj2) {
        if (((zzfd) obj2).zzb.zza.isEmpty()) {
            return;
        }
        throw null;
    }

    public static void zzp(zzhf zzhfVar, Object obj, Object obj2) {
        zzfg zzfgVar = (zzfg) obj;
        zzhg zzhgVarZze = zzfgVar.zzc;
        zzhg zzhgVar = ((zzfg) obj2).zzc;
        zzhg zzhgVar2 = zzhg.zza;
        if (!zzhgVar2.equals(zzhgVar)) {
            if (zzhgVar2.equals(zzhgVarZze)) {
                zzhgVarZze = zzhg.zze(zzhgVarZze, zzhgVar);
            } else {
                zzhgVarZze.zzd(zzhgVar);
            }
        }
        zzfgVar.zzc = zzhgVarZze;
    }

    public static void zzq(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzc(i, list, z);
    }

    public static void zzr(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzg(i, list, z);
    }

    public static void zzs(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzj(i, list, z);
    }

    public static void zzt(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzl(i, list, z);
    }

    public static void zzu(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzn(i, list, z);
    }

    public static void zzv(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzp(i, list, z);
    }

    public static void zzw(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzs(i, list, z);
    }

    public static void zzx(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzu(i, list, z);
    }

    public static void zzy(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzy(i, list, z);
    }

    public static void zzz(int i, List list, zzhs zzhsVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhsVar.zzA(i, list, z);
    }
}
