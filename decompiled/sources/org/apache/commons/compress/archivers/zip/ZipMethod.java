package org.apache.commons.compress.archivers.zip;

import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public enum ZipMethod {
    STORED(0),
    UNSHRINKING(1),
    EXPANDING_LEVEL_1(2),
    EXPANDING_LEVEL_2(3),
    EXPANDING_LEVEL_3(4),
    EXPANDING_LEVEL_4(5),
    IMPLODING(6),
    TOKENIZATION(7),
    DEFLATED(8),
    ENHANCED_DEFLATED(9),
    PKWARE_IMPLODING(10),
    BZIP2(12),
    LZMA(14),
    JPEG(96),
    WAVPACK(97),
    PPMD(98),
    AES_ENCRYPTED(99),
    UNKNOWN(-1);

    public static final Map<Integer, ZipMethod> codeToEnum;
    public final int code;

    static {
        HashMap map = new HashMap();
        for (ZipMethod zipMethod : values()) {
            map.put(Integer.valueOf(zipMethod.code), zipMethod);
        }
        codeToEnum = DesugarCollections.unmodifiableMap(map);
    }

    ZipMethod(int i) {
        this.code = i;
    }

    public static ZipMethod getMethodByCode(int i) {
        return codeToEnum.get(Integer.valueOf(i));
    }

    public int getCode() {
        return this.code;
    }
}
