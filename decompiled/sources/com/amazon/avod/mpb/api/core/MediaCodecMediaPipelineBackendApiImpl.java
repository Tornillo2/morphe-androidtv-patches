package com.amazon.avod.mpb.api.core;

import android.content.Context;
import com.amazon.avod.mpb.annotate.CalledFromIgnite;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendApi;
import com.amazon.avod.mpb.api.query.CodecQuery;
import com.amazon.avod.mpb.api.query.CodecQueryResult;
import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import com.amazon.avod.mpb.media.playback.mediacodec.MediaCodecRenderer;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMediaCodecMediaPipelineBackendApiImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaCodecMediaPipelineBackendApiImpl.kt\ncom/amazon/avod/mpb/api/core/MediaCodecMediaPipelineBackendApiImpl\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,173:1\n205#2:174\n1869#3,2:175\n*S KotlinDebug\n*F\n+ 1 MediaCodecMediaPipelineBackendApiImpl.kt\ncom/amazon/avod/mpb/api/core/MediaCodecMediaPipelineBackendApiImpl\n*L\n88#1:174\n127#1:175,2\n*E\n"})
public final class MediaCodecMediaPipelineBackendApiImpl implements MediaPipelineBackendApi<MediaCodecRenderer> {

    @NotNull
    public static final Factory Factory = new Factory();

    @NotNull
    public final Context appContext;

    @NotNull
    public final MediaPipelineBackendApiCallbacks callbacks;

    @NotNull
    public final CapabilitiesProvider capabilitiesProvider;

    @NotNull
    public final MediaCodecDeviceCapabilityDetector capabilityDetector;

    @NotNull
    public PictureMode currentPictureMode;

    @NotNull
    public final DevicePropertyProvider devicePropertyProvider;

    @NotNull
    public final FailoverManager failoverManager;

    @NotNull
    public final List<MediaCodecRenderer> mediaCodecInstances;

    @NotNull
    public final MediaCodecQuerier mediaCodecQuerier;

    @NotNull
    public final MediaCodecRenderer.MediaCodecRendererFactory mediaCodecRendererFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements MediaPipelineBackendApi.MediaPipelineBackendApiFactory<MediaCodecRenderer, MediaPipelineBackendContextImpl> {

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class PropertyKeys {
            public static final /* synthetic */ EnumEntries $ENTRIES;
            public static final /* synthetic */ PropertyKeys[] $VALUES;
            public static final PropertyKeys pictureMode;

            public static final /* synthetic */ PropertyKeys[] $values() {
                return new PropertyKeys[]{pictureMode};
            }

            static {
                PropertyKeys propertyKeys = new PropertyKeys(MediaPipelineBackendEngineManager.PictureModeConstants.PROPERTY_KEY, 0);
                pictureMode = propertyKeys;
                PropertyKeys[] propertyKeysArr = {propertyKeys};
                $VALUES = propertyKeysArr;
                $ENTRIES = EnumEntriesKt.enumEntries(propertyKeysArr);
            }

            public PropertyKeys(String str, int i) {
            }

            @NotNull
            public static EnumEntries<PropertyKeys> getEntries() {
                return $ENTRIES;
            }

            public static PropertyKeys valueOf(String str) {
                return (PropertyKeys) Enum.valueOf(PropertyKeys.class, str);
            }

            public static PropertyKeys[] values() {
                return (PropertyKeys[]) $VALUES.clone();
            }
        }

        public Factory() {
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi.MediaPipelineBackendApiFactory
        @CalledFromIgnite
        @NotNull
        public MediaCodecMediaPipelineBackendApiImpl create(@NotNull MediaPipelineBackendApiCallbacks callbacks, @NotNull MediaPipelineBackendContextImpl mediaPipelineBackendContext, @NotNull CapabilitiesProvider capabilitiesProvider, @NotNull DevicePropertyProvider devicePropertyProvider, @NotNull FailoverManager failoverManager) {
            Intrinsics.checkNotNullParameter(callbacks, "callbacks");
            Intrinsics.checkNotNullParameter(mediaPipelineBackendContext, "mediaPipelineBackendContext");
            Intrinsics.checkNotNullParameter(capabilitiesProvider, "capabilitiesProvider");
            Intrinsics.checkNotNullParameter(devicePropertyProvider, "devicePropertyProvider");
            Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
            MpbLog.INSTANCE.initialize(callbacks.logCallback);
            Context context = mediaPipelineBackendContext.appContext;
            return new MediaCodecMediaPipelineBackendApiImpl(callbacks, context, new MediaCodecDeviceCapabilityDetector(context, devicePropertyProvider), new MediaCodecRenderer.MediaCodecRendererFactory(), capabilitiesProvider, devicePropertyProvider, failoverManager, null, null, 384, null);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MediaPipelineBackendContextImpl implements MediaPipelineBackendApi.MediaPipelineBackendContext {

        @NotNull
        public final Context appContext;

        public MediaPipelineBackendContextImpl(@NotNull Context appContext) {
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            this.appContext = appContext;
        }

        public static /* synthetic */ MediaPipelineBackendContextImpl copy$default(MediaPipelineBackendContextImpl mediaPipelineBackendContextImpl, Context context, int i, Object obj) {
            if ((i & 1) != 0) {
                context = mediaPipelineBackendContextImpl.appContext;
            }
            return mediaPipelineBackendContextImpl.copy(context);
        }

        @NotNull
        public final Context component1() {
            return this.appContext;
        }

        @NotNull
        public final MediaPipelineBackendContextImpl copy(@NotNull Context appContext) {
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            return new MediaPipelineBackendContextImpl(appContext);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MediaPipelineBackendContextImpl) && Intrinsics.areEqual(this.appContext, ((MediaPipelineBackendContextImpl) obj).appContext);
        }

        @NotNull
        public final Context getAppContext() {
            return this.appContext;
        }

        public int hashCode() {
            return this.appContext.hashCode();
        }

        @NotNull
        public String toString() {
            return "MediaPipelineBackendContextImpl(appContext=" + this.appContext + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Factory.PropertyKeys.values().length];
            try {
                iArr[Factory.PropertyKeys.pictureMode.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MediaCodecMediaPipelineBackendApiImpl(@NotNull MediaPipelineBackendApiCallbacks callbacks, @NotNull Context appContext, @NotNull MediaCodecDeviceCapabilityDetector capabilityDetector, @NotNull MediaCodecRenderer.MediaCodecRendererFactory mediaCodecRendererFactory, @NotNull CapabilitiesProvider capabilitiesProvider, @NotNull DevicePropertyProvider devicePropertyProvider, @NotNull FailoverManager failoverManager, @NotNull List<MediaCodecRenderer> mediaCodecInstances, @NotNull MediaCodecQuerier mediaCodecQuerier) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(capabilityDetector, "capabilityDetector");
        Intrinsics.checkNotNullParameter(mediaCodecRendererFactory, "mediaCodecRendererFactory");
        Intrinsics.checkNotNullParameter(capabilitiesProvider, "capabilitiesProvider");
        Intrinsics.checkNotNullParameter(devicePropertyProvider, "devicePropertyProvider");
        Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
        Intrinsics.checkNotNullParameter(mediaCodecInstances, "mediaCodecInstances");
        Intrinsics.checkNotNullParameter(mediaCodecQuerier, "mediaCodecQuerier");
        this.callbacks = callbacks;
        this.appContext = appContext;
        this.capabilityDetector = capabilityDetector;
        this.mediaCodecRendererFactory = mediaCodecRendererFactory;
        this.capabilitiesProvider = capabilitiesProvider;
        this.devicePropertyProvider = devicePropertyProvider;
        this.failoverManager = failoverManager;
        this.mediaCodecInstances = mediaCodecInstances;
        this.mediaCodecQuerier = mediaCodecQuerier;
        this.currentPictureMode = PictureMode.NONE;
    }

    public static final boolean _init_$lambda$0(List list) {
        return !list.isEmpty();
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    @NotNull
    public CodecQueryResult canDecodeMedia(@NotNull CodecQuery codecQuery) {
        Intrinsics.checkNotNullParameter(codecQuery, "codecQuery");
        return this.mediaCodecQuerier.executeQuery(codecQuery);
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    @CalledFromIgnite
    @NotNull
    public String getCapabilities() throws MediaPipelineBackendException {
        try {
            Json.Default r0 = Json.Default;
            MediaPipelineBackendCapabilities capabilities = this.capabilitiesProvider.getCapabilities();
            r0.getClass();
            return r0.encodeToString(MediaPipelineBackendCapabilities.Companion.serializer(), capabilities);
        } catch (Exception e) {
            throw new MediaPipelineBackendException("Invalid capability config", MediaPipelineBackendResultCode.AV_MPB_INVALID_CAPABILITY_CONFIG, e);
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    @NotNull
    public String getProperty(@NotNull String key) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            if (WhenMappings.$EnumSwitchMapping$0[Factory.PropertyKeys.valueOf(key).ordinal()] == 1) {
                return this.currentPictureMode.propertyName;
            }
            throw new NoWhenBranchMatchedException();
        } catch (IllegalArgumentException unused) {
            throw new MediaPipelineBackendException("Failed to get property, unknown key ".concat(key), MediaPipelineBackendResultCode.AV_MPB_KEY_NOT_FOUND);
        }
    }

    public final boolean isInstanceTracked(@NotNull MediaCodecRenderer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        return this.mediaCodecInstances.contains(instance);
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    public void setProperty(@NotNull String key, @Nullable String str) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            if (WhenMappings.$EnumSwitchMapping$0[Factory.PropertyKeys.valueOf(key).ordinal()] != 1) {
                throw new NoWhenBranchMatchedException();
            }
            PictureMode pictureModeFromPropertyName = PictureMode.Companion.fromPropertyName(str);
            if (pictureModeFromPropertyName == null || !this.capabilitiesProvider.getCapabilities().supportedPictureModes.contains(pictureModeFromPropertyName)) {
                throw new MediaPipelineBackendException("Failed to set property, unknown picture mode " + str, MediaPipelineBackendResultCode.AV_MPB_PICTURE_MODE_NOT_SUPPORTED);
            }
            this.currentPictureMode = pictureModeFromPropertyName;
            Iterator<T> it = this.mediaCodecInstances.iterator();
            while (it.hasNext()) {
                ((MediaCodecRenderer) it.next()).setPictureMode(this.currentPictureMode);
            }
            this.callbacks.apiPropertyChangedCallback.onPropertyChanged(key, pictureModeFromPropertyName.propertyName);
        } catch (IllegalArgumentException e) {
            throw new MediaPipelineBackendException("Failed to set property, unknown key ".concat(key), MediaPipelineBackendResultCode.AV_MPB_KEY_NOT_FOUND, e);
        }
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    @NotNull
    public MediaCodecRenderer createInstance() throws MediaPipelineBackendException {
        if (this.mediaCodecInstances.size() >= this.capabilitiesProvider.getCapabilities().maxInstanceCount) {
            throw new MediaPipelineBackendException("Failed to create instance, too many instances", MediaPipelineBackendResultCode.AV_MPB_CONCURRENT_INSTANCE_CREATION_FAILED);
        }
        MediaCodecRenderer mediaCodecRendererCreate = this.mediaCodecRendererFactory.create(this.appContext, this.capabilityDetector, this.capabilitiesProvider, this.currentPictureMode, this.devicePropertyProvider, this.failoverManager);
        this.mediaCodecInstances.add(mediaCodecRendererCreate);
        return mediaCodecRendererCreate;
    }

    @Override // com.amazon.avod.mpb.api.core.MediaPipelineBackendApi
    public void destroyInstance(@NotNull MediaCodecRenderer mediaPipelineBackendInstance) {
        Intrinsics.checkNotNullParameter(mediaPipelineBackendInstance, "mediaPipelineBackendInstance");
        this.mediaCodecInstances.remove(mediaPipelineBackendInstance);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MediaCodecMediaPipelineBackendApiImpl(MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks, Context context, MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector, MediaCodecRenderer.MediaCodecRendererFactory mediaCodecRendererFactory, CapabilitiesProvider capabilitiesProvider, DevicePropertyProvider devicePropertyProvider, FailoverManager failoverManager, List list, MediaCodecQuerier mediaCodecQuerier, int i, DefaultConstructorMarker defaultConstructorMarker) {
        final List arrayList = (i & 128) != 0 ? new ArrayList() : list;
        this(mediaPipelineBackendApiCallbacks, context, mediaCodecDeviceCapabilityDetector, mediaCodecRendererFactory, capabilitiesProvider, devicePropertyProvider, failoverManager, arrayList, (i & 256) != 0 ? new MediaCodecQuerier(MediaCodecEnumeratorImpl.Companion.getInstance(), mediaCodecDeviceCapabilityDetector, devicePropertyProvider, failoverManager, null, null, new Function0() { // from class: com.amazon.avod.mpb.api.core.MediaCodecMediaPipelineBackendApiImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(MediaCodecMediaPipelineBackendApiImpl._init_$lambda$0(arrayList));
            }
        }, 48, null) : mediaCodecQuerier);
    }
}
