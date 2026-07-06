package com.amazon.ion.apps;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PrintApp extends BaseApp {
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
        if (this.myOutputDir == null) {
            process(ionReader, System.out);
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.myOutputDir, file.getName()));
        try {
            process(ionReader, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    @Override // com.amazon.ion.apps.BaseApp
    public int processOptions(String[] strArr) {
        int i;
        int i2 = 0;
        while (i2 < strArr.length) {
            String str = strArr[i2];
            if ("--catalog".equals(str)) {
                i = i2 + 1;
                loadCatalog(strArr[i]);
            } else if ("--output-dir".equals(str)) {
                i = i2 + 1;
                String str2 = strArr[i];
                File file = new File(str2);
                this.myOutputDir = file;
                if (!file.isDirectory() || !this.myOutputDir.canWrite()) {
                    throw new RuntimeException(RemoteInput$$ExternalSyntheticOutline0.m("Not a writeable directory: ", str2));
                }
            } else {
                if (!"--output".equals(str)) {
                    return i2;
                }
                i = i2 + 1;
                String str3 = strArr[i];
                this.myOutputFile = str3;
                File parentFile = new File(str3).getParentFile();
                this.myOutputDir = parentFile;
                if (!parentFile.isDirectory() || !this.myOutputDir.canWrite()) {
                    throw new RuntimeException(RemoteInput$$ExternalSyntheticOutline0.m("Not a writeable directory: ", str3));
                }
            }
            i2 = i + 1;
        }
        return strArr.length;
    }

    @Override // com.amazon.ion.apps.BaseApp
    public void process(IonReader ionReader) throws IOException, IonException {
        if (this.myOutputDir == null) {
            process(ionReader, System.out);
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.myOutputFile));
        try {
            process(ionReader, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public void process(IonReader ionReader, OutputStream outputStream) throws IOException, IonException {
        this.mySystem.newTextWriter(outputStream).writeValues(ionReader);
        outputStream.write(10);
        outputStream.flush();
    }
}
