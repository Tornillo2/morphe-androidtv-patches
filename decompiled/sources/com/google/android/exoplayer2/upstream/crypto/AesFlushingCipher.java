package com.google.android.exoplayer2.upstream.crypto;

import androidx.annotation.Nullable;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AesFlushingCipher {
    public final int blockSize;
    public final Cipher cipher;
    public final byte[] flushedBlock;
    public int pendingXorBytes;
    public final byte[] zerosBlock;

    public AesFlushingCipher(int i, byte[] bArr, @Nullable String str, long j) {
        this(i, bArr, getFNV64Hash(str), j);
    }

    public static long getFNV64Hash(@Nullable String str) {
        long j = 0;
        if (str == null) {
            return 0L;
        }
        for (int i = 0; i < str.length(); i++) {
            long jCharAt = j ^ ((long) str.charAt(i));
            j = jCharAt + (jCharAt << 1) + (jCharAt << 4) + (jCharAt << 5) + (jCharAt << 7) + (jCharAt << 8) + (jCharAt << 40);
        }
        return j;
    }

    public final byte[] getInitializationVector(long j, long j2) {
        return ByteBuffer.allocate(16).putLong(j).putLong(j2).array();
    }

    public final int nonFlushingUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            return this.cipher.update(bArr, i, i2, bArr2, i3);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        do {
            int i7 = this.pendingXorBytes;
            if (i7 <= 0) {
                int iNonFlushingUpdate = nonFlushingUpdate(bArr, i4, i5, bArr2, i6);
                if (i5 == iNonFlushingUpdate) {
                    return;
                }
                int i8 = i5 - iNonFlushingUpdate;
                int i9 = 0;
                Assertions.checkState(i8 < this.blockSize);
                int i10 = i6 + iNonFlushingUpdate;
                int i11 = this.blockSize - i8;
                this.pendingXorBytes = i11;
                Assertions.checkState(nonFlushingUpdate(this.zerosBlock, 0, i11, this.flushedBlock, 0) == this.blockSize);
                while (i9 < i8) {
                    bArr2[i10] = this.flushedBlock[i9];
                    i9++;
                    i10++;
                }
                return;
            }
            bArr2[i6] = (byte) (bArr[i4] ^ this.flushedBlock[this.blockSize - i7]);
            i6++;
            i4++;
            this.pendingXorBytes = i7 - 1;
            i5--;
        } while (i5 != 0);
    }

    public void updateInPlace(byte[] bArr, int i, int i2) {
        update(bArr, i, i2, bArr, i);
    }

    public AesFlushingCipher(int i, byte[] bArr, long j, long j2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = cipher;
            int blockSize = cipher.getBlockSize();
            this.blockSize = blockSize;
            this.zerosBlock = new byte[blockSize];
            this.flushedBlock = new byte[blockSize];
            long j3 = j2 / ((long) blockSize);
            int i2 = (int) (j2 % ((long) blockSize));
            cipher.init(i, new SecretKeySpec(bArr, Util.splitAtFirst(cipher.getAlgorithm(), SchemaId.METRIC_SCHEMA_ID_DELIMITER)[0]), new IvParameterSpec(getInitializationVector(j, j3)));
            if (i2 != 0) {
                byte[] bArr2 = new byte[i2];
                update(bArr2, 0, i2, bArr2, 0);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
