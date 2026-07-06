package com.amazon.ignitionshared.recommendation.factory;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.ignitionshared.recommendation.model.Recommendation;
import com.amazon.reporting.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nProgramFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProgramFactory.kt\ncom/amazon/ignitionshared/recommendation/factory/ProgramFactory\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,59:1\n29#2:60\n29#2:61\n*S KotlinDebug\n*F\n+ 1 ProgramFactory.kt\ncom/amazon/ignitionshared/recommendation/factory/ProgramFactory\n*L\n36#1:60\n37#1:61\n*E\n"})
public final class ProgramFactory {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "ProgramFactory";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public final Intent buildPendingIntent(ComponentName componentName, String str) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        DeepLinkIntentParser.Companion.getClass();
        intent.putExtra(DeepLinkIntentParser.Companion.internalDeepLinkKey, str);
        return intent;
    }

    @SuppressLint({"RestrictedApi"})
    public final long createProgram(@NotNull Context context, long j, @NotNull Recommendation recommendation, int i, @NotNull ComponentName deepLinkActivityName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(recommendation, "recommendation");
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        PreviewProgram.Builder builder = new PreviewProgram.Builder();
        builder.setChannelId(j);
        builder.setTitle(recommendation.title);
        builder.setDescription(recommendation.description);
        builder.setInternalProviderId(recommendation.id);
        builder.setType(4);
        builder.setWeight(i);
        PreviewProgram.Builder intent = builder.setIntent(buildPendingIntent(deepLinkActivityName, recommendation.actionData));
        intent.setThumbnailUri(Uri.parse(recommendation.cardImageUrl));
        intent.setPosterArtUri(Uri.parse(recommendation.cardImageUrl));
        PreviewProgram previewProgram = new PreviewProgram();
        previewProgram.mValues = intent.mValues;
        Uri uriInsert = context.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, previewProgram.toContentValues(false));
        if (uriInsert != null && !uriInsert.equals(Uri.EMPTY)) {
            return ContentUris.parseId(uriInsert);
        }
        Log.e(TAG, "Insert program failed");
        return 0L;
    }
}
