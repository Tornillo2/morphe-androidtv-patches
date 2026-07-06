package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzl extends zzj {
    public static final WeakReference zza = new WeakReference(null);
    public WeakReference zzb;

    public zzl(byte[] bArr) {
        super(bArr);
        this.zzb = zza;
    }

    public abstract byte[] zzb();

    @Override // com.google.android.gms.common.zzj
    public final byte[] zzf() {
        byte[] bArrZzb;
        synchronized (this) {
            try {
                bArrZzb = (byte[]) this.zzb.get();
                if (bArrZzb == null) {
                    bArrZzb = zzb();
                    this.zzb = new WeakReference(bArrZzb);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArrZzb;
    }
}
