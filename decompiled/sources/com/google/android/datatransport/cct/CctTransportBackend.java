package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransporter;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo;
import com.google.android.datatransport.cct.internal.AutoValue_BatchedLogRequest;
import com.google.android.datatransport.cct.internal.AutoValue_ClientInfo;
import com.google.android.datatransport.cct.internal.AutoValue_LogEvent;
import com.google.android.datatransport.cct.internal.AutoValue_LogRequest;
import com.google.android.datatransport.cct.internal.AutoValue_LogResponse;
import com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Function;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.common.net.HttpHeaders;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CctTransportBackend implements TransportBackend {
    public static final String ACCEPT_ENCODING_HEADER_KEY = "Accept-Encoding";
    public static final String API_KEY_HEADER_KEY = "X-Goog-Api-Key";
    public static final int CONNECTION_TIME_OUT = 30000;
    public static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    public static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    public static final String GZIP_CONTENT_ENCODING = "gzip";
    public static final int INVALID_VERSION_CODE = -1;
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String KEY_APPLICATION_BUILD = "application_build";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_DEVICE = "device";
    public static final String KEY_FINGERPRINT = "fingerprint";
    public static final String KEY_HARDWARE = "hardware";
    public static final String KEY_LOCALE = "locale";
    public static final String KEY_MANUFACTURER = "manufacturer";
    public static final String KEY_MCC_MNC = "mcc_mnc";

    @VisibleForTesting
    public static final String KEY_MOBILE_SUBTYPE = "mobile-subtype";
    public static final String KEY_MODEL = "model";

    @VisibleForTesting
    public static final String KEY_NETWORK_TYPE = "net-type";
    public static final String KEY_OS_BUILD = "os-uild";
    public static final String KEY_PRODUCT = "product";
    public static final String KEY_SDK_VERSION = "sdk-version";
    public static final String KEY_TIMEZONE_OFFSET = "tz-offset";
    public static final String LOG_TAG = "CctTransportBackend";
    public static final int READ_TIME_OUT = 130000;
    public final Context applicationContext;
    public final ConnectivityManager connectivityManager;
    public final DataEncoder dataEncoder;
    public final URL endPoint;
    public final int readTimeout;
    public final Clock uptimeClock;
    public final Clock wallTimeClock;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class HttpRequest {

        @Nullable
        public final String apiKey;
        public final BatchedLogRequest requestBody;
        public final URL url;

        public HttpRequest(URL url, BatchedLogRequest batchedLogRequest, @Nullable String str) {
            this.url = url;
            this.requestBody = batchedLogRequest;
            this.apiKey = str;
        }

        public HttpRequest withUrl(URL url) {
            return new HttpRequest(url, this.requestBody, this.apiKey);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class HttpResponse {
        public final int code;
        public final long nextRequestMillis;

        @Nullable
        public final URL redirectUrl;

        public HttpResponse(int i, @Nullable URL url, long j) {
            this.code = i;
            this.redirectUrl = url;
            this.nextRequestMillis = j;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$ZhERf3WZ-xxKL1vqOg2FJv71EaQ, reason: not valid java name */
    public static /* synthetic */ HttpRequest m348$r8$lambda$ZhERf3WZxxKL1vqOg2FJv71EaQ(HttpRequest httpRequest, HttpResponse httpResponse) {
        URL url = httpResponse.redirectUrl;
        if (url == null) {
            return null;
        }
        Logging.d(LOG_TAG, "Following redirect to: %s", url);
        return httpRequest.withUrl(httpResponse.redirectUrl);
    }

    public CctTransportBackend(Context context, Clock clock, Clock clock2, int i) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.endPoint = parseUrlOrThrow(CCTDestination.DEFAULT_END_POINT);
        this.uptimeClock = clock2;
        this.wallTimeClock = clock;
        this.readTimeout = i;
    }

    public static int getNetSubtypeValue(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.value;
        }
        int subtype = networkInfo.getSubtype();
        if (subtype == -1) {
            return NetworkConnectionInfo.MobileSubtype.COMBINED.value;
        }
        if (NetworkConnectionInfo.MobileSubtype.forNumber(subtype) != null) {
            return subtype;
        }
        return 0;
    }

    public static int getNetTypeValue(NetworkInfo networkInfo) {
        return networkInfo == null ? NetworkConnectionInfo.NetworkType.NONE.value : networkInfo.getType();
    }

    public static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logging.e(LOG_TAG, "Unable to find version code for package", e);
            return -1;
        }
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    @VisibleForTesting
    public static long getTzOffset() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    public static InputStream maybeUnGzip(InputStream inputStream, String str) throws IOException {
        return "gzip".equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    public static URL parseUrlOrThrow(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Invalid url: ", str), e);
        }
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal decorate(EventInternal eventInternal) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        EventInternal.Builder builder = eventInternal.toBuilder();
        builder.addMetadata(KEY_SDK_VERSION, Build.VERSION.SDK_INT);
        builder.addMetadata(KEY_MODEL, Build.MODEL);
        builder.addMetadata("hardware", Build.HARDWARE);
        builder.addMetadata(KEY_DEVICE, Build.DEVICE);
        builder.addMetadata(KEY_PRODUCT, Build.PRODUCT);
        builder.addMetadata(KEY_OS_BUILD, Build.ID);
        builder.addMetadata(KEY_MANUFACTURER, Build.MANUFACTURER);
        builder.addMetadata(KEY_FINGERPRINT, Build.FINGERPRINT);
        builder.addMetadata(KEY_TIMEZONE_OFFSET, getTzOffset());
        builder.addMetadata(KEY_NETWORK_TYPE, getNetTypeValue(activeNetworkInfo));
        builder.addMetadata(KEY_MOBILE_SUBTYPE, getNetSubtypeValue(activeNetworkInfo));
        builder.addMetadata(KEY_COUNTRY, Locale.getDefault().getCountry());
        builder.addMetadata("locale", Locale.getDefault().getLanguage());
        builder.addMetadata(KEY_MCC_MNC, getTelephonyManager(this.applicationContext).getSimOperator());
        builder.addMetadata(KEY_APPLICATION_BUILD, Integer.toString(getPackageVersionCode(this.applicationContext)));
        return builder.build();
    }

    public final HttpResponse doSend(HttpRequest httpRequest) throws IOException {
        Logging.i(LOG_TAG, "Making request to: %s", httpRequest.url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpRequest.url.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.readTimeout);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod(MetricsTransporter.REQUEST_METHOD);
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", "3.1.8"));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = httpRequest.apiKey;
        if (str != null) {
            httpURLConnection.setRequestProperty(API_KEY_HEADER_KEY, str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    this.dataEncoder.encode(httpRequest.requestBody, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    Logging.i(LOG_TAG, "Status Code: %d", Integer.valueOf(responseCode));
                    Logging.d(LOG_TAG, "Content-Type: %s", httpURLConnection.getHeaderField("Content-Type"));
                    Logging.d(LOG_TAG, "Content-Encoding: %s", httpURLConnection.getHeaderField("Content-Encoding"));
                    if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                        return new HttpResponse(responseCode, new URL(httpURLConnection.getHeaderField(HttpHeaders.LOCATION)), 0L);
                    }
                    if (responseCode != 200) {
                        return new HttpResponse(responseCode, null, 0L);
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        InputStream inputStreamMaybeUnGzip = maybeUnGzip(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                        try {
                            HttpResponse httpResponse = new HttpResponse(responseCode, null, ((AutoValue_LogResponse) LogResponse.fromJson(new BufferedReader(new InputStreamReader(inputStreamMaybeUnGzip)))).nextRequestWaitMillis);
                            if (inputStreamMaybeUnGzip != null) {
                                inputStreamMaybeUnGzip.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return httpResponse;
                        } finally {
                        }
                    } catch (Throwable th) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } finally {
                }
            } catch (Throwable th3) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (EncodingException e) {
            e = e;
            Logging.e(LOG_TAG, "Couldn't encode request, returning with 400", e);
            return new HttpResponse(400, null, 0L);
        } catch (ConnectException e2) {
            e = e2;
            Logging.e(LOG_TAG, "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, null, 0L);
        } catch (UnknownHostException e3) {
            e = e3;
            Logging.e(LOG_TAG, "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, null, 0L);
        } catch (IOException e4) {
            e = e4;
            Logging.e(LOG_TAG, "Couldn't encode request, returning with 400", e);
            return new HttpResponse(400, null, 0L);
        }
    }

    public final BatchedLogRequest getRequestBody(BackendRequest backendRequest) {
        LogEvent.Builder builderProtoBuilder;
        HashMap map = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String transportName = eventInternal.getTransportName();
            if (map.containsKey(transportName)) {
                ((List) map.get(transportName)).add(eventInternal);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                map.put(transportName, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            AutoValue_LogRequest.Builder builder = new AutoValue_LogRequest.Builder();
            builder.qosTier = QosTier.DEFAULT;
            builder.requestTimeMs = Long.valueOf(this.wallTimeClock.getTime());
            builder.requestUptimeMs = Long.valueOf(this.uptimeClock.getTime());
            AutoValue_ClientInfo.Builder builder2 = new AutoValue_ClientInfo.Builder();
            builder2.clientType = ClientInfo.ClientType.ANDROID_FIREBASE;
            AutoValue_AndroidClientInfo.Builder builder3 = new AutoValue_AndroidClientInfo.Builder();
            builder3.sdkVersion = Integer.valueOf(eventInternal2.getInteger(KEY_SDK_VERSION));
            builder3.model = eventInternal2.get(KEY_MODEL);
            builder3.hardware = eventInternal2.get("hardware");
            builder3.device = eventInternal2.get(KEY_DEVICE);
            builder3.product = eventInternal2.get(KEY_PRODUCT);
            builder3.osBuild = eventInternal2.get(KEY_OS_BUILD);
            builder3.manufacturer = eventInternal2.get(KEY_MANUFACTURER);
            builder3.fingerprint = eventInternal2.get(KEY_FINGERPRINT);
            builder3.country = eventInternal2.get(KEY_COUNTRY);
            builder3.locale = eventInternal2.get("locale");
            builder3.mccMnc = eventInternal2.get(KEY_MCC_MNC);
            builder3.applicationBuild = eventInternal2.get(KEY_APPLICATION_BUILD);
            builder2.androidClientInfo = builder3.build();
            builder.clientInfo = builder2.build();
            try {
                builder.setSource(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                builder.logSourceName = (String) entry.getKey();
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal3.getEncodedPayload();
                Encoding encoding = encodedPayload.encoding;
                if (encoding.equals(new Encoding("proto"))) {
                    builderProtoBuilder = LogEvent.protoBuilder(encodedPayload.bytes);
                } else if (encoding.equals(new Encoding("json"))) {
                    builderProtoBuilder = LogEvent.jsonBuilder(new String(encodedPayload.bytes, Charset.forName("UTF-8")));
                } else {
                    Logging.w(LOG_TAG, "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                AutoValue_LogEvent.Builder builder4 = (AutoValue_LogEvent.Builder) builderProtoBuilder;
                builder4.eventTimeMs = Long.valueOf(eventInternal3.getEventMillis());
                builder4.eventUptimeMs = Long.valueOf(eventInternal3.getUptimeMillis());
                builder4.timezoneOffsetSeconds = Long.valueOf(eventInternal3.getLong(KEY_TIMEZONE_OFFSET));
                AutoValue_NetworkConnectionInfo.Builder builder5 = new AutoValue_NetworkConnectionInfo.Builder();
                builder5.networkType = NetworkConnectionInfo.NetworkType.forNumber(eventInternal3.getInteger(KEY_NETWORK_TYPE));
                builder5.mobileSubtype = NetworkConnectionInfo.MobileSubtype.forNumber(eventInternal3.getInteger(KEY_MOBILE_SUBTYPE));
                builder4.networkConnectionInfo = builder5.build();
                if (eventInternal3.getCode() != null) {
                    builder4.eventCode = eventInternal3.getCode();
                }
                arrayList3.add(builderProtoBuilder.build());
            }
            builder.logEvents = arrayList3;
            arrayList2.add(builder.build());
        }
        return new AutoValue_BatchedLogRequest(arrayList2);
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse send(BackendRequest backendRequest) {
        BatchedLogRequest requestBody = getRequestBody(backendRequest);
        URL urlOrThrow = this.endPoint;
        if (backendRequest.getExtras() != null) {
            try {
                CCTDestination cCTDestinationFromByteArray = CCTDestination.fromByteArray(backendRequest.getExtras());
                String str = cCTDestinationFromByteArray.apiKey;
                str = str != null ? str : null;
                String str2 = cCTDestinationFromByteArray.endPoint;
                if (str2 != null) {
                    urlOrThrow = parseUrlOrThrow(str2);
                }
            } catch (IllegalArgumentException unused) {
                return BackendResponse.fatalError();
            }
        }
        try {
            HttpResponse httpResponse = (HttpResponse) Retries.retry(5, new HttpRequest(urlOrThrow, requestBody, str), new Function() { // from class: com.google.android.datatransport.cct.CctTransportBackend$$ExternalSyntheticLambda0
                @Override // com.google.android.datatransport.runtime.retries.Function
                public final Object apply(Object obj) {
                    return this.f$0.doSend((CctTransportBackend.HttpRequest) obj);
                }
            }, new CctTransportBackend$$ExternalSyntheticLambda1());
            int i = httpResponse.code;
            if (i == 200) {
                return BackendResponse.ok(httpResponse.nextRequestMillis);
            }
            if (i < 500 && i != 404) {
                return i == 400 ? BackendResponse.invalidPayload() : BackendResponse.fatalError();
            }
            return BackendResponse.transientError();
        } catch (IOException e) {
            Logging.e(LOG_TAG, "Could not make request to the backend", e);
            return BackendResponse.transientError();
        }
    }

    public CctTransportBackend(Context context, Clock clock, Clock clock2) {
        this(context, clock, clock2, READ_TIME_OUT);
    }
}
