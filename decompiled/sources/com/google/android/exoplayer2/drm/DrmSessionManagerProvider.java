package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.MediaItem;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface DrmSessionManagerProvider {
    DrmSessionManager get(MediaItem mediaItem);
}
