package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class Lifecycling {
    public static final int GENERATED_CALLBACK = 2;
    public static final int REFLECTIVE_CALLBACK = 1;

    @NotNull
    public static final Lifecycling INSTANCE = new Lifecycling();

    @NotNull
    public static final Map<Class<?>, Integer> callbackCache = new HashMap();

    @NotNull
    public static final Map<Class<?>, List<Constructor<? extends GeneratedAdapter>>> classToAdapters = new HashMap();

    @JvmStatic
    @NotNull
    public static final String getAdapterName(@NotNull String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        return StringsKt__StringsJVMKt.replace$default(className, ExternalFourCCMapper.CODEC_NAME_SPLITTER, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, false, 4, (Object) null).concat("_LifecycleAdapter");
    }

    @JvmStatic
    @NotNull
    public static final LifecycleEventObserver lifecycleEventObserver(@NotNull Object object) {
        Intrinsics.checkNotNullParameter(object, "object");
        boolean z = object instanceof LifecycleEventObserver;
        boolean z2 = object instanceof DefaultLifecycleObserver;
        if (z && z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) object, (LifecycleEventObserver) object);
        }
        if (z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) object, null);
        }
        if (z) {
            return (LifecycleEventObserver) object;
        }
        Class<?> cls = object.getClass();
        Lifecycling lifecycling = INSTANCE;
        if (lifecycling.getObserverConstructorType(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(object);
        }
        List<Constructor<? extends GeneratedAdapter>> list = classToAdapters.get(cls);
        Intrinsics.checkNotNull(list);
        List<Constructor<? extends GeneratedAdapter>> list2 = list;
        if (list2.size() == 1) {
            return new SingleGeneratedAdapterObserver(lifecycling.createGeneratedAdapter(list2.get(0), object));
        }
        int size = list2.size();
        GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
        for (int i = 0; i < size; i++) {
            generatedAdapterArr[i] = INSTANCE.createGeneratedAdapter(list2.get(i), object);
        }
        return new CompositeGeneratedAdaptersObserver(generatedAdapterArr);
    }

    public final GeneratedAdapter createGeneratedAdapter(Constructor<? extends GeneratedAdapter> constructor, Object obj) {
        try {
            GeneratedAdapter generatedAdapterNewInstance = constructor.newInstance(obj);
            Intrinsics.checkNotNullExpressionValue(generatedAdapterNewInstance, "{\n            constructo…tance(`object`)\n        }");
            return generatedAdapterNewInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public final Constructor<? extends GeneratedAdapter> generatedConstructor(Class<?> cls) {
        try {
            Package r0 = cls.getPackage();
            String name = cls.getCanonicalName();
            String fullPackage = r0 != null ? r0.getName() : "";
            Intrinsics.checkNotNullExpressionValue(fullPackage, "fullPackage");
            if (fullPackage.length() != 0) {
                Intrinsics.checkNotNullExpressionValue(name, "name");
                name = name.substring(fullPackage.length() + 1);
                Intrinsics.checkNotNullExpressionValue(name, "this as java.lang.String).substring(startIndex)");
            }
            Intrinsics.checkNotNullExpressionValue(name, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String adapterName = getAdapterName(name);
            if (fullPackage.length() != 0) {
                adapterName = fullPackage + '.' + adapterName;
            }
            Constructor declaredConstructor = Class.forName(adapterName).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public final int getObserverConstructorType(Class<?> cls) {
        Map<Class<?>, Integer> map = callbackCache;
        Integer num = map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iResolveObserverCallbackType = resolveObserverCallbackType(cls);
        map.put(cls, Integer.valueOf(iResolveObserverCallbackType));
        return iResolveObserverCallbackType;
    }

    public final boolean isLifecycleParent(Class<?> cls) {
        return cls != null && LifecycleObserver.class.isAssignableFrom(cls);
    }

    public final int resolveObserverCallbackType(Class<?> cls) {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends GeneratedAdapter> constructorGeneratedConstructor = generatedConstructor(cls);
        if (constructorGeneratedConstructor != null) {
            classToAdapters.put(cls, CollectionsKt__CollectionsJVMKt.listOf(constructorGeneratedConstructor));
            return 2;
        }
        if (ClassesInfoCache.sInstance.hasLifecycleMethods(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (isLifecycleParent(superclass)) {
            Intrinsics.checkNotNullExpressionValue(superclass, "superclass");
            if (getObserverConstructorType(superclass) == 1) {
                return 1;
            }
            List<Constructor<? extends GeneratedAdapter>> list = classToAdapters.get(superclass);
            Intrinsics.checkNotNull(list);
            arrayList = new ArrayList(list);
        } else {
            arrayList = null;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        Intrinsics.checkNotNullExpressionValue(interfaces, "klass.interfaces");
        for (Class<?> intrface : interfaces) {
            if (isLifecycleParent(intrface)) {
                Intrinsics.checkNotNullExpressionValue(intrface, "intrface");
                if (getObserverConstructorType(intrface) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                List<Constructor<? extends GeneratedAdapter>> list2 = classToAdapters.get(intrface);
                Intrinsics.checkNotNull(list2);
                arrayList.addAll(list2);
            }
        }
        if (arrayList == null) {
            return 1;
        }
        classToAdapters.put(cls, arrayList);
        return 2;
    }
}
