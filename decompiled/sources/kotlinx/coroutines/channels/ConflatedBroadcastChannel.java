package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImpl$$ExternalSyntheticOutline0;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ObsoleteCoroutinesApi
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {

    @Deprecated
    @NotNull
    public static final State<Object> INITIAL_STATE;

    @Deprecated
    @NotNull
    public static final Symbol UNDEFINED;
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU;
    public static final /* synthetic */ AtomicIntegerFieldUpdater _updating$FU;
    public static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU;

    @NotNull
    private volatile /* synthetic */ Object _state;

    @NotNull
    private volatile /* synthetic */ int _updating;

    @NotNull
    private volatile /* synthetic */ Object onCloseHandler;

    @NotNull
    public static final Companion Companion = new Companion();

    @Deprecated
    @NotNull
    public static final Closed CLOSED = new Closed(null);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Closed {

        @JvmField
        @Nullable
        public final Throwable closeCause;

        public Closed(@Nullable Throwable th) {
            this.closeCause = th;
        }

        @NotNull
        public final Throwable getSendException() {
            Throwable th = this.closeCause;
            return th == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }

        @NotNull
        public final Throwable getValueException() {
            Throwable th = this.closeCause;
            return th == null ? new IllegalStateException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class State<E> {

        @JvmField
        @Nullable
        public final Subscriber<E>[] subscribers;

        @JvmField
        @Nullable
        public final Object value;

        public State(@Nullable Object obj, @Nullable Subscriber<E>[] subscriberArr) {
            this.value = obj;
            this.subscribers = subscriberArr;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Subscriber<E> extends ConflatedChannel<E> implements ReceiveChannel<E> {

        @NotNull
        public final ConflatedBroadcastChannel<E> broadcastChannel;

        public Subscriber(@NotNull ConflatedBroadcastChannel<E> conflatedBroadcastChannel) {
            super(null);
            this.broadcastChannel = conflatedBroadcastChannel;
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractSendChannel
        @NotNull
        public Object offerInternal(E e) {
            return super.offerInternal(e);
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractChannel
        public void onCancelIdempotent(boolean z) {
            if (z) {
                this.broadcastChannel.closeSubscriber(this);
            }
        }
    }

    static {
        Symbol symbol = new Symbol("UNDEFINED");
        UNDEFINED = symbol;
        INITIAL_STATE = new State<>(symbol, null);
        _state$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
        _updating$FU = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
        onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "onCloseHandler");
    }

    public ConflatedBroadcastChannel() {
        this._state = INITIAL_STATE;
        this._updating = 0;
        this.onCloseHandler = null;
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Symbol symbol;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (symbol = AbstractChannelKt.HANDLER_INVOKED) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, obj, symbol)) {
            return;
        }
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1);
        ((Function1) obj).invoke(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> void registerSelectSend(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        if (selectInstance.trySelect()) {
            Closed closedOfferInternal = offerInternal(e);
            if (closedOfferInternal != null) {
                selectInstance.resumeSelectWithException(closedOfferInternal.getSendException());
            } else {
                UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
            }
        }
    }

    public final Subscriber<E>[] addSubscriber(Subscriber<E>[] subscriberArr, Subscriber<E> subscriber) {
        return subscriberArr == null ? new Subscriber[]{subscriber} : (Subscriber[]) ArraysKt___ArraysJvmKt.plus(subscriberArr, subscriber);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* JADX INFO: renamed from: close, reason: merged with bridge method [inline-methods] */
    public boolean cancel(@Nullable Throwable th) {
        Object obj;
        int i;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                return false;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid state ", obj));
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, th == null ? CLOSED : new Closed(th)));
        Subscriber<E>[] subscriberArr = ((State) obj).subscribers;
        if (subscriberArr != null) {
            for (Subscriber<E> subscriber : subscriberArr) {
                subscriber.cancel(th);
            }
        }
        invokeOnCloseHandler(th);
        return true;
    }

    public final void closeSubscriber(Subscriber<E> subscriber) {
        Object obj;
        Object obj2;
        Subscriber<E>[] subscriberArr;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                return;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid state ", obj));
            }
            State state = (State) obj;
            obj2 = state.value;
            subscriberArr = state.subscribers;
            Intrinsics.checkNotNull(subscriberArr);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new State(obj2, removeSubscriber(subscriberArr, subscriber))));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return new SelectClause2<E, SendChannel<? super E>>(this) { // from class: kotlinx.coroutines.channels.ConflatedBroadcastChannel$onSend$1
            public final /* synthetic */ ConflatedBroadcastChannel<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, E e, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
                this.this$0.registerSelectSend(selectInstance, e, function2);
            }
        };
    }

    public final E getValue() throws Throwable {
        Object obj = this._state;
        if (obj instanceof Closed) {
            throw ((Closed) obj).getValueException();
        }
        if (!(obj instanceof State)) {
            throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid state ", obj));
        }
        E e = (E) ((State) obj).value;
        if (e != UNDEFINED) {
            return e;
        }
        throw new IllegalStateException("No value");
    }

    @Nullable
    public final E getValueOrNull() {
        Object obj = this._state;
        if (obj instanceof Closed) {
            return null;
        }
        if (!(obj instanceof State)) {
            throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid state ", obj));
        }
        Symbol symbol = UNDEFINED;
        E e = (E) ((State) obj).value;
        if (e == symbol) {
            return null;
        }
        return e;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, function1)) {
            Object obj = this.onCloseHandler;
            if (obj != AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Another handler was already registered: ", obj));
            }
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        Object obj2 = this._state;
        if ((obj2 instanceof Closed) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
            function1.invoke(((Closed) obj2).closeCause);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._state instanceof Closed;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return SendChannel.DefaultImpls.offer(this, e);
    }

    public final Closed offerInternal(E e) {
        Object obj;
        if (!_updating$FU.compareAndSet(this, 0, 1)) {
            return null;
        }
        do {
            try {
                obj = this._state;
                if (obj instanceof Closed) {
                    return (Closed) obj;
                }
                if (!(obj instanceof State)) {
                    throw new IllegalStateException(("Invalid state " + obj).toString());
                }
            } finally {
                this._updating = 0;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new State(e, ((State) obj).subscribers)));
        Subscriber<E>[] subscriberArr = ((State) obj).subscribers;
        if (subscriberArr != null) {
            for (Subscriber<E> subscriber : subscriberArr) {
                subscriber.offerInternal(e);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Object obj;
        State state;
        Subscriber subscriber = new Subscriber(this);
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                subscriber.cancel(((Closed) obj).closeCause);
                return subscriber;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(CancellableContinuationImpl$$ExternalSyntheticOutline0.m("Invalid state ", obj));
            }
            state = (State) obj;
            Object obj2 = state.value;
            if (obj2 != UNDEFINED) {
                subscriber.offerInternal(obj2);
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, new State(state.value, addSubscriber(state.subscribers, subscriber))));
        return subscriber;
    }

    public final Subscriber<E>[] removeSubscriber(Subscriber<E>[] subscriberArr, Subscriber<E> subscriber) {
        int length = subscriberArr.length;
        int iIndexOf = ArraysKt___ArraysKt.indexOf(subscriberArr, subscriber);
        if (length == 1) {
            return null;
        }
        Subscriber<E>[] subscriberArr2 = new Subscriber[length - 1];
        ArraysKt___ArraysJvmKt.copyInto$default(subscriberArr, subscriberArr2, 0, 0, iIndexOf, 6, (Object) null);
        ArraysKt___ArraysJvmKt.copyInto$default(subscriberArr, subscriberArr2, iIndexOf, iIndexOf + 1, 0, 8, (Object) null);
        return subscriberArr2;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) throws Throwable {
        Closed closedOfferInternal = offerInternal(e);
        if (closedOfferInternal != null) {
            throw closedOfferInternal.getSendException();
        }
        if (CoroutineSingletons.COROUTINE_SUSPENDED == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo2084trySendJP2dKIU(E e) {
        Closed closedOfferInternal = offerInternal(e);
        if (closedOfferInternal != null) {
            ChannelResult.Companion companion = ChannelResult.Companion;
            Throwable sendException = closedOfferInternal.getSendException();
            companion.getClass();
            return new ChannelResult.Closed(sendException);
        }
        ChannelResult.Companion companion2 = ChannelResult.Companion;
        Unit unit = Unit.INSTANCE;
        companion2.getClass();
        return unit;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }

    public ConflatedBroadcastChannel(E e) {
        this();
        _state$FU.lazySet(this, new State(e, null));
    }

    public static /* synthetic */ void getValue$annotations() {
    }
}
