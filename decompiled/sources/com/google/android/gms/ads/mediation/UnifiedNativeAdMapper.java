package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class UnifiedNativeAdMapper {
    public String zza;
    public List zzb;
    public String zzc;
    public NativeAd.Image zzd;
    public String zze;
    public String zzf;
    public Double zzg;
    public String zzh;
    public String zzi;
    public VideoController zzj;
    public boolean zzk;
    public View zzl;
    public View zzm;
    public Object zzn;
    public Bundle zzo = new Bundle();
    public boolean zzp;
    public boolean zzq;
    public float zzr;

    @NonNull
    public View getAdChoicesContent() {
        return this.zzl;
    }

    @NonNull
    public final String getAdvertiser() {
        return this.zzf;
    }

    @NonNull
    public final String getBody() {
        return this.zzc;
    }

    @NonNull
    public final String getCallToAction() {
        return this.zze;
    }

    public float getCurrentTime() {
        return 0.0f;
    }

    public float getDuration() {
        return 0.0f;
    }

    @NonNull
    public final Bundle getExtras() {
        return this.zzo;
    }

    @NonNull
    public final String getHeadline() {
        return this.zza;
    }

    @NonNull
    public final NativeAd.Image getIcon() {
        return this.zzd;
    }

    @NonNull
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    public float getMediaContentAspectRatio() {
        return this.zzr;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzq;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzp;
    }

    @NonNull
    public final String getPrice() {
        return this.zzi;
    }

    @NonNull
    public final Double getStarRating() {
        return this.zzg;
    }

    @NonNull
    public final String getStore() {
        return this.zzh;
    }

    public boolean hasVideoContent() {
        return this.zzk;
    }

    public void setAdChoicesContent(@NonNull View view) {
        this.zzl = view;
    }

    public final void setAdvertiser(@NonNull String str) {
        this.zzf = str;
    }

    public final void setBody(@NonNull String str) {
        this.zzc = str;
    }

    public final void setCallToAction(@NonNull String str) {
        this.zze = str;
    }

    public final void setExtras(@NonNull Bundle bundle) {
        this.zzo = bundle;
    }

    public void setHasVideoContent(boolean z) {
        this.zzk = z;
    }

    public final void setHeadline(@NonNull String str) {
        this.zza = str;
    }

    public final void setIcon(@NonNull NativeAd.Image image) {
        this.zzd = image;
    }

    public final void setImages(@NonNull List<NativeAd.Image> list) {
        this.zzb = list;
    }

    public void setMediaContentAspectRatio(float f) {
        this.zzr = f;
    }

    public void setMediaView(@NonNull View view) {
        this.zzm = view;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.zzq = z;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.zzp = z;
    }

    public final void setPrice(@NonNull String str) {
        this.zzi = str;
    }

    public final void setStarRating(@NonNull Double d) {
        this.zzg = d;
    }

    public final void setStore(@NonNull String str) {
        this.zzh = str;
    }

    @NonNull
    public final View zza() {
        return this.zzm;
    }

    @NonNull
    public final VideoController zzb() {
        return this.zzj;
    }

    @NonNull
    public final Object zzc() {
        return this.zzn;
    }

    public final void zzd(@NonNull Object obj) {
        this.zzn = obj;
    }

    public final void zze(@NonNull VideoController videoController) {
        this.zzj = videoController;
    }

    public void recordImpression() {
    }

    public void handleClick(@NonNull View view) {
    }

    public void untrackView(@NonNull View view) {
    }

    public void trackViews(@NonNull View view, @NonNull Map<String, View> map, @NonNull Map<String, View> map2) {
    }
}
