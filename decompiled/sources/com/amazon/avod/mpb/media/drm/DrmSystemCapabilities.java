package com.amazon.avod.mpb.media.drm;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class DrmSystemCapabilities {

    @NotNull
    public final List<SupportedSystem> supportedSystems;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new DrmSystemCapabilities$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<DrmSystemCapabilities> serializer() {
            return DrmSystemCapabilities$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ DrmSystemCapabilities(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.supportedSystems = list;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, DrmSystemCapabilities$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(SupportedSystem$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DrmSystemCapabilities copy$default(DrmSystemCapabilities drmSystemCapabilities, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = drmSystemCapabilities.supportedSystems;
        }
        return drmSystemCapabilities.copy(list);
    }

    @NotNull
    public final List<SupportedSystem> component1() {
        return this.supportedSystems;
    }

    @NotNull
    public final DrmSystemCapabilities copy(@NotNull List<SupportedSystem> supportedSystems) {
        Intrinsics.checkNotNullParameter(supportedSystems, "supportedSystems");
        return new DrmSystemCapabilities(supportedSystems);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrmSystemCapabilities) && Intrinsics.areEqual(this.supportedSystems, ((DrmSystemCapabilities) obj).supportedSystems);
    }

    @NotNull
    public final List<SupportedSystem> getSupportedSystems() {
        return this.supportedSystems;
    }

    public int hashCode() {
        return this.supportedSystems.hashCode();
    }

    @NotNull
    public String toString() {
        return "DrmSystemCapabilities(supportedSystems=" + this.supportedSystems + ")";
    }

    public DrmSystemCapabilities(@NotNull List<SupportedSystem> supportedSystems) {
        Intrinsics.checkNotNullParameter(supportedSystems, "supportedSystems");
        this.supportedSystems = supportedSystems;
    }
}
