package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    public static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_ENABLE_HDMV_DTS_AUDIO_STREAMS = 64;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    public final List<Format> closedCaptionFormats;
    public final int flags;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public final SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo));
    }

    public final UserDataReader buildUserDataReader(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo));
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    @Nullable
    public TsPayloadReader createPayloadReader(int i, TsPayloadReader.EsInfo esInfo) {
        if (i != 2) {
            if (i == 3 || i == 4) {
                return new PesReader(new MpegAudioReader(esInfo.language));
            }
            if (i == 21) {
                return new PesReader(new Id3Reader());
            }
            if (i == 27) {
                if (isSet(4)) {
                    return null;
                }
                return new PesReader(new H264Reader(buildSeiReader(esInfo), isSet(1), isSet(8)));
            }
            if (i == 36) {
                return new PesReader(new H265Reader(buildSeiReader(esInfo)));
            }
            if (i == 89) {
                return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
            }
            if (i != 138) {
                if (i == 172) {
                    return new PesReader(new Ac4Reader(esInfo.language));
                }
                if (i == 257) {
                    return new SectionReader(new PassthroughSectionPayloadReader("application/vnd.dvb.ait"));
                }
                if (i == 134) {
                    if (isSet(16)) {
                        return null;
                    }
                    return new SectionReader(new PassthroughSectionPayloadReader("application/x-scte35"));
                }
                if (i != 135) {
                    switch (i) {
                        case 15:
                            if (!isSet(2)) {
                                break;
                            }
                            break;
                        case 16:
                            break;
                        case 17:
                            if (!isSet(2)) {
                                break;
                            }
                            break;
                        default:
                            switch (i) {
                                case 130:
                                    if (!isSet(64)) {
                                    }
                                    break;
                            }
                            break;
                    }
                    return null;
                }
                return new PesReader(new Ac3Reader(esInfo.language));
            }
            return new PesReader(new DtsReader(esInfo.language));
        }
        return new PesReader(new H262Reader(buildUserDataReader(esInfo)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v4 */
    public final List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo esInfo) {
        String str;
        int i;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        ArrayList arrayList = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int unsignedByte2 = parsableByteArray.position + parsableByteArray.readUnsignedByte();
            if (unsignedByte == 134) {
                arrayList = new ArrayList();
                int unsignedByte3 = parsableByteArray.readUnsignedByte() & 31;
                for (int i2 = 0; i2 < unsignedByte3; i2++) {
                    String string = parsableByteArray.readString(3, Charsets.UTF_8);
                    int unsignedByte4 = parsableByteArray.readUnsignedByte();
                    boolean z = (unsignedByte4 & 128) != 0;
                    if (z) {
                        i = unsignedByte4 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i = 1;
                    }
                    byte unsignedByte5 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    List<byte[]> listBuildCea708InitializationData = z ? CodecSpecificDataUtil.buildCea708InitializationData((unsignedByte5 & 64) != 0) : null;
                    Format.Builder builder = new Format.Builder();
                    builder.sampleMimeType = str;
                    builder.language = string;
                    builder.accessibilityChannel = i;
                    builder.initializationData = listBuildCea708InitializationData;
                    arrayList.add(new Format(builder));
                }
            }
            parsableByteArray.setPosition(unsignedByte2);
            arrayList = arrayList;
        }
        return arrayList;
    }

    public final boolean isSet(int i) {
        return (i & this.flags) != 0;
    }

    public DefaultTsPayloadReaderFactory(int i) {
        this(i, ImmutableList.of());
    }

    public DefaultTsPayloadReaderFactory(int i, List<Format> list) {
        this.flags = i;
        this.closedCaptionFormats = list;
    }
}
