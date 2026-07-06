package com.amazon.livingroom.mediapipelinebackend;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AvCodecTypeHelper {
    public static <T> T findByIndex(Class<T> cls, int i) {
        try {
            return cls.getEnumConstants()[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unknown codec type with id=", i), e);
        }
    }
}
