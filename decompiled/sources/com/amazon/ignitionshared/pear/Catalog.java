package com.amazon.ignitionshared.pear;

import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class Catalog {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public final Integer seasonNumber;

    @Nullable
    public final String title;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Catalog> serializer() {
            return Catalog$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Catalog() {
        this((Integer) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public static Catalog copy$default(Catalog catalog, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = catalog.seasonNumber;
        }
        if ((i & 2) != 0) {
            str = catalog.title;
        }
        catalog.getClass();
        return new Catalog(num, str);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(Catalog catalog, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || catalog.seasonNumber != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, catalog.seasonNumber);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) && catalog.title == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, catalog.title);
    }

    @Nullable
    public final Integer component1() {
        return this.seasonNumber;
    }

    @Nullable
    public final String component2() {
        return this.title;
    }

    @NotNull
    public final Catalog copy(@Nullable Integer num, @Nullable String str) {
        return new Catalog(num, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Catalog)) {
            return false;
        }
        Catalog catalog = (Catalog) obj;
        return Intrinsics.areEqual(this.seasonNumber, catalog.seasonNumber) && Intrinsics.areEqual(this.title, catalog.title);
    }

    @Nullable
    public final Integer getSeasonNumber() {
        return this.seasonNumber;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        Integer num = this.seasonNumber;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.title;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Catalog(seasonNumber=" + this.seasonNumber + ", title=" + this.title + ")";
    }

    public /* synthetic */ Catalog(int i, Integer num, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.seasonNumber = null;
        } else {
            this.seasonNumber = num;
        }
        if ((i & 2) == 0) {
            this.title = null;
        } else {
            this.title = str;
        }
    }

    public Catalog(@Nullable Integer num, @Nullable String str) {
        this.seasonNumber = num;
        this.title = str;
    }

    public /* synthetic */ Catalog(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str);
    }

    @SerialName(TvContractCompat.Programs.COLUMN_SEASON_NUMBER)
    public static /* synthetic */ void getSeasonNumber$annotations() {
    }
}
