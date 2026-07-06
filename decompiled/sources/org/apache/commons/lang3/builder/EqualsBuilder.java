package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class EqualsBuilder implements Builder<Boolean> {
    public static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal<>();
    public List<Class<?>> bypassReflectionClasses;
    public boolean isEquals = true;
    public boolean testTransients = false;
    public boolean testRecursive = false;
    public Class<?> reflectUpToClass = null;
    public String[] excludeFields = null;

    public EqualsBuilder() {
        ArrayList arrayList = new ArrayList();
        this.bypassReflectionClasses = arrayList;
        arrayList.add(String.class);
    }

    public static Pair<IDKey, IDKey> getRegisterPair(Object obj, Object obj2) {
        return new ImmutablePair(new IDKey(obj), new IDKey(obj2));
    }

    public static Set<Pair<IDKey, IDKey>> getRegistry() {
        return REGISTRY.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isRegistered(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        Pair<IDKey, IDKey> registerPair = getRegisterPair(obj, obj2);
        ImmutablePair immutablePair = (ImmutablePair) registerPair;
        ImmutablePair immutablePair2 = new ImmutablePair((IDKey) immutablePair.right, (IDKey) immutablePair.left);
        if (registry != null) {
            return registry.contains(registerPair) || registry.contains(immutablePair2);
        }
        return false;
    }

    public static boolean reflectionEquals(Object obj, Object obj2, Collection<String> collection) {
        return reflectionEquals(obj, obj2, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static void register(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        if (registry == null) {
            registry = new HashSet<>();
            REGISTRY.set(registry);
        }
        registry.add(getRegisterPair(obj, obj2));
    }

    public static void unregister(Object obj, Object obj2) {
        Set<Pair<IDKey, IDKey>> registry = getRegistry();
        if (registry != null) {
            registry.remove(getRegisterPair(obj, obj2));
            if (registry.isEmpty()) {
                REGISTRY.remove();
            }
        }
    }

    public EqualsBuilder append(Object obj, Object obj2) {
        if (!this.isEquals || obj == obj2) {
            return this;
        }
        if (obj == null || obj2 == null) {
            setEquals(false);
            return this;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            appendArray(obj, obj2);
            return this;
        }
        if (!this.testRecursive || ClassUtils.isPrimitiveOrWrapper(cls)) {
            this.isEquals = obj.equals(obj2);
            return this;
        }
        reflectionAppend(obj, obj2);
        return this;
    }

    public final void appendArray(Object obj, Object obj2) {
        if (obj.getClass() != obj2.getClass()) {
            setEquals(false);
            return;
        }
        if (obj instanceof long[]) {
            append((long[]) obj, (long[]) obj2);
            return;
        }
        if (obj instanceof int[]) {
            append((int[]) obj, (int[]) obj2);
            return;
        }
        if (obj instanceof short[]) {
            append((short[]) obj, (short[]) obj2);
            return;
        }
        if (obj instanceof char[]) {
            append((char[]) obj, (char[]) obj2);
            return;
        }
        if (obj instanceof byte[]) {
            append((byte[]) obj, (byte[]) obj2);
            return;
        }
        if (obj instanceof double[]) {
            append((double[]) obj, (double[]) obj2);
            return;
        }
        if (obj instanceof float[]) {
            append((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            append((boolean[]) obj, (boolean[]) obj2);
        } else {
            append((Object[]) obj, (Object[]) obj2);
        }
    }

    public EqualsBuilder appendSuper(boolean z) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z;
        return this;
    }

    public boolean isEquals() {
        return this.isEquals;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.lang3.builder.EqualsBuilder reflectionAppend(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean r0 = r5.isEquals
            if (r0 != 0) goto L6
            goto L6b
        L6:
            if (r6 != r7) goto L9
            goto L6b
        L9:
            r0 = 0
            if (r6 == 0) goto L72
            if (r7 != 0) goto Lf
            goto L72
        Lf:
            java.lang.Class r1 = r6.getClass()
            java.lang.Class r2 = r7.getClass()
            boolean r3 = r1.isInstance(r7)
            if (r3 == 0) goto L24
            boolean r3 = r2.isInstance(r6)
            if (r3 != 0) goto L30
            goto L32
        L24:
            boolean r3 = r2.isInstance(r6)
            if (r3 == 0) goto L6f
            boolean r3 = r1.isInstance(r7)
            if (r3 != 0) goto L32
        L30:
            r3 = r1
            goto L33
        L32:
            r3 = r2
        L33:
            boolean r4 = r3.isArray()     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r4 == 0) goto L3d
            r5.append(r6, r7)     // Catch: java.lang.IllegalArgumentException -> L6c
            return r5
        L3d:
            java.util.List<java.lang.Class<?>> r4 = r5.bypassReflectionClasses     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r4 == 0) goto L56
            boolean r1 = r4.contains(r1)     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r1 != 0) goto L4f
            java.util.List<java.lang.Class<?>> r1 = r5.bypassReflectionClasses     // Catch: java.lang.IllegalArgumentException -> L6c
            boolean r1 = r1.contains(r2)     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r1 == 0) goto L56
        L4f:
            boolean r6 = r6.equals(r7)     // Catch: java.lang.IllegalArgumentException -> L6c
            r5.isEquals = r6     // Catch: java.lang.IllegalArgumentException -> L6c
            return r5
        L56:
            r5.reflectionAppend(r6, r7, r3)     // Catch: java.lang.IllegalArgumentException -> L6c
        L59:
            java.lang.Class r1 = r3.getSuperclass()     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r1 == 0) goto L6b
            java.lang.Class<?> r1 = r5.reflectUpToClass     // Catch: java.lang.IllegalArgumentException -> L6c
            if (r3 == r1) goto L6b
            java.lang.Class r3 = r3.getSuperclass()     // Catch: java.lang.IllegalArgumentException -> L6c
            r5.reflectionAppend(r6, r7, r3)     // Catch: java.lang.IllegalArgumentException -> L6c
            goto L59
        L6b:
            return r5
        L6c:
            r5.isEquals = r0
            return r5
        L6f:
            r5.isEquals = r0
            return r5
        L72:
            r5.isEquals = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.builder.EqualsBuilder.reflectionAppend(java.lang.Object, java.lang.Object):org.apache.commons.lang3.builder.EqualsBuilder");
    }

    public void reset() {
        this.isEquals = true;
    }

    public EqualsBuilder setBypassReflectionClasses(List<Class<?>> list) {
        this.bypassReflectionClasses = list;
        return this;
    }

    public void setEquals(boolean z) {
        this.isEquals = z;
    }

    public EqualsBuilder setExcludeFields(String... strArr) {
        this.excludeFields = strArr;
        return this;
    }

    public EqualsBuilder setReflectUpToClass(Class<?> cls) {
        this.reflectUpToClass = cls;
        return this;
    }

    public EqualsBuilder setTestRecursive(boolean z) {
        this.testRecursive = z;
        return this;
    }

    public EqualsBuilder setTestTransients(boolean z) {
        this.testTransients = z;
        return this;
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z) {
        return reflectionEquals(obj, obj2, z, null, false, new String[0]);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.builder.Builder
    public Boolean build() {
        return Boolean.valueOf(isEquals());
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z, Class<?> cls, String... strArr) {
        return reflectionEquals(obj, obj2, z, cls, false, strArr);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, String... strArr) {
        return reflectionEquals(obj, obj2, false, null, false, strArr);
    }

    public static boolean reflectionEquals(Object obj, Object obj2, boolean z, Class<?> cls, boolean z2, String... strArr) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.excludeFields = strArr;
        equalsBuilder.reflectUpToClass = cls;
        equalsBuilder.testTransients = z;
        equalsBuilder.testRecursive = z2;
        equalsBuilder.reflectionAppend(obj, obj2);
        return equalsBuilder.isEquals;
    }

    public EqualsBuilder append(long j, long j2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = j == j2;
        return this;
    }

    public EqualsBuilder append(int i, int i2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = i == i2;
        return this;
    }

    public EqualsBuilder append(short s, short s2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = s == s2;
        return this;
    }

    public EqualsBuilder append(char c, char c2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = c == c2;
        return this;
    }

    public EqualsBuilder append(byte b, byte b2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = b == b2;
        return this;
    }

    public EqualsBuilder append(double d, double d2) {
        return !this.isEquals ? this : append(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    public final void reflectionAppend(Object obj, Object obj2, Class<?> cls) {
        if (isRegistered(obj, obj2)) {
            return;
        }
        try {
            register(obj, obj2);
            Field[] declaredFields = cls.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);
            for (int i = 0; i < declaredFields.length && this.isEquals; i++) {
                Field field = declaredFields[i];
                if (!ArrayUtils.contains(this.excludeFields, field.getName()) && !field.getName().contains("$") && (this.testTransients || !Modifier.isTransient(field.getModifiers()))) {
                    if (!Modifier.isStatic(field.getModifiers()) && !field.isAnnotationPresent(EqualsExclude.class)) {
                        try {
                            append(field.get(obj), field.get(obj2));
                        } catch (IllegalAccessException unused) {
                            throw new InternalError("Unexpected IllegalAccessException");
                        }
                    }
                }
            }
            unregister(obj, obj2);
        } catch (Throwable th) {
            unregister(obj, obj2);
            throw th;
        }
    }

    public EqualsBuilder append(float f, float f2) {
        return !this.isEquals ? this : append(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    public EqualsBuilder append(boolean z, boolean z2) {
        if (!this.isEquals) {
            return this;
        }
        this.isEquals = z == z2;
        return this;
    }

    public EqualsBuilder append(Object[] objArr, Object[] objArr2) {
        if (this.isEquals && objArr != objArr2) {
            if (objArr != null && objArr2 != null) {
                if (objArr.length != objArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < objArr.length && this.isEquals; i++) {
                    append(objArr[i], objArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(long[] jArr, long[] jArr2) {
        if (this.isEquals && jArr != jArr2) {
            if (jArr != null && jArr2 != null) {
                if (jArr.length != jArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < jArr.length && this.isEquals; i++) {
                    append(jArr[i], jArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(int[] iArr, int[] iArr2) {
        if (this.isEquals && iArr != iArr2) {
            if (iArr != null && iArr2 != null) {
                if (iArr.length != iArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < iArr.length && this.isEquals; i++) {
                    append(iArr[i], iArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(short[] sArr, short[] sArr2) {
        if (this.isEquals && sArr != sArr2) {
            if (sArr != null && sArr2 != null) {
                if (sArr.length != sArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < sArr.length && this.isEquals; i++) {
                    append(sArr[i], sArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(char[] cArr, char[] cArr2) {
        if (this.isEquals && cArr != cArr2) {
            if (cArr != null && cArr2 != null) {
                if (cArr.length != cArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < cArr.length && this.isEquals; i++) {
                    append(cArr[i], cArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(byte[] bArr, byte[] bArr2) {
        if (this.isEquals && bArr != bArr2) {
            if (bArr != null && bArr2 != null) {
                if (bArr.length != bArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < bArr.length && this.isEquals; i++) {
                    append(bArr[i], bArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(double[] dArr, double[] dArr2) {
        if (this.isEquals && dArr != dArr2) {
            if (dArr != null && dArr2 != null) {
                if (dArr.length != dArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < dArr.length && this.isEquals; i++) {
                    append(dArr[i], dArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(float[] fArr, float[] fArr2) {
        if (this.isEquals && fArr != fArr2) {
            if (fArr != null && fArr2 != null) {
                if (fArr.length != fArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < fArr.length && this.isEquals; i++) {
                    append(fArr[i], fArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }

    public EqualsBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (this.isEquals && zArr != zArr2) {
            if (zArr != null && zArr2 != null) {
                if (zArr.length != zArr2.length) {
                    setEquals(false);
                    return this;
                }
                for (int i = 0; i < zArr.length && this.isEquals; i++) {
                    append(zArr[i], zArr2[i]);
                }
            } else {
                setEquals(false);
                return this;
            }
        }
        return this;
    }
}
