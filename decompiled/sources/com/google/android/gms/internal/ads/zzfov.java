package com.google.android.gms.internal.ads;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzfov implements Serializable {
    public static zzfov zzc() {
        return zzfod.zza;
    }

    public static zzfov zzd(@CheckForNull Object obj) {
        return obj == null ? zzfod.zza : new zzfpe(obj);
    }

    public abstract zzfov zza(zzfon zzfonVar);

    public abstract Object zzb(Object obj);
}
