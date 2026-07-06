package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.IonException;
import com.amazon.ion.impl.IonBinary;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BlockedBuffer {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static int _defaultBlockSizeMin = 0;
    public static int _defaultBlockSizeUpperLimit = 0;
    public static int _validate_count = 0;
    public static boolean debugValidation = false;
    public static final boolean test_with_no_version_checking = false;
    public ArrayList<bbBlock> _blocks;
    public int _buf_limit;
    public int _lastCapacity;
    public int _mutation_version;
    public Object _mutator;
    public int _next_block_position;
    public int _version;
    public int _blockSizeMin = _defaultBlockSizeMin;
    public int _blockSizeUpperLimit = _defaultBlockSizeUpperLimit;
    public TreeSet<Monitor> _updatelist = new TreeSet<>(CompareMonitor.instance);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BlockedBufferException extends IonException {
        public static final long serialVersionUID = 1582507845614969389L;

        public BlockedBufferException() {
        }

        public BlockedBufferException(String str) {
            super(str);
        }

        public BlockedBufferException(String str, Throwable th) {
            super(str, th);
        }

        public BlockedBufferException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BlockedByteInputStream extends InputStream {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public int _blockPosition;
        public BlockedBuffer _buf;
        public bbBlock _curr;
        public int _mark;
        public int _pos;
        public int _version;

        public BlockedByteInputStream(BlockedBuffer blockedBuffer) {
            this(0, blockedBuffer);
        }

        public final void _set_position(int i) {
            this._pos = i;
            bbBlock bbblockFindBlockForRead = this._buf.findBlockForRead(this, this._version, this._curr, i);
            this._curr = bbblockFindBlockForRead;
            this._blockPosition = this._pos - bbblockFindBlockForRead._offset;
        }

        public final boolean _validate() {
            return this._buf._validate();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            this._buf = null;
            this._pos = -1;
        }

        public final void fail_on_version_change() throws IOException {
            if (this._buf._version == this._version) {
                return;
            }
            close();
            throw new BlockedBufferException("buffer has been changed!");
        }

        @Override // java.io.InputStream
        public final void mark(int i) {
            this._mark = this._pos;
        }

        public final int position() {
            return this._pos;
        }

        @Override // java.io.InputStream
        public final int read(byte[] bArr, int i, int i2) throws IOException {
            if (this._buf == null) {
                throw new IOException("stream is closed");
            }
            fail_on_version_change();
            int i3 = this._pos;
            int i4 = this._buf._buf_limit;
            if (i3 > i4) {
                throw new IllegalArgumentException();
            }
            int i5 = i2 + i3;
            if (i5 <= i4) {
                i4 = i5;
            }
            while (true) {
                int i6 = this._pos;
                if (i6 >= i4) {
                    fail_on_version_change();
                    return this._pos - i3;
                }
                bbBlock bbblock = this._curr;
                int i7 = this._blockPosition;
                int i8 = bbblock._limit - i7;
                if (i8 > i4 - i6) {
                    i8 = i4 - i6;
                    this._blockPosition = i7 + i8;
                } else {
                    this._curr = this._buf.findBlockForRead(this, this._version, bbblock, i6 + i8);
                    this._blockPosition = 0;
                }
                System.arraycopy(bbblock._buffer, i7, bArr, i, i8);
                this._pos += i8;
                i += i8;
            }
        }

        @Override // java.io.InputStream
        public final void reset() throws IOException {
            int i = this._mark;
            if (i == -1) {
                throw new IOException("mark not set");
            }
            _set_position(i);
        }

        public final BlockedByteInputStream setPosition(int i) throws IOException {
            if (this._buf == null) {
                throw new IOException("stream is closed");
            }
            fail_on_version_change();
            if (i < 0 || i > this._buf._buf_limit) {
                throw new IllegalArgumentException();
            }
            _set_position(i);
            fail_on_version_change();
            return this;
        }

        @Override // java.io.InputStream
        public final long skip(long j) throws IOException {
            if (j < 0 || j > 2147483647L) {
                throw new IllegalArgumentException("we only handle buffer less than 2147483647 bytes in length");
            }
            if (this._buf == null) {
                throw new IOException("stream is closed");
            }
            fail_on_version_change();
            int i = this._pos;
            BlockedBuffer blockedBuffer = this._buf;
            int i2 = blockedBuffer._buf_limit;
            if (i >= i2) {
                return -1L;
            }
            int i3 = (int) j;
            if (i3 == 0) {
                return 0L;
            }
            int i4 = i3 + i;
            if (i4 <= i2) {
                i2 = i4;
            }
            int i5 = this._blockPosition;
            bbBlock bbblock = this._curr;
            if (i2 > i5 + bbblock._offset) {
                this._curr = blockedBuffer.findBlockForRead(this, this._version, bbblock, i2);
            }
            this._blockPosition = i2 - this._curr._offset;
            this._pos = i2;
            fail_on_version_change();
            return this._pos - i;
        }

        public final void sync() throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            this._version = blockedBuffer._version;
            this._curr = null;
            this._pos = 0;
        }

        public final int writeTo(OutputStream outputStream, int i) throws IOException {
            if (this._buf == null) {
                throw new IOException("stream is closed");
            }
            fail_on_version_change();
            int i2 = this._pos;
            int i3 = this._buf._buf_limit;
            if (i2 > i3) {
                throw new IllegalArgumentException();
            }
            int i4 = i + i2;
            if (i4 <= i3) {
                i3 = i4;
            }
            while (true) {
                int i5 = this._pos;
                if (i5 >= i3) {
                    break;
                }
                bbBlock bbblock = this._curr;
                int i6 = bbblock._limit;
                int i7 = this._blockPosition;
                int i8 = i6 - i7;
                boolean z = i8 > i3 - i5;
                if (z) {
                    i8 = i3 - i5;
                }
                outputStream.write(bbblock._buffer, i7, i8);
                int i9 = this._pos + i8;
                this._pos = i9;
                if (z) {
                    this._blockPosition += i8;
                    break;
                }
                bbBlock bbblockFindBlockForRead = this._buf.findBlockForRead(this, this._version, this._curr, i9);
                this._curr = bbblockFindBlockForRead;
                this._blockPosition = this._pos - bbblockFindBlockForRead._offset;
            }
            fail_on_version_change();
            return this._pos - i2;
        }

        public BlockedByteInputStream(BlockedBuffer blockedBuffer, int i) {
            this(i, blockedBuffer);
        }

        public BlockedByteInputStream(int i, BlockedBuffer blockedBuffer) {
            if (blockedBuffer != null) {
                this._version = blockedBuffer._version;
                this._buf = blockedBuffer;
                _set_position(i);
                this._mark = -1;
                return;
            }
            throw new IllegalArgumentException();
        }

        @Override // java.io.InputStream
        public final int read() throws IOException {
            if (this._buf != null) {
                fail_on_version_change();
                int i = this._pos;
                BlockedBuffer blockedBuffer = this._buf;
                if (i >= blockedBuffer._buf_limit) {
                    return -1;
                }
                int i2 = this._blockPosition;
                bbBlock bbblock = this._curr;
                if (i2 >= bbblock._limit) {
                    this._curr = blockedBuffer.findBlockForRead(this, this._version, bbblock, i);
                    this._blockPosition = 0;
                }
                byte[] bArr = this._curr._buffer;
                int i3 = this._blockPosition;
                int i4 = bArr[i3] & 255;
                this._blockPosition = i3 + 1;
                this._pos++;
                fail_on_version_change();
                return i4;
            }
            throw new IOException("input stream is closed");
        }

        public final int writeTo(ByteWriter byteWriter, int i) throws IOException {
            if (this._buf != null) {
                fail_on_version_change();
                int i2 = this._pos;
                int i3 = this._buf._buf_limit;
                if (i2 <= i3) {
                    int i4 = i + i2;
                    if (i4 <= i3) {
                        i3 = i4;
                    }
                    while (true) {
                        int i5 = this._pos;
                        if (i5 >= i3) {
                            break;
                        }
                        bbBlock bbblock = this._curr;
                        int i6 = bbblock._limit;
                        int i7 = this._blockPosition;
                        int i8 = i6 - i7;
                        boolean z = i8 > i3 - i5;
                        if (z) {
                            i8 = i3 - i5;
                        }
                        byteWriter.write(bbblock._buffer, i7, i8);
                        int i9 = this._pos + i8;
                        this._pos = i9;
                        if (z) {
                            this._blockPosition += i8;
                            break;
                        }
                        bbBlock bbblockFindBlockForRead = this._buf.findBlockForRead(this, this._version, this._curr, i9);
                        this._curr = bbblockFindBlockForRead;
                        this._blockPosition = this._pos - bbblockFindBlockForRead._offset;
                    }
                    fail_on_version_change();
                    return this._pos - i2;
                }
                throw new IllegalArgumentException();
            }
            throw new IOException("stream is closed");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BufferedOutputStream extends OutputStream {
        public BlockedBuffer _buffer;
        public BlockedByteOutputStream _writer;

        public BufferedOutputStream() {
            this(new BlockedBuffer());
        }

        public int byteSize() {
            return this._buffer._buf_limit;
        }

        public byte[] getBytes() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteSize());
            writeBytes(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this._writer.write(i);
        }

        public int writeBytes(OutputStream outputStream) throws IOException {
            BlockedBuffer blockedBuffer = this._buffer;
            int i = blockedBuffer._buf_limit;
            int i2 = blockedBuffer._version;
            blockedBuffer.start_mutate(this, i2);
            bbBlock bbblockFindBlockForRead = null;
            int i3 = 0;
            while (i3 < i) {
                bbblockFindBlockForRead = this._buffer.findBlockForRead(this, i2, bbblockFindBlockForRead, i3);
                if (bbblockFindBlockForRead == null) {
                    throw new IOException("buffer missing expected bytes");
                }
                int iBytesAvailableToRead = bbblockFindBlockForRead.bytesAvailableToRead(i3);
                if (iBytesAvailableToRead <= 0) {
                    throw new IOException("buffer missing expected bytes");
                }
                outputStream.write(bbblockFindBlockForRead._buffer, 0, iBytesAvailableToRead);
                i3 += iBytesAvailableToRead;
            }
            this._buffer.end_mutate(this);
            return i3;
        }

        public BufferedOutputStream(BlockedBuffer blockedBuffer) {
            this._buffer = blockedBuffer;
            this._writer = new BlockedByteOutputStream(blockedBuffer);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this._writer.write(bArr, i, i2);
        }

        public int getBytes(byte[] bArr, int i, int i2) throws IOException {
            return writeBytes((OutputStream) new SimpleByteBuffer(bArr, i, i2, false).getWriter());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CompareMonitor implements Comparator<Monitor> {
        public static CompareMonitor instance = new CompareMonitor();

        public static CompareMonitor getComparator() {
            return instance;
        }

        @Override // java.util.Comparator
        public int compare(Monitor monitor, Monitor monitor2) {
            return monitor.getMemberIdOffset() - monitor2.getMemberIdOffset();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Monitor {
        int getMemberIdOffset();

        boolean notifyInsert(int i, int i2);

        boolean notifyRemove(int i, int i2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PositionMonitor implements Monitor {
        public int _pos;

        public PositionMonitor(int i) {
            this._pos = i;
        }

        @Override // com.amazon.ion.impl.BlockedBuffer.Monitor
        public int getMemberIdOffset() {
            return this._pos;
        }

        @Override // com.amazon.ion.impl.BlockedBuffer.Monitor
        public boolean notifyInsert(int i, int i2) {
            return false;
        }

        @Override // com.amazon.ion.impl.BlockedBuffer.Monitor
        public boolean notifyRemove(int i, int i2) {
            return false;
        }
    }

    static {
        resetParameters();
    }

    public BlockedBuffer() {
        start_mutate(this, 0);
        init(0, null);
        end_mutate(this);
    }

    public static void resetParameters() {
        debugValidation = false;
        _defaultBlockSizeMin = 32768;
        _defaultBlockSizeUpperLimit = 32768;
    }

    public static void setBlockSizeParameters(int i, int i2, boolean z) {
        debugValidation = z;
        setBlockSizeParameters(i, i2);
    }

    public boolean _validate() {
        bbBlock bbblock;
        int i;
        int i2 = _validate_count + 1;
        _validate_count = i2;
        if (i2 % 128 != 0) {
            return true;
        }
        int i3 = 0;
        boolean z = i2 == 28 && i2 < 0;
        int i4 = 0;
        while (i3 < this._blocks.size() && (i = (bbblock = this._blocks.get(i3))._idx) != -1) {
            if (i != i3) {
                PrintStream printStream = System.out;
                StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("block ", i3, ": index is wrong, it is ");
                sbM.append(bbblock._idx);
                sbM.append(" it should be ");
                sbM.append(i3);
                printStream.println(sbM.toString());
                z = true;
            }
            int i5 = bbblock._offset;
            if (i5 != i4) {
                PrintStream printStream2 = System.out;
                StringBuilder sbM2 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("block ", i3, ": starting offset is wrong, it is ");
                sbM2.append(bbblock._offset);
                sbM2.append(" should be ");
                sbM2.append(i4);
                printStream2.println(sbM2.toString());
            } else {
                int i6 = bbblock._limit;
                if (i6 < 0 || i6 > bbblock._buffer.length) {
                    PrintStream printStream3 = System.out;
                    StringBuilder sbM3 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("block ", i3, ": limit is out of range, it is ");
                    sbM3.append(bbblock._limit);
                    sbM3.append(" should be between 0 and ");
                    sbM3.append(bbblock._buffer.length);
                    printStream3.println(sbM3.toString());
                } else if (i6 != 0 || (bbblock._idx == this._next_block_position - 1 && i5 == this._buf_limit)) {
                    i4 += bbblock._limit;
                    i3++;
                } else {
                    System.out.println("block " + i3 + ": has a ZERO limit");
                }
            }
            z = true;
            i4 += bbblock._limit;
            i3++;
        }
        if (i3 != this._next_block_position) {
            System.out.println("next block position is wrong, is " + this._next_block_position + " should be " + i3);
            z = true;
        }
        for (int i7 = i3 + 1; i7 < this._blocks.size(); i7++) {
            bbBlock bbblock2 = this._blocks.get(i7);
            if (bbblock2._offset != -1) {
                PrintStream printStream4 = System.out;
                StringBuilder sbM4 = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("block ", i7, ": (in freed range) has non -1 offset, offset is ");
                sbM4.append(bbblock2._offset);
                printStream4.println(sbM4.toString());
                z = true;
            }
        }
        if (i4 != this._buf_limit) {
            System.out.println("buffer _buf_limit: limit is incorrect, it is " + this._buf_limit + " should be " + i4);
            z = true;
        }
        int i8 = this._next_block_position;
        if (i8 > 0) {
            bbBlock bbblock3 = this._blocks.get(i8 - 1);
            if (bbblock3._offset + bbblock3._limit != this._buf_limit) {
                System.out.println("last block " + bbblock3._idx + " limit isn't _buf_limit (" + this._buf_limit + "):  calc'd last block limit is " + bbblock3._offset + " + " + bbblock3._limit + " = " + (bbblock3._offset + bbblock3._limit));
                z = true;
            }
        }
        int i9 = this._buf_limit;
        if (i9 < 0 || (i9 > 0 && this._next_block_position < 1)) {
            System.out.println("this._buf_limit " + this._buf_limit + " is invalid");
            z = true;
        }
        if (z) {
            System.out.println("failed with validation count = " + _validate_count);
        }
        return !z;
    }

    public final bbBlock addBlock(Object obj, int i, int i2, int i3, int i4) {
        bbBlock bbblock;
        int i5 = this._next_block_position;
        while (true) {
            if (i5 >= this._blocks.size()) {
                bbblock = null;
                break;
            }
            bbblock = this._blocks.get(this._next_block_position);
            if (bbblock._buffer.length >= i4) {
                this._blocks.remove(this._next_block_position);
                break;
            }
            i5++;
        }
        if (bbblock == null) {
            if (i4 <= this._blockSizeUpperLimit) {
                int iNextBlockSize = 0;
                while (iNextBlockSize < i4) {
                    iNextBlockSize = nextBlockSize(obj, i);
                }
                i4 = iNextBlockSize;
            }
            bbblock = new bbBlock(i4);
        }
        if (i2 == -1) {
            i2 = 0;
            while (i2 < this._next_block_position && this._blocks.get(i2)._offset >= 0 && i3 < this._blocks.get(i2)._offset) {
                i2++;
            }
        }
        bbblock._idx = i2;
        bbblock._offset = i3;
        this._blocks.add(i2, bbblock);
        this._next_block_position++;
        while (true) {
            i2++;
            if (i2 >= this._next_block_position) {
                return bbblock;
            }
            this._blocks.get(i2)._idx = i2;
        }
    }

    public final void adjustOffsets(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return;
        }
        this._next_block_position += i3;
        while (true) {
            i++;
            if (i >= this._next_block_position) {
                this._buf_limit += i2;
                return;
            } else {
                bbBlock bbblock = this._blocks.get(i);
                bbblock._offset += i2;
                bbblock._idx += i3;
            }
        }
    }

    public final void clear(Object obj, int i) {
        this._buf_limit = 0;
        for (int i2 = 0; i2 < this._blocks.size(); i2++) {
            this._blocks.get(i2).clearBlock();
        }
        bbBlock bbblock = this._blocks.get(0);
        bbblock._idx = 0;
        bbblock._offset = 0;
        bbblock._limit = 0;
        this._next_block_position = 1;
    }

    public int end_mutate(Object obj) {
        int i = this._version;
        int i2 = this._mutation_version;
        if (i != i2) {
            throw new BlockedBufferException("version mismatch failure");
        }
        if (obj != this._mutator) {
            throw new BlockedBufferException("caller mismatch failure");
        }
        int i3 = i2 + 1;
        this._version = i3;
        this._mutation_version = 0;
        this._mutator = null;
        return i3;
    }

    public bbBlock findBlockForRead(Object obj, int i, bbBlock bbblock, int i2) {
        if (i2 > this._buf_limit) {
            throw new BlockedBufferException("invalid position");
        }
        if (bbblock != null && (bbblock.containsForRead(i2) || (i2 == this._buf_limit && i2 - bbblock._offset == bbblock._limit))) {
            return bbblock;
        }
        if (i2 != this._buf_limit) {
            return findBlockHelper(i2, 0, this._next_block_position);
        }
        bbBlock bbblock2 = this._blocks.get(this._next_block_position - 1);
        if (bbblock2.containsForWrite(i2)) {
            return bbblock2;
        }
        throw new BlockedBufferException("valid position can't be found!");
    }

    public bbBlock findBlockForWrite(Object obj, int i, bbBlock bbblock, int i2) {
        int i3;
        if (i2 > this._buf_limit + 1) {
            throw new BlockedBufferException("writes must be contiguous");
        }
        if (bbblock == null || !bbblock.hasRoomToWrite(i2, 1)) {
            bbBlock bbblockFindBlockHelper = i2 == this._buf_limit ? this._blocks.get(this._next_block_position - 1) : (bbblock == null || i2 != bbblock._offset + bbblock._limit) ? findBlockHelper(i2, 0, this._next_block_position) : this._blocks.get(bbblock._idx + 1);
            if (bbblockFindBlockHelper.hasRoomToWrite(i2, 1)) {
                return bbblockFindBlockHelper;
            }
            int i4 = bbblockFindBlockHelper._idx;
            return i4 < this._next_block_position - 1 ? this._blocks.get(i4 + 1) : addBlock(obj, i, i4 + 1, i2, nextBlockSize(obj, i));
        }
        if (bbblock._offset + bbblock._limit == i2 && (i3 = bbblock._idx) < this._next_block_position) {
            bbBlock bbblock2 = this._blocks.get(i3 + 1);
            if (bbblock2.containsForWrite(i2)) {
                return bbblock2;
            }
        }
        return bbblock;
    }

    public final bbBlock findBlockHelper(int i, int i2, int i3) {
        if (i3 - i2 > 3) {
            int i4 = (i3 + i2) / 2;
            return this._blocks.get(i4)._offset > i ? findBlockHelper(i, i2, i4) : findBlockHelper(i, i4, i3);
        }
        while (i2 < i3) {
            bbBlock bbblock = this._blocks.get(i2);
            if (i <= bbblock._offset + bbblock._limit) {
                if (bbblock.containsForRead(i)) {
                    return bbblock;
                }
                if (bbblock._offset >= i) {
                    break;
                }
            }
            i2++;
        }
        return this._blocks.get(i2 - 1);
    }

    public int getVersion() {
        return this._version;
    }

    public final bbBlock init(int i, bbBlock bbblock) {
        int i2;
        this._lastCapacity = _defaultBlockSizeMin;
        this._blockSizeUpperLimit = _defaultBlockSizeUpperLimit;
        while (true) {
            i2 = this._lastCapacity;
            if (i2 >= i || i2 >= this._blockSizeUpperLimit) {
                break;
            }
            nextBlockSize(this, 0);
        }
        int i3 = i / i2;
        if (bbblock != null) {
            i3 = 1;
        }
        this._blocks = new ArrayList<>(i3);
        if (bbblock == null) {
            bbblock = new bbBlock(nextBlockSize(this, 0));
        }
        this._blocks.add(bbblock);
        this._next_block_position = 1;
        int length = bbblock._buffer.length;
        while (true) {
            i -= length;
            if (i <= 0) {
                return bbblock;
            }
            bbBlock bbblock2 = new bbBlock(nextBlockSize(this, 0));
            bbblock2._idx = -1;
            this._blocks.add(bbblock2);
            length = bbblock2._buffer.length;
        }
    }

    public int insert(Object obj, int i, bbBlock bbblock, int i2, int i3) {
        int iUnusedBlockCapacity = i3 - bbblock.unusedBlockCapacity();
        if (iUnusedBlockCapacity <= 0) {
            insertInCurrOnly(obj, i, bbblock, i2, i3);
            return i3;
        }
        int i4 = bbblock._idx;
        bbBlock bbblock2 = i4 < this._next_block_position + (-1) ? this._blocks.get(i4 + 1) : null;
        if (bbblock2 != null && iUnusedBlockCapacity <= bbblock2.unusedBlockCapacity()) {
            insertInCurrAndNext(obj, i, bbblock, i2, i3, bbblock2);
            return i3;
        }
        int i5 = iUnusedBlockCapacity % this._blockSizeUpperLimit;
        int iBytesAvailableToRead = bbblock.bytesAvailableToRead(i2);
        if (i5 < iBytesAvailableToRead) {
            i5 = iBytesAvailableToRead;
        }
        if (i5 >= iUnusedBlockCapacity || iUnusedBlockCapacity >= this._blockSizeUpperLimit) {
            iUnusedBlockCapacity = i5;
        }
        bbBlock bbblockInsertMakeNewTailBlock = insertMakeNewTailBlock(obj, i, bbblock, iUnusedBlockCapacity);
        if (i3 > bbblockInsertMakeNewTailBlock.unusedBlockCapacity() + bbblock.unusedBlockCapacity()) {
            insertAsManyBlocksAsNeeded(obj, i, bbblock, i2, i3, bbblockInsertMakeNewTailBlock);
            return i3;
        }
        insertBlock(bbblockInsertMakeNewTailBlock);
        insertInCurrAndNext(obj, i, bbblock, i2, i3, bbblockInsertMakeNewTailBlock);
        return i3;
    }

    public final int insertAsManyBlocksAsNeeded(Object obj, int i, bbBlock bbblock, int i2, int i3, bbBlock bbblock2) {
        int i4 = i2 - bbblock._offset;
        int i5 = bbblock._limit - i4;
        int iUnusedBlockCapacity = bbblock.unusedBlockCapacity();
        int i6 = bbblock._limit + iUnusedBlockCapacity;
        bbblock._limit = i6;
        int i7 = bbblock._offset + i6;
        int length = (i3 - iUnusedBlockCapacity) - bbblock2._buffer.length;
        int i8 = 0;
        while (length > 0) {
            i8++;
            bbBlock bbblock3 = new bbBlock(nextBlockSize(obj, i));
            int length2 = bbblock3._buffer.length;
            bbblock3._limit = length2;
            if (length2 > length) {
                bbblock3._limit = length;
            }
            int i9 = bbblock._idx + i8;
            bbblock3._idx = i9;
            bbblock3._offset = i7;
            this._blocks.add(i9, bbblock3);
            int i10 = bbblock3._limit;
            length -= i10;
            i7 += i10;
        }
        int i11 = i8 + 1;
        bbblock2._limit = bbblock2._buffer.length;
        int i12 = bbblock._idx + i11;
        bbblock2._idx = i12;
        bbblock2._offset = i7;
        this._blocks.add(i12, bbblock2);
        adjustOffsets(bbblock2._idx, i3, i11);
        notifyInsert(i2, i3);
        if (i5 > 0) {
            System.arraycopy(bbblock._buffer, i4, bbblock2._buffer, bbblock2._limit - i5, i5);
        }
        return i3;
    }

    public final void insertBlock(bbBlock bbblock) {
        this._blocks.add(bbblock._idx, bbblock);
        this._next_block_position++;
        int i = bbblock._idx;
        while (true) {
            i++;
            if (i >= this._next_block_position) {
                return;
            }
            this._blocks.get(i)._idx++;
        }
    }

    public final int insertInCurrAndNext(Object obj, int i, bbBlock bbblock, int i2, int i3, bbBlock bbblock2) {
        int iBytesAvailableToRead = bbblock.bytesAvailableToRead(i2);
        int iUnusedBlockCapacity = i3 - bbblock.unusedBlockCapacity();
        int i4 = iUnusedBlockCapacity > iBytesAvailableToRead ? iBytesAvailableToRead : iUnusedBlockCapacity;
        int i5 = bbblock2._limit;
        if (i5 > 0) {
            byte[] bArr = bbblock2._buffer;
            System.arraycopy(bArr, 0, bArr, iUnusedBlockCapacity, i5);
        }
        bbblock2._limit += iUnusedBlockCapacity;
        if (i4 > 0) {
            System.arraycopy(bbblock._buffer, bbblock._limit - i4, bbblock2._buffer, iUnusedBlockCapacity - i4, i4);
        }
        int i6 = iBytesAvailableToRead - i4;
        if (i6 > 0) {
            int i7 = i2 - bbblock._offset;
            byte[] bArr2 = bbblock._buffer;
            System.arraycopy(bArr2, i7, bArr2, i7 + i3, i6);
        }
        int iUnusedBlockCapacity2 = bbblock.unusedBlockCapacity();
        if (iUnusedBlockCapacity2 > 0) {
            bbblock._limit += iUnusedBlockCapacity2;
            bbblock2._offset += iUnusedBlockCapacity2;
        }
        adjustOffsets(bbblock2._idx, i3, 0);
        notifyInsert(i2, i3);
        return i3;
    }

    public final int insertInCurrOnly(Object obj, int i, bbBlock bbblock, int i2, int i3) {
        byte[] bArr = bbblock._buffer;
        int i4 = bbblock._offset;
        System.arraycopy(bArr, i2 - i4, bArr, (i2 - i4) + i3, bbblock.bytesAvailableToRead(i2));
        bbblock._limit += i3;
        adjustOffsets(bbblock._idx, i3, 0);
        notifyInsert(i2, i3);
        return i3;
    }

    public final bbBlock insertMakeNewTailBlock(Object obj, int i, bbBlock bbblock, int i2) {
        int iNextBlockSize;
        if (i2 < this._blockSizeUpperLimit) {
            do {
                iNextBlockSize = nextBlockSize(obj, i);
            } while (iNextBlockSize < i2);
            i2 = iNextBlockSize;
        }
        bbBlock bbblock2 = new bbBlock(i2);
        bbblock2._idx = bbblock._idx + 1;
        bbblock2._offset = bbblock._offset + bbblock._limit;
        return bbblock2;
    }

    public boolean mutation_in_progress(Object obj, int i) {
        if (this._mutation_version != i) {
            throw new BlockedBufferException("unexpected update lock conflict");
        }
        if (obj == this._mutator) {
            return true;
        }
        throw new BlockedBufferException("caller mismatch failure");
    }

    public final int nextBlockSize(Object obj, int i) {
        int i2 = this._lastCapacity;
        if (i2 == 0) {
            this._lastCapacity = this._blockSizeMin;
        } else if (i2 < this._blockSizeUpperLimit) {
            this._lastCapacity = i2 * 2;
        }
        return this._lastCapacity;
    }

    public void notifyInsert(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        Iterator<Monitor> it = this._updatelist.tailSet(new PositionMonitor(i)).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public void notifyRegister(Monitor monitor) {
        this._updatelist.add(monitor);
    }

    public void notifyRemove(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        Iterator<Monitor> it = this._updatelist.tailSet(new PositionMonitor(i)).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public void notifyUnregister(Monitor monitor) {
        this._updatelist.remove(monitor);
    }

    public bbBlock remove(Object obj, int i, bbBlock bbblock, int i2, int i3) {
        int i4;
        int i5;
        if (i3 == 0) {
            return bbblock;
        }
        if (i3 >= 0) {
            int i6 = i2 + i3;
            int i7 = this._buf_limit;
            if (i6 <= i7) {
                int i8 = bbblock._idx;
                if (i2 == 0 && i3 == i7) {
                    clear(obj, i);
                    notifyRemove(0, i3);
                    return null;
                }
                int i9 = i2 - bbblock._offset;
                int i10 = bbblock._limit;
                int i11 = i10 - i9;
                if (i11 > i3) {
                    i11 = i3;
                }
                if (i11 == i10) {
                    i5 = i8 - 1;
                    i4 = i3;
                } else {
                    int i12 = (i10 - i9) - i11;
                    if (i12 > 0) {
                        byte[] bArr = bbblock._buffer;
                        System.arraycopy(bArr, i10 - i12, bArr, i9, i12);
                    }
                    i4 = i3 - i11;
                    bbblock._limit -= i11;
                    if (i4 > 0) {
                        int i13 = bbblock._idx + 1;
                        bbblock = this._blocks.get(i13);
                        i8 = i13;
                        i5 = i8;
                    } else {
                        i5 = i8;
                    }
                }
                int i14 = 0;
                while (i4 > 0) {
                    int i15 = bbblock._limit;
                    if (i4 < i15) {
                        break;
                    }
                    i4 -= i15;
                    this._blocks.remove(i8);
                    i14++;
                    bbblock.clearBlock();
                    this._blocks.add(bbblock);
                    if (i8 < this._next_block_position - i14) {
                        bbblock = this._blocks.get(i8);
                    } else {
                        if (i8 <= 0) {
                            throw new BlockedBufferException("fatal - no current block!");
                        }
                        i8--;
                        bbblock = this._blocks.get(i8);
                    }
                }
                if (i4 > 0) {
                    byte[] bArr2 = bbblock._buffer;
                    System.arraycopy(bArr2, i4, bArr2, 0, bbblock._limit - i4);
                    bbblock._limit -= i4;
                    bbblock._offset += i4;
                }
                adjustOffsets(i5, -i3, -i14);
                notifyRemove(i2, i3);
                return bbblock;
            }
        }
        throw new IllegalArgumentException();
    }

    public final int size() {
        return this._buf_limit;
    }

    public void start_mutate(Object obj, int i) {
        if (this._mutation_version != 0 || this._mutator != null) {
            throw new BlockedBufferException("lock conflict");
        }
        if (i != this._version) {
            throw new BlockedBufferException("version conflict on update");
        }
        this._mutator = obj;
        this._mutation_version = i;
    }

    public bbBlock truncate(Object obj, int i, int i2) {
        if (i2 < 0 || i2 > this._buf_limit) {
            throw new IllegalArgumentException();
        }
        bbBlock bbblock = null;
        for (int i3 = this._next_block_position - 1; i3 >= 0; i3--) {
            bbblock = this._blocks.get(i3);
            if (bbblock._offset <= i2) {
                break;
            }
            bbblock.clearBlock();
        }
        if (bbblock == null) {
            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("block missing at position ", i2));
        }
        this._next_block_position = bbblock._idx + 1;
        bbblock._limit = i2 - bbblock._offset;
        this._buf_limit = i2;
        return findBlockForRead(Integer.valueOf(i2), i, bbblock, i2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class bbBlock {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public byte[] _buffer;
        public int _idx;
        public int _limit;
        public int _offset;

        public bbBlock(int i) {
            this._buffer = new byte[i];
        }

        public final int blockCapacity() {
            return this._buffer.length;
        }

        public final int blockOffsetFromAbsolute(int i) {
            return i - this._offset;
        }

        public final int bytesAvailableToRead(int i) {
            return this._limit - (i - this._offset);
        }

        public final int bytesAvailableToWrite(int i) {
            return this._buffer.length - (i - this._offset);
        }

        public bbBlock clearBlock() {
            this._idx = -1;
            this._offset = -1;
            this._limit = 0;
            return this;
        }

        public final boolean containsForRead(int i) {
            int i2 = this._offset;
            return i >= i2 && i < i2 + this._limit;
        }

        public final boolean containsForWrite(int i) {
            int i2 = this._offset;
            return i >= i2 && i <= i2 + this._limit;
        }

        public final boolean hasRoomToWrite(int i, int i2) {
            return i2 <= this._buffer.length - (i - this._offset);
        }

        public final int unusedBlockCapacity() {
            return this._buffer.length - this._limit;
        }

        public bbBlock(byte[] bArr) {
            this._buffer = bArr;
            this._limit = bArr.length;
        }
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BlockedBuffer m243clone() {
        BlockedBuffer blockedBuffer = new BlockedBuffer(this._buf_limit);
        int i = this._buf_limit;
        bbBlock bbblock = blockedBuffer._blocks.get(0);
        int length = bbblock._buffer.length;
        int i2 = 0;
        for (int i3 = 0; i3 < this._blocks.size(); i3++) {
            bbBlock bbblock2 = this._blocks.get(i3);
            int i4 = bbblock2._limit;
            if (i4 >= 1) {
                int i5 = bbblock2._offset + i4;
                int i6 = length - i2;
                if (i4 > i6) {
                    i4 = i6;
                }
                System.arraycopy(bbblock2._buffer, 0, bbblock._buffer, i2, i4);
                i2 += i4;
                if (i5 >= i) {
                    break;
                }
            }
        }
        bbblock._limit = i2;
        blockedBuffer._buf_limit = i2;
        return blockedBuffer;
    }

    public static void setBlockSizeParameters(int i, int i2) {
        if (i >= 0 && i2 >= i) {
            _defaultBlockSizeMin = i;
            _defaultBlockSizeUpperLimit = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BlockedByteOutputStream extends OutputStream {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public int _blockPosition;
        public BlockedBuffer _buf;
        public bbBlock _curr;
        public int _pos;
        public int _version;

        public BlockedByteOutputStream() {
            BlockedBuffer blockedBuffer = new BlockedBuffer();
            this._buf = blockedBuffer;
            this._version = blockedBuffer._version;
            _set_position(0);
        }

        public final void _set_position(int i) {
            this._pos = i;
            bbBlock bbblockFindBlockForRead = this._buf.findBlockForRead(this, this._version, this._curr, i);
            this._curr = bbblockFindBlockForRead;
            this._blockPosition = this._pos - bbblockFindBlockForRead._offset;
        }

        public final boolean _validate() {
            return this._buf._validate();
        }

        public final void _write(int i) throws IOException {
            if (bytesAvailableToWriteInCurr(this._pos) < 1) {
                this._curr = this._buf.findBlockForWrite(this, this._version, this._curr, this._pos);
                this._blockPosition = 0;
            }
            bbBlock bbblock = this._curr;
            byte[] bArr = bbblock._buffer;
            int i2 = this._blockPosition;
            int i3 = i2 + 1;
            this._blockPosition = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = this._pos + 1;
            this._pos = i4;
            if (i3 > bbblock._limit) {
                bbblock._limit = i3;
                BlockedBuffer blockedBuffer = this._buf;
                if (i4 > blockedBuffer._buf_limit) {
                    blockedBuffer._buf_limit = i4;
                }
            }
        }

        public final int bytesAvailableToWriteInCurr(int i) {
            bbBlock bbblock = this._curr;
            return bbblock._idx < this._buf._next_block_position + (-1) ? bbblock.bytesAvailableToRead(i) : bbblock._buffer.length - (i - bbblock._offset);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            this._buf = null;
            this._pos = -1;
        }

        public final void end_write() {
            this._version = this._buf.end_mutate(this);
        }

        public final void fail_on_version_change() throws IOException {
            if (this._buf._version == this._version) {
                return;
            }
            close();
            throw new BlockedBufferException("buffer has been changed!");
        }

        public final void insert(int i) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            if (i < 0) {
                throw new IllegalArgumentException();
            }
            if (i > 0) {
                blockedBuffer.start_mutate(this, this._version);
                this._buf.insert(this, this._version, this._curr, this._pos, i);
                this._version = this._buf.end_mutate(this);
            }
        }

        public final int position() {
            return this._pos;
        }

        public final void remove(int i) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            blockedBuffer.start_mutate(this, this._version);
            this._curr = this._buf.remove(this, this._version, this._curr, this._pos, i);
            this._version = this._buf.end_mutate(this);
        }

        public final BlockedByteOutputStream setPosition(int i) throws IOException {
            if (this._buf == null) {
                throw new IOException("stream is closed");
            }
            fail_on_version_change();
            if (i < 0 || i > this._buf._buf_limit) {
                throw new IllegalArgumentException();
            }
            _set_position(i);
            fail_on_version_change();
            return this;
        }

        public final void start_write() {
            this._buf.start_mutate(this, this._version);
        }

        public final void sync() throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            this._version = blockedBuffer._version;
            this._pos = 0;
            this._curr = null;
        }

        public final void truncate() throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            if (blockedBuffer._buf_limit == this._pos) {
                return;
            }
            blockedBuffer.start_mutate(this, this._version);
            this._curr = this._buf.truncate(this, this._version, this._pos);
            this._version = this._buf.end_mutate(this);
        }

        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer == null) {
                throw new IOException("stream is closed");
            }
            blockedBuffer.start_mutate(this, this._version);
            _write(i);
            this._version = this._buf.end_mutate(this);
        }

        public BlockedByteOutputStream(BlockedBuffer blockedBuffer) {
            this._buf = blockedBuffer;
            this._version = blockedBuffer._version;
            _set_position(0);
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer != null) {
                blockedBuffer.start_mutate(this, this._version);
                _write(bArr, i, i2);
                this._version = this._buf.end_mutate(this);
                return;
            }
            throw new IOException("stream is closed");
        }

        public final void insert(byte b) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer != null) {
                blockedBuffer.start_mutate(this, this._version);
                this._buf.insert(this, this._version, this._curr, this._pos, 1);
                _write(b);
                this._version = this._buf.end_mutate(this);
                return;
            }
            throw new IOException("stream is closed");
        }

        public final void _write(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            while (i < i3) {
                int iBytesAvailableToWriteInCurr = bytesAvailableToWriteInCurr(this._pos);
                int i4 = i3 - i;
                if (iBytesAvailableToWriteInCurr > i4) {
                    iBytesAvailableToWriteInCurr = i4;
                }
                if (iBytesAvailableToWriteInCurr > 0) {
                    System.arraycopy(bArr, i, this._curr._buffer, this._blockPosition, iBytesAvailableToWriteInCurr);
                    i += iBytesAvailableToWriteInCurr;
                    int i5 = this._pos + iBytesAvailableToWriteInCurr;
                    this._pos = i5;
                    int i6 = this._blockPosition + iBytesAvailableToWriteInCurr;
                    this._blockPosition = i6;
                    bbBlock bbblock = this._curr;
                    if (i6 > bbblock._limit) {
                        bbblock._limit = i6;
                        BlockedBuffer blockedBuffer = this._buf;
                        if (i5 > blockedBuffer._buf_limit) {
                            blockedBuffer._buf_limit = i5;
                        }
                    }
                }
                if (i >= i3) {
                    return;
                }
                bbBlock bbblockFindBlockForWrite = this._buf.findBlockForWrite(this, this._version, this._curr, this._pos);
                this._curr = bbblockFindBlockForWrite;
                this._blockPosition = this._pos - bbblockFindBlockForWrite._offset;
            }
        }

        public BlockedByteOutputStream(BlockedBuffer blockedBuffer, int i) {
            if (blockedBuffer != null && i >= 0 && i <= blockedBuffer._buf_limit) {
                this._buf = blockedBuffer;
                this._version = blockedBuffer._version;
                _set_position(0);
                return;
            }
            throw new IllegalArgumentException();
        }

        public final void write(InputStream inputStream) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer != null) {
                blockedBuffer.start_mutate(this, this._version);
                _write(inputStream, -1);
                this._version = this._buf.end_mutate(this);
                return;
            }
            throw new IOException("stream is closed");
        }

        public final void insert(byte[] bArr, int i, int i2) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer != null) {
                blockedBuffer.start_mutate(this, this._version);
                this._buf.insert(this, this._version, this._curr, this._pos, i2);
                _write(bArr, i, i2);
                this._version = this._buf.end_mutate(this);
                return;
            }
            throw new IOException("stream is closed");
        }

        public final void write(InputStream inputStream, int i) throws IOException {
            BlockedBuffer blockedBuffer = this._buf;
            if (blockedBuffer != null) {
                blockedBuffer.start_mutate(this, this._version);
                _write(inputStream, i);
                this._version = this._buf.end_mutate(this);
                return;
            }
            throw new IOException("stream is closed");
        }

        public final void _write(InputStream inputStream, int i) throws IOException {
            if (i == 0) {
                return;
            }
            boolean z = i == -1;
            while (true) {
                int iBytesAvailableToWriteInCurr = bytesAvailableToWriteInCurr(this._pos);
                int i2 = z ? iBytesAvailableToWriteInCurr : i;
                if (i2 > iBytesAvailableToWriteInCurr) {
                    i2 = iBytesAvailableToWriteInCurr;
                }
                int i3 = inputStream.read(this._curr._buffer, this._blockPosition, i2);
                if (i3 == -1) {
                    return;
                }
                if (i3 > 0) {
                    int i4 = this._pos + i3;
                    this._pos = i4;
                    int i5 = this._blockPosition + i3;
                    this._blockPosition = i5;
                    bbBlock bbblock = this._curr;
                    if (i5 > bbblock._limit) {
                        bbblock._limit = i5;
                        BlockedBuffer blockedBuffer = this._buf;
                        if (i4 > blockedBuffer._buf_limit) {
                            blockedBuffer._buf_limit = i4;
                        }
                    }
                }
                if (i3 == iBytesAvailableToWriteInCurr) {
                    bbBlock bbblockFindBlockForWrite = this._buf.findBlockForWrite(this, this._version, this._curr, this._pos);
                    this._curr = bbblockFindBlockForWrite;
                    this._blockPosition = this._pos - bbblockFindBlockForWrite._offset;
                }
                if (!z && (i = i - i3) < 1) {
                    return;
                }
            }
        }
    }

    public BlockedBuffer(int i) {
        start_mutate(this, 0);
        init(i, null);
        end_mutate(this);
    }

    public BlockedBuffer(byte[] bArr) {
        start_mutate(this, 0);
        init(0, new bbBlock(bArr));
        this._buf_limit = bArr.length;
        end_mutate(this);
    }

    public BlockedBuffer(InputStream inputStream) throws IOException {
        try {
            new IonBinary.Writer(this).write(inputStream);
        } finally {
            inputStream.close();
        }
    }
}
