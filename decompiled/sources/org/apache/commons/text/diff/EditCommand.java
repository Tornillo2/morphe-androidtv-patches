package org.apache.commons.text.diff;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class EditCommand<T> {
    public final T object;

    public EditCommand(T t) {
        this.object = t;
    }

    public abstract void accept(CommandVisitor<T> commandVisitor);

    public T getObject() {
        return this.object;
    }
}
