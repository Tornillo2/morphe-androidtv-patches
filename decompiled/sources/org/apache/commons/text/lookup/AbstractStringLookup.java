package org.apache.commons.text.lookup;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractStringLookup implements StringLookup {
    public static final String EMPTY = "";
    public static final char SPLIT_CH = ':';
    public static final String SPLIT_STR = String.valueOf(':');

    public String substringAfter(String str, char c) {
        int iIndexOf = str.indexOf(c);
        return iIndexOf > -1 ? str.substring(iIndexOf + 1) : "";
    }

    public String substringAfterLast(String str, char c) {
        int iLastIndexOf = str.lastIndexOf(c);
        return iLastIndexOf > -1 ? str.substring(iLastIndexOf + 1) : "";
    }

    public String substringAfter(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        return iIndexOf > -1 ? str.substring(str2.length() + iIndexOf) : "";
    }
}
