package kotlin.jvm.internal;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.internal.CollectionDescriptorsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,205:1\n1573#2:206\n1604#2,4:207\n1267#2,4:211\n1252#2,4:217\n465#3:215\n415#3:216\n*S KotlinDebug\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n*L\n107#1:206\n107#1:207,4\n155#1:211,4\n163#1:217,4\n163#1:215\n163#1:216\n*E\n"})
public final class ClassReference implements KClass<Object>, ClassBasedDeclarationContainer {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;

    @NotNull
    public static final HashMap<String, String> classFqNames;

    @NotNull
    public static final HashMap<String, String> primitiveFqNames;

    @NotNull
    public static final HashMap<String, String> primitiveWrapperFqNames;

    @NotNull
    public static final Map<String, String> simpleNames;

    @NotNull
    public final Class<?> jClass;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,205:1\n1#2:206\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @Nullable
        public final String getClassQualifiedName(@NotNull Class<?> jClass) {
            String str;
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            String strConcat = null;
            if (jClass.isAnonymousClass() || jClass.isLocalClass()) {
                return null;
            }
            if (!jClass.isArray()) {
                String str2 = (String) ClassReference.classFqNames.get(jClass.getName());
                return str2 == null ? jClass.getCanonicalName() : str2;
            }
            Class<?> componentType = jClass.getComponentType();
            if (componentType.isPrimitive() && (str = (String) ClassReference.classFqNames.get(componentType.getName())) != null) {
                strConcat = str.concat("Array");
            }
            return strConcat == null ? CollectionDescriptorsKt.ARRAY_NAME : strConcat;
        }

        @Nullable
        public final String getClassSimpleName(@NotNull Class<?> jClass) {
            String str;
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            String strConcat = null;
            if (jClass.isAnonymousClass()) {
                return null;
            }
            if (!jClass.isLocalClass()) {
                if (!jClass.isArray()) {
                    String str2 = (String) ClassReference.simpleNames.get(jClass.getName());
                    return str2 == null ? jClass.getSimpleName() : str2;
                }
                Class<?> componentType = jClass.getComponentType();
                if (componentType.isPrimitive() && (str = (String) ClassReference.simpleNames.get(componentType.getName())) != null) {
                    strConcat = str.concat("Array");
                }
                return strConcat == null ? "Array" : strConcat;
            }
            String simpleName = jClass.getSimpleName();
            Method enclosingMethod = jClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                return StringsKt__StringsKt.substringAfter$default(simpleName, enclosingMethod.getName() + '$', (String) null, 2, (Object) null);
            }
            Constructor<?> enclosingConstructor = jClass.getEnclosingConstructor();
            if (enclosingConstructor == null) {
                return StringsKt__StringsKt.substringAfter$default(simpleName, '$', (String) null, 2, (Object) null);
            }
            return StringsKt__StringsKt.substringAfter$default(simpleName, enclosingConstructor.getName() + '$', (String) null, 2, (Object) null);
        }

        public final boolean isInstance(@Nullable Object obj, @NotNull Class<?> jClass) {
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            Map map = ClassReference.FUNCTION_CLASSES;
            Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
            Integer num = (Integer) map.get(jClass);
            if (num != null) {
                return TypeIntrinsics.isFunctionOfArity(obj, num.intValue());
            }
            if (jClass.isPrimitive()) {
                jClass = JvmClassMappingKt.getJavaObjectType(Reflection.getOrCreateKotlinClass(jClass));
            }
            return jClass.isInstance(obj);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        int i = 0;
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listListOf, 10));
        for (Object obj : listListOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            arrayList.add(new Pair((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        FUNCTION_CLASSES = MapsKt__MapsKt.toMap(arrayList);
        HashMap<String, String> map = new HashMap<>();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        primitiveFqNames = map;
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = map2;
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            Intrinsics.checkNotNull(str);
            map3.put(ActivityCompat$$ExternalSyntheticOutline0.m(sb, StringsKt__StringsKt.substringAfterLast$default(str, '.', (String) null, 2, (Object) null), "CompanionObject"), str.concat(".Companion"));
        }
        for (Map.Entry<Class<? extends Function<?>>, Integer> entry : FUNCTION_CLASSES.entrySet()) {
            map3.put(entry.getKey().getName(), "kotlin.Function" + entry.getValue().intValue());
        }
        classFqNames = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map3.size()));
        Iterator<T> it = map3.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            Object key = entry2.getKey();
            String str2 = (String) entry2.getValue();
            Intrinsics.checkNotNull(str2);
            linkedHashMap.put(key, StringsKt__StringsKt.substringAfterLast$default(str2, '.', (String) null, 2, (Object) null));
        }
        simpleNames = linkedHashMap;
    }

    public ClassReference(@NotNull Class<?> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ClassReference) && JvmClassMappingKt.getJavaObjectType(this).equals(JvmClassMappingKt.getJavaObjectType((KClass) obj));
    }

    public final Void error() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    @NotNull
    public List<Annotation> getAnnotations() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KFunction<Object>> getConstructors() {
        error();
        throw null;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    @NotNull
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    @NotNull
    public Collection<KCallable<?>> getMembers() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public Collection<KClass<?>> getNestedClasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public Object getObjectInstance() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getQualifiedName() {
        return Companion.getClassQualifiedName(this.jClass);
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KClass<? extends Object>> getSealedSubclasses() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public String getSimpleName() {
        return Companion.getClassSimpleName(this.jClass);
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KType> getSupertypes() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @NotNull
    public List<KTypeParameter> getTypeParameters() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @Nullable
    public KVisibility getVisibility() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    @SinceKotlin(version = "1.1")
    public boolean isInstance(@Nullable Object obj) {
        return Companion.isInstance(obj, this.jClass);
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        error();
        throw null;
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        error();
        throw null;
    }

    @NotNull
    public String toString() {
        return this.jClass + Reflection.REFLECTION_NOT_AVAILABLE;
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSealedSubclasses$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getSupertypes$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getTypeParameters$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getVisibility$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isAbstract$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isCompanion$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isData$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isFinal$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void isFun$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isInner$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isOpen$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isSealed$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void isValue$annotations() {
    }
}
