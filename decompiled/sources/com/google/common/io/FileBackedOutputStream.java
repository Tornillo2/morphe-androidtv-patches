package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
@J2ktIncompatible
@GwtIncompatible
public final class FileBackedOutputStream extends OutputStream {

    @GuardedBy("this")
    public File file;
    public final int fileThreshold;

    @GuardedBy("this")
    public MemoryOutput memory;

    @GuardedBy("this")
    public OutputStream out;
    public final boolean resetOnFinalize;
    public final ByteSource source;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MemoryOutput extends ByteArrayOutputStream {
        public MemoryOutput() {
        }

        public byte[] getBuffer() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }

        public MemoryOutput(AnonymousClass1 anonymousClass1) {
        }
    }

    public FileBackedOutputStream(int fileThreshold) {
        this(fileThreshold, false);
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    @VisibleForTesting
    public synchronized File getFile() {
        return this.file;
    }

    public final synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        Objects.requireNonNull(this.memory);
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    public synchronized void reset() throws IOException {
        try {
            close();
            MemoryOutput memoryOutput = this.memory;
            if (memoryOutput == null) {
                this.memory = new MemoryOutput();
            } else {
                memoryOutput.reset();
            }
            this.out = this.memory;
            File file = this.file;
            if (file != null) {
                this.file = null;
                if (!file.delete()) {
                    throw new IOException("Could not delete: " + file);
                }
            }
        } catch (Throwable th) {
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            File file2 = this.file;
            if (file2 != null) {
                this.file = null;
                if (!file2.delete()) {
                    throw new IOException("Could not delete: " + file2);
                }
            }
            throw th;
        } finally {
        }
    }

    @GuardedBy("this")
    public final void update(int len) throws IOException {
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput == null || memoryOutput.getCount() + len <= this.fileThreshold) {
            return;
        }
        File fileCreateTempFile = TempFileCreator.INSTANCE.createTempFile("FileBackedOutputStream");
        if (this.resetOnFinalize) {
            fileCreateTempFile.deleteOnExit();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            fileOutputStream.write(this.memory.getBuffer(), 0, this.memory.getCount());
            fileOutputStream.flush();
            this.out = fileOutputStream;
            this.file = fileCreateTempFile;
            this.memory = null;
        } catch (IOException e) {
            fileCreateTempFile.delete();
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int b) throws IOException {
        update(1);
        this.out.write(b);
    }

    public FileBackedOutputStream(int fileThreshold, boolean resetOnFinalize) {
        Preconditions.checkArgument(fileThreshold >= 0, "fileThreshold must be non-negative, but was %s", fileThreshold);
        this.fileThreshold = fileThreshold;
        this.resetOnFinalize = resetOnFinalize;
        MemoryOutput memoryOutput = new MemoryOutput();
        this.memory = memoryOutput;
        this.out = memoryOutput;
        if (resetOnFinalize) {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
                public void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }

                @Override // com.google.common.io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        } else {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
                @Override // com.google.common.io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        update(len);
        this.out.write(b, off, len);
    }
}
