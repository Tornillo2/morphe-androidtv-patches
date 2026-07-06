package com.amazon.ignitionshared.recommendation.contentprovider;

import android.content.Context;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ContentProviderAuthorityKt {
    @NotNull
    public static final String getContentProviderAuthority(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(context.getPackageName(), ".provider");
    }
}
