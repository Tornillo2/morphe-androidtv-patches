package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformProperty<T> {

    @NotNull
    public final DeviceProperties.Property<T> originalProperty;

    @NotNull
    public final Function1<DeviceProperties, T> platformGetter;

    /* JADX WARN: Multi-variable type inference failed */
    public PlatformProperty(@NotNull DeviceProperties.Property<T> originalProperty, @NotNull Function1<? super DeviceProperties, ? extends T> platformGetter) {
        Intrinsics.checkNotNullParameter(originalProperty, "originalProperty");
        Intrinsics.checkNotNullParameter(platformGetter, "platformGetter");
        this.originalProperty = originalProperty;
        this.platformGetter = platformGetter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlatformProperty copy$default(PlatformProperty platformProperty, DeviceProperties.Property property, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            property = platformProperty.originalProperty;
        }
        if ((i & 2) != 0) {
            function1 = platformProperty.platformGetter;
        }
        return platformProperty.copy(property, function1);
    }

    @NotNull
    public final DeviceProperties.Property<T> component1() {
        return this.originalProperty;
    }

    @NotNull
    public final Function1<DeviceProperties, T> component2() {
        return this.platformGetter;
    }

    @NotNull
    public final PlatformProperty<T> copy(@NotNull DeviceProperties.Property<T> originalProperty, @NotNull Function1<? super DeviceProperties, ? extends T> platformGetter) {
        Intrinsics.checkNotNullParameter(originalProperty, "originalProperty");
        Intrinsics.checkNotNullParameter(platformGetter, "platformGetter");
        return new PlatformProperty<>(originalProperty, platformGetter);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformProperty)) {
            return false;
        }
        PlatformProperty platformProperty = (PlatformProperty) obj;
        return Intrinsics.areEqual(this.originalProperty, platformProperty.originalProperty) && Intrinsics.areEqual(this.platformGetter, platformProperty.platformGetter);
    }

    @NotNull
    public final DeviceProperties.Property<T> getOriginalProperty() {
        return this.originalProperty;
    }

    @NotNull
    public final Function1<DeviceProperties, T> getPlatformGetter() {
        return this.platformGetter;
    }

    public int hashCode() {
        return this.platformGetter.hashCode() + (this.originalProperty.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "PlatformProperty(originalProperty=" + this.originalProperty + ", platformGetter=" + this.platformGetter + ")";
    }
}
