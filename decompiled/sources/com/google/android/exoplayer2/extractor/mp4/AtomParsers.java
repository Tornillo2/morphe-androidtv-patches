package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.AudioFocusManager$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ExtractorUtil;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.metadata.mp4.SmtaMetadataEntry;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.exoplayer2.video.DolbyVisionConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AtomParsers {
    public static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    public static final String TAG = "AtomParsers";
    public static final int TYPE_clcp = 1668047728;
    public static final int TYPE_mdta = 1835299937;
    public static final int TYPE_meta = 1835365473;
    public static final int TYPE_nclc = 1852009571;
    public static final int TYPE_nclx = 1852009592;
    public static final int TYPE_sbtl = 1935832172;
    public static final int TYPE_soun = 1936684398;
    public static final int TYPE_subt = 1937072756;
    public static final int TYPE_text = 1952807028;
    public static final int TYPE_vide = 1986618469;
    public static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ChunkIterator {
        public final ParsableByteArray chunkOffsets;
        public final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        public int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        public int remainingSamplesPerChunkChanges;
        public final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EsdsData {
        public final long bitrate;
        public final byte[] initializationData;
        public final String mimeType;
        public final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j, long j2) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j;
            this.peakBitrate = j2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;

        @Nullable
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        public final ParsableByteArray data;
        public final int fixedSampleSize;
        public final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom, Format format) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if ("audio/raw".equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (unsignedIntToInt == 0 || unsignedIntToInt % pcmFrameSize != 0) {
                    Log.w("AtomParsers", "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + unsignedIntToInt);
                    unsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = unsignedIntToInt == 0 ? -1 : unsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == -1 ? this.data.readUnsignedIntToInt() : i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        public int currentByte;
        public final ParsableByteArray data;
        public final int fieldSize;
        public final int sampleCount;
        public int sampleIndex;

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            int unsignedByte = this.data.readUnsignedByte();
            this.currentByte = unsignedByte;
            return (unsignedByte & 240) >> 4;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TkhdData {
        public final long duration;
        public final int id;
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    public static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    public static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        return jArr[0] <= j2 && j2 < jArr[Util.constrainValue(4, 0, length)] && jArr[Util.constrainValue(jArr.length - 4, 0, length)] < j3 && j3 <= j;
    }

    public static int findBoxPosition(ParsableByteArray parsableByteArray, int i, int i2, int i3) throws ParserException {
        int i4 = parsableByteArray.position;
        ExtractorUtil.checkContainerInput(i4 >= i2, null);
        while (i4 - i2 < i3) {
            parsableByteArray.setPosition(i4);
            int i5 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i5 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i) {
                return i4;
            }
            i4 += i5;
        }
        return -1;
    }

    public static int getTrackTypeForHdlr(int i) {
        if (i == 1936684398) {
            return 1;
        }
        if (i == 1986618469) {
            return 2;
        }
        if (i == 1952807028 || i == 1935832172 || i == 1937072756 || i == 1668047728) {
            return 3;
        }
        return i == 1835365473 ? 5 : -1;
    }

    public static void maybeSkipRemainingMetaAtomHeaderBytes(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.position;
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            i += 4;
        }
        parsableByteArray.setPosition(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r23, int r24, int r25, int r26, int r27, java.lang.String r28, boolean r29, @androidx.annotation.Nullable com.google.android.exoplayer2.drm.DrmInitData r30, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r31, int r32) throws com.google.android.exoplayer2.ParserException {
        /*
            Method dump skipped, instruction units count: 822
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    @Nullable
    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        int i3 = i + 8;
        String string = null;
        Integer numValueOf = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i6 = parsableByteArray.readInt();
            int i7 = parsableByteArray.readInt();
            if (i7 == 1718775137) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i7 == 1935894637) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4, Charsets.UTF_8);
            } else if (i7 == 1935894633) {
                i4 = i3;
                i5 = i6;
            }
            i3 += i6;
        }
        if (!"cenc".equals(string) && !"cbc1".equals(string) && !"cens".equals(string) && !"cbcs".equals(string)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(numValueOf != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, string);
        ExtractorUtil.checkContainerInput(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    @Nullable
    public static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1701606260);
        if (leafAtomOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    public static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, null, -1L, -1L);
        }
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        long j = unsignedInt2;
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        if (j <= 0) {
            j = -1;
        }
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, j, unsignedInt > 0 ? unsignedInt : -1L);
    }

    public static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & 127;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & 127);
        }
        return i;
    }

    public static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    @Nullable
    public static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.position < i) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    @Nullable
    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1751411826);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(1801812339);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(1768715124);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != 1835299937) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int i = parsableByteArray.readInt();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i2] = parsableByteArray.readString(i3 - 8, Charsets.UTF_8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int i4 = parsableByteArray2.position;
            int i5 = parsableByteArray2.readInt();
            int i6 = parsableByteArray2.readInt() - 1;
            if (i6 < 0 || i6 >= i) {
                AudioFocusManager$$ExternalSyntheticOutline0.m("Skipped metadata with unknown key index: ", i6, "AtomParsers");
            } else {
                MdtaMetadataEntry mdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, i4 + i5, strArr[i6]);
                if (mdtaMetadataEntryFromIlst != null) {
                    arrayList.add(mdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(i4 + i5);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        if (i == 1835365492) {
            parsableByteArray.readDelimiterTerminatedString((char) 0);
            String delimiterTerminatedString = parsableByteArray.readDelimiterTerminatedString((char) 0);
            if (delimiterTerminatedString != null) {
                Format.Builder builder = new Format.Builder();
                builder.id = Integer.toString(i3);
                builder.sampleMimeType = delimiterTerminatedString;
                stsdData.format = new Format(builder);
            }
        }
    }

    public static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    public static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    @Nullable
    public static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    @Nullable
    public static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int i3 = parsableByteArray.position;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i4 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, i3, i4)) != null) {
                return commonEncryptionSinfFromParent;
            }
            i3 += i4;
        }
        return null;
    }

    @Nullable
    public static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int i6 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = unsignedByte & 15;
                    i4 = (unsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += i6;
        }
    }

    @Nullable
    public static Metadata parseSmta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (true) {
            int i2 = parsableByteArray.position;
            if (i2 >= i) {
                return null;
            }
            int i3 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1935766900) {
                if (i3 < 14) {
                    return null;
                }
                parsableByteArray.skipBytes(5);
                int unsignedByte = parsableByteArray.readUnsignedByte();
                if (unsignedByte != 12 && unsignedByte != 13) {
                    return null;
                }
                float f = unsignedByte == 12 ? 240.0f : 120.0f;
                parsableByteArray.skipBytes(1);
                return new Metadata(new SmtaMetadataEntry(f, parsableByteArray.readUnsignedByte()));
            }
            parsableByteArray.setPosition(i2 + i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x01fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x020c A[ADDED_TO_REGION, LOOP:9: B:92:0x020c->B:96:0x0216, LOOP_START, PHI: r16
      0x020c: PHI (r16v4 int) = (r16v3 int), (r16v5 int) binds: [B:91:0x020a, B:96:0x0216] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x021c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer2.extractor.mp4.Track r38, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r39, com.google.android.exoplayer2.extractor.GaplessInfoHolder r40) throws com.google.android.exoplayer2.ParserException {
        /*
            Method dump skipped, instruction units count: 1198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    public static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, @Nullable DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        int i4 = 0;
        while (i4 < i3) {
            int i5 = parsableByteArray.position;
            int i6 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i6 > 0, "childAtomSize must be positive");
            int i7 = parsableByteArray.readInt();
            if (i7 == 1635148593 || i7 == 1635148595 || i7 == 1701733238 || i7 == 1831958048 || i7 == 1836070006 || i7 == 1752589105 || i7 == 1751479857 || i7 == 1932670515 || i7 == 1211250227 || i7 == 1987063864 || i7 == 1987063865 || i7 == 1635135537 || i7 == 1685479798 || i7 == 1685479729 || i7 == 1685481573 || i7 == 1685481521) {
                StsdData stsdData2 = stsdData;
                int i8 = i4;
                parseVideoSampleEntry(parsableByteArray, i7, i5, i6, i, i2, drmInitData, stsdData2, i8);
                stsdData = stsdData2;
                i4 = i8;
            } else if (i7 == 1836069985 || i7 == 1701733217 || i7 == 1633889587 || i7 == 1700998451 || i7 == 1633889588 || i7 == 1835823201 || i7 == 1685353315 || i7 == 1685353317 || i7 == 1685353320 || i7 == 1685353324 || i7 == 1685353336 || i7 == 1935764850 || i7 == 1935767394 || i7 == 1819304813 || i7 == 1936684916 || i7 == 1953984371 || i7 == 778924082 || i7 == 778924083 || i7 == 1835557169 || i7 == 1835560241 || i7 == 1634492771 || i7 == 1634492791 || i7 == 1970037111 || i7 == 1332770163 || i7 == 1716281667) {
                StsdData stsdData3 = stsdData;
                parseAudioSampleEntry(parsableByteArray, i7, i5, i6, i, str, z, drmInitData, stsdData3, i4);
                stsdData = stsdData3;
            } else if (i7 == 1414810956 || i7 == 1954034535 || i7 == 2004251764 || i7 == 1937010800 || i7 == 1664495672) {
                parseTextSampleEntry(parsableByteArray, i7, i5, i6, i, str, stsdData);
            } else if (i7 == 1835365492) {
                parseMetaDataSampleEntry(parsableByteArray, i7, i5, i, stsdData);
            } else if (i7 == 1667329389) {
                Format.Builder builder = new Format.Builder();
                builder.id = Integer.toString(i);
                builder.sampleMimeType = "application/x-camera-motion";
                stsdData.format = new Format(builder);
            }
            parsableByteArray.setPosition(i5 + i6);
            i4++;
        }
        return stsdData;
    }

    public static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        String str2 = "application/ttml+xml";
        ImmutableList immutableListOf = null;
        long j = Long.MAX_VALUE;
        if (i != 1414810956) {
            if (i == 1954034535) {
                int i5 = i3 - 16;
                byte[] bArr = new byte[i5];
                parsableByteArray.readBytes(bArr, 0, i5);
                immutableListOf = ImmutableList.of(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i == 2004251764) {
                str2 = "application/x-mp4-vtt";
            } else if (i == 1937010800) {
                j = 0;
            } else {
                if (i != 1664495672) {
                    throw new IllegalStateException();
                }
                stsdData.requiredSampleTransformation = 1;
                str2 = "application/x-mp4-cea-608";
            }
        }
        Format.Builder builder = new Format.Builder();
        builder.id = Integer.toString(i4);
        builder.sampleMimeType = str2;
        builder.language = str;
        builder.subsampleOffsetUs = j;
        builder.initializationData = immutableListOf;
        stsdData.format = new Format(builder);
    }

    public static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i2 = parsableByteArray.position;
        int i3 = fullAtomVersion == 0 ? 4 : 8;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            j = -9223372036854775807L;
            if (i5 >= i3) {
                parsableByteArray.skipBytes(i3);
                break;
            }
            if (parsableByteArray.data[i2 + i5] != -1) {
                long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                if (unsignedInt != 0) {
                    j = unsignedInt;
                }
            } else {
                i5++;
            }
        }
        parsableByteArray.skipBytes(16);
        int i6 = parsableByteArray.readInt();
        int i7 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i8 = parsableByteArray.readInt();
        int i9 = parsableByteArray.readInt();
        if (i6 == 0 && i7 == 65536 && i8 == -65536 && i9 == 0) {
            i4 = 90;
        } else if (i6 == 0 && i7 == -65536 && i8 == 65536 && i9 == 0) {
            i4 = 270;
        } else if (i6 == -65536 && i7 == 0 && i8 == 0 && i9 == -65536) {
            i4 = 180;
        }
        return new TkhdData(i, j, i4);
    }

    @Nullable
    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, @Nullable DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType;
        Pair<long[], long[]> edts;
        Atom.ContainerAtom containerAtomOfType2 = containerAtom.getContainerAtomOfType(1835297121);
        containerAtomOfType2.getClass();
        Atom.LeafAtom leafAtomOfType = containerAtomOfType2.getLeafAtomOfType(1751411826);
        leafAtomOfType.getClass();
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(leafAtomOfType.data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(1953196132);
        leafAtomOfType2.getClass();
        TkhdData tkhd = parseTkhd(leafAtomOfType2.data);
        long j2 = j == -9223372036854775807L ? tkhd.duration : j;
        long mvhd = parseMvhd(leafAtom.data);
        long jScaleLargeTimestamp = j2 != -9223372036854775807L ? Util.scaleLargeTimestamp(j2, 1000000L, mvhd) : -9223372036854775807L;
        Atom.ContainerAtom containerAtomOfType3 = containerAtomOfType2.getContainerAtomOfType(1835626086);
        containerAtomOfType3.getClass();
        Atom.ContainerAtom containerAtomOfType4 = containerAtomOfType3.getContainerAtomOfType(1937007212);
        containerAtomOfType4.getClass();
        Atom.LeafAtom leafAtomOfType3 = containerAtomOfType2.getLeafAtomOfType(1835296868);
        leafAtomOfType3.getClass();
        Pair<Long, String> mdhd = parseMdhd(leafAtomOfType3.data);
        Atom.LeafAtom leafAtomOfType4 = containerAtomOfType4.getLeafAtomOfType(1937011556);
        if (leafAtomOfType4 == null) {
            throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", null);
        }
        StsdData stsd = parseStsd(leafAtomOfType4.data, tkhd.id, tkhd.rotationDegrees, (String) mdhd.second, drmInitData, z2);
        if (z || (containerAtomOfType = containerAtom.getContainerAtomOfType(1701082227)) == null || (edts = parseEdts(containerAtomOfType)) == null) {
            jArr = null;
            jArr2 = null;
        } else {
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        return new Track(tkhd.id, trackTypeForHdlr, ((Long) mdhd.first).longValue(), mvhd, jScaleLargeTimestamp, stsd.format, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    public static List<TrackSampleTable> parseTraks(Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder, long j, @Nullable DrmInitData drmInitData, boolean z, boolean z2, Function<Track, Track> function) throws ParserException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < containerAtom.containerChildren.size(); i++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i);
            if (containerAtom2.type == 1953653099) {
                Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(1836476516);
                leafAtomOfType.getClass();
                Track trackApply = function.apply(parseTrak(containerAtom2, leafAtomOfType, j, drmInitData, z, z2));
                if (trackApply != null) {
                    Atom.ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(1835297121);
                    containerAtomOfType.getClass();
                    Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(1835626086);
                    containerAtomOfType2.getClass();
                    Atom.ContainerAtom containerAtomOfType3 = containerAtomOfType2.getContainerAtomOfType(1937007212);
                    containerAtomOfType3.getClass();
                    arrayList.add(parseStbl(trackApply, containerAtomOfType3, gaplessInfoHolder));
                }
            }
        }
        return arrayList;
    }

    public static Pair<Metadata, Metadata> parseUdta(Atom.LeafAtom leafAtom) {
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        Metadata udtaMeta = null;
        Metadata smta = null;
        while (parsableByteArray.bytesLeft() >= 8) {
            int i = parsableByteArray.position;
            int i2 = parsableByteArray.readInt();
            int i3 = parsableByteArray.readInt();
            if (i3 == 1835365473) {
                parsableByteArray.setPosition(i);
                udtaMeta = parseUdtaMeta(parsableByteArray, i + i2);
            } else if (i3 == 1936553057) {
                parsableByteArray.setPosition(i);
                smta = parseSmta(parsableByteArray, i + i2);
            }
            parsableByteArray.setPosition(i + i2);
        }
        return Pair.create(udtaMeta, smta);
    }

    @Nullable
    public static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaAtomHeaderBytes(parsableByteArray);
        while (true) {
            int i2 = parsableByteArray.position;
            if (i2 >= i) {
                return null;
            }
            int i3 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(i2);
                return parseIlst(parsableByteArray, i2 + i3);
            }
            parsableByteArray.setPosition(i2 + i3);
        }
    }

    public static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, @Nullable DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        DrmInitData drmInitData2;
        int i7;
        int i8;
        String str;
        float f;
        List<byte[]> list;
        int i9;
        int i10;
        int i11;
        String str2;
        int i12 = i2;
        int i13 = i3;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray.setPosition(i12 + 16);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int i14 = parsableByteArray.position;
        int iIntValue = i;
        if (iIntValue == 1701733238) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i12, i13);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData2.trackEncryptionBoxes[i6] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(i14);
        }
        String str3 = "video/3gpp";
        String str4 = iIntValue == 1831958048 ? "video/mpeg" : iIntValue == 1211250227 ? "video/3gpp" : null;
        float paspFromParent = 1.0f;
        String str5 = null;
        List<byte[]> listOf = null;
        byte[] projFromParent = null;
        int i15 = -1;
        int iIsoColorPrimariesToColorSpace = -1;
        int i16 = -1;
        int iIsoTransferCharacteristicsToColorTransfer = -1;
        ByteBuffer byteBufferAllocateHdrStaticInfo = null;
        EsdsData esdsData = null;
        boolean z = false;
        while (i14 - i12 < i13) {
            parsableByteArray.setPosition(i14);
            int i17 = parsableByteArray.position;
            int i18 = parsableByteArray.readInt();
            if (i18 == 0 && parsableByteArray.position - i2 == i13) {
                break;
            }
            ExtractorUtil.checkContainerInput(i18 > 0, "childAtomSize must be positive");
            int i19 = parsableByteArray.readInt();
            if (i19 == 1635148611) {
                ExtractorUtil.checkContainerInput(str4 == null, null);
                parsableByteArray.setPosition(i17 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                listOf = avcConfig.initializationData;
                stsdData2.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcConfig.pixelWidthHeightRatio;
                }
                str5 = avcConfig.codecs;
                str4 = "video/avc";
                drmInitData2 = drmInitDataCopyWithSchemeType;
                i7 = i14;
            } else {
                if (i19 == 1752589123) {
                    ExtractorUtil.checkContainerInput(str4 == null, null);
                    parsableByteArray.setPosition(i17 + 8);
                    HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                    listOf = hevcConfig.initializationData;
                    stsdData2.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                    if (!z) {
                        paspFromParent = hevcConfig.pixelWidthHeightRatio;
                    }
                    str5 = hevcConfig.codecs;
                    int i20 = hevcConfig.colorSpace;
                    int i21 = hevcConfig.colorRange;
                    iIsoTransferCharacteristicsToColorTransfer = hevcConfig.colorTransfer;
                    iIsoColorPrimariesToColorSpace = i20;
                    drmInitData2 = drmInitDataCopyWithSchemeType;
                    i7 = i14;
                    i16 = i21;
                    i8 = iIntValue;
                    str = str3;
                    str4 = "video/hevc";
                } else {
                    if (i19 == 1685480259 || i19 == 1685485123) {
                        drmInitData2 = drmInitDataCopyWithSchemeType;
                        i7 = i14;
                        i8 = iIntValue;
                        str = str3;
                        f = paspFromParent;
                        list = listOf;
                        i9 = iIsoColorPrimariesToColorSpace;
                        i10 = i16;
                        i11 = iIsoTransferCharacteristicsToColorTransfer;
                        DolbyVisionConfig dolbyVisionConfig = DolbyVisionConfig.parse(parsableByteArray);
                        if (dolbyVisionConfig != null) {
                            str5 = dolbyVisionConfig.codecs;
                            str4 = "video/dolby-vision";
                        }
                    } else {
                        i7 = i14;
                        if (i19 == 1987076931) {
                            ExtractorUtil.checkContainerInput(str4 == null, null);
                            str2 = iIntValue == 1987063864 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                            parsableByteArray.setPosition(i17 + 12);
                            parsableByteArray.skipBytes(2);
                            boolean z2 = (parsableByteArray.readUnsignedByte() & 1) != 0;
                            int unsignedByte = parsableByteArray.readUnsignedByte();
                            int unsignedByte2 = parsableByteArray.readUnsignedByte();
                            iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(unsignedByte);
                            i16 = z2 ? 1 : 2;
                            iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(unsignedByte2);
                        } else if (i19 == 1635135811) {
                            ExtractorUtil.checkContainerInput(str4 == null, null);
                            str2 = "video/av01";
                        } else if (i19 == 1668050025) {
                            if (byteBufferAllocateHdrStaticInfo == null) {
                                byteBufferAllocateHdrStaticInfo = allocateHdrStaticInfo();
                            }
                            ByteBuffer byteBuffer = byteBufferAllocateHdrStaticInfo;
                            byteBuffer.position(21);
                            byteBuffer.putShort(parsableByteArray.readShort());
                            byteBuffer.putShort(parsableByteArray.readShort());
                            byteBufferAllocateHdrStaticInfo = byteBuffer;
                            drmInitData2 = drmInitDataCopyWithSchemeType;
                        } else if (i19 == 1835295606) {
                            if (byteBufferAllocateHdrStaticInfo == null) {
                                byteBufferAllocateHdrStaticInfo = allocateHdrStaticInfo();
                            }
                            ByteBuffer byteBuffer2 = byteBufferAllocateHdrStaticInfo;
                            short s = parsableByteArray.readShort();
                            short s2 = parsableByteArray.readShort();
                            short s3 = parsableByteArray.readShort();
                            i8 = iIntValue;
                            short s4 = parsableByteArray.readShort();
                            str = str3;
                            short s5 = parsableByteArray.readShort();
                            short s6 = parsableByteArray.readShort();
                            drmInitData2 = drmInitDataCopyWithSchemeType;
                            short s7 = parsableByteArray.readShort();
                            List<byte[]> list2 = listOf;
                            short s8 = parsableByteArray.readShort();
                            long unsignedInt = parsableByteArray.readUnsignedInt();
                            long unsignedInt2 = parsableByteArray.readUnsignedInt();
                            byteBuffer2.position(1);
                            byteBuffer2.putShort(s5);
                            byteBuffer2.putShort(s6);
                            byteBuffer2.putShort(s);
                            byteBuffer2.putShort(s2);
                            byteBuffer2.putShort(s3);
                            byteBuffer2.putShort(s4);
                            byteBuffer2.putShort(s7);
                            byteBuffer2.putShort(s8);
                            byteBuffer2.putShort((short) (unsignedInt / 10000));
                            byteBuffer2.putShort((short) (unsignedInt2 / 10000));
                            byteBufferAllocateHdrStaticInfo = byteBuffer2;
                            listOf = list2;
                            paspFromParent = paspFromParent;
                        } else {
                            drmInitData2 = drmInitDataCopyWithSchemeType;
                            i8 = iIntValue;
                            str = str3;
                            f = paspFromParent;
                            list = listOf;
                            if (i19 == 1681012275) {
                                ExtractorUtil.checkContainerInput(str4 == null, null);
                                str4 = str;
                            } else if (i19 == 1702061171) {
                                ExtractorUtil.checkContainerInput(str4 == null, null);
                                EsdsData esdsFromParent = parseEsdsFromParent(parsableByteArray, i17);
                                String str6 = esdsFromParent.mimeType;
                                byte[] bArr = esdsFromParent.initializationData;
                                listOf = bArr != null ? ImmutableList.of(bArr) : list;
                                esdsData = esdsFromParent;
                                str4 = str6;
                                paspFromParent = f;
                                i14 = i7 + i18;
                                i12 = i2;
                                i13 = i3;
                                stsdData2 = stsdData;
                                iIntValue = i8;
                                str3 = str;
                                drmInitDataCopyWithSchemeType = drmInitData2;
                            } else if (i19 == 1885434736) {
                                paspFromParent = parsePaspFromParent(parsableByteArray, i17);
                                listOf = list;
                                z = true;
                                i14 = i7 + i18;
                                i12 = i2;
                                i13 = i3;
                                stsdData2 = stsdData;
                                iIntValue = i8;
                                str3 = str;
                                drmInitDataCopyWithSchemeType = drmInitData2;
                            } else if (i19 == 1937126244) {
                                projFromParent = parseProjFromParent(parsableByteArray, i17, i18);
                            } else if (i19 == 1936995172) {
                                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                                parsableByteArray.skipBytes(3);
                                if (unsignedByte3 == 0) {
                                    int unsignedByte4 = parsableByteArray.readUnsignedByte();
                                    if (unsignedByte4 == 0) {
                                        i15 = 0;
                                    } else if (unsignedByte4 == 1) {
                                        i15 = 1;
                                    } else if (unsignedByte4 == 2) {
                                        i15 = 2;
                                    } else if (unsignedByte4 == 3) {
                                        i15 = 3;
                                    }
                                }
                            } else {
                                i9 = iIsoColorPrimariesToColorSpace;
                                if (i19 == 1668246642) {
                                    i10 = i16;
                                    if (i9 == -1) {
                                        i11 = iIsoTransferCharacteristicsToColorTransfer;
                                        if (i10 == -1 && i11 == -1) {
                                            int i22 = parsableByteArray.readInt();
                                            if (i22 == 1852009592 || i22 == 1852009571) {
                                                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                                                int unsignedShort4 = parsableByteArray.readUnsignedShort();
                                                parsableByteArray.skipBytes(2);
                                                boolean z3 = i18 == 19 && (parsableByteArray.readUnsignedByte() & 128) != 0;
                                                iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(unsignedShort3);
                                                i16 = z3 ? 1 : 2;
                                                iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(unsignedShort4);
                                            } else {
                                                Log.w("AtomParsers", "Unsupported color type: " + Atom.getAtomTypeString(i22));
                                            }
                                        }
                                        i14 = i7 + i18;
                                        i12 = i2;
                                        i13 = i3;
                                        stsdData2 = stsdData;
                                        iIntValue = i8;
                                        str3 = str;
                                        drmInitDataCopyWithSchemeType = drmInitData2;
                                    }
                                } else {
                                    i10 = i16;
                                }
                                i11 = iIsoTransferCharacteristicsToColorTransfer;
                            }
                            listOf = list;
                            paspFromParent = f;
                            i14 = i7 + i18;
                            i12 = i2;
                            i13 = i3;
                            stsdData2 = stsdData;
                            iIntValue = i8;
                            str3 = str;
                            drmInitDataCopyWithSchemeType = drmInitData2;
                        }
                        str4 = str2;
                        drmInitData2 = drmInitDataCopyWithSchemeType;
                    }
                    i16 = i10;
                    iIsoTransferCharacteristicsToColorTransfer = i11;
                    iIsoColorPrimariesToColorSpace = i9;
                    listOf = list;
                    paspFromParent = f;
                    i14 = i7 + i18;
                    i12 = i2;
                    i13 = i3;
                    stsdData2 = stsdData;
                    iIntValue = i8;
                    str3 = str;
                    drmInitDataCopyWithSchemeType = drmInitData2;
                }
                i14 = i7 + i18;
                i12 = i2;
                i13 = i3;
                stsdData2 = stsdData;
                iIntValue = i8;
                str3 = str;
                drmInitDataCopyWithSchemeType = drmInitData2;
            }
            i8 = iIntValue;
            str = str3;
            i14 = i7 + i18;
            i12 = i2;
            i13 = i3;
            stsdData2 = stsdData;
            iIntValue = i8;
            str3 = str;
            drmInitDataCopyWithSchemeType = drmInitData2;
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        float f2 = paspFromParent;
        List<byte[]> list3 = listOf;
        int i23 = iIsoColorPrimariesToColorSpace;
        int i24 = i16;
        int i25 = iIsoTransferCharacteristicsToColorTransfer;
        if (str4 == null) {
            return;
        }
        Format.Builder builder = new Format.Builder();
        builder.id = Integer.toString(i4);
        builder.sampleMimeType = str4;
        builder.codecs = str5;
        builder.width = unsignedShort;
        builder.height = unsignedShort2;
        builder.pixelWidthHeightRatio = f2;
        builder.rotationDegrees = i5;
        builder.projectionData = projFromParent;
        builder.stereoMode = i15;
        builder.initializationData = list3;
        builder.drmInitData = drmInitData3;
        if (i23 != -1 || i24 != -1 || i25 != -1 || byteBufferAllocateHdrStaticInfo != null) {
            builder.colorInfo = new ColorInfo(i23, i24, i25, byteBufferAllocateHdrStaticInfo != null ? byteBufferAllocateHdrStaticInfo.array() : null);
        }
        EsdsData esdsData2 = esdsData;
        if (esdsData2 != null) {
            builder.averageBitrate = Ints.saturatedCast(esdsData2.bitrate);
            builder.peakBitrate = Ints.saturatedCast(esdsData2.peakBitrate);
        }
        stsdData.format = new Format(builder);
    }
}
