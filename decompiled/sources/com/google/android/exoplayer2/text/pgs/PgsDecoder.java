package com.google.android.exoplayer2.text.pgs;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PgsDecoder extends SimpleSubtitleDecoder {
    public static final byte INFLATE_HEADER = 120;
    public static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    public static final int SECTION_TYPE_END = 128;
    public static final int SECTION_TYPE_IDENTIFIER = 22;
    public static final int SECTION_TYPE_PALETTE = 20;
    public final ParsableByteArray buffer;
    public final CueBuilder cueBuilder;
    public final ParsableByteArray inflatedBuffer;

    @Nullable
    public Inflater inflater;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CueBuilder {
        public int bitmapHeight;
        public int bitmapWidth;
        public int bitmapX;
        public int bitmapY;
        public boolean colorsSet;
        public int planeHeight;
        public int planeWidth;
        public final ParsableByteArray bitmapData = new ParsableByteArray();
        public final int[] colors = new int[256];

        @Nullable
        public Cue build() {
            ParsableByteArray parsableByteArray;
            int i;
            int unsignedByte;
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || (i = (parsableByteArray = this.bitmapData).limit) == 0 || parsableByteArray.position != i || !this.colorsSet) {
                return null;
            }
            parsableByteArray.setPosition(0);
            int i2 = this.bitmapWidth * this.bitmapHeight;
            int[] iArr = new int[i2];
            int i3 = 0;
            while (i3 < i2) {
                int unsignedByte2 = this.bitmapData.readUnsignedByte();
                if (unsignedByte2 != 0) {
                    unsignedByte = i3 + 1;
                    iArr[i3] = this.colors[unsignedByte2];
                } else {
                    int unsignedByte3 = this.bitmapData.readUnsignedByte();
                    if (unsignedByte3 != 0) {
                        unsignedByte = ((unsignedByte3 & 64) == 0 ? unsignedByte3 & 63 : ((unsignedByte3 & 63) << 8) | this.bitmapData.readUnsignedByte()) + i3;
                        Arrays.fill(iArr, i3, unsignedByte, (unsignedByte3 & 128) == 0 ? 0 : this.colors[this.bitmapData.readUnsignedByte()]);
                    }
                }
                i3 = unsignedByte;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
            Cue.Builder builder = new Cue.Builder();
            builder.bitmap = bitmapCreateBitmap;
            float f = this.bitmapX;
            int i4 = this.planeWidth;
            builder.position = f / i4;
            builder.positionAnchor = 0;
            float f2 = this.bitmapY;
            int i5 = this.planeHeight;
            builder.line = f2 / i5;
            builder.lineType = 0;
            builder.lineAnchor = 0;
            builder.size = this.bitmapWidth / i4;
            builder.bitmapHeight = this.bitmapHeight / i5;
            return builder.build();
        }

        public final void parseBitmapSection(ParsableByteArray parsableByteArray, int i) {
            int unsignedInt24;
            if (i < 4) {
                return;
            }
            parsableByteArray.skipBytes(3);
            int i2 = i - 4;
            if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                if (i2 < 7 || (unsignedInt24 = parsableByteArray.readUnsignedInt24()) < 4) {
                    return;
                }
                this.bitmapWidth = parsableByteArray.readUnsignedShort();
                this.bitmapHeight = parsableByteArray.readUnsignedShort();
                this.bitmapData.reset(unsignedInt24 - 4);
                i2 = i - 11;
            }
            ParsableByteArray parsableByteArray2 = this.bitmapData;
            int i3 = parsableByteArray2.position;
            int i4 = parsableByteArray2.limit;
            if (i3 >= i4 || i2 <= 0) {
                return;
            }
            int iMin = Math.min(i2, i4 - i3);
            parsableByteArray.readBytes(this.bitmapData.data, i3, iMin);
            this.bitmapData.setPosition(i3 + iMin);
        }

        public final void parseIdentifierSection(ParsableByteArray parsableByteArray, int i) {
            if (i < 19) {
                return;
            }
            this.planeWidth = parsableByteArray.readUnsignedShort();
            this.planeHeight = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(11);
            this.bitmapX = parsableByteArray.readUnsignedShort();
            this.bitmapY = parsableByteArray.readUnsignedShort();
        }

        public final void parsePaletteSection(ParsableByteArray parsableByteArray, int i) {
            if (i % 5 != 2) {
                return;
            }
            parsableByteArray.skipBytes(2);
            Arrays.fill(this.colors, 0);
            int i2 = i / 5;
            for (int i3 = 0; i3 < i2; i3++) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                int unsignedByte4 = parsableByteArray.readUnsignedByte();
                double d = unsignedByte2;
                double d2 = unsignedByte3 - 128;
                double d3 = unsignedByte4 - 128;
                this.colors[unsignedByte] = (Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255) << 8) | (parsableByteArray.readUnsignedByte() << 24) | (Util.constrainValue((int) ((1.402d * d2) + d), 0, 255) << 16) | Util.constrainValue((int) ((d3 * 1.772d) + d), 0, 255);
            }
            this.colorsSet = true;
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    public PgsDecoder() {
        super("PgsDecoder");
        this.buffer = new ParsableByteArray();
        this.inflatedBuffer = new ParsableByteArray();
        this.cueBuilder = new CueBuilder();
    }

    @Nullable
    public static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder) {
        int i = parsableByteArray.limit;
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int i2 = parsableByteArray.position + unsignedShort;
        Cue cueBuild = null;
        if (i2 > i) {
            parsableByteArray.setPosition(i);
            return null;
        }
        if (unsignedByte != 128) {
            switch (unsignedByte) {
                case 20:
                    cueBuilder.parsePaletteSection(parsableByteArray, unsignedShort);
                    break;
                case 21:
                    cueBuilder.parseBitmapSection(parsableByteArray, unsignedShort);
                    break;
                case 22:
                    cueBuilder.parseIdentifierSection(parsableByteArray, unsignedShort);
                    break;
            }
        } else {
            cueBuild = cueBuilder.build();
            cueBuilder.reset();
        }
        parsableByteArray.setPosition(i2);
        return cueBuild;
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.buffer.reset(bArr, i);
        maybeInflateData(this.buffer);
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue nextSection = readNextSection(this.buffer, this.cueBuilder);
            if (nextSection != null) {
                arrayList.add(nextSection);
            }
        }
        return new PgsSubtitle(DesugarCollections.unmodifiableList(arrayList));
    }

    public final void maybeInflateData(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() <= 0 || parsableByteArray.peekUnsignedByte() != 120) {
            return;
        }
        if (this.inflater == null) {
            this.inflater = new Inflater();
        }
        if (Util.inflate(parsableByteArray, this.inflatedBuffer, this.inflater)) {
            ParsableByteArray parsableByteArray2 = this.inflatedBuffer;
            parsableByteArray.reset(parsableByteArray2.data, parsableByteArray2.limit);
        }
    }
}
