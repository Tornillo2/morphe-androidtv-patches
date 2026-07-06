package org.apache.commons.text.similarity;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LevenshteinDistance implements EditDistance<Integer> {
    public static final LevenshteinDistance DEFAULT_INSTANCE = new LevenshteinDistance(null);
    public final Integer threshold;

    public LevenshteinDistance() {
        this(null);
    }

    public static LevenshteinDistance getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static int limitedCompare(CharSequence charSequence, CharSequence charSequence2, int i) {
        int i2;
        int length;
        CharSequence charSequence3;
        CharSequence charSequence4;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        int length2 = charSequence.length();
        int length3 = charSequence2.length();
        if (length2 == 0) {
            if (length3 <= i) {
                return length3;
            }
            return -1;
        }
        if (length3 == 0) {
            if (length2 <= i) {
                return length2;
            }
            return -1;
        }
        if (length2 > length3) {
            length = charSequence.length();
            i2 = length3;
            charSequence4 = charSequence;
            charSequence3 = charSequence2;
        } else {
            i2 = length2;
            length = length3;
            charSequence3 = charSequence;
            charSequence4 = charSequence2;
        }
        if (length - i2 > i) {
            return -1;
        }
        int i3 = i2 + 1;
        int[] iArr = new int[i3];
        int[] iArr2 = new int[i3];
        int i4 = 1;
        int iMin = Math.min(i2, i) + 1;
        for (int i5 = 0; i5 < iMin; i5++) {
            iArr[i5] = i5;
        }
        Arrays.fill(iArr, iMin, i3, Integer.MAX_VALUE);
        Arrays.fill(iArr2, Integer.MAX_VALUE);
        int i6 = 1;
        while (i6 <= length) {
            char cCharAt = charSequence4.charAt(i6 - 1);
            iArr2[0] = i6;
            int iMax = Math.max(i4, i6 - i);
            int iMin2 = i6 > Integer.MAX_VALUE - i ? i2 : Math.min(i2, i6 + i);
            if (iMax > i4) {
                iArr2[iMax - 1] = Integer.MAX_VALUE;
            }
            while (iMax <= iMin2) {
                int i7 = iMax - 1;
                if (charSequence3.charAt(i7) == cCharAt) {
                    iArr2[iMax] = iArr[i7];
                } else {
                    iArr2[iMax] = Math.min(Math.min(iArr2[i7], iArr[iMax]), iArr[i7]) + 1;
                }
                iMax++;
            }
            i6++;
            int[] iArr3 = iArr2;
            iArr2 = iArr;
            iArr = iArr3;
            i4 = 1;
        }
        int i8 = iArr[i2];
        if (i8 <= i) {
            return i8;
        }
        return -1;
    }

    public static int unlimitedCompare(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        if (length > length2) {
            length2 = charSequence.length();
            length = length2;
        } else {
            charSequence2 = charSequence;
            charSequence = charSequence2;
        }
        int[] iArr = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            iArr[i] = i;
        }
        for (int i2 = 1; i2 <= length2; i2++) {
            int i3 = iArr[0];
            char cCharAt = charSequence.charAt(i2 - 1);
            iArr[0] = i2;
            int i4 = 1;
            while (i4 <= length) {
                int i5 = iArr[i4];
                int i6 = i4 - 1;
                iArr[i4] = Math.min(Math.min(iArr[i6] + 1, iArr[i4] + 1), i3 + (charSequence2.charAt(i6) == cCharAt ? 0 : 1));
                i4++;
                i3 = i5;
            }
        }
        return iArr[length];
    }

    public Integer getThreshold() {
        return this.threshold;
    }

    public LevenshteinDistance(Integer num) {
        if (num != null && num.intValue() < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = num;
    }

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Integer apply(CharSequence charSequence, CharSequence charSequence2) {
        Integer num = this.threshold;
        return num != null ? Integer.valueOf(limitedCompare(charSequence, charSequence2, num.intValue())) : Integer.valueOf(unlimitedCompare(charSequence, charSequence2));
    }
}
