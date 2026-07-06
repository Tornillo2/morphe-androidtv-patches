package org.apache.commons.text.lookup;

import java.nio.file.Files;
import java.nio.file.Paths;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FileStringLookup extends AbstractStringLookup {
    public static final AbstractStringLookup INSTANCE = new FileStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(String.valueOf(':'));
        if (strArrSplit.length < 2) {
            throw IllegalArgumentExceptions.format("Bad file key format [%s], expected format is CharsetName:DocumentPath.", str);
        }
        String str2 = strArrSplit[0];
        String strSubstringAfter = substringAfter(str, ':');
        try {
            return new String(Files.readAllBytes(Paths.get(strSubstringAfter, new String[0])), str2);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up file [%s] with charset [%s].", strSubstringAfter, str2);
        }
    }
}
