package com.google.android.gms.ads.mediation.customevent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class CustomEventExtras {
    public final HashMap zza = new HashMap();

    @Nullable
    public Object getExtra(@NonNull String str) {
        return this.zza.get(str);
    }

    public void setExtra(@NonNull String str, @NonNull Object obj) {
        this.zza.put(str, obj);
    }
}
