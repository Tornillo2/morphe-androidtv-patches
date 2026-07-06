package androidx.media3.extractor.text.ttml;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.TextEmphasisSpan;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TtmlRenderUtil {
    public static final String TAG = "TtmlRenderUtil";

    public static void applyStylesToSpan(Spannable spannable, int i, int i2, TtmlStyle ttmlStyle, @Nullable TtmlNode ttmlNode, Map<String, TtmlStyle> map, int i3) {
        TtmlNode ttmlNodeFindRubyTextNode;
        TtmlStyle ttmlStyleResolveStyle;
        int i4;
        if (ttmlStyle.getStyle() != -1) {
            spannable.setSpan(new StyleSpan(ttmlStyle.getStyle()), i, i2, 33);
        }
        if (ttmlStyle.isLinethrough()) {
            spannable.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (ttmlStyle.isUnderline()) {
            spannable.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (ttmlStyle.hasFontColor) {
            SpanUtil.addOrReplaceSpan(spannable, new ForegroundColorSpan(ttmlStyle.getFontColor()), i, i2, 33);
        }
        if (ttmlStyle.hasBackgroundColor) {
            SpanUtil.addOrReplaceSpan(spannable, new BackgroundColorSpan(ttmlStyle.getBackgroundColor()), i, i2, 33);
        }
        if (ttmlStyle.fontFamily != null) {
            SpanUtil.addOrReplaceSpan(spannable, new TypefaceSpan(ttmlStyle.fontFamily), i, i2, 33);
        }
        TextEmphasis textEmphasis = ttmlStyle.textEmphasis;
        if (textEmphasis != null) {
            textEmphasis.getClass();
            int i5 = textEmphasis.markShape;
            if (i5 == -1) {
                i5 = (i3 == 2 || i3 == 1) ? 3 : 1;
                i4 = 1;
            } else {
                i4 = textEmphasis.markFill;
            }
            int i6 = textEmphasis.position;
            if (i6 == -2) {
                i6 = 1;
            }
            SpanUtil.addOrReplaceSpan(spannable, new TextEmphasisSpan(i5, i4, i6), i, i2, 33);
        }
        int i7 = ttmlStyle.rubyType;
        if (i7 == 2) {
            TtmlNode ttmlNodeFindRubyContainerNode = findRubyContainerNode(ttmlNode, map);
            if (ttmlNodeFindRubyContainerNode != null && (ttmlNodeFindRubyTextNode = findRubyTextNode(ttmlNodeFindRubyContainerNode, map)) != null) {
                if (ttmlNodeFindRubyTextNode.getChildCount() != 1 || ttmlNodeFindRubyTextNode.getChild(0).text == null) {
                    Log.i("TtmlRenderUtil", "Skipping rubyText node without exactly one text child.");
                } else {
                    String str = ttmlNodeFindRubyTextNode.getChild(0).text;
                    Util.castNonNull(str);
                    TtmlStyle ttmlStyleResolveStyle2 = resolveStyle(ttmlNodeFindRubyTextNode.style, ttmlNodeFindRubyTextNode.styleIds, map);
                    int i8 = ttmlStyleResolveStyle2 != null ? ttmlStyleResolveStyle2.rubyPosition : -1;
                    if (i8 == -1 && (ttmlStyleResolveStyle = resolveStyle(ttmlNodeFindRubyContainerNode.style, ttmlNodeFindRubyContainerNode.styleIds, map)) != null) {
                        i8 = ttmlStyleResolveStyle.rubyPosition;
                    }
                    spannable.setSpan(new RubySpan(str, i8), i, i2, 33);
                }
            }
        } else if (i7 == 3 || i7 == 4) {
            spannable.setSpan(new DeleteTextSpan(), i, i2, 33);
        }
        if (ttmlStyle.getTextCombine()) {
            SpanUtil.addOrReplaceSpan(spannable, new HorizontalTextInVerticalContextSpan(), i, i2, 33);
        }
        int i9 = ttmlStyle.fontSizeUnit;
        if (i9 == 1) {
            SpanUtil.addOrReplaceSpan(spannable, new AbsoluteSizeSpan((int) ttmlStyle.fontSize, true), i, i2, 33);
        } else if (i9 == 2) {
            SpanUtil.addOrReplaceSpan(spannable, new RelativeSizeSpan(ttmlStyle.fontSize), i, i2, 33);
        } else {
            if (i9 != 3) {
                return;
            }
            SpanUtil.addOrReplaceSpan(spannable, new RelativeSizeSpan(ttmlStyle.fontSize / 100.0f), i, i2, 33);
        }
    }

    public static String applyTextElementSpacePolicy(String str) {
        return str.replaceAll("\r\n", StringUtils.LF).replaceAll(" *\n *", StringUtils.LF).replaceAll(StringUtils.LF, StringUtils.SPACE).replaceAll("[ \t\\x0B\f\r]+", StringUtils.SPACE);
    }

    public static void endParagraph(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length < 0 || spannableStringBuilder.charAt(length) == '\n') {
            return;
        }
        spannableStringBuilder.append('\n');
    }

    @Nullable
    public static TtmlNode findRubyContainerNode(@Nullable TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        while (ttmlNode != null) {
            TtmlStyle ttmlStyleResolveStyle = resolveStyle(ttmlNode.style, ttmlNode.styleIds, map);
            if (ttmlStyleResolveStyle != null && ttmlStyleResolveStyle.rubyType == 1) {
                return ttmlNode;
            }
            ttmlNode = ttmlNode.parent;
        }
        return null;
    }

    @Nullable
    public static TtmlNode findRubyTextNode(TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(ttmlNode);
        while (!arrayDeque.isEmpty()) {
            TtmlNode ttmlNode2 = (TtmlNode) arrayDeque.pop();
            TtmlStyle ttmlStyleResolveStyle = resolveStyle(ttmlNode2.style, ttmlNode2.styleIds, map);
            if (ttmlStyleResolveStyle != null && ttmlStyleResolveStyle.rubyType == 3) {
                return ttmlNode2;
            }
            for (int childCount = ttmlNode2.getChildCount() - 1; childCount >= 0; childCount--) {
                arrayDeque.push(ttmlNode2.getChild(childCount));
            }
        }
        return null;
    }

    @Nullable
    public static TtmlStyle resolveStyle(@Nullable TtmlStyle ttmlStyle, @Nullable String[] strArr, Map<String, TtmlStyle> map) {
        int i = 0;
        if (ttmlStyle == null) {
            if (strArr == null) {
                return null;
            }
            if (strArr.length == 1) {
                return map.get(strArr[0]);
            }
            if (strArr.length > 1) {
                TtmlStyle ttmlStyle2 = new TtmlStyle();
                int length = strArr.length;
                while (i < length) {
                    ttmlStyle2.inherit(map.get(strArr[i]), true);
                    i++;
                }
                return ttmlStyle2;
            }
        } else {
            if (strArr != null && strArr.length == 1) {
                ttmlStyle.inherit(map.get(strArr[0]), true);
                return ttmlStyle;
            }
            if (strArr != null && strArr.length > 1) {
                int length2 = strArr.length;
                while (i < length2) {
                    ttmlStyle.inherit(map.get(strArr[i]), true);
                    i++;
                }
            }
        }
        return ttmlStyle;
    }
}
