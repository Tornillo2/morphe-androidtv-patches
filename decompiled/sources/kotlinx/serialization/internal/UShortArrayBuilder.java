package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.UShortArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@ExperimentalSerializationApi
@ExperimentalUnsignedTypes
public final class UShortArrayBuilder extends PrimitiveArrayBuilder<UShortArray> {

    @NotNull
    public short[] buffer;
    public int position;

    public /* synthetic */ UShortArrayBuilder(short[] sArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(sArr);
    }

    /* JADX INFO: renamed from: append-xj2QHRw$kotlinx_serialization_core, reason: not valid java name */
    public final void m2181appendxj2QHRw$kotlinx_serialization_core(short s) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        short[] sArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        sArr[i] = s;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* synthetic */ UShortArray build$kotlinx_serialization_core() {
        return new UShortArray(m2182buildamswpOA$kotlinx_serialization_core());
    }

    @NotNull
    /* JADX INFO: renamed from: build-amswpOA$kotlinx_serialization_core, reason: not valid java name */
    public short[] m2182buildamswpOA$kotlinx_serialization_core() {
        short[] sArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
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

    public UShortArrayBuilder(short[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }
}
