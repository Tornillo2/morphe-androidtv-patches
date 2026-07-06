package androidx.media3.exoplayer.scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.scheduler.RequirementsWatcher;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class RequirementsWatcher {
    public final Context context;
    public final Handler handler = Util.createHandlerForCurrentOrMainLooper();
    public final Listener listener;

    @Nullable
    public NetworkCallback networkCallback;
    public int notMetRequirements;

    @Nullable
    public DeviceStatusChangeReceiver receiver;
    public final Requirements requirements;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class DeviceStatusChangeReceiver extends BroadcastReceiver {
        public DeviceStatusChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isInitialStickyBroadcast()) {
                return;
            }
            RequirementsWatcher.this.checkRequirements();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public final class NetworkCallback extends ConnectivityManager.NetworkCallback {
        public boolean networkValidated;
        public boolean receivedCapabilitiesChange;

        public static /* synthetic */ void $r8$lambda$_lAkuTwDCkZCZx8Nvp3TBo4ZsCE(NetworkCallback networkCallback) {
            RequirementsWatcher requirementsWatcher = RequirementsWatcher.this;
            if (requirementsWatcher.networkCallback != null) {
                requirementsWatcher.recheckNotMetNetworkRequirements();
            }
        }

        /* JADX INFO: renamed from: $r8$lambda$d8BquElBfy-Ktw1PmIB8ZvpY-to, reason: not valid java name */
        public static /* synthetic */ void m149$r8$lambda$d8BquElBfyKtw1PmIB8ZvpYto(NetworkCallback networkCallback) {
            RequirementsWatcher requirementsWatcher = RequirementsWatcher.this;
            if (requirementsWatcher.networkCallback != null) {
                requirementsWatcher.checkRequirements();
            }
        }

        public NetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            postCheckRequirements();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onBlockedStatusChanged(Network network, boolean z) {
            if (z) {
                return;
            }
            postRecheckNotMetNetworkRequirements();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            boolean zHasCapability = networkCapabilities.hasCapability(16);
            if (this.receivedCapabilitiesChange && this.networkValidated == zHasCapability) {
                if (zHasCapability) {
                    postRecheckNotMetNetworkRequirements();
                }
            } else {
                this.receivedCapabilitiesChange = true;
                this.networkValidated = zHasCapability;
                postCheckRequirements();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            postCheckRequirements();
        }

        public final void postCheckRequirements() {
            RequirementsWatcher.this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.scheduler.RequirementsWatcher$NetworkCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RequirementsWatcher.NetworkCallback.m149$r8$lambda$d8BquElBfyKtw1PmIB8ZvpYto(this.f$0);
                }
            });
        }

        public final void postRecheckNotMetNetworkRequirements() {
            RequirementsWatcher.this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.scheduler.RequirementsWatcher$NetworkCallback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RequirementsWatcher.NetworkCallback.$r8$lambda$_lAkuTwDCkZCZx8Nvp3TBo4ZsCE(this.f$0);
                }
            });
        }
    }

    public RequirementsWatcher(Context context, Listener listener, Requirements requirements) {
        this.context = context.getApplicationContext();
        this.listener = listener;
        this.requirements = requirements;
    }

    public final void checkRequirements() {
        int notMetRequirements = this.requirements.getNotMetRequirements(this.context);
        if (this.notMetRequirements != notMetRequirements) {
            this.notMetRequirements = notMetRequirements;
            this.listener.onRequirementsStateChanged(this, notMetRequirements);
        }
    }

    public Requirements getRequirements() {
        return this.requirements;
    }

    public final void recheckNotMetNetworkRequirements() {
        if ((this.notMetRequirements & 3) == 0) {
            return;
        }
        checkRequirements();
    }

    @RequiresApi(24)
    public final void registerNetworkCallbackV24() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        connectivityManager.getClass();
        NetworkCallback networkCallback = new NetworkCallback();
        this.networkCallback = networkCallback;
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }

    public int start() {
        this.notMetRequirements = this.requirements.getNotMetRequirements(this.context);
        IntentFilter intentFilter = new IntentFilter();
        if (this.requirements.isNetworkRequired()) {
            if (Util.SDK_INT >= 24) {
                registerNetworkCallbackV24();
            } else {
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
        }
        if (this.requirements.isChargingRequired()) {
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        }
        if (this.requirements.isIdleRequired()) {
            if (Util.SDK_INT >= 23) {
                intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
            } else {
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
            }
        }
        if (this.requirements.isStorageNotLowRequired()) {
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        }
        DeviceStatusChangeReceiver deviceStatusChangeReceiver = new DeviceStatusChangeReceiver();
        this.receiver = deviceStatusChangeReceiver;
        this.context.registerReceiver(deviceStatusChangeReceiver, intentFilter, null, this.handler);
        return this.notMetRequirements;
    }

    public void stop() {
        Context context = this.context;
        DeviceStatusChangeReceiver deviceStatusChangeReceiver = this.receiver;
        deviceStatusChangeReceiver.getClass();
        context.unregisterReceiver(deviceStatusChangeReceiver);
        this.receiver = null;
        if (Util.SDK_INT < 24 || this.networkCallback == null) {
            return;
        }
        unregisterNetworkCallbackV24();
    }

    @RequiresApi(24)
    public final void unregisterNetworkCallbackV24() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        connectivityManager.getClass();
        NetworkCallback networkCallback = this.networkCallback;
        networkCallback.getClass();
        connectivityManager.unregisterNetworkCallback(networkCallback);
        this.networkCallback = null;
    }
}
