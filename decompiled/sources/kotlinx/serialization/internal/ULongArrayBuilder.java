package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.ULongArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@ExperimentalSerializationApi
@ExperimentalUnsignedTypes
public final class ULongArrayBuilder extends PrimitiveArrayBuilder<ULongArray> {

    @NotNull
    public long[] buffer;
    public int position;

    public /* synthetic */ ULongArrayBuilder(long[] jArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(jArr);
    }

    /* JADX INFO: renamed from: append-VKZWuLQ$kotlinx_serialization_core, reason: not valid java name */
    public final void m2173appendVKZWuLQ$kotlinx_serialization_core(long j) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        long[] jArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        jArr[i] = j;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* synthetic */ ULongArray build$kotlinx_serialization_core() {
        return new ULongArray(m2174buildY2RjT0g$kotlinx_serialization_core());
    }

    @NotNull
    /* JADX INFO: renamed from: build-Y2RjT0g$kotlinx_serialization_core, reason: not valid java name */
    public long[] m2174buildY2RjT0g$kotlinx_serialization_core() {
        long[] jArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        long[] jArr = this.buffer;
        if (jArr.length < i) {
            int length = jArr.length * 2;
            if (i < length) {
                i = length;
            }
            long[] jArrCopyOf = Arrays.copyOf(jArr, i);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
            this.buffer = jArrCopyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    public ULongArrayBuilder(long[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }
}
