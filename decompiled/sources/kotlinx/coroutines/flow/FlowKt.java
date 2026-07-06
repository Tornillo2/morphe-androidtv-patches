package kotlinx.coroutines.flow;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;
import kotlin.time.Duration;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public final class FlowKt {

    @NotNull
    public static final String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

    @Deprecated(level = DeprecationLevel.WARNING, message = "'BroadcastChannel' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull BroadcastChannel<T> broadcastChannel) {
        return new FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1(broadcastChannel);
    }

    @NotNull
    public static final <T> SharedFlow<T> asSharedFlow(@NotNull MutableSharedFlow<T> mutableSharedFlow) {
        return FlowKt__ShareKt.asSharedFlow(mutableSharedFlow);
    }

    @NotNull
    public static final <T> StateFlow<T> asStateFlow(@NotNull MutableStateFlow<T> mutableStateFlow) {
        return FlowKt__ShareKt.asStateFlow(mutableStateFlow);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'cache()' is 'shareIn' with unlimited replay and 'started = SharingStared.Lazily' argument'", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE, started = SharingStared.Lazily)", imports = {}))
    @NotNull
    public static final <T> Flow<T> cache(@NotNull Flow<? extends T> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> callbackFlow(@BuilderInference @NotNull Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.callbackFlow(function2);
    }

    @NotNull
    public static final <T> Flow<T> cancellable(@NotNull Flow<? extends T> flow) {
        return FlowKt__ContextKt.cancellable(flow);
    }

    @NotNull
    /* JADX INFO: renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m2108catch(@NotNull Flow<? extends T> flow, @NotNull Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(flow, function3);
    }

    @Nullable
    public static final <T> Object catchImpl(@NotNull Flow<? extends T> flow, @NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Throwable> continuation) {
        return FlowKt__ErrorsKt.catchImpl(flow, flowCollector, continuation);
    }

    @NotNull
    public static final <T> Flow<T> channelFlow(@BuilderInference @NotNull Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return FlowKt__BuildersKt.channelFlow(function2);
    }

    @Nullable
    public static final Object collect(@NotNull Flow<?> flow, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__CollectKt.collect(flow, continuation);
    }

    @Nullable
    public static final <T> Object collectIndexed(@NotNull Flow<? extends T> flow, @NotNull Function3<? super Integer, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__CollectKt.collectIndexed(flow, function3, continuation);
    }

    @Nullable
    public static final <T> Object collectLatest(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__CollectKt.collectLatest(flow, function2, continuation);
    }

    @Nullable
    public static final <T> Object collectWhile(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__LimitKt.collectWhile(flow, function2, continuation);
    }

    public static final /* synthetic */ <T, R> Flow<R> combine(Iterable<? extends Flow<? extends T>> iterable, Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2) {
        FlowKt__ZipKt.combine(iterable, function2);
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, transform)", imports = {}))
    @NotNull
    public static final <T1, T2, T3, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Function4<? super T1, ? super T2, ? super T3, ? super Continuation<? super R>, ? extends Object> function4) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, function4);
    }

    public static final /* synthetic */ <T, R> Flow<R> combineTransform(Iterable<? extends Flow<? extends T>> iterable, @BuilderInference Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3) {
        FlowKt__ZipKt.combineTransform(iterable, function3);
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'compose' is 'let'", replaceWith = @ReplaceWith(expression = "let(transformer)", imports = {}))
    @NotNull
    public static final <T, R> Flow<R> compose(@NotNull Flow<? extends T> flow, @NotNull Function1<? super Flow<? extends T>, ? extends Flow<? extends R>> function1) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'concatMap' is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    @NotNull
    public static final <T, R> Flow<R> concatMap(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends Flow<? extends R>> function1) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emit(value) }'", replaceWith = @ReplaceWith(expression = "onCompletion { emit(value) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> concatWith(@NotNull Flow<? extends T> flow, T t) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> conflate(@NotNull Flow<? extends T> flow) {
        return FlowKt__ContextKt.conflate(flow);
    }

    @NotNull
    public static final <T> Flow<T> consumeAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return FlowKt__ChannelsKt.consumeAsFlow(receiveChannel);
    }

    @Nullable
    public static final <T> Object count(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super Integer> continuation) {
        return FlowKt__CountKt.count(flow, continuation);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.debounce(flow, j);
    }

    @FlowPreview
    @NotNull
    /* JADX INFO: renamed from: debounce-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m2109debounceHG0u8IE(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.m2111debounceHG0u8IE(flow, j);
    }

    @FlowPreview
    @JvmName(name = "debounceDuration")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T> Flow<T> debounceDuration(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Duration> function1) {
        return FlowKt__DelayKt.debounceDuration(flow, function1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'onEach { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onEach { delay(timeMillis) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> delayEach(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__MigrationKt.delayEach(flow, j);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'onStart { delay(timeMillis) }'", replaceWith = @ReplaceWith(expression = "onStart { delay(timeMillis) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> delayFlow(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__MigrationKt.delayFlow(flow, j);
    }

    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow);
    }

    @NotNull
    public static final <T, K> Flow<T> distinctUntilChangedBy(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, ? extends K> function1) {
        return FlowKt__DistinctKt.distinctUntilChangedBy(flow, function1);
    }

    @NotNull
    public static final <T> Flow<T> drop(@NotNull Flow<? extends T> flow, int i) {
        return FlowKt__LimitKt.drop(flow, i);
    }

    @NotNull
    public static final <T> Flow<T> dropWhile(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(flow, function2);
    }

    @Nullable
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull ReceiveChannel<? extends T> receiveChannel, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__ChannelsKt.emitAll(flowCollector, receiveChannel, continuation);
    }

    @NotNull
    public static final <T> Flow<T> emptyFlow() {
        return EmptyFlow.INSTANCE;
    }

    public static final void ensureActive(@NotNull FlowCollector<?> flowCollector) {
        FlowKt__EmittersKt.ensureActive(flowCollector);
    }

    @NotNull
    public static final <T> Flow<T> filter(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__TransformKt$filter$$inlined$unsafeTransform$1(flow, function2);
    }

    public static final <R> Flow<R> filterIsInstance(Flow<?> flow) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> filterNot(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__TransformKt$filterNot$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> filterNotNull(@NotNull Flow<? extends T> flow) {
        return new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(flow);
    }

    @Nullable
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.first(flow, continuation);
    }

    @Nullable
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.firstOrNull(flow, continuation);
    }

    @NotNull
    public static final ReceiveChannel<Unit> fixedPeriodTicker(@NotNull CoroutineScope coroutineScope, long j, long j2) {
        return FlowKt__DelayKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue is 'flatMapConcat'", replaceWith = @ReplaceWith(expression = "flatMapConcat(mapper)", imports = {}))
    @NotNull
    public static final <T, R> Flow<R> flatMap(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @FlowPreview
    @NotNull
    public static final <T, R> Flow<R> flatMapConcat(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.flatMapConcat(flow, function2);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T, R> Flow<R> flatMapLatest(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.flatMapLatest(flow, function2);
    }

    @FlowPreview
    @NotNull
    public static final <T, R> Flow<R> flatMapMerge(@NotNull Flow<? extends T> flow, int i, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MergeKt.flatMapMerge(flow, i, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'flatten' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    @NotNull
    public static final <T> Flow<T> flatten(@NotNull Flow<? extends Flow<? extends T>> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> flattenConcat(@NotNull Flow<? extends Flow<? extends T>> flow) {
        return FlowKt__MergeKt.flattenConcat(flow);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> flattenMerge(@NotNull Flow<? extends Flow<? extends T>> flow, int i) {
        return FlowKt__MergeKt.flattenMerge(flow, i);
    }

    @NotNull
    public static final <T> Flow<T> flow(@BuilderInference @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new SafeFlow(function2);
    }

    @JvmName(name = "flowCombine")
    @NotNull
    public static final <T1, T2, R> Flow<R> flowCombine(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, flow2, function3);
    }

    @JvmName(name = "flowCombineTransform")
    @NotNull
    public static final <T1, T2, R> Flow<R> flowCombineTransform(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @BuilderInference @NotNull Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4) {
        return FlowKt__ZipKt.flowCombineTransform(flow, flow2, function4);
    }

    @NotNull
    public static final <T> Flow<T> flowOf(@NotNull T... tArr) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1(tArr);
    }

    @NotNull
    public static final <T> Flow<T> flowOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        return FlowKt__ContextKt.flowOn(flow, coroutineContext);
    }

    @Nullable
    public static final <T, R> Object fold(@NotNull Flow<? extends T> flow, R r, @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, @NotNull Continuation<? super R> continuation) {
        return FlowKt__ReduceKt.fold(flow, r, function3, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'forEach' is 'collect'", replaceWith = @ReplaceWith(expression = "collect(action)", imports = {}))
    public static final <T> void forEach(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    public static final int getDEFAULT_CONCURRENCY() {
        return FlowKt__MergeKt.getDEFAULT_CONCURRENCY();
    }

    @Nullable
    public static final <T> Object last(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.last(flow, continuation);
    }

    @Nullable
    public static final <T> Object lastOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.lastOrNull(flow, continuation);
    }

    @NotNull
    public static final <T> Job launchIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return FlowKt__CollectKt.launchIn(flow, coroutineScope);
    }

    @NotNull
    public static final <T, R> Flow<R> map(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return new FlowKt__TransformKt$map$$inlined$unsafeTransform$1(flow, function2);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T, R> Flow<R> mapLatest(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return FlowKt__MergeKt.mapLatest(flow, function2);
    }

    @NotNull
    public static final <T, R> Flow<R> mapNotNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        return new FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> merge(@NotNull Iterable<? extends Flow<? extends T>> iterable) {
        return FlowKt__MergeKt.merge(iterable);
    }

    @NotNull
    public static final Void noImpl() {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    @NotNull
    public static final <T> Flow<T> observeOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> onCompletion(@NotNull Flow<? extends T> flow, @NotNull Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(flow, function3);
    }

    @NotNull
    public static final <T> Flow<T> onEach(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> onEmpty(@NotNull Flow<? extends T> flow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1(flow, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> onErrorResume(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emitAll(fallback) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> onErrorResumeNext(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emit(fallback) }'", replaceWith = @ReplaceWith(expression = "catch { emit(fallback) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> onErrorReturn(@NotNull Flow<? extends T> flow, T t) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> onStart(@NotNull Flow<? extends T> flow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(function2, flow);
    }

    @NotNull
    public static final <T> SharedFlow<T> onSubscription(@NotNull SharedFlow<? extends T> sharedFlow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new SubscribedSharedFlow(sharedFlow, function2);
    }

    @FlowPreview
    @NotNull
    public static final <T> ReceiveChannel<T> produceIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return FlowKt__ChannelsKt.produceIn(flow, coroutineScope);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'publish()' is 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, 0)", imports = {}))
    @NotNull
    public static final <T> Flow<T> publish(@NotNull Flow<? extends T> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Collect flow in the desired context instead")
    @NotNull
    public static final <T> Flow<T> publishOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> receiveAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return FlowKt__ChannelsKt.receiveAsFlow(receiveChannel);
    }

    @Nullable
    public static final <S, T extends S> Object reduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super S, ? super T, ? super Continuation<? super S>, ? extends Object> function3, @NotNull Continuation<? super S> continuation) {
        return FlowKt__ReduceKt.reduce(flow, function3, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'replay()' is 'shareIn' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, Int.MAX_VALUE)", imports = {}))
    @NotNull
    public static final <T> Flow<T> replay(@NotNull Flow<? extends T> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> retry(@NotNull Flow<? extends T> flow, long j, @NotNull Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt__ErrorsKt.retry(flow, j, function2);
    }

    @NotNull
    public static final <T> Flow<T> retryWhen(@NotNull Flow<? extends T> flow, @NotNull Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function4) {
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(flow, function4);
    }

    @NotNull
    public static final <T, R> Flow<R> runningFold(@NotNull Flow<? extends T> flow, R r, @BuilderInference @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(r, flow, function3);
    }

    @NotNull
    public static final <T> Flow<T> runningReduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(flow, function3);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> sample(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.sample(flow, j);
    }

    @FlowPreview
    @NotNull
    /* JADX INFO: renamed from: sample-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m2110sampleHG0u8IE(@NotNull Flow<? extends T> flow, long j) {
        return FlowKt__DelayKt.m2112sampleHG0u8IE(flow, j);
    }

    @NotNull
    public static final <T, R> Flow<R> scan(@NotNull Flow<? extends T> flow, R r, @BuilderInference @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(r, flow, function3);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow has less verbose 'scan' shortcut", replaceWith = @ReplaceWith(expression = "scan(initial, operation)", imports = {}))
    @NotNull
    public static final <T, R> Flow<R> scanFold(@NotNull Flow<? extends T> flow, R r, @BuilderInference @NotNull Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "'scanReduce' was renamed to 'runningReduce' to be consistent with Kotlin standard library", replaceWith = @ReplaceWith(expression = "runningReduce(operation)", imports = {}))
    @NotNull
    public static final <T> Flow<T> scanReduce(@NotNull Flow<? extends T> flow, @NotNull Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3) {
        return new FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(flow, function3);
    }

    @NotNull
    public static final <T> SharedFlow<T> shareIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull SharingStarted sharingStarted, int i) {
        return FlowKt__ShareKt.shareIn(flow, coroutineScope, sharingStarted, i);
    }

    @Nullable
    public static final <T> Object single(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.single(flow, continuation);
    }

    @Nullable
    public static final <T> Object singleOrNull(@NotNull Flow<? extends T> flow, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.singleOrNull(flow, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'skip' is 'drop'", replaceWith = @ReplaceWith(expression = "drop(count)", imports = {}))
    @NotNull
    public static final <T> Flow<T> skip(@NotNull Flow<? extends T> flow, int i) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emit(value) }'", replaceWith = @ReplaceWith(expression = "onStart { emit(value) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> startWith(@NotNull Flow<? extends T> flow, T t) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Nullable
    public static final <T> Object stateIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull Continuation<? super StateFlow<? extends T>> continuation) {
        return FlowKt__ShareKt.stateIn(flow, coroutineScope, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'flowOn' instead")
    @NotNull
    public static final <T> Flow<T> subscribeOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext coroutineContext) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogues of 'switchMap' are 'transformLatest', 'flatMapLatest' and 'mapLatest'", replaceWith = @ReplaceWith(expression = "this.flatMapLatest(transform)", imports = {}))
    @NotNull
    public static final <T, R> Flow<R> switchMap(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        return FlowKt__MigrationKt.switchMap(flow, function2);
    }

    @NotNull
    public static final <T> Flow<T> take(@NotNull Flow<? extends T> flow, int i) {
        return FlowKt__LimitKt.take(flow, i);
    }

    @NotNull
    public static final <T> Flow<T> takeWhile(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return new FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1(flow, function2);
    }

    @Nullable
    public static final <T, C extends Collection<? super T>> Object toCollection(@NotNull Flow<? extends T> flow, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return FlowKt__CollectionKt.toCollection(flow, c, continuation);
    }

    @Nullable
    public static final <T> Object toList(@NotNull Flow<? extends T> flow, @NotNull List<T> list, @NotNull Continuation<? super List<? extends T>> continuation) {
        return FlowKt__CollectionKt.toCollection(flow, list, continuation);
    }

    @Nullable
    public static final <T> Object toSet(@NotNull Flow<? extends T> flow, @NotNull Set<T> set, @NotNull Continuation<? super Set<? extends T>> continuation) {
        return FlowKt__CollectionKt.toCollection(flow, set, continuation);
    }

    @NotNull
    public static final <T, R> Flow<R> transform(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return FlowKt__EmittersKt.transform(flow, function3);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T, R> Flow<R> transformLatest(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return FlowKt__MergeKt.transformLatest(flow, function3);
    }

    @NotNull
    public static final <T, R> Flow<R> transformWhile(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        return FlowKt__LimitKt.transformWhile(flow, function3);
    }

    @PublishedApi
    @NotNull
    public static final <T, R> Flow<R> unsafeTransform(@NotNull Flow<? extends T> flow, @BuilderInference @NotNull Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1(flow, function3);
    }

    @NotNull
    public static final <T> Flow<IndexedValue<T>> withIndex(@NotNull Flow<? extends T> flow) {
        return new FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1(flow);
    }

    @NotNull
    public static final <T1, T2, R> Flow<R> zip(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new CombineKt$zipImpl$$inlined$unsafeFlow$1(flow2, flow, function3);
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull Function0<? extends T> function0) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$1(function0);
    }

    @NotNull
    public static final <T> Flow<T> buffer(@NotNull Flow<? extends T> flow, int i, @NotNull BufferOverflow bufferOverflow) {
        return FlowKt__ContextKt.buffer(flow, i, bufferOverflow);
    }

    @NotNull
    public static final <T1, T2, T3, R> Flow<R> combine(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @BuilderInference @NotNull Function4<? super T1, ? super T2, ? super T3, ? super Continuation<? super R>, ? extends Object> function4) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, function4);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    @NotNull
    public static final <T1, T2, T3, T4, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super R>, ? extends Object> function5) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, function5);
    }

    @NotNull
    public static final <T1, T2, R> Flow<R> combineTransform(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @BuilderInference @NotNull Function4<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super Continuation<? super Unit>, ? extends Object> function4) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, function4);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { if (it == null) emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onCompletion { if (it == null) emitAll(other) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> concatWith(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Nullable
    public static final <T> Object count(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super Integer> continuation) {
        return FlowKt__CountKt.count(flow, function2, continuation);
    }

    @FlowPreview
    @OverloadResolutionByLambdaReturnType
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Long> function1) {
        return FlowKt__DelayKt.debounceInternal$FlowKt__DelayKt(flow, function1);
    }

    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super T, Boolean> function2) {
        return FlowKt__DistinctKt.distinctUntilChanged(flow, function2);
    }

    @Nullable
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation) {
        return FlowKt__CollectKt.emitAll(flowCollector, flow, continuation);
    }

    @Nullable
    public static final <T> Object first(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.first(flow, function2, continuation);
    }

    @Nullable
    public static final <T> Object firstOrNull(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return FlowKt__ReduceKt.firstOrNull(flow, function2, continuation);
    }

    @NotNull
    public static final <T> Flow<T> flowOf(T t) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(t);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'merge' is 'flattenConcat'", replaceWith = @ReplaceWith(expression = "flattenConcat()", imports = {}))
    @NotNull
    public static final <T> Flow<T> merge(@NotNull Flow<? extends Flow<? extends T>> flow) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { e -> if (predicate(e)) emit(fallback) else throw e }'", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }", imports = {}))
    @NotNull
    public static final <T> Flow<T> onErrorReturn(@NotNull Flow<? extends T> flow, T t, @NotNull Function1<? super Throwable, Boolean> function1) {
        return FlowKt__MigrationKt.onErrorReturn(flow, t, function1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'publish(bufferSize)' is 'buffer' followed by 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.buffer(bufferSize).shareIn(scope, 0)", imports = {}))
    @NotNull
    public static final <T> Flow<T> publish(@NotNull Flow<? extends T> flow, int i) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'replay(bufferSize)' is 'shareIn' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.", replaceWith = @ReplaceWith(expression = "this.shareIn(scope, bufferSize)", imports = {}))
    @NotNull
    public static final <T> Flow<T> replay(@NotNull Flow<? extends T> flow, int i) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emitAll(other) }'", replaceWith = @ReplaceWith(expression = "onStart { emitAll(other) }", imports = {}))
    @NotNull
    public static final <T> Flow<T> startWith(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> StateFlow<T> stateIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull SharingStarted sharingStarted, T t) {
        return FlowKt__ShareKt.stateIn(flow, coroutineScope, sharingStarted, t);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2(function1);
    }

    @NotNull
    public static final <T1, T2, T3, T4, R> Flow<R> combine(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super R>, ? extends Object> function5) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, function5);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "combine(this, other, other2, other3, transform)", imports = {}))
    @NotNull
    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Flow<? extends T5> flow5, @NotNull Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super R>, ? extends Object> function6) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, flow5, function6);
    }

    @NotNull
    public static final <T1, T2, T3, R> Flow<R> combineTransform(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @BuilderInference @NotNull Function5<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super Continuation<? super Unit>, ? extends Object> function5) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, function5);
    }

    @NotNull
    public static final <T> Flow<T> merge(@NotNull Flow<? extends T>... flowArr) {
        return FlowKt__MergeKt.merge(flowArr);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead")
    public static final <T> void subscribe(@NotNull Flow<? extends T> flow, @NotNull Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Function2<? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function22) {
        FlowKt__MigrationKt.noImpl();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull Iterable<? extends T> iterable) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3(iterable);
    }

    @NotNull
    public static final <T1, T2, T3, T4, T5, R> Flow<R> combine(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Flow<? extends T5> flow5, @NotNull Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super R>, ? extends Object> function6) {
        return FlowKt__ZipKt.combine(flow, flow2, flow3, flow4, flow5, function6);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Flow analogue of 'combineLatest' is 'combine'", replaceWith = @ReplaceWith(expression = "this.combine(other, transform)", imports = {}))
    @NotNull
    public static final <T1, T2, R> Flow<R> combineLatest(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, flow2, function3);
    }

    @NotNull
    public static final <T1, T2, T3, T4, R> Flow<R> combineTransform(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @BuilderInference @NotNull Function6<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super Continuation<? super Unit>, ? extends Object> function6) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, function6);
    }

    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull Iterator<? extends T> it) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$4(it);
    }

    public static final <T, R> Flow<R> combine(Flow<? extends T>[] flowArr, Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineTransform(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Flow<? extends T3> flow3, @NotNull Flow<? extends T4> flow4, @NotNull Flow<? extends T5> flow5, @BuilderInference @NotNull Function7<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super Continuation<? super Unit>, ? extends Object> function7) {
        return FlowKt__ZipKt.combineTransform(flow, flow2, flow3, flow4, flow5, function7);
    }

    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull Sequence<? extends T> sequence) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$5(sequence);
    }

    @NotNull
    public static final <T1, T2, R> Flow<R> combine(@NotNull Flow<? extends T1> flow, @NotNull Flow<? extends T2> flow2, @NotNull Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, flow2, function3);
    }

    public static final <T, R> Flow<R> combineTransform(Flow<? extends T>[] flowArr, @BuilderInference Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull T[] tArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6(tArr);
    }

    @NotNull
    public static final Flow<Integer> asFlow(@NotNull int[] iArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7(iArr);
    }

    @NotNull
    public static final Flow<Long> asFlow(@NotNull long[] jArr) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(jArr);
    }

    @NotNull
    public static final Flow<Integer> asFlow(@NotNull IntRange intRange) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9(intRange);
    }

    @NotNull
    public static final Flow<Long> asFlow(@NotNull LongRange longRange) {
        return new FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$10(longRange);
    }
}
