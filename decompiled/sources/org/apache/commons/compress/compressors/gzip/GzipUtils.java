package org.apache.commons.compress.compressors.gzip;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class GzipUtils {
    public static final FileNameUtil fileNameUtil;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(".tgz", ".tar");
        linkedHashMap.put(".taz", ".tar");
        linkedHashMap.put(".svgz", ".svg");
        linkedHashMap.put(".cpgz", ".cpio");
        linkedHashMap.put(".wmz", ".wmf");
        linkedHashMap.put(".emz", ".emf");
        linkedHashMap.put(".gz", "");
        linkedHashMap.put(".z", "");
        linkedHashMap.put("-gz", "");
        linkedHashMap.put("-z", "");
        linkedHashMap.put("_z", "");
        fileNameUtil = new FileNameUtil(linkedHashMap, ".gz");
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
}
