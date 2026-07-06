package com.amazon.primevideo.nativebilling.messages;

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
@SourceDebugExtension({"SMAP\nGetAlternativeBillingTokenResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetAlternativeBillingTokenResponse.kt\ncom/amazon/primevideo/nativebilling/messages/GetAlternativeBillingTokenResponse\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,16:1\n205#2:17\n*S KotlinDebug\n*F\n+ 1 GetAlternativeBillingTokenResponse.kt\ncom/amazon/primevideo/nativebilling/messages/GetAlternativeBillingTokenResponse\n*L\n14#1:17\n*E\n"})
public final class GetAlternativeBillingTokenResponse {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final String debugMessage;

    @NotNull
    public final String requestId;
    public final int responseCode;

    @Nullable
    public final String token;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<GetAlternativeBillingTokenResponse> serializer() {
            return GetAlternativeBillingTokenResponse$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ GetAlternativeBillingTokenResponse(int i, String str, int i2, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, GetAlternativeBillingTokenResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.requestId = str;
        this.responseCode = i2;
        this.debugMessage = str2;
        if ((i & 8) == 0) {
            this.token = null;
        } else {
            this.token = str3;
        }
    }

    public static /* synthetic */ GetAlternativeBillingTokenResponse copy$default(GetAlternativeBillingTokenResponse getAlternativeBillingTokenResponse, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = getAlternativeBillingTokenResponse.requestId;
        }
        if ((i2 & 2) != 0) {
            i = getAlternativeBillingTokenResponse.responseCode;
        }
        if ((i2 & 4) != 0) {
            str2 = getAlternativeBillingTokenResponse.debugMessage;
        }
        if ((i2 & 8) != 0) {
            str3 = getAlternativeBillingTokenResponse.token;
        }
        return getAlternativeBillingTokenResponse.copy(str, i, str2, str3);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(GetAlternativeBillingTokenResponse getAlternativeBillingTokenResponse, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, getAlternativeBillingTokenResponse.requestId);
        compositeEncoder.encodeIntElement(serialDescriptor, 1, getAlternativeBillingTokenResponse.responseCode);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, getAlternativeBillingTokenResponse.debugMessage);
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) && getAlternativeBillingTokenResponse.token == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, stringSerializer, getAlternativeBillingTokenResponse.token);
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

    @Nullable
    public final String component4() {
        return this.token;
    }

    @NotNull
    public final GetAlternativeBillingTokenResponse copy(@NotNull String requestId, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return new GetAlternativeBillingTokenResponse(requestId, i, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetAlternativeBillingTokenResponse)) {
            return false;
        }
        GetAlternativeBillingTokenResponse getAlternativeBillingTokenResponse = (GetAlternativeBillingTokenResponse) obj;
        return Intrinsics.areEqual(this.requestId, getAlternativeBillingTokenResponse.requestId) && this.responseCode == getAlternativeBillingTokenResponse.responseCode && Intrinsics.areEqual(this.debugMessage, getAlternativeBillingTokenResponse.debugMessage) && Intrinsics.areEqual(this.token, getAlternativeBillingTokenResponse.token);
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

    @Nullable
    public final String getToken() {
        return this.token;
    }

    public int hashCode() {
        int iHashCode = ((this.requestId.hashCode() * 31) + this.responseCode) * 31;
        String str = this.debugMessage;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.token;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public final String toJsonString() {
        Json.Default r0 = Json.Default;
        r0.getClass();
        return r0.encodeToString(Companion.serializer(), this);
    }

    @NotNull
    public String toString() {
        return "GetAlternativeBillingTokenResponse(requestId=" + this.requestId + ", responseCode=" + this.responseCode + ", debugMessage=" + this.debugMessage + ", token=" + this.token + ")";
    }

    public GetAlternativeBillingTokenResponse(@NotNull String requestId, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.requestId = requestId;
        this.responseCode = i;
        this.debugMessage = str;
        this.token = str2;
    }

    public /* synthetic */ GetAlternativeBillingTokenResponse(String str, int i, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 8) != 0 ? null : str3);
    }
}
