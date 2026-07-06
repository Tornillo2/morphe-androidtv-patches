package com.amazon.minerva.client.thirdparty.transport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;
import com.amazon.livingroom.deviceproperties.NetworkProperties;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIConstant;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricsTransporter {
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DEVICE_TYPE_ID = "x-amz-device-type";
    public static final String GZIP = "gzip";
    public static final String OAUTH_TOKEN_HEADER = "x-amz-access-token";
    public static final String REQUEST_METHOD = "POST";
    public static final String TAG = "MetricsTransporter";
    public static final int sByteArraySize = 8192;
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final PowerManager mPowerManager;
    public ServiceKPIReporter mServiceKPIReporter;
    public final WifiManager mWifiManager;

    public MetricsTransporter(Context context, MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(NetworkProperties.CONNECTION_TYPE_WIFI);
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mServiceKPIReporter = serviceKPIReporter;
    }

    public final void acquireWakeLock(PowerManager.WakeLock wakeLock, long j) {
        if (wakeLock != null) {
            wakeLock.acquire(j);
        }
    }

    public final void acquireWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock != null) {
            wifiLock.acquire();
        }
    }

    public final void addHeaders(HttpURLConnection httpURLConnection) {
        String ionFormat = this.mMetricsConfigurationHelper.getUploadConfiguration().getIonFormat();
        String deviceType = CustomDeviceUtil.getInstance().getDeviceType();
        httpURLConnection.setRequestProperty("Content-Type", ionFormat);
        httpURLConnection.setRequestProperty(DEVICE_TYPE_ID, deviceType);
        httpURLConnection.setRequestProperty("Accept", ionFormat);
    }

    public final byte[] consumeResponsePayload(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        Log.i(TAG, "Consuming response payload.");
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public final PowerManager.WakeLock createWakeLock() {
        String str = TAG;
        Log.i(str, "Acquiring wakelock.");
        return this.mPowerManager.newWakeLock(1, str + ":HTTPMetricsTransportWakeLock");
    }

    public final WifiManager.WifiLock createWifiLock() {
        String str = TAG;
        Log.i(str, "Acquiring wifi lock.");
        return this.mWifiManager.createWifiLock(3, str + ":HTTPMetricsTransportWifiLock");
    }

    public final String getOAuthToken() {
        try {
            CustomDeviceUtil.getInstance().getOAuthProvider().getAccessToken();
            throw null;
        } catch (Exception e) {
            Log.e(TAG, "Exception getting the OAuth Token: " + e);
            return null;
        }
    }

    public final UploadResult handleResponse(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseCode == 200) {
            Log.i(TAG, String.format("Successfully uploaded metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult(UploadResult.SUCCESS, responseMessage, consumeResponsePayload(httpURLConnection.getInputStream()));
        }
        if (responseCode >= 500) {
            Log.w(TAG, String.format("Server error when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult(UploadResult.SERVER_ERROR, responseMessage);
        }
        if (responseCode >= 400) {
            Log.e(TAG, String.format("Client error when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult(UploadResult.CLIENT_ERROR, responseMessage);
        }
        Log.w(TAG, String.format("Unexpected response code when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
        return new UploadResult(UploadResult.UNEXPECTED_ERROR, responseMessage);
    }

    public boolean hasNetworkConnectivity() {
        return isDeviceOnline();
    }

    public final boolean isDeviceOnline() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final void releaseWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        wakeLock.release();
    }

    public final void releaseWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        wifiLock.release();
    }

    public UploadResult transmit(SerializedBatch serializedBatch) throws Throwable {
        WifiManager.WifiLock wifiLockCreateWifiLock;
        PowerManager.WakeLock wakeLockCreateWakeLock;
        HttpURLConnection httpURLConnection;
        if (serializedBatch == null || serializedBatch.getBatchContent() == null || serializedBatch.getBatchContent().length == 0) {
            Log.e(TAG, "Transmitted metric batch is invalid.");
            return new UploadResult(UploadResult.UNEXPECTED_ERROR, "Invalid Batch");
        }
        if (!isDeviceOnline()) {
            Log.d(TAG, "Abort transmission due to no usable connection.");
            return new UploadResult(UploadResult.NO_CONNECTION, "No Connection");
        }
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                wifiLockCreateWifiLock = createWifiLock();
                try {
                    acquireWifiLock(wifiLockCreateWifiLock);
                    wakeLockCreateWakeLock = createWakeLock();
                    try {
                        acquireWakeLock(wakeLockCreateWakeLock, this.mMetricsConfigurationHelper.getUploadConfiguration().getWakeLockTimeoutMillis());
                        String urlEndpoint = this.mMetricsConfigurationHelper.getUploadConfiguration().getUrlEndpoint();
                        String hashedDeviceType = CustomDeviceUtil.getInstance().getHashedDeviceType();
                        Log.i(TAG, "Using device type: " + hashedDeviceType + ", region: " + serializedBatch.getRegion());
                        httpURLConnection = (HttpURLConnection) new URL(urlEndpoint.replace("{deviceType}", hashedDeviceType).replace("{region}", serializedBatch.getRegion())).openConnection();
                    } catch (MalformedURLException e) {
                        e = e;
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Exception e3) {
                        e = e3;
                    }
                    try {
                        try {
                            getOAuthToken();
                            byte[] batchContent = serializedBatch.getBatchContent();
                            try {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                                gZIPOutputStream.write(batchContent);
                                gZIPOutputStream.flush();
                                gZIPOutputStream.finish();
                                gZIPOutputStream.close();
                                byteArrayOutputStream.flush();
                                batchContent = byteArrayOutputStream.toByteArray();
                                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
                            } catch (Exception e4) {
                                Log.w(TAG, "Error gzip compressing batch, falling back to uncompressed", e4);
                            }
                            httpURLConnection.setConnectTimeout(this.mMetricsConfigurationHelper.getUploadConfiguration().getConnectTimeoutMillis());
                            httpURLConnection.setReadTimeout(this.mMetricsConfigurationHelper.getUploadConfiguration().getReadTimeoutMillis());
                            httpURLConnection.setFixedLengthStreamingMode(batchContent.length);
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setRequestMethod(REQUEST_METHOD);
                            addHeaders(httpURLConnection);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                            bufferedOutputStream.write(batchContent);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            UploadResult uploadResultHandleResponse = handleResponse(httpURLConnection);
                            httpURLConnection.disconnect();
                            Log.i(TAG, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                            return uploadResultHandleResponse;
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection2 = httpURLConnection;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            Log.i(TAG, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                            throw th;
                        }
                    } catch (MalformedURLException e5) {
                        e = e5;
                        httpURLConnection2 = httpURLConnection;
                        String str = TAG;
                        Log.w(str, "MalformedURLException thrown when creating endpointURL", e);
                        UploadResult uploadResult = new UploadResult(UploadResult.UNEXPECTED_ERROR, "Invalid EndpointURL");
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        Log.i(str, "Releasing wake and wifi locks.");
                        releaseWakeLock(wakeLockCreateWakeLock);
                        releaseWifiLock(wifiLockCreateWifiLock);
                        this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                        return uploadResult;
                    } catch (IOException e6) {
                        e = e6;
                        httpURLConnection2 = httpURLConnection;
                        String str2 = TAG;
                        Log.w(str2, "IOException thrown when uploading metrics", e);
                        UploadResult uploadResult2 = new UploadResult(UploadResult.UNEXPECTED_ERROR, "IOException");
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        Log.i(str2, "Releasing wake and wifi locks.");
                        releaseWakeLock(wakeLockCreateWakeLock);
                        releaseWifiLock(wifiLockCreateWifiLock);
                        this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                        return uploadResult2;
                    } catch (Exception e7) {
                        e = e7;
                        httpURLConnection2 = httpURLConnection;
                        String str3 = TAG;
                        Log.i(str3, "Exception .... " + e);
                        UploadResult uploadResult3 = new UploadResult(UploadResult.UNEXPECTED_ERROR, "Unknown exception");
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        Log.i(str3, "Releasing wake and wifi locks.");
                        releaseWakeLock(wakeLockCreateWakeLock);
                        releaseWifiLock(wifiLockCreateWifiLock);
                        this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.metricName, KPIConstant.KPI_METRIC_GROUP_ID, 1L);
                        return uploadResult3;
                    }
                } catch (MalformedURLException e8) {
                    e = e8;
                    wakeLockCreateWakeLock = null;
                } catch (IOException e9) {
                    e = e9;
                    wakeLockCreateWakeLock = null;
                } catch (Exception e10) {
                    e = e10;
                    wakeLockCreateWakeLock = null;
                } catch (Throwable th2) {
                    th = th2;
                    wakeLockCreateWakeLock = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (MalformedURLException e11) {
            e = e11;
            wifiLockCreateWifiLock = null;
            wakeLockCreateWakeLock = null;
        } catch (IOException e12) {
            e = e12;
            wifiLockCreateWifiLock = null;
            wakeLockCreateWakeLock = null;
        } catch (Exception e13) {
            e = e13;
            wifiLockCreateWifiLock = null;
            wakeLockCreateWakeLock = null;
        } catch (Throwable th4) {
            th = th4;
            wifiLockCreateWifiLock = null;
            wakeLockCreateWakeLock = null;
        }
    }
}
