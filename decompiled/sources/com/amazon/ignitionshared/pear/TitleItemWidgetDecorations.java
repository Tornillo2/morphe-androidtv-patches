package com.amazon.ignitionshared.pear;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class TitleItemWidgetDecorations {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final Catalog catalog;

    @Nullable
    public final String catalogType;

    @NotNull
    public final Deeplink deeplink;

    @NotNull
    public final String description;

    @Nullable
    public final Boolean entitlement;

    @NotNull
    public final Visuals visuals;

    @Nullable
    public final WatchState watchState;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<TitleItemWidgetDecorations> serializer() {
            return TitleItemWidgetDecorations$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ TitleItemWidgetDecorations(int i, Deeplink deeplink, Visuals visuals, String str, WatchState watchState, Boolean bool, String str2, Catalog catalog, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, TitleItemWidgetDecorations$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.deeplink = deeplink;
        this.visuals = visuals;
        this.description = str;
        if ((i & 8) == 0) {
            this.watchState = null;
        } else {
            this.watchState = watchState;
        }
        if ((i & 16) == 0) {
            this.entitlement = null;
        } else {
            this.entitlement = bool;
        }
        if ((i & 32) == 0) {
            this.catalogType = null;
        } else {
            this.catalogType = str2;
        }
        if ((i & 64) == 0) {
            this.catalog = null;
        } else {
            this.catalog = catalog;
        }
    }

    public static /* synthetic */ TitleItemWidgetDecorations copy$default(TitleItemWidgetDecorations titleItemWidgetDecorations, Deeplink deeplink, Visuals visuals, String str, WatchState watchState, Boolean bool, String str2, Catalog catalog, int i, Object obj) {
        if ((i & 1) != 0) {
            deeplink = titleItemWidgetDecorations.deeplink;
        }
        if ((i & 2) != 0) {
            visuals = titleItemWidgetDecorations.visuals;
        }
        if ((i & 4) != 0) {
            str = titleItemWidgetDecorations.description;
        }
        if ((i & 8) != 0) {
            watchState = titleItemWidgetDecorations.watchState;
        }
        if ((i & 16) != 0) {
            bool = titleItemWidgetDecorations.entitlement;
        }
        if ((i & 32) != 0) {
            str2 = titleItemWidgetDecorations.catalogType;
        }
        if ((i & 64) != 0) {
            catalog = titleItemWidgetDecorations.catalog;
        }
        String str3 = str2;
        Catalog catalog2 = catalog;
        Boolean bool2 = bool;
        String str4 = str;
        return titleItemWidgetDecorations.copy(deeplink, visuals, str4, watchState, bool2, str3, catalog2);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(TitleItemWidgetDecorations titleItemWidgetDecorations, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Deeplink$$serializer.INSTANCE, titleItemWidgetDecorations.deeplink);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Visuals$$serializer.INSTANCE, titleItemWidgetDecorations.visuals);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, titleItemWidgetDecorations.description);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || titleItemWidgetDecorations.watchState != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, WatchStateSerializer.INSTANCE, titleItemWidgetDecorations.watchState);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || titleItemWidgetDecorations.entitlement != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, BooleanSerializer.INSTANCE, titleItemWidgetDecorations.entitlement);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || titleItemWidgetDecorations.catalogType != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, titleItemWidgetDecorations.catalogType);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) && titleItemWidgetDecorations.catalog == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, Catalog$$serializer.INSTANCE, titleItemWidgetDecorations.catalog);
    }

    @NotNull
    public final Deeplink component1() {
        return this.deeplink;
    }

    @NotNull
    public final Visuals component2() {
        return this.visuals;
    }

    @NotNull
    public final String component3() {
        return this.description;
    }

    @Nullable
    public final WatchState component4() {
        return this.watchState;
    }

    @Nullable
    public final Boolean component5() {
        return this.entitlement;
    }

    @Nullable
    public final String component6() {
        return this.catalogType;
    }

    @Nullable
    public final Catalog component7() {
        return this.catalog;
    }

    @NotNull
    public final TitleItemWidgetDecorations copy(@NotNull Deeplink deeplink, @NotNull Visuals visuals, @NotNull String description, @Nullable WatchState watchState, @Nullable Boolean bool, @Nullable String str, @Nullable Catalog catalog) {
        Intrinsics.checkNotNullParameter(deeplink, "deeplink");
        Intrinsics.checkNotNullParameter(visuals, "visuals");
        Intrinsics.checkNotNullParameter(description, "description");
        return new TitleItemWidgetDecorations(deeplink, visuals, description, watchState, bool, str, catalog);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TitleItemWidgetDecorations)) {
            return false;
        }
        TitleItemWidgetDecorations titleItemWidgetDecorations = (TitleItemWidgetDecorations) obj;
        return Intrinsics.areEqual(this.deeplink, titleItemWidgetDecorations.deeplink) && Intrinsics.areEqual(this.visuals, titleItemWidgetDecorations.visuals) && Intrinsics.areEqual(this.description, titleItemWidgetDecorations.description) && Intrinsics.areEqual(this.watchState, titleItemWidgetDecorations.watchState) && Intrinsics.areEqual(this.entitlement, titleItemWidgetDecorations.entitlement) && Intrinsics.areEqual(this.catalogType, titleItemWidgetDecorations.catalogType) && Intrinsics.areEqual(this.catalog, titleItemWidgetDecorations.catalog);
    }

    @Nullable
    public final Catalog getCatalog() {
        return this.catalog;
    }

    @Nullable
    public final String getCatalogType() {
        return this.catalogType;
    }

    @NotNull
    public final Deeplink getDeeplink() {
        return this.deeplink;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final Boolean getEntitlement() {
        return this.entitlement;
    }

    @NotNull
    public final Visuals getVisuals() {
        return this.visuals;
    }

    @Nullable
    public final WatchState getWatchState() {
        return this.watchState;
    }

    public int hashCode() {
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.description, (this.visuals.items.hashCode() + (this.deeplink.hashCode() * 31)) * 31, 31);
        WatchState watchState = this.watchState;
        int iHashCode = (iM + (watchState == null ? 0 : watchState.hashCode())) * 31;
        Boolean bool = this.entitlement;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.catalogType;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Catalog catalog = this.catalog;
        return iHashCode3 + (catalog != null ? catalog.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "TitleItemWidgetDecorations(deeplink=" + this.deeplink + ", visuals=" + this.visuals + ", description=" + this.description + ", watchState=" + this.watchState + ", entitlement=" + this.entitlement + ", catalogType=" + this.catalogType + ", catalog=" + this.catalog + ")";
    }

    public TitleItemWidgetDecorations(@NotNull Deeplink deeplink, @NotNull Visuals visuals, @NotNull String description, @Nullable WatchState watchState, @Nullable Boolean bool, @Nullable String str, @Nullable Catalog catalog) {
        Intrinsics.checkNotNullParameter(deeplink, "deeplink");
        Intrinsics.checkNotNullParameter(visuals, "visuals");
        Intrinsics.checkNotNullParameter(description, "description");
        this.deeplink = deeplink;
        this.visuals = visuals;
        this.description = description;
        this.watchState = watchState;
        this.entitlement = bool;
        this.catalogType = str;
        this.catalog = catalog;
    }

    public /* synthetic */ TitleItemWidgetDecorations(Deeplink deeplink, Visuals visuals, String str, WatchState watchState, Boolean bool, String str2, Catalog catalog, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deeplink, visuals, str, (i & 8) != 0 ? null : watchState, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : catalog);
    }

    @SerialName("catalog_type")
    public static /* synthetic */ void getCatalogType$annotations() {
    }

    @SerialName("deep_link")
    public static /* synthetic */ void getDeeplink$annotations() {
    }

    @SerialName("watch_state")
    public static /* synthetic */ void getWatchState$annotations() {
    }
}
