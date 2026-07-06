package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.SlowMotionData;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SefReader {
    public static final int LENGTH_OF_ONE_SDR = 12;
    public static final int SAMSUNG_TAIL_SIGNATURE = 1397048916;
    public static final int STATE_CHECKING_FOR_SEF = 1;
    public static final int STATE_READING_SDRS = 2;
    public static final int STATE_READING_SEF_DATA = 3;
    public static final int STATE_SHOULD_CHECK_FOR_SEF = 0;
    public static final String TAG = "SefReader";
    public static final int TAIL_FOOTER_LENGTH = 8;
    public static final int TAIL_HEADER_LENGTH = 12;
    public static final int TYPE_SLOW_MOTION_DATA = 2192;
    public static final int TYPE_SUPER_SLOW_DEFLICKERING_ON = 2820;
    public static final int TYPE_SUPER_SLOW_MOTION_BGM = 2817;
    public static final int TYPE_SUPER_SLOW_MOTION_DATA = 2816;
    public static final int TYPE_SUPER_SLOW_MOTION_EDIT_DATA = 2819;
    public final List<DataReference> dataReferences = new ArrayList();
    public int readerState = 0;
    public int tailLength;
    public static final Splitter COLON_SPLITTER = Splitter.on(':');
    public static final Splitter ASTERISK_SPLITTER = Splitter.on('*');

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DataReference {
        public final int dataType;
        public final int size;
        public final long startOffset;

        public DataReference(int i, long j, int i2) {
            this.dataType = i;
            this.startOffset = j;
            this.size = i2;
        }
    }

    public static int nameToDataType(String str) throws ParserException {
        str.getClass();
        switch (str) {
            case "SlowMotion_Data":
                return 2192;
            case "Super_SlowMotion_Edit_Data":
                return 2819;
            case "Super_SlowMotion_Data":
                return 2816;
            case "Super_SlowMotion_Deflickering_On":
                return 2820;
            case "Super_SlowMotion_BGM":
                return 2817;
            default:
                throw ParserException.createForMalformedContainer("Invalid SEF name", null);
        }
    }

    public static SlowMotionData readSlowMotionData(ParsableByteArray parsableByteArray, int i) throws ParserException {
        ArrayList arrayList = new ArrayList();
        parsableByteArray.getClass();
        List<String> listSplitToList = ASTERISK_SPLITTER.splitToList(parsableByteArray.readString(i, Charsets.UTF_8));
        for (int i2 = 0; i2 < listSplitToList.size(); i2++) {
            List<String> listSplitToList2 = COLON_SPLITTER.splitToList(listSplitToList.get(i2));
            if (listSplitToList2.size() != 3) {
                throw ParserException.createForMalformedContainer(null, null);
            }
            try {
                arrayList.add(new SlowMotionData.Segment(Long.parseLong(listSplitToList2.get(0)), Long.parseLong(listSplitToList2.get(1)), 1 << (Integer.parseInt(listSplitToList2.get(2)) - 1)));
            } catch (NumberFormatException e) {
                throw ParserException.createForMalformedContainer(null, e);
            }
        }
        return new SlowMotionData(arrayList);
    }

    public final void checkForSefData(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        extractorInput.readFully(parsableByteArray.data, 0, 8);
        this.tailLength = parsableByteArray.readLittleEndianInt() + 8;
        if (parsableByteArray.readInt() != 1397048916) {
            positionHolder.position = 0L;
        } else {
            positionHolder.position = extractorInput.getPosition() - ((long) (this.tailLength - 12));
            this.readerState = 2;
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder, List<Metadata.Entry> list) throws IOException {
        int i = this.readerState;
        long j = 0;
        if (i == 0) {
            long length = extractorInput.getLength();
            if (length != -1 && length >= 8) {
                j = length - 8;
            }
            positionHolder.position = j;
            this.readerState = 1;
        } else if (i == 1) {
            checkForSefData(extractorInput, positionHolder);
        } else if (i == 2) {
            readSdrs(extractorInput, positionHolder);
        } else {
            if (i != 3) {
                throw new IllegalStateException();
            }
            readSefData(extractorInput, list);
            positionHolder.position = 0L;
        }
        return 1;
    }

    public final void readSdrs(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        long length = extractorInput.getLength();
        int i = this.tailLength - 20;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i);
        extractorInput.readFully(parsableByteArray.data, 0, i);
        for (int i2 = 0; i2 < i / 12; i2++) {
            parsableByteArray.skipBytes(2);
            short littleEndianShort = parsableByteArray.readLittleEndianShort();
            if (littleEndianShort == 2192 || littleEndianShort == 2816 || littleEndianShort == 2817 || littleEndianShort == 2819 || littleEndianShort == 2820) {
                this.dataReferences.add(new DataReference(littleEndianShort, (length - ((long) this.tailLength)) - ((long) parsableByteArray.readLittleEndianInt()), parsableByteArray.readLittleEndianInt()));
            } else {
                parsableByteArray.skipBytes(8);
            }
        }
        if (this.dataReferences.isEmpty()) {
            positionHolder.position = 0L;
        } else {
            this.readerState = 3;
            positionHolder.position = this.dataReferences.get(0).startOffset;
        }
    }

    public final void readSefData(ExtractorInput extractorInput, List<Metadata.Entry> list) throws IOException {
        long position = extractorInput.getPosition();
        int length = (int) ((extractorInput.getLength() - extractorInput.getPosition()) - ((long) this.tailLength));
        ParsableByteArray parsableByteArray = new ParsableByteArray(length);
        extractorInput.readFully(parsableByteArray.data, 0, length);
        for (int i = 0; i < this.dataReferences.size(); i++) {
            DataReference dataReference = this.dataReferences.get(i);
            parsableByteArray.setPosition((int) (dataReference.startOffset - position));
            parsableByteArray.skipBytes(4);
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int iNameToDataType = nameToDataType(parsableByteArray.readString(littleEndianInt, Charsets.UTF_8));
            int i2 = dataReference.size - (littleEndianInt + 8);
            if (iNameToDataType == 2192) {
                list.add(readSlowMotionData(parsableByteArray, i2));
            } else if (iNameToDataType != 2816 && iNameToDataType != 2817 && iNameToDataType != 2819 && iNameToDataType != 2820) {
                throw new IllegalStateException();
            }
        }
    }

    public void reset() {
        this.dataReferences.clear();
        this.readerState = 0;
    }
}
