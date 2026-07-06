package com.google.android.gms.common.images;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zad {
    public final Uri zaa;

    public zad(Uri uri) {
        this.zaa = uri;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zad) {
            return Objects.equal(((zad) obj).zaa, this.zaa);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zaa});
    }
}
