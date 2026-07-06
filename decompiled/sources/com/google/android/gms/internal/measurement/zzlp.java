package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlp {
    public static final zzlo zza;
    public static final zzlo zzb;

    static {
        zzlo zzloVar = null;
        try {
            zzloVar = (zzlo) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzloVar;
        zzb = new zzlo();
    }

    public static zzlo zza() {
        return zza;
    }

    public static zzlo zzb() {
        return zzb;
    }
}
