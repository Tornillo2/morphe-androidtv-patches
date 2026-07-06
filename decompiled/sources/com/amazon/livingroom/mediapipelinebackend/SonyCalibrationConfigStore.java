package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SonyCalibrationConfigStore {

    @NotNull
    public static final SonyCalibrationConfigStore INSTANCE = new SonyCalibrationConfigStore();

    @NotNull
    public static final Map<Config, DeviceProperties.Property<String>> configDevicePropertiesMap;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Config {
        public final int calibratedMode;

        @NotNull
        public final String panelType;

        @NotNull
        public final String signalType;

        public Config(@NotNull String panelType, @NotNull String signalType, int i) {
            Intrinsics.checkNotNullParameter(panelType, "panelType");
            Intrinsics.checkNotNullParameter(signalType, "signalType");
            this.panelType = panelType;
            this.signalType = signalType;
            this.calibratedMode = i;
        }

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = config.panelType;
            }
            if ((i2 & 2) != 0) {
                str2 = config.signalType;
            }
            if ((i2 & 4) != 0) {
                i = config.calibratedMode;
            }
            return config.copy(str, str2, i);
        }

        public final String component1() {
            return this.panelType;
        }

        public final String component2() {
            return this.signalType;
        }

        public final int component3() {
            return this.calibratedMode;
        }

        @NotNull
        public final Config copy(@NotNull String panelType, @NotNull String signalType, int i) {
            Intrinsics.checkNotNullParameter(panelType, "panelType");
            Intrinsics.checkNotNullParameter(signalType, "signalType");
            return new Config(panelType, signalType, i);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Config)) {
                return false;
            }
            Config config = (Config) obj;
            return Intrinsics.areEqual(this.panelType, config.panelType) && Intrinsics.areEqual(this.signalType, config.signalType) && this.calibratedMode == config.calibratedMode;
        }

        public int hashCode() {
            return DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.signalType, this.panelType.hashCode() * 31, 31) + this.calibratedMode;
        }

        @NotNull
        public String toString() {
            String str = this.panelType;
            String str2 = this.signalType;
            return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(TrackGroup$$ExternalSyntheticOutline0.m("Config(panelType=", str, ", signalType=", str2, ", calibratedMode="), this.calibratedMode, ")");
        }
    }

    static {
        Pair pair = new Pair(new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.SDR, 3), DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_BROADCAST);
        Pair pair2 = new Pair(new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.SDR, 4), DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_CINEMATIC);
        Config config = new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.HDR, 3);
        DeviceProperties.Property<String> property = DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_BROADCAST;
        Pair pair3 = new Pair(config, property);
        Config config2 = new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.HDR, 4);
        DeviceProperties.Property<String> property2 = DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_CINEMATIC;
        Pair pair4 = new Pair(config2, property2);
        Pair pair5 = new Pair(new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.DOLBY_VISION, 3), property);
        Pair pair6 = new Pair(new Config(PictureQualityController.PANEL_TYPE_LCD, PictureQualityController.SignalType.DOLBY_VISION, 4), property2);
        Pair pair7 = new Pair(new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.SDR, 3), DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_BROADCAST);
        Pair pair8 = new Pair(new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.SDR, 4), DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_CINEMATIC);
        Config config3 = new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.HDR, 3);
        DeviceProperties.Property<String> property3 = DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_BROADCAST;
        Pair pair9 = new Pair(config3, property3);
        Config config4 = new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.HDR, 4);
        DeviceProperties.Property<String> property4 = DeviceProperties.SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_CINEMATIC;
        configDevicePropertiesMap = MapsKt__MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, new Pair(config4, property4), new Pair(new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.DOLBY_VISION, 3), property3), new Pair(new Config(PictureQualityController.PANEL_TYPE_OLED, PictureQualityController.SignalType.DOLBY_VISION, 4), property4));
    }

    @NotNull
    public final Map<Config, DeviceProperties.Property<String>> getConfigDevicePropertiesMap() {
        return configDevicePropertiesMap;
    }
}
