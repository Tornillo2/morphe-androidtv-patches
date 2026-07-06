package com.amazon.avod.mpb.api.core;

import java.util.Iterator;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerialName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PictureMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ PictureMode[] $VALUES;

    @NotNull
    public static final Companion Companion;

    @NotNull
    public final String propertyName;

    @SerialName("none")
    public static final PictureMode NONE = new PictureMode("NONE", 0, "none");

    @SerialName("filmmaker")
    public static final PictureMode FILM_MAKER = new PictureMode("FILM_MAKER", 1, "filmmaker");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nMediaPipelineBackendCapabilities.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaPipelineBackendCapabilities.kt\ncom/amazon/avod/mpb/api/core/PictureMode$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1#2:87\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @Nullable
        public final PictureMode fromPropertyName(@Nullable String str) {
            PictureMode next;
            Iterator<PictureMode> it = PictureMode.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(next.propertyName, str)) {
                    break;
                }
            }
            return next;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final /* synthetic */ PictureMode[] $values() {
        return new PictureMode[]{NONE, FILM_MAKER};
    }

    static {
        PictureMode[] pictureModeArr$values = $values();
        $VALUES = pictureModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(pictureModeArr$values);
        Companion = new Companion();
    }

    public PictureMode(String str, int i, String str2) {
        this.propertyName = str2;
    }

    @NotNull
    public static EnumEntries<PictureMode> getEntries() {
        return $ENTRIES;
    }

    public static PictureMode valueOf(String str) {
        return (PictureMode) Enum.valueOf(PictureMode.class, str);
    }

    public static PictureMode[] values() {
        return (PictureMode[]) $VALUES.clone();
    }

    @NotNull
    public final String getPropertyName() {
        return this.propertyName;
    }
}
