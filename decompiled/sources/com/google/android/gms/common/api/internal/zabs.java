package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zabs {
    public final ApiKey zaa;
    public final Feature zab;

    public /* synthetic */ zabs(ApiKey apiKey, Feature feature, zabr zabrVar) {
        this.zaa = apiKey;
        this.zab = feature;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof zabs)) {
            zabs zabsVar = (zabs) obj;
            if (Objects.equal(this.zaa, zabsVar.zaa) && Objects.equal(this.zab, zabsVar.zab)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zaa, this.zab});
    }

    public final String toString() {
        Objects.ToStringHelper toStringHelper = new Objects.ToStringHelper(this, null);
        toStringHelper.add("key", this.zaa);
        toStringHelper.add("feature", this.zab);
        return toStringHelper.toString();
    }
}
