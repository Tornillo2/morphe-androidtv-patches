package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ReflectionToStringBuilder extends ToStringBuilder {
    public boolean appendStatics;
    public boolean appendTransients;
    public String[] excludeFieldNames;
    public boolean excludeNullValues;
    public Class<?> upToClass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReflectionToStringBuilder(Object obj) {
        super(obj, null, null);
        checkNotNull(obj);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    public static Object checkNotNull(Object obj) {
        Validate.isTrue(obj != null, "The Object passed in should not be null.", new Object[0]);
        return obj;
    }

    public static String[] toNoNullStringArray(Collection<String> collection) {
        return collection == null ? ArrayUtils.EMPTY_STRING_ARRAY : toNoNullStringArray(collection.toArray());
    }

    public static String toString(Object obj) {
        return toString(obj, null, false, false, null);
    }

    public static String toStringExclude(Object obj, Collection<String> collection) {
        return toStringExclude(obj, toNoNullStringArray(collection));
    }

    public boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers()) && !isAppendTransients()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !isAppendStatics()) {
            return false;
        }
        String[] strArr = this.excludeFieldNames;
        if (strArr == null || Arrays.binarySearch(strArr, field.getName()) < 0) {
            return !field.isAnnotationPresent(ToStringExclude.class);
        }
        return false;
    }

    public void appendFieldsIn(Class<?> cls) {
        if (cls.isArray()) {
            reflectionAppendArray(getObject());
            return;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (Field field : declaredFields) {
            String name = field.getName();
            if (accept(field)) {
                try {
                    Object value = getValue(field);
                    if (!this.excludeNullValues || value != null) {
                        append(name, value, !field.isAnnotationPresent(ToStringSummary.class));
                    }
                } catch (IllegalAccessException e) {
                    throw new InternalError("Unexpected IllegalAccessException: " + e.getMessage());
                }
            }
        }
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    public Class<?> getUpToClass() {
        return this.upToClass;
    }

    public Object getValue(Field field) throws IllegalAccessException {
        return field.get(getObject());
    }

    public boolean isAppendStatics() {
        return this.appendStatics;
    }

    public boolean isAppendTransients() {
        return this.appendTransients;
    }

    public boolean isExcludeNullValues() {
        return this.excludeNullValues;
    }

    public ReflectionToStringBuilder reflectionAppendArray(Object obj) {
        getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, obj);
        return this;
    }

    public void setAppendStatics(boolean z) {
        this.appendStatics = z;
    }

    public void setAppendTransients(boolean z) {
        this.appendTransients = z;
    }

    public ReflectionToStringBuilder setExcludeFieldNames(String... strArr) {
        if (strArr == null) {
            this.excludeFieldNames = null;
            return this;
        }
        String[] noNullStringArray = toNoNullStringArray(strArr);
        this.excludeFieldNames = noNullStringArray;
        Arrays.sort(noNullStringArray);
        return this;
    }

    public void setExcludeNullValues(boolean z) {
        this.excludeNullValues = z;
    }

    public void setUpToClass(Class<?> cls) {
        Object object;
        if (cls != null && (object = getObject()) != null && !cls.isInstance(object)) {
            throw new IllegalArgumentException("Specified class is not a superclass of the object");
        }
        this.upToClass = cls;
    }

    public static String toString(Object obj, ToStringStyle toStringStyle) {
        return toString(obj, toStringStyle, false, false, null);
    }

    public static String toStringExclude(Object obj, String... strArr) {
        ReflectionToStringBuilder reflectionToStringBuilder = new ReflectionToStringBuilder(obj);
        reflectionToStringBuilder.setExcludeFieldNames(strArr);
        return reflectionToStringBuilder.toString();
    }

    public static String[] toNoNullStringArray(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj.toString());
            }
        }
        return (String[]) arrayList.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z) {
        return toString(obj, toStringStyle, z, false, null);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z, boolean z2) {
        return toString(obj, toStringStyle, z, z2, null);
    }

    public static <T> String toString(T t, ToStringStyle toStringStyle, boolean z, boolean z2, Class<? super T> cls) {
        return new ReflectionToStringBuilder(t, toStringStyle, null, cls, z, z2).toString();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        super(obj, toStringStyle, null);
        checkNotNull(obj);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    public static <T> String toString(T t, ToStringStyle toStringStyle, boolean z, boolean z2, boolean z3, Class<? super T> cls) {
        return new ReflectionToStringBuilder(t, toStringStyle, null, cls, z, z2, z3).toString();
    }

    @Override // org.apache.commons.lang3.builder.ToStringBuilder
    public String toString() {
        if (getObject() == null) {
            return getStyle().getNullText();
        }
        Class<?> superclass = getObject().getClass();
        appendFieldsIn(superclass);
        while (superclass.getSuperclass() != null && superclass != getUpToClass()) {
            superclass = superclass.getSuperclass();
            appendFieldsIn(superclass);
        }
        return super.toString();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        super(obj, toStringStyle, stringBuffer);
        checkNotNull(obj);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public <T> ReflectionToStringBuilder(T t, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class<? super T> cls, boolean z, boolean z2) {
        super(t, toStringStyle, stringBuffer);
        checkNotNull(t);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
        setUpToClass(cls);
        setAppendTransients(z);
        setAppendStatics(z2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public <T> ReflectionToStringBuilder(T t, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class<? super T> cls, boolean z, boolean z2, boolean z3) {
        super(t, toStringStyle, stringBuffer);
        checkNotNull(t);
        this.appendStatics = false;
        this.appendTransients = false;
        this.upToClass = null;
        setUpToClass(cls);
        setAppendTransients(z);
        setAppendStatics(z2);
        setExcludeNullValues(z3);
    }
}
