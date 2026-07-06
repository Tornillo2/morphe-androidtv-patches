package com.amazon.livingroom.di;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument {
    public final String flag;
    public final DeviceProperties.Property<?> property;

    public CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument(String flag, DeviceProperties.Property<?> property) {
        Intrinsics.checkNotNullParameter(flag, "flag");
        Intrinsics.checkNotNullParameter(property, "property");
        this.flag = flag;
        this.property = property;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument copy$default(CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument, String str, DeviceProperties.Property property, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument.flag;
        }
        if ((i & 2) != 0) {
            property = coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument.property;
        }
        return coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument.copy(str, property);
    }

    public final String component1() {
        return this.flag;
    }

    public final DeviceProperties.Property<?> component2() {
        return this.property;
    }

    public final CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument copy(String flag, DeviceProperties.Property<?> property) {
        Intrinsics.checkNotNullParameter(flag, "flag");
        Intrinsics.checkNotNullParameter(property, "property");
        return new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument(flag, property);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument)) {
            return false;
        }
        CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument = (CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument) obj;
        return Intrinsics.areEqual(this.flag, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument.flag) && Intrinsics.areEqual(this.property, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument.property);
    }

    public final String getFlag() {
        return this.flag;
    }

    public final DeviceProperties.Property<?> getProperty() {
        return this.property;
    }

    public int hashCode() {
        return this.property.hashCode() + (this.flag.hashCode() * 31);
    }

    public String toString() {
        return "CommandLineArgument(flag=" + this.flag + ", property=" + this.property + ")";
    }
}
