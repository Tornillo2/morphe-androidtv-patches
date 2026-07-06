package org.apache.commons.lang3.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ClassUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class MemberUtils {
    public static final int ACCESS_TEST = 7;
    public static final Class<?>[] ORDERED_PRIMITIVE_TYPES = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Executable {
        public final boolean isVarArgs;
        public final Class<?>[] parameterTypes;

        public Executable(Method method) {
            this.parameterTypes = method.getParameterTypes();
            this.isVarArgs = method.isVarArgs();
        }

        public static Executable access$000(Constructor constructor) {
            return new Executable((Constructor<?>) constructor);
        }

        public static Executable access$100(Method method) {
            return new Executable(method);
        }

        public static Executable of(Method method) {
            return new Executable(method);
        }

        public Class<?>[] getParameterTypes() {
            return this.parameterTypes;
        }

        public boolean isVarArgs() {
            return this.isVarArgs;
        }

        public static Executable of(Constructor<?> constructor) {
            return new Executable(constructor);
        }

        public Executable(Constructor<?> constructor) {
            this.parameterTypes = constructor.getParameterTypes();
            this.isVarArgs = constructor.isVarArgs();
        }
    }

    public static int compareConstructorFit(Constructor<?> constructor, Constructor<?> constructor2, Class<?>[] clsArr) {
        return Float.compare(getTotalTransformationCost(clsArr, new Executable(constructor)), getTotalTransformationCost(clsArr, new Executable(constructor2)));
    }

    public static int compareMethodFit(Method method, Method method2, Class<?>[] clsArr) {
        return Float.compare(getTotalTransformationCost(clsArr, new Executable(method)), getTotalTransformationCost(clsArr, new Executable(method2)));
    }

    public static int compareParameterTypes(Executable executable, Executable executable2, Class<?>[] clsArr) {
        return Float.compare(getTotalTransformationCost(clsArr, executable), getTotalTransformationCost(clsArr, executable2));
    }

    public static float getObjectTransformationCost(Class<?> cls, Class<?> cls2) {
        if (cls2.isPrimitive()) {
            return getPrimitivePromotionCost(cls, cls2);
        }
        float f = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && ClassUtils.isAssignable(cls, cls2)) {
                    f += 0.25f;
                    break;
                }
                f += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f + 1.5f : f;
    }

    public static float getPrimitivePromotionCost(Class<?> cls, Class<?> cls2) {
        float f;
        if (cls.isPrimitive()) {
            f = 0.0f;
        } else {
            cls = ClassUtils.wrapperToPrimitive(cls);
            f = 0.1f;
        }
        int i = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = ORDERED_PRIMITIVE_TYPES;
            if (i >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i]) {
                f += 0.1f;
                if (i < clsArr.length - 1) {
                    cls = clsArr[i + 1];
                }
            }
            i++;
        }
        return f;
    }

    public static float getTotalTransformationCost(Class<?>[] clsArr, Executable executable) {
        float objectTransformationCost;
        Class<?>[] clsArr2 = executable.parameterTypes;
        boolean z = executable.isVarArgs;
        int length = clsArr2.length;
        if (z) {
            length--;
        }
        long j = length;
        if (clsArr.length < j) {
            return Float.MAX_VALUE;
        }
        boolean z2 = false;
        float objectTransformationCost2 = 0.0f;
        for (int i = 0; i < j; i++) {
            objectTransformationCost2 += getObjectTransformationCost(clsArr[i], clsArr2[i]);
        }
        if (z) {
            boolean z3 = clsArr.length < clsArr2.length;
            if (clsArr.length == clsArr2.length && clsArr[clsArr.length - 1].isArray()) {
                z2 = true;
            }
            Class<?> componentType = clsArr2[clsArr2.length - 1].getComponentType();
            if (z3) {
                objectTransformationCost = getObjectTransformationCost(componentType, Object.class);
            } else if (z2) {
                objectTransformationCost = getObjectTransformationCost(clsArr[clsArr.length - 1].getComponentType(), componentType);
            } else {
                for (int length2 = clsArr2.length - 1; length2 < clsArr.length; length2++) {
                    objectTransformationCost2 += getObjectTransformationCost(clsArr[length2], componentType) + 0.001f;
                }
            }
            return objectTransformationCost + 0.001f + objectTransformationCost2;
        }
        return objectTransformationCost2;
    }

    public static boolean isAccessible(Member member) {
        return (member == null || !Modifier.isPublic(member.getModifiers()) || member.isSynthetic()) ? false : true;
    }

    public static boolean isMatchingConstructor(Constructor<?> constructor, Class<?>[] clsArr) {
        return isMatchingExecutable(new Executable(constructor), clsArr);
    }

    public static boolean isMatchingExecutable(Executable executable, Class<?>[] clsArr) {
        Class<?>[] clsArr2 = executable.parameterTypes;
        if (ClassUtils.isAssignable(clsArr, clsArr2, true)) {
            return true;
        }
        if (!executable.isVarArgs) {
            return false;
        }
        int i = 0;
        while (i < clsArr2.length - 1 && i < clsArr.length) {
            if (!ClassUtils.isAssignable(clsArr[i], clsArr2[i], true)) {
                return false;
            }
            i++;
        }
        Class<?> componentType = clsArr2[clsArr2.length - 1].getComponentType();
        while (i < clsArr.length) {
            if (!ClassUtils.isAssignable(clsArr[i], componentType, true)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isMatchingMethod(Method method, Class<?>[] clsArr) {
        return isMatchingExecutable(new Executable(method), clsArr);
    }

    public static boolean isPackageAccess(int i) {
        return (i & 7) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean setAccessibleWorkaround(AccessibleObject accessibleObject) {
        if (accessibleObject != 0 && !accessibleObject.isAccessible()) {
            Member member = (Member) accessibleObject;
            if (!accessibleObject.isAccessible() && Modifier.isPublic(member.getModifiers()) && isPackageAccess(member.getDeclaringClass().getModifiers())) {
                try {
                    accessibleObject.setAccessible(true);
                    return true;
                } catch (SecurityException unused) {
                }
            }
        }
        return false;
    }
}
