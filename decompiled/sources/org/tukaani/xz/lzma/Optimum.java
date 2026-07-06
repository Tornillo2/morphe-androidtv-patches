package org.tukaani.xz.lzma;

/* JADX INFO: loaded from: classes4.dex */
public final class Optimum {
    public static final int INFINITY_PRICE = 1073741824;
    public int backPrev;
    public int backPrev2;
    public boolean hasPrev2;
    public int optPrev;
    public int optPrev2;
    public boolean prev1IsLiteral;
    public int price;
    public final State state = new State();
    public final int[] reps = new int[4];

    public void reset() {
        this.price = 1073741824;
    }

    public void set1(int i, int i2, int i3) {
        this.price = i;
        this.optPrev = i2;
        this.backPrev = i3;
        this.prev1IsLiteral = false;
    }

    public void set2(int i, int i2, int i3) {
        this.price = i;
        this.optPrev = i2 + 1;
        this.backPrev = i3;
        this.prev1IsLiteral = true;
        this.hasPrev2 = false;
    }

    public void set3(int i, int i2, int i3, int i4, int i5) {
        this.price = i;
        this.optPrev = i4 + i2 + 1;
        this.backPrev = i5;
        this.prev1IsLiteral = true;
        this.hasPrev2 = true;
        this.optPrev2 = i2;
        this.backPrev2 = i3;
    }
}
