package com.amazon.avod.mpb.api.query;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import com.google.android.gms.common.Scopes;
import java.lang.annotation.Annotation;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class CodecQuery {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final Map<CodecQueryAttributeKey, String> attributes;

    @NotNull
    public final String mimeSubType;

    @NotNull
    public final MediaType mimeType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<CodecQuery> serializer() {
            return CodecQuery$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new CodecQuery$$ExternalSyntheticLambda0()), null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new CodecQuery$$ExternalSyntheticLambda1())};
    }

    public /* synthetic */ CodecQuery(int i, MediaType mediaType, String str, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, CodecQuery$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.mimeType = mediaType;
        this.mimeSubType = str;
        this.attributes = map;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return EnumsKt.createSimpleEnumSerializer("com.amazon.avod.mpb.api.query.MediaType", MediaType.values());
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
        return new LinkedHashMapSerializer(EnumsKt.createAnnotatedEnumSerializer("com.amazon.avod.mpb.api.query.CodecQueryAttributeKey", CodecQueryAttributeKey.values(), new String[]{"codecs", "width", "height", "bitrate", "framerate", Scopes.PROFILE, "level", "hdrMetadataType", "colorGamut", "transferFunction", "channels", "samplerate"}, new Annotation[][]{null, null, null, null, null, null, null, null, null, null, null, null}, null), StringSerializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CodecQuery copy$default(CodecQuery codecQuery, MediaType mediaType, String str, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            mediaType = codecQuery.mimeType;
        }
        if ((i & 2) != 0) {
            str = codecQuery.mimeSubType;
        }
        if ((i & 4) != 0) {
            map = codecQuery.attributes;
        }
        return codecQuery.copy(mediaType, str, map);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$core_mpb_release(CodecQuery codecQuery, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, lazyArr[0].getValue(), codecQuery.mimeType);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, codecQuery.mimeSubType);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, lazyArr[2].getValue(), codecQuery.attributes);
    }

    @NotNull
    public final MediaType component1() {
        return this.mimeType;
    }

    @NotNull
    public final String component2() {
        return this.mimeSubType;
    }

    @NotNull
    public final Map<CodecQueryAttributeKey, String> component3() {
        return this.attributes;
    }

    @NotNull
    public final CodecQuery copy(@NotNull MediaType mimeType, @NotNull String mimeSubType, @NotNull Map<CodecQueryAttributeKey, String> attributes) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(mimeSubType, "mimeSubType");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new CodecQuery(mimeType, mimeSubType, attributes);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CodecQuery)) {
            return false;
        }
        CodecQuery codecQuery = (CodecQuery) obj;
        return this.mimeType == codecQuery.mimeType && Intrinsics.areEqual(this.mimeSubType, codecQuery.mimeSubType) && Intrinsics.areEqual(this.attributes, codecQuery.attributes);
    }

    @NotNull
    public final Map<CodecQueryAttributeKey, String> getAttributes() {
        return this.attributes;
    }

    @NotNull
    public final String getMimeSubType() {
        return this.mimeSubType;
    }

    @NotNull
    public final MediaType getMimeType() {
        return this.mimeType;
    }

    public int hashCode() {
        return this.attributes.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.mimeSubType, this.mimeType.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        return "CodecQuery(mimeType=" + this.mimeType + ", mimeSubType=" + this.mimeSubType + ", attributes=" + this.attributes + ")";
    }

    public CodecQuery(@NotNull MediaType mimeType, @NotNull String mimeSubType, @NotNull Map<CodecQueryAttributeKey, String> attributes) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(mimeSubType, "mimeSubType");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.mimeType = mimeType;
        this.mimeSubType = mimeSubType;
        this.attributes = attributes;
    }
}
