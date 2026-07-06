package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.UByteArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@ExperimentalSerializationApi
@ExperimentalUnsignedTypes
public final class UByteArrayBuilder extends PrimitiveArrayBuilder<UByteArray> {

    @NotNull
    public byte[] buffer;
    public int position;

    public /* synthetic */ UByteArrayBuilder(byte[] bArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr);
    }

    /* JADX INFO: renamed from: append-7apg3OU$kotlinx_serialization_core, reason: not valid java name */
    public final void m2157append7apg3OU$kotlinx_serialization_core(byte b) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* synthetic */ UByteArray build$kotlinx_serialization_core() {
        return new UByteArray(m2158buildTcUX1vc$kotlinx_serialization_core());
    }

    @NotNull
    /* JADX INFO: renamed from: build-TcUX1vc$kotlinx_serialization_core, reason: not valid java name */
    public byte[] m2158buildTcUX1vc$kotlinx_serialization_core() {
        byte[] bArrCopyOf = Arrays.copyOf(this.buffer, this.position);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        byte[] bArr = this.buffer;
        if (bArr.length < i) {
            int length = bArr.length * 2;
            if (i < length) {
                i = length;
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr, i);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            this.buffer = bArrCopyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    public UByteArrayBuilder(byte[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }
}
