package com.google.android.exoplayer2;

import android.os.Bundle;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PercentageRating extends Rating {
    public static final int TYPE = 1;
    public final float percent;
    public static final String FIELD_PERCENT = Util.intToStringMaxRadix(1);
    public static final Bundleable.Creator<PercentageRating> CREATOR = new PercentageRating$$ExternalSyntheticLambda0();

    public PercentageRating() {
        this.percent = -1.0f;
    }

    public static PercentageRating fromBundle(Bundle bundle) {
        Assertions.checkArgument(bundle.getInt(Rating.FIELD_RATING_TYPE, -1) == 1);
        float f = bundle.getFloat(FIELD_PERCENT, -1.0f);
        return f == -1.0f ? new PercentageRating() : new PercentageRating(f);
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof PercentageRating) && this.percent == ((PercentageRating) obj).percent;
    }

    public float getPercent() {
        return this.percent;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }

    @Override // com.google.android.exoplayer2.Rating
    public boolean isRated() {
        return this.percent != -1.0f;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(Rating.FIELD_RATING_TYPE, 1);
        bundle.putFloat(FIELD_PERCENT, this.percent);
        return bundle;
    }

    public PercentageRating(@FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f) {
        Assertions.checkArgument(f >= 0.0f && f <= 100.0f, "percent must be in the range of [0, 100]");
        this.percent = f;
    }
}
