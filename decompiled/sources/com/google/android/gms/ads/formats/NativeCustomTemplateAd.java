package com.google.android.gms.ads.formats;

import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface NativeCustomTemplateAd {

    @NonNull
    public static final String ASSET_NAME_VIDEO = "_videoMediaView";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DisplayOpenMeasurement {
        void setView(@NonNull View view);

        boolean start();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnCustomClickListener {
        void onCustomClick(@NonNull NativeCustomTemplateAd nativeCustomTemplateAd, @NonNull String str);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnCustomTemplateAdLoadedListener {
        void onCustomTemplateAdLoaded(@NonNull NativeCustomTemplateAd nativeCustomTemplateAd);
    }

    void destroy();

    @NonNull
    List<String> getAvailableAssetNames();

    @NonNull
    String getCustomTemplateId();

    @NonNull
    DisplayOpenMeasurement getDisplayOpenMeasurement();

    @NonNull
    NativeAd.Image getImage(@NonNull String str);

    @NonNull
    CharSequence getText(@NonNull String str);

    @NonNull
    VideoController getVideoController();

    @NonNull
    MediaView getVideoMediaView();

    void performClick(@NonNull String str);

    void recordImpression();
}
