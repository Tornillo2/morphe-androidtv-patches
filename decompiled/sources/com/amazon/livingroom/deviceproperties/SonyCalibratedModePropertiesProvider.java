package com.amazon.livingroom.deviceproperties;

import android.os.Build;
import com.amazon.livingroom.mediapipelinebackend.Constants;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SonyCalibratedModePropertiesProvider {

    @NotNull
    public final String lcdHdrBroadcast;

    @NotNull
    public final String lcdHdrCinematic;

    @NotNull
    public final String lcdSdrBroadcast;

    @NotNull
    public final String lcdSdrCinematic;

    @NotNull
    public final String oledHdrBroadcast;

    @NotNull
    public final String oledHdrCinematic;

    @NotNull
    public final String oledSdrBroadcast;

    @NotNull
    public final String oledSdrCinematic;

    @NotNull
    public final SonyCalibratedModeConnector sonyCalibratedModeConnector;

    @Inject
    public SonyCalibratedModePropertiesProvider(@NotNull SonyCalibratedModeConnector sonyCalibratedModeConnector) {
        Intrinsics.checkNotNullParameter(sonyCalibratedModeConnector, "sonyCalibratedModeConnector");
        this.sonyCalibratedModeConnector = sonyCalibratedModeConnector;
        this.oledHdrBroadcast = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": false,\n  \"AutoToneCurve\": true,\n  \"AutoWhiteBalance\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": 1,\n  \"BlackLevel\": 50,\n  \"PeakLuminance\": 3,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 1,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 3\n}";
        this.oledHdrCinematic = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": false,\n  \"AutoToneCurve\": true,\n  \"AutoWhiteBalance\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": 0,\n  \"BlackLevel\": 50,\n  \"PeakLuminance\": 3,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 0,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 1\n}";
        this.oledSdrBroadcast = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": true,\n  \"AutoToneCurve\": true,\n  \"AutoWhiteBalance\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": -1,\n  \"BlackLevel\": 50,\n  \"PeakLuminance\": 2,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 2,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 1,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 3\n}";
        this.oledSdrCinematic = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": true,\n  \"AutoToneCurve\": true,\n  \"AutoWhiteBalance\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": -2,\n  \"BlackLevel\": 50,\n  \"PeakLuminance\": 2,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 0,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 1\n}";
        this.lcdHdrBroadcast = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": false,\n  \"AutoToneCurve\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": 1,\n  \"BlackLevel\": 50,\n  \"AutoLocalDimming\": 2,\n  \"XtendedDynamicRange\": 3,\n  \"Color\": 50,\n  \"Hue\": 50,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 1,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 3\n}";
        this.lcdHdrCinematic = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": false,\n  \"AutoToneCurve\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": 0,\n  \"BlackLevel\": 50,\n  \"AutoLocalDimming\": 2,\n  \"XtendedDynamicRange\": 3,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 0,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 1\n}";
        this.lcdSdrBroadcast = "{\n    \"AmbientLightSensor\": true,\n    \"AutoLuminanceLevel\": true,\n    \"AutoToneCurve\": true,\n    \"Brightness\": 50,\n    \"Contrast\": 90,\n    \"Gamma\": -1,\n    \"BlackLevel\": 50,\n    \"AutoLocalDimming\": 2,\n    \"XtendedDynamicRange\": 1,\n    \"Color\": 50,\n    \"Hue\": 0,\n    \"ColorTemperature\": 2,\n    \"RedGain\": 0,\n    \"GreenGain\": 0,\n    \"BlueGain\": 0,\n    \"RedBias\": 0,\n    \"GreenBias\": 0,\n    \"BlueBias\": 0,\n    \"RedHue\": 0,\n    \"GreenHue\": 0,\n    \"BlueHue\": 0,\n    \"CyanHue\": 0,\n    \"MagentaHue\": 0,\n    \"YellowHue\": 0,\n    \"RedSaturation\": 0,\n    \"GreenSaturation\": 0,\n    \"BlueSaturation\": 0,\n    \"CyanSaturation\": 0,\n    \"MagentaSaturation\": 0,\n    \"YellowSaturation\": 0,\n    \"RedLightness\": 0,\n    \"GreenLightness\": 0,\n    \"BlueLightness\": 0,\n    \"CyanLightness\": 0,\n    \"MagentaLightness\": 0,\n    \"YellowLightness\": 0,\n    \"Sharpness\": 50,\n    \"RealityCreation\": 1,\n    \"Resolution\": 20,\n    \"RandomNoiseReduction\": 0,\n    \"DigitalNoiseReduction\": 0,\n    \"SmoothGradation\": 1,\n    \"MotionFlow\": 6,\n    \"Smoothness\": 1,\n    \"ClearnessRange\": 1,\n    \"FilmMode\": 3\n}";
        this.lcdSdrCinematic = "{\n  \"AmbientLightSensor\": true,\n  \"AutoLuminanceLevel\": true,\n  \"AutoToneCurve\": true,\n  \"Brightness\": 50,\n  \"Contrast\": 90,\n  \"Gamma\": -2,\n  \"BlackLevel\": 50,\n  \"AutoLocalDimming\": 2,\n  \"XtendedDynamicRange\": 0,\n  \"Color\": 50,\n  \"Hue\": 0,\n  \"ColorTemperature\": 3,\n  \"RedGain\": 0,\n  \"GreenGain\": 0,\n  \"BlueGain\": 0,\n  \"RedBias\": 0,\n  \"GreenBias\": 0,\n  \"BlueBias\": 0,\n  \"RedHue\": 0,\n  \"GreenHue\": 0,\n  \"BlueHue\": 0,\n  \"CyanHue\": 0,\n  \"MagentaHue\": 0,\n  \"YellowHue\": 0,\n  \"RedSaturation\": 0,\n  \"GreenSaturation\": 0,\n  \"BlueSaturation\": 0,\n  \"CyanSaturation\": 0,\n  \"MagentaSaturation\": 0,\n  \"YellowSaturation\": 0,\n  \"RedLightness\": 0,\n  \"GreenLightness\": 0,\n  \"BlueLightness\": 0,\n  \"CyanLightness\": 0,\n  \"MagentaLightness\": 0,\n  \"YellowLightness\": 0,\n  \"Sharpness\": 50,\n  \"RealityCreation\": 1,\n  \"Resolution\": 20,\n  \"RandomNoiseReduction\": 0,\n  \"DigitalNoiseReduction\": 0,\n  \"SmoothGradation\": 1,\n  \"MotionFlow\": 6,\n  \"Smoothness\": 0,\n  \"ClearnessRange\": 0,\n  \"FilmMode\": 1\n}";
    }

    @NotNull
    public final String getLcdHdrBroadcast() {
        return this.lcdHdrBroadcast;
    }

    @NotNull
    public final String getLcdHdrCinematic() {
        return this.lcdHdrCinematic;
    }

    @NotNull
    public final String getLcdSdrBroadcast() {
        return this.lcdSdrBroadcast;
    }

    @NotNull
    public final String getLcdSdrCinematic() {
        return this.lcdSdrCinematic;
    }

    @NotNull
    public final String getOledHdrBroadcast() {
        return this.oledHdrBroadcast;
    }

    @NotNull
    public final String getOledHdrCinematic() {
        return this.oledHdrCinematic;
    }

    @NotNull
    public final String getOledSdrBroadcast() {
        return this.oledSdrBroadcast;
    }

    @NotNull
    public final String getOledSdrCinematic() {
        return this.oledSdrCinematic;
    }

    public final boolean isCalibratedModeEnabled() {
        return StringsKt__StringsJVMKt.equals(Build.MANUFACTURER, Constants.MANUFACTURERS.SONY, true);
    }

    public final boolean isDeviceCapableOfCalibratedMode() {
        return this.sonyCalibratedModeConnector.isConnected;
    }
}
