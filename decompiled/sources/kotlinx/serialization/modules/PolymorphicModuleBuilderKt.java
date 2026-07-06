package kotlinx.serialization.modules;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class PolymorphicModuleBuilderKt {
    public static final <Base, T extends Base> void subclass(PolymorphicModuleBuilder<? super Base> polymorphicModuleBuilder, KClass<T> clazz) {
        Intrinsics.checkNotNullParameter(polymorphicModuleBuilder, "<this>");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <Base, T extends Base> void subclass(PolymorphicModuleBuilder<? super Base> polymorphicModuleBuilder, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(polymorphicModuleBuilder, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
