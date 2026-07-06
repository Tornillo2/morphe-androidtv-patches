package com.google.android.exoplayer2.extractor;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Id3Peeker {
    public final ParsableByteArray scratch = new ParsableByteArray(10);

    @Nullable
    public Metadata peekId3Data(ExtractorInput extractorInput, @Nullable Id3Decoder.FramePredicate framePredicate) throws IOException {
        Metadata metadataDecode = null;
        int i = 0;
        while (true) {
            try {
                extractorInput.peekFully(this.scratch.data, 0, 10);
                this.scratch.setPosition(0);
                if (this.scratch.readUnsignedInt24() != 4801587) {
                    break;
                }
                this.scratch.skipBytes(3);
                int synchSafeInt = this.scratch.readSynchSafeInt();
                int i2 = synchSafeInt + 10;
                if (metadataDecode == null) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.scratch.data, 0, bArr, 0, 10);
                    extractorInput.peekFully(bArr, 10, synchSafeInt);
                    metadataDecode = new Id3Decoder(framePredicate).decode(bArr, i2);
                } else {
                    extractorInput.advancePeekPosition(synchSafeInt);
                }
                i += i2;
            } catch (EOFException unused) {
            }
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        return metadataDecode;
    }
}
