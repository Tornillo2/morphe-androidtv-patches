package com.google.android.exoplayer2.drm;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {

    @GuardedBy("lock")
    public MediaItem.DrmConfiguration drmConfiguration;

    @Nullable
    public DataSource.Factory drmHttpDataSourceFactory;
    public final Object lock = new Object();

    @GuardedBy("lock")
    public DrmSessionManager manager;

    @Nullable
    public String userAgent;

    @RequiresApi(18)
    public final DrmSessionManager createManager(MediaItem.DrmConfiguration drmConfiguration) {
        DataSource.Factory factory = this.drmHttpDataSourceFactory;
        DataSource.Factory factory2 = factory;
        if (factory == null) {
            DefaultHttpDataSource.Factory factory3 = new DefaultHttpDataSource.Factory();
            factory3.userAgent = this.userAgent;
            factory2 = factory3;
        }
        Uri uri = drmConfiguration.licenseUri;
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(uri == null ? null : uri.toString(), drmConfiguration.forceDefaultLicenseUri, factory2);
        UnmodifiableIterator<Map.Entry<String, String>> it = drmConfiguration.licenseRequestHeaders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            httpMediaDrmCallback.setKeyRequestProperty(next.getKey(), next.getValue());
        }
        DefaultDrmSessionManager.Builder builder = new DefaultDrmSessionManager.Builder();
        builder.setUuidAndExoMediaDrmProvider(drmConfiguration.scheme, FrameworkMediaDrm.DEFAULT_PROVIDER);
        builder.multiSession = drmConfiguration.multiSession;
        builder.playClearSamplesWithoutKeys = drmConfiguration.playClearContentWithoutKey;
        builder.setUseDrmSessionsForClearContent(Ints.toArray(drmConfiguration.forcedSessionTrackTypes));
        DefaultDrmSessionManager defaultDrmSessionManagerBuild = builder.build(httpMediaDrmCallback);
        defaultDrmSessionManagerBuild.setMode(0, drmConfiguration.getKeySetId());
        return defaultDrmSessionManagerBuild;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManagerProvider
    public DrmSessionManager get(MediaItem mediaItem) {
        DrmSessionManager drmSessionManager;
        mediaItem.localConfiguration.getClass();
        MediaItem.DrmConfiguration drmConfiguration = mediaItem.localConfiguration.drmConfiguration;
        if (drmConfiguration == null || Util.SDK_INT < 18) {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }
        synchronized (this.lock) {
            try {
                if (!drmConfiguration.equals(this.drmConfiguration)) {
                    this.drmConfiguration = drmConfiguration;
                    this.manager = createManager(drmConfiguration);
                }
                drmSessionManager = this.manager;
                drmSessionManager.getClass();
            } catch (Throwable th) {
                throw th;
            }
        }
        return drmSessionManager;
    }

    public void setDrmHttpDataSourceFactory(@Nullable DataSource.Factory factory) {
        this.drmHttpDataSourceFactory = factory;
    }

    @Deprecated
    public void setDrmUserAgent(@Nullable String str) {
        this.userAgent = str;
    }
}
