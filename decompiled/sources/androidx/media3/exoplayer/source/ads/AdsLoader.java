package androidx.media3.exoplayer.source.ads;

import androidx.annotation.Nullable;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface AdsLoader {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Provider {
        @Nullable
        AdsLoader getAdsLoader(MediaItem.AdsConfiguration adsConfiguration);
    }

    @UnstableApi
    void handlePrepareComplete(AdsMediaSource adsMediaSource, int i, int i2);

    @UnstableApi
    void handlePrepareError(AdsMediaSource adsMediaSource, int i, int i2, IOException iOException);

    void release();

    void setPlayer(@Nullable Player player);

    @UnstableApi
    void setSupportedContentTypes(int... iArr);

    @UnstableApi
    void start(AdsMediaSource adsMediaSource, DataSpec dataSpec, Object obj, AdViewProvider adViewProvider, EventListener eventListener);

    @UnstableApi
    void stop(AdsMediaSource adsMediaSource, EventListener eventListener);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public interface EventListener {
        void onAdClicked();

        void onAdLoadError(AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec);

        void onAdPlaybackState(AdPlaybackState adPlaybackState);

        void onAdTapped();

        /* JADX INFO: renamed from: androidx.media3.exoplayer.source.ads.AdsLoader$EventListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static void $default$onAdClicked(EventListener eventListener) {
            }

            public static void $default$onAdTapped(EventListener eventListener) {
            }

            public static void $default$onAdPlaybackState(EventListener eventListener, AdPlaybackState adPlaybackState) {
            }

            public static void $default$onAdLoadError(EventListener eventListener, AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec) {
            }
        }
    }
}
