package com.google.android.exoplayer2.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.math.DoubleMath;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DvbParser {
    public static final int DATA_TYPE_24_TABLE_DATA = 32;
    public static final int DATA_TYPE_28_TABLE_DATA = 33;
    public static final int DATA_TYPE_2BP_CODE_STRING = 16;
    public static final int DATA_TYPE_48_TABLE_DATA = 34;
    public static final int DATA_TYPE_4BP_CODE_STRING = 17;
    public static final int DATA_TYPE_8BP_CODE_STRING = 18;
    public static final int DATA_TYPE_END_LINE = 240;
    public static final int OBJECT_CODING_PIXELS = 0;
    public static final int OBJECT_CODING_STRING = 1;
    public static final int PAGE_STATE_NORMAL = 0;
    public static final int REGION_DEPTH_4_BIT = 2;
    public static final int REGION_DEPTH_8_BIT = 3;
    public static final int SEGMENT_TYPE_CLUT_DEFINITION = 18;
    public static final int SEGMENT_TYPE_DISPLAY_DEFINITION = 20;
    public static final int SEGMENT_TYPE_OBJECT_DATA = 19;
    public static final int SEGMENT_TYPE_PAGE_COMPOSITION = 16;
    public static final int SEGMENT_TYPE_REGION_COMPOSITION = 17;
    public static final String TAG = "DvbParser";
    public static final byte[] defaultMap2To4 = {0, 7, 8, Ascii.SI};
    public static final byte[] defaultMap2To8 = {0, 119, -120, -1};
    public static final byte[] defaultMap4To8 = {0, 17, 34, TarConstants.LF_CHR, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    public Bitmap bitmap;
    public final Canvas canvas;
    public final ClutDefinition defaultClutDefinition;
    public final DisplayDefinition defaultDisplayDefinition;
    public final Paint defaultPaint;
    public final Paint fillRegionPaint;
    public final SubtitleService subtitleService;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.id = i;
            this.clutEntries2Bit = iArr;
            this.clutEntries4Bit = iArr2;
            this.clutEntries8Bit = iArr3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int i, int i2, int i3, int i4, int i5, int i6) {
            this.width = i;
            this.height = i2;
            this.horizontalPositionMinimum = i3;
            this.horizontalPositionMaximum = i4;
            this.verticalPositionMinimum = i5;
            this.verticalPositionMaximum = i6;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.id = i;
            this.nonModifyingColorFlag = z;
            this.topFieldData = bArr;
            this.bottomFieldData = bArr2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int i, int i2, int i3, SparseArray<PageRegion> sparseArray) {
            this.timeOutSecs = i;
            this.version = i2;
            this.state = i3;
            this.regions = sparseArray;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int i, int i2) {
            this.horizontalAddress = i;
            this.verticalAddress = i2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray<RegionObject> sparseArray) {
            this.id = i;
            this.fillFlag = z;
            this.width = i2;
            this.height = i3;
            this.levelOfCompatibility = i4;
            this.depth = i5;
            this.clutId = i6;
            this.pixelCode8Bit = i7;
            this.pixelCode4Bit = i8;
            this.pixelCode2Bit = i9;
            this.regionObjects = sparseArray;
        }

        public void mergeFrom(RegionComposition regionComposition) {
            SparseArray<RegionObject> sparseArray = regionComposition.regionObjects;
            for (int i = 0; i < sparseArray.size(); i++) {
                this.regionObjects.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int i, int i2, int i3, int i4, int i5, int i6) {
            this.type = i;
            this.provider = i2;
            this.horizontalPosition = i3;
            this.verticalPosition = i4;
            this.foregroundPixelCode = i5;
            this.backgroundPixelCode = i6;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SubtitleService {
        public final int ancillaryPageId;

        @Nullable
        public DisplayDefinition displayDefinition;

        @Nullable
        public PageComposition pageComposition;
        public final int subtitlePageId;
        public final SparseArray<RegionComposition> regions = new SparseArray<>();
        public final SparseArray<ClutDefinition> cluts = new SparseArray<>();
        public final SparseArray<ObjectData> objects = new SparseArray<>();
        public final SparseArray<ClutDefinition> ancillaryCluts = new SparseArray<>();
        public final SparseArray<ObjectData> ancillaryObjects = new SparseArray<>();

        public SubtitleService(int i, int i2) {
            this.subtitlePageId = i;
            this.ancillaryPageId = i2;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    public DvbParser(int i, int i2) {
        Paint paint = new Paint();
        this.defaultPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect(null);
        Paint paint2 = new Paint();
        this.fillRegionPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect(null);
        this.canvas = new Canvas();
        this.defaultDisplayDefinition = new DisplayDefinition(719, 575, 0, 719, 0, 575);
        this.defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
        this.subtitleService = new SubtitleService(i, i2);
    }

    public static byte[] buildClutMapTable(int i, int i2, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) parsableBitArray.readBits(i2);
        }
        return bArr;
    }

    public static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    public static int[] generateDefault4BitClutEntries() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i = 1; i < 16; i++) {
            if (i < 8) {
                iArr[i] = getColor(255, (i & 1) != 0 ? 255 : 0, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
            } else {
                iArr[i] = getColor(255, (i & 1) != 0 ? 127 : 0, (i & 2) != 0 ? 127 : 0, (i & 4) == 0 ? 0 : 127);
            }
        }
        return iArr;
    }

    public static int[] generateDefault8BitClutEntries() {
        int i;
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (i2 < 8) {
                iArr[i2] = getColor(63, (i2 & 1) != 0 ? 255 : 0, (i2 & 2) != 0 ? 255 : 0, (i2 & 4) == 0 ? 0 : 255);
            } else {
                int i3 = i2 & 136;
                int i4 = DoubleMath.MAX_FACTORIAL;
                if (i3 == 0) {
                    int i5 = ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? DoubleMath.MAX_FACTORIAL : 0);
                    int i6 = ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? DoubleMath.MAX_FACTORIAL : 0);
                    i = (i2 & 4) == 0 ? 0 : 85;
                    if ((i2 & 64) == 0) {
                        i4 = 0;
                    }
                    iArr[i2] = getColor(255, i5, i6, i + i4);
                } else if (i3 == 8) {
                    int i7 = ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? DoubleMath.MAX_FACTORIAL : 0);
                    int i8 = ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? DoubleMath.MAX_FACTORIAL : 0);
                    i = (i2 & 4) == 0 ? 0 : 85;
                    if ((i2 & 64) == 0) {
                        i4 = 0;
                    }
                    iArr[i2] = getColor(127, i7, i8, i + i4);
                } else if (i3 == 128) {
                    iArr[i2] = getColor(255, ((i2 & 1) != 0 ? 43 : 0) + 127 + ((i2 & 16) != 0 ? 85 : 0), ((i2 & 2) != 0 ? 43 : 0) + 127 + ((i2 & 32) != 0 ? 85 : 0), ((i2 & 4) == 0 ? 0 : 43) + 127 + ((i2 & 64) == 0 ? 0 : 85));
                } else if (i3 == 136) {
                    iArr[i2] = getColor(255, ((i2 & 1) != 0 ? 43 : 0) + ((i2 & 16) != 0 ? 85 : 0), ((i2 & 2) != 0 ? 43 : 0) + ((i2 & 32) != 0 ? 85 : 0), ((i2 & 4) == 0 ? 0 : 43) + ((i2 & 64) == 0 ? 0 : 85));
                }
            }
        }
        return iArr;
    }

    public static int getColor(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0078 A[LOOP:0: B:3:0x0002->B:33:0x0078, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int paint2BitPixelCodeString(com.google.android.exoplayer2.util.ParsableBitArray r9, int[] r10, @androidx.annotation.Nullable byte[] r11, int r12, int r13, @androidx.annotation.Nullable android.graphics.Paint r14, android.graphics.Canvas r15) {
        /*
            r6 = 0
            r0 = 0
        L2:
            r1 = 2
            int r2 = r9.readBits(r1)
            r3 = 1
            if (r2 == 0) goto Ld
            r7 = r0
        Lb:
            r8 = 1
            goto L5a
        Ld:
            boolean r2 = r9.readBit()
            r4 = 3
            if (r2 == 0) goto L21
            int r2 = r9.readBits(r4)
            int r2 = r2 + r4
            int r1 = r9.readBits(r1)
        L1d:
            r7 = r0
            r8 = r2
            r2 = r1
            goto L5a
        L21:
            boolean r2 = r9.readBit()
            if (r2 == 0) goto L2a
            r7 = r0
            r2 = 0
            goto Lb
        L2a:
            int r2 = r9.readBits(r1)
            if (r2 == 0) goto L57
            if (r2 == r3) goto L53
            if (r2 == r1) goto L47
            if (r2 == r4) goto L3a
            r7 = r0
            r2 = 0
        L38:
            r8 = 0
            goto L5a
        L3a:
            r2 = 8
            int r2 = r9.readBits(r2)
            int r2 = r2 + 29
            int r1 = r9.readBits(r1)
            goto L1d
        L47:
            r2 = 4
            int r2 = r9.readBits(r2)
            int r2 = r2 + 12
            int r1 = r9.readBits(r1)
            goto L1d
        L53:
            r7 = r0
            r2 = 0
            r8 = 2
            goto L5a
        L57:
            r2 = 0
            r7 = 1
            goto L38
        L5a:
            if (r8 == 0) goto L74
            if (r14 == 0) goto L74
            if (r11 == 0) goto L62
            r2 = r11[r2]
        L62:
            r0 = r10[r2]
            r14.setColor(r0)
            float r1 = (float) r12
            float r2 = (float) r13
            int r0 = r12 + r8
            float r0 = (float) r0
            int r3 = r3 + r13
            float r4 = (float) r3
            r5 = r14
            r3 = r0
            r0 = r15
            r0.drawRect(r1, r2, r3, r4, r5)
        L74:
            int r12 = r12 + r8
            if (r7 == 0) goto L78
            return r12
        L78:
            r0 = r7
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.dvb.DvbParser.paint2BitPixelCodeString(com.google.android.exoplayer2.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085 A[LOOP:0: B:3:0x0002->B:36:0x0085, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0084 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int paint4BitPixelCodeString(com.google.android.exoplayer2.util.ParsableBitArray r9, int[] r10, @androidx.annotation.Nullable byte[] r11, int r12, int r13, @androidx.annotation.Nullable android.graphics.Paint r14, android.graphics.Canvas r15) {
        /*
            r6 = 0
            r0 = 0
        L2:
            r1 = 4
            int r2 = r9.readBits(r1)
            r3 = 1
            if (r2 == 0) goto Le
            r7 = r0
        Lb:
            r8 = 1
            goto L67
        Le:
            boolean r2 = r9.readBit()
            r4 = 3
            if (r2 != 0) goto L25
            int r1 = r9.readBits(r4)
            if (r1 == 0) goto L21
            int r1 = r1 + 2
            r7 = r0
            r8 = r1
            r2 = 0
            goto L67
        L21:
            r2 = 0
            r7 = 1
        L23:
            r8 = 0
            goto L67
        L25:
            boolean r2 = r9.readBit()
            r7 = 2
            if (r2 != 0) goto L39
            int r2 = r9.readBits(r7)
            int r2 = r2 + r1
            int r1 = r9.readBits(r1)
        L35:
            r7 = r0
            r8 = r2
            r2 = r1
            goto L67
        L39:
            int r2 = r9.readBits(r7)
            if (r2 == 0) goto L64
            if (r2 == r3) goto L60
            if (r2 == r7) goto L55
            if (r2 == r4) goto L48
            r7 = r0
            r2 = 0
            goto L23
        L48:
            r2 = 8
            int r2 = r9.readBits(r2)
            int r2 = r2 + 25
            int r1 = r9.readBits(r1)
            goto L35
        L55:
            int r2 = r9.readBits(r1)
            int r2 = r2 + 9
            int r1 = r9.readBits(r1)
            goto L35
        L60:
            r7 = r0
            r2 = 0
            r8 = 2
            goto L67
        L64:
            r7 = r0
            r2 = 0
            goto Lb
        L67:
            if (r8 == 0) goto L81
            if (r14 == 0) goto L81
            if (r11 == 0) goto L6f
            r2 = r11[r2]
        L6f:
            r0 = r10[r2]
            r14.setColor(r0)
            float r1 = (float) r12
            float r2 = (float) r13
            int r0 = r12 + r8
            float r0 = (float) r0
            int r3 = r3 + r13
            float r4 = (float) r3
            r5 = r14
            r3 = r0
            r0 = r15
            r0.drawRect(r1, r2, r3, r4, r5)
        L81:
            int r12 = r12 + r8
            if (r7 == 0) goto L85
            return r12
        L85:
            r0 = r7
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.dvb.DvbParser.paint4BitPixelCodeString(com.google.android.exoplayer2.util.ParsableBitArray, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    public static int paint8BitPixelCodeString(ParsableBitArray parsableBitArray, int[] iArr, @Nullable byte[] bArr, int i, int i2, @Nullable Paint paint, Canvas canvas) {
        boolean z;
        int bits;
        boolean z2 = false;
        while (true) {
            int bits2 = parsableBitArray.readBits(8);
            if (bits2 != 0) {
                z = z2;
                bits = 1;
            } else if (parsableBitArray.readBit()) {
                z = z2;
                bits = parsableBitArray.readBits(7);
                bits2 = parsableBitArray.readBits(8);
            } else {
                int bits3 = parsableBitArray.readBits(7);
                if (bits3 != 0) {
                    z = z2;
                    bits = bits3;
                    bits2 = 0;
                } else {
                    bits2 = 0;
                    z = true;
                    bits = 0;
                }
            }
            if (bits != 0 && paint != null) {
                if (bArr != null) {
                    bits2 = bArr[bits2];
                }
                paint.setColor(iArr[bits2]);
                canvas.drawRect(i, i2, i + bits, 1 + i2, paint);
            }
            i += bits;
            if (z) {
                return i;
            }
            z2 = z;
        }
    }

    public static void paintPixelDataSubBlock(byte[] bArr, int[] iArr, int i, int i2, int i3, @Nullable Paint paint, Canvas canvas) {
        int[] iArr2;
        Paint paint2;
        Canvas canvas2;
        byte[] bArr2;
        byte[] bArr3;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, bArr.length);
        byte[] bArrBuildClutMapTable = null;
        byte[] bArrBuildClutMapTable2 = null;
        int iPaint2BitPixelCodeString = i2;
        int i4 = i3;
        byte[] bArrBuildClutMapTable3 = null;
        while (parsableBitArray.bitsLeft() != 0) {
            int bits = parsableBitArray.readBits(8);
            if (bits != 240) {
                switch (bits) {
                    case 16:
                        iArr2 = iArr;
                        Paint paint3 = paint;
                        canvas2 = canvas;
                        if (i == 3) {
                            bArr3 = bArrBuildClutMapTable3 == null ? defaultMap2To8 : bArrBuildClutMapTable3;
                        } else if (i != 2) {
                            bArr2 = null;
                            paint2 = paint3;
                            iPaint2BitPixelCodeString = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, iPaint2BitPixelCodeString, i4, paint2, canvas2);
                            parsableBitArray.byteAlign();
                        } else {
                            bArr3 = bArrBuildClutMapTable2 == null ? defaultMap2To4 : bArrBuildClutMapTable2;
                        }
                        paint2 = paint3;
                        bArr2 = bArr3;
                        iPaint2BitPixelCodeString = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, iPaint2BitPixelCodeString, i4, paint2, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 17:
                        iArr2 = iArr;
                        Paint paint4 = paint;
                        canvas2 = canvas;
                        paint2 = paint4;
                        iPaint2BitPixelCodeString = paint4BitPixelCodeString(parsableBitArray, iArr2, i == 3 ? bArrBuildClutMapTable == null ? defaultMap4To8 : bArrBuildClutMapTable : null, iPaint2BitPixelCodeString, i4, paint2, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 18:
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas2 = canvas;
                        iPaint2BitPixelCodeString = paint8BitPixelCodeString(parsableBitArray, iArr2, null, iPaint2BitPixelCodeString, i4, paint2, canvas2);
                        break;
                    default:
                        switch (bits) {
                            case 32:
                                bArrBuildClutMapTable2 = buildClutMapTable(4, 4, parsableBitArray);
                                break;
                            case 33:
                                bArrBuildClutMapTable3 = buildClutMapTable(4, 8, parsableBitArray);
                                break;
                            case 34:
                                bArrBuildClutMapTable = buildClutMapTable(16, 8, parsableBitArray);
                                break;
                        }
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas2 = canvas;
                        break;
                }
            } else {
                iArr2 = iArr;
                paint2 = paint;
                canvas2 = canvas;
                i4 += 2;
                iPaint2BitPixelCodeString = i2;
            }
            iArr = iArr2;
            paint = paint2;
            canvas = canvas2;
        }
    }

    public static void paintPixelDataSubBlocks(ObjectData objectData, ClutDefinition clutDefinition, int i, int i2, int i3, @Nullable Paint paint, Canvas canvas) {
        int[] iArr = i == 3 ? clutDefinition.clutEntries8Bit : i == 2 ? clutDefinition.clutEntries4Bit : clutDefinition.clutEntries2Bit;
        paintPixelDataSubBlock(objectData.topFieldData, iArr, i, i2, i3, paint, canvas);
        paintPixelDataSubBlock(objectData.bottomFieldData, iArr, i, i2, i3 + 1, paint, canvas);
    }

    public static ClutDefinition parseClutDefinition(ParsableBitArray parsableBitArray, int i) {
        int bits;
        int i2;
        int bits2;
        int bits3;
        int bits4;
        int i3 = 8;
        int bits5 = parsableBitArray.readBits(8);
        parsableBitArray.skipBits(8);
        int i4 = 2;
        int i5 = i - 2;
        int[] iArrGenerateDefault2BitClutEntries = generateDefault2BitClutEntries();
        int[] iArrGenerateDefault4BitClutEntries = generateDefault4BitClutEntries();
        int[] iArrGenerateDefault8BitClutEntries = generateDefault8BitClutEntries();
        while (i5 > 0) {
            int bits6 = parsableBitArray.readBits(i3);
            int bits7 = parsableBitArray.readBits(i3);
            int[] iArr = (bits7 & 128) != 0 ? iArrGenerateDefault2BitClutEntries : (bits7 & 64) != 0 ? iArrGenerateDefault4BitClutEntries : iArrGenerateDefault8BitClutEntries;
            if ((bits7 & 1) != 0) {
                bits3 = parsableBitArray.readBits(i3);
                bits4 = parsableBitArray.readBits(i3);
                bits = parsableBitArray.readBits(i3);
                bits2 = parsableBitArray.readBits(i3);
                i2 = i5 - 6;
            } else {
                int bits8 = parsableBitArray.readBits(6) << i4;
                int bits9 = parsableBitArray.readBits(4) << 4;
                bits = parsableBitArray.readBits(4) << 4;
                i2 = i5 - 4;
                bits2 = parsableBitArray.readBits(i4) << 6;
                bits3 = bits8;
                bits4 = bits9;
            }
            if (bits3 == 0) {
                bits4 = 0;
                bits = 0;
                bits2 = 255;
            }
            double d = bits3;
            double d2 = bits4 - 128;
            double d3 = bits - 128;
            iArr[bits6] = getColor((byte) (255 - (bits2 & 255)), Util.constrainValue((int) ((1.402d * d2) + d), 0, 255), Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255), Util.constrainValue((int) ((d3 * 1.772d) + d), 0, 255));
            i5 = i2;
            bits5 = bits5;
            i3 = 8;
            i4 = 2;
        }
        return new ClutDefinition(bits5, iArrGenerateDefault2BitClutEntries, iArrGenerateDefault4BitClutEntries, iArrGenerateDefault8BitClutEntries);
    }

    public static DisplayDefinition parseDisplayDefinition(ParsableBitArray parsableBitArray) {
        int i;
        int bits;
        int i2;
        int i3;
        parsableBitArray.skipBits(4);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int bits2 = parsableBitArray.readBits(16);
        int bits3 = parsableBitArray.readBits(16);
        if (bit) {
            int bits4 = parsableBitArray.readBits(16);
            int bits5 = parsableBitArray.readBits(16);
            int bits6 = parsableBitArray.readBits(16);
            bits = parsableBitArray.readBits(16);
            i = bits5;
            i3 = bits6;
            i2 = bits4;
        } else {
            i = bits2;
            bits = bits3;
            i2 = 0;
            i3 = 0;
        }
        return new DisplayDefinition(bits2, bits3, i2, i, i3, bits);
    }

    public static ObjectData parseObjectData(ParsableBitArray parsableBitArray) {
        byte[] bArr;
        int bits = parsableBitArray.readBits(16);
        parsableBitArray.skipBits(4);
        int bits2 = parsableBitArray.readBits(2);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(1);
        byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
        if (bits2 != 1) {
            if (bits2 == 0) {
                int bits3 = parsableBitArray.readBits(16);
                int bits4 = parsableBitArray.readBits(16);
                if (bits3 > 0) {
                    bArr2 = new byte[bits3];
                    parsableBitArray.readBytes(bArr2, 0, bits3);
                }
                if (bits4 > 0) {
                    bArr = new byte[bits4];
                    parsableBitArray.readBytes(bArr, 0, bits4);
                }
            }
            return new ObjectData(bits, bit, bArr2, bArr);
        }
        parsableBitArray.skipBits(parsableBitArray.readBits(8) * 16);
        bArr = bArr2;
        return new ObjectData(bits, bit, bArr2, bArr);
    }

    public static PageComposition parsePageComposition(ParsableBitArray parsableBitArray, int i) {
        int bits = parsableBitArray.readBits(8);
        int bits2 = parsableBitArray.readBits(4);
        int bits3 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i2 = i - 2;
        SparseArray sparseArray = new SparseArray();
        while (i2 > 0) {
            int bits4 = parsableBitArray.readBits(8);
            parsableBitArray.skipBits(8);
            i2 -= 6;
            sparseArray.put(bits4, new PageRegion(parsableBitArray.readBits(16), parsableBitArray.readBits(16)));
        }
        return new PageComposition(bits, bits2, bits3, sparseArray);
    }

    public static RegionComposition parseRegionComposition(ParsableBitArray parsableBitArray, int i) {
        int bits;
        int bits2;
        int bits3 = parsableBitArray.readBits(8);
        int i2 = 4;
        parsableBitArray.skipBits(4);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int i3 = 16;
        int bits4 = parsableBitArray.readBits(16);
        int bits5 = parsableBitArray.readBits(16);
        int bits6 = parsableBitArray.readBits(3);
        int bits7 = parsableBitArray.readBits(3);
        int i4 = 2;
        parsableBitArray.skipBits(2);
        int bits8 = parsableBitArray.readBits(8);
        int bits9 = parsableBitArray.readBits(8);
        int bits10 = parsableBitArray.readBits(4);
        int bits11 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i5 = i - 10;
        SparseArray sparseArray = new SparseArray();
        while (i5 > 0) {
            int bits12 = parsableBitArray.readBits(i3);
            int bits13 = parsableBitArray.readBits(i4);
            int bits14 = parsableBitArray.readBits(i4);
            int bits15 = parsableBitArray.readBits(12);
            parsableBitArray.skipBits(i2);
            int bits16 = parsableBitArray.readBits(12);
            int i6 = i5 - 6;
            if (bits13 == 1 || bits13 == 2) {
                i5 -= 8;
                bits2 = parsableBitArray.readBits(8);
                bits = parsableBitArray.readBits(8);
            } else {
                i5 = i6;
                bits2 = 0;
                bits = 0;
            }
            sparseArray.put(bits12, new RegionObject(bits13, bits14, bits15, bits16, bits2, bits));
            i2 = 4;
            i3 = 16;
            i4 = 2;
        }
        return new RegionComposition(bits3, bit, bits4, bits5, bits6, bits7, bits8, bits9, bits10, bits11, sparseArray);
    }

    public static void parseSubtitlingSegment(ParsableBitArray parsableBitArray, SubtitleService subtitleService) {
        RegionComposition regionComposition;
        int bits = parsableBitArray.readBits(8);
        int bits2 = parsableBitArray.readBits(16);
        int bits3 = parsableBitArray.readBits(16);
        int bytePosition = parsableBitArray.getBytePosition() + bits3;
        if (bits3 * 8 > parsableBitArray.bitsLeft()) {
            Log.w("DvbParser", "Data field length exceeds limit");
            parsableBitArray.skipBits(parsableBitArray.bitsLeft());
            return;
        }
        switch (bits) {
            case 16:
                if (bits2 == subtitleService.subtitlePageId) {
                    PageComposition pageComposition = subtitleService.pageComposition;
                    PageComposition pageComposition2 = parsePageComposition(parsableBitArray, bits3);
                    if (pageComposition2.state != 0) {
                        subtitleService.pageComposition = pageComposition2;
                        subtitleService.regions.clear();
                        subtitleService.cluts.clear();
                        subtitleService.objects.clear();
                    } else if (pageComposition != null && pageComposition.version != pageComposition2.version) {
                        subtitleService.pageComposition = pageComposition2;
                    }
                }
                break;
            case 17:
                PageComposition pageComposition3 = subtitleService.pageComposition;
                if (bits2 == subtitleService.subtitlePageId && pageComposition3 != null) {
                    RegionComposition regionComposition2 = parseRegionComposition(parsableBitArray, bits3);
                    if (pageComposition3.state == 0 && (regionComposition = subtitleService.regions.get(regionComposition2.id)) != null) {
                        regionComposition2.mergeFrom(regionComposition);
                    }
                    subtitleService.regions.put(regionComposition2.id, regionComposition2);
                }
                break;
            case 18:
                if (bits2 == subtitleService.subtitlePageId) {
                    ClutDefinition clutDefinition = parseClutDefinition(parsableBitArray, bits3);
                    subtitleService.cluts.put(clutDefinition.id, clutDefinition);
                } else if (bits2 == subtitleService.ancillaryPageId) {
                    ClutDefinition clutDefinition2 = parseClutDefinition(parsableBitArray, bits3);
                    subtitleService.ancillaryCluts.put(clutDefinition2.id, clutDefinition2);
                }
                break;
            case 19:
                if (bits2 == subtitleService.subtitlePageId) {
                    ObjectData objectData = parseObjectData(parsableBitArray);
                    subtitleService.objects.put(objectData.id, objectData);
                } else if (bits2 == subtitleService.ancillaryPageId) {
                    ObjectData objectData2 = parseObjectData(parsableBitArray);
                    subtitleService.ancillaryObjects.put(objectData2.id, objectData2);
                }
                break;
            case 20:
                if (bits2 == subtitleService.subtitlePageId) {
                    subtitleService.displayDefinition = parseDisplayDefinition(parsableBitArray);
                }
                break;
        }
        parsableBitArray.skipBytes(bytePosition - parsableBitArray.getBytePosition());
    }

    public List<Cue> decode(byte[] bArr, int i) {
        SparseArray<PageRegion> sparseArray;
        int i2;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, i);
        while (parsableBitArray.bitsLeft() >= 48 && parsableBitArray.readBits(8) == 15) {
            parseSubtitlingSegment(parsableBitArray, this.subtitleService);
        }
        SubtitleService subtitleService = this.subtitleService;
        PageComposition pageComposition = subtitleService.pageComposition;
        if (pageComposition == null) {
            return Collections.EMPTY_LIST;
        }
        DisplayDefinition displayDefinition = subtitleService.displayDefinition;
        if (displayDefinition == null) {
            displayDefinition = this.defaultDisplayDefinition;
        }
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || displayDefinition.width + 1 != bitmap.getWidth() || displayDefinition.height + 1 != this.bitmap.getHeight()) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(displayDefinition.width + 1, displayDefinition.height + 1, Bitmap.Config.ARGB_8888);
            this.bitmap = bitmapCreateBitmap;
            this.canvas.setBitmap(bitmapCreateBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<PageRegion> sparseArray2 = pageComposition.regions;
        int i3 = 0;
        while (i3 < sparseArray2.size()) {
            this.canvas.save();
            PageRegion pageRegionValueAt = sparseArray2.valueAt(i3);
            RegionComposition regionComposition = this.subtitleService.regions.get(sparseArray2.keyAt(i3));
            int i4 = pageRegionValueAt.horizontalAddress + displayDefinition.horizontalPositionMinimum;
            int i5 = pageRegionValueAt.verticalAddress + displayDefinition.verticalPositionMinimum;
            this.canvas.clipRect(i4, i5, Math.min(regionComposition.width + i4, displayDefinition.horizontalPositionMaximum), Math.min(regionComposition.height + i5, displayDefinition.verticalPositionMaximum));
            ClutDefinition clutDefinition = this.subtitleService.cluts.get(regionComposition.clutId);
            if (clutDefinition == null && (clutDefinition = this.subtitleService.ancillaryCluts.get(regionComposition.clutId)) == null) {
                clutDefinition = this.defaultClutDefinition;
            }
            ClutDefinition clutDefinition2 = clutDefinition;
            SparseArray<RegionObject> sparseArray3 = regionComposition.regionObjects;
            int i6 = 0;
            while (i6 < sparseArray3.size()) {
                int iKeyAt = sparseArray3.keyAt(i6);
                RegionObject regionObjectValueAt = sparseArray3.valueAt(i6);
                ObjectData objectData = this.subtitleService.objects.get(iKeyAt);
                if (objectData == null) {
                    objectData = this.subtitleService.ancillaryObjects.get(iKeyAt);
                }
                if (objectData != null) {
                    sparseArray = sparseArray2;
                    i2 = i6;
                    paintPixelDataSubBlocks(objectData, clutDefinition2, regionComposition.depth, regionObjectValueAt.horizontalPosition + i4, regionObjectValueAt.verticalPosition + i5, objectData.nonModifyingColorFlag ? null : this.defaultPaint, this.canvas);
                } else {
                    sparseArray = sparseArray2;
                    i2 = i6;
                }
                i6 = i2 + 1;
                sparseArray2 = sparseArray;
            }
            SparseArray<PageRegion> sparseArray4 = sparseArray2;
            if (regionComposition.fillFlag) {
                int i7 = regionComposition.depth;
                this.fillRegionPaint.setColor(i7 == 3 ? clutDefinition2.clutEntries8Bit[regionComposition.pixelCode8Bit] : i7 == 2 ? clutDefinition2.clutEntries4Bit[regionComposition.pixelCode4Bit] : clutDefinition2.clutEntries2Bit[regionComposition.pixelCode2Bit]);
                this.canvas.drawRect(i4, i5, regionComposition.width + i4, regionComposition.height + i5, this.fillRegionPaint);
            }
            Cue.Builder builder = new Cue.Builder();
            builder.bitmap = Bitmap.createBitmap(this.bitmap, i4, i5, regionComposition.width, regionComposition.height);
            float f = i4;
            int i8 = displayDefinition.width;
            builder.position = f / i8;
            builder.positionAnchor = 0;
            int i9 = displayDefinition.height;
            builder.line = i5 / i9;
            builder.lineType = 0;
            builder.lineAnchor = 0;
            builder.size = regionComposition.width / i8;
            builder.bitmapHeight = regionComposition.height / i9;
            arrayList.add(builder.build());
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.canvas.restore();
            i3++;
            sparseArray2 = sparseArray4;
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    public void reset() {
        this.subtitleService.reset();
    }
}
