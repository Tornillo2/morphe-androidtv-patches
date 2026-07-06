package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Log;
import com.amazon.livingroom.deviceproperties.NetworkProperties;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WifiLockManager {
    public static final String TAG = "WifiLockManager";
    public static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    public boolean enabled;
    public boolean stayAwake;

    @Nullable
    public WifiManager.WifiLock wifiLock;

    @Nullable
    public final WifiManager wifiManager;

    public WifiLockManager(Context context) {
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService(NetworkProperties.CONNECTION_TYPE_WIFI);
    }

    public void setEnabled(boolean z) {
        if (z && this.wifiLock == null) {
            WifiManager wifiManager = this.wifiManager;
            if (wifiManager == null) {
                Log.w("WifiLockManager", "WifiManager is null, therefore not creating the WifiLock.");
                return;
            } else {
                WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager.createWifiLock(3, "ExoPlayer:WifiLockManager");
                this.wifiLock = wifiLockCreateWifiLock;
                wifiLockCreateWifiLock.setReferenceCounted(false);
            }
        }
        this.enabled = z;
        updateWifiLock();
    }

    public void setStayAwake(boolean z) {
        this.stayAwake = z;
        updateWifiLock();
    }

    public final void updateWifiLock() {
        WifiManager.WifiLock wifiLock = this.wifiLock;
        if (wifiLock == null) {
            return;
        }
        if (this.enabled && this.stayAwake) {
            wifiLock.acquire();
        } else {
            wifiLock.release();
        }
    }
}
