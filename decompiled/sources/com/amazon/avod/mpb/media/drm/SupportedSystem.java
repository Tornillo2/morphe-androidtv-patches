package com.amazon.avod.mpb.media.drm;

import java.lang.annotation.Annotation;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class SupportedSystem {

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final List<EncryptionScheme> encryptionSchemes;
    public final int maxSupportedSessions;

    @NotNull
    public final DrmSystemName name;

    @NotNull
    public final List<SessionType> sessionTypes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<SupportedSystem> serializer() {
            return SupportedSystem$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new SupportedSystem$$ExternalSyntheticLambda0()), LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new SupportedSystem$$ExternalSyntheticLambda1()), LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new SupportedSystem$$ExternalSyntheticLambda2()), null};
    }

    public /* synthetic */ SupportedSystem(int i, DrmSystemName drmSystemName, List list, List list2, int i2, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, SupportedSystem$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.name = drmSystemName;
        this.encryptionSchemes = list;
        this.sessionTypes = list2;
        this.maxSupportedSessions = i2;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return EnumsKt.createAnnotatedEnumSerializer("com.amazon.avod.mpb.media.drm.DrmSystemName", DrmSystemName.values(), new String[]{"com.widevine.alpha", "com.microsoft.playready"}, new Annotation[][]{null, null}, null);
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
        return new ArrayListSerializer(EnumsKt.createAnnotatedEnumSerializer("com.amazon.avod.mpb.media.drm.EncryptionScheme", EncryptionScheme.values(), new String[]{"cenc", "cbcs"}, new Annotation[][]{null, null}, null));
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
        return new ArrayListSerializer(EnumsKt.createAnnotatedEnumSerializer("com.amazon.avod.mpb.media.drm.SessionType", SessionType.values(), new String[]{"persistent", "non-persistent"}, new Annotation[][]{null, null}, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SupportedSystem copy$default(SupportedSystem supportedSystem, DrmSystemName drmSystemName, List list, List list2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            drmSystemName = supportedSystem.name;
        }
        if ((i2 & 2) != 0) {
            list = supportedSystem.encryptionSchemes;
        }
        if ((i2 & 4) != 0) {
            list2 = supportedSystem.sessionTypes;
        }
        if ((i2 & 8) != 0) {
            i = supportedSystem.maxSupportedSessions;
        }
        return supportedSystem.copy(drmSystemName, list, list2, i);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$core_mpb_release(SupportedSystem supportedSystem, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, lazyArr[0].getValue(), supportedSystem.name);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), supportedSystem.encryptionSchemes);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, lazyArr[2].getValue(), supportedSystem.sessionTypes);
        compositeEncoder.encodeIntElement(serialDescriptor, 3, supportedSystem.maxSupportedSessions);
    }

    @NotNull
    public final DrmSystemName component1() {
        return this.name;
    }

    @NotNull
    public final List<EncryptionScheme> component2() {
        return this.encryptionSchemes;
    }

    @NotNull
    public final List<SessionType> component3() {
        return this.sessionTypes;
    }

    public final int component4() {
        return this.maxSupportedSessions;
    }

    @NotNull
    public final SupportedSystem copy(@NotNull DrmSystemName name, @NotNull List<? extends EncryptionScheme> encryptionSchemes, @NotNull List<? extends SessionType> sessionTypes, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(encryptionSchemes, "encryptionSchemes");
        Intrinsics.checkNotNullParameter(sessionTypes, "sessionTypes");
        return new SupportedSystem(name, encryptionSchemes, sessionTypes, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SupportedSystem)) {
            return false;
        }
        SupportedSystem supportedSystem = (SupportedSystem) obj;
        return this.name == supportedSystem.name && Intrinsics.areEqual(this.encryptionSchemes, supportedSystem.encryptionSchemes) && Intrinsics.areEqual(this.sessionTypes, supportedSystem.sessionTypes) && this.maxSupportedSessions == supportedSystem.maxSupportedSessions;
    }

    @NotNull
    public final List<EncryptionScheme> getEncryptionSchemes() {
        return this.encryptionSchemes;
    }

    public final int getMaxSupportedSessions() {
        return this.maxSupportedSessions;
    }

    @NotNull
    public final DrmSystemName getName() {
        return this.name;
    }

    @NotNull
    public final List<SessionType> getSessionTypes() {
        return this.sessionTypes;
    }

    public int hashCode() {
        return ((this.sessionTypes.hashCode() + ((this.encryptionSchemes.hashCode() + (this.name.hashCode() * 31)) * 31)) * 31) + this.maxSupportedSessions;
    }

    @NotNull
    public String toString() {
        return "SupportedSystem(name=" + this.name + ", encryptionSchemes=" + this.encryptionSchemes + ", sessionTypes=" + this.sessionTypes + ", maxSupportedSessions=" + this.maxSupportedSessions + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SupportedSystem(@NotNull DrmSystemName name, @NotNull List<? extends EncryptionScheme> encryptionSchemes, @NotNull List<? extends SessionType> sessionTypes, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(encryptionSchemes, "encryptionSchemes");
        Intrinsics.checkNotNullParameter(sessionTypes, "sessionTypes");
        this.name = name;
        this.encryptionSchemes = encryptionSchemes;
        this.sessionTypes = sessionTypes;
        this.maxSupportedSessions = i;
    }
}
