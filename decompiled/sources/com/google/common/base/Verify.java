package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Verify {
    public static void verify(boolean expression) {
        if (!expression) {
            throw new VerifyException();
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T reference) {
        verifyNotNull(reference, "expected a non-null reference", new Object[0]);
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        if (reference != null) {
            return reference;
        }
        throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, char p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, int p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, long p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, char p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, int p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, long p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, char p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, int p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, long p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Integer.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, char p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, int p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, long p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Long.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, char p1, Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, int p1, Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, long p1, Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void verify(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }
}
