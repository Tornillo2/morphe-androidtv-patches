package org.apache.commons.lang3;

import kotlinx.coroutines.DebugKt;
import org.apache.commons.lang3.math.NumberUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class BooleanUtils {
    public static boolean and(boolean... zArr) {
        if (zArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (boolean z : zArr) {
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    public static boolean isNotFalse(Boolean bool) {
        return !Boolean.FALSE.equals(bool);
    }

    public static boolean isNotTrue(Boolean bool) {
        return !Boolean.TRUE.equals(bool);
    }

    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    public static Boolean negate(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
    }

    public static boolean or(boolean... zArr) {
        if (zArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static boolean toBoolean(int i) {
        return i != 0;
    }

    public static boolean toBooleanDefaultIfNull(Boolean bool, boolean z) {
        return bool == null ? z : bool.booleanValue();
    }

    public static Boolean toBooleanObject(int i) {
        return i == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static int toInteger(boolean z) {
        return z ? 1 : 0;
    }

    public static Integer toIntegerObject(boolean z, Integer num, Integer num2) {
        return z ? num : num2;
    }

    public static String toString(boolean z, String str, String str2) {
        return z ? str : str2;
    }

    public static String toStringOnOff(Boolean bool) {
        return toString(bool, DebugKt.DEBUG_PROPERTY_VALUE_ON, DebugKt.DEBUG_PROPERTY_VALUE_OFF, null);
    }

    public static String toStringTrueFalse(Boolean bool) {
        return toString(bool, "true", "false", null);
    }

    public static String toStringYesNo(Boolean bool) {
        return toString(bool, "yes", "no", null);
    }

    public static boolean xor(boolean... zArr) {
        if (zArr == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        boolean z = false;
        for (boolean z2 : zArr) {
            z ^= z2;
        }
        return z;
    }

    public static boolean toBoolean(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    public static Boolean toBooleanObject(Integer num) {
        if (num == null) {
            return null;
        }
        return num.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static int toInteger(boolean z, int i, int i2) {
        return z ? i : i2;
    }

    public static Integer toIntegerObject(boolean z) {
        return z ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    public static String toString(Boolean bool, String str, String str2, String str3) {
        return bool == null ? str3 : bool.booleanValue() ? str : str2;
    }

    public static String toStringOnOff(boolean z) {
        return z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
    }

    public static String toStringTrueFalse(boolean z) {
        return z ? "true" : "false";
    }

    public static String toStringYesNo(boolean z) {
        return z ? "yes" : "no";
    }

    public static boolean toBoolean(int i, int i2, int i3) {
        if (i == i2) {
            return true;
        }
        if (i == i3) {
            return false;
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static Boolean toBooleanObject(int i, int i2, int i3, int i4) {
        if (i == i2) {
            return Boolean.TRUE;
        }
        if (i == i3) {
            return Boolean.FALSE;
        }
        if (i == i4) {
            return null;
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static int toInteger(Boolean bool, int i, int i2, int i3) {
        return bool == null ? i3 : bool.booleanValue() ? i : i2;
    }

    public static Integer toIntegerObject(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
    }

    public static boolean toBoolean(Integer num, Integer num2, Integer num3) {
        if (num == null) {
            if (num2 == null) {
                return true;
            }
            if (num3 == null) {
                return false;
            }
        } else {
            if (num.equals(num2)) {
                return true;
            }
            if (num.equals(num3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static Integer toIntegerObject(Boolean bool, Integer num, Integer num2, Integer num3) {
        return bool == null ? num3 : bool.booleanValue() ? num : num2;
    }

    public static Boolean and(Boolean... boolArr) {
        if (boolArr != null) {
            if (boolArr.length != 0) {
                try {
                    return and(ArrayUtils.toPrimitive(boolArr)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (NullPointerException unused) {
                    throw new IllegalArgumentException("The array must not contain any null elements");
                }
            }
            throw new IllegalArgumentException("Array is empty");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static Boolean or(Boolean... boolArr) {
        if (boolArr != null) {
            if (boolArr.length != 0) {
                try {
                    return or(ArrayUtils.toPrimitive(boolArr)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (NullPointerException unused) {
                    throw new IllegalArgumentException("The array must not contain any null elements");
                }
            }
            throw new IllegalArgumentException("Array is empty");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static Boolean xor(Boolean... boolArr) {
        if (boolArr != null) {
            if (boolArr.length != 0) {
                try {
                    return xor(ArrayUtils.toPrimitive(boolArr)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (NullPointerException unused) {
                    throw new IllegalArgumentException("The array must not contain any null elements");
                }
            }
            throw new IllegalArgumentException("Array is empty");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static Boolean toBooleanObject(Integer num, Integer num2, Integer num3, Integer num4) {
        if (num == null) {
            if (num2 == null) {
                return Boolean.TRUE;
            }
            if (num3 == null) {
                return Boolean.FALSE;
            }
            if (num4 == null) {
                return null;
            }
        } else {
            if (num.equals(num2)) {
                return Boolean.TRUE;
            }
            if (num.equals(num3)) {
                return Boolean.FALSE;
            }
            if (num.equals(num4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static boolean toBoolean(String str) {
        return toBooleanObject(str) == Boolean.TRUE;
    }

    public static boolean toBoolean(String str, String str2, String str3) {
        if (str == str2) {
            return true;
        }
        if (str == str3) {
            return false;
        }
        if (str != null) {
            if (str.equals(str2)) {
                return true;
            }
            if (str.equals(str3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The String did not match either specified value");
    }

    public static Boolean toBooleanObject(String str) {
        Boolean bool;
        if (str == "true") {
            return Boolean.TRUE;
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 1) {
            bool = null;
            char cCharAt = str.charAt(0);
            if (cCharAt == 'y' || cCharAt == 'Y' || cCharAt == 't' || cCharAt == 'T') {
                return Boolean.TRUE;
            }
            if (cCharAt == 'n' || cCharAt == 'N' || cCharAt == 'f' || cCharAt == 'F') {
                return Boolean.FALSE;
            }
        } else if (length != 2) {
            bool = null;
            if (length == 3) {
                char cCharAt2 = str.charAt(0);
                char cCharAt3 = str.charAt(1);
                char cCharAt4 = str.charAt(2);
                if ((cCharAt2 == 'y' || cCharAt2 == 'Y') && ((cCharAt3 == 'e' || cCharAt3 == 'E') && (cCharAt4 == 's' || cCharAt4 == 'S'))) {
                    return Boolean.TRUE;
                }
                if ((cCharAt2 == 'o' || cCharAt2 == 'O') && ((cCharAt3 == 'f' || cCharAt3 == 'F') && (cCharAt4 == 'f' || cCharAt4 == 'F'))) {
                    return Boolean.FALSE;
                }
            } else if (length == 4) {
                char cCharAt5 = str.charAt(0);
                char cCharAt6 = str.charAt(1);
                char cCharAt7 = str.charAt(2);
                char cCharAt8 = str.charAt(3);
                if ((cCharAt5 == 't' || cCharAt5 == 'T') && ((cCharAt6 == 'r' || cCharAt6 == 'R') && ((cCharAt7 == 'u' || cCharAt7 == 'U') && (cCharAt8 == 'e' || cCharAt8 == 'E')))) {
                    return Boolean.TRUE;
                }
            } else if (length == 5) {
                char cCharAt9 = str.charAt(0);
                char cCharAt10 = str.charAt(1);
                char cCharAt11 = str.charAt(2);
                char cCharAt12 = str.charAt(3);
                char cCharAt13 = str.charAt(4);
                if ((cCharAt9 == 'f' || cCharAt9 == 'F') && ((cCharAt10 == 'a' || cCharAt10 == 'A') && ((cCharAt11 == 'l' || cCharAt11 == 'L') && ((cCharAt12 == 's' || cCharAt12 == 'S') && (cCharAt13 == 'e' || cCharAt13 == 'E'))))) {
                    return Boolean.FALSE;
                }
            }
        } else {
            bool = null;
            char cCharAt14 = str.charAt(0);
            char cCharAt15 = str.charAt(1);
            if ((cCharAt14 == 'o' || cCharAt14 == 'O') && (cCharAt15 == 'n' || cCharAt15 == 'N')) {
                return Boolean.TRUE;
            }
            if ((cCharAt14 == 'n' || cCharAt14 == 'N') && (cCharAt15 == 'o' || cCharAt15 == 'O')) {
                return Boolean.FALSE;
            }
        }
        return bool;
    }

    public static Boolean toBooleanObject(String str, String str2, String str3, String str4) {
        if (str == null) {
            if (str2 == null) {
                return Boolean.TRUE;
            }
            if (str3 == null) {
                return Boolean.FALSE;
            }
            if (str4 == null) {
                return null;
            }
        } else {
            if (str.equals(str2)) {
                return Boolean.TRUE;
            }
            if (str.equals(str3)) {
                return Boolean.FALSE;
            }
            if (str.equals(str4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The String did not match any specified value");
    }
}
