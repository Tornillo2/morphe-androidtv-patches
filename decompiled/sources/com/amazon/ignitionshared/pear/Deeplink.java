package com.amazon.ignitionshared.pear;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class Deeplink {

    @NotNull
    public static final Companion Companion = new Companion();

    /* JADX INFO: renamed from: android, reason: collision with root package name */
    @NotNull
    public final String f5android;

    @Nullable
    public final String html5;

    @NotNull
    public final String nativeV1;

    @NotNull
    public final String nativeV2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Deeplink> serializer() {
            return Deeplink$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Deeplink(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
        if (14 != (i & 14)) {
            PluginExceptionsKt.throwMissingFieldException(i, 14, Deeplink$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        if ((i & 1) == 0) {
            this.html5 = null;
        } else {
            this.html5 = str;
        }
        this.nativeV1 = str2;
        this.nativeV2 = str3;
        this.f5android = str4;
    }

    public static /* synthetic */ Deeplink copy$default(Deeplink deeplink, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deeplink.html5;
        }
        if ((i & 2) != 0) {
            str2 = deeplink.nativeV1;
        }
        if ((i & 4) != 0) {
            str3 = deeplink.nativeV2;
        }
        if ((i & 8) != 0) {
            str4 = deeplink.f5android;
        }
        return deeplink.copy(str, str2, str3, str4);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(Deeplink deeplink, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || deeplink.html5 != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, deeplink.html5);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 1, deeplink.nativeV1);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, deeplink.nativeV2);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, deeplink.f5android);
    }

    @Nullable
    public final String component1() {
        return this.html5;
    }

    @NotNull
    public final String component2() {
        return this.nativeV1;
    }

    @NotNull
    public final String component3() {
        return this.nativeV2;
    }

    @NotNull
    public final String component4() {
        return this.f5android;
    }

    @NotNull
    public final Deeplink copy(@Nullable String str, @NotNull String nativeV1, @NotNull String nativeV2, @NotNull String android2) {
        Intrinsics.checkNotNullParameter(nativeV1, "nativeV1");
        Intrinsics.checkNotNullParameter(nativeV2, "nativeV2");
        Intrinsics.checkNotNullParameter(android2, "android");
        return new Deeplink(str, nativeV1, nativeV2, android2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deeplink)) {
            return false;
        }
        Deeplink deeplink = (Deeplink) obj;
        return Intrinsics.areEqual(this.html5, deeplink.html5) && Intrinsics.areEqual(this.nativeV1, deeplink.nativeV1) && Intrinsics.areEqual(this.nativeV2, deeplink.nativeV2) && Intrinsics.areEqual(this.f5android, deeplink.f5android);
    }

    @NotNull
    public final String getAndroid() {
        return this.f5android;
    }

    @Nullable
    public final String getHtml5() {
        return this.html5;
    }

    @NotNull
    public final String getNativeV1() {
        return this.nativeV1;
    }

    @NotNull
    public final String getNativeV2() {
        return this.nativeV2;
    }

    public int hashCode() {
        String str = this.html5;
        return this.f5android.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.nativeV2, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.nativeV1, (str == null ? 0 : str.hashCode()) * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.html5;
        String str2 = this.nativeV1;
        String str3 = this.nativeV2;
        String str4 = this.f5android;
        StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Deeplink(html5=", str, ", nativeV1=", str2, ", nativeV2=");
        sbM.append(str3);
        sbM.append(", android=");
        sbM.append(str4);
        sbM.append(")");
        return sbM.toString();
    }

    public Deeplink(@Nullable String str, @NotNull String nativeV1, @NotNull String nativeV2, @NotNull String android2) {
        Intrinsics.checkNotNullParameter(nativeV1, "nativeV1");
        Intrinsics.checkNotNullParameter(nativeV2, "nativeV2");
        Intrinsics.checkNotNullParameter(android2, "android");
        this.html5 = str;
        this.nativeV1 = nativeV1;
        this.nativeV2 = nativeV2;
        this.f5android = android2;
    }

    public /* synthetic */ Deeplink(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, str4);
    }

    @SerialName("native-v1")
    public static /* synthetic */ void getNativeV1$annotations() {
    }

    @SerialName("native-v2")
    public static /* synthetic */ void getNativeV2$annotations() {
    }
}
