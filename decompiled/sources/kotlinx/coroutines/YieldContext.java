package kotlinx.coroutines;

import kotlin.PublishedApi;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@PublishedApi
public final class YieldContext extends AbstractCoroutineContextElement {

    @NotNull
    public static final Key Key = new Key();

    @JvmField
    public boolean dispatcherWasUnconfined;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Key implements CoroutineContext.Key<YieldContext> {
        public Key() {
        }

        public Key(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public YieldContext() {
        super(Key);
    }
}
