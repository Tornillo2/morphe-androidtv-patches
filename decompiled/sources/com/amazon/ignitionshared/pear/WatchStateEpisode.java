package com.amazon.ignitionshared.pear;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
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
public final class WatchStateEpisode implements WatchState {

    @NotNull
    public static final Companion Companion = new Companion();
    public final int episodeNumber;

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
        public final KSerializer<WatchStateEpisode> serializer() {
            return WatchStateEpisode$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ WatchStateEpisode(int i, Id id, String str, String str2, Integer num, int i2, SerializationConstructorMarker serializationConstructorMarker) {
        if (17 != (i & 17)) {
            PluginExceptionsKt.throwMissingFieldException(i, 17, WatchStateEpisode$$serializer.INSTANCE.getDescriptor());
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
        this.episodeNumber = i2;
    }

    public static /* synthetic */ WatchStateEpisode copy$default(WatchStateEpisode watchStateEpisode, Id id, String str, String str2, Integer num, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            id = watchStateEpisode.focusId;
        }
        if ((i2 & 2) != 0) {
            str = watchStateEpisode.lastEngagementTime;
        }
        if ((i2 & 4) != 0) {
            str2 = watchStateEpisode.watchNextType;
        }
        if ((i2 & 8) != 0) {
            num = watchStateEpisode.totalTimeSeconds;
        }
        if ((i2 & 16) != 0) {
            i = watchStateEpisode.episodeNumber;
        }
        int i3 = i;
        String str3 = str2;
        return watchStateEpisode.copy(id, str, str3, num, i3);
    }

    @JvmStatic
    public static final void write$Self$ignitionshared_release(WatchStateEpisode watchStateEpisode, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Id$$serializer.INSTANCE, watchStateEpisode.focusId);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || watchStateEpisode.lastEngagementTime != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, watchStateEpisode.lastEngagementTime);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || watchStateEpisode.watchNextType != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, watchStateEpisode.watchNextType);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || watchStateEpisode.totalTimeSeconds != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, watchStateEpisode.totalTimeSeconds);
        }
        compositeEncoder.encodeIntElement(serialDescriptor, 4, watchStateEpisode.episodeNumber);
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
        return this.episodeNumber;
    }

    @NotNull
    public final WatchStateEpisode copy(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        return new WatchStateEpisode(focusId, str, str2, num, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WatchStateEpisode)) {
            return false;
        }
        WatchStateEpisode watchStateEpisode = (WatchStateEpisode) obj;
        return Intrinsics.areEqual(this.focusId, watchStateEpisode.focusId) && Intrinsics.areEqual(this.lastEngagementTime, watchStateEpisode.lastEngagementTime) && Intrinsics.areEqual(this.watchNextType, watchStateEpisode.watchNextType) && Intrinsics.areEqual(this.totalTimeSeconds, watchStateEpisode.totalTimeSeconds) && this.episodeNumber == watchStateEpisode.episodeNumber;
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
        return ((iHashCode3 + (num != null ? num.hashCode() : 0)) * 31) + this.episodeNumber;
    }

    @NotNull
    public String toString() {
        Id id = this.focusId;
        String str = this.lastEngagementTime;
        String str2 = this.watchNextType;
        Integer num = this.totalTimeSeconds;
        int i = this.episodeNumber;
        StringBuilder sb = new StringBuilder("WatchStateEpisode(focusId=");
        sb.append(id);
        sb.append(", lastEngagementTime=");
        sb.append(str);
        sb.append(", watchNextType=");
        sb.append(str2);
        sb.append(", totalTimeSeconds=");
        sb.append(num);
        sb.append(", episodeNumber=");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, i, ")");
    }

    public WatchStateEpisode(@NotNull Id focusId, @Nullable String str, @Nullable String str2, @Nullable Integer num, int i) {
        Intrinsics.checkNotNullParameter(focusId, "focusId");
        this.focusId = focusId;
        this.lastEngagementTime = str;
        this.watchNextType = str2;
        this.totalTimeSeconds = num;
        this.episodeNumber = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ WatchStateEpisode(Id id, String str, String str2, Integer num, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        int i3;
        Integer num2;
        str = (i2 & 2) != 0 ? null : str;
        str2 = (i2 & 4) != 0 ? null : str2;
        if ((i2 & 8) != 0) {
            i3 = i;
            num2 = null;
        } else {
            i3 = i;
            num2 = num;
        }
        this(id, str, str2, num2, i3);
    }
}
