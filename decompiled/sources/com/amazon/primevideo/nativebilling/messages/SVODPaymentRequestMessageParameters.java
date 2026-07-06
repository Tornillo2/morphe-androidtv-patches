package com.amazon.primevideo.nativebilling.messages;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class SVODPaymentRequestMessageParameters {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String basePlanId;

    @NotNull
    public final String obfuscatedAccountId;

    @Nullable
    public final String offerId;

    @NotNull
    public final String productId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<SVODPaymentRequestMessageParameters> serializer() {
            return SVODPaymentRequestMessageParameters$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ SVODPaymentRequestMessageParameters(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, SVODPaymentRequestMessageParameters$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.productId = str;
        this.obfuscatedAccountId = str2;
        this.basePlanId = str3;
        if ((i & 8) == 0) {
            this.offerId = null;
        } else {
            this.offerId = str4;
        }
    }

    public static /* synthetic */ SVODPaymentRequestMessageParameters copy$default(SVODPaymentRequestMessageParameters sVODPaymentRequestMessageParameters, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sVODPaymentRequestMessageParameters.productId;
        }
        if ((i & 2) != 0) {
            str2 = sVODPaymentRequestMessageParameters.obfuscatedAccountId;
        }
        if ((i & 4) != 0) {
            str3 = sVODPaymentRequestMessageParameters.basePlanId;
        }
        if ((i & 8) != 0) {
            str4 = sVODPaymentRequestMessageParameters.offerId;
        }
        return sVODPaymentRequestMessageParameters.copy(str, str2, str3, str4);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(SVODPaymentRequestMessageParameters sVODPaymentRequestMessageParameters, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, sVODPaymentRequestMessageParameters.productId);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, sVODPaymentRequestMessageParameters.obfuscatedAccountId);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, sVODPaymentRequestMessageParameters.basePlanId);
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) && sVODPaymentRequestMessageParameters.offerId == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, sVODPaymentRequestMessageParameters.offerId);
    }

    @NotNull
    public final String component1() {
        return this.productId;
    }

    @NotNull
    public final String component2() {
        return this.obfuscatedAccountId;
    }

    @NotNull
    public final String component3() {
        return this.basePlanId;
    }

    @Nullable
    public final String component4() {
        return this.offerId;
    }

    @NotNull
    public final SVODPaymentRequestMessageParameters copy(@NotNull String productId, @NotNull String obfuscatedAccountId, @NotNull String basePlanId, @Nullable String str) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        Intrinsics.checkNotNullParameter(basePlanId, "basePlanId");
        return new SVODPaymentRequestMessageParameters(productId, obfuscatedAccountId, basePlanId, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SVODPaymentRequestMessageParameters)) {
            return false;
        }
        SVODPaymentRequestMessageParameters sVODPaymentRequestMessageParameters = (SVODPaymentRequestMessageParameters) obj;
        return Intrinsics.areEqual(this.productId, sVODPaymentRequestMessageParameters.productId) && Intrinsics.areEqual(this.obfuscatedAccountId, sVODPaymentRequestMessageParameters.obfuscatedAccountId) && Intrinsics.areEqual(this.basePlanId, sVODPaymentRequestMessageParameters.basePlanId) && Intrinsics.areEqual(this.offerId, sVODPaymentRequestMessageParameters.offerId);
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

    public int hashCode() {
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.basePlanId, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.obfuscatedAccountId, this.productId.hashCode() * 31, 31), 31);
        String str = this.offerId;
        return iM + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.productId;
        String str2 = this.obfuscatedAccountId;
        String str3 = this.basePlanId;
        String str4 = this.offerId;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("SVODPaymentRequestMessageParameters(productId=", str, ", obfuscatedAccountId=", str2, ", basePlanId=");
        sbM.append(str3);
        sbM.append(", offerId=");
        sbM.append(str4);
        sbM.append(")");
        return sbM.toString();
    }

    public SVODPaymentRequestMessageParameters(@NotNull String productId, @NotNull String obfuscatedAccountId, @NotNull String basePlanId, @Nullable String str) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        Intrinsics.checkNotNullParameter(basePlanId, "basePlanId");
        this.productId = productId;
        this.obfuscatedAccountId = obfuscatedAccountId;
        this.basePlanId = basePlanId;
        this.offerId = str;
    }

    public /* synthetic */ SVODPaymentRequestMessageParameters(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4);
    }
}
