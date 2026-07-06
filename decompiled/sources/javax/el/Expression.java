package javax.el;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Expression implements Serializable {
    public static final long serialVersionUID = 1;

    public abstract boolean equals(Object obj);

    public abstract String getExpressionString();

    public abstract int hashCode();

    public abstract boolean isLiteralText();
}
