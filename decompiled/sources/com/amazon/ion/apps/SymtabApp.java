package com.amazon.ion.apps;

import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.impl.SharedSymbolTable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SymtabApp extends BaseApp {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public ArrayList<SymbolTable> myImports = new ArrayList<>();
    public ArrayList<String> mySymbols = new ArrayList<>();
    public String mySymtabName;
    public int mySymtabVersion;

    /* JADX INFO: renamed from: com.amazon.ion.apps.SymtabApp$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.SYMBOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void main(String[] strArr) {
        if (strArr.length < 1) {
            System.err.println("Need one file to build symtab");
        } else {
            super.doMain(strArr);
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public /* bridge */ /* synthetic */ void doMain(String[] strArr) {
        super.doMain(strArr);
    }

    public final void intern(String str) {
        if (str == null || str.equals(SystemSymbols.ION) || str.startsWith("$ion_")) {
            return;
        }
        this.mySymbols.add(str);
    }

    public final void internAnnotations(IonReader ionReader) {
        Iterator<String> itIterateTypeAnnotations = ionReader.iterateTypeAnnotations();
        while (itIterateTypeAnnotations.hasNext()) {
            intern(itIterateTypeAnnotations.next());
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public boolean optionsAreValid(String[] strArr) {
        if (this.mySymtabName == null) {
            throw new RuntimeException("Must provide --name");
        }
        if (this.mySymtabVersion == 0) {
            this.mySymtabVersion = 1;
        }
        if (strArr.length != 0) {
            return true;
        }
        System.err.println("Must provide list of files to provide symbols");
        return false;
    }

    @Override // com.amazon.ion.apps.BaseApp
    public void process(IonReader ionReader) throws IonException {
        while (ionReader.hasNext()) {
            IonType next = ionReader.next();
            intern(ionReader.getFieldName());
            internAnnotations(ionReader);
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[next.ordinal()];
            if (i == 1) {
                intern(ionReader.stringValue());
            } else if (i == 2 || i == 3 || i == 4) {
                ionReader.stepIn();
            }
            while (!ionReader.hasNext() && ionReader.getDepth() > 0) {
                ionReader.stepOut();
            }
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public void processFiles(String[] strArr) {
        super.processFiles(strArr);
        SymbolTable[] symbolTableArr = new SymbolTable[this.myImports.size()];
        this.myImports.toArray(symbolTableArr);
        try {
            ((SharedSymbolTable) this.mySystem.newSharedSymbolTable(this.mySymtabName, this.mySymtabVersion, this.mySymbols.iterator(), symbolTableArr)).writeTo(this.mySystem.newTextWriter((OutputStream) System.out));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public int processOptions(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if ("--catalog".equals(str)) {
                i++;
                loadCatalog(strArr[i]);
            } else if ("--import".equals(str)) {
                i++;
                String str2 = strArr[i];
                SymbolTable table = this.mySystem.getCatalog().getTable(str2);
                if (table == null) {
                    throw new RuntimeException("There's no symbol table in the catalog named ".concat(str2));
                }
                this.myImports.add(table);
                logDebug("Imported symbol table " + str2 + "@" + table.getVersion());
            } else if ("--name".equals(str)) {
                if (this.mySymtabName != null) {
                    throw new RuntimeException("Multiple names");
                }
                i++;
                String str3 = strArr[i];
                this.mySymtabName = str3;
                if (str3.length() == 0) {
                    throw new RuntimeException("Name must not be empty");
                }
            } else {
                if (!"--version".equals(str)) {
                    return i;
                }
                if (this.mySymtabVersion != 0) {
                    throw new RuntimeException("Multiple versions");
                }
                int i2 = Integer.parseInt(str);
                if (i2 < 1) {
                    throw new RuntimeException("Version must be at least 1");
                }
                if (i2 != 1) {
                    throw new UnsupportedOperationException("Symtab extension not implemented");
                }
                this.mySymtabVersion = i2;
            }
            i++;
        }
        return strArr.length;
    }
}
