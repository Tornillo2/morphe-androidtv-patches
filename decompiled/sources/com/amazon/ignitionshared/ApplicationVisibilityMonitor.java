package com.amazon.ignitionshared;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.AnyThread;
import androidx.annotation.UiThread;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class ApplicationVisibilityMonitor implements AutoCloseable {

    @NotNull
    public static final String ACTION_HDMI_PLUGGED = "android.intent.action.HDMI_PLUGGED";

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int FOREGROUND_SESSION_NONE = 0;

    @NotNull
    public static final String LOG_TAG = "ActivityLifecycle";

    @NotNull
    public final Context context;
    public volatile int foregroundSessionId;
    public boolean hasSeenHdmiConnected;

    @NotNull
    public final HdmiBroadcastReceiver hdmiBroadcastReceiver;
    public boolean isAppInForeground;
    public boolean isHdmiConnected;
    public boolean isReceiverRegistered;
    public int nextForegroundSessionId;

    @Nullable
    public Function0<Unit> onHdmiDisconnected;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class HdmiBroadcastReceiver extends BroadcastReceiver {
        public HdmiBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (Intrinsics.areEqual(intent.getAction(), ApplicationVisibilityMonitor.ACTION_HDMI_PLUGGED)) {
                boolean booleanExtra = intent.getBooleanExtra("state", false);
                ApplicationVisibilityMonitor applicationVisibilityMonitor = ApplicationVisibilityMonitor.this;
                if (!applicationVisibilityMonitor.hasSeenHdmiConnected && !booleanExtra) {
                    Log.w(ApplicationVisibilityMonitor.LOG_TAG, "Ignoring HDMI disconnected broadcast, keeping isHdmiConnected=true");
                    return;
                }
                if (booleanExtra) {
                    applicationVisibilityMonitor.hasSeenHdmiConnected = true;
                }
                if (applicationVisibilityMonitor.isHdmiConnected != booleanExtra) {
                    applicationVisibilityMonitor.isHdmiConnected = booleanExtra;
                    Log.i(ApplicationVisibilityMonitor.LOG_TAG, "HDMI status changed: connected=" + booleanExtra);
                    ApplicationVisibilityMonitor applicationVisibilityMonitor2 = ApplicationVisibilityMonitor.this;
                    if (applicationVisibilityMonitor2.isHdmiConnected) {
                        applicationVisibilityMonitor2.updateForegroundSessionId();
                        return;
                    }
                    Log.i(ApplicationVisibilityMonitor.LOG_TAG, "HDMI disconnected, treating app as background");
                    ApplicationVisibilityMonitor.this.foregroundSessionId = 0;
                    Function0<Unit> function0 = ApplicationVisibilityMonitor.this.onHdmiDisconnected;
                    if (function0 != null) {
                        function0.invoke();
                    }
                }
            }
        }
    }

    @Inject
    public ApplicationVisibilityMonitor(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.nextForegroundSessionId = 1;
        this.isHdmiConnected = true;
        this.hdmiBroadcastReceiver = new HdmiBroadcastReceiver();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.isReceiverRegistered) {
            this.context.unregisterReceiver(this.hdmiBroadcastReceiver);
            this.isReceiverRegistered = false;
            Log.i(LOG_TAG, "HDMI broadcast receiver unregistered");
        }
        this.onHdmiDisconnected = null;
    }

    @AnyThread
    public final int getForegroundSessionId() {
        return this.foregroundSessionId;
    }

    @Nullable
    public final Function0<Unit> getOnHdmiDisconnected() {
        return this.onHdmiDisconnected;
    }

    public final void initialize() {
        registerHdmiReceiver();
    }

    @UiThread
    public final void onBackground() {
        Log.i(LOG_TAG, "App's main activity now in background: previously foregroundSessionId=" + this.foregroundSessionId);
        this.isAppInForeground = false;
        this.foregroundSessionId = 0;
    }

    @UiThread
    public final void onForeground() {
        if (!this.isAppInForeground) {
            Log.i(LOG_TAG, "App's main activity is now in foreground");
            this.isAppInForeground = true;
            updateForegroundSessionId();
        } else {
            Log.w(LOG_TAG, "Got redundant foreground notification: foregroundSessionId=" + this.foregroundSessionId);
        }
    }

    public final void registerHdmiReceiver() {
        try {
            this.context.registerReceiver(this.hdmiBroadcastReceiver, new IntentFilter(ACTION_HDMI_PLUGGED));
            this.isReceiverRegistered = true;
            Log.i(LOG_TAG, "HDMI_PLUGGED broadcast receiver registered");
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to register HDMI receiver", e);
        }
    }

    public final void setOnHdmiDisconnected(@Nullable Function0<Unit> function0) {
        this.onHdmiDisconnected = function0;
    }

    public final void updateForegroundSessionId() {
        if (this.isAppInForeground && this.isHdmiConnected && this.foregroundSessionId == 0) {
            int i = this.nextForegroundSessionId;
            this.nextForegroundSessionId = i + 1;
            this.foregroundSessionId = i;
            Log.i(LOG_TAG, "App is now visible: foregroundSessionId=" + this.foregroundSessionId);
        }
    }
}
