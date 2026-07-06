package kotlinx.coroutines.flow.internal;

import android.R;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {

    @Nullable
    public SubscriptionCountStateFlow _subscriptionCount;
    public int nCollectors;
    public int nextIndex;

    @Nullable
    public S[] slots;

    @NotNull
    public final S allocateSlot() {
        S s;
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            try {
                S[] sArr = this.slots;
                if (sArr == null) {
                    sArr = (S[]) createSlotArray(2);
                    this.slots = sArr;
                } else if (this.nCollectors >= sArr.length) {
                    Object[] objArrCopyOf = Arrays.copyOf(sArr, sArr.length * 2);
                    Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                    this.slots = (S[]) ((AbstractSharedFlowSlot[]) objArrCopyOf);
                    sArr = (S[]) ((AbstractSharedFlowSlot[]) objArrCopyOf);
                }
                int i = this.nextIndex;
                do {
                    s = sArr[i];
                    if (s == null) {
                        s = (S) createSlot();
                        sArr[i] = s;
                    }
                    i++;
                    if (i >= sArr.length) {
                        i = 0;
                    }
                } while (!s.allocateLocked(this));
                this.nextIndex = i;
                this.nCollectors++;
                subscriptionCountStateFlow = this._subscriptionCount;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(1);
        }
        return s;
    }

    @NotNull
    public abstract S createSlot();

    @NotNull
    public abstract S[] createSlotArray(int i);

    public final void forEachSlotLocked(@NotNull Function1<? super S, Unit> function1) {
        S[] sArr;
        if (this.nCollectors == 0 || (sArr = this.slots) == null) {
            return;
        }
        for (R.bool boolVar : sArr) {
            if (boolVar != null) {
                function1.invoke(boolVar);
            }
        }
    }

    public final void freeSlot(@NotNull S s) {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        int i;
        Continuation<Unit>[] continuationArrFreeLocked;
        synchronized (this) {
            try {
                int i2 = this.nCollectors - 1;
                this.nCollectors = i2;
                subscriptionCountStateFlow = this._subscriptionCount;
                if (i2 == 0) {
                    this.nextIndex = 0;
                }
                continuationArrFreeLocked = s.freeLocked(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Continuation<Unit> continuation : continuationArrFreeLocked) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(-1);
        }
    }

    public final int getNCollectors() {
        return this.nCollectors;
    }

    @Nullable
    public final S[] getSlots() {
        return this.slots;
    }

    @NotNull
    public final StateFlow<Integer> getSubscriptionCount() {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            subscriptionCountStateFlow = this._subscriptionCount;
            if (subscriptionCountStateFlow == null) {
                subscriptionCountStateFlow = new SubscriptionCountStateFlow(this.nCollectors);
                this._subscriptionCount = subscriptionCountStateFlow;
            }
        }
        return subscriptionCountStateFlow;
    }

    public static /* synthetic */ void getSlots$annotations() {
    }
}
