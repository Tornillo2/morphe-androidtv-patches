package com.amazon.ion.apps;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl._Private_IonBinaryWriterImpl;
import com.amazon.ion.impl._Private_IonWriterBase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class EncodeApp extends BaseApp {
    public SymbolTable[] myImports;
    public File myOutputDir;
    public String myOutputFile;

    public static void main(String[] strArr) {
        super.doMain(strArr);
    }

    @Override // com.amazon.ion.apps.BaseApp
    public /* bridge */ /* synthetic */ void doMain(String[] strArr) {
        super.doMain(strArr);
    }

    @Override // com.amazon.ion.apps.BaseApp
    public void process(File file, IonReader ionReader) throws IOException, IonException {
        IonWriter ionWriterNewBinaryWriter = this.mySystem.newBinaryWriter(this.myImports);
        ((_Private_IonWriterBase) ionWriterNewBinaryWriter).writeValues(ionReader);
        byte[] bytes = ((_Private_IonBinaryWriterImpl) ionWriterNewBinaryWriter).getBytes();
        if (this.myOutputDir == null) {
            System.out.write(bytes);
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.myOutputDir, file.getName()));
        try {
            fileOutputStream.write(bytes);
        } finally {
            fileOutputStream.close();
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public int processOptions(String[] strArr) {
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < strArr.length) {
            String str = strArr[i2];
            if (!"--catalog".equals(str)) {
                if (!"--import".equals(str)) {
                    if (!"--output-dir".equals(str)) {
                        if (!"--output".equals(str)) {
                            break;
                        }
                        i = i2 + 1;
                        String str2 = strArr[i];
                        this.myOutputFile = str2;
                        File parentFile = new File(str2).getParentFile();
                        this.myOutputDir = parentFile;
                        if (!parentFile.isDirectory() || !this.myOutputDir.canWrite()) {
                            throw new RuntimeException(RemoteInput$$ExternalSyntheticOutline0.m("Not a writeable directory: ", str2));
                        }
                    } else {
                        i = i2 + 1;
                        String str3 = strArr[i];
                        File file = new File(str3);
                        this.myOutputDir = file;
                        if (!file.isDirectory() || !this.myOutputDir.canWrite()) {
                            throw new RuntimeException(RemoteInput$$ExternalSyntheticOutline0.m("Not a writeable directory: ", str3));
                        }
                    }
                } else {
                    i = i2 + 1;
                    arrayList.add(getLatestSharedSymtab(strArr[i]));
                }
            } else {
                i = i2 + 1;
                loadCatalog(strArr[i]);
            }
            i2 = i + 1;
        }
        this.myImports = (SymbolTable[]) arrayList.toArray(new SymbolTable[0]);
        return i2;
    }

    @Override // com.amazon.ion.apps.BaseApp
    public void process(IonReader ionReader) throws IOException, IonException {
        IonWriter ionWriterNewBinaryWriter = this.mySystem.newBinaryWriter(this.myImports);
        ((_Private_IonWriterBase) ionWriterNewBinaryWriter).writeValues(ionReader);
        byte[] bytes = ((_Private_IonBinaryWriterImpl) ionWriterNewBinaryWriter).getBytes();
        if (this.myOutputDir != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.myOutputFile));
            try {
                fileOutputStream.write(bytes);
                return;
            } finally {
                fileOutputStream.close();
            }
        }
        System.out.write(bytes);
    }
}
