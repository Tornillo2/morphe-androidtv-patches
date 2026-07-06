package androidx.media3.exoplayer.source.mediaparser;

import android.annotation.SuppressLint;
import android.media.MediaParser;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(30)
@SuppressLint({"Override"})
@UnstableApi
public final class InputReaderAdapterV30 implements MediaParser.SeekableInputReader {
    public long currentPosition;

    @Nullable
    public DataReader dataReader;
    public long lastSeekPosition;
    public long resourceLength;

    public long getAndResetSeekPosition() {
        long j = this.lastSeekPosition;
        this.lastSeekPosition = -1L;
        return j;
    }

    @Override // android.media.MediaParser.InputReader
    public long getLength() {
        return this.resourceLength;
    }

    @Override // android.media.MediaParser.InputReader
    public long getPosition() {
        return this.currentPosition;
    }

    @Override // android.media.MediaParser.InputReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        DataReader dataReader = this.dataReader;
        Util.castNonNull(dataReader);
        int i3 = dataReader.read(bArr, i, i2);
        this.currentPosition += (long) i3;
        return i3;
    }

    @Override // android.media.MediaParser.SeekableInputReader
    public void seekToPosition(long j) {
        this.lastSeekPosition = j;
    }

    public void setCurrentPosition(long j) {
        this.currentPosition = j;
    }

    public void setDataReader(DataReader dataReader, long j) {
        this.dataReader = dataReader;
        this.resourceLength = j;
        this.lastSeekPosition = -1L;
    }
}
