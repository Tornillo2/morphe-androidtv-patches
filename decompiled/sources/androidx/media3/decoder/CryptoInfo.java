package androidx.media3.decoder;

import android.media.MediaCodec;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CryptoInfo {
    public int clearBlocks;
    public int encryptedBlocks;
    public final MediaCodec.CryptoInfo frameworkCryptoInfo;

    @Nullable
    public byte[] iv;

    @Nullable
    public byte[] key;
    public int mode;

    @Nullable
    public int[] numBytesOfClearData;

    @Nullable
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;

    @Nullable
    public final PatternHolderV24 patternHolder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static final class PatternHolderV24 {
        public final MediaCodec.CryptoInfo frameworkCryptoInfo;
        public final MediaCodec.CryptoInfo.Pattern pattern;

        public final void set(int i, int i2) {
            this.pattern.set(i, i2);
            this.frameworkCryptoInfo.setPattern(this.pattern);
        }

        public PatternHolderV24(MediaCodec.CryptoInfo cryptoInfo) {
            this.frameworkCryptoInfo = cryptoInfo;
            this.pattern = CryptoInfo$PatternHolderV24$$ExternalSyntheticApiModelOutline0.m(0, 0);
        }
    }

    public CryptoInfo() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.frameworkCryptoInfo = cryptoInfo;
        this.patternHolder = Util.SDK_INT >= 24 ? new PatternHolderV24(cryptoInfo) : null;
    }

    public MediaCodec.CryptoInfo getFrameworkCryptoInfo() {
        return this.frameworkCryptoInfo;
    }

    public void increaseClearDataFirstSubSampleBy(int i) {
        if (i == 0) {
            return;
        }
        if (this.numBytesOfClearData == null) {
            int[] iArr = new int[1];
            this.numBytesOfClearData = iArr;
            this.frameworkCryptoInfo.numBytesOfClearData = iArr;
        }
        int[] iArr2 = this.numBytesOfClearData;
        iArr2[0] = iArr2[0] + i;
    }

    public void set(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        this.numSubSamples = i;
        this.numBytesOfClearData = iArr;
        this.numBytesOfEncryptedData = iArr2;
        this.key = bArr;
        this.iv = bArr2;
        this.mode = i2;
        this.encryptedBlocks = i3;
        this.clearBlocks = i4;
        MediaCodec.CryptoInfo cryptoInfo = this.frameworkCryptoInfo;
        cryptoInfo.numSubSamples = i;
        cryptoInfo.numBytesOfClearData = iArr;
        cryptoInfo.numBytesOfEncryptedData = iArr2;
        cryptoInfo.key = bArr;
        cryptoInfo.iv = bArr2;
        cryptoInfo.mode = i2;
        if (Util.SDK_INT >= 24) {
            PatternHolderV24 patternHolderV24 = this.patternHolder;
            patternHolderV24.getClass();
            patternHolderV24.set(i3, i4);
        }
    }
}
