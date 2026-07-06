package org.tukaani.xz.lzma;

/* JADX INFO: loaded from: classes4.dex */
public final class State {
    public static final int LIT_LIT = 0;
    public static final int LIT_LONGREP = 8;
    public static final int LIT_MATCH = 7;
    public static final int LIT_SHORTREP = 9;
    public static final int LIT_STATES = 7;
    public static final int MATCH_LIT = 4;
    public static final int MATCH_LIT_LIT = 1;
    public static final int NONLIT_MATCH = 10;
    public static final int NONLIT_REP = 11;
    public static final int REP_LIT = 5;
    public static final int REP_LIT_LIT = 2;
    public static final int SHORTREP_LIT = 6;
    public static final int SHORTREP_LIT_LIT = 3;
    public static final int STATES = 12;
    public int state;

    public State() {
    }

    public int get() {
        return this.state;
    }

    public boolean isLiteral() {
        return this.state < 7;
    }

    public void reset() {
        this.state = 0;
    }

    public void set(State state) {
        this.state = state.state;
    }

    public void updateLiteral() {
        int i = this.state;
        this.state = i <= 3 ? 0 : i <= 9 ? i - 3 : i - 6;
    }

    public void updateLongRep() {
        this.state = this.state < 7 ? 8 : 11;
    }

    public void updateMatch() {
        this.state = this.state >= 7 ? 10 : 7;
    }

    public void updateShortRep() {
        this.state = this.state < 7 ? 9 : 11;
    }

    public State(State state) {
        this.state = state.state;
    }
}
