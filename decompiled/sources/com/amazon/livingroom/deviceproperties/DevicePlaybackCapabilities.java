package com.amazon.livingroom.deviceproperties;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
@SourceDebugExtension({"SMAP\nDevicePlaybackCapabilities.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DevicePlaybackCapabilities.kt\ncom/amazon/livingroom/deviceproperties/DevicePlaybackCapabilities\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,50:1\n205#2:51\n*S KotlinDebug\n*F\n+ 1 DevicePlaybackCapabilities.kt\ncom/amazon/livingroom/deviceproperties/DevicePlaybackCapabilities\n*L\n23#1:51\n*E\n"})
public final class DevicePlaybackCapabilities {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();
    public final boolean intraChunkSeekSupport;
    public final int maxInstanceCount;

    @NotNull
    public final List<String> supportedPictureModes;

    @NotNull
    public final List<String> supportedTracks;
    public final boolean supportsAudioVolume;
    public final boolean supportsDynamicPlayerResize;
    public final boolean supportsTrackDARUpdates;
    public final boolean supportsVariableAspectRatio;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        @NotNull
        public final DevicePlaybackCapabilities create(boolean z, boolean z2, boolean z3, int i, boolean z4) {
            return new DevicePlaybackCapabilities(z, z2, i, z3 ? CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"none", MediaPipelineBackendEngineManager.PictureModeConstants.PROPERTY_VALUE_CALIBRATED_CINEMATIC, MediaPipelineBackendEngineManager.PictureModeConstants.PROPERTY_VALUE_CALIBRATED_BROADCAST}) : CollectionsKt__CollectionsJVMKt.listOf("none"), z4);
        }

        @NotNull
        public final KSerializer<DevicePlaybackCapabilities> serializer() {
            return DevicePlaybackCapabilities$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{null, null, null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new DevicePlaybackCapabilities$$ExternalSyntheticLambda1()), null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new DevicePlaybackCapabilities$$ExternalSyntheticLambda2()), null, null};
    }

    public /* synthetic */ DevicePlaybackCapabilities(int i, boolean z, boolean z2, int i2, List list, boolean z3, List list2, boolean z4, boolean z5, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (i & 31)) {
            PluginExceptionsKt.throwMissingFieldException(i, 31, DevicePlaybackCapabilities$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.intraChunkSeekSupport = z;
        this.supportsVariableAspectRatio = z2;
        this.maxInstanceCount = i2;
        this.supportedPictureModes = list;
        this.supportsAudioVolume = z3;
        if ((i & 32) == 0) {
            this.supportedTracks = CollectionsKt__CollectionsJVMKt.listOf("AV");
        } else {
            this.supportedTracks = list2;
        }
        if ((i & 64) == 0) {
            this.supportsDynamicPlayerResize = true;
        } else {
            this.supportsDynamicPlayerResize = z4;
        }
        if ((i & 128) == 0) {
            this.supportsTrackDARUpdates = true;
        } else {
            this.supportsTrackDARUpdates = z5;
        }
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
        return new ArrayListSerializer(StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DevicePlaybackCapabilities copy$default(DevicePlaybackCapabilities devicePlaybackCapabilities, boolean z, boolean z2, int i, List list, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = devicePlaybackCapabilities.intraChunkSeekSupport;
        }
        if ((i2 & 2) != 0) {
            z2 = devicePlaybackCapabilities.supportsVariableAspectRatio;
        }
        if ((i2 & 4) != 0) {
            i = devicePlaybackCapabilities.maxInstanceCount;
        }
        if ((i2 & 8) != 0) {
            list = devicePlaybackCapabilities.supportedPictureModes;
        }
        if ((i2 & 16) != 0) {
            z3 = devicePlaybackCapabilities.supportsAudioVolume;
        }
        boolean z4 = z3;
        int i3 = i;
        return devicePlaybackCapabilities.copy(z, z2, i3, list, z4);
    }

    @JvmStatic
    @NotNull
    public static final DevicePlaybackCapabilities create(boolean z, boolean z2, boolean z3, int i, boolean z4) {
        return Companion.create(z, z2, z3, i, z4);
    }

    public static final Unit toJsonString$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.encodeDefaults = true;
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(DevicePlaybackCapabilities devicePlaybackCapabilities, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeBooleanElement(serialDescriptor, 0, devicePlaybackCapabilities.intraChunkSeekSupport);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 1, devicePlaybackCapabilities.supportsVariableAspectRatio);
        compositeEncoder.encodeIntElement(serialDescriptor, 2, devicePlaybackCapabilities.maxInstanceCount);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 3, lazyArr[3].getValue(), devicePlaybackCapabilities.supportedPictureModes);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 4, devicePlaybackCapabilities.supportsAudioVolume);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || !Intrinsics.areEqual(devicePlaybackCapabilities.supportedTracks, CollectionsKt__CollectionsJVMKt.listOf("AV"))) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 5, lazyArr[5].getValue(), devicePlaybackCapabilities.supportedTracks);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || !devicePlaybackCapabilities.supportsDynamicPlayerResize) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 6, devicePlaybackCapabilities.supportsDynamicPlayerResize);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) && devicePlaybackCapabilities.supportsTrackDARUpdates) {
            return;
        }
        compositeEncoder.encodeBooleanElement(serialDescriptor, 7, devicePlaybackCapabilities.supportsTrackDARUpdates);
    }

    public final boolean component1() {
        return this.intraChunkSeekSupport;
    }

    public final boolean component2() {
        return this.supportsVariableAspectRatio;
    }

    public final int component3() {
        return this.maxInstanceCount;
    }

    @NotNull
    public final List<String> component4() {
        return this.supportedPictureModes;
    }

    public final boolean component5() {
        return this.supportsAudioVolume;
    }

    @NotNull
    public final DevicePlaybackCapabilities copy(boolean z, boolean z2, int i, @NotNull List<String> supportedPictureModes, boolean z3) {
        Intrinsics.checkNotNullParameter(supportedPictureModes, "supportedPictureModes");
        return new DevicePlaybackCapabilities(z, z2, i, supportedPictureModes, z3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DevicePlaybackCapabilities)) {
            return false;
        }
        DevicePlaybackCapabilities devicePlaybackCapabilities = (DevicePlaybackCapabilities) obj;
        return this.intraChunkSeekSupport == devicePlaybackCapabilities.intraChunkSeekSupport && this.supportsVariableAspectRatio == devicePlaybackCapabilities.supportsVariableAspectRatio && this.maxInstanceCount == devicePlaybackCapabilities.maxInstanceCount && Intrinsics.areEqual(this.supportedPictureModes, devicePlaybackCapabilities.supportedPictureModes) && this.supportsAudioVolume == devicePlaybackCapabilities.supportsAudioVolume;
    }

    public final boolean getIntraChunkSeekSupport() {
        return this.intraChunkSeekSupport;
    }

    public final int getMaxInstanceCount() {
        return this.maxInstanceCount;
    }

    @NotNull
    public final List<String> getSupportedPictureModes() {
        return this.supportedPictureModes;
    }

    @NotNull
    public final List<String> getSupportedTracks() {
        return this.supportedTracks;
    }

    public final boolean getSupportsAudioVolume() {
        return this.supportsAudioVolume;
    }

    public final boolean getSupportsDynamicPlayerResize() {
        return this.supportsDynamicPlayerResize;
    }

    public final boolean getSupportsTrackDARUpdates() {
        return this.supportsTrackDARUpdates;
    }

    public final boolean getSupportsVariableAspectRatio() {
        return this.supportsVariableAspectRatio;
    }

    public int hashCode() {
        return MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsAudioVolume) + ((this.supportedPictureModes.hashCode() + ((((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsVariableAspectRatio) + (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.intraChunkSeekSupport) * 31)) * 31) + this.maxInstanceCount) * 31)) * 31);
    }

    @NotNull
    public final String toJsonString() {
        return JsonKt.Json$default(null, new DevicePlaybackCapabilities$$ExternalSyntheticLambda0(), 1, null).encodeToString(Companion.serializer(), this);
    }

    @NotNull
    public String toString() {
        return "DevicePlaybackCapabilities(intraChunkSeekSupport=" + this.intraChunkSeekSupport + ", supportsVariableAspectRatio=" + this.supportsVariableAspectRatio + ", maxInstanceCount=" + this.maxInstanceCount + ", supportedPictureModes=" + this.supportedPictureModes + ", supportsAudioVolume=" + this.supportsAudioVolume + ")";
    }

    public DevicePlaybackCapabilities(boolean z, boolean z2, int i, @NotNull List<String> supportedPictureModes, boolean z3) {
        Intrinsics.checkNotNullParameter(supportedPictureModes, "supportedPictureModes");
        this.intraChunkSeekSupport = z;
        this.supportsVariableAspectRatio = z2;
        this.maxInstanceCount = i;
        this.supportedPictureModes = supportedPictureModes;
        this.supportsAudioVolume = z3;
        this.supportedTracks = CollectionsKt__CollectionsJVMKt.listOf("AV");
        this.supportsDynamicPlayerResize = true;
        this.supportsTrackDARUpdates = true;
    }
}
