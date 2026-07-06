package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.ignitionshared.Version;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import dagger.Lazy;
import javax.inject.Inject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nSonyCalibratedModeConnector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SonyCalibratedModeConnector.kt\ncom/amazon/livingroom/mediapipelinebackend/SonyCalibratedModeConnector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,160:1\n1#2:161\n*E\n"})
public final class SonyCalibratedModeConnector implements PictureQualityController.OnConnectedListener, LifecycleEventObserver {

    @NotNull
    public static final String MIN_PQC_VERSION_WITH_USER_SETTINGS_API = "1.3.0";

    @NotNull
    public final CalibratedModeSettingsChangedListener calibratedModeSettingsChangedListener;

    @Nullable
    public CalibratedModeUserSettingsChangeListener calibratedModeSettingsForwardingListener;
    public boolean canObserveOSSettings;

    @NotNull
    public final Context context;
    public boolean isCalibratedModeEnabledInOS;
    public boolean isConnected;

    @NotNull
    public final MetricsRecorder metricsRecorder;

    @NotNull
    public final Lazy<PictureQualityController> pictureQualityController;

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(SonyCalibratedModeConnector.class)).getSimpleName();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class CalibratedModeSettingsChangedListener implements PictureQualityController.CalibratedModeListener {
        public CalibratedModeSettingsChangedListener() {
        }

        @Override // com.sony.dtv.picturequalitycontrol.PictureQualityController.CalibratedModeListener
        public void onChanged(boolean z) {
            Companion companion = SonyCalibratedModeConnector.Companion;
            companion.getClass();
            Log.d(SonyCalibratedModeConnector.TAG, "PQC onSettingsChanged(): isCalibratedModeEnabled: " + z);
            SonyCalibratedModeConnector sonyCalibratedModeConnector = SonyCalibratedModeConnector.this;
            if (sonyCalibratedModeConnector.isCalibratedModeEnabledInOS != z) {
                sonyCalibratedModeConnector.isCalibratedModeEnabledInOS = z;
                CalibratedModeUserSettingsChangeListener calibratedModeUserSettingsChangeListener = sonyCalibratedModeConnector.calibratedModeSettingsForwardingListener;
                if (calibratedModeUserSettingsChangeListener != null) {
                    calibratedModeUserSettingsChangeListener.onCalibratedModeSettingsChanged(z);
                } else {
                    companion.getClass();
                    Log.w(SonyCalibratedModeConnector.TAG, "PQC reports settings changed, but no listener is registered to consume the event");
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CalibratedModeUserSettingsChangeListener {
        void onCalibratedModeSettingsChanged(boolean z);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @Nullable
        public final String getTAG() {
            return SonyCalibratedModeConnector.TAG;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MetricsEvent {

        @NotNull
        public static final String CONNECTION_ERROR = "SonyCalibratedMode.PictureQualityController.ConnectionError";

        @NotNull
        public static final String CONNECTION_FAILURE = "SonyCalibratedMode.PictureQualityController.ConnectionFailure";

        @NotNull
        public static final String CONNECTION_START = "SonyCalibratedMode.PictureQualityController.ConnectionStarted";

        @NotNull
        public static final String CONNECTION_SUCCESS = "SonyCalibratedMode.PictureQualityController.ConnectionSuccess";

        @NotNull
        public static final String DISCONNECTION_ERROR = "SonyCalibratedMode.PictureQualityController.DisconnectionError";

        @NotNull
        public static final String DISCONNECTION_START = "SonyCalibratedMode.PictureQualityController.DisconnectionStarted";

        @NotNull
        public static final String DISCONNECTION_SUCCESS = "SonyCalibratedMode.PictureQualityController.DisconnectionSuccess";

        @NotNull
        public static final MetricsEvent INSTANCE = new MetricsEvent();

        @NotNull
        public static final String SETTINGS_LISTENER_REGISTRATION = "SonyCalibratedMode.PictureQualityController.SettingsListenerRegistration";

        @NotNull
        public static final String SETTINGS_LISTENER_REGISTRATION_ERROR = "SonyCalibratedMode.PictureQualityController.SettingsListenerRegistrationError";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public SonyCalibratedModeConnector(@ApplicationContext @NotNull Context context, @NotNull Lazy<PictureQualityController> pictureQualityController, @NotNull MetricsRecorder metricsRecorder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pictureQualityController, "pictureQualityController");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        this.context = context;
        this.pictureQualityController = pictureQualityController;
        this.metricsRecorder = metricsRecorder;
        this.calibratedModeSettingsChangedListener = new CalibratedModeSettingsChangedListener();
    }

    public final boolean getCanObserveOSSettings() {
        return this.canObserveOSSettings;
    }

    @Nullable
    public final Bundle getDevicePictureCapabilities() {
        return this.pictureQualityController.get().getCapabilities();
    }

    public final boolean isCalibratedModeEnabledInOS() {
        return this.isCalibratedModeEnabledInOS;
    }

    public final boolean isCalibratedModeSupported() {
        return this.isConnected;
    }

    public final void observeLifecycle(@NotNull Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Log.d(TAG, "Will start observing App lifecycle");
        lifecycle.addObserver(this);
    }

    @Override // com.sony.dtv.picturequalitycontrol.PictureQualityController.OnConnectedListener
    public void onConnected() {
        Log.d(TAG, "PQC onConnected(). PQC version is: " + this.pictureQualityController.get().getVersion());
        this.isConnected = true;
        recordCountMetric(MetricsEvent.CONNECTION_SUCCESS);
        Version.Companion companion = Version.Companion;
        String version = this.pictureQualityController.get().getVersion();
        Intrinsics.checkNotNullExpressionValue(version, "getVersion(...)");
        boolean z = companion.parse(version).compareTo(companion.parse(MIN_PQC_VERSION_WITH_USER_SETTINGS_API)) >= 0;
        this.canObserveOSSettings = z;
        if (z) {
            try {
                recordCountMetric(MetricsEvent.SETTINGS_LISTENER_REGISTRATION);
                this.pictureQualityController.get().registerCalibratedModeListener(this.calibratedModeSettingsChangedListener);
                this.isCalibratedModeEnabledInOS = this.pictureQualityController.get().isCalibratedModeEnabled();
            } catch (Exception e) {
                Log.e(TAG, "Unable to register User settings change listener", e);
                recordCountMetric(MetricsEvent.SETTINGS_LISTENER_REGISTRATION_ERROR);
            }
        }
    }

    @Override // com.sony.dtv.picturequalitycontrol.PictureQualityController.OnConnectedListener
    public void onConnectionFailed() {
        Log.e(TAG, "PQC onConnectionFailed()");
        this.isConnected = false;
        recordCountMetric(MetricsEvent.CONNECTION_FAILURE);
    }

    @Override // com.sony.dtv.picturequalitycontrol.PictureQualityController.OnConnectedListener
    public void onDisconnected() {
        Log.d(TAG, "PQC onDisconnected()");
        this.isConnected = false;
        recordCountMetric(MetricsEvent.DISCONNECTION_SUCCESS);
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            try {
                recordCountMetric(MetricsEvent.CONNECTION_START);
                this.pictureQualityController.get().connect(this.context, this);
                return;
            } catch (Exception e) {
                Log.e(TAG, "Call to connect to PQC failed", e);
                recordCountMetric(MetricsEvent.CONNECTION_ERROR);
                return;
            }
        }
        if (i == 2 && this.isConnected) {
            try {
                recordCountMetric(MetricsEvent.DISCONNECTION_START);
                if (this.canObserveOSSettings) {
                    this.pictureQualityController.get().unregisterCalibratedModeListener(this.calibratedModeSettingsChangedListener);
                }
                this.pictureQualityController.get().disconnect();
            } catch (Exception e2) {
                Log.e(TAG, "Failed to disconnect from PQC", e2);
                recordCountMetric(MetricsEvent.DISCONNECTION_ERROR);
            }
        }
    }

    public final void recordCountMetric(String str) {
        MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.SONY_CALIBRATED_MODE_SCHEMA_ID);
        MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, str, 1, null, 4, null);
        this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
    }

    public final int setCalibratedMode(@NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        String str = TAG;
        Log.d(str, "Calling PQC.set() with Bundle: " + bundle);
        int i = this.pictureQualityController.get().set(bundle);
        Log.d(str, "PQC.Set() returned " + i);
        return i;
    }

    public final void setUserSettingsChangeListener(@NotNull CalibratedModeUserSettingsChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.calibratedModeSettingsForwardingListener = listener;
    }
}
