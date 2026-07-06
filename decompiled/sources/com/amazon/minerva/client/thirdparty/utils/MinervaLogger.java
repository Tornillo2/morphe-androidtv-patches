package com.amazon.minerva.client.thirdparty.utils;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MinervaLogger {
    public static final int MAX_TAG_LENGTH_ON_FOS6_AND_BELOW = 23;
    public static final String TAG = "MinervaLogger";
    public String mTagToUse;

    public MinervaLogger(String str) {
        this.mTagToUse = makeTagSafe(str);
    }

    public void debug(String str) {
        if (Log.isLoggable(this.mTagToUse, 3)) {
            Log.d(this.mTagToUse, str);
        }
    }

    public void error(String str) {
        Log.e(this.mTagToUse, str);
    }

    public void info(String str) {
        Log.i(this.mTagToUse, str);
    }

    public boolean isDebugEnabled() {
        return Log.isLoggable(this.mTagToUse, 3);
    }

    public final String makeTagSafe(String str) {
        if (str == null) {
            Log.e(TAG, "Null tag passed in, using empty instead. Make sure to change, null tags should not be used");
            return "";
        }
        if (str.length() <= 23) {
            return str;
        }
        String strSubstring = str.substring(0, 23);
        Log.i(TAG, "Tag truncated, used to be " + str + ", now is " + strSubstring);
        return strSubstring;
    }

    public void verbose(String str) {
        if (Log.isLoggable(this.mTagToUse, 2)) {
            Log.v(this.mTagToUse, str);
        }
    }

    public void warn(String str) {
        Log.w(this.mTagToUse, str);
    }

    public void wtf(String str) {
        Log.wtf(this.mTagToUse, str);
    }
}
