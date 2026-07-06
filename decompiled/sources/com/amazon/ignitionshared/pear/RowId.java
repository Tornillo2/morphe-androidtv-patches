package com.amazon.ignitionshared.pear;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class RowId {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String rowid;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<RowId> serializer() {
            return RowId$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ RowId(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.rowid = str;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, RowId$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ RowId copy$default(RowId rowId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rowId.rowid;
        }
        return rowId.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.rowid;
    }

    @NotNull
    public final RowId copy(@NotNull String rowid) {
        Intrinsics.checkNotNullParameter(rowid, "rowid");
        return new RowId(rowid);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RowId) && Intrinsics.areEqual(this.rowid, ((RowId) obj).rowid);
    }

    @NotNull
    public final String getRowid() {
        return this.rowid;
    }

    public int hashCode() {
        return this.rowid.hashCode();
    }

    @NotNull
    public String toString() {
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("RowId(rowid=", this.rowid, ")");
    }

    public RowId(@NotNull String rowid) {
        Intrinsics.checkNotNullParameter(rowid, "rowid");
        this.rowid = rowid;
    }
}
