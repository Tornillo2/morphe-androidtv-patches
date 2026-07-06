package com.amazon.primevideo.nativebilling;

import android.content.Context;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.PendingPurchasesParams;
import com.android.billingclient.api.PurchasesUpdatedListener;
import java.io.Closeable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class BillingClientProvider {

    @Nullable
    public BillingClientHolder billingClientHolder;

    @NotNull
    public final Condition condition;

    @NotNull
    public final Context context;

    @NotNull
    public final ReentrantLock lock;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BillingClientHolder implements Closeable {

        @NotNull
        public final BillingClient billingClient;

        @NotNull
        public final Function0<Unit> resetBillingClient;

        public BillingClientHolder(@NotNull BillingClient billingClient, @NotNull Function0<Unit> resetBillingClient) {
            Intrinsics.checkNotNullParameter(billingClient, "billingClient");
            Intrinsics.checkNotNullParameter(resetBillingClient, "resetBillingClient");
            this.billingClient = billingClient;
            this.resetBillingClient = resetBillingClient;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.billingClient.endConnection();
            this.resetBillingClient.invoke();
        }

        @NotNull
        public final BillingClient getBillingClient() {
            return this.billingClient;
        }

        @NotNull
        public final Function0<Unit> getResetBillingClient() {
            return this.resetBillingClient;
        }
    }

    public static Unit $r8$lambda$Non4y0eODfOFTcE67QYVkZ0cDlE(BillingClientProvider billingClientProvider) {
        billingClientProvider.destroyBillingClient();
        return Unit.INSTANCE;
    }

    @Inject
    public BillingClientProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        Intrinsics.checkNotNullExpressionValue(conditionNewCondition, "newCondition(...)");
        this.condition = conditionNewCondition;
    }

    public static final Unit provideBillingClientHolder$lambda$2$lambda$0(BillingClientProvider billingClientProvider) {
        billingClientProvider.destroyBillingClient();
        return Unit.INSTANCE;
    }

    public final void destroyBillingClient() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.billingClientHolder = null;
            this.condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    @NotNull
    public final BillingClientHolder provideBillingClientHolder(@NotNull PurchasesUpdatedListener purchasesUpdatedListener) {
        Intrinsics.checkNotNullParameter(purchasesUpdatedListener, "purchasesUpdatedListener");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (this.billingClientHolder != null) {
            try {
                this.condition.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        BillingClient.Builder builderNewBuilder = BillingClient.newBuilder(this.context);
        builderNewBuilder.zzf = purchasesUpdatedListener;
        PendingPurchasesParams.Builder builder = new PendingPurchasesParams.Builder();
        builder.enableOneTimeProducts = true;
        builderNewBuilder.zzd = builder.build();
        builderNewBuilder.zzl = true;
        builderNewBuilder.zza = true;
        BillingClientHolder billingClientHolder = new BillingClientHolder(builderNewBuilder.build(), new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingClientProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                this.f$0.destroyBillingClient();
                return Unit.INSTANCE;
            }
        });
        this.billingClientHolder = billingClientHolder;
        reentrantLock.unlock();
        return billingClientHolder;
    }
}
