package com.google.android.gms.internal.ads;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpe extends zzfov {
    public final Object zza;

    public zzfpe(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfpe) {
            return this.zza.equals(((zzfpe) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Optional.of(", this.zza.toString(), ")");
    }

    @Override // com.google.android.gms.internal.ads.zzfov
    public final zzfov zza(zzfon zzfonVar) {
        Object objApply = zzfonVar.apply(this.zza);
        zzfoz.zzc(objApply, "the Function passed to Optional.transform() must not return null.");
        return new zzfpe(objApply);
    }

    @Override // com.google.android.gms.internal.ads.zzfov
    public final Object zzb(Object obj) {
        return this.zza;
    }
}
