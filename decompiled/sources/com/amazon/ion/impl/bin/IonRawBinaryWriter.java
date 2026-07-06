package com.amazon.ion.impl.bin;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl.IonReaderBinaryIncremental;
import com.amazon.ion.impl._Private_IonWriterBase;
import com.amazon.ion.impl._Private_RecyclingStack;
import com.amazon.ion.impl.bin.AbstractIonWriter;
import com.amazon.ion.impl.bin.utf8.Utf8StringEncoder;
import com.amazon.ion.impl.bin.utf8.Utf8StringEncoderPool;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonRawBinaryWriter extends AbstractIonWriter implements _Private_IonRawWriter {
    public static final BigInteger BIG_INT_LONG_MAX_VALUE;
    public static final BigInteger BIG_INT_LONG_MIN_VALUE;
    public static final byte BLOB_TYPE = -96;
    public static final byte BOOL_FALSE = 16;
    public static final byte BOOL_TRUE = 17;
    public static final byte CLOB_TYPE = -112;
    public static final byte DECIMAL_NEGATIVE_ZERO_MANTISSA = -128;
    public static final byte DECIMAL_POS_ZERO = 80;
    public static final byte DECIMAL_TYPE = 80;
    public static final byte FLOAT_TYPE = 64;
    public static final byte INT_ZERO = 32;
    public static final byte[] IVM = bytes(224, 1, 0, IonReaderBinaryIncremental.IVM_FINAL_BYTE);
    public static final int MAX_ANNOTATION_LENGTH = 127;
    public static final byte NEG_INT_TYPE = 48;
    public static final byte[] NULLS;
    public static final byte NULL_NULL;
    public static final byte POS_INT_TYPE = 32;
    public static final int SID_UNASSIGNED = -1;
    public static final byte STRING_TYPE = -128;
    public static final byte[] STRING_TYPED_PREALLOCATED_2;
    public static final byte[] STRING_TYPED_PREALLOCATED_3;
    public static final byte STRING_TYPE_EXTENDED_LENGTH = -114;
    public static final byte SYMBOL_TYPE = 112;
    public static final byte TIMESTAMP_TYPE = 96;
    public static final byte VARINT_NEG_ZERO = -64;
    public final BlockAllocator allocator;
    public final WriteBuffer buffer;
    public boolean closed;
    public final _Private_RecyclingStack<ContainerInfo> containers;
    public final List<Integer> currentAnnotationSids;
    public int currentFieldSid;
    public int depth;
    public boolean hasTopLevelSymbolTableAnnotation;
    public boolean hasWrittenValuesSinceConstructed;
    public boolean hasWrittenValuesSinceFinished;
    public final boolean isFloatBinary32Enabled;
    public final OutputStream out;
    public final WriteBuffer patchBuffer;
    public final PatchList patchPoints;
    public final PreallocationMode preallocationMode;
    public final StreamCloseMode streamCloseMode;
    public final StreamFlushMode streamFlushMode;
    public final Utf8StringEncoder utf8StringEncoder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ContainerInfo {
        public ContainerType type = null;
        public long position = -1;
        public long length = -1;
        public PatchList patches = null;

        public void appendPatch(PatchPoint patchPoint) {
            if (this.patches == null) {
                this.patches = new PatchList();
            }
            this.patches.append(patchPoint);
        }

        public void extendPatches(PatchList patchList) {
            PatchList patchList2 = this.patches;
            if (patchList2 == null) {
                this.patches = patchList;
            } else {
                patchList2.extend(patchList);
            }
        }

        public void initialize(ContainerType containerType, long j) {
            this.type = containerType;
            this.position = j;
            this.patches = null;
            this.length = 0L;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(CI ");
            sb.append(this.type);
            sb.append(" pos:");
            sb.append(this.position);
            sb.append(" len:");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.length, ")");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ContainerType {
        SEQUENCE(true),
        STRUCT(true),
        VALUE(false),
        ANNOTATION(false);

        public final boolean allowedInStepOut;

        ContainerType(boolean z) {
            this.allowedInStepOut = z;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PatchList implements Iterable<PatchPoint> {
        public Node head = null;
        public Node tail = null;

        /* JADX INFO: renamed from: com.amazon.ion.impl.bin.IonRawBinaryWriter$PatchList$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 implements Iterator<PatchPoint> {
            public Node curr;

            public AnonymousClass1() {
                this.curr = PatchList.this.head;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.curr != null;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public PatchPoint next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = this.curr;
                PatchPoint patchPoint = node.value;
                this.curr = node.next;
                return patchPoint;
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Node {
            public Node next;
            public final PatchPoint value;

            public Node(PatchPoint patchPoint) {
                this.value = patchPoint;
            }
        }

        public void append(PatchPoint patchPoint) {
            Node node = new Node(patchPoint);
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                this.tail = node;
            }
        }

        public void clear() {
            this.head = null;
            this.tail = null;
        }

        public void extend(PatchList patchList) {
            if (patchList != null) {
                if (this.head != null) {
                    this.tail.next = patchList.head;
                    this.tail = patchList.tail;
                } else {
                    Node node = patchList.head;
                    if (node != null) {
                        this.head = node;
                        this.tail = patchList.tail;
                    }
                }
            }
        }

        public boolean isEmpty() {
            return this.head == null && this.tail == null;
        }

        @Override // java.lang.Iterable
        public Iterator<PatchPoint> iterator() {
            return new AnonymousClass1();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(PATCHES");
            Iterator<PatchPoint> it = iterator();
            while (true) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) it;
                if (!anonymousClass1.hasNext()) {
                    sb.append(")");
                    return sb.toString();
                }
                PatchPoint next = anonymousClass1.next();
                sb.append(StringUtils.SPACE);
                sb.append(next);
            }
        }

        public PatchPoint truncate(long j) {
            Node node = null;
            for (Node node2 = this.head; node2 != null; node2 = node2.next) {
                PatchPoint patchPoint = node2.value;
                if (patchPoint.oldPosition >= j) {
                    this.tail = node;
                    if (node == null) {
                        this.head = null;
                        return patchPoint;
                    }
                    node.next = null;
                    return patchPoint;
                }
                node = node2;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PatchPoint {
        public final int oldLength;
        public final long oldPosition;
        public final int patchLength;
        public final long patchPosition;

        public PatchPoint(long j, int i, long j2, int i2) {
            this.oldPosition = j;
            this.oldLength = i;
            this.patchPosition = j2;
            this.patchLength = i2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(PP old::(");
            sb.append(this.oldPosition);
            sb.append(StringUtils.SPACE);
            sb.append(this.oldLength);
            sb.append(") patch::(");
            sb.append(this.patchPosition);
            sb.append(StringUtils.SPACE);
            return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.patchLength, ")");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class PreallocationMode {
        public static final /* synthetic */ PreallocationMode[] $VALUES;
        public static final PreallocationMode PREALLOCATE_0;
        public static final PreallocationMode PREALLOCATE_1;
        public static final PreallocationMode PREALLOCATE_2;
        public final byte[] annotationsTypedPreallocatedBytes;
        public final byte[][] containerTypedPreallocatedBytes;
        public final int contentMaxLength;
        public final int typedLength;

        /* JADX INFO: renamed from: com.amazon.ion.impl.bin.IonRawBinaryWriter$PreallocationMode$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum AnonymousClass1 extends PreallocationMode {
            public AnonymousClass1(String str, int i, int i2, int i3) {
                super(str, i, i2, i3);
            }

            @Override // com.amazon.ion.impl.bin.IonRawBinaryWriter.PreallocationMode
            public void patchLength(WriteBuffer writeBuffer, long j, long j2) {
                throw new IllegalStateException("Cannot patch in PREALLOCATE 0 mode");
            }
        }

        /* JADX INFO: renamed from: com.amazon.ion.impl.bin.IonRawBinaryWriter$PreallocationMode$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum AnonymousClass2 extends PreallocationMode {
            public AnonymousClass2(String str, int i, int i2, int i3) {
                super(str, i, i2, i3);
            }

            @Override // com.amazon.ion.impl.bin.IonRawBinaryWriter.PreallocationMode
            public void patchLength(WriteBuffer writeBuffer, long j, long j2) {
                writeBuffer.writeVarUIntDirect1At(j, j2);
            }
        }

        /* JADX INFO: renamed from: com.amazon.ion.impl.bin.IonRawBinaryWriter$PreallocationMode$3, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum AnonymousClass3 extends PreallocationMode {
            public AnonymousClass3(String str, int i, int i2, int i3) {
                super(str, i, i2, i3);
            }

            @Override // com.amazon.ion.impl.bin.IonRawBinaryWriter.PreallocationMode
            public void patchLength(WriteBuffer writeBuffer, long j, long j2) {
                writeBuffer.writeVarUIntDirect2At(j, j2);
            }
        }

        static {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1("PREALLOCATE_0", 0, 0, 1);
            PREALLOCATE_0 = anonymousClass1;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2("PREALLOCATE_1", 1, 127, 2);
            PREALLOCATE_1 = anonymousClass2;
            AnonymousClass3 anonymousClass3 = new AnonymousClass3("PREALLOCATE_2", 2, 16383, 3);
            PREALLOCATE_2 = anonymousClass3;
            $VALUES = new PreallocationMode[]{anonymousClass1, anonymousClass2, anonymousClass3};
        }

        public static PreallocationMode valueOf(String str) {
            return (PreallocationMode) Enum.valueOf(PreallocationMode.class, str);
        }

        public static PreallocationMode[] values() {
            return (PreallocationMode[]) $VALUES.clone();
        }

        public static PreallocationMode withPadSize(int i) {
            if (i == 0) {
                return PREALLOCATE_0;
            }
            if (i == 1) {
                return PREALLOCATE_1;
            }
            if (i == 2) {
                return PREALLOCATE_2;
            }
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("No such preallocation mode for: ", i));
        }

        public abstract void patchLength(WriteBuffer writeBuffer, long j, long j2);

        public PreallocationMode(String str, int i, int i2, int i3) {
            this.contentMaxLength = i2;
            this.typedLength = i3;
            this.containerTypedPreallocatedBytes = IonRawBinaryWriter.makeContainerTypedPreallocatedTable(i3);
            this.annotationsTypedPreallocatedBytes = IonRawBinaryWriter.makeTypedPreallocatedBytes(238, i3);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum StreamCloseMode {
        NO_CLOSE,
        CLOSE
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum StreamFlushMode {
        NO_FLUSH,
        FLUSH
    }

    static {
        byte[] bArr = new byte[IonType.values().length];
        NULLS = bArr;
        IonType ionType = IonType.NULL;
        bArr[ionType.ordinal()] = Ascii.SI;
        bArr[IonType.BOOL.ordinal()] = 31;
        bArr[IonType.INT.ordinal()] = 47;
        bArr[IonType.FLOAT.ordinal()] = 79;
        bArr[IonType.DECIMAL.ordinal()] = 95;
        bArr[IonType.TIMESTAMP.ordinal()] = 111;
        bArr[IonType.SYMBOL.ordinal()] = 127;
        bArr[IonType.STRING.ordinal()] = -113;
        bArr[IonType.CLOB.ordinal()] = -97;
        bArr[IonType.BLOB.ordinal()] = -81;
        bArr[IonType.LIST.ordinal()] = -65;
        bArr[IonType.SEXP.ordinal()] = ExifInterface.MARKER_SOF15;
        bArr[IonType.STRUCT.ordinal()] = -33;
        NULL_NULL = bArr[ionType.ordinal()];
        BIG_INT_LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
        BIG_INT_LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
        STRING_TYPED_PREALLOCATED_2 = makeTypedPreallocatedBytes(142, 2);
        STRING_TYPED_PREALLOCATED_3 = makeTypedPreallocatedBytes(142, 3);
    }

    public IonRawBinaryWriter(BlockAllocatorProvider blockAllocatorProvider, int i, OutputStream outputStream, AbstractIonWriter.WriteValueOptimization writeValueOptimization, StreamCloseMode streamCloseMode, StreamFlushMode streamFlushMode, PreallocationMode preallocationMode, boolean z) throws IOException {
        super(writeValueOptimization);
        this.utf8StringEncoder = Utf8StringEncoderPool.getInstance().getOrCreate();
        outputStream.getClass();
        BlockAllocator blockAllocatorVendAllocator = blockAllocatorProvider.vendAllocator(i);
        this.allocator = blockAllocatorVendAllocator;
        this.out = outputStream;
        this.streamCloseMode = streamCloseMode;
        this.streamFlushMode = streamFlushMode;
        this.preallocationMode = preallocationMode;
        this.isFloatBinary32Enabled = z;
        this.buffer = new WriteBuffer(blockAllocatorVendAllocator);
        this.patchBuffer = new WriteBuffer(blockAllocatorVendAllocator);
        this.patchPoints = new PatchList();
        this.containers = new _Private_RecyclingStack<>(10, new _Private_RecyclingStack.ElementFactory<ContainerInfo>() { // from class: com.amazon.ion.impl.bin.IonRawBinaryWriter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.ion.impl._Private_RecyclingStack.ElementFactory
            public ContainerInfo newElement() {
                return new ContainerInfo();
            }
        });
        this.depth = 0;
        this.hasWrittenValuesSinceFinished = false;
        this.hasWrittenValuesSinceConstructed = false;
        this.currentFieldSid = -1;
        this.currentAnnotationSids = new ArrayList();
        this.hasTopLevelSymbolTableAnnotation = false;
        this.closed = false;
    }

    public static byte[] bytes(int... iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) iArr[i];
        }
        return bArr;
    }

    public static void checkSid(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid symbol with SID: ", i));
        }
    }

    private void clearAnnotations() {
        this.currentAnnotationSids.clear();
        this.hasTopLevelSymbolTableAnnotation = false;
    }

    public static byte[][] makeContainerTypedPreallocatedTable(int i) {
        byte[][] bArr = new byte[IonType.values().length][];
        bArr[IonType.LIST.ordinal()] = makeTypedPreallocatedBytes(190, i);
        bArr[IonType.SEXP.ordinal()] = makeTypedPreallocatedBytes(206, i);
        bArr[IonType.STRUCT.ordinal()] = makeTypedPreallocatedBytes(222, i);
        return bArr;
    }

    public static final byte[] makeTypedPreallocatedBytes(int i, int i2) {
        byte[] bArr = new byte[i2];
        bArr[0] = (byte) i;
        if (i2 > 1) {
            bArr[i2 - 1] = -128;
        }
        return bArr;
    }

    public final void addPatchPoint(long j, int i, long j2) {
        PatchPoint patchPoint = new PatchPoint(j, i, this.patchBuffer.position(), this.patchBuffer.writeVarUInt(j2));
        if (this.containers.isEmpty()) {
            this.patchPoints.append(patchPoint);
        } else {
            this.containers.top.appendPatch(patchPoint);
        }
        updateLength(r7 - i);
    }

    @Override // com.amazon.ion.IonWriter
    public void addTypeAnnotation(String str) {
        throw new UnsupportedOperationException("Cannot add annotations on a low-level binary writer via string");
    }

    public void addTypeAnnotationSymbol(SymbolToken symbolToken) {
        addTypeAnnotationSymbol(symbolToken.getSid());
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        try {
            try {
                finish();
            } finally {
                this.closed = true;
                if (this.streamCloseMode == StreamCloseMode.CLOSE) {
                    this.out.close();
                }
            }
        } catch (IllegalStateException unused) {
        }
        this.buffer.close();
        this.patchBuffer.close();
        this.allocator.getClass();
        this.utf8StringEncoder.close();
    }

    public final void extendPatchPoints(PatchList patchList) {
        if (this.containers.isEmpty()) {
            this.patchPoints.extend(patchList);
        } else {
            this.containers.top.extendPatches(patchList);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void finish() throws IOException {
        if (this.closed) {
            return;
        }
        if (!this.containers.isEmpty() || this.depth > 0) {
            throw new IllegalStateException("Cannot finish within container: " + this.containers);
        }
        if (this.patchPoints.isEmpty()) {
            this.buffer.writeTo(this.out);
        } else {
            Iterator<PatchPoint> it = this.patchPoints.iterator();
            long j = 0;
            while (true) {
                PatchList.AnonymousClass1 anonymousClass1 = (PatchList.AnonymousClass1) it;
                if (!anonymousClass1.hasNext()) {
                    break;
                }
                PatchPoint next = anonymousClass1.next();
                this.buffer.writeTo(this.out, j, next.oldPosition - j);
                this.patchBuffer.writeTo(this.out, next.patchPosition, next.patchLength);
                j = next.oldPosition + ((long) next.oldLength);
            }
            WriteBuffer writeBuffer = this.buffer;
            writeBuffer.writeTo(this.out, j, writeBuffer.position() - j);
        }
        this.patchPoints.clear();
        this.patchBuffer.reset();
        this.buffer.reset();
        if (this.streamFlushMode == StreamFlushMode.FLUSH) {
            this.out.flush();
        }
        this.hasWrittenValuesSinceFinished = false;
    }

    public final void finishValue() {
        if (!this.containers.isEmpty() && this.containers.top.type == ContainerType.ANNOTATION) {
            popContainer();
        }
        this.hasWrittenValuesSinceFinished = true;
        this.hasWrittenValuesSinceConstructed = true;
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public IonCatalog getCatalog() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public int getDepth() {
        return this.depth;
    }

    public int getFieldId() {
        return this.currentFieldSid;
    }

    @Override // com.amazon.ion.IonWriter
    public SymbolTable getSymbolTable() {
        return Symbols.systemSymbolTable();
    }

    public boolean hasAnnotations() {
        return !this.currentAnnotationSids.isEmpty();
    }

    public boolean hasTopLevelSymbolTableAnnotation() {
        return this.hasTopLevelSymbolTableAnnotation;
    }

    public boolean hasWrittenValuesSinceConstructed() {
        return this.hasWrittenValuesSinceConstructed;
    }

    public boolean hasWrittenValuesSinceFinished() {
        return this.hasWrittenValuesSinceFinished;
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public boolean isFieldNameSet() {
        return this.currentFieldSid > -1;
    }

    public boolean isIVM(int i) {
        return this.depth == 0 && i == 2 && !hasAnnotations();
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return !this.containers.isEmpty() && this.containers.top.type == ContainerType.STRUCT;
    }

    public final void patchSingleByteTypedOptimisticValue(byte b, ContainerInfo containerInfo) {
        long j = containerInfo.length;
        if (j <= 13) {
            this.buffer.writeUInt8At(containerInfo.position - 1, ((long) b) | j);
        } else {
            this.buffer.writeUInt8At(containerInfo.position - 1, b | Ascii.SO);
            addPatchPoint(containerInfo.position, 0, containerInfo.length);
        }
    }

    public final ContainerInfo popContainer() {
        PreallocationMode preallocationMode;
        ContainerInfo containerInfoPop = this.containers.pop();
        if (containerInfoPop == null) {
            throw new IllegalStateException("Tried to pop container state without said container");
        }
        long j = containerInfoPop.length;
        if (containerInfoPop.type != ContainerType.VALUE) {
            long j2 = containerInfoPop.position;
            if (j <= this.preallocationMode.contentMaxLength && (preallocationMode = this.preallocationMode) != PreallocationMode.PREALLOCATE_0) {
                preallocationMode.patchLength(this.buffer, j2, j);
            } else if (containerInfoPop.length > 13 || this.preallocationMode != PreallocationMode.PREALLOCATE_0) {
                addPatchPoint(j2, this.preallocationMode.typedLength - 1, j);
            } else {
                long j3 = j2 - 1;
                this.buffer.writeUInt8At(j3, ((long) (this.buffer.getUInt8At(j3) & 240)) | containerInfoPop.length);
            }
        }
        PatchList patchList = containerInfoPop.patches;
        if (patchList != null) {
            extendPatchPoints(patchList);
        }
        updateLength(j);
        return containerInfoPop;
    }

    public long position() {
        return this.buffer.position();
    }

    public final void prepareValue() {
        if (isInStruct() && this.currentFieldSid <= -1) {
            throw new IllegalStateException(_Private_IonWriterBase.ERROR_MISSING_FIELD_NAME);
        }
        int i = this.currentFieldSid;
        if (i > -1) {
            checkSid(i);
            writeVarUInt(this.currentFieldSid);
            this.currentFieldSid = -1;
        }
        if (this.currentAnnotationSids.isEmpty()) {
            return;
        }
        updateLength(this.preallocationMode.typedLength);
        pushContainer(ContainerType.ANNOTATION);
        this.buffer.writeBytes(this.preallocationMode.annotationsTypedPreallocatedBytes);
        long jPosition = this.buffer.position();
        this.buffer.writeVarUInt(0L);
        Iterator<Integer> it = this.currentAnnotationSids.iterator();
        int iWriteVarUInt = 0;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            checkSid(iIntValue);
            iWriteVarUInt += this.buffer.writeVarUInt(iIntValue);
        }
        if (iWriteVarUInt > 127) {
            throw new IonException("Annotations too large: " + this.currentAnnotationSids);
        }
        updateLength(iWriteVarUInt + 1);
        this.buffer.writeVarUIntDirect1At(jPosition, iWriteVarUInt);
        this.currentAnnotationSids.clear();
        this.hasTopLevelSymbolTableAnnotation = false;
    }

    public final void pushContainer(ContainerType containerType) {
        this.containers.push().initialize(containerType, this.buffer.position() + 1);
    }

    @Override // com.amazon.ion.IonWriter
    public void setFieldName(String str) {
        throw new UnsupportedOperationException("Cannot set field name on a low-level binary writer via string");
    }

    @Override // com.amazon.ion.IonWriter
    public void setFieldNameSymbol(SymbolToken symbolToken) {
        setFieldNameSymbol(symbolToken.getSid());
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        clearAnnotations();
        if (symbolTokenArr != null) {
            for (SymbolToken symbolToken : symbolTokenArr) {
                addTypeAnnotationSymbol(symbolToken.getSid());
            }
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotations(String... strArr) {
        throw new UnsupportedOperationException("Cannot set annotations on a low-level binary writer via string");
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        if (!IonType.isContainer(ionType)) {
            throw new IonException("Cannot step into " + ionType);
        }
        prepareValue();
        updateLength(this.preallocationMode.typedLength);
        pushContainer(ionType == IonType.STRUCT ? ContainerType.STRUCT : ContainerType.SEQUENCE);
        this.depth++;
        this.buffer.writeBytes(this.preallocationMode.containerTypedPreallocatedBytes[ionType.ordinal()]);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        if (this.currentFieldSid > -1) {
            throw new IonException("Cannot step out with field name set");
        }
        if (!this.currentAnnotationSids.isEmpty()) {
            throw new IonException("Cannot step out with field name set");
        }
        if (this.containers.isEmpty() || !this.containers.top.type.allowedInStepOut) {
            throw new IonException("Cannot step out when not in container");
        }
        popContainer();
        this.depth--;
        finishValue();
    }

    public void truncate(long j) {
        this.buffer.truncate(j);
        PatchPoint patchPointTruncate = this.patchPoints.truncate(j);
        if (patchPointTruncate != null) {
            this.patchBuffer.truncate(patchPointTruncate.patchPosition);
        }
    }

    public final void updateLength(long j) {
        if (this.containers.isEmpty()) {
            return;
        }
        this.containers.top.length += j;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr) throws IOException {
        if (bArr == null) {
            writeNull(IonType.BLOB);
        } else {
            writeBlob(bArr, 0, bArr.length);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        prepareValue();
        updateLength(1L);
        if (z) {
            this.buffer.writeByte((byte) 17);
        } else {
            this.buffer.writeByte((byte) 16);
        }
        finishValue();
    }

    @Override // com.amazon.ion.impl._Private_ByteTransferSink
    public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
        prepareValue();
        updateLength(i2);
        this.buffer.writeBytes(bArr, i, i2);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr) throws IOException {
        if (bArr == null) {
            writeNull(IonType.CLOB);
        } else {
            writeClob(bArr, 0, bArr.length);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            writeNull(IonType.DECIMAL);
            return;
        }
        prepareValue();
        if (bigDecimal.signum() == 0 && bigDecimal.scale() == 0 && !Decimal.isNegativeZero(bigDecimal)) {
            updateLength(1L);
            this.buffer.writeUInt8(80L);
        } else {
            updateLength(1L);
            pushContainer(ContainerType.VALUE);
            this.buffer.writeByte((byte) 80);
            writeDecimalValue(bigDecimal);
            patchSingleByteTypedOptimisticValue((byte) 80, popContainer());
        }
        finishValue();
    }

    public final void writeDecimalValue(BigDecimal bigDecimal) {
        boolean zIsNegativeZero = Decimal.isNegativeZero(bigDecimal);
        int iSignum = bigDecimal.signum();
        writeVarInt(-bigDecimal.scale());
        BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
        if (bigIntegerUnscaledValue.compareTo(BIG_INT_LONG_MIN_VALUE) < 0 || bigIntegerUnscaledValue.compareTo(BIG_INT_LONG_MAX_VALUE) > 0) {
            if (iSignum <= 0) {
                bigIntegerUnscaledValue = bigIntegerUnscaledValue.negate();
            }
            byte[] byteArray = bigIntegerUnscaledValue.toByteArray();
            if (iSignum < 0) {
                byte b = byteArray[0];
                if ((b & 128) == 0) {
                    byteArray[0] = (byte) (b | 128);
                } else {
                    updateLength(1L);
                    this.buffer.writeUInt8(128L);
                }
            }
            updateLength(byteArray.length);
            WriteBuffer writeBuffer = this.buffer;
            writeBuffer.getClass();
            writeBuffer.writeBytes(byteArray, 0, byteArray.length);
            return;
        }
        long jLongValue = bigIntegerUnscaledValue.longValue();
        if (iSignum != 0 || zIsNegativeZero) {
            if (zIsNegativeZero) {
                updateLength(1L);
                this.buffer.writeByte((byte) -128);
                return;
            }
            if (jLongValue == Long.MIN_VALUE) {
                updateLength(9L);
                this.buffer.writeUInt8(128L);
                this.buffer.writeUInt64(jLongValue);
                return;
            }
            if (jLongValue >= -127 && jLongValue <= 127) {
                updateLength(1L);
                this.buffer.writeInt8(jLongValue);
                return;
            }
            if (jLongValue >= -32767 && jLongValue <= 32767) {
                updateLength(2L);
                this.buffer.writeInt16(jLongValue);
                return;
            }
            if (jLongValue >= -8388607 && jLongValue <= 8388607) {
                updateLength(3L);
                this.buffer.writeInt24(jLongValue);
                return;
            }
            if (jLongValue >= -2147483647L && jLongValue <= 2147483647L) {
                updateLength(4L);
                this.buffer.writeInt32(jLongValue);
                return;
            }
            if (jLongValue >= -549755813887L && jLongValue <= 549755813887L) {
                updateLength(5L);
                this.buffer.writeInt40(jLongValue);
                return;
            }
            if (jLongValue >= -140737488355327L && jLongValue <= 140737488355327L) {
                updateLength(6L);
                this.buffer.writeInt48(jLongValue);
            } else if (jLongValue < -36028797018963967L || jLongValue > 36028797018963967L) {
                updateLength(8L);
                this.buffer.writeInt64(jLongValue);
            } else {
                updateLength(7L);
                this.buffer.writeInt56(jLongValue);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // com.amazon.ion.IonWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeFloat(double r5) throws java.io.IOException {
        /*
            r4 = this;
            r4.prepareValue()
            boolean r0 = r4.isFloatBinary32Enabled
            if (r0 == 0) goto L24
            float r0 = (float) r5
            double r1 = (double) r0
            int r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r3 != 0) goto L24
            r5 = 5
            r4.updateLength(r5)
            com.amazon.ion.impl.bin.WriteBuffer r5 = r4.buffer
            r1 = 68
            r5.writeUInt8(r1)
            com.amazon.ion.impl.bin.WriteBuffer r5 = r4.buffer
            int r6 = java.lang.Float.floatToRawIntBits(r0)
            long r0 = (long) r6
            r5.writeUInt32(r0)
            goto L39
        L24:
            r0 = 9
            r4.updateLength(r0)
            com.amazon.ion.impl.bin.WriteBuffer r0 = r4.buffer
            r1 = 72
            r0.writeUInt8(r1)
            com.amazon.ion.impl.bin.WriteBuffer r0 = r4.buffer
            long r5 = java.lang.Double.doubleToRawLongBits(r5)
            r0.writeUInt64(r5)
        L39:
            r4.finishValue()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.bin.IonRawBinaryWriter.writeFloat(double):void");
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        prepareValue();
        if (j == 0) {
            updateLength(1L);
            this.buffer.writeByte((byte) 32);
        } else if (j >= 0) {
            writeTypedUInt(32, j);
        } else if (j == Long.MIN_VALUE) {
            updateLength(9L);
            this.buffer.writeUInt8(56L);
            this.buffer.writeUInt64(j);
        } else {
            writeTypedUInt(48, -j);
        }
        finishValue();
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public void writeIonVersionMarker() throws IOException {
        this.buffer.writeBytes(IVM);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        prepareValue();
        updateLength(1L);
        this.buffer.writeByte(NULL_NULL);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        if (str == null) {
            writeNull(IonType.STRING);
            return;
        }
        prepareValue();
        Utf8StringEncoder.Result resultEncode = this.utf8StringEncoder.encode(str);
        int i = resultEncode.encodedLength;
        byte[] bArr = resultEncode.buffer;
        long jPosition = this.buffer.position();
        if (i <= 13) {
            this.buffer.writeUInt8(i | (-128));
        } else {
            this.buffer.writeUInt8(-114L);
            this.buffer.writeVarUInt(i);
        }
        this.buffer.writeBytes(bArr, 0, i);
        updateLength(this.buffer.position() - jPosition);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbol(String str) throws IOException {
        throw new UnsupportedOperationException("Symbol writing via string is not supported in low-level binary writer");
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbolToken(SymbolToken symbolToken) throws IOException {
        if (symbolToken == null) {
            writeNull(IonType.SYMBOL);
        } else {
            writeSymbolToken(symbolToken.getSid());
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        if (timestamp == null) {
            writeNull(IonType.TIMESTAMP);
            return;
        }
        prepareValue();
        updateLength(1L);
        pushContainer(ContainerType.VALUE);
        this.buffer.writeByte(TIMESTAMP_TYPE);
        if (timestamp._offset == null) {
            updateLength(1L);
            this.buffer.writeByte((byte) -64);
        } else {
            writeVarInt(r2.intValue());
        }
        writeVarUInt(timestamp._year);
        int iOrdinal = timestamp._precision.ordinal();
        if (iOrdinal >= Timestamp.Precision.MONTH.ordinal()) {
            writeVarUInt(timestamp._month);
        }
        if (iOrdinal >= Timestamp.Precision.DAY.ordinal()) {
            writeVarUInt(timestamp._day);
        }
        if (iOrdinal >= Timestamp.Precision.MINUTE.ordinal()) {
            writeVarUInt(timestamp._hour);
            writeVarUInt(timestamp._minute);
        }
        if (iOrdinal >= Timestamp.Precision.SECOND.ordinal()) {
            writeVarUInt(timestamp._second);
            BigDecimal bigDecimal = timestamp._fraction;
            if (bigDecimal != null) {
                BigInteger bigIntegerUnscaledValue = bigDecimal.unscaledValue();
                int i = -bigDecimal.scale();
                if (!bigIntegerUnscaledValue.equals(BigInteger.ZERO) || i <= -1) {
                    writeDecimalValue(bigDecimal);
                }
            }
        }
        patchSingleByteTypedOptimisticValue(TIMESTAMP_TYPE, popContainer());
        finishValue();
    }

    public final void writeTypedBytes(int i, byte[] bArr, int i2, int i3) {
        int iWriteVarUInt = i3 + 1;
        if (i3 < 14) {
            this.buffer.writeUInt8(i | i3);
        } else {
            this.buffer.writeUInt8(i | 14);
            iWriteVarUInt += this.buffer.writeVarUInt(i3);
        }
        updateLength(iWriteVarUInt);
        this.buffer.writeBytes(bArr, i2, i3);
    }

    public final void writeTypedUInt(int i, long j) {
        if (j <= 255) {
            updateLength(2L);
            this.buffer.writeUInt8(i | 1);
            this.buffer.writeUInt8(j);
            return;
        }
        if (j <= 65535) {
            updateLength(3L);
            this.buffer.writeUInt8(i | 2);
            this.buffer.writeUInt16(j);
            return;
        }
        if (j <= 16777215) {
            updateLength(4L);
            this.buffer.writeUInt8(i | 3);
            this.buffer.writeUInt24(j);
            return;
        }
        if (j <= 4294967295L) {
            updateLength(5L);
            this.buffer.writeUInt8(i | 4);
            this.buffer.writeUInt32(j);
            return;
        }
        if (j <= 1099511627775L) {
            updateLength(6L);
            this.buffer.writeUInt8(i | 5);
            this.buffer.writeUInt40(j);
        } else if (j <= 281474976710655L) {
            updateLength(7L);
            this.buffer.writeUInt8(i | 6);
            this.buffer.writeUInt48(j);
        } else if (j <= 72057594037927935L) {
            updateLength(8L);
            this.buffer.writeUInt8(i | 7);
            this.buffer.writeUInt56(j);
        } else {
            updateLength(9L);
            this.buffer.writeUInt8(i | 8);
            this.buffer.writeUInt64(j);
        }
    }

    public final void writeVarInt(long j) {
        updateLength(this.buffer.writeVarInt(j));
    }

    public final void writeVarUInt(long j) {
        if (j < 0) {
            throw new IonException("Cannot write negative value as unsigned");
        }
        updateLength(this.buffer.writeVarUInt(j));
    }

    @Override // com.amazon.ion.impl.bin._Private_IonRawWriter
    public void addTypeAnnotationSymbol(int i) {
        if (this.depth == 0 && i == 3) {
            this.hasTopLevelSymbolTableAnnotation = true;
        }
        this.currentAnnotationSids.add(Integer.valueOf(i));
    }

    @Override // com.amazon.ion.impl.bin._Private_IonRawWriter
    public void setFieldNameSymbol(int i) {
        if (!isInStruct()) {
            throw new IonException("Cannot set field name outside of struct context");
        }
        this.currentFieldSid = i;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.BLOB);
            return;
        }
        prepareValue();
        writeTypedBytes(-96, bArr, i, i2);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.CLOB);
            return;
        }
        prepareValue();
        writeTypedBytes(-112, bArr, i, i2);
        finishValue();
    }

    @Override // com.amazon.ion.impl.bin._Private_IonRawWriter
    public void writeSymbolToken(int i) throws IOException {
        if (!isIVM(i)) {
            checkSid(i);
            prepareValue();
            writeTypedUInt(112, i);
            finishValue();
            return;
        }
        throw new IonException("Direct writing of IVM is not supported in low-level binary writer");
    }

    @Override // com.amazon.ion.impl.bin._Private_IonRawWriter
    public void setTypeAnnotationSymbols(int... iArr) {
        clearAnnotations();
        if (iArr != null) {
            for (int i : iArr) {
                addTypeAnnotationSymbol(i);
            }
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        byte b = NULL_NULL;
        if (ionType != null && (b = NULLS[ionType.ordinal()]) == 0) {
            throw new IllegalArgumentException("Cannot write a null for: " + ionType);
        }
        prepareValue();
        updateLength(1L);
        this.buffer.writeByte(b);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        int i;
        if (bigInteger == null) {
            writeNull(IonType.INT);
            return;
        }
        if (bigInteger.compareTo(BIG_INT_LONG_MIN_VALUE) >= 0 && bigInteger.compareTo(BIG_INT_LONG_MAX_VALUE) <= 0) {
            writeInt(bigInteger.longValue());
            return;
        }
        prepareValue();
        if (bigInteger.signum() < 0) {
            bigInteger = bigInteger.negate();
            i = 48;
        } else {
            i = 32;
        }
        byte[] byteArray = bigInteger.toByteArray();
        writeTypedBytes(i, byteArray, 0, byteArray.length);
        finishValue();
    }

    @Override // com.amazon.ion.impl.bin.AbstractIonWriter
    public void writeString(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            writeNull(IonType.STRING);
            return;
        }
        prepareValue();
        writeTypedBytes(-128, bArr, i, i2);
        finishValue();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
    }
}
