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
public final class RefreshRequest {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final HttpRequestV1 requestV1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<RefreshRequest> serializer() {
            return RefreshRequest$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ RefreshRequest(int i, HttpRequestV1 httpRequestV1, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.requestV1 = httpRequestV1;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, RefreshRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ RefreshRequest copy$default(RefreshRequest refreshRequest, HttpRequestV1 httpRequestV1, int i, Object obj) {
        if ((i & 1) != 0) {
            httpRequestV1 = refreshRequest.requestV1;
        }
        return refreshRequest.copy(httpRequestV1);
    }

    @NotNull
    public final HttpRequestV1 component1() {
        return this.requestV1;
    }

    @NotNull
    public final RefreshRequest copy(@NotNull HttpRequestV1 requestV1) {
        Intrinsics.checkNotNullParameter(requestV1, "requestV1");
        return new RefreshRequest(requestV1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RefreshRequest) && Intrinsics.areEqual(this.requestV1, ((RefreshRequest) obj).requestV1);
    }

    @NotNull
    public final HttpRequestV1 getRequestV1() {
        return this.requestV1;
    }

    public int hashCode() {
        return this.requestV1.hashCode();
    }

    @NotNull
    public String toString() {
        return "RefreshRequest(requestV1=" + this.requestV1 + ")";
    }

    public RefreshRequest(@NotNull HttpRequestV1 requestV1) {
        Intrinsics.checkNotNullParameter(requestV1, "requestV1");
        this.requestV1 = requestV1;
    }

    @SerialName("http-request-v1")
    public static /* synthetic */ void getRequestV1$annotations() {
    }
}
