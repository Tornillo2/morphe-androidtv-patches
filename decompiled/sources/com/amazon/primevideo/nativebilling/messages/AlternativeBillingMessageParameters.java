package com.amazon.primevideo.nativebilling.messages;

import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class AlternativeBillingMessageParameters {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Json json = JsonKt.Json$default(null, new AlternativeBillingMessageParameters$$ExternalSyntheticLambda0(), 1, null);

    @NotNull
    public final String requestId;

    @Nullable
    public final Boolean willRequestAnotherToken;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nAlternativeBillingMessageParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AlternativeBillingMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/AlternativeBillingMessageParameters$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,15:1\n222#2:16\n*S KotlinDebug\n*F\n+ 1 AlternativeBillingMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/AlternativeBillingMessageParameters$Companion\n*L\n12#1:16\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final AlternativeBillingMessageParameters of(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Json json = AlternativeBillingMessageParameters.json;
            json.getClass();
            return (AlternativeBillingMessageParameters) json.decodeFromString(AlternativeBillingMessageParameters.Companion.serializer(), message);
        }

        @NotNull
        public final KSerializer<AlternativeBillingMessageParameters> serializer() {
            return AlternativeBillingMessageParameters$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ AlternativeBillingMessageParameters(int i, String str, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, AlternativeBillingMessageParameters$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.requestId = str;
        if ((i & 2) == 0) {
            this.willRequestAnotherToken = Boolean.FALSE;
        } else {
            this.willRequestAnotherToken = bool;
        }
    }

    public static /* synthetic */ AlternativeBillingMessageParameters copy$default(AlternativeBillingMessageParameters alternativeBillingMessageParameters, String str, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = alternativeBillingMessageParameters.requestId;
        }
        if ((i & 2) != 0) {
            bool = alternativeBillingMessageParameters.willRequestAnotherToken;
        }
        return alternativeBillingMessageParameters.copy(str, bool);
    }

    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(AlternativeBillingMessageParameters alternativeBillingMessageParameters, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, alternativeBillingMessageParameters.requestId);
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) && Intrinsics.areEqual(alternativeBillingMessageParameters.willRequestAnotherToken, Boolean.FALSE)) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, BooleanSerializer.INSTANCE, alternativeBillingMessageParameters.willRequestAnotherToken);
    }

    @NotNull
    public final String component1() {
        return this.requestId;
    }

    @Nullable
    public final Boolean component2() {
        return this.willRequestAnotherToken;
    }

    @NotNull
    public final AlternativeBillingMessageParameters copy(@NotNull String requestId, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return new AlternativeBillingMessageParameters(requestId, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlternativeBillingMessageParameters)) {
            return false;
        }
        AlternativeBillingMessageParameters alternativeBillingMessageParameters = (AlternativeBillingMessageParameters) obj;
        return Intrinsics.areEqual(this.requestId, alternativeBillingMessageParameters.requestId) && Intrinsics.areEqual(this.willRequestAnotherToken, alternativeBillingMessageParameters.willRequestAnotherToken);
    }

    @NotNull
    public final String getRequestId() {
        return this.requestId;
    }

    @Nullable
    public final Boolean getWillRequestAnotherToken() {
        return this.willRequestAnotherToken;
    }

    public int hashCode() {
        int iHashCode = this.requestId.hashCode() * 31;
        Boolean bool = this.willRequestAnotherToken;
        return iHashCode + (bool == null ? 0 : bool.hashCode());
    }

    @NotNull
    public String toString() {
        return "AlternativeBillingMessageParameters(requestId=" + this.requestId + ", willRequestAnotherToken=" + this.willRequestAnotherToken + ")";
    }

    public AlternativeBillingMessageParameters(@NotNull String requestId, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.requestId = requestId;
        this.willRequestAnotherToken = bool;
    }

    public /* synthetic */ AlternativeBillingMessageParameters(String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? Boolean.FALSE : bool);
    }
}
