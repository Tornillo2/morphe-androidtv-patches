package androidx.work;

import androidx.annotation.NonNull;
import androidx.work.Data;
import j$.util.DesugarCollections;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ArrayCreatingInputMerger extends InputMerger {
    public final Object concatenateArrayAndNonArray(Object array, Object obj) {
        int length = Array.getLength(array);
        Object objNewInstance = Array.newInstance(obj.getClass(), length + 1);
        System.arraycopy(array, 0, objNewInstance, 0, length);
        Array.set(objNewInstance, length, obj);
        return objNewInstance;
    }

    public final Object concatenateArrays(Object array1, Object array2) {
        int length = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        Object objNewInstance = Array.newInstance(array1.getClass().getComponentType(), length + length2);
        System.arraycopy(array1, 0, objNewInstance, 0, length);
        System.arraycopy(array2, 0, objNewInstance, length, length2);
        return objNewInstance;
    }

    public final Object concatenateNonArrays(Object obj1, Object obj2) {
        Object objNewInstance = Array.newInstance(obj1.getClass(), 2);
        Array.set(objNewInstance, 0, obj1);
        Array.set(objNewInstance, 1, obj2);
        return objNewInstance;
    }

    public final Object createArrayFor(Object obj) {
        Object objNewInstance = Array.newInstance(obj.getClass(), 1);
        Array.set(objNewInstance, 0, obj);
        return objNewInstance;
    }

    @Override // androidx.work.InputMerger
    @NonNull
    public Data merge(@NonNull List<Data> inputs) {
        Data.Builder builder = new Data.Builder();
        HashMap map = new HashMap();
        Iterator<Data> it = inputs.iterator();
        while (it.hasNext()) {
            for (Map.Entry entry : DesugarCollections.unmodifiableMap(it.next().mValues).entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                Class<?> cls = value.getClass();
                Object obj = map.get(str);
                if (obj != null) {
                    Class<?> cls2 = obj.getClass();
                    if (cls2.equals(cls)) {
                        value = cls2.isArray() ? concatenateArrays(obj, value) : concatenateNonArrays(obj, value);
                    } else if (cls2.isArray() && cls2.getComponentType().equals(cls)) {
                        value = concatenateArrayAndNonArray(obj, value);
                    } else {
                        if (!cls.isArray() || !cls.getComponentType().equals(cls2)) {
                            throw new IllegalArgumentException();
                        }
                        value = concatenateArrayAndNonArray(value, obj);
                    }
                } else if (!cls.isArray()) {
                    value = createArrayFor(value);
                }
                map.put(str, value);
            }
        }
        builder.putAll(map);
        return builder.build();
    }
}
