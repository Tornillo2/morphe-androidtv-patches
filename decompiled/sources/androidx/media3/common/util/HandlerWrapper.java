package androidx.media3.common.util;

import android.os.Looper;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface HandlerWrapper {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Message {
        HandlerWrapper getTarget();

        void sendToTarget();
    }

    Looper getLooper();

    boolean hasMessages(int i);

    Message obtainMessage(int i);

    Message obtainMessage(int i, int i2, int i3);

    Message obtainMessage(int i, int i2, int i3, @Nullable Object obj);

    Message obtainMessage(int i, @Nullable Object obj);

    boolean post(Runnable runnable);

    boolean postAtFrontOfQueue(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j);

    void removeCallbacksAndMessages(@Nullable Object obj);

    void removeMessages(int i);

    boolean sendEmptyMessage(int i);

    boolean sendEmptyMessageAtTime(int i, long j);

    boolean sendEmptyMessageDelayed(int i, int i2);

    boolean sendMessageAtFrontOfQueue(Message message);
}
