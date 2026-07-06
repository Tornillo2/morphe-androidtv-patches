package kotlinx.serialization.json.internal;

import kotlin.UByte;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElementKt$$ExternalSyntheticBackport1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SuppressAnimalSniffer
public final class ComposerForUnsignedNumbers extends Composer {
    public final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(@NotNull InternalJsonWriter writer, boolean z) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = z;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int i) {
        if (this.forceQuoting) {
            printQuoted(Long.toString(((long) i) & 4294967295L, 10));
        } else {
            print(Long.toString(((long) i) & 4294967295L, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long j) {
        if (this.forceQuoting) {
            printQuoted(JsonElementKt$$ExternalSyntheticBackport1.m(j, 10));
        } else {
            print(JsonElementKt$$ExternalSyntheticBackport1.m(j, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte b) {
        boolean z = this.forceQuoting;
        String strM641toStringimpl = UByte.m641toStringimpl(b);
        if (z) {
            printQuoted(strM641toStringimpl);
        } else {
            print(strM641toStringimpl);
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short s) {
        boolean z = this.forceQuoting;
        String strM904toStringimpl = UShort.m904toStringimpl(s);
        if (z) {
            printQuoted(strM904toStringimpl);
        } else {
            print(strM904toStringimpl);
        }
    }
}
