package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: renamed from: kotlinx.serialization.internal.ClassValueParametrizedCache$get-gIAlu-s$$inlined$getOrSet$1, reason: invalid class name */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences$getOrSet$2\n+ 2 Caching.kt\nkotlinx/serialization/internal/ClassValueParametrizedCache\n*L\n1#1,89:1\n128#2:90\n*E\n"})
public final class ClassValueParametrizedCache$getgIAlus$$inlined$getOrSet$1<T> implements Function0<T> {
    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        return (T) new ParametrizedCacheEntry();
    }
}
