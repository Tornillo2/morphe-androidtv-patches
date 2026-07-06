package kotlin.sequences;

import android.R;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline0;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline1;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__AppendableKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_Sequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,3224:1\n183#1,2:3225\n320#1,7:3227\n1332#1,3:3235\n747#1,4:3238\n712#1,4:3242\n730#1,4:3246\n783#1,4:3250\n1025#1,3:3254\n1028#1,3:3264\n1045#1,3:3267\n1048#1,3:3277\n1332#1,3:3294\n1321#1,2:3297\n1#2:3234\n384#3,7:3257\n384#3,7:3270\n384#3,7:3280\n384#3,7:3287\n*S KotlinDebug\n*F\n+ 1 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n91#1:3225,2\n103#1:3227,7\n462#1:3235,3\n666#1:3238,4\n682#1:3242,4\n697#1:3246,4\n768#1:3250,4\n996#1:3254,3\n996#1:3264,3\n1011#1:3267,3\n1011#1:3277,3\n1114#1:3294,3\n1152#1:3297,2\n996#1:3257,7\n1011#1:3270,7\n1027#1:3280,7\n1047#1:3287,7\n*E\n"})
public class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {

    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$filterIsInstance$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Function1<Object, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(Object obj) {
            Intrinsics.throwUndefinedForReified();
            throw null;
        }

        @Override // kotlin.jvm.functions.Function1
        public Boolean invoke(Object obj) {
            Intrinsics.throwUndefinedForReified();
            throw null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$flatMap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class C00681<R> extends FunctionReferenceImpl implements Function1<Iterable<? extends R>, Iterator<? extends R>> {
        public static final C00681 INSTANCE = new C00681();

        public C00681() {
            super(1, Iterable.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Iterator<R> invoke(Iterable<? extends R> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$flatMap$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass2<R> extends FunctionReferenceImpl implements Function1<Sequence<? extends R>, Iterator<? extends R>> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1, Sequence.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Iterator<R> invoke(Sequence<? extends R> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$minus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00701<T> implements Sequence<T> {
        public final /* synthetic */ T $element;
        public final /* synthetic */ Sequence<T> $this_minus;

        /* JADX WARN: Multi-variable type inference failed */
        public C00701(Sequence<? extends T> sequence, T t) {
            this.$this_minus = sequence;
            this.$element = t;
        }

        public static final boolean iterator$lambda$0(Ref.BooleanRef booleanRef, Object obj, Object obj2) {
            if (booleanRef.element || !Intrinsics.areEqual(obj2, obj)) {
                return true;
            }
            booleanRef.element = true;
            return false;
        }

        @Override // kotlin.sequences.Sequence
        public Iterator<T> iterator() {
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            Sequence<T> sequence = this.$this_minus;
            final T t = this.$element;
            return ((FilteringSequence) SequencesKt___SequencesKt.filter(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(SequencesKt___SequencesKt.C00701.iterator$lambda$0(booleanRef, t, obj));
                }
            })).iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$runningFold$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFold$1", f = "_Sequences.kt", i = {0, 1, 1}, l = {2423, 2427}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "accumulator"}, s = {"L$0", "L$0", "L$1"})
    public static final class C00721<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ R $initial;
        public final /* synthetic */ Function2<R, T, R> $operation;
        public final /* synthetic */ Sequence<T> $this_runningFold;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00721(R r, Sequence<? extends T> sequence, Function2<? super R, ? super T, ? extends R> function2, Continuation<? super C00721> continuation) {
            super(2, continuation);
            this.$initial = r;
            this.$this_runningFold = sequence;
            this.$operation = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00721 c00721 = new C00721(this.$initial, this.$this_runningFold, this.$operation, continuation);
            c00721.L$0 = obj;
            return c00721;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
        
            if (r1.yield(r7, r6) == r0) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0066, code lost:
        
            if (r4.yield(r3, r6) == r0) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
        
            return r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0050  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0066 -> B:7:0x0019). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
            /*
                r6 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2b
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r6.L$2
                java.util.Iterator r1 = (java.util.Iterator) r1
                java.lang.Object r3 = r6.L$1
                java.lang.Object r4 = r6.L$0
                kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
                kotlin.ResultKt.throwOnFailure(r7)
            L19:
                r7 = r3
                goto L4a
            L1b:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L23:
                java.lang.Object r1 = r6.L$0
                kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
                kotlin.ResultKt.throwOnFailure(r7)
                goto L40
            L2b:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                r1 = r7
                kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
                R r7 = r6.$initial
                r6.L$0 = r1
                r6.label = r3
                java.lang.Object r7 = r1.yield(r7, r6)
                if (r7 != r0) goto L40
                goto L68
            L40:
                R r7 = r6.$initial
                kotlin.sequences.Sequence<T> r3 = r6.$this_runningFold
                java.util.Iterator r3 = r3.iterator()
                r4 = r1
                r1 = r3
            L4a:
                boolean r3 = r1.hasNext()
                if (r3 == 0) goto L69
                java.lang.Object r3 = r1.next()
                kotlin.jvm.functions.Function2<R, T, R> r5 = r6.$operation
                java.lang.Object r3 = r5.invoke(r7, r3)
                r6.L$0 = r4
                r6.L$1 = r3
                r6.L$2 = r1
                r6.label = r2
                java.lang.Object r7 = r4.yield(r3, r6)
                if (r7 != r0) goto L19
            L68:
                return r0
            L69:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt.C00721.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C00721) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", f = "_Sequences.kt", i = {0, 1, 1, 1}, l = {2451, 2456}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "accumulator", "index"}, s = {"L$0", "L$0", "L$1", "I$0"})
    public static final class C00731<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ R $initial;
        public final /* synthetic */ Function3<Integer, R, T, R> $operation;
        public final /* synthetic */ Sequence<T> $this_runningFoldIndexed;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00731(R r, Sequence<? extends T> sequence, Function3<? super Integer, ? super R, ? super T, ? extends R> function3, Continuation<? super C00731> continuation) {
            super(2, continuation);
            this.$initial = r;
            this.$this_runningFoldIndexed = sequence;
            this.$operation = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00731 c00731 = new C00731(this.$initial, this.$this_runningFoldIndexed, this.$operation, continuation);
            c00731.L$0 = obj;
            return c00731;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
        
            if (r1.yield(r10, r9) == r0) goto L20;
         */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2e
                if (r1 == r3) goto L26
                if (r1 != r2) goto L1e
                int r1 = r9.I$0
                java.lang.Object r3 = r9.L$2
                java.util.Iterator r3 = (java.util.Iterator) r3
                java.lang.Object r4 = r9.L$1
                java.lang.Object r5 = r9.L$0
                kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = r4
                r4 = r1
                goto L4d
            L1e:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L26:
                java.lang.Object r1 = r9.L$0
                kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L43
            L2e:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                r1 = r10
                kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
                R r10 = r9.$initial
                r9.L$0 = r1
                r9.label = r3
                java.lang.Object r10 = r1.yield(r10, r9)
                if (r10 != r0) goto L43
                goto L76
            L43:
                R r10 = r9.$initial
                kotlin.sequences.Sequence<T> r3 = r9.$this_runningFoldIndexed
                java.util.Iterator r3 = r3.iterator()
                r4 = 0
                r5 = r1
            L4d:
                boolean r1 = r3.hasNext()
                if (r1 == 0) goto L7f
                java.lang.Object r1 = r3.next()
                kotlin.jvm.functions.Function3<java.lang.Integer, R, T, R> r6 = r9.$operation
                int r7 = r4 + 1
                if (r4 < 0) goto L7a
                java.lang.Integer r8 = new java.lang.Integer
                r8.<init>(r4)
                java.lang.Object r4 = r6.invoke(r8, r10, r1)
                r9.L$0 = r5
                r9.L$1 = r4
                r9.L$2 = r3
                r9.I$0 = r7
                r9.label = r2
                java.lang.Object r10 = r5.yield(r4, r9)
                if (r10 != r0) goto L77
            L76:
                return r0
            L77:
                r10 = r4
                r4 = r7
                goto L4d
            L7a:
                kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
                r10 = 0
                throw r10
            L7f:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt.C00731.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C00731) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduce$1", f = "_Sequences.kt", i = {0, 0, 0, 1, 1, 1}, l = {2480, 2483}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    public static final class C00741<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function2<S, T, S> $operation;
        public final /* synthetic */ Sequence<T> $this_runningReduce;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00741(Sequence<? extends T> sequence, Function2<? super S, ? super T, ? extends S> function2, Continuation<? super C00741> continuation) {
            super(2, continuation);
            this.$this_runningReduce = sequence;
            this.$operation = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00741 c00741 = new C00741(this.$this_runningReduce, this.$operation, continuation);
            c00741.L$0 = obj;
            return c00741;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            SequenceScope sequenceScope;
            Object next;
            Iterator it;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.L$0;
                Iterator it2 = this.$this_runningReduce.iterator();
                if (it2.hasNext()) {
                    next = it2.next();
                    this.L$0 = sequenceScope;
                    this.L$1 = it2;
                    this.L$2 = next;
                    this.label = 1;
                    if (sequenceScope.yield(next, this) != coroutineSingletons) {
                        it = it2;
                    }
                    return coroutineSingletons;
                }
                return Unit.INSTANCE;
            }
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            next = this.L$2;
            it = (Iterator) this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            while (it.hasNext()) {
                next = this.$operation.invoke((S) next, (T) it.next());
                this.L$0 = sequenceScope;
                this.L$1 = it;
                this.L$2 = next;
                this.label = 2;
                if (sequenceScope.yield(next, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C00741) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningReduceIndexed$1", f = "_Sequences.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {2509, 2513}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "accumulator", "$this$sequence", "iterator", "accumulator", "index"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0"})
    public static final class C00751<S> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super S>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function3<Integer, S, T, S> $operation;
        public final /* synthetic */ Sequence<T> $this_runningReduceIndexed;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00751(Sequence<? extends T> sequence, Function3<? super Integer, ? super S, ? super T, ? extends S> function3, Continuation<? super C00751> continuation) {
            super(2, continuation);
            this.$this_runningReduceIndexed = sequence;
            this.$operation = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00751 c00751 = new C00751(this.$this_runningReduceIndexed, this.$operation, continuation);
            c00751.L$0 = obj;
            return c00751;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            SequenceScope sequenceScope;
            Iterator it;
            Object next;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.L$0;
                it = this.$this_runningReduceIndexed.iterator();
                if (it.hasNext()) {
                    next = it.next();
                    this.L$0 = sequenceScope;
                    this.L$1 = it;
                    this.L$2 = next;
                    this.label = 1;
                    if (sequenceScope.yield(next, this) != coroutineSingletons) {
                    }
                    return coroutineSingletons;
                }
                return Unit.INSTANCE;
            }
            if (i == 1) {
                next = this.L$2;
                it = (Iterator) this.L$1;
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = this.I$0;
                Object obj2 = this.L$2;
                it = (Iterator) this.L$1;
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                i2 = i3;
                next = obj2;
            }
            while (it.hasNext()) {
                Function3<Integer, S, T, S> function3 = this.$operation;
                int i4 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                S sInvoke = function3.invoke(new Integer(i2), (S) next, (T) it.next());
                this.L$0 = sequenceScope;
                this.L$1 = it;
                this.L$2 = sInvoke;
                this.I$0 = i4;
                this.label = 2;
                if (sequenceScope.yield(sInvoke, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                next = sInvoke;
                i2 = i4;
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super S> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C00751) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$sortedWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00771<T> implements Sequence<T> {
        public final /* synthetic */ Comparator<? super T> $comparator;
        public final /* synthetic */ Sequence<T> $this_sortedWith;

        /* JADX WARN: Multi-variable type inference failed */
        public C00771(Sequence<? extends T> sequence, Comparator<? super T> comparator) {
            this.$this_sortedWith = sequence;
            this.$comparator = comparator;
        }

        @Override // kotlin.sequences.Sequence
        public Iterator<T> iterator() {
            List mutableList = SequencesKt___SequencesKt.toMutableList(this.$this_sortedWith);
            CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, this.$comparator);
            return mutableList.iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2", f = "_Sequences.kt", i = {0, 0, 0}, l = {2985}, m = "invokeSuspend", n = {"$this$result", "iterator", "next"}, s = {"L$0", "L$1", "L$2"})
    public static final class C00782<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Sequence<T> $this_zipWithNext;
        public final /* synthetic */ Function2<T, T, R> $transform;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00782(Sequence<? extends T> sequence, Function2<? super T, ? super T, ? extends R> function2, Continuation<? super C00782> continuation) {
            super(2, continuation);
            this.$this_zipWithNext = sequence;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00782 c00782 = new C00782(this.$this_zipWithNext, this.$transform, continuation);
            c00782.L$0 = obj;
            return c00782;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0059 -> B:6:0x0016). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
            /*
                r6 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L20
                if (r1 != r2) goto L18
                java.lang.Object r1 = r6.L$2
                java.lang.Object r3 = r6.L$1
                java.util.Iterator r3 = (java.util.Iterator) r3
                java.lang.Object r4 = r6.L$0
                kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
                kotlin.ResultKt.throwOnFailure(r7)
            L16:
                r7 = r1
                goto L3d
            L18:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L20:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
                kotlin.sequences.Sequence<T> r1 = r6.$this_zipWithNext
                java.util.Iterator r1 = r1.iterator()
                boolean r3 = r1.hasNext()
                if (r3 != 0) goto L36
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            L36:
                java.lang.Object r3 = r1.next()
                r4 = r7
                r7 = r3
                r3 = r1
            L3d:
                boolean r1 = r3.hasNext()
                if (r1 == 0) goto L5c
                java.lang.Object r1 = r3.next()
                kotlin.jvm.functions.Function2<T, T, R> r5 = r6.$transform
                java.lang.Object r7 = r5.invoke(r7, r1)
                r6.L$0 = r4
                r6.L$1 = r3
                r6.L$2 = r1
                r6.label = r2
                java.lang.Object r7 = r4.yield(r7, r6)
                if (r7 != r0) goto L16
                return r0
            L5c:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt.C00782.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super R> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C00782) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public static Object $r8$lambda$CDHTL88qJn3ftnmuDsIsMJ7U1zQ(Function1 function1, Object obj) {
        function1.invoke(obj);
        return obj;
    }

    public static Pair $r8$lambda$QNKnANqb3tTjGr3HyoWvim4tnb8(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public static /* synthetic */ Object $r8$lambda$Rm8l6RNTTAQPbOvQrDOPSWSShGU(Sequence sequence, Object obj) {
        requireNoNulls$lambda$16$SequencesKt___SequencesKt(sequence, obj);
        return obj;
    }

    public static /* synthetic */ Object $r8$lambda$VVXoILx4Eja6b5jwLZrM9ZhA_fk(Function2 function2, int i, Object obj) {
        onEachIndexed$lambda$15$SequencesKt___SequencesKt(function2, i, obj);
        return obj;
    }

    /* JADX INFO: renamed from: $r8$lambda$lV2uflAkFf7-Pl558ImAZcPMkn8, reason: not valid java name */
    public static Pair m1865$r8$lambda$lV2uflAkFf7Pl558ImAZcPMkn8(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public static /* synthetic */ Object $r8$lambda$mEmfGKnynnt3hFzOYy5qtZED4Mg(int i, int i2) {
        elementAt$lambda$0$SequencesKt___SequencesKt(i, i2);
        throw null;
    }

    /* JADX INFO: renamed from: $r8$lambda$oNUJM-vXB5kxvQXf6GE10ZSQ_ms, reason: not valid java name */
    public static boolean m1866$r8$lambda$oNUJMvXB5kxvQXf6GE10ZSQ_ms(Object obj) {
        return obj == null;
    }

    public static final <T> boolean all(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            if (!function1.invoke((Object) itM.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean any(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            if (function1.invoke((Object) itM.next()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static <T> Iterable<T> asIterable(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(sequence);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <T> Sequence<T> asSequence(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return sequence;
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associate(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(it.next());
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K> Map<K, T> associateBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : sequence) {
            linkedHashMap.put(keySelector.invoke(t), t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, ? super T>> M associateByTo(@NotNull Sequence<? extends T> sequence, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : sequence) {
            destination.put(keySelector.invoke(t), t);
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull Sequence<? extends T> sequence, @NotNull M destination, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(it.next());
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <K, V> Map<K, V> associateWith(@NotNull Sequence<? extends K> sequence, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (K k : sequence) {
            linkedHashMap.put(k, valueSelector.invoke(k));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateWithTo(@NotNull Sequence<? extends K> sequence, @NotNull M destination, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (K k : sequence) {
            destination.put(k, valueSelector.invoke(k));
        }
        return destination;
    }

    @JvmName(name = "averageOfByte")
    public static final double averageOfByte(@NotNull Sequence<Byte> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Byte> it = sequence.iterator();
        double dByteValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dByteValue += (double) it.next().byteValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dByteValue / ((double) i);
    }

    @JvmName(name = "averageOfDouble")
    public static final double averageOfDouble(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        double dDoubleValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dDoubleValue += it.next().doubleValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dDoubleValue / ((double) i);
    }

    @JvmName(name = "averageOfFloat")
    public static final double averageOfFloat(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        double dFloatValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dFloatValue += (double) it.next().floatValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dFloatValue / ((double) i);
    }

    @JvmName(name = "averageOfInt")
    public static final double averageOfInt(@NotNull Sequence<Integer> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Integer> it = sequence.iterator();
        double dIntValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dIntValue += (double) it.next().intValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dIntValue / ((double) i);
    }

    @JvmName(name = "averageOfLong")
    public static final double averageOfLong(@NotNull Sequence<Long> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Long> it = sequence.iterator();
        double dLongValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dLongValue += it.next().longValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dLongValue / ((double) i);
    }

    @JvmName(name = "averageOfShort")
    public static final double averageOfShort(@NotNull Sequence<Short> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Short> it = sequence.iterator();
        double dShortValue = 0.0d;
        int i = 0;
        while (it.hasNext()) {
            dShortValue += (double) it.next().shortValue();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dShortValue / ((double) i);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> Sequence<R> chunked(@NotNull Sequence<? extends T> sequence, int i, @NotNull Function1<? super List<? extends T>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return windowed(sequence, i, i, true, transform);
    }

    public static <T> boolean contains(@NotNull Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return indexOf(sequence, t) >= 0;
    }

    public static final <T> int count(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        int i = 0;
        while (itM.hasNext()) {
            if (function1.invoke((Object) itM.next()).booleanValue() && (i = i + 1) < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i;
    }

    @NotNull
    public static final <T> Sequence<T> distinct(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return distinctBy(sequence, new SequencesKt__SequencesKt$$ExternalSyntheticLambda4());
    }

    @NotNull
    public static final <T, K> Sequence<T> distinctBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return new DistinctSequence(sequence, selector);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Sequence<T> drop(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        if (i >= 0) {
            return i == 0 ? sequence : sequence instanceof DropTakeSequence ? ((DropTakeSequence) sequence).drop(i) : new DropSequence(sequence, i);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <T> Sequence<T> dropWhile(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new DropWhileSequence(sequence, predicate);
    }

    public static final <T> T elementAt(@NotNull Sequence<? extends T> sequence, final int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return (T) elementAtOrElse(sequence, i, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SequencesKt___SequencesKt.elementAt$lambda$0$SequencesKt___SequencesKt(i, ((Integer) obj).intValue());
                throw null;
            }
        });
    }

    public static final Object elementAt$lambda$0$SequencesKt___SequencesKt(int i, int i2) {
        throw new IndexOutOfBoundsException("Sequence doesn't contain element at index " + i + '.');
    }

    public static final <T> T elementAtOrElse(@NotNull Sequence<? extends T> sequence, int i, @NotNull Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i < 0) {
            return defaultValue.invoke(Integer.valueOf(i));
        }
        int i2 = 0;
        for (T t : sequence) {
            int i3 = i2 + 1;
            if (i == i2) {
                return t;
            }
            i2 = i3;
        }
        return defaultValue.invoke(Integer.valueOf(i));
    }

    @Nullable
    public static final <T> T elementAtOrNull(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        if (i < 0) {
            return null;
        }
        int i2 = 0;
        for (T t : sequence) {
            int i3 = i2 + 1;
            if (i == i2) {
                return t;
            }
            i2 = i3;
        }
        return null;
    }

    @NotNull
    public static <T> Sequence<T> filter(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new FilteringSequence(sequence, true, predicate);
    }

    @NotNull
    public static final <T> Sequence<T> filterIndexed(@NotNull Sequence<? extends T> sequence, @NotNull final Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new TransformingSequence(new FilteringSequence(new IndexingSequence(sequence), true, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SequencesKt___SequencesKt.filterIndexed$lambda$2$SequencesKt___SequencesKt(predicate, (IndexedValue) obj));
            }
        }), new SequencesKt___SequencesKt$$ExternalSyntheticLambda3());
    }

    public static final boolean filterIndexed$lambda$2$SequencesKt___SequencesKt(Function2 function2, IndexedValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((Boolean) function2.invoke(Integer.valueOf(it.index), it.value)).booleanValue();
    }

    public static final Object filterIndexed$lambda$3$SequencesKt___SequencesKt(IndexedValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.value;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterIndexedTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (predicate.invoke(Integer.valueOf(i), t).booleanValue()) {
                destination.add(t);
            }
            i = i2;
        }
        return destination;
    }

    public static final <R> Sequence<R> filterIsInstance(Sequence<?> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <R, C extends Collection<? super R>> C filterIsInstanceTo(Sequence<?> sequence, C destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<?> it = sequence.iterator();
        if (!it.hasNext()) {
            return destination;
        }
        it.next();
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> Sequence<T> filterNot(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new FilteringSequence(sequence, false, predicate);
    }

    @NotNull
    public static final <T> Sequence<T> filterNotNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return filterNot(sequence, new SequencesKt___SequencesKt$$ExternalSyntheticLambda6());
    }

    public static final boolean filterNotNull$lambda$5$SequencesKt___SequencesKt(Object obj) {
        return obj == null;
    }

    @NotNull
    public static final <C extends Collection<? super T>, T> C filterNotNullTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t : sequence) {
            if (t != null) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterNotTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : sequence) {
            if (!predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : sequence) {
            if (predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @InlineOnly
    public static final <T> T find(Sequence<? extends T> sequence, Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        return null;
    }

    @InlineOnly
    public static final <T> T findLast(Sequence<? extends T> sequence, Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                t = (T) obj;
            }
        }
        return t;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public static final <T> T first(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOf(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        R rInvoke;
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "transform");
        while (true) {
            if (!itM.hasNext()) {
                rInvoke = null;
                break;
            }
            rInvoke = function1.invoke((Object) itM.next());
            if (rInvoke != null) {
                break;
            }
        }
        if (rInvoke != null) {
            return rInvoke;
        }
        throw new NoSuchElementException("No element of the sequence was transformed to a non-null value.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOfOrNull(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "transform");
        while (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            if (rInvoke != null) {
                return rInvoke;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @Nullable
    public static final <T> T firstOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            ?? r0 = (Object) itM.next();
            if (function1.invoke(r0).booleanValue()) {
                return r0;
            }
        }
        return null;
    }

    @NotNull
    public static final <T, R> Sequence<R> flatMap(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new FlatteningSequence(sequence, transform, AnonymousClass2.INSTANCE);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapIndexedIterable")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> Sequence<R> flatMapIndexedIterable(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return SequencesKt__SequencesKt.flatMapIndexed(sequence, transform, SequencesKt___SequencesKt$flatMapIndexed$1.INSTANCE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedIterableTo(Sequence<? extends T> sequence, C destination, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapIndexedSequence")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> Sequence<R> flatMapIndexedSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return SequencesKt__SequencesKt.flatMapIndexed(sequence, transform, SequencesKt___SequencesKt$flatMapIndexed$2.INSTANCE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequenceTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedSequenceTo(Sequence<? extends T> sequence, C destination, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapIterable")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> Sequence<R> flatMapIterable(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new FlatteningSequence(sequence, transform, C00681.INSTANCE);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapIterableTo")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIterableTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(it.next()));
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C flatMapTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(it.next()));
        }
        return destination;
    }

    public static final <T, R> R fold(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            r = operation.invoke(r, it.next());
        }
        return r;
    }

    public static final <T, R> R foldIndexed(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            r = operation.invoke(Integer.valueOf(i), r, t);
            i = i2;
        }
        return r;
    }

    public static final <T> void forEach(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Unit> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "action");
        while (itM.hasNext()) {
            function1.invoke((Object) itM.next());
        }
    }

    public static final <T> void forEachIndexed(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            action.invoke(Integer.valueOf(i), t);
            i = i2;
        }
    }

    @NotNull
    public static final <T, K> Map<K, List<T>> groupBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : sequence) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, List<T>>> M groupByTo(@NotNull Sequence<? extends T> sequence, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : sequence) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(t);
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Grouping<T, K> groupingBy(@NotNull final Sequence<? extends T> sequence, @NotNull final Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        return new Grouping<T, K>() { // from class: kotlin.sequences.SequencesKt___SequencesKt.groupingBy.1
            @Override // kotlin.collections.Grouping
            public K keyOf(T t) {
                return keySelector.invoke(t);
            }

            @Override // kotlin.collections.Grouping
            public Iterator<T> sourceIterator() {
                return sequence.iterator();
            }
        };
    }

    public static final <T> int indexOf(@NotNull Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        int i = 0;
        for (T t2 : sequence) {
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual(t, t2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfFirst(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        int i = 0;
        while (itM.hasNext()) {
            R.color colorVar = (Object) itM.next();
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (function1.invoke(colorVar).booleanValue()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfLast(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        int i = -1;
        int i2 = 0;
        while (itM.hasNext()) {
            R.color colorVar = (Object) itM.next();
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (function1.invoke(colorVar).booleanValue()) {
                i = i2;
            }
            i2++;
        }
        return i;
    }

    @NotNull
    public static final <T, A extends Appendable> A joinTo(@NotNull Sequence<? extends T> sequence, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (T t : sequence) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            StringsKt__AppendableKt.appendElement(buffer, t, function1);
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ Appendable joinTo$default(Sequence sequence, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(sequence, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (i2 & 64) != 0 ? null : function1);
        return appendable;
    }

    @NotNull
    public static final <T> String joinToString(@NotNull Sequence<? extends T> sequence, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(sequence, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(Sequence sequence, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(sequence, charSequence, charSequence2, charSequence3, i, charSequence5, function12);
    }

    public static final <T> T last(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        T t = null;
        boolean z = false;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    public static final <T> int lastIndexOf(@NotNull Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        int i = -1;
        int i2 = 0;
        for (T t2 : sequence) {
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            if (Intrinsics.areEqual(t, t2)) {
                i = i2;
            }
            i2++;
        }
        return i;
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                t = (T) obj;
            }
        }
        return t;
    }

    @NotNull
    public static <T, R> Sequence<R> map(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new TransformingSequence(sequence, transform);
    }

    @NotNull
    public static final <T, R> Sequence<R> mapIndexed(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new TransformingIndexedSequence(sequence, transform);
    }

    @NotNull
    public static final <T, R> Sequence<R> mapIndexedNotNull(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return filterNotNull(new TransformingIndexedSequence(sequence, transform));
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            R rInvoke = transform.invoke(Integer.valueOf(i), t);
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
            i = i2;
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        for (T t : sequence) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            destination.add(transform.invoke(Integer.valueOf(i), t));
            i = i2;
        }
        return destination;
    }

    @NotNull
    public static <T, R> Sequence<R> mapNotNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return filterNotNull(new TransformingSequence(sequence, transform));
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            R rInvoke = transform.invoke(it.next());
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapTo(@NotNull Sequence<? extends T> sequence, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            destination.add(transform.invoke(it.next()));
        }
        return destination;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T maxByOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <T, R extends Comparable<? super R>> T maxByOrThrow(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double maxOf(Sequence<? extends T> sequence, Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R maxOfOrNull(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) itM.next());
        while (itM.hasNext()) {
            R rInvoke2 = function1.invoke((Object) itM.next());
            if (rInvoke.compareTo(rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWith(Sequence<? extends T> sequence, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWithOrNull(Sequence<? extends T> sequence, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) < 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: maxOrNull, reason: collision with other method in class */
    public static final Double m1871maxOrNull(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, it.next().doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final double maxOrThrow(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, it.next().doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T maxWithOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final <T> T maxWithOrThrow(@NotNull Sequence<? extends T> sequence, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T minByOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <T, R extends Comparable<? super R>> T minByOrThrow(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        ?? r0 = (Object) itM.next();
        if (!itM.hasNext()) {
            return r0;
        }
        R rInvoke = function1.invoke(r0);
        do {
            Object obj = (Object) itM.next();
            R rInvoke2 = function1.invoke(obj);
            r0 = r0;
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
                r0 = (T) obj;
            }
        } while (itM.hasNext());
        return (T) r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double minOf(Sequence<? extends T> sequence, Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R minOfOrNull(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        R rInvoke = function1.invoke((Object) itM.next());
        while (itM.hasNext()) {
            R rInvoke2 = function1.invoke((Object) itM.next());
            if (rInvoke.compareTo(rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWith(Sequence<? extends T> sequence, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWithOrNull(Sequence<? extends T> sequence, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        R rInvoke = selector.invoke(it.next());
        while (it.hasNext()) {
            R rInvoke2 = selector.invoke(it.next());
            if (comparator.compare(rInvoke, rInvoke2) > 0) {
                rInvoke = rInvoke2;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: minOrNull, reason: collision with other method in class */
    public static final Double m1879minOrNull(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, it.next().doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final double minOrThrow(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = it.next().doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, it.next().doubleValue());
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T minWithOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final <T> T minWithOrThrow(@NotNull Sequence<? extends T> sequence, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @NotNull
    public static final <T> Sequence<T> minus(@NotNull Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return new C00701(sequence, t);
    }

    @InlineOnly
    public static final <T> Sequence<T> minusElement(Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return minus(sequence, t);
    }

    public static final <T> boolean none(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        while (itM.hasNext()) {
            if (function1.invoke((Object) itM.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Sequence<T> onEach(@NotNull Sequence<? extends T> sequence, @NotNull final Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        return map(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                action.invoke(obj);
                return obj;
            }
        });
    }

    public static final Object onEach$lambda$14$SequencesKt___SequencesKt(Function1 function1, Object obj) {
        function1.invoke(obj);
        return obj;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T> Sequence<T> onEachIndexed(@NotNull Sequence<? extends T> sequence, @NotNull final Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        return mapIndexed(sequence, new Function2() { // from class: kotlin.sequences.SequencesKt___SequencesKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SequencesKt___SequencesKt.onEachIndexed$lambda$15$SequencesKt___SequencesKt(action, ((Integer) obj).intValue(), obj2);
                return obj2;
            }
        });
    }

    public static final Object onEachIndexed$lambda$15$SequencesKt___SequencesKt(Function2 function2, int i, Object obj) {
        function2.invoke(Integer.valueOf(i), obj);
        return obj;
    }

    @NotNull
    public static final <T> Pair<List<T>, List<T>> partition(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (T t : sequence) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            } else {
                arrayList2.add(t);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return SequencesKt__SequencesKt.flatten(ArraysKt___ArraysKt.asSequence(new Sequence[]{sequence, ArraysKt___ArraysKt.asSequence(new Object[]{t})}));
    }

    @InlineOnly
    public static final <T> Sequence<T> plusElement(Sequence<? extends T> sequence, T t) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return plus(sequence, t);
    }

    public static final <S, T extends S> S reduce(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty sequence can't be reduced.");
        }
        S next = it.next();
        while (it.hasNext()) {
            next = operation.invoke(next, it.next());
        }
        return next;
    }

    public static final <S, T extends S> S reduceIndexed(@NotNull Sequence<? extends T> sequence, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty sequence can't be reduced.");
        }
        S next = it.next();
        int i = 1;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            next = operation.invoke(Integer.valueOf(i), next, it.next());
            i = i2;
        }
        return next;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceIndexedOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        S next = it.next();
        int i = 1;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            next = operation.invoke(Integer.valueOf(i), next, it.next());
            i = i2;
        }
        return next;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        S next = it.next();
        while (it.hasNext()) {
            next = operation.invoke(next, it.next());
        }
        return next;
    }

    @NotNull
    public static final <T> Sequence<T> requireNoNulls(@NotNull final Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return map(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SequencesKt___SequencesKt.requireNoNulls$lambda$16$SequencesKt___SequencesKt(sequence, obj);
                return obj;
            }
        });
    }

    public static final Object requireNoNulls$lambda$16$SequencesKt___SequencesKt(Sequence sequence, Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException("null element found in " + sequence + '.');
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> Sequence<R> runningFold(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return SequencesKt__SequenceBuilderKt.sequence(new C00721(r, sequence, operation, null));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> Sequence<R> runningFoldIndexed(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return SequencesKt__SequenceBuilderKt.sequence(new C00731(r, sequence, operation, null));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> Sequence<S> runningReduce(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return SequencesKt__SequenceBuilderKt.sequence(new C00741(sequence, operation, null));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> Sequence<S> runningReduceIndexed(@NotNull Sequence<? extends T> sequence, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return SequencesKt__SequenceBuilderKt.sequence(new C00751(sequence, operation, null));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> Sequence<R> scan(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return runningFold(sequence, r, operation);
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> Sequence<R> scanIndexed(@NotNull Sequence<? extends T> sequence, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return runningFoldIndexed(sequence, r, operation);
    }

    public static final <T> T single(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        T t = null;
        boolean z = false;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Sequence contains more than one matching element.");
                }
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "predicate");
        boolean z = false;
        T t = null;
        while (itM.hasNext()) {
            Object obj = (Object) itM.next();
            if (function1.invoke(obj).booleanValue()) {
                if (z) {
                    return null;
                }
                z = true;
                t = (T) obj;
            }
        }
        if (z) {
            return t;
        }
        return null;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> Sequence<T> sorted(@NotNull final Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return (Sequence<T>) new Sequence<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt.sorted.1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                List mutableList = SequencesKt___SequencesKt.toMutableList(sequence);
                CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
                return mutableList.iterator();
            }
        };
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> Sequence<T> sortedBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sequence, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> Sequence<T> sortedByDescending(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sequence, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final <T extends Comparable<? super T>> Sequence<T> sortedDescending(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return sortedWith(sequence, ComparisonsKt__ComparisonsKt.reverseOrder());
    }

    @NotNull
    public static <T> Sequence<T> sortedWith(@NotNull Sequence<? extends T> sequence, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return new C00771(sequence, comparator);
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> int sumBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Integer> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        int iIntValue = 0;
        while (itM.hasNext()) {
            iIntValue += function1.invoke((Object) itM.next()).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> double sumByDouble(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        double dDoubleValue = 0.0d;
        while (itM.hasNext()) {
            dDoubleValue += function1.invoke((Object) itM.next()).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfByte")
    public static final int sumOfByte(@NotNull Sequence<Byte> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Byte> it = sequence.iterator();
        int iByteValue = 0;
        while (it.hasNext()) {
            iByteValue += it.next().byteValue();
        }
        return iByteValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final <T> double sumOfDouble(Sequence<? extends T> sequence, Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        double dDoubleValue = 0.0d;
        while (itM.hasNext()) {
            dDoubleValue += function1.invoke((Object) itM.next()).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfFloat")
    public static final float sumOfFloat(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        float fFloatValue = 0.0f;
        while (it.hasNext()) {
            fFloatValue += it.next().floatValue();
        }
        return fFloatValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final <T> int sumOfInt(Sequence<? extends T> sequence, Function1<? super T, Integer> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        int iIntValue = 0;
        while (itM.hasNext()) {
            iIntValue += function1.invoke((Object) itM.next()).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final <T> long sumOfLong(Sequence<? extends T> sequence, Function1<? super T, Long> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        long jLongValue = 0;
        while (itM.hasNext()) {
            jLongValue += function1.invoke((Object) itM.next()).longValue();
        }
        return jLongValue;
    }

    @JvmName(name = "sumOfShort")
    public static final int sumOfShort(@NotNull Sequence<Short> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Short> it = sequence.iterator();
        int iShortValue = 0;
        while (it.hasNext()) {
            iShortValue += it.next().shortValue();
        }
        return iShortValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> int sumOfUInt(Sequence<? extends T> sequence, Function1<? super T, UInt> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        int i = 0;
        while (itM.hasNext()) {
            i += function1.invoke((Object) itM.next()).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> long sumOfULong(Sequence<? extends T> sequence, Function1<? super T, ULong> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        long j = 0;
        while (itM.hasNext()) {
            j += function1.invoke((Object) itM.next()).data;
        }
        return j;
    }

    @NotNull
    public static final <T> Sequence<T> take(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        if (i >= 0) {
            return i == 0 ? EmptySequence.INSTANCE : sequence instanceof DropTakeSequence ? ((DropTakeSequence) sequence).take(i) : new TakeSequence(sequence, i);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <T> Sequence<T> takeWhile(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return new TakeWhileSequence(sequence, predicate);
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C toCollection(@NotNull Sequence<? extends T> sequence, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    @NotNull
    public static final <T> HashSet<T> toHashSet(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        HashSet<T> hashSet = new HashSet<>();
        toCollection(sequence, hashSet);
        return hashSet;
    }

    @NotNull
    public static <T> List<T> toList(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        T next = it.next();
        if (!it.hasNext()) {
            return CollectionsKt__CollectionsJVMKt.listOf(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> toMutableList(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        ArrayList arrayList = new ArrayList();
        toCollection(sequence, arrayList);
        return arrayList;
    }

    @NotNull
    public static final <T> Set<T> toMutableSet(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }

    @NotNull
    public static final <T> Set<T> toSet(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptySet.INSTANCE;
        }
        T next = it.next();
        if (!it.hasNext()) {
            return SetsKt__SetsJVMKt.setOf(next);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(next);
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> Sequence<List<T>> windowed(@NotNull Sequence<? extends T> sequence, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return SlidingWindowKt.windowedSequence(sequence, i, i2, z, false);
    }

    public static /* synthetic */ Sequence windowed$default(Sequence sequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(sequence, i, i2, z);
    }

    @NotNull
    public static final <T> Sequence<IndexedValue<T>> withIndex(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return new IndexingSequence(sequence);
    }

    @NotNull
    public static final <T, R> Sequence<Pair<T, R>> zip(@NotNull Sequence<? extends T> sequence, @NotNull Sequence<? extends R> other) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return new MergingSequence(sequence, other, new SequencesKt___SequencesKt$$ExternalSyntheticLambda0());
    }

    public static final Pair zip$lambda$17$SequencesKt___SequencesKt(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> Sequence<Pair<T, T>> zipWithNext(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return zipWithNext(sequence, new SequencesKt___SequencesKt$$ExternalSyntheticLambda4());
    }

    public static final Pair zipWithNext$lambda$18$SequencesKt___SequencesKt(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> Sequence<List<T>> chunked(@NotNull Sequence<? extends T> sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return SlidingWindowKt.windowedSequence(sequence, i, i, true, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Sequence<T> minus(@NotNull final Sequence<? extends T> sequence, @NotNull final T[] elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return elements.length == 0 ? sequence : new Sequence<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt.minus.2
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                Sequence<T> sequence2 = sequence;
                final T[] tArr = elements;
                return ((FilteringSequence) SequencesKt___SequencesKt.filterNot(sequence2, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(ArraysKt___ArraysKt.contains(tArr, obj));
                    }
                })).iterator();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> Sequence<R> windowed(@NotNull Sequence<? extends T> sequence, int i, int i2, boolean z, @NotNull Function1<? super List<? extends T>, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return map(SlidingWindowKt.windowedSequence(sequence, i, i2, z, true), transform);
    }

    public static /* synthetic */ Sequence windowed$default(Sequence sequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(sequence, i, i2, z, function1);
    }

    @NotNull
    public static final <T, R, V> Sequence<V> zip(@NotNull Sequence<? extends T> sequence, @NotNull Sequence<? extends R> other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return new MergingSequence(sequence, other, transform);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T, R> Sequence<R> zipWithNext(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super T, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return SequencesKt__SequenceBuilderKt.sequence(new C00782(sequence, transform, null));
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull Sequence<? extends T> sequence, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : sequence) {
            destination.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associateBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : sequence) {
            linkedHashMap.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T> Sequence<T> minus(@NotNull final Sequence<? extends T> sequence, @NotNull final Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt.minus.3
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                final Collection collectionConvertToListIfNotCollection = CollectionsKt__MutableCollectionsKt.convertToListIfNotCollection(elements);
                return collectionConvertToListIfNotCollection.isEmpty() ? sequence.iterator() : ((FilteringSequence) SequencesKt___SequencesKt.filterNot(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(collectionConvertToListIfNotCollection.contains(obj));
                    }
                })).iterator();
            }
        };
    }

    @NotNull
    public static final <T> Sequence<T> minus(@NotNull final Sequence<? extends T> sequence, @NotNull final Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt___SequencesKt.minus.4
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                final List list = SequencesKt___SequencesKt.toList(elements);
                return list.isEmpty() ? sequence.iterator() : ((FilteringSequence) SequencesKt___SequencesKt.filterNot(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$4$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(list.contains(obj));
                    }
                })).iterator();
            }
        };
    }

    public static final <T> boolean any(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return sequence.iterator().hasNext();
    }

    public static <T> int count(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i;
    }

    @Nullable
    public static <T> T firstOrNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static final <T> boolean none(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return !sequence.iterator().hasNext();
    }

    @NotNull
    public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> sequence, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return plus((Sequence) sequence, (Iterable) ArraysKt___ArraysJvmKt.asList(elements));
    }

    public static final <T> T first(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    @NotNull
    public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> sequence, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return SequencesKt__SequencesKt.flatten(ArraysKt___ArraysKt.asSequence(new Sequence[]{sequence, CollectionsKt___CollectionsKt.asSequence(elements)}));
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    @JvmName(name = "sumOfDouble")
    public static final double sumOfDouble(@NotNull Sequence<Double> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Double> it = sequence.iterator();
        double dDoubleValue = 0.0d;
        while (it.hasNext()) {
            dDoubleValue += it.next().doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfInt")
    public static final int sumOfInt(@NotNull Sequence<Integer> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Integer> it = sequence.iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            iIntValue += it.next().intValue();
        }
        return iIntValue;
    }

    @JvmName(name = "sumOfLong")
    public static final long sumOfLong(@NotNull Sequence<Long> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Long> it = sequence.iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            jLongValue += it.next().longValue();
        }
        return jLongValue;
    }

    public static <T> T last(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                next = it.next();
            }
            return next;
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: maxOrNull, reason: collision with other method in class */
    public static final Float m1872maxOrNull(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = it.next().floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.max(fFloatValue, it.next().floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    /* JADX INFO: renamed from: maxOrThrow, reason: collision with other method in class */
    public static final float m1873maxOrThrow(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        if (it.hasNext()) {
            float fFloatValue = it.next().floatValue();
            while (it.hasNext()) {
                fFloatValue = Math.max(fFloatValue, it.next().floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    /* JADX INFO: renamed from: minOrNull, reason: collision with other method in class */
    public static final Float m1880minOrNull(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float fFloatValue = it.next().floatValue();
        while (it.hasNext()) {
            fFloatValue = Math.min(fFloatValue, it.next().floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    /* JADX INFO: renamed from: minOrThrow, reason: collision with other method in class */
    public static final float m1881minOrThrow(@NotNull Sequence<Float> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<Float> it = sequence.iterator();
        if (it.hasNext()) {
            float fFloatValue = it.next().floatValue();
            while (it.hasNext()) {
                fFloatValue = Math.min(fFloatValue, it.next().floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    public static final <T> T single(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            T next = it.next();
            if (it.hasNext()) {
                throw new IllegalArgumentException("Sequence has more than one element.");
            }
            return next;
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull Sequence<? extends T> sequence, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : sequence) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Double m1869maxOfOrNull(Sequence<? extends T> sequence, Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Double m1877minOfOrNull(Sequence<? extends T> sequence, Function1<? super T, Double> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        double dDoubleValue = function1.invoke((Object) itM.next()).doubleValue();
        while (itM.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, function1.invoke((Object) itM.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    @NotNull
    public static final <T> Sequence<T> plus(@NotNull Sequence<? extends T> sequence, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return SequencesKt__SequencesKt.flatten(ArraysKt___ArraysKt.asSequence(new Sequence[]{sequence, elements}));
    }

    @NotNull
    public static final <T, K, V> Map<K, List<V>> groupBy(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : sequence) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T> float m1867maxOf(Sequence<? extends T> sequence, Function1<? super T, Float> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (itM.hasNext()) {
            float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
            while (itM.hasNext()) {
                fFloatValue = Math.max(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T> float m1875minOf(Sequence<? extends T> sequence, Function1<? super T, Float> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (itM.hasNext()) {
            float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
            while (itM.hasNext()) {
                fFloatValue = Math.min(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T maxOrNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (next.compareTo(next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    @NotNull
    /* JADX INFO: renamed from: maxOrThrow, reason: collision with other method in class */
    public static final <T extends Comparable<? super T>> T m1874maxOrThrow(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                T next2 = it.next();
                if (next.compareTo(next2) < 0) {
                    next = next2;
                }
            }
            return next;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T minOrNull(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (next.compareTo(next2) > 0) {
                next = next2;
            }
        }
        return next;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    @NotNull
    /* JADX INFO: renamed from: minOrThrow, reason: collision with other method in class */
    public static final <T extends Comparable<? super T>> T m1882minOrThrow(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator<? extends T> it = sequence.iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                T next2 = it.next();
                if (next.compareTo(next2) > 0) {
                    next = next2;
                }
            }
            return next;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Float m1870maxOfOrNull(Sequence<? extends T> sequence, Function1<? super T, Float> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
        while (itM.hasNext()) {
            fFloatValue = Math.max(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Float m1878minOfOrNull(Sequence<? extends T> sequence, Function1<? super T, Float> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (!itM.hasNext()) {
            return null;
        }
        float fFloatValue = function1.invoke((Object) itM.next()).floatValue();
        while (itM.hasNext()) {
            fFloatValue = Math.min(fFloatValue, function1.invoke((Object) itM.next()).floatValue());
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m1868maxOf(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            while (itM.hasNext()) {
                R rInvoke2 = function1.invoke((Object) itM.next());
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m1876minOf(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        Iterator itM = SequencesKt___SequencesJvmKt$$ExternalSyntheticOutline0.m(sequence, "<this>", function1, "selector");
        if (itM.hasNext()) {
            R rInvoke = function1.invoke((Object) itM.next());
            while (itM.hasNext()) {
                R rInvoke2 = function1.invoke((Object) itM.next());
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    public static Object $r8$lambda$a19oMviktfkfj6GExYZkOBcS3Mk(Object obj) {
        return obj;
    }

    public static final Object distinct$lambda$13$SequencesKt___SequencesKt(Object obj) {
        return obj;
    }
}
