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
public final class RecommendationsV1 {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final Credentials credentials;

    @NotNull
    public final Error error;

    @NotNull
    public final Request request;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<RecommendationsV1> serializer() {
            return RecommendationsV1$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ RecommendationsV1(int i, Credentials credentials, Error error, Request request, SerializationConstructorMarker serializationConstructorMarker) {
        if (6 != (i & 6)) {
            PluginExceptionsKt.throwMissingFieldException(i, 6, RecommendationsV1$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        if ((i & 1) == 0) {
            this.credentials = null;
        } else {
            this.credentials = credentials;
        }
        this.error = error;
        this.request = request;
    }

    public static /* synthetic */ RecommendationsV1 copy$default(RecommendationsV1 recommendationsV1, Credentials credentials, Error error, Request request, int i, Object obj) {
        if ((i & 1) != 0) {
            credentials = recommendationsV1.credentials;
        }
        if ((i & 2) != 0) {
            error = recommendationsV1.error;
        }
        if ((i & 4) != 0) {
            request = recommendationsV1.request;
        }
        return recommendationsV1.copy(credentials, error, request);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(RecommendationsV1 recommendationsV1, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || recommendationsV1.credentials != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, Credentials$$serializer.INSTANCE, recommendationsV1.credentials);
        }
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Error$$serializer.INSTANCE, recommendationsV1.error);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, Request$$serializer.INSTANCE, recommendationsV1.request);
    }

    @Nullable
    public final Credentials component1() {
        return this.credentials;
    }

    @NotNull
    public final Error component2() {
        return this.error;
    }

    @NotNull
    public final Request component3() {
        return this.request;
    }

    @NotNull
    public final RecommendationsV1 copy(@Nullable Credentials credentials, @NotNull Error error, @NotNull Request request) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(request, "request");
        return new RecommendationsV1(credentials, error, request);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecommendationsV1)) {
            return false;
        }
        RecommendationsV1 recommendationsV1 = (RecommendationsV1) obj;
        return Intrinsics.areEqual(this.credentials, recommendationsV1.credentials) && Intrinsics.areEqual(this.error, recommendationsV1.error) && Intrinsics.areEqual(this.request, recommendationsV1.request);
    }

    @Nullable
    public final Credentials getCredentials() {
        return this.credentials;
    }

    @NotNull
    public final Error getError() {
        return this.error;
    }

    @NotNull
    public final Request getRequest() {
        return this.request;
    }

    public int hashCode() {
        Credentials credentials = this.credentials;
        return this.request.requestV1.hashCode() + ((this.error.requestV1.hashCode() + ((credentials == null ? 0 : credentials.oauth20V1.hashCode()) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        return "RecommendationsV1(credentials=" + this.credentials + ", error=" + this.error + ", request=" + this.request + ")";
    }

    public RecommendationsV1(@Nullable Credentials credentials, @NotNull Error error, @NotNull Request request) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(request, "request");
        this.credentials = credentials;
        this.error = error;
        this.request = request;
    }

    public /* synthetic */ RecommendationsV1(Credentials credentials, Error error, Request request, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : credentials, error, request);
    }
}
