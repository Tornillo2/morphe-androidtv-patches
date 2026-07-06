package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class BooleanArrayBuilder extends PrimitiveArrayBuilder<boolean[]> {

    @NotNull
    public boolean[] buffer;
    public int position;

    public BooleanArrayBuilder(@NotNull boolean[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    public final void append$kotlinx_serialization_core(boolean z) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        boolean[] zArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        zArr[i] = z;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        boolean[] zArr = this.buffer;
        if (zArr.length < i) {
            int length = zArr.length * 2;
            if (i < length) {
                i = length;
            }
            boolean[] zArrCopyOf = Arrays.copyOf(zArr, i);
            Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "copyOf(...)");
            this.buffer = zArrCopyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    @NotNull
    public boolean[] build$kotlinx_serialization_core() {
        boolean[] zArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "copyOf(...)");
        return zArrCopyOf;
    }
}
