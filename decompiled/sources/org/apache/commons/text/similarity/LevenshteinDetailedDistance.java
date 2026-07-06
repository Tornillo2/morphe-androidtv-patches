package org.apache.commons.text.similarity;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LevenshteinDetailedDistance implements EditDistance<LevenshteinResults> {
    public static final LevenshteinDetailedDistance DEFAULT_INSTANCE = new LevenshteinDetailedDistance(null);
    public final Integer threshold;

    public LevenshteinDetailedDistance() {
        this(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0064, code lost:
    
        if (r16 != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
    
        r4 = r4 + 1;
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
    
        r3 = r3 + 1;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007b, code lost:
    
        if (r16 != false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.apache.commons.text.similarity.LevenshteinResults findDetailedResults(java.lang.CharSequence r13, java.lang.CharSequence r14, int[][] r15, boolean r16) {
        /*
            int r0 = r14.length()
            int r1 = r13.length()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        Lc:
            if (r0 < 0) goto L87
            if (r1 < 0) goto L87
            r6 = -1
            if (r1 != 0) goto L15
            r7 = -1
            goto L1b
        L15:
            r7 = r15[r0]
            int r8 = r1 + (-1)
            r7 = r7[r8]
        L1b:
            if (r0 != 0) goto L1f
            r8 = -1
            goto L25
        L1f:
            int r8 = r0 + (-1)
            r8 = r15[r8]
            r8 = r8[r1]
        L25:
            if (r0 <= 0) goto L32
            if (r1 <= 0) goto L32
            int r9 = r0 + (-1)
            r9 = r15[r9]
            int r10 = r1 + (-1)
            r9 = r9[r10]
            goto L33
        L32:
            r9 = -1
        L33:
            if (r7 != r6) goto L3a
            if (r8 != r6) goto L3a
            if (r9 != r6) goto L3a
            goto L87
        L3a:
            r10 = r15[r0]
            r10 = r10[r1]
            if (r1 <= 0) goto L55
            if (r0 <= 0) goto L55
            int r11 = r1 + (-1)
            char r11 = r13.charAt(r11)
            int r12 = r0 + (-1)
            char r12 = r14.charAt(r12)
            if (r11 != r12) goto L55
        L50:
            int r1 = r1 + (-1)
            int r0 = r0 + (-1)
            goto Lc
        L55:
            int r11 = r10 + (-1)
            r12 = 1
            if (r11 != r7) goto L5e
            if (r10 > r9) goto L5e
            if (r10 <= r8) goto L62
        L5e:
            if (r9 != r6) goto L6f
            if (r8 != r6) goto L6f
        L62:
            int r1 = r1 + (-1)
            if (r16 == 0) goto L6a
        L66:
            int r4 = r4 + 1
            r6 = 0
            goto L80
        L6a:
            int r3 = r3 + 1
            r6 = 1
        L6d:
            r12 = 0
            goto L80
        L6f:
            if (r11 != r8) goto L75
            if (r10 > r9) goto L75
            if (r10 <= r7) goto L79
        L75:
            if (r9 != r6) goto L7e
            if (r7 != r6) goto L7e
        L79:
            int r0 = r0 + (-1)
            if (r16 == 0) goto L66
            goto L6a
        L7e:
            r6 = 0
            goto L6d
        L80:
            if (r12 != 0) goto Lc
            if (r6 != 0) goto Lc
            int r5 = r5 + 1
            goto L50
        L87:
            org.apache.commons.text.similarity.LevenshteinResults r13 = new org.apache.commons.text.similarity.LevenshteinResults
            int r14 = r4 + r3
            int r14 = r14 + r5
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r13.<init>(r14, r0, r1, r2)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.text.similarity.LevenshteinDetailedDistance.findDetailedResults(java.lang.CharSequence, java.lang.CharSequence, int[][], boolean):org.apache.commons.text.similarity.LevenshteinResults");
    }

    public static LevenshteinDetailedDistance getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static LevenshteinResults limitedCompare(CharSequence charSequence, CharSequence charSequence2, int i) {
        int i2;
        int length;
        boolean z;
        CharSequence charSequence3;
        CharSequence charSequence4;
        char c = 0;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        int length2 = charSequence.length();
        int length3 = charSequence2.length();
        if (length2 == 0) {
            return length3 <= i ? new LevenshteinResults(Integer.valueOf(length3), Integer.valueOf(length3), 0, 0) : new LevenshteinResults(-1, 0, 0, 0);
        }
        if (length3 == 0) {
            return length2 <= i ? new LevenshteinResults(Integer.valueOf(length2), 0, Integer.valueOf(length2), 0) : new LevenshteinResults(-1, 0, 0, 0);
        }
        int i3 = 1;
        if (length2 > length3) {
            length = charSequence.length();
            i2 = length3;
            z = true;
            charSequence4 = charSequence;
            charSequence3 = charSequence2;
        } else {
            i2 = length2;
            length = length3;
            z = false;
            charSequence3 = charSequence;
            charSequence4 = charSequence2;
        }
        int i4 = i2 + 1;
        int[] iArr = new int[i4];
        int[] iArr2 = new int[i4];
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, length + 1, i4);
        for (int i5 = 0; i5 <= i2; i5++) {
            iArr3[0][i5] = i5;
        }
        for (int i6 = 0; i6 <= length; i6++) {
            iArr3[i6][0] = i6;
        }
        int iMin = Math.min(i2, i) + 1;
        for (int i7 = 0; i7 < iMin; i7++) {
            iArr[i7] = i7;
        }
        Arrays.fill(iArr, iMin, i4, Integer.MAX_VALUE);
        Arrays.fill(iArr2, Integer.MAX_VALUE);
        int i8 = 1;
        while (i8 <= length) {
            char cCharAt = charSequence4.charAt(i8 - 1);
            iArr2[c] = i8;
            int iMax = Math.max(i3, i8 - i);
            int iMin2 = i8 > Integer.MAX_VALUE - i ? i2 : Math.min(i2, i8 + i);
            if (iMax > iMin2) {
                return new LevenshteinResults(-1, 0, 0, 0);
            }
            if (iMax > i3) {
                iArr2[iMax - 1] = Integer.MAX_VALUE;
            }
            while (iMax <= iMin2) {
                int i9 = iMax - 1;
                int i10 = iMax;
                if (charSequence3.charAt(i9) == cCharAt) {
                    iArr2[i10] = iArr[i9];
                } else {
                    iArr2[i10] = Math.min(Math.min(iArr2[i9], iArr[i10]), iArr[i9]) + 1;
                }
                iArr3[i8][i10] = iArr2[i10];
                iMax = i10 + 1;
            }
            i8++;
            int[] iArr4 = iArr2;
            iArr2 = iArr;
            iArr = iArr4;
            c = 0;
            i3 = 1;
        }
        return iArr[i2] <= i ? findDetailedResults(charSequence3, charSequence4, iArr3, z) : new LevenshteinResults(-1, 0, 0, 0);
    }

    public static LevenshteinResults unlimitedCompare(CharSequence charSequence, CharSequence charSequence2) {
        int i;
        int length;
        boolean z;
        CharSequence charSequence3;
        CharSequence charSequence4;
        char c = 0;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int length2 = charSequence.length();
        int length3 = charSequence2.length();
        if (length2 == 0) {
            return new LevenshteinResults(Integer.valueOf(length3), Integer.valueOf(length3), 0, 0);
        }
        if (length3 == 0) {
            return new LevenshteinResults(Integer.valueOf(length2), 0, Integer.valueOf(length2), 0);
        }
        if (length2 > length3) {
            length = charSequence.length();
            i = length3;
            z = true;
            charSequence4 = charSequence;
            charSequence3 = charSequence2;
        } else {
            i = length2;
            length = length3;
            z = false;
            charSequence3 = charSequence;
            charSequence4 = charSequence2;
        }
        int i2 = i + 1;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, length + 1, i2);
        for (int i3 = 0; i3 <= i; i3++) {
            iArr3[0][i3] = i3;
        }
        for (int i4 = 0; i4 <= length; i4++) {
            iArr3[i4][0] = i4;
        }
        for (int i5 = 0; i5 <= i; i5++) {
            iArr[i5] = i5;
        }
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int i6 = 1;
        while (i6 <= length) {
            char cCharAt = charSequence4.charAt(i6 - 1);
            iArr5[c] = i6;
            for (int i7 = 1; i7 <= i; i7++) {
                int i8 = i7 - 1;
                int iMin = Math.min(Math.min(iArr5[i8] + 1, iArr4[i7] + 1), iArr4[i8] + (charSequence3.charAt(i8) == cCharAt ? 0 : 1));
                iArr5[i7] = iMin;
                iArr3[i6][i7] = iMin;
            }
            i6++;
            int[] iArr6 = iArr4;
            iArr4 = iArr5;
            iArr5 = iArr6;
            c = 0;
        }
        return findDetailedResults(charSequence3, charSequence4, iArr3, z);
    }

    public Integer getThreshold() {
        return this.threshold;
    }

    public LevenshteinDetailedDistance(Integer num) {
        if (num != null && num.intValue() < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = num;
    }

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public LevenshteinResults apply(CharSequence charSequence, CharSequence charSequence2) {
        Integer num = this.threshold;
        return num != null ? limitedCompare(charSequence, charSequence2, num.intValue()) : unlimitedCompare(charSequence, charSequence2);
    }
}
