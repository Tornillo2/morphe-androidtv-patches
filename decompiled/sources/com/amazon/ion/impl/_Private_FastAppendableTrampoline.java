package com.amazon.ion.impl;

import com.amazon.ion.util._Private_FastAppendable;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_FastAppendableTrampoline {
    public static _Private_FastAppendable forAppendable(Appendable appendable) {
        return new AppendableFastAppendable(appendable);
    }

    public static _Private_FastAppendable forOutputStream(OutputStream outputStream) {
        return new OutputStreamFastAppendable(outputStream);
    }
}
