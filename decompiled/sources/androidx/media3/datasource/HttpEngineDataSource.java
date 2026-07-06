package androidx.media3.datasource;

import android.net.Uri;
import android.net.http.HttpEngine;
import android.net.http.HttpException;
import android.net.http.UrlRequest;
import android.net.http.UrlResponseInfo;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import com.google.common.base.Ascii;
import com.google.common.base.Predicate;
import com.google.common.net.HttpHeaders;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(34)
@UnstableApi
public final class HttpEngineDataSource extends BaseDataSource implements HttpDataSource {

    @UnstableApi
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;

    @UnstableApi
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    public static final int READ_BUFFER_SIZE_BYTES = 32768;
    public long bytesRemaining;
    public final Clock clock;
    public final int connectTimeoutMs;

    @Nullable
    public Predicate<String> contentTypePredicate;
    public volatile long currentConnectTimeoutMs;

    @Nullable
    public DataSpec currentDataSpec;

    @Nullable
    public UrlRequestWrapper currentUrlRequestWrapper;

    @Nullable
    public final HttpDataSource.RequestProperties defaultRequestProperties;

    @Nullable
    public IOException exception;
    public final Executor executor;
    public boolean finished;
    public final boolean handleSetCookieRequests;
    public final HttpEngine httpEngine;
    public final boolean keepPostFor302Redirects;
    public boolean opened;
    public final ConditionVariable operation;

    @Nullable
    public ByteBuffer readBuffer;
    public final int readTimeoutMs;
    public final int requestPriority;
    public final HttpDataSource.RequestProperties requestProperties;
    public final boolean resetTimeoutOnRedirects;

    @Nullable
    public UrlResponseInfo responseInfo;

    @Nullable
    public final String userAgent;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements HttpDataSource.Factory {
        public int connectTimeoutMs;

        @Nullable
        public Predicate<String> contentTypePredicate;
        public final HttpDataSource.RequestProperties defaultRequestProperties;
        public final Executor executor;
        public boolean handleSetCookieRequests;
        public final HttpEngine httpEngine;
        public boolean keepPostFor302Redirects;
        public int readTimeoutMs;
        public int requestPriority;
        public boolean resetTimeoutOnRedirects;

        @Nullable
        public TransferListener transferListener;

        @Nullable
        public String userAgent;

        public Factory(HttpEngine httpEngine, Executor executor) {
            httpEngine.getClass();
            this.httpEngine = httpEngine;
            this.executor = executor;
            this.defaultRequestProperties = new HttpDataSource.RequestProperties();
            this.requestPriority = 3;
            this.connectTimeoutMs = 8000;
            this.readTimeoutMs = 8000;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setConnectionTimeoutMs(int i) {
            this.connectTimeoutMs = i;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setContentTypePredicate(@Nullable Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        @CanIgnoreReturnValue
        @UnstableApi
        public HttpDataSource.Factory setDefaultRequestProperties(Map map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setHandleSetCookieRequests(boolean z) {
            this.handleSetCookieRequests = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setKeepPostFor302Redirects(boolean z) {
            this.keepPostFor302Redirects = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setReadTimeoutMs(int i) {
            this.readTimeoutMs = i;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setRequestPriority(int i) {
            this.requestPriority = i;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setResetTimeoutOnRedirects(boolean z) {
            this.resetTimeoutOnRedirects = z;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setTransferListener(@Nullable TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        @CanIgnoreReturnValue
        @UnstableApi
        public Factory setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory, androidx.media3.datasource.DataSource.Factory
        @UnstableApi
        public HttpDataSource createDataSource() {
            HttpEngineDataSource httpEngineDataSource = new HttpEngineDataSource(this.httpEngine, this.executor, this.requestPriority, this.connectTimeoutMs, this.readTimeoutMs, this.resetTimeoutOnRedirects, this.handleSetCookieRequests, this.userAgent, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                httpEngineDataSource.addTransferListener(transferListener);
            }
            return httpEngineDataSource;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        @CanIgnoreReturnValue
        @UnstableApi
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class UrlRequestCallback implements UrlRequest.Callback {
        public volatile boolean isClosed;

        public UrlRequestCallback() {
            this.isClosed = false;
        }

        public void close() {
            this.isClosed = true;
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onCanceled(UrlRequest urlRequest, @Nullable UrlResponseInfo urlResponseInfo) {
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onFailed(UrlRequest urlRequest, @Nullable UrlResponseInfo urlResponseInfo, HttpException httpException) {
            try {
                if (this.isClosed) {
                    return;
                }
                if (HttpEngineDataSource$UrlRequestCallback$$ExternalSyntheticApiModelOutline0.m(httpException) && HttpEngineDataSource$UrlRequestCallback$$ExternalSyntheticApiModelOutline1.m(httpException).getErrorCode() == 1) {
                    HttpEngineDataSource.this.exception = new UnknownHostException();
                } else {
                    HttpEngineDataSource.this.exception = httpException;
                }
                HttpEngineDataSource.this.operation.open();
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            if (this.isClosed) {
                return;
            }
            HttpEngineDataSource.this.operation.open();
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            DataSpec dataSpecWithUri;
            if (this.isClosed) {
                return;
            }
            DataSpec dataSpec = HttpEngineDataSource.this.currentDataSpec;
            dataSpec.getClass();
            int httpStatusCode = urlResponseInfo.getHttpStatusCode();
            if (dataSpec.httpMethod == 2 && (httpStatusCode == 307 || httpStatusCode == 308)) {
                HttpEngineDataSource.this.exception = new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), null, urlResponseInfo.getHeaders().getAsMap(), dataSpec, Util.EMPTY_BYTE_ARRAY);
                HttpEngineDataSource.this.operation.open();
                return;
            }
            HttpEngineDataSource httpEngineDataSource = HttpEngineDataSource.this;
            if (httpEngineDataSource.resetTimeoutOnRedirects) {
                httpEngineDataSource.resetConnectTimeout();
            }
            HttpEngineDataSource httpEngineDataSource2 = HttpEngineDataSource.this;
            boolean z = httpEngineDataSource2.keepPostFor302Redirects && dataSpec.httpMethod == 2 && httpStatusCode == 302;
            if (!z && !httpEngineDataSource2.handleSetCookieRequests) {
                urlRequest.followRedirect();
                return;
            }
            String cookies = HttpEngineDataSource.parseCookies(urlResponseInfo.getHeaders().getAsMap().get(HttpHeaders.SET_COOKIE));
            if (!z && TextUtils.isEmpty(cookies)) {
                urlRequest.followRedirect();
                return;
            }
            urlRequest.cancel();
            if (z || dataSpec.httpMethod != 2) {
                dataSpecWithUri = dataSpec.withUri(Uri.parse(str));
            } else {
                DataSpec.Builder builder = new DataSpec.Builder(dataSpec);
                builder.uri = Uri.parse(str);
                builder.httpMethod = 1;
                builder.httpBody = null;
                dataSpecWithUri = builder.build();
            }
            if (!TextUtils.isEmpty(cookies)) {
                HashMap map = new HashMap();
                map.putAll(dataSpec.httpRequestHeaders);
                map.put(HttpHeaders.COOKIE, cookies);
                DataSpec.Builder builder2 = new DataSpec.Builder(dataSpecWithUri);
                builder2.httpRequestHeaders = map;
                dataSpecWithUri = builder2.build();
            }
            try {
                UrlRequestWrapper urlRequestWrapperBuildRequestWrapper = HttpEngineDataSource.this.buildRequestWrapper(dataSpecWithUri);
                UrlRequestWrapper urlRequestWrapper = HttpEngineDataSource.this.currentUrlRequestWrapper;
                if (urlRequestWrapper != null) {
                    urlRequestWrapper.close();
                }
                HttpEngineDataSource.this.currentUrlRequestWrapper = urlRequestWrapperBuildRequestWrapper;
                urlRequestWrapperBuildRequestWrapper.start();
            } catch (IOException e) {
                HttpEngineDataSource.this.exception = e;
            }
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (this.isClosed) {
                return;
            }
            HttpEngineDataSource.this.responseInfo = urlResponseInfo;
            HttpEngineDataSource.this.operation.open();
        }

        @Override // android.net.http.UrlRequest.Callback
        public synchronized void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (this.isClosed) {
                return;
            }
            HttpEngineDataSource.this.finished = true;
            HttpEngineDataSource.this.operation.open();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UrlRequestWrapper {
        public final UrlRequest urlRequest;
        public final UrlRequestCallback urlRequestCallback;

        public UrlRequestWrapper(UrlRequest urlRequest, UrlRequestCallback urlRequestCallback) {
            this.urlRequest = urlRequest;
            this.urlRequestCallback = urlRequestCallback;
        }

        public void close() {
            this.urlRequestCallback.isClosed = true;
            this.urlRequest.cancel();
        }

        public int getStatus() throws InterruptedException {
            final ConditionVariable conditionVariable = new ConditionVariable();
            final int[] iArr = new int[1];
            this.urlRequest.getStatus(new UrlRequest.StatusListener() { // from class: androidx.media3.datasource.HttpEngineDataSource.UrlRequestWrapper.1
                @Override // android.net.http.UrlRequest.StatusListener
                public void onStatus(int i) {
                    iArr[0] = i;
                    conditionVariable.open();
                }
            });
            conditionVariable.block();
            return iArr[0];
        }

        public UrlRequest.Callback getUrlRequestCallback() {
            return this.urlRequestCallback;
        }

        public void read(ByteBuffer byteBuffer) {
            this.urlRequest.read(byteBuffer);
        }

        public void start() {
            this.urlRequest.start();
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.datasource.httpengine");
    }

    @UnstableApi
    public HttpEngineDataSource(HttpEngine httpEngine, Executor executor, int i, int i2, int i3, boolean z, boolean z2, @Nullable String str, @Nullable HttpDataSource.RequestProperties requestProperties, @Nullable Predicate<String> predicate, boolean z3) {
        super(true);
        httpEngine.getClass();
        this.httpEngine = httpEngine;
        executor.getClass();
        this.executor = executor;
        this.requestPriority = i;
        this.connectTimeoutMs = i2;
        this.readTimeoutMs = i3;
        this.resetTimeoutOnRedirects = z;
        this.handleSetCookieRequests = z2;
        this.userAgent = str;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = predicate;
        this.keepPostFor302Redirects = z3;
        this.clock = Clock.DEFAULT;
        this.requestProperties = new HttpDataSource.RequestProperties();
        this.operation = new ConditionVariable();
    }

    public static int copyByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int iMin = Math.min(byteBuffer.remaining(), byteBuffer2.remaining());
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + iMin);
        byteBuffer2.put(byteBuffer);
        byteBuffer.limit(iLimit);
        return iMin;
    }

    @Nullable
    public static String getFirstHeader(Map<String, List<String>> map, String str) {
        List<String> list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static boolean isCompressed(UrlResponseInfo urlResponseInfo) {
        Iterator<Map.Entry<String, String>> it = urlResponseInfo.getHeaders().getAsList().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equalsIgnoreCase("Content-Encoding")) {
                return !r0.getValue().equalsIgnoreCase("identity");
            }
        }
        return false;
    }

    @Nullable
    public static String parseCookies(@Nullable List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return TextUtils.join(";", list);
    }

    private void skipFully(long j, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        if (j == 0) {
            return;
        }
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        while (j > 0) {
            try {
                this.operation.close();
                orCreateReadBuffer.clear();
                readInternal(orCreateReadBuffer, dataSpec);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                }
                if (this.finished) {
                    throw new OpenException(dataSpec, 2008, 14);
                }
                orCreateReadBuffer.flip();
                Assertions.checkState(orCreateReadBuffer.hasRemaining());
                int iMin = (int) Math.min(orCreateReadBuffer.remaining(), j);
                orCreateReadBuffer.position(orCreateReadBuffer.position() + iMin);
                j -= (long) iMin;
            } catch (IOException e) {
                if (e instanceof HttpDataSource.HttpDataSourceException) {
                    throw ((HttpDataSource.HttpDataSourceException) e);
                }
                throw new OpenException(e, dataSpec, e instanceof SocketTimeoutException ? 2002 : 2001, 14);
            }
        }
    }

    public final boolean blockUntilConnectTimeout() throws InterruptedException {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        boolean zBlock = false;
        while (!zBlock && jElapsedRealtime < this.currentConnectTimeoutMs) {
            zBlock = this.operation.block((this.currentConnectTimeoutMs - jElapsedRealtime) + 5);
            jElapsedRealtime = this.clock.elapsedRealtime();
        }
        return zBlock;
    }

    public final UrlRequest.Builder buildRequestBuilder(DataSpec dataSpec, UrlRequest.Callback callback) throws IOException {
        UrlRequest.Builder directExecutorAllowed = this.httpEngine.newUrlRequestBuilder(dataSpec.uri.toString(), this.executor, callback).setPriority(this.requestPriority).setDirectExecutorAllowed(true);
        HashMap map = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            map.putAll(requestProperties.getSnapshot());
        }
        map.putAll(this.requestProperties.getSnapshot());
        map.putAll(dataSpec.httpRequestHeaders);
        for (Map.Entry entry : map.entrySet()) {
            directExecutorAllowed.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        if (dataSpec.httpBody != null && !map.containsKey("Content-Type")) {
            throw new OpenException("HTTP request with non-empty body must set Content-Type", dataSpec, 1004, 0);
        }
        String strBuildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(dataSpec.position, dataSpec.length);
        if (strBuildRangeRequestHeader != null) {
            directExecutorAllowed.addHeader(HttpHeaders.RANGE, strBuildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            directExecutorAllowed.addHeader("User-Agent", str);
        }
        directExecutorAllowed.setHttpMethod(DataSpec.getStringForHttpMethod(dataSpec.httpMethod));
        if (dataSpec.httpBody != null) {
            directExecutorAllowed.setUploadDataProvider(new ByteArrayUploadDataProvider(dataSpec.httpBody), this.executor);
        }
        return directExecutorAllowed;
    }

    public final UrlRequestWrapper buildRequestWrapper(DataSpec dataSpec) throws IOException {
        UrlRequestCallback urlRequestCallback = new UrlRequestCallback();
        return new UrlRequestWrapper(buildRequestBuilder(dataSpec, urlRequestCallback).build(), urlRequestCallback);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    @UnstableApi
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // androidx.media3.datasource.HttpDataSource
    @UnstableApi
    public void clearRequestProperty(String str) {
        this.requestProperties.remove(str);
    }

    @Override // androidx.media3.datasource.DataSource
    @UnstableApi
    public synchronized void close() {
        try {
            UrlRequestWrapper urlRequestWrapper = this.currentUrlRequestWrapper;
            if (urlRequestWrapper != null) {
                urlRequestWrapper.close();
                this.currentUrlRequestWrapper = null;
            }
            ByteBuffer byteBuffer = this.readBuffer;
            if (byteBuffer != null) {
                byteBuffer.limit(0);
            }
            this.currentDataSpec = null;
            this.responseInfo = null;
            this.exception = null;
            this.finished = false;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Nullable
    @VisibleForTesting
    @UnstableApi
    public UrlRequest.Callback getCurrentUrlRequestCallback() {
        UrlRequestWrapper urlRequestWrapper = this.currentUrlRequestWrapper;
        if (urlRequestWrapper == null) {
            return null;
        }
        return urlRequestWrapper.urlRequestCallback;
    }

    public final ByteBuffer getOrCreateReadBuffer() {
        if (this.readBuffer == null) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(32768);
            this.readBuffer = byteBufferAllocateDirect;
            byteBufferAllocateDirect.limit(0);
        }
        return this.readBuffer;
    }

    @Override // androidx.media3.datasource.HttpDataSource
    @UnstableApi
    public int getResponseCode() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo == null || urlResponseInfo.getHttpStatusCode() <= 0) {
            return -1;
        }
        return this.responseInfo.getHttpStatusCode();
    }

    @Override // androidx.media3.datasource.BaseDataSource, androidx.media3.datasource.DataSource
    @UnstableApi
    public Map<String, List<String>> getResponseHeaders() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        return urlResponseInfo == null ? Collections.EMPTY_MAP : urlResponseInfo.getHeaders().getAsMap();
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    @UnstableApi
    public Uri getUri() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo == null) {
            return null;
        }
        return Uri.parse(urlResponseInfo.getUrl());
    }

    @Override // androidx.media3.datasource.DataSource
    @UnstableApi
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] responseBody;
        String firstHeader;
        dataSpec.getClass();
        Assertions.checkState(!this.opened);
        this.operation.close();
        resetConnectTimeout();
        this.currentDataSpec = dataSpec;
        try {
            UrlRequestWrapper urlRequestWrapperBuildRequestWrapper = buildRequestWrapper(dataSpec);
            this.currentUrlRequestWrapper = urlRequestWrapperBuildRequestWrapper;
            urlRequestWrapperBuildRequestWrapper.start();
            transferInitializing(dataSpec);
            try {
                boolean zBlockUntilConnectTimeout = blockUntilConnectTimeout();
                IOException iOException = this.exception;
                if (iOException != null) {
                    String message = iOException.getMessage();
                    if (message == null || !Ascii.toLowerCase(message).contains("err_cleartext_not_permitted")) {
                        throw new OpenException(iOException, dataSpec, 2001, urlRequestWrapperBuildRequestWrapper.getStatus());
                    }
                    throw new HttpDataSource.CleartextNotPermittedException(iOException, dataSpec);
                }
                if (!zBlockUntilConnectTimeout) {
                    throw new OpenException(new SocketTimeoutException(), dataSpec, 2002, urlRequestWrapperBuildRequestWrapper.getStatus());
                }
                UrlResponseInfo urlResponseInfo = this.responseInfo;
                urlResponseInfo.getClass();
                int httpStatusCode = urlResponseInfo.getHttpStatusCode();
                Map<String, List<String>> asMap = urlResponseInfo.getHeaders().getAsMap();
                long j = 0;
                if (httpStatusCode < 200 || httpStatusCode > 299) {
                    if (httpStatusCode == 416) {
                        if (dataSpec.position == HttpUtil.getDocumentSize(getFirstHeader(asMap, HttpHeaders.CONTENT_RANGE))) {
                            this.opened = true;
                            transferStarted(dataSpec);
                            long j2 = dataSpec.length;
                            if (j2 != -1) {
                                return j2;
                            }
                            return 0L;
                        }
                    }
                    try {
                        responseBody = readResponseBody();
                    } catch (IOException unused) {
                        responseBody = Util.EMPTY_BYTE_ARRAY;
                    }
                    throw new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), httpStatusCode == 416 ? new DataSourceException(2008) : null, asMap, dataSpec, responseBody);
                }
                Predicate<String> predicate = this.contentTypePredicate;
                if (predicate != null && (firstHeader = getFirstHeader(asMap, "Content-Type")) != null && !predicate.apply(firstHeader)) {
                    throw new HttpDataSource.InvalidContentTypeException(firstHeader, dataSpec);
                }
                if (httpStatusCode == 200) {
                    long j3 = dataSpec.position;
                    if (j3 != 0) {
                        j = j3;
                    }
                }
                if (isCompressed(urlResponseInfo)) {
                    this.bytesRemaining = dataSpec.length;
                } else {
                    long j4 = dataSpec.length;
                    if (j4 != -1) {
                        this.bytesRemaining = j4;
                    } else {
                        long contentLength = HttpUtil.getContentLength(getFirstHeader(asMap, HttpHeaders.CONTENT_LENGTH), getFirstHeader(asMap, HttpHeaders.CONTENT_RANGE));
                        this.bytesRemaining = contentLength != -1 ? contentLength - j : -1L;
                    }
                }
                this.opened = true;
                transferStarted(dataSpec);
                skipFully(j, dataSpec);
                return this.bytesRemaining;
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                throw new OpenException(new InterruptedIOException(), dataSpec, 1004, -1);
            }
        } catch (IOException e) {
            if (e instanceof HttpDataSource.HttpDataSourceException) {
                throw ((HttpDataSource.HttpDataSourceException) e);
            }
            throw new OpenException(e, dataSpec, 2000, 0);
        }
    }

    @Override // androidx.media3.common.DataReader
    @UnstableApi
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        Assertions.checkState(this.opened);
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        if (!orCreateReadBuffer.hasRemaining()) {
            this.operation.close();
            orCreateReadBuffer.clear();
            DataSpec dataSpec = this.currentDataSpec;
            Util.castNonNull(dataSpec);
            readInternal(orCreateReadBuffer, dataSpec);
            if (this.finished) {
                this.bytesRemaining = 0L;
                return -1;
            }
            orCreateReadBuffer.flip();
            Assertions.checkState(orCreateReadBuffer.hasRemaining());
        }
        long j = this.bytesRemaining;
        if (j == -1) {
            j = Long.MAX_VALUE;
        }
        int iMin = (int) Longs.min(j, orCreateReadBuffer.remaining(), i2);
        orCreateReadBuffer.get(bArr, i, iMin);
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - ((long) iMin);
        }
        bytesTransferred(iMin);
        return iMin;
    }

    public final void readInternal(ByteBuffer byteBuffer, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        UrlRequestWrapper urlRequestWrapper = this.currentUrlRequestWrapper;
        Util.castNonNull(urlRequestWrapper);
        urlRequestWrapper.read(byteBuffer);
        try {
            if (!this.operation.block(this.readTimeoutMs)) {
                throw new SocketTimeoutException();
            }
        } catch (InterruptedException unused) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            Thread.currentThread().interrupt();
            this.exception = new InterruptedIOException();
        } catch (SocketTimeoutException e) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            this.exception = new HttpDataSource.HttpDataSourceException(e, dataSpec, 2002, 2);
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            if (!(iOException instanceof HttpDataSource.HttpDataSourceException)) {
                throw HttpDataSource.HttpDataSourceException.createForIOException(iOException, dataSpec, 2);
            }
            throw ((HttpDataSource.HttpDataSourceException) iOException);
        }
    }

    public final byte[] readResponseBody() throws IOException {
        byte[] bArrCopyOf = Util.EMPTY_BYTE_ARRAY;
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        while (!this.finished) {
            this.operation.close();
            orCreateReadBuffer.clear();
            readInternal(orCreateReadBuffer, this.currentDataSpec);
            orCreateReadBuffer.flip();
            if (orCreateReadBuffer.remaining() > 0) {
                int length = bArrCopyOf.length;
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, orCreateReadBuffer.remaining() + bArrCopyOf.length);
                orCreateReadBuffer.get(bArrCopyOf, length, orCreateReadBuffer.remaining());
            }
        }
        return bArrCopyOf;
    }

    public final void resetConnectTimeout() {
        this.currentConnectTimeoutMs = this.clock.elapsedRealtime() + ((long) this.connectTimeoutMs);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    @UnstableApi
    public void setRequestProperty(String str, String str2) {
        this.requestProperties.set(str, str2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public static final class OpenException extends HttpDataSource.HttpDataSourceException {
        public final int httpEngineConnectionStatus;

        public OpenException(IOException iOException, DataSpec dataSpec, int i, int i2) {
            super(iOException, dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }

        public OpenException(String str, DataSpec dataSpec, int i, int i2) {
            super(str, dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }

        public OpenException(DataSpec dataSpec, int i, int i2) {
            super(dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }
    }

    @UnstableApi
    public int read(ByteBuffer byteBuffer) throws HttpDataSource.HttpDataSourceException {
        int iCopyByteBuffer;
        Assertions.checkState(this.opened);
        if (byteBuffer.isDirect()) {
            if (!byteBuffer.hasRemaining()) {
                return 0;
            }
            if (this.bytesRemaining == 0) {
                return -1;
            }
            int iRemaining = byteBuffer.remaining();
            ByteBuffer byteBuffer2 = this.readBuffer;
            if (byteBuffer2 != null && (iCopyByteBuffer = copyByteBuffer(byteBuffer2, byteBuffer)) != 0) {
                long j = this.bytesRemaining;
                if (j != -1) {
                    this.bytesRemaining = j - ((long) iCopyByteBuffer);
                }
                bytesTransferred(iCopyByteBuffer);
                return iCopyByteBuffer;
            }
            this.operation.close();
            DataSpec dataSpec = this.currentDataSpec;
            Util.castNonNull(dataSpec);
            readInternal(byteBuffer, dataSpec);
            if (this.finished) {
                this.bytesRemaining = 0L;
                return -1;
            }
            Assertions.checkState(iRemaining > byteBuffer.remaining());
            int iRemaining2 = iRemaining - byteBuffer.remaining();
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) iRemaining2);
            }
            bytesTransferred(iRemaining2);
            return iRemaining2;
        }
        throw new IllegalArgumentException("Passed buffer is not a direct ByteBuffer");
    }
}
