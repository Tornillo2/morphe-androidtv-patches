package com.amazon.ion;

import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface SymbolTable {
    public static final int UNKNOWN_SYMBOL_ID = -1;

    SymbolToken find(String str);

    String findKnownSymbol(int i);

    int findSymbol(String str);

    int getImportedMaxId();

    SymbolTable[] getImportedTables();

    String getIonVersionId();

    int getMaxId();

    String getName();

    SymbolTable getSystemSymbolTable();

    int getVersion();

    SymbolToken intern(String str);

    boolean isLocalTable();

    boolean isReadOnly();

    boolean isSharedTable();

    boolean isSubstitute();

    boolean isSystemTable();

    Iterator<String> iterateDeclaredSymbolNames();

    void makeReadOnly();

    void writeTo(IonWriter ionWriter) throws IOException;
}
