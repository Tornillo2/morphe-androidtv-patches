package com.google.android.exoplayer2.upstream.cache;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CacheDataSink implements DataSink {
    public static final int DEFAULT_BUFFER_SIZE = 20480;
    public static final long DEFAULT_FRAGMENT_SIZE = 5242880;
    public static final long MIN_RECOMMENDED_FRAGMENT_SIZE = 2097152;
    public static final String TAG = "CacheDataSink";
    public final int bufferSize;
    public ReusableBufferedOutputStream bufferedOutputStream;
    public final Cache cache;

    @Nullable
    public DataSpec dataSpec;
    public long dataSpecBytesWritten;
    public long dataSpecFragmentSize;

    @Nullable
    public File file;
    public final long fragmentSize;

    @Nullable
    public OutputStream outputStream;
    public long outputStreamBytesWritten;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CacheDataSinkException extends Cache.CacheException {
        public CacheDataSinkException(IOException iOException) {
            super(iOException);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements DataSink.Factory {
        public Cache cache;
        public long fragmentSize = 5242880;
        public int bufferSize = 20480;

        @Override // com.google.android.exoplayer2.upstream.DataSink.Factory
        public DataSink createDataSink() {
            Cache cache = this.cache;
            cache.getClass();
            return new CacheDataSink(cache, this.fragmentSize, this.bufferSize);
        }

        @CanIgnoreReturnValue
        public Factory setBufferSize(int i) {
            this.bufferSize = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        @CanIgnoreReturnValue
        public Factory setFragmentSize(long j) {
            this.fragmentSize = j;
            return this;
        }
    }

    public CacheDataSink(Cache cache, long j) {
        this(cache, j, 20480);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void close() throws CacheDataSinkException {
        if (this.dataSpec == null) {
            return;
        }
        try {
            closeCurrentOutputStream();
        } catch (IOException e) {
            throw new CacheDataSinkException((Throwable) e);
        }
    }

    public final void closeCurrentOutputStream() throws IOException {
        OutputStream outputStream = this.outputStream;
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.flush();
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file = this.file;
            this.file = null;
            this.cache.commitFile(file, this.outputStreamBytesWritten);
        } catch (Throwable th) {
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file2 = this.file;
            this.file = null;
            file2.delete();
            throw th;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void open(DataSpec dataSpec) throws CacheDataSinkException {
        dataSpec.key.getClass();
        if (dataSpec.length == -1 && dataSpec.isFlagSet(2)) {
            this.dataSpec = null;
            return;
        }
        this.dataSpec = dataSpec;
        this.dataSpecFragmentSize = dataSpec.isFlagSet(4) ? this.fragmentSize : Long.MAX_VALUE;
        this.dataSpecBytesWritten = 0L;
        try {
            openNextOutputStream(dataSpec);
        } catch (IOException e) {
            throw new CacheDataSinkException((Throwable) e);
        }
    }

    public final void openNextOutputStream(DataSpec dataSpec) throws IOException {
        long j = dataSpec.length;
        long jMin = j != -1 ? Math.min(j - this.dataSpecBytesWritten, this.dataSpecFragmentSize) : -1L;
        Cache cache = this.cache;
        String str = dataSpec.key;
        Util.castNonNull(str);
        this.file = cache.startFile(str, dataSpec.position + this.dataSpecBytesWritten, jMin);
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        if (this.bufferSize > 0) {
            ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
            if (reusableBufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(fileOutputStream, this.bufferSize);
            } else {
                reusableBufferedOutputStream.reset(fileOutputStream);
            }
            this.outputStream = this.bufferedOutputStream;
        } else {
            this.outputStream = fileOutputStream;
        }
        this.outputStreamBytesWritten = 0L;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void write(byte[] bArr, int i, int i2) throws CacheDataSinkException {
        DataSpec dataSpec = this.dataSpec;
        if (dataSpec == null) {
            return;
        }
        int i3 = 0;
        while (i3 < i2) {
            try {
                if (this.outputStreamBytesWritten == this.dataSpecFragmentSize) {
                    closeCurrentOutputStream();
                    openNextOutputStream(dataSpec);
                }
                int iMin = (int) Math.min(i2 - i3, this.dataSpecFragmentSize - this.outputStreamBytesWritten);
                OutputStream outputStream = this.outputStream;
                Util.castNonNull(outputStream);
                outputStream.write(bArr, i + i3, iMin);
                i3 += iMin;
                long j = iMin;
                this.outputStreamBytesWritten += j;
                this.dataSpecBytesWritten += j;
            } catch (IOException e) {
                throw new CacheDataSinkException((Throwable) e);
            }
        }
    }

    public CacheDataSink(Cache cache, long j, int i) {
        Assertions.checkState(j > 0 || j == -1, "fragmentSize must be positive or C.LENGTH_UNSET.");
        if (j != -1 && j < 2097152) {
            Log.w("CacheDataSink", "fragmentSize is below the minimum recommended value of 2097152. This may cause poor cache performance.");
        }
        cache.getClass();
        this.cache = cache;
        this.fragmentSize = j == -1 ? Long.MAX_VALUE : j;
        this.bufferSize = i;
    }
}
