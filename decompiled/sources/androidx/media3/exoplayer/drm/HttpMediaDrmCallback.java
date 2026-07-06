package androidx.media3.exoplayer.drm;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.net.HttpHeaders;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    public static final int MAX_MANUAL_REDIRECTS = 5;
    public final DataSource.Factory dataSourceFactory;

    @Nullable
    public final String defaultLicenseUrl;
    public final boolean forceDefaultLicenseUrl;
    public final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(@Nullable String str, DataSource.Factory factory) {
        this(str, false, factory);
    }

    public static byte[] executePost(DataSource.Factory factory, String str, @Nullable byte[] bArr, Map<String, String> map) throws MediaDrmCallbackException {
        StatsDataSource statsDataSource = new StatsDataSource(factory.createDataSource());
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.uri = Uri.parse(str);
        builder.httpRequestHeaders = map;
        builder.httpMethod = 2;
        builder.httpBody = bArr;
        builder.flags = 1;
        DataSpec dataSpecBuild = builder.build();
        int i = 0;
        DataSpec dataSpecBuild2 = dataSpecBuild;
        while (true) {
            try {
                DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(statsDataSource, dataSpecBuild2);
                try {
                    byte[] byteArray = Util.toByteArray(dataSourceInputStream);
                    Util.closeQuietly(dataSourceInputStream);
                    return byteArray;
                } catch (HttpDataSource.InvalidResponseCodeException e) {
                    try {
                        String redirectUrl = getRedirectUrl(e, i);
                        if (redirectUrl == null) {
                            throw e;
                        }
                        i++;
                        DataSpec.Builder builder2 = new DataSpec.Builder(dataSpecBuild2);
                        builder2.uri = Uri.parse(redirectUrl);
                        dataSpecBuild2 = builder2.build();
                        Util.closeQuietly(dataSourceInputStream);
                    } catch (Throwable th) {
                        Util.closeQuietly(dataSourceInputStream);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                Uri uri = statsDataSource.lastOpenedUri;
                uri.getClass();
                throw new MediaDrmCallbackException(dataSpecBuild, uri, statsDataSource.dataSource.getResponseHeaders(), statsDataSource.bytesRead, e2);
            }
        }
    }

    @Nullable
    public static String getRedirectUrl(HttpDataSource.InvalidResponseCodeException invalidResponseCodeException, int i) {
        Map<String, List<String>> map;
        List<String> list;
        int i2 = invalidResponseCodeException.responseCode;
        if ((i2 != 307 && i2 != 308) || i >= 5 || (map = invalidResponseCodeException.headerFields) == null || (list = map.get(HttpHeaders.LOCATION)) == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void clearAllKeyRequestProperties() {
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.clear();
        }
    }

    public void clearKeyRequestProperty(String str) {
        str.getClass();
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.remove(str);
        }
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws MediaDrmCallbackException {
        String str = keyRequest.licenseServerUrl;
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(str)) {
            str = this.defaultLicenseUrl;
        }
        if (TextUtils.isEmpty(str)) {
            DataSpec.Builder builder = new DataSpec.Builder();
            Uri uri = Uri.EMPTY;
            builder.uri = uri;
            throw new MediaDrmCallbackException(builder.build(), uri, RegularImmutableMap.EMPTY, 0L, new IllegalStateException("No license URL"));
        }
        HashMap map = new HashMap();
        UUID uuid2 = C.PLAYREADY_UUID;
        map.put("Content-Type", uuid2.equals(uuid) ? "text/xml" : C.CLEARKEY_UUID.equals(uuid) ? CctTransportBackend.JSON_CONTENT_TYPE : "application/octet-stream");
        if (uuid2.equals(uuid)) {
            map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }
        synchronized (this.keyRequestProperties) {
            map.putAll(this.keyRequestProperties);
        }
        return executePost(this.dataSourceFactory, str, keyRequest.data, map);
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws MediaDrmCallbackException {
        return executePost(this.dataSourceFactory, provisionRequest.defaultUrl + "&signedRequest=" + Util.fromUtf8Bytes(provisionRequest.data), null, Collections.EMPTY_MAP);
    }

    public void setKeyRequestProperty(String str, String str2) {
        str.getClass();
        str2.getClass();
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }

    public HttpMediaDrmCallback(@Nullable String str, boolean z, DataSource.Factory factory) {
        Assertions.checkArgument((z && TextUtils.isEmpty(str)) ? false : true);
        this.dataSourceFactory = factory;
        this.defaultLicenseUrl = str;
        this.forceDefaultLicenseUrl = z;
        this.keyRequestProperties = new HashMap();
    }
}
