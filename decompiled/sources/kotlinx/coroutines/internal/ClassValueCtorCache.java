package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@IgnoreJRERequirement
public final class ClassValueCtorCache extends CtorCache {

    @NotNull
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();

    @NotNull
    public static final ClassValueCtorCache$cache$1 cache = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        @Override // java.lang.ClassValue
        public /* bridge */ /* synthetic */ Function1<? super Throwable, ? extends Throwable> computeValue(Class cls) {
            return computeValue2((Class<?>) cls);
        }

        @Override // java.lang.ClassValue
        @NotNull
        /* JADX INFO: renamed from: computeValue, reason: avoid collision after fix types in other method */
        public Function1<? super Throwable, ? extends Throwable> computeValue2(@Nullable Class<?> cls) {
            if (cls != null) {
                return ExceptionsConstructorKt.createConstructor(cls);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<out kotlin.Throwable>");
        }
    };

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        return (Function1) cache.get(cls);
    }
}
