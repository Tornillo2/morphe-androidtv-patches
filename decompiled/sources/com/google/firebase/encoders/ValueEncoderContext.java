package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface ValueEncoderContext {
    @NonNull
    ValueEncoderContext add(double d) throws IOException;

    @NonNull
    ValueEncoderContext add(float f) throws IOException;

    @NonNull
    ValueEncoderContext add(int i) throws IOException;

    @NonNull
    ValueEncoderContext add(long j) throws IOException;

    @NonNull
    ValueEncoderContext add(@Nullable String str) throws IOException;

    @NonNull
    ValueEncoderContext add(boolean z) throws IOException;

    @NonNull
    ValueEncoderContext add(@NonNull byte[] bArr) throws IOException;
}
