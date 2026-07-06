package kotlinx.serialization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.Triple;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Serializers.kt\nkotlinx/serialization/SerializersKt__SerializersKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,421:1\n78#2:422\n78#2:423\n78#2:430\n78#2:431\n1557#3:424\n1628#3,3:425\n1557#3:432\n1628#3,3:433\n1557#3:436\n1628#3,3:437\n78#4:428\n78#4:429\n37#5:440\n36#5,3:441\n*S KotlinDebug\n*F\n+ 1 Serializers.kt\nkotlinx/serialization/SerializersKt__SerializersKt\n*L\n35#1:422\n54#1:423\n232#1:430\n256#1:431\n190#1:424\n190#1:425,3\n267#1:432\n267#1:433,3\n269#1:436\n269#1:437,3\n223#1:428\n230#1:429\n334#1:440\n334#1:441,3\n*E\n"})
public final /* synthetic */ class SerializersKt__SerializersKt {
    public static /* synthetic */ KClassifier $r8$lambda$kySqFMIAiKMq9XadqaNh2wiOkNo() {
        serializerByKClassImpl$lambda$1$SerializersKt__SerializersKt();
        throw null;
    }

    public static final KSerializer<? extends Object> builtinParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KSerializer<Object>> list, Function0<? extends KClassifier> function0) {
        if (!Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Collection.class))) {
            ReflectionFactory reflectionFactory = Reflection.factory;
            if (!Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(List.class)) && !Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(List.class)) && !Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(ArrayList.class))) {
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(HashSet.class))) {
                    return new HashSetSerializer(list.get(0));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(LinkedHashSet.class))) {
                    return new LinkedHashSetSerializer(list.get(0));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(HashMap.class))) {
                    return new HashMapSerializer(list.get(0), list.get(1));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(LinkedHashMap.class))) {
                    return new LinkedHashMapSerializer(list.get(0), list.get(1));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Map.Entry.class))) {
                    return BuiltinSerializersKt.MapEntrySerializer(list.get(0), list.get(1));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Pair.class))) {
                    return BuiltinSerializersKt.PairSerializer(list.get(0), list.get(1));
                }
                if (Intrinsics.areEqual(kClass, reflectionFactory.getOrCreateKotlinClass(Triple.class))) {
                    return BuiltinSerializersKt.TripleSerializer(list.get(0), list.get(1), list.get(2));
                }
                if (!PlatformKt.isReferenceArray(kClass)) {
                    return null;
                }
                KClassifier kClassifierInvoke = function0.invoke();
                Intrinsics.checkNotNull(kClassifierInvoke, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                return BuiltinSerializersKt.ArraySerializer((KClass) kClassifierInvoke, list.get(0));
            }
        }
        return new ArrayListSerializer(list.get(0));
    }

    public static final KSerializer<? extends Object> compiledParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KSerializer<Object>> list) {
        KSerializer[] kSerializerArr = (KSerializer[]) list.toArray(new KSerializer[0]);
        return PlatformKt.constructSerializerForGivenTypeArgs(kClass, (KSerializer<Object>[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
    }

    @PublishedApi
    @NotNull
    public static final KSerializer<?> moduleThenPolymorphic(@NotNull SerializersModule module, @NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        KSerializer<?> contextual$default = SerializersModule.getContextual$default(module, kClass, null, 2, null);
        return contextual$default == null ? new PolymorphicSerializer(kClass) : contextual$default;
    }

    @PublishedApi
    @NotNull
    public static final KSerializer<?> noCompiledSerializer(@NotNull String forClass) {
        Intrinsics.checkNotNullParameter(forClass, "forClass");
        throw new SerializationException(Platform_commonKt.notRegisteredMessage(forClass));
    }

    public static final <T> KSerializer<T> nullable$SerializersKt__SerializersKt(KSerializer<T> kSerializer, boolean z) {
        if (z) {
            return BuiltinSerializersKt.getNullable(kSerializer);
        }
        Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.SerializersKt__SerializersKt.nullable?>");
        return kSerializer;
    }

    @Nullable
    public static final KSerializer<? extends Object> parametrizedSerializerOrNull(@NotNull KClass<Object> kClass, @NotNull List<? extends KSerializer<Object>> serializers, @NotNull Function0<? extends KClassifier> elementClassifierIfArray) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        Intrinsics.checkNotNullParameter(elementClassifierIfArray, "elementClassifierIfArray");
        KSerializer<? extends Object> kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt = builtinParametrizedSerializer$SerializersKt__SerializersKt(kClass, serializers, elementClassifierIfArray);
        return kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt == null ? compiledParametrizedSerializer$SerializersKt__SerializersKt(kClass, serializers) : kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt;
    }

    @InternalSerializationApi
    @NotNull
    public static final <T> KSerializer<T> serializer(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KSerializer<T> kSerializerSerializerOrNull = serializerOrNull(kClass);
        if (kSerializerSerializerOrNull != null) {
            return kSerializerSerializerOrNull;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw null;
    }

    public static final KSerializer<Object> serializerByKClassImpl$SerializersKt__SerializersKt(SerializersModule serializersModule, KClass<Object> kClass, List<? extends KSerializer<Object>> list, boolean z) {
        KSerializer<? extends Object> contextual;
        if (list.isEmpty()) {
            contextual = serializerOrNull(kClass);
            if (contextual == null) {
                contextual = SerializersModule.getContextual$default(serializersModule, kClass, null, 2, null);
            }
        } else {
            try {
                KSerializer<? extends Object> kSerializerParametrizedSerializerOrNull = parametrizedSerializerOrNull(kClass, list, new SerializersKt__SerializersKt$$ExternalSyntheticLambda0());
                contextual = kSerializerParametrizedSerializerOrNull == null ? serializersModule.getContextual(kClass, list) : kSerializerParametrizedSerializerOrNull;
            } catch (IndexOutOfBoundsException e) {
                throw new SerializationException("Unable to retrieve a serializer, the number of passed type serializers differs from the actual number of generic parameters", e);
            }
        }
        if (contextual != null) {
            return nullable$SerializersKt__SerializersKt(contextual, z);
        }
        return null;
    }

    public static final KClassifier serializerByKClassImpl$lambda$1$SerializersKt__SerializersKt() {
        throw new SerializationException("It is not possible to retrieve an array serializer using KClass alone, use KType instead or ArraySerializer factory");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlinx.serialization.KSerializer<java.lang.Object> serializerByKTypeImpl$SerializersKt__SerializersKt(kotlinx.serialization.modules.SerializersModule r6, kotlin.reflect.KType r7, boolean r8) {
        /*
            kotlin.reflect.KClass r0 = kotlinx.serialization.internal.Platform_commonKt.kclass(r7)
            boolean r1 = r7.isMarkedNullable()
            java.util.List r7 = r7.getArguments()
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r7, r3)
            r2.<init>(r3)
            java.util.Iterator r7 = r7.iterator()
        L1b:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L2f
            java.lang.Object r3 = r7.next()
            kotlin.reflect.KTypeProjection r3 = (kotlin.reflect.KTypeProjection) r3
            kotlin.reflect.KType r3 = kotlinx.serialization.internal.Platform_commonKt.typeOrThrow(r3)
            r2.add(r3)
            goto L1b
        L2f:
            boolean r7 = r2.isEmpty()
            r3 = 2
            r4 = 0
            if (r7 == 0) goto L4a
            boolean r7 = kotlinx.serialization.internal.PlatformKt.isInterface(r0)
            if (r7 == 0) goto L45
            kotlinx.serialization.KSerializer r7 = kotlinx.serialization.modules.SerializersModule.getContextual$default(r6, r0, r4, r3, r4)
            if (r7 == 0) goto L45
        L43:
            r7 = r4
            goto L5c
        L45:
            kotlinx.serialization.KSerializer r7 = kotlinx.serialization.SerializersCacheKt.findCachedSerializer(r0, r1)
            goto L5c
        L4a:
            boolean r7 = r6.getHasInterfaceContextualSerializers$kotlinx_serialization_core()
            if (r7 == 0) goto L51
            goto L43
        L51:
            java.lang.Object r7 = kotlinx.serialization.SerializersCacheKt.findParametrizedCachedSerializer(r0, r2, r1)
            boolean r5 = r7 instanceof kotlin.Result.Failure
            if (r5 == 0) goto L5a
            r7 = r4
        L5a:
            kotlinx.serialization.KSerializer r7 = (kotlinx.serialization.KSerializer) r7
        L5c:
            if (r7 == 0) goto L5f
            return r7
        L5f:
            boolean r7 = r2.isEmpty()
            if (r7 == 0) goto L80
            kotlinx.serialization.KSerializer r7 = serializerOrNull(r0)
            if (r7 != 0) goto La5
            kotlinx.serialization.KSerializer r7 = kotlinx.serialization.modules.SerializersModule.getContextual$default(r6, r0, r4, r3, r4)
            if (r7 != 0) goto La5
            boolean r6 = kotlinx.serialization.internal.PlatformKt.isInterface(r0)
            if (r6 == 0) goto L7e
            kotlinx.serialization.PolymorphicSerializer r6 = new kotlinx.serialization.PolymorphicSerializer
            r6.<init>(r0)
        L7c:
            r7 = r6
            goto La5
        L7e:
            r7 = r4
            goto La5
        L80:
            java.util.List r7 = serializersForParameters(r6, r2, r8)
            if (r7 != 0) goto L87
            goto Lac
        L87:
            kotlinx.serialization.SerializersKt__SerializersKt$$ExternalSyntheticLambda1 r8 = new kotlinx.serialization.SerializersKt__SerializersKt$$ExternalSyntheticLambda1
            r8.<init>()
            kotlinx.serialization.KSerializer r8 = parametrizedSerializerOrNull(r0, r7, r8)
            if (r8 != 0) goto La4
            kotlinx.serialization.KSerializer r7 = r6.getContextual(r0, r7)
            if (r7 != 0) goto La5
            boolean r6 = kotlinx.serialization.internal.PlatformKt.isInterface(r0)
            if (r6 == 0) goto L7e
            kotlinx.serialization.PolymorphicSerializer r6 = new kotlinx.serialization.PolymorphicSerializer
            r6.<init>(r0)
            goto L7c
        La4:
            r7 = r8
        La5:
            if (r7 == 0) goto Lac
            kotlinx.serialization.KSerializer r6 = nullable$SerializersKt__SerializersKt(r7, r1)
            return r6
        Lac:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.SerializersKt__SerializersKt.serializerByKTypeImpl$SerializersKt__SerializersKt(kotlinx.serialization.modules.SerializersModule, kotlin.reflect.KType, boolean):kotlinx.serialization.KSerializer");
    }

    public static final KClassifier serializerByKTypeImpl$lambda$0$SerializersKt__SerializersKt(List list) {
        return ((KType) list.get(0)).getClassifier();
    }

    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerOrNull(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @Nullable
    public static final List<KSerializer<Object>> serializersForParameters(@NotNull SerializersModule serializersModule, @NotNull List<? extends KType> typeArguments, boolean z) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        if (z) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeArguments, 10));
            Iterator<T> it = typeArguments.iterator();
            while (it.hasNext()) {
                arrayList.add(SerializersKt.serializer(serializersModule, (KType) it.next()));
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeArguments, 10));
        Iterator<T> it2 = typeArguments.iterator();
        while (it2.hasNext()) {
            KSerializer<Object> kSerializerSerializerOrNull = serializerOrNull(serializersModule, (KType) it2.next());
            if (kSerializerSerializerOrNull == null) {
                return null;
            }
            arrayList2.add(kSerializerSerializerOrNull);
        }
        return arrayList2;
    }

    @PublishedApi
    @NotNull
    public static final KSerializer<?> moduleThenPolymorphic(@NotNull SerializersModule module, @NotNull KClass<?> kClass, @NotNull KSerializer<?>[] argSerializers) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
        KSerializer<?> contextual = module.getContextual(kClass, ArraysKt___ArraysJvmKt.asList(argSerializers));
        return contextual == null ? new PolymorphicSerializer(kClass) : contextual;
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final KSerializer<Object> serializer(@NotNull KClass<?> kClass, @NotNull List<? extends KSerializer<?>> typeArgumentsSerializers, boolean z) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        return serializer(SerializersModuleKt.getEmptySerializersModule(), kClass, typeArgumentsSerializers, z);
    }

    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull SerializersModule serializersModule, @NotNull KType type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, type, false);
    }

    @PublishedApi
    @NotNull
    public static final KSerializer<?> noCompiledSerializer(@NotNull SerializersModule module, @NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        KSerializer<?> contextual$default = SerializersModule.getContextual$default(module, kClass, null, 2, null);
        if (contextual$default != null) {
            return contextual$default;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw null;
    }

    @InternalSerializationApi
    @Nullable
    public static final <T> KSerializer<T> serializerOrNull(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KSerializer<T> kSerializerCompiledSerializerImpl = PlatformKt.compiledSerializerImpl(kClass);
        return kSerializerCompiledSerializerImpl == null ? PrimitivesKt.builtinSerializerOrNull(kClass) : kSerializerCompiledSerializerImpl;
    }

    @PublishedApi
    @NotNull
    public static final KSerializer<?> noCompiledSerializer(@NotNull SerializersModule module, @NotNull KClass<?> kClass, @NotNull KSerializer<?>[] argSerializers) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(argSerializers, "argSerializers");
        KSerializer<?> contextual = module.getContextual(kClass, ArraysKt___ArraysJvmKt.asList(argSerializers));
        if (contextual != null) {
            return contextual;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw null;
    }

    @NotNull
    public static final KSerializer<Object> serializer(@NotNull KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return serializer(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @NotNull
    public static final KSerializer<Object> serializer(@NotNull SerializersModule serializersModule, @NotNull KType type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        KSerializer<Object> kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt = serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, type, true);
        if (kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt != null) {
            return kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt;
        }
        PlatformKt.platformSpecificSerializerNotRegistered(Platform_commonKt.kclass(type));
        throw null;
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final KSerializer<Object> serializer(@NotNull SerializersModule serializersModule, @NotNull KClass<?> kClass, @NotNull List<? extends KSerializer<?>> typeArgumentsSerializers, boolean z) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        KSerializer<Object> kSerializerSerializerByKClassImpl$SerializersKt__SerializersKt = serializerByKClassImpl$SerializersKt__SerializersKt(serializersModule, kClass, typeArgumentsSerializers, z);
        if (kSerializerSerializerByKClassImpl$SerializersKt__SerializersKt != null) {
            return kSerializerSerializerByKClassImpl$SerializersKt__SerializersKt;
        }
        PlatformKt.platformSpecificSerializerNotRegistered(kClass);
        throw null;
    }

    public static final <T> KSerializer<T> serializer() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> KSerializer<T> serializer(SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
