package com.amazon.ion.apps;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonSystem;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.SimpleCatalog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseApp {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public SimpleCatalog myCatalog;
    public IonSystem mySystem;

    public BaseApp() {
        SimpleCatalog simpleCatalog = new SimpleCatalog();
        this.myCatalog = simpleCatalog;
        this.mySystem = IonSystemBuilder.STANDARD.withCatalog(simpleCatalog).build();
    }

    public static byte[] loadAsByteArray(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public void doMain(String[] strArr) {
        int iProcessOptions = processOptions(strArr);
        int length = strArr.length - iProcessOptions;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, iProcessOptions, strArr2, 0, length);
        if (optionsAreValid(strArr2)) {
            processFiles(strArr2);
        }
    }

    public SymbolTable getLatestSharedSymtab(String str) {
        SymbolTable table = this.mySystem.getCatalog().getTable(str);
        if (table == null) {
            throw new RuntimeException("There's no symbol table in the catalog named ".concat(str));
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Found shared symbol table ", str, "@");
        sbM.append(table.getVersion());
        logDebug(sbM.toString());
        return table;
    }

    public void loadCatalog(String str) {
        System.err.println("Loading catalog from " + str);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
            try {
                IonReader ionReaderNewReader = this.mySystem.newReader(bufferedInputStream);
                while (ionReaderNewReader.next() != null) {
                    this.myCatalog.putTable(this.mySystem.newSharedSymbolTable(ionReaderNewReader, true));
                }
                this.mySystem.getClass();
            } finally {
                bufferedInputStream.close();
            }
        } catch (Exception e) {
            StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Error loading catalog from ", str, ": ");
            sbM.append(e.getMessage());
            throw new RuntimeException(sbM.toString(), e);
        }
    }

    public void logDebug(String str) {
        System.err.println(str);
    }

    public boolean optionsAreValid(String[] strArr) {
        return true;
    }

    public void process(IonReader ionReader) throws IOException, IonException {
    }

    public boolean processFile(String str) {
        File file = new File(str);
        if (!file.canRead() || !file.isFile()) {
            System.err.println("Skipping unreadable file: " + str);
            return false;
        }
        try {
            process(file);
            return true;
        } catch (IonException e) {
            System.err.println("An error occurred while processing " + str);
            System.err.println(e.getMessage());
            return false;
        } catch (IOException e2) {
            System.err.println("An error occurred while processing " + str);
            System.err.println(e2.getMessage());
            return false;
        }
    }

    public void processFiles(String[] strArr) {
        if (strArr.length == 0) {
            processStdIn();
            return;
        }
        for (String str : strArr) {
            processFile(str);
        }
    }

    public int processOptions(String[] strArr) {
        return 0;
    }

    public void processStdIn() {
        try {
            process(this.mySystem.newReader(loadAsByteArray(System.in)));
        } catch (IonException e) {
            System.err.println("An error occurred while processing stdin");
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println("An error occurred while processing stdin");
            System.err.println(e2.getMessage());
        }
    }

    public void process(File file) throws IOException, IonException {
        process(file, this.mySystem.newReader(loadAsByteArray(file)));
    }

    public void process(File file, IonReader ionReader) throws IOException, IonException {
        process(ionReader);
    }

    public static byte[] loadAsByteArray(File file) throws IOException {
        long length = file.length();
        if (length >= 0 && length <= 2147483647L) {
            byte[] bArr = new byte[(int) length];
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                if (fileInputStream.read(bArr) == length && fileInputStream.read() == -1) {
                    return bArr;
                }
                System.err.println("Read the wrong number of bytes from " + file);
                fileInputStream.close();
                return null;
            } finally {
                fileInputStream.close();
            }
        }
        throw new IllegalArgumentException("File too long: " + file);
    }
}
