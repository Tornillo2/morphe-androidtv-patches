package edu.umd.cs.findbugs.annotations;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public enum Priority {
    HIGH(1),
    MEDIUM(2),
    LOW(3),
    IGNORE(5);

    public final int priorityValue;

    Priority(int i) {
        this.priorityValue = i;
    }

    public int getPriorityValue() {
        return this.priorityValue;
    }
}
