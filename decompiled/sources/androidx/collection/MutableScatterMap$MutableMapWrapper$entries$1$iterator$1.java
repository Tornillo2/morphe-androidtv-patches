package androidx.collection;

import java.util.Iterator;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
    public int current = -1;

    @NotNull
    public Iterator<? extends Map.Entry<K, V>> iterator;
    public final /* synthetic */ MutableScatterMap<K, V> this$0;

    /* JADX INFO: renamed from: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1328}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$3", "I$0", "I$1", "J$0", "I$2", "I$3"})
    @SourceDebugExtension({"SMAP\nScatterMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMap\n+ 3 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1#1,1850:1\n363#2,6:1851\n373#2,3:1858\n376#2,9:1862\n1826#3:1857\n1688#3:1861\n*S KotlinDebug\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1\n*L\n1326#1:1851,6\n1326#1:1858,3\n1326#1:1862,9\n1326#1:1857\n1326#1:1861\n*E\n"})
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<K, V>>, Continuation<? super Unit>, Object> {
        public int I$0;
        public int I$1;
        public int I$2;
        public int I$3;
        public long J$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public final /* synthetic */ MutableScatterMap<K, V> this$0;
        public final /* synthetic */ MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableScatterMap<K, V> mutableScatterMap, MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = mutableScatterMap;
            this.this$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00be  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0057 -> B:14:0x006b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0074 -> B:20:0x00a2). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x009f -> B:21:0x00a5). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00b9 -> B:26:0x00bc). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r23) throws java.lang.Throwable {
            /*
                r22 = this;
                r0 = r22
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r4 = 8
                r5 = 1
                if (r2 == 0) goto L34
                if (r2 != r5) goto L2c
                int r2 = r0.I$3
                int r6 = r0.I$2
                long r7 = r0.J$0
                int r9 = r0.I$1
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$3
                long[] r11 = (long[]) r11
                java.lang.Object r12 = r0.L$2
                androidx.collection.MutableScatterMap r12 = (androidx.collection.MutableScatterMap) r12
                java.lang.Object r13 = r0.L$1
                androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 r13 = (androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1) r13
                java.lang.Object r14 = r0.L$0
                kotlin.sequences.SequenceScope r14 = (kotlin.sequences.SequenceScope) r14
                kotlin.ResultKt.throwOnFailure(r23)
                goto La2
            L2c:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L34:
                kotlin.ResultKt.throwOnFailure(r23)
                java.lang.Object r2 = r0.L$0
                kotlin.sequences.SequenceScope r2 = (kotlin.sequences.SequenceScope) r2
                androidx.collection.MutableScatterMap<K, V> r6 = r0.this$0
                androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 r7 = r0.this$1
                long[] r8 = r6.metadata
                int r9 = r8.length
                int r9 = r9 + (-2)
                if (r9 < 0) goto Lc4
                r10 = 0
            L47:
                r11 = r8[r10]
                long r13 = ~r11
                r15 = 7
                long r13 = r13 << r15
                long r13 = r13 & r11
                r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r13 = r13 & r15
                int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r17 == 0) goto Lb9
                int r13 = r10 - r9
                int r13 = ~r13
                int r13 = r13 >>> 31
                int r13 = 8 - r13
                r14 = r10
                r10 = r9
                r9 = r14
                r14 = r2
                r2 = 0
                r20 = r11
                r12 = r6
                r11 = r8
                r6 = r13
                r13 = r7
                r7 = r20
            L6b:
                if (r2 >= r6) goto Lac
                r15 = 255(0xff, double:1.26E-321)
                long r15 = r15 & r7
                r17 = 128(0x80, double:6.3E-322)
                int r19 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
                if (r19 >= 0) goto La2
                int r15 = r9 << 3
                int r15 = r15 + r2
                r13.current = r15
                androidx.collection.MutableMapEntry r3 = new androidx.collection.MutableMapEntry
                r17 = 8
                java.lang.Object[] r4 = r12.keys
                java.lang.Object[] r5 = r12.values
                r3.<init>(r4, r5, r15)
                r0.L$0 = r14
                r0.L$1 = r13
                r0.L$2 = r12
                r0.L$3 = r11
                r0.I$0 = r10
                r0.I$1 = r9
                r0.J$0 = r7
                r0.I$2 = r6
                r0.I$3 = r2
                r4 = 1
                r0.label = r4
                java.lang.Object r3 = r14.yield(r3, r0)
                if (r3 != r1) goto La5
                return r1
            La2:
                r4 = 1
                r17 = 8
            La5:
                long r7 = r7 >> r17
                int r2 = r2 + r4
                r4 = 8
                r5 = 1
                goto L6b
            Lac:
                r3 = 8
                r4 = 1
                if (r6 != r3) goto Lc4
                r2 = r10
                r10 = r9
                r9 = r2
                r8 = r11
                r6 = r12
                r7 = r13
                r2 = r14
                goto Lbc
            Lb9:
                r3 = 8
                r4 = 1
            Lbc:
                if (r10 == r9) goto Lc4
                int r10 = r10 + 1
                r4 = 8
                r5 = 1
                goto L47
            Lc4:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull SequenceScope<? super Map.Entry<K, V>> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(MutableScatterMap<K, V> mutableScatterMap) {
        this.this$0 = mutableScatterMap;
        this.iterator = SequencesKt__SequenceBuilderKt.iterator(new AnonymousClass1(mutableScatterMap, this, null));
    }

    public final int getCurrent() {
        return this.current;
    }

    @NotNull
    public final Iterator<Map.Entry<K, V>> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i = this.current;
        if (i != -1) {
            this.this$0.removeValueAt(i);
            this.current = -1;
        }
    }

    public final void setCurrent(int i) {
        this.current = i;
    }

    public final void setIterator(@NotNull Iterator<? extends Map.Entry<K, V>> it) {
        Intrinsics.checkNotNullParameter(it, "<set-?>");
        this.iterator = it;
    }

    @Override // java.util.Iterator
    @NotNull
    public Map.Entry<K, V> next() {
        return this.iterator.next();
    }
}
