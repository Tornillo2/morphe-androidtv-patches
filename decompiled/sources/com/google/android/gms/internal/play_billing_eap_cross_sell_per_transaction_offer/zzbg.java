package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbg {
    public final zzbj zza;
    public boolean zzb;
    public long zzc;
    public long zzd;

    public zzbg() {
        this.zza = zzbj.zza;
    }

    public static zzbg zzb(zzbj zzbjVar) {
        zzbg zzbgVar = new zzbg(zzbjVar);
        zzbgVar.zze();
        return zzbgVar;
    }

    public static zzbg zzc(zzbj zzbjVar) {
        return new zzbg(zzbjVar);
    }

    public final String toString() {
        String str;
        long jZzh = zzh();
        TimeUnit timeUnit = TimeUnit.DAYS;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
            timeUnit = TimeUnit.HOURS;
            if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
                timeUnit = TimeUnit.MINUTES;
                if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
                    timeUnit = TimeUnit.SECONDS;
                    if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
                        timeUnit = TimeUnit.MILLISECONDS;
                        if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
                            timeUnit = TimeUnit.MICROSECONDS;
                            if (timeUnit.convert(jZzh, timeUnit2) <= 0) {
                                timeUnit = timeUnit2;
                            }
                        }
                    }
                }
            }
        }
        String str2 = String.format(Locale.ROOT, "%.4g", Double.valueOf(jZzh / timeUnit2.convert(1L, timeUnit)));
        switch (zzbf.zza[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = CmcdData.Factory.STREAMING_FORMAT_SS;
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = CmcdData.Factory.STREAMING_FORMAT_HLS;
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str2, StringUtils.SPACE, str);
    }

    public final long zza(TimeUnit timeUnit) {
        return timeUnit.convert(zzh(), TimeUnit.NANOSECONDS);
    }

    public final zzbg zzd() {
        this.zzc = 0L;
        this.zzb = false;
        return this;
    }

    public final zzbg zze() {
        zzbe.zze(!this.zzb, "This stopwatch is already running.");
        this.zzb = true;
        this.zzd = this.zza.zza();
        return this;
    }

    public final zzbg zzf() {
        long jZza = this.zza.zza();
        zzbe.zze(this.zzb, "This stopwatch is already stopped.");
        this.zzb = false;
        this.zzc = (jZza - this.zzd) + this.zzc;
        return this;
    }

    public final boolean zzg() {
        return this.zzb;
    }

    public final long zzh() {
        return this.zzb ? (this.zza.zza() - this.zzd) + this.zzc : this.zzc;
    }

    public zzbg(zzbj zzbjVar) {
        zzbe.zzc(zzbjVar, "ticker");
        this.zza = zzbjVar;
    }
}
