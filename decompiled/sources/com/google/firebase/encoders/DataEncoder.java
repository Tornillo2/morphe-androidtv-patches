package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface DataEncoder {
    @NonNull
    String encode(@NonNull Object obj);

    void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException;
}
