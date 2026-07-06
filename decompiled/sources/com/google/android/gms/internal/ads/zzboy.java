package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationAppOpenAd;
import com.google.android.gms.ads.mediation.MediationAppOpenAdConfiguration;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterscrollerAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzboy extends zzboa {
    public final Object zza;
    public zzbpa zzb;
    public zzbvh zzc;
    public IObjectWrapper zzd;
    public View zze;
    public MediationInterstitialAd zzf;
    public UnifiedNativeAdMapper zzg;
    public MediationRewardedAd zzh;
    public MediationInterscrollerAd zzi;
    public MediationAppOpenAd zzj;
    public final String zzk = "";

    public zzboy(@NonNull Adapter adapter) {
        this.zza = adapter;
    }

    public static final boolean zzW(zzl zzlVar) {
        if (zzlVar.zzf) {
            return true;
        }
        zzay.zzb();
        return zzbzm.zzr();
    }

    @Nullable
    public static final String zzX(String str, zzl zzlVar) {
        try {
            return new JSONObject(str).getString("max_ad_content_rating");
        } catch (JSONException unused) {
            return zzlVar.zzu;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzA(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Requesting rewarded ad from adapter.");
            try {
                ((Adapter) this.zza).loadRewardedAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, null), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), ""), new zzbow(this, zzboeVar));
                return;
            } catch (Exception e) {
                zzbzt.zzh("", e);
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzB(zzl zzlVar, String str, String str2) throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            zzA(this.zzd, zzlVar, str, new zzbpb((Adapter) obj, this.zzc));
            return;
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzC(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Requesting rewarded interstitial ad from adapter.");
            try {
                ((Adapter) this.zza).loadRewardedInterstitialAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, null), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), ""), new zzbow(this, zzboeVar));
                return;
            } catch (Exception e) {
                zzbzt.zzh("", e);
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzD(IObjectWrapper iObjectWrapper) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        Object obj = this.zza;
        if (obj instanceof OnContextChangedListener) {
            ((OnContextChangedListener) obj).onContextChanged(context);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzE() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onPause();
            } catch (Throwable th) {
                throw zzboy$$ExternalSyntheticOutline0.m("", th);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzF() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onResume();
            } catch (Throwable th) {
                throw zzboy$$ExternalSyntheticOutline0.m("", th);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzG(boolean z) throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof OnImmersiveModeUpdatedListener) {
            try {
                ((OnImmersiveModeUpdatedListener) obj).onImmersiveModeUpdated(z);
                return;
            } catch (Throwable th) {
                zzbzt.zzh("", th);
                return;
            }
        }
        zzbzt.zze(OnImmersiveModeUpdatedListener.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzH(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Show app open ad from adapter.");
            MediationAppOpenAd mediationAppOpenAd = this.zzj;
            if (mediationAppOpenAd != null) {
                mediationAppOpenAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
                return;
            } else {
                zzbzt.zzg("Can not show null mediation app open ad.");
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzI() throws RemoteException {
        if (this.zza instanceof MediationInterstitialAdapter) {
            zzbzt.zze("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zza).showInterstitial();
                return;
            } catch (Throwable th) {
                throw zzboy$$ExternalSyntheticOutline0.m("", th);
            }
        }
        zzbzt.zzj(MediationInterstitialAdapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzJ(IObjectWrapper iObjectWrapper) throws RemoteException {
        Object obj = this.zza;
        if ((obj instanceof Adapter) || (obj instanceof MediationInterstitialAdapter)) {
            if (obj instanceof MediationInterstitialAdapter) {
                zzI();
                return;
            }
            zzbzt.zze("Show interstitial ad from adapter.");
            MediationInterstitialAd mediationInterstitialAd = this.zzf;
            if (mediationInterstitialAd != null) {
                mediationInterstitialAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
                return;
            } else {
                zzbzt.zzg("Can not show null mediation interstitial ad.");
                throw new RemoteException();
            }
        }
        zzbzt.zzj(MediationInterstitialAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzK(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Show rewarded ad from adapter.");
            MediationRewardedAd mediationRewardedAd = this.zzh;
            if (mediationRewardedAd != null) {
                mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
                return;
            } else {
                zzbzt.zzg("Can not show null mediation rewarded ad.");
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzL() throws RemoteException {
        if (this.zza instanceof Adapter) {
            MediationRewardedAd mediationRewardedAd = this.zzh;
            if (mediationRewardedAd != null) {
                mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(this.zzd));
                return;
            } else {
                zzbzt.zzg("Can not show null mediated rewarded ad.");
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final boolean zzM() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final boolean zzN() throws RemoteException {
        if (this.zza instanceof Adapter) {
            return this.zzc != null;
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzboj zzO() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzbok zzP() {
        return null;
    }

    public final Bundle zzU(zzl zzlVar) {
        Bundle bundle;
        Bundle bundle2 = zzlVar.zzm;
        return (bundle2 == null || (bundle = bundle2.getBundle(this.zza.getClass().getName())) == null) ? new Bundle() : bundle;
    }

    public final Bundle zzV(String str, zzl zzlVar, String str2) throws RemoteException {
        zzbzt.zze("Server parameters: ".concat(String.valueOf(str)));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zza instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzlVar != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzlVar.zzg);
                }
            }
            bundle.remove("max_ad_content_rating");
            return bundle;
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zze() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zzf() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final Bundle zzg() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzdq zzh() {
        Object obj = this.zza;
        if (obj instanceof zza) {
            try {
                return ((zza) obj).getVideoController();
            } catch (Throwable th) {
                zzbzt.zzh("", th);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzbfk zzi() {
        zzbpa zzbpaVar = this.zzb;
        if (zzbpaVar == null) {
            return null;
        }
        NativeCustomTemplateAd nativeCustomTemplateAd = zzbpaVar.zzc;
        if (nativeCustomTemplateAd instanceof zzbfl) {
            return ((zzbfl) nativeCustomTemplateAd).zza;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzboh zzj() {
        MediationInterscrollerAd mediationInterscrollerAd = this.zzi;
        if (mediationInterscrollerAd != null) {
            return new zzboz(mediationInterscrollerAd);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzbon zzk() {
        UnifiedNativeAdMapper unifiedNativeAdMapper;
        UnifiedNativeAdMapper unifiedNativeAdMapper2;
        Object obj = this.zza;
        if (!(obj instanceof MediationNativeAdapter)) {
            if (!(obj instanceof Adapter) || (unifiedNativeAdMapper = this.zzg) == null) {
                return null;
            }
            return new zzbpd(unifiedNativeAdMapper);
        }
        zzbpa zzbpaVar = this.zzb;
        if (zzbpaVar == null || (unifiedNativeAdMapper2 = zzbpaVar.zzb) == null) {
            return null;
        }
        return new zzbpd(unifiedNativeAdMapper2);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzbqj zzl() {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            return zzbqj.zza(((Adapter) obj).getVersionInfo());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    @Nullable
    public final zzbqj zzm() {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            return zzbqj.zza(((Adapter) obj).getSDKVersionInfo());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final IObjectWrapper zzn() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationBannerAdapter) {
            try {
                return new ObjectWrapper(((MediationBannerAdapter) obj).getBannerView());
            } catch (Throwable th) {
                throw zzboy$$ExternalSyntheticOutline0.m("", th);
            }
        }
        if (obj instanceof Adapter) {
            return new ObjectWrapper(this.zze);
        }
        zzbzt.zzj(MediationBannerAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzo() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onDestroy();
            } catch (Throwable th) {
                throw zzboy$$ExternalSyntheticOutline0.m("", th);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzp(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzbvh zzbvhVar, String str2) throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            this.zzd = iObjectWrapper;
            this.zzc = zzbvhVar;
            zzbvhVar.zzl(new ObjectWrapper(obj));
            return;
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    @Override // com.google.android.gms.internal.ads.zzbob
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzq(com.google.android.gms.dynamic.IObjectWrapper r5, com.google.android.gms.internal.ads.zzbki r6, java.util.List r7) throws android.os.RemoteException {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zza
            boolean r0 = r0 instanceof com.google.android.gms.ads.mediation.Adapter
            if (r0 == 0) goto L88
            com.google.android.gms.internal.ads.zzbos r0 = new com.google.android.gms.internal.ads.zzbos
            r0.<init>(r4, r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r7 = r7.iterator()
        L14:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L7a
            java.lang.Object r1 = r7.next()
            com.google.android.gms.internal.ads.zzbko r1 = (com.google.android.gms.internal.ads.zzbko) r1
            java.lang.String r2 = r1.zza
            int r3 = r2.hashCode()
            switch(r3) {
                case -1396342996: goto L61;
                case -1052618729: goto L56;
                case -239580146: goto L4b;
                case 604727084: goto L40;
                case 1167692200: goto L35;
                case 1911491517: goto L2a;
                default: goto L29;
            }
        L29:
            goto L6c
        L2a:
            java.lang.String r3 = "rewarded_interstitial"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.REWARDED_INTERSTITIAL
            goto L6d
        L35:
            java.lang.String r3 = "app_open"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.APP_OPEN_AD
            goto L6d
        L40:
            java.lang.String r3 = "interstitial"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.INTERSTITIAL
            goto L6d
        L4b:
            java.lang.String r3 = "rewarded"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.REWARDED
            goto L6d
        L56:
            java.lang.String r3 = "native"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.NATIVE
            goto L6d
        L61:
            java.lang.String r3 = "banner"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6c
            com.google.android.gms.ads.AdFormat r2 = com.google.android.gms.ads.AdFormat.BANNER
            goto L6d
        L6c:
            r2 = 0
        L6d:
            if (r2 == 0) goto L14
            com.google.android.gms.ads.mediation.MediationConfiguration r3 = new com.google.android.gms.ads.mediation.MediationConfiguration
            android.os.Bundle r1 = r1.zzb
            r3.<init>(r2, r1)
            r6.add(r3)
            goto L14
        L7a:
            java.lang.Object r7 = r4.zza
            com.google.android.gms.ads.mediation.Adapter r7 = (com.google.android.gms.ads.mediation.Adapter) r7
            java.lang.Object r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r5)
            android.content.Context r5 = (android.content.Context) r5
            r7.initialize(r5, r0, r6)
            return
        L88:
            android.os.RemoteException r5 = new android.os.RemoteException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzboy.zzq(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzbki, java.util.List):void");
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzr(IObjectWrapper iObjectWrapper, zzbvh zzbvhVar, List list) throws RemoteException {
        zzbzt.zzj("Could not initialize rewarded video adapter.");
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzs(zzl zzlVar, String str) throws RemoteException {
        zzB(zzlVar, str, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzt(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Requesting app open ad from adapter.");
            try {
                ((Adapter) this.zza).loadAppOpenAd(new MediationAppOpenAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, null), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), ""), new zzbox(this, zzboeVar));
                return;
            } catch (Exception e) {
                zzbzt.zzh("", e);
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzu(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        zzv(iObjectWrapper, zzqVar, zzlVar, str, null, zzboeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzv(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        Object obj = this.zza;
        if (!(obj instanceof MediationBannerAdapter) && !(obj instanceof Adapter)) {
            zzbzt.zzj(MediationBannerAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
            throw new RemoteException();
        }
        zzbzt.zze("Requesting banner ad from adapter.");
        AdSize adSizeZzd = zzqVar.zzn ? zzb.zzd(zzqVar.zze, zzqVar.zzb) : new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza);
        Object obj2 = this.zza;
        if (!(obj2 instanceof MediationBannerAdapter)) {
            if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadBannerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, str2), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), adSizeZzd, this.zzk), new zzbot(this, zzboeVar));
                    return;
                } finally {
                    RemoteException remoteExceptionM = zzboy$$ExternalSyntheticOutline0.m("", th);
                }
            }
            return;
        }
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) obj2;
            List list = zzlVar.zze;
            HashSet hashSet = list != null ? new HashSet(list) : null;
            long j = zzlVar.zzb;
            zzboq zzboqVar = new zzboq(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzW(zzlVar), zzlVar.zzg, zzlVar.zzr, zzlVar.zzt, zzX(str, zzlVar));
            Bundle bundle = zzlVar.zzm;
            mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzbpa(zzboeVar), zzV(str, zzlVar, str2), adSizeZzd, zzboqVar, bundle != null ? bundle.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m(str, th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzw(IObjectWrapper iObjectWrapper, zzq zzqVar, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        if (this.zza instanceof Adapter) {
            zzbzt.zze("Requesting interscroller ad from adapter.");
            try {
                Adapter adapter = (Adapter) this.zza;
                adapter.loadInterscrollerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, str2), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), zzb.zze(zzqVar.zze, zzqVar.zzb), ""), new zzbor(this, zzboeVar, adapter));
                return;
            } catch (Exception e) {
                zzbzt.zzh("", e);
                throw new RemoteException();
            }
        }
        zzbzt.zzj(Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzx(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, zzboe zzboeVar) throws RemoteException {
        zzy(iObjectWrapper, zzlVar, str, null, zzboeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzy(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, String str2, zzboe zzboeVar) throws RemoteException {
        Object obj = this.zza;
        if (!(obj instanceof MediationInterstitialAdapter) && !(obj instanceof Adapter)) {
            zzbzt.zzj(MediationInterstitialAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
            throw new RemoteException();
        }
        zzbzt.zze("Requesting interstitial ad from adapter.");
        Object obj2 = this.zza;
        if (!(obj2 instanceof MediationInterstitialAdapter)) {
            if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadInterstitialAd(new MediationInterstitialAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, str2), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), this.zzk), new zzbou(this, zzboeVar));
                    return;
                } finally {
                    RemoteException remoteExceptionM = zzboy$$ExternalSyntheticOutline0.m("", th);
                }
            }
            return;
        }
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) obj2;
            List list = zzlVar.zze;
            HashSet hashSet = list != null ? new HashSet(list) : null;
            long j = zzlVar.zzb;
            zzboq zzboqVar = new zzboq(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzW(zzlVar), zzlVar.zzg, zzlVar.zzr, zzlVar.zzt, zzX(str, zzlVar));
            Bundle bundle = zzlVar.zzm;
            mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzbpa(zzboeVar), zzV(str, zzlVar, str2), zzboqVar, bundle != null ? bundle.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m(str, th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbob
    public final void zzz(IObjectWrapper iObjectWrapper, zzl zzlVar, String str, String str2, zzboe zzboeVar, zzbee zzbeeVar, List list) throws RemoteException {
        Object obj = this.zza;
        if (!(obj instanceof MediationNativeAdapter) && !(obj instanceof Adapter)) {
            zzbzt.zzj(MediationNativeAdapter.class.getCanonicalName() + " or " + Adapter.class.getCanonicalName() + " #009 Class mismatch: " + this.zza.getClass().getCanonicalName());
            throw new RemoteException();
        }
        zzbzt.zze("Requesting native ad from adapter.");
        Object obj2 = this.zza;
        if (!(obj2 instanceof MediationNativeAdapter)) {
            if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadNativeAd(new MediationNativeAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzV(str, zzlVar, str2), zzU(zzlVar), zzW(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzX(str, zzlVar), this.zzk, zzbeeVar), new zzbov(this, zzboeVar));
                    return;
                } finally {
                    RemoteException remoteExceptionM = zzboy$$ExternalSyntheticOutline0.m("", th);
                }
            }
            return;
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) obj2;
            List list2 = zzlVar.zze;
            HashSet hashSet = list2 != null ? new HashSet(list2) : null;
            long j = zzlVar.zzb;
            zzbpc zzbpcVar = new zzbpc(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzW(zzlVar), zzlVar.zzg, zzbeeVar, list, zzlVar.zzr, zzlVar.zzt, zzX(str, zzlVar));
            Bundle bundle = zzlVar.zzm;
            Bundle bundle2 = bundle != null ? bundle.getBundle(mediationNativeAdapter.getClass().getName()) : null;
            this.zzb = new zzbpa(zzboeVar);
            mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzb, zzV(str, zzlVar, str2), zzbpcVar, bundle2);
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m(str, th);
        }
    }

    public zzboy(@NonNull MediationAdapter mediationAdapter) {
        this.zza = mediationAdapter;
    }
}
