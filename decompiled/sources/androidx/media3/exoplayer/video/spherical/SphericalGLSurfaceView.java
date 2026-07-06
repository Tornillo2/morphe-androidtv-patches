package androidx.media3.exoplayer.video.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.WindowManager;
import androidx.annotation.AnyThread;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.spherical.OrientationListener;
import androidx.media3.exoplayer.video.spherical.TouchTracker;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SphericalGLSurfaceView extends GLSurfaceView {
    public static final int FIELD_OF_VIEW_DEGREES = 90;
    public static final float PX_PER_DEGREES = 25.0f;
    public static final float UPRIGHT_ROLL = 3.1415927f;
    public static final float Z_FAR = 100.0f;
    public static final float Z_NEAR = 0.1f;
    public boolean isOrientationListenerRegistered;
    public boolean isStarted;
    public final Handler mainHandler;
    public final OrientationListener orientationListener;

    @Nullable
    public final Sensor orientationSensor;
    public final SceneRenderer scene;
    public final SensorManager sensorManager;

    @Nullable
    public Surface surface;

    @Nullable
    public SurfaceTexture surfaceTexture;
    public final TouchTracker touchTracker;
    public boolean useSensorRotation;
    public final CopyOnWriteArrayList<VideoSurfaceListener> videoSurfaceListeners;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public final class Renderer implements GLSurfaceView.Renderer, TouchTracker.Listener, OrientationListener.Listener {
        public final float[] deviceOrientationMatrix;
        public float deviceRoll;
        public final SceneRenderer scene;
        public float touchPitch;
        public final float[] touchPitchMatrix;
        public final float[] touchYawMatrix;
        public final float[] projectionMatrix = new float[16];
        public final float[] viewProjectionMatrix = new float[16];
        public final float[] viewMatrix = new float[16];
        public final float[] tempMatrix = new float[16];

        public Renderer(SceneRenderer sceneRenderer) {
            float[] fArr = new float[16];
            this.deviceOrientationMatrix = fArr;
            float[] fArr2 = new float[16];
            this.touchPitchMatrix = fArr2;
            float[] fArr3 = new float[16];
            this.touchYawMatrix = fArr3;
            this.scene = sceneRenderer;
            Matrix.setIdentityM(fArr, 0);
            Matrix.setIdentityM(fArr2, 0);
            Matrix.setIdentityM(fArr3, 0);
            this.deviceRoll = 3.1415927f;
        }

        public final float calculateFieldOfViewInYDirection(float f) {
            if (f > 1.0f) {
                return (float) (Math.toDegrees(Math.atan(Math.tan(Math.toRadians(45.0d)) / ((double) f))) * 2.0d);
            }
            return 90.0f;
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl10) {
            synchronized (this) {
                Matrix.multiplyMM(this.tempMatrix, 0, this.deviceOrientationMatrix, 0, this.touchYawMatrix, 0);
                Matrix.multiplyMM(this.viewMatrix, 0, this.touchPitchMatrix, 0, this.tempMatrix, 0);
            }
            Matrix.multiplyMM(this.viewProjectionMatrix, 0, this.projectionMatrix, 0, this.viewMatrix, 0);
            this.scene.drawFrame(this.viewProjectionMatrix, false);
        }

        @Override // androidx.media3.exoplayer.video.spherical.OrientationListener.Listener
        @BinderThread
        public synchronized void onOrientationChange(float[] fArr, float f) {
            float[] fArr2 = this.deviceOrientationMatrix;
            System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
            this.deviceRoll = -f;
            updatePitchMatrix();
        }

        @Override // androidx.media3.exoplayer.video.spherical.TouchTracker.Listener
        @UiThread
        public synchronized void onScrollChange(PointF pointF) {
            this.touchPitch = pointF.y;
            updatePitchMatrix();
            Matrix.setRotateM(this.touchYawMatrix, 0, -pointF.x, 0.0f, 1.0f, 0.0f);
        }

        @Override // androidx.media3.exoplayer.video.spherical.TouchTracker.Listener
        @UiThread
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return SphericalGLSurfaceView.this.performClick();
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
            GLES20.glViewport(0, 0, i, i2);
            float f = i / i2;
            Matrix.perspectiveM(this.projectionMatrix, 0, calculateFieldOfViewInYDirection(f), f, 0.1f, 100.0f);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            SphericalGLSurfaceView.this.onSurfaceTextureAvailable(this.scene.init());
        }

        @AnyThread
        public final void updatePitchMatrix() {
            Matrix.setRotateM(this.touchPitchMatrix, 0, -this.touchPitch, (float) Math.cos(this.deviceRoll), (float) Math.sin(this.deviceRoll), 0.0f);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface VideoSurfaceListener {
        void onVideoSurfaceCreated(Surface surface);

        void onVideoSurfaceDestroyed(Surface surface);
    }

    /* JADX INFO: renamed from: $r8$lambda$2h4bGm0n_IoJDjdsX-rnNLhYgyM, reason: not valid java name */
    public static /* synthetic */ void m175$r8$lambda$2h4bGm0n_IoJDjdsXrnNLhYgyM(SphericalGLSurfaceView sphericalGLSurfaceView) {
        Surface surface = sphericalGLSurfaceView.surface;
        if (surface != null) {
            Iterator<VideoSurfaceListener> it = sphericalGLSurfaceView.videoSurfaceListeners.iterator();
            while (it.hasNext()) {
                it.next().onVideoSurfaceDestroyed(surface);
            }
        }
        releaseSurface(sphericalGLSurfaceView.surfaceTexture, surface);
        sphericalGLSurfaceView.surfaceTexture = null;
        sphericalGLSurfaceView.surface = null;
    }

    public static /* synthetic */ void $r8$lambda$mGlwrqEoWGz2nUYvIKhMzpgxISY(SphericalGLSurfaceView sphericalGLSurfaceView, SurfaceTexture surfaceTexture) {
        SurfaceTexture surfaceTexture2 = sphericalGLSurfaceView.surfaceTexture;
        Surface surface = sphericalGLSurfaceView.surface;
        Surface surface2 = new Surface(surfaceTexture);
        sphericalGLSurfaceView.surfaceTexture = surfaceTexture;
        sphericalGLSurfaceView.surface = surface2;
        Iterator<VideoSurfaceListener> it = sphericalGLSurfaceView.videoSurfaceListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoSurfaceCreated(surface2);
        }
        releaseSurface(surfaceTexture2, surface);
    }

    public SphericalGLSurfaceView(Context context) {
        this(context, null);
    }

    public static void releaseSurface(@Nullable SurfaceTexture surfaceTexture, @Nullable Surface surface) {
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        if (surface != null) {
            surface.release();
        }
    }

    public void addVideoSurfaceListener(VideoSurfaceListener videoSurfaceListener) {
        this.videoSurfaceListeners.add(videoSurfaceListener);
    }

    public CameraMotionListener getCameraMotionListener() {
        return this.scene;
    }

    public VideoFrameMetadataListener getVideoFrameMetadataListener() {
        return this.scene;
    }

    @Nullable
    public Surface getVideoSurface() {
        return this.surface;
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mainHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SphericalGLSurfaceView.m175$r8$lambda$2h4bGm0n_IoJDjdsXrnNLhYgyM(this.f$0);
            }
        });
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        this.isStarted = false;
        updateOrientationListenerRegistration();
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        this.isStarted = true;
        updateOrientationListenerRegistration();
    }

    public final void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture) {
        this.mainHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SphericalGLSurfaceView.$r8$lambda$mGlwrqEoWGz2nUYvIKhMzpgxISY(this.f$0, surfaceTexture);
            }
        });
    }

    public void removeVideoSurfaceListener(VideoSurfaceListener videoSurfaceListener) {
        this.videoSurfaceListeners.remove(videoSurfaceListener);
    }

    public void setDefaultStereoMode(int i) {
        this.scene.defaultStereoMode = i;
    }

    public void setUseSensorRotation(boolean z) {
        this.useSensorRotation = z;
        updateOrientationListenerRegistration();
    }

    public final void updateOrientationListenerRegistration() {
        boolean z = this.useSensorRotation && this.isStarted;
        Sensor sensor = this.orientationSensor;
        if (sensor == null || z == this.isOrientationListenerRegistered) {
            return;
        }
        if (z) {
            this.sensorManager.registerListener(this.orientationListener, sensor, 0);
        } else {
            this.sensorManager.unregisterListener(this.orientationListener);
        }
        this.isOrientationListenerRegistered = z;
    }

    public SphericalGLSurfaceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoSurfaceListeners = new CopyOnWriteArrayList<>();
        this.mainHandler = new Handler(Looper.getMainLooper());
        Object systemService = context.getSystemService("sensor");
        systemService.getClass();
        SensorManager sensorManager = (SensorManager) systemService;
        this.sensorManager = sensorManager;
        Sensor defaultSensor = Util.SDK_INT >= 18 ? sensorManager.getDefaultSensor(15) : null;
        this.orientationSensor = defaultSensor == null ? sensorManager.getDefaultSensor(11) : defaultSensor;
        SceneRenderer sceneRenderer = new SceneRenderer();
        this.scene = sceneRenderer;
        Renderer renderer = new Renderer(sceneRenderer);
        TouchTracker touchTracker = new TouchTracker(context, renderer, 25.0f);
        this.touchTracker = touchTracker;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getClass();
        this.orientationListener = new OrientationListener(windowManager.getDefaultDisplay(), touchTracker, renderer);
        this.useSensorRotation = true;
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setOnTouchListener(touchTracker);
    }
}
