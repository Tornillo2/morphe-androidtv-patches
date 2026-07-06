package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdView extends BaseAdView {
    public AdView(@NonNull Context context) {
        super(context, 0);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    @NonNull
    public final VideoController zza() {
        return this.zza.zze;
    }

    public AdView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public AdView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
    }
}
