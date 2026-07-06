package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonWriter;
import com.amazon.ion.impl.lite.IonSystemLite;
import com.amazon.ion.system.IonWriterBuilder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_IonWriterFactory {
    public static IonWriter makeSystemWriter(IonContainer ionContainer) {
        IonSystem system = ionContainer.getSystem();
        return new IonWriterSystemTree(((IonSystemLite) system)._system_symbol_table, ((IonSystemLite) system)._catalog, ionContainer, null);
    }

    public static IonWriter makeWriter(IonContainer ionContainer) {
        return makeWriter(((IonSystemLite) ionContainer.getSystem())._catalog, ionContainer);
    }

    public static IonWriter makeWriter(IonCatalog ionCatalog, IonContainer ionContainer) {
        IonSystem system = ionContainer.getSystem();
        return new IonWriterUser(ionCatalog, system, new IonWriterSystemTree(((IonSystemLite) system)._system_symbol_table, ionCatalog, ionContainer, IonWriterBuilder.InitialIvmHandling.SUPPRESS));
    }
}
