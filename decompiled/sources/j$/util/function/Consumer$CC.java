package j$.util.function;

import j$.time.format.v;
import j$.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: j$.util.function.Consumer$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class Consumer$CC {
    public static Consumer $default$andThen(Consumer consumer, Consumer consumer2) {
        Objects.requireNonNull(consumer2);
        return new v(4, consumer, consumer2);
    }
}
