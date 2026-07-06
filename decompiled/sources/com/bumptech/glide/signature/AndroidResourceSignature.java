package com.bumptech.glide.signature;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AndroidResourceSignature implements Key {
    public final Key applicationVersion;
    public final int nightMode;

    public AndroidResourceSignature(int i, Key key) {
        this.nightMode = i;
        this.applicationVersion = key;
    }

    @NonNull
    public static Key obtain(@NonNull Context context) {
        return new AndroidResourceSignature(context.getResources().getConfiguration().uiMode & 48, ApplicationVersionSignature.obtain(context));
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof AndroidResourceSignature) {
            AndroidResourceSignature androidResourceSignature = (AndroidResourceSignature) obj;
            if (this.nightMode == androidResourceSignature.nightMode && this.applicationVersion.equals(androidResourceSignature.applicationVersion)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return Util.hashCode(this.applicationVersion, this.nightMode);
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.applicationVersion.updateDiskCacheKey(messageDigest);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.nightMode).array());
    }
}
