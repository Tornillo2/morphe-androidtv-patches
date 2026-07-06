package com.google.ads;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    public final com.google.android.gms.ads.AdSize zza;

    @NonNull
    public static final AdSize SMART_BANNER = new AdSize(-1, -2);

    @NonNull
    public static final AdSize BANNER = new AdSize(MediaSessionCompat.MAX_BITMAP_SIZE_IN_DP, 50);

    @NonNull
    public static final AdSize IAB_MRECT = new AdSize(DrawableCrossFadeFactory.Builder.DEFAULT_DURATION_MS, 250);

    @NonNull
    public static final AdSize IAB_BANNER = new AdSize(468, 60);

    @NonNull
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90);

    @NonNull
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600);

    public AdSize(@NonNull com.google.android.gms.ads.AdSize adSize) {
        this.zza = adSize;
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof AdSize) {
            return this.zza.equals(((AdSize) obj).zza);
        }
        return false;
    }

    @Nullable
    public AdSize findBestSize(@NonNull AdSize... adSizeArr) {
        AdSize adSize = null;
        if (adSizeArr == null) {
            return null;
        }
        com.google.android.gms.ads.AdSize adSize2 = this.zza;
        int i = adSize2.zzb;
        int i2 = adSize2.zzc;
        float f = 0.0f;
        for (AdSize adSize3 : adSizeArr) {
            com.google.android.gms.ads.AdSize adSize4 = adSize3.zza;
            if (isSizeAppropriate(adSize4.zzb, adSize4.zzc)) {
                float f2 = (r7 * r6) / (i * i2);
                if (f2 > 1.0f) {
                    f2 = 1.0f / f2;
                }
                if (f2 > f) {
                    adSize = adSize3;
                    f = f2;
                }
            }
        }
        return adSize;
    }

    public int getHeight() {
        return this.zza.zzc;
    }

    public int getHeightInPixels(@NonNull Context context) {
        return this.zza.getHeightInPixels(context);
    }

    public int getWidth() {
        return this.zza.zzb;
    }

    public int getWidthInPixels(@NonNull Context context) {
        return this.zza.getWidthInPixels(context);
    }

    public int hashCode() {
        return this.zza.zzd.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zza.isAutoHeight();
    }

    public boolean isCustomAdSize() {
        return false;
    }

    public boolean isFullWidth() {
        return this.zza.isFullWidth();
    }

    public boolean isSizeAppropriate(int i, int i2) {
        com.google.android.gms.ads.AdSize adSize = this.zza;
        float f = adSize.zzb;
        float f2 = i;
        float f3 = f * 1.25f;
        int i3 = adSize.zzc;
        if (f2 > f3 || f2 < f * 0.8f) {
            return false;
        }
        float f4 = i2;
        float f5 = i3;
        return f4 <= 1.25f * f5 && f4 >= f5 * 0.8f;
    }

    @NonNull
    public String toString() {
        return this.zza.zzd;
    }

    public AdSize(int i, int i2) {
        this(new com.google.android.gms.ads.AdSize(i, i2));
    }
}
