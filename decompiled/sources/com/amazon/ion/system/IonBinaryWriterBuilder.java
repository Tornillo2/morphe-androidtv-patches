package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.system.IonWriterBuilder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonBinaryWriterBuilder extends IonWriterBuilderBase<IonBinaryWriterBuilder> {
    public boolean myStreamCopyOptimized;

    public IonBinaryWriterBuilder(IonBinaryWriterBuilder ionBinaryWriterBuilder) {
        super(ionBinaryWriterBuilder);
        this.myStreamCopyOptimized = ionBinaryWriterBuilder.myStreamCopyOptimized;
    }

    public static IonBinaryWriterBuilder standard() {
        return new _Private_IonBinaryWriterBuilder.Mutable();
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonBinaryWriterBuilder copy();

    @Override // com.amazon.ion.system.IonWriterBuilder
    public IonWriterBuilder.InitialIvmHandling getInitialIvmHandling() {
        return IonWriterBuilder.InitialIvmHandling.ENSURE;
    }

    public abstract SymbolTable getInitialSymbolTable();

    @Override // com.amazon.ion.system.IonWriterBuilder
    public IonWriterBuilder.IvmMinimizing getIvmMinimizing() {
        return null;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonBinaryWriterBuilder immutable();

    public boolean isStreamCopyOptimized() {
        return this.myStreamCopyOptimized;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonBinaryWriterBuilder mutable();

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setImports(SymbolTable[] symbolTableArr) {
        super.setImports(symbolTableArr);
    }

    public abstract void setInitialSymbolTable(SymbolTable symbolTable);

    public abstract void setIsFloatBinary32Enabled(boolean z);

    public abstract void setLocalSymbolTableAppendEnabled(boolean z);

    public void setStreamCopyOptimized(boolean z) {
        mutationCheck();
        this.myStreamCopyOptimized = z;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public final IonBinaryWriterBuilder withCatalog(IonCatalog ionCatalog) {
        return (IonBinaryWriterBuilder) super.withCatalog(ionCatalog);
    }

    public abstract IonBinaryWriterBuilder withFloatBinary32Disabled();

    public abstract IonBinaryWriterBuilder withFloatBinary32Enabled();

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public final IonBinaryWriterBuilder withImports(SymbolTable... symbolTableArr) {
        return (IonBinaryWriterBuilder) super.withImports(symbolTableArr);
    }

    public abstract IonBinaryWriterBuilder withInitialSymbolTable(SymbolTable symbolTable);

    public abstract IonBinaryWriterBuilder withLocalSymbolTableAppendDisabled();

    public abstract IonBinaryWriterBuilder withLocalSymbolTableAppendEnabled();

    public final IonBinaryWriterBuilder withStreamCopyOptimized(boolean z) {
        IonBinaryWriterBuilder ionBinaryWriterBuilderMutable = mutable();
        ionBinaryWriterBuilderMutable.setStreamCopyOptimized(z);
        return ionBinaryWriterBuilderMutable;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public IonWriterBuilderBase withCatalog(IonCatalog ionCatalog) {
        return (IonBinaryWriterBuilder) super.withCatalog(ionCatalog);
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public IonWriterBuilderBase withImports(SymbolTable[] symbolTableArr) {
        return (IonBinaryWriterBuilder) super.withImports(symbolTableArr);
    }

    public IonBinaryWriterBuilder() {
    }
}
