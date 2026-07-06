package de.odysseus.el.misc;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TypeConverterImpl implements TypeConverter {
    public static final long serialVersionUID = 1;

    public Object coerceStringToType(String str, Class<?> cls) {
        PropertyEditor propertyEditorFindEditor = PropertyEditorManager.findEditor(cls);
        if (propertyEditorFindEditor == null) {
            if ("".equals(str)) {
                return null;
            }
            throw new ELException(LocalMessages.get("error.coerce.type", str, String.class, cls));
        }
        if ("".equals(str)) {
            try {
                propertyEditorFindEditor.setAsText(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } else {
            try {
                propertyEditorFindEditor.setAsText(str);
            } catch (IllegalArgumentException unused2) {
                throw new ELException(LocalMessages.get("error.coerce.value", str, str.getClass(), cls));
            }
        }
        return propertyEditorFindEditor.getValue();
    }

    public BigDecimal coerceToBigDecimal(Object obj) {
        if (obj == null || "".equals(obj)) {
            return BigDecimal.valueOf(0L);
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        if (obj instanceof Number) {
            return new BigDecimal(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return new BigDecimal((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), BigDecimal.class));
            }
        }
        if (obj instanceof Character) {
            return new BigDecimal((int) ((short) ((Character) obj).charValue()));
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), BigDecimal.class));
    }

    public BigInteger coerceToBigInteger(Object obj) {
        if (obj == null || "".equals(obj)) {
            return BigInteger.valueOf(0L);
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).toBigInteger();
        }
        if (obj instanceof Number) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            try {
                return new BigInteger((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), BigInteger.class));
            }
        }
        if (obj instanceof Character) {
            return BigInteger.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), BigInteger.class));
    }

    public Boolean coerceToBoolean(Object obj) {
        if (obj == null || "".equals(obj)) {
            return Boolean.FALSE;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            return Boolean.valueOf((String) obj);
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Boolean.class));
    }

    public Byte coerceToByte(Object obj) {
        if (obj == null || "".equals(obj)) {
            return (byte) 0;
        }
        if (obj instanceof Byte) {
            return (Byte) obj;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            try {
                return Byte.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Byte.class));
            }
        }
        if (obj instanceof Character) {
            return Byte.valueOf(Short.valueOf((short) ((Character) obj).charValue()).byteValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Byte.class));
    }

    public Character coerceToCharacter(Object obj) {
        if (obj == null || "".equals(obj)) {
            return (char) 0;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof Number) {
            return Character.valueOf((char) ((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            return Character.valueOf(((String) obj).charAt(0));
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Character.class));
    }

    public Double coerceToDouble(Object obj) {
        if (obj == null || "".equals(obj)) {
            return Double.valueOf(0.0d);
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Double.class));
            }
        }
        if (obj instanceof Character) {
            return Double.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Double.class));
    }

    public <T extends Enum<T>> T coerceToEnum(Object obj, Class<T> cls) {
        if (obj == null || "".equals(obj)) {
            return null;
        }
        if (cls.isInstance(obj)) {
            return (T) obj;
        }
        if (!(obj instanceof String)) {
            throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), cls));
        }
        try {
            return (T) Enum.valueOf(cls, (String) obj);
        } catch (IllegalArgumentException unused) {
            throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), cls));
        }
    }

    public Float coerceToFloat(Object obj) {
        if (obj == null || "".equals(obj)) {
            return Float.valueOf(0.0f);
        }
        if (obj instanceof Float) {
            return (Float) obj;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            try {
                return Float.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Float.class));
            }
        }
        if (obj instanceof Character) {
            return Float.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Float.class));
    }

    public Integer coerceToInteger(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Integer.class));
            }
        }
        if (obj instanceof Character) {
            return Integer.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Integer.class));
    }

    public Long coerceToLong(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0L;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Long.class));
            }
        }
        if (obj instanceof Character) {
            return Long.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Long.class));
    }

    public Short coerceToShort(Object obj) {
        if (obj == null || "".equals(obj)) {
            return (short) 0;
        }
        if (obj instanceof Short) {
            return (Short) obj;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            try {
                return Short.valueOf((String) obj);
            } catch (NumberFormatException unused) {
                throw new ELException(LocalMessages.get("error.coerce.value", obj, obj.getClass(), Short.class));
            }
        }
        if (obj instanceof Character) {
            return Short.valueOf((short) ((Character) obj).charValue());
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), Short.class));
    }

    public String coerceToString(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj instanceof Enum ? ((Enum) obj).name() : obj.toString();
    }

    public Object coerceToType(Object obj, Class<?> cls) {
        if (cls == String.class) {
            return coerceToString(obj);
        }
        if (cls == Long.class || cls == Long.TYPE) {
            return coerceToLong(obj);
        }
        if (cls == Double.class || cls == Double.TYPE) {
            return coerceToDouble(obj);
        }
        if (cls == Boolean.class || cls == Boolean.TYPE) {
            return coerceToBoolean(obj);
        }
        if (cls == Integer.class || cls == Integer.TYPE) {
            return coerceToInteger(obj);
        }
        if (cls == Float.class || cls == Float.TYPE) {
            return coerceToFloat(obj);
        }
        if (cls == Short.class || cls == Short.TYPE) {
            return coerceToShort(obj);
        }
        if (cls == Byte.class || cls == Byte.TYPE) {
            return coerceToByte(obj);
        }
        if (cls == Character.class || cls == Character.TYPE) {
            return coerceToCharacter(obj);
        }
        if (cls == BigDecimal.class) {
            return coerceToBigDecimal(obj);
        }
        if (cls == BigInteger.class) {
            return coerceToBigInteger(obj);
        }
        if (cls.getSuperclass() == Enum.class) {
            return coerceToEnum(obj, cls);
        }
        if (obj == null || obj.getClass() == cls || cls.isInstance(obj)) {
            return obj;
        }
        if (obj instanceof String) {
            return coerceStringToType((String) obj, cls);
        }
        throw new ELException(LocalMessages.get("error.coerce.type", obj, obj.getClass(), cls));
    }

    @Override // de.odysseus.el.misc.TypeConverter
    public <T> T convert(Object obj, Class<T> cls) throws ELException {
        return (T) coerceToType(obj, cls);
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(getClass());
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
