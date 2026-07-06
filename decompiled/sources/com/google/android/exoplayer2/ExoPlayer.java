package com.google.android.exoplayer2;

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
import com.google.android.exoplayer2.DefaultLivePlaybackSpeedControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AuxEffectInfo;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ExoPlayer extends Player {
    public static final long DEFAULT_DETACH_SURFACE_TIMEOUT_MS = 2000;
    public static final long DEFAULT_RELEASE_TIMEOUT_MS = 500;

    /* JADX INFO: renamed from: com.google.android.exoplayer2.ExoPlayer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
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
        public Supplier<TrackSelector> trackSelectorSupplier;
        public boolean useLazyPreparation;
        public boolean usePlatformDiagnostics;
        public int videoChangeFrameRateStrategy;
        public int videoScalingMode;
        public int wakeMode;

        /* JADX INFO: renamed from: $r8$lambda$4qhvUKIRrN-DysIrv45HiMI7TtI, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m367$r8$lambda$4qhvUKIRrNDysIrv45HiMI7TtI(Context context) {
            return new DefaultRenderersFactory(context);
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$X2_mf1HQvcGu0T_SxEgrzZuFpjU(Context context) {
            return new DefaultRenderersFactory(context);
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$Z9NUWa_6QXYrw0QqdfsKlmwIjME(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$c7ckL0smTA3bpmiGzJBPoUjDcO8(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        public static /* synthetic */ TrackSelector $r8$lambda$jNFyOnL11Ub_hDLTi6yxZquXOOw(Context context) {
            return new DefaultTrackSelector(context);
        }

        public Builder(final Context context) {
            this(context, (Supplier<RenderersFactory>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda1
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultRenderersFactory(context);
                }
            }, (Supplier<MediaSource.Factory>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return ExoPlayer.Builder.$r8$lambda$Z9NUWa_6QXYrw0QqdfsKlmwIjME(context);
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
        public Builder experimentalSetForegroundModeTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.foregroundModeTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAnalyticsCollector(AnalyticsCollector analyticsCollector) {
            Assertions.checkState(!this.buildCalled);
            analyticsCollector.getClass();
            this.analyticsCollectorFunction = new ExoPlayer$Builder$$ExternalSyntheticLambda7(analyticsCollector);
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
        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            Assertions.checkState(!this.buildCalled);
            bandwidthMeter.getClass();
            this.bandwidthMeterSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda5(bandwidthMeter);
            return this;
        }

        @CanIgnoreReturnValue
        @VisibleForTesting
        public Builder setClock(Clock clock) {
            Assertions.checkState(!this.buildCalled);
            this.clock = clock;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setDetachSurfaceTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.detachSurfaceTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHandleAudioBecomingNoisy(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.handleAudioBecomingNoisy = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLivePlaybackSpeedControl(LivePlaybackSpeedControl livePlaybackSpeedControl) {
            Assertions.checkState(!this.buildCalled);
            livePlaybackSpeedControl.getClass();
            this.livePlaybackSpeedControl = livePlaybackSpeedControl;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLoadControl(LoadControl loadControl) {
            Assertions.checkState(!this.buildCalled);
            loadControl.getClass();
            this.loadControlSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda0(loadControl);
            return this;
        }

        @CanIgnoreReturnValue
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
            this.mediaSourceFactorySupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda4(factory);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPauseAtEndOfMediaItems(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.pauseAtEndOfMediaItems = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPlaybackLooper(Looper looper) {
            Assertions.checkState(!this.buildCalled);
            this.playbackLooper = looper;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
            Assertions.checkState(!this.buildCalled);
            this.priorityTaskManager = priorityTaskManager;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setReleaseTimeoutMs(long j) {
            Assertions.checkState(!this.buildCalled);
            this.releaseTimeoutMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            Assertions.checkState(!this.buildCalled);
            renderersFactory.getClass();
            this.renderersFactorySupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda3(renderersFactory);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSeekBackIncrementMs(@IntRange(from = 1) long j) {
            Assertions.checkArgument(j > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekBackIncrementMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSeekForwardIncrementMs(@IntRange(from = 1) long j) {
            Assertions.checkArgument(j > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekForwardIncrementMs = j;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSeekParameters(SeekParameters seekParameters) {
            Assertions.checkState(!this.buildCalled);
            seekParameters.getClass();
            this.seekParameters = seekParameters;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSkipSilenceEnabled(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.skipSilenceEnabled = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTrackSelector(TrackSelector trackSelector) {
            Assertions.checkState(!this.buildCalled);
            trackSelector.getClass();
            this.trackSelectorSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda6(trackSelector);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUseLazyPreparation(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.useLazyPreparation = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUsePlatformDiagnostics(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.usePlatformDiagnostics = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setVideoChangeFrameRateStrategy(int i) {
            Assertions.checkState(!this.buildCalled);
            this.videoChangeFrameRateStrategy = i;
            return this;
        }

        @CanIgnoreReturnValue
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

        public Builder(final Context context, RenderersFactory renderersFactory) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda3(renderersFactory), (Supplier<MediaSource.Factory>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda8
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return ExoPlayer.Builder.$r8$lambda$c7ckL0smTA3bpmiGzJBPoUjDcO8(context);
                }
            });
            renderersFactory.getClass();
        }

        public Builder(final Context context, MediaSource.Factory factory) {
            this(context, (Supplier<RenderersFactory>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda9
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultRenderersFactory(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda4(factory));
            factory.getClass();
        }

        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda3(renderersFactory), new ExoPlayer$Builder$$ExternalSyntheticLambda4(factory));
            renderersFactory.getClass();
            factory.getClass();
        }

        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            this(context, new ExoPlayer$Builder$$ExternalSyntheticLambda3(renderersFactory), new ExoPlayer$Builder$$ExternalSyntheticLambda4(factory), new ExoPlayer$Builder$$ExternalSyntheticLambda6(trackSelector), new ExoPlayer$Builder$$ExternalSyntheticLambda0(loadControl), new ExoPlayer$Builder$$ExternalSyntheticLambda5(bandwidthMeter), new ExoPlayer$Builder$$ExternalSyntheticLambda7(analyticsCollector));
            renderersFactory.getClass();
            factory.getClass();
            trackSelector.getClass();
            bandwidthMeter.getClass();
            analyticsCollector.getClass();
        }

        public Builder(final Context context, Supplier<RenderersFactory> supplier, Supplier<MediaSource.Factory> supplier2) {
            this(context, supplier, supplier2, (Supplier<TrackSelector>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda10
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return new DefaultTrackSelector(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda11(), (Supplier<BandwidthMeter>) new Supplier() { // from class: com.google.android.exoplayer2.ExoPlayer$Builder$$ExternalSyntheticLambda12
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return DefaultBandwidthMeter.getSingletonInstance(context);
                }
            }, new ExoPlayer$Builder$$ExternalSyntheticLambda13());
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

        public static /* synthetic */ LoadControl $r8$lambda$0ZBTIT1FQlcAqNBLJ4PSbxp0JLA(LoadControl loadControl) {
            return loadControl;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$5R__z8g6rfDkVQItyR81ZIAIaLQ(MediaSource.Factory factory) {
            return factory;
        }

        public static /* synthetic */ TrackSelector $r8$lambda$C4W3_CpBDGRsHmoIhfOUQmtXsl4(TrackSelector trackSelector) {
            return trackSelector;
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$FK7Dw1u7xIDBJvaxQg5a74SvH1Y(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: renamed from: $r8$lambda$GYdozbRODnlGDQgrVACU4rpd-6I, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m368$r8$lambda$GYdozbRODnlGDQgrVACU4rpd6I(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$Rm5gSJZcXNi8I85Rhm4WwGIpY4w(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: renamed from: $r8$lambda$bsU2eMgw2rlWiR0tUb-fhAgkqHY, reason: not valid java name */
        public static /* synthetic */ MediaSource.Factory m369$r8$lambda$bsU2eMgw2rlWiR0tUbfhAgkqHY(MediaSource.Factory factory) {
            return factory;
        }

        public static /* synthetic */ TrackSelector $r8$lambda$cQeZLRke51P0ok19tBSVs02sDm8(TrackSelector trackSelector) {
            return trackSelector;
        }

        public static /* synthetic */ BandwidthMeter $r8$lambda$fEmpVpTBogYpTIssa6crV_MLBUY(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* JADX INFO: renamed from: $r8$lambda$kYlFSN-U3DurMKE9Ve65tBSXwvw, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m370$r8$lambda$kYlFSNU3DurMKE9Ve65tBSXwvw(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: renamed from: $r8$lambda$ltlrQNENPRNydt6r-8ac7PXQmJI, reason: not valid java name */
        public static /* synthetic */ MediaSource.Factory m371$r8$lambda$ltlrQNENPRNydt6r8ac7PXQmJI(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: renamed from: $r8$lambda$p0Ms_bX8eN-xA4umMZoBizwyc8E, reason: not valid java name */
        public static /* synthetic */ BandwidthMeter m372$r8$lambda$p0Ms_bX8eNxA4umMZoBizwyc8E(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        public static /* synthetic */ LoadControl $r8$lambda$riCuehVJ3XIhWdU_TuhRzEblprE(LoadControl loadControl) {
            return loadControl;
        }

        public static /* synthetic */ RenderersFactory $r8$lambda$v7DyEDr6jtJ3QYVITlw9zjMwXT8(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public static /* synthetic */ AnalyticsCollector $r8$lambda$IIDiw4AI8OJmsthAmly1AALdDXs(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }

        /* JADX INFO: renamed from: $r8$lambda$r2sIpREQn6y0MaHXaNJ-XGqRix8, reason: not valid java name */
        public static /* synthetic */ AnalyticsCollector m373$r8$lambda$r2sIpREQn6y0MaHXaNJXGqRix8(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
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
    @Deprecated
    public interface TextComponent {
        @Deprecated
        CueGroup getCurrentCues();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
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

    void addAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    void addMediaSource(int i, MediaSource mediaSource);

    void addMediaSource(MediaSource mediaSource);

    void addMediaSources(int i, List<MediaSource> list);

    void addMediaSources(List<MediaSource> list);

    void clearAuxEffectInfo();

    void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

    void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    PlayerMessage createMessage(PlayerMessage.Target target);

    boolean experimentalIsSleepingForOffload();

    void experimentalSetOffloadSchedulingEnabled(boolean z);

    AnalyticsCollector getAnalyticsCollector();

    @Nullable
    @Deprecated
    AudioComponent getAudioComponent();

    @Nullable
    DecoderCounters getAudioDecoderCounters();

    @Nullable
    Format getAudioFormat();

    int getAudioSessionId();

    Clock getClock();

    @Deprecated
    TrackGroupArray getCurrentTrackGroups();

    @Deprecated
    TrackSelectionArray getCurrentTrackSelections();

    @Nullable
    @Deprecated
    DeviceComponent getDeviceComponent();

    boolean getPauseAtEndOfMediaItems();

    Looper getPlaybackLooper();

    @Override // com.google.android.exoplayer2.Player
    @Nullable
    ExoPlaybackException getPlayerError();

    @Override // com.google.android.exoplayer2.Player
    @Nullable
    /* bridge */ /* synthetic */ PlaybackException getPlayerError();

    Renderer getRenderer(int i);

    int getRendererCount();

    int getRendererType(int i);

    SeekParameters getSeekParameters();

    boolean getSkipSilenceEnabled();

    @Nullable
    @Deprecated
    TextComponent getTextComponent();

    @Nullable
    TrackSelector getTrackSelector();

    int getVideoChangeFrameRateStrategy();

    @Nullable
    @Deprecated
    VideoComponent getVideoComponent();

    @Nullable
    DecoderCounters getVideoDecoderCounters();

    @Nullable
    Format getVideoFormat();

    int getVideoScalingMode();

    boolean isTunnelingEnabled();

    @Deprecated
    void prepare(MediaSource mediaSource);

    @Deprecated
    void prepare(MediaSource mediaSource, boolean z, boolean z2);

    void removeAnalyticsListener(AnalyticsListener analyticsListener);

    void removeAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    @Deprecated
    void retry();

    void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

    void setAudioSessionId(int i);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setCameraMotionListener(CameraMotionListener cameraMotionListener);

    void setForegroundMode(boolean z);

    void setHandleAudioBecomingNoisy(boolean z);

    @Deprecated
    void setHandleWakeLock(boolean z);

    void setMediaSource(MediaSource mediaSource);

    void setMediaSource(MediaSource mediaSource, long j);

    void setMediaSource(MediaSource mediaSource, boolean z);

    void setMediaSources(List<MediaSource> list);

    void setMediaSources(List<MediaSource> list, int i, long j);

    void setMediaSources(List<MediaSource> list, boolean z);

    void setPauseAtEndOfMediaItems(boolean z);

    @RequiresApi(23)
    void setPreferredAudioDevice(@Nullable AudioDeviceInfo audioDeviceInfo);

    void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager);

    void setSeekParameters(@Nullable SeekParameters seekParameters);

    void setShuffleOrder(ShuffleOrder shuffleOrder);

    void setSkipSilenceEnabled(boolean z);

    void setVideoChangeFrameRateStrategy(int i);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void setVideoScalingMode(int i);

    void setWakeMode(int i);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AudioOffloadListener {
        void onExperimentalOffloadSchedulingEnabledChanged(boolean z);

        void onExperimentalOffloadedPlayback(boolean z);

        void onExperimentalSleepingForOffloadChanged(boolean z);

        /* JADX INFO: renamed from: com.google.android.exoplayer2.ExoPlayer$AudioOffloadListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static void $default$onExperimentalOffloadSchedulingEnabledChanged(AudioOffloadListener audioOffloadListener, boolean z) {
            }

            public static void $default$onExperimentalOffloadedPlayback(AudioOffloadListener audioOffloadListener, boolean z) {
            }

            public static void $default$onExperimentalSleepingForOffloadChanged(AudioOffloadListener audioOffloadListener, boolean z) {
            }
        }
    }
}
