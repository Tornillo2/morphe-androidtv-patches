package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjr {
    public static final zzjp zza = new zzjq();
    public static final zzjp zzb;

    static {
        zzjp zzjpVar = null;
        try {
            zzjpVar = (zzjp) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zzb = zzjpVar;
    }

    public static zzjp zza() {
        zzjp zzjpVar = zzb;
        if (zzjpVar != null) {
            return zzjpVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzjp zzb() {
        return zza;
    }
}
