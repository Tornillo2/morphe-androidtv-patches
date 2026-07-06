package com.amazon.livingroom.deviceproperties.expression;

import androidx.annotation.NonNull;
import com.amazon.livingroom.reflection.ReflectionCall;
import java.lang.reflect.Array;
import java.util.Locale;
import javax.el.ELException;
import org.apache.commons.text.StringTokenizer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ReflectionCall
public class TypeConverterImpl extends de.odysseus.el.misc.TypeConverterImpl {
    public final Object allocateArray(@NonNull Class<?> cls, int i) {
        try {
            return Array.newInstance(cls, i);
        } catch (Exception e) {
            throw new ELException("Failed to allocate array of type " + cls, e);
        }
    }

    @Override // de.odysseus.el.misc.TypeConverterImpl
    public Object coerceStringToType(@NonNull String str, @NonNull Class<?> cls) {
        Class<?> componentType = cls.getComponentType();
        if (componentType != null) {
            return coerceToArray(str, componentType);
        }
        throw new ELException(String.format(Locale.US, "Cannot coerce '%s' from String to '%s'", str, cls));
    }

    @NonNull
    public final Object coerceToArray(@NonNull String str, @NonNull Class<?> cls) {
        StringTokenizer cSVInstance = StringTokenizer.getCSVInstance(str);
        Object objAllocateArray = allocateArray(cls, cSVInstance.size());
        int size = cSVInstance.size();
        for (int i = 0; i < size; i++) {
            Array.set(objAllocateArray, i, convert(cSVInstance.next(), cls));
        }
        return objAllocateArray;
    }

    @Override // de.odysseus.el.misc.TypeConverterImpl, de.odysseus.el.misc.TypeConverter
    public <T> T convert(Object obj, Class<T> cls) throws ELException {
        if (obj == null) {
            return null;
        }
        return (T) coerceToType(obj, cls);
    }
}
