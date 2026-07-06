package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import j$.util.DesugarCollections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Primitives {
    public static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;
    public static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(16);
        Class cls = Boolean.TYPE;
        linkedHashMap.put(cls, Boolean.class);
        linkedHashMap2.put(Boolean.class, cls);
        Class cls2 = Byte.TYPE;
        linkedHashMap.put(cls2, Byte.class);
        linkedHashMap2.put(Byte.class, cls2);
        Class cls3 = Character.TYPE;
        linkedHashMap.put(cls3, Character.class);
        linkedHashMap2.put(Character.class, cls3);
        Class cls4 = Double.TYPE;
        linkedHashMap.put(cls4, Double.class);
        linkedHashMap2.put(Double.class, cls4);
        Class cls5 = Float.TYPE;
        linkedHashMap.put(cls5, Float.class);
        linkedHashMap2.put(Float.class, cls5);
        Class cls6 = Integer.TYPE;
        linkedHashMap.put(cls6, Integer.class);
        linkedHashMap2.put(Integer.class, cls6);
        Class cls7 = Long.TYPE;
        linkedHashMap.put(cls7, Long.class);
        linkedHashMap2.put(Long.class, cls7);
        Class cls8 = Short.TYPE;
        linkedHashMap.put(cls8, Short.class);
        linkedHashMap2.put(Short.class, cls8);
        Class cls9 = Void.TYPE;
        linkedHashMap.put(cls9, Void.class);
        linkedHashMap2.put(Void.class, cls9);
        PRIMITIVE_TO_WRAPPER_TYPE = DesugarCollections.unmodifiableMap(linkedHashMap);
        WRAPPER_TO_PRIMITIVE_TYPE = DesugarCollections.unmodifiableMap(linkedHashMap2);
    }

    public static void add(Map<Class<?>, Class<?>> forward, Map<Class<?>, Class<?>> backward, Class<?> key, Class<?> value) {
        forward.put(key, value);
        backward.put(value, key);
    }

    public static Set<Class<?>> allPrimitiveTypes() {
        return PRIMITIVE_TO_WRAPPER_TYPE.keySet();
    }

    public static Set<Class<?>> allWrapperTypes() {
        return WRAPPER_TO_PRIMITIVE_TYPE.keySet();
    }

    public static boolean isWrapperType(Class<?> type) {
        Map<Class<?>, Class<?>> map = WRAPPER_TO_PRIMITIVE_TYPE;
        type.getClass();
        return map.containsKey(type);
    }

    public static <T> Class<T> unwrap(Class<T> type) {
        type.getClass();
        Class<T> cls = (Class) WRAPPER_TO_PRIMITIVE_TYPE.get(type);
        return cls == null ? type : cls;
    }

    public static <T> Class<T> wrap(Class<T> type) {
        type.getClass();
        Class<T> cls = (Class) PRIMITIVE_TO_WRAPPER_TYPE.get(type);
        return cls == null ? type : cls;
    }
}
