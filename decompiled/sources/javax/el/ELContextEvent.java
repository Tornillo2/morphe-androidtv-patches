package javax.el;

import java.util.EventObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ELContextEvent extends EventObject {
    public static final long serialVersionUID = 1;

    public ELContextEvent(ELContext eLContext) {
        super(eLContext);
    }

    public ELContext getELContext() {
        return (ELContext) getSource();
    }
}
