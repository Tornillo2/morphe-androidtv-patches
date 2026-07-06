package org.apache.commons.compress.compressors.xz;

import java.util.HashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class XZUtils {
    public static final FileNameUtil fileNameUtil;

    static {
        HashMap map = new HashMap();
        map.put(".txz", ".tar");
        map.put(".xz", "");
        map.put("-xz", "");
        fileNameUtil = new FileNameUtil(map, ".xz");
    }

    public static String getCompressedFilename(String str) {
        return fileNameUtil.getCompressedFilename(str);
    }

    public static String getUncompressedFilename(String str) {
        return fileNameUtil.getUncompressedFilename(str);
    }

    public static boolean isCompressedFilename(String str) {
        return fileNameUtil.isCompressedFilename(str);
    }

    public static boolean isXZCompressionAvailable() {
        try {
            XZCompressorInputStream.matches(null, 0);
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }
}
