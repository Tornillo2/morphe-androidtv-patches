package com.amazon.ion.system;

import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonValue;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonReaderBuilder {
    public IonBufferConfiguration bufferConfiguration;
    public IonCatalog catalog;
    public boolean isAnnotationIteratorReuseEnabled;
    public boolean isIncrementalReadingEnabled;

    public IonReaderBuilder() {
        this.catalog = null;
        this.isIncrementalReadingEnabled = false;
        this.bufferConfiguration = null;
        this.isAnnotationIteratorReuseEnabled = true;
    }

    public static IonReaderBuilder standard() {
        return new _Private_IonReaderBuilder.Mutable();
    }

    public abstract IonReader build(IonValue ionValue);

    public abstract IonReader build(InputStream inputStream);

    public abstract IonReader build(Reader reader);

    public IonReader build(byte[] bArr) {
        return build(bArr, 0, bArr.length);
    }

    public abstract IonReader build(byte[] bArr, int i, int i2);

    public abstract IonTextReader build(String str);

    public IonReaderBuilder copy() {
        return new _Private_IonReaderBuilder.Mutable(this);
    }

    public IonBufferConfiguration getBufferConfiguration() {
        return this.bufferConfiguration;
    }

    public IonCatalog getCatalog() {
        return this.catalog;
    }

    public boolean isAnnotationIteratorReuseEnabled() {
        return this.isAnnotationIteratorReuseEnabled;
    }

    public boolean isIncrementalReadingEnabled() {
        return this.isIncrementalReadingEnabled;
    }

    public IonReaderBuilder mutable() {
        return copy();
    }

    public void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public void setAnnotationIteratorReuseDisabled() {
        mutationCheck();
        this.isAnnotationIteratorReuseEnabled = false;
    }

    public void setAnnotationIteratorReuseEnabled() {
        mutationCheck();
        this.isAnnotationIteratorReuseEnabled = true;
    }

    public void setBufferConfiguration(IonBufferConfiguration ionBufferConfiguration) {
        mutationCheck();
        this.bufferConfiguration = ionBufferConfiguration;
    }

    public void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.catalog = ionCatalog;
    }

    public void setIncrementalReadingDisabled() {
        mutationCheck();
        this.isIncrementalReadingEnabled = false;
    }

    public void setIncrementalReadingEnabled() {
        mutationCheck();
        this.isIncrementalReadingEnabled = true;
    }

    public IonCatalog validateCatalog() {
        IonCatalog ionCatalog = this.catalog;
        return ionCatalog != null ? ionCatalog : new SimpleCatalog();
    }

    public IonReaderBuilder withAnnotationIteratorReuseEnabled(boolean z) {
        IonReaderBuilder ionReaderBuilderMutable = mutable();
        if (z) {
            ionReaderBuilderMutable.setAnnotationIteratorReuseEnabled();
            return ionReaderBuilderMutable;
        }
        ionReaderBuilderMutable.setAnnotationIteratorReuseDisabled();
        return ionReaderBuilderMutable;
    }

    public IonReaderBuilder withBufferConfiguration(IonBufferConfiguration ionBufferConfiguration) {
        IonReaderBuilder ionReaderBuilderMutable = mutable();
        ionReaderBuilderMutable.setBufferConfiguration(ionBufferConfiguration);
        return ionReaderBuilderMutable;
    }

    public IonReaderBuilder withCatalog(IonCatalog ionCatalog) {
        IonReaderBuilder ionReaderBuilderMutable = mutable();
        ionReaderBuilderMutable.setCatalog(ionCatalog);
        return ionReaderBuilderMutable;
    }

    public IonReaderBuilder withIncrementalReadingEnabled(boolean z) {
        IonReaderBuilder ionReaderBuilderMutable = mutable();
        if (z) {
            ionReaderBuilderMutable.setIncrementalReadingEnabled();
            return ionReaderBuilderMutable;
        }
        ionReaderBuilderMutable.setIncrementalReadingDisabled();
        return ionReaderBuilderMutable;
    }

    public IonReaderBuilder(IonReaderBuilder ionReaderBuilder) {
        this.catalog = null;
        this.isIncrementalReadingEnabled = false;
        this.bufferConfiguration = null;
        this.isAnnotationIteratorReuseEnabled = true;
        this.catalog = ionReaderBuilder.catalog;
        this.isIncrementalReadingEnabled = ionReaderBuilder.isIncrementalReadingEnabled;
        this.bufferConfiguration = ionReaderBuilder.bufferConfiguration;
        this.isAnnotationIteratorReuseEnabled = ionReaderBuilder.isAnnotationIteratorReuseEnabled;
    }

    public IonReaderBuilder immutable() {
        return this;
    }
}
