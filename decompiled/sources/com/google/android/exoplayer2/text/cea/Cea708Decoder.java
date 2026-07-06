package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.Engine;
import com.google.android.exoplayer2.AudioFocusManager$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import j$.util.DesugarCollections;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.text.Typography;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Cea708Decoder extends CeaDecoder {
    public static final int CC_VALID_FLAG = 4;
    public static final int CHARACTER_BIG_CARONS = 42;
    public static final int CHARACTER_BIG_OE = 44;
    public static final int CHARACTER_BOLD_BULLET = 53;
    public static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    public static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    public static final int CHARACTER_DIAERESIS_Y = 63;
    public static final int CHARACTER_ELLIPSIS = 37;
    public static final int CHARACTER_FIVE_EIGHTHS = 120;
    public static final int CHARACTER_HORIZONTAL_BORDER = 125;
    public static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    public static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    public static final int CHARACTER_MN = 127;
    public static final int CHARACTER_NBTSP = 33;
    public static final int CHARACTER_ONE_EIGHTH = 118;
    public static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    public static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    public static final int CHARACTER_SEVEN_EIGHTHS = 121;
    public static final int CHARACTER_SM = 61;
    public static final int CHARACTER_SMALL_CARONS = 58;
    public static final int CHARACTER_SMALL_OE = 60;
    public static final int CHARACTER_SOLID_BLOCK = 48;
    public static final int CHARACTER_THREE_EIGHTHS = 119;
    public static final int CHARACTER_TM = 57;
    public static final int CHARACTER_TSP = 32;
    public static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    public static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    public static final int CHARACTER_VERTICAL_BORDER = 122;
    public static final int COMMAND_BS = 8;
    public static final int COMMAND_CLW = 136;
    public static final int COMMAND_CR = 13;
    public static final int COMMAND_CW0 = 128;
    public static final int COMMAND_CW1 = 129;
    public static final int COMMAND_CW2 = 130;
    public static final int COMMAND_CW3 = 131;
    public static final int COMMAND_CW4 = 132;
    public static final int COMMAND_CW5 = 133;
    public static final int COMMAND_CW6 = 134;
    public static final int COMMAND_CW7 = 135;
    public static final int COMMAND_DF0 = 152;
    public static final int COMMAND_DF1 = 153;
    public static final int COMMAND_DF2 = 154;
    public static final int COMMAND_DF3 = 155;
    public static final int COMMAND_DF4 = 156;
    public static final int COMMAND_DF5 = 157;
    public static final int COMMAND_DF6 = 158;
    public static final int COMMAND_DF7 = 159;
    public static final int COMMAND_DLC = 142;
    public static final int COMMAND_DLW = 140;
    public static final int COMMAND_DLY = 141;
    public static final int COMMAND_DSW = 137;
    public static final int COMMAND_ETX = 3;
    public static final int COMMAND_EXT1 = 16;
    public static final int COMMAND_EXT1_END = 23;
    public static final int COMMAND_EXT1_START = 17;
    public static final int COMMAND_FF = 12;
    public static final int COMMAND_HCR = 14;
    public static final int COMMAND_HDW = 138;
    public static final int COMMAND_NUL = 0;
    public static final int COMMAND_P16_END = 31;
    public static final int COMMAND_P16_START = 24;
    public static final int COMMAND_RST = 143;
    public static final int COMMAND_SPA = 144;
    public static final int COMMAND_SPC = 145;
    public static final int COMMAND_SPL = 146;
    public static final int COMMAND_SWA = 151;
    public static final int COMMAND_TGW = 139;
    public static final int DTVCC_PACKET_DATA = 2;
    public static final int DTVCC_PACKET_START = 3;
    public static final int GROUP_C0_END = 31;
    public static final int GROUP_C1_END = 159;
    public static final int GROUP_C2_END = 31;
    public static final int GROUP_C3_END = 159;
    public static final int GROUP_G0_END = 127;
    public static final int GROUP_G1_END = 255;
    public static final int GROUP_G2_END = 127;
    public static final int GROUP_G3_END = 255;
    public static final int NUM_WINDOWS = 8;
    public static final String TAG = "Cea708Decoder";
    public final CueInfoBuilder[] cueInfoBuilders;

    @Nullable
    public List<Cue> cues;
    public CueInfoBuilder currentCueInfoBuilder;

    @Nullable
    public DtvCcPacket currentDtvCcPacket;
    public int currentWindow;
    public final boolean isWideAspectRatio;

    @Nullable
    public List<Cue> lastCues;
    public final int selectedServiceNumber;
    public final ParsableByteArray ccData = new ParsableByteArray();
    public final ParsableBitArray captionChannelPacketData = new ParsableBitArray();
    public int previousSequenceNumber = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Cea708CueInfo {
        public static final Comparator<Cea708CueInfo> LEAST_IMPORTANT_FIRST = new Cea708Decoder$Cea708CueInfo$$ExternalSyntheticLambda0();
        public final Cue cue;
        public final int priority;

        public Cea708CueInfo(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5) {
            Cue.Builder builder = new Cue.Builder();
            builder.text = charSequence;
            builder.textAlignment = alignment;
            builder.line = f;
            builder.lineType = i;
            builder.lineAnchor = i2;
            builder.position = f2;
            builder.positionAnchor = i3;
            builder.size = f3;
            if (z) {
                builder.setWindowColor(i4);
            }
            this.cue = builder.build();
            this.priority = i5;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CueInfoBuilder {
        public static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        public static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        public static final int DEFAULT_PRIORITY = 4;
        public static final int DIRECTION_BOTTOM_TO_TOP = 3;
        public static final int DIRECTION_LEFT_TO_RIGHT = 0;
        public static final int DIRECTION_RIGHT_TO_LEFT = 1;
        public static final int DIRECTION_TOP_TO_BOTTOM = 2;
        public static final int HORIZONTAL_SIZE = 209;
        public static final int JUSTIFICATION_CENTER = 2;
        public static final int JUSTIFICATION_FULL = 3;
        public static final int JUSTIFICATION_LEFT = 0;
        public static final int JUSTIFICATION_RIGHT = 1;
        public static final int MAXIMUM_ROW_COUNT = 15;
        public static final int PEN_FONT_STYLE_DEFAULT = 0;
        public static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        public static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        public static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        public static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        public static final int PEN_OFFSET_NORMAL = 1;
        public static final int PEN_SIZE_STANDARD = 1;
        public static final int[] PEN_STYLE_BACKGROUND;
        public static final int[] PEN_STYLE_EDGE_TYPE;
        public static final int[] PEN_STYLE_FONT_STYLE;
        public static final int RELATIVE_CUE_SIZE = 99;
        public static final int VERTICAL_SIZE = 74;
        public static final int[] WINDOW_STYLE_FILL;
        public static final int[] WINDOW_STYLE_JUSTIFICATION;
        public static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        public static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        public static final boolean[] WINDOW_STYLE_WORD_WRAP;
        public int anchorId;
        public int backgroundColor;
        public int backgroundColorStartPosition;
        public boolean defined;
        public int foregroundColor;
        public int foregroundColorStartPosition;
        public int horizontalAnchor;
        public int italicsStartPosition;
        public int justification;
        public int penStyleId;
        public int priority;
        public boolean relativePositioning;
        public int row;
        public int rowCount;
        public boolean rowLock;
        public int underlineStartPosition;
        public int verticalAnchor;
        public boolean visible;
        public int windowFillColor;
        public int windowStyleId;
        public final List<SpannableString> rolledUpCaptions = new ArrayList();
        public final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueInfoBuilder() {
            reset();
        }

        public static int getArgbColorFromCeaColor(int i, int i2, int i3) {
            return getArgbColorFromCeaColor(i, i2, i3, 0);
        }

        public void append(char c) {
            if (c != '\n') {
                this.captionStringBuilder.append(c);
                return;
            }
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.clear();
            if (this.italicsStartPosition != -1) {
                this.italicsStartPosition = 0;
            }
            if (this.underlineStartPosition != -1) {
                this.underlineStartPosition = 0;
            }
            if (this.foregroundColorStartPosition != -1) {
                this.foregroundColorStartPosition = 0;
            }
            if (this.backgroundColorStartPosition != -1) {
                this.backgroundColorStartPosition = 0;
            }
            while (true) {
                if ((!this.rowLock || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                    return;
                } else {
                    this.rolledUpCaptions.remove(0);
                }
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        @Nullable
        public Cea708CueInfo build() {
            Layout.Alignment alignment;
            float f;
            float f2;
            int i;
            float f3;
            int i2;
            if (isEmpty()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i3 = 0; i3 < this.rolledUpCaptions.size(); i3++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i3));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) buildSpannableString());
            int i4 = this.justification;
            int i5 = 2;
            if (i4 == 0) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (i4 == 1) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i4 != 2) {
                if (i4 != 3) {
                    throw new IllegalArgumentException("Unexpected justification value: " + this.justification);
                }
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            if (this.relativePositioning) {
                f = this.horizontalAnchor / 99.0f;
                f2 = this.verticalAnchor / 99.0f;
            } else {
                f = this.horizontalAnchor / 209.0f;
                f2 = this.verticalAnchor / 74.0f;
            }
            float f4 = (f * 0.9f) + 0.05f;
            float f5 = (f2 * 0.9f) + 0.05f;
            int i6 = this.anchorId;
            if (i6 / 3 == 0) {
                i = i6;
                f3 = f4;
                i2 = 0;
            } else if (i6 / 3 == 1) {
                i = i6;
                f3 = f4;
                i2 = 1;
            } else {
                i = i6;
                f3 = f4;
                i2 = 2;
            }
            if (i % 3 == 0) {
                i5 = 0;
            } else if (i % 3 == 1) {
                i5 = 1;
            }
            int i7 = this.windowFillColor;
            return new Cea708CueInfo(spannableStringBuilder, alignment, f5, 0, i2, f3, i5, -3.4028235E38f, i7 != COLOR_SOLID_BLACK, i7, this.priority);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.defined = true;
            this.visible = z;
            this.rowLock = z2;
            this.priority = i;
            this.relativePositioning = z4;
            this.verticalAnchor = i2;
            this.horizontalAnchor = i3;
            this.anchorId = i6;
            int i9 = i4 + 1;
            if (this.rowCount != i9) {
                this.rowCount = i9;
                while (true) {
                    if ((!z2 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    } else {
                        this.rolledUpCaptions.remove(0);
                    }
                }
            }
            if (i7 != 0 && this.windowStyleId != i7) {
                this.windowStyleId = i7;
                int i10 = i7 - 1;
                int i11 = WINDOW_STYLE_FILL[i10];
                boolean z5 = WINDOW_STYLE_WORD_WRAP[i10];
                int i12 = WINDOW_STYLE_PRINT_DIRECTION[i10];
                int i13 = WINDOW_STYLE_SCROLL_DIRECTION[i10];
                int i14 = WINDOW_STYLE_JUSTIFICATION[i10];
                this.windowFillColor = i11;
                this.justification = i14;
            }
            if (i8 == 0 || this.penStyleId == i8) {
                return;
            }
            this.penStyleId = i8;
            int i15 = i8 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i15], PEN_STYLE_FONT_STYLE[i15]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i15], COLOR_SOLID_BLACK);
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            if (this.defined) {
                return this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
            }
            return true;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int i = COLOR_SOLID_BLACK;
            this.windowFillColor = i;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = i;
        }

        public void setPenAttributes(int i, int i2, int i3, boolean z, boolean z2, int i4, int i5) {
            if (this.italicsStartPosition != -1) {
                if (!z) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition == -1) {
                if (z2) {
                    this.underlineStartPosition = this.captionStringBuilder.length();
                }
            } else {
                if (z2) {
                    return;
                }
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void setPenColor(int i, int i2, int i3) {
            if (this.foregroundColorStartPosition != -1 && this.foregroundColor != i) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i;
            }
            if (this.backgroundColorStartPosition != -1 && this.backgroundColor != i2) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i2 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i2;
            }
        }

        public void setPenLocation(int i, int i2) {
            if (this.row != i) {
                append('\n');
            }
            this.row = i;
        }

        public void setVisibility(boolean z) {
            this.visible = z;
        }

        public void setWindowAttributes(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
            this.windowFillColor = i;
            this.justification = i6;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getArgbColorFromCeaColor(int r4, int r5, int r6, int r7) {
            /*
                r0 = 0
                r1 = 4
                com.google.android.exoplayer2.util.Assertions.checkIndex(r4, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r5, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r6, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r7, r0, r1)
                r1 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L1b
                if (r7 == r1) goto L1b
                r3 = 2
                if (r7 == r3) goto L20
                r3 = 3
                if (r7 == r3) goto L1e
            L1b:
                r7 = 255(0xff, float:3.57E-43)
                goto L22
            L1e:
                r7 = 0
                goto L22
            L20:
                r7 = 127(0x7f, float:1.78E-43)
            L22:
                if (r4 <= r1) goto L27
                r4 = 255(0xff, float:3.57E-43)
                goto L28
            L27:
                r4 = 0
            L28:
                if (r5 <= r1) goto L2d
                r5 = 255(0xff, float:3.57E-43)
                goto L2e
            L2d:
                r5 = 0
            L2e:
                if (r6 <= r1) goto L32
                r0 = 255(0xff, float:3.57E-43)
            L32:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.CueInfoBuilder.getArgbColorFromCeaColor(int, int, int, int):int");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DtvCcPacket {
        public int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int i, int i2) {
            this.sequenceNumber = i;
            this.packetSize = i2;
            this.packetData = new byte[(i2 * 2) - 1];
        }
    }

    public Cea708Decoder(int i, @Nullable List<byte[]> list) {
        this.selectedServiceNumber = i == -1 ? 1 : i;
        this.isWideAspectRatio = list != null && CodecSpecificDataUtil.parseCea708InitializationData(list);
        this.cueInfoBuilders = new CueInfoBuilder[8];
        for (int i2 = 0; i2 < 8; i2++) {
            this.cueInfoBuilders[i2] = new CueInfoBuilder();
        }
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
    }

    private List<Cue> getDisplayCues() {
        Cea708CueInfo cea708CueInfoBuild;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            if (!this.cueInfoBuilders[i].isEmpty()) {
                CueInfoBuilder cueInfoBuilder = this.cueInfoBuilders[i];
                if (cueInfoBuilder.visible && (cea708CueInfoBuild = cueInfoBuilder.build()) != null) {
                    arrayList.add(cea708CueInfoBuild);
                }
            }
        }
        Collections.sort(arrayList, Cea708CueInfo.LEAST_IMPORTANT_FIRST);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(((Cea708CueInfo) arrayList.get(i2)).cue);
        }
        return DesugarCollections.unmodifiableList(arrayList2);
    }

    private void resetCueBuilders() {
        for (int i = 0; i < 8; i++) {
            this.cueInfoBuilders[i].reset();
        }
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        list.getClass();
        return new CeaSubtitle(list);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        ByteBuffer byteBuffer = subtitleInputBuffer.data;
        byteBuffer.getClass();
        this.ccData.reset(byteBuffer.array(), byteBuffer.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int unsignedByte = this.ccData.readUnsignedByte();
            int i = unsignedByte & 3;
            boolean z = (unsignedByte & 4) == 4;
            byte unsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte unsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if (i == 2 || i == 3) {
                if (z) {
                    if (i == 3) {
                        finalizeCurrentPacket();
                        int i2 = (unsignedByte2 & 192) >> 6;
                        int i3 = this.previousSequenceNumber;
                        if (i3 != -1 && i2 != (i3 + 1) % 4) {
                            resetCueBuilders();
                            Log.w("Cea708Decoder", "Sequence number discontinuity. previous=" + this.previousSequenceNumber + " current=" + i2);
                        }
                        this.previousSequenceNumber = i2;
                        int i4 = unsignedByte2 & 63;
                        if (i4 == 0) {
                            i4 = 64;
                        }
                        DtvCcPacket dtvCcPacket = new DtvCcPacket(i2, i4);
                        this.currentDtvCcPacket = dtvCcPacket;
                        byte[] bArr = dtvCcPacket.packetData;
                        dtvCcPacket.currentIndex = 1;
                        bArr[0] = unsignedByte3;
                    } else {
                        Assertions.checkArgument(i == 2);
                        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                        if (dtvCcPacket2 == null) {
                            Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = dtvCcPacket2.packetData;
                            int i5 = dtvCcPacket2.currentIndex;
                            int i6 = i5 + 1;
                            dtvCcPacket2.currentIndex = i6;
                            bArr2[i5] = unsignedByte2;
                            dtvCcPacket2.currentIndex = i5 + 2;
                            bArr2[i6] = unsignedByte3;
                        }
                    }
                    DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                    if (dtvCcPacket3.currentIndex == (dtvCcPacket3.packetSize * 2) - 1) {
                        finalizeCurrentPacket();
                    }
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    @Nullable
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    @Nullable
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    public final void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket == null) {
            return;
        }
        processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public String getName() {
        return "Cea708Decoder";
    }

    public final void handleC0Command(int i) {
        if (i != 0) {
            if (i == 3) {
                this.cues = getDisplayCues();
                return;
            }
            if (i == 8) {
                this.currentCueInfoBuilder.backspace();
                return;
            }
            switch (i) {
                case 12:
                    resetCueBuilders();
                    break;
                case 13:
                    this.currentCueInfoBuilder.append('\n');
                    break;
                case 14:
                    break;
                default:
                    if (i >= 17 && i <= 23) {
                        AudioFocusManager$$ExternalSyntheticOutline0.m("Currently unsupported COMMAND_EXT1 Command: ", i, "Cea708Decoder");
                        this.captionChannelPacketData.skipBits(8);
                    } else if (i >= 24 && i <= 31) {
                        AudioFocusManager$$ExternalSyntheticOutline0.m("Currently unsupported COMMAND_P16 Command: ", i, "Cea708Decoder");
                        this.captionChannelPacketData.skipBits(16);
                    } else {
                        AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid C0 command: ", i, "Cea708Decoder");
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void handleC1Command(int i) {
        int i2 = 1;
        switch (i) {
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                int i3 = i - 128;
                if (this.currentWindow != i3) {
                    this.currentWindow = i3;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[i3];
                }
                break;
            case 136:
                while (i2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i2].clear();
                    }
                    i2++;
                }
                break;
            case 137:
                for (int i4 = 1; i4 <= 8; i4++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i4].visible = true;
                    }
                }
                break;
            case 138:
                while (i2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i2].visible = false;
                    }
                    i2++;
                }
                break;
            case 139:
                for (int i5 = 1; i5 <= 8; i5++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i5].visible = !r0.visible;
                    }
                }
                break;
            case 140:
                while (i2 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i2].reset();
                    }
                    i2++;
                }
                break;
            case 141:
                this.captionChannelPacketData.skipBits(8);
                break;
            case 142:
                break;
            case 143:
                resetCueBuilders();
                break;
            case 144:
                if (this.currentCueInfoBuilder.defined) {
                    handleSetPenAttributes();
                } else {
                    this.captionChannelPacketData.skipBits(16);
                }
                break;
            case 145:
                if (this.currentCueInfoBuilder.defined) {
                    handleSetPenColor();
                } else {
                    this.captionChannelPacketData.skipBits(24);
                }
                break;
            case 146:
                if (this.currentCueInfoBuilder.defined) {
                    handleSetPenLocation();
                } else {
                    this.captionChannelPacketData.skipBits(16);
                }
                break;
            case 147:
            case TarConstants.CHKSUM_OFFSET /* 148 */:
            case 149:
            case Engine.JOB_POOL_SIZE /* 150 */:
            default:
                AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid C1 command: ", i, "Cea708Decoder");
                break;
            case 151:
                if (this.currentCueInfoBuilder.defined) {
                    handleSetWindowAttributes();
                } else {
                    this.captionChannelPacketData.skipBits(32);
                }
                break;
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                int i6 = i - 152;
                handleDefineWindow(i6);
                if (this.currentWindow != i6) {
                    this.currentWindow = i6;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[i6];
                }
                break;
        }
    }

    public final void handleC2Command(int i) {
        if (i <= 7) {
            return;
        }
        if (i <= 15) {
            this.captionChannelPacketData.skipBits(8);
        } else if (i <= 23) {
            this.captionChannelPacketData.skipBits(16);
        } else if (i <= 31) {
            this.captionChannelPacketData.skipBits(24);
        }
    }

    public final void handleC3Command(int i) {
        if (i <= 135) {
            this.captionChannelPacketData.skipBits(32);
            return;
        }
        if (i <= 143) {
            this.captionChannelPacketData.skipBits(40);
        } else if (i <= 159) {
            this.captionChannelPacketData.skipBits(2);
            this.captionChannelPacketData.skipBits(this.captionChannelPacketData.readBits(6) * 8);
        }
    }

    public final void handleDefineWindow(int i) {
        CueInfoBuilder cueInfoBuilder = this.cueInfoBuilders[i];
        this.captionChannelPacketData.skipBits(2);
        boolean bit = this.captionChannelPacketData.readBit();
        boolean bit2 = this.captionChannelPacketData.readBit();
        boolean bit3 = this.captionChannelPacketData.readBit();
        int bits = this.captionChannelPacketData.readBits(3);
        boolean bit4 = this.captionChannelPacketData.readBit();
        int bits2 = this.captionChannelPacketData.readBits(7);
        int bits3 = this.captionChannelPacketData.readBits(8);
        int bits4 = this.captionChannelPacketData.readBits(4);
        int bits5 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        int bits6 = this.captionChannelPacketData.readBits(6);
        this.captionChannelPacketData.skipBits(2);
        cueInfoBuilder.defineWindow(bit, bit2, bit3, bits, bit4, bits2, bits3, bits5, bits6, bits4, this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    public final void handleG0Character(int i) {
        if (i == 127) {
            this.currentCueInfoBuilder.append((char) 9835);
        } else {
            this.currentCueInfoBuilder.append((char) (i & 255));
        }
    }

    public final void handleG1Character(int i) {
        this.currentCueInfoBuilder.append((char) (i & 255));
    }

    public final void handleG2Character(int i) {
        if (i == 32) {
            this.currentCueInfoBuilder.append(' ');
            return;
        }
        if (i == 33) {
            this.currentCueInfoBuilder.append(Typography.nbsp);
            return;
        }
        if (i == 37) {
            this.currentCueInfoBuilder.append(Typography.ellipsis);
            return;
        }
        if (i == 42) {
            this.currentCueInfoBuilder.append((char) 352);
            return;
        }
        if (i == 44) {
            this.currentCueInfoBuilder.append((char) 338);
            return;
        }
        if (i == 63) {
            this.currentCueInfoBuilder.append((char) 376);
            return;
        }
        if (i == 57) {
            this.currentCueInfoBuilder.append(Typography.tm);
            return;
        }
        if (i == 58) {
            this.currentCueInfoBuilder.append((char) 353);
            return;
        }
        if (i == 60) {
            this.currentCueInfoBuilder.append((char) 339);
            return;
        }
        if (i == 61) {
            this.currentCueInfoBuilder.append((char) 8480);
            return;
        }
        switch (i) {
            case 48:
                this.currentCueInfoBuilder.append((char) 9608);
                break;
            case 49:
                this.currentCueInfoBuilder.append(Typography.leftSingleQuote);
                break;
            case 50:
                this.currentCueInfoBuilder.append(Typography.rightSingleQuote);
                break;
            case 51:
                this.currentCueInfoBuilder.append(Typography.leftDoubleQuote);
                break;
            case 52:
                this.currentCueInfoBuilder.append(Typography.rightDoubleQuote);
                break;
            case 53:
                this.currentCueInfoBuilder.append(Typography.bullet);
                break;
            default:
                switch (i) {
                    case 118:
                        this.currentCueInfoBuilder.append((char) 8539);
                        break;
                    case 119:
                        this.currentCueInfoBuilder.append((char) 8540);
                        break;
                    case 120:
                        this.currentCueInfoBuilder.append((char) 8541);
                        break;
                    case 121:
                        this.currentCueInfoBuilder.append((char) 8542);
                        break;
                    case 122:
                        this.currentCueInfoBuilder.append((char) 9474);
                        break;
                    case 123:
                        this.currentCueInfoBuilder.append((char) 9488);
                        break;
                    case 124:
                        this.currentCueInfoBuilder.append((char) 9492);
                        break;
                    case 125:
                        this.currentCueInfoBuilder.append((char) 9472);
                        break;
                    case 126:
                        this.currentCueInfoBuilder.append((char) 9496);
                        break;
                    case 127:
                        this.currentCueInfoBuilder.append((char) 9484);
                        break;
                    default:
                        AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid G2 character: ", i, "Cea708Decoder");
                        break;
                }
                break;
        }
    }

    public final void handleG3Character(int i) {
        if (i == 160) {
            this.currentCueInfoBuilder.append((char) 13252);
        } else {
            AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid G3 character: ", i, "Cea708Decoder");
            this.currentCueInfoBuilder.append('_');
        }
    }

    public final void handleSetPenAttributes() {
        this.currentCueInfoBuilder.setPenAttributes(this.captionChannelPacketData.readBits(4), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    public final void handleSetPenColor() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), 0));
    }

    public final void handleSetPenLocation() {
        this.captionChannelPacketData.skipBits(4);
        int bits = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenLocation(bits, this.captionChannelPacketData.readBits(6));
    }

    public final void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.captionChannelPacketData.readBits(2);
        CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), 0);
        this.captionChannelPacketData.readBit();
        this.captionChannelPacketData.readBit();
        this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.readBits(2);
        int bits = this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.skipBits(8);
        CueInfoBuilder cueInfoBuilder = this.currentCueInfoBuilder;
        cueInfoBuilder.windowFillColor = argbColorFromCeaColor;
        cueInfoBuilder.justification = bits;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @RequiresNonNull({"currentDtvCcPacket"})
    public final void processCurrentPacket() {
        DtvCcPacket dtvCcPacket = this.currentDtvCcPacket;
        if (dtvCcPacket.currentIndex != (dtvCcPacket.packetSize * 2) - 1) {
            Log.d("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + ");");
        }
        ParsableBitArray parsableBitArray = this.captionChannelPacketData;
        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
        parsableBitArray.reset(dtvCcPacket2.packetData, dtvCcPacket2.currentIndex);
        boolean z = false;
        while (true) {
            if (this.captionChannelPacketData.bitsLeft() <= 0) {
                break;
            }
            int bits = this.captionChannelPacketData.readBits(3);
            int bits2 = this.captionChannelPacketData.readBits(5);
            if (bits == 7) {
                this.captionChannelPacketData.skipBits(2);
                bits = this.captionChannelPacketData.readBits(6);
                if (bits < 7) {
                    AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid extended service number: ", bits, "Cea708Decoder");
                }
            }
            if (bits2 == 0) {
                if (bits != 0) {
                    Log.w("Cea708Decoder", "serviceNumber is non-zero (" + bits + ") when blockSize is 0");
                }
            } else if (bits != this.selectedServiceNumber) {
                this.captionChannelPacketData.skipBytes(bits2);
            } else {
                int position = (bits2 * 8) + this.captionChannelPacketData.getPosition();
                while (this.captionChannelPacketData.getPosition() < position) {
                    int bits3 = this.captionChannelPacketData.readBits(8);
                    if (bits3 == 16) {
                        int bits4 = this.captionChannelPacketData.readBits(8);
                        if (bits4 <= 31) {
                            handleC2Command(bits4);
                        } else {
                            if (bits4 <= 127) {
                                handleG2Character(bits4);
                            } else if (bits4 <= 159) {
                                handleC3Command(bits4);
                            } else if (bits4 <= 255) {
                                handleG3Character(bits4);
                            } else {
                                AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid extended command: ", bits4, "Cea708Decoder");
                            }
                            z = true;
                        }
                    } else if (bits3 <= 31) {
                        handleC0Command(bits3);
                    } else {
                        if (bits3 <= 127) {
                            handleG0Character(bits3);
                        } else if (bits3 <= 159) {
                            handleC1Command(bits3);
                        } else if (bits3 <= 255) {
                            handleG1Character(bits3);
                        } else {
                            AudioFocusManager$$ExternalSyntheticOutline0.m("Invalid base command: ", bits3, "Cea708Decoder");
                        }
                        z = true;
                    }
                }
            }
        }
        if (z) {
            this.cues = getDisplayCues();
        }
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public void setPositionUs(long j) {
        this.playbackPositionUs = j;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
    }
}
