package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaDrm;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DrmKeyStatusNotifierNotSupported implements DrmKeyStatusNotifier {
    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void onKeyStatusChange(@NotNull byte[] sessionId, @NotNull List<MediaDrm.KeyStatus> keyInformation) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(keyInformation, "keyInformation");
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void removeSession(@NotNull byte[] sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void setKeyStatusChangeCallback(@NotNull Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
    }
}
