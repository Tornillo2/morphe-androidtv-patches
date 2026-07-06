package com.amazon.primevideo.nativebilling.messages;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
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
@SourceDebugExtension({"SMAP\nStoreCountryQueryResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StoreCountryQueryResponse.kt\ncom/amazon/primevideo/nativebilling/messages/StoreCountryQueryResponse\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,11:1\n205#2:12\n*S KotlinDebug\n*F\n+ 1 StoreCountryQueryResponse.kt\ncom/amazon/primevideo/nativebilling/messages/StoreCountryQueryResponse\n*L\n9#1:12\n*E\n"})
public final class StoreCountryQueryResponse {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final String storeCountry;
    public final boolean success;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<StoreCountryQueryResponse> serializer() {
            return StoreCountryQueryResponse$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ StoreCountryQueryResponse(int i, boolean z, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, StoreCountryQueryResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.success = z;
        this.storeCountry = str;
    }

    public static StoreCountryQueryResponse copy$default(StoreCountryQueryResponse storeCountryQueryResponse, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = storeCountryQueryResponse.success;
        }
        if ((i & 2) != 0) {
            str = storeCountryQueryResponse.storeCountry;
        }
        storeCountryQueryResponse.getClass();
        return new StoreCountryQueryResponse(z, str);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(StoreCountryQueryResponse storeCountryQueryResponse, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeBooleanElement(serialDescriptor, 0, storeCountryQueryResponse.success);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, storeCountryQueryResponse.storeCountry);
    }

    public final boolean component1() {
        return this.success;
    }

    @Nullable
    public final String component2() {
        return this.storeCountry;
    }

    @NotNull
    public final StoreCountryQueryResponse copy(boolean z, @Nullable String str) {
        return new StoreCountryQueryResponse(z, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoreCountryQueryResponse)) {
            return false;
        }
        StoreCountryQueryResponse storeCountryQueryResponse = (StoreCountryQueryResponse) obj;
        return this.success == storeCountryQueryResponse.success && Intrinsics.areEqual(this.storeCountry, storeCountryQueryResponse.storeCountry);
    }

    @Nullable
    public final String getStoreCountry() {
        return this.storeCountry;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public int hashCode() {
        int iM = MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.success) * 31;
        String str = this.storeCountry;
        return iM + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public final String toJsonString() {
        Json.Default r0 = Json.Default;
        r0.getClass();
        return r0.encodeToString(Companion.serializer(), this);
    }

    @NotNull
    public String toString() {
        return "StoreCountryQueryResponse(success=" + this.success + ", storeCountry=" + this.storeCountry + ")";
    }

    public StoreCountryQueryResponse(boolean z, @Nullable String str) {
        this.success = z;
        this.storeCountry = str;
    }
}
