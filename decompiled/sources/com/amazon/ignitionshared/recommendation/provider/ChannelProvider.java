package com.amazon.ignitionshared.recommendation.provider;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.amazon.ignitionshared.recommendation.factory.ChannelFactory;
import com.amazon.reporting.Log;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
public final class ChannelProvider {
    public static final String TAG = "ChannelProvider";

    @NotNull
    public final ChannelFactory channelFactory;

    @NotNull
    public final Bundle defaultChannelBundle;

    @NotNull
    public final String defaultChannelInternalId;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String[] CHANNELS_MAP_PROJECTION = {"_id", "internal_provider_id", "browsable"};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ChannelProvider(@NotNull ChannelFactory channelFactory, @NotNull String defaultChannelInternalId) {
        Intrinsics.checkNotNullParameter(channelFactory, "channelFactory");
        Intrinsics.checkNotNullParameter(defaultChannelInternalId, "defaultChannelInternalId");
        this.channelFactory = channelFactory;
        this.defaultChannelInternalId = defaultChannelInternalId;
        Bundle bundle = new Bundle(1);
        bundle.putString("internal_provider_id", defaultChannelInternalId);
        this.defaultChannelBundle = bundle;
    }

    public final long createDefaultChannel(Context context) {
        return this.channelFactory.createDefaultChannel(context, this.defaultChannelInternalId);
    }

    public final long getDefaultChannelId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long j = 0;
        try {
            Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.Channels.CONTENT_URI, CHANNELS_MAP_PROJECTION, this.defaultChannelBundle, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToNext()) {
                        int columnIndex = cursorQuery.getColumnIndex("_id");
                        if (columnIndex < 0) {
                            throw new Exception("Column index not found.");
                        }
                        j = cursorQuery.getLong(columnIndex);
                    }
                    if (cursorQuery.getCount() > 1) {
                        Log.w(TAG, "Default channel id found more than once");
                    }
                } finally {
                }
            }
            CloseableKt.closeFinally(cursorQuery, null);
            return j;
        } catch (Exception unused) {
            Log.e(TAG, "Error getting Default channel id");
            return 0L;
        }
    }

    public final long getOrCreateDefaultChannelId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long defaultChannelId = getDefaultChannelId(context);
        return defaultChannelId == 0 ? this.channelFactory.createDefaultChannel(context, this.defaultChannelInternalId) : defaultChannelId;
    }
}
