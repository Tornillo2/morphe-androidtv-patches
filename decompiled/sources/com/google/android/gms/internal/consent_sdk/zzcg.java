package com.google.android.gms.internal.consent_sdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcg<T> implements zzcl<T> {
    public static final Object zza = new Object();
    public volatile zzcl<T> zzb;
    public volatile Object zzc = zza;

    public zzcg(zzcl<T> zzclVar) {
        this.zzb = zzclVar;
    }

    public static <P extends zzcl<T>, T> zzcl<T> zza(P p) {
        p.getClass();
        return p instanceof zzcg ? p : new zzcg(p);
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final T zzb() {
        T tZzb;
        T t = (T) this.zzc;
        Object obj = zza;
        if (t != obj) {
            return t;
        }
        synchronized (this) {
            try {
                tZzb = (T) this.zzc;
                if (tZzb == obj) {
                    tZzb = this.zzb.zzb();
                    Object obj2 = this.zzc;
                    if (obj2 != obj && !(obj2 instanceof zzcj) && obj2 != tZzb) {
                        String strValueOf = String.valueOf(obj2);
                        String strValueOf2 = String.valueOf(tZzb);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 118 + strValueOf2.length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(strValueOf);
                        sb.append(" & ");
                        sb.append(strValueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzc = tZzb;
                    this.zzb = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tZzb;
    }
}
