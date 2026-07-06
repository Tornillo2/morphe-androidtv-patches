package com.amazon.ignitionshared.pear;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class VisualItem {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String mimeType;

    @NotNull
    public final String type;

    @NotNull
    public final String url;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<VisualItem> serializer() {
            return VisualItem$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ VisualItem(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, VisualItem$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.type = str;
        this.mimeType = str2;
        this.url = str3;
    }

    public static /* synthetic */ VisualItem copy$default(VisualItem visualItem, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = visualItem.type;
        }
        if ((i & 2) != 0) {
            str2 = visualItem.mimeType;
        }
        if ((i & 4) != 0) {
            str3 = visualItem.url;
        }
        return visualItem.copy(str, str2, str3);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(VisualItem visualItem, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, visualItem.type);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, visualItem.mimeType);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, visualItem.url);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.mimeType;
    }

    @NotNull
    public final String component3() {
        return this.url;
    }

    @NotNull
    public final VisualItem copy(@NotNull String type, @NotNull String mimeType, @NotNull String url) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(url, "url");
        return new VisualItem(type, mimeType, url);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisualItem)) {
            return false;
        }
        VisualItem visualItem = (VisualItem) obj;
        return Intrinsics.areEqual(this.type, visualItem.type) && Intrinsics.areEqual(this.mimeType, visualItem.mimeType) && Intrinsics.areEqual(this.url, visualItem.url);
    }

    @NotNull
    public final String getMimeType() {
        return this.mimeType;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return this.url.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.mimeType, this.type.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.type;
        String str2 = this.mimeType;
        return ActivityCompat$$ExternalSyntheticOutline0.m(TrackGroup$$ExternalSyntheticOutline0.m("VisualItem(type=", str, ", mimeType=", str2, ", url="), this.url, ")");
    }

    public VisualItem(@NotNull String type, @NotNull String mimeType, @NotNull String url) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(url, "url");
        this.type = type;
        this.mimeType = mimeType;
        this.url = url;
    }

    @SerialName("mime-type")
    public static /* synthetic */ void getMimeType$annotations() {
    }
}
