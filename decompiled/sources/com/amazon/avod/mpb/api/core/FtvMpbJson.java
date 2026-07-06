package com.amazon.avod.mpb.api.core;

import com.amazon.avod.mpb.api.query.CodecQuery;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFtvMpbJson.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FtvMpbJson.kt\ncom/amazon/avod/mpb/api/core/FtvMpbJson\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,19:1\n222#2:20\n*S KotlinDebug\n*F\n+ 1 FtvMpbJson.kt\ncom/amazon/avod/mpb/api/core/FtvMpbJson\n*L\n9#1:20\n*E\n"})
public final class FtvMpbJson {

    @NotNull
    public static final FtvMpbJson INSTANCE = new FtvMpbJson();

    @NotNull
    public final CodecQuery buildCodecQuery(@NotNull String codecQueryJson) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(codecQueryJson, "codecQueryJson");
        try {
            Json.Default r0 = Json.Default;
            r0.getClass();
            return (CodecQuery) r0.decodeFromString(CodecQuery.Companion.serializer(), codecQueryJson);
        } catch (Exception e) {
            throw new MediaPipelineBackendException("Invalid codecQuery", MediaPipelineBackendResultCode.AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_QUERY, e);
        }
    }
}
