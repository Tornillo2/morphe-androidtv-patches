package androidx.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class GestureDetectorCompat {
    public final GestureDetector mDetector;

    public GestureDetectorCompat(@NonNull Context context, @NonNull GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean isLongpressEnabled() {
        return this.mDetector.isLongpressEnabled();
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        return this.mDetector.onTouchEvent(motionEvent);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setIsLongpressEnabled(boolean z) {
        this.mDetector.setIsLongpressEnabled(z);
    }

    public void setOnDoubleTapListener(@Nullable GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mDetector.setOnDoubleTapListener(onDoubleTapListener);
    }

    public GestureDetectorCompat(@NonNull Context context, @NonNull GestureDetector.OnGestureListener onGestureListener, @Nullable Handler handler) {
        this.mDetector = new GestureDetector(context, onGestureListener, handler);
    }
}
