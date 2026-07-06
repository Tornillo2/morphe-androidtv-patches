package kotlinx.coroutines.channels;

import androidx.lifecycle.SavedStateHandle;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.google.common.math.DoubleMath;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.apache.commons.compress.archivers.zip.UnixStat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ChannelsKt__DeprecatedKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {404}, m = "any", n = {"$this$consume$iv"}, s = {"L$0"})
    public static final class AnonymousClass1<E> extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.any(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$consumes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00811 extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ ReceiveChannel<?> $this_consumes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00811(ReceiveChannel<?> receiveChannel) {
            super(1);
            this.$this_consumes = receiveChannel;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            ChannelsKt__Channels_commonKt.cancelConsumed(this.$this_consumes, th);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$consumesAll$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00821 extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ ReceiveChannel<?>[] $channels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00821(ReceiveChannel<?>[] receiveChannelArr) {
            super(1);
            this.$channels = receiveChannelArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws Throwable {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) throws Throwable {
            Throwable th2 = null;
            for (ReceiveChannel<?> receiveChannel : this.$channels) {
                try {
                    ChannelsKt__Channels_commonKt.cancelConsumed(receiveChannel, th);
                } catch (Throwable th3) {
                    if (th2 == null) {
                        th2 = th3;
                    } else {
                        ExceptionsKt__ExceptionsKt.addSuppressed(th2, th3);
                    }
                }
            }
            if (th2 != null) {
                throw th2;
            }
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "count", n = {"count", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    public static final class C00831<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C00831(Continuation<? super C00831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.count(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00841 extends SuspendLambda implements Function2<Object, Continuation<Object>, Object> {
        public /* synthetic */ Object L$0;
        public int label;

        public C00841(Continuation<? super C00841> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00841 c00841 = new C00841(2, continuation);
            c00841.L$0 = obj;
            return c00841;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.L$0;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(Object obj, @Nullable Continuation<Object> continuation) {
            return ((C00841) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {387, 388, 390}, m = "invokeSuspend", n = {"$this$produce", SavedStateHandle.KEYS, "$this$produce", SavedStateHandle.KEYS, "e", "$this$produce", SavedStateHandle.KEYS, "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
    public static final class C00851<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function2<E, Continuation<? super K>, Object> $selector;
        public final /* synthetic */ ReceiveChannel<E> $this_distinctBy;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00851(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super C00851> continuation) {
            super(2, continuation);
            this.$this_distinctBy = receiveChannel;
            this.$selector = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00851 c00851 = new C00851(this.$this_distinctBy, this.$selector, continuation);
            c00851.L$0 = obj;
            return c00851;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v18 */
        /* JADX WARN: Type inference failed for: r5v19 */
        /* JADX WARN: Type inference failed for: r5v2 */
        /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v9 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v15 */
        /* JADX WARN: Type inference failed for: r6v16 */
        /* JADX WARN: Type inference failed for: r6v17 */
        /* JADX WARN: Type inference failed for: r6v18 */
        /* JADX WARN: Type inference failed for: r6v19 */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object, java.util.HashSet] */
        /* JADX WARN: Type inference failed for: r6v20 */
        /* JADX WARN: Type inference failed for: r6v21 */
        /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v4 */
        /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Type inference failed for: r6v7 */
        /* JADX WARN: Type inference failed for: r6v8, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v11 */
        /* JADX WARN: Type inference failed for: r7v12 */
        /* JADX WARN: Type inference failed for: r7v13 */
        /* JADX WARN: Type inference failed for: r7v14 */
        /* JADX WARN: Type inference failed for: r7v3 */
        /* JADX WARN: Type inference failed for: r7v4 */
        /* JADX WARN: Type inference failed for: r7v5 */
        /* JADX WARN: Type inference failed for: r9v0 */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x009f -> B:29:0x00b8). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00b2 -> B:28:0x00b4). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1<E> for r10v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
            /*
                r10 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L4f
                if (r1 == r4) goto L3f
                if (r1 == r3) goto L2a
                if (r1 != r2) goto L22
                java.lang.Object r1 = r10.L$3
                java.lang.Object r5 = r10.L$2
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r10.L$1
                java.util.HashSet r6 = (java.util.HashSet) r6
                java.lang.Object r7 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r11)
                goto Lb4
            L22:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L2a:
                java.lang.Object r1 = r10.L$3
                java.lang.Object r5 = r10.L$2
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r10.L$1
                java.util.HashSet r6 = (java.util.HashSet) r6
                java.lang.Object r7 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r11)
                r9 = r5
                r5 = r1
                r1 = r9
                goto L9b
            L3f:
                java.lang.Object r1 = r10.L$2
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r10.L$1
                java.util.HashSet r5 = (java.util.HashSet) r5
                java.lang.Object r6 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r11)
                goto L77
            L4f:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
                java.util.HashSet r1 = new java.util.HashSet
                r1.<init>()
                kotlinx.coroutines.channels.ReceiveChannel<E> r5 = r10.$this_distinctBy
                kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
                r6 = r5
                r5 = r1
                r1 = r6
                r6 = r11
            L65:
                r10.L$0 = r6
                r10.L$1 = r5
                r10.L$2 = r1
                r11 = 0
                r10.L$3 = r11
                r10.label = r4
                java.lang.Object r11 = r1.hasNext(r10)
                if (r11 != r0) goto L77
                goto Lb1
            L77:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto Lbb
                java.lang.Object r11 = r1.next()
                kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super K>, java.lang.Object> r7 = r10.$selector
                r10.L$0 = r6
                r10.L$1 = r5
                r10.L$2 = r1
                r10.L$3 = r11
                r10.label = r3
                java.lang.Object r7 = r7.invoke(r11, r10)
                if (r7 != r0) goto L96
                goto Lb1
            L96:
                r9 = r5
                r5 = r11
                r11 = r7
                r7 = r6
                r6 = r9
            L9b:
                boolean r8 = r6.contains(r11)
                if (r8 != 0) goto Lb8
                r10.L$0 = r7
                r10.L$1 = r6
                r10.L$2 = r1
                r10.L$3 = r11
                r10.label = r2
                java.lang.Object r5 = r7.send(r5, r10)
                if (r5 != r0) goto Lb2
            Lb1:
                return r0
            Lb2:
                r5 = r1
                r1 = r11
            Lb4:
                r6.add(r1)
                r1 = r5
            Lb8:
                r5 = r6
                r6 = r7
                goto L65
            Lbb:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00851.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00851) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", f = "Deprecated.kt", i = {0, 0, 1, 2}, l = {164, 169, DoubleMath.MAX_FACTORIAL}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "$this$produce"}, s = {"L$0", "I$0", "L$0", "L$0"})
    public static final class C00861 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $n;
        public final /* synthetic */ ReceiveChannel<Object> $this_drop;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00861(int i, ReceiveChannel<Object> receiveChannel, Continuation<? super C00861> continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_drop = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00861 c00861 = new C00861(this.$n, this.$this_drop, continuation);
            c00861.L$0 = obj;
            return c00861;
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        
            if (r9 == r0) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0073, code lost:
        
            if (r1 == 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00a2, code lost:
        
            if (r4.send(r9, r8) == r0) goto L37;
         */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00a5  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0063 -> B:23:0x0066). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00a2 -> B:8:0x001a). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L3e
                if (r1 == r4) goto L30
                if (r1 == r3) goto L24
                if (r1 != r2) goto L1c
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
                kotlin.ResultKt.throwOnFailure(r9)
            L1a:
                r9 = r4
                goto L7c
            L1c:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L24:
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
                kotlin.ResultKt.throwOnFailure(r9)
                goto L8c
            L30:
                int r1 = r8.I$0
                java.lang.Object r5 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r9)
                goto L66
            L3e:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
                int r1 = r8.$n
                if (r1 < 0) goto L4b
                r5 = 1
                goto L4c
            L4b:
                r5 = 0
            L4c:
                if (r5 == 0) goto La8
                if (r1 <= 0) goto L76
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r5 = r8.$this_drop
                kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
                r6 = r9
            L57:
                r8.L$0 = r6
                r8.L$1 = r5
                r8.I$0 = r1
                r8.label = r4
                java.lang.Object r9 = r5.hasNext(r8)
                if (r9 != r0) goto L66
                goto La4
            L66:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto L75
                r5.next()
                int r1 = r1 + (-1)
                if (r1 != 0) goto L57
            L75:
                r9 = r6
            L76:
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r8.$this_drop
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            L7c:
                r8.L$0 = r9
                r8.L$1 = r1
                r8.label = r3
                java.lang.Object r4 = r1.hasNext(r8)
                if (r4 != r0) goto L89
                goto La4
            L89:
                r7 = r4
                r4 = r9
                r9 = r7
            L8c:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto La5
                java.lang.Object r9 = r1.next()
                r8.L$0 = r4
                r8.L$1 = r1
                r8.label = r2
                java.lang.Object r9 = r4.send(r9, r8)
                if (r9 != r0) goto L1a
            La4:
                return r0
            La5:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            La8:
                java.lang.String r9 = "Requested element count "
                java.lang.String r0 = " is less than zero."
                java.lang.String r9 = androidx.collection.ObjectListKt$$ExternalSyntheticOutline1.m(r9, r1, r0)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r9 = r9.toString()
                r0.<init>(r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00861.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00861) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2, 3, 4}, l = {181, 182, 183, 187, 188}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
    public static final class C00871 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function2<Object, Continuation<? super Boolean>, Object> $predicate;
        public final /* synthetic */ ReceiveChannel<Object> $this_dropWhile;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00871(ReceiveChannel<Object> receiveChannel, Function2<Object, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C00871> continuation) {
            super(2, continuation);
            this.$this_dropWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00871 c00871 = new C00871(this.$this_dropWhile, this.$predicate, continuation);
            c00871.L$0 = obj;
            return c00871;
        }

        /* JADX WARN: Code restructure failed: missing block: B:44:0x00eb, code lost:
        
            if (r4.send(r12, r11) == r0) goto L45;
         */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005f A[PHI: r1 r8 r12
          0x005f: PHI (r1v3 kotlinx.coroutines.channels.ChannelIterator<java.lang.Object>) = 
          (r1v5 kotlinx.coroutines.channels.ChannelIterator<java.lang.Object>)
          (r1v15 kotlinx.coroutines.channels.ChannelIterator<java.lang.Object>)
         binds: [B:21:0x007d, B:17:0x0054] A[DONT_GENERATE, DONT_INLINE]
          0x005f: PHI (r8v1 kotlinx.coroutines.channels.ProducerScope) = (r8v4 kotlinx.coroutines.channels.ProducerScope), (r8v8 kotlinx.coroutines.channels.ProducerScope) binds: [B:21:0x007d, B:17:0x0054] A[DONT_GENERATE, DONT_INLINE]
          0x005f: PHI (r12v3 java.lang.Object) = (r12v8 java.lang.Object), (r12v0 java.lang.Object) binds: [B:21:0x007d, B:17:0x0054] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00ab  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00bc  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00dd  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00ee  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x009e -> B:16:0x0052). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00eb -> B:10:0x0021). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 241
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00871.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00871) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {38}, m = "elementAt", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    public static final class C00881<E> extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C00881(Continuation<? super C00881> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {53}, m = "elementAtOrNull", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    public static final class C00891<E> extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C00891(Continuation<? super C00891> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {198, 199, 199}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    public static final class C00901<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function2<E, Continuation<? super Boolean>, Object> $predicate;
        public final /* synthetic */ ReceiveChannel<E> $this_filter;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00901(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C00901> continuation) {
            super(2, continuation);
            this.$this_filter = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00901 c00901 = new C00901(this.$this_filter, this.$predicate, continuation);
            c00901.L$0 = obj;
            return c00901;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x004f, code lost:
        
            r6 = r7;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0098  */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v13, types: [kotlinx.coroutines.channels.ProducerScope] */
        /* JADX WARN: Type inference failed for: r6v14 */
        /* JADX WARN: Type inference failed for: r6v15 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
        /* JADX WARN: Type inference failed for: r7v3 */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r8v0 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L41
                if (r1 == r5) goto L35
                if (r1 == r4) goto L24
                if (r1 != r3) goto L1c
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r6 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4f
            L1c:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L24:
                java.lang.Object r1 = r9.L$2
                java.lang.Object r6 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r10)
                r8 = r6
                r6 = r1
                r1 = r8
                goto L7f
            L35:
                java.lang.Object r1 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r6 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r10)
                goto L5e
            L41:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
                kotlinx.coroutines.channels.ReceiveChannel<E> r1 = r9.$this_filter
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r6 = r10
            L4f:
                r9.L$0 = r6
                r9.L$1 = r1
                r9.L$2 = r2
                r9.label = r5
                java.lang.Object r10 = r1.hasNext(r9)
                if (r10 != r0) goto L5e
                goto L95
            L5e:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto L98
                java.lang.Object r10 = r1.next()
                kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r7 = r9.$predicate
                r9.L$0 = r6
                r9.L$1 = r1
                r9.L$2 = r10
                r9.label = r4
                java.lang.Object r7 = r7.invoke(r10, r9)
                if (r7 != r0) goto L7b
                goto L95
            L7b:
                r8 = r6
                r6 = r10
                r10 = r7
                r7 = r8
            L7f:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto L96
                r9.L$0 = r7
                r9.L$1 = r1
                r9.L$2 = r2
                r9.label = r3
                java.lang.Object r10 = r7.send(r6, r9)
                if (r10 != r0) goto L96
            L95:
                return r0
            L96:
                r6 = r7
                goto L4f
            L98:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00901.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00901) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {211, 212, 212}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "e", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
    public static final class C00911 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function3<Integer, Object, Continuation<? super Boolean>, Object> $predicate;
        public final /* synthetic */ ReceiveChannel<Object> $this_filterIndexed;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00911(ReceiveChannel<Object> receiveChannel, Function3<? super Integer, Object, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super C00911> continuation) {
            super(2, continuation);
            this.$this_filterIndexed = receiveChannel;
            this.$predicate = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00911 c00911 = new C00911(this.$this_filterIndexed, this.$predicate, continuation);
            c00911.L$0 = obj;
            return c00911;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0058, code lost:
        
            r7 = r8;
         */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ae  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) throws java.lang.Throwable {
            /*
                r12 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r12.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L47
                if (r1 == r5) goto L39
                if (r1 == r4) goto L26
                if (r1 != r3) goto L1e
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r13)
                goto L58
            L1e:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L26:
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$2
                java.lang.Object r7 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
                java.lang.Object r8 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                kotlin.ResultKt.throwOnFailure(r13)
                r11 = r7
                r7 = r6
                r6 = r11
                goto L93
            L39:
                int r1 = r12.I$0
                java.lang.Object r6 = r12.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r13)
                goto L69
            L47:
                kotlin.ResultKt.throwOnFailure(r13)
                java.lang.Object r13 = r12.L$0
                kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r12.$this_filterIndexed
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r6 = 0
                r7 = r13
                r6 = r1
                r1 = 0
            L58:
                r12.L$0 = r7
                r12.L$1 = r6
                r12.L$2 = r2
                r12.I$0 = r1
                r12.label = r5
                java.lang.Object r13 = r6.hasNext(r12)
                if (r13 != r0) goto L69
                goto Lab
            L69:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lae
                java.lang.Object r13 = r6.next()
                kotlin.jvm.functions.Function3<java.lang.Integer, java.lang.Object, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r8 = r12.$predicate
                int r9 = r1 + 1
                java.lang.Integer r10 = new java.lang.Integer
                r10.<init>(r1)
                r12.L$0 = r7
                r12.L$1 = r6
                r12.L$2 = r13
                r12.I$0 = r9
                r12.label = r4
                java.lang.Object r1 = r8.invoke(r10, r13, r12)
                if (r1 != r0) goto L8f
                goto Lab
            L8f:
                r8 = r7
                r7 = r13
                r13 = r1
                r1 = r9
            L93:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lac
                r12.L$0 = r8
                r12.L$1 = r6
                r12.L$2 = r2
                r12.I$0 = r1
                r12.label = r3
                java.lang.Object r13 = r8.send(r7, r12)
                if (r13 != r0) goto Lac
            Lab:
                return r0
            Lac:
                r7 = r8
                goto L58
            Lae:
                kotlin.Unit r13 = kotlin.Unit.INSTANCE
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00911.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00911) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1", f = "Deprecated.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00921 extends SuspendLambda implements Function2<Object, Continuation<? super Boolean>, Object> {
        public final /* synthetic */ Function2<Object, Continuation<? super Boolean>, Object> $predicate;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00921(Function2<Object, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C00921> continuation) {
            super(2, continuation);
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00921 c00921 = new C00921(this.$predicate, continuation);
            c00921.L$0 = obj;
            return c00921;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Function2<Object, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                this.label = 1;
                obj = function2.invoke(obj2, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boolean.valueOf(!((Boolean) obj).booleanValue());
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(Object obj, @Nullable Continuation<? super Boolean> continuation) {
            return ((C00921) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00931<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        public /* synthetic */ Object L$0;
        public int label;

        public C00931(Continuation<? super C00931> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00931 c00931 = new C00931(2, continuation);
            c00931.L$0 = obj;
            return c00931;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(this.L$0 != null);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@Nullable E e, @Nullable Continuation<? super Boolean> continuation) {
            return ((C00931) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    public static final class C00941<E, C extends Collection<? super E>> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C00941(Continuation<? super C00941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (Collection) null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 242}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    public static final class AnonymousClass3<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (SendChannel) null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {65}, m = "first", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    public static final class C00951<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C00951(Continuation<? super C00951> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.first(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {75}, m = "firstOrNull", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    public static final class C00961<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C00961(Continuation<? super C00961> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1", f = "Deprecated.kt", i = {0, 1, 2}, l = {321, 322, 322}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    public static final class C00971 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<Object> $this_flatMap;
        public final /* synthetic */ Function2<Object, Continuation<? super ReceiveChannel<Object>>, Object> $transform;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C00971(ReceiveChannel<Object> receiveChannel, Function2<Object, ? super Continuation<? super ReceiveChannel<Object>>, ? extends Object> function2, Continuation<? super C00971> continuation) {
            super(2, continuation);
            this.$this_flatMap = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C00971 c00971 = new C00971(this.$this_flatMap, this.$transform, continuation);
            c00971.L$0 = obj;
            return c00971;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0080  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007d -> B:13:0x0049). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L3b
                if (r1 == r4) goto L2f
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L49
            L1b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L23:
                java.lang.Object r1 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L71
            L2f:
                java.lang.Object r1 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L56
            L3b:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r7.$this_flatMap
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r5 = r8
            L49:
                r7.L$0 = r5
                r7.L$1 = r1
                r7.label = r4
                java.lang.Object r8 = r1.hasNext(r7)
                if (r8 != r0) goto L56
                goto L7f
            L56:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 == 0) goto L80
                java.lang.Object r8 = r1.next()
                kotlin.jvm.functions.Function2<java.lang.Object, kotlin.coroutines.Continuation<? super kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object>>, java.lang.Object> r6 = r7.$transform
                r7.L$0 = r5
                r7.L$1 = r1
                r7.label = r3
                java.lang.Object r8 = r6.invoke(r8, r7)
                if (r8 != r0) goto L71
                goto L7f
            L71:
                kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
                r7.L$0 = r5
                r7.L$1 = r1
                r7.label = r2
                java.lang.Object r8 = kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel(r8, r5, r7)
                if (r8 != r0) goto L49
            L7f:
                return r0
            L80:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00971.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C00971) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {487}, m = "indexOf", n = {"element", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2"})
    public static final class C00981<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public C00981(Continuation<? super C00981> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {97, 100}, m = "last", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    public static final class C00991<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C00991(Continuation<? super C00991> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.last(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 0}, l = {487}, m = "lastIndexOf", n = {"element", "lastIndex", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    public static final class C01001<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;
        public /* synthetic */ Object result;

        public C01001(Continuation<? super C01001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {123, 126}, m = "lastOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    public static final class C01011<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C01011(Continuation<? super C01011> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {487, 333, 333}, m = "invokeSuspend", n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
    public static final class C01021<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<E> $this_map;
        public final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01021(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C01021> continuation) {
            super(2, continuation);
            this.$this_map = receiveChannel;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01021 c01021 = new C01021(this.$this_map, this.$transform, continuation);
            c01021.L$0 = obj;
            return c01021;
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x008f A[Catch: all -> 0x0025, TRY_LEAVE, TryCatch #0 {all -> 0x0025, blocks: (B:8:0x0020, B:24:0x0073, B:28:0x0087, B:30:0x008f, B:20:0x005d, B:23:0x006c), top: B:44:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00c3  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00be -> B:24:0x0073). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 207
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01021.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super R> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01021) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {344, 345, 345}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    public static final class C01031<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<E> $this_mapIndexed;
        public final /* synthetic */ Function3<Integer, E, Continuation<? super R>, Object> $transform;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01031(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super C01031> continuation) {
            super(2, continuation);
            this.$this_mapIndexed = receiveChannel;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01031 c01031 = new C01031(this.$this_mapIndexed, this.$transform, continuation);
            c01031.L$0 = obj;
            return c01031;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a4 -> B:13:0x0056). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1<R> for r11v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                r11 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r11.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L46
                if (r1 == r4) goto L38
                if (r1 == r3) goto L26
                if (r1 != r2) goto L1e
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r12)
                r12 = r6
                goto L56
            L1e:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L26:
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$2
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                java.lang.Object r6 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
                java.lang.Object r7 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
                kotlin.ResultKt.throwOnFailure(r12)
                goto L92
            L38:
                int r1 = r11.I$0
                java.lang.Object r5 = r11.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r12)
                goto L68
            L46:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
                kotlinx.coroutines.channels.ReceiveChannel<E> r1 = r11.$this_mapIndexed
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r5 = 0
                r5 = r1
                r1 = 0
            L56:
                r11.L$0 = r12
                r11.L$1 = r5
                r11.I$0 = r1
                r11.label = r4
                java.lang.Object r6 = r5.hasNext(r11)
                if (r6 != r0) goto L65
                goto La3
            L65:
                r10 = r6
                r6 = r12
                r12 = r10
            L68:
                java.lang.Boolean r12 = (java.lang.Boolean) r12
                boolean r12 = r12.booleanValue()
                if (r12 == 0) goto La7
                java.lang.Object r12 = r5.next()
                kotlin.jvm.functions.Function3<java.lang.Integer, E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r7 = r11.$transform
                int r8 = r1 + 1
                java.lang.Integer r9 = new java.lang.Integer
                r9.<init>(r1)
                r11.L$0 = r6
                r11.L$1 = r5
                r11.L$2 = r6
                r11.I$0 = r8
                r11.label = r3
                java.lang.Object r12 = r7.invoke(r9, r12, r11)
                if (r12 != r0) goto L8e
                goto La3
            L8e:
                r7 = r6
                r1 = r8
                r6 = r5
                r5 = r7
            L92:
                r11.L$0 = r7
                r11.L$1 = r6
                r8 = 0
                r11.L$2 = r8
                r11.I$0 = r1
                r11.label = r2
                java.lang.Object r12 = r5.send(r12, r11)
                if (r12 != r0) goto La4
            La3:
                return r0
            La4:
                r5 = r6
                r12 = r7
                goto L56
            La7:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01031.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super R> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01031) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {UnixStat.DEFAULT_FILE_PERM, 422}, m = "maxWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "max"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    public static final class C01041<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public C01041(Continuation<? super C01041> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {434, 436}, m = "minWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    public static final class C01051<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public C01051(Continuation<? super C01051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.minWith(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {447}, m = "none", n = {"$this$consume$iv"}, s = {"L$0"})
    public static final class C01061<E> extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public C01061(Continuation<? super C01061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.none(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C01071 extends SuspendLambda implements Function2<Object, Continuation<Object>, Object> {
        public final /* synthetic */ ReceiveChannel<Object> $this_requireNoNulls;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01071(ReceiveChannel<Object> receiveChannel, Continuation<? super C01071> continuation) {
            super(2, continuation);
            this.$this_requireNoNulls = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01071 c01071 = new C01071(this.$this_requireNoNulls, continuation);
            c01071.L$0 = obj;
            return c01071;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            if (obj2 != null) {
                return obj2;
            }
            throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + '.');
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@Nullable Object obj, @Nullable Continuation<Object> continuation) {
            return ((C01071) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {136, 139}, m = "single", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    public static final class C01081<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C01081(Continuation<? super C01081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.single(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {149, 152}, m = "singleOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    public static final class C01091<E> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public C01091(Continuation<? super C01091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {GifHeaderParser.LABEL_COMMENT_EXTENSION, 255}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining"}, s = {"L$0", "I$0", "L$0", "I$0"})
    public static final class C01101 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $n;
        public final /* synthetic */ ReceiveChannel<Object> $this_take;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01101(int i, ReceiveChannel<Object> receiveChannel, Continuation<? super C01101> continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_take = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01101 c01101 = new C01101(this.$n, this.$this_take, continuation);
            c01101.L$0 = obj;
            return c01101;
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
        
            if (r5.send(r8, r7) == r0) goto L28;
         */
        /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0076 -> B:7:0x0019). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L31
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                int r1 = r7.I$0
                java.lang.Object r4 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r8)
            L19:
                r8 = r5
                goto L79
            L1b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L23:
                int r1 = r7.I$0
                java.lang.Object r4 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5e
            L31:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                int r1 = r7.$n
                if (r1 != 0) goto L3f
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L3f:
                if (r1 < 0) goto L43
                r4 = 1
                goto L44
            L43:
                r4 = 0
            L44:
                if (r4 == 0) goto L83
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r4 = r7.$this_take
                kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
            L4c:
                r7.L$0 = r8
                r7.L$1 = r4
                r7.I$0 = r1
                r7.label = r3
                java.lang.Object r5 = r4.hasNext(r7)
                if (r5 != r0) goto L5b
                goto L78
            L5b:
                r6 = r5
                r5 = r8
                r8 = r6
            L5e:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 == 0) goto L80
                java.lang.Object r8 = r4.next()
                r7.L$0 = r5
                r7.L$1 = r4
                r7.I$0 = r1
                r7.label = r2
                java.lang.Object r8 = r5.send(r8, r7)
                if (r8 != r0) goto L19
            L78:
                return r0
            L79:
                int r1 = r1 + (-1)
                if (r1 != 0) goto L4c
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L80:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L83:
                java.lang.String r8 = "Requested element count "
                java.lang.String r0 = " is less than zero."
                java.lang.String r8 = androidx.collection.ObjectListKt$$ExternalSyntheticOutline1.m(r8, r1, r0)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r8 = r8.toString()
                r0.<init>(r8)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01101.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01101) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {269, 270, 271}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    public static final class C01111 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function2<Object, Continuation<? super Boolean>, Object> $predicate;
        public final /* synthetic */ ReceiveChannel<Object> $this_takeWhile;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01111(ReceiveChannel<Object> receiveChannel, Function2<Object, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super C01111> continuation) {
            super(2, continuation);
            this.$this_takeWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01111 c01111 = new C01111(this.$this_takeWhile, this.$predicate, continuation);
            c01111.L$0 = obj;
            return c01111;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0060  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0082  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0098  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0095 -> B:13:0x004b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L3d
                if (r1 == r4) goto L31
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r9)
                goto L4b
            L1b:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L23:
                java.lang.Object r1 = r8.L$2
                java.lang.Object r5 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
                java.lang.Object r6 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
                kotlin.ResultKt.throwOnFailure(r9)
                goto L7a
            L31:
                java.lang.Object r1 = r8.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r5 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r9)
                goto L58
            L3d:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r8.$this_takeWhile
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r5 = r9
            L4b:
                r8.L$0 = r5
                r8.L$1 = r1
                r8.label = r4
                java.lang.Object r9 = r1.hasNext(r8)
                if (r9 != r0) goto L58
                goto L94
            L58:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto L98
                java.lang.Object r9 = r1.next()
                kotlin.jvm.functions.Function2<java.lang.Object, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r6 = r8.$predicate
                r8.L$0 = r5
                r8.L$1 = r1
                r8.L$2 = r9
                r8.label = r3
                java.lang.Object r6 = r6.invoke(r9, r8)
                if (r6 != r0) goto L75
                goto L94
            L75:
                r7 = r1
                r1 = r9
                r9 = r6
                r6 = r5
                r5 = r7
            L7a:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 != 0) goto L85
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L85:
                r8.L$0 = r6
                r8.L$1 = r5
                r9 = 0
                r8.L$2 = r9
                r8.label = r2
                java.lang.Object r9 = r6.send(r1, r8)
                if (r9 != r0) goto L95
            L94:
                return r0
            L95:
                r1 = r5
                r5 = r6
                goto L4b
            L98:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01111.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<Object> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01111) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 278}, m = "toChannel", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    public static final class C01121<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C01121(Continuation<? super C01121> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.toChannel(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toCollection", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    public static final class C01131<E, C extends Collection<? super E>> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public C01131(Continuation<? super C01131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.toCollection(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toMap", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    public static final class AnonymousClass2<K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.toMap(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {370, 371}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0"})
    public static final class C01141 extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<Object>>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<Object> $this_withIndex;
        public int I$0;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01141(ReceiveChannel<Object> receiveChannel, Continuation<? super C01141> continuation) {
            super(2, continuation);
            this.$this_withIndex = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01141 c01141 = new C01141(this.$this_withIndex, continuation);
            c01141.L$0 = obj;
            return c01141;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0079 -> B:7:0x001c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L34
                if (r1 == r3) goto L26
                if (r1 != r2) goto L1e
                int r1 = r9.I$0
                java.lang.Object r4 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = r4
                r4 = r1
                r1 = r10
            L1c:
                r10 = r5
                goto L42
            L1e:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L26:
                int r1 = r9.I$0
                java.lang.Object r4 = r9.L$1
                kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
                java.lang.Object r5 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
                kotlin.ResultKt.throwOnFailure(r10)
                goto L57
            L34:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
                kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r9.$this_withIndex
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
                r4 = 0
            L42:
                r9.L$0 = r10
                r9.L$1 = r1
                r9.I$0 = r4
                r9.label = r3
                java.lang.Object r5 = r1.hasNext(r9)
                if (r5 != r0) goto L51
                goto L78
            L51:
                r8 = r5
                r5 = r10
                r10 = r8
                r8 = r4
                r4 = r1
                r1 = r8
            L57:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto L7c
                java.lang.Object r10 = r4.next()
                kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue
                int r7 = r1 + 1
                r6.<init>(r1, r10)
                r9.L$0 = r5
                r9.L$1 = r4
                r9.I$0 = r7
                r9.label = r2
                java.lang.Object r10 = r5.send(r6, r9)
                if (r10 != r0) goto L79
            L78:
                return r0
            L79:
                r1 = r4
                r4 = r7
                goto L1c
            L7c:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01141.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super IndexedValue<Object>> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01141) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C01151 extends Lambda implements Function2<Object, Object, Pair<Object, Object>> {
        public static final C01151 INSTANCE = new C01151(2);

        public C01151() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public Pair<Object, Object> invoke(Object obj, Object obj2) {
            return new Pair<>(obj, obj2);
        }

        @Override // kotlin.jvm.functions.Function2
        @NotNull
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Pair<Object, Object> invoke2(Object obj, Object obj2) {
            return new Pair<>(obj, obj2);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V] */
    /* JADX INFO: renamed from: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {487, 469, 471}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
    public static final class C01162<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<R> $other;
        public final /* synthetic */ ReceiveChannel<E> $this_zip;
        public final /* synthetic */ Function2<E, R, V> $transform;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01162(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super C01162> continuation) {
            super(2, continuation);
            this.$other = receiveChannel;
            this.$this_zip = receiveChannel2;
            this.$transform = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            C01162 c01162 = new C01162(this.$other, this.$this_zip, this.$transform, continuation);
            c01162.L$0 = obj;
            return c01162;
        }

        /* JADX WARN: Code restructure failed: missing block: B:51:0x0086, code lost:
        
            r6 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
         */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00a3 A[Catch: all -> 0x0028, TRY_LEAVE, TryCatch #2 {all -> 0x0028, blocks: (B:8:0x0024, B:24:0x0086, B:27:0x009b, B:29:0x00a3, B:20:0x0069, B:23:0x007e), top: B:49:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00cb A[Catch: all -> 0x0051, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:33:0x00c3, B:35:0x00cb, B:15:0x0049), top: B:45:0x0049 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00ed  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 249
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01162.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super V> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C01162) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object any(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) throws java.lang.Throwable {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$any$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L2b
            goto L47
        L2b:
            r5 = move-exception
            goto L4c
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch: java.lang.Throwable -> L2b
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L2b
            r0.label = r3     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r5 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L2b
            if (r5 != r1) goto L47
            return r1
        L47:
            r0 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r0)
            return r5
        L4c:
            throw r5     // Catch: java.lang.Throwable -> L4d
        L4d:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        return new C00811(receiveChannel);
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        return new C00821(receiveChannelArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #1 {all -> 0x0033, blocks: (B:12:0x002f, B:25:0x005e, B:27:0x0066), top: B:41:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005b -> B:25:0x005e). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object count(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00831
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$count$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L33
            goto L5e
        L33:
            r6 = move-exception
            goto L82
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef
            r7.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch: java.lang.Throwable -> L7f
            r4 = r7
            r7 = r6
            r6 = r2
        L4c:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L7c
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L7c
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L7c
            r0.label = r3     // Catch: java.lang.Throwable -> L7c
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L7c
            if (r2 != r1) goto L5b
            return r1
        L5b:
            r5 = r2
            r2 = r7
            r7 = r5
        L5e:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L33
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r7 == 0) goto L70
            r6.next()     // Catch: java.lang.Throwable -> L33
            int r7 = r4.element     // Catch: java.lang.Throwable -> L33
            int r7 = r7 + r3
            r4.element = r7     // Catch: java.lang.Throwable -> L33
            r7 = r2
            goto L4c
        L70:
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r6)
            int r6 = r4.element
            java.lang.Integer r7 = new java.lang.Integer
            r7.<init>(r6)
            return r7
        L7c:
            r6 = move-exception
            r2 = r7
            goto L82
        L7f:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L82:
            throw r6     // Catch: java.lang.Throwable -> L83
        L83:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel distinct(ReceiveChannel receiveChannel) {
        return distinctBy$default(receiveChannel, null, new C00841(2, null), 1, null);
    }

    @PublishedApi
    @NotNull
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00851(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return distinctBy(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel drop(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00861(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return drop(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel dropWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00871(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return dropWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0062, B:27:0x006a, B:33:0x007b, B:34:0x0092), top: B:44:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[Catch: all -> 0x0037, TRY_ENTER, TryCatch #1 {all -> 0x0037, blocks: (B:12:0x0033, B:25:0x0062, B:27:0x006a, B:33:0x007b, B:34:0x0092), top: B:44:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x005d -> B:25:0x0062). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object elementAt(kotlinx.coroutines.channels.ReceiveChannel r9, int r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00881
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00881) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAt$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 46
            r4 = 1
            java.lang.String r5 = "ReceiveChannel doesn't contain element at index "
            if (r2 == 0) goto L42
            if (r2 != r4) goto L3a
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L37
            goto L62
        L37:
            r9 = move-exception
            goto Lac
        L3a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L42:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 < 0) goto L97
            kotlinx.coroutines.channels.ChannelIterator r11 = r9.iterator()     // Catch: java.lang.Throwable -> L93
            r2 = 0
        L4c:
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L93
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L93
            r0.I$0 = r10     // Catch: java.lang.Throwable -> L93
            r0.I$1 = r2     // Catch: java.lang.Throwable -> L93
            r0.label = r4     // Catch: java.lang.Throwable -> L93
            java.lang.Object r6 = r11.hasNext(r0)     // Catch: java.lang.Throwable -> L93
            if (r6 != r1) goto L5d
            return r1
        L5d:
            r8 = r6
            r6 = r9
            r9 = r2
            r2 = r11
            r11 = r8
        L62:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.lang.Throwable -> L37
            boolean r11 = r11.booleanValue()     // Catch: java.lang.Throwable -> L37
            if (r11 == 0) goto L7b
            java.lang.Object r11 = r2.next()     // Catch: java.lang.Throwable -> L37
            int r7 = r9 + 1
            if (r10 != r9) goto L77
            r9 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r9)
            return r11
        L77:
            r11 = r2
            r9 = r6
            r2 = r7
            goto L4c
        L7b:
            java.lang.IndexOutOfBoundsException r9 = new java.lang.IndexOutOfBoundsException     // Catch: java.lang.Throwable -> L37
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L37
            r11.<init>()     // Catch: java.lang.Throwable -> L37
            r11.append(r5)     // Catch: java.lang.Throwable -> L37
            r11.append(r10)     // Catch: java.lang.Throwable -> L37
            r11.append(r3)     // Catch: java.lang.Throwable -> L37
            java.lang.String r10 = r11.toString()     // Catch: java.lang.Throwable -> L37
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L37
            throw r9     // Catch: java.lang.Throwable -> L37
        L93:
            r10 = move-exception
            r6 = r9
            r9 = r10
            goto Lac
        L97:
            java.lang.IndexOutOfBoundsException r11 = new java.lang.IndexOutOfBoundsException     // Catch: java.lang.Throwable -> L93
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L93
            r0.append(r10)     // Catch: java.lang.Throwable -> L93
            r0.append(r3)     // Catch: java.lang.Throwable -> L93
            java.lang.String r10 = r0.toString()     // Catch: java.lang.Throwable -> L93
            r11.<init>(r10)     // Catch: java.lang.Throwable -> L93
            throw r11     // Catch: java.lang.Throwable -> L93
        Lac:
            throw r9     // Catch: java.lang.Throwable -> Lad
        Lad:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: all -> 0x007e, TRY_LEAVE, TryCatch #2 {all -> 0x007e, blocks: (B:27:0x0066, B:29:0x006e, B:23:0x0051, B:22:0x004c), top: B:47:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0062 -> B:27:0x0066). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel r8, int r9, kotlin.coroutines.Continuation r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00891
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00891) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$elementAtOrNull$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L39
            r7 = r2
            r2 = r8
            r8 = r5
            r5 = r0
            r0 = r7
            goto L66
        L39:
            r8 = move-exception
            goto L86
        L3b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L43:
            kotlin.ResultKt.throwOnFailure(r10)
            if (r9 >= 0) goto L4c
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r4)
            return r4
        L4c:
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch: java.lang.Throwable -> L7e
            r2 = 0
        L51:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L7e
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L7e
            r0.I$0 = r9     // Catch: java.lang.Throwable -> L7e
            r0.I$1 = r2     // Catch: java.lang.Throwable -> L7e
            r0.label = r3     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r5 = r10.hasNext(r0)     // Catch: java.lang.Throwable -> L7e
            if (r5 != r1) goto L62
            return r1
        L62:
            r7 = r0
            r0 = r10
            r10 = r5
            r5 = r7
        L66:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L7e
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L7e
            if (r10 == 0) goto L82
            java.lang.Object r10 = r0.next()     // Catch: java.lang.Throwable -> L7e
            int r6 = r2 + 1
            if (r9 != r2) goto L7a
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r4)
            return r10
        L7a:
            r10 = r0
            r0 = r5
            r2 = r6
            goto L51
        L7e:
            r9 = move-exception
            r5 = r8
            r8 = r9
            goto L86
        L82:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r4)
            return r4
        L86:
            throw r8     // Catch: java.lang.Throwable -> L87
        L87:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r5, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00901(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filter(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterIndexed(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00911(receiveChannel, function3, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterNot(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return filter(receiveChannel, coroutineContext, new C00921(function2, null));
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterNot(receiveChannel, coroutineContext, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return filter$default(receiveChannel, null, new C00931(2, null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0056 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[Catch: all -> 0x0033, TryCatch #1 {all -> 0x0033, blocks: (B:12:0x002f, B:25:0x005a, B:27:0x0062, B:29:0x0068, B:21:0x0048), top: B:41:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0057 -> B:25:0x005a). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Collection r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00941
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00941) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L33
            goto L5a
        L33:
            r5 = move-exception
            goto L76
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r5.iterator()     // Catch: java.lang.Throwable -> L72
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L48:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L33
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L33
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L33
            r0.label = r3     // Catch: java.lang.Throwable -> L33
            java.lang.Object r2 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L33
            if (r2 != r1) goto L57
            return r1
        L57:
            r4 = r2
            r2 = r7
            r7 = r4
        L5a:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L33
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r7 == 0) goto L6d
            java.lang.Object r7 = r5.next()     // Catch: java.lang.Throwable -> L33
            if (r7 == 0) goto L6b
            r2.add(r7)     // Catch: java.lang.Throwable -> L33
        L6b:
            r7 = r2
            goto L48
        L6d:
            r5 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r5)
            return r2
        L72:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L76:
            throw r5     // Catch: java.lang.Throwable -> L77
        L77:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0059 A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #2 {all -> 0x002f, blocks: (B:12:0x002b, B:23:0x0051, B:25:0x0059, B:28:0x0062, B:29:0x0069), top: B:40:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062 A[Catch: all -> 0x002f, TRY_ENTER, TryCatch #2 {all -> 0x002f, blocks: (B:12:0x002b, B:23:0x0051, B:25:0x0059, B:28:0x0062, B:29:0x0069), top: B:40:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object first(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00951
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$first$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2f
            goto L51
        L2f:
            r5 = move-exception
            goto L6d
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch: java.lang.Throwable -> L6a
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L6a
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L6a
            r0.label = r3     // Catch: java.lang.Throwable -> L6a
            java.lang.Object r0 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L6a
            if (r0 != r1) goto L4d
            return r1
        L4d:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L51:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L2f
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L2f
            if (r6 == 0) goto L62
            java.lang.Object r5 = r5.next()     // Catch: java.lang.Throwable -> L2f
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r6)
            return r5
        L62:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L2f
            java.lang.String r6 = "ReceiveChannel is empty."
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L2f
            throw r5     // Catch: java.lang.Throwable -> L2f
        L6a:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L6d:
            throw r5     // Catch: java.lang.Throwable -> L6e
        L6e:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object firstOrNull(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00961
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$firstOrNull$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2f
            goto L51
        L2f:
            r5 = move-exception
            goto L69
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.ChannelIterator r6 = r5.iterator()     // Catch: java.lang.Throwable -> L66
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L66
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L66
            r0.label = r3     // Catch: java.lang.Throwable -> L66
            java.lang.Object r0 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L66
            if (r0 != r1) goto L4d
            return r1
        L4d:
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
        L51:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L2f
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L2f
            r1 = 0
            if (r6 != 0) goto L5e
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r1)
            return r1
        L5e:
            java.lang.Object r5 = r5.next()     // Catch: java.lang.Throwable -> L2f
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r1)
            return r5
        L66:
            r6 = move-exception
            r0 = r5
            r5 = r6
        L69:
            throw r5     // Catch: java.lang.Throwable -> L6a
        L6a:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel flatMap(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C00971(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return flatMap(receiveChannel, coroutineContext, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:12:0x0031, B:25:0x0065, B:27:0x006e, B:29:0x0078, B:32:0x0083, B:21:0x0051), top: B:44:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0062 -> B:25:0x0065). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object indexOf(kotlinx.coroutines.channels.ReceiveChannel r7, java.lang.Object r8, kotlin.coroutines.Continuation r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00981
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00981) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$indexOf$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            goto L65
        L35:
            r7 = move-exception
            goto L98
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L40:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
            r9.<init>()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch: java.lang.Throwable -> L94
            r6 = r8
            r8 = r7
            r7 = r2
            r2 = r9
            r9 = r6
        L51:
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L35
            r0.L$3 = r7     // Catch: java.lang.Throwable -> L35
            r0.label = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r4 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L35
            if (r4 != r1) goto L62
            return r1
        L62:
            r6 = r4
            r4 = r9
            r9 = r6
        L65:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L35
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L35
            r5 = 0
            if (r9 == 0) goto L8a
            java.lang.Object r9 = r7.next()     // Catch: java.lang.Throwable -> L35
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r9)     // Catch: java.lang.Throwable -> L35
            if (r9 == 0) goto L83
            int r7 = r2.element     // Catch: java.lang.Throwable -> L35
            java.lang.Integer r9 = new java.lang.Integer     // Catch: java.lang.Throwable -> L35
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L35
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r5)
            return r9
        L83:
            int r9 = r2.element     // Catch: java.lang.Throwable -> L35
            int r9 = r9 + r3
            r2.element = r9     // Catch: java.lang.Throwable -> L35
            r9 = r4
            goto L51
        L8a:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r5)
            java.lang.Integer r7 = new java.lang.Integer
            r8 = -1
            r7.<init>(r8)
            return r7
        L94:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L98:
            throw r7     // Catch: java.lang.Throwable -> L99
        L99:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091 A[Catch: all -> 0x0034, TRY_LEAVE, TryCatch #0 {all -> 0x0034, blocks: (B:13:0x0030, B:37:0x0089, B:39:0x0091), top: B:51:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0085 -> B:37:0x0089). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object last(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00991
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C00991) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$last$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4f
            if (r2 == r4) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r6 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L34
            goto L89
        L34:
            r6 = move-exception
            r2 = r4
            goto La8
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L4c
            goto L67
        L4c:
            r6 = move-exception
            goto La8
        L4f:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch: java.lang.Throwable -> L9c
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L9c
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9c
            r0.label = r4     // Catch: java.lang.Throwable -> L9c
            java.lang.Object r2 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L9c
            if (r2 != r1) goto L63
            goto L84
        L63:
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
        L67:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L4c
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L4c
            if (r7 == 0) goto La0
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> L4c
            r5 = r2
            r2 = r6
            r6 = r5
        L76:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L9c
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L9c
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L9c
            r0.label = r3     // Catch: java.lang.Throwable -> L9c
            java.lang.Object r4 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L9c
            if (r4 != r1) goto L85
        L84:
            return r1
        L85:
            r5 = r4
            r4 = r6
            r6 = r7
            r7 = r5
        L89:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L34
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L34
            if (r7 == 0) goto L97
            java.lang.Object r7 = r2.next()     // Catch: java.lang.Throwable -> L34
            r6 = r4
            goto L76
        L97:
            r7 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r7)
            return r6
        L9c:
            r7 = move-exception
            r2 = r6
            r6 = r7
            goto La8
        La0:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = "ReceiveChannel is empty."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L4c
            throw r6     // Catch: java.lang.Throwable -> L4c
        La8:
            throw r6     // Catch: java.lang.Throwable -> La9
        La9:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b A[Catch: all -> 0x0039, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:25:0x0073, B:27:0x007b, B:29:0x0085, B:30:0x0089, B:21:0x005d), top: B:42:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0070 -> B:25:0x0073). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel r7, java.lang.Object r8, kotlin.coroutines.Continuation r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01001
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastIndexOf$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r7 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            java.lang.Object r5 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L39
            goto L73
        L39:
            r7 = move-exception
            goto La0
        L3c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L44:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
            r9.<init>()
            r2 = -1
            r9.element = r2
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            kotlinx.coroutines.channels.ChannelIterator r4 = r7.iterator()     // Catch: java.lang.Throwable -> L9c
            r6 = r8
            r8 = r7
            r7 = r4
            r4 = r9
            r9 = r6
        L5d:
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L39
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L39
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L39
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L39
            r0.L$4 = r7     // Catch: java.lang.Throwable -> L39
            r0.label = r3     // Catch: java.lang.Throwable -> L39
            java.lang.Object r5 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L39
            if (r5 != r1) goto L70
            return r1
        L70:
            r6 = r5
            r5 = r9
            r9 = r6
        L73:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L39
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L39
            if (r9 == 0) goto L90
            java.lang.Object r9 = r7.next()     // Catch: java.lang.Throwable -> L39
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)     // Catch: java.lang.Throwable -> L39
            if (r9 == 0) goto L89
            int r9 = r2.element     // Catch: java.lang.Throwable -> L39
            r4.element = r9     // Catch: java.lang.Throwable -> L39
        L89:
            int r9 = r2.element     // Catch: java.lang.Throwable -> L39
            int r9 = r9 + r3
            r2.element = r9     // Catch: java.lang.Throwable -> L39
            r9 = r5
            goto L5d
        L90:
            r7 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r7)
            int r7 = r4.element
            java.lang.Integer r8 = new java.lang.Integer
            r8.<init>(r7)
            return r8
        L9c:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        La0:
            throw r7     // Catch: java.lang.Throwable -> La1
        La1:
            r9 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0095 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x0031, B:39:0x008d, B:41:0x0095), top: B:53:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0089 -> B:39:0x008d). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object lastOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01011
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$lastOrNull$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L4f
            if (r2 == r4) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L35
            goto L8d
        L35:
            r7 = move-exception
            r2 = r4
            goto La2
        L39:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L41:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L4d
            goto L67
        L4d:
            r7 = move-exception
            goto La2
        L4f:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch: java.lang.Throwable -> L9f
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L9f
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L9f
            r0.label = r4     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L9f
            if (r2 != r1) goto L63
            goto L88
        L63:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L67:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L4d
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L4d
            if (r8 != 0) goto L73
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r5)
            return r5
        L73:
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L4d
            r6 = r2
            r2 = r7
            r7 = r6
        L7a:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L9f
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L9f
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L9f
            r0.label = r3     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r4 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L9f
            if (r4 != r1) goto L89
        L88:
            return r1
        L89:
            r6 = r4
            r4 = r7
            r7 = r8
            r8 = r6
        L8d:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L35
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r8 == 0) goto L9b
            java.lang.Object r8 = r2.next()     // Catch: java.lang.Throwable -> L35
            r7 = r4
            goto L7a
        L9b:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r5)
            return r7
        L9f:
            r8 = move-exception
            r2 = r7
            r7 = r8
        La2:
            throw r7     // Catch: java.lang.Throwable -> La3
        La3:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C01021(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return map(receiveChannel, coroutineContext, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext coroutineContext, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C01031(receiveChannel, function3, null), 6, null);
    }

    public static ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return filterNotNull(mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return filterNotNull(map(receiveChannel, coroutineContext, function2));
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapNotNull(receiveChannel, coroutineContext, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a9 A[Catch: all -> 0x00b7, TRY_LEAVE, TryCatch #1 {all -> 0x00b7, blocks: (B:40:0x00a1, B:42:0x00a9, B:36:0x008c, B:26:0x0060), top: B:56:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x009d -> B:15:0x003b). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object maxWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01041
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01041) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$maxWith$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L5d
            if (r2 == r4) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r8 = r0.L$3
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            java.util.Comparator r4 = (java.util.Comparator) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3e
            r7 = r0
            r0 = r8
            r8 = r2
        L3b:
            r2 = r7
            goto La1
        L3e:
            r8 = move-exception
            r9 = r2
            goto Lc0
        L42:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4a:
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$0
            java.util.Comparator r2 = (java.util.Comparator) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L5a
            goto L78
        L5a:
            r8 = move-exception
            goto Lc0
        L5d:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch: java.lang.Throwable -> Lb7
            r0.L$0 = r9     // Catch: java.lang.Throwable -> Lb7
            r0.L$1 = r8     // Catch: java.lang.Throwable -> Lb7
            r0.L$2 = r10     // Catch: java.lang.Throwable -> Lb7
            r0.label = r4     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r2 = r10.hasNext(r0)     // Catch: java.lang.Throwable -> Lb7
            if (r2 != r1) goto L73
            goto L9c
        L73:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r2
            r2 = r7
        L78:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L5a
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L5a
            if (r10 != 0) goto L84
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r9, r5)
            return r5
        L84:
            java.lang.Object r10 = r8.next()     // Catch: java.lang.Throwable -> L5a
            r4 = r9
            r9 = r8
            r8 = r4
            r4 = r2
        L8c:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> Lb7
            r0.L$1 = r8     // Catch: java.lang.Throwable -> Lb7
            r0.L$2 = r9     // Catch: java.lang.Throwable -> Lb7
            r0.L$3 = r10     // Catch: java.lang.Throwable -> Lb7
            r0.label = r3     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r2 = r9.hasNext(r0)     // Catch: java.lang.Throwable -> Lb7
            if (r2 != r1) goto L9d
        L9c:
            return r1
        L9d:
            r7 = r0
            r0 = r10
            r10 = r2
            goto L3b
        La1:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> Lb7
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> Lb7
            if (r10 == 0) goto Lbc
            java.lang.Object r10 = r9.next()     // Catch: java.lang.Throwable -> Lb7
            int r6 = r4.compare(r0, r10)     // Catch: java.lang.Throwable -> Lb7
            if (r6 >= 0) goto Lb5
        Lb3:
            r0 = r2
            goto L8c
        Lb5:
            r10 = r0
            goto Lb3
        Lb7:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto Lc0
        Lbc:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r5)
            return r0
        Lc0:
            throw r8     // Catch: java.lang.Throwable -> Lc1
        Lc1:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a9 A[Catch: all -> 0x00b7, TRY_LEAVE, TryCatch #1 {all -> 0x00b7, blocks: (B:40:0x00a1, B:42:0x00a9, B:36:0x008c, B:26:0x0060), top: B:56:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x009d -> B:15:0x003b). Please report as a decompilation issue!!! */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object minWith(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Comparator r9, kotlin.coroutines.Continuation r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01051
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01051) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$minWith$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L5d
            if (r2 == r4) goto L4a
            if (r2 != r3) goto L42
            java.lang.Object r8 = r0.L$3
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$0
            java.util.Comparator r4 = (java.util.Comparator) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3e
            r7 = r0
            r0 = r8
            r8 = r2
        L3b:
            r2 = r7
            goto La1
        L3e:
            r8 = move-exception
            r9 = r2
            goto Lc0
        L42:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4a:
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r2 = r0.L$0
            java.util.Comparator r2 = (java.util.Comparator) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L5a
            goto L78
        L5a:
            r8 = move-exception
            goto Lc0
        L5d:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ChannelIterator r10 = r8.iterator()     // Catch: java.lang.Throwable -> Lb7
            r0.L$0 = r9     // Catch: java.lang.Throwable -> Lb7
            r0.L$1 = r8     // Catch: java.lang.Throwable -> Lb7
            r0.L$2 = r10     // Catch: java.lang.Throwable -> Lb7
            r0.label = r4     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r2 = r10.hasNext(r0)     // Catch: java.lang.Throwable -> Lb7
            if (r2 != r1) goto L73
            goto L9c
        L73:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r2
            r2 = r7
        L78:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L5a
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L5a
            if (r10 != 0) goto L84
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r9, r5)
            return r5
        L84:
            java.lang.Object r10 = r8.next()     // Catch: java.lang.Throwable -> L5a
            r4 = r9
            r9 = r8
            r8 = r4
            r4 = r2
        L8c:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> Lb7
            r0.L$1 = r8     // Catch: java.lang.Throwable -> Lb7
            r0.L$2 = r9     // Catch: java.lang.Throwable -> Lb7
            r0.L$3 = r10     // Catch: java.lang.Throwable -> Lb7
            r0.label = r3     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r2 = r9.hasNext(r0)     // Catch: java.lang.Throwable -> Lb7
            if (r2 != r1) goto L9d
        L9c:
            return r1
        L9d:
            r7 = r0
            r0 = r10
            r10 = r2
            goto L3b
        La1:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> Lb7
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> Lb7
            if (r10 == 0) goto Lbc
            java.lang.Object r10 = r9.next()     // Catch: java.lang.Throwable -> Lb7
            int r6 = r4.compare(r0, r10)     // Catch: java.lang.Throwable -> Lb7
            if (r6 <= 0) goto Lb5
        Lb3:
            r0 = r2
            goto L8c
        Lb5:
            r10 = r0
            goto Lb3
        Lb7:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto Lc0
        Lbc:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r8, r5)
            return r0
        Lc0:
            throw r8     // Catch: java.lang.Throwable -> Lc1
        Lc1:
            r10 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r9, r8)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object none(kotlinx.coroutines.channels.ReceiveChannel r4, kotlin.coroutines.Continuation r5) throws java.lang.Throwable {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01061
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$none$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L2b
            goto L47
        L2b:
            r5 = move-exception
            goto L57
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.ChannelIterator r5 = r4.iterator()     // Catch: java.lang.Throwable -> L2b
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L2b
            r0.label = r3     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r5 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L2b
            if (r5 != r1) goto L47
            return r1
        L47:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L2b
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L2b
            r5 = r5 ^ r3
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L2b
            r0 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r0)
            return r5
        L57:
            throw r5     // Catch: java.lang.Throwable -> L58
        L58:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006a A[Catch: all -> 0x0048, TRY_LEAVE, TryCatch #2 {all -> 0x0048, blocks: (B:20:0x0044, B:29:0x0062, B:31:0x006a, B:41:0x0094, B:42:0x009b), top: B:53:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008c A[Catch: all -> 0x0030, TRY_ENTER, TryCatch #1 {all -> 0x0030, blocks: (B:13:0x002c, B:35:0x007f, B:39:0x008c, B:40:0x0093), top: B:51:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0094 A[Catch: all -> 0x0048, TRY_ENTER, TryCatch #2 {all -> 0x0048, blocks: (B:20:0x0044, B:29:0x0062, B:31:0x006a, B:41:0x0094, B:42:0x009b), top: B:53:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object single(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01081
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01081) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$single$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r6 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L30
            goto L7f
        L30:
            r6 = move-exception
            r2 = r0
            goto L9f
        L34:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3c:
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L48
            goto L62
        L48:
            r6 = move-exception
            goto L9f
        L4a:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r6.iterator()     // Catch: java.lang.Throwable -> L9c
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L9c
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L9c
            r0.label = r4     // Catch: java.lang.Throwable -> L9c
            java.lang.Object r2 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L9c
            if (r2 != r1) goto L5e
            goto L7a
        L5e:
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
        L62:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L48
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L48
            if (r7 == 0) goto L94
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> L48
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L48
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L48
            r0.label = r3     // Catch: java.lang.Throwable -> L48
            java.lang.Object r6 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L48
            if (r6 != r1) goto L7b
        L7a:
            return r1
        L7b:
            r0 = r7
            r7 = r6
            r6 = r0
            r0 = r2
        L7f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L30
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L30
            if (r7 != 0) goto L8c
            r7 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r7)
            return r6
        L8c:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L30
            java.lang.String r7 = "ReceiveChannel has more than one element."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L30
            throw r6     // Catch: java.lang.Throwable -> L30
        L94:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException     // Catch: java.lang.Throwable -> L48
            java.lang.String r7 = "ReceiveChannel is empty."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L48
            throw r6     // Catch: java.lang.Throwable -> L48
        L9c:
            r7 = move-exception
            r2 = r6
            r6 = r7
        L9f:
            throw r6     // Catch: java.lang.Throwable -> La0
        La0:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object singleOrNull(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01091
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01091) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$singleOrNull$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L4b
            if (r2 == r4) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r7 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L31
            goto L84
        L31:
            r7 = move-exception
            r2 = r0
            goto L97
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3d:
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L49
            goto L63
        L49:
            r7 = move-exception
            goto L97
        L4b:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r7.iterator()     // Catch: java.lang.Throwable -> L94
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L94
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L94
            r0.label = r4     // Catch: java.lang.Throwable -> L94
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L94
            if (r2 != r1) goto L5f
            goto L7f
        L5f:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L63:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L49
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L49
            if (r8 != 0) goto L6f
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r5)
            return r5
        L6f:
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L49
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L49
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L49
            r0.label = r3     // Catch: java.lang.Throwable -> L49
            java.lang.Object r7 = r7.hasNext(r0)     // Catch: java.lang.Throwable -> L49
            if (r7 != r1) goto L80
        L7f:
            return r1
        L80:
            r0 = r8
            r8 = r7
            r7 = r0
            r0 = r2
        L84:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L31
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L31
            if (r8 == 0) goto L90
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r5)
            return r5
        L90:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r0, r5)
            return r7
        L94:
            r8 = move-exception
            r2 = r7
            r7 = r8
        L97:
            throw r7     // Catch: java.lang.Throwable -> L98
        L98:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r2, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel take(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C01101(i, receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return take(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel takeWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C01111(receiveChannel, function2, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return takeWhile(receiveChannel, coroutineContext, function2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0086, code lost:
    
        if (r8 == r1) goto L32;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #1 {all -> 0x0039, blocks: (B:13:0x0032, B:28:0x006e, B:30:0x0076, B:20:0x004f), top: B:43:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r7v0, types: [C extends kotlinx.coroutines.channels.SendChannel<? super E>] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0086 -> B:14:0x0035). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r6, @org.jetbrains.annotations.NotNull C r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01121
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01121) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toChannel$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L53
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L39
        L35:
            r8 = r6
            r6 = r7
            r7 = r2
            goto L5a
        L39:
            r6 = move-exception
            goto L92
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L43:
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L39
            goto L6e
        L53:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L8e
        L5a:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L8e
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L8e
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L8e
            r0.label = r4     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L8e
            if (r2 != r1) goto L69
            goto L88
        L69:
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r2
            r2 = r5
        L6e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L39
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L39
            if (r8 == 0) goto L89
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L39
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L39
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L39
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L39
            r0.label = r3     // Catch: java.lang.Throwable -> L39
            java.lang.Object r8 = r2.send(r8, r0)     // Catch: java.lang.Throwable -> L39
            if (r8 != r1) goto L35
        L88:
            return r1
        L89:
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
            return r2
        L8e:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L92:
            throw r6     // Catch: java.lang.Throwable -> L93
        L93:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0056 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #1 {all -> 0x0033, blocks: (B:12:0x002f, B:25:0x005a, B:27:0x0062, B:21:0x0048), top: B:39:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0057 -> B:25:0x005a). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull C r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super C> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01131
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.C01131) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toCollection$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r2 = r0.L$0
            java.util.Collection r2 = (java.util.Collection) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L33
            goto L5a
        L33:
            r5 = move-exception
            goto L74
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r5.iterator()     // Catch: java.lang.Throwable -> L70
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L48:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L33
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L33
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L33
            r0.label = r3     // Catch: java.lang.Throwable -> L33
            java.lang.Object r2 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L33
            if (r2 != r1) goto L57
            return r1
        L57:
            r4 = r2
            r2 = r7
            r7 = r4
        L5a:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L33
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r7 == 0) goto L6b
            java.lang.Object r7 = r5.next()     // Catch: java.lang.Throwable -> L33
            r2.add(r7)     // Catch: java.lang.Throwable -> L33
            r7 = r2
            goto L48
        L6b:
            r5 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r5)
            return r2
        L70:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L74:
            throw r5     // Catch: java.lang.Throwable -> L75
        L75:
            r7 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0056 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #0 {all -> 0x0033, blocks: (B:12:0x002f, B:25:0x005a, B:27:0x0062, B:21:0x0048), top: B:37:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0057 -> B:25:0x005a). Please report as a decompilation issue!!! */
    @kotlin.PublishedApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r6, @org.jetbrains.annotations.NotNull M r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super M> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$toMap$2
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            java.util.Map r2 = (java.util.Map) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L33
            goto L5a
        L33:
            r6 = move-exception
            goto L7a
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L76
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L48:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L33
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L33
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L33
            r0.label = r3     // Catch: java.lang.Throwable -> L33
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L33
            if (r2 != r1) goto L57
            return r1
        L57:
            r5 = r2
            r2 = r8
            r8 = r5
        L5a:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L33
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r8 == 0) goto L71
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L33
            kotlin.Pair r8 = (kotlin.Pair) r8     // Catch: java.lang.Throwable -> L33
            A r4 = r8.first     // Catch: java.lang.Throwable -> L33
            B r8 = r8.second     // Catch: java.lang.Throwable -> L33
            r2.put(r4, r8)     // Catch: java.lang.Throwable -> L33
            r8 = r2
            goto L48
        L71:
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
            return r2
        L76:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L7a:
            throw r6     // Catch: java.lang.Throwable -> L7b
        L7b:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMutableList(ReceiveChannel receiveChannel, Continuation continuation) {
        return toCollection(receiveChannel, new ArrayList(), continuation);
    }

    @PublishedApi
    @Nullable
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toSet(ReceiveChannel receiveChannel, Continuation continuation) {
        return toMutableSet(receiveChannel, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel withIndex(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00811(receiveChannel), new C01141(receiveChannel, null), 6, null);
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return withIndex(receiveChannel, coroutineContext);
    }

    public static ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext coroutineContext, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, new C00821(new ReceiveChannel[]{receiveChannel, receiveChannel2}), new C01162(receiveChannel2, receiveChannel, function2, null), 6, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0060, code lost:
    
        r8 = r0;
        r0 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b A[Catch: all -> 0x0090, TryCatch #0 {all -> 0x0090, blocks: (B:25:0x0060, B:29:0x0073, B:31:0x007b, B:33:0x0081, B:24:0x005c), top: B:45:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r7v0, types: [kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Object, kotlinx.coroutines.channels.SendChannel] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel r6, kotlinx.coroutines.channels.SendChannel r7, kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNullTo$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L59
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L39
            r8 = r6
            r6 = r7
            r7 = r2
            goto L60
        L39:
            r6 = move-exception
            goto L9d
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L39
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r2
        L57:
            r2 = r5
            goto L73
        L59:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L90
        L60:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L90
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L90
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L90
            r0.label = r4     // Catch: java.lang.Throwable -> L90
            java.lang.Object r2 = r8.hasNext(r0)     // Catch: java.lang.Throwable -> L90
            if (r2 != r1) goto L6f
            goto L8f
        L6f:
            r5 = r0
            r0 = r8
            r8 = r2
            goto L57
        L73:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L90
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L90
            if (r8 == 0) goto L98
            java.lang.Object r8 = r0.next()     // Catch: java.lang.Throwable -> L90
            if (r8 == 0) goto L95
            r2.L$0 = r7     // Catch: java.lang.Throwable -> L90
            r2.L$1 = r6     // Catch: java.lang.Throwable -> L90
            r2.L$2 = r0     // Catch: java.lang.Throwable -> L90
            r2.label = r3     // Catch: java.lang.Throwable -> L90
            java.lang.Object r8 = r7.send(r8, r2)     // Catch: java.lang.Throwable -> L90
            if (r8 != r1) goto L95
        L8f:
            return r1
        L90:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
            goto L9d
        L95:
            r8 = r0
            r0 = r2
            goto L60
        L98:
            r8 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r6, r8)
            return r7
        L9d:
            throw r6     // Catch: java.lang.Throwable -> L9e
        L9e:
            r8 = move-exception
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object toMap(ReceiveChannel receiveChannel, Continuation continuation) {
        return toMap(receiveChannel, new LinkedHashMap(), continuation);
    }
}
