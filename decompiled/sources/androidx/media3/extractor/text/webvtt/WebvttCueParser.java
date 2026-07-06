package androidx.media3.extractor.text.webvtt;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import com.google.common.base.Charsets;
import j$.util.DesugarCollections;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class WebvttCueParser {
    public static final char CHAR_AMPERSAND = '&';
    public static final char CHAR_GREATER_THAN = '>';
    public static final char CHAR_LESS_THAN = '<';
    public static final char CHAR_SEMI_COLON = ';';
    public static final char CHAR_SLASH = '/';
    public static final char CHAR_SPACE = ' ';
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    public static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    public static final Map<String, Integer> DEFAULT_BACKGROUND_COLORS;
    public static final float DEFAULT_POSITION = 0.5f;
    public static final Map<String, Integer> DEFAULT_TEXT_COLORS;
    public static final String ENTITY_AMPERSAND = "amp";
    public static final String ENTITY_GREATER_THAN = "gt";
    public static final String ENTITY_LESS_THAN = "lt";
    public static final String ENTITY_NON_BREAK_SPACE = "nbsp";
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final String TAG = "WebvttCueParser";
    public static final String TAG_BOLD = "b";
    public static final String TAG_CLASS = "c";
    public static final String TAG_ITALIC = "i";
    public static final String TAG_LANG = "lang";
    public static final String TAG_RUBY = "ruby";
    public static final String TAG_RUBY_TEXT = "rt";
    public static final String TAG_UNDERLINE = "u";
    public static final String TAG_VOICE = "v";
    public static final int TEXT_ALIGNMENT_CENTER = 2;
    public static final int TEXT_ALIGNMENT_END = 3;
    public static final int TEXT_ALIGNMENT_LEFT = 4;
    public static final int TEXT_ALIGNMENT_RIGHT = 5;
    public static final int TEXT_ALIGNMENT_START = 1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Element {
        public static final Comparator<Element> BY_START_POSITION_ASC = new WebvttCueParser$Element$$ExternalSyntheticLambda0();
        public final int endPosition;
        public final StartTag startTag;

        public Element(StartTag startTag, int i) {
            this.startTag = startTag;
            this.endPosition = i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StartTag {
        public final Set<String> classes;
        public final String name;
        public final int position;
        public final String voice;

        public StartTag(String str, int i, String str2, Set<String> set) {
            this.position = i;
            this.name = str;
            this.voice = str2;
            this.classes = set;
        }

        public static StartTag buildStartTag(String str, int i) {
            String str2;
            String strTrim = str.trim();
            Assertions.checkArgument(!strTrim.isEmpty());
            int iIndexOf = strTrim.indexOf(StringUtils.SPACE);
            if (iIndexOf == -1) {
                str2 = "";
            } else {
                String strTrim2 = strTrim.substring(iIndexOf).trim();
                strTrim = strTrim.substring(0, iIndexOf);
                str2 = strTrim2;
            }
            String[] strArrSplit = Util.split(strTrim, "\\.");
            String str3 = strArrSplit[0];
            HashSet hashSet = new HashSet();
            for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                hashSet.add(strArrSplit[i2]);
            }
            return new StartTag(str3, i, str2, hashSet);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", Collections.EMPTY_SET);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i, WebvttCssStyle webvttCssStyle) {
            this.score = i;
            this.style = webvttCssStyle;
        }

        @Override // java.lang.Comparable
        public int compareTo(StyleMatch styleMatch) {
            return Integer.compare(this.score, styleMatch.score);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WebvttCueInfoBuilder {
        public CharSequence text;
        public long startTimeUs = 0;
        public long endTimeUs = 0;
        public int textAlignment = 2;
        public float line = -3.4028235E38f;
        public int lineType = 1;
        public int lineAnchor = 0;
        public float position = -3.4028235E38f;
        public int positionAnchor = Integer.MIN_VALUE;
        public float size = 1.0f;
        public int verticalType = Integer.MIN_VALUE;

        public static float computeLine(float f, int i) {
            if (f == -3.4028235E38f || i != 0 || (f >= 0.0f && f <= 1.0f)) {
                return f != -3.4028235E38f ? f : i == 0 ? 1.0f : -3.4028235E38f;
            }
            return 1.0f;
        }

        @Nullable
        public static Layout.Alignment convertTextAlignment(int i) {
            if (i != 1) {
                if (i == 2) {
                    return Layout.Alignment.ALIGN_CENTER;
                }
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown textAlignment: ", i, "WebvttCueParser");
                            return null;
                        }
                    }
                }
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
            return Layout.Alignment.ALIGN_NORMAL;
        }

        public static float deriveMaxSize(int i, float f) {
            if (i == 0) {
                return 1.0f - f;
            }
            if (i == 1) {
                return f <= 0.5f ? f * 2.0f : (1.0f - f) * 2.0f;
            }
            if (i == 2) {
                return f;
            }
            throw new IllegalStateException(String.valueOf(i));
        }

        public static float derivePosition(int i) {
            if (i != 4) {
                return i != 5 ? 0.5f : 1.0f;
            }
            return 0.0f;
        }

        public static int derivePositionAnchor(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 3) {
                return 2;
            }
            if (i != 4) {
                return i != 5 ? 1 : 2;
            }
            return 0;
        }

        public WebvttCueInfo build() {
            return new WebvttCueInfo(toCueBuilder().build(), this.startTimeUs, this.endTimeUs);
        }

        public Cue.Builder toCueBuilder() {
            float fDerivePosition = this.position;
            if (fDerivePosition == -3.4028235E38f) {
                fDerivePosition = derivePosition(this.textAlignment);
            }
            int iDerivePositionAnchor = this.positionAnchor;
            if (iDerivePositionAnchor == Integer.MIN_VALUE) {
                iDerivePositionAnchor = derivePositionAnchor(this.textAlignment);
            }
            Cue.Builder builder = new Cue.Builder();
            builder.textAlignment = convertTextAlignment(this.textAlignment);
            float fComputeLine = computeLine(this.line, this.lineType);
            int i = this.lineType;
            builder.line = fComputeLine;
            builder.lineType = i;
            builder.lineAnchor = this.lineAnchor;
            builder.position = fDerivePosition;
            builder.positionAnchor = iDerivePositionAnchor;
            builder.size = Math.min(this.size, deriveMaxSize(iDerivePositionAnchor, fDerivePosition));
            builder.verticalType = this.verticalType;
            CharSequence charSequence = this.text;
            if (charSequence != null) {
                builder.text = charSequence;
            }
            return builder;
        }
    }

    static {
        HashMap map = new HashMap();
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 255, 255, map, "white");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 255, 0, map, "lime");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 255, 255, map, "cyan");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 0, 0, map, "red");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 255, 0, map, "yellow");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 0, 255, map, "magenta");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 0, 255, map, "blue");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 0, 0, map, "black");
        DEFAULT_TEXT_COLORS = DesugarCollections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 255, 255, map2, "bg_white");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 255, 0, map2, "bg_lime");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 255, 255, map2, "bg_cyan");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 0, 0, map2, "bg_red");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 255, 0, map2, "bg_yellow");
        WebvttCueParser$$ExternalSyntheticOutline0.m(255, 0, 255, map2, "bg_magenta");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 0, 255, map2, "bg_blue");
        WebvttCueParser$$ExternalSyntheticOutline0.m(0, 0, 0, map2, "bg_black");
        DEFAULT_BACKGROUND_COLORS = DesugarCollections.unmodifiableMap(map2);
    }

    public static void applyDefaultColors(SpannableStringBuilder spannableStringBuilder, Set<String> set, int i, int i2) {
        for (String str : set) {
            Map<String, Integer> map = DEFAULT_TEXT_COLORS;
            if (map.containsKey(str)) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(map.get(str).intValue()), i, i2, 33);
            } else {
                Map<String, Integer> map2 = DEFAULT_BACKGROUND_COLORS;
                if (map2.containsKey(str)) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(map2.get(str).intValue()), i, i2, 33);
                }
            }
        }
    }

    public static void applyEntity(String str, SpannableStringBuilder spannableStringBuilder) {
        str.getClass();
        switch (str) {
            case "gt":
                spannableStringBuilder.append('>');
                break;
            case "lt":
                spannableStringBuilder.append('<');
                break;
            case "amp":
                spannableStringBuilder.append('&');
                break;
            case "nbsp":
                spannableStringBuilder.append(' ');
                break;
            default:
                Log.w("WebvttCueParser", "ignoring unsupported entity: '&" + str + ";'");
                break;
        }
    }

    public static void applyRubySpans(SpannableStringBuilder spannableStringBuilder, @Nullable String str, StartTag startTag, List<Element> list, List<WebvttCssStyle> list2) {
        int rubyPosition = getRubyPosition(list2, str, startTag);
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        Collections.sort(arrayList, Element.BY_START_POSITION_ASC);
        int i = startTag.position;
        int length = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if ("rt".equals(((Element) arrayList.get(i2)).startTag.name)) {
                Element element = (Element) arrayList.get(i2);
                int iFirstKnownRubyPosition = firstKnownRubyPosition(getRubyPosition(list2, str, element.startTag), rubyPosition, 1);
                int i3 = element.startTag.position - length;
                int i4 = element.endPosition - length;
                CharSequence charSequenceSubSequence = spannableStringBuilder.subSequence(i3, i4);
                spannableStringBuilder.delete(i3, i4);
                spannableStringBuilder.setSpan(new RubySpan(charSequenceSubSequence.toString(), iFirstKnownRubyPosition), i, i3, 33);
                length = charSequenceSubSequence.length() + length;
                i = i3;
            }
        }
    }

    public static void applySpansForTag(@Nullable String str, StartTag startTag, List<Element> list, SpannableStringBuilder spannableStringBuilder, List<WebvttCssStyle> list2) {
        int i;
        int length;
        i = startTag.position;
        length = spannableStringBuilder.length();
        String str2 = startTag.name;
        str2.getClass();
        int i2 = 0;
        switch (str2) {
            case "":
            case "v":
            case "lang":
                break;
            case "b":
                spannableStringBuilder.setSpan(new StyleSpan(1), i, length, 33);
                break;
            case "c":
                applyDefaultColors(spannableStringBuilder, startTag.classes, i, length);
                break;
            case "i":
                spannableStringBuilder.setSpan(new StyleSpan(2), i, length, 33);
                break;
            case "u":
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, length, 33);
                break;
            case "ruby":
                applyRubySpans(spannableStringBuilder, str, startTag, list, list2);
                break;
            default:
                return;
        }
        List<StyleMatch> applicableStyles = getApplicableStyles(list2, str, startTag);
        while (true) {
            ArrayList arrayList = (ArrayList) applicableStyles;
            if (i2 >= arrayList.size()) {
                return;
            }
            applyStyleToText(spannableStringBuilder, ((StyleMatch) arrayList.get(i2)).style, i, length);
            i2++;
        }
    }

    public static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i, int i2) {
        if (webvttCssStyle == null) {
            return;
        }
        if (webvttCssStyle.getStyle() != -1) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new StyleSpan(webvttCssStyle.getStyle()), i, i2, 33);
        }
        if (webvttCssStyle.isLinethrough()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (webvttCssStyle.isUnderline()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (webvttCssStyle.hasFontColor) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new ForegroundColorSpan(webvttCssStyle.getFontColor()), i, i2, 33);
        }
        if (webvttCssStyle.hasBackgroundColor) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i, i2, 33);
        }
        if (webvttCssStyle.fontFamily != null) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new TypefaceSpan(webvttCssStyle.fontFamily), i, i2, 33);
        }
        int i3 = webvttCssStyle.fontSizeUnit;
        if (i3 == 1) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new AbsoluteSizeSpan((int) webvttCssStyle.fontSize, true), i, i2, 33);
        } else if (i3 == 2) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new RelativeSizeSpan(webvttCssStyle.fontSize), i, i2, 33);
        } else if (i3 == 3) {
            SpanUtil.addOrReplaceSpan(spannableStringBuilder, new RelativeSizeSpan(webvttCssStyle.fontSize / 100.0f), i, i2, 33);
        }
        if (webvttCssStyle.combineUpright) {
            spannableStringBuilder.setSpan(new HorizontalTextInVerticalContextSpan(), i, i2, 33);
        }
    }

    public static int findEndOfTag(String str, int i) {
        int iIndexOf = str.indexOf(62, i);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    public static int firstKnownRubyPosition(int i, int i2, int i3) {
        if (i != -1) {
            return i;
        }
        if (i2 != -1) {
            return i2;
        }
        if (i3 != -1) {
            return i3;
        }
        throw new IllegalArgumentException();
    }

    public static List<StyleMatch> getApplicableStyles(List<WebvttCssStyle> list, @Nullable String str, StartTag startTag) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            WebvttCssStyle webvttCssStyle = list.get(i);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                arrayList.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static int getRubyPosition(List<WebvttCssStyle> list, @Nullable String str, StartTag startTag) {
        List<StyleMatch> applicableStyles = getApplicableStyles(list, str, startTag);
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) applicableStyles;
            if (i >= arrayList.size()) {
                return -1;
            }
            int i2 = ((StyleMatch) arrayList.get(i)).style.rubyPosition;
            if (i2 != -1) {
                return i2;
            }
            i++;
        }
    }

    public static String getTagName(String str) {
        String strTrim = str.trim();
        Assertions.checkArgument(!strTrim.isEmpty());
        return Util.splitAtFirst(strTrim, "[ \\.]")[0];
    }

    public static boolean isSupportedTag(String str) {
        str.getClass();
        switch (str) {
            case "b":
            case "c":
            case "i":
            case "u":
            case "v":
            case "rt":
            case "lang":
            case "ruby":
                return true;
            default:
                return false;
        }
    }

    @VisibleForTesting(otherwise = 3)
    public static Cue newCueForText(CharSequence charSequence) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        webvttCueInfoBuilder.text = charSequence;
        return webvttCueInfoBuilder.toCueBuilder().build();
    }

    @Nullable
    public static WebvttCueInfo parseCue(ParsableByteArray parsableByteArray, List<WebvttCssStyle> list) {
        parsableByteArray.getClass();
        Charset charset = Charsets.UTF_8;
        String line = parsableByteArray.readLine(charset);
        if (line == null) {
            return null;
        }
        Pattern pattern = CUE_HEADER_PATTERN;
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return parseCue(null, matcher, parsableByteArray, list);
        }
        String line2 = parsableByteArray.readLine(charset);
        if (line2 == null) {
            return null;
        }
        Matcher matcher2 = pattern.matcher(line2);
        if (matcher2.matches()) {
            return parseCue(line.trim(), matcher2, parsableByteArray, list);
        }
        return null;
    }

    public static Cue.Builder parseCueSettingsList(String str) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        parseCueSettingsList(str, webvttCueInfoBuilder);
        return webvttCueInfoBuilder.toCueBuilder();
    }

    public static SpannedString parseCueText(@Nullable String str, String str2, List<WebvttCssStyle> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char cCharAt = str2.charAt(i);
            if (cCharAt == '&') {
                i++;
                int iIndexOf = str2.indexOf(59, i);
                int iIndexOf2 = str2.indexOf(32, i);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    applyEntity(str2.substring(i, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append((CharSequence) StringUtils.SPACE);
                    }
                    i = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt != '<') {
                spannableStringBuilder.append(cCharAt);
                i++;
            } else {
                int iFindEndOfTag = i + 1;
                if (iFindEndOfTag < str2.length()) {
                    boolean z = str2.charAt(iFindEndOfTag) == '/';
                    iFindEndOfTag = findEndOfTag(str2, iFindEndOfTag);
                    int i2 = iFindEndOfTag - 2;
                    boolean z2 = str2.charAt(i2) == '/';
                    int i3 = i + (z ? 2 : 1);
                    if (!z2) {
                        i2 = iFindEndOfTag - 1;
                    }
                    String strSubstring = str2.substring(i3, i2);
                    if (!strSubstring.trim().isEmpty()) {
                        String tagName = getTagName(strSubstring);
                        if (isSupportedTag(tagName)) {
                            if (z) {
                                while (!arrayDeque.isEmpty()) {
                                    StartTag startTag = (StartTag) arrayDeque.pop();
                                    applySpansForTag(str, startTag, arrayList, spannableStringBuilder, list);
                                    if (arrayDeque.isEmpty()) {
                                        arrayList.clear();
                                    } else {
                                        arrayList.add(new Element(startTag, spannableStringBuilder.length()));
                                    }
                                    if (startTag.name.equals(tagName)) {
                                        break;
                                    }
                                }
                            } else if (!z2) {
                                arrayDeque.push(StartTag.buildStartTag(strSubstring, spannableStringBuilder.length()));
                            }
                        }
                    }
                }
                i = iFindEndOfTag;
            }
        }
        while (!arrayDeque.isEmpty()) {
            applySpansForTag(str, (StartTag) arrayDeque.pop(), arrayList, spannableStringBuilder, list);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), Collections.EMPTY_LIST, spannableStringBuilder, list);
        return SpannedString.valueOf(spannableStringBuilder);
    }

    public static int parseLineAnchor(String str) {
        str.getClass();
        switch (str) {
            case "center":
            case "middle":
                return 1;
            case "end":
                return 2;
            case "start":
                return 0;
            default:
                Log.w("WebvttCueParser", "Invalid anchor value: ".concat(str));
                return Integer.MIN_VALUE;
        }
    }

    public static void parseLineAttribute(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            webvttCueInfoBuilder.lineAnchor = parseLineAnchor(str.substring(iIndexOf + 1));
            str = str.substring(0, iIndexOf);
        }
        if (str.endsWith("%")) {
            webvttCueInfoBuilder.line = WebvttParserUtil.parsePercentage(str);
            webvttCueInfoBuilder.lineType = 0;
        } else {
            webvttCueInfoBuilder.line = Integer.parseInt(str);
            webvttCueInfoBuilder.lineType = 1;
        }
    }

    public static int parsePositionAnchor(String str) {
        str.getClass();
        switch (str) {
            case "line-left":
            case "start":
                return 0;
            case "center":
            case "middle":
                return 1;
            case "line-right":
            case "end":
                return 2;
            default:
                Log.w("WebvttCueParser", "Invalid anchor value: ".concat(str));
                return Integer.MIN_VALUE;
        }
    }

    public static void parsePositionAttribute(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            webvttCueInfoBuilder.positionAnchor = parsePositionAnchor(str.substring(iIndexOf + 1));
            str = str.substring(0, iIndexOf);
        }
        webvttCueInfoBuilder.position = WebvttParserUtil.parsePercentage(str);
    }

    public static int parseTextAlignment(String str) {
        str.getClass();
        switch (str) {
            case "center":
            case "middle":
                return 2;
            case "end":
                return 3;
            case "left":
                return 4;
            case "right":
                return 5;
            case "start":
                return 1;
            default:
                Log.w("WebvttCueParser", "Invalid alignment value: ".concat(str));
                return 2;
        }
    }

    public static int parseVerticalAttribute(String str) {
        str.getClass();
        if (str.equals("lr")) {
            return 2;
        }
        if (str.equals("rl")) {
            return 1;
        }
        Log.w("WebvttCueParser", "Invalid 'vertical' value: ".concat(str));
        return Integer.MIN_VALUE;
    }

    public static void parseCueSettingsList(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            try {
                if ("line".equals(strGroup)) {
                    parseLineAttribute(strGroup2, webvttCueInfoBuilder);
                } else if ("align".equals(strGroup)) {
                    webvttCueInfoBuilder.textAlignment = parseTextAlignment(strGroup2);
                } else if ("position".equals(strGroup)) {
                    parsePositionAttribute(strGroup2, webvttCueInfoBuilder);
                } else if ("size".equals(strGroup)) {
                    webvttCueInfoBuilder.size = WebvttParserUtil.parsePercentage(strGroup2);
                } else if ("vertical".equals(strGroup)) {
                    webvttCueInfoBuilder.verticalType = parseVerticalAttribute(strGroup2);
                } else {
                    Log.w("WebvttCueParser", "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    @Nullable
    public static WebvttCueInfo parseCue(@Nullable String str, Matcher matcher, ParsableByteArray parsableByteArray, List<WebvttCssStyle> list) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        try {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            webvttCueInfoBuilder.startTimeUs = WebvttParserUtil.parseTimestampUs(strGroup);
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            webvttCueInfoBuilder.endTimeUs = WebvttParserUtil.parseTimestampUs(strGroup2);
            String strGroup3 = matcher.group(3);
            strGroup3.getClass();
            parseCueSettingsList(strGroup3, webvttCueInfoBuilder);
            StringBuilder sb = new StringBuilder();
            parsableByteArray.getClass();
            String line = parsableByteArray.readLine(Charsets.UTF_8);
            while (!TextUtils.isEmpty(line)) {
                if (sb.length() > 0) {
                    sb.append(StringUtils.LF);
                }
                sb.append(line.trim());
                line = parsableByteArray.readLine(Charsets.UTF_8);
            }
            webvttCueInfoBuilder.text = parseCueText(str, sb.toString(), list);
            return webvttCueInfoBuilder.build();
        } catch (NumberFormatException unused) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return null;
        }
    }
}
