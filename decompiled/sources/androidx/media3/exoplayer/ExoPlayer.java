package androidx.media3.exoplayer;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionArray;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import androidx.media3.extractor.DefaultExtractorsFactory;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ExoPlayer extends Player {

    @UnstableApi
    public static final long DEFAULT_DETACH_SURFACE_TIMEOUT_MS = 2000;

    @UnstableApi
    public static final long DEFAULT_RELEASE_TIMEOUT_MS = 500;

    /* JADX INFO: renamed from: androidx.media3.exoplayer.ExoPlayer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    @Deprecated
    public interface AudioComponent {
        @Deprecated
        void clearAuxEffectInfo();

        @Deprecated
        AudioAttributes getAudioAttributes();

        @Deprecated
        int getAudioSessionId();

        @Deprecated
        boolean getSkipSilenceEnabled();

        @Deprecated
        float getVolume();

        @Deprecated
        void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

        @Deprecated
        void setAudioSessionId(int i);

        @Deprecated
        void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

        @Deprecated
        void setSkipSilenceEnabled(boolean z);

        @Deprecated
        void setVolume(float f);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public Function<Clock, AnalyticsCollector> analyticsCollectorFunction;
        public AudioAttributes audioAttributes;
        public Supplier<BandwidthMeter> bandwidthMeterSupplier;
        public boolean buildCalled;
        public Clock clock;
        public final Context context;
        public long detachSurfaceTimeoutMs;
        public boolean deviceVolumeControlEnabled;
        public long foregroundModeTimeoutMs;
        public boolean handleAudioBecomingNoisy;
        public boolean handleAudioFocus;
        public LivePlaybackSpeedControl livePlaybackSpeedControl;
        public Supplier<LoadControl> loadControlSupplier;
        public Looper looper;
        public Supplier<MediaSource.Factory> mediaSourceFactorySupplier;
        public boolean pauseAtEndOfMediaItems;

        @Nullable
        public Looper playbackLooper;

        @Nullable
        public PriorityTaskManager priorityTaskManager;
        public long releaseTimeoutMs;
        public Supplier<RenderersFactory> renderersFactorySupplier;
        public long seekBackIncrementMs;
        public long seekForwardIncrementMs;
        public SeekParameters seekParameters;
        public boolean skipSilenceEnabled;
        public boolean suppressPlaybackOnUnsuitableOutput;
        public Supplier<TrackSelector> trackSelectorSupplier;
        public boolean useLazyPreparation;
        public boolean usePlatformDiagnostics;
        public int videoChangeFrameRateStrategy;
        public int videoScalingMode;
        public int wakeMode;

        /* JADX INFO: renamed from: $r8$lambda$6RcAC-_BqYMBagvVbrZlEBfEca8, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m91$r8$lambda$6RcAC_BqYMBagvVbrZlEBfEca8(Context context) {
            return new DefaultRenderersFactory(context);
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$CuRhI_aLZcnOrCqB72Htj9Divm8(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$EUAzPSpf_tY17bpaOOdpyKtURb0(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$R1GKsm0ndd_u8FB2mbmtZL6XWCo(Context context) {
            return new DefaultRenderersFactory(context);
        }

        public static /* synthetic */ TrackSelector $r8$lambda$xUvto8h1vMLVIth0_snkoRcg_Fw(Context context) {
            return new DefaultTrackSelector(context);
        }

        public Builder(final Context context) {
            this(context, (Supplier<RenderersFactory>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda10
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultRenderersFactory(context);
                }
            }, (Supplier<MediaSource.Factory>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda11
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return ExoPlayer.Builder.$r8$lambda$EUAzPSpf_tY17bpaOOdpyKtURb0(context);
                }
            });
        }

        public ExoPlayer build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new ExoPlayerImpl(this, null);
        }

        public SimpleExoPlayer buildSimpleExoPlayer() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new SimpleExoPlayer(this);
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder experimentalSetForegroundModeTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.foregroundModeTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setAnalyticsCollector(AnalyticsCollector analyticsCollector) {
            Assertions.checkState(!this.buildCalled);
            analyticsCollector.getClass();
            this.analyticsCollectorFunction = new ExoPlayer$Builder$$ExternalSyntheticLambda9(analyticsCollector);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
            Assertions.checkState(!this.buildCalled);
            audioAttributes.getClass();
            this.audioAttributes = audioAttributes;
            this.handleAudioFocus = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            Assertions.checkState(!this.buildCalled);
            bandwidthMeter.getClass();
            this.bandwidthMeterSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda1(bandwidthMeter);
            return this;
        }

        @CanIgnoreReturnValue
        @VisibleForTesting
        @UnstableApi
        public Builder setClock(Clock clock) {
            Assertions.checkState(!this.buildCalled);
            this.clock = clock;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setDetachSurfaceTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.detachSurfaceTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setDeviceVolumeControlEnabled(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.deviceVolumeControlEnabled = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHandleAudioBecomingNoisy(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.handleAudioBecomingNoisy = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setLivePlaybackSpeedControl(LivePlaybackSpeedControl livePlaybackSpeedControl) {
            Assertions.checkState(!this.buildCalled);
            livePlaybackSpeedControl.getClass();
            this.livePlaybackSpeedControl = livePlaybackSpeedControl;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setLoadControl(LoadControl loadControl) {
            Assertions.checkState(!this.buildCalled);
            loadControl.getClass();
            this.loadControlSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda0(loadControl);
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setLooper(Looper looper) {
            Assertions.checkState(!this.buildCalled);
            looper.getClass();
            this.looper = looper;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            Assertions.checkState(!this.buildCalled);
            factory.getClass();
            this.mediaSourceFactorySupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda7(factory);
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setPauseAtEndOfMediaItems(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.pauseAtEndOfMediaItems = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setPlaybackLooper(Looper looper) {
            Assertions.checkState(!this.buildCalled);
            this.playbackLooper = looper;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
            Assertions.checkState(!this.buildCalled);
            this.priorityTaskManager = priorityTaskManager;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setReleaseTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.releaseTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            Assertions.checkState(!this.buildCalled);
            renderersFactory.getClass();
            this.renderersFactorySupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda12(renderersFactory);
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setSeekBackIncrementMs(@IntRange(from = 1) long j) {
            Assertions.checkArgument(j > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekBackIncrementMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setSeekForwardIncrementMs(@IntRange(from = 1) long j) {
            Assertions.checkArgument(j > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekForwardIncrementMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setSeekParameters(SeekParameters seekParameters) {
            Assertions.checkState(!this.buildCalled);
            seekParameters.getClass();
            this.seekParameters = seekParameters;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setSkipSilenceEnabled(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.skipSilenceEnabled = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setSuppressPlaybackOnUnsuitableOutput(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.suppressPlaybackOnUnsuitableOutput = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setTrackSelector(TrackSelector trackSelector) {
            Assertions.checkState(!this.buildCalled);
            trackSelector.getClass();
            this.trackSelectorSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda8(trackSelector);
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setUseLazyPreparation(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.useLazyPreparation = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setUsePlatformDiagnostics(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.usePlatformDiagnostics = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setVideoChangeFrameRateStrategy(int i) {
            Assertions.checkState(!this.buildCalled);
            this.videoChangeFrameRateStrategy = i;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Builder setVideoScalingMode(int i) {
            Assertions.checkState(!this.buildCalled);
            this.videoScalingMode = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setWakeMode(int i) {
            Assertions.checkState(!this.buildCalled);
            this.wakeMode = i;
            return this;
        }

        @UnstableApi
        public Builder(final Context context, RenderersFactory renderersFactory) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda12(renderersFactory), (Supplier<MediaSource.Factory>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda13
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return ExoPlayer.Builder.$r8$lambda$CuRhI_aLZcnOrCqB72Htj9Divm8(context);
                }
            });
            renderersFactory.getClass();
        }

        @UnstableApi
        public Builder(final Context context, MediaSource.Factory factory) {
            this(context, (Supplier<RenderersFactory>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda6
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultRenderersFactory(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda7(factory));
            factory.getClass();
        }

        @UnstableApi
        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda12(renderersFactory), new ExoPlayer$Builder$$ExternalSyntheticLambda7(factory));
            renderersFactory.getClass();
            factory.getClass();
        }

        @UnstableApi
        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda12(renderersFactory), new ExoPlayer$Builder$$ExternalSyntheticLambda7(factory), new ExoPlayer$Builder$$ExternalSyntheticLambda8(trackSelector), new ExoPlayer$Builder$$ExternalSyntheticLambda0(loadControl), new ExoPlayer$Builder$$ExternalSyntheticLambda1(bandwidthMeter), new ExoPlayer$Builder$$ExternalSyntheticLambda9(analyticsCollector));
            renderersFactory.getClass();
            factory.getClass();
            trackSelector.getClass();
            bandwidthMeter.getClass();
            analyticsCollector.getClass();
        }

        public Builder(final Context context, Supplier<RenderersFactory> supplier, Supplier<MediaSource.Factory> supplier2) {
            this(context, supplier, supplier2, (Supplier<TrackSelector>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultTrackSelector(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda3(), (Supplier<BandwidthMeter>) new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda4
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return DefaultBandwidthMeter.getSingletonInstance(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda5());
        }

        public Builder(Context context, Supplier<RenderersFactory> supplier, Supplier<MediaSource.Factory> supplier2, Supplier<TrackSelector> supplier3, Supplier<LoadControl> supplier4, Supplier<BandwidthMeter> supplier5, Function<Clock, AnalyticsCollector> function) {
            context.getClass();
            this.context = context;
            this.renderersFactorySupplier = supplier;
            this.mediaSourceFactorySupplier = supplier2;
            this.trackSelectorSupplier = supplier3;
            this.loadControlSupplier = supplier4;
            this.bandwidthMeterSupplier = supplier5;
            this.analyticsCollectorFunction = function;
            this.looper = Util.getCurrentOrMainLooper();
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.wakeMode = 0;
            this.videoScalingMode = 1;
            this.videoChangeFrameRateStrategy = 0;
            this.useLazyPreparation = true;
            this.seekParameters = SeekParameters.DEFAULT;
            this.seekBackIncrementMs = 5000L;
            this.seekForwardIncrementMs = 15000L;
            this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().build();
            this.clock = Clock.DEFAULT;
            this.releaseTimeoutMs = 500L;
            this.detachSurfaceTimeoutMs = 2000L;
            this.usePlatformDiagnostics = true;
        }

        /* JADX INFO: renamed from: $r8$lambda$1S4JTYE6v7ASEpYX-zQPTquCFzM, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m90$r8$lambda$1S4JTYE6v7ASEpYXzQPTquCFzM(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$7MOeVljFYbNJSx4YWkqBWoIPhhE(MediaSource.Factory factory) {
            return factory;
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$Eq15Ln8PNQcPn18jU3xmiFMBsBQ(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ LoadControl $r8$lambda$HekyiBTTjarrX819XM5j8sFRos8(LoadControl loadControl) {
            return loadControl;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$HqY2oZARF3vhlKA0gKaeZ2l_Oa8(MediaSource.Factory factory) {
            return factory;
        }

        public static /* synthetic */ BandwidthMeter $r8$lambda$JbmMWFJp8RW05MCZr4_dSrD2mXk(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$NIZlxLhJka1v5DVOKEY1pN3MTDI(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: renamed from: $r8$lambda$YdD1-a4RqBfR_B5CWrkD4jlJiGk, reason: not valid java name */
        public static /* synthetic */ TrackSelector m92$r8$lambda$YdD1a4RqBfR_B5CWrkD4jlJiGk(TrackSelector trackSelector) {
            return trackSelector;
        }

        /* JADX INFO: renamed from: $r8$lambda$YwDzeLNprsVtl1MHkEBsxBa-1UM, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m93$r8$lambda$YwDzeLNprsVtl1MHkEBsxBa1UM(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$bS92BgJcaDzz1iqX_WNJL58bdxw(MediaSource.Factory factory) {
            return factory;
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$ffDWj_WogckEjCB1hwQ1mwWZqlU(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ TrackSelector $r8$lambda$m6r26ESPcd9sydnSrNPy_vd0BrA(TrackSelector trackSelector) {
            return trackSelector;
        }

        public static /* synthetic */ BandwidthMeter $r8$lambda$ooSX0sBAeDNqrD3eng7u3iRInCk(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        public static /* synthetic */ LoadControl $r8$lambda$yap_ggLI8CRfFBN_Dw8pJDNsx_0(LoadControl loadControl) {
            return loadControl;
        }

        public static /* synthetic */ AnalyticsCollector $r8$lambda$aqaZ6PUTjvfPMrALVap9l9VmKK4(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }

        /* JADX INFO: renamed from: $r8$lambda$jBow5eFbucOb-YB-cKkwoOpjjYE, reason: not valid java name */
        public static /* synthetic */ AnalyticsCollector m94$r8$lambda$jBow5eFbucObYBcKkwoOpjjYE(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    @Deprecated
    public interface DeviceComponent {
        @Deprecated
        void decreaseDeviceVolume();

        @Deprecated
        DeviceInfo getDeviceInfo();

        @Deprecated
        int getDeviceVolume();

        @Deprecated
        void increaseDeviceVolume();

        @Deprecated
        boolean isDeviceMuted();

        @Deprecated
        void setDeviceMuted(boolean z);

        @Deprecated
        void setDeviceVolume(int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    @Deprecated
    public interface TextComponent {
        @Deprecated
        CueGroup getCurrentCues();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    @Deprecated
    public interface VideoComponent {
        @Deprecated
        void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

        @Deprecated
        void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

        @Deprecated
        void clearVideoSurface();

        @Deprecated
        void clearVideoSurface(@Nullable Surface surface);

        @Deprecated
        void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder);

        @Deprecated
        void clearVideoSurfaceView(@Nullable SurfaceView surfaceView);

        @Deprecated
        void clearVideoTextureView(@Nullable TextureView textureView);

        @Deprecated
        int getVideoChangeFrameRateStrategy();

        @Deprecated
        int getVideoScalingMode();

        @Deprecated
        VideoSize getVideoSize();

        @Deprecated
        void setCameraMotionListener(CameraMotionListener cameraMotionListener);

        @Deprecated
        void setVideoChangeFrameRateStrategy(int i);

        @Deprecated
        void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

        @Deprecated
        void setVideoScalingMode(int i);

        @Deprecated
        void setVideoSurface(@Nullable Surface surface);

        @Deprecated
        void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder);

        @Deprecated
        void setVideoSurfaceView(@Nullable SurfaceView surfaceView);

        @Deprecated
        void setVideoTextureView(@Nullable TextureView textureView);
    }

    void addAnalyticsListener(AnalyticsListener analyticsListener);

    @UnstableApi
    void addAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    @UnstableApi
    void addMediaSource(int i, MediaSource mediaSource);

    @UnstableApi
    void addMediaSource(MediaSource mediaSource);

    @UnstableApi
    void addMediaSources(int i, List<MediaSource> list);

    @UnstableApi
    void addMediaSources(List<MediaSource> list);

    @UnstableApi
    void clearAuxEffectInfo();

    @UnstableApi
    void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

    @UnstableApi
    void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    @UnstableApi
    PlayerMessage createMessage(PlayerMessage.Target target);

    @UnstableApi
    AnalyticsCollector getAnalyticsCollector();

    @Nullable
    @UnstableApi
    @Deprecated
    AudioComponent getAudioComponent();

    @Nullable
    @UnstableApi
    DecoderCounters getAudioDecoderCounters();

    @Nullable
    @UnstableApi
    Format getAudioFormat();

    @UnstableApi
    int getAudioSessionId();

    @UnstableApi
    Clock getClock();

    @UnstableApi
    @Deprecated
    TrackGroupArray getCurrentTrackGroups();

    @UnstableApi
    @Deprecated
    TrackSelectionArray getCurrentTrackSelections();

    @Nullable
    @UnstableApi
    @Deprecated
    DeviceComponent getDeviceComponent();

    @UnstableApi
    boolean getPauseAtEndOfMediaItems();

    @UnstableApi
    Looper getPlaybackLooper();

    @Override // androidx.media3.common.Player
    @Nullable
    /* bridge */ /* synthetic */ PlaybackException getPlayerError();

    @Override // androidx.media3.common.Player
    @Nullable
    ExoPlaybackException getPlayerError();

    @UnstableApi
    Renderer getRenderer(int i);

    @UnstableApi
    int getRendererCount();

    @UnstableApi
    int getRendererType(int i);

    @UnstableApi
    SeekParameters getSeekParameters();

    @UnstableApi
    boolean getSkipSilenceEnabled();

    @Nullable
    @UnstableApi
    @Deprecated
    TextComponent getTextComponent();

    @Nullable
    @UnstableApi
    TrackSelector getTrackSelector();

    @UnstableApi
    int getVideoChangeFrameRateStrategy();

    @Nullable
    @UnstableApi
    @Deprecated
    VideoComponent getVideoComponent();

    @Nullable
    @UnstableApi
    DecoderCounters getVideoDecoderCounters();

    @Nullable
    @UnstableApi
    Format getVideoFormat();

    @UnstableApi
    int getVideoScalingMode();

    @UnstableApi
    boolean isSleepingForOffload();

    @UnstableApi
    boolean isTunnelingEnabled();

    @UnstableApi
    @Deprecated
    void prepare(MediaSource mediaSource);

    @UnstableApi
    @Deprecated
    void prepare(MediaSource mediaSource, boolean z, boolean z2);

    void removeAnalyticsListener(AnalyticsListener analyticsListener);

    @UnstableApi
    void removeAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    @Override // androidx.media3.common.Player
    void replaceMediaItem(int i, MediaItem mediaItem);

    @Override // androidx.media3.common.Player
    void replaceMediaItems(int i, int i2, List<MediaItem> list);

    @UnstableApi
    void setAudioSessionId(int i);

    @UnstableApi
    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    @UnstableApi
    void setCameraMotionListener(CameraMotionListener cameraMotionListener);

    @UnstableApi
    void setForegroundMode(boolean z);

    void setHandleAudioBecomingNoisy(boolean z);

    @UnstableApi
    void setImageOutput(ImageOutput imageOutput);

    @UnstableApi
    void setMediaSource(MediaSource mediaSource);

    @UnstableApi
    void setMediaSource(MediaSource mediaSource, long j);

    @UnstableApi
    void setMediaSource(MediaSource mediaSource, boolean z);

    @UnstableApi
    void setMediaSources(List<MediaSource> list);

    @UnstableApi
    void setMediaSources(List<MediaSource> list, int i, long j);

    @UnstableApi
    void setMediaSources(List<MediaSource> list, boolean z);

    @UnstableApi
    void setPauseAtEndOfMediaItems(boolean z);

    @RequiresApi(23)
    @UnstableApi
    void setPreferredAudioDevice(@Nullable AudioDeviceInfo audioDeviceInfo);

    @UnstableApi
    void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager);

    @UnstableApi
    void setSeekParameters(@Nullable SeekParameters seekParameters);

    @UnstableApi
    void setShuffleOrder(ShuffleOrder shuffleOrder);

    @UnstableApi
    void setSkipSilenceEnabled(boolean z);

    @UnstableApi
    void setVideoChangeFrameRateStrategy(int i);

    @RequiresApi(18)
    @UnstableApi
    void setVideoEffects(List<Effect> list);

    @UnstableApi
    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    @UnstableApi
    void setVideoScalingMode(int i);

    void setWakeMode(int i);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public interface AudioOffloadListener {
        void onOffloadedPlayback(boolean z);

        void onSleepingForOffloadChanged(boolean z);

        /* JADX INFO: renamed from: androidx.media3.exoplayer.ExoPlayer$AudioOffloadListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static void $default$onOffloadedPlayback(AudioOffloadListener audioOffloadListener, boolean z) {
            }

            public static void $default$onSleepingForOffloadChanged(AudioOffloadListener audioOffloadListener, boolean z) {
            }
        }
    }
}
