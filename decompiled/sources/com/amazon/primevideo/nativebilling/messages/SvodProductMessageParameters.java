package com.amazon.primevideo.nativebilling.messages;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
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
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class SvodProductMessageParameters {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Json json = JsonKt.Json$default(null, new SvodProductMessageParameters$$ExternalSyntheticLambda0(), 1, null);

    @NotNull
    public final String basePlanId;

    @NotNull
    public final String obfuscatedAccountId;

    @Nullable
    public final String offerId;

    @NotNull
    public final String productId;

    @NotNull
    public final String requestId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nSvodProductMessageParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SvodProductMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/SvodProductMessageParameters$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,44:1\n222#2:45\n*S KotlinDebug\n*F\n+ 1 SvodProductMessageParameters.kt\ncom/amazon/primevideo/nativebilling/messages/SvodProductMessageParameters$Companion\n*L\n41#1:45\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final SvodProductMessageParameters of(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Json json = SvodProductMessageParameters.json;
            json.getClass();
            return (SvodProductMessageParameters) json.decodeFromString(SvodProductMessageParameters.Companion.serializer(), message);
        }

        @NotNull
        public final KSerializer<SvodProductMessageParameters> serializer() {
            return SvodProductMessageParameters$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ SvodProductMessageParameters(int i, String str, String str2, String str3, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, SvodProductMessageParameters$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.requestId = str;
        this.productId = str2;
        this.basePlanId = str3;
        this.obfuscatedAccountId = str4;
        if ((i & 16) == 0) {
            this.offerId = null;
        } else {
            this.offerId = str5;
        }
    }

    public static /* synthetic */ SvodProductMessageParameters copy$default(SvodProductMessageParameters svodProductMessageParameters, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = svodProductMessageParameters.requestId;
        }
        if ((i & 2) != 0) {
            str2 = svodProductMessageParameters.productId;
        }
        if ((i & 4) != 0) {
            str3 = svodProductMessageParameters.basePlanId;
        }
        if ((i & 8) != 0) {
            str4 = svodProductMessageParameters.obfuscatedAccountId;
        }
        if ((i & 16) != 0) {
            str5 = svodProductMessageParameters.offerId;
        }
        String str6 = str5;
        String str7 = str3;
        return svodProductMessageParameters.copy(str, str2, str7, str4, str6);
    }

    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(SvodProductMessageParameters svodProductMessageParameters, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, svodProductMessageParameters.requestId);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, svodProductMessageParameters.productId);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, svodProductMessageParameters.basePlanId);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, svodProductMessageParameters.obfuscatedAccountId);
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) && svodProductMessageParameters.offerId == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, svodProductMessageParameters.offerId);
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
        return this.basePlanId;
    }

    @NotNull
    public final String component4() {
        return this.obfuscatedAccountId;
    }

    @Nullable
    public final String component5() {
        return this.offerId;
    }

    @NotNull
    public final SvodProductMessageParameters copy(@NotNull String requestId, @NotNull String productId, @NotNull String basePlanId, @NotNull String obfuscatedAccountId, @Nullable String str) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(basePlanId, "basePlanId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        return new SvodProductMessageParameters(requestId, productId, basePlanId, obfuscatedAccountId, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!SvodProductMessageParameters.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.amazon.primevideo.nativebilling.messages.SvodProductMessageParameters");
        SvodProductMessageParameters svodProductMessageParameters = (SvodProductMessageParameters) obj;
        return Intrinsics.areEqual(this.requestId, svodProductMessageParameters.requestId) && Intrinsics.areEqual(this.productId, svodProductMessageParameters.productId) && Intrinsics.areEqual(this.basePlanId, svodProductMessageParameters.basePlanId) && Intrinsics.areEqual(this.obfuscatedAccountId, svodProductMessageParameters.obfuscatedAccountId) && Intrinsics.areEqual(this.offerId, svodProductMessageParameters.offerId);
    }

    @NotNull
    public final String getBasePlanId() {
        return this.basePlanId;
    }

    @NotNull
    public final String getObfuscatedAccountId() {
        return this.obfuscatedAccountId;
    }

    @Nullable
    public final String getOfferId() {
        return this.offerId;
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
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.obfuscatedAccountId, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.basePlanId, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.productId, this.requestId.hashCode() * 31, 31), 31), 31);
        String str = this.offerId;
        return iM + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        String str = this.requestId;
        String str2 = this.productId;
        String str3 = this.basePlanId;
        String str4 = this.obfuscatedAccountId;
        String str5 = this.offerId;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("SvodProductMessageParameters(requestId=", str, ", productId=", str2, ", basePlanId=");
        sbM.append(str3);
        sbM.append(", obfuscatedAccountId=");
        sbM.append(str4);
        sbM.append(", offerId=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sbM, str5, ")");
    }

    public SvodProductMessageParameters(@NotNull String requestId, @NotNull String productId, @NotNull String basePlanId, @NotNull String obfuscatedAccountId, @Nullable String str) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(basePlanId, "basePlanId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        this.requestId = requestId;
        this.productId = productId;
        this.basePlanId = basePlanId;
        this.obfuscatedAccountId = obfuscatedAccountId;
        this.offerId = str;
    }

    public /* synthetic */ SvodProductMessageParameters(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? null : str5);
    }
}
