package androidx.collection;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LruCacheKt {

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCacheKt$lruCache$1\n*L\n1#1,355:1\n*E\n"})
    public static final class AnonymousClass1 extends Lambda implements Function2<Object, Object, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(2);

        public AnonymousClass1() {
            super(2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function2
        @NotNull
        public final Integer invoke(@NotNull Object obj, @NotNull Object obj2) {
            Intrinsics.checkNotNullParameter(obj, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(obj2, "<anonymous parameter 1>");
            return 1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(Object obj, Object obj2) {
            invoke(obj, obj2);
            return 1;
        }
    }

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCacheKt$lruCache$2\n*L\n1#1,355:1\n*E\n"})
    public static final class AnonymousClass2 extends Lambda implements Function1<Object, Object> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2(1);

        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public final Object invoke(@NotNull Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCacheKt$lruCache$3\n*L\n1#1,355:1\n*E\n"})
    public static final class AnonymousClass3 extends Lambda implements Function4<Boolean, Object, Object, Object, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3(4);

        public AnonymousClass3() {
            super(4);
        }

        public final void invoke(boolean z, @NotNull Object obj, @NotNull Object obj2, @Nullable Object obj3) {
            Intrinsics.checkNotNullParameter(obj, "<anonymous parameter 1>");
            Intrinsics.checkNotNullParameter(obj2, "<anonymous parameter 2>");
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Object obj, Object obj2, Object obj3) {
            invoke(bool.booleanValue(), obj, obj2, obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCacheKt$lruCache$4\n*L\n1#1,355:1\n*E\n"})
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

        @Override // androidx.collection.LruCache
        @Nullable
        public V create(@NotNull K key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return this.$create.invoke(key);
        }

        @Override // androidx.collection.LruCache
        public void entryRemoved(boolean z, @NotNull K key, @NotNull V oldValue, @Nullable V v) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(oldValue, "oldValue");
            this.$onEntryRemoved.invoke(Boolean.valueOf(z), key, oldValue, v);
        }

        @Override // androidx.collection.LruCache
        public int sizeOf(@NotNull K key, @NotNull V value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            return this.$sizeOf.invoke(key, value).intValue();
        }
    }

    @NotNull
    public static final <K, V> LruCache<K, V> lruCache(int i, @NotNull Function2<? super K, ? super V, Integer> sizeOf, @NotNull Function1<? super K, ? extends V> create, @NotNull Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> onEntryRemoved) {
        Intrinsics.checkNotNullParameter(sizeOf, "sizeOf");
        Intrinsics.checkNotNullParameter(create, "create");
        Intrinsics.checkNotNullParameter(onEntryRemoved, "onEntryRemoved");
        return new AnonymousClass4(i, sizeOf, create, onEntryRemoved);
    }

    public static /* synthetic */ LruCache lruCache$default(int i, Function2 sizeOf, Function1 create, Function4 onEntryRemoved, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            sizeOf = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            create = AnonymousClass2.INSTANCE;
        }
        if ((i2 & 8) != 0) {
            onEntryRemoved = AnonymousClass3.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(sizeOf, "sizeOf");
        Intrinsics.checkNotNullParameter(create, "create");
        Intrinsics.checkNotNullParameter(onEntryRemoved, "onEntryRemoved");
        return new AnonymousClass4(i, sizeOf, create, onEntryRemoved);
    }
}
