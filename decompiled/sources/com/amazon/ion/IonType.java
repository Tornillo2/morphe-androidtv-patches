package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum IonType {
    NULL,
    BOOL,
    INT,
    FLOAT,
    DECIMAL,
    TIMESTAMP,
    SYMBOL,
    STRING,
    CLOB,
    BLOB,
    LIST,
    SEXP,
    STRUCT,
    DATAGRAM;

    public static boolean isContainer(IonType ionType) {
        return ionType != null && ionType.ordinal() >= LIST.ordinal();
    }

    public static boolean isLob(IonType ionType) {
        return ionType == BLOB || ionType == CLOB;
    }

    public static boolean isText(IonType ionType) {
        return ionType == STRING || ionType == SYMBOL;
    }
}
