package de.odysseus.el.misc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BooleanOperations {
    public static final Set<Class<? extends Number>> SIMPLE_FLOAT_TYPES;
    public static final Set<Class<? extends Number>> SIMPLE_INTEGER_TYPES;

    static {
        HashSet hashSet = new HashSet();
        SIMPLE_INTEGER_TYPES = hashSet;
        HashSet hashSet2 = new HashSet();
        SIMPLE_FLOAT_TYPES = hashSet2;
        hashSet.add(Byte.class);
        hashSet.add(Short.class);
        hashSet.add(Integer.class);
        hashSet.add(Long.class);
        hashSet2.add(Float.class);
        hashSet2.add(Double.class);
    }

    public static final boolean empty(TypeConverter typeConverter, Object obj) {
        if (obj == null || "".equals(obj)) {
            return true;
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        return false;
    }

    public static final boolean eq(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (BigDecimal.class.isAssignableFrom(cls) || BigDecimal.class.isAssignableFrom(cls2)) {
            return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).equals(typeConverter.convert(obj2, BigDecimal.class));
        }
        Set<Class<? extends Number>> set = SIMPLE_FLOAT_TYPES;
        if (set.contains(cls) || set.contains(cls2)) {
            return ((Double) typeConverter.convert(obj, Double.class)).equals(typeConverter.convert(obj2, Double.class));
        }
        if (BigInteger.class.isAssignableFrom(cls) || BigInteger.class.isAssignableFrom(cls2)) {
            return ((BigInteger) typeConverter.convert(obj, BigInteger.class)).equals(typeConverter.convert(obj2, BigInteger.class));
        }
        Set<Class<? extends Number>> set2 = SIMPLE_INTEGER_TYPES;
        return (set2.contains(cls) || set2.contains(cls2)) ? ((Long) typeConverter.convert(obj, Long.class)).equals(typeConverter.convert(obj2, Long.class)) : (cls == Boolean.class || cls2 == Boolean.class) ? ((Boolean) typeConverter.convert(obj, Boolean.class)).equals(typeConverter.convert(obj2, Boolean.class)) : obj instanceof Enum ? obj == typeConverter.convert(obj2, obj.getClass()) : obj2 instanceof Enum ? typeConverter.convert(obj, obj2.getClass()) == obj2 : (cls == String.class || cls2 == String.class) ? ((String) typeConverter.convert(obj, String.class)).equals(typeConverter.convert(obj2, String.class)) : obj.equals(obj2);
    }

    public static final boolean ge(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return !lt0(typeConverter, obj, obj2);
    }

    public static final boolean gt(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == obj2 || obj == null || obj2 == null) {
            return false;
        }
        return gt0(typeConverter, obj, obj2);
    }

    public static final boolean gt0(TypeConverter typeConverter, Object obj, Object obj2) {
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (BigDecimal.class.isAssignableFrom(cls) || BigDecimal.class.isAssignableFrom(cls2)) {
            return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).compareTo((BigDecimal) typeConverter.convert(obj2, BigDecimal.class)) > 0;
        }
        Set<Class<? extends Number>> set = SIMPLE_FLOAT_TYPES;
        if (set.contains(cls) || set.contains(cls2)) {
            return ((Double) typeConverter.convert(obj, Double.class)).doubleValue() > ((Double) typeConverter.convert(obj2, Double.class)).doubleValue();
        }
        if (BigInteger.class.isAssignableFrom(cls) || BigInteger.class.isAssignableFrom(cls2)) {
            return ((BigInteger) typeConverter.convert(obj, BigInteger.class)).compareTo((BigInteger) typeConverter.convert(obj2, BigInteger.class)) > 0;
        }
        Set<Class<? extends Number>> set2 = SIMPLE_INTEGER_TYPES;
        if (set2.contains(cls) || set2.contains(cls2)) {
            return ((Long) typeConverter.convert(obj, Long.class)).longValue() > ((Long) typeConverter.convert(obj2, Long.class)).longValue();
        }
        if (cls == String.class || cls2 == String.class) {
            return ((String) typeConverter.convert(obj, String.class)).compareTo((String) typeConverter.convert(obj2, String.class)) > 0;
        }
        if (obj instanceof Comparable) {
            return ((Comparable) obj).compareTo(obj2) > 0;
        }
        if (obj2 instanceof Comparable) {
            return ((Comparable) obj2).compareTo(obj) < 0;
        }
        throw new ELException(LocalMessages.get("error.compare.types", obj.getClass(), obj2.getClass()));
    }

    public static final boolean le(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return !gt0(typeConverter, obj, obj2);
    }

    public static final boolean lt(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == obj2 || obj == null || obj2 == null) {
            return false;
        }
        return lt0(typeConverter, obj, obj2);
    }

    public static final boolean lt0(TypeConverter typeConverter, Object obj, Object obj2) {
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (BigDecimal.class.isAssignableFrom(cls) || BigDecimal.class.isAssignableFrom(cls2)) {
            return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).compareTo((BigDecimal) typeConverter.convert(obj2, BigDecimal.class)) < 0;
        }
        Set<Class<? extends Number>> set = SIMPLE_FLOAT_TYPES;
        if (set.contains(cls) || set.contains(cls2)) {
            return ((Double) typeConverter.convert(obj, Double.class)).doubleValue() < ((Double) typeConverter.convert(obj2, Double.class)).doubleValue();
        }
        if (BigInteger.class.isAssignableFrom(cls) || BigInteger.class.isAssignableFrom(cls2)) {
            return ((BigInteger) typeConverter.convert(obj, BigInteger.class)).compareTo((BigInteger) typeConverter.convert(obj2, BigInteger.class)) < 0;
        }
        Set<Class<? extends Number>> set2 = SIMPLE_INTEGER_TYPES;
        if (set2.contains(cls) || set2.contains(cls2)) {
            return ((Long) typeConverter.convert(obj, Long.class)).longValue() < ((Long) typeConverter.convert(obj2, Long.class)).longValue();
        }
        if (cls == String.class || cls2 == String.class) {
            return ((String) typeConverter.convert(obj, String.class)).compareTo((String) typeConverter.convert(obj2, String.class)) < 0;
        }
        if (obj instanceof Comparable) {
            return ((Comparable) obj).compareTo(obj2) < 0;
        }
        if (obj2 instanceof Comparable) {
            return ((Comparable) obj2).compareTo(obj) > 0;
        }
        throw new ELException(LocalMessages.get("error.compare.types", obj.getClass(), obj2.getClass()));
    }

    public static final boolean ne(TypeConverter typeConverter, Object obj, Object obj2) {
        return !eq(typeConverter, obj, obj2);
    }
}
