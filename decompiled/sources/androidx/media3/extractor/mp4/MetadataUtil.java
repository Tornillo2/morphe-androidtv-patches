package androidx.media3.extractor.mp4;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.metadata.id3.ApicFrame;
import androidx.media3.extractor.metadata.id3.CommentFrame;
import androidx.media3.extractor.metadata.id3.Id3Frame;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.collect.ImmutableList;
import com.google.common.net.HttpHeaders;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetadataUtil {
    public static final int PICTURE_TYPE_FRONT_COVER = 3;
    public static final int SHORT_TYPE_ALBUM = 6384738;
    public static final int SHORT_TYPE_ARTIST = 4280916;
    public static final int SHORT_TYPE_COMMENT = 6516084;
    public static final int SHORT_TYPE_COMPOSER_1 = 6516589;
    public static final int SHORT_TYPE_COMPOSER_2 = 7828084;
    public static final int SHORT_TYPE_ENCODER = 7630703;
    public static final int SHORT_TYPE_GENRE = 6776174;
    public static final int SHORT_TYPE_LYRICS = 7108978;
    public static final int SHORT_TYPE_NAME_1 = 7233901;
    public static final int SHORT_TYPE_NAME_2 = 7631467;
    public static final int SHORT_TYPE_YEAR = 6578553;

    @VisibleForTesting
    public static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop", "Abstract", "Art Rock", "Baroque", "Bhangra", "Big beat", "Breakbeat", "Chillout", "Downtempo", "Dub", "EBM", "Eclectic", "Electro", "Electroclash", "Emo", "Experimental", "Garage", "Global", "IDM", "Illbient", "Industro-Goth", "Jam Band", "Krautrock", "Leftfield", "Lounge", "Math Rock", "New Romantic", "Nu-Breakz", "Post-Punk", "Post-Rock", "Psytrance", "Shoegaze", "Space Rock", "Trop Rock", "World Music", "Neoclassical", "Audiobook", "Audio theatre", "Neue Deutsche Welle", "Podcast", "Indie-Rock", "G-Funk", "Dubstep", "Garage Rock", "Psybient"};
    public static final String TAG = "MetadataUtil";
    public static final int TYPE_ALBUM_ARTIST = 1631670868;
    public static final int TYPE_COMPILATION = 1668311404;
    public static final int TYPE_COVER_ART = 1668249202;
    public static final int TYPE_DISK_NUMBER = 1684632427;
    public static final int TYPE_GAPLESS_ALBUM = 1885823344;
    public static final int TYPE_GENRE = 1735291493;
    public static final int TYPE_GROUPING = 6779504;
    public static final int TYPE_INTERNAL = 757935405;
    public static final int TYPE_RATING = 1920233063;
    public static final int TYPE_SORT_ALBUM = 1936679276;
    public static final int TYPE_SORT_ALBUM_ARTIST = 1936679265;
    public static final int TYPE_SORT_ARTIST = 1936679282;
    public static final int TYPE_SORT_COMPOSER = 1936679791;
    public static final int TYPE_SORT_TRACK_NAME = 1936682605;
    public static final int TYPE_TEMPO = 1953329263;
    public static final int TYPE_TOP_BYTE_COPYRIGHT = 169;
    public static final int TYPE_TOP_BYTE_REPLACEMENT = 253;
    public static final int TYPE_TRACK_NUMBER = 1953655662;
    public static final int TYPE_TV_SHOW = 1953919848;
    public static final int TYPE_TV_SORT_SHOW = 1936683886;

    @Nullable
    public static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            String nullTerminatedString = parsableByteArray.readNullTerminatedString(i2 - 16);
            return new CommentFrame("und", nullTerminatedString, nullTerminatedString);
        }
        Log.w("MetadataUtil", "Failed to parse comment attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    @Nullable
    public static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.readInt();
        if (parsableByteArray.readInt() != 1684108385) {
            Log.w("MetadataUtil", "Failed to parse cover art attribute");
            return null;
        }
        int i2 = parsableByteArray.readInt() & ViewCompat.MEASURED_SIZE_MASK;
        String str = i2 == 13 ? "image/jpeg" : i2 == 14 ? MimeTypes.IMAGE_PNG : null;
        if (str == null) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unrecognized cover art flags: ", i2, "MetadataUtil");
            return null;
        }
        parsableByteArray.skipBytes(4);
        int i3 = i - 16;
        byte[] bArr = new byte[i3];
        parsableByteArray.readBytes(bArr, 0, i3);
        return new ApicFrame(str, null, 3, bArr);
    }

    @Nullable
    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.readInt() + parsableByteArray.position;
        int i2 = parsableByteArray.readInt();
        int i3 = (i2 >> 24) & 255;
        try {
            if (i3 == 169 || i3 == 253) {
                int i4 = 16777215 & i2;
                if (i4 == 6516084) {
                    return parseCommentAttribute(i2, parsableByteArray);
                }
                if (i4 == 7233901 || i4 == 7631467) {
                    return parseTextAttribute(i2, "TIT2", parsableByteArray);
                }
                if (i4 == 6516589 || i4 == 7828084) {
                    return parseTextAttribute(i2, "TCOM", parsableByteArray);
                }
                if (i4 == 6578553) {
                    return parseTextAttribute(i2, "TDRC", parsableByteArray);
                }
                if (i4 == 4280916) {
                    return parseTextAttribute(i2, "TPE1", parsableByteArray);
                }
                if (i4 == 7630703) {
                    return parseTextAttribute(i2, "TSSE", parsableByteArray);
                }
                if (i4 == 6384738) {
                    return parseTextAttribute(i2, "TALB", parsableByteArray);
                }
                if (i4 == 7108978) {
                    return parseTextAttribute(i2, "USLT", parsableByteArray);
                }
                if (i4 == 6776174) {
                    return parseTextAttribute(i2, "TCON", parsableByteArray);
                }
                if (i4 == 6779504) {
                    return parseTextAttribute(i2, "TIT1", parsableByteArray);
                }
            } else {
                if (i2 == 1735291493) {
                    return parseStandardGenreAttribute(parsableByteArray);
                }
                if (i2 == 1684632427) {
                    return parseIndexAndCountAttribute(i2, "TPOS", parsableByteArray);
                }
                if (i2 == 1953655662) {
                    return parseIndexAndCountAttribute(i2, "TRCK", parsableByteArray);
                }
                if (i2 == 1953329263) {
                    return parseUint8Attribute(i2, "TBPM", parsableByteArray, true, false);
                }
                if (i2 == 1668311404) {
                    return parseUint8Attribute(i2, "TCMP", parsableByteArray, true, true);
                }
                if (i2 == 1668249202) {
                    return parseCoverArt(parsableByteArray);
                }
                if (i2 == 1631670868) {
                    return parseTextAttribute(i2, "TPE2", parsableByteArray);
                }
                if (i2 == 1936682605) {
                    return parseTextAttribute(i2, "TSOT", parsableByteArray);
                }
                if (i2 == 1936679276) {
                    return parseTextAttribute(i2, "TSO2", parsableByteArray);
                }
                if (i2 == 1936679282) {
                    return parseTextAttribute(i2, "TSOA", parsableByteArray);
                }
                if (i2 == 1936679265) {
                    return parseTextAttribute(i2, "TSOP", parsableByteArray);
                }
                if (i2 == 1936679791) {
                    return parseTextAttribute(i2, "TSOC", parsableByteArray);
                }
                if (i2 == 1920233063) {
                    return parseUint8Attribute(i2, "ITUNESADVISORY", parsableByteArray, false, false);
                }
                if (i2 == 1885823344) {
                    return parseUint8Attribute(i2, "ITUNESGAPLESS", parsableByteArray, false, true);
                }
                if (i2 == 1936683886) {
                    return parseTextAttribute(i2, "TVSHOWSORT", parsableByteArray);
                }
                if (i2 == 1953919848) {
                    return parseTextAttribute(i2, "TVSHOW", parsableByteArray);
                }
                if (i2 == 757935405) {
                    return parseInternalAttribute(parsableByteArray, i);
                }
            }
            Log.d("MetadataUtil", "Skipped unknown metadata entry: " + Atom.getAtomTypeString(i2));
            parsableByteArray.setPosition(i);
            return null;
        } finally {
            parsableByteArray.setPosition(i);
        }
    }

    @Nullable
    public static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385 && i2 >= 22) {
            parsableByteArray.skipBytes(10);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (unsignedShort > 0) {
                String strM = MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("", unsignedShort);
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    strM = strM + SchemaId.METRIC_SCHEMA_ID_DELIMITER + unsignedShort2;
                }
                return new TextInformationFrame(str, (String) null, ImmutableList.of(strM));
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    @Nullable
    public static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        String nullTerminatedString = null;
        String nullTerminatedString2 = null;
        int i2 = -1;
        int i3 = -1;
        while (true) {
            int i4 = parsableByteArray.position;
            if (i4 >= i) {
                break;
            }
            int i5 = parsableByteArray.readInt();
            int i6 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (i6 == 1835360622) {
                nullTerminatedString = parsableByteArray.readNullTerminatedString(i5 - 12);
            } else if (i6 == 1851878757) {
                nullTerminatedString2 = parsableByteArray.readNullTerminatedString(i5 - 12);
            } else {
                if (i6 == 1684108385) {
                    i2 = i4;
                    i3 = i5;
                }
                parsableByteArray.skipBytes(i5 - 12);
            }
        }
        if (nullTerminatedString == null || nullTerminatedString2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(nullTerminatedString, nullTerminatedString2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    @Nullable
    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i, String str) {
        while (true) {
            int i2 = parsableByteArray.position;
            if (i2 >= i) {
                return null;
            }
            int i3 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1684108385) {
                int i4 = parsableByteArray.readInt();
                int i5 = parsableByteArray.readInt();
                int i6 = i3 - 16;
                byte[] bArr = new byte[i6];
                parsableByteArray.readBytes(bArr, 0, i6);
                return new MdtaMetadataEntry(str, bArr, i5, i4);
            }
            parsableByteArray.setPosition(i2 + i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.media3.extractor.metadata.id3.TextInformationFrame parseStandardGenreAttribute(androidx.media3.common.util.ParsableByteArray r3) {
        /*
            int r3 = parseUint8AttributeValue(r3)
            r0 = 0
            if (r3 <= 0) goto L11
            java.lang.String[] r1 = androidx.media3.extractor.mp4.MetadataUtil.STANDARD_GENRES
            int r2 = r1.length
            if (r3 > r2) goto L11
            int r3 = r3 + (-1)
            r3 = r1[r3]
            goto L12
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L20
            androidx.media3.extractor.metadata.id3.TextInformationFrame r1 = new androidx.media3.extractor.metadata.id3.TextInformationFrame
            java.lang.String r2 = "TCON"
            com.google.common.collect.ImmutableList r3 = com.google.common.collect.ImmutableList.of(r3)
            r1.<init>(r2, r0, r3)
            return r1
        L20:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            androidx.media3.common.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.MetadataUtil.parseStandardGenreAttribute(androidx.media3.common.util.ParsableByteArray):androidx.media3.extractor.metadata.id3.TextInformationFrame");
    }

    @Nullable
    public static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, (String) null, ImmutableList.of(parsableByteArray.readNullTerminatedString(i2 - 16)));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    @Nullable
    public static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            uint8AttributeValue = Math.min(1, uint8AttributeValue);
        }
        if (uint8AttributeValue >= 0) {
            return z ? new TextInformationFrame(str, (String) null, ImmutableList.of(Integer.toString(uint8AttributeValue))) : new CommentFrame("und", str, Integer.toString(uint8AttributeValue));
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    public static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }

    public static void setFormatGaplessInfo(int i, GaplessInfoHolder gaplessInfoHolder, Format.Builder builder) {
        if (i == 1 && gaplessInfoHolder.hasGaplessInfo()) {
            builder.encoderDelay = gaplessInfoHolder.encoderDelay;
            builder.encoderPadding = gaplessInfoHolder.encoderPadding;
        }
    }

    public static void setFormatMetadata(int i, @Nullable Metadata metadata, Format.Builder builder, Metadata... metadataArr) {
        Metadata metadata2 = new Metadata(new Metadata.Entry[0]);
        if (metadata != null) {
            int i2 = 0;
            while (true) {
                Metadata.Entry[] entryArr = metadata.entries;
                if (i2 >= entryArr.length) {
                    break;
                }
                Metadata.Entry entry = entryArr[i2];
                if (entry instanceof MdtaMetadataEntry) {
                    MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                    if (!mdtaMetadataEntry.key.equals("com.android.capture.fps")) {
                        metadata2 = metadata2.copyWithAppendedEntries(mdtaMetadataEntry);
                    } else if (i == 2) {
                        metadata2 = metadata2.copyWithAppendedEntries(mdtaMetadataEntry);
                    }
                }
                i2++;
            }
        }
        for (Metadata metadata3 : metadataArr) {
            metadata2 = metadata2.copyWithAppendedEntriesFrom(metadata3);
        }
        if (metadata2.entries.length > 0) {
            builder.metadata = metadata2;
        }
    }
}
