package com.amazon.ignitionshared.pear;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
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
public final class Oauth20V1 {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final RefreshRequest refreshRequest;

    @NotNull
    public String refreshToken;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Oauth20V1> serializer() {
            return Oauth20V1$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Oauth20V1(int i, RefreshRequest refreshRequest, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, Oauth20V1$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.refreshRequest = refreshRequest;
        this.refreshToken = str;
    }

    public static /* synthetic */ Oauth20V1 copy$default(Oauth20V1 oauth20V1, RefreshRequest refreshRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            refreshRequest = oauth20V1.refreshRequest;
        }
        if ((i & 2) != 0) {
            str = oauth20V1.refreshToken;
        }
        return oauth20V1.copy(refreshRequest, str);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(Oauth20V1 oauth20V1, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, RefreshRequest$$serializer.INSTANCE, oauth20V1.refreshRequest);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, oauth20V1.refreshToken);
    }

    @NotNull
    public final RefreshRequest component1() {
        return this.refreshRequest;
    }

    @NotNull
    public final String component2() {
        return this.refreshToken;
    }

    @NotNull
    public final Oauth20V1 copy(@NotNull RefreshRequest refreshRequest, @NotNull String refreshToken) {
        Intrinsics.checkNotNullParameter(refreshRequest, "refreshRequest");
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
        return new Oauth20V1(refreshRequest, refreshToken);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Oauth20V1)) {
            return false;
        }
        Oauth20V1 oauth20V1 = (Oauth20V1) obj;
        return Intrinsics.areEqual(this.refreshRequest, oauth20V1.refreshRequest) && Intrinsics.areEqual(this.refreshToken, oauth20V1.refreshToken);
    }

    @NotNull
    public final RefreshRequest getRefreshRequest() {
        return this.refreshRequest;
    }

    @NotNull
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public int hashCode() {
        return this.refreshToken.hashCode() + (this.refreshRequest.requestV1.hashCode() * 31);
    }

    public final void setRefreshToken(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.refreshToken = str;
    }

    @NotNull
    public String toString() {
        return "Oauth20V1(refreshRequest=" + this.refreshRequest + ", refreshToken=" + this.refreshToken + ")";
    }

    public Oauth20V1(@NotNull RefreshRequest refreshRequest, @NotNull String refreshToken) {
        Intrinsics.checkNotNullParameter(refreshRequest, "refreshRequest");
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
        this.refreshRequest = refreshRequest;
        this.refreshToken = refreshToken;
    }

    @SerialName("refresh-request")
    public static /* synthetic */ void getRefreshRequest$annotations() {
    }

    @SerialName("refresh-token")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }
}
