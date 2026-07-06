package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaDrm;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DrmKeyRequest {
    public static final int DEFAULT_REQUEST_TYPE = 0;
    public final MediaDrm.KeyRequest mediaDrmKeyRequest;

    public DrmKeyRequest(@NonNull MediaDrm.KeyRequest keyRequest) {
        this.mediaDrmKeyRequest = keyRequest;
    }

    @NonNull
    @CalledFromNative
    public byte[] getData() {
        return this.mediaDrmKeyRequest.getData();
    }

    @CalledFromNative
    public int getRequestType() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.mediaDrmKeyRequest.getRequestType();
        }
        return 0;
    }
}
