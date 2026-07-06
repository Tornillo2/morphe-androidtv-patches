package com.google.android.gms.ads.formats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_ANY = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE = 2;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_PORTRAIT = 3;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_SQUARE = 4;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_UNKNOWN = 0;

    @Deprecated
    public static final int ORIENTATION_ANY = 0;

    @Deprecated
    public static final int ORIENTATION_LANDSCAPE = 2;

    @Deprecated
    public static final int ORIENTATION_PORTRAIT = 1;
    public final boolean zza;
    public final int zzb;
    public final int zzc;
    public final boolean zzd;
    public final int zze;
    public final VideoOptions zzf;
    public final boolean zzg;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public @interface AdChoicesPlacement {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public VideoOptions zze;
        public boolean zza = false;
        public int zzb = -1;
        public int zzc = 0;
        public boolean zzd = false;
        public int zzf = 1;
        public boolean zzg = false;

        @NonNull
        public NativeAdOptions build() {
            return new NativeAdOptions(this, null);
        }

        @NonNull
        public Builder setAdChoicesPlacement(@AdChoicesPlacement int i) {
            this.zzf = i;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setImageOrientation(int i) {
            this.zzb = i;
            return this;
        }

        @NonNull
        public Builder setMediaAspectRatio(@NativeMediaAspectRatio int i) {
            this.zzc = i;
            return this;
        }

        @NonNull
        public Builder setRequestCustomMuteThisAd(boolean z) {
            this.zzg = z;
            return this;
        }

        @NonNull
        public Builder setRequestMultipleImages(boolean z) {
            this.zzd = z;
            return this;
        }

        @NonNull
        public Builder setReturnUrlsForImageAssets(boolean z) {
            this.zza = z;
            return this;
        }

        @NonNull
        public Builder setVideoOptions(@NonNull VideoOptions videoOptions) {
            this.zze = videoOptions;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public @interface NativeMediaAspectRatio {
    }

    public /* synthetic */ NativeAdOptions(Builder builder, zzd zzdVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = builder.zzc;
        this.zzd = builder.zzd;
        this.zze = builder.zzf;
        this.zzf = builder.zze;
        this.zzg = builder.zzg;
    }

    public int getAdChoicesPlacement() {
        return this.zze;
    }

    @Deprecated
    public int getImageOrientation() {
        return this.zzb;
    }

    public int getMediaAspectRatio() {
        return this.zzc;
    }

    @Nullable
    public VideoOptions getVideoOptions() {
        return this.zzf;
    }

    public boolean shouldRequestMultipleImages() {
        return this.zzd;
    }

    public boolean shouldReturnUrlsForImageAssets() {
        return this.zza;
    }

    public final boolean zza() {
        return this.zzg;
    }
}
