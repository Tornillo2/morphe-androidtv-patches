package com.amazon.ion.util;

import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.impl._Private_IonConstants;
import com.amazon.ion.impl._Private_ListWriter;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonStreamUtils {
    public static boolean cookieMatches(byte[] bArr, byte[] bArr2, int i, int i2) {
        if (bArr2 == null || i2 < bArr.length) {
            return false;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] != bArr2[i + i3]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGzip(byte[] bArr, int i, int i2) {
        return cookieMatches(GzipOrRawInputStream.GZIP_HEADER, bArr, i, i2);
    }

    public static boolean isIonBinary(byte[] bArr) {
        if (bArr != null) {
            if (cookieMatches(_Private_IonConstants.BINARY_VERSION_MARKER_1_0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public static InputStream unGzip(InputStream inputStream) throws IOException {
        return new GzipOrRawInputStream(inputStream);
    }

    public static void writeBoolList(IonWriter ionWriter, boolean[] zArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeBoolList(zArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (boolean z : zArr) {
            ionWriter.writeBool(z);
        }
        ionWriter.stepOut();
    }

    public static void writeFloatList(IonWriter ionWriter, float[] fArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeFloatList(fArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (float f : fArr) {
            ionWriter.writeFloat(f);
        }
        ionWriter.stepOut();
    }

    public static void writeIntList(IonWriter ionWriter, byte[] bArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeIntList(bArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (byte b : bArr) {
            ionWriter.writeInt(b);
        }
        ionWriter.stepOut();
    }

    public static void writeStringList(IonWriter ionWriter, String[] strArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeStringList(strArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (String str : strArr) {
            ionWriter.writeString(str);
        }
        ionWriter.stepOut();
    }

    public static boolean isIonBinary(byte[] bArr, int i, int i2) {
        return cookieMatches(_Private_IonConstants.BINARY_VERSION_MARKER_1_0, bArr, i, i2);
    }

    public static void writeFloatList(IonWriter ionWriter, double[] dArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeFloatList(dArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (double d : dArr) {
            ionWriter.writeFloat(d);
        }
        ionWriter.stepOut();
    }

    public static void writeIntList(IonWriter ionWriter, short[] sArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeIntList(sArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (short s : sArr) {
            ionWriter.writeInt(s);
        }
        ionWriter.stepOut();
    }

    public static void writeIntList(IonWriter ionWriter, int[] iArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeIntList(iArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (int i : iArr) {
            ionWriter.writeInt(i);
        }
        ionWriter.stepOut();
    }

    public static void writeIntList(IonWriter ionWriter, long[] jArr) throws IOException {
        if (ionWriter instanceof _Private_ListWriter) {
            ((_Private_ListWriter) ionWriter).writeIntList(jArr);
            return;
        }
        ionWriter.stepIn(IonType.LIST);
        for (long j : jArr) {
            ionWriter.writeInt(j);
        }
        ionWriter.stepOut();
    }
}
