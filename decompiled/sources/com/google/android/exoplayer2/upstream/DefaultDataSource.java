package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultDataSource implements DataSource {
    public static final String SCHEME_ANDROID_RESOURCE = "android.resource";
    public static final String SCHEME_ASSET = "asset";
    public static final String SCHEME_CONTENT = "content";
    public static final String SCHEME_DATA = "data";
    public static final String SCHEME_RAW = "rawresource";
    public static final String SCHEME_RTMP = "rtmp";
    public static final String SCHEME_UDP = "udp";
    public static final String TAG = "DefaultDataSource";

    @Nullable
    public DataSource assetDataSource;
    public final DataSource baseDataSource;

    @Nullable
    public DataSource contentDataSource;
    public final Context context;

    @Nullable
    public DataSource dataSchemeDataSource;

    @Nullable
    public DataSource dataSource;

    @Nullable
    public DataSource fileDataSource;

    @Nullable
    public DataSource rawResourceDataSource;

    @Nullable
    public DataSource rtmpDataSource;
    public final List<TransferListener> transferListeners;

    @Nullable
    public DataSource udpDataSource;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements DataSource.Factory {
        public final DataSource.Factory baseDataSourceFactory;
        public final Context context;

        @Nullable
        public TransferListener transferListener;

        public Factory(Context context) {
            this(context, new DefaultHttpDataSource.Factory());
        }

        @CanIgnoreReturnValue
        public Factory setTransferListener(@Nullable TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        public Factory(Context context, DataSource.Factory factory) {
            this.context = context.getApplicationContext();
            this.baseDataSourceFactory = factory;
        }

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        public DefaultDataSource createDataSource() {
            DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                defaultDataSource.addTransferListener(transferListener);
            }
            return defaultDataSource;
        }
    }

    public DefaultDataSource(Context context, boolean z) {
        this(context, null, 8000, 8000, z);
    }

    public final void addListenersToDataSource(DataSource dataSource) {
        for (int i = 0; i < this.transferListeners.size(); i++) {
            dataSource.addTransferListener(this.transferListeners.get(i));
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.baseDataSource.addTransferListener(transferListener);
        this.transferListeners.add(transferListener);
        maybeAddListenerToDataSource(this.fileDataSource, transferListener);
        maybeAddListenerToDataSource(this.assetDataSource, transferListener);
        maybeAddListenerToDataSource(this.contentDataSource, transferListener);
        maybeAddListenerToDataSource(this.rtmpDataSource, transferListener);
        maybeAddListenerToDataSource(this.udpDataSource, transferListener);
        maybeAddListenerToDataSource(this.dataSchemeDataSource, transferListener);
        maybeAddListenerToDataSource(this.rawResourceDataSource, transferListener);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.dataSource = null;
            }
        }
    }

    public final DataSource getAssetDataSource() {
        if (this.assetDataSource == null) {
            AssetDataSource assetDataSource = new AssetDataSource(this.context);
            this.assetDataSource = assetDataSource;
            addListenersToDataSource(assetDataSource);
        }
        return this.assetDataSource;
    }

    public final DataSource getContentDataSource() {
        if (this.contentDataSource == null) {
            ContentDataSource contentDataSource = new ContentDataSource(this.context);
            this.contentDataSource = contentDataSource;
            addListenersToDataSource(contentDataSource);
        }
        return this.contentDataSource;
    }

    public final DataSource getDataSchemeDataSource() {
        if (this.dataSchemeDataSource == null) {
            DataSchemeDataSource dataSchemeDataSource = new DataSchemeDataSource(false);
            this.dataSchemeDataSource = dataSchemeDataSource;
            addListenersToDataSource(dataSchemeDataSource);
        }
        return this.dataSchemeDataSource;
    }

    public final DataSource getFileDataSource() {
        if (this.fileDataSource == null) {
            FileDataSource fileDataSource = new FileDataSource(false);
            this.fileDataSource = fileDataSource;
            addListenersToDataSource(fileDataSource);
        }
        return this.fileDataSource;
    }

    public final DataSource getRawResourceDataSource() {
        if (this.rawResourceDataSource == null) {
            RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this.context);
            this.rawResourceDataSource = rawResourceDataSource;
            addListenersToDataSource(rawResourceDataSource);
        }
        return this.rawResourceDataSource;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        DataSource dataSource = this.dataSource;
        return dataSource == null ? Collections.EMPTY_MAP : dataSource.getResponseHeaders();
    }

    public final DataSource getRtmpDataSource() {
        if (this.rtmpDataSource == null) {
            try {
                DataSource dataSource = (DataSource) Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(null).newInstance(null);
                this.rtmpDataSource = dataSource;
                addListenersToDataSource(dataSource);
            } catch (ClassNotFoundException unused) {
                Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating RTMP extension", e);
            }
            if (this.rtmpDataSource == null) {
                this.rtmpDataSource = this.baseDataSource;
            }
        }
        return this.rtmpDataSource;
    }

    public final DataSource getUdpDataSource() {
        if (this.udpDataSource == null) {
            UdpDataSource udpDataSource = new UdpDataSource();
            this.udpDataSource = udpDataSource;
            addListenersToDataSource(udpDataSource);
        }
        return this.udpDataSource;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    @Nullable
    public Uri getUri() {
        DataSource dataSource = this.dataSource;
        if (dataSource == null) {
            return null;
        }
        return dataSource.getUri();
    }

    public final void maybeAddListenerToDataSource(@Nullable DataSource dataSource, TransferListener transferListener) {
        if (dataSource != null) {
            dataSource.addTransferListener(transferListener);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        Assertions.checkState(this.dataSource == null);
        String scheme = dataSpec.uri.getScheme();
        if (Util.isLocalFileUri(dataSpec.uri)) {
            String path = dataSpec.uri.getPath();
            if (path == null || !path.startsWith("/android_asset/")) {
                this.dataSource = getFileDataSource();
            } else {
                this.dataSource = getAssetDataSource();
            }
        } else if ("asset".equals(scheme)) {
            this.dataSource = getAssetDataSource();
        } else if ("content".equals(scheme)) {
            this.dataSource = getContentDataSource();
        } else if ("rtmp".equals(scheme)) {
            this.dataSource = getRtmpDataSource();
        } else if ("udp".equals(scheme)) {
            this.dataSource = getUdpDataSource();
        } else if ("data".equals(scheme)) {
            this.dataSource = getDataSchemeDataSource();
        } else if ("rawresource".equals(scheme) || "android.resource".equals(scheme)) {
            this.dataSource = getRawResourceDataSource();
        } else {
            this.dataSource = this.baseDataSource;
        }
        return this.dataSource.open(dataSpec);
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        DataSource dataSource = this.dataSource;
        dataSource.getClass();
        return dataSource.read(bArr, i, i2);
    }

    public DefaultDataSource(Context context, @Nullable String str, boolean z) {
        this(context, str, 8000, 8000, z);
    }

    public DefaultDataSource(Context context, @Nullable String str, int i, int i2, boolean z) {
        DefaultHttpDataSource.Factory factory = new DefaultHttpDataSource.Factory();
        factory.userAgent = str;
        factory.connectTimeoutMs = i;
        factory.readTimeoutMs = i2;
        factory.allowCrossProtocolRedirects = z;
        this(context, factory.createDataSource());
    }

    public DefaultDataSource(Context context, DataSource dataSource) {
        this.context = context.getApplicationContext();
        dataSource.getClass();
        this.baseDataSource = dataSource;
        this.transferListeners = new ArrayList();
    }
}
