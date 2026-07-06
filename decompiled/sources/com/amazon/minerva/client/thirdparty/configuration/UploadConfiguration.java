package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchBinarySerializer;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchTextSerializer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UploadConfiguration {
    public static final String TAG = "UploadConfiguration";
    public int mConnectTimeoutMillis;
    public String mIonFormat;
    public String mKpiRegion;
    public int mReadTimeoutMillis;
    public String mUrlEndpoint;
    public long mWakeLockTimeoutMillis;

    public UploadConfiguration(String str, String str2, int i, int i2, long j, String str3) {
        if (isValidInput(str, str2, i, i2, j, str3)) {
            this.mUrlEndpoint = str;
            this.mIonFormat = str2;
            this.mConnectTimeoutMillis = i;
            this.mReadTimeoutMillis = i2;
            this.mWakeLockTimeoutMillis = j;
            this.mKpiRegion = str3;
        }
    }

    public MetricBatchSerializer createMetricBatchSerializer() {
        return this.mIonFormat.equals("text/x-amzn-ion") ? new MetricBatchTextSerializer() : new MetricBatchBinarySerializer();
    }

    public int getConnectTimeoutMillis() {
        return this.mConnectTimeoutMillis;
    }

    public String getIonFormat() {
        return this.mIonFormat;
    }

    public String getKPIRegion() {
        return this.mKpiRegion;
    }

    public int getReadTimeoutMillis() {
        return this.mReadTimeoutMillis;
    }

    public String getUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    public long getWakeLockTimeoutMillis() {
        return this.mWakeLockTimeoutMillis;
    }

    public final boolean isValidInput(String str, String str2, long j, long j2, long j3, String str3) {
        if (str == null || str.length() == 0) {
            Log.e(TAG, String.format("Invalid urlEndpoint: %s.", str));
            return false;
        }
        if (str2 == null || str2.length() == 0) {
            Log.e(TAG, String.format("Invalid ionFormat: %s.", str2));
            return false;
        }
        if (j <= 0) {
            Log.e(TAG, String.format("Invalid connectTimeoutMillis: %d.", Long.valueOf(j)));
            return false;
        }
        if (j2 <= 0) {
            Log.e(TAG, String.format("Invalid readTimeoutMillis: %d.", Long.valueOf(j2)));
            return false;
        }
        if (j3 <= 0) {
            Log.e(TAG, String.format("Invalid wakeLockTimeoutMillis: %d.", Long.valueOf(j3)));
            return false;
        }
        if (str3 != null && str3.length() != 0) {
            return true;
        }
        Log.e(TAG, String.format("Invalid kpiRegion: %s.", str3));
        return false;
    }
}
