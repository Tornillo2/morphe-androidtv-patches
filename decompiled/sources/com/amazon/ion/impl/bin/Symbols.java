package com.amazon.ion.impl.bin;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import com.amazon.ion.IonException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Symbols {
    public static SymbolTable SYSTEM_SYMBOL_TABLE;
    public static final List<SymbolToken> SYSTEM_TOKENS;
    public static final Map<String, SymbolToken> SYSTEM_TOKEN_MAP;

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.Symbols$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass2 implements Iterator<String> {
        public final /* synthetic */ Iterator val$tokenIter;

        public AnonymousClass2(Iterator it) {
            this.val$tokenIter = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.val$tokenIter.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public String next() {
            return ((SymbolToken) this.val$tokenIter.next()).getText();
        }
    }

    static {
        List<SymbolToken> listUnmodifiableList = DesugarCollections.unmodifiableList(Arrays.asList(symbol(SystemSymbols.ION, 1), symbol(SystemSymbols.ION_1_0, 2), symbol(SystemSymbols.ION_SYMBOL_TABLE, 3), symbol("name", 4), symbol("version", 5), symbol(SystemSymbols.IMPORTS, 6), symbol(SystemSymbols.SYMBOLS, 7), symbol(SystemSymbols.MAX_ID, 8), symbol(SystemSymbols.ION_SHARED_SYMBOL_TABLE, 9)));
        SYSTEM_TOKENS = listUnmodifiableList;
        HashMap map = new HashMap();
        for (SymbolToken symbolToken : listUnmodifiableList) {
            map.put(symbolToken.getText(), symbolToken);
        }
        SYSTEM_TOKEN_MAP = DesugarCollections.unmodifiableMap(map);
        SYSTEM_SYMBOL_TABLE = new AnonymousClass3(SystemSymbols.ION, 1);
    }

    public static SymbolToken symbol(final String str, final int i) {
        str.getClass();
        if (i > 0) {
            return new SymbolToken() { // from class: com.amazon.ion.impl.bin.Symbols.1
                @Override // com.amazon.ion.SymbolToken
                public String assumeText() {
                    return str;
                }

                @Override // com.amazon.ion.SymbolToken
                public int getSid() {
                    return i;
                }

                @Override // com.amazon.ion.SymbolToken
                public String getText() {
                    return str;
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder("(symbol '");
                    sb.append(str);
                    sb.append("' ");
                    return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, i, ")");
                }
            };
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Symbol value must be positive: ", i));
    }

    public static Iterator<String> symbolNameIterator(Iterator<SymbolToken> it) {
        return new AnonymousClass2(it);
    }

    public static SymbolToken systemSymbol(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("No such system SID: ", i));
        }
        return SYSTEM_TOKENS.get(i - 1);
    }

    public static SymbolTable systemSymbolTable() {
        return SYSTEM_SYMBOL_TABLE;
    }

    public static Collection<SymbolToken> systemSymbols() {
        return SYSTEM_TOKENS;
    }

    public static SymbolTable unknownSharedSymbolTable(final String str, final int i, final int i2) {
        return new AbstractSymbolTable(str, i) { // from class: com.amazon.ion.impl.bin.Symbols.4
            @Override // com.amazon.ion.SymbolTable
            public SymbolToken find(String str2) {
                return null;
            }

            @Override // com.amazon.ion.SymbolTable
            public String findKnownSymbol(int i3) {
                return null;
            }

            @Override // com.amazon.ion.SymbolTable
            public int getImportedMaxId() {
                return 0;
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolTable[] getImportedTables() {
                return null;
            }

            @Override // com.amazon.ion.SymbolTable
            public int getMaxId() {
                return i2;
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolTable getSystemSymbolTable() {
                return Symbols.systemSymbolTable();
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolToken intern(String str2) {
                throw new UnsupportedOperationException("Cannot intern into substitute unknown shared symbol table: " + str + " version " + i);
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isLocalTable() {
                return false;
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isReadOnly() {
                return true;
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isSharedTable() {
                return true;
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isSubstitute() {
                return true;
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isSystemTable() {
                return false;
            }

            @Override // com.amazon.ion.SymbolTable
            public Iterator<String> iterateDeclaredSymbolNames() {
                return new Iterator<String>() { // from class: com.amazon.ion.impl.bin.Symbols.4.1
                    public int id = 1;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.id <= i2;
                    }

                    @Override // java.util.Iterator
                    public /* bridge */ /* synthetic */ String next() {
                        next2();
                        return null;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    @Override // java.util.Iterator
                    /* JADX INFO: renamed from: next, reason: avoid collision after fix types in other method */
                    public String next2() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        this.id++;
                        return null;
                    }
                };
            }
        };
    }

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin.Symbols$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass3 extends AbstractSymbolTable {
        public AnonymousClass3(String str, int i) {
            super(str, i);
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken find(String str) {
            return (SymbolToken) Symbols.SYSTEM_TOKEN_MAP.get(str);
        }

        @Override // com.amazon.ion.SymbolTable
        public String findKnownSymbol(int i) {
            if (i < 1) {
                throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("SID cannot be less than 1: ", i));
            }
            if (i > 9) {
                return null;
            }
            return ((SymbolToken) Symbols.SYSTEM_TOKENS.get(i - 1)).getText();
        }

        @Override // com.amazon.ion.SymbolTable
        public int getImportedMaxId() {
            return 0;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable[] getImportedTables() {
            return null;
        }

        @Override // com.amazon.ion.SymbolTable
        public int getMaxId() {
            return 9;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken intern(String str) {
            SymbolToken symbolToken = (SymbolToken) Symbols.SYSTEM_TOKEN_MAP.get(str);
            if (symbolToken != null) {
                return symbolToken;
            }
            throw new IonException("Cannot intern new symbol into system symbol table");
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isLocalTable() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isReadOnly() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSharedTable() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSubstitute() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSystemTable() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public Iterator<String> iterateDeclaredSymbolNames() {
            return new AnonymousClass2(Symbols.SYSTEM_TOKENS.iterator());
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable getSystemSymbolTable() {
            return this;
        }
    }
}
