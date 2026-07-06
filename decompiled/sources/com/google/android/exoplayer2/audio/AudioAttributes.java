package com.google.android.exoplayer2.audio;

import android.media.AudioAttributes;
import android.os.Bundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AudioAttributes implements Bundleable {
    public final int allowedCapturePolicy;

    @Nullable
    public AudioAttributesV21 audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final int spatializationBehavior;
    public final int usage;
    public static final AudioAttributes DEFAULT = new Builder().build();
    public static final String FIELD_CONTENT_TYPE = Util.intToStringMaxRadix(0);
    public static final String FIELD_FLAGS = Integer.toString(1, 36);
    public static final String FIELD_USAGE = Integer.toString(2, 36);
    public static final String FIELD_ALLOWED_CAPTURE_POLICY = Integer.toString(3, 36);
    public static final String FIELD_SPATIALIZATION_BEHAVIOR = Integer.toString(4, 36);
    public static final Bundleable.Creator<AudioAttributes> CREATOR = new AudioAttributes$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static final class Api29 {
        @DoNotInline
        public static void setAllowedCapturePolicy(AudioAttributes.Builder builder, int i) {
            builder.setAllowedCapturePolicy(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(32)
    public static final class Api32 {
        @DoNotInline
        public static void setSpatializationBehavior(AudioAttributes.Builder builder, int i) {
            builder.setSpatializationBehavior(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static final class AudioAttributesV21 {
        public final android.media.AudioAttributes audioAttributes;

        public AudioAttributesV21(AudioAttributes audioAttributes) {
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(audioAttributes.contentType).setFlags(audioAttributes.flags).setUsage(audioAttributes.usage);
            int i = Util.SDK_INT;
            if (i >= 29) {
                Api29.setAllowedCapturePolicy(usage, audioAttributes.allowedCapturePolicy);
            }
            if (i >= 32) {
                Api32.setSpatializationBehavior(usage, audioAttributes.spatializationBehavior);
            }
            this.audioAttributes = usage.build();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public int contentType = 0;
        public int flags = 0;
        public int usage = 1;
        public int allowedCapturePolicy = 1;
        public int spatializationBehavior = 0;

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, this.allowedCapturePolicy, this.spatializationBehavior);
        }

        @CanIgnoreReturnValue
        public Builder setAllowedCapturePolicy(int i) {
            this.allowedCapturePolicy = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setContentType(int i) {
            this.contentType = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setFlags(int i) {
            this.flags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSpatializationBehavior(int i) {
            this.spatializationBehavior = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUsage(int i) {
            this.usage = i;
            return this;
        }
    }

    public static AudioAttributes $r8$lambda$aAjiqZCTnUjuflPZ0ItfIiXUZ6w(Bundle bundle) {
        Builder builder = new Builder();
        String str = FIELD_CONTENT_TYPE;
        if (bundle.containsKey(str)) {
            builder.contentType = bundle.getInt(str);
        }
        String str2 = FIELD_FLAGS;
        if (bundle.containsKey(str2)) {
            builder.flags = bundle.getInt(str2);
        }
        String str3 = FIELD_USAGE;
        if (bundle.containsKey(str3)) {
            builder.usage = bundle.getInt(str3);
        }
        String str4 = FIELD_ALLOWED_CAPTURE_POLICY;
        if (bundle.containsKey(str4)) {
            builder.allowedCapturePolicy = bundle.getInt(str4);
        }
        String str5 = FIELD_SPATIALIZATION_BEHAVIOR;
        if (bundle.containsKey(str5)) {
            builder.spatializationBehavior = bundle.getInt(str5);
        }
        return builder.build();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AudioAttributes.class == obj.getClass()) {
            AudioAttributes audioAttributes = (AudioAttributes) obj;
            if (this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage && this.allowedCapturePolicy == audioAttributes.allowedCapturePolicy && this.spatializationBehavior == audioAttributes.spatializationBehavior) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(21)
    public AudioAttributesV21 getAudioAttributesV21() {
        if (this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new AudioAttributesV21(this);
        }
        return this.audioAttributesV21;
    }

    public int hashCode() {
        return ((((((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage) * 31) + this.allowedCapturePolicy) * 31) + this.spatializationBehavior;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_CONTENT_TYPE, this.contentType);
        bundle.putInt(FIELD_FLAGS, this.flags);
        bundle.putInt(FIELD_USAGE, this.usage);
        bundle.putInt(FIELD_ALLOWED_CAPTURE_POLICY, this.allowedCapturePolicy);
        bundle.putInt(FIELD_SPATIALIZATION_BEHAVIOR, this.spatializationBehavior);
        return bundle;
    }

    public AudioAttributes(int i, int i2, int i3, int i4, int i5) {
        this.contentType = i;
        this.flags = i2;
        this.usage = i3;
        this.allowedCapturePolicy = i4;
        this.spatializationBehavior = i5;
    }
}
