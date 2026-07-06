package org.apache.commons.text.similarity;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class JaroWinklerSimilarity implements SimilarityScore<Double> {
    public static int[] matches(CharSequence charSequence, CharSequence charSequence2) {
        CharSequence charSequence3;
        CharSequence charSequence4;
        if (charSequence.length() > charSequence2.length()) {
            charSequence4 = charSequence;
            charSequence3 = charSequence2;
        } else {
            charSequence3 = charSequence;
            charSequence4 = charSequence2;
        }
        int iMax = Math.max((charSequence4.length() / 2) - 1, 0);
        int[] iArr = new int[charSequence3.length()];
        Arrays.fill(iArr, -1);
        boolean[] zArr = new boolean[charSequence4.length()];
        int i = 0;
        for (int i2 = 0; i2 < charSequence3.length(); i2++) {
            char cCharAt = charSequence3.charAt(i2);
            int iMax2 = Math.max(i2 - iMax, 0);
            int iMin = Math.min(i2 + iMax + 1, charSequence4.length());
            while (true) {
                if (iMax2 >= iMin) {
                    break;
                }
                if (!zArr[iMax2] && cCharAt == charSequence4.charAt(iMax2)) {
                    iArr[i2] = iMax2;
                    zArr[iMax2] = true;
                    i++;
                    break;
                }
                iMax2++;
            }
        }
        char[] cArr = new char[i];
        char[] cArr2 = new char[i];
        int i3 = 0;
        for (int i4 = 0; i4 < charSequence3.length(); i4++) {
            if (iArr[i4] != -1) {
                cArr[i3] = charSequence3.charAt(i4);
                i3++;
            }
        }
        int i5 = 0;
        for (int i6 = 0; i6 < charSequence4.length(); i6++) {
            if (zArr[i6]) {
                cArr2[i5] = charSequence4.charAt(i6);
                i5++;
            }
        }
        int i7 = 0;
        for (int i8 = 0; i8 < i; i8++) {
            if (cArr[i8] != cArr2[i8]) {
                i7++;
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < Math.min(4, charSequence3.length()) && charSequence.charAt(i10) == charSequence2.charAt(i10); i10++) {
            i9++;
        }
        return new int[]{i, i7, i9};
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (StringUtils.equals(charSequence, charSequence2)) {
            return Double.valueOf(1.0d);
        }
        int[] iArrMatches = matches(charSequence, charSequence2);
        double d = iArrMatches[0];
        if (d == 0.0d) {
            return Double.valueOf(0.0d);
        }
        double length = (((d - (((double) iArrMatches[1]) / 2.0d)) / d) + ((d / ((double) charSequence2.length())) + (d / ((double) charSequence.length())))) / 3.0d;
        if (length >= 0.7d) {
            length += (1.0d - length) * ((double) iArrMatches[2]) * 0.1d;
        }
        return Double.valueOf(length);
    }
}
