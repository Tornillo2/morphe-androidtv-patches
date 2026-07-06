package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbnm extends zzbzy {
    public final zzbnl zza;

    public zzbnm(zzbnl zzbnlVar, @Nullable String str) {
        super(str);
        this.zza = zzbnlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbzy, com.google.android.gms.internal.ads.zzbzl
    @WorkerThread
    public final boolean zza(String str) {
        zzbzt.zze("LeibnizHttpUrlPinger pinging URL: ".concat(String.valueOf(str)));
        if ("oda".equals(Uri.parse(str).getScheme())) {
            return true;
        }
        zzbzt.zze("URL does not match oda:// scheme, falling back on HttpUrlPinger");
        return super.zza(str);
    }
}
