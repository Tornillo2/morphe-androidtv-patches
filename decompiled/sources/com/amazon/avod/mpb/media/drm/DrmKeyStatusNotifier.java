package com.amazon.avod.mpb.media.drm;

import android.media.MediaDrm;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DrmKeyStatusNotifier {
    void onKeyStatusChange(@NotNull byte[] bArr, @NotNull List<MediaDrm.KeyStatus> list);

    void removeSession(@NotNull byte[] bArr);

    void setKeyStatusChangeCallback(@NotNull Function1<? super Boolean, Unit> function1);
}
