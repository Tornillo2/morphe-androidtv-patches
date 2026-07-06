package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.core.MediaPipelineBackend;
import com.amazon.avod.mpb.api.query.CodecQuery;
import com.amazon.avod.mpb.api.query.CodecQueryResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface MediaPipelineBackendApi<T extends MediaPipelineBackend> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface MediaPipelineBackendApiFactory<T extends MediaPipelineBackend, U extends MediaPipelineBackendContext> {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class DefaultImpls {
            public static /* synthetic */ MediaPipelineBackendApi create$default(MediaPipelineBackendApiFactory mediaPipelineBackendApiFactory, MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks, MediaPipelineBackendContext mediaPipelineBackendContext, CapabilitiesProvider capabilitiesProvider, DevicePropertyProvider devicePropertyProvider, FailoverManager failoverManager, int i, Object obj) throws MediaPipelineBackendException {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
                }
                if ((i & 16) != 0) {
                    failoverManager = NoOpFailoverManager.INSTANCE;
                }
                return mediaPipelineBackendApiFactory.create(mediaPipelineBackendApiCallbacks, mediaPipelineBackendContext, capabilitiesProvider, devicePropertyProvider, failoverManager);
            }
        }

        @NotNull
        MediaPipelineBackendApi<T> create(@NotNull MediaPipelineBackendApiCallbacks mediaPipelineBackendApiCallbacks, @NotNull U u, @NotNull CapabilitiesProvider capabilitiesProvider, @NotNull DevicePropertyProvider devicePropertyProvider, @NotNull FailoverManager failoverManager) throws MediaPipelineBackendException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface MediaPipelineBackendContext {
    }

    @NotNull
    CodecQueryResult canDecodeMedia(@NotNull CodecQuery codecQuery) throws MediaPipelineBackendException;

    @NotNull
    T createInstance() throws MediaPipelineBackendException;

    void destroyInstance(@NotNull T t) throws MediaPipelineBackendException;

    @NotNull
    String getCapabilities();

    @Nullable
    String getProperty(@NotNull String str) throws MediaPipelineBackendException;

    void setProperty(@NotNull String str, @Nullable String str2) throws MediaPipelineBackendException;
}
