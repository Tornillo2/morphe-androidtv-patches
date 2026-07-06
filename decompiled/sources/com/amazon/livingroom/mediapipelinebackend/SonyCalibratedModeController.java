package com.amazon.livingroom.mediapipelinebackend;

import android.os.Bundle;
import com.amazon.ignitionshared.ApplicationVisibilityMonitor;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector;
import com.amazon.livingroom.mediapipelinebackend.SonyCalibrationConfigStore;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nSonyCalibratedModeController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SonyCalibratedModeController.kt\ncom/amazon/livingroom/mediapipelinebackend/SonyCalibratedModeController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,98:1\n1869#2:99\n1870#2:101\n1869#2,2:102\n1#3:100\n*S KotlinDebug\n*F\n+ 1 SonyCalibratedModeController.kt\ncom/amazon/livingroom/mediapipelinebackend/SonyCalibratedModeController\n*L\n61#1:99\n61#1:101\n89#1:102,2\n*E\n"})
public final class SonyCalibratedModeController implements SonyCalibratedModeConnector.CalibratedModeUserSettingsChangeListener {
    public int activeCalibratedMode;

    @NotNull
    public final List<CalibratedModeChangeListener> calibratedModeListeners;

    @NotNull
    public final SonyCalibratedModeConnector connector;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final List<String> supportedSignalTypes;

    @NotNull
    public final ApplicationVisibilityMonitor visibilityMonitor;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CalibratedModeChangeListener {
        void onCalibratedModeChanged(int i);
    }

    @Inject
    public SonyCalibratedModeController(@NotNull SonyCalibratedModeConnector connector, @NotNull DeviceProperties deviceProperties, @NotNull ApplicationVisibilityMonitor visibilityMonitor) {
        Intrinsics.checkNotNullParameter(connector, "connector");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(visibilityMonitor, "visibilityMonitor");
        this.connector = connector;
        this.deviceProperties = deviceProperties;
        this.visibilityMonitor = visibilityMonitor;
        this.calibratedModeListeners = new ArrayList();
        List listCreateListBuilder = CollectionsKt__CollectionsJVMKt.createListBuilder();
        if (((Boolean) deviceProperties.get(DeviceProperties.SUPPORTS_DOLBY_VISION)).booleanValue()) {
            ((ListBuilder) listCreateListBuilder).add(PictureQualityController.SignalType.DOLBY_VISION);
        }
        if (((Boolean) deviceProperties.get(DeviceProperties.SUPPORTS_HDR10)).booleanValue()) {
            ((ListBuilder) listCreateListBuilder).add(PictureQualityController.SignalType.HDR);
        }
        ((ListBuilder) listCreateListBuilder).add(PictureQualityController.SignalType.SDR);
        this.supportedSignalTypes = CollectionsKt__CollectionsJVMKt.build(listCreateListBuilder);
        connector.calibratedModeSettingsForwardingListener = this;
    }

    public final boolean addCalibratedModeChangeListener(@NotNull CalibratedModeChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.calibratedModeListeners.add(listener);
    }

    public final int getActiveCalibratedMode() {
        return this.activeCalibratedMode;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.SonyCalibratedModeConnector.CalibratedModeUserSettingsChangeListener
    public void onCalibratedModeSettingsChanged(boolean z) {
        int i = z ? this.activeCalibratedMode : 0;
        Iterator<T> it = this.calibratedModeListeners.iterator();
        while (it.hasNext()) {
            ((CalibratedModeChangeListener) it.next()).onCalibratedModeChanged(i);
        }
    }

    public final int setCalibratedMode(int i) {
        MpbLog.t("SonyCalibratedModeController.setCalibratedMode(" + i + ")");
        if (this.visibilityMonitor.foregroundSessionId <= 0) {
            return ErrorCode.INVALID_BACKGROUND_OPERATION;
        }
        if (i == 0) {
            this.activeCalibratedMode = 0;
            return 0;
        }
        try {
            SonyCalibratedModeConnector sonyCalibratedModeConnector = this.connector;
            if (!sonyCalibratedModeConnector.isConnected) {
                return ErrorCode.PICTURE_MODE_NOT_SUPPORTED;
            }
            Bundle devicePictureCapabilities = sonyCalibratedModeConnector.getDevicePictureCapabilities();
            if (devicePictureCapabilities == null) {
                return ErrorCode.CALIBRATED_MODE_DEVICE_CAPABILITY_NOT_AVAILABLE;
            }
            String string = devicePictureCapabilities.getString(PictureQualityController.SettingType.PANEL_TYPE);
            if (string == null) {
                return ErrorCode.CALIBRATED_MODE_PANEL_TYPE_NOT_FOUND;
            }
            for (String str : this.supportedSignalTypes) {
                SonyCalibrationConfigStore.Config config = new SonyCalibrationConfigStore.Config(string, str, i);
                SonyCalibrationConfigStore.INSTANCE.getClass();
                DeviceProperties.Property<String> property = SonyCalibrationConfigStore.configDevicePropertiesMap.get(config);
                if (property == null) {
                    MpbLog.e("Unable to get calibration data for config: " + config);
                    return ErrorCode.CALIBRATED_MODE_CALIBRATION_CONFIG_NOT_FOUND;
                }
                Bundle bundle = new Bundle();
                bundle.putString(str, (String) this.deviceProperties.get(property));
                int calibratedMode = this.connector.setCalibratedMode(bundle);
                if (calibratedMode != 0) {
                    MpbLog.e("Set Calibrated mode failed for " + config + " signal type. Sony API returned " + calibratedMode);
                    return ErrorCode.SET_CALIBRATED_MODE_FAILED;
                }
            }
            MpbLog.t("Calibrated Mode successfully set");
            SonyCalibratedModeConnector sonyCalibratedModeConnector2 = this.connector;
            if (sonyCalibratedModeConnector2.canObserveOSSettings && !sonyCalibratedModeConnector2.isCalibratedModeEnabledInOS) {
                return ErrorCode.CONFIG_SET_BUT_CALIBRATED_MODE_IS_DISABLED_BY_USER;
            }
            this.activeCalibratedMode = i;
            return 0;
        } catch (Exception e) {
            MpbLog.e("Set calibrated mode failed", e);
            return ErrorCode.SET_CALIBRATED_MODE_FAILED;
        }
    }
}
