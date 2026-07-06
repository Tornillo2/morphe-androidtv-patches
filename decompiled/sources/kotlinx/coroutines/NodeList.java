package kotlinx.coroutines;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @NotNull
    public final String getString(@NotNull String str) {
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("List{", str, "}[");
        boolean z = true;
        for (LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual(nextNode, this); nextNode = nextNode.getNextNode()) {
            if (nextNode instanceof JobNode) {
                JobNode jobNode = (JobNode) nextNode;
                if (z) {
                    z = false;
                } else {
                    sbM.append(", ");
                }
                sbM.append(jobNode);
            }
        }
        sbM.append("]");
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return super.toString();
    }

    @Override // kotlinx.coroutines.Incomplete
    @NotNull
    public NodeList getList() {
        return this;
    }
}
