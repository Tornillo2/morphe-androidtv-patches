package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NetworkStateTracker extends ConstraintTracker<NetworkState> {
    public static final String TAG = Logger.tagWithPrefix("NetworkStateTracker");
    public NetworkStateBroadcastReceiver mBroadcastReceiver;
    public final ConnectivityManager mConnectivityManager;

    @RequiresApi(24)
    public NetworkStateCallback mNetworkCallback;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class NetworkStateBroadcastReceiver extends BroadcastReceiver {
        public NetworkStateBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null || !intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                return;
            }
            Logger.get().debug(NetworkStateTracker.TAG, "Network broadcast received", new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public class NetworkStateCallback extends ConnectivityManager.NetworkCallback {
        public NetworkStateCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities capabilities) {
            Logger.get().debug(NetworkStateTracker.TAG, String.format("Network capabilities changed: %s", capabilities), new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@NonNull Network network) {
            Logger.get().debug(NetworkStateTracker.TAG, "Network connection lost", new Throwable[0]);
            NetworkStateTracker networkStateTracker = NetworkStateTracker.this;
            networkStateTracker.setState(networkStateTracker.getActiveNetworkState());
        }
    }

    public NetworkStateTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        this.mConnectivityManager = (ConnectivityManager) this.mAppContext.getSystemService("connectivity");
        if (isNetworkCallbackSupported()) {
            this.mNetworkCallback = new NetworkStateCallback();
        } else {
            this.mBroadcastReceiver = new NetworkStateBroadcastReceiver();
        }
    }

    public static boolean isNetworkCallbackSupported() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public NetworkState getActiveNetworkState() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        boolean z = false;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean zIsActiveNetworkValidated = isActiveNetworkValidated();
        boolean zIsActiveNetworkMetered = this.mConnectivityManager.isActiveNetworkMetered();
        if (activeNetworkInfo != null && !activeNetworkInfo.isRoaming()) {
            z = true;
        }
        return new NetworkState(z2, zIsActiveNetworkValidated, zIsActiveNetworkMetered, z);
    }

    @VisibleForTesting
    public boolean isActiveNetworkValidated() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            NetworkCapabilities networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mConnectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                if (networkCapabilities.hasCapability(16)) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException e) {
            Logger.get().error(TAG, "Unable to validate active network", e);
            return false;
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void startTracking() {
        if (!isNetworkCallbackSupported()) {
            Logger.get().debug(TAG, "Registering broadcast receiver", new Throwable[0]);
            this.mAppContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            return;
        }
        try {
            Logger.get().debug(TAG, "Registering network callback", new Throwable[0]);
            this.mConnectivityManager.registerDefaultNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException e) {
            Logger.get().error(TAG, "Received exception while registering network callback", e);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void stopTracking() {
        if (!isNetworkCallbackSupported()) {
            Logger.get().debug(TAG, "Unregistering broadcast receiver", new Throwable[0]);
            this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
            return;
        }
        try {
            Logger.get().debug(TAG, "Unregistering network callback", new Throwable[0]);
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        } catch (IllegalArgumentException | SecurityException e) {
            Logger.get().error(TAG, "Received exception while unregistering network callback", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public NetworkState getInitialState() {
        return getActiveNetworkState();
    }
}
