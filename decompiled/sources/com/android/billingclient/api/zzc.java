package com.android.billingclient.api;

import androidx.annotation.Nullable;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class zzc {
    public final String zza;
    public final String zzb;

    @Nullable
    public final String zzc;

    public /* synthetic */ zzc(JSONObject jSONObject, zzd zzdVar) {
        this.zza = jSONObject.optString("productId");
        this.zzb = jSONObject.optString("productType");
        String strOptString = jSONObject.optString("offerToken");
        this.zzc = true == strOptString.isEmpty() ? null : strOptString;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzcVar = (zzc) obj;
        return this.zza.equals(zzcVar.zza) && this.zzb.equals(zzcVar.zzb) && Objects.equals(this.zzc, zzcVar.zzc);
    }

    public final int hashCode() {
        return Objects.hash(this.zza, this.zzb, this.zzc);
    }

    public final String toString() {
        return String.format("{id: %s, type: %s, offer token: %s}", this.zza, this.zzb, this.zzc);
    }
}
