package com.google.android.gms.ads.search;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class SearchAdRequest {
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;

    @NonNull
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;

    @NotOnlyInitialized
    public final zzdx zza;
    public final String zzb;

    public /* synthetic */ SearchAdRequest(zzb zzbVar, zzc zzcVar) {
        this.zzb = zzbVar.zzb;
        this.zza = new zzdx(zzbVar.zza, this);
    }

    @Deprecated
    public int getAnchorTextColor() {
        return 0;
    }

    @Deprecated
    public int getBackgroundColor() {
        return 0;
    }

    @Deprecated
    public int getBackgroundGradientBottom() {
        return 0;
    }

    @Deprecated
    public int getBackgroundGradientTop() {
        return 0;
    }

    @Deprecated
    public int getBorderColor() {
        return 0;
    }

    @Deprecated
    public int getBorderThickness() {
        return 0;
    }

    @Deprecated
    public int getBorderType() {
        return 0;
    }

    @Deprecated
    public int getCallButtonColor() {
        return 0;
    }

    @Nullable
    @Deprecated
    public String getCustomChannels() {
        return null;
    }

    @Nullable
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(@NonNull Class<T> cls) {
        return this.zza.zzd(cls);
    }

    @Deprecated
    public int getDescriptionTextColor() {
        return 0;
    }

    @Nullable
    @Deprecated
    public String getFontFace() {
        return null;
    }

    @Deprecated
    public int getHeaderTextColor() {
        return 0;
    }

    @Deprecated
    public int getHeaderTextSize() {
        return 0;
    }

    @NonNull
    public Location getLocation() {
        return null;
    }

    @Nullable
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(@NonNull Class<T> cls) {
        return (T) this.zza.zzh(cls);
    }

    @Nullable
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(@NonNull Class<T> cls) {
        return this.zza.zzf(cls);
    }

    @NonNull
    public String getQuery() {
        return this.zzb;
    }

    public boolean isTestDevice(@NonNull Context context) {
        return this.zza.zzs(context);
    }

    public final zzdx zza() {
        return this.zza;
    }
}
