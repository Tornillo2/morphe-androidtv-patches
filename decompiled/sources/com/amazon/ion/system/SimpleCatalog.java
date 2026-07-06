package com.amazon.ion.system;

import com.amazon.ion.IonMutableCatalog;
import com.amazon.ion.SymbolTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SimpleCatalog implements IonMutableCatalog, Iterable<SymbolTable> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public Map<String, TreeMap<Integer, SymbolTable>> myTablesByName = new HashMap();

    public static Integer bestMatch(int i, Iterable<Integer> iterable) {
        Integer num = null;
        int i2 = i;
        for (Integer num2 : iterable) {
            int iIntValue = num2.intValue();
            if (i < i2) {
                if (i < iIntValue && iIntValue < i2) {
                    num = num2;
                    i2 = iIntValue;
                }
            } else if (i2 >= i || i2 < iIntValue) {
                num = num2;
                i2 = iIntValue;
            }
        }
        return num;
    }

    @Override // com.amazon.ion.IonCatalog
    public SymbolTable getTable(String str) {
        TreeMap<Integer, SymbolTable> treeMap;
        SymbolTable symbolTable;
        if (str == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("name is empty");
        }
        synchronized (this.myTablesByName) {
            treeMap = this.myTablesByName.get(str);
        }
        if (treeMap == null) {
            return null;
        }
        synchronized (treeMap) {
            symbolTable = treeMap.get(treeMap.lastKey());
        }
        return symbolTable;
    }

    @Override // java.lang.Iterable
    public Iterator<SymbolTable> iterator() {
        ArrayList arrayList;
        synchronized (this.myTablesByName) {
            try {
                arrayList = new ArrayList(this.myTablesByName.size());
                for (TreeMap<Integer, SymbolTable> treeMap : this.myTablesByName.values()) {
                    synchronized (treeMap) {
                        arrayList.addAll(treeMap.values());
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList.iterator();
    }

    @Override // com.amazon.ion.IonMutableCatalog
    public void putTable(SymbolTable symbolTable) {
        if (symbolTable.isLocalTable() || symbolTable.isSystemTable() || symbolTable.isSubstitute()) {
            throw new IllegalArgumentException("table cannot be local or system or substitute table");
        }
        String name = symbolTable.getName();
        int version = symbolTable.getVersion();
        synchronized (this.myTablesByName) {
            try {
                TreeMap<Integer, SymbolTable> treeMap = this.myTablesByName.get(name);
                if (treeMap == null) {
                    treeMap = new TreeMap<>();
                    this.myTablesByName.put(name, treeMap);
                }
                synchronized (treeMap) {
                    treeMap.put(Integer.valueOf(version), symbolTable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002a, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        throw r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.ion.SymbolTable removeTable(java.lang.String r4, int r5) {
        /*
            r3 = this;
            java.util.Map<java.lang.String, java.util.TreeMap<java.lang.Integer, com.amazon.ion.SymbolTable>> r0 = r3.myTablesByName
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.util.TreeMap<java.lang.Integer, com.amazon.ion.SymbolTable>> r1 = r3.myTablesByName     // Catch: java.lang.Throwable -> L2a
            java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L2a
            java.util.TreeMap r1 = (java.util.TreeMap) r1     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L2c
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L2a
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L24
            java.lang.Object r5 = r1.remove(r5)     // Catch: java.lang.Throwable -> L24
            com.amazon.ion.SymbolTable r5 = (com.amazon.ion.SymbolTable) r5     // Catch: java.lang.Throwable -> L24
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L24
            if (r2 == 0) goto L26
            java.util.Map<java.lang.String, java.util.TreeMap<java.lang.Integer, com.amazon.ion.SymbolTable>> r2 = r3.myTablesByName     // Catch: java.lang.Throwable -> L24
            r2.remove(r4)     // Catch: java.lang.Throwable -> L24
            goto L26
        L24:
            r4 = move-exception
            goto L28
        L26:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L24
            goto L2d
        L28:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L24
            throw r4     // Catch: java.lang.Throwable -> L2a
        L2a:
            r4 = move-exception
            goto L2f
        L2c:
            r5 = 0
        L2d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
            return r5
        L2f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.system.SimpleCatalog.removeTable(java.lang.String, int):com.amazon.ion.SymbolTable");
    }

    @Override // com.amazon.ion.IonCatalog
    public SymbolTable getTable(String str, int i) {
        TreeMap<Integer, SymbolTable> treeMap;
        SymbolTable symbolTable;
        if (str != null) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("name is empty");
            }
            if (i >= 1) {
                synchronized (this.myTablesByName) {
                    treeMap = this.myTablesByName.get(str);
                }
                if (treeMap == null) {
                    return null;
                }
                synchronized (treeMap) {
                    try {
                        symbolTable = treeMap.get(Integer.valueOf(i));
                        if (symbolTable == null) {
                            symbolTable = treeMap.get(bestMatch(i, treeMap.keySet()));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return symbolTable;
            }
            throw new IllegalArgumentException("version is < 1");
        }
        throw new IllegalArgumentException("name is null");
    }
}
