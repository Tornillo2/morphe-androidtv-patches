package kotlinx.serialization;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.CachingKt;
import kotlinx.serialization.internal.ParametrizedSerializerCache;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.SerializerCache;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializersCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,79:1\n78#1:81\n78#1:82\n78#2:80\n78#2:83\n78#2:84\n*S KotlinDebug\n*F\n+ 1 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n*L\n22#1:81\n28#1:82\n54#1:80\n28#1:83\n45#1:84\n*E\n"})
public final class SerializersCacheKt {

    @NotNull
    public static final SerializerCache<? extends Object> SERIALIZERS_CACHE = CachingKt.createCache(new SerializersCacheKt$$ExternalSyntheticLambda0());

    @NotNull
    public static final SerializerCache<Object> SERIALIZERS_CACHE_NULLABLE = CachingKt.createCache(new SerializersCacheKt$$ExternalSyntheticLambda1());

    @NotNull
    public static final ParametrizedSerializerCache<? extends Object> PARAMETRIZED_SERIALIZERS_CACHE = CachingKt.createParametrizedCache(new SerializersCacheKt$$ExternalSyntheticLambda2());

    @NotNull
    public static final ParametrizedSerializerCache<Object> PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE = CachingKt.createParametrizedCache(new SerializersCacheKt$$ExternalSyntheticLambda3());

    public static final KSerializer PARAMETRIZED_SERIALIZERS_CACHE$lambda$3(KClass clazz, final List types) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(types, "types");
        List<KSerializer<Object>> listSerializersForParameters = SerializersKt__SerializersKt.serializersForParameters(SerializersModuleKt.getEmptySerializersModule(), types, true);
        Intrinsics.checkNotNull(listSerializersForParameters);
        return SerializersKt__SerializersKt.parametrizedSerializerOrNull(clazz, listSerializersForParameters, new Function0() { // from class: kotlinx.serialization.SerializersCacheKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SerializersCacheKt.PARAMETRIZED_SERIALIZERS_CACHE$lambda$3$lambda$2(types);
            }
        });
    }

    public static final KClassifier PARAMETRIZED_SERIALIZERS_CACHE$lambda$3$lambda$2(List list) {
        return ((KType) list.get(0)).getClassifier();
    }

    public static final KSerializer PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5(KClass clazz, final List types) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(types, "types");
        List<KSerializer<Object>> listSerializersForParameters = SerializersKt__SerializersKt.serializersForParameters(SerializersModuleKt.getEmptySerializersModule(), types, true);
        Intrinsics.checkNotNull(listSerializersForParameters);
        KSerializer<? extends Object> kSerializerParametrizedSerializerOrNull = SerializersKt__SerializersKt.parametrizedSerializerOrNull(clazz, listSerializersForParameters, new Function0() { // from class: kotlinx.serialization.SerializersCacheKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SerializersCacheKt.PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5$lambda$4(types);
            }
        });
        if (kSerializerParametrizedSerializerOrNull != null) {
            return BuiltinSerializersKt.getNullable(kSerializerParametrizedSerializerOrNull);
        }
        return null;
    }

    public static final KClassifier PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5$lambda$4(List list) {
        return ((KType) list.get(0)).getClassifier();
    }

    public static final KSerializer SERIALIZERS_CACHE$lambda$0(KClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        KSerializer kSerializerSerializerOrNull = SerializersKt__SerializersKt.serializerOrNull(it);
        if (kSerializerSerializerOrNull != null) {
            return kSerializerSerializerOrNull;
        }
        if (PlatformKt.isInterface(it)) {
            return new PolymorphicSerializer(it);
        }
        return null;
    }

    public static final KSerializer SERIALIZERS_CACHE_NULLABLE$lambda$1(KClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        KSerializer kSerializerSerializerOrNull = SerializersKt__SerializersKt.serializerOrNull(it);
        if (kSerializerSerializerOrNull == null) {
            kSerializerSerializerOrNull = PlatformKt.isInterface(it) ? new PolymorphicSerializer(it) : null;
        }
        if (kSerializerSerializerOrNull != null) {
            return BuiltinSerializersKt.getNullable(kSerializerSerializerOrNull);
        }
        return null;
    }

    @Nullable
    public static final KSerializer<Object> findCachedSerializer(@NotNull KClass<Object> clazz, boolean z) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (z) {
            return SERIALIZERS_CACHE_NULLABLE.get(clazz);
        }
        KSerializer<? extends Object> kSerializer = SERIALIZERS_CACHE.get(clazz);
        if (kSerializer != null) {
            return kSerializer;
        }
        return null;
    }

    @NotNull
    public static final Object findParametrizedCachedSerializer(@NotNull KClass<Object> clazz, @NotNull List<? extends KType> types, boolean z) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(types, "types");
        return !z ? PARAMETRIZED_SERIALIZERS_CACHE.mo2148getgIAlus(clazz, types) : PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE.mo2148getgIAlus(clazz, types);
    }

    @NotNull
    public static final SerializerCache<? extends Object> getSERIALIZERS_CACHE() {
        return SERIALIZERS_CACHE;
    }

    @Nullable
    public static final PolymorphicSerializer<? extends Object> polymorphicIfInterface(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        if (PlatformKt.isInterface(kClass)) {
            return new PolymorphicSerializer<>(kClass);
        }
        return null;
    }

    public static /* synthetic */ void getPARAMETRIZED_SERIALIZERS_CACHE$annotations() {
    }

    public static /* synthetic */ void getPARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$annotations() {
    }

    public static /* synthetic */ void getSERIALIZERS_CACHE$annotations() {
    }

    public static /* synthetic */ void getSERIALIZERS_CACHE_NULLABLE$annotations() {
    }
}
