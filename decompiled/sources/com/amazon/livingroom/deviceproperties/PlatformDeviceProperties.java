package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nPlatformDeviceProperties.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformDeviceProperties.kt\ncom/amazon/livingroom/deviceproperties/PlatformDeviceProperties\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,71:1\n1193#2,2:72\n1267#2,4:74\n*S KotlinDebug\n*F\n+ 1 PlatformDeviceProperties.kt\ncom/amazon/livingroom/deviceproperties/PlatformDeviceProperties\n*L\n35#1:72,2\n35#1:74,4\n*E\n"})
public final class PlatformDeviceProperties extends AbstractDeviceProperties {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG = "PlatformDeviceProperties";

    @NotNull
    public final AbstractDeviceProperties defaultProperties;

    @NotNull
    public final Map<String, Function1<DeviceProperties, ?>> properties;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public PlatformDeviceProperties(@NotNull AbstractDeviceProperties defaultProperties, @NotNull List<? extends PlatformProperty<?>> platformProperties) {
        Intrinsics.checkNotNullParameter(defaultProperties, "defaultProperties");
        Intrinsics.checkNotNullParameter(platformProperties, "platformProperties");
        this.defaultProperties = defaultProperties;
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(platformProperties, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity < 16 ? 16 : iMapCapacity);
        Iterator<T> it = platformProperties.iterator();
        while (it.hasNext()) {
            PlatformProperty platformProperty = (PlatformProperty) it.next();
            linkedHashMap.put(platformProperty.originalProperty.getName(), platformProperty.platformGetter);
        }
        this.properties = linkedHashMap;
    }

    @Override // com.amazon.livingroom.deviceproperties.AbstractDeviceProperties
    @Nullable
    public <T> T get(@NotNull DeviceProperties.Property<T> property, @NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        Function1<DeviceProperties, ?> function1 = this.properties.get(property.getName());
        if (function1 == null) {
            return (T) this.defaultProperties.get(property, otherProperties);
        }
        T t = (T) function1.invoke(this.defaultProperties);
        if (t != null) {
            Log.d(LOG_TAG, String.format(Locale.US, "Property '%s' is overridden by platform to '%s'.", Arrays.copyOf(new Object[]{property.getName(), t}, 2)));
            return t;
        }
        Log.e(LOG_TAG, String.format(Locale.US, "Failed to fetch platform property '%s'", Arrays.copyOf(new Object[]{property.getName()}, 1)));
        return (T) this.defaultProperties.get(property, otherProperties);
    }
}
