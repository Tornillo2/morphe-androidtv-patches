package com.amazon.ignitionshared.pear;

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
public final class Id {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String type;

    @NotNull
    public final String value;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Id> serializer() {
            return Id$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Id(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, Id$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.type = str;
        this.value = str2;
    }

    public static /* synthetic */ Id copy$default(Id id, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = id.type;
        }
        if ((i & 2) != 0) {
            str2 = id.value;
        }
        return id.copy(str, str2);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(Id id, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, id.type);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, id.value);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.value;
    }

    @NotNull
    public final Id copy(@NotNull String type, @NotNull String value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        return new Id(type, value);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Id)) {
            return false;
        }
        Id id = (Id) obj;
        return Intrinsics.areEqual(this.type, id.type) && Intrinsics.areEqual(this.value, id.value);
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + (this.type.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "Id(type=" + this.type + ", value=" + this.value + ")";
    }

    public Id(@NotNull String type, @NotNull String value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        this.type = type;
        this.value = value;
    }
}
