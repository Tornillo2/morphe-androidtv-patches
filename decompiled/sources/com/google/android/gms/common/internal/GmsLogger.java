package com.google.android.gms.common.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class GmsLogger {
    public final String zza;

    @Nullable
    public final String zzb;

    @KeepForSdk
    public GmsLogger(@NonNull String str) {
        this(str, null);
    }

    @KeepForSdk
    public boolean canLog(int i) {
        return Log.isLoggable(this.zza, i);
    }

    @KeepForSdk
    public boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2) {
        if (Log.isLoggable(this.zza, 3)) {
            Log.d(str, zza(str2));
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2) {
        if (Log.isLoggable(this.zza, 6)) {
            Log.e(str, zza(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void efmt(@NonNull String str, @NonNull @FormatString String str2, @NonNull Object... objArr) {
        if (Log.isLoggable(this.zza, 6)) {
            Log.e(str, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2) {
        if (Log.isLoggable(this.zza, 4)) {
            Log.i(str, zza(str2));
        }
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2) {
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2) {
        if (Log.isLoggable(this.zza, 2)) {
            Log.v(str, zza(str2));
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2) {
        if (Log.isLoggable(this.zza, 5)) {
            Log.w(str, zza(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void wfmt(@NonNull String str, @NonNull @FormatString String str2, @NonNull Object... objArr) {
        if (Log.isLoggable(this.zza, 5)) {
            Log.w(this.zza, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    public void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 7)) {
            Log.e(str, zza(str2), th);
            Log.wtf(str, zza(str2), th);
        }
    }

    public final String zza(String str) {
        String str2 = this.zzb;
        return str2 == null ? str : str2.concat(str);
    }

    @FormatMethod
    public final String zzb(String str, Object... objArr) {
        String str2 = this.zzb;
        String str3 = String.format(str, objArr);
        return str2 == null ? str3 : str2.concat(str3);
    }

    @KeepForSdk
    public GmsLogger(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zza = str;
        this.zzb = (str2 == null || str2.length() <= 0) ? null : str2;
    }

    @KeepForSdk
    public void pii(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
    }

    @KeepForSdk
    public void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 3)) {
            Log.d(str, zza(str2), th);
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 6)) {
            Log.e(str, zza(str2), th);
        }
    }

    @KeepForSdk
    public void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 4)) {
            Log.i(str, zza(str2), th);
        }
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 2)) {
            Log.v(str, zza(str2), th);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        if (Log.isLoggable(this.zza, 5)) {
            Log.w(str, zza(str2), th);
        }
    }
}
