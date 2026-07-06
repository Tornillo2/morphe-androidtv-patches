package com.amazon.avod.mpb.api.core;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PropertyDefinition<T> {
    public final boolean isMutable;

    @NotNull
    public final String key;

    @NotNull
    public final Serializer<T> serializer;
    public final boolean shouldNotifyOnChange;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Serializer<Integer> intSerializer = new PropertyDefinition$Companion$intSerializer$1();

    @NotNull
    public static final Serializer<Boolean> booleanSerializer = new PropertyDefinition$Companion$booleanSerializer$1();

    @NotNull
    public static final Serializer<Double> doubleSerializer = new PropertyDefinition$Companion$doubleSerializer$1();

    @NotNull
    public static final Serializer<Float> floatSerializer = new PropertyDefinition$Companion$floatSerializer$1();

    @NotNull
    public static final Serializer<String> stringSerializer = new PropertyDefinition$Companion$stringSerializer$1();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final Serializer<Boolean> getBooleanSerializer() {
            return PropertyDefinition.booleanSerializer;
        }

        @NotNull
        public final Serializer<Double> getDoubleSerializer() {
            return PropertyDefinition.doubleSerializer;
        }

        @NotNull
        public final Serializer<Float> getFloatSerializer() {
            return PropertyDefinition.floatSerializer;
        }

        @NotNull
        public final Serializer<Integer> getIntSerializer() {
            return PropertyDefinition.intSerializer;
        }

        @NotNull
        public final Serializer<String> getStringSerializer() {
            return PropertyDefinition.stringSerializer;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Serializer<T> {
        T fromString(@NotNull String str);

        @NotNull
        String toString(T t);
    }

    public PropertyDefinition(@NotNull String key, @NotNull Serializer<T> serializer, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.key = key;
        this.serializer = serializer;
        this.isMutable = z;
        this.shouldNotifyOnChange = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PropertyDefinition copy$default(PropertyDefinition propertyDefinition, String str, Serializer serializer, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = propertyDefinition.key;
        }
        if ((i & 2) != 0) {
            serializer = propertyDefinition.serializer;
        }
        if ((i & 4) != 0) {
            z = propertyDefinition.isMutable;
        }
        if ((i & 8) != 0) {
            z2 = propertyDefinition.shouldNotifyOnChange;
        }
        return propertyDefinition.copy(str, serializer, z, z2);
    }

    @NotNull
    public final String component1() {
        return this.key;
    }

    public final Serializer<T> component2() {
        return this.serializer;
    }

    public final boolean component3() {
        return this.isMutable;
    }

    public final boolean component4() {
        return this.shouldNotifyOnChange;
    }

    @NotNull
    public final PropertyDefinition<T> copy(@NotNull String key, @NotNull Serializer<T> serializer, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        return new PropertyDefinition<>(key, serializer, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PropertyDefinition)) {
            return false;
        }
        PropertyDefinition propertyDefinition = (PropertyDefinition) obj;
        return Intrinsics.areEqual(this.key, propertyDefinition.key) && Intrinsics.areEqual(this.serializer, propertyDefinition.serializer) && this.isMutable == propertyDefinition.isMutable && this.shouldNotifyOnChange == propertyDefinition.shouldNotifyOnChange;
    }

    public final T fromString(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return this.serializer.fromString(str);
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public final boolean getShouldNotifyOnChange() {
        return this.shouldNotifyOnChange;
    }

    public int hashCode() {
        return MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.shouldNotifyOnChange) + ((MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.isMutable) + ((this.serializer.hashCode() + (this.key.hashCode() * 31)) * 31)) * 31);
    }

    public final boolean isMutable() {
        return this.isMutable;
    }

    @NotNull
    public String toString() {
        return "PropertyDefinition(key=" + this.key + ", serializer=" + this.serializer + ", isMutable=" + this.isMutable + ", shouldNotifyOnChange=" + this.shouldNotifyOnChange + ")";
    }

    @NotNull
    public final String toString(T t) {
        return this.serializer.toString(t);
    }

    public /* synthetic */ PropertyDefinition(String str, Serializer serializer, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, serializer, (i & 4) != 0 ? true : z, (i & 8) != 0 ? false : z2);
    }
}
