package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowKt__ChannelsKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "'BroadcastChannel' is obsolete and all corresponding operators are deprecated in the favour of StateFlow and SharedFlow")
    @NotNull
    public static final <T> Flow<T> asFlow(@NotNull BroadcastChannel<T> broadcastChannel) {
        return new FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1(broadcastChannel);
    }

    @NotNull
    public static final <T> Flow<T> consumeAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, true, null, 0, null, 28, null);
    }

    @Nullable
    public static final <T> Object emitAll(@NotNull FlowCollector<? super T> flowCollector, @NotNull ReceiveChannel<? extends T> receiveChannel, @NotNull Continuation<? super Unit> continuation) {
        Object objEmitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        return objEmitAllImpl$FlowKt__ChannelsKt == CoroutineSingletons.COROUTINE_SUSPENDED ? objEmitAllImpl$FlowKt__ChannelsKt : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008e, code lost:
    
        if (r9 == r1) goto L38;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006f A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #2 {all -> 0x0037, blocks: (B:13:0x0030, B:27:0x006b, B:29:0x006f, B:35:0x007e, B:36:0x007f, B:20:0x004b), top: B:50:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007f A[Catch: all -> 0x0037, TRY_LEAVE, TryCatch #2 {all -> 0x0037, blocks: (B:13:0x0030, B:27:0x006b, B:29:0x006f, B:35:0x007e, B:36:0x007f, B:20:0x004b), top: B:50:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r6v0, types: [kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.flow.FlowCollector<? super T>] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r8v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x008e -> B:14:0x0033). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector<? super T> r6, kotlinx.coroutines.channels.ReceiveChannel<? extends T> r7, boolean r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L53
            if (r2 == r4) goto L41
            if (r2 != r3) goto L39
            boolean r6 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L37
        L33:
            r5 = r8
            r8 = r6
            r6 = r5
            goto L59
        L37:
            r8 = move-exception
            goto L95
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            boolean r6 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L37
            kotlinx.coroutines.channels.ChannelResult r9 = (kotlinx.coroutines.channels.ChannelResult) r9     // Catch: java.lang.Throwable -> L37
            java.lang.Object r9 = r9.holder     // Catch: java.lang.Throwable -> L37
            goto L6b
        L53:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.flow.FlowKt__EmittersKt.ensureActive(r6)
        L59:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L91
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L91
            r0.Z$0 = r8     // Catch: java.lang.Throwable -> L91
            r0.label = r4     // Catch: java.lang.Throwable -> L91
            java.lang.Object r9 = r7.mo2082receiveCatchingJP2dKIU(r0)     // Catch: java.lang.Throwable -> L91
            if (r9 != r1) goto L68
            goto L90
        L68:
            r5 = r8
            r8 = r6
            r6 = r5
        L6b:
            boolean r2 = r9 instanceof kotlinx.coroutines.channels.ChannelResult.Closed     // Catch: java.lang.Throwable -> L37
            if (r2 == 0) goto L7f
            java.lang.Throwable r8 = kotlinx.coroutines.channels.ChannelResult.m2093exceptionOrNullimpl(r9)     // Catch: java.lang.Throwable -> L37
            if (r8 != 0) goto L7e
            if (r6 == 0) goto L7b
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r6)
        L7b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L7e:
            throw r8     // Catch: java.lang.Throwable -> L37
        L7f:
            kotlinx.coroutines.channels.ChannelResult.m2095getOrThrowimpl(r9)     // Catch: java.lang.Throwable -> L37
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L37
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L37
            r0.Z$0 = r6     // Catch: java.lang.Throwable -> L37
            r0.label = r3     // Catch: java.lang.Throwable -> L37
            java.lang.Object r9 = r8.emit(r9, r0)     // Catch: java.lang.Throwable -> L37
            if (r9 != r1) goto L33
        L90:
            return r1
        L91:
            r6 = move-exception
            r5 = r8
            r8 = r6
            r6 = r5
        L95:
            throw r8     // Catch: java.lang.Throwable -> L96
        L96:
            r9 = move-exception
            if (r6 == 0) goto L9c
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.cancelConsumed(r7, r8)
        L9c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.ReceiveChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @FlowPreview
    @NotNull
    public static final <T> ReceiveChannel<T> produceIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(coroutineScope);
    }

    @NotNull
    public static final <T> Flow<T> receiveAsFlow(@NotNull ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, false, null, 0, null, 28, null);
    }
}
