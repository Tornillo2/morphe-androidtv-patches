package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class SystemFactory {
    @Deprecated
    public static IonSystem newSystem() {
        return IonSystemBuilder.STANDARD.build();
    }

    @Deprecated
    public static IonSystem newSystem(IonCatalog ionCatalog) {
        return IonSystemBuilder.STANDARD.withCatalog(ionCatalog).build();
    }
}
