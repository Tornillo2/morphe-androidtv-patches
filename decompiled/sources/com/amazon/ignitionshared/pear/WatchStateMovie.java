package com.amazon.ignitionshared.pear;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class WatchStateMovie implements WatchState {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final Id focusId;

    @Nullable
    public final String lastEngagementTime;

    @Nullable
    public final Integer totalTimeSeconds;

    @Nullable
    public final String watchNextType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<WatchStateMovie> serializer() {
            return WatchStateMovie$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ WatchStateMovie(int i, Id id, String str, String str2, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, WatchStateMovie$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.focusId = id;
        if ((i & 2) == 0) {
            this.lastEngagementTime = null;
        } else {
            this.lastEngagementTime = str;
        }
        if ((i & 4) == 0) {
            this.watchNextType = null;
        } else {
            this.watchNextType = str2;
        }
        if ((i & 8) == 0) {
            this.totalTimeSeconds = null;
        } else {
            this.totalTimeSeconds = num;
        }
    }

    public static /* synthetic */ WatchStateMovie copy$default(WatchStateMovie watchStateMovie, Id id, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            id = watchStateMovie.focusId;
        }
        if ((i & 2) != 0) {
            str = watchStateMovie.lastEngagementTime;
        }
        if ((i & 4) != 0) {
            str2 = watchStateMovie.watchNextType;
        }
        if ((i & 8) != 0) {
            num = watchStateMovie.totalTimeSeconds;
        }
        return watchStateMovie.copy(id, str, str2, num);
    }

    @JvmStatic
    public static final void write$Self$ignitionshared_release(WatchStateMovie watchStateMovie, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Id$$serializer.INSTANCE, watchStateMovie.focusId);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || watchStateMovie.lastEngagementTime != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, watchStateMovie.lastEngagementTime);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || watchStateMovie.watchNextType != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, watchStateMovie.watchNextType);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) && watchStateMovie.totalTimeSeconds == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, watchStateMovie.totalTimeSeconds);
    }

    @NotNull
    public final Id component1() {
        return this.focusId;
    }

    @Nullable
    public final String component2() {
        return this.lastEngagementTime;
    }

    @Nullable
    public final String component3() {
        return this.watchNextType;
    }

    @Nullable
    public final Integer component4() {
        return this.totalTimeSeconds;
    }

    @NotNull
    public final WatchStateMovie copy(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        return new WatchStateMovie(focusId, str, str2, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WatchStateMovie)) {
            return false;
        }
        WatchStateMovie watchStateMovie = (WatchStateMovie) obj;
        return Intrinsics.areEqual(this.focusId, watchStateMovie.focusId) && Intrinsics.areEqual(this.lastEngagementTime, watchStateMovie.lastEngagementTime) && Intrinsics.areEqual(this.watchNextType, watchStateMovie.watchNextType) && Intrinsics.areEqual(this.totalTimeSeconds, watchStateMovie.totalTimeSeconds);
    }

    @Override // com.amazon.ignitionshared.pear.WatchState
    @NotNull
    public Id getFocusId() {
        return this.focusId;
    }

    @Override // com.amazon.ignitionshared.pear.WatchState
    @Nullable
    public String getLastEngagementTime() {
        return this.lastEngagementTime;
    }

    @Override // com.amazon.ignitionshared.pear.WatchState
    @Nullable
    public Integer getTotalTimeSeconds() {
        return this.totalTimeSeconds;
    }

    @Override // com.amazon.ignitionshared.pear.WatchState
    @Nullable
    public String getWatchNextType() {
        return this.watchNextType;
    }

    public int hashCode() {
        int iHashCode = this.focusId.hashCode() * 31;
        String str = this.lastEngagementTime;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.watchNextType;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.totalTimeSeconds;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "WatchStateMovie(focusId=" + this.focusId + ", lastEngagementTime=" + this.lastEngagementTime + ", watchNextType=" + this.watchNextType + ", totalTimeSeconds=" + this.totalTimeSeconds + ")";
    }

    public WatchStateMovie(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        this.focusId = focusId;
        this.lastEngagementTime = str;
        this.watchNextType = str2;
        this.totalTimeSeconds = num;
    }

    public /* synthetic */ WatchStateMovie(Id id, String str, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(id, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num);
    }
}
