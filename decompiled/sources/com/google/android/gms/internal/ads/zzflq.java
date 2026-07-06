package com.google.android.gms.internal.ads;

import android.net.Network;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzflq extends zzfle {
    public zzfpp<Integer> zza;
    public zzfpp<Integer> zzb;

    @Nullable
    public zzflp zzc;

    @Nullable
    public HttpURLConnection zzd;

    public zzflq(zzfpp<Integer> zzfppVar, zzfpp<Integer> zzfppVar2, @Nullable zzflp zzflpVar) {
        this.zza = zzfppVar;
        this.zzb = zzfppVar2;
        this.zzc = zzflpVar;
    }

    public static /* synthetic */ Integer zzf() {
        return -1;
    }

    public static /* synthetic */ Integer zzg() {
        return -1;
    }

    public static void zzs(@Nullable HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        zzs(this.zzd);
    }

    public HttpURLConnection zzm() throws IOException {
        ((Integer) this.zza.zza()).getClass();
        ((Integer) this.zzb.zza()).getClass();
        zzflp zzflpVar = this.zzc;
        zzflpVar.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzflpVar.zza();
        this.zzd = httpURLConnection;
        return httpURLConnection;
    }

    public HttpURLConnection zzn(zzflp zzflpVar, final int i, final int i2) throws IOException {
        this.zza = new zzfpp() { // from class: com.google.android.gms.internal.ads.zzflg
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i);
            }
        };
        this.zzb = new zzfpp() { // from class: com.google.android.gms.internal.ads.zzflh
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i2);
            }
        };
        this.zzc = zzflpVar;
        return zzm();
    }

    @RequiresApi(21)
    public HttpURLConnection zzo(@NonNull final Network network, @NonNull final URL url, final int i, final int i2) throws IOException {
        this.zza = new zzfpp() { // from class: com.google.android.gms.internal.ads.zzfli
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i);
            }
        };
        this.zzb = new zzfpp() { // from class: com.google.android.gms.internal.ads.zzflj
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i2);
            }
        };
        this.zzc = new zzflp() { // from class: com.google.android.gms.internal.ads.zzflk
            @Override // com.google.android.gms.internal.ads.zzflp
            public final URLConnection zza() {
                return network.openConnection(url);
            }
        };
        return zzm();
    }

    public URLConnection zzr(@NonNull final URL url, final int i) throws IOException {
        this.zza = new zzfpp() { // from class: com.google.android.gms.internal.ads.zzfll
            @Override // com.google.android.gms.internal.ads.zzfpp
            public final Object zza() {
                return Integer.valueOf(i);
            }
        };
        this.zzc = new zzflp() { // from class: com.google.android.gms.internal.ads.zzflm
            @Override // com.google.android.gms.internal.ads.zzflp
            public final URLConnection zza() {
                return url.openConnection();
            }
        };
        return zzm();
    }

    public zzflq() {
        this(zzfln.zza, zzflo.zza, null);
    }
}
