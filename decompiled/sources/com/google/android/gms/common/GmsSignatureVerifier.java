package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*javatests/com/google/android/gmscore/integ/client/common/robolectric/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
public class GmsSignatureVerifier {
    public static final zzab zza;
    public static final zzab zzb;
    public static final HashMap zzc;

    static {
        zzz zzzVar = new zzz();
        zzzVar.zza = "com.google.android.gms";
        zzzVar.zzb = 204200000L;
        zzl zzlVar = zzn.zzd;
        zzzVar.zzc(zzag.zzn(zzlVar.zzf(), zzn.zzb.zzf()));
        zzl zzlVar2 = zzn.zzc;
        zzzVar.zzb(zzag.zzn(zzlVar2.zzf(), zzn.zza.zzf()));
        zza = zzzVar.zze();
        zzz zzzVar2 = new zzz();
        zzzVar2.zza = "com.android.vending";
        zzzVar2.zzb = 82240000L;
        zzzVar2.zzc(zzag.zzm(zzlVar.zzf()));
        zzzVar2.zzb(zzag.zzm(zzlVar2.zzf()));
        zzb = zzzVar2.zze();
        zzc = new HashMap();
    }
}
