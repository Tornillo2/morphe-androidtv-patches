package com.amazon.avod.mpb.api.query;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CodecQueryAttributesKt {
    @Nullable
    public static final <T> T convertAttribute(@NotNull CodecQuery codecQuery, @NotNull CodecQueryAttributeKey key, @NotNull Function1<? super String, ? extends T> conversion) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(codecQuery, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(conversion, "conversion");
        String str = codecQuery.attributes.get(key);
        if (str == null) {
            return null;
        }
        try {
            return conversion.invoke(str);
        } catch (Exception e) {
            throw new MediaPipelineBackendException("Invalid format for " + key + ": " + str, MediaPipelineBackendResultCode.AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_KEY_FORMAT, e);
        }
    }

    public static final <T> T convertRequiredAttribute(@NotNull CodecQuery codecQuery, @NotNull CodecQueryAttributeKey key, @NotNull Function1<? super String, ? extends T> conversion) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(codecQuery, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(conversion, "conversion");
        T t = (T) convertAttribute(codecQuery, key, conversion);
        if (t != null) {
            return t;
        }
        throw new MediaPipelineBackendException("Missing required value for " + key, MediaPipelineBackendResultCode.AV_MPB_DECODER_QUERY_EXCEPTION_MISSING_VALUE);
    }
}
