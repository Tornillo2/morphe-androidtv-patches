package com.amazon.ion.impl.lite;

import com.amazon.ion.IonLob;
import com.amazon.ion.impl._Private_IonValue;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.CRC32;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonLobLite extends IonValueLite implements IonLob {
    public byte[] _lob_value;

    public IonLobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.IonLob
    public final int byteSize() {
        validateThisNotNull();
        return this._lob_value.length;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonLobLite mo245clone();

    public final void copyBytesFrom(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            this._lob_value = null;
            set_flag(4);
            return;
        }
        byte[] bArr2 = this._lob_value;
        if (bArr2 == null || bArr2.length != i2) {
            this._lob_value = new byte[i2];
        }
        System.arraycopy(bArr, i, this._lob_value, 0, i2);
        clear_flag(4);
    }

    @Override // com.amazon.ion.IonLob
    public final byte[] getBytes() {
        if (is_true(4)) {
            return null;
        }
        return (byte[]) this._lob_value.clone();
    }

    public byte[] getBytesNoCopy() {
        return this._lob_value;
    }

    public int lobHashCode(int i, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (!is_true(4)) {
            CRC32 crc32 = new CRC32();
            crc32.update(getBytes());
            i ^= (int) crc32.getValue();
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonLob
    public final InputStream newInputStream() {
        if (is_true(4)) {
            return null;
        }
        return new ByteArrayInputStream(this._lob_value);
    }

    @Override // com.amazon.ion.IonLob
    public final void setBytes(byte[] bArr) {
        setBytes(bArr, 0, bArr == null ? 0 : bArr.length);
    }

    public IonLobLite(IonLobLite ionLobLite, IonContext ionContext) {
        super(ionLobLite, ionContext);
        byte[] bArr = ionLobLite._lob_value;
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            this._lob_value = bArr2;
            System.arraycopy(ionLobLite._lob_value, 0, bArr2, 0, length);
        }
    }

    @Override // com.amazon.ion.IonLob
    public final void setBytes(byte[] bArr, int i, int i2) {
        checkForLock();
        copyBytesFrom(bArr, i, i2);
    }
}
