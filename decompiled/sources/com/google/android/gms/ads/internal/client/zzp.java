package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbzm;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzp {
    public static final zzp zza = new zzp();

    @VisibleForTesting
    public zzp() {
    }

    public final zzl zza(Context context, zzdx zzdxVar) {
        String strZzq;
        Date date = zzdxVar.zza;
        long time = date != null ? date.getTime() : -1L;
        String str = zzdxVar.zzb;
        int i = zzdxVar.zzd;
        Set set = zzdxVar.zze;
        List listUnmodifiableList = !set.isEmpty() ? DesugarCollections.unmodifiableList(new ArrayList(set)) : null;
        boolean zZzs = zzdxVar.zzs(context);
        Bundle bundle = zzdxVar.zzf.getBundle(AdMobAdapter.class.getName());
        String str2 = zzdxVar.zzh;
        SearchAdRequest searchAdRequest = zzdxVar.zzj;
        zzfh zzfhVar = searchAdRequest != null ? new zzfh(searchAdRequest) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzbzm zzbzmVar = zzay.zza.zzb;
            strZzq = zzbzm.zzq(Thread.currentThread().getStackTrace(), packageName);
        } else {
            strZzq = null;
        }
        boolean z = zzdxVar.zzo;
        RequestConfiguration requestConfiguration = zzej.zzf().zzi;
        return new zzl(8, time, bundle, i, listUnmodifiableList, zZzs, Math.max(zzdxVar.zzk, requestConfiguration.getTagForChildDirectedTreatment()), false, str2, zzfhVar, null, str, zzdxVar.zzf, zzdxVar.zzm, DesugarCollections.unmodifiableList(new ArrayList(zzdxVar.zzn)), zzdxVar.zzi, strZzq, z, null, requestConfiguration.getTagForUnderAgeOfConsent(), (String) Collections.max(Arrays.asList(null, requestConfiguration.getMaxAdContentRating()), zzo.zza), zzdxVar.zzo(), zzdxVar.zzq, zzdxVar.zzp);
    }
}
