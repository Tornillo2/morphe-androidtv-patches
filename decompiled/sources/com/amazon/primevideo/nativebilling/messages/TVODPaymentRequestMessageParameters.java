package com.amazon.primevideo.nativebilling.messages;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class TVODPaymentRequestMessageParameters {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String obfuscatedAccountId;

    @NotNull
    public final String offerIdToken;

    @NotNull
    public final String productId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<TVODPaymentRequestMessageParameters> serializer() {
            return TVODPaymentRequestMessageParameters$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ TVODPaymentRequestMessageParameters(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, TVODPaymentRequestMessageParameters$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.productId = str;
        this.obfuscatedAccountId = str2;
        this.offerIdToken = str3;
    }

    public static /* synthetic */ TVODPaymentRequestMessageParameters copy$default(TVODPaymentRequestMessageParameters tVODPaymentRequestMessageParameters, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tVODPaymentRequestMessageParameters.productId;
        }
        if ((i & 2) != 0) {
            str2 = tVODPaymentRequestMessageParameters.obfuscatedAccountId;
        }
        if ((i & 4) != 0) {
            str3 = tVODPaymentRequestMessageParameters.offerIdToken;
        }
        return tVODPaymentRequestMessageParameters.copy(str, str2, str3);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(TVODPaymentRequestMessageParameters tVODPaymentRequestMessageParameters, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, tVODPaymentRequestMessageParameters.productId);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, tVODPaymentRequestMessageParameters.obfuscatedAccountId);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, tVODPaymentRequestMessageParameters.offerIdToken);
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
        return this.offerIdToken;
    }

    @NotNull
    public final TVODPaymentRequestMessageParameters copy(@NotNull String productId, @NotNull String obfuscatedAccountId, @NotNull String offerIdToken) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        Intrinsics.checkNotNullParameter(offerIdToken, "offerIdToken");
        return new TVODPaymentRequestMessageParameters(productId, obfuscatedAccountId, offerIdToken);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TVODPaymentRequestMessageParameters)) {
            return false;
        }
        TVODPaymentRequestMessageParameters tVODPaymentRequestMessageParameters = (TVODPaymentRequestMessageParameters) obj;
        return Intrinsics.areEqual(this.productId, tVODPaymentRequestMessageParameters.productId) && Intrinsics.areEqual(this.obfuscatedAccountId, tVODPaymentRequestMessageParameters.obfuscatedAccountId) && Intrinsics.areEqual(this.offerIdToken, tVODPaymentRequestMessageParameters.offerIdToken);
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

    public int hashCode() {
        return this.offerIdToken.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.obfuscatedAccountId, this.productId.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.productId;
        String str2 = this.obfuscatedAccountId;
        return ActivityCompat$$ExternalSyntheticOutline0.m(TrackGroup$$ExternalSyntheticOutline0.m("TVODPaymentRequestMessageParameters(productId=", str, ", obfuscatedAccountId=", str2, ", offerIdToken="), this.offerIdToken, ")");
    }

    public TVODPaymentRequestMessageParameters(@NotNull String productId, @NotNull String obfuscatedAccountId, @NotNull String offerIdToken) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(obfuscatedAccountId, "obfuscatedAccountId");
        Intrinsics.checkNotNullParameter(offerIdToken, "offerIdToken");
        this.productId = productId;
        this.obfuscatedAccountId = obfuscatedAccountId;
        this.offerIdToken = offerIdToken;
    }
}
