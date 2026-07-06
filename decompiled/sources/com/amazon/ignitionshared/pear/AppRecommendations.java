package com.amazon.ignitionshared.pear;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class AppRecommendations {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String app;

    @NotNull
    public final Feed feeds;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<AppRecommendations> serializer() {
            return AppRecommendations$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ AppRecommendations(int i, String str, Feed feed, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, AppRecommendations$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.app = str;
        this.feeds = feed;
    }

    public static /* synthetic */ AppRecommendations copy$default(AppRecommendations appRecommendations, String str, Feed feed, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appRecommendations.app;
        }
        if ((i & 2) != 0) {
            feed = appRecommendations.feeds;
        }
        return appRecommendations.copy(str, feed);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(AppRecommendations appRecommendations, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, appRecommendations.app);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Feed$$serializer.INSTANCE, appRecommendations.feeds);
    }

    @NotNull
    public final String component1() {
        return this.app;
    }

    @NotNull
    public final Feed component2() {
        return this.feeds;
    }

    @NotNull
    public final AppRecommendations copy(@NotNull String app, @NotNull Feed feeds) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(feeds, "feeds");
        return new AppRecommendations(app, feeds);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppRecommendations)) {
            return false;
        }
        AppRecommendations appRecommendations = (AppRecommendations) obj;
        return Intrinsics.areEqual(this.app, appRecommendations.app) && Intrinsics.areEqual(this.feeds, appRecommendations.feeds);
    }

    @NotNull
    public final String getApp() {
        return this.app;
    }

    @NotNull
    public final Feed getFeeds() {
        return this.feeds;
    }

    public int hashCode() {
        return this.feeds.recsV1.hashCode() + (this.app.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "AppRecommendations(app=" + this.app + ", feeds=" + this.feeds + ")";
    }

    public AppRecommendations(@NotNull String app, @NotNull Feed feeds) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(feeds, "feeds");
        this.app = app;
        this.feeds = feeds;
    }
}
