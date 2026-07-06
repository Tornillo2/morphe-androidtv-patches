package kotlin.sequences;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SequencesKt__SequenceBuilderKt {
    public static final int State_Done = 4;
    public static final int State_Failed = 5;
    public static final int State_ManyNotReady = 1;
    public static final int State_ManyReady = 2;
    public static final int State_NotReady = 0;
    public static final int State_Ready = 3;

    @SinceKotlin(version = "1.3")
    @NotNull
    public static <T> Iterator<T> iterator(@BuilderInference @NotNull Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
        sequenceBuilderIterator.nextStep = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(block, sequenceBuilderIterator, sequenceBuilderIterator);
        return sequenceBuilderIterator;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static <T> Sequence<T> sequence(@BuilderInference @NotNull final Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                return SequencesKt__SequenceBuilderKt.iterator(block);
            }
        };
    }
}
