package androidx.media3.extractor.text.ttml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.media3.common.text.Cue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TtmlNode {
    public static final String ANNOTATION_POSITION_AFTER = "after";
    public static final String ANNOTATION_POSITION_BEFORE = "before";
    public static final String ANNOTATION_POSITION_OUTSIDE = "outside";
    public static final String ANONYMOUS_REGION_ID = "";
    public static final String ATTR_EBUTTS_MULTI_ROW_ALIGN = "multiRowAlign";
    public static final String ATTR_ID = "id";
    public static final String ATTR_TTS_BACKGROUND_COLOR = "backgroundColor";
    public static final String ATTR_TTS_COLOR = "color";
    public static final String ATTR_TTS_DISPLAY_ALIGN = "displayAlign";
    public static final String ATTR_TTS_EXTENT = "extent";
    public static final String ATTR_TTS_FONT_FAMILY = "fontFamily";
    public static final String ATTR_TTS_FONT_SIZE = "fontSize";
    public static final String ATTR_TTS_FONT_STYLE = "fontStyle";
    public static final String ATTR_TTS_FONT_WEIGHT = "fontWeight";
    public static final String ATTR_TTS_ORIGIN = "origin";
    public static final String ATTR_TTS_RUBY = "ruby";
    public static final String ATTR_TTS_RUBY_POSITION = "rubyPosition";
    public static final String ATTR_TTS_SHEAR = "shear";
    public static final String ATTR_TTS_TEXT_ALIGN = "textAlign";
    public static final String ATTR_TTS_TEXT_COMBINE = "textCombine";
    public static final String ATTR_TTS_TEXT_DECORATION = "textDecoration";
    public static final String ATTR_TTS_TEXT_EMPHASIS = "textEmphasis";
    public static final String ATTR_TTS_WRITING_MODE = "writingMode";
    public static final String BOLD = "bold";
    public static final String CENTER = "center";
    public static final String COMBINE_ALL = "all";
    public static final String COMBINE_NONE = "none";
    public static final String END = "end";
    public static final String ITALIC = "italic";
    public static final String LEFT = "left";
    public static final String LINETHROUGH = "linethrough";
    public static final String NO_LINETHROUGH = "nolinethrough";
    public static final String NO_UNDERLINE = "nounderline";
    public static final String RIGHT = "right";
    public static final String RUBY_BASE = "base";
    public static final String RUBY_BASE_CONTAINER = "baseContainer";
    public static final String RUBY_CONTAINER = "container";
    public static final String RUBY_DELIMITER = "delimiter";
    public static final String RUBY_TEXT = "text";
    public static final String RUBY_TEXT_CONTAINER = "textContainer";
    public static final String START = "start";
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DATA = "data";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_INFORMATION = "information";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";
    public static final String TEXT_EMPHASIS_AUTO = "auto";
    public static final String TEXT_EMPHASIS_MARK_CIRCLE = "circle";
    public static final String TEXT_EMPHASIS_MARK_DOT = "dot";
    public static final String TEXT_EMPHASIS_MARK_FILLED = "filled";
    public static final String TEXT_EMPHASIS_MARK_OPEN = "open";
    public static final String TEXT_EMPHASIS_MARK_SESAME = "sesame";
    public static final String TEXT_EMPHASIS_NONE = "none";
    public static final String UNDERLINE = "underline";
    public static final String VERTICAL = "tb";
    public static final String VERTICAL_LR = "tblr";
    public static final String VERTICAL_RL = "tbrl";
    public List<TtmlNode> children;
    public final long endTimeUs;

    @Nullable
    public final String imageId;
    public final boolean isTextNode;
    public final HashMap<String, Integer> nodeEndsByRegion;
    public final HashMap<String, Integer> nodeStartsByRegion;

    @Nullable
    public final TtmlNode parent;
    public final String regionId;
    public final long startTimeUs;

    @Nullable
    public final TtmlStyle style;

    @Nullable
    public final String[] styleIds;

    @Nullable
    public final String tag;

    @Nullable
    public final String text;

    public TtmlNode(@Nullable String str, @Nullable String str2, long j, long j2, @Nullable TtmlStyle ttmlStyle, @Nullable String[] strArr, String str3, @Nullable String str4, @Nullable TtmlNode ttmlNode) {
        this.tag = str;
        this.text = str2;
        this.imageId = str4;
        this.style = ttmlStyle;
        this.styleIds = strArr;
        this.isTextNode = str2 != null;
        this.startTimeUs = j;
        this.endTimeUs = j2;
        str3.getClass();
        this.regionId = str3;
        this.parent = ttmlNode;
        this.nodeStartsByRegion = new HashMap<>();
        this.nodeEndsByRegion = new HashMap<>();
    }

    public static TtmlNode buildNode(@Nullable String str, long j, long j2, @Nullable TtmlStyle ttmlStyle, @Nullable String[] strArr, String str2, @Nullable String str3, @Nullable TtmlNode ttmlNode) {
        return new TtmlNode(str, null, j, j2, ttmlStyle, strArr, str2, str3, ttmlNode);
    }

    public static TtmlNode buildTextNode(String str) {
        return new TtmlNode(null, TtmlRenderUtil.applyTextElementSpacePolicy(str), -9223372036854775807L, -9223372036854775807L, null, null, "", null, null);
    }

    public static void cleanUpText(SpannableStringBuilder spannableStringBuilder) {
        for (DeleteTextSpan deleteTextSpan : (DeleteTextSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), DeleteTextSpan.class)) {
            spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(deleteTextSpan), spannableStringBuilder.getSpanEnd(deleteTextSpan), "");
        }
        for (int i = 0; i < spannableStringBuilder.length(); i++) {
            if (spannableStringBuilder.charAt(i) == ' ') {
                int i2 = i + 1;
                int i3 = i2;
                while (i3 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i3) == ' ') {
                    i3++;
                }
                int i4 = i3 - i2;
                if (i4 > 0) {
                    spannableStringBuilder.delete(i, i4 + i);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
        }
        for (int i5 = 0; i5 < spannableStringBuilder.length() - 1; i5++) {
            if (spannableStringBuilder.charAt(i5) == '\n') {
                int i6 = i5 + 1;
                if (spannableStringBuilder.charAt(i6) == ' ') {
                    spannableStringBuilder.delete(i6, i5 + 2);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == ' ') {
            spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
        }
        for (int i7 = 0; i7 < spannableStringBuilder.length() - 1; i7++) {
            if (spannableStringBuilder.charAt(i7) == ' ') {
                int i8 = i7 + 1;
                if (spannableStringBuilder.charAt(i8) == '\n') {
                    spannableStringBuilder.delete(i7, i8);
                }
            }
        }
        if (spannableStringBuilder.length() <= 0 || spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) != '\n') {
            return;
        }
        spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
    }

    public static SpannableStringBuilder getRegionOutputText(String str, Map<String, Cue.Builder> map) {
        if (!map.containsKey(str)) {
            Cue.Builder builder = new Cue.Builder();
            builder.text = new SpannableStringBuilder();
            map.put(str, builder);
        }
        CharSequence charSequence = map.get(str).text;
        charSequence.getClass();
        return (SpannableStringBuilder) charSequence;
    }

    public void addChild(TtmlNode ttmlNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(ttmlNode);
    }

    public final void applyStyleToOutput(Map<String, TtmlStyle> map, Cue.Builder builder, int i, int i2, int i3) {
        TtmlStyle ttmlStyleResolveStyle = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, map);
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) builder.text;
        if (spannableStringBuilder == null) {
            spannableStringBuilder = new SpannableStringBuilder();
            builder.text = spannableStringBuilder;
        }
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        if (ttmlStyleResolveStyle != null) {
            TtmlRenderUtil.applyStylesToSpan(spannableStringBuilder2, i, i2, ttmlStyleResolveStyle, this.parent, map, i3);
            if ("p".equals(this.tag)) {
                float f = ttmlStyleResolveStyle.shearPercentage;
                if (f != Float.MAX_VALUE) {
                    builder.shearDegrees = (f * (-90.0f)) / 100.0f;
                }
                Layout.Alignment alignment = ttmlStyleResolveStyle.textAlign;
                if (alignment != null) {
                    builder.textAlignment = alignment;
                }
                Layout.Alignment alignment2 = ttmlStyleResolveStyle.multiRowAlign;
                if (alignment2 != null) {
                    builder.multiRowAlignment = alignment2;
                }
            }
        }
    }

    public TtmlNode getChild(int i) {
        List<TtmlNode> list = this.children;
        if (list != null) {
            return list.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        List<TtmlNode> list = this.children;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<Cue> getCues(long j, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        ArrayList arrayList = new ArrayList();
        traverseForImage(j, this.regionId, arrayList);
        TreeMap treeMap = new TreeMap();
        traverseForText(j, false, this.regionId, treeMap);
        traverseForStyle(j, map, map2, this.regionId, treeMap);
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            Pair pair = (Pair) obj;
            String str = map3.get(pair.second);
            if (str != null) {
                byte[] bArrDecode = Base64.decode(str, 0);
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                TtmlRegion ttmlRegion = map2.get(pair.first);
                ttmlRegion.getClass();
                Cue.Builder builder = new Cue.Builder();
                builder.bitmap = bitmapDecodeByteArray;
                builder.position = ttmlRegion.position;
                builder.positionAnchor = 0;
                builder.line = ttmlRegion.line;
                builder.lineType = 0;
                builder.lineAnchor = ttmlRegion.lineAnchor;
                builder.size = ttmlRegion.width;
                builder.bitmapHeight = ttmlRegion.height;
                builder.verticalType = ttmlRegion.verticalType;
                arrayList2.add(builder.build());
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            TtmlRegion ttmlRegion2 = map2.get(entry.getKey());
            ttmlRegion2.getClass();
            Cue.Builder builder2 = (Cue.Builder) entry.getValue();
            CharSequence charSequence = builder2.text;
            charSequence.getClass();
            cleanUpText((SpannableStringBuilder) charSequence);
            float f = ttmlRegion2.line;
            int i2 = ttmlRegion2.lineType;
            builder2.line = f;
            builder2.lineType = i2;
            builder2.lineAnchor = ttmlRegion2.lineAnchor;
            builder2.position = ttmlRegion2.position;
            builder2.size = ttmlRegion2.width;
            float f2 = ttmlRegion2.textSize;
            int i3 = ttmlRegion2.textSizeType;
            builder2.textSize = f2;
            builder2.textSizeType = i3;
            builder2.verticalType = ttmlRegion2.verticalType;
            arrayList2.add(builder2.build());
        }
        return arrayList2;
    }

    public final void getEventTimes(TreeSet<Long> treeSet, boolean z) {
        boolean zEquals = "p".equals(this.tag);
        boolean zEquals2 = "div".equals(this.tag);
        if (z || zEquals || (zEquals2 && this.imageId != null)) {
            long j = this.startTimeUs;
            if (j != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j));
            }
            long j2 = this.endTimeUs;
            if (j2 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j2));
            }
        }
        if (this.children == null) {
            return;
        }
        for (int i = 0; i < this.children.size(); i++) {
            this.children.get(i).getEventTimes(treeSet, z || zEquals);
        }
    }

    public long[] getEventTimesUs() {
        TreeSet<Long> treeSet = new TreeSet<>();
        int i = 0;
        getEventTimes(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator<Long> it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    @Nullable
    public String[] getStyleIds() {
        return this.styleIds;
    }

    public boolean isActive(long j) {
        long j2 = this.startTimeUs;
        if (j2 == -9223372036854775807L && this.endTimeUs == -9223372036854775807L) {
            return true;
        }
        if (j2 <= j && this.endTimeUs == -9223372036854775807L) {
            return true;
        }
        if (j2 != -9223372036854775807L || j >= this.endTimeUs) {
            return j2 <= j && j < this.endTimeUs;
        }
        return true;
    }

    public final void traverseForImage(long j, String str, List<Pair<String, String>> list) {
        if (!"".equals(this.regionId)) {
            str = this.regionId;
        }
        if (isActive(j) && "div".equals(this.tag) && this.imageId != null) {
            list.add(new Pair<>(str, this.imageId));
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChild(i).traverseForImage(j, str, list);
        }
    }

    public final void traverseForStyle(long j, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, String str, Map<String, Cue.Builder> map3) {
        if (isActive(j)) {
            String str2 = "".equals(this.regionId) ? str : this.regionId;
            Iterator<Map.Entry<String, Integer>> it = this.nodeEndsByRegion.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, Integer> next = it.next();
                String key = next.getKey();
                int iIntValue = this.nodeStartsByRegion.containsKey(key) ? this.nodeStartsByRegion.get(key).intValue() : 0;
                int iIntValue2 = next.getValue().intValue();
                if (iIntValue != iIntValue2) {
                    Cue.Builder builder = map3.get(key);
                    builder.getClass();
                    TtmlRegion ttmlRegion = map2.get(str2);
                    ttmlRegion.getClass();
                    applyStyleToOutput(map, builder, iIntValue, iIntValue2, ttmlRegion.verticalType);
                }
            }
            for (int i = 0; i < getChildCount(); i++) {
                getChild(i).traverseForStyle(j, map, map2, str2, map3);
            }
        }
    }

    public final void traverseForText(long j, boolean z, String str, Map<String, Cue.Builder> map) {
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        if ("metadata".equals(this.tag)) {
            return;
        }
        if (!"".equals(this.regionId)) {
            str = this.regionId;
        }
        String str2 = str;
        if (this.isTextNode && z) {
            SpannableStringBuilder regionOutputText = getRegionOutputText(str2, map);
            String str3 = this.text;
            str3.getClass();
            regionOutputText.append((CharSequence) str3);
            return;
        }
        if ("br".equals(this.tag) && z) {
            getRegionOutputText(str2, map).append('\n');
            return;
        }
        if (isActive(j)) {
            for (Map.Entry<String, Cue.Builder> entry : map.entrySet()) {
                HashMap<String, Integer> map2 = this.nodeStartsByRegion;
                String key = entry.getKey();
                CharSequence charSequence = entry.getValue().text;
                charSequence.getClass();
                map2.put(key, Integer.valueOf(charSequence.length()));
            }
            boolean zEquals = "p".equals(this.tag);
            int i = 0;
            while (i < getChildCount()) {
                long j2 = j;
                Map<String, Cue.Builder> map3 = map;
                getChild(i).traverseForText(j2, z || zEquals, str2, map3);
                i++;
                j = j2;
                map = map3;
            }
            Map<String, Cue.Builder> map4 = map;
            if (zEquals) {
                TtmlRenderUtil.endParagraph(getRegionOutputText(str2, map4));
            }
            for (Map.Entry<String, Cue.Builder> entry2 : map4.entrySet()) {
                HashMap<String, Integer> map5 = this.nodeEndsByRegion;
                String key2 = entry2.getKey();
                CharSequence charSequence2 = entry2.getValue().text;
                charSequence2.getClass();
                map5.put(key2, Integer.valueOf(charSequence2.length()));
            }
        }
    }
}
