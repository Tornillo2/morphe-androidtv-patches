package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class HardwareConfigState {
    public static final File FD_SIZE_LIST = new File("/proc/self/fd");
    public static final int MAXIMUM_FDS_FOR_HARDWARE_CONFIGS_O = 700;
    public static final int MAXIMUM_FDS_FOR_HARDWARE_CONFIGS_P = 20000;
    public static final int MINIMUM_DECODES_BETWEEN_FD_CHECKS = 50;

    @VisibleForTesting
    public static final int MIN_HARDWARE_DIMENSION_O = 128;
    public static final int MIN_HARDWARE_DIMENSION_P = 0;
    public static volatile HardwareConfigState instance;

    @GuardedBy("this")
    public int decodesSinceLastFdCheck;
    public final int fdCountLimit;

    @GuardedBy("this")
    public boolean isFdSizeBelowHardwareLimit = true;
    public final boolean isHardwareConfigAllowedByDeviceModel = isHardwareConfigAllowedByDeviceModel();
    public final int minHardwareDimension;

    @VisibleForTesting
    public HardwareConfigState() {
        if (Build.VERSION.SDK_INT >= 28) {
            this.fdCountLimit = 20000;
            this.minHardwareDimension = 0;
        } else {
            this.fdCountLimit = MAXIMUM_FDS_FOR_HARDWARE_CONFIGS_O;
            this.minHardwareDimension = 128;
        }
    }

    public static HardwareConfigState getInstance() {
        if (instance == null) {
            synchronized (HardwareConfigState.class) {
                try {
                    if (instance == null) {
                        instance = new HardwareConfigState();
                    }
                } finally {
                }
            }
        }
        return instance;
    }

    public static boolean isHardwareConfigAllowedByDeviceModel() {
        String str = Build.MODEL;
        if (str == null || str.length() < 7) {
            return true;
        }
        String strSubstring = str.substring(0, 7);
        strSubstring.getClass();
        switch (strSubstring) {
            case "SM-A520":
            case "SM-G930":
            case "SM-G935":
            case "SM-G960":
            case "SM-G965":
            case "SM-J720":
            case "SM-N935":
                if (Build.VERSION.SDK_INT != 26) {
                }
                break;
        }
        return true;
    }

    public final synchronized boolean isFdSizeBelowHardwareLimit() {
        try {
            boolean z = true;
            int i = this.decodesSinceLastFdCheck + 1;
            this.decodesSinceLastFdCheck = i;
            if (i >= 50) {
                this.decodesSinceLastFdCheck = 0;
                int length = FD_SIZE_LIST.list().length;
                if (length >= this.fdCountLimit) {
                    z = false;
                }
                this.isFdSizeBelowHardwareLimit = z;
                if (!z && Log.isLoggable(Downsampler.TAG, 5)) {
                    Log.w(Downsampler.TAG, "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + this.fdCountLimit);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.isFdSizeBelowHardwareLimit;
    }

    public boolean isHardwareConfigAllowed(int i, int i2, boolean z, boolean z2) {
        int i3;
        return z && this.isHardwareConfigAllowedByDeviceModel && Build.VERSION.SDK_INT >= 26 && !z2 && i >= (i3 = this.minHardwareDimension) && i2 >= i3 && isFdSizeBelowHardwareLimit();
    }

    @TargetApi(26)
    public boolean setHardwareConfigIfAllowed(int i, int i2, BitmapFactory.Options options, boolean z, boolean z2) {
        boolean zIsHardwareConfigAllowed = isHardwareConfigAllowed(i, i2, z, z2);
        if (zIsHardwareConfigAllowed) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return zIsHardwareConfigAllowed;
    }
}
