package de.odysseus.el.misc;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class NumberOperations {
    public static final Long LONG_ZERO = 0L;

    public static final Number add(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return LONG_ZERO;
        }
        if ((obj instanceof BigDecimal) || (obj2 instanceof BigDecimal)) {
            return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).add((BigDecimal) typeConverter.convert(obj2, BigDecimal.class));
        }
        if (isFloatOrDoubleOrDotEe(obj) || isFloatOrDoubleOrDotEe(obj2)) {
            if ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) {
                return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).add((BigDecimal) typeConverter.convert(obj2, BigDecimal.class));
            }
            return Double.valueOf(((Double) typeConverter.convert(obj2, Double.class)).doubleValue() + ((Double) typeConverter.convert(obj, Double.class)).doubleValue());
        }
        if ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) {
            return ((BigInteger) typeConverter.convert(obj, BigInteger.class)).add((BigInteger) typeConverter.convert(obj2, BigInteger.class));
        }
        return Long.valueOf(((Long) typeConverter.convert(obj2, Long.class)).longValue() + ((Long) typeConverter.convert(obj, Long.class)).longValue());
    }

    public static final Number div(TypeConverter typeConverter, Object obj, Object obj2) {
        return (obj == null && obj2 == null) ? LONG_ZERO : (isBigDecimalOrBigInteger(obj) || isBigDecimalOrBigInteger(obj2)) ? ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).divide((BigDecimal) typeConverter.convert(obj2, BigDecimal.class), 4) : Double.valueOf(((Double) typeConverter.convert(obj, Double.class)).doubleValue() / ((Double) typeConverter.convert(obj2, Double.class)).doubleValue());
    }

    public static final boolean isBigDecimalOrBigInteger(Object obj) {
        return (obj instanceof BigDecimal) || (obj instanceof BigInteger);
    }

    public static final boolean isBigDecimalOrFloatOrDoubleOrDotEe(Object obj) {
        return (obj instanceof BigDecimal) || isFloatOrDoubleOrDotEe(obj);
    }

    public static final boolean isDotEe(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '.' || cCharAt == 'E' || cCharAt == 'e') {
                return true;
            }
        }
        return false;
    }

    public static final boolean isFloatOrDouble(Object obj) {
        return (obj instanceof Float) || (obj instanceof Double);
    }

    public static final boolean isFloatOrDoubleOrDotEe(Object obj) {
        return isFloatOrDouble(obj) || isDotEe(obj);
    }

    public static final Number mod(TypeConverter typeConverter, Object obj, Object obj2) {
        return (obj == null && obj2 == null) ? LONG_ZERO : (isBigDecimalOrFloatOrDoubleOrDotEe(obj) || isBigDecimalOrFloatOrDoubleOrDotEe(obj2)) ? Double.valueOf(((Double) typeConverter.convert(obj, Double.class)).doubleValue() % ((Double) typeConverter.convert(obj2, Double.class)).doubleValue()) : ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) ? ((BigInteger) typeConverter.convert(obj, BigInteger.class)).remainder((BigInteger) typeConverter.convert(obj2, BigInteger.class)) : Long.valueOf(((Long) typeConverter.convert(obj, Long.class)).longValue() % ((Long) typeConverter.convert(obj2, Long.class)).longValue());
    }

    public static final Number mul(TypeConverter typeConverter, Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return LONG_ZERO;
        }
        if ((obj instanceof BigDecimal) || (obj2 instanceof BigDecimal)) {
            return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).multiply((BigDecimal) typeConverter.convert(obj2, BigDecimal.class));
        }
        if (isFloatOrDoubleOrDotEe(obj) || isFloatOrDoubleOrDotEe(obj2)) {
            if ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) {
                return ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).multiply((BigDecimal) typeConverter.convert(obj2, BigDecimal.class));
            }
            return Double.valueOf(((Double) typeConverter.convert(obj2, Double.class)).doubleValue() * ((Double) typeConverter.convert(obj, Double.class)).doubleValue());
        }
        if ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) {
            return ((BigInteger) typeConverter.convert(obj, BigInteger.class)).multiply((BigInteger) typeConverter.convert(obj2, BigInteger.class));
        }
        return Long.valueOf(((Long) typeConverter.convert(obj2, Long.class)).longValue() * ((Long) typeConverter.convert(obj, Long.class)).longValue());
    }

    public static final Number neg(TypeConverter typeConverter, Object obj) {
        if (obj == null) {
            return LONG_ZERO;
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).negate();
        }
        if (obj instanceof BigInteger) {
            return ((BigInteger) obj).negate();
        }
        if (obj instanceof Double) {
            return Double.valueOf(-((Double) obj).doubleValue());
        }
        if (obj instanceof Float) {
            return Float.valueOf(-((Float) obj).floatValue());
        }
        if (obj instanceof String) {
            return isDotEe((String) obj) ? Double.valueOf(-((Double) typeConverter.convert(obj, Double.class)).doubleValue()) : Long.valueOf(-((Long) typeConverter.convert(obj, Long.class)).longValue());
        }
        if (obj instanceof Long) {
            return Long.valueOf(-((Long) obj).longValue());
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(-((Integer) obj).intValue());
        }
        if (obj instanceof Short) {
            return Short.valueOf((short) (-((Short) obj).shortValue()));
        }
        if (obj instanceof Byte) {
            return Byte.valueOf((byte) (-((Byte) obj).byteValue()));
        }
        throw new ELException(LocalMessages.get("error.negate", obj.getClass()));
    }

    public static final Number sub(TypeConverter typeConverter, Object obj, Object obj2) {
        return (obj == null && obj2 == null) ? LONG_ZERO : ((obj instanceof BigDecimal) || (obj2 instanceof BigDecimal)) ? ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).subtract((BigDecimal) typeConverter.convert(obj2, BigDecimal.class)) : (isFloatOrDoubleOrDotEe(obj) || isFloatOrDoubleOrDotEe(obj2)) ? ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) ? ((BigDecimal) typeConverter.convert(obj, BigDecimal.class)).subtract((BigDecimal) typeConverter.convert(obj2, BigDecimal.class)) : Double.valueOf(((Double) typeConverter.convert(obj, Double.class)).doubleValue() - ((Double) typeConverter.convert(obj2, Double.class)).doubleValue()) : ((obj instanceof BigInteger) || (obj2 instanceof BigInteger)) ? ((BigInteger) typeConverter.convert(obj, BigInteger.class)).subtract((BigInteger) typeConverter.convert(obj2, BigInteger.class)) : Long.valueOf(((Long) typeConverter.convert(obj, Long.class)).longValue() - ((Long) typeConverter.convert(obj2, Long.class)).longValue());
    }

    public static final boolean isDotEe(Object obj) {
        return (obj instanceof String) && isDotEe((String) obj);
    }
}
