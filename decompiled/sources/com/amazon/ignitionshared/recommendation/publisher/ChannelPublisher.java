package com.amazon.ignitionshared.recommendation.publisher;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.tvprovider.media.tv.ChannelLogoUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.amazon.ignitionshared.recommendation.factory.ProgramFactory;
import com.amazon.ignitionshared.recommendation.model.Recommendation;
import com.amazon.ignitionshared.recommendation.provider.ChannelProvider;
import com.amazon.reporting.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
public final class ChannelPublisher implements RecommendationPublisher {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "ChannelPublisher";

    @NotNull
    public final ChannelProvider channelProvider;

    @NotNull
    public final Context context;

    @NotNull
    public final ComponentName deepLinkActivityName;
    public final int icon;

    @NotNull
    public final ProgramFactory programFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ChannelPublisher(@NotNull Context context, @NotNull ChannelProvider channelProvider, @NotNull ProgramFactory programFactory, @NotNull ComponentName deepLinkActivityName, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(channelProvider, "channelProvider");
        Intrinsics.checkNotNullParameter(programFactory, "programFactory");
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        this.context = context;
        this.channelProvider = channelProvider;
        this.programFactory = programFactory;
        this.deepLinkActivityName = deepLinkActivityName;
        this.icon = i;
    }

    @Override // com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher
    public boolean clearRecommendations() {
        Log.d(TAG, "Clearing recommendations");
        long defaultChannelId = this.channelProvider.getDefaultChannelId(this.context);
        if (defaultChannelId == 0) {
            return true;
        }
        this.context.getContentResolver().delete(TvContractCompat.buildPreviewProgramsUriForChannel(defaultChannelId), null, null);
        return true;
    }

    @Override // com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher
    public boolean publishRecommendations(@NotNull Collection<Recommendation> recommendations) {
        Intrinsics.checkNotNullParameter(recommendations, "recommendations");
        long orCreateDefaultChannelId = this.channelProvider.getOrCreateDefaultChannelId(this.context);
        if (orCreateDefaultChannelId == 0) {
            Log.e(TAG, "Couldn't publish recommendations. No default channel found");
            return false;
        }
        try {
            writeChannelLogo(this.context, orCreateDefaultChannelId, this.icon);
            publishRecommendationsOnChannel(new ArrayList(recommendations), orCreateDefaultChannelId);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final void publishRecommendationsOnChannel(List<Recommendation> list, long j) {
        long j2;
        Object objCreateFailure;
        int i = 0;
        for (Recommendation recommendation : list) {
            int i2 = i + 1;
            int size = list.size() - i;
            if (recommendation.groupPosition == 0) {
                try {
                    j2 = j;
                } catch (Throwable th) {
                    th = th;
                    j2 = j;
                }
                try {
                    objCreateFailure = Long.valueOf(this.programFactory.createProgram(this.context, j2, recommendation, size, this.deepLinkActivityName));
                } catch (Throwable th2) {
                    th = th2;
                    objCreateFailure = ResultKt.createFailure(th);
                }
                if (Result.m583exceptionOrNullimpl(objCreateFailure) != null) {
                    Log.e(TAG, "Failed to create recommendation");
                }
            } else {
                j2 = j;
            }
            i = i2;
            j = j2;
        }
    }

    public final void writeChannelLogo(Context context, long j, @DrawableRes int i) {
        ChannelLogoUtils.storeChannelLogo(context, j, BitmapFactory.decodeResource(context.getResources(), i));
    }
}
