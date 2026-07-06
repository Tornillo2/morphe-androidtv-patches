package com.amazon.livingroom.localisation;

import com.amazon.livingroom.appstartupconfig.AppStartupConfigProvider;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class CustomerLocaleProvider {
    public static final String TAG = "DeviceLocaleProvider";
    public final AppStartupConfigProvider appStartupConfigProvider;
    public final DeviceLocaleProvider deviceLocaleProvider;

    @Inject
    public CustomerLocaleProvider(DeviceLocaleProvider deviceLocaleProvider, AppStartupConfigProvider appStartupConfigProvider) {
        this.deviceLocaleProvider = deviceLocaleProvider;
        this.appStartupConfigProvider = appStartupConfigProvider;
    }

    public String getUXLocale() {
        try {
            return ((JSONObject) ((JSONObject) this.appStartupConfigProvider.getAppStartupConfig(5L, TimeUnit.SECONDS).get("customerConfig")).get("locale")).optString("uxLocale");
        } catch (NullPointerException | JSONException unused) {
            Log.w(TAG, "uxLocale is empty");
            return this.deviceLocaleProvider.getCurrentLocaleName();
        }
    }
}
