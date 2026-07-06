package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class ShortArrayBuilder extends PrimitiveArrayBuilder<short[]> {

    @NotNull
    public short[] buffer;
    public int position;

    public ShortArrayBuilder(@NotNull short[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    public final void append$kotlinx_serialization_core(short s) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        short[] sArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        sArr[i] = s;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        short[] sArr = this.buffer;
        if (sArr.length < i) {
            int length = sArr.length * 2;
            if (i < length) {
                i = length;
            }
            short[] sArrCopyOf = Arrays.copyOf(sArr, i);
            Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
            this.buffer = sArrCopyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    @NotNull
    public short[] build$kotlinx_serialization_core() {
        short[] sArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }
}
