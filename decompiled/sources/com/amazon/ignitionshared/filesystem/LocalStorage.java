package com.amazon.ignitionshared.filesystem;

import android.util.AtomicFile;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import com.amazon.reporting.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class LocalStorage {
    public static final int FAIL_WRITE = 4;
    public static final int SUCCESS = 0;
    public static final String TAG = "LocalStorage";
    public final AtomicFile file;
    public final List<WriteListener> writeListeners = new CopyOnWriteArrayList();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface WriteListener {
        void onWrite();
    }

    @Inject
    public LocalStorage(@Named(Names.IGNITE_ROOT_DIR) File file) {
        this.file = new AtomicFile(new File(file, "localstorage.bin"));
    }

    public void addWriteListener(WriteListener writeListener) {
        this.writeListeners.add(writeListener);
    }

    public boolean isEmpty() {
        try {
            FileInputStream fileInputStreamOpenRead = this.file.openRead();
            try {
                boolean z = fileInputStreamOpenRead.read() != -1;
                fileInputStreamOpenRead.close();
                return z;
            } finally {
            }
        } catch (IOException e) {
            Log.w(TAG, String.format("Local storage file \"%s\" could not be read ", this.file.getBaseFile().getName()), e);
            return true;
        }
    }

    public final void notifyWriteListeners() {
        Iterator<WriteListener> it = this.writeListeners.iterator();
        while (it.hasNext()) {
            it.next().onWrite();
        }
    }

    @CalledFromNative
    public byte[] read() {
        try {
            return this.file.readFully();
        } catch (IOException unused) {
            return null;
        }
    }

    @CalledFromNative
    public int write(byte[] bArr) {
        FileOutputStream fileOutputStreamStartWrite = null;
        try {
            try {
                fileOutputStreamStartWrite = this.file.startWrite();
                fileOutputStreamStartWrite.write(bArr);
                this.file.finishWrite(fileOutputStreamStartWrite);
                notifyWriteListeners();
                return 0;
            } catch (Exception e) {
                Log.e(TAG, "Failed to write to local storage", e);
                if (fileOutputStreamStartWrite != null) {
                    this.file.failWrite(fileOutputStreamStartWrite);
                }
                notifyWriteListeners();
                return 4;
            }
        } catch (Throwable th) {
            notifyWriteListeners();
            throw th;
        }
    }
}
