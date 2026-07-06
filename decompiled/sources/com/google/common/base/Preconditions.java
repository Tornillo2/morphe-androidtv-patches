package com.google.common.base;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Preconditions {
    public static String badElementIndex(int index, int size, String desc) {
        if (index < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return Strings.lenientFormat("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("negative size: ", size));
    }

    public static String badPositionIndex(int index, int size, String desc) {
        if (index < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("negative size: ", size));
    }

    public static String badPositionIndexes(int start, int end, int size) {
        return (start < 0 || start > size) ? badPositionIndex(start, size, "start index") : (end < 0 || end > size) ? badPositionIndex(end, size, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(end), Integer.valueOf(start));
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size) {
        checkElementIndex(index, size, "index");
        return index;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference) {
        reference.getClass();
        return reference;
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size) {
        checkPositionIndex(index, size, "index");
        return index;
    }

    public static void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size, String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
        return index;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size, String desc) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
        }
        return index;
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, char p1) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, char p1) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, char p1) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, int p1) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, int p1) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, int p1) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, long p1) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, long p1) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, long p1) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, char p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, char p1, char p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, char p1, char p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, int p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, char p1, int p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Integer.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, char p1, int p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, long p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Long.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, char p1, long p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Long.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, char p1, long p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, Object p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, char p1, Object p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, char p1, Object p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, char p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Character.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, int p1, char p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Character.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, int p1, char p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, int p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, int p1, int p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Integer.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, int p1, int p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, long p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Long.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, int p1, long p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Long.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, int p1, long p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, Object p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), p2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, int p1, Object p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), p2));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, int p1, Object p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Integer.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, char p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Character.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, long p1, char p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Character.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, long p1, char p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, int p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, long p1, int p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Integer.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, long p1, int p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Integer.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, long p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Long.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, long p1, long p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Long.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, long p1, long p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), Long.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, Object p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), p2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, long p1, Object p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), p2));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, long p1, Object p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Long.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, char p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, char p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, char p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, int p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Integer.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, int p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Integer.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, int p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Integer.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, long p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Long.valueOf(p2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, long p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Long.valueOf(p2)));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, long p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Long.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, Object p2) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, Object p2) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, Object p2) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, Object p2, Object p3) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
    }

    public static void checkState(boolean expression, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }
}
