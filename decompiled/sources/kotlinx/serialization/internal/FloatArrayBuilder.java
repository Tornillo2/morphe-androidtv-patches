package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class FloatArrayBuilder extends PrimitiveArrayBuilder<float[]> {

    @NotNull
    public float[] buffer;
    public int position;

    public FloatArrayBuilder(@NotNull float[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    public final void append$kotlinx_serialization_core(float f) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        float[] fArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        fArr[i] = f;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        float[] fArr = this.buffer;
        if (fArr.length < i) {
            int length = fArr.length * 2;
            if (i < length) {
                i = length;
            }
            float[] fArrCopyOf = Arrays.copyOf(fArr, i);
            Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
            this.buffer = fArrCopyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    @NotNull
    public float[] build$kotlinx_serialization_core() {
        float[] fArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        return fArrCopyOf;
    }
}
