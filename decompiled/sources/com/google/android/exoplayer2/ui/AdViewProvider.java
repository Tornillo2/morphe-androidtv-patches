package com.google.android.exoplayer2.ui;

import android.view.ViewGroup;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface AdViewProvider {

    /* JADX INFO: renamed from: com.google.android.exoplayer2.ui.AdViewProvider$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    List<AdOverlayInfo> getAdOverlayInfos();

    @Nullable
    ViewGroup getAdViewGroup();
}
