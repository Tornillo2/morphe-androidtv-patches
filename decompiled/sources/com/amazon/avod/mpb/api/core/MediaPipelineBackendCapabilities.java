package com.amazon.avod.mpb.api.core;

import com.amazon.ion.impl.IonUTF8;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class MediaPipelineBackendCapabilities {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();
    public final boolean intraChunkSeekSupport;
    public final int maxInstanceCount;

    @NotNull
    public final List<PictureMode> supportedPictureModes;

    @NotNull
    public final Set<TrackConfiguration> supportedTracks;
    public final boolean supportsAudioMixing;
    public final boolean supportsAudioMuting;
    public final boolean supportsAudioVolume;
    public final boolean supportsDynamicPlayerResize;
    public final boolean supportsTrackDARUpdates;
    public final boolean supportsVariableAspectRatio;
    public final boolean supportsViewportAnimation;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<MediaPipelineBackendCapabilities> serializer() {
            return MediaPipelineBackendCapabilities$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new MediaPipelineBackendCapabilities$$ExternalSyntheticLambda1()), null, null, null, null, null, null, null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new MediaPipelineBackendCapabilities$$ExternalSyntheticLambda2()), null};
    }

    public /* synthetic */ MediaPipelineBackendCapabilities(int i, boolean z, List list, boolean z2, boolean z3, boolean z4, int i2, boolean z5, boolean z6, boolean z7, Set set, boolean z8, SerializationConstructorMarker serializationConstructorMarker) {
        if (2047 != (i & IonUTF8.UNICODE_MAX_TWO_BYTE_SCALAR)) {
            PluginExceptionsKt.throwMissingFieldException(i, IonUTF8.UNICODE_MAX_TWO_BYTE_SCALAR, MediaPipelineBackendCapabilities$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.intraChunkSeekSupport = z;
        this.supportedPictureModes = list;
        this.supportsVariableAspectRatio = z2;
        this.supportsDynamicPlayerResize = z3;
        this.supportsViewportAnimation = z4;
        this.maxInstanceCount = i2;
        this.supportsAudioVolume = z5;
        this.supportsAudioMuting = z6;
        this.supportsAudioMixing = z7;
        this.supportedTracks = set;
        this.supportsTrackDARUpdates = z8;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(EnumsKt.createAnnotatedEnumSerializer("com.amazon.avod.mpb.api.core.PictureMode", PictureMode.values(), new String[]{"none", "filmmaker"}, new Annotation[][]{null, null}, null));
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
        return new LinkedHashSetSerializer(EnumsKt.createSimpleEnumSerializer("com.amazon.avod.mpb.api.core.TrackConfiguration", TrackConfiguration.values()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaPipelineBackendCapabilities copy$default(MediaPipelineBackendCapabilities mediaPipelineBackendCapabilities, boolean z, List list, boolean z2, boolean z3, boolean z4, int i, boolean z5, boolean z6, boolean z7, Set set, boolean z8, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = mediaPipelineBackendCapabilities.intraChunkSeekSupport;
        }
        if ((i2 & 2) != 0) {
            list = mediaPipelineBackendCapabilities.supportedPictureModes;
        }
        if ((i2 & 4) != 0) {
            z2 = mediaPipelineBackendCapabilities.supportsVariableAspectRatio;
        }
        if ((i2 & 8) != 0) {
            z3 = mediaPipelineBackendCapabilities.supportsDynamicPlayerResize;
        }
        if ((i2 & 16) != 0) {
            z4 = mediaPipelineBackendCapabilities.supportsViewportAnimation;
        }
        if ((i2 & 32) != 0) {
            i = mediaPipelineBackendCapabilities.maxInstanceCount;
        }
        if ((i2 & 64) != 0) {
            z5 = mediaPipelineBackendCapabilities.supportsAudioVolume;
        }
        if ((i2 & 128) != 0) {
            z6 = mediaPipelineBackendCapabilities.supportsAudioMuting;
        }
        if ((i2 & 256) != 0) {
            z7 = mediaPipelineBackendCapabilities.supportsAudioMixing;
        }
        if ((i2 & 512) != 0) {
            set = mediaPipelineBackendCapabilities.supportedTracks;
        }
        if ((i2 & 1024) != 0) {
            z8 = mediaPipelineBackendCapabilities.supportsTrackDARUpdates;
        }
        Set set2 = set;
        boolean z9 = z8;
        boolean z10 = z6;
        boolean z11 = z7;
        int i3 = i;
        boolean z12 = z5;
        boolean z13 = z4;
        boolean z14 = z2;
        return mediaPipelineBackendCapabilities.copy(z, list, z14, z3, z13, i3, z12, z10, z11, set2, z9);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$core_mpb_release(MediaPipelineBackendCapabilities mediaPipelineBackendCapabilities, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeBooleanElement(serialDescriptor, 0, mediaPipelineBackendCapabilities.intraChunkSeekSupport);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), mediaPipelineBackendCapabilities.supportedPictureModes);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 2, mediaPipelineBackendCapabilities.supportsVariableAspectRatio);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 3, mediaPipelineBackendCapabilities.supportsDynamicPlayerResize);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 4, mediaPipelineBackendCapabilities.supportsViewportAnimation);
        compositeEncoder.encodeIntElement(serialDescriptor, 5, mediaPipelineBackendCapabilities.maxInstanceCount);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 6, mediaPipelineBackendCapabilities.supportsAudioVolume);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 7, mediaPipelineBackendCapabilities.supportsAudioMuting);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 8, mediaPipelineBackendCapabilities.supportsAudioMixing);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 9, lazyArr[9].getValue(), mediaPipelineBackendCapabilities.supportedTracks);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 10, mediaPipelineBackendCapabilities.supportsTrackDARUpdates);
    }

    public final boolean component1() {
        return this.intraChunkSeekSupport;
    }

    @NotNull
    public final Set<TrackConfiguration> component10() {
        return this.supportedTracks;
    }

    public final boolean component11() {
        return this.supportsTrackDARUpdates;
    }

    @NotNull
    public final List<PictureMode> component2() {
        return this.supportedPictureModes;
    }

    public final boolean component3() {
        return this.supportsVariableAspectRatio;
    }

    public final boolean component4() {
        return this.supportsDynamicPlayerResize;
    }

    public final boolean component5() {
        return this.supportsViewportAnimation;
    }

    public final int component6() {
        return this.maxInstanceCount;
    }

    public final boolean component7() {
        return this.supportsAudioVolume;
    }

    public final boolean component8() {
        return this.supportsAudioMuting;
    }

    public final boolean component9() {
        return this.supportsAudioMixing;
    }

    @NotNull
    public final MediaPipelineBackendCapabilities copy(boolean z, @NotNull List<? extends PictureMode> supportedPictureModes, boolean z2, boolean z3, boolean z4, int i, boolean z5, boolean z6, boolean z7, @NotNull Set<? extends TrackConfiguration> supportedTracks, boolean z8) {
        Intrinsics.checkNotNullParameter(supportedPictureModes, "supportedPictureModes");
        Intrinsics.checkNotNullParameter(supportedTracks, "supportedTracks");
        return new MediaPipelineBackendCapabilities(z, supportedPictureModes, z2, z3, z4, i, z5, z6, z7, supportedTracks, z8);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPipelineBackendCapabilities)) {
            return false;
        }
        MediaPipelineBackendCapabilities mediaPipelineBackendCapabilities = (MediaPipelineBackendCapabilities) obj;
        return this.intraChunkSeekSupport == mediaPipelineBackendCapabilities.intraChunkSeekSupport && Intrinsics.areEqual(this.supportedPictureModes, mediaPipelineBackendCapabilities.supportedPictureModes) && this.supportsVariableAspectRatio == mediaPipelineBackendCapabilities.supportsVariableAspectRatio && this.supportsDynamicPlayerResize == mediaPipelineBackendCapabilities.supportsDynamicPlayerResize && this.supportsViewportAnimation == mediaPipelineBackendCapabilities.supportsViewportAnimation && this.maxInstanceCount == mediaPipelineBackendCapabilities.maxInstanceCount && this.supportsAudioVolume == mediaPipelineBackendCapabilities.supportsAudioVolume && this.supportsAudioMuting == mediaPipelineBackendCapabilities.supportsAudioMuting && this.supportsAudioMixing == mediaPipelineBackendCapabilities.supportsAudioMixing && Intrinsics.areEqual(this.supportedTracks, mediaPipelineBackendCapabilities.supportedTracks) && this.supportsTrackDARUpdates == mediaPipelineBackendCapabilities.supportsTrackDARUpdates;
    }

    public final boolean getIntraChunkSeekSupport() {
        return this.intraChunkSeekSupport;
    }

    public final int getMaxInstanceCount() {
        return this.maxInstanceCount;
    }

    @NotNull
    public final List<PictureMode> getSupportedPictureModes() {
        return this.supportedPictureModes;
    }

    @NotNull
    public final Set<TrackConfiguration> getSupportedTracks() {
        return this.supportedTracks;
    }

    public final boolean getSupportsAudioMixing() {
        return this.supportsAudioMixing;
    }

    public final boolean getSupportsAudioMuting() {
        return this.supportsAudioMuting;
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

    public final boolean getSupportsViewportAnimation() {
        return this.supportsViewportAnimation;
    }

    public int hashCode() {
        return MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsTrackDARUpdates) + ((this.supportedTracks.hashCode() + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsAudioMixing) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsAudioMuting) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsAudioVolume) + ((((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsViewportAnimation) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsDynamicPlayerResize) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.supportsVariableAspectRatio) + ((this.supportedPictureModes.hashCode() + (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.intraChunkSeekSupport) * 31)) * 31)) * 31)) * 31)) * 31) + this.maxInstanceCount) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        return "MediaPipelineBackendCapabilities(intraChunkSeekSupport=" + this.intraChunkSeekSupport + ", supportedPictureModes=" + this.supportedPictureModes + ", supportsVariableAspectRatio=" + this.supportsVariableAspectRatio + ", supportsDynamicPlayerResize=" + this.supportsDynamicPlayerResize + ", supportsViewportAnimation=" + this.supportsViewportAnimation + ", maxInstanceCount=" + this.maxInstanceCount + ", supportsAudioVolume=" + this.supportsAudioVolume + ", supportsAudioMuting=" + this.supportsAudioMuting + ", supportsAudioMixing=" + this.supportsAudioMixing + ", supportedTracks=" + this.supportedTracks + ", supportsTrackDARUpdates=" + this.supportsTrackDARUpdates + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MediaPipelineBackendCapabilities(boolean z, @NotNull List<? extends PictureMode> supportedPictureModes, boolean z2, boolean z3, boolean z4, int i, boolean z5, boolean z6, boolean z7, @NotNull Set<? extends TrackConfiguration> supportedTracks, boolean z8) {
        Intrinsics.checkNotNullParameter(supportedPictureModes, "supportedPictureModes");
        Intrinsics.checkNotNullParameter(supportedTracks, "supportedTracks");
        this.intraChunkSeekSupport = z;
        this.supportedPictureModes = supportedPictureModes;
        this.supportsVariableAspectRatio = z2;
        this.supportsDynamicPlayerResize = z3;
        this.supportsViewportAnimation = z4;
        this.maxInstanceCount = i;
        this.supportsAudioVolume = z5;
        this.supportsAudioMuting = z6;
        this.supportsAudioMixing = z7;
        this.supportedTracks = supportedTracks;
        this.supportsTrackDARUpdates = z8;
    }
}
