package org.tukaani.xz.lzma;

import java.lang.reflect.Array;
import org.tukaani.xz.rangecoder.RangeCoder;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LZMACoder {
    public static final int ALIGN_BITS = 4;
    public static final int ALIGN_MASK = 15;
    public static final int ALIGN_SIZE = 16;
    public static final int DIST_MODEL_END = 14;
    public static final int DIST_MODEL_START = 4;
    public static final int DIST_SLOTS = 64;
    public static final int DIST_STATES = 4;
    public static final int FULL_DISTANCES = 128;
    public static final int MATCH_LEN_MAX = 273;
    public static final int MATCH_LEN_MIN = 2;
    public static final int POS_STATES_MAX = 16;
    public static final int REPS = 4;
    public final short[] distAlign;
    public final short[][] distSlots;
    public final short[][] distSpecial;
    public final short[][] isMatch;
    public final short[] isRep;
    public final short[] isRep0;
    public final short[][] isRep0Long;
    public final short[] isRep1;
    public final short[] isRep2;
    public final int posMask;
    public final int[] reps = new int[4];
    public final State state = new State();

    public abstract class LengthCoder {
        public static final int HIGH_SYMBOLS = 256;
        public static final int LOW_SYMBOLS = 8;
        public static final int MID_SYMBOLS = 8;
        public final short[] choice = new short[2];
        public final short[] high;
        public final short[][] low;
        public final short[][] mid;

        public LengthCoder() {
            Class cls = Short.TYPE;
            this.low = (short[][]) Array.newInstance((Class<?>) cls, 16, 8);
            this.mid = (short[][]) Array.newInstance((Class<?>) cls, 16, 8);
            this.high = new short[256];
        }

        public void reset() {
            RangeCoder.initProbs(this.choice);
            int i = 0;
            while (true) {
                short[][] sArr = this.low;
                if (i >= sArr.length) {
                    break;
                }
                RangeCoder.initProbs(sArr[i]);
                i++;
            }
            for (int i2 = 0; i2 < this.low.length; i2++) {
                RangeCoder.initProbs(this.mid[i2]);
            }
            RangeCoder.initProbs(this.high);
        }
    }

    public abstract class LiteralCoder {
        public final int lc;
        public final int literalPosMask;

        public abstract class LiteralSubcoder {
            public final short[] probs = new short[768];

            public LiteralSubcoder() {
            }

            public void reset() {
                RangeCoder.initProbs(this.probs);
            }
        }

        public LiteralCoder(int i, int i2) {
            this.lc = i;
            this.literalPosMask = (1 << i2) - 1;
        }

        public final int getSubcoderIndex(int i, int i2) {
            int i3 = this.lc;
            return (i >> (8 - i3)) + ((i2 & this.literalPosMask) << i3);
        }
    }

    public LZMACoder(int i) {
        Class cls = Short.TYPE;
        this.isMatch = (short[][]) Array.newInstance((Class<?>) cls, 12, 16);
        this.isRep = new short[12];
        this.isRep0 = new short[12];
        this.isRep1 = new short[12];
        this.isRep2 = new short[12];
        this.isRep0Long = (short[][]) Array.newInstance((Class<?>) cls, 12, 16);
        this.distSlots = (short[][]) Array.newInstance((Class<?>) cls, 4, 64);
        this.distSpecial = new short[][]{new short[2], new short[2], new short[4], new short[4], new short[8], new short[8], new short[16], new short[16], new short[32], new short[32]};
        this.distAlign = new short[16];
        this.posMask = (1 << i) - 1;
    }

    public static final int getDistState(int i) {
        if (i < 6) {
            return i - 2;
        }
        return 3;
    }

    public void reset() {
        int[] iArr = this.reps;
        int i = 0;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        this.state.state = 0;
        int i2 = 0;
        while (true) {
            short[][] sArr = this.isMatch;
            if (i2 >= sArr.length) {
                break;
            }
            RangeCoder.initProbs(sArr[i2]);
            i2++;
        }
        RangeCoder.initProbs(this.isRep);
        RangeCoder.initProbs(this.isRep0);
        RangeCoder.initProbs(this.isRep1);
        RangeCoder.initProbs(this.isRep2);
        int i3 = 0;
        while (true) {
            short[][] sArr2 = this.isRep0Long;
            if (i3 >= sArr2.length) {
                break;
            }
            RangeCoder.initProbs(sArr2[i3]);
            i3++;
        }
        int i4 = 0;
        while (true) {
            short[][] sArr3 = this.distSlots;
            if (i4 >= sArr3.length) {
                break;
            }
            RangeCoder.initProbs(sArr3[i4]);
            i4++;
        }
        while (true) {
            short[][] sArr4 = this.distSpecial;
            if (i >= sArr4.length) {
                RangeCoder.initProbs(this.distAlign);
                return;
            } else {
                RangeCoder.initProbs(sArr4[i]);
                i++;
            }
        }
    }
}
