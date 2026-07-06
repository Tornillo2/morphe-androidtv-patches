package com.google.android.gms.internal.consent_sdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzci<T> implements zzch<T> {
    public static final zzci<Object> zza = new zzci<>(null);
    public final T zzb;

    public zzci(T t) {
        this.zzb = t;
    }

    public static <T> zzch<T> zza(T t) {
        if (t != null) {
            return new zzci(t);
        }
        throw new NullPointerException("instance cannot be null");
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final T zzb() {
        return this.zzb;
    }
}
