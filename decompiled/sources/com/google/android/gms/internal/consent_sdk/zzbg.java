package com.google.android.gms.internal.consent_sdk;

import android.os.Handler;
import android.webkit.WebView;
import androidx.annotation.UiThread;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@UiThread
public final class zzbg extends WebView {
    public final Handler zza;
    public final zzbm zzb;
    public boolean zzc;

    public zzbg(zzbi zzbiVar, Handler handler, zzbm zzbmVar) {
        super(zzbiVar);
        this.zzc = false;
        this.zza = handler;
        this.zzb = zzbmVar;
    }

    public static /* synthetic */ boolean zzc(zzbg zzbgVar, String str) {
        return str != null && str.startsWith("consent://");
    }

    public static /* synthetic */ boolean zze(zzbg zzbgVar, boolean z) {
        zzbgVar.zzc = true;
        return true;
    }

    public final void zzb(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        final String string = sb.toString();
        this.zza.post(new Runnable() { // from class: com.google.android.gms.internal.consent_sdk.zzbd
            @Override // java.lang.Runnable
            public final void run() {
                zzce.zza(this.zza, string);
            }
        });
    }
}
