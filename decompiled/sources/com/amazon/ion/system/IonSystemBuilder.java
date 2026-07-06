package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;
import com.amazon.ion.impl.SharedSymbolTable;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import com.amazon.ion.impl.lite.IonSystemLite;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonSystemBuilder {
    public static final IonSystemBuilder STANDARD = new IonSystemBuilder();
    public IonCatalog myCatalog;
    public boolean myStreamCopyOptimized;
    public IonReaderBuilder readerBuilder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Mutable extends IonSystemBuilder {
        public Mutable(IonSystemBuilder ionSystemBuilder) {
            super(ionSystemBuilder);
        }

        @Override // com.amazon.ion.system.IonSystemBuilder
        public IonSystemBuilder immutable() {
            return new IonSystemBuilder(this);
        }

        public Mutable(IonSystemBuilder ionSystemBuilder, AnonymousClass1 anonymousClass1) {
            super(ionSystemBuilder);
        }

        @Override // com.amazon.ion.system.IonSystemBuilder
        public IonSystemBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.system.IonSystemBuilder
        public void mutationCheck() {
        }
    }

    public static IonSystemBuilder standard() {
        return STANDARD;
    }

    public final IonSystem build() {
        IonCatalog simpleCatalog = this.myCatalog;
        if (simpleCatalog == null) {
            simpleCatalog = new SimpleCatalog();
        }
        IonTextWriterBuilder ionTextWriterBuilderWithCharset = IonTextWriterBuilder.standard().withCharset(IonTextWriterBuilder.ASCII);
        ionTextWriterBuilderWithCharset.setCatalog(simpleCatalog);
        _Private_IonBinaryWriterBuilder.Mutable mutable = new _Private_IonBinaryWriterBuilder.Mutable();
        mutable.setCatalog(simpleCatalog);
        mutable.setStreamCopyOptimized(this.myStreamCopyOptimized);
        mutable.setInitialSymbolTable(SharedSymbolTable.getSystemSymbolTable(1));
        IonReaderBuilder mutable2 = this.readerBuilder;
        if (mutable2 == null) {
            mutable2 = new _Private_IonReaderBuilder.Mutable();
        }
        return new IonSystemLite(ionTextWriterBuilderWithCharset, mutable, mutable2.withCatalog(simpleCatalog));
    }

    public final IonSystemBuilder copy() {
        return new Mutable(this);
    }

    public final IonCatalog getCatalog() {
        return this.myCatalog;
    }

    public final IonReaderBuilder getReaderBuilder() {
        return this.readerBuilder;
    }

    public final boolean isStreamCopyOptimized() {
        return this.myStreamCopyOptimized;
    }

    public IonSystemBuilder mutable() {
        return new Mutable(this);
    }

    public void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public final void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.myCatalog = ionCatalog;
    }

    public final void setReaderBuilder(IonReaderBuilder ionReaderBuilder) {
        mutationCheck();
        this.readerBuilder = ionReaderBuilder;
    }

    public final void setStreamCopyOptimized(boolean z) {
        mutationCheck();
        this.myStreamCopyOptimized = z;
    }

    public final IonSystemBuilder withCatalog(IonCatalog ionCatalog) {
        IonSystemBuilder ionSystemBuilderMutable = mutable();
        ionSystemBuilderMutable.setCatalog(ionCatalog);
        return ionSystemBuilderMutable;
    }

    public final IonSystemBuilder withReaderBuilder(IonReaderBuilder ionReaderBuilder) {
        IonSystemBuilder ionSystemBuilderMutable = mutable();
        ionSystemBuilderMutable.setReaderBuilder(ionReaderBuilder);
        return ionSystemBuilderMutable;
    }

    public final IonSystemBuilder withStreamCopyOptimized(boolean z) {
        IonSystemBuilder ionSystemBuilderMutable = mutable();
        ionSystemBuilderMutable.setStreamCopyOptimized(z);
        return ionSystemBuilderMutable;
    }

    public IonSystemBuilder() {
        this.myStreamCopyOptimized = false;
    }

    public IonSystemBuilder(IonSystemBuilder ionSystemBuilder) {
        this.myStreamCopyOptimized = false;
        this.myCatalog = ionSystemBuilder.myCatalog;
        this.myStreamCopyOptimized = ionSystemBuilder.myStreamCopyOptimized;
        this.readerBuilder = ionSystemBuilder.readerBuilder;
    }

    public IonSystemBuilder immutable() {
        return this;
    }
}
