package com.amazon.ignitionshared.pear;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class Update {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final RecommendationsV1 recsV1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Update> serializer() {
            return Update$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Update(int i, RecommendationsV1 recommendationsV1, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.recsV1 = recommendationsV1;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, Update$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ Update copy$default(Update update, RecommendationsV1 recommendationsV1, int i, Object obj) {
        if ((i & 1) != 0) {
            recommendationsV1 = update.recsV1;
        }
        return update.copy(recommendationsV1);
    }

    @NotNull
    public final RecommendationsV1 component1() {
        return this.recsV1;
    }

    @NotNull
    public final Update copy(@NotNull RecommendationsV1 recsV1) {
        Intrinsics.checkNotNullParameter(recsV1, "recsV1");
        return new Update(recsV1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Update) && Intrinsics.areEqual(this.recsV1, ((Update) obj).recsV1);
    }

    @NotNull
    public final RecommendationsV1 getRecsV1() {
        return this.recsV1;
    }

    public int hashCode() {
        return this.recsV1.hashCode();
    }

    @NotNull
    public String toString() {
        return "Update(recsV1=" + this.recsV1 + ")";
    }

    public Update(@NotNull RecommendationsV1 recsV1) {
        Intrinsics.checkNotNullParameter(recsV1, "recsV1");
        this.recsV1 = recsV1;
    }

    @SerialName("recommendations-v1")
    public static /* synthetic */ void getRecsV1$annotations() {
    }
}
