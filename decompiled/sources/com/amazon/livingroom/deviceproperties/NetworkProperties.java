package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.android.billingclient.api.ProxyBillingActivity;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class NetworkProperties {
    public static final String CONNECTION_TYPE_LAN = "lan";
    public static final String CONNECTION_TYPE_WIFI = "wifi";
    public Context context;

    @Inject
    public NetworkProperties(@ApplicationContext Context context) {
        this.context = context;
    }

    public int getConnectionStrength() {
        try {
            if (getConnectionType().equals(CONNECTION_TYPE_WIFI)) {
                return WifiManager.calculateSignalLevel(((WifiManager) this.context.getApplicationContext().getSystemService(CONNECTION_TYPE_WIFI)).getConnectionInfo().getRssi(), ProxyBillingActivity.REQUEST_CODE_IN_APP_MESSAGE_FLOW);
            }
            return 100;
        } catch (Exception unused) {
            return 0;
        }
    }

    public final String getConnectionType() {
        try {
            int type = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
            if (type == 9) {
                return CONNECTION_TYPE_LAN;
            }
            if (type == 1) {
                return CONNECTION_TYPE_WIFI;
            }
            return "other[" + type + "]";
        } catch (Exception unused) {
            return "disconnected";
        }
    }

    public boolean isWiFiNetworkConnection() {
        return getConnectionType().equals(CONNECTION_TYPE_WIFI);
    }
}
