package com.amazon.primevideo.nativebilling.messages;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import kotlin.Unit;
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
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class TvodPurchaseMessageParameters {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Json json = JsonKt.Json$default(null, new TvodPurchaseMessageParameters$$ExternalSyntheticLambda0(), 1, null);

    @NotNull
    public final String obfuscatedAccountId;

    @NotNull
    public final String offerIdToken;

    @NotNull
    public final String productId;

    @NotNull
    public final String requestId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nTvodPurchaseMessageParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TvodPurchaseMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/TvodPurchaseMessageParameters$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,41:1\n222#2:42\n*S KotlinDebug\n*F\n+ 1 TvodPurchaseMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/TvodPurchaseMessageParameters$Companion\n*L\n38#1:42\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final TvodPurchaseMessageParameters of(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Json json = TvodPurchaseMessageParameters.json;
            json.getClass();
            return (TvodPurchaseMessageParameters) json.decodeFromString(TvodPurchaseMessageParameters.Companion.serializer(), message);
        }

        @NotNull
        public final KSerializer<TvodPurchaseMessageParameters> serializer() {
            return TvodPurchaseMessageParameters$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ TvodPurchaseMessageParameters(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, TvodPurchaseMessageParameters$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.requestId = str;
        this.productId = str2;
        this.offerIdToken = str3;
        this.obfuscatedAccountId = str4;
    }

    public static /* synthetic */ TvodPurchaseMessageParameters copy$default(TvodPurchaseMessageParameters tvodPurchaseMessageParameters, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tvodPurchaseMessageParameters.requestId;
        }
        if ((i & 2) != 0) {
            str2 = tvodPurchaseMessageParameters.productId;
        }
        if ((i & 4) != 0) {
            str3 = tvodPurchaseMessageParameters.offerIdToken;
        }
        if ((i & 8) != 0) {
            str4 = tvodPurchaseMessageParameters.obfuscatedAccountId;
        }
        return tvodPurchaseMessageParameters.copy(str, str2, str3, str4);
    }

    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(TvodPurchaseMessageParameters tvodPurchaseMessageParameters, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, tvodPurchaseMessageParameters.requestId);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, tvodPurchaseMessageParameters.productId);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, tvodPurchaseMessageParameters.offerIdToken);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, tvodPurchaseMessageParameters.obfuscatedAccountId);
    }

    @NotNull
    public final String component1() {
        return this.requestId;
    }

    @NotNull
    public final String component2() {
        return this.productId;
    }

    @NotNull
    public final String component3() {
        return this.offerIdToken;
    }

    @NotNull
    public final String component4() {
        return this.obfuscatedAccountId;
    }

    @NotNull
    public final TvodPurchaseMessageParameters copy(@NotNull String requestId, @NotNull String productId, @NotNull String offerIdToken, @NotNull String obfuscatedAccountId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(offerIdToken, "offerIdToken");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        return new TvodPurchaseMessageParameters(requestId, productId, offerIdToken, obfuscatedAccountId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!TvodPurchaseMessageParameters.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.amazon.primevideo.nativebilling.messages.TvodPurchaseMessageParameters");
        TvodPurchaseMessageParameters tvodPurchaseMessageParameters = (TvodPurchaseMessageParameters) obj;
        return Intrinsics.areEqual(this.requestId, tvodPurchaseMessageParameters.requestId) && Intrinsics.areEqual(this.productId, tvodPurchaseMessageParameters.productId) && Intrinsics.areEqual(this.offerIdToken, tvodPurchaseMessageParameters.offerIdToken) && Intrinsics.areEqual(this.obfuscatedAccountId, tvodPurchaseMessageParameters.obfuscatedAccountId);
    }

    @NotNull
    public final String getObfuscatedAccountId() {
        return this.obfuscatedAccountId;
    }

    @NotNull
    public final String getOfferIdToken() {
        return this.offerIdToken;
    }

    @NotNull
    public final String getProductId() {
        return this.productId;
    }

    @NotNull
    public final String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return this.obfuscatedAccountId.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.offerIdToken, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.productId, this.requestId.hashCode() * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.requestId;
        String str2 = this.productId;
        String str3 = this.offerIdToken;
        String str4 = this.obfuscatedAccountId;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("TvodPurchaseMessageParameters(requestId=", str, ", productId=", str2, ", offerIdToken=");
        sbM.append(str3);
        sbM.append(", obfuscatedAccountId=");
        sbM.append(str4);
        sbM.append(")");
        return sbM.toString();
    }

    public TvodPurchaseMessageParameters(@NotNull String requestId, @NotNull String productId, @NotNull String offerIdToken, @NotNull String obfuscatedAccountId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(offerIdToken, "offerIdToken");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        this.requestId = requestId;
        this.productId = productId;
        this.offerIdToken = offerIdToken;
        this.obfuscatedAccountId = obfuscatedAccountId;
    }
}
