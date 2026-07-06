package com.amazon.ignitionshared;

import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class NativeAllocatorMessageHandler implements ServiceInitializer, AutoCloseable {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String LOG_TAG;

    @NotNull
    public static final String MEMORY_PURGE_REQUEST_MESSAGE_TYPE = "gmb.purge_memory.request";
    public final ExecutorService executor;

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    @NotNull
    public final NativeAllocator nativeAllocator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        String simpleName = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(NativeAllocatorMessageHandler.class)).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        LOG_TAG = simpleName;
    }

    @Inject
    public NativeAllocatorMessageHandler(@NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull NativeAllocator nativeAllocator) {
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(nativeAllocator, "nativeAllocator");
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.nativeAllocator = nativeAllocator;
        this.executor = Executors.newSingleThreadExecutor(new NativeAllocatorMessageHandler$$ExternalSyntheticLambda1());
    }

    public static final Thread executor$lambda$0(Runnable runnable) {
        return new Thread(runnable, "memory-purge-thread");
    }

    public static final void initialize$lambda$2(final NativeAllocatorMessageHandler nativeAllocatorMessageHandler, String str) {
        nativeAllocatorMessageHandler.executor.submit(new Runnable() { // from class: com.amazon.ignitionshared.NativeAllocatorMessageHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.executeMemoryPurge();
            }
        });
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.executor.shutdown();
        try {
            if (this.executor.awaitTermination(5L, TimeUnit.SECONDS)) {
                return;
            }
            this.executor.shutdownNow();
        } catch (InterruptedException unused) {
            this.executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public final void executeMemoryPurge() {
        try {
            int iPurgeNativeMemory = this.nativeAllocator.purgeNativeMemory();
            if (iPurgeNativeMemory == 0) {
                Log.i(LOG_TAG, "Successfully purged Native memory.");
                return;
            }
            Log.w(LOG_TAG, "Error encountered while purging native memory with ErrorCode: " + iPurgeNativeMemory);
        } catch (Exception e) {
            Log.w(LOG_TAG, "Exception encountered during memory purge", e);
        } catch (UnsatisfiedLinkError e2) {
            Log.w(LOG_TAG, "Error encountered during memory purge", e2);
        }
    }

    @Override // com.amazon.ignitionshared.ServiceInitializer
    public void initialize() {
        this.gmbMessageProcessor.subscribeMessageHandler(MEMORY_PURGE_REQUEST_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.ignitionshared.NativeAllocatorMessageHandler$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                NativeAllocatorMessageHandler.initialize$lambda$2(this.f$0, str);
            }
        });
    }
}
