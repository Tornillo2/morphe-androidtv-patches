package com.sony.dtv.picturequalitycontrol;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class PictureQualityController {
    public static final int AUTO_LOCAL_DIMMING_HIGH = 3;
    public static final int AUTO_LOCAL_DIMMING_LOW = 1;
    public static final int AUTO_LOCAL_DIMMING_MEDIUM = 2;
    public static final int AUTO_LOCAL_DIMMING_OFF = 0;
    public static final int CLEARNESS_LIST_HIGH = 1;
    public static final int CLEARNESS_LIST_LOW = 0;
    public static final int COLOR_TEMPERATURE_COOL = 0;
    public static final int COLOR_TEMPERATURE_NEUTRAL = 1;
    public static final int COLOR_TEMPERATURE_WARM = 2;
    public static final int DIGITAL_NOISE_REDUCTION_AUTO = 4;
    public static final int DIGITAL_NOISE_REDUCTION_HIGH = 3;
    public static final int DIGITAL_NOISE_REDUCTION_LOW = 1;
    public static final int DIGITAL_NOISE_REDUCTION_MEDIUM = 2;
    public static final int DIGITAL_NOISE_REDUCTION_OFF = 0;
    public static final int FILM_MODE_AUTO = 4;
    public static final int FILM_MODE_HIGH = 3;
    public static final int FILM_MODE_LOW = 1;
    public static final int FILM_MODE_MEDIUM = 2;
    public static final int FILM_MODE_OFF = 0;
    public static final int MOTION_FLOW_AUTO = 7;
    public static final int MOTION_FLOW_CLEAR = 3;
    public static final int MOTION_FLOW_CUSTOM = 6;
    public static final int MOTION_FLOW_HIGH = 2;
    public static final int MOTION_FLOW_IMPULSE = 4;
    public static final int MOTION_FLOW_OFF = 0;
    public static final int MOTION_FLOW_STANDARD = 1;
    public static final int MOTION_FLOW_TRUE_CINEMA = 5;
    public static final String PANEL_TYPE_LCD = "LCD";
    public static final String PANEL_TYPE_OLED = "OLED";
    public static final int RANDOM_NOISE_REDUCTION_AUTO = 4;
    public static final int RANDOM_NOISE_REDUCTION_HIGH = 3;
    public static final int RANDOM_NOISE_REDUCTION_LOW = 1;
    public static final int RANDOM_NOISE_REDUCTION_MEDIUM = 2;
    public static final int RANDOM_NOISE_REDUCTION_OFF = 0;
    public static final int REALITY_CREATION_AUTO = 2;
    public static final int REALITY_CREATION_MANUAL = 1;
    public static final int REALITY_CREATION_OFF = 0;
    public static final int SMOOTH_GRADATION_HIGH = 3;
    public static final int SMOOTH_GRADATION_LOW = 1;
    public static final int SMOOTH_GRADATION_MEDIUM = 2;
    public static final int SMOOTH_GRADATION_OFF = 0;
    private static final String VERSION = "1.3.0";
    public static final int XTENDED_DYNAMIC_RANGE_HIGH = 3;
    public static final int XTENDED_DYNAMIC_RANGE_LOW = 1;
    public static final int XTENDED_DYNAMIC_RANGE_MEDIUM = 2;
    public static final int XTENDED_DYNAMIC_RANGE_OFF = 0;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CalibratedModeListener {
        void onChanged(boolean z);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DataTypeList {
        public static final String DEFAULT = "Default";
        public static final String ITEMS = "Items";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DataTypeRange {
        public static final String DEFAULT = "Default";
        public static final String MAX = "Max";
        public static final String MIN = "Min";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DataTypeToggle {
        public static final String DEFAULT = "Default";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnConnectedListener {
        void onConnected();

        void onConnectionFailed();

        void onDisconnected();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Result {
        public static final int FATAL = -1;
        public static final int NOT_AVAILABLE = -2;
        public static final int SUCCESS = 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SettingType {
        public static final String AMBIENT_LIGHT_SENSOR = "AmbientLightSensor";
        public static final String AUTO_LOCAL_DIMMING = "AutoLocalDimming";
        public static final String AUTO_LUMINANCE_LEVEL = "AutoLuminanceLevel";
        public static final String AUTO_TONE_CURVE = "AutoToneCurve";
        public static final String AUTO_WHITE_BALANCE = "AutoWhiteBalance";
        public static final String BLACK_LEVEL = "BlackLevel";
        public static final String BLUE_BIAS = "BlueBias";
        public static final String BLUE_GAIN = "BlueGain";
        public static final String BLUE_HUE = "BlueHue";
        public static final String BLUE_LIGHTNESS = "BlueLightness";
        public static final String BLUE_SATURATION = "BlueSaturation";
        public static final String BRIGHTNESS = "Brightness";
        public static final String CLEARNESS_LIST = "ClearnessList";
        public static final String CLEARNESS_RANGE = "ClearnessRange";
        public static final String COLOR = "Color";
        public static final String COLOR_TEMPERATURE = "ColorTemperature";
        public static final String CONTRAST = "Contrast";
        public static final String CYAN_HUE = "CyanHue";
        public static final String CYAN_LIGHTNESS = "CyanLightness";
        public static final String CYAN_SATURATION = "CyanSaturation";
        public static final String DIGITAL_NOISE_REDUCTION = "DigitalNoiseReduction";
        public static final String FILE_MODE = "FilmMode";
        public static final String GAMMA = "Gamma";
        public static final String GREEN_BIAS = "GreenBias";
        public static final String GREEN_GAIN = "GreenGain";
        public static final String GREEN_HUE = "GreenHue";
        public static final String GREEN_LIGHTNESS = "GreenLightness";
        public static final String GREEN_SATURATION = "GreenSaturation";
        public static final String HUE = "Hue";
        public static final String LED_MOTION_MODE = "LedMotionMode";
        public static final String LIGHT_SENSOR = "LightSensor";
        public static final String MAGENTA_HUE = "MagentaHue";
        public static final String MAGENTA_LIGHTNESS = "MagentaLightness";
        public static final String MAGENTA_SATURATION = "MagentaSaturation";
        public static final String MOTION_FLOW = "MotionFlow";
        public static final String PANEL_TYPE = "PanelType";
        public static final String PEAK_LUMINANCE = "PeakLuminance";
        public static final String RANDOM_NOISE_REDUCTION = "RandomNoiseReduction";
        public static final String REALITY_CREATION = "RealityCreation";
        public static final String RED_BIAS = "RedBias";
        public static final String RED_GAIN = "RedGain";
        public static final String RED_HUE = "RedHue";
        public static final String RED_LIGHTNESS = "RedLightness";
        public static final String RED_SATURATION = "RedSaturation";
        public static final String RESOLUTION = "Resolution";
        public static final String SHARPNESS = "Sharpness";
        public static final String SMOOTHNESS = "Smoothness";
        public static final String SMOOTH_GRADATION = "SmoothGradation";
        public static final String XTENDED_DYNAMIC_RANGE = "XtendedDynamicRange";
        public static final String YELLOW_HUE = "YellowHue";
        public static final String YELLOW_LIGHTNESS = "YellowLightness";
        public static final String YELLOW_SATURATION = "YellowSaturation";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SignalType {
        public static final String DOLBY_VISION = "DolbyVision";
        public static final String HDR = "HDR";
        public static final String SDR = "SDR";
    }

    public void connect(@NonNull Context context, @NonNull OnConnectedListener onConnectedListener) {
        throw new RuntimeException("Stub!");
    }

    public void disconnect() {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public Bundle get() {
        throw new RuntimeException("Stub!");
    }

    @Nullable
    public Bundle getCapabilities() {
        throw new RuntimeException("Stub!");
    }

    @NonNull
    public String getVersion() {
        return "1.3.0";
    }

    public boolean isCalibratedModeEnabled() {
        throw new RuntimeException("Stub!");
    }

    public boolean registerCalibratedModeListener(CalibratedModeListener calibratedModeListener) {
        throw new RuntimeException("Stub!");
    }

    public int set(@NonNull Bundle bundle) {
        throw new RuntimeException("Stub!");
    }

    public boolean unregisterCalibratedModeListener(CalibratedModeListener calibratedModeListener) {
        throw new RuntimeException("Stub!");
    }
}
