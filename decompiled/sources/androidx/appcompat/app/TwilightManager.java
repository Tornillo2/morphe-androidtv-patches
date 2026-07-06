package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TwilightManager {
    public static final int SUNRISE = 6;
    public static final int SUNSET = 22;
    public static final String TAG = "TwilightManager";
    public static TwilightManager sInstance;
    public final Context mContext;
    public final LocationManager mLocationManager;
    public final TwilightState mTwilightState = new TwilightState();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TwilightState {
        public boolean isNight;
        public long nextUpdate;
    }

    @VisibleForTesting
    public TwilightManager(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    public static TwilightManager getInstance(@NonNull Context context) {
        if (sInstance == null) {
            Context applicationContext = context.getApplicationContext();
            sInstance = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return sInstance;
    }

    @VisibleForTesting
    public static void setInstance(TwilightManager twilightManager) {
        sInstance = twilightManager;
    }

    @SuppressLint({"MissingPermission"})
    public final Location getLastKnownLocation() {
        Location lastKnownLocationForProvider = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? getLastKnownLocationForProvider("network") : null;
        Location lastKnownLocationForProvider2 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 ? getLastKnownLocationForProvider("gps") : null;
        return (lastKnownLocationForProvider2 == null || lastKnownLocationForProvider == null) ? lastKnownLocationForProvider2 != null ? lastKnownLocationForProvider2 : lastKnownLocationForProvider : lastKnownLocationForProvider2.getTime() > lastKnownLocationForProvider.getTime() ? lastKnownLocationForProvider2 : lastKnownLocationForProvider;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final Location getLastKnownLocationForProvider(String str) {
        try {
            if (this.mLocationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d(TAG, "Failed to get last known location", e);
            return null;
        }
    }

    public boolean isNight() {
        TwilightState twilightState = this.mTwilightState;
        if (isStateValid()) {
            return twilightState.isNight;
        }
        Location lastKnownLocation = getLastKnownLocation();
        if (lastKnownLocation != null) {
            updateState(lastKnownLocation);
            return twilightState.isNight;
        }
        Log.i(TAG, "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    public final boolean isStateValid() {
        return this.mTwilightState.nextUpdate > System.currentTimeMillis();
    }

    public final void updateState(@NonNull Location location) {
        long j;
        TwilightState twilightState = this.mTwilightState;
        long jCurrentTimeMillis = System.currentTimeMillis();
        TwilightCalculator twilightCalculator = TwilightCalculator.getInstance();
        twilightCalculator.calculateTwilight(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        twilightCalculator.calculateTwilight(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = twilightCalculator.state == 1;
        long j2 = twilightCalculator.sunrise;
        long j3 = twilightCalculator.sunset;
        twilightCalculator.calculateTwilight(jCurrentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j4 = twilightCalculator.sunrise;
        if (j2 == -1 || j3 == -1) {
            j = jCurrentTimeMillis + 43200000;
        } else {
            if (jCurrentTimeMillis > j3) {
                j2 = j4;
            } else if (jCurrentTimeMillis > j2) {
                j2 = j3;
            }
            j = j2 + 60000;
        }
        twilightState.isNight = z;
        twilightState.nextUpdate = j;
    }
}
