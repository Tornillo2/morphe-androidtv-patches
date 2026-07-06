package com.amazon.ignitionshared.recommendation.factory;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.amazon.reporting.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
@SourceDebugExtension({"SMAP\nChannelFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChannelFactory.kt\ncom/amazon/ignitionshared/recommendation/factory/ChannelFactory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,57:1\n1#2:58\n*E\n"})
public final class ChannelFactory {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "ChannelFactory";

    @NotNull
    public final String applicationName;

    @NotNull
    public final ComponentName deepLinkActivityName;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ChannelFactory(@NotNull ComponentName deepLinkActivityName, @NotNull String applicationName) {
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        this.deepLinkActivityName = deepLinkActivityName;
        this.applicationName = applicationName;
    }

    public final long createDefaultChannel(@NotNull Context context, @NotNull String internalId) throws Exception {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(internalId, "internalId");
        String strBuildInputId = TvContract.buildInputId(this.deepLinkActivityName);
        Intent intent = new Intent();
        intent.setComponent(this.deepLinkActivityName);
        Channel.Builder builder = new Channel.Builder();
        builder.setDisplayName(this.applicationName);
        builder.setType(TvContractCompat.Channels.TYPE_PREVIEW);
        builder.setInputId(strBuildInputId);
        builder.setAppLinkIntent(intent);
        builder.setInternalProviderId(internalId);
        Channel channelBuild = builder.build();
        try {
            Uri uriInsert = context.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, channelBuild.toContentValues(false));
            if (uriInsert != null && !uriInsert.equals(Uri.EMPTY)) {
                long id = ContentUris.parseId(uriInsert);
                TvContractCompat.requestChannelBrowsable(context, id);
                return id;
            }
            Log.e(TAG, "Insert channel failed");
            return 0L;
        } catch (IllegalArgumentException e) {
            throw new Exception("Illegal argument exception thrown with URI: " + TvContractCompat.Channels.CONTENT_URI + " channel: " + channelBuild.toContentValues(false), e);
        }
    }
}
