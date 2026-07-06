package com.amazon.avod.mpb.media.drm;

import android.media.MediaDrm;
import android.os.Build;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidDrmKeyRequest {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int DEFAULT_REQUEST_TYPE = 0;

    @NotNull
    public final MediaDrm.KeyRequest mediaDrmKeyRequest;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public AndroidDrmKeyRequest(@NotNull MediaDrm.KeyRequest mediaDrmKeyRequest) {
        Intrinsics.checkNotNullParameter(mediaDrmKeyRequest, "mediaDrmKeyRequest");
        this.mediaDrmKeyRequest = mediaDrmKeyRequest;
    }

    @NotNull
    public final byte[] getData() {
        byte[] data = this.mediaDrmKeyRequest.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        return data;
    }

    public final int getRequestType() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.mediaDrmKeyRequest.getRequestType();
        }
        return 0;
    }
}
