package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.deviceproperties.DecoderCapabilitiesProvider;
import com.amazon.livingroom.deviceproperties.DisplayPropertiesProvider;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class VideoCapabilitiesProvider {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int FULL_HD_HEIGHT = 1080;
    public static final int FULL_HD_WIDTH = 1920;
    public static final int ULTRA_HD_HEIGHT = 2160;
    public static final int ULTRA_HD_WIDTH = 3840;

    @NotNull
    public final DecoderCapabilitiesProvider decoderCapabilitiesProvider;

    @NotNull
    public final DisplayPropertiesProvider displayPropertiesProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public VideoCapabilitiesProvider(@NotNull DisplayPropertiesProvider displayPropertiesProvider, @NotNull DecoderCapabilitiesProvider decoderCapabilitiesProvider) {
        Intrinsics.checkNotNullParameter(displayPropertiesProvider, "displayPropertiesProvider");
        Intrinsics.checkNotNullParameter(decoderCapabilitiesProvider, "decoderCapabilitiesProvider");
        this.displayPropertiesProvider = displayPropertiesProvider;
        this.decoderCapabilitiesProvider = decoderCapabilitiesProvider;
    }

    public final boolean checkUhdDependentSupport(DeviceProperties deviceProperties, boolean z, boolean z2) {
        return ((Boolean) deviceProperties.get(DeviceProperties.SUPPORTS_UHD)).booleanValue() ? z2 : z;
    }

    public final int getMaxVideoHeight(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        DisplayPropertiesProvider.DisplayProperties currentDisplayProperties = this.displayPropertiesProvider.getCurrentDisplayProperties();
        return !((Boolean) otherProperties.get(DeviceProperties.SUPPORTS_UHD)).booleanValue() ? Math.min(currentDisplayProperties.maxVideoHeight, FULL_HD_HEIGHT) : currentDisplayProperties.maxVideoHeight;
    }

    public final int getMaxVideoWidth(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        DisplayPropertiesProvider.DisplayProperties currentDisplayProperties = this.displayPropertiesProvider.getCurrentDisplayProperties();
        return !((Boolean) otherProperties.get(DeviceProperties.SUPPORTS_UHD)).booleanValue() ? Math.min(currentDisplayProperties.maxVideoWidth, FULL_HD_WIDTH) : currentDisplayProperties.maxVideoWidth;
    }

    public final boolean hasExternalOutput() {
        return this.displayPropertiesProvider.getCurrentDisplayProperties().hasExternalOutput;
    }

    public final boolean isHdmiAudioPluggedIn() {
        return this.displayPropertiesProvider.getCurrentDisplayProperties().hdmiAudioPluggedIn;
    }

    public final boolean supportsAv1Main10(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        DecoderCapabilitiesProvider.DecoderCapabilities decoderCapabilities = this.decoderCapabilitiesProvider.getDecoderCapabilities(otherProperties);
        return checkUhdDependentSupport(otherProperties, decoderCapabilities.supportsAv1Main10L4, decoderCapabilities.supportsAv1Main10L5);
    }

    public final boolean supportsDolbyVision(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        return this.displayPropertiesProvider.getCurrentDisplayProperties().supportsDolbyVision && supportsDolbyVisionDvheStn(otherProperties);
    }

    public final boolean supportsDolbyVisionDvheStn(DeviceProperties deviceProperties) {
        DecoderCapabilitiesProvider.DecoderCapabilities decoderCapabilities = this.decoderCapabilitiesProvider.getDecoderCapabilities(deviceProperties);
        return checkUhdDependentSupport(deviceProperties, decoderCapabilities.supportsDolbyVisionDvheStnFhd30, decoderCapabilities.supportsDolbyVisionDvheStnUhd30);
    }

    public final boolean supportsHdcp() {
        return this.displayPropertiesProvider.getCurrentDisplayProperties().supportsHdcp;
    }

    public final boolean supportsHdr10() {
        return this.displayPropertiesProvider.getCurrentDisplayProperties().supportsHdr10;
    }

    public final boolean supportsHdr10Plus() {
        return this.displayPropertiesProvider.getCurrentDisplayProperties().supportsHdr10Plus;
    }

    public final boolean supportsHevc(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        return this.decoderCapabilitiesProvider.getDecoderCapabilities(otherProperties).supportsHevcMainL4;
    }

    public final boolean supportsHevcMain10(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        DecoderCapabilitiesProvider.DecoderCapabilities decoderCapabilities = this.decoderCapabilitiesProvider.getDecoderCapabilities(otherProperties);
        return checkUhdDependentSupport(otherProperties, decoderCapabilities.supportsHevcMain10L4, decoderCapabilities.supportsHevcMain10L5);
    }

    public final boolean supportsUhd(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        DisplayPropertiesProvider.DisplayProperties currentDisplayProperties = this.displayPropertiesProvider.getCurrentDisplayProperties();
        if (currentDisplayProperties.maxVideoWidth < 3840 || currentDisplayProperties.maxVideoHeight < 2160) {
            return false;
        }
        return this.decoderCapabilitiesProvider.getDecoderCapabilities(otherProperties).supportsHevcMainL5;
    }
}
