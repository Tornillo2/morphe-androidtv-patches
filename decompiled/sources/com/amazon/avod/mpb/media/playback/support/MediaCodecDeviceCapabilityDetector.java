package com.amazon.avod.mpb.media.playback.support;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.config.MPBInternalConfig;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumerator;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import com.amazon.avod.mpb.media.playback.render.ReceiverAudioCapabilities;
import com.amazon.avod.mpb.util.Preconditions2;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaCodecDeviceCapabilityDetector {

    @NotNull
    public final MediaCodecList mAllCodecs;

    @NotNull
    public final Context mAppContext;

    @NotNull
    public final MediaCodecEnumerator mCodecEnumerator;

    @NotNull
    public final MPBInternalConfig mConfig;

    @NotNull
    public final DevicePropertyProvider mPropertyProvider;

    @NotNull
    public final Lazy supportedHdrFormats$delegate;

    @VisibleForTesting
    public MediaCodecDeviceCapabilityDetector(@NotNull Context mAppContext, @NotNull MPBInternalConfig mConfig, @NotNull MediaCodecEnumerator mCodecEnumerator, @NotNull MediaCodecList mAllCodecs, @NotNull DevicePropertyProvider mPropertyProvider) {
        Intrinsics.checkNotNullParameter(mAppContext, "mAppContext");
        Intrinsics.checkNotNullParameter(mConfig, "mConfig");
        Intrinsics.checkNotNullParameter(mCodecEnumerator, "mCodecEnumerator");
        Intrinsics.checkNotNullParameter(mAllCodecs, "mAllCodecs");
        Intrinsics.checkNotNullParameter(mPropertyProvider, "mPropertyProvider");
        this.mAppContext = mAppContext;
        this.mConfig = mConfig;
        this.mCodecEnumerator = mCodecEnumerator;
        this.mAllCodecs = mAllCodecs;
        this.mPropertyProvider = mPropertyProvider;
        this.supportedHdrFormats$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DisplayModeAndToneMappingEvaluator.getSupportedHdrFormats(this.f$0.mAppContext);
            }
        });
    }

    @NotNull
    public final AudioDeviceInfo[] getConnectedOutputDevices() {
        AudioManager audioManager = (AudioManager) this.mAppContext.getSystemService("audio");
        if (audioManager == null || !Preconditions2.isSdkIntAtLeast(23)) {
            return new AudioDeviceInfo[0];
        }
        AudioDeviceInfo[] devices = audioManager.getDevices(2);
        Intrinsics.checkNotNullExpressionValue(devices, "getDevices(...)");
        return devices;
    }

    public final boolean getDeviceTunnelModeCapability(@NotNull String mimeType) {
        MediaCodecInfo mediaCodecInfo;
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        try {
            String strLookupDecoderWithMimeType = DisplayModeAndToneMappingEvaluator.lookupDecoderWithMimeType(this.mAllCodecs, mimeType, true);
            MediaCodecInfo[] codecInfos = this.mAllCodecs.getCodecInfos();
            if (strLookupDecoderWithMimeType != null && codecInfos != null) {
                Iterator it = ArrayIteratorKt.iterator(codecInfos);
                do {
                    ArrayIterator arrayIterator = (ArrayIterator) it;
                    if (arrayIterator.hasNext()) {
                        mediaCodecInfo = (MediaCodecInfo) arrayIterator.next();
                    } else {
                        MpbLog.warnf("couldn't get mediaCodecInfo for given decoder, unable to detect tunnel mode support, will retry", new Object[0]);
                    }
                } while (!strLookupDecoderWithMimeType.equals(mediaCodecInfo.getName()));
                boolean zIsFeatureSupported = mediaCodecInfo.getCapabilitiesForType(mimeType).isFeatureSupported("tunneled-playback");
                MpbLog.warnf("Analyzed tunnel mode support, mime: %s -> %s", mimeType, Boolean.valueOf(zIsFeatureSupported));
                return zIsFeatureSupported;
            }
            MpbLog.warnf("Unable to detect tunnel mode support, secureDecoderName %s, allMediaCodecInfo %s, will retry", strLookupDecoderWithMimeType == null ? AbstractJsonLexerKt.NULL : "non-null", codecInfos == null ? AbstractJsonLexerKt.NULL : "non-null");
        } catch (MediaCodec.CodecException e) {
            MpbLog.exceptionf(e, "Unable to detect tunnel mode support, mime: %s,", mimeType);
        } catch (IllegalArgumentException e2) {
            MpbLog.exceptionf(e2, "Unable to detect tunnel mode support, mime: %s,", mimeType);
        }
        return false;
    }

    @NotNull
    public final ImmutableList<HdrFormat> getSupportedHdrFormats() {
        return (ImmutableList) this.supportedHdrFormats$delegate.getValue();
    }

    public final boolean isBluetoothAudioOutputDeviceConnected() {
        if (Preconditions2.isSdkIntAtLeast(23)) {
            for (AudioDeviceInfo audioDeviceInfo : getConnectedOutputDevices()) {
                if (audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 23) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isDolbyDigitalPlusSupported() {
        if (!isDolbyOverBluetoothSupported()) {
            return false;
        }
        if (this.mCodecEnumerator.getSupportedCodec(this.mConfig.getDolbyDigitalPlusMimeTypes()) != null) {
            return true;
        }
        return isDolbyPassthroughSupported();
    }

    public final boolean isDolbyOverBluetoothSupported() {
        if (this.mConfig.getDolbyOverBluetoothAudioOutputSupported()) {
            return true;
        }
        return !isBluetoothAudioOutputDeviceConnected();
    }

    public final boolean isDolbyPassthroughSupported() {
        return this.mPropertyProvider.isOpticalOutputEnabled() ? this.mConfig.getOpticalAudioPassthroughEnabled() : this.mConfig.getHdmiAudioPassthroughEnabled() && ReceiverAudioCapabilities.Companion.getCapabilities(this.mAppContext).supportsEncoding(6);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaCodecDeviceCapabilityDetector(@NotNull Context appContext, @NotNull DevicePropertyProvider propertyProvider) {
        this(appContext, DefaultMPBInternalConfig.INSTANCE, MediaCodecEnumeratorImpl.Companion.getInstance(), new MediaCodecList(1), propertyProvider);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(propertyProvider, "propertyProvider");
    }
}
