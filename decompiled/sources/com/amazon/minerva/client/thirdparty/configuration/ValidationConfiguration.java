package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ValidationConfiguration {
    public static final String TAG = "ValidationConfiguration";
    public int mMaxKeySizeBytes;
    public int mMaxKeyValuePairCount;
    public int mMaxMetricEventSizeBytes;
    public int mMaxValueSizeBytes;

    public ValidationConfiguration(int i, int i2, int i3, int i4) {
        if (isValidInput(i, i2, i3, i4)) {
            this.mMaxKeyValuePairCount = i;
            this.mMaxMetricEventSizeBytes = i2;
            this.mMaxKeySizeBytes = i3;
            this.mMaxValueSizeBytes = i4;
        }
    }

    public int getMaxKeySizeBytes() {
        return this.mMaxKeySizeBytes;
    }

    public int getMaxKeyValuePairCount() {
        return this.mMaxKeyValuePairCount;
    }

    public int getMaxMetricEventSizeBytes() {
        return this.mMaxMetricEventSizeBytes;
    }

    public int getMaxValueSizeBytes() {
        return this.mMaxValueSizeBytes;
    }

    public final boolean isValidInput(int i, int i2, int i3, int i4) {
        if (i <= 0) {
            Log.e(TAG, String.format("Invalid maxKeyValuePairCount: %d", Integer.valueOf(i)));
            return false;
        }
        if (i2 <= 0) {
            Log.e(TAG, String.format("Invalid maxMetricEventSizeBytes: %d", Integer.valueOf(i2)));
            return false;
        }
        if (i3 <= 0) {
            Log.e(TAG, String.format("Invalid maxKeySizeBytes: %d", Integer.valueOf(i3)));
            return false;
        }
        if (i4 > 0) {
            return true;
        }
        Log.e(TAG, String.format("Invalid maxValueSizeBytes: %d", Integer.valueOf(i4)));
        return false;
    }
}
