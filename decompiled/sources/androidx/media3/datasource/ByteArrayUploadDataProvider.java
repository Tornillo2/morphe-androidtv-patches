package androidx.media3.datasource;

import android.net.http.UploadDataProvider;
import android.net.http.UploadDataSink;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(34)
public final class ByteArrayUploadDataProvider extends UploadDataProvider {
    public final byte[] data;
    public int position;

    public ByteArrayUploadDataProvider(byte[] bArr) {
        this.data = bArr;
    }

    @Override // android.net.http.UploadDataProvider
    public long getLength() {
        return this.data.length;
    }

    @Override // android.net.http.UploadDataProvider
    public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
        int iMin = Math.min(byteBuffer.remaining(), this.data.length - this.position);
        byteBuffer.put(this.data, this.position, iMin);
        this.position += iMin;
        uploadDataSink.onReadSucceeded(false);
    }

    @Override // android.net.http.UploadDataProvider
    public void rewind(UploadDataSink uploadDataSink) throws IOException {
        this.position = 0;
        uploadDataSink.onRewindSucceeded();
    }
}
