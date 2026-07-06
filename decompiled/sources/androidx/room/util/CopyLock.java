package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class CopyLock {
    public static final Map<String, Lock> sThreadLocks = new HashMap();
    public final File mCopyLockFile;
    public final boolean mFileLevelLock;
    public FileChannel mLockChannel;
    public final Lock mThreadLock;

    public CopyLock(@NonNull String str, @NonNull File file, boolean z) {
        File file2 = new File(file, AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, ".lck"));
        this.mCopyLockFile = file2;
        this.mThreadLock = getThreadLock(file2.getAbsolutePath());
        this.mFileLevelLock = z;
    }

    public static Lock getThreadLock(String str) {
        Lock reentrantLock;
        Map<String, Lock> map = sThreadLocks;
        synchronized (map) {
            try {
                reentrantLock = map.get(str);
                if (reentrantLock == null) {
                    reentrantLock = new ReentrantLock();
                    map.put(str, reentrantLock);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return reentrantLock;
    }

    public void lock() {
        this.mThreadLock.lock();
        if (this.mFileLevelLock) {
            try {
                FileChannel channel = new FileOutputStream(this.mCopyLockFile).getChannel();
                this.mLockChannel = channel;
                channel.lock();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to grab copy lock.", e);
            }
        }
    }

    public void unlock() {
        FileChannel fileChannel = this.mLockChannel;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.mThreadLock.unlock();
    }
}
