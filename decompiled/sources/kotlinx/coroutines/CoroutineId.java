package kotlinx.coroutines;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@IgnoreJRERequirement
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement<String> {

    @NotNull
    public static final Key Key = new Key();
    public final long id;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Key implements CoroutineContext.Key<CoroutineId> {
        public Key() {
        }

        public Key(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public CoroutineId(long j) {
        super(Key);
        this.id = j;
    }

    public static CoroutineId copy$default(CoroutineId coroutineId, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineId.id;
        }
        coroutineId.getClass();
        return new CoroutineId(j);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final CoroutineId copy(long j) {
        return new CoroutineId(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoroutineId) && this.id == ((CoroutineId) obj).id;
    }

    public final long getId() {
        return this.id;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.id);
    }

    @NotNull
    public String toString() {
        return "CoroutineId(" + this.id + ')';
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public void restoreThreadContext(@NotNull CoroutineContext coroutineContext, @NotNull String str) {
        Thread.currentThread().setName(str);
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    @NotNull
    public String updateThreadContext(@NotNull CoroutineContext coroutineContext) {
        String str;
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        if (coroutineName == null || (str = coroutineName.name) == null) {
            str = "coroutine";
        }
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, CoroutineContextKt.DEBUG_THREAD_NAME_SEPARATOR, 0, false, 6, (Object) null);
        if (iLastIndexOf$default < 0) {
            iLastIndexOf$default = name.length();
        }
        StringBuilder sb = new StringBuilder(str.length() + iLastIndexOf$default + 10);
        String strSubstring = name.substring(0, iLastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(strSubstring);
        sb.append(CoroutineContextKt.DEBUG_THREAD_NAME_SEPARATOR);
        sb.append(str);
        sb.append('#');
        sb.append(this.id);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        threadCurrentThread.setName(string);
        return name;
    }
}
