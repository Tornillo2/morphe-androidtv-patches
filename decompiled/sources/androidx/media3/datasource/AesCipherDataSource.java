package androidx.media3.datasource;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AesCipherDataSource implements DataSource {

    @Nullable
    public AesFlushingCipher cipher;
    public final byte[] secretKey;
    public final DataSource upstream;

    public AesCipherDataSource(byte[] bArr, DataSource dataSource) {
        this.upstream = dataSource;
        this.secretKey = bArr;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.upstream.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.cipher = null;
        this.upstream.close();
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
    public long open(DataSpec dataSpec) throws IOException {
        long jOpen = this.upstream.open(dataSpec);
        this.cipher = new AesFlushingCipher(2, this.secretKey, dataSpec.key, dataSpec.uriPositionOffset + dataSpec.position);
        return jOpen;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.upstream.read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        AesFlushingCipher aesFlushingCipher = this.cipher;
        Util.castNonNull(aesFlushingCipher);
        aesFlushingCipher.update(bArr, i, i3, bArr, i);
        return i3;
    }
}
