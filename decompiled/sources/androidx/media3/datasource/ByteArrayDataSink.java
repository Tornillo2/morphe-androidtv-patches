package androidx.media3.datasource;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ByteArrayDataSink implements DataSink {
    public ByteArrayOutputStream stream;

    @Override // androidx.media3.datasource.DataSink
    public void close() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = this.stream;
        Util.castNonNull(byteArrayOutputStream);
        byteArrayOutputStream.close();
    }

    @Nullable
    public byte[] getData() {
        ByteArrayOutputStream byteArrayOutputStream = this.stream;
        if (byteArrayOutputStream == null) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // androidx.media3.datasource.DataSink
    public void open(DataSpec dataSpec) {
        long j = dataSpec.length;
        if (j == -1) {
            this.stream = new ByteArrayOutputStream();
        } else {
            Assertions.checkArgument(j <= 2147483647L);
            this.stream = new ByteArrayOutputStream((int) dataSpec.length);
        }
    }

    @Override // androidx.media3.datasource.DataSink
    public void write(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = this.stream;
        Util.castNonNull(byteArrayOutputStream);
        byteArrayOutputStream.write(bArr, i, i2);
    }
}
