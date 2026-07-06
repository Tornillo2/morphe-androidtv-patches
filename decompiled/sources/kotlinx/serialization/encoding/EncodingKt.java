package kotlinx.serialization.encoding;

import android.R;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nEncoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,508:1\n489#1,2:509\n491#1,2:514\n1872#2,3:511\n*S KotlinDebug\n*F\n+ 1 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n*L\n502#1:509,2\n502#1:514,2\n503#1:511,3\n*E\n"})
public final class EncodingKt {
    public static final void encodeCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, int i, @NotNull Function1<? super CompositeEncoder, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, i);
        block.invoke(compositeEncoderBeginCollection);
        compositeEncoderBeginCollection.endStructure(descriptor);
    }

    public static final void encodeStructure(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, @NotNull Function1<? super CompositeEncoder, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        block.invoke(compositeEncoderBeginStructure);
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    public static final <E> void encodeCollection(@NotNull Encoder encoder, @NotNull SerialDescriptor descriptor, @NotNull Collection<? extends E> collection, @NotNull Function3<? super CompositeEncoder, ? super Integer, ? super E, Unit> block) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(collection, "collection");
        Intrinsics.checkNotNullParameter(block, "block");
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, collection.size());
        Iterator<T> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            R.color colorVar = (Object) it.next();
            int i2 = i + 1;
            if (i >= 0) {
                block.invoke(compositeEncoderBeginCollection, Integer.valueOf(i), colorVar);
                i = i2;
            } else {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
        compositeEncoderBeginCollection.endStructure(descriptor);
    }
}
