package androidx.core.util;

import android.util.LruCache;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LruCacheKt {

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function2<Object, Object, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(2);

        public AnonymousClass1() {
            super(2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(Object obj, Object obj2) {
            return 1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(Object obj, Object obj2) {
            return 1;
        }
    }

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 extends Lambda implements Function1<Object, Object> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2(1);

        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3 extends Lambda implements Function4<Boolean, Object, Object, Object, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3(4);

        public AnonymousClass3() {
            super(4);
        }

        public final void invoke(boolean z, Object obj, Object obj2, Object obj3) {
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Object obj, Object obj2, Object obj3) {
            bool.booleanValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass4<K, V> extends LruCache<K, V> {
        public final /* synthetic */ Function1<K, V> $create;
        public final /* synthetic */ Function4<Boolean, K, V, V, Unit> $onEntryRemoved;
        public final /* synthetic */ Function2<K, V, Integer> $sizeOf;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(int i, Function2<? super K, ? super V, Integer> function2, Function1<? super K, ? extends V> function1, Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> function4) {
            super(i);
            this.$sizeOf = function2;
            this.$create = function1;
            this.$onEntryRemoved = function4;
        }

        @Override // android.util.LruCache
        public V create(K k) {
            return this.$create.invoke(k);
        }

        @Override // android.util.LruCache
        public void entryRemoved(boolean z, K k, V v, V v2) {
            this.$onEntryRemoved.invoke(Boolean.valueOf(z), k, v, v2);
        }

        @Override // android.util.LruCache
        public int sizeOf(K k, V v) {
            return this.$sizeOf.invoke(k, v).intValue();
        }
    }

    @NotNull
    public static final <K, V> LruCache<K, V> lruCache(int i, @NotNull Function2<? super K, ? super V, Integer> function2, @NotNull Function1<? super K, ? extends V> function1, @NotNull Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> function4) {
        return new AnonymousClass4(i, function2, function1, function4);
    }

    public static /* synthetic */ LruCache lruCache$default(int i, Function2 function2, Function1 function1, Function4 function4, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            function1 = AnonymousClass2.INSTANCE;
        }
        if ((i2 & 8) != 0) {
            function4 = AnonymousClass3.INSTANCE;
        }
        return new AnonymousClass4(i, function2, function1, function4);
    }
}
