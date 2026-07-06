package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IcyDataSource implements DataSource {
    public int bytesUntilMetadata;
    public final Listener listener;
    public final int metadataIntervalBytes;
    public final byte[] metadataLengthByteHolder;
    public final DataSource upstream;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onIcyMetadata(ParsableByteArray parsableByteArray);
    }

    public IcyDataSource(DataSource dataSource, int i, Listener listener) {
        Assertions.checkArgument(i > 0);
        this.upstream = dataSource;
        this.metadataIntervalBytes = i;
        this.listener = listener;
        this.metadataLengthByteHolder = new byte[1];
        this.bytesUntilMetadata = i;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.upstream.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    @Nullable
    public Uri getUri() {
        return this.upstream.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.bytesUntilMetadata == 0) {
            if (!readMetadata()) {
                return -1;
            }
            this.bytesUntilMetadata = this.metadataIntervalBytes;
        }
        int i3 = this.upstream.read(bArr, i, Math.min(this.bytesUntilMetadata, i2));
        if (i3 != -1) {
            this.bytesUntilMetadata -= i3;
        }
        return i3;
    }

    public final boolean readMetadata() throws IOException {
        if (this.upstream.read(this.metadataLengthByteHolder, 0, 1) != -1) {
            int i = (this.metadataLengthByteHolder[0] & 255) << 4;
            if (i != 0) {
                byte[] bArr = new byte[i];
                int i2 = i;
                int i3 = 0;
                while (i2 > 0) {
                    int i4 = this.upstream.read(bArr, i3, i2);
                    if (i4 != -1) {
                        i3 += i4;
                        i2 -= i4;
                    }
                }
                while (i > 0 && bArr[i - 1] == 0) {
                    i--;
                }
                if (i > 0) {
                    this.listener.onIcyMetadata(new ParsableByteArray(bArr, i));
                }
            }
            return true;
        }
        return false;
    }
}
