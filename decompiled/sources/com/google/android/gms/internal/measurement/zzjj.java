package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlinx.coroutines.scheduling.CoroutineScheduler;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzjj extends zzir {
    public static final Logger zzb = Logger.getLogger(zzjj.class.getName());
    public static final boolean zzc = zzmv.zzx();
    public zzjk zza;

    public zzjj() {
    }

    public static int zzA(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if ((CoroutineScheduler.PARKED_VERSION_MASK & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzjj zzC(byte[] bArr) {
        return new zzjg(bArr, 0, bArr.length);
    }

    public static int zzt(zzjb zzjbVar) {
        int iZzd = zzjbVar.zzd();
        return zzA(iZzd) + iZzd;
    }

    @Deprecated
    public static int zzu(int i, zzlj zzljVar, zzlu zzluVar) {
        int iZzA = zzA(i << 3);
        int i2 = iZzA + iZzA;
        zzil zzilVar = (zzil) zzljVar;
        int iZzbu = zzilVar.zzbu();
        if (iZzbu == -1) {
            iZzbu = zzluVar.zza(zzilVar);
            zzilVar.zzbx(iZzbu);
        }
        return i2 + iZzbu;
    }

    public static int zzv(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    public static int zzw(zzkp zzkpVar) {
        int iZza = zzkpVar.zza();
        return zzA(iZza) + iZza;
    }

    public static int zzx(zzlj zzljVar, zzlu zzluVar) {
        zzil zzilVar = (zzil) zzljVar;
        int iZzbu = zzilVar.zzbu();
        if (iZzbu == -1) {
            iZzbu = zzluVar.zza(zzilVar);
            zzilVar.zzbx(iZzbu);
        }
        return zzA(iZzbu) + iZzbu;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzna.zzc(str);
        } catch (zzmz unused) {
            length = str.getBytes(zzkk.zzb).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i) {
        return zzA(i << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzE(String str, zzmz zzmzVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzmzVar);
        byte[] bytes = str.getBytes(zzkk.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzjb zzjbVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public zzjj(zzji zzjiVar) {
    }
}
