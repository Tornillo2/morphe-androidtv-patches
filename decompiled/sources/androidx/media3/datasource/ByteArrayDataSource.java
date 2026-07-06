package androidx.media3.datasource;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ByteArrayDataSource extends BaseDataSource {
    public int bytesRemaining;
    public final byte[] data;
    public boolean opened;
    public int readPosition;

    @Nullable
    public Uri uri;

    public ByteArrayDataSource(byte[] bArr) {
        super(false);
        bArr.getClass();
        Assertions.checkArgument(bArr.length > 0);
        this.data = bArr;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
        this.uri = null;
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        transferInitializing(dataSpec);
        long j = dataSpec.position;
        byte[] bArr = this.data;
        if (j > bArr.length) {
            throw new DataSourceException(2008);
        }
        this.readPosition = (int) j;
        int length = bArr.length - ((int) j);
        this.bytesRemaining = length;
        long j2 = dataSpec.length;
        if (j2 != -1) {
            this.bytesRemaining = (int) Math.min(length, j2);
        }
        this.opened = true;
        transferStarted(dataSpec);
        long j3 = dataSpec.length;
        return j3 != -1 ? j3 : this.bytesRemaining;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.bytesRemaining;
        if (i3 == 0) {
            return -1;
        }
        int iMin = Math.min(i2, i3);
        System.arraycopy(this.data, this.readPosition, bArr, i, iMin);
        this.readPosition += iMin;
        this.bytesRemaining -= iMin;
        bytesTransferred(iMin);
        return iMin;
    }
}
