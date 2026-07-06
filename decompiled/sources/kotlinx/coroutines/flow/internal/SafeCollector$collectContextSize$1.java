package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SafeCollector$collectContextSize$1 extends Lambda implements Function2<Integer, CoroutineContext.Element, Integer> {
    public static final SafeCollector$collectContextSize$1 INSTANCE = new SafeCollector$collectContextSize$1(2);

    public SafeCollector$collectContextSize$1() {
        super(2);
    }

    @NotNull
    public final Integer invoke(int i, @NotNull CoroutineContext.Element element) {
        return Integer.valueOf(i + 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Integer invoke(Integer num, CoroutineContext.Element element) {
        return invoke(num.intValue(), element);
    }
}
