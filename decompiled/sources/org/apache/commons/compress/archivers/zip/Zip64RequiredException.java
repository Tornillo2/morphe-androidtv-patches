package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Zip64RequiredException extends ZipException {
    public static final String ARCHIVE_TOO_BIG_MESSAGE = "archive's size exceeds the limit of 4GByte.";
    public static final String TOO_MANY_ENTRIES_MESSAGE = "archive contains more than 65535 entries.";
    public static final long serialVersionUID = 20110809;

    public Zip64RequiredException(String str) {
        super(str);
    }

    public static String getEntryTooBigMessage(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getName() + "'s size exceeds the limit of 4GByte.";
    }
}
