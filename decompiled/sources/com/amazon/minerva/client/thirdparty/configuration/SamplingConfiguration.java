package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SamplingConfiguration {
    public static final String TAG = "SamplingConfiguration";
    public int mDefaultSamplingRate;

    public SamplingConfiguration(int i) {
        if (isValidInput(i)) {
            this.mDefaultSamplingRate = i;
        } else {
            this.mDefaultSamplingRate = 100;
        }
    }

    public int getDefaultSamplingRate() {
        return this.mDefaultSamplingRate;
    }

    public final boolean isValidInput(int i) {
        if (i >= 0 && i <= 100) {
            return true;
        }
        Log.e(TAG, String.format("Invalid defaultSamplingRate: %d.", Integer.valueOf(i)));
        return false;
    }
}
