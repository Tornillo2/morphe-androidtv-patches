package org.apache.commons.lang3;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class RandomStringUtils {
    public static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, 0, 0, false, false);
    }

    public static String randomAlphabetic(int i, int i2) {
        return random(RandomUtils.nextInt(i, i2), 0, 0, true, false);
    }

    public static String randomAlphanumeric(int i, int i2) {
        return random(RandomUtils.nextInt(i, i2), 0, 0, true, true);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomGraph(int i) {
        return random(i, 33, 126, false, false);
    }

    public static String randomNumeric(int i, int i2) {
        return random(RandomUtils.nextInt(i, i2), 0, 0, false, true);
    }

    public static String randomPrint(int i) {
        return random(i, 32, 126, false, false);
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String randomAscii(int i, int i2) {
        return randomAscii(RandomUtils.nextInt(i, i2));
    }

    public static String randomGraph(int i, int i2) {
        return randomGraph(RandomUtils.nextInt(i, i2));
    }

    public static String randomPrint(int i, int i2) {
        return randomPrint(RandomUtils.nextInt(i, i2));
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, null, RANDOM);
    }

    public static String randomAlphabetic(int i) {
        return random(i, 0, 0, true, false);
    }

    public static String randomAlphanumeric(int i) {
        return random(i, 0, 0, true, true);
    }

    public static String randomNumeric(int i) {
        return random(i, 0, 0, false, true);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String random(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char cNextInt;
        if (i == 0) {
            return "";
        }
        if (i >= 0) {
            if (cArr != 0 && cArr.length == 0) {
                throw new IllegalArgumentException("The chars array must not be empty");
            }
            if (i2 == 0 && i3 == 0) {
                if (cArr != 0) {
                    i3 = cArr.length;
                } else if (z || z2) {
                    i3 = 123;
                    i2 = 32;
                } else {
                    i3 = 1114111;
                }
            } else if (i3 <= i2) {
                throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline0.m("Parameter end (", i3, ") must be greater than start (", i2, ")"));
            }
            if (cArr == 0 && ((z2 && i3 <= 48) || (z && i3 <= 65))) {
                throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Parameter end (", i3, ") must be greater then (48) for generating digits or greater then (65) for generating letters."));
            }
            StringBuilder sb = new StringBuilder(i);
            int i4 = i3 - i2;
            while (true) {
                int i5 = i - 1;
                if (i != 0) {
                    if (cArr == 0) {
                        cNextInt = random.nextInt(i4) + i2;
                        int type = Character.getType(cNextInt);
                        if (type == 0 || type == 18 || type == 19) {
                        }
                    } else {
                        cNextInt = cArr[random.nextInt(i4) + i2];
                    }
                    int iCharCount = Character.charCount(cNextInt);
                    if (i5 != 0 || iCharCount <= 1) {
                        if ((z && Character.isLetter(cNextInt)) || ((z2 && Character.isDigit(cNextInt)) || (!z && !z2))) {
                            sb.appendCodePoint(cNextInt);
                            i = iCharCount == 2 ? i - 2 : i5;
                        }
                    }
                } else {
                    return sb.toString();
                }
            }
        } else {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested random string length ", i, " is less than 0."));
        }
    }

    public static String random(int i, String str) {
        if (str == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, str.toCharArray());
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
