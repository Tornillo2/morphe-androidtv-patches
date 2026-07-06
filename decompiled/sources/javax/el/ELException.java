package javax.el;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ELException extends RuntimeException {
    public static final long serialVersionUID = 1;

    public ELException() {
    }

    public ELException(String str) {
        super(str);
    }

    public ELException(Throwable th) {
        super(th);
    }

    public ELException(String str, Throwable th) {
        super(str, th);
    }
}
