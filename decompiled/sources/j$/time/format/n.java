package j$.time.format;

/* JADX INFO: loaded from: classes2.dex */
public final class n implements f {
    public final String a;

    public n(String str) {
        this.a = str;
    }

    @Override // j$.time.format.f
    public final boolean j(t tVar, StringBuilder sb) {
        sb.append(this.a);
        return true;
    }

    @Override // j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        if (i > charSequence.length() || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        String str = this.a;
        return !qVar.g(charSequence, i, str, 0, str.length()) ? ~i : str.length() + i;
    }

    public final String toString() {
        return "'" + this.a.replace("'", "''") + "'";
    }
}
