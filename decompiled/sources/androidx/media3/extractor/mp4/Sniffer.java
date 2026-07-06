package androidx.media3.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    public static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153, 1769172332, 1885955686};
    public static final int SEARCH_LENGTH = 4096;

    public static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static boolean sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        boolean z3;
        int i;
        int i2;
        boolean z4;
        long length = extractorInput.getLength();
        long j = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        long j2 = -1;
        int i3 = (length > (-1L) ? 1 : (length == (-1L) ? 0 : -1));
        if (i3 != 0 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j = length;
        }
        int i4 = (int) j;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i5 = 0;
        int i6 = 0;
        boolean z5 = false;
        while (i6 < i4) {
            parsableByteArray.reset(8);
            if (!extractorInput.peekFully(parsableByteArray.data, i5, 8, true)) {
                break;
            }
            long unsignedInt = parsableByteArray.readUnsignedInt();
            int i7 = parsableByteArray.readInt();
            if (unsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                i = i6;
                unsignedInt = parsableByteArray.readLong();
                i2 = 16;
            } else {
                if (unsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j2) {
                        unsignedInt = (length2 - extractorInput.getPeekPosition()) + ((long) 8);
                    }
                }
                i = i6;
                i2 = 8;
            }
            long j3 = i2;
            if (unsignedInt < j3) {
                return false;
            }
            int i8 = i + i2;
            if (i7 == 1836019574) {
                i4 += (int) unsignedInt;
                if (i3 != 0 && i4 > length) {
                    i4 = (int) length;
                }
                i6 = i8;
            } else {
                if (i7 == 1836019558 || i7 == 1836475768) {
                    z3 = true;
                    break;
                }
                if (i7 == 1835295092) {
                    z5 = true;
                }
                int i9 = i3;
                if ((((long) i8) + unsignedInt) - j3 >= i4) {
                    break;
                }
                int i10 = (int) (unsignedInt - j3);
                i6 = i8 + i10;
                if (i7 == 1718909296) {
                    if (i10 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(i10);
                    extractorInput.peekFully(parsableByteArray.data, 0, i10);
                    int i11 = i10 / 4;
                    int i12 = 0;
                    while (true) {
                        if (i12 >= i11) {
                            z4 = z5;
                            break;
                        }
                        if (i12 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt(), z2)) {
                            z4 = true;
                            break;
                        }
                        i12++;
                    }
                    if (!z4) {
                        return false;
                    }
                    z5 = z4;
                } else if (i10 != 0) {
                    extractorInput.advancePeekPosition(i10);
                }
                i3 = i9;
            }
            j2 = -1;
            i5 = 0;
        }
        z3 = false;
        return z5 && z == z3;
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, false, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }
}
