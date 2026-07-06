package com.amazon.ignitionshared;

import android.os.SystemClock;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class GMBMessageSender {
    public static final int FAILURE_CODE = 1;
    public static final int SUCCESS_CODE = 0;
    public boolean igniteInitialised;

    @NotNull
    public final Object igniteInitializedMutex = new Object();

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(GMBMessageSender.class)).getSimpleName();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public GMBMessageSender() {
    }

    private final native void sendGMBMessage(String str, String str2);

    public static /* synthetic */ int sendGMBMessageToClient$default(GMBMessageSender gMBMessageSender, String str, String str2, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 0;
        }
        return gMBMessageSender.sendGMBMessageToClient(str, str2, j);
    }

    public final boolean getIgniteInitialised() {
        return this.igniteInitialised;
    }

    public final int sendGMBMessageToClient(@NotNull String eventType, @NotNull String payload, long j) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        Intrinsics.checkNotNullParameter(payload, "payload");
        synchronized (this.igniteInitializedMutex) {
            if (j > 0) {
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    long jElapsedRealtime2 = jElapsedRealtime;
                    while (!this.igniteInitialised) {
                        long j2 = jElapsedRealtime2 - jElapsedRealtime;
                        if (j2 >= j) {
                            break;
                        }
                        this.igniteInitializedMutex.wait(j - j2);
                        jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    }
                    Log.i(TAG, "Waited " + (jElapsedRealtime2 - jElapsedRealtime) + "ms for Ignite to initialise");
                } catch (Throwable th) {
                    throw th;
                }
            }
            String str = TAG;
            Log.d(str, "Sending GMB message: \"" + eventType + "\" to client with payload:\n" + payload);
            if (this.igniteInitialised) {
                sendGMBMessage(eventType, payload);
                return 0;
            }
            Log.e(str, "Failed to send GMB message of type " + eventType);
            return 1;
        }
    }

    public final void setIgniteInitialised(boolean z) {
        synchronized (this.igniteInitializedMutex) {
            this.igniteInitialised = z;
            this.igniteInitializedMutex.notifyAll();
        }
    }
}
