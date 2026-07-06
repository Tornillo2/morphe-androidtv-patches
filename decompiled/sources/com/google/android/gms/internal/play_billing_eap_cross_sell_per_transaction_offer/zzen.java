package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzen extends zzdx {
    public static final /* synthetic */ int zzb = 0;
    public static final Logger zzc = Logger.getLogger(zzen.class.getName());
    public static final boolean zzd = zzhm.zzx();
    public zzeo zza;

    public zzen() {
        throw null;
    }

    public static int zzA(zzgj zzgjVar, zzgt zzgtVar) {
        int iZze = ((zzdq) zzgjVar).zze(zzgtVar);
        return zzC(iZze) + iZze;
    }

    public static int zzB(String str) {
        int length;
        try {
            length = zzhp.zzc(str);
        } catch (zzho unused) {
            length = str.getBytes(zzfm.zza).length;
        }
        return zzC(length) + length;
    }

    public static int zzC(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzD(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    public static int zzy(int i, zzgj zzgjVar, zzgt zzgtVar) {
        int iZzC = zzC(i << 3);
        return ((zzdq) zzgjVar).zze(zzgtVar) + iZzC + iZzC;
    }

    public static int zzz(zzgj zzgjVar) {
        int iZzj = zzgjVar.zzj();
        return zzC(iZzj) + iZzj;
    }

    public final void zzE() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzF(String str, zzho zzhoVar) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzhoVar);
        byte[] bytes = str.getBytes(zzfm.zza);
        try {
            int length = bytes.length;
            zzv(length);
            zzm(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzel(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzeg zzegVar) throws IOException;

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract void zzh(int i) throws IOException;

    public abstract void zzi(int i, long j) throws IOException;

    public abstract void zzj(long j) throws IOException;

    public abstract void zzk(int i, int i2) throws IOException;

    public abstract void zzl(int i) throws IOException;

    public abstract void zzm(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzn(int i, zzgj zzgjVar, zzgt zzgtVar) throws IOException;

    public abstract void zzp(int i, zzgj zzgjVar) throws IOException;

    public abstract void zzq(int i, zzeg zzegVar) throws IOException;

    public abstract void zzr(int i, String str) throws IOException;

    public abstract void zzt(int i, int i2) throws IOException;

    public abstract void zzu(int i, int i2) throws IOException;

    public abstract void zzv(int i) throws IOException;

    public abstract void zzw(int i, long j) throws IOException;

    public abstract void zzx(long j) throws IOException;

    public zzen(zzem zzemVar) {
    }
}
