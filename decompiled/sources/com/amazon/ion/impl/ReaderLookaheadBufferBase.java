package com.amazon.ion.impl;

import com.amazon.ion.BufferConfiguration;
import com.amazon.ion.IonReader;
import com.amazon.ion.system.IonReaderBuilder;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ReaderLookaheadBufferBase implements ReaderLookaheadBuffer {
    public final BufferConfiguration.DataHandler dataHandler;
    public final InputStream input;
    public boolean isSkippingCurrentValue;
    public int markedAvailable;
    public int markedReadIndex;
    public final int maximumBufferSize;
    public final BufferConfiguration.OversizedValueHandler oversizedValueHandler;
    public final ResizingPipedInputStream pipe;

    public ReaderLookaheadBufferBase(BufferConfiguration<?> bufferConfiguration, InputStream inputStream) {
        this.input = inputStream;
        this.pipe = new ResizingPipedInputStream(bufferConfiguration.initialBufferSize, bufferConfiguration.maximumBufferSize, true);
        this.maximumBufferSize = bufferConfiguration.maximumBufferSize;
        this.oversizedValueHandler = bufferConfiguration.oversizedValueHandler;
        this.dataHandler = bufferConfiguration.dataHandler;
        clearMark();
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBuffer
    public final int available() {
        return this.pipe.available();
    }

    public final void clearMark() {
        this.markedAvailable = -1;
        this.markedReadIndex = -1;
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBuffer
    public final void fillInput() throws Exception {
        clearMark();
        fillInputHelper();
    }

    public abstract void fillInputHelper() throws Exception;

    public InputStream getInput() {
        return this.input;
    }

    public int getMaximumBufferSize() {
        return this.maximumBufferSize;
    }

    public InputStream getPipe() {
        return this.pipe;
    }

    public boolean isSkippingCurrentValue() {
        return this.isSkippingCurrentValue;
    }

    public final void mark() {
        if (moreDataRequired()) {
            throw new IllegalStateException("moreDataRequired() must be false before calling mark().");
        }
        uncheckedMark();
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBuffer
    public final IonReader newIonReader(IonReaderBuilder ionReaderBuilder) {
        return ionReaderBuilder.build(this.pipe);
    }

    public final void rewind() {
        int i;
        int i2 = this.markedReadIndex;
        if (i2 < 0 || (i = this.markedAvailable) < 0) {
            throw new IllegalStateException("Must call mark() before rewind().");
        }
        this.pipe.rewind(i2, i);
    }

    public void startNewValue() {
        this.isSkippingCurrentValue = false;
    }

    public void startSkippingValue() throws Exception {
        if (this.isSkippingCurrentValue) {
            return;
        }
        this.isSkippingCurrentValue = true;
        truncateToEndOfPreviousValue();
    }

    public abstract void truncateToEndOfPreviousValue() throws Exception;

    public final void uncheckedMark() {
        this.markedAvailable = this.pipe.available();
        this.markedReadIndex = this.pipe.getReadIndex();
    }
}
