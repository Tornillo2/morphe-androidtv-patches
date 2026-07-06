package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public abstract class LineBuffer {
    public StringBuilder line = new StringBuilder();
    public boolean sawReturn;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void add(char[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            boolean r0 = r6.sawReturn
            r1 = 0
            r2 = 10
            r3 = 1
            if (r0 == 0) goto L19
            if (r9 <= 0) goto L19
            char r0 = r7[r8]
            if (r0 != r2) goto L10
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            r6.finishLine(r0)
            if (r0 == 0) goto L19
            int r0 = r8 + 1
            goto L1a
        L19:
            r0 = r8
        L1a:
            int r8 = r8 + r9
            r9 = r0
        L1c:
            if (r0 >= r8) goto L51
            char r4 = r7[r0]
            if (r4 == r2) goto L44
            r5 = 13
            if (r4 == r5) goto L27
            goto L4f
        L27:
            java.lang.StringBuilder r4 = r6.line
            int r5 = r0 - r9
            r4.append(r7, r9, r5)
            r6.sawReturn = r3
            int r9 = r0 + 1
            if (r9 >= r8) goto L41
            char r4 = r7[r9]
            if (r4 != r2) goto L3a
            r4 = 1
            goto L3b
        L3a:
            r4 = 0
        L3b:
            r6.finishLine(r4)
            if (r4 == 0) goto L41
            r0 = r9
        L41:
            int r9 = r0 + 1
            goto L4f
        L44:
            java.lang.StringBuilder r4 = r6.line
            int r5 = r0 - r9
            r4.append(r7, r9, r5)
            r6.finishLine(r3)
            goto L41
        L4f:
            int r0 = r0 + r3
            goto L1c
        L51:
            java.lang.StringBuilder r0 = r6.line
            int r8 = r8 - r9
            r0.append(r7, r9, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.LineBuffer.add(char[], int, int):void");
    }

    public void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }

    @CanIgnoreReturnValue
    public final boolean finishLine(boolean sawNewline) throws IOException {
        handleLine(this.line.toString(), this.sawReturn ? sawNewline ? "\r\n" : StringUtils.CR : sawNewline ? StringUtils.LF : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return sawNewline;
    }

    public abstract void handleLine(String line, String end) throws IOException;
}
