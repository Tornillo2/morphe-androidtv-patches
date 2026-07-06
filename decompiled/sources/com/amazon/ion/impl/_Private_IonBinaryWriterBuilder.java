package com.amazon.ion.impl;

import com.amazon.ion.IonBinaryWriter;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SubstituteSymbolTableException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.BlockedBuffer;
import com.amazon.ion.impl.bin.IonManagedBinaryWriter;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonWriterBuilderBase;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class _Private_IonBinaryWriterBuilder extends IonBinaryWriterBuilder {
    public final _Private_IonManagedBinaryWriterBuilder myBinaryWriterBuilder;
    public SymbolTable myInitialSymbolTable;
    public ValueFactory mySymtabValueFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Mutable extends _Private_IonBinaryWriterBuilder {
        public Mutable() {
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonBinaryWriterBuilder copy() {
            return new Mutable(this);
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public _Private_IonBinaryWriterBuilder immutable() {
            return new _Private_IonBinaryWriterBuilder(this);
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public _Private_IonBinaryWriterBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder
        public /* bridge */ /* synthetic */ IonBinaryWriterBuilder withFloatBinary32Disabled() {
            return withFloatBinary32Disabled();
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder
        public /* bridge */ /* synthetic */ IonBinaryWriterBuilder withFloatBinary32Enabled() {
            return withFloatBinary32Enabled();
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder
        public /* bridge */ /* synthetic */ IonBinaryWriterBuilder withInitialSymbolTable(SymbolTable symbolTable) {
            return withInitialSymbolTable(symbolTable);
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder
        public /* bridge */ /* synthetic */ IonBinaryWriterBuilder withLocalSymbolTableAppendDisabled() {
            return withLocalSymbolTableAppendDisabled();
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder
        public /* bridge */ /* synthetic */ IonBinaryWriterBuilder withLocalSymbolTableAppendEnabled() {
            return withLocalSymbolTableAppendEnabled();
        }

        public Mutable(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonBinaryWriterBuilder mutable() {
            return this;
        }

        public Mutable(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder) {
            super(_private_ionbinarywriterbuilder);
        }

        @Override // com.amazon.ion.impl._Private_IonBinaryWriterBuilder, com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonBinaryWriterBuilder immutable() {
            return new _Private_IonBinaryWriterBuilder(this);
        }

        public Mutable(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, AnonymousClass1 anonymousClass1) {
            super(_private_ionbinarywriterbuilder);
        }

        @Override // com.amazon.ion.system.IonWriterBuilderBase
        public void mutationCheck() {
        }
    }

    public static _Private_IonBinaryWriterBuilder standard() {
        return new Mutable();
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriter build(OutputStream outputStream) {
        try {
            _Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder = fillDefaults().myBinaryWriterBuilder;
            _private_ionmanagedbinarywriterbuilder.getClass();
            return new IonManagedBinaryWriter(_private_ionmanagedbinarywriterbuilder, outputStream);
        } catch (IOException e) {
            throw new IonException("I/O Error", e);
        }
    }

    public SymbolTable buildContextSymbolTable() {
        return this.myInitialSymbolTable.isReadOnly() ? this.myInitialSymbolTable : ((LocalSymbolTable) this.myInitialSymbolTable).makeCopy();
    }

    @Deprecated
    public final IonBinaryWriter buildLegacy() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderFillLegacyDefaults = fillLegacyDefaults();
        return new _Private_IonBinaryWriterImpl(_private_ionbinarywriterbuilderFillLegacyDefaults, _private_ionbinarywriterbuilderFillLegacyDefaults.buildSystemWriter(new BlockedBuffer.BufferedOutputStream()));
    }

    public final IonWriterSystemBinary buildSystemWriter(OutputStream outputStream) {
        return new IonWriterSystemBinary(this.myInitialSymbolTable.getSystemSymbolTable(), outputStream, false, true);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public final _Private_IonBinaryWriterBuilder copy() {
        return new Mutable(this);
    }

    public final _Private_IonBinaryWriterBuilder fillDefaults() {
        Mutable mutable = new Mutable(this);
        if (mutable.mySymtabValueFactory == null) {
            mutable.setSymtabValueFactory(IonSystemBuilder.STANDARD.build());
        }
        return new _Private_IonBinaryWriterBuilder(mutable);
    }

    public final _Private_IonBinaryWriterBuilder fillLegacyDefaults() {
        Mutable mutable = new Mutable(this);
        if (mutable.mySymtabValueFactory == null) {
            mutable.setSymtabValueFactory(IonSystemBuilder.STANDARD.build());
        }
        SymbolTable symbolTable = mutable.myInitialSymbolTable;
        if (symbolTable == null) {
            mutable.setInitialSymbolTable(_Private_Utils.initialSymtab(LocalSymbolTable.DEFAULT_LST_FACTORY, _Private_Utils.systemSymtab(1), IonWriterBuilderBase.safeCopy(mutable.myImports)));
        } else if (symbolTable.isSystemTable()) {
            mutable.setInitialSymbolTable(_Private_Utils.initialSymtab(LocalSymbolTable.DEFAULT_LST_FACTORY, symbolTable, IonWriterBuilderBase.safeCopy(mutable.myImports)));
        }
        return new _Private_IonBinaryWriterBuilder(mutable);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public SymbolTable getInitialSymbolTable() {
        return this.myInitialSymbolTable;
    }

    public ValueFactory getSymtabValueFactory() {
        return this.mySymtabValueFactory;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public _Private_IonBinaryWriterBuilder immutable() {
        return this;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
        this.myBinaryWriterBuilder.catalog = ionCatalog;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public void setImports(SymbolTable... symbolTableArr) {
        super.setImports(symbolTableArr);
        this.myBinaryWriterBuilder.withImports(symbolTableArr);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public void setInitialSymbolTable(SymbolTable symbolTable) {
        mutationCheck();
        if (symbolTable != null) {
            if (symbolTable.isLocalTable()) {
                for (SymbolTable symbolTable2 : ((LocalSymbolTable) symbolTable).getImportedTablesNoCopy()) {
                    if (symbolTable2.isSubstitute()) {
                        throw new SubstituteSymbolTableException("Cannot encode with substitute symbol table: " + symbolTable2.getName());
                    }
                }
            } else if (!symbolTable.isSystemTable()) {
                throw new IllegalArgumentException("symtab must be local or system table");
            }
        }
        this.myInitialSymbolTable = symbolTable;
        this.myBinaryWriterBuilder.withInitialSymbolTable(symbolTable);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public void setIsFloatBinary32Enabled(boolean z) {
        mutationCheck();
        if (z) {
            this.myBinaryWriterBuilder.isFloatBinary32Enabled = true;
        } else {
            this.myBinaryWriterBuilder.isFloatBinary32Enabled = false;
        }
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public void setLocalSymbolTableAppendEnabled(boolean z) {
        mutationCheck();
        if (z) {
            this.myBinaryWriterBuilder.isLocalSymbolTableAppendEnabled = true;
        } else {
            this.myBinaryWriterBuilder.isLocalSymbolTableAppendEnabled = false;
        }
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public void setStreamCopyOptimized(boolean z) {
        super.setStreamCopyOptimized(z);
        this.myBinaryWriterBuilder.withStreamCopyOptimization(z);
    }

    public void setSymtabValueFactory(ValueFactory valueFactory) {
        mutationCheck();
        this.mySymtabValueFactory = valueFactory;
    }

    public _Private_IonBinaryWriterBuilder withSymtabValueFactory(ValueFactory valueFactory) {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setSymtabValueFactory(valueFactory);
        return _private_ionbinarywriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public _Private_IonBinaryWriterBuilder mutable() {
        return new Mutable(this);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public _Private_IonBinaryWriterBuilder withFloatBinary32Disabled() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setIsFloatBinary32Enabled(false);
        return _private_ionbinarywriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public _Private_IonBinaryWriterBuilder withFloatBinary32Enabled() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setIsFloatBinary32Enabled(true);
        return _private_ionbinarywriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public _Private_IonBinaryWriterBuilder withInitialSymbolTable(SymbolTable symbolTable) {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setInitialSymbolTable(symbolTable);
        return _private_ionbinarywriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public _Private_IonBinaryWriterBuilder withLocalSymbolTableAppendDisabled() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setLocalSymbolTableAppendEnabled(false);
        return _private_ionbinarywriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder
    public _Private_IonBinaryWriterBuilder withLocalSymbolTableAppendEnabled() {
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderMutable = mutable();
        _private_ionbinarywriterbuilderMutable.setLocalSymbolTableAppendEnabled(true);
        return _private_ionbinarywriterbuilderMutable;
    }

    public _Private_IonBinaryWriterBuilder(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder) {
        super(_private_ionbinarywriterbuilder);
        this.mySymtabValueFactory = _private_ionbinarywriterbuilder.mySymtabValueFactory;
        this.myInitialSymbolTable = _private_ionbinarywriterbuilder.myInitialSymbolTable;
        _Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder = _private_ionbinarywriterbuilder.myBinaryWriterBuilder;
        _private_ionmanagedbinarywriterbuilder.getClass();
        this.myBinaryWriterBuilder = new _Private_IonManagedBinaryWriterBuilder(_private_ionmanagedbinarywriterbuilder);
    }

    @Override // com.amazon.ion.system.IonBinaryWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public IonBinaryWriterBuilder copy() {
        return new Mutable(this);
    }

    public _Private_IonBinaryWriterBuilder() {
        _Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilderCreate = _Private_IonManagedBinaryWriterBuilder.create(_Private_IonManagedBinaryWriterBuilder.AllocatorMode.POOLED);
        _private_ionmanagedbinarywriterbuilderCreate.preallocationMode = IonRawBinaryWriter.PreallocationMode.withPadSize(0);
        this.myBinaryWriterBuilder = _private_ionmanagedbinarywriterbuilderCreate;
    }
}
