package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Enums {

    @GwtIncompatible
    public static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap();

    @GwtIncompatible
    public static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> enumClass) {
        Map<String, WeakReference<? extends Enum<?>>> mapPopulateCache;
        Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> map = enumConstantCache;
        synchronized (map) {
            try {
                mapPopulateCache = map.get(enumClass);
                if (mapPopulateCache == null) {
                    mapPopulateCache = populateCache(enumClass);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mapPopulateCache;
    }

    @GwtIncompatible
    public static Field getField(Enum<?> enumValue) {
        try {
            return enumValue.getDeclaringClass().getDeclaredField(enumValue.name());
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> enumClass, String value) {
        enumClass.getClass();
        value.getClass();
        return Platform.getEnumIfPresent(enumClass, value);
    }

    @GwtIncompatible
    public static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> enumClass) {
        HashMap map = new HashMap();
        for (Enum r2 : EnumSet.allOf(enumClass)) {
            map.put(r2.name(), new WeakReference(r2));
        }
        enumConstantCache.put(enumClass, map);
        return map;
    }

    @GwtIncompatible
    public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> enumClass) {
        return new StringConverter(enumClass);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Class<T> enumClass;

        public StringConverter(Class<T> enumClass) {
            super(true);
            enumClass.getClass();
            this.enumClass = enumClass;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Object enumValue) {
            return ((Enum) enumValue).name();
        }

        @Override // com.google.common.base.Converter
        public Object doForward(String value) {
            return Enum.valueOf(this.enumClass, value);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof StringConverter) {
                return this.enumClass.equals(((StringConverter) object).enumClass);
            }
            return false;
        }

        public int hashCode() {
            return this.enumClass.hashCode();
        }

        public String toString() {
            return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
        }

        public String doBackward(T enumValue) {
            return enumValue.name();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public T doForward2(String str) {
            return (T) Enum.valueOf(this.enumClass, str);
        }
    }
}
