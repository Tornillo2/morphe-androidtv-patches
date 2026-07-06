package com.amazon.ignitionshared.pear;

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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class CollectionWidget {

    @NotNull
    public final WidgetDecorations decorations;

    @NotNull
    public final List<GroupWidget> widgetlist;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new CollectionWidget$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<CollectionWidget> serializer() {
            return CollectionWidget$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ CollectionWidget(int i, WidgetDecorations widgetDecorations, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CollectionWidget$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.decorations = widgetDecorations;
        this.widgetlist = list;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(GroupWidget$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CollectionWidget copy$default(CollectionWidget collectionWidget, WidgetDecorations widgetDecorations, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            widgetDecorations = collectionWidget.decorations;
        }
        if ((i & 2) != 0) {
            list = collectionWidget.widgetlist;
        }
        return collectionWidget.copy(widgetDecorations, list);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(CollectionWidget collectionWidget, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, WidgetDecorations$$serializer.INSTANCE, collectionWidget.decorations);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), collectionWidget.widgetlist);
    }

    @NotNull
    public final WidgetDecorations component1() {
        return this.decorations;
    }

    @NotNull
    public final List<GroupWidget> component2() {
        return this.widgetlist;
    }

    @NotNull
    public final CollectionWidget copy(@NotNull WidgetDecorations decorations, @NotNull List<GroupWidget> widgetlist) {
        Intrinsics.checkNotNullParameter(decorations, "decorations");
        Intrinsics.checkNotNullParameter(widgetlist, "widgetlist");
        return new CollectionWidget(decorations, widgetlist);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CollectionWidget)) {
            return false;
        }
        CollectionWidget collectionWidget = (CollectionWidget) obj;
        return Intrinsics.areEqual(this.decorations, collectionWidget.decorations) && Intrinsics.areEqual(this.widgetlist, collectionWidget.widgetlist);
    }

    @NotNull
    public final WidgetDecorations getDecorations() {
        return this.decorations;
    }

    @NotNull
    public final List<GroupWidget> getWidgetlist() {
        return this.widgetlist;
    }

    public int hashCode() {
        return this.widgetlist.hashCode() + (this.decorations.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "CollectionWidget(decorations=" + this.decorations + ", widgetlist=" + this.widgetlist + ")";
    }

    public CollectionWidget(@NotNull WidgetDecorations decorations, @NotNull List<GroupWidget> widgetlist) {
        Intrinsics.checkNotNullParameter(decorations, "decorations");
        Intrinsics.checkNotNullParameter(widgetlist, "widgetlist");
        this.decorations = decorations;
        this.widgetlist = widgetlist;
    }
}
