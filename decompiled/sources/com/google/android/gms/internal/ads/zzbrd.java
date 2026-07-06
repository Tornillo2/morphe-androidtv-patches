package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.internal.client.zzct;
import com.google.android.gms.ads.internal.client.zzcv;
import com.google.android.gms.ads.internal.client.zzcw;
import com.google.android.gms.ads.internal.client.zzcx;
import com.google.android.gms.ads.internal.client.zzdn;
import com.google.android.gms.ads.internal.client.zzep;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.ads.nativead.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbrd extends NativeAd {
    public final zzbgn zza;

    @Nullable
    public final zzbrc zzc;

    @Nullable
    public final NativeAd.AdChoicesInfo zzd;
    public final List zzb = new ArrayList();
    public final List zze = new ArrayList();

    public zzbrd(zzbgn zzbgnVar) {
        zzbeq zzbeqVarZzk;
        this.zza = zzbgnVar;
        zzbra zzbraVar = null;
        try {
            List listZzu = zzbgnVar.zzu();
            if (listZzu != null) {
                for (Object obj : listZzu) {
                    zzbeq zzbeqVarZzg = obj instanceof IBinder ? zzbep.zzg((IBinder) obj) : null;
                    if (zzbeqVarZzg != null) {
                        this.zzb.add(new zzbrc(zzbeqVarZzg));
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
                        this.zze.add(new zzcx(zzcwVarZzb));
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
        zzbrc zzbrcVar = zzbeqVarZzk != null ? new zzbrc(zzbeqVarZzk) : null;
        this.zzc = zzbrcVar;
        try {
            if (this.zza.zzi() != null) {
                zzbraVar = new zzbra(this.zza.zzi());
            }
        } catch (RemoteException e4) {
            zzbzt.zzh("", e4);
        }
        this.zzd = zzbraVar;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void cancelUnconfirmedClick() {
        try {
            this.zza.zzw();
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to cancelUnconfirmedClick", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void destroy() {
        try {
            this.zza.zzx();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void enableCustomClickGesture() {
        try {
            this.zza.zzC();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getAdvertiser() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getBody() {
        try {
            return this.zza.zzo();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getCallToAction() {
        try {
            return this.zza.zzp();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
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

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getHeadline() {
        try {
            return this.zza.zzq();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final NativeAd.Image getIcon() {
        return this.zzc;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
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

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zze;
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getPrice() {
        try {
            return this.zza.zzs();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
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

    @Override // com.google.android.gms.ads.nativead.NativeAd
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

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final String getStore() {
        try {
            return this.zza.zzt();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zza.zzG();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zza.zzH();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
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

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zza.zzz(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void recordCustomClickGesture() {
        try {
            this.zza.zzA();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zza.zzI(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zza.zzB(bundle);
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zza.zzD(new zzct(muteThisAdListener));
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {
        try {
            this.zza.zzE(new zzfe(onPaidEventListener));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to setOnPaidEventListener", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    public final void setUnconfirmedClickListener(NativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zza.zzF(new zzbrl(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzbzt.zzh("Failed to setUnconfirmedClickListener", e);
        }
    }

    @Override // com.google.android.gms.ads.nativead.NativeAd
    @Nullable
    public final /* bridge */ /* synthetic */ Object zza() {
        try {
            return this.zza.zzm();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            return null;
        }
    }
}
