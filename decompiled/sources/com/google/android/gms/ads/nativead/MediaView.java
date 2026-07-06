package com.google.android.gms.ads.nativead;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbfk;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MediaView extends FrameLayout {

    @Nullable
    public MediaContent zza;
    public boolean zzb;
    public ImageView.ScaleType zzc;
    public boolean zzd;
    public zzb zze;
    public zzc zzf;

    public MediaView(@NonNull Context context) {
        super(context);
    }

    @Nullable
    public MediaContent getMediaContent() {
        return this.zza;
    }

    public void setImageScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.zzd = true;
        this.zzc = scaleType;
        zzc zzcVar = this.zzf;
        if (zzcVar != null) {
            zzcVar.zza.zzc(scaleType);
        }
    }

    public void setMediaContent(@Nullable MediaContent mediaContent) {
        boolean zZzr;
        this.zzb = true;
        this.zza = mediaContent;
        zzb zzbVar = this.zze;
        if (zzbVar != null) {
            zzbVar.zza.zzb(mediaContent);
        }
        if (mediaContent == null) {
            return;
        }
        try {
            zzbfk zzbfkVarZza = mediaContent.zza();
            if (zzbfkVarZza != null) {
                if (!mediaContent.hasVideoContent()) {
                    if (mediaContent.zzb()) {
                        zZzr = zzbfkVarZza.zzr(ObjectWrapper.wrap(this));
                    }
                    removeAllViews();
                }
                zZzr = zzbfkVarZza.zzs(ObjectWrapper.wrap(this));
                if (zZzr) {
                    return;
                }
                removeAllViews();
            }
        } catch (RemoteException e) {
            removeAllViews();
            zzbzt.zzh("", e);
        }
    }

    public final synchronized void zza(zzb zzbVar) {
        this.zze = zzbVar;
        if (this.zzb) {
            zzbVar.zza.zzb(this.zza);
        }
    }

    public final synchronized void zzb(zzc zzcVar) {
        this.zzf = zzcVar;
        if (this.zzd) {
            zzcVar.zza.zzc(this.zzc);
        }
    }

    public MediaView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public MediaView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
