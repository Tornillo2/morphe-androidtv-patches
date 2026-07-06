package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.Collections;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Reflection {
    public static final KClass[] EMPTY_K_CLASS_ARRAY;
    public static final String REFLECTION_NOT_AVAILABLE = " (Kotlin reflection is not available)";
    public static final ReflectionFactory factory;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        factory = reflectionFactory;
        EMPTY_K_CLASS_ARRAY = new KClass[0];
    }

    public static KClass createKotlinClass(Class cls) {
        return factory.createKotlinClass(cls);
    }

    public static KFunction function(FunctionReference functionReference) {
        return factory.function(functionReference);
    }

    public static KClass getOrCreateKotlinClass(Class cls) {
        return factory.getOrCreateKotlinClass(cls);
    }

    public static KClass[] getOrCreateKotlinClasses(Class[] clsArr) {
        int length = clsArr.length;
        if (length == 0) {
            return EMPTY_K_CLASS_ARRAY;
        }
        KClass[] kClassArr = new KClass[length];
        for (int i = 0; i < length; i++) {
            kClassArr[i] = factory.getOrCreateKotlinClass(clsArr[i]);
        }
        return kClassArr;
    }

    @SinceKotlin(version = "1.4")
    public static KDeclarationContainer getOrCreateKotlinPackage(Class cls) {
        return factory.getOrCreateKotlinPackage(cls, "");
    }

    @SinceKotlin(version = "1.6")
    public static KType mutableCollectionType(KType kType) {
        return factory.mutableCollectionType(kType);
    }

    public static KMutableProperty0 mutableProperty0(MutablePropertyReference0 mutablePropertyReference0) {
        return factory.mutableProperty0(mutablePropertyReference0);
    }

    public static KMutableProperty1 mutableProperty1(MutablePropertyReference1 mutablePropertyReference1) {
        return factory.mutableProperty1(mutablePropertyReference1);
    }

    public static KMutableProperty2 mutableProperty2(MutablePropertyReference2 mutablePropertyReference2) {
        return factory.mutableProperty2(mutablePropertyReference2);
    }

    @SinceKotlin(version = "1.6")
    public static KType nothingType(KType kType) {
        return factory.nothingType(kType);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(KClassifier kClassifier) {
        return factory.typeOf(kClassifier, Collections.EMPTY_LIST, true);
    }

    @SinceKotlin(version = "1.6")
    public static KType platformType(KType kType, KType kType2) {
        return factory.platformType(kType, kType2);
    }

    public static KProperty0 property0(PropertyReference0 propertyReference0) {
        return factory.property0(propertyReference0);
    }

    public static KProperty1 property1(PropertyReference1 propertyReference1) {
        return factory.property1(propertyReference1);
    }

    public static KProperty2 property2(PropertyReference2 propertyReference2) {
        return factory.property2(propertyReference2);
    }

    @SinceKotlin(version = "1.1")
    public static String renderLambdaToString(Lambda lambda) {
        return factory.renderLambdaToString(lambda);
    }

    @SinceKotlin(version = "1.4")
    public static void setUpperBounds(KTypeParameter kTypeParameter, KType kType) {
        factory.setUpperBounds(kTypeParameter, Collections.singletonList(kType));
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(KClassifier kClassifier) {
        return factory.typeOf(kClassifier, Collections.EMPTY_LIST, false);
    }

    @SinceKotlin(version = "1.4")
    public static KTypeParameter typeParameter(Object obj, String str, KVariance kVariance, boolean z) {
        return factory.typeParameter(obj, str, kVariance, z);
    }

    public static KClass createKotlinClass(Class cls, String str) {
        return factory.createKotlinClass(cls, str);
    }

    public static KClass getOrCreateKotlinClass(Class cls, String str) {
        return factory.getOrCreateKotlinClass(cls, str);
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class cls, String str) {
        return factory.getOrCreateKotlinPackage(cls, str);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class cls) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Collections.EMPTY_LIST, true);
    }

    @SinceKotlin(version = "1.3")
    public static String renderLambdaToString(FunctionBase functionBase) {
        return factory.renderLambdaToString(functionBase);
    }

    @SinceKotlin(version = "1.4")
    public static void setUpperBounds(KTypeParameter kTypeParameter, KType... kTypeArr) {
        factory.setUpperBounds(kTypeParameter, ArraysKt___ArraysKt.toList(kTypeArr));
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class cls) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Collections.EMPTY_LIST, false);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class cls, KTypeProjection kTypeProjection) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Collections.singletonList(kTypeProjection), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class cls, KTypeProjection kTypeProjection) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Collections.singletonList(kTypeProjection), false);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class cls, KTypeProjection kTypeProjection, KTypeProjection kTypeProjection2) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Arrays.asList(kTypeProjection, kTypeProjection2), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class cls, KTypeProjection kTypeProjection, KTypeProjection kTypeProjection2) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), Arrays.asList(kTypeProjection, kTypeProjection2), false);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class cls, KTypeProjection... kTypeProjectionArr) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), ArraysKt___ArraysKt.toList(kTypeProjectionArr), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class cls, KTypeProjection... kTypeProjectionArr) {
        ReflectionFactory reflectionFactory = factory;
        return reflectionFactory.typeOf(reflectionFactory.getOrCreateKotlinClass(cls), ArraysKt___ArraysKt.toList(kTypeProjectionArr), false);
    }
}
