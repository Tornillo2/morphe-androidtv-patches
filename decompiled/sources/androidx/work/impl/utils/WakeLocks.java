package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.work.Logger;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WakeLocks {
    public static final String TAG = Logger.tagWithPrefix("WakeLocks");
    public static final WeakHashMap<PowerManager.WakeLock, String> sWakeLocks = new WeakHashMap<>();

    public static void checkWakeLocks() {
        HashMap map = new HashMap();
        WeakHashMap<PowerManager.WakeLock, String> weakHashMap = sWakeLocks;
        synchronized (weakHashMap) {
            map.putAll(weakHashMap);
        }
        for (PowerManager.WakeLock wakeLock : map.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get().warning(TAG, String.format("WakeLock held for %s", map.get(wakeLock)), new Throwable[0]);
            }
        }
    }

    public static PowerManager.WakeLock newWakeLock(@NonNull Context context, @NonNull String tag) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        String strM = RemoteInput$$ExternalSyntheticOutline0.m("WorkManager: ", tag);
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, strM);
        WeakHashMap<PowerManager.WakeLock, String> weakHashMap = sWakeLocks;
        synchronized (weakHashMap) {
            weakHashMap.put(wakeLockNewWakeLock, strM);
        }
        return wakeLockNewWakeLock;
    }
}
