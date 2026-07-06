package androidx.media3.exoplayer.trackselection;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class TrackSelector {

    @Nullable
    public BandwidthMeter bandwidthMeter;

    @Nullable
    public InvalidationListener listener;

    public final BandwidthMeter getBandwidthMeter() {
        BandwidthMeter bandwidthMeter = this.bandwidthMeter;
        Assertions.checkStateNotNull(bandwidthMeter);
        return bandwidthMeter;
    }

    public TrackSelectionParameters getParameters() {
        return TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
    }

    @Nullable
    public RendererCapabilities.Listener getRendererCapabilitiesListener() {
        return null;
    }

    @CallSuper
    public void init(InvalidationListener invalidationListener, BandwidthMeter bandwidthMeter) {
        this.listener = invalidationListener;
        this.bandwidthMeter = bandwidthMeter;
    }

    public final void invalidate() {
        InvalidationListener invalidationListener = this.listener;
        if (invalidationListener != null) {
            invalidationListener.onTrackSelectionsInvalidated();
        }
    }

    public final void invalidateForRendererCapabilitiesChange(Renderer renderer) {
        InvalidationListener invalidationListener = this.listener;
        if (invalidationListener != null) {
            invalidationListener.onRendererCapabilitiesChanged(renderer);
        }
    }

    public boolean isSetParametersSupported() {
        return this instanceof DefaultTrackSelector;
    }

    public abstract void onSelectionActivated(@Nullable Object obj);

    @CallSuper
    public void release() {
        this.listener = null;
        this.bandwidthMeter = null;
    }

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException;

    public void setAudioAttributes(AudioAttributes audioAttributes) {
    }

    public void setParameters(TrackSelectionParameters trackSelectionParameters) {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface InvalidationListener {
        void onRendererCapabilitiesChanged(Renderer renderer);

        void onTrackSelectionsInvalidated();

        /* JADX INFO: renamed from: androidx.media3.exoplayer.trackselection.TrackSelector$InvalidationListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static void $default$onRendererCapabilitiesChanged(InvalidationListener invalidationListener, Renderer renderer) {
            }
        }
    }
}
