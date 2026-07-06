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
public final class GroupWidget {

    @NotNull
    public final List<TitleItemWidget> itemslist;

    @NotNull
    public final String title;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new GroupWidget$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<GroupWidget> serializer() {
            return GroupWidget$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ GroupWidget(int i, String str, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, GroupWidget$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.title = str;
        this.itemslist = list;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(TitleItemWidget$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GroupWidget copy$default(GroupWidget groupWidget, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = groupWidget.title;
        }
        if ((i & 2) != 0) {
            list = groupWidget.itemslist;
        }
        return groupWidget.copy(str, list);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(GroupWidget groupWidget, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, groupWidget.title);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), groupWidget.itemslist);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final List<TitleItemWidget> component2() {
        return this.itemslist;
    }

    @NotNull
    public final GroupWidget copy(@NotNull String title, @NotNull List<TitleItemWidget> itemslist) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(itemslist, "itemslist");
        return new GroupWidget(title, itemslist);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupWidget)) {
            return false;
        }
        GroupWidget groupWidget = (GroupWidget) obj;
        return Intrinsics.areEqual(this.title, groupWidget.title) && Intrinsics.areEqual(this.itemslist, groupWidget.itemslist);
    }

    @NotNull
    public final List<TitleItemWidget> getItemslist() {
        return this.itemslist;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.itemslist.hashCode() + (this.title.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "GroupWidget(title=" + this.title + ", itemslist=" + this.itemslist + ")";
    }

    public GroupWidget(@NotNull String title, @NotNull List<TitleItemWidget> itemslist) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(itemslist, "itemslist");
        this.title = title;
        this.itemslist = itemslist;
    }
}
