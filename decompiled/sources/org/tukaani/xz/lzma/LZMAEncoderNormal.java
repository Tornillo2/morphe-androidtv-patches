package org.tukaani.xz.lzma;

import org.apache.commons.compress.archivers.zip.UnixStat;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes4.dex */
public final class LZMAEncoderNormal extends LZMAEncoder {
    public static final /* synthetic */ boolean $assertionsDisabled;
    public static int EXTRA_SIZE_AFTER = 0;
    public static int EXTRA_SIZE_BEFORE = 0;
    public static final int OPTS = 4096;
    public static /* synthetic */ Class class$org$tukaani$xz$lzma$LZMAEncoderNormal;
    public Matches matches;
    public final State nextState;
    public int optCur;
    public int optEnd;
    public final Optimum[] opts;
    public final int[] repLens;

    static {
        if (class$org$tukaani$xz$lzma$LZMAEncoderNormal == null) {
            class$org$tukaani$xz$lzma$LZMAEncoderNormal = class$("org.tukaani.xz.lzma.LZMAEncoderNormal");
        }
        $assertionsDisabled = true;
        EXTRA_SIZE_BEFORE = 4096;
        EXTRA_SIZE_AFTER = 4096;
    }

    public LZMAEncoderNormal(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(rangeEncoder, LZEncoder.getInstance(i4, Math.max(i5, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, i6, 273, i7, i8), i, i2, i3, i4, i6);
        this.opts = new Optimum[4096];
        this.optCur = 0;
        this.optEnd = 0;
        this.repLens = new int[4];
        this.nextState = new State();
        for (int i9 = 0; i9 < 4096; i9++) {
            this.opts[i9] = new Optimum();
        }
    }

    public static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static int getMemoryUsage(int i, int i2, int i3) {
        return LZEncoder.getMemoryUsage(i, Math.max(i2, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, 273, i3) + 256;
    }

    public final void calc1BytePrices(int i, int i2, int i3, int i4) {
        boolean z;
        int i5 = this.lz.getByte(0);
        int i6 = this.lz.getByte(this.opts[this.optCur].reps[0] + 1);
        int price = this.opts[this.optCur].price + this.literalEncoder.getPrice(i5, i6, this.lz.getByte(1), i, this.opts[this.optCur].state);
        Optimum[] optimumArr = this.opts;
        int i7 = this.optCur;
        Optimum optimum = optimumArr[i7 + 1];
        if (price < optimum.price) {
            optimum.set1(price, i7, -1);
            z = true;
        } else {
            z = false;
        }
        if (i6 == i5) {
            Optimum[] optimumArr2 = this.opts;
            int i8 = this.optCur;
            Optimum optimum2 = optimumArr2[i8 + 1];
            if (optimum2.optPrev == i8 || optimum2.backPrev != 0) {
                int shortRepPrice = getShortRepPrice(i4, optimumArr2[i8].state, i2);
                Optimum[] optimumArr3 = this.opts;
                int i9 = this.optCur;
                Optimum optimum3 = optimumArr3[i9 + 1];
                if (shortRepPrice <= optimum3.price) {
                    optimum3.set1(shortRepPrice, i9, 0);
                    z = true;
                }
            }
        }
        if (z || i6 == i5 || i3 <= 2) {
            return;
        }
        int matchLen = this.lz.getMatchLen(1, this.opts[this.optCur].reps[0], Math.min(this.niceLen, i3 - 1));
        if (matchLen >= 2) {
            State state = this.nextState;
            State state2 = this.opts[this.optCur].state;
            state.getClass();
            state.state = state2.state;
            this.nextState.updateLiteral();
            int longRepAndLenPrice = getLongRepAndLenPrice(0, matchLen, this.nextState, (i + 1) & this.posMask) + price;
            int i10 = this.optCur + 1 + matchLen;
            while (true) {
                int i11 = this.optEnd;
                if (i11 >= i10) {
                    break;
                }
                Optimum[] optimumArr4 = this.opts;
                int i12 = i11 + 1;
                this.optEnd = i12;
                optimumArr4[i12].price = 1073741824;
            }
            Optimum optimum4 = this.opts[i10];
            if (longRepAndLenPrice < optimum4.price) {
                optimum4.set2(longRepAndLenPrice, this.optCur, 0);
            }
        }
    }

    public final int calcLongRepPrices(int i, int i2, int i3, int i4) {
        int i5;
        int iMin = Math.min(i3, this.niceLen);
        int i6 = 2;
        for (int i7 = 0; i7 < 4; i7++) {
            int matchLen = this.lz.getMatchLen(this.opts[this.optCur].reps[i7], iMin);
            if (matchLen >= 2) {
                while (true) {
                    int i8 = this.optEnd;
                    i5 = this.optCur;
                    if (i8 >= i5 + matchLen) {
                        break;
                    }
                    Optimum[] optimumArr = this.opts;
                    int i9 = i8 + 1;
                    this.optEnd = i9;
                    optimumArr[i9].price = 1073741824;
                }
                int longRepPrice = getLongRepPrice(i4, i7, this.opts[i5].state, i2);
                for (int i10 = matchLen; i10 >= 2; i10--) {
                    int price = this.repLenEncoder.getPrice(i10, i2) + longRepPrice;
                    Optimum[] optimumArr2 = this.opts;
                    int i11 = this.optCur;
                    Optimum optimum = optimumArr2[i11 + i10];
                    if (price < optimum.price) {
                        optimum.set1(price, i11, i7);
                    }
                }
                if (i7 == 0) {
                    i6 = matchLen + 1;
                }
                int i12 = i6;
                int matchLen2 = this.lz.getMatchLen(matchLen + 1, this.opts[this.optCur].reps[i7], Math.min(this.niceLen, (i3 - matchLen) - 1));
                if (matchLen2 >= 2) {
                    int price2 = this.repLenEncoder.getPrice(matchLen, i2) + longRepPrice;
                    State state = this.nextState;
                    State state2 = this.opts[this.optCur].state;
                    state.getClass();
                    state.state = state2.state;
                    this.nextState.updateLongRep();
                    int i13 = i + matchLen;
                    int price3 = price2 + this.literalEncoder.getPrice(this.lz.getByte(matchLen, 0), this.lz.getByte(0), this.lz.getByte(matchLen, 1), i13, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = getLongRepAndLenPrice(0, matchLen2, this.nextState, (i13 + 1) & this.posMask) + price3;
                    int i14 = this.optCur + matchLen + 1 + matchLen2;
                    while (true) {
                        int i15 = this.optEnd;
                        if (i15 >= i14) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i16 = i15 + 1;
                        this.optEnd = i16;
                        optimumArr3[i16].price = 1073741824;
                    }
                    Optimum optimum2 = this.opts[i14];
                    if (longRepAndLenPrice < optimum2.price) {
                        optimum2.set3(longRepAndLenPrice, this.optCur, i7, matchLen, 0);
                    }
                }
                i6 = i12;
            }
        }
        return i6;
    }

    public final void calcNormalMatchPrices(int i, int i2, int i3, int i4, int i5) {
        int i6;
        Matches matches;
        int[] iArr;
        int i7;
        Matches matches2 = this.matches;
        if (matches2.len[matches2.count - 1] > i3) {
            matches2.count = 0;
            while (true) {
                matches = this.matches;
                iArr = matches.len;
                i7 = matches.count;
                if (iArr[i7] >= i3) {
                    break;
                } else {
                    matches.count = i7 + 1;
                }
            }
            matches.count = i7 + 1;
            iArr[i7] = i3;
        }
        Matches matches3 = this.matches;
        if (matches3.len[matches3.count - 1] < i5) {
            return;
        }
        while (true) {
            int i8 = this.optEnd;
            i6 = this.optCur;
            Matches matches4 = this.matches;
            if (i8 >= matches4.len[matches4.count - 1] + i6) {
                break;
            }
            Optimum[] optimumArr = this.opts;
            int i9 = i8 + 1;
            this.optEnd = i9;
            optimumArr[i9].price = 1073741824;
        }
        int normalMatchPrice = getNormalMatchPrice(i4, this.opts[i6].state);
        int i10 = 0;
        while (i5 > this.matches.len[i10]) {
            i10++;
        }
        int i11 = i5;
        while (true) {
            int i12 = this.matches.dist[i10];
            int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, i12, i11, i2);
            Optimum[] optimumArr2 = this.opts;
            int i13 = this.optCur;
            Optimum optimum = optimumArr2[i13 + i11];
            if (matchAndLenPrice < optimum.price) {
                optimum.set1(matchAndLenPrice, i13, i12 + 4);
            }
            if (i11 == this.matches.len[i10]) {
                int matchLen = this.lz.getMatchLen(i11 + 1, i12, Math.min(this.niceLen, (i3 - i11) - 1));
                if (matchLen >= 2) {
                    State state = this.nextState;
                    State state2 = this.opts[this.optCur].state;
                    state.getClass();
                    state.state = state2.state;
                    this.nextState.updateMatch();
                    int i14 = i + i11;
                    int price = matchAndLenPrice + this.literalEncoder.getPrice(this.lz.getByte(i11, 0), this.lz.getByte(0), this.lz.getByte(i11, 1), i14, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = getLongRepAndLenPrice(0, matchLen, this.nextState, (i14 + 1) & this.posMask) + price;
                    int i15 = this.optCur + i11 + 1 + matchLen;
                    while (true) {
                        int i16 = this.optEnd;
                        if (i16 >= i15) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i17 = i16 + 1;
                        this.optEnd = i17;
                        optimumArr3[i17].price = 1073741824;
                    }
                    Optimum optimum2 = this.opts[i15];
                    if (longRepAndLenPrice < optimum2.price) {
                        optimum2.set3(longRepAndLenPrice, this.optCur, i12 + 4, i11, 0);
                    }
                }
                i10++;
                if (i10 == this.matches.count) {
                    return;
                }
            }
            i11++;
        }
    }

    public final int convertOpts() {
        int i = this.optCur;
        this.optEnd = i;
        int i2 = this.opts[i].optPrev;
        while (true) {
            Optimum[] optimumArr = this.opts;
            int i3 = this.optCur;
            Optimum optimum = optimumArr[i3];
            if (optimum.prev1IsLiteral) {
                Optimum optimum2 = optimumArr[i2];
                optimum2.optPrev = i3;
                optimum2.backPrev = -1;
                int i4 = i2 - 1;
                this.optCur = i2;
                if (optimum.hasPrev2) {
                    Optimum optimum3 = optimumArr[i4];
                    optimum3.optPrev = i2;
                    optimum3.backPrev = optimum.backPrev2;
                    this.optCur = i4;
                    i2 = optimum.optPrev2;
                } else {
                    i2 = i4;
                }
            }
            Optimum optimum4 = optimumArr[i2];
            int i5 = optimum4.optPrev;
            optimum4.optPrev = this.optCur;
            this.optCur = i2;
            if (i2 <= 0) {
                int i6 = optimumArr[0].optPrev;
                this.optCur = i6;
                this.back = optimumArr[i6].backPrev;
                return i6;
            }
            i2 = i5;
        }
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    public int getNextSymbol() {
        int i;
        int i2;
        LZMAEncoderNormal lZMAEncoderNormal = this;
        int i3 = lZMAEncoderNormal.optCur;
        int i4 = lZMAEncoderNormal.optEnd;
        if (i3 < i4) {
            Optimum[] optimumArr = lZMAEncoderNormal.opts;
            int i5 = optimumArr[i3].optPrev;
            int i6 = i5 - i3;
            lZMAEncoderNormal.optCur = i5;
            lZMAEncoderNormal.back = optimumArr[i5].backPrev;
            return i6;
        }
        if (!$assertionsDisabled && i3 != i4) {
            throw new AssertionError();
        }
        lZMAEncoderNormal.optCur = 0;
        lZMAEncoderNormal.optEnd = 0;
        lZMAEncoderNormal.back = -1;
        if (lZMAEncoderNormal.readAhead == -1) {
            lZMAEncoderNormal.matches = lZMAEncoderNormal.getMatches();
        }
        int iMin = Math.min(lZMAEncoderNormal.lz.getAvail(), 273);
        if (iMin >= 2) {
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (i7 >= 4) {
                    break;
                }
                lZMAEncoderNormal.repLens[i7] = lZMAEncoderNormal.lz.getMatchLen(lZMAEncoderNormal.reps[i7], iMin);
                int[] iArr = lZMAEncoderNormal.repLens;
                int i9 = iArr[i7];
                if (i9 < 2) {
                    iArr[i7] = 0;
                } else if (i9 > iArr[i8]) {
                    i8 = i7;
                }
                i7++;
            }
            int i10 = lZMAEncoderNormal.repLens[i8];
            int i11 = lZMAEncoderNormal.niceLen;
            if (i10 >= i11) {
                lZMAEncoderNormal.back = i8;
                lZMAEncoderNormal.skip(i10 - 1);
                return lZMAEncoderNormal.repLens[i8];
            }
            Matches matches = lZMAEncoderNormal.matches;
            int i12 = matches.count;
            if (i12 > 0) {
                int i13 = i12 - 1;
                i2 = matches.len[i13];
                int i14 = matches.dist[i13];
                if (i2 >= i11) {
                    lZMAEncoderNormal.back = i14 + 4;
                    lZMAEncoderNormal.skip(i2 - 1);
                    return i2;
                }
            } else {
                i2 = 0;
            }
            int i15 = lZMAEncoderNormal.lz.getByte(0);
            int i16 = lZMAEncoderNormal.lz.getByte(lZMAEncoderNormal.reps[0] + 1);
            if (i2 >= 2 || i15 == i16 || lZMAEncoderNormal.repLens[i8] >= 2) {
                int pos = lZMAEncoderNormal.lz.getPos();
                int i17 = lZMAEncoderNormal.posMask & pos;
                lZMAEncoderNormal.opts[1].set1(lZMAEncoderNormal.literalEncoder.getPrice(i15, i16, lZMAEncoderNormal.lz.getByte(1), pos, lZMAEncoderNormal.state), 0, -1);
                int anyMatchPrice = lZMAEncoderNormal.getAnyMatchPrice(lZMAEncoderNormal.state, i17);
                int anyRepPrice = lZMAEncoderNormal.getAnyRepPrice(anyMatchPrice, lZMAEncoderNormal.state);
                if (i16 == i15) {
                    int shortRepPrice = lZMAEncoderNormal.getShortRepPrice(anyRepPrice, lZMAEncoderNormal.state, i17);
                    Optimum optimum = lZMAEncoderNormal.opts[1];
                    if (shortRepPrice < optimum.price) {
                        optimum.set1(shortRepPrice, 0, 0);
                    }
                }
                int iMax = Math.max(i2, lZMAEncoderNormal.repLens[i8]);
                lZMAEncoderNormal.optEnd = iMax;
                if (iMax < 2) {
                    if (!$assertionsDisabled && iMax != 0) {
                        throw new AssertionError(lZMAEncoderNormal.optEnd);
                    }
                    lZMAEncoderNormal.back = lZMAEncoderNormal.opts[1].backPrev;
                    return 1;
                }
                lZMAEncoderNormal.updatePrices();
                State state = lZMAEncoderNormal.opts[0].state;
                State state2 = lZMAEncoderNormal.state;
                state.getClass();
                state.state = state2.state;
                System.arraycopy(lZMAEncoderNormal.reps, 0, lZMAEncoderNormal.opts[0].reps, 0, 4);
                for (int i18 = lZMAEncoderNormal.optEnd; i18 >= 2; i18--) {
                    lZMAEncoderNormal.opts[i18].price = 1073741824;
                }
                int i19 = 0;
                for (i = 4; i19 < i; i = 4) {
                    int i20 = lZMAEncoderNormal.repLens[i19];
                    if (i20 >= 2) {
                        int longRepPrice = lZMAEncoderNormal.getLongRepPrice(anyRepPrice, i19, lZMAEncoderNormal.state, i17);
                        do {
                            int price = lZMAEncoderNormal.repLenEncoder.getPrice(i20, i17) + longRepPrice;
                            Optimum optimum2 = lZMAEncoderNormal.opts[i20];
                            if (price < optimum2.price) {
                                optimum2.set1(price, 0, i19);
                            }
                            i20--;
                        } while (i20 >= 2);
                    }
                    i19++;
                }
                int iMax2 = Math.max(lZMAEncoderNormal.repLens[0] + 1, 2);
                if (iMax2 <= i2) {
                    int normalMatchPrice = lZMAEncoderNormal.getNormalMatchPrice(anyMatchPrice, lZMAEncoderNormal.state);
                    int i21 = 0;
                    while (iMax2 > lZMAEncoderNormal.matches.len[i21]) {
                        i21++;
                    }
                    while (true) {
                        int i22 = lZMAEncoderNormal.matches.dist[i21];
                        int matchAndLenPrice = lZMAEncoderNormal.getMatchAndLenPrice(normalMatchPrice, i22, iMax2, i17);
                        Optimum optimum3 = lZMAEncoderNormal.opts[iMax2];
                        if (matchAndLenPrice < optimum3.price) {
                            optimum3.set1(matchAndLenPrice, 0, i22 + 4);
                        }
                        Matches matches2 = lZMAEncoderNormal.matches;
                        if (iMax2 == matches2.len[i21] && (i21 = i21 + 1) == matches2.count) {
                            break;
                        }
                        iMax2++;
                    }
                }
                int iMin2 = Math.min(lZMAEncoderNormal.lz.getAvail(), UnixStat.PERM_MASK);
                while (true) {
                    int i23 = lZMAEncoderNormal.optCur + 1;
                    lZMAEncoderNormal.optCur = i23;
                    if (i23 >= lZMAEncoderNormal.optEnd) {
                        break;
                    }
                    Matches matches3 = lZMAEncoderNormal.getMatches();
                    lZMAEncoderNormal.matches = matches3;
                    int i24 = matches3.count;
                    if (i24 > 0 && matches3.len[i24 - 1] >= lZMAEncoderNormal.niceLen) {
                        break;
                    }
                    int i25 = iMin2 - 1;
                    int i26 = pos + 1;
                    int i27 = lZMAEncoderNormal.posMask & i26;
                    lZMAEncoderNormal.updateOptStateAndReps();
                    Optimum optimum4 = lZMAEncoderNormal.opts[lZMAEncoderNormal.optCur];
                    int anyMatchPrice2 = lZMAEncoderNormal.getAnyMatchPrice(optimum4.state, i27) + optimum4.price;
                    int anyRepPrice2 = lZMAEncoderNormal.getAnyRepPrice(anyMatchPrice2, lZMAEncoderNormal.opts[lZMAEncoderNormal.optCur].state);
                    lZMAEncoderNormal.calc1BytePrices(i26, i27, i25, anyRepPrice2);
                    if (i25 >= 2) {
                        int iCalcLongRepPrices = lZMAEncoderNormal.calcLongRepPrices(i26, i27, i25, anyRepPrice2);
                        if (lZMAEncoderNormal.matches.count > 0) {
                            lZMAEncoderNormal.calcNormalMatchPrices(i26, i27, i25, anyMatchPrice2, iCalcLongRepPrices);
                        }
                    }
                    lZMAEncoderNormal = this;
                    pos = i26;
                    iMin2 = i25;
                }
                return convertOpts();
            }
        }
        return 1;
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder, org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        this.optCur = 0;
        this.optEnd = 0;
        super.reset();
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateOptStateAndReps() {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lzma.LZMAEncoderNormal.updateOptStateAndReps():void");
    }
}
