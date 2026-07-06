package com.amazon.livingroom.mediapipelinebackend;

import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.livingroom.deviceproperties.DevicePlaybackCapabilities;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.InternalPlaybackSurface;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine;
import j$.lang.Iterable$EL;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class MediaPipelineBackendEngineManager implements MediaPipelineBackendEngine.DestroyInterceptor {
    public static final Map<String, Integer> supportedPictureModes = MediaPipelineBackendEngineManager$$ExternalSyntheticBackport1.m(new Map.Entry[]{new AbstractMap.SimpleEntry("none", 0), new AbstractMap.SimpleEntry(PictureModeConstants.PROPERTY_VALUE_CALIBRATED_BROADCAST, 3), new AbstractMap.SimpleEntry(PictureModeConstants.PROPERTY_VALUE_CALIBRATED_CINEMATIC, 4)});
    public final AudioFocusManager audioFocusManager;
    public final CodecQuerier codecQuerier;
    public final DeviceProperties deviceProperties;
    public final MediaSessionAdapter mediaSessionAdapter;
    public final MediaPipelineBackendEngine.Factory mpbEngineFactory;
    public final SonyCalibratedModeController sonyCalibratedModeController;
    public final InternalPlaybackSurface.Factory surfaceFactory;
    public final WakeLocker wakeLocker;
    public final Set<MediaPipelineBackendEngine> mpbInstances = new HashSet();
    public int nextMpbInstanceId = 0;
    public MediaPipelineBackendEngine cachedMpbEngine = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PictureModeConstants {
        public static final int CALIBRATED_MODE_BROADCAST = 3;
        public static final int CALIBRATED_MODE_CINEMATIC = 4;
        public static final int NONE = 0;
        public static final String PROPERTY_KEY = "pictureMode";
        public static final String PROPERTY_VALUE_CALIBRATED_BROADCAST = "calibratedBroadcast";
        public static final String PROPERTY_VALUE_CALIBRATED_CINEMATIC = "calibratedCinematic";
        public static final String PROPERTY_VALUE_NONE = "none";
    }

    @Inject
    public MediaPipelineBackendEngineManager(MediaPipelineBackendEngine.Factory factory, DeviceProperties deviceProperties, SonyCalibratedModeController sonyCalibratedModeController, MediaSessionAdapter mediaSessionAdapter, AudioFocusManager audioFocusManager, WakeLocker wakeLocker, CodecQuerier codecQuerier, InternalPlaybackSurface.Factory factory2) {
        this.mpbEngineFactory = factory;
        this.deviceProperties = deviceProperties;
        this.sonyCalibratedModeController = sonyCalibratedModeController;
        this.mediaSessionAdapter = mediaSessionAdapter;
        this.audioFocusManager = audioFocusManager;
        this.wakeLocker = wakeLocker;
        this.codecQuerier = codecQuerier;
        this.surfaceFactory = factory2;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngine.DestroyInterceptor
    public synchronized boolean allowDestroy(MediaPipelineBackendEngine mediaPipelineBackendEngine) {
        if (this.mpbInstances.size() == 1) {
            MpbLog.i("Suppressing destruction of last live MPBEngine");
            this.cachedMpbEngine = mediaPipelineBackendEngine;
            return false;
        }
        MpbLog.i("Allowing destruction of MPBEngine. Remaining live instances: " + this.mpbInstances.size());
        this.mpbInstances.remove(mediaPipelineBackendEngine);
        return true;
    }

    @CalledFromNative
    public synchronized ResultHolder<Integer> canDecodeAudio(String str, int i, String str2, int i2) {
        try {
        } catch (Exception e) {
            MpbLog.e("Exception in canDecodeAudio call: ", e);
            return ResultHolder.fromErrorCode(ErrorCode.UNSUPPORTED_CODEC);
        }
        return this.codecQuerier.canDecodeAudio(str, i, str2, i2);
    }

    @CalledFromNative
    public synchronized ResultHolder<Integer> canDecodeVideo(String str, int i, int i2, int i3, float f, String str2, String str3, String str4) {
        try {
        } catch (Exception e) {
            MpbLog.e("Exception in canDecodeVideo call: ", e);
            return ResultHolder.fromErrorCode(ErrorCode.UNSUPPORTED_CODEC);
        }
        return this.codecQuerier.canDecodeVideo(str, i, i2, i3, f, str2, str3, str4);
    }

    /* JADX INFO: renamed from: createEngine, reason: merged with bridge method [inline-methods] */
    public final MediaPipelineBackendEngine lambda$initEngine$0(Handler handler, PlaybackSurface playbackSurface, int i) {
        return this.mpbEngineFactory.create(handler, createListener(), this, playbackSurface, i);
    }

    public final MediaPipelineListener createListener() {
        MediaPipelineCompositeListener mediaPipelineCompositeListener = new MediaPipelineCompositeListener();
        mediaPipelineCompositeListener.addListener(new WakeLockerAdapter(this.wakeLocker));
        mediaPipelineCompositeListener.addListener(this.mediaSessionAdapter);
        mediaPipelineCompositeListener.addListener(this.audioFocusManager);
        return mediaPipelineCompositeListener;
    }

    @CalledFromNative
    public ResultHolder<String> getJsonCapabilities() {
        return ResultHolder.fromResult(DevicePlaybackCapabilities.create(((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_INTRA_CHUNK_SEEKING)).booleanValue(), ((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_VARIABLE_ASPECT_RATIO)).booleanValue(), ((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_CALIBRATED_MODE)).booleanValue(), ((Integer) this.deviceProperties.get(DeviceProperties.MAX_MPB_INSTANCES)).intValue(), ((Boolean) this.deviceProperties.get(DeviceProperties.SUPPORTS_AUDIO_VOLUME)).booleanValue()).toJsonString());
    }

    @CalledFromNative
    public int getMaxInstances() {
        return ((Integer) this.deviceProperties.get(DeviceProperties.MAX_MPB_INSTANCES)).intValue();
    }

    public final String getPictureModeString(int i) {
        for (Map.Entry<String, Integer> entry : supportedPictureModes.entrySet()) {
            if (i == entry.getValue().intValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    @CalledFromNative
    public ResultHolder<String> getProperty(String str) {
        if (!str.equals(PictureModeConstants.PROPERTY_KEY)) {
            return ResultHolder.fromErrorCode(ErrorCode.MPB_KEY_NOT_FOUND);
        }
        String pictureModeString = getPictureModeString(this.sonyCalibratedModeController.activeCalibratedMode);
        return pictureModeString == null ? ResultHolder.fromErrorCode(ErrorCode.PICTURE_MODE_NAME_NOT_FOUND) : ResultHolder.fromResult(pictureModeString);
    }

    @CalledFromNative
    public synchronized ResultHolder<MediaPipelineBackendEngine> initEngine() {
        HandlerThread handlerThread;
        MediaPipelineBackendEngine mediaPipelineBackendEngine = this.cachedMpbEngine;
        HandlerThread handlerThread2 = null;
        if (mediaPipelineBackendEngine != null) {
            ResultHolder<MediaPipelineBackendEngine> resultHolderFromResult = ResultHolder.fromResult(mediaPipelineBackendEngine);
            this.cachedMpbEngine = null;
            return resultHolderFromResult;
        }
        final int i = this.nextMpbInstanceId;
        this.nextMpbInstanceId = i + 1;
        final InternalPlaybackSurface internalPlaybackSurfaceCreate = this.surfaceFactory.create("ExoMpb-" + i);
        boolean z = false;
        try {
            handlerThread = new HandlerThread("ExoPlayerApp-" + i);
        } catch (Throwable th) {
            th = th;
        }
        try {
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            FutureTask futureTask = new FutureTask(new Callable() { // from class: com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticLambda3
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$initEngine$0(handler, internalPlaybackSurfaceCreate, i);
                }
            });
            handler.post(futureTask);
            try {
                try {
                    MediaPipelineBackendEngine mediaPipelineBackendEngine2 = (MediaPipelineBackendEngine) futureTask.get();
                    this.mpbInstances.add(mediaPipelineBackendEngine2);
                    MpbLog.i("initEngine(): attached new MPB instance to mpbInstanceId " + i + ", instance count=" + this.mpbInstances.size());
                    z = true;
                    return ResultHolder.fromResult(mediaPipelineBackendEngine2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    MpbLog.e("Interrupted while initialising ExoPlayer", e);
                    ResultHolder<MediaPipelineBackendEngine> resultHolderFromErrorCode = ResultHolder.fromErrorCode(ErrorCode.MPB_ENGINE_INIT_INTERRUPTED);
                    if (!z) {
                        handlerThread.quitSafely();
                        internalPlaybackSurfaceCreate.close();
                    }
                    return resultHolderFromErrorCode;
                }
            } catch (ExecutionException e2) {
                MpbLog.e("Failed to initialise ExoPlayer", e2);
                ResultHolder<MediaPipelineBackendEngine> resultHolderFromErrorCode2 = ResultHolder.fromErrorCode(ErrorCode.MPB_ENGINE_INIT_FAILED);
                if (!z) {
                    handlerThread.quitSafely();
                    internalPlaybackSurfaceCreate.close();
                }
                return resultHolderFromErrorCode2;
            }
        } catch (Throwable th2) {
            th = th2;
            handlerThread2 = handlerThread;
            if (0 == 0) {
                if (handlerThread2 != null) {
                    handlerThread2.quitSafely();
                }
                internalPlaybackSurfaceCreate.close();
            }
            throw th;
        }
    }

    public synchronized void releasePlayers() {
        Iterable$EL.forEach(this.mpbInstances, new MediaPipelineBackendEngineManager$$ExternalSyntheticLambda4());
    }

    @CalledFromNative
    public int setPictureMode(int i) {
        MpbLog.t("MPBEngine Manager setPictureMode(" + i + ")");
        return !supportedPictureModes.containsValue(Integer.valueOf(i)) ? ErrorCode.PICTURE_MODE_NOT_SUPPORTED : this.sonyCalibratedModeController.setCalibratedMode(i);
    }

    @CalledFromNative
    public int setProperty(String str, String str2) {
        if (!str.equals(PictureModeConstants.PROPERTY_KEY)) {
            return ErrorCode.MPB_KEY_NOT_FOUND;
        }
        Integer num = supportedPictureModes.get(str2);
        if (num == null) {
            return ErrorCode.PICTURE_MODE_NOT_SUPPORTED;
        }
        return num.intValue() == this.sonyCalibratedModeController.activeCalibratedMode ? ErrorCode.MPB_PICTURE_MODE_NOT_SET : setPictureMode(num.intValue());
    }
}
