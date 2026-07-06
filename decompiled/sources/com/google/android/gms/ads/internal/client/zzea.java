package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AppEventListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaum;
import com.google.android.gms.internal.ads.zzbnv;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzbzt;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzea {

    @VisibleForTesting
    public final zzaz zza;
    public final zzbnv zzb;
    public final zzp zzc;
    public final AtomicBoolean zzd;
    public final VideoController zze;

    @Nullable
    public zza zzf;
    public AdListener zzg;
    public AdSize[] zzh;

    @Nullable
    public AppEventListener zzi;

    @Nullable
    public zzbu zzj;
    public VideoOptions zzk;
    public String zzl;

    @NotOnlyInitialized
    public final ViewGroup zzm;
    public int zzn;
    public boolean zzo;

    @Nullable
    public OnPaidEventListener zzp;

    public zzea(ViewGroup viewGroup) {
        this(viewGroup, null, false, zzp.zza, null, 0);
    }

    public static zzq zzC(Context context, AdSize[] adSizeArr, int i) {
        for (AdSize adSize : adSizeArr) {
            if (adSize.equals(AdSize.INVALID)) {
                return zzq.zze();
            }
        }
        zzq zzqVar = new zzq(context, adSizeArr);
        zzqVar.zzj = i == 1;
        return zzqVar;
    }

    public static boolean zzD(int i) {
        return i == 1;
    }

    public final boolean zzA() {
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                return zzbuVar.zzY();
            }
            return false;
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final AdSize[] zzB() {
        return this.zzh;
    }

    public final AdListener zza() {
        return this.zzg;
    }

    @Nullable
    public final AdSize zzb() {
        zzq zzqVarZzg;
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null && (zzqVarZzg = zzbuVar.zzg()) != null) {
                return new AdSize(zzqVarZzg.zze, zzqVarZzg.zzb, zzqVarZzg.zza);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        AdSize[] adSizeArr = this.zzh;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    @Nullable
    public final OnPaidEventListener zzc() {
        return this.zzp;
    }

    @Nullable
    public final ResponseInfo zzd() {
        zzdn zzdnVarZzk = null;
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzdnVarZzk = zzbuVar.zzk();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        return ResponseInfo.zza(zzdnVarZzk);
    }

    public final VideoController zzf() {
        return this.zze;
    }

    public final VideoOptions zzg() {
        return this.zzk;
    }

    @Nullable
    public final AppEventListener zzh() {
        return this.zzi;
    }

    @Nullable
    public final zzdq zzi() {
        zzbu zzbuVar = this.zzj;
        if (zzbuVar != null) {
            try {
                return zzbuVar.zzl();
            } catch (RemoteException e) {
                zzbzt.zzl("#007 Could not call remote method.", e);
            }
        }
        return null;
    }

    public final String zzj() {
        zzbu zzbuVar;
        if (this.zzl == null && (zzbuVar = this.zzj) != null) {
            try {
                this.zzl = zzbuVar.zzr();
            } catch (RemoteException e) {
                zzbzt.zzl("#007 Could not call remote method.", e);
            }
        }
        return this.zzl;
    }

    public final void zzk() {
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzx();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final /* synthetic */ void zzl(IObjectWrapper iObjectWrapper) {
        this.zzm.addView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00d1 A[Catch: RemoteException -> 0x00cf, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x00cf, blocks: (B:27:0x009e, B:29:0x00a4, B:31:0x00b2, B:33:0x00c4, B:36:0x00d1), top: B:50:0x009e, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzm(com.google.android.gms.ads.internal.client.zzdx r10) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.client.zzea.zzm(com.google.android.gms.ads.internal.client.zzdx):void");
    }

    public final void zzn() {
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzz();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzo() {
        if (this.zzd.getAndSet(true)) {
            return;
        }
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzA();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzp() {
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzB();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzq(@Nullable zza zzaVar) {
        try {
            this.zzf = zzaVar;
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzC(zzaVar != null ? new zzb(zzaVar) : null);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzr(AdListener adListener) {
        this.zzg = adListener;
        this.zza.zza(adListener);
    }

    public final void zzs(AdSize... adSizeArr) {
        if (this.zzh != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        zzt(adSizeArr);
    }

    public final void zzt(AdSize... adSizeArr) {
        this.zzh = adSizeArr;
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzF(zzC(this.zzm.getContext(), this.zzh, this.zzn));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        this.zzm.requestLayout();
    }

    public final void zzu(String str) {
        if (this.zzl != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzl = str;
    }

    public final void zzv(@Nullable AppEventListener appEventListener) {
        try {
            this.zzi = appEventListener;
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzG(appEventListener != null ? new zzaum(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzw(boolean z) {
        this.zzo = z;
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzN(z);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzx(@Nullable OnPaidEventListener onPaidEventListener) {
        try {
            this.zzp = onPaidEventListener;
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzP(new zzfe(onPaidEventListener));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final void zzy(VideoOptions videoOptions) {
        this.zzk = videoOptions;
        try {
            zzbu zzbuVar = this.zzj;
            if (zzbuVar != null) {
                zzbuVar.zzU(videoOptions == null ? null : new zzfl(videoOptions));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    public final boolean zzz(zzbu zzbuVar) {
        try {
            IObjectWrapper iObjectWrapperZzn = zzbuVar.zzn();
            if (iObjectWrapperZzn == null || ((View) ObjectWrapper.unwrap(iObjectWrapperZzn)).getParent() != null) {
                return false;
            }
            this.zzm.addView((View) ObjectWrapper.unwrap(iObjectWrapperZzn));
            this.zzj = zzbuVar;
            return true;
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            return false;
        }
    }

    public zzea(ViewGroup viewGroup, int i) {
        this(viewGroup, null, false, zzp.zza, null, i);
    }

    public zzea(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzp.zza, null, 0);
    }

    public zzea(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, z, zzp.zza, null, i);
    }

    @VisibleForTesting
    public zzea(ViewGroup viewGroup, @Nullable AttributeSet attributeSet, boolean z, zzp zzpVar, @Nullable zzbu zzbuVar, int i) {
        zzq zzqVarZze;
        this.zzb = new zzbnv();
        this.zze = new VideoController();
        this.zza = new zzdz(this);
        this.zzm = viewGroup;
        this.zzc = zzpVar;
        this.zzj = null;
        this.zzd = new AtomicBoolean(false);
        this.zzn = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzy zzyVar = new zzy(context, attributeSet);
                this.zzh = zzyVar.zzb(z);
                this.zzl = zzyVar.zzb;
                if (viewGroup.isInEditMode()) {
                    zzbzm zzbzmVarZzb = zzay.zzb();
                    AdSize adSize = this.zzh[0];
                    int i2 = this.zzn;
                    if (adSize.equals(AdSize.INVALID)) {
                        zzqVarZze = zzq.zze();
                    } else {
                        zzq zzqVar = new zzq(context, adSize);
                        zzqVar.zzj = i2 == 1;
                        zzqVarZze = zzqVar;
                    }
                    zzbzmVarZzb.getClass();
                    zzbzm.zzD(viewGroup, zzqVarZze, "Ads by Google", -16777216, -1);
                }
            } catch (IllegalArgumentException e) {
                zzay.zzb().zzl(viewGroup, new zzq(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }
}
