package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl._Private_IonTextWriterBuilder;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.system.IonWriterBuilder;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonTextWriterBuilder extends IonWriterBuilderBase<IonTextWriterBuilder> {
    public static final Charset ASCII = _Private_Utils.ASCII_CHARSET;
    public static final Charset UTF8 = _Private_Utils.UTF8_CHARSET;
    public Charset myCharset;
    public IonWriterBuilder.InitialIvmHandling myInitialIvmHandling;
    public IonWriterBuilder.IvmMinimizing myIvmMinimizing;
    public int myLongStringThreshold;
    public LstMinimizing myLstMinimizing;
    public NewLineType myNewLineType;
    public boolean myTopLevelValuesOnNewLines;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LstMinimizing {
        LOCALS,
        EVERYTHING
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NewLineType {
        CRLF("\r\n"),
        LF(StringUtils.LF),
        PLATFORM_DEPENDENT(System.getProperty("line.separator"));

        public final CharSequence charSequence;

        NewLineType(CharSequence charSequence) {
            this.charSequence = charSequence;
        }

        public CharSequence getCharSequence() {
            return this.charSequence;
        }
    }

    public IonTextWriterBuilder() {
    }

    public static IonTextWriterBuilder json() {
        return _Private_IonTextWriterBuilder.standard().withJsonDowngrade();
    }

    public static IonTextWriterBuilder minimal() {
        return _Private_IonTextWriterBuilder.standard().withMinimalSystemData();
    }

    public static IonTextWriterBuilder pretty() {
        return _Private_IonTextWriterBuilder.standard().withPrettyPrinting();
    }

    public static IonTextWriterBuilder standard() {
        return _Private_IonTextWriterBuilder.standard();
    }

    public abstract IonWriter build(Appendable appendable);

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonTextWriterBuilder copy();

    public final Charset getCharset() {
        return this.myCharset;
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriterBuilder.InitialIvmHandling getInitialIvmHandling() {
        return this.myInitialIvmHandling;
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriterBuilder.IvmMinimizing getIvmMinimizing() {
        return this.myIvmMinimizing;
    }

    public final int getLongStringThreshold() {
        return this.myLongStringThreshold;
    }

    public final LstMinimizing getLstMinimizing() {
        return this.myLstMinimizing;
    }

    public final NewLineType getNewLineType() {
        return this.myNewLineType;
    }

    public final boolean getWriteTopLevelValuesOnNewLines() {
        return this.myTopLevelValuesOnNewLines;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonTextWriterBuilder immutable();

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public abstract IonTextWriterBuilder mutable();

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
    }

    public void setCharset(Charset charset) {
        mutationCheck();
        if (charset == null || charset.equals(ASCII) || charset.equals(UTF8)) {
            this.myCharset = charset;
        } else {
            throw new IllegalArgumentException("Unsupported Charset " + charset);
        }
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setImports(SymbolTable[] symbolTableArr) {
        super.setImports(symbolTableArr);
    }

    public void setInitialIvmHandling(IonWriterBuilder.InitialIvmHandling initialIvmHandling) {
        mutationCheck();
        this.myInitialIvmHandling = initialIvmHandling;
    }

    public void setIvmMinimizing(IonWriterBuilder.IvmMinimizing ivmMinimizing) {
        mutationCheck();
        this.myIvmMinimizing = ivmMinimizing;
    }

    public void setLongStringThreshold(int i) {
        mutationCheck();
        this.myLongStringThreshold = i;
    }

    public void setLstMinimizing(LstMinimizing lstMinimizing) {
        mutationCheck();
        this.myLstMinimizing = lstMinimizing;
    }

    public void setNewLineType(NewLineType newLineType) {
        mutationCheck();
        this.myNewLineType = newLineType;
    }

    public void setWriteTopLevelValuesOnNewLines(boolean z) {
        mutationCheck();
        this.myTopLevelValuesOnNewLines = z;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public final IonTextWriterBuilder withCatalog(IonCatalog ionCatalog) {
        return (IonTextWriterBuilder) super.withCatalog(ionCatalog);
    }

    public final IonTextWriterBuilder withCharset(Charset charset) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setCharset(charset);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withCharsetAscii() {
        return withCharset(ASCII);
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public final IonTextWriterBuilder withImports(SymbolTable... symbolTableArr) {
        return (IonTextWriterBuilder) super.withImports(symbolTableArr);
    }

    public final IonTextWriterBuilder withInitialIvmHandling(IonWriterBuilder.InitialIvmHandling initialIvmHandling) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setInitialIvmHandling(initialIvmHandling);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withIvmMinimizing(IonWriterBuilder.IvmMinimizing ivmMinimizing) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setIvmMinimizing(ivmMinimizing);
        return ionTextWriterBuilderMutable;
    }

    public abstract IonTextWriterBuilder withJsonDowngrade();

    public final IonTextWriterBuilder withLongStringThreshold(int i) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setLongStringThreshold(i);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withLstMinimizing(LstMinimizing lstMinimizing) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setLstMinimizing(lstMinimizing);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withMinimalSystemData() {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setInitialIvmHandling(IonWriterBuilder.InitialIvmHandling.SUPPRESS);
        ionTextWriterBuilderMutable.setIvmMinimizing(IonWriterBuilder.IvmMinimizing.DISTANT);
        ionTextWriterBuilderMutable.setLstMinimizing(LstMinimizing.EVERYTHING);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withNewLineType(NewLineType newLineType) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setNewLineType(newLineType);
        return ionTextWriterBuilderMutable;
    }

    public abstract IonTextWriterBuilder withPrettyPrinting();

    public final IonTextWriterBuilder withWriteTopLevelValuesOnNewLines(boolean z) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setWriteTopLevelValuesOnNewLines(z);
        return ionTextWriterBuilderMutable;
    }

    public IonTextWriterBuilder(IonTextWriterBuilder ionTextWriterBuilder) {
        super(ionTextWriterBuilder);
        this.myCharset = ionTextWriterBuilder.myCharset;
        this.myInitialIvmHandling = ionTextWriterBuilder.myInitialIvmHandling;
        this.myIvmMinimizing = ionTextWriterBuilder.myIvmMinimizing;
        this.myLstMinimizing = ionTextWriterBuilder.myLstMinimizing;
        this.myLongStringThreshold = ionTextWriterBuilder.myLongStringThreshold;
        this.myNewLineType = ionTextWriterBuilder.myNewLineType;
        this.myTopLevelValuesOnNewLines = ionTextWriterBuilder.myTopLevelValuesOnNewLines;
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public IonWriterBuilderBase withCatalog(IonCatalog ionCatalog) {
        return (IonTextWriterBuilder) super.withCatalog(ionCatalog);
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public IonWriterBuilderBase withImports(SymbolTable[] symbolTableArr) {
        return (IonTextWriterBuilder) super.withImports(symbolTableArr);
    }
}
