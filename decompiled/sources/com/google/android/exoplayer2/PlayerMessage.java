package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PlayerMessage {
    public final Clock clock;
    public boolean isCanceled;
    public boolean isDelivered;
    public boolean isProcessed;
    public boolean isSent;
    public Looper looper;
    public int mediaItemIndex;

    @Nullable
    public Object payload;
    public final Sender sender;
    public final Target target;
    public final Timeline timeline;
    public int type;
    public long positionMs = -9223372036854775807L;
    public boolean deleteAfterDelivery = true;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Sender {
        void sendMessage(PlayerMessage playerMessage);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Target {
        void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException;
    }

    public PlayerMessage(Sender sender, Target target, Timeline timeline, int i, Clock clock, Looper looper) {
        this.sender = sender;
        this.target = target;
        this.timeline = timeline;
        this.looper = looper;
        this.clock = clock;
        this.mediaItemIndex = i;
    }

    public synchronized boolean blockUntilDelivered() throws InterruptedException {
        try {
            Assertions.checkState(this.isSent);
            Assertions.checkState(this.looper.getThread() != Thread.currentThread());
            while (!this.isProcessed) {
                wait();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.isDelivered;
    }

    @CanIgnoreReturnValue
    public synchronized PlayerMessage cancel() {
        Assertions.checkState(this.isSent);
        this.isCanceled = true;
        markAsProcessed(false);
        return this;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Looper getLooper() {
        return this.looper;
    }

    public int getMediaItemIndex() {
        return this.mediaItemIndex;
    }

    @Nullable
    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public synchronized boolean isCanceled() {
        return this.isCanceled;
    }

    public synchronized void markAsProcessed(boolean z) {
        this.isDelivered = z | this.isDelivered;
        this.isProcessed = true;
        notifyAll();
    }

    @CanIgnoreReturnValue
    public PlayerMessage send() {
        Assertions.checkState(!this.isSent);
        if (this.positionMs == -9223372036854775807L) {
            Assertions.checkArgument(this.deleteAfterDelivery);
        }
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setDeleteAfterDelivery(boolean z) {
        Assertions.checkState(!this.isSent);
        this.deleteAfterDelivery = z;
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public PlayerMessage setHandler(Handler handler) {
        setLooper(handler.getLooper());
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setLooper(Looper looper) {
        Assertions.checkState(!this.isSent);
        this.looper = looper;
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setPayload(@Nullable Object obj) {
        Assertions.checkState(!this.isSent);
        this.payload = obj;
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setPosition(long j) {
        Assertions.checkState(!this.isSent);
        this.positionMs = j;
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setType(int i) {
        Assertions.checkState(!this.isSent);
        this.type = i;
        return this;
    }

    @CanIgnoreReturnValue
    public PlayerMessage setPosition(int i, long j) {
        Assertions.checkState(!this.isSent);
        Assertions.checkArgument(j != -9223372036854775807L);
        if (i >= 0 && (this.timeline.isEmpty() || i < this.timeline.getWindowCount())) {
            this.mediaItemIndex = i;
            this.positionMs = j;
            return this;
        }
        throw new IllegalSeekPositionException(this.timeline, i, j);
    }

    public synchronized boolean blockUntilDelivered(long j) throws InterruptedException, TimeoutException {
        boolean z;
        try {
            Assertions.checkState(this.isSent);
            Assertions.checkState(this.looper.getThread() != Thread.currentThread());
            long jElapsedRealtime = this.clock.elapsedRealtime() + j;
            while (true) {
                z = this.isProcessed;
                if (z || j <= 0) {
                    break;
                }
                this.clock.getClass();
                wait(j);
                j = jElapsedRealtime - this.clock.elapsedRealtime();
            }
            if (!z) {
                throw new TimeoutException("Message delivery timed out.");
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.isDelivered;
    }
}
