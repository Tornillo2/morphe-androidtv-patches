package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzad extends zzaa {
    public zzad() {
        super(4);
    }

    @CanIgnoreReturnValue
    public final zzad zzb(Object obj) {
        zza(obj);
        return this;
    }

    @CanIgnoreReturnValue
    public final zzad zzc(Iterator it) {
        while (it.hasNext()) {
            zza(it.next());
        }
        return this;
    }

    public zzad(int i) {
        super(4);
    }
}
