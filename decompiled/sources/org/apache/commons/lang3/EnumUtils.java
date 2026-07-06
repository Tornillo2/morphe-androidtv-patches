package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class EnumUtils {
    public static final String CANNOT_STORE_S_S_VALUES_IN_S_BITS = "Cannot store %s %s values in %s bits";
    public static final String ENUM_CLASS_MUST_BE_DEFINED = "EnumClass must be defined.";
    public static final String NULL_ELEMENTS_NOT_PERMITTED = "null elements not permitted";
    public static final String S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE = "%s does not seem to be an Enum type";

    public static <E extends Enum<E>> Class<E> asEnum(Class<E> cls) {
        Validate.notNull(cls, ENUM_CLASS_MUST_BE_DEFINED, new Object[0]);
        Validate.isTrue(cls.isEnum(), S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE, cls);
        return cls;
    }

    public static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> cls) {
        asEnum(cls);
        E[] enumConstants = cls.getEnumConstants();
        Validate.isTrue(enumConstants.length <= 64, CANNOT_STORE_S_S_VALUES_IN_S_BITS, Integer.valueOf(enumConstants.length), cls.getSimpleName(), 64);
        return cls;
    }

    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, Iterable<? extends E> iterable) {
        checkBitVectorable(cls);
        Validate.notNull(iterable);
        Iterator<? extends E> it = iterable.iterator();
        long jOrdinal = 0;
        while (it.hasNext()) {
            E next = it.next();
            Validate.isTrue(next != null, NULL_ELEMENTS_NOT_PERMITTED, new Object[0]);
            jOrdinal |= 1 << next.ordinal();
        }
        return jOrdinal;
    }

    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> cls, Iterable<? extends E> iterable) {
        asEnum(cls);
        Validate.notNull(iterable);
        EnumSet<Enum> enumSetNoneOf = EnumSet.noneOf(cls);
        Iterator<? extends E> it = iterable.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            E next = it.next();
            if (next == null) {
                z = false;
            }
            Validate.isTrue(z, NULL_ELEMENTS_NOT_PERMITTED, new Object[0]);
            enumSetNoneOf.add(next);
        }
        long[] jArr = new long[((cls.getEnumConstants().length - 1) / 64) + 1];
        for (Enum r0 : enumSetNoneOf) {
            int iOrdinal = r0.ordinal() / 64;
            jArr[iOrdinal] = jArr[iOrdinal] | (1 << (r0.ordinal() % 64));
        }
        ArrayUtils.reverse(jArr);
        return jArr;
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str) {
        if (str == null) {
            return null;
        }
        try {
            return (E) Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> cls, String str) {
        if (str != null && cls.isEnum()) {
            for (E e : cls.getEnumConstants()) {
                if (e.name().equalsIgnoreCase(str)) {
                    return e;
                }
            }
        }
        return null;
    }

    public static <E extends Enum<E>> List<E> getEnumList(Class<E> cls) {
        return new ArrayList(Arrays.asList(cls.getEnumConstants()));
    }

    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (E e : cls.getEnumConstants()) {
            linkedHashMap.put(e.name(), e);
        }
        return linkedHashMap;
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> cls, String str) {
        return getEnum(cls, str) != null;
    }

    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(Class<E> cls, String str) {
        return getEnumIgnoreCase(cls, str) != null;
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> cls, long j) {
        checkBitVectorable(cls);
        cls.getEnumConstants();
        return processBitVectors(cls, j);
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVectors(Class<E> cls, long... jArr) {
        asEnum(cls);
        EnumSet<E> enumSetNoneOf = EnumSet.noneOf(cls);
        Validate.notNull(jArr);
        long[] jArrClone = ArrayUtils.clone(jArr);
        ArrayUtils.reverse(jArrClone);
        for (E e : cls.getEnumConstants()) {
            int iOrdinal = e.ordinal() / 64;
            if (iOrdinal < jArrClone.length && (jArrClone[iOrdinal] & (1 << (e.ordinal() % 64))) != 0) {
                enumSetNoneOf.add(e);
            }
        }
        return enumSetNoneOf;
    }

    @SafeVarargs
    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, E... eArr) {
        Validate.noNullElements(eArr);
        return generateBitVector(cls, Arrays.asList(eArr));
    }

    @SafeVarargs
    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> cls, E... eArr) {
        asEnum(cls);
        Validate.noNullElements(eArr);
        EnumSet<Enum> enumSetNoneOf = EnumSet.noneOf(cls);
        Collections.addAll(enumSetNoneOf, eArr);
        long[] jArr = new long[((cls.getEnumConstants().length - 1) / 64) + 1];
        for (Enum r0 : enumSetNoneOf) {
            int iOrdinal = r0.ordinal() / 64;
            jArr[iOrdinal] = jArr[iOrdinal] | (1 << (r0.ordinal() % 64));
        }
        ArrayUtils.reverse(jArr);
        return jArr;
    }
}
