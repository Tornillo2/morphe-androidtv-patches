package com.amazon.livingroom.di;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import com.amazon.ignitionshared.recommendation.factory.ChannelFactory;
import com.amazon.ignitionshared.recommendation.factory.NotificationFactory;
import com.amazon.ignitionshared.recommendation.factory.ProgramFactory;
import com.amazon.ignitionshared.recommendation.provider.ChannelProvider;
import com.amazon.ignitionshared.recommendation.publisher.ChannelPublisher;
import com.amazon.ignitionshared.recommendation.publisher.NotificationPublisher;
import com.amazon.ignitionshared.recommendation.publisher.RecommendationPublisher;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module
public final class RecommendationModule {

    @NotNull
    public static final RecommendationModule INSTANCE = new RecommendationModule();

    @ApplicationScope
    @Provides
    @NotNull
    public final RecommendationPublisher provideRecommendationPublisher(@ApplicationContext @NotNull Context context, @Named(Names.MAIN_ACTIVITY_NAME) @NotNull ComponentName deepLinkActivityName, @Named(Names.RECOMMENDATION_APPLICATION_NAME) @NotNull String applicationName, @Named(Names.RECOMMENDATION_APPLICATION_ICON) int i, @Named(Names.RECOMMENDATION_APPLICATION_COLOR) int i2, @Named(Names.RECOMMENDATION_DEFAULT_CHANNEL_ID) @NotNull String defaultChannelId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deepLinkActivityName, "deepLinkActivityName");
        Intrinsics.checkNotNullParameter(applicationName, "applicationName");
        Intrinsics.checkNotNullParameter(defaultChannelId, "defaultChannelId");
        return Build.VERSION.SDK_INT >= 26 ? new ChannelPublisher(context, new ChannelProvider(new ChannelFactory(deepLinkActivityName, applicationName), defaultChannelId), new ProgramFactory(), deepLinkActivityName, i) : new NotificationPublisher(context, new NotificationFactory(i, i2), deepLinkActivityName);
    }
}
