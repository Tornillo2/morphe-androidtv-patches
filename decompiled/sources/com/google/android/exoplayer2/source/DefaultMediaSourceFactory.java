package com.google.android.exoplayer2.source;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.text.SubtitleDecoderFactory;
import com.google.android.exoplayer2.text.SubtitleExtractor;
import com.google.android.exoplayer2.ui.AdViewProvider;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    public static final String TAG = "DMediaSourceFactory";

    @Nullable
    public AdViewProvider adViewProvider;

    @Nullable
    public AdsLoader.Provider adsLoaderProvider;
    public DataSource.Factory dataSourceFactory;
    public final DelegateFactoryLoader delegateFactoryLoader;
    public long liveMaxOffsetMs;
    public float liveMaxSpeed;
    public long liveMinOffsetMs;
    public float liveMinSpeed;
    public long liveTargetOffsetMs;

    @Nullable
    public LoadErrorHandlingPolicy loadErrorHandlingPolicy;

    @Nullable
    public MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    public boolean useProgressiveMediaSourceForSubtitles;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public interface AdsLoaderProvider extends AdsLoader.Provider {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DelegateFactoryLoader {
        public DataSource.Factory dataSourceFactory;

        @Nullable
        public DrmSessionManagerProvider drmSessionManagerProvider;
        public final ExtractorsFactory extractorsFactory;

        @Nullable
        public LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        public final Map<Integer, Supplier<MediaSource.Factory>> mediaSourceFactorySuppliers = new HashMap();
        public final Set<Integer> supportedTypes = new HashSet();
        public final Map<Integer, MediaSource.Factory> mediaSourceFactories = new HashMap();

        public static /* synthetic */ MediaSource.Factory $r8$lambda$a2N4R4jZeM6pw6IQDhoqkZuNOTA(DelegateFactoryLoader delegateFactoryLoader, DataSource.Factory factory) {
            return new ProgressiveMediaSource.Factory(factory, delegateFactoryLoader.extractorsFactory);
        }

        public DelegateFactoryLoader(ExtractorsFactory extractorsFactory) {
            this.extractorsFactory = extractorsFactory;
        }

        public final void ensureAllSuppliersAreLoaded() {
            maybeLoadSupplier(0);
            maybeLoadSupplier(1);
            maybeLoadSupplier(2);
            maybeLoadSupplier(3);
            maybeLoadSupplier(4);
        }

        @Nullable
        public MediaSource.Factory getMediaSourceFactory(int i) {
            MediaSource.Factory factory = this.mediaSourceFactories.get(Integer.valueOf(i));
            if (factory != null) {
                return factory;
            }
            Supplier<MediaSource.Factory> supplierMaybeLoadSupplier = maybeLoadSupplier(i);
            if (supplierMaybeLoadSupplier == null) {
                return null;
            }
            MediaSource.Factory factory2 = supplierMaybeLoadSupplier.get();
            DrmSessionManagerProvider drmSessionManagerProvider = this.drmSessionManagerProvider;
            if (drmSessionManagerProvider != null) {
                factory2.setDrmSessionManagerProvider(drmSessionManagerProvider);
            }
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
            if (loadErrorHandlingPolicy != null) {
                factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            }
            this.mediaSourceFactories.put(Integer.valueOf(i), factory2);
            return factory2;
        }

        public int[] getSupportedTypes() {
            ensureAllSuppliersAreLoaded();
            return Ints.toArray(this.supportedTypes);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0085  */
        @androidx.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.google.common.base.Supplier<com.google.android.exoplayer2.source.MediaSource.Factory> maybeLoadSupplier(int r5) {
            /*
                r4 = this;
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.google.android.exoplayer2.source.MediaSource$Factory>> r0 = r4.mediaSourceFactorySuppliers
                java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
                boolean r0 = r0.containsKey(r1)
                if (r0 == 0) goto L19
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.google.android.exoplayer2.source.MediaSource$Factory>> r0 = r4.mediaSourceFactorySuppliers
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r5 = r0.get(r5)
                com.google.common.base.Supplier r5 = (com.google.common.base.Supplier) r5
                return r5
            L19:
                com.google.android.exoplayer2.upstream.DataSource$Factory r0 = r4.dataSourceFactory
                r0.getClass()
                java.lang.Class<com.google.android.exoplayer2.source.MediaSource$Factory> r1 = com.google.android.exoplayer2.source.MediaSource.Factory.class
                r2 = 0
                if (r5 == 0) goto L6a
                r3 = 1
                if (r5 == r3) goto L5a
                r3 = 2
                if (r5 == r3) goto L49
                r3 = 3
                if (r5 == r3) goto L39
                r1 = 4
                if (r5 == r1) goto L30
                goto L7a
            L30:
                com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4 r1 = new com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4     // Catch: java.lang.ClassNotFoundException -> L37
                r1.<init>()     // Catch: java.lang.ClassNotFoundException -> L37
            L35:
                r2 = r1
                goto L7a
            L37:
                goto L7a
            L39:
                java.lang.String r0 = "com.google.android.exoplayer2.source.rtsp.RtspMediaSource$Factory"
                java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.ClassNotFoundException -> L37
                java.lang.Class r0 = r0.asSubclass(r1)     // Catch: java.lang.ClassNotFoundException -> L37
                com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda3 r1 = new com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda3     // Catch: java.lang.ClassNotFoundException -> L37
                r1.<init>()     // Catch: java.lang.ClassNotFoundException -> L37
                goto L35
            L49:
                java.lang.String r3 = "com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.ClassNotFoundException -> L37
                java.lang.Class r1 = r3.asSubclass(r1)     // Catch: java.lang.ClassNotFoundException -> L37
                com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda2 r3 = new com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda2     // Catch: java.lang.ClassNotFoundException -> L37
                r3.<init>()     // Catch: java.lang.ClassNotFoundException -> L37
            L58:
                r2 = r3
                goto L7a
            L5a:
                java.lang.String r3 = "com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource$Factory"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.ClassNotFoundException -> L37
                java.lang.Class r1 = r3.asSubclass(r1)     // Catch: java.lang.ClassNotFoundException -> L37
                com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1 r3 = new com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1     // Catch: java.lang.ClassNotFoundException -> L37
                r3.<init>()     // Catch: java.lang.ClassNotFoundException -> L37
                goto L58
            L6a:
                java.lang.String r3 = "com.google.android.exoplayer2.source.dash.DashMediaSource$Factory"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.ClassNotFoundException -> L37
                java.lang.Class r1 = r3.asSubclass(r1)     // Catch: java.lang.ClassNotFoundException -> L37
                com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda0 r3 = new com.google.android.exoplayer2.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda0     // Catch: java.lang.ClassNotFoundException -> L37
                r3.<init>()     // Catch: java.lang.ClassNotFoundException -> L37
                goto L58
            L7a:
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.google.android.exoplayer2.source.MediaSource$Factory>> r0 = r4.mediaSourceFactorySuppliers
                java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
                r0.put(r1, r2)
                if (r2 == 0) goto L8e
                java.util.Set<java.lang.Integer> r0 = r4.supportedTypes
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r0.add(r5)
            L8e:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.DefaultMediaSourceFactory.DelegateFactoryLoader.maybeLoadSupplier(int):com.google.common.base.Supplier");
        }

        public void setDataSourceFactory(DataSource.Factory factory) {
            if (factory != this.dataSourceFactory) {
                this.dataSourceFactory = factory;
                this.mediaSourceFactorySuppliers.clear();
                this.mediaSourceFactories.clear();
            }
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setDrmSessionManagerProvider(drmSessionManagerProvider);
            }
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$4Rsj-LyuZQCVX9Jkp62Ar_D_s-E, reason: not valid java name */
    public static /* synthetic */ Extractor[] m472$r8$lambda$4RsjLyuZQCVX9Jkp62Ar_D_sE(Format format) {
        SubtitleDecoderFactory subtitleDecoderFactory = SubtitleDecoderFactory.DEFAULT;
        return new Extractor[]{subtitleDecoderFactory.supportsFormat(format) ? new SubtitleExtractor(subtitleDecoderFactory.createDecoder(format), format) : new UnknownSubtitlesExtractor(format)};
    }

    public DefaultMediaSourceFactory(Context context) {
        this(new DefaultDataSource.Factory(context));
    }

    public static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        long j = clippingConfiguration.startPositionMs;
        if (j == 0 && clippingConfiguration.endPositionMs == Long.MIN_VALUE && !clippingConfiguration.relativeToDefaultPosition) {
            return mediaSource;
        }
        long jMsToUs = Util.msToUs(j);
        long jMsToUs2 = Util.msToUs(mediaItem.clippingConfiguration.endPositionMs);
        MediaItem.ClippingConfiguration clippingConfiguration2 = mediaItem.clippingConfiguration;
        return new ClippingMediaSource(mediaSource, jMsToUs, jMsToUs2, !clippingConfiguration2.startsAtKeyFrame, clippingConfiguration2.relativeToLiveWindow, clippingConfiguration2.relativeToDefaultPosition);
    }

    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls, DataSource.Factory factory) {
        try {
            return cls.getConstructor(DataSource.Factory.class).newInstance(factory);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory clearLocalAdInsertionComponents() {
        this.adsLoaderProvider = null;
        this.adViewProvider = null;
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    public MediaSource createMediaSource(MediaItem mediaItem) {
        mediaItem.localConfiguration.getClass();
        String scheme = mediaItem.localConfiguration.uri.getScheme();
        if (scheme != null && scheme.equals("ssai")) {
            MediaSource.Factory factory = this.serverSideAdInsertionMediaSourceFactory;
            factory.getClass();
            return factory.createMediaSource(mediaItem);
        }
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        MediaSource.Factory mediaSourceFactory = this.delegateFactoryLoader.getMediaSourceFactory(iInferContentTypeForUriAndMimeType);
        Assertions.checkStateNotNull(mediaSourceFactory, "No suitable media source factory found for content type: " + iInferContentTypeForUriAndMimeType);
        MediaItem.LiveConfiguration liveConfiguration = mediaItem.liveConfiguration;
        liveConfiguration.getClass();
        MediaItem.LiveConfiguration.Builder builder = new MediaItem.LiveConfiguration.Builder(liveConfiguration);
        MediaItem.LiveConfiguration liveConfiguration2 = mediaItem.liveConfiguration;
        if (liveConfiguration2.targetOffsetMs == -9223372036854775807L) {
            builder.targetOffsetMs = this.liveTargetOffsetMs;
        }
        if (liveConfiguration2.minPlaybackSpeed == -3.4028235E38f) {
            builder.minPlaybackSpeed = this.liveMinSpeed;
        }
        if (liveConfiguration2.maxPlaybackSpeed == -3.4028235E38f) {
            builder.maxPlaybackSpeed = this.liveMaxSpeed;
        }
        if (liveConfiguration2.minOffsetMs == -9223372036854775807L) {
            builder.minOffsetMs = this.liveMinOffsetMs;
        }
        if (liveConfiguration2.maxOffsetMs == -9223372036854775807L) {
            builder.maxOffsetMs = this.liveMaxOffsetMs;
        }
        MediaItem.LiveConfiguration liveConfiguration3 = new MediaItem.LiveConfiguration(builder);
        if (!liveConfiguration3.equals(mediaItem.liveConfiguration)) {
            MediaItem.Builder builder2 = new MediaItem.Builder(mediaItem);
            builder2.setLiveConfiguration(liveConfiguration3);
            mediaItem = builder2.build();
        }
        MediaSource mediaSourceCreateMediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        ImmutableList<MediaItem.SubtitleConfiguration> immutableList = mediaItem.localConfiguration.subtitleConfigurations;
        if (!immutableList.isEmpty()) {
            MediaSource[] mediaSourceArr = new MediaSource[immutableList.size() + 1];
            mediaSourceArr[0] = mediaSourceCreateMediaSource;
            for (int i = 0; i < immutableList.size(); i++) {
                if (this.useProgressiveMediaSourceForSubtitles) {
                    Format.Builder builder3 = new Format.Builder();
                    builder3.sampleMimeType = immutableList.get(i).mimeType;
                    builder3.language = immutableList.get(i).language;
                    builder3.selectionFlags = immutableList.get(i).selectionFlags;
                    builder3.roleFlags = immutableList.get(i).roleFlags;
                    builder3.label = immutableList.get(i).label;
                    builder3.id = immutableList.get(i).id;
                    final Format format = new Format(builder3);
                    ProgressiveMediaSource.Factory factory2 = new ProgressiveMediaSource.Factory(this.dataSourceFactory, new ExtractorsFactory() { // from class: com.google.android.exoplayer2.source.DefaultMediaSourceFactory$$ExternalSyntheticLambda0
                        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
                        public final Extractor[] createExtractors() {
                            return DefaultMediaSourceFactory.m472$r8$lambda$4RsjLyuZQCVX9Jkp62Ar_D_sE(format);
                        }

                        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
                        public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
                            return createExtractors();
                        }
                    });
                    LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
                    if (loadErrorHandlingPolicy != null) {
                        factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
                    }
                    mediaSourceArr[i + 1] = factory2.createMediaSource(MediaItem.fromUri(immutableList.get(i).uri.toString()));
                } else {
                    SingleSampleMediaSource.Factory factory3 = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
                    LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
                    if (loadErrorHandlingPolicy2 != null) {
                        factory3.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
                    }
                    mediaSourceArr[i + 1] = factory3.createMediaSource(immutableList.get(i), -9223372036854775807L);
                }
            }
            mediaSourceCreateMediaSource = new MergingMediaSource(false, false, mediaSourceArr);
        }
        return maybeWrapWithAdsMediaSource(mediaItem, maybeClipMediaSource(mediaItem, mediaSourceCreateMediaSource));
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory experimentalUseProgressiveMediaSourceForSubtitles(boolean z) {
        this.useProgressiveMediaSourceForSubtitles = z;
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    public int[] getSupportedTypes() {
        return this.delegateFactoryLoader.getSupportedTypes();
    }

    public final MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        mediaItem.localConfiguration.getClass();
        MediaItem.AdsConfiguration adsConfiguration = mediaItem.localConfiguration.adsConfiguration;
        if (adsConfiguration == null) {
            return mediaSource;
        }
        AdsLoader.Provider provider = this.adsLoaderProvider;
        AdViewProvider adViewProvider = this.adViewProvider;
        if (provider == null || adViewProvider == null) {
            Log.w("DMediaSourceFactory", "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
            return mediaSource;
        }
        AdsLoader adsLoader = provider.getAdsLoader(adsConfiguration);
        if (adsLoader == null) {
            Log.w("DMediaSourceFactory", "Playing media without ads, as no AdsLoader was provided.");
            return mediaSource;
        }
        DataSpec dataSpec = new DataSpec(adsConfiguration.adTagUri);
        Object objOf = adsConfiguration.adsId;
        if (objOf == null) {
            objOf = ImmutableList.of((Uri) mediaItem.mediaId, mediaItem.localConfiguration.uri, adsConfiguration.adTagUri);
        }
        return new AdsMediaSource(mediaSource, dataSpec, objOf, this, adsLoader, adViewProvider);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public DefaultMediaSourceFactory setAdViewProvider(@Nullable AdViewProvider adViewProvider) {
        this.adViewProvider = adViewProvider;
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public DefaultMediaSourceFactory setAdsLoaderProvider(@Nullable AdsLoader.Provider provider) {
        this.adsLoaderProvider = provider;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.delegateFactoryLoader.setDataSourceFactory(factory);
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        setDrmSessionManagerProvider(drmSessionManagerProvider);
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLiveMaxOffsetMs(long j) {
        this.liveMaxOffsetMs = j;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLiveMaxSpeed(float f) {
        this.liveMaxSpeed = f;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLiveMinOffsetMs(long j) {
        this.liveMinOffsetMs = j;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLiveMinSpeed(float f) {
        this.liveMinSpeed = f;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLiveTargetOffsetMs(long j) {
        this.liveTargetOffsetMs = j;
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLocalAdInsertionComponents(AdsLoader.Provider provider, AdViewProvider adViewProvider) {
        provider.getClass();
        this.adsLoaderProvider = provider;
        adViewProvider.getClass();
        this.adViewProvider = adViewProvider;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setServerSideAdInsertionMediaSourceFactory(@Nullable MediaSource.Factory factory) {
        this.serverSideAdInsertionMediaSourceFactory = factory;
        return this;
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this(new DefaultDataSource.Factory(context), extractorsFactory);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        DelegateFactoryLoader delegateFactoryLoader = this.delegateFactoryLoader;
        Assertions.checkNotNull(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
        delegateFactoryLoader.setDrmSessionManagerProvider(drmSessionManagerProvider);
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Factory
    @CanIgnoreReturnValue
    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        Assertions.checkNotNull(loadErrorHandlingPolicy, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        return this;
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory) {
        this(factory, new DefaultExtractorsFactory());
    }

    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls) {
        try {
            return cls.getConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = factory;
        DelegateFactoryLoader delegateFactoryLoader = new DelegateFactoryLoader(extractorsFactory);
        this.delegateFactoryLoader = delegateFactoryLoader;
        delegateFactoryLoader.setDataSourceFactory(factory);
        this.liveTargetOffsetMs = -9223372036854775807L;
        this.liveMinOffsetMs = -9223372036854775807L;
        this.liveMaxOffsetMs = -9223372036854775807L;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnknownSubtitlesExtractor implements Extractor {
        public final Format format;

        public UnknownSubtitlesExtractor(Format format) {
            this.format = format;
        }

        @Override // com.google.android.exoplayer2.extractor.Extractor
        public void init(ExtractorOutput extractorOutput) {
            TrackOutput trackOutputTrack = extractorOutput.track(0, 3);
            extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
            extractorOutput.endTracks();
            Format format = this.format;
            format.getClass();
            Format.Builder builder = new Format.Builder(format);
            builder.sampleMimeType = "text/x-unknown";
            builder.codecs = this.format.sampleMimeType;
            trackOutputTrack.format(new Format(builder));
        }

        @Override // com.google.android.exoplayer2.extractor.Extractor
        public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
            return extractorInput.skip(Integer.MAX_VALUE) == -1 ? -1 : 0;
        }

        @Override // com.google.android.exoplayer2.extractor.Extractor
        public boolean sniff(ExtractorInput extractorInput) {
            return true;
        }

        @Override // com.google.android.exoplayer2.extractor.Extractor
        public void release() {
        }

        @Override // com.google.android.exoplayer2.extractor.Extractor
        public void seek(long j, long j2) {
        }
    }
}
