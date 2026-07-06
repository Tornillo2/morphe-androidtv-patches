package org.apache.commons.text;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class CaseUtils {
    public static Set<Integer> generateDelimiterSet(char[] cArr) {
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(Character.codePointAt(new char[]{' '}, 0)));
        if (cArr != null && cArr.length != 0) {
            for (int i = 0; i < cArr.length; i++) {
                hashSet.add(Integer.valueOf(Character.codePointAt(cArr, i)));
            }
        }
        return hashSet;
    }

    public static String toCamelCase(String str, boolean z, char... cArr) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        String lowerCase = str.toLowerCase();
        int length = lowerCase.length();
        int[] iArr = new int[length];
        Set<Integer> setGenerateDelimiterSet = generateDelimiterSet(cArr);
        boolean z2 = z;
        int iCharCount = 0;
        int i = 0;
        while (iCharCount < length) {
            int iCodePointAt = lowerCase.codePointAt(iCharCount);
            if (((HashSet) setGenerateDelimiterSet).contains(Integer.valueOf(iCodePointAt))) {
                z2 = i != 0;
                iCharCount = Character.charCount(iCodePointAt) + iCharCount;
            } else if (z2 || (i == 0 && z)) {
                int titleCase = Character.toTitleCase(iCodePointAt);
                iArr[i] = titleCase;
                iCharCount = Character.charCount(titleCase) + iCharCount;
                i++;
                z2 = false;
            } else {
                iArr[i] = iCodePointAt;
                iCharCount = Character.charCount(iCodePointAt) + iCharCount;
                i++;
            }
        }
        return i != 0 ? new String(iArr, 0, i) : lowerCase;
    }
}
