package org.apache.commons.text.diff;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class StringsComparator {
    public final String left;
    public final String right;
    public final int[] vDown;
    public final int[] vUp;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Snake {
        public final int diag;
        public final int end;
        public final int start;

        public Snake(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.diag = i3;
        }

        public int getDiag() {
            return this.diag;
        }

        public int getEnd() {
            return this.end;
        }

        public int getStart() {
            return this.start;
        }
    }

    public StringsComparator(String str, String str2) {
        this.left = str;
        this.right = str2;
        int length = str2.length() + str.length() + 2;
        this.vDown = new int[length];
        this.vUp = new int[length];
    }

    public final void buildScript(int i, int i2, int i3, int i4, EditScript<Character> editScript) {
        int i5;
        Snake middleSnake = getMiddleSnake(i, i2, i3, i4);
        if (middleSnake == null || (((i5 = middleSnake.start) == i2 && middleSnake.diag == i2 - i4) || (middleSnake.end == i && middleSnake.diag == i - i3))) {
            int i6 = i;
            int i7 = i3;
            while (true) {
                if (i6 >= i2 && i7 >= i4) {
                    return;
                }
                if (i6 < i2 && i7 < i4 && this.left.charAt(i6) == this.right.charAt(i7)) {
                    editScript.append(new KeepCommand<>(Character.valueOf(this.left.charAt(i6))));
                    i6++;
                } else if (i2 - i > i4 - i3) {
                    editScript.append(new DeleteCommand<>(Character.valueOf(this.left.charAt(i6))));
                    i6++;
                } else {
                    editScript.append(new InsertCommand<>(Character.valueOf(this.right.charAt(i7))));
                }
                i7++;
            }
        } else {
            buildScript(i, i5, i3, i5 - middleSnake.diag, editScript);
            int i8 = middleSnake.start;
            while (true) {
                int i9 = middleSnake.end;
                if (i8 >= i9) {
                    buildScript(i9, i2, i9 - middleSnake.diag, i4, editScript);
                    return;
                } else {
                    editScript.append(new KeepCommand<>(Character.valueOf(this.left.charAt(i8))));
                    i8++;
                }
            }
        }
    }

    public final Snake buildSnake(int i, int i2, int i3, int i4) {
        int i5 = i;
        while (true) {
            int i6 = i5 - i2;
            if (i6 >= i4 || i5 >= i3 || this.left.charAt(i5) != this.right.charAt(i6)) {
                break;
            }
            i5++;
        }
        return new Snake(i, i5, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0105, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.apache.commons.text.diff.StringsComparator.Snake getMiddleSnake(int r12, int r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.text.diff.StringsComparator.getMiddleSnake(int, int, int, int):org.apache.commons.text.diff.StringsComparator$Snake");
    }

    public EditScript<Character> getScript() {
        EditScript<Character> editScript = new EditScript<>();
        buildScript(0, this.left.length(), 0, this.right.length(), editScript);
        return editScript;
    }
}
