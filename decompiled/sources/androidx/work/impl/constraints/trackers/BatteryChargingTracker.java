package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public static final String TAG = Logger.tagWithPrefix("BatteryChrgTracker");

    public BatteryChargingTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        if (Build.VERSION.SDK_INT >= 23) {
            intentFilter.addAction("android.os.action.CHARGING");
            intentFilter.addAction("android.os.action.DISCHARGING");
            return intentFilter;
        }
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        return intentFilter;
    }

    public final boolean isBatteryChangedIntentCharging(Intent intent) {
        if (Build.VERSION.SDK_INT < 23) {
            return intent.getIntExtra("plugged", 0) != 0;
        }
        int intExtra = intent.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0026  */
    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBroadcastReceive(android.content.Context r6, @androidx.annotation.NonNull android.content.Intent r7) {
        /*
            r5 = this;
            java.lang.String r6 = r7.getAction()
            if (r6 != 0) goto L7
            goto L54
        L7:
            androidx.work.Logger r7 = androidx.work.Logger.get()
            java.lang.String r0 = androidx.work.impl.constraints.trackers.BatteryChargingTracker.TAG
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r6
            java.lang.String r4 = "Received %s"
            java.lang.String r2 = java.lang.String.format(r4, r2)
            java.lang.Throwable[] r4 = new java.lang.Throwable[r3]
            r7.debug(r0, r2, r4)
            int r7 = r6.hashCode()
            r0 = -1
            switch(r7) {
                case -1886648615: goto L47;
                case -54942926: goto L3e;
                case 948344062: goto L33;
                case 1019184907: goto L28;
                default: goto L26;
            }
        L26:
            r1 = -1
            goto L51
        L28:
            java.lang.String r7 = "android.intent.action.ACTION_POWER_CONNECTED"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L31
            goto L26
        L31:
            r1 = 3
            goto L51
        L33:
            java.lang.String r7 = "android.os.action.CHARGING"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L3c
            goto L26
        L3c:
            r1 = 2
            goto L51
        L3e:
            java.lang.String r7 = "android.os.action.DISCHARGING"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L51
            goto L26
        L47:
            java.lang.String r7 = "android.intent.action.ACTION_POWER_DISCONNECTED"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L50
            goto L26
        L50:
            r1 = 0
        L51:
            switch(r1) {
                case 0: goto L67;
                case 1: goto L61;
                case 2: goto L5b;
                case 3: goto L55;
                default: goto L54;
            }
        L54:
            return
        L55:
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r5.setState(r6)
            return
        L5b:
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r5.setState(r6)
            return
        L61:
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r5.setState(r6)
            return
        L67:
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r5.setState(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.trackers.BatteryChargingTracker.onBroadcastReceive(android.content.Context, android.content.Intent):void");
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean getInitialState() {
        Intent intentRegisterReceiver = this.mAppContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver != null) {
            return Boolean.valueOf(isBatteryChangedIntentCharging(intentRegisterReceiver));
        }
        Logger.get().error(TAG, "getInitialState - null intent received", new Throwable[0]);
        return null;
    }
}
