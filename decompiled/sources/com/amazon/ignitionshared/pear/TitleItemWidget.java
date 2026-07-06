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
public final class TitleItemWidget {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final TitleItemWidgetDecorations decorations;

    @NotNull
    public final String title;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<TitleItemWidget> serializer() {
            return TitleItemWidget$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ TitleItemWidget(int i, TitleItemWidgetDecorations titleItemWidgetDecorations, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, TitleItemWidget$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.decorations = titleItemWidgetDecorations;
        this.title = str;
    }

    public static /* synthetic */ TitleItemWidget copy$default(TitleItemWidget titleItemWidget, TitleItemWidgetDecorations titleItemWidgetDecorations, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            titleItemWidgetDecorations = titleItemWidget.decorations;
        }
        if ((i & 2) != 0) {
            str = titleItemWidget.title;
        }
        return titleItemWidget.copy(titleItemWidgetDecorations, str);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(TitleItemWidget titleItemWidget, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, TitleItemWidgetDecorations$$serializer.INSTANCE, titleItemWidget.decorations);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, titleItemWidget.title);
    }

    @NotNull
    public final TitleItemWidgetDecorations component1() {
        return this.decorations;
    }

    @NotNull
    public final String component2() {
        return this.title;
    }

    @NotNull
    public final TitleItemWidget copy(@NotNull TitleItemWidgetDecorations decorations, @NotNull String title) {
        Intrinsics.checkNotNullParameter(decorations, "decorations");
        Intrinsics.checkNotNullParameter(title, "title");
        return new TitleItemWidget(decorations, title);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TitleItemWidget)) {
            return false;
        }
        TitleItemWidget titleItemWidget = (TitleItemWidget) obj;
        return Intrinsics.areEqual(this.decorations, titleItemWidget.decorations) && Intrinsics.areEqual(this.title, titleItemWidget.title);
    }

    @NotNull
    public final TitleItemWidgetDecorations getDecorations() {
        return this.decorations;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.title.hashCode() + (this.decorations.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "TitleItemWidget(decorations=" + this.decorations + ", title=" + this.title + ")";
    }

    public TitleItemWidget(@NotNull TitleItemWidgetDecorations decorations, @NotNull String title) {
        Intrinsics.checkNotNullParameter(decorations, "decorations");
        Intrinsics.checkNotNullParameter(title, "title");
        this.decorations = decorations;
        this.title = title;
    }
}
