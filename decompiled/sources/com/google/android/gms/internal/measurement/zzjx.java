package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjx implements zzlh {
    public static final zzjx zza = new zzjx();

    public static zzjx zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final zzlg zzb(Class cls) {
        if (!zzkc.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
        try {
            return (zzlg) zzkc.zzbC(cls.asSubclass(zzkc.class)).zzl(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final boolean zzc(Class cls) {
        return zzkc.class.isAssignableFrom(cls);
    }
}
