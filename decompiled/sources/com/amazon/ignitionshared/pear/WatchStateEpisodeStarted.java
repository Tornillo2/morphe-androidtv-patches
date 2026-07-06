package com.amazon.ignitionshared.pear;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
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
public final class WatchStateEpisodeStarted implements WatchState {

    @NotNull
    public static final Companion Companion = new Companion();
    public final int bookmarkTimeSeconds;
    public final int episodeNumber;

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
        public final KSerializer<WatchStateEpisodeStarted> serializer() {
            return WatchStateEpisodeStarted$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ WatchStateEpisodeStarted(int i, Id id, String str, String str2, Integer num, int i2, String str3, int i3, SerializationConstructorMarker serializationConstructorMarker) {
        if (113 != (i & 113)) {
            PluginExceptionsKt.throwMissingFieldException(i, 113, WatchStateEpisodeStarted$$serializer.INSTANCE.getDescriptor());
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
        this.episodeNumber = i3;
    }

    public static /* synthetic */ WatchStateEpisodeStarted copy$default(WatchStateEpisodeStarted watchStateEpisodeStarted, Id id, String str, String str2, Integer num, int i, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            id = watchStateEpisodeStarted.focusId;
        }
        if ((i3 & 2) != 0) {
            str = watchStateEpisodeStarted.lastEngagementTime;
        }
        if ((i3 & 4) != 0) {
            str2 = watchStateEpisodeStarted.watchNextType;
        }
        if ((i3 & 8) != 0) {
            num = watchStateEpisodeStarted.totalTimeSeconds;
        }
        if ((i3 & 16) != 0) {
            i = watchStateEpisodeStarted.bookmarkTimeSeconds;
        }
        if ((i3 & 32) != 0) {
            str3 = watchStateEpisodeStarted.lastWatchTime;
        }
        if ((i3 & 64) != 0) {
            i2 = watchStateEpisodeStarted.episodeNumber;
        }
        String str4 = str3;
        int i4 = i2;
        int i5 = i;
        String str5 = str2;
        return watchStateEpisodeStarted.copy(id, str, str5, num, i5, str4, i4);
    }

    @JvmStatic
    public static final void write$Self$ignitionshared_release(WatchStateEpisodeStarted watchStateEpisodeStarted, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Id$$serializer.INSTANCE, watchStateEpisodeStarted.focusId);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || watchStateEpisodeStarted.lastEngagementTime != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, watchStateEpisodeStarted.lastEngagementTime);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || watchStateEpisodeStarted.watchNextType != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, watchStateEpisodeStarted.watchNextType);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || watchStateEpisodeStarted.totalTimeSeconds != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, watchStateEpisodeStarted.totalTimeSeconds);
        }
        compositeEncoder.encodeIntElement(serialDescriptor, 4, watchStateEpisodeStarted.bookmarkTimeSeconds);
        compositeEncoder.encodeStringElement(serialDescriptor, 5, watchStateEpisodeStarted.lastWatchTime);
        compositeEncoder.encodeIntElement(serialDescriptor, 6, watchStateEpisodeStarted.episodeNumber);
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

    public final int component7() {
        return this.episodeNumber;
    }

    @NotNull
    public final WatchStateEpisodeStarted copy(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i, @NotNull String lastWatchTime, int i2) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        Intrinsics.checkNotNullParameter(lastWatchTime, "lastWatchTime");
        return new WatchStateEpisodeStarted(focusId, str, str2, num, i, lastWatchTime, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WatchStateEpisodeStarted)) {
            return false;
        }
        WatchStateEpisodeStarted watchStateEpisodeStarted = (WatchStateEpisodeStarted) obj;
        return Intrinsics.areEqual(this.focusId, watchStateEpisodeStarted.focusId) && Intrinsics.areEqual(this.lastEngagementTime, watchStateEpisodeStarted.lastEngagementTime) && Intrinsics.areEqual(this.watchNextType, watchStateEpisodeStarted.watchNextType) && Intrinsics.areEqual(this.totalTimeSeconds, watchStateEpisodeStarted.totalTimeSeconds) && this.bookmarkTimeSeconds == watchStateEpisodeStarted.bookmarkTimeSeconds && Intrinsics.areEqual(this.lastWatchTime, watchStateEpisodeStarted.lastWatchTime) && this.episodeNumber == watchStateEpisodeStarted.episodeNumber;
    }

    public final int getBookmarkTimeSeconds() {
        return this.bookmarkTimeSeconds;
    }

    public final int getEpisodeNumber() {
        return this.episodeNumber;
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
        return DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.lastWatchTime, (((iHashCode3 + (num != null ? num.hashCode() : 0)) * 31) + this.bookmarkTimeSeconds) * 31, 31) + this.episodeNumber;
    }

    @NotNull
    public String toString() {
        Id id = this.focusId;
        String str = this.lastEngagementTime;
        String str2 = this.watchNextType;
        Integer num = this.totalTimeSeconds;
        int i = this.bookmarkTimeSeconds;
        String str3 = this.lastWatchTime;
        int i2 = this.episodeNumber;
        StringBuilder sb = new StringBuilder("WatchStateEpisodeStarted(focusId=");
        sb.append(id);
        sb.append(", lastEngagementTime=");
        sb.append(str);
        sb.append(", watchNextType=");
        sb.append(str2);
        sb.append(", totalTimeSeconds=");
        sb.append(num);
        sb.append(", bookmarkTimeSeconds=");
        sb.append(i);
        sb.append(", lastWatchTime=");
        sb.append(str3);
        sb.append(", episodeNumber=");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, i2, ")");
    }

    public WatchStateEpisodeStarted(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i, @NotNull String lastWatchTime, int i2) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        Intrinsics.checkNotNullParameter(lastWatchTime, "lastWatchTime");
        this.focusId = focusId;
        this.lastEngagementTime = str;
        this.watchNextType = str2;
        this.totalTimeSeconds = num;
        this.bookmarkTimeSeconds = i;
        this.lastWatchTime = lastWatchTime;
        this.episodeNumber = i2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ WatchStateEpisodeStarted(Id id, String str, String str2, Integer num, int i, String str3, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        int i4;
        String str4;
        int i5;
        Integer num2;
        str = (i3 & 2) != 0 ? null : str;
        str2 = (i3 & 4) != 0 ? null : str2;
        if ((i3 & 8) != 0) {
            i4 = i2;
            str4 = str3;
            i5 = i;
            num2 = null;
        } else {
            i4 = i2;
            str4 = str3;
            i5 = i;
            num2 = num;
        }
        this(id, str, str2, num2, i5, str4, i4);
    }
}
