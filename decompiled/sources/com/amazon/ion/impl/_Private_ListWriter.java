package com.amazon.ion.impl;

import com.amazon.ion.IonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface _Private_ListWriter extends IonWriter {
    void writeBoolList(boolean[] zArr) throws IOException;

    void writeFloatList(double[] dArr) throws IOException;

    void writeFloatList(float[] fArr) throws IOException;

    void writeIntList(byte[] bArr) throws IOException;

    void writeIntList(int[] iArr) throws IOException;

    void writeIntList(long[] jArr) throws IOException;

    void writeIntList(short[] sArr) throws IOException;

    void writeStringList(String[] strArr) throws IOException;
}
