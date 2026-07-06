package com.amazon.ion.impl.bin;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.IonBinaryWriter;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SubstituteSymbolTableException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl.bin.AbstractIonWriter;
import com.amazon.ion.impl.bin.IonBinaryWriterAdapter;
import com.amazon.ion.impl.bin.IonManagedBinaryWriter;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.amazon.ion.system.SimpleCatalog;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_IonManagedBinaryWriterBuilder {
    public static final int DEFAULT_BLOCK_SIZE = 32768;
    public volatile IonCatalog catalog;
    public volatile IonManagedBinaryWriter.ImportedSymbolContext imports;
    public volatile SymbolTable initialSymbolTable;
    public volatile boolean isFloatBinary32Enabled;
    public volatile boolean isLocalSymbolTableAppendEnabled;
    public volatile AbstractIonWriter.WriteValueOptimization optimization;
    public volatile IonRawBinaryWriter.PreallocationMode preallocationMode;
    public final BlockAllocatorProvider provider;
    public volatile int symbolsBlockSize;
    public volatile int userBlockSize;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AllocatorMode {
        POOLED { // from class: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode.1
            @Override // com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode
            public BlockAllocatorProvider createAllocatorProvider() {
                return PooledBlockAllocatorProvider.getInstance();
            }
        },
        BASIC { // from class: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode.2
            @Override // com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode
            public BlockAllocatorProvider createAllocatorProvider() {
                return BlockAllocatorProviders.BASIC_PROVIDER;
            }
        };

        public abstract BlockAllocatorProvider createAllocatorProvider();

        AllocatorMode(AnonymousClass1 anonymousClass1) {
        }
    }

    public _Private_IonManagedBinaryWriterBuilder(BlockAllocatorProvider blockAllocatorProvider) {
        this.provider = blockAllocatorProvider;
        this.symbolsBlockSize = 32768;
        this.userBlockSize = 32768;
        this.imports = IonManagedBinaryWriter.ONLY_SYSTEM_IMPORTS;
        this.preallocationMode = IonRawBinaryWriter.PreallocationMode.PREALLOCATE_2;
        this.catalog = new SimpleCatalog();
        this.optimization = AbstractIonWriter.WriteValueOptimization.NONE;
        this.isLocalSymbolTableAppendEnabled = false;
        this.isFloatBinary32Enabled = false;
    }

    public static _Private_IonManagedBinaryWriterBuilder create(AllocatorMode allocatorMode) {
        return new _Private_IonManagedBinaryWriterBuilder(allocatorMode.createAllocatorProvider());
    }

    public _Private_IonManagedBinaryWriterBuilder copy() {
        return new _Private_IonManagedBinaryWriterBuilder(this);
    }

    public IonBinaryWriter newLegacyWriter() {
        try {
            return new IonBinaryWriterAdapter(new IonBinaryWriterAdapter.Factory() { // from class: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.1
                @Override // com.amazon.ion.impl.bin.IonBinaryWriterAdapter.Factory
                public IonWriter create(OutputStream outputStream) throws IOException {
                    _Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder = _Private_IonManagedBinaryWriterBuilder.this;
                    _private_ionmanagedbinarywriterbuilder.getClass();
                    return new IonManagedBinaryWriter(_private_ionmanagedbinarywriterbuilder, outputStream);
                }
            });
        } catch (IOException e) {
            throw new IonException("I/O error", e);
        }
    }

    public IonWriter newWriter(OutputStream outputStream) throws IOException {
        return new IonManagedBinaryWriter(this, outputStream);
    }

    public _Private_IonManagedBinaryWriterBuilder withCatalog(IonCatalog ionCatalog) {
        this.catalog = ionCatalog;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withFlatImports(SymbolTable... symbolTableArr) {
        if (symbolTableArr != null) {
            withImports(IonManagedBinaryWriter.ImportedSymbolResolverMode.FLAT, Arrays.asList(symbolTableArr));
        }
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withFloatBinary32Disabled() {
        this.isFloatBinary32Enabled = false;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withFloatBinary32Enabled() {
        this.isFloatBinary32Enabled = true;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withImports(SymbolTable... symbolTableArr) {
        if (symbolTableArr != null) {
            withImports(IonManagedBinaryWriter.ImportedSymbolResolverMode.DELEGATE, Arrays.asList(symbolTableArr));
        }
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withInitialSymbolTable(SymbolTable symbolTable) {
        if (symbolTable != null) {
            if (!symbolTable.isLocalTable() && !symbolTable.isSystemTable()) {
                throw new IllegalArgumentException("Initial symbol table must be local or system");
            }
            if (!symbolTable.isSystemTable()) {
                for (SymbolTable symbolTable2 : symbolTable.getImportedTables()) {
                    if (symbolTable2.isSubstitute()) {
                        throw new SubstituteSymbolTableException("Cannot use initial symbol table with imported substitutes");
                    }
                }
            } else {
                if (symbolTable.getMaxId() != 9) {
                    throw new IllegalArgumentException("Unsupported system symbol table");
                }
                symbolTable = null;
            }
        }
        this.initialSymbolTable = symbolTable;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withLocalSymbolTableAppendDisabled() {
        this.isLocalSymbolTableAppendEnabled = false;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withLocalSymbolTableAppendEnabled() {
        this.isLocalSymbolTableAppendEnabled = true;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withPaddedLengthPreallocation(int i) {
        this.preallocationMode = IonRawBinaryWriter.PreallocationMode.withPadSize(i);
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withPreallocationMode(IonRawBinaryWriter.PreallocationMode preallocationMode) {
        this.preallocationMode = preallocationMode;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withStreamCopyOptimization(boolean z) {
        this.optimization = z ? AbstractIonWriter.WriteValueOptimization.COPY_OPTIMIZED : AbstractIonWriter.WriteValueOptimization.NONE;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withSymbolsBlockSize(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Block size cannot be less than 1: ", i));
        }
        this.symbolsBlockSize = i;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withUserBlockSize(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Block size cannot be less than 1: ", i));
        }
        this.userBlockSize = i;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withFlatImports(List<SymbolTable> list) {
        withImports(IonManagedBinaryWriter.ImportedSymbolResolverMode.FLAT, list);
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withImports(List<SymbolTable> list) {
        withImports(IonManagedBinaryWriter.ImportedSymbolResolverMode.DELEGATE, list);
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withImports(IonManagedBinaryWriter.ImportedSymbolResolverMode importedSymbolResolverMode, List<SymbolTable> list) {
        this.imports = new IonManagedBinaryWriter.ImportedSymbolContext(importedSymbolResolverMode, list);
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder(_Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder) {
        this.provider = _private_ionmanagedbinarywriterbuilder.provider;
        this.symbolsBlockSize = _private_ionmanagedbinarywriterbuilder.symbolsBlockSize;
        this.userBlockSize = _private_ionmanagedbinarywriterbuilder.userBlockSize;
        this.preallocationMode = _private_ionmanagedbinarywriterbuilder.preallocationMode;
        this.imports = _private_ionmanagedbinarywriterbuilder.imports;
        this.catalog = _private_ionmanagedbinarywriterbuilder.catalog;
        this.optimization = _private_ionmanagedbinarywriterbuilder.optimization;
        this.initialSymbolTable = _private_ionmanagedbinarywriterbuilder.initialSymbolTable;
        this.isLocalSymbolTableAppendEnabled = _private_ionmanagedbinarywriterbuilder.isLocalSymbolTableAppendEnabled;
        this.isFloatBinary32Enabled = _private_ionmanagedbinarywriterbuilder.isFloatBinary32Enabled;
    }
}
