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
public final class Credentials {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final Oauth20V1 oauth20V1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Credentials> serializer() {
            return Credentials$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Credentials(int i, Oauth20V1 oauth20V1, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.oauth20V1 = oauth20V1;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, Credentials$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ Credentials copy$default(Credentials credentials, Oauth20V1 oauth20V1, int i, Object obj) {
        if ((i & 1) != 0) {
            oauth20V1 = credentials.oauth20V1;
        }
        return credentials.copy(oauth20V1);
    }

    @NotNull
    public final Oauth20V1 component1() {
        return this.oauth20V1;
    }

    @NotNull
    public final Credentials copy(@NotNull Oauth20V1 oauth20V1) {
        Intrinsics.checkNotNullParameter(oauth20V1, "oauth20V1");
        return new Credentials(oauth20V1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Credentials) && Intrinsics.areEqual(this.oauth20V1, ((Credentials) obj).oauth20V1);
    }

    @NotNull
    public final Oauth20V1 getOauth20V1() {
        return this.oauth20V1;
    }

    public int hashCode() {
        return this.oauth20V1.hashCode();
    }

    @NotNull
    public String toString() {
        return "Credentials(oauth20V1=" + this.oauth20V1 + ")";
    }

    public Credentials(@NotNull Oauth20V1 oauth20V1) {
        Intrinsics.checkNotNullParameter(oauth20V1, "oauth20V1");
        this.oauth20V1 = oauth20V1;
    }

    @SerialName("oauth-2.0-v1")
    public static /* synthetic */ void getOauth20V1$annotations() {
    }
}
