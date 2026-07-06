package com.amazon.avod.mpb.media.playback.mediacodec;

import android.media.MediaFormat;
import com.amazon.avod.mpb.api.core.DevicePropertyProvider;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.media.playback.support.MediaCodecDeviceCapabilityDetector;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nTunneledPlaybackEvaluator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TunneledPlaybackEvaluator.kt\ncom/amazon/avod/mpb/media/playback/mediacodec/TunneledPlaybackEvaluator\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,46:1\n384#2,7:47\n*S KotlinDebug\n*F\n+ 1 TunneledPlaybackEvaluator.kt\ncom/amazon/avod/mpb/media/playback/mediacodec/TunneledPlaybackEvaluator\n*L\n42#1:47,7\n*E\n"})
public final class TunneledPlaybackEvaluator {

    @NotNull
    public final MediaCodecDeviceCapabilityDetector deviceCapabilityDetector;

    @NotNull
    public final DevicePropertyProvider devicePropertyProvider;

    @NotNull
    public final Map<String, Boolean> deviceSupportByVideoType;

    public TunneledPlaybackEvaluator(@NotNull MediaCodecDeviceCapabilityDetector deviceCapabilityDetector, @NotNull DevicePropertyProvider devicePropertyProvider) {
        Intrinsics.checkNotNullParameter(deviceCapabilityDetector, "deviceCapabilityDetector");
        Intrinsics.checkNotNullParameter(devicePropertyProvider, "devicePropertyProvider");
        this.deviceCapabilityDetector = deviceCapabilityDetector;
        this.devicePropertyProvider = devicePropertyProvider;
        this.deviceSupportByVideoType = new LinkedHashMap();
    }

    public final boolean deviceSupportsTunnelingFor(String str) {
        Map<String, Boolean> map = this.deviceSupportByVideoType;
        Boolean boolValueOf = map.get(str);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(this.deviceCapabilityDetector.getDeviceTunnelModeCapability(str));
            map.put(str, boolValueOf);
        }
        return boolValueOf.booleanValue();
    }

    public final boolean evaluateTunneledPlaybackCapability(@NotNull MediaFormat videoFormat) {
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        String string = videoFormat.getString("mime");
        Intrinsics.checkNotNull(string);
        boolean zDeviceSupportsTunnelingFor = deviceSupportsTunnelingFor(string);
        boolean zIsBluetoothAudioOutputDeviceConnected = this.deviceCapabilityDetector.isBluetoothAudioOutputDeviceConnected();
        boolean z = this.devicePropertyProvider.isTunnelModeEnabled() && zDeviceSupportsTunnelingFor && (this.devicePropertyProvider.isTunnelModeOverBtEnabled() || !zIsBluetoothAudioOutputDeviceConnected);
        MpbLog.logf("Tunneled " + z + "(Cfg:" + this.devicePropertyProvider.isTunnelModeEnabled() + "/Cap:" + zDeviceSupportsTunnelingFor + ", BTSink:" + zIsBluetoothAudioOutputDeviceConnected + "/BTCfg:" + this.devicePropertyProvider.isTunnelModeOverBtEnabled() + ")", new Object[0]);
        return z;
    }
}
