package com.google.android.gms.ads.formats;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class UnifiedNativeAd {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public interface OnUnifiedNativeAdLoadedListener {
        void onUnifiedNativeAdLoaded(@NonNull UnifiedNativeAd unifiedNativeAd);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public interface UnconfirmedClickListener {
        void onUnconfirmedClickCancelled();

        void onUnconfirmedClickReceived(@NonNull String str);
    }

    public abstract void cancelUnconfirmedClick();

    public abstract void destroy();

    public abstract void enableCustomClickGesture();

    @NonNull
    public abstract NativeAd.AdChoicesInfo getAdChoicesInfo();

    @NonNull
    public abstract String getAdvertiser();

    @NonNull
    public abstract String getBody();

    @NonNull
    public abstract String getCallToAction();

    @NonNull
    public abstract Bundle getExtras();

    @NonNull
    public abstract String getHeadline();

    @NonNull
    public abstract NativeAd.Image getIcon();

    @NonNull
    public abstract List<NativeAd.Image> getImages();

    @NonNull
    public abstract MediaContent getMediaContent();

    @NonNull
    @Deprecated
    public abstract String getMediationAdapterClassName();

    @NonNull
    public abstract List<MuteThisAdReason> getMuteThisAdReasons();

    @NonNull
    public abstract String getPrice();

    @Nullable
    public abstract ResponseInfo getResponseInfo();

    @NonNull
    public abstract Double getStarRating();

    @NonNull
    public abstract String getStore();

    @NonNull
    @Deprecated
    public abstract VideoController getVideoController();

    public abstract boolean isCustomClickGestureEnabled();

    public abstract boolean isCustomMuteThisAdEnabled();

    public abstract void muteThisAd(@NonNull MuteThisAdReason muteThisAdReason);

    @KeepForSdk
    public abstract void performClick(@NonNull Bundle bundle);

    public abstract void recordCustomClickGesture();

    @KeepForSdk
    public abstract boolean recordImpression(@NonNull Bundle bundle);

    @KeepForSdk
    public abstract void reportTouchEvent(@NonNull Bundle bundle);

    public abstract void setMuteThisAdListener(@NonNull MuteThisAdListener muteThisAdListener);

    public abstract void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener);

    public abstract void setUnconfirmedClickListener(@NonNull UnconfirmedClickListener unconfirmedClickListener);

    @NonNull
    public abstract Object zza();
}
