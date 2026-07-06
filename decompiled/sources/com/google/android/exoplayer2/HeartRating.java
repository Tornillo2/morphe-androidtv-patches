package com.google.android.exoplayer2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class HeartRating extends Rating {
    public static final int TYPE = 0;
    public final boolean isHeart;
    public final boolean rated;
    public static final String FIELD_RATED = Util.intToStringMaxRadix(1);
    public static final String FIELD_IS_HEART = Integer.toString(2, 36);
    public static final Bundleable.Creator<HeartRating> CREATOR = new HeartRating$$ExternalSyntheticLambda0();

    public HeartRating() {
        this.rated = false;
        this.isHeart = false;
    }

    public static HeartRating fromBundle(Bundle bundle) {
        Assertions.checkArgument(bundle.getInt(Rating.FIELD_RATING_TYPE, -1) == 0);
        return bundle.getBoolean(FIELD_RATED, false) ? new HeartRating(bundle.getBoolean(FIELD_IS_HEART, false)) : new HeartRating();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof HeartRating)) {
            return false;
        }
        HeartRating heartRating = (HeartRating) obj;
        return this.isHeart == heartRating.isHeart && this.rated == heartRating.rated;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.rated), Boolean.valueOf(this.isHeart)});
    }

    public boolean isHeart() {
        return this.isHeart;
    }

    @Override // com.google.android.exoplayer2.Rating
    public boolean isRated() {
        return this.rated;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(Rating.FIELD_RATING_TYPE, 0);
        bundle.putBoolean(FIELD_RATED, this.rated);
        bundle.putBoolean(FIELD_IS_HEART, this.isHeart);
        return bundle;
    }

    public HeartRating(boolean z) {
        this.rated = true;
        this.isHeart = z;
    }
}
