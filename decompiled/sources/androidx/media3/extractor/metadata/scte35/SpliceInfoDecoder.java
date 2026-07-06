package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SpliceInfoDecoder extends SimpleMetadataDecoder {
    public static final int TYPE_PRIVATE_COMMAND = 255;
    public static final int TYPE_SPLICE_INSERT = 5;
    public static final int TYPE_SPLICE_NULL = 0;
    public static final int TYPE_SPLICE_SCHEDULE = 4;
    public static final int TYPE_TIME_SIGNAL = 6;
    public final ParsableByteArray sectionData = new ParsableByteArray();
    public final ParsableBitArray sectionHeader = new ParsableBitArray();
    public TimestampAdjuster timestampAdjuster;

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
        if (timestampAdjuster == null || metadataInputBuffer.subsampleOffsetUs != timestampAdjuster.getTimestampOffsetUs()) {
            TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(metadataInputBuffer.timeUs);
            this.timestampAdjuster = timestampAdjuster2;
            timestampAdjuster2.adjustSampleTimestamp(metadataInputBuffer.timeUs - metadataInputBuffer.subsampleOffsetUs);
        }
        byte[] bArrArray = byteBuffer.array();
        int iLimit = byteBuffer.limit();
        this.sectionData.reset(bArrArray, iLimit);
        this.sectionHeader.reset(bArrArray, iLimit);
        this.sectionHeader.skipBits(39);
        long bits = (((long) this.sectionHeader.readBits(1)) << 32) | ((long) this.sectionHeader.readBits(32));
        this.sectionHeader.skipBits(20);
        int bits2 = this.sectionHeader.readBits(12);
        int bits3 = this.sectionHeader.readBits(8);
        this.sectionData.skipBytes(14);
        Metadata.Entry fromSection = bits3 != 0 ? bits3 != 255 ? bits3 != 4 ? bits3 != 5 ? bits3 != 6 ? null : TimeSignalCommand.parseFromSection(this.sectionData, bits, this.timestampAdjuster) : SpliceInsertCommand.parseFromSection(this.sectionData, bits, this.timestampAdjuster) : SpliceScheduleCommand.parseFromSection(this.sectionData) : PrivateCommand.parseFromSection(this.sectionData, bits2, bits) : new SpliceNullCommand();
        return fromSection == null ? new Metadata(new Metadata.Entry[0]) : new Metadata(fromSection);
    }
}
