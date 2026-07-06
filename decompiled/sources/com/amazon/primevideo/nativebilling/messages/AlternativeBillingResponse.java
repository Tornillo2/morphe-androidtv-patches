package com.amazon.primevideo.nativebilling.messages;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
@SourceDebugExtension({"SMAP\nAlternativeBillingResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AlternativeBillingResponse.kt\ncom/amazon/primevideo/nativebilling/messages/AlternativeBillingResponse\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,11:1\n205#2:12\n*S KotlinDebug\n*F\n+ 1 AlternativeBillingResponse.kt\ncom/amazon/primevideo/nativebilling/messages/AlternativeBillingResponse\n*L\n9#1:12\n*E\n"})
public final class AlternativeBillingResponse {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final String debugMessage;

    @NotNull
    public final String requestId;
    public final int responseCode;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<AlternativeBillingResponse> serializer() {
            return AlternativeBillingResponse$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ AlternativeBillingResponse(int i, String str, int i2, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, AlternativeBillingResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.requestId = str;
        this.responseCode = i2;
        this.debugMessage = str2;
    }

    public static /* synthetic */ AlternativeBillingResponse copy$default(AlternativeBillingResponse alternativeBillingResponse, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = alternativeBillingResponse.requestId;
        }
        if ((i2 & 2) != 0) {
            i = alternativeBillingResponse.responseCode;
        }
        if ((i2 & 4) != 0) {
            str2 = alternativeBillingResponse.debugMessage;
        }
        return alternativeBillingResponse.copy(str, i, str2);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(AlternativeBillingResponse alternativeBillingResponse, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, alternativeBillingResponse.requestId);
        compositeEncoder.encodeIntElement(serialDescriptor, 1, alternativeBillingResponse.responseCode);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, alternativeBillingResponse.debugMessage);
    }

    @NotNull
    public final String component1() {
        return this.requestId;
    }

    public final int component2() {
        return this.responseCode;
    }

    @Nullable
    public final String component3() {
        return this.debugMessage;
    }

    @NotNull
    public final AlternativeBillingResponse copy(@NotNull String requestId, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return new AlternativeBillingResponse(requestId, i, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlternativeBillingResponse)) {
            return false;
        }
        AlternativeBillingResponse alternativeBillingResponse = (AlternativeBillingResponse) obj;
        return Intrinsics.areEqual(this.requestId, alternativeBillingResponse.requestId) && this.responseCode == alternativeBillingResponse.responseCode && Intrinsics.areEqual(this.debugMessage, alternativeBillingResponse.debugMessage);
    }

    @Nullable
    public final String getDebugMessage() {
        return this.debugMessage;
    }

    @NotNull
    public final String getRequestId() {
        return this.requestId;
    }

    public final int getResponseCode() {
        return this.responseCode;
    }

    public int hashCode() {
        int iHashCode = ((this.requestId.hashCode() * 31) + this.responseCode) * 31;
        String str = this.debugMessage;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public final String toJsonString() {
        Json.Default r0 = Json.Default;
        r0.getClass();
        return r0.encodeToString(Companion.serializer(), this);
    }

    @NotNull
    public String toString() {
        String str = this.requestId;
        int i = this.responseCode;
        String str2 = this.debugMessage;
        StringBuilder sb = new StringBuilder("AlternativeBillingResponse(requestId=");
        sb.append(str);
        sb.append(", responseCode=");
        sb.append(i);
        sb.append(", debugMessage=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str2, ")");
    }

    public AlternativeBillingResponse(@NotNull String requestId, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.requestId = requestId;
        this.responseCode = i;
        this.debugMessage = str;
    }
}
