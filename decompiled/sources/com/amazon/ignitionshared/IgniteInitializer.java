package com.amazon.ignitionshared;

import android.content.Context;
import android.net.ConnectivityManager;
import com.amazon.ignitionshared.filesystem.LocalStorage;
import com.amazon.livingroom.accessibility.TextToSpeechEngine;
import com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider;
import com.amazon.livingroom.mediapipelinebackend.DrmSystemManager;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbContext;
import com.amazon.livingroom.mediapipelinebackend.FtvMpbDrmContext;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IgniteInitializer {
    public IgniteInitializer() {
        throw new UnsupportedOperationException("Abstract utility class; do not instantiate");
    }

    public static native void initializeAndroidContext(String str);

    public static void initializeCAres(Context context) {
        initializeCAres((ConnectivityManager) context.getSystemService("connectivity"));
    }

    public static native void initializeCAres(ConnectivityManager connectivityManager);

    public static native void initializeDeviceAttestation(DeviceAttestationService deviceAttestationService);

    public static native void initializeDevicePropertiesProvider(IgniteDevicePropertiesProvider igniteDevicePropertiesProvider);

    public static native void initializeDrmBridge(DrmSystemManager drmSystemManager, FtvMpbDrmContext ftvMpbDrmContext);

    public static native void initializeExitReasonHandler(ExitReasonHandler exitReasonHandler);

    public static native void initializeGMBMessageProcessor(GMBMessageProcessor gMBMessageProcessor);

    public static native void initializeJni(NativeThreadInitializer nativeThreadInitializer);

    public static native void initializeLifecycleBridge(IgniteJavaCallbacks igniteJavaCallbacks);

    public static native void initializeLocalStorage(LocalStorage localStorage);

    public static native void initializeMpb(MediaPipelineBackendEngineManager mediaPipelineBackendEngineManager, FtvMpbContext ftvMpbContext);

    public static native void initializeTextToSpeech(TextToSpeechEngine textToSpeechEngine);
}
