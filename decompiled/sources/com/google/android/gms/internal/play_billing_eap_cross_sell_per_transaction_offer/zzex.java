package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzex {
    public static final zzex zzb = new zzex(true);
    public final zzhb zza = new zzgw(null);
    public boolean zzc;
    public boolean zzd;

    public zzex() {
    }

    public static int zza(zzhq zzhqVar, int i, Object obj) {
        int iZzC = zzen.zzC(i << 3);
        if (zzhqVar == zzhq.zzj) {
            zzfm.zzd((zzgj) obj);
            iZzC += iZzC;
        }
        return zzb(zzhqVar, obj) + iZzC;
    }

    public static int zzb(zzhq zzhqVar, Object obj) {
        int iZzd;
        int iZzC;
        zzhq zzhqVar2 = zzhq.zza;
        zzhr zzhrVar = zzhr.zza;
        switch (zzhqVar.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                int i = zzen.zzb;
                return 8;
            case 1:
                ((Float) obj).getClass();
                int i2 = zzen.zzb;
                return 4;
            case 2:
                return zzen.zzD(((Long) obj).longValue());
            case 3:
                return zzen.zzD(((Long) obj).longValue());
            case 4:
                return zzen.zzD(((Integer) obj).intValue());
            case 5:
                ((Long) obj).getClass();
                int i3 = zzen.zzb;
                return 8;
            case 6:
                ((Integer) obj).getClass();
                int i4 = zzen.zzb;
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                int i5 = zzen.zzb;
                return 1;
            case 8:
                if (!(obj instanceof zzeg)) {
                    return zzen.zzB((String) obj);
                }
                int i6 = zzen.zzb;
                iZzd = ((zzeg) obj).zzd();
                iZzC = zzen.zzC(iZzd);
                break;
                break;
            case 9:
                return ((zzgj) obj).zzj();
            case 10:
                if (!(obj instanceof zzft)) {
                    return zzen.zzz((zzgj) obj);
                }
                int i7 = zzen.zzb;
                iZzd = ((zzft) obj).zza();
                iZzC = zzen.zzC(iZzd);
                break;
                break;
            case 11:
                if (!(obj instanceof zzeg)) {
                    int i8 = zzen.zzb;
                    iZzd = ((byte[]) obj).length;
                    iZzC = zzen.zzC(iZzd);
                } else {
                    int i9 = zzen.zzb;
                    iZzd = ((zzeg) obj).zzd();
                    iZzC = zzen.zzC(iZzd);
                }
                break;
            case 12:
                return zzen.zzC(((Integer) obj).intValue());
            case 13:
                return obj instanceof zzfi ? zzen.zzD(((zzfi) obj).zza()) : zzen.zzD(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                int i10 = zzen.zzb;
                return 4;
            case 15:
                ((Long) obj).getClass();
                int i11 = zzen.zzb;
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return zzen.zzC((iIntValue >> 31) ^ (iIntValue + iIntValue));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return zzen.zzD((jLongValue >> 63) ^ (jLongValue + jLongValue));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return iZzC + iZzd;
    }

    public static int zzc(zzew zzewVar, Object obj) {
        zzewVar.zzb();
        throw null;
    }

    public static zzex zze() {
        return zzb;
    }

    public static boolean zzj(Map.Entry entry) {
        ((zzew) entry.getKey()).zzc();
        throw null;
    }

    public static boolean zzk(Object obj) {
        if (obj instanceof zzgk) {
            return ((zzgk) obj).zzk();
        }
        if (obj instanceof zzft) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    public static final int zzl(Map.Entry entry) {
        zzew zzewVar = (zzew) entry.getKey();
        entry.getValue();
        zzewVar.zzc();
        throw null;
    }

    public static final void zzm(zzew zzewVar, Object obj) {
        zzewVar.zzb();
        throw null;
    }

    public final Object clone() throws CloneNotSupportedException {
        zzex zzexVar = new zzex();
        zzhb zzhbVar = this.zza;
        if (zzhbVar.zzb > 0) {
            ((zzew) ((zzgx) zzhbVar.zzg(0)).zzb).zze();
            throw null;
        }
        Iterator it = zzhbVar.zzd().iterator();
        if (!it.hasNext()) {
            zzexVar.zzd = this.zzd;
            return zzexVar;
        }
        Map.Entry entry = (Map.Entry) it.next();
        zzew zzewVar = (zzew) entry.getKey();
        entry.getValue();
        zzewVar.zze();
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzex) {
            return this.zza.equals(((zzex) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzd() {
        zzhb zzhbVar = this.zza;
        if (zzhbVar.zzb > 0) {
            zzl(zzhbVar.zzg(0));
            throw null;
        }
        Iterator it = zzhbVar.zzd().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        zzl((Map.Entry) it.next());
        throw null;
    }

    public final Iterator zzf() {
        zzhb zzhbVar = this.zza;
        return zzhbVar.isEmpty() ? Collections.emptyIterator() : this.zzd ? new zzfr(((zzgz) zzhbVar.entrySet()).iterator()) : ((zzgz) zzhbVar.entrySet()).iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        zzhb zzhbVar = this.zza;
        int i = zzhbVar.zzb;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = ((zzgx) zzhbVar.zzg(i2)).zzc;
            if (obj instanceof zzfg) {
                ((zzfg) obj).zzu();
            }
        }
        Iterator it = zzhbVar.zzd().iterator();
        while (it.hasNext()) {
            Object value = ((Map.Entry) it.next()).getValue();
            if (value instanceof zzfg) {
                ((zzfg) value).zzu();
            }
        }
        zzhbVar.zza();
        this.zzc = true;
    }

    public final void zzh(zzew zzewVar, Object obj) {
        zzewVar.zze();
        throw null;
    }

    public final boolean zzi() {
        zzhb zzhbVar = this.zza;
        if (zzhbVar.zzb > 0) {
            zzj(zzhbVar.zzg(0));
            throw null;
        }
        Iterator it = zzhbVar.zzd().iterator();
        if (!it.hasNext()) {
            return true;
        }
        zzj((Map.Entry) it.next());
        throw null;
    }

    public zzex(boolean z) {
        zzg();
        zzg();
    }
}
