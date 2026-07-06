package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.InlineMe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TextInformationFrame extends Id3Frame {
    public static final Parcelable.Creator<TextInformationFrame> CREATOR = new AnonymousClass1();

    @Nullable
    public final String description;

    @Deprecated
    public final String value;
    public final ImmutableList<String> values;

    /* JADX INFO: renamed from: com.google.android.exoplayer2.metadata.id3.TextInformationFrame$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Parcelable.Creator<TextInformationFrame> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInformationFrame createFromParcel(Parcel parcel) {
            return new TextInformationFrame(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInformationFrame[] newArray(int i) {
            return new TextInformationFrame[i];
        }
    }

    public /* synthetic */ TextInformationFrame(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public static List<Integer> parseId3v2point4TimestampFrameForDate(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
                return arrayList;
            }
            if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                return arrayList;
            }
            if (str.length() >= 4) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
            }
            return arrayList;
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TextInformationFrame.class == obj.getClass()) {
            TextInformationFrame textInformationFrame = (TextInformationFrame) obj;
            if (Util.areEqual(this.id, textInformationFrame.id) && Util.areEqual(this.description, textInformationFrame.description) && this.values.equals(textInformationFrame.values)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iM = DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.id, 527, 31);
        String str = this.description;
        return this.values.hashCode() + ((iM + (str != null ? str.hashCode() : 0)) * 31);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.exoplayer2.metadata.id3.Id3Frame, com.google.android.exoplayer2.metadata.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.id;
        str.getClass();
        byte b = -1;
        switch (str.hashCode()) {
            case 82815:
                if (str.equals("TAL")) {
                    b = 0;
                }
                break;
            case 82878:
                if (str.equals("TCM")) {
                    b = 1;
                }
                break;
            case 82897:
                if (str.equals("TDA")) {
                    b = 2;
                }
                break;
            case 83253:
                if (str.equals("TP1")) {
                    b = 3;
                }
                break;
            case 83254:
                if (str.equals("TP2")) {
                    b = 4;
                }
                break;
            case 83255:
                if (str.equals("TP3")) {
                    b = 5;
                }
                break;
            case 83341:
                if (str.equals("TRK")) {
                    b = 6;
                }
                break;
            case 83378:
                if (str.equals("TT2")) {
                    b = 7;
                }
                break;
            case 83536:
                if (str.equals("TXT")) {
                    b = 8;
                }
                break;
            case 83552:
                if (str.equals("TYE")) {
                    b = 9;
                }
                break;
            case 2567331:
                if (str.equals("TALB")) {
                    b = 10;
                }
                break;
            case 2569357:
                if (str.equals("TCOM")) {
                    b = Ascii.VT;
                }
                break;
            case 2569891:
                if (str.equals("TDAT")) {
                    b = Ascii.FF;
                }
                break;
            case 2570401:
                if (str.equals("TDRC")) {
                    b = 13;
                }
                break;
            case 2570410:
                if (str.equals("TDRL")) {
                    b = Ascii.SO;
                }
                break;
            case 2571565:
                if (str.equals("TEXT")) {
                    b = Ascii.SI;
                }
                break;
            case 2575251:
                if (str.equals("TIT2")) {
                    b = 16;
                }
                break;
            case 2581512:
                if (str.equals("TPE1")) {
                    b = 17;
                }
                break;
            case 2581513:
                if (str.equals("TPE2")) {
                    b = Ascii.DC2;
                }
                break;
            case 2581514:
                if (str.equals("TPE3")) {
                    b = 19;
                }
                break;
            case 2583398:
                if (str.equals("TRCK")) {
                    b = Ascii.DC4;
                }
                break;
            case 2590194:
                if (str.equals("TYER")) {
                    b = Ascii.NAK;
                }
                break;
        }
        try {
            switch (b) {
                case 0:
                case 10:
                    builder.albumTitle = this.values.get(0);
                    break;
                case 1:
                case 11:
                    builder.composer = this.values.get(0);
                    break;
                case 2:
                case 12:
                    String str2 = this.values.get(0);
                    int i = Integer.parseInt(str2.substring(2, 4));
                    int i2 = Integer.parseInt(str2.substring(0, 2));
                    builder.recordingMonth = Integer.valueOf(i);
                    builder.recordingDay = Integer.valueOf(i2);
                    break;
                case 3:
                case 17:
                    builder.artist = this.values.get(0);
                    break;
                case 4:
                case 18:
                    builder.albumArtist = this.values.get(0);
                    break;
                case 5:
                case 19:
                    builder.conductor = this.values.get(0);
                    break;
                case 6:
                case 20:
                    String[] strArrSplit = Util.split(this.values.get(0), SchemaId.METRIC_SCHEMA_ID_DELIMITER);
                    int i3 = Integer.parseInt(strArrSplit[0]);
                    Integer numValueOf = strArrSplit.length > 1 ? Integer.valueOf(Integer.parseInt(strArrSplit[1])) : null;
                    builder.trackNumber = Integer.valueOf(i3);
                    builder.totalTrackCount = numValueOf;
                    break;
                case 7:
                case 16:
                    builder.title = this.values.get(0);
                    break;
                case 8:
                case 15:
                    builder.writer = this.values.get(0);
                    break;
                case 9:
                case 21:
                    builder.recordingYear = Integer.valueOf(Integer.parseInt(this.values.get(0)));
                    break;
                case 13:
                    ArrayList arrayList = (ArrayList) parseId3v2point4TimestampFrameForDate(this.values.get(0));
                    int size = arrayList.size();
                    if (size != 1) {
                        if (size != 2) {
                            if (size == 3) {
                                builder.recordingDay = (Integer) arrayList.get(2);
                            }
                        }
                        builder.recordingMonth = (Integer) arrayList.get(1);
                    }
                    builder.recordingYear = (Integer) arrayList.get(0);
                    break;
                case 14:
                    ArrayList arrayList2 = (ArrayList) parseId3v2point4TimestampFrameForDate(this.values.get(0));
                    int size2 = arrayList2.size();
                    if (size2 != 1) {
                        if (size2 != 2) {
                            if (size2 == 3) {
                                builder.releaseDay = (Integer) arrayList2.get(2);
                            }
                        }
                        builder.releaseMonth = (Integer) arrayList2.get(1);
                    }
                    builder.releaseYear = (Integer) arrayList2.get(0);
                    break;
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException unused) {
        }
    }

    @Override // com.google.android.exoplayer2.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": description=" + this.description + ": values=" + this.values;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.description);
        parcel.writeStringArray((String[]) this.values.toArray(new String[0]));
    }

    public TextInformationFrame(String str, @Nullable String str2, List<String> list) {
        super(str);
        Assertions.checkArgument(!list.isEmpty());
        this.description = str2;
        ImmutableList<String> immutableListCopyOf = ImmutableList.copyOf((Collection) list);
        this.values = immutableListCopyOf;
        this.value = immutableListCopyOf.get(0);
    }

    @InlineMe(imports = {"com.google.common.collect.ImmutableList"}, replacement = "this(id, description, ImmutableList.of(value))")
    @Deprecated
    public TextInformationFrame(String str, @Nullable String str2, String str3) {
        this(str, str2, ImmutableList.of(str3));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextInformationFrame(Parcel parcel) {
        String string = parcel.readString();
        string.getClass();
        String string2 = parcel.readString();
        String[] strArrCreateStringArray = parcel.createStringArray();
        strArrCreateStringArray.getClass();
        this(string, string2, ImmutableList.copyOf(strArrCreateStringArray));
    }
}
