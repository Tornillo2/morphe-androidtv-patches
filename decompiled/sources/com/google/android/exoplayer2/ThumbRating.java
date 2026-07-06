package com.google.android.exoplayer2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ThumbRating extends Rating {
    public static final int TYPE = 3;
    public final boolean isThumbsUp;
    public final boolean rated;
    public static final String FIELD_RATED = Util.intToStringMaxRadix(1);
    public static final String FIELD_IS_THUMBS_UP = Integer.toString(2, 36);
    public static final Bundleable.Creator<ThumbRating> CREATOR = new ThumbRating$$ExternalSyntheticLambda0();

    public ThumbRating() {
        this.rated = false;
        this.isThumbsUp = false;
    }

    public static ThumbRating fromBundle(Bundle bundle) {
        Assertions.checkArgument(bundle.getInt(Rating.FIELD_RATING_TYPE, -1) == 3);
        return bundle.getBoolean(FIELD_RATED, false) ? new ThumbRating(bundle.getBoolean(FIELD_IS_THUMBS_UP, false)) : new ThumbRating();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ThumbRating)) {
            return false;
        }
        ThumbRating thumbRating = (ThumbRating) obj;
        return this.isThumbsUp == thumbRating.isThumbsUp && this.rated == thumbRating.rated;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.rated), Boolean.valueOf(this.isThumbsUp)});
    }

    @Override // com.google.android.exoplayer2.Rating
    public boolean isRated() {
        return this.rated;
    }

    public boolean isThumbsUp() {
        return this.isThumbsUp;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(Rating.FIELD_RATING_TYPE, 3);
        bundle.putBoolean(FIELD_RATED, this.rated);
        bundle.putBoolean(FIELD_IS_THUMBS_UP, this.isThumbsUp);
        return bundle;
    }

    public ThumbRating(boolean z) {
        this.rated = true;
        this.isThumbsUp = z;
    }
}
