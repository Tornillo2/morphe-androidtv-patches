package com.amazon.ion.util;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_FastAppendable extends Appendable {
    void appendAscii(char c) throws IOException;

    void appendAscii(CharSequence charSequence) throws IOException;

    void appendAscii(CharSequence charSequence, int i, int i2) throws IOException;

    void appendUtf16(char c) throws IOException;

    void appendUtf16Surrogate(char c, char c2) throws IOException;
}
