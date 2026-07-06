package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzkm extends IOException {
    public zzkm(String str) {
        super(str);
    }

    public static zzkl zza() {
        return new zzkl("Protocol message tag had invalid wire type.");
    }

    public static zzkm zzb() {
        return new zzkm("Protocol message contained an invalid tag (zero).");
    }

    public static zzkm zzc() {
        return new zzkm("Protocol message had invalid UTF-8.");
    }

    public static zzkm zzd() {
        return new zzkm("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzkm zze() {
        return new zzkm("Failed to parse the message.");
    }

    public static zzkm zzf() {
        return new zzkm("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
