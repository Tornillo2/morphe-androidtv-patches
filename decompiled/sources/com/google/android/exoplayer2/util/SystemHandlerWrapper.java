package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SystemHandlerWrapper implements HandlerWrapper {
    public static final int MAX_POOL_SIZE = 50;

    @GuardedBy("messagePool")
    public static final List<SystemMessage> messagePool = new ArrayList(50);
    public final Handler handler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SystemMessage implements HandlerWrapper.Message {

        @Nullable
        public SystemHandlerWrapper handler;

        @Nullable
        public Message message;

        public SystemMessage() {
        }

        @Override // com.google.android.exoplayer2.util.HandlerWrapper.Message
        public HandlerWrapper getTarget() {
            SystemHandlerWrapper systemHandlerWrapper = this.handler;
            systemHandlerWrapper.getClass();
            return systemHandlerWrapper;
        }

        public final void recycle() {
            this.message = null;
            this.handler = null;
            SystemHandlerWrapper.recycleMessage(this);
        }

        public boolean sendAtFrontOfQueue(Handler handler) {
            Message message = this.message;
            message.getClass();
            boolean zSendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(message);
            recycle();
            return zSendMessageAtFrontOfQueue;
        }

        @Override // com.google.android.exoplayer2.util.HandlerWrapper.Message
        public void sendToTarget() {
            Message message = this.message;
            message.getClass();
            message.sendToTarget();
            recycle();
        }

        @CanIgnoreReturnValue
        public SystemMessage setMessage(Message message, SystemHandlerWrapper systemHandlerWrapper) {
            this.message = message;
            this.handler = systemHandlerWrapper;
            return this;
        }

        public SystemMessage(AnonymousClass1 anonymousClass1) {
        }
    }

    public SystemHandlerWrapper(Handler handler) {
        this.handler = handler;
    }

    public static SystemMessage obtainSystemMessage() {
        SystemMessage systemMessage;
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                systemMessage = list.isEmpty() ? new SystemMessage() : list.remove(list.size() - 1);
            } catch (Throwable th) {
                throw th;
            }
        }
        return systemMessage;
    }

    public static void recycleMessage(SystemMessage systemMessage) {
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                if (list.size() < 50) {
                    list.add(systemMessage);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public Looper getLooper() {
        return this.handler.getLooper();
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean hasMessages(int i) {
        return this.handler.hasMessages(i);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i) {
        SystemMessage systemMessageObtainSystemMessage = obtainSystemMessage();
        systemMessageObtainSystemMessage.message = this.handler.obtainMessage(i);
        systemMessageObtainSystemMessage.handler = this;
        return systemMessageObtainSystemMessage;
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean postAtFrontOfQueue(Runnable runnable) {
        return this.handler.postAtFrontOfQueue(runnable);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean postDelayed(Runnable runnable, long j) {
        return this.handler.postDelayed(runnable, j);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public void removeCallbacksAndMessages(@Nullable Object obj) {
        this.handler.removeCallbacksAndMessages(obj);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public void removeMessages(int i) {
        this.handler.removeMessages(i);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendEmptyMessage(int i) {
        return this.handler.sendEmptyMessage(i);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendEmptyMessageAtTime(int i, long j) {
        return this.handler.sendEmptyMessageAtTime(i, j);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendEmptyMessageDelayed(int i, int i2) {
        return this.handler.sendEmptyMessageDelayed(i, i2);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public boolean sendMessageAtFrontOfQueue(HandlerWrapper.Message message) {
        return ((SystemMessage) message).sendAtFrontOfQueue(this.handler);
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i, @Nullable Object obj) {
        SystemMessage systemMessageObtainSystemMessage = obtainSystemMessage();
        systemMessageObtainSystemMessage.message = this.handler.obtainMessage(i, obj);
        systemMessageObtainSystemMessage.handler = this;
        return systemMessageObtainSystemMessage;
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i, int i2, int i3) {
        SystemMessage systemMessageObtainSystemMessage = obtainSystemMessage();
        systemMessageObtainSystemMessage.message = this.handler.obtainMessage(i, i2, i3);
        systemMessageObtainSystemMessage.handler = this;
        return systemMessageObtainSystemMessage;
    }

    @Override // com.google.android.exoplayer2.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i, int i2, int i3, @Nullable Object obj) {
        SystemMessage systemMessageObtainSystemMessage = obtainSystemMessage();
        systemMessageObtainSystemMessage.message = this.handler.obtainMessage(i, i2, i3, obj);
        systemMessageObtainSystemMessage.handler = this;
        return systemMessageObtainSystemMessage;
    }
}
