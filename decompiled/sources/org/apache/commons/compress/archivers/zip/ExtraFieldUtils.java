package org.apache.commons.compress.archivers.zip;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ExtraFieldUtils {
    public static final int WORD = 4;
    public static final Map<ZipShort, Class<?>> implementations = new ConcurrentHashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UnparseableExtraField {
        public static final int READ_KEY = 2;
        public static final int SKIP_KEY = 1;
        public static final int THROW_KEY = 0;
        public final int key;
        public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
        public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
        public static final UnparseableExtraField READ = new UnparseableExtraField(2);

        public UnparseableExtraField(int i) {
            this.key = i;
        }

        public int getKey() {
            return this.key;
        }
    }

    static {
        register(AsiExtraField.class);
        register(X5455_ExtendedTimestamp.class);
        register(X7875_NewUnix.class);
        register(JarMarker.class);
        register(UnicodePathExtraField.class);
        register(UnicodeCommentExtraField.class);
        register(Zip64ExtendedInformationExtraField.class);
    }

    public static ZipExtraField createExtraField(ZipShort zipShort) throws IllegalAccessException, InstantiationException {
        Class<?> cls = implementations.get(zipShort);
        if (cls != null) {
            return (ZipExtraField) cls.newInstance();
        }
        UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
        unrecognizedExtraField.headerId = zipShort;
        return unrecognizedExtraField;
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] zipExtraFieldArr) {
        boolean z = zipExtraFieldArr.length > 0 && (zipExtraFieldArr[zipExtraFieldArr.length - 1] instanceof UnparseableExtraFieldData);
        int length = zipExtraFieldArr.length;
        if (z) {
            length--;
        }
        int i = length * 4;
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            i += zipExtraField.getCentralDirectoryLength().value;
        }
        byte[] bArr = new byte[i];
        int length2 = 0;
        for (int i2 = 0; i2 < length; i2++) {
            System.arraycopy(zipExtraFieldArr[i2].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(zipExtraFieldArr[i2].getCentralDirectoryLength().getBytes(), 0, bArr, length2 + 2, 2);
            byte[] centralDirectoryData = zipExtraFieldArr[i2].getCentralDirectoryData();
            System.arraycopy(centralDirectoryData, 0, bArr, length2 + 4, centralDirectoryData.length);
            length2 += centralDirectoryData.length + 4;
        }
        if (z) {
            byte[] centralDirectoryData2 = zipExtraFieldArr[zipExtraFieldArr.length - 1].getCentralDirectoryData();
            System.arraycopy(centralDirectoryData2, 0, bArr, length2, centralDirectoryData2.length);
        }
        return bArr;
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] zipExtraFieldArr) {
        boolean z = zipExtraFieldArr.length > 0 && (zipExtraFieldArr[zipExtraFieldArr.length - 1] instanceof UnparseableExtraFieldData);
        int length = zipExtraFieldArr.length;
        if (z) {
            length--;
        }
        int i = length * 4;
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            i += zipExtraField.getLocalFileDataLength().value;
        }
        byte[] bArr = new byte[i];
        int length2 = 0;
        for (int i2 = 0; i2 < length; i2++) {
            System.arraycopy(zipExtraFieldArr[i2].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(zipExtraFieldArr[i2].getLocalFileDataLength().getBytes(), 0, bArr, length2 + 2, 2);
            byte[] localFileDataData = zipExtraFieldArr[i2].getLocalFileDataData();
            System.arraycopy(localFileDataData, 0, bArr, length2 + 4, localFileDataData.length);
            length2 += localFileDataData.length + 4;
        }
        if (z) {
            byte[] localFileDataData2 = zipExtraFieldArr[zipExtraFieldArr.length - 1].getLocalFileDataData();
            System.arraycopy(localFileDataData2, 0, bArr, length2, localFileDataData2.length);
        }
        return bArr;
    }

    public static ZipExtraField[] parse(byte[] bArr) throws ZipException {
        return parse(bArr, true, UnparseableExtraField.THROW);
    }

    public static void register(Class<?> cls) {
        try {
            implementations.put(((ZipExtraField) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException unused) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        } catch (InstantiationException unused3) {
            throw new RuntimeException(cls + " is not a concrete class");
        }
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z) throws ZipException {
        return parse(bArr, z, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z, UnparseableExtraField unparseableExtraField) throws ZipException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (i > bArr.length - 4) {
                break;
            }
            ZipShort zipShort = new ZipShort(bArr, i);
            int i2 = new ZipShort(bArr, i + 2).value;
            int i3 = i + 4;
            if (i3 + i2 > bArr.length) {
                int i4 = unparseableExtraField.key;
                if (i4 == 0) {
                    StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("bad extra field starting at ", i, ".  Block length of ", i2, " bytes exceeds remaining data of ");
                    sbM.append((bArr.length - i) - 4);
                    sbM.append(" bytes.");
                    throw new ZipException(sbM.toString());
                }
                if (i4 != 1) {
                    if (i4 == 2) {
                        UnparseableExtraFieldData unparseableExtraFieldData = new UnparseableExtraFieldData();
                        if (z) {
                            unparseableExtraFieldData.parseFromLocalFileData(bArr, i, bArr.length - i);
                        } else {
                            unparseableExtraFieldData.parseFromCentralDirectoryData(bArr, i, bArr.length - i);
                        }
                        arrayList.add(unparseableExtraFieldData);
                    } else {
                        throw new ZipException("unknown UnparseableExtraField key: " + unparseableExtraField.key);
                    }
                }
            } else {
                try {
                    ZipExtraField zipExtraFieldCreateExtraField = createExtraField(zipShort);
                    if (z) {
                        zipExtraFieldCreateExtraField.parseFromLocalFileData(bArr, i3, i2);
                    } else {
                        zipExtraFieldCreateExtraField.parseFromCentralDirectoryData(bArr, i3, i2);
                    }
                    arrayList.add(zipExtraFieldCreateExtraField);
                    i += i2 + 4;
                } catch (IllegalAccessException e) {
                    throw new ZipException(e.getMessage());
                } catch (InstantiationException e2) {
                    throw new ZipException(e2.getMessage());
                }
            }
        }
        return (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
    }
}
