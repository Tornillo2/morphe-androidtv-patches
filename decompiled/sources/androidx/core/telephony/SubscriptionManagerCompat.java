package androidx.core.telephony;

import android.os.Build;
import android.telephony.SubscriptionManager;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat$LegacyServiceMapHolder$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
public class SubscriptionManagerCompat {
    public static Method sGetSlotIndexMethod;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static int getSlotIndex(int i) {
            return SubscriptionManager.getSlotIndex(i);
        }
    }

    public static int getSlotIndex(int i) {
        if (i == -1) {
            return -1;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            return Api29Impl.getSlotIndex(i);
        }
        try {
            if (sGetSlotIndexMethod == null) {
                Class<?> cls = Integer.TYPE;
                if (i2 >= 26) {
                    sGetSlotIndexMethod = ContextCompat$LegacyServiceMapHolder$$ExternalSyntheticApiModelOutline0.m().getDeclaredMethod("getSlotIndex", cls);
                } else {
                    sGetSlotIndexMethod = ContextCompat$LegacyServiceMapHolder$$ExternalSyntheticApiModelOutline0.m().getDeclaredMethod("getSlotId", cls);
                }
                sGetSlotIndexMethod.setAccessible(true);
            }
            Integer num = (Integer) sGetSlotIndexMethod.invoke(null, Integer.valueOf(i));
            if (num != null) {
                return num.intValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return -1;
    }
}
