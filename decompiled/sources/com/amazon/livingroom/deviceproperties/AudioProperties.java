package com.amazon.livingroom.deviceproperties;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.AudioInfoReceiver;
import com.amazon.livingroom.mediapipelinebackend.AvAudioDeviceInfo;
import com.amazon.livingroom.mediapipelinebackend.Constants;
import com.amazon.reporting.Log;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nAudioProperties.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AudioProperties.kt\ncom/amazon/livingroom/deviceproperties/AudioProperties\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,203:1\n12637#2,2:204\n1310#2,2:206\n*S KotlinDebug\n*F\n+ 1 AudioProperties.kt\ncom/amazon/livingroom/deviceproperties/AudioProperties\n*L\n120#1:204,2\n159#1:206,2\n*E\n"})
public final class AudioProperties {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int EAC3_TEST_SAMPLE_RATE = 48000;

    @NotNull
    public static final String LOG_TAG = "AudioProperties";

    @NotNull
    public static final String NRDP_EXTERNAL_SURROUND_SOUND_ENABLED = "nrdp_external_surround_sound_enabled";

    @NotNull
    public final AudioInfoReceiver audioInfoReceiver;

    @NotNull
    public final AudioManager audioManager;

    @NotNull
    public final Context context;

    @NotNull
    public final Lazy hasOnDeviceEac3Decoder$delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final Pair<AudioFormat, AudioAttributes> buildAudioFormatAndAttributes(int i) {
            return new Pair<>(new AudioFormat.Builder().setChannelMask(252).setEncoding(i).setSampleRate(48000).build(), new AudioAttributes.Builder().setUsage(1).setContentType(3).build());
        }

        @RequiresApi(api = 23)
        @SuppressLint({"InlinedApi"})
        public final boolean isBluetoothAudioDeviceType(int i) {
            return i == 7 || i == 8 || i == 26 || i == 27 || i == 30;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public AudioProperties(@ApplicationContext @NotNull Context context, @NotNull AudioManager audioManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        this.context = context;
        this.audioManager = audioManager;
        this.audioInfoReceiver = AudioInfoReceiver.registerNewInstance(context);
        this.hasOnDeviceEac3Decoder$delegate = LazyKt__LazyJVMKt.lazy(new AudioProperties$$ExternalSyntheticLambda0());
    }

    public static final boolean hasOnDeviceEac3Decoder_delegate$lambda$0() {
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/eac3", 48000, 6);
        Intrinsics.checkNotNullExpressionValue(mediaFormatCreateAudioFormat, "createAudioFormat(...)");
        try {
            return new MediaCodecList(1).findDecoderForFormat(mediaFormatCreateAudioFormat) != null;
        } catch (IllegalArgumentException e) {
            Log.w(LOG_TAG, "E-AC3 format reported invalid", e);
            return false;
        }
    }

    public final boolean getHasOnDeviceEac3Decoder() {
        return ((Boolean) this.hasOnDeviceEac3Decoder$delegate.getValue()).booleanValue();
    }

    public final boolean getSupportsEac3PlaybackRateAdjustment(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        Object obj = otherProperties.get(DeviceProperties.SUPPORTS_ON_DEVICE_EAC3_DECODE);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    public final boolean getSupportsOnDeviceEac3Decode() {
        return getHasOnDeviceEac3Decoder() && supportsSurroundOutput(2);
    }

    public final boolean getSupportsSurroundSoundEAC3(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        if (((Boolean) otherProperties.get(DeviceProperties.SUPPORTS_EAC3_PASSTHROUGH)).booleanValue()) {
            return true;
        }
        Object obj = otherProperties.get(DeviceProperties.SUPPORTS_ON_DEVICE_EAC3_DECODE);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    @RequiresApi(api = 23)
    public final boolean isBluetoothAudioConnected() {
        AudioDeviceInfo audioDeviceInfo;
        AudioDeviceInfo[] devices = this.audioManager.getDevices(2);
        Intrinsics.checkNotNull(devices);
        int length = devices.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                audioDeviceInfo = null;
                break;
            }
            audioDeviceInfo = devices[i];
            if (Companion.isBluetoothAudioDeviceType(audioDeviceInfo.getType())) {
                break;
            }
            i++;
        }
        return audioDeviceInfo != null;
    }

    public final boolean isEac3PassthroughOnly(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        return !((Boolean) otherProperties.get(DeviceProperties.SUPPORTS_ON_DEVICE_EAC3_DECODE)).booleanValue();
    }

    public final boolean isExternalSoundSystemSelected() {
        String str = Build.MANUFACTURER;
        if (!StringsKt__StringsJVMKt.equals(str, Constants.MANUFACTURERS.SONY, true) && !StringsKt__StringsJVMKt.equals(str, "Philips", true)) {
            return false;
        }
        if (!isSystemSettingEnabled(NRDP_EXTERNAL_SURROUND_SOUND_ENABLED)) {
            String upperCase = NRDP_EXTERNAL_SURROUND_SOUND_ENABLED.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            if (!isSystemSettingEnabled(upperCase)) {
                Log.i(LOG_TAG, str + " device and audio surround disabled");
                return false;
            }
        }
        Log.i(LOG_TAG, str + " device and audio surround enabled");
        return true;
    }

    public final boolean isSystemSettingEnabled(String str) {
        return Settings.Global.getInt(this.context.getContentResolver(), str, 0) != 0;
    }

    public final boolean supportsEac3Passthrough(@NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        return supportsSurroundOutput(6);
    }

    public final boolean supportsSurroundOutput(int i) {
        if (isExternalSoundSystemSelected()) {
            return true;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23 || !isBluetoothAudioConnected()) {
            AvAudioDeviceInfo audioDeviceInfo = this.audioInfoReceiver.getAudioDeviceInfo();
            int[] iArr = audioDeviceInfo.encodings;
            Intrinsics.checkNotNullExpressionValue(iArr, "getEncodings(...)");
            if (ArraysKt___ArraysKt.contains(iArr, i) && audioDeviceInfo.channels >= 6) {
                return true;
            }
            if (i2 >= 23) {
                AudioDeviceInfo[] devices = this.audioManager.getDevices(2);
                Intrinsics.checkNotNullExpressionValue(devices, "getDevices(...)");
                for (AudioDeviceInfo audioDeviceInfo2 : devices) {
                    int[] encodings = audioDeviceInfo2.getEncodings();
                    Intrinsics.checkNotNullExpressionValue(encodings, "getEncodings(...)");
                    if (ArraysKt___ArraysKt.contains(encodings, i)) {
                        int[] channelMasks = audioDeviceInfo2.getChannelMasks();
                        Intrinsics.checkNotNullExpressionValue(channelMasks, "getChannelMasks(...)");
                        if (ArraysKt___ArraysKt.contains(channelMasks, 252)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
