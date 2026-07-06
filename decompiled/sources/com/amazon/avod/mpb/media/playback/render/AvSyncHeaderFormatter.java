package com.amazon.avod.mpb.media.playback.render;

import com.amazon.avod.mpb.util.Preconditions2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AvSyncHeaderFormatter {
    public static final int AVSYNC_HEADER_SIZE = 16;
    public static final int AVSYNC_HEADER_SIZE_V2 = 20;
    public static final int AVSYNC_HEADER_START_CODE = 1431633921;
    public static final int AVSYNC_HEADER_START_CODE_V2 = 1431633922;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final ByteBuffer avSyncHeader;
    public final int avSyncHeaderSize;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public AvSyncHeaderFormatter() {
        int i = Preconditions2.isSdkIntAtLeast(28) ? 20 : 16;
        this.avSyncHeaderSize = i;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(...)");
        this.avSyncHeader = byteBufferAllocate;
    }

    @NotNull
    public final ByteBuffer getAvSyncHeader() {
        return this.avSyncHeader;
    }

    public final int getAvSyncHeaderSize() {
        return this.avSyncHeaderSize;
    }

    public final void setAvSyncHeader(int i, long j) {
        if (Preconditions2.isSdkIntAtLeast(28)) {
            setAvSyncHeaderV2(i, j);
        } else {
            setAvSyncHeaderV1(i, j);
        }
    }

    public final void setAvSyncHeaderV1(int i, long j) {
        this.avSyncHeader.clear();
        this.avSyncHeader.order(ByteOrder.BIG_ENDIAN);
        this.avSyncHeader.putInt(AVSYNC_HEADER_START_CODE);
        this.avSyncHeader.putInt(i);
        this.avSyncHeader.putLong(TimeUnit.MICROSECONDS.toNanos(j));
        this.avSyncHeader.clear();
    }

    public final void setAvSyncHeaderV2(int i, long j) {
        this.avSyncHeader.clear();
        this.avSyncHeader.order(ByteOrder.BIG_ENDIAN);
        this.avSyncHeader.putInt(AVSYNC_HEADER_START_CODE_V2);
        this.avSyncHeader.putInt(i);
        this.avSyncHeader.putLong(TimeUnit.MICROSECONDS.toNanos(j));
        this.avSyncHeader.putInt(20);
        this.avSyncHeader.clear();
    }
}
