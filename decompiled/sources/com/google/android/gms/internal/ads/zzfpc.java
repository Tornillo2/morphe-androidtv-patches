package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpc implements Serializable, zzfpa {
    public final List zza;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfpc) {
            return this.zza.equals(((zzfpc) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 306654252;
    }

    public final String toString() {
        List list = this.zza;
        StringBuilder sb = new StringBuilder("Predicates.and(");
        boolean z = true;
        for (Object obj : list) {
            if (!z) {
                sb.append(',');
            }
            sb.append(obj);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfpa
    public final boolean zza(Object obj) {
        for (int i = 0; i < this.zza.size(); i++) {
            if (!((zzfpa) this.zza.get(i)).zza(obj)) {
                return false;
            }
        }
        return true;
    }
}
