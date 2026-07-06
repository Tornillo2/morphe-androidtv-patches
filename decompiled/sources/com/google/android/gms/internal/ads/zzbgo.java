package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.internal.client.zzct;
import com.google.android.gms.ads.internal.client.zzcv;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.client.zzcx;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzep;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbgo extends UnifiedNativeAd {
    public final zzbgn zza;
    public final zzber zzc;
    public final NativeAd.AdChoicesInfo zze;
    public final List zzb = new ArrayList();
    public final VideoController zzd = new VideoController();
    public final List zzf = new ArrayList();

    public zzbgo(zzbgn zzbgnVar) {
        zzbeq zzbeqVarZzk;
        zzbeq zzbeoVar;
        this.zza = zzbgnVar;
        zzbej zzbejVar = null;
        try {
            List listZzu = zzbgnVar.zzu();
            if (listZzu != null) {
                for (Object obj : listZzu) {
                    if (obj instanceof IBinder) {
                        IBinder iBinder = (IBinder) obj;
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        zzbeoVar = iInterfaceQueryLocalInterface instanceof zzbeq ? (zzbeq) iInterfaceQueryLocalInterface : new zzbeo(iBinder);
                    } else {
                        zzbeoVar = null;
                    }
                    if (zzbeoVar != null) {
                        this.zzb.add(new zzber(zzbeoVar));
                    }
                }
            }
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
        try {
            List listZzv = this.zza.zzv();
            if (listZzv != null) {
                for (Object obj2 : listZzv) {
                    zzcw zzcwVarZzb = obj2 instanceof IBinder ? zzcv.zzb((IBinder) obj2) : null;
                    if (zzcwVarZzb != null) {
                        this.zzf.add(new zzcx(zzcwVarZzb));
                    }
                }
            }
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
        }
        try {
            zzbeqVarZzk = this.zza.zzk();
        } catch (RemoteException e3) {
            zzbzt.zzh("", e3);
        }
        zzber zzberVar = zzbeqVarZzk != null ? new zzber(zzbeqVarZzk) : null;
        this.zzc = zzberVar;
        try {
            if (this.zza.zzi() != null) {
                zzbejVar = new zzbej(this.zza.zzi());
            }
        } catch (RemoteException e4) {
            zzbzt.zzh("", e4);
        }
        this.zze = zzbejVar;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void cancelUnconfirmedClick() {
        try {
            this.zza.zzw();
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to cancelUnconfirmedClick", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void destroy() {
        try {
            this.zza.zzx();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void enableCustomClickGesture() {
        try {
            this.zza.zzC();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getAdvertiser() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getBody() {
        try {
            return this.zza.zzo();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getCallToAction() {
        try {
            return this.zza.zzp();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Bundle getExtras() {
        try {
            Bundle bundleZzf = this.zza.zzf();
            if (bundleZzf != null) {
                return bundleZzf;
            }
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
        return new Bundle();
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getHeadline() {
        try {
            return this.zza.zzq();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final NativeAd.Image getIcon() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final MediaContent getMediaContent() {
        try {
            if (this.zza.zzj() != null) {
                return new zzep(this.zza.zzj(), null);
            }
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
        return null;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getMediationAdapterClassName() {
        try {
            return this.zza.zzr();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zzf;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getPrice() {
        try {
            return this.zza.zzs();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final ResponseInfo getResponseInfo() {
        zzdn zzdnVarZzg;
        try {
            zzdnVarZzg = this.zza.zzg();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            zzdnVarZzg = null;
        }
        return ResponseInfo.zza(zzdnVarZzg);
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final Double getStarRating() {
        try {
            double dZze = this.zza.zze();
            if (dZze == -1.0d) {
                return null;
            }
            return Double.valueOf(dZze);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final String getStore() {
        try {
            return this.zza.zzt();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final VideoController getVideoController() {
        try {
            if (this.zza.zzh() != null) {
                this.zzd.zzb(this.zza.zzh());
            }
        } catch (RemoteException e) {
            zzbzt.zzh("Exception occurred while getting video controller", e);
        }
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zza.zzG();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zza.zzH();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void muteThisAd(MuteThisAdReason muteThisAdReason) {
        try {
            try {
                if (this.zza.zzH()) {
                    if (muteThisAdReason == null) {
                        this.zza.zzy(null);
                        return;
                    } else if (muteThisAdReason instanceof zzcx) {
                        this.zza.zzy(((zzcx) muteThisAdReason).zzb);
                        return;
                    } else {
                        zzbzt.zzg("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
                        return;
                    }
                }
            } catch (RemoteException e) {
                zzbzt.zzh("", e);
            }
            zzbzt.zzg("Ad is not custom mute enabled");
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zza.zzz(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void recordCustomClickGesture() {
        try {
            this.zza.zzA();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zza.zzI(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zza.zzB(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zza.zzD(new zzct(muteThisAdListener));
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zza.zzE(new zzfe(onPaidEventListener));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to setOnPaidEventListener", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setUnconfirmedClickListener(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zza.zzF(new zzbgz(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to setUnconfirmedClickListener", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    @Nullable
    public final Object zza() {
        try {
            IObjectWrapper iObjectWrapperZzl = this.zza.zzl();
            if (iObjectWrapperZzl != null) {
                return ObjectWrapper.unwrap(iObjectWrapperZzl);
            }
            return null;
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }
}
