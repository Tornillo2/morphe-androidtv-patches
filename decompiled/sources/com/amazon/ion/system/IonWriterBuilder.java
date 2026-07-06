package com.amazon.ion.system;

import com.amazon.ion.IonWriter;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonWriterBuilder {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum InitialIvmHandling {
        ENSURE,
        SUPPRESS
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum IvmMinimizing {
        ADJACENT,
        DISTANT
    }

    public abstract IonWriter build(OutputStream outputStream);

    public abstract InitialIvmHandling getInitialIvmHandling();

    public abstract IvmMinimizing getIvmMinimizing();
}
