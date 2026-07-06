package androidx.media3.common;

import android.os.Bundle;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DeviceInfo implements Bundleable {
    public static final int PLAYBACK_TYPE_LOCAL = 0;
    public static final int PLAYBACK_TYPE_REMOTE = 1;

    @IntRange(from = 0)
    public final int maxVolume;

    @IntRange(from = 0)
    public final int minVolume;
    public final int playbackType;

    @Nullable
    public final String routingControllerId;
    public static final DeviceInfo UNKNOWN = new Builder(0).build();
    public static final String FIELD_PLAYBACK_TYPE = Util.intToStringMaxRadix(0);
    public static final String FIELD_MIN_VOLUME = Integer.toString(1, 36);
    public static final String FIELD_MAX_VOLUME = Integer.toString(2, 36);
    public static final String FIELD_ROUTING_CONTROLLER_ID = Integer.toString(3, 36);

    @UnstableApi
    @Deprecated
    public static final Bundleable.Creator<DeviceInfo> CREATOR = new DeviceInfo$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public int maxVolume;
        public int minVolume;
        public final int playbackType;

        @Nullable
        public String routingControllerId;

        public Builder(int i) {
            this.playbackType = i;
        }

        public DeviceInfo build() {
            Assertions.checkArgument(this.minVolume <= this.maxVolume);
            return new DeviceInfo(this);
        }

        @CanIgnoreReturnValue
        public Builder setMaxVolume(@IntRange(from = 0) int i) {
            this.maxVolume = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinVolume(@IntRange(from = 0) int i) {
            this.minVolume = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setRoutingControllerId(@Nullable String str) {
            Assertions.checkArgument(this.playbackType != 0 || str == null);
            this.routingControllerId = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaybackType {
    }

    @UnstableApi
    public static DeviceInfo fromBundle(Bundle bundle) {
        int i = bundle.getInt(FIELD_PLAYBACK_TYPE, 0);
        int i2 = bundle.getInt(FIELD_MIN_VOLUME, 0);
        int i3 = bundle.getInt(FIELD_MAX_VOLUME, 0);
        String string = bundle.getString(FIELD_ROUTING_CONTROLLER_ID);
        Builder builder = new Builder(i);
        builder.minVolume = i2;
        builder.maxVolume = i3;
        builder.setRoutingControllerId(string);
        return builder.build();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.playbackType == deviceInfo.playbackType && this.minVolume == deviceInfo.minVolume && this.maxVolume == deviceInfo.maxVolume && Util.areEqual(this.routingControllerId, deviceInfo.routingControllerId);
    }

    public int hashCode() {
        int i = (((((527 + this.playbackType) * 31) + this.minVolume) * 31) + this.maxVolume) * 31;
        String str = this.routingControllerId;
        return i + (str == null ? 0 : str.hashCode());
    }

    @Override // androidx.media3.common.Bundleable
    @UnstableApi
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i = this.playbackType;
        if (i != 0) {
            bundle.putInt(FIELD_PLAYBACK_TYPE, i);
        }
        int i2 = this.minVolume;
        if (i2 != 0) {
            bundle.putInt(FIELD_MIN_VOLUME, i2);
        }
        int i3 = this.maxVolume;
        if (i3 != 0) {
            bundle.putInt(FIELD_MAX_VOLUME, i3);
        }
        String str = this.routingControllerId;
        if (str != null) {
            bundle.putString(FIELD_ROUTING_CONTROLLER_ID, str);
        }
        return bundle;
    }

    @UnstableApi
    @Deprecated
    public DeviceInfo(int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        Builder builder = new Builder(i);
        builder.minVolume = i2;
        builder.maxVolume = i3;
        this(builder);
    }

    public DeviceInfo(Builder builder) {
        this.playbackType = builder.playbackType;
        this.minVolume = builder.minVolume;
        this.maxVolume = builder.maxVolume;
        this.routingControllerId = builder.routingControllerId;
    }
}
