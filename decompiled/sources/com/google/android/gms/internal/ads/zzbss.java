package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.query.UpdateClickUrlCallback;
import com.google.android.gms.ads.query.UpdateImpressionUrlsCallback;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbss {

    @Nonnull
    public final View zza;

    @Nullable
    public final Map zzb;

    @Nullable
    public final zzbyk zzc;

    public zzbss(zzbsr zzbsrVar) {
        View view = zzbsrVar.zza;
        this.zza = view;
        Map map = zzbsrVar.zzb;
        this.zzb = map;
        zzbyk zzbykVarZza = zzbsm.zza(view.getContext());
        this.zzc = zzbykVarZza;
        if (zzbykVarZza == null || map.isEmpty()) {
            return;
        }
        try {
            zzbykVarZza.zzf(new zzbst((zzb) ObjectWrapper.wrap(view), new ObjectWrapper(map)));
        } catch (RemoteException unused) {
            zzbzt.zzg("Failed to call remote method.");
        }
    }

    public final void zza(List list) {
        if (list == null || list.isEmpty()) {
            zzbzt.zzj("No click urls were passed to recordClick");
            return;
        }
        if (this.zzc == null) {
            zzbzt.zzj("Failed to get internal reporting info generator in recordClick.");
        }
        try {
            this.zzc.zzg(list, ObjectWrapper.wrap(this.zza), new zzbsq(this, list));
        } catch (RemoteException e) {
            zzbzt.zzg("RemoteException recording click: ".concat(e.toString()));
        }
    }

    public final void zzb(List list) {
        if (list == null || list.isEmpty()) {
            zzbzt.zzj("No impression urls were passed to recordImpression");
            return;
        }
        zzbyk zzbykVar = this.zzc;
        if (zzbykVar == null) {
            zzbzt.zzj("Failed to get internal reporting info generator from recordImpression.");
            return;
        }
        try {
            zzbykVar.zzh(list, ObjectWrapper.wrap(this.zza), new zzbsp(this, list));
        } catch (RemoteException e) {
            zzbzt.zzg("RemoteException recording impression urls: ".concat(e.toString()));
        }
    }

    public final void zzc(MotionEvent motionEvent) {
        zzbyk zzbykVar = this.zzc;
        if (zzbykVar == null) {
            zzbzt.zze("Failed to get internal reporting info generator.");
            return;
        }
        try {
            zzbykVar.zzj(ObjectWrapper.wrap(motionEvent));
        } catch (RemoteException unused) {
            zzbzt.zzg("Failed to call remote method.");
        }
    }

    public final void zzd(Uri uri, UpdateClickUrlCallback updateClickUrlCallback) {
        if (this.zzc == null) {
            updateClickUrlCallback.getClass();
        }
        try {
            this.zzc.zzk(new ArrayList(Arrays.asList(uri)), ObjectWrapper.wrap(this.zza), new zzbso(this, updateClickUrlCallback));
        } catch (RemoteException e) {
            "Internal error: ".concat(e.toString());
            updateClickUrlCallback.getClass();
        }
    }

    public final void zze(List list, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        if (this.zzc == null) {
            updateImpressionUrlsCallback.getClass();
        }
        try {
            this.zzc.zzl(list, ObjectWrapper.wrap(this.zza), new zzbsn(this, updateImpressionUrlsCallback));
        } catch (RemoteException e) {
            "Internal error: ".concat(e.toString());
            updateImpressionUrlsCallback.getClass();
        }
    }
}
