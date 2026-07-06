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
public final class WatchStateMovieStarted implements WatchState {

    @NotNull
    public static final Companion Companion = new Companion();
    public final int bookmarkTimeSeconds;

    @NotNull
    public final Id focusId;

    @Nullable
    public final String lastEngagementTime;

    @NotNull
    public final String lastWatchTime;

    @Nullable
    public final Integer totalTimeSeconds;

    @Nullable
    public final String watchNextType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<WatchStateMovieStarted> serializer() {
            return WatchStateMovieStarted$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ WatchStateMovieStarted(int i, Id id, String str, String str2, Integer num, int i2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (49 != (i & 49)) {
            PluginExceptionsKt.throwMissingFieldException(i, 49, WatchStateMovieStarted$$serializer.INSTANCE.getDescriptor());
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
        this.bookmarkTimeSeconds = i2;
        this.lastWatchTime = str3;
    }

    public static /* synthetic */ WatchStateMovieStarted copy$default(WatchStateMovieStarted watchStateMovieStarted, Id id, String str, String str2, Integer num, int i, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            id = watchStateMovieStarted.focusId;
        }
        if ((i2 & 2) != 0) {
            str = watchStateMovieStarted.lastEngagementTime;
        }
        if ((i2 & 4) != 0) {
            str2 = watchStateMovieStarted.watchNextType;
        }
        if ((i2 & 8) != 0) {
            num = watchStateMovieStarted.totalTimeSeconds;
        }
        if ((i2 & 16) != 0) {
            i = watchStateMovieStarted.bookmarkTimeSeconds;
        }
        if ((i2 & 32) != 0) {
            str3 = watchStateMovieStarted.lastWatchTime;
        }
        int i3 = i;
        String str4 = str3;
        return watchStateMovieStarted.copy(id, str, str2, num, i3, str4);
    }

    @JvmStatic
    public static final void write$Self$ignitionshared_release(WatchStateMovieStarted watchStateMovieStarted, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Id$$serializer.INSTANCE, watchStateMovieStarted.focusId);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || watchStateMovieStarted.lastEngagementTime != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, watchStateMovieStarted.lastEngagementTime);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || watchStateMovieStarted.watchNextType != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, watchStateMovieStarted.watchNextType);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || watchStateMovieStarted.totalTimeSeconds != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, watchStateMovieStarted.totalTimeSeconds);
        }
        compositeEncoder.encodeIntElement(serialDescriptor, 4, watchStateMovieStarted.bookmarkTimeSeconds);
        compositeEncoder.encodeStringElement(serialDescriptor, 5, watchStateMovieStarted.lastWatchTime);
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

    public final int component5() {
        return this.bookmarkTimeSeconds;
    }

    @NotNull
    public final String component6() {
        return this.lastWatchTime;
    }

    @NotNull
    public final WatchStateMovieStarted copy(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i, @NotNull String lastWatchTime) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        Intrinsics.checkNotNullParameter(lastWatchTime, "lastWatchTime");
        return new WatchStateMovieStarted(focusId, str, str2, num, i, lastWatchTime);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WatchStateMovieStarted)) {
            return false;
        }
        WatchStateMovieStarted watchStateMovieStarted = (WatchStateMovieStarted) obj;
        return Intrinsics.areEqual(this.focusId, watchStateMovieStarted.focusId) && Intrinsics.areEqual(this.lastEngagementTime, watchStateMovieStarted.lastEngagementTime) && Intrinsics.areEqual(this.watchNextType, watchStateMovieStarted.watchNextType) && Intrinsics.areEqual(this.totalTimeSeconds, watchStateMovieStarted.totalTimeSeconds) && this.bookmarkTimeSeconds == watchStateMovieStarted.bookmarkTimeSeconds && Intrinsics.areEqual(this.lastWatchTime, watchStateMovieStarted.lastWatchTime);
    }

    public final int getBookmarkTimeSeconds() {
        return this.bookmarkTimeSeconds;
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

    @NotNull
    public final String getLastWatchTime() {
        return this.lastWatchTime;
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
        return this.lastWatchTime.hashCode() + ((((iHashCode3 + (num != null ? num.hashCode() : 0)) * 31) + this.bookmarkTimeSeconds) * 31);
    }

    @NotNull
    public String toString() {
        return "WatchStateMovieStarted(focusId=" + this.focusId + ", lastEngagementTime=" + this.lastEngagementTime + ", watchNextType=" + this.watchNextType + ", totalTimeSeconds=" + this.totalTimeSeconds + ", bookmarkTimeSeconds=" + this.bookmarkTimeSeconds + ", lastWatchTime=" + this.lastWatchTime + ")";
    }

    public WatchStateMovieStarted(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i, @NotNull String lastWatchTime) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        Intrinsics.checkNotNullParameter(lastWatchTime, "lastWatchTime");
        this.focusId = focusId;
        this.lastEngagementTime = str;
        this.watchNextType = str2;
        this.totalTimeSeconds = num;
        this.bookmarkTimeSeconds = i;
        this.lastWatchTime = lastWatchTime;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ WatchStateMovieStarted(Id id, String str, String str2, Integer num, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        int i3;
        Integer num2;
        str = (i2 & 2) != 0 ? null : str;
        str2 = (i2 & 4) != 0 ? null : str2;
        if ((i2 & 8) != 0) {
            str4 = str3;
            i3 = i;
            num2 = null;
        } else {
            str4 = str3;
            i3 = i;
            num2 = num;
        }
        this(id, str, str2, num2, i3, str4);
    }
}
