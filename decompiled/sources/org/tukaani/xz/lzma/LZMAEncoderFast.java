package org.tukaani.xz.lzma;

import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes4.dex */
public final class LZMAEncoderFast extends LZMAEncoder {
    public static int EXTRA_SIZE_AFTER = 272;
    public static int EXTRA_SIZE_BEFORE = 1;
    public Matches matches;

    public LZMAEncoderFast(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(rangeEncoder, LZEncoder.getInstance(i4, Math.max(i5, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, i6, 273, i7, i8), i, i2, i3, i4, i6);
        this.matches = null;
    }

    public static int getMemoryUsage(int i, int i2, int i3) {
        return LZEncoder.getMemoryUsage(i, Math.max(i2, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, 273, i3);
    }

    public final boolean changePair(int i, int i2) {
        return i < (i2 >>> 7);
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    public int getNextSymbol() {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.readAhead == -1) {
            this.matches = getMatches();
        }
        this.back = -1;
        int iMin = Math.min(this.lz.getAvail(), 273);
        if (iMin < 2) {
            return 1;
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < 4; i7++) {
            int matchLen = this.lz.getMatchLen(this.reps[i7], iMin);
            if (matchLen >= 2) {
                if (matchLen >= this.niceLen) {
                    this.back = i7;
                    skip(matchLen - 1);
                    return matchLen;
                }
                if (matchLen > i5) {
                    i6 = i7;
                    i5 = matchLen;
                }
            }
        }
        Matches matches = this.matches;
        int i8 = matches.count;
        if (i8 > 0) {
            i2 = matches.len[i8 - 1];
            i = matches.dist[i8 - 1];
            if (i2 >= this.niceLen) {
                this.back = i + 4;
                i3 = i2 - 1;
                skip(i3);
                return i2;
            }
            while (true) {
                Matches matches2 = this.matches;
                int i9 = matches2.count;
                if (i9 <= 1 || i2 != matches2.len[i9 - 2] + 1 || !changePair(matches2.dist[i9 - 2], i)) {
                    break;
                }
                Matches matches3 = this.matches;
                int i10 = matches3.count;
                matches3.count = i10 - 1;
                i2 = matches3.len[i10 - 2];
                i = matches3.dist[i10 - 2];
            }
            if (i2 == 2 && i >= 128) {
                i2 = 1;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        if (i5 >= 2 && (i5 + 1 >= i2 || ((i5 + 2 >= i2 && i >= 512) || (i5 + 3 >= i2 && i >= 32768)))) {
            this.back = i6;
            skip(i5 - 1);
            return i5;
        }
        if (i2 < 2 || iMin <= 2) {
            return 1;
        }
        Matches matches4 = getMatches();
        this.matches = matches4;
        int i11 = matches4.count;
        if (i11 > 0) {
            int i12 = matches4.len[i11 - 1];
            int i13 = matches4.dist[i11 - 1];
            if ((i12 >= i2 && i13 < i) || ((i12 == (i4 = i2 + 1) && !changePair(i, i13)) || i12 > i4 || (i12 + 1 >= i2 && i2 >= 3 && changePair(i13, i)))) {
                return 1;
            }
        }
        int iMax = Math.max(i2 - 1, 2);
        for (int i14 = 0; i14 < 4; i14++) {
            if (this.lz.getMatchLen(this.reps[i14], iMax) == iMax) {
                return 1;
            }
        }
        this.back = i + 4;
        i3 = i2 - 2;
        skip(i3);
        return i2;
    }
}
