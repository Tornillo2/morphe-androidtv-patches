package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzdq;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.mediation.MediationAppOpenAd;
import com.google.android.gms.ads.mediation.MediationAppOpenAdConfiguration;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.mediation.rtb.RtbSignalData;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbqh extends zzbpu {
    public final RtbAdapter zza;
    public MediationInterstitialAd zzb;
    public MediationRewardedAd zzc;
    public MediationAppOpenAd zzd;
    public String zze = "";

    public zzbqh(RtbAdapter rtbAdapter) {
        this.zza = rtbAdapter;
    }

    public static final Bundle zzw(String str) throws RemoteException {
        zzbzt.zzj("Server parameters: ".concat(String.valueOf(str)));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                bundle2.putString(next, jSONObject.getString(next));
            }
            return bundle2;
        } catch (JSONException e) {
            zzbzt.zzh("", e);
            throw new RemoteException();
        }
    }

    public static final boolean zzx(zzl zzlVar) {
        if (zzlVar.zzf) {
            return true;
        }
        zzay.zzb();
        return zzbzm.zzr();
    }

    @Nullable
    public static final String zzy(String str, zzl zzlVar) {
        try {
            return new JSONObject(str).getString("max_ad_content_rating");
        } catch (JSONException unused) {
            return zzlVar.zzu;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    @Nullable
    public final zzdq zze() {
        MediationExtrasReceiver mediationExtrasReceiver = this.zza;
        if (mediationExtrasReceiver instanceof zza) {
            try {
                return ((zza) mediationExtrasReceiver).getVideoController();
            } catch (Throwable th) {
                zzbzt.zzh("", th);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final zzbqj zzf() throws RemoteException {
        return zzbqj.zza(this.zza.getVersionInfo());
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final zzbqj zzg() throws RemoteException {
        return zzbqj.zza(this.zza.getSDKVersionInfo());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzh(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzq zzqVar, zzbpy zzbpyVar) throws RemoteException {
        AdFormat adFormat;
        try {
            zzbqf zzbqfVar = new zzbqf(this, zzbpyVar);
            RtbAdapter rtbAdapter = this.zza;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals("banner")) {
                        adFormat = AdFormat.BANNER;
                        MediationConfiguration mediationConfiguration = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(mediationConfiguration);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                case -1052618729:
                    if (str.equals("native")) {
                        adFormat = AdFormat.NATIVE;
                        MediationConfiguration mediationConfiguration2 = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(mediationConfiguration2);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList2, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                case -239580146:
                    if (str.equals("rewarded")) {
                        adFormat = AdFormat.REWARDED;
                        MediationConfiguration mediationConfiguration22 = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList22 = new ArrayList();
                        arrayList22.add(mediationConfiguration22);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList22, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                case 604727084:
                    if (str.equals("interstitial")) {
                        adFormat = AdFormat.INTERSTITIAL;
                        MediationConfiguration mediationConfiguration222 = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList222 = new ArrayList();
                        arrayList222.add(mediationConfiguration222);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList222, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                case 1167692200:
                    if (str.equals("app_open")) {
                        adFormat = AdFormat.APP_OPEN_AD;
                        MediationConfiguration mediationConfiguration2222 = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList2222 = new ArrayList();
                        arrayList2222.add(mediationConfiguration2222);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList2222, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                case 1911491517:
                    if (str.equals("rewarded_interstitial")) {
                        adFormat = AdFormat.REWARDED_INTERSTITIAL;
                        MediationConfiguration mediationConfiguration22222 = new MediationConfiguration(adFormat, bundle2);
                        ArrayList arrayList22222 = new ArrayList();
                        arrayList22222.add(mediationConfiguration22222);
                        rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList22222, bundle, new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza)), zzbqfVar);
                        return;
                    }
                    throw new IllegalArgumentException("Internal Error");
                default:
                    throw new IllegalArgumentException("Internal Error");
            }
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Error generating signals for RTB", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzi(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpg zzbpgVar, zzboe zzboeVar) throws RemoteException {
        try {
            this.zza.loadRtbAppOpenAd(new MediationAppOpenAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), this.zze), new zzbqe(this, zzbpgVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render app open ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzj(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException {
        try {
            this.zza.loadRtbBannerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza), this.zze), new zzbqa(this, zzbpjVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render banner ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzk(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpj zzbpjVar, zzboe zzboeVar, zzq zzqVar) throws RemoteException {
        try {
            this.zza.loadRtbInterscrollerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), new AdSize(zzqVar.zze, zzqVar.zzb, zzqVar.zza), this.zze), new zzbqb(this, zzbpjVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render interscroller ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzl(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpm zzbpmVar, zzboe zzboeVar) throws RemoteException {
        try {
            this.zza.loadRtbInterstitialAd(new MediationInterstitialAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), this.zze), new zzbqc(this, zzbpmVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render interstitial ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzm(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar) throws RemoteException {
        zzn(str, str2, zzlVar, iObjectWrapper, zzbppVar, zzboeVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzn(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbpp zzbppVar, zzboe zzboeVar, zzbee zzbeeVar) throws RemoteException {
        try {
            this.zza.loadRtbNativeAd(new MediationNativeAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), this.zze, zzbeeVar), new zzbqd(this, zzbppVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render native ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzo(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException {
        try {
            this.zza.loadRtbRewardedInterstitialAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), this.zze), new zzbqg(this, zzbpsVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render rewarded interstitial ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzp(String str, String str2, zzl zzlVar, IObjectWrapper iObjectWrapper, zzbps zzbpsVar, zzboe zzboeVar) throws RemoteException {
        try {
            this.zza.loadRtbRewardedAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzw(str2), zzv(zzlVar), zzx(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzy(str2, zzlVar), this.zze), new zzbqg(this, zzbpsVar, zzboeVar));
        } catch (Throwable th) {
            throw zzboy$$ExternalSyntheticOutline0.m("Adapter failed to render rewarded ad.", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final void zzq(String str) {
        this.zze = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationAppOpenAd mediationAppOpenAd = this.zzd;
        if (mediationAppOpenAd == null) {
            return false;
        }
        try {
            mediationAppOpenAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzbzt.zzh("", th);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationInterstitialAd mediationInterstitialAd = this.zzb;
        if (mediationInterstitialAd == null) {
            return false;
        }
        try {
            mediationInterstitialAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzbzt.zzh("", th);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbpv
    public final boolean zzt(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationRewardedAd mediationRewardedAd = this.zzc;
        if (mediationRewardedAd == null) {
            return false;
        }
        try {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzbzt.zzh("", th);
            return true;
        }
    }

    public final Bundle zzv(zzl zzlVar) {
        Bundle bundle;
        Bundle bundle2 = zzlVar.zzm;
        return (bundle2 == null || (bundle = bundle2.getBundle(this.zza.getClass().getName())) == null) ? new Bundle() : bundle;
    }
}
