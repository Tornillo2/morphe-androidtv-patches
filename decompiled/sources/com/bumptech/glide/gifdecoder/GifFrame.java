package com.bumptech.glide.gifdecoder;

import androidx.annotation.ColorInt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class GifFrame {
    public static final int DISPOSAL_BACKGROUND = 2;
    public static final int DISPOSAL_NONE = 1;
    public static final int DISPOSAL_PREVIOUS = 3;
    public static final int DISPOSAL_UNSPECIFIED = 0;
    public int bufferFrameStart;
    public int delay;
    public int dispose;
    public int ih;
    public boolean interlace;
    public int iw;
    public int ix;
    public int iy;

    @ColorInt
    public int[] lct;
    public int transIndex;
    public boolean transparency;
}
