package com.amazon.ignitionshared.recommendation.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RecommendationException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationException(@NotNull String detailMessage) {
        super(detailMessage);
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationException(@NotNull Throwable throwable) {
        super(throwable);
        Intrinsics.checkNotNullParameter(throwable, "throwable");
    }
}
