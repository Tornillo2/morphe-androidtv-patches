package com.google.android.exoplayer2;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Rating implements Bundleable {
    public static final int RATING_TYPE_HEART = 0;
    public static final int RATING_TYPE_PERCENTAGE = 1;
    public static final int RATING_TYPE_STAR = 2;
    public static final int RATING_TYPE_THUMB = 3;
    public static final int RATING_TYPE_UNSET = -1;
    public static final float RATING_UNSET = -1.0f;
    public static final String FIELD_RATING_TYPE = Util.intToStringMaxRadix(0);
    public static final Bundleable.Creator<Rating> CREATOR = new Rating$$ExternalSyntheticLambda0();

    public static Rating fromBundle(Bundle bundle) {
        int i = bundle.getInt(FIELD_RATING_TYPE, -1);
        if (i == 0) {
            return (Rating) HeartRating.CREATOR.fromBundle(bundle);
        }
        if (i == 1) {
            return (Rating) PercentageRating.CREATOR.fromBundle(bundle);
        }
        if (i == 2) {
            return (Rating) StarRating.CREATOR.fromBundle(bundle);
        }
        if (i == 3) {
            return (Rating) ThumbRating.CREATOR.fromBundle(bundle);
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unknown RatingType: ", i));
    }

    public abstract boolean isRated();
}
