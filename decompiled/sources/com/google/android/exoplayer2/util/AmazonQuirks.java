package com.google.android.exoplayer2.util;

import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AmazonQuirks {
    public static final String AMAZON = "Amazon";
    public static final int AUDIO_HARDWARE_LATENCY_FOR_TABLETS = 90000;
    public static final String DEVICEMODEL;
    public static final String FIRETV_GEN1_DEVICE_MODEL = "AFTB";
    public static final String FIRETV_GEN2_DEVICE_MODEL = "AFTS";
    public static final String FIRETV_STICK_DEVICE_MODEL = "AFTM";
    public static final String FIRETV_STICK_GEN2_DEVICE_MODEL = "AFTT";
    public static final String FIRE_PHONE_DEVICE_MODEL = "SD";
    public static final String KINDLE_TABLET_DEVICE_MODEL = "KF";
    public static final String MANUFACTURER;
    public static final String TAG = "AmazonQuirks";
    public static final boolean isAmazonDevice;
    public static final boolean isFirePhone;
    public static final boolean isFireTVGen1;
    public static final boolean isFireTVGen2;
    public static final boolean isFireTVStick;
    public static final boolean isKindleTablet;
    public static boolean skipProfileLevelCheck;

    static {
        String str = Build.MODEL;
        DEVICEMODEL = str;
        String str2 = Build.MANUFACTURER;
        MANUFACTURER = str2;
        boolean zEqualsIgnoreCase = str2.equalsIgnoreCase("Amazon");
        isAmazonDevice = zEqualsIgnoreCase;
        boolean z = false;
        isFireTVGen1 = zEqualsIgnoreCase && str.equalsIgnoreCase(FIRETV_GEN1_DEVICE_MODEL);
        isFireTVGen2 = zEqualsIgnoreCase && str.equalsIgnoreCase(FIRETV_GEN2_DEVICE_MODEL);
        isFireTVStick = zEqualsIgnoreCase && str.equalsIgnoreCase(FIRETV_STICK_DEVICE_MODEL);
        isKindleTablet = zEqualsIgnoreCase && str.startsWith(KINDLE_TABLET_DEVICE_MODEL);
        if (zEqualsIgnoreCase && str.startsWith(FIRE_PHONE_DEVICE_MODEL)) {
            z = true;
        }
        isFirePhone = z;
        loadForcedLogSettings();
    }

    public static int getAudioHWLatency() {
        return AUDIO_HARDWARE_LATENCY_FOR_TABLETS;
    }

    public static String getSystemProperty(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isAmazonDevice() {
        return isAmazonDevice;
    }

    public static boolean isDolbyPassthroughQuirkEnabled() {
        return isFireTVGen1Family();
    }

    public static boolean isFireTVGen1Family() {
        return isFireTVGen1 || isFireTVStick;
    }

    public static boolean isFireTVGen2() {
        return isFireTVGen2;
    }

    public static boolean isLatencyQuirkEnabled() {
        if (Util.SDK_INT <= 19) {
            return isKindleTablet || isFirePhone;
        }
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void loadForcedLogSettings() {
        /*
            java.lang.String r0 = "com.amazon.exoplayer.forcelog"
            java.lang.String r0 = getSystemProperty(r0)
            if (r0 == 0) goto L70
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L11
            goto L70
        L11:
            java.lang.String r1 = "#"
            java.lang.String[] r0 = r0.split(r1)     // Catch: java.lang.Exception -> L68
            int r1 = r0.length     // Catch: java.lang.Exception -> L68
            r2 = 0
            r3 = 0
        L1a:
            if (r3 >= r1) goto L70
            r4 = r0[r3]     // Catch: java.lang.Exception -> L68
            java.lang.String r5 = ":"
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.Exception -> L68
            r5 = r4[r2]     // Catch: java.lang.Exception -> L68
            com.google.android.exoplayer2.util.Logger$Module r5 = com.google.android.exoplayer2.util.Logger.Module.valueOf(r5)     // Catch: java.lang.Exception -> L68
            r6 = 1
            r4 = r4[r6]     // Catch: java.lang.Exception -> L68
            java.lang.String r4 = r4.toLowerCase()     // Catch: java.lang.Exception -> L68
            int r6 = r4.hashCode()     // Catch: java.lang.Exception -> L68
            switch(r6) {
                case 3237038: goto L57;
                case 3641990: goto L4d;
                case 96784904: goto L43;
                case 351107458: goto L39;
                default: goto L38;
            }     // Catch: java.lang.Exception -> L68
        L38:
            goto L61
        L39:
            java.lang.String r6 = "verbose"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> L68
            if (r4 == 0) goto L61
            r4 = 2
            goto L62
        L43:
            java.lang.String r6 = "error"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> L68
            if (r4 == 0) goto L61
            r4 = 6
            goto L62
        L4d:
            java.lang.String r6 = "warn"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> L68
            if (r4 == 0) goto L61
            r4 = 5
            goto L62
        L57:
            java.lang.String r6 = "info"
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> L68
            if (r4 == 0) goto L61
            r4 = 4
            goto L62
        L61:
            r4 = 3
        L62:
            com.google.android.exoplayer2.util.Logger.setLogLevel(r5, r4)     // Catch: java.lang.Exception -> L68
            int r3 = r3 + 1
            goto L1a
        L68:
            r0 = move-exception
            java.lang.String r1 = com.google.android.exoplayer2.util.AmazonQuirks.TAG
            java.lang.String r2 = "Could not set logging level."
            android.util.Log.e(r1, r2, r0)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.AmazonQuirks.loadForcedLogSettings():void");
    }

    public static boolean shouldSkipProfileLevelCheck() {
        return skipProfileLevelCheck;
    }

    public static void skipProfileLevelCheck(boolean z) {
        skipProfileLevelCheck = z;
    }

    public static boolean useDefaultPassthroughDecoder() {
        if (isFireTVGen1Family()) {
            android.util.Log.i(TAG, "Using platform Dolby decoder");
            return false;
        }
        android.util.Log.i(TAG, "Using default Dolby pass-through decoder");
        return true;
    }
}
