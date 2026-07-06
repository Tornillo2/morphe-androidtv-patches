package androidx.media3.exoplayer.scheduler;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Requirements implements Parcelable {
    public static final Parcelable.Creator<Requirements> CREATOR = new AnonymousClass1();
    public static final int DEVICE_CHARGING = 8;
    public static final int DEVICE_IDLE = 4;
    public static final int DEVICE_STORAGE_NOT_LOW = 16;
    public static final int NETWORK = 1;
    public static final int NETWORK_UNMETERED = 2;
    public final int requirements;

    /* JADX INFO: renamed from: androidx.media3.exoplayer.scheduler.Requirements$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Parcelable.Creator<Requirements> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Requirements createFromParcel(Parcel parcel) {
            return new Requirements(parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Requirements[] newArray(int i) {
            return new Requirements[i];
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequirementFlags {
    }

    public Requirements(int i) {
        this.requirements = (i & 2) != 0 ? i | 1 : i;
    }

    public static boolean isInternetConnectivityValidated(ConnectivityManager connectivityManager) {
        if (Util.SDK_INT < 24) {
            return true;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            return false;
        }
        try {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities != null) {
                if (networkCapabilities.hasCapability(16)) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException unused) {
            return true;
        }
    }

    public boolean checkRequirements(Context context) {
        return getNotMetRequirements(context) == 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && Requirements.class == obj.getClass() && this.requirements == ((Requirements) obj).requirements;
    }

    public Requirements filterRequirements(int i) {
        int i2 = this.requirements;
        int i3 = i & i2;
        return i3 == i2 ? this : new Requirements(i3);
    }

    public final int getNotMetNetworkRequirements(Context context) {
        if (!isNetworkRequired()) {
            return 0;
        }
        Object systemService = context.getSystemService("connectivity");
        systemService.getClass();
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected() && isInternetConnectivityValidated(connectivityManager)) ? (isUnmeteredNetworkRequired() && connectivityManager.isActiveNetworkMetered()) ? 2 : 0 : this.requirements & 3;
    }

    public int getNotMetRequirements(Context context) {
        int notMetNetworkRequirements = getNotMetNetworkRequirements(context);
        if (isChargingRequired() && !isDeviceCharging(context)) {
            notMetNetworkRequirements |= 8;
        }
        if (isIdleRequired() && !isDeviceIdle(context)) {
            notMetNetworkRequirements |= 4;
        }
        return (!isStorageNotLowRequired() || isStorageNotLow(context)) ? notMetNetworkRequirements : notMetNetworkRequirements | 16;
    }

    public int getRequirements() {
        return this.requirements;
    }

    public int hashCode() {
        return this.requirements;
    }

    public boolean isChargingRequired() {
        return (this.requirements & 8) != 0;
    }

    public final boolean isDeviceCharging(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return false;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    public final boolean isDeviceIdle(Context context) {
        Object systemService = context.getSystemService("power");
        systemService.getClass();
        PowerManager powerManager = (PowerManager) systemService;
        int i = Util.SDK_INT;
        return i >= 23 ? powerManager.isDeviceIdleMode() : i >= 20 ? !powerManager.isInteractive() : !powerManager.isScreenOn();
    }

    public boolean isIdleRequired() {
        return (this.requirements & 4) != 0;
    }

    public boolean isNetworkRequired() {
        return (this.requirements & 1) != 0;
    }

    public final boolean isStorageNotLow(Context context) {
        return context.registerReceiver(null, new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW")) == null;
    }

    public boolean isStorageNotLowRequired() {
        return (this.requirements & 16) != 0;
    }

    public boolean isUnmeteredNetworkRequired() {
        return (this.requirements & 2) != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.requirements);
    }
}
