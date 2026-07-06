package com.google.android.gms.common.images;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Size {
    public final int zaa;
    public final int zab;

    public Size(int i, int i2) {
        this.zaa = i;
        this.zab = i2;
    }

    @NonNull
    public static Size parseSize(@NonNull String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            zaa(str);
            throw null;
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            zaa(str);
            throw null;
        }
    }

    public static NumberFormatException zaa(String str) {
        throw new NumberFormatException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Invalid Size: \"", str, ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zaa == size.zaa && this.zab == size.zab) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.zab;
    }

    public int getWidth() {
        return this.zaa;
    }

    public int hashCode() {
        int i = this.zaa;
        return ((i >>> 16) | (i << 16)) ^ this.zab;
    }

    @NonNull
    public String toString() {
        return this.zaa + "x" + this.zab;
    }
}
