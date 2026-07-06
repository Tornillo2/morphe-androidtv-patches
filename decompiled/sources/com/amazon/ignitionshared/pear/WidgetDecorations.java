package com.amazon.ignitionshared.pear;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class WidgetDecorations {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final RowId uiRowMapping;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<WidgetDecorations> serializer() {
            return WidgetDecorations$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ WidgetDecorations(int i, RowId rowId, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.uiRowMapping = rowId;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, WidgetDecorations$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ WidgetDecorations copy$default(WidgetDecorations widgetDecorations, RowId rowId, int i, Object obj) {
        if ((i & 1) != 0) {
            rowId = widgetDecorations.uiRowMapping;
        }
        return widgetDecorations.copy(rowId);
    }

    @NotNull
    public final RowId component1() {
        return this.uiRowMapping;
    }

    @NotNull
    public final WidgetDecorations copy(@NotNull RowId uiRowMapping) {
        Intrinsics.checkNotNullParameter(uiRowMapping, "uiRowMapping");
        return new WidgetDecorations(uiRowMapping);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WidgetDecorations) && Intrinsics.areEqual(this.uiRowMapping, ((WidgetDecorations) obj).uiRowMapping);
    }

    @NotNull
    public final RowId getUiRowMapping() {
        return this.uiRowMapping;
    }

    public int hashCode() {
        return this.uiRowMapping.rowid.hashCode();
    }

    @NotNull
    public String toString() {
        return "WidgetDecorations(uiRowMapping=" + this.uiRowMapping + ")";
    }

    public WidgetDecorations(@NotNull RowId uiRowMapping) {
        Intrinsics.checkNotNullParameter(uiRowMapping, "uiRowMapping");
        this.uiRowMapping = uiRowMapping;
    }

    @SerialName("ui_row_mapping")
    public static /* synthetic */ void getUiRowMapping$annotations() {
    }
}
