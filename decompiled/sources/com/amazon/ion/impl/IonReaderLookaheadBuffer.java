package com.amazon.ion.impl;

import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.impl.ResizingPipedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonReaderLookaheadBuffer extends ReaderLookaheadBufferBase {
    public static final int HIGHEST_BIT_BITMASK = 128;
    public static final int ION_SYMBOL_TABLE_SID = 3;
    public static final int IVM_REMAINING_LENGTH = 3;
    public static final int IVM_START_BYTE = 224;
    public static final int LOWER_SEVEN_BITS_BITMASK = 127;
    public static final int MAXIMUM_SUPPORTED_VAR_UINT_BYTES = 9;
    public static final int VALUE_BITS_PER_VARUINT_BYTE = 7;
    public long additionalBytesNeeded;
    public final List<Integer> annotationSids;
    public long currentNumberOfAnnotations;
    public boolean handlerNeedsToBeNotifiedOfOversizedValue;
    public final VarUInt inProgressVarUInt;
    public boolean isSymbolTableAnnotationFirst;
    public boolean isSystemValue;
    public int ivmSecondByteIndex;
    public int nopPadStartIndex;
    public long numberOfAnnotationSidBytesRemaining;
    public final IonBufferConfiguration.OversizedSymbolTableHandler oversizedSymbolTableHandler;
    public final int pageSize;
    public int peekIndex;
    public State state;
    public final List<SymbolTableMarker> symbolTableMarkers;
    public int valueEndIndex;
    public int valuePostHeaderIndex;
    public int valuePreHeaderIndex;
    public int valueStartAvailable;
    public int valueStartWriteIndex;
    public IonTypeID valueTid;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ReadTypeIdResult {
        STRUCT,
        NOT_STRUCT,
        NO_DATA
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum State {
        BEFORE_TYPE_ID,
        READING_TYPE_ID,
        READING_HEADER,
        SKIPPING_VALUE,
        READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION,
        READING_SYMBOL_TABLE_LENGTH,
        DONE
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SymbolTableMarker {
        public int endIndex;
        public int startIndex;

        public SymbolTableMarker(int i, int i2) {
            this.startIndex = i;
            this.endIndex = i + i2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class VarUInt {
        public boolean isComplete;
        public Location location;
        public int numberOfBytesRead;
        public long value;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum Location {
            VALUE_LENGTH,
            ANNOTATION_WRAPPER_LENGTH,
            ANNOTATION_WRAPPER_SIDS_LENGTH,
            ANNOTATION_WRAPPER_SID
        }

        public static /* synthetic */ int access$308(VarUInt varUInt) {
            int i = varUInt.numberOfBytesRead;
            varUInt.numberOfBytesRead = i + 1;
            return i;
        }

        public final void reset(Location location) {
            this.location = location;
            this.value = 0L;
            this.numberOfBytesRead = 0;
            this.isComplete = false;
        }

        public VarUInt() {
            reset(Location.VALUE_LENGTH);
        }
    }

    public IonReaderLookaheadBuffer(IonBufferConfiguration ionBufferConfiguration, InputStream inputStream) {
        super(ionBufferConfiguration, inputStream);
        this.symbolTableMarkers = new ArrayList(2);
        this.annotationSids = new ArrayList(3);
        this.state = State.BEFORE_TYPE_ID;
        this.nopPadStartIndex = -1;
        this.ivmSecondByteIndex = -1;
        this.peekIndex = 0;
        this.handlerNeedsToBeNotifiedOfOversizedValue = true;
        this.pipe.registerNotificationConsumer(new ResizingPipedInputStream.NotificationConsumer() { // from class: com.amazon.ion.impl.IonReaderLookaheadBuffer.1
            @Override // com.amazon.ion.impl.ResizingPipedInputStream.NotificationConsumer
            public void bytesConsolidatedToStartOfBuffer(int i) {
                IonReaderLookaheadBuffer.this.shiftIndicesLeft(-1, i);
            }
        });
        this.pageSize = ionBufferConfiguration.initialBufferSize;
        this.oversizedSymbolTableHandler = ionBufferConfiguration.oversizedSymbolTableHandler;
        this.inProgressVarUInt = new VarUInt();
        reset();
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBufferBase
    public void fillInputHelper() throws Exception {
        int iFillOrSkip;
        while (true) {
            State state = this.state;
            if (state == State.BEFORE_TYPE_ID || state == State.READING_TYPE_ID) {
                reset();
                this.state = State.READING_TYPE_ID;
                if (readTypeID(true) != ReadTypeIdResult.NO_DATA) {
                    int i = this.peekIndex;
                    this.valuePostHeaderIndex = i;
                    int i2 = i - 1;
                    this.valuePreHeaderIndex = i2;
                    this.valueStartWriteIndex = i2;
                }
            }
            if (this.state == State.READING_HEADER) {
                readHeader();
                if (!this.inProgressVarUInt.isComplete) {
                    return;
                } else {
                    this.valuePostHeaderIndex = this.peekIndex;
                }
            }
            if (this.state == State.READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION) {
                ReadTypeIdResult typeID = readTypeID(false);
                if (typeID == ReadTypeIdResult.NO_DATA) {
                    return;
                }
                this.additionalBytesNeeded--;
                if (typeID == ReadTypeIdResult.STRUCT) {
                    this.state = State.READING_SYMBOL_TABLE_LENGTH;
                } else {
                    this.state = State.SKIPPING_VALUE;
                }
            }
            if (this.state == State.READING_SYMBOL_TABLE_LENGTH) {
                this.isSystemValue = true;
                if (this.inProgressVarUInt.location == VarUInt.Location.VALUE_LENGTH) {
                    readVarUInt();
                    VarUInt varUInt = this.inProgressVarUInt;
                    if (!varUInt.isComplete) {
                        return;
                    } else {
                        this.additionalBytesNeeded = varUInt.value;
                    }
                }
                this.symbolTableMarkers.add(new SymbolTableMarker(this.peekIndex, (int) this.additionalBytesNeeded));
                this.state = State.SKIPPING_VALUE;
            }
            if (this.state == State.SKIPPING_VALUE) {
                if (this.valueTid.isNopPad) {
                    long jAvailableBeyondBoundary = this.pipe.availableBeyondBoundary();
                    long j = this.additionalBytesNeeded;
                    if (jAvailableBeyondBoundary <= j) {
                        this.additionalBytesNeeded = j - ((long) this.pipe.availableBeyondBoundary());
                        startSkippingValue();
                        this.handlerNeedsToBeNotifiedOfOversizedValue = false;
                    }
                }
                while (this.additionalBytesNeeded > 0) {
                    long jAvailableBeyondBoundary2 = this.pipe.availableBeyondBoundary();
                    long j2 = this.additionalBytesNeeded;
                    if (jAvailableBeyondBoundary2 >= j2) {
                        iFillOrSkip = (int) j2;
                        this.pipe.extendBoundary(iFillOrSkip);
                        this.peekIndex += iFillOrSkip;
                    } else {
                        iFillOrSkip = fillOrSkip();
                        if (iFillOrSkip < 1) {
                            return;
                        }
                    }
                    this.dataHandler.getClass();
                    this.additionalBytesNeeded -= (long) iFillOrSkip;
                }
                this.state = State.BEFORE_TYPE_ID;
            }
            if (this.state != State.BEFORE_TYPE_ID) {
                return;
            }
            this.valueEndIndex = this.peekIndex;
            boolean z = this.isSystemValue;
            if (!z && !this.isSkippingCurrentValue && !this.valueTid.isNopPad) {
                return;
            }
            if (this.valueTid.isNopPad && this.nopPadStartIndex < 0) {
                this.nopPadStartIndex = this.valuePreHeaderIndex;
            }
            if (z && this.isSkippingCurrentValue) {
                reset();
                this.state = State.DONE;
                return;
            } else if (z && this.nopPadStartIndex > -1) {
                reclaimNopPadding();
            }
        }
    }

    public final int fillOrSkip() throws Exception {
        int iAvailableBeyondBoundary = ((int) this.additionalBytesNeeded) - this.pipe.availableBeyondBoundary();
        int iSkipBytesFromInput = this.isSkippingCurrentValue ? skipBytesFromInput(iAvailableBeyondBoundary) : fillPage(iAvailableBeyondBoundary);
        if (iSkipBytesFromInput < 1) {
            return 0;
        }
        if (this.isSkippingCurrentValue) {
            notifyHandlerOfOversizedValue();
            return (((int) this.additionalBytesNeeded) - iAvailableBeyondBoundary) + iSkipBytesFromInput;
        }
        int iMin = (int) Math.min(this.additionalBytesNeeded, iSkipBytesFromInput);
        this.pipe.extendBoundary(iMin);
        this.peekIndex += iMin;
        return iMin;
    }

    public final int fillPage(int i) throws Exception {
        int iCapacity = this.pipe.capacity() - this.pipe.size();
        if (iCapacity <= 0) {
            int iCapacity2 = this.maximumBufferSize - this.pipe.capacity();
            if (i > iCapacity2) {
                int i2 = this.nopPadStartIndex;
                if (i2 <= -1 || this.valuePreHeaderIndex - i2 < i) {
                    startSkippingValue();
                } else {
                    reclaimNopPadding();
                }
            } else {
                i = Math.min(this.pageSize, iCapacity2);
            }
        } else {
            i = iCapacity;
        }
        return this.isSkippingCurrentValue ? this.state == State.SKIPPING_VALUE ? skipBytesFromInput(i) : i : this.pipe.receive(this.input, i);
    }

    public List<Integer> getAnnotationSids() {
        return this.annotationSids;
    }

    public int getIvmIndex() {
        return this.ivmSecondByteIndex;
    }

    public List<SymbolTableMarker> getSymbolTableMarkers() {
        return this.symbolTableMarkers;
    }

    public int getValueEnd() {
        return this.valueEndIndex;
    }

    public int getValueStart() {
        return this.valuePostHeaderIndex;
    }

    public IonTypeID getValueTid() {
        return this.valueTid;
    }

    public final void initializeVarUInt(VarUInt.Location location) {
        this.inProgressVarUInt.reset(location);
        this.state = State.READING_HEADER;
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBuffer
    public boolean moreDataRequired() {
        return this.pipe.available() <= 0 || this.state != State.BEFORE_TYPE_ID;
    }

    public final void notifyHandlerOfOversizedValue() throws Exception {
        if (this.handlerNeedsToBeNotifiedOfOversizedValue) {
            if (this.isSystemValue) {
                this.oversizedSymbolTableHandler.getClass();
            } else {
                this.oversizedValueHandler.getClass();
            }
        }
        this.handlerNeedsToBeNotifiedOfOversizedValue = false;
    }

    public final int readByte() throws Exception {
        if (this.pipe.availableBeyondBoundary() == 0 && fillPage(1) < 1) {
            return -1;
        }
        if (this.isSkippingCurrentValue) {
            return this.input.read();
        }
        int iPeek = this.pipe.peek(this.peekIndex);
        this.pipe.extendBoundary(1);
        this.peekIndex++;
        return iPeek;
    }

    public final void readHeader() throws Exception {
        VarUInt.Location location = this.inProgressVarUInt.location;
        if (location == VarUInt.Location.VALUE_LENGTH) {
            readVarUInt();
            VarUInt varUInt = this.inProgressVarUInt;
            if (varUInt.isComplete) {
                this.additionalBytesNeeded = varUInt.value;
                this.state = State.SKIPPING_VALUE;
                return;
            }
            return;
        }
        if (location == VarUInt.Location.ANNOTATION_WRAPPER_LENGTH) {
            readVarUInt();
            VarUInt varUInt2 = this.inProgressVarUInt;
            if (!varUInt2.isComplete) {
                return;
            }
            this.additionalBytesNeeded = varUInt2.value;
            initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH);
        }
        if (this.inProgressVarUInt.location == VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH) {
            readVarUInt();
            VarUInt varUInt3 = this.inProgressVarUInt;
            if (!varUInt3.isComplete) {
                return;
            }
            this.additionalBytesNeeded -= (long) varUInt3.numberOfBytesRead;
            this.numberOfAnnotationSidBytesRemaining = varUInt3.value;
            initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SID);
        }
        if (this.inProgressVarUInt.location != VarUInt.Location.ANNOTATION_WRAPPER_SID) {
            return;
        }
        while (true) {
            readVarUInt();
            VarUInt varUInt4 = this.inProgressVarUInt;
            if (!varUInt4.isComplete) {
                return;
            }
            long j = this.currentNumberOfAnnotations + 1;
            this.currentNumberOfAnnotations = j;
            if (j == 1 && varUInt4.value == 3) {
                this.isSymbolTableAnnotationFirst = true;
            }
            this.annotationSids.add(Integer.valueOf((int) varUInt4.value));
            long j2 = this.numberOfAnnotationSidBytesRemaining;
            int i = this.inProgressVarUInt.numberOfBytesRead;
            long j3 = j2 - ((long) i);
            this.numberOfAnnotationSidBytesRemaining = j3;
            this.additionalBytesNeeded -= (long) i;
            if (j3 <= 0) {
                this.state = State.SKIPPING_VALUE;
                if (this.isSymbolTableAnnotationFirst) {
                    this.state = State.READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION;
                    return;
                }
                return;
            }
            initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SID);
        }
    }

    public final ReadTypeIdResult readTypeID(boolean z) throws Exception {
        int i = readByte();
        if (i < 0) {
            return ReadTypeIdResult.NO_DATA;
        }
        this.valueTid = IonTypeID.TYPE_IDS[i];
        this.dataHandler.getClass();
        if (i != 224) {
            IonTypeID ionTypeID = this.valueTid;
            if (!ionTypeID.isValid) {
                throw new IonException("Invalid type ID.");
            }
            IonType ionType = ionTypeID.type;
            if (ionType == IonType.BOOL) {
                this.state = State.BEFORE_TYPE_ID;
            } else if (ionType == IonTypeID.ION_TYPE_ANNOTATION_WRAPPER) {
                if (ionTypeID.variableLength) {
                    initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_LENGTH);
                } else {
                    setAdditionalBytesNeeded(ionTypeID.length, z);
                    initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH);
                }
            } else if (ionTypeID.isNull) {
                this.state = State.BEFORE_TYPE_ID;
            } else if (ionTypeID.variableLength) {
                initializeVarUInt(VarUInt.Location.VALUE_LENGTH);
            } else {
                setAdditionalBytesNeeded(ionTypeID.length, z);
                this.state = State.SKIPPING_VALUE;
            }
        } else {
            if (!z) {
                throw new IonException("Invalid annotation header.");
            }
            this.additionalBytesNeeded = 3L;
            this.isSystemValue = true;
            resetSymbolTableMarkers();
            this.ivmSecondByteIndex = this.peekIndex;
            this.state = State.SKIPPING_VALUE;
        }
        return this.valueTid.type == IonType.STRUCT ? ReadTypeIdResult.STRUCT : ReadTypeIdResult.NOT_STRUCT;
    }

    public final void readVarUInt() throws Exception {
        while (this.inProgressVarUInt.numberOfBytesRead < 9) {
            int i = readByte();
            if (i < 0) {
                return;
            }
            VarUInt.access$308(this.inProgressVarUInt);
            VarUInt varUInt = this.inProgressVarUInt;
            varUInt.value = (varUInt.value << 7) | ((long) (i & 127));
            if ((i & 128) != 0) {
                varUInt.isComplete = true;
                this.dataHandler.getClass();
                return;
            }
        }
        throw new IonException("Found a VarUInt that was too large to fit in a `long`");
    }

    public final void reclaimNopPadding() {
        this.pipe.consolidate(this.valuePreHeaderIndex, this.nopPadStartIndex);
        int i = this.nopPadStartIndex;
        shiftIndicesLeft(i, this.valuePreHeaderIndex - i);
        this.nopPadStartIndex = -1;
    }

    public final void reset() {
        this.additionalBytesNeeded = 0L;
        this.isSystemValue = false;
        this.isSymbolTableAnnotationFirst = false;
        this.numberOfAnnotationSidBytesRemaining = 0L;
        this.currentNumberOfAnnotations = 0L;
        this.valuePreHeaderIndex = -1;
        this.valuePostHeaderIndex = -1;
        this.valueTid = null;
        this.valueEndIndex = -1;
        this.annotationSids.clear();
        this.valueStartAvailable = this.pipe.available();
        this.isSkippingCurrentValue = false;
    }

    public void resetIvmIndex() {
        this.ivmSecondByteIndex = -1;
    }

    public void resetNopPadIndex() {
        this.nopPadStartIndex = -1;
    }

    public void resetSymbolTableMarkers() {
        this.symbolTableMarkers.clear();
    }

    public void rewindToValueStart() {
        if (this.valuePreHeaderIndex < 0) {
            throw new IllegalStateException("A value must be buffered before calling rewindToValueStart().");
        }
        int boundary = this.pipe.getBoundary() - this.valuePreHeaderIndex;
        if (boundary < this.pipe.available()) {
            throw new IllegalStateException("IonReader.next() must be called on the current value before calling rewindToValueStart().");
        }
        this.pipe.rewind(this.valuePreHeaderIndex, boundary);
        this.peekIndex = this.valuePreHeaderIndex;
    }

    public final void setAdditionalBytesNeeded(long j, boolean z) {
        if (z) {
            this.additionalBytesNeeded = j;
        }
    }

    public final void shiftIndicesLeft(int i, int i2) {
        this.peekIndex = Math.max(this.peekIndex - i2, 0);
        this.valuePreHeaderIndex -= i2;
        this.valuePostHeaderIndex -= i2;
        for (SymbolTableMarker symbolTableMarker : this.symbolTableMarkers) {
            int i3 = symbolTableMarker.startIndex;
            if (i3 > i) {
                symbolTableMarker.startIndex = i3 - i2;
                symbolTableMarker.endIndex -= i2;
            }
        }
        int i4 = this.ivmSecondByteIndex;
        if (i4 > i) {
            this.ivmSecondByteIndex = i4 - i2;
        }
    }

    public final int skipBytesFromInput(int i) throws IOException {
        try {
            return (int) this.input.skip(i);
        } catch (EOFException unused) {
            return 0;
        }
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBufferBase
    public void truncateToEndOfPreviousValue() {
        int i = this.valueStartWriteIndex;
        this.peekIndex = i;
        this.pipe.truncate(i, this.valueStartAvailable);
        this.handlerNeedsToBeNotifiedOfOversizedValue = true;
    }
}
