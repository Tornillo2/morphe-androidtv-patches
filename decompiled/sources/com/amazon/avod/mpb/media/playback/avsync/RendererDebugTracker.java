package com.amazon.avod.mpb.media.playback.avsync;

import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.amazon.avod.mpb.threading.ExecutorBuilder;
import com.google.common.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRendererDebugTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RendererDebugTracker.kt\ncom/amazon/avod/mpb/media/playback/avsync/RendererDebugTracker\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,87:1\n384#2,7:88\n*S KotlinDebug\n*F\n+ 1 RendererDebugTracker.kt\ncom/amazon/avod/mpb/media/playback/avsync/RendererDebugTracker\n*L\n83#1:88,7\n*E\n"})
public final class RendererDebugTracker {

    @Nullable
    public Long audioSamplePTSInUs;
    public final int avSyncThresholdUs;

    @NotNull
    public final Map<String, Set<String>> debugInformation;

    @NotNull
    public final ExecutorService executorService;

    @Nullable
    public Long videoSamplePTSInUs;

    @VisibleForTesting
    public RendererDebugTracker(@NotNull Map<String, Set<String>> debugInformation, int i, @NotNull ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(debugInformation, "debugInformation");
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.debugInformation = debugInformation;
        this.avSyncThresholdUs = i;
        this.executorService = executorService;
    }

    public static final void clearPTS$lambda$0(RendererDebugTracker rendererDebugTracker) {
        rendererDebugTracker.audioSamplePTSInUs = null;
        rendererDebugTracker.videoSamplePTSInUs = null;
    }

    public static final void updateFirstSampleInStreamPTS$lambda$1(boolean z, RendererDebugTracker rendererDebugTracker, long j) {
        if (z) {
            rendererDebugTracker.audioSamplePTSInUs = Long.valueOf(j);
        } else {
            rendererDebugTracker.videoSamplePTSInUs = Long.valueOf(j);
        }
        Long l = rendererDebugTracker.audioSamplePTSInUs;
        Long l2 = rendererDebugTracker.videoSamplePTSInUs;
        if (l == null || l2 == null) {
            return;
        }
        if (rendererDebugTracker.avSyncThresholdUs < Math.abs(l.longValue() - l2.longValue())) {
            rendererDebugTracker.updateDebugInfo("AVPTSMisMatch", "APTSUs:" + l + ", VPTSUs:" + l2);
        }
        TimeUnit timeUnit = TimeUnit.MICROSECONDS;
        MpbLog.logf("RendererDebugTracker first samples in stream [audioPtsMs:%d, videoPtsMs:%d, diffMs:%d]", Long.valueOf(timeUnit.toMillis(l.longValue())), Long.valueOf(timeUnit.toMillis(l2.longValue())), Long.valueOf(timeUnit.toMillis(Math.abs(l.longValue() - l2.longValue()))));
        rendererDebugTracker.clearPTS();
    }

    public final void clearPTS() {
        this.executorService.execute(new Runnable() { // from class: com.amazon.avod.mpb.media.playback.avsync.RendererDebugTracker$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RendererDebugTracker.clearPTS$lambda$0(this.f$0);
            }
        });
    }

    public final void updateDebugInfo(@NotNull final String debugInfoType, @NotNull final String debugInformation) {
        Intrinsics.checkNotNullParameter(debugInfoType, "debugInfoType");
        Intrinsics.checkNotNullParameter(debugInformation, "debugInformation");
        this.executorService.execute(new Runnable() { // from class: com.amazon.avod.mpb.media.playback.avsync.RendererDebugTracker$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateDebugInformation(debugInfoType, debugInformation);
            }
        });
    }

    @VisibleForTesting
    public final void updateDebugInformation(@NotNull String debugInfoType, @NotNull String debugInformation) {
        Intrinsics.checkNotNullParameter(debugInfoType, "debugInfoType");
        Intrinsics.checkNotNullParameter(debugInformation, "debugInformation");
        Map<String, Set<String>> map = this.debugInformation;
        Set<String> hashSet = map.get(debugInfoType);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(debugInfoType, hashSet);
        }
        hashSet.add(debugInformation);
    }

    public final void updateFirstSampleInStreamPTS(final boolean z, final long j) {
        this.executorService.execute(new Runnable() { // from class: com.amazon.avod.mpb.media.playback.avsync.RendererDebugTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RendererDebugTracker.updateFirstSampleInStreamPTS$lambda$1(z, this, j);
            }
        });
    }

    public RendererDebugTracker() {
        HashMap map = new HashMap();
        DefaultMPBInternalConfig.INSTANCE.getClass();
        int i = DefaultMPBInternalConfig.avSyncThresholdUs;
        ExecutorBuilder executorBuilder = new ExecutorBuilder((Class<?>) RendererDebugTracker.class);
        executorBuilder.withFixedThreadPoolSize(1);
        executorBuilder.allowCoreThreadExpiry(100L, TimeUnit.MILLISECONDS);
        this(map, i, executorBuilder.build());
    }
}
