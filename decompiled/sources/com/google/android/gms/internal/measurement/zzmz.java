package com.google.android.gms.internal.measurement;

import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzmz extends IllegalArgumentException {
    public zzmz(int i, int i2) {
        super(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Unpaired surrogate at index ", i, " of ", i2));
    }
}
