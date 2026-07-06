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
public final class Refresh {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String refreshRate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<Refresh> serializer() {
            return Refresh$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ Refresh(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.refreshRate = str;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, Refresh$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ Refresh copy$default(Refresh refresh, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = refresh.refreshRate;
        }
        return refresh.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.refreshRate;
    }

    @NotNull
    public final Refresh copy(@NotNull String refreshRate) {
        Intrinsics.checkNotNullParameter(refreshRate, "refreshRate");
        return new Refresh(refreshRate);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Refresh) && Intrinsics.areEqual(this.refreshRate, ((Refresh) obj).refreshRate);
    }

    @NotNull
    public final String getRefreshRate() {
        return this.refreshRate;
    }

    public int hashCode() {
        return this.refreshRate.hashCode();
    }

    @NotNull
    public String toString() {
        return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Refresh(refreshRate=", this.refreshRate, ")");
    }

    public Refresh(@NotNull String refreshRate) {
        Intrinsics.checkNotNullParameter(refreshRate, "refreshRate");
        this.refreshRate = refreshRate;
    }
}
