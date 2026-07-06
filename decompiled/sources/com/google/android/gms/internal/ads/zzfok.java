package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzfok implements zzfpa {
    public static zzfok zzc(char c) {
        return new zzfoh(c);
    }

    @Override // com.google.android.gms.internal.ads.zzfpa
    @Deprecated
    public final /* synthetic */ boolean zza(Object obj) {
        return zzb(((Character) obj).charValue());
    }

    public abstract boolean zzb(char c);
}
