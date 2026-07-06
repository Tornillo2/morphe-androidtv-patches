package com.google.android.gms.common;

import android.content.Intent;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class UserRecoverableException extends Exception {
    public final Intent zza;

    public UserRecoverableException(@NonNull String str, @NonNull Intent intent) {
        super(str);
        this.zza = intent;
    }

    @NonNull
    public Intent getIntent() {
        return new Intent(this.zza);
    }
}
