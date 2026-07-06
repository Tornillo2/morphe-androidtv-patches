package com.amazon.ignitionshared.pear;

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
public final class Visuals {

    @NotNull
    public final List<VisualItem> items;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Visuals$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Visuals> serializer() {
            return Visuals$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Visuals(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.items = list;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, Visuals$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(VisualItem$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Visuals copy$default(Visuals visuals, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = visuals.items;
        }
        return visuals.copy(list);
    }

    @NotNull
    public final List<VisualItem> component1() {
        return this.items;
    }

    @NotNull
    public final Visuals copy(@NotNull List<VisualItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return new Visuals(items);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Visuals) && Intrinsics.areEqual(this.items, ((Visuals) obj).items);
    }

    @NotNull
    public final List<VisualItem> getItems() {
        return this.items;
    }

    public int hashCode() {
        return this.items.hashCode();
    }

    @NotNull
    public String toString() {
        return "Visuals(items=" + this.items + ")";
    }

    public Visuals(@NotNull List<VisualItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
    }
}
