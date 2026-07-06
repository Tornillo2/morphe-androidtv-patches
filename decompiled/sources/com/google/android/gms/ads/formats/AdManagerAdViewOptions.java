package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzfj;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbgh;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SafeParcelable.Class(creator = "AdManagerAdViewOptionsCreator")
public final class AdManagerAdViewOptions extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<AdManagerAdViewOptions> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getManualImpressionsEnabled", id = 1)
    public final boolean zza;

    @Nullable
    @SafeParcelable.Field(getter = "getDelayedBannerAdListenerBinder", id = 2)
    public final IBinder zzb;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean zza = false;

        @Nullable
        public ShouldDelayBannerRenderingListener zzb;

        @NonNull
        public AdManagerAdViewOptions build() {
            return new AdManagerAdViewOptions(this, (zzb) null);
        }

        @NonNull
        public Builder setManualImpressionsEnabled(boolean z) {
            this.zza = z;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setShouldDelayBannerRenderingListener(@NonNull ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener) {
            this.zzb = shouldDelayBannerRenderingListener;
            return this;
        }
    }

    public AdManagerAdViewOptions(Builder builder, zzb zzbVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb != null ? new zzfj(builder.zzb) : null;
    }

    public boolean getManualImpressionsEnabled() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        boolean z = this.zza;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzb, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    @Nullable
    public final zzbgh zza() {
        IBinder iBinder = this.zzb;
        if (iBinder == null) {
            return null;
        }
        return zzbgg.zzc(iBinder);
    }

    @SafeParcelable.Constructor
    public AdManagerAdViewOptions(@SafeParcelable.Param(id = 1) boolean z, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.zza = z;
        this.zzb = iBinder;
    }
}
