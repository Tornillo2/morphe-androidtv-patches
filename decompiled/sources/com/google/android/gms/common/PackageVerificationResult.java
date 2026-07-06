package com.google.android.gms.common;

import androidx.annotation.NonNull;
import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@CheckReturnValue
public class PackageVerificationResult {
    public final String zza;
    public final boolean zzb;

    @Nullable
    public final String zzc;

    @Nullable
    public final Throwable zzd;

    public PackageVerificationResult(String str, int i, boolean z, @Nullable String str2, @Nullable Throwable th) {
        this.zza = str;
        this.zzb = z;
        this.zzc = str2;
        this.zzd = th;
    }

    @NonNull
    public static PackageVerificationResult zza(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        return new PackageVerificationResult(str, 1, false, str2, th);
    }

    @NonNull
    public static PackageVerificationResult zzd(@NonNull String str, int i) {
        return new PackageVerificationResult(str, i, true, null, null);
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        String str = this.zzc;
        Throwable th = this.zzd;
        String strConcat = "PackageVerificationRslt: ".concat(String.valueOf(str));
        if (th == null) {
            throw new SecurityException(strConcat);
        }
        throw new SecurityException(strConcat, th);
    }

    public final boolean zzc() {
        return this.zzb;
    }
}
