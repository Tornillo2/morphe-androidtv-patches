package androidx.media3.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import com.google.common.base.Ascii;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class ColorParser {
    public static final Map<String, Integer> COLOR_MAP;
    public static final String RGB = "rgb";
    public static final String RGBA = "rgba";
    public static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    public static final Pattern RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    public static final Pattern RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");

    static {
        HashMap map = new HashMap();
        COLOR_MAP = map;
        ColorParser$$ExternalSyntheticOutline0.m(-984833, map, "aliceblue", -332841, "antiquewhite");
        ColorParser$$ExternalSyntheticOutline1.m(map, "aqua", -16711681, -8388652, "aquamarine");
        ColorParser$$ExternalSyntheticOutline0.m(-983041, map, "azure", -657956, "beige");
        ColorParser$$ExternalSyntheticOutline0.m(-6972, map, "bisque", -16777216, "black");
        ColorParser$$ExternalSyntheticOutline0.m(-5171, map, "blanchedalmond", -16776961, "blue");
        ColorParser$$ExternalSyntheticOutline0.m(-7722014, map, "blueviolet", -5952982, "brown");
        ColorParser$$ExternalSyntheticOutline0.m(-2180985, map, "burlywood", -10510688, "cadetblue");
        ColorParser$$ExternalSyntheticOutline0.m(-8388864, map, "chartreuse", -2987746, "chocolate");
        ColorParser$$ExternalSyntheticOutline0.m(-32944, map, "coral", -10185235, "cornflowerblue");
        ColorParser$$ExternalSyntheticOutline0.m(-1828, map, "cornsilk", -2354116, "crimson");
        ColorParser$$ExternalSyntheticOutline1.m(map, "cyan", -16711681, -16777077, "darkblue");
        ColorParser$$ExternalSyntheticOutline0.m(-16741493, map, "darkcyan", -4684277, "darkgoldenrod");
        ColorParser$$ExternalSyntheticOutline1.m(map, "darkgray", -5658199, -16751616, "darkgreen");
        ColorParser$$ExternalSyntheticOutline1.m(map, "darkgrey", -5658199, -4343957, "darkkhaki");
        ColorParser$$ExternalSyntheticOutline0.m(-7667573, map, "darkmagenta", -11179217, "darkolivegreen");
        ColorParser$$ExternalSyntheticOutline0.m(-29696, map, "darkorange", -6737204, "darkorchid");
        ColorParser$$ExternalSyntheticOutline0.m(-7667712, map, "darkred", -1468806, "darksalmon");
        ColorParser$$ExternalSyntheticOutline0.m(-7357297, map, "darkseagreen", -12042869, "darkslateblue");
        map.put("darkslategray", -13676721);
        map.put("darkslategrey", -13676721);
        ColorParser$$ExternalSyntheticOutline1.m(map, "darkturquoise", -16724271, -7077677, "darkviolet");
        ColorParser$$ExternalSyntheticOutline0.m(-60269, map, "deeppink", -16728065, "deepskyblue");
        map.put("dimgray", -9868951);
        map.put("dimgrey", -9868951);
        ColorParser$$ExternalSyntheticOutline1.m(map, "dodgerblue", -14774017, -5103070, "firebrick");
        ColorParser$$ExternalSyntheticOutline0.m(-1296, map, "floralwhite", -14513374, "forestgreen");
        ColorParser$$ExternalSyntheticOutline1.m(map, "fuchsia", -65281, -2302756, "gainsboro");
        ColorParser$$ExternalSyntheticOutline0.m(-460545, map, "ghostwhite", -10496, "gold");
        map.put("goldenrod", -2448096);
        map.put("gray", -8355712);
        ColorParser$$ExternalSyntheticOutline0.m(-16744448, map, "green", -5374161, "greenyellow");
        ColorParser$$ExternalSyntheticOutline1.m(map, "grey", -8355712, -983056, "honeydew");
        ColorParser$$ExternalSyntheticOutline0.m(-38476, map, "hotpink", -3318692, "indianred");
        ColorParser$$ExternalSyntheticOutline0.m(-11861886, map, "indigo", -16, "ivory");
        ColorParser$$ExternalSyntheticOutline0.m(-989556, map, "khaki", -1644806, "lavender");
        ColorParser$$ExternalSyntheticOutline0.m(-3851, map, "lavenderblush", -8586240, "lawngreen");
        ColorParser$$ExternalSyntheticOutline0.m(-1331, map, "lemonchiffon", -5383962, "lightblue");
        ColorParser$$ExternalSyntheticOutline0.m(-1015680, map, "lightcoral", -2031617, "lightcyan");
        map.put("lightgoldenrodyellow", -329006);
        map.put("lightgray", -2894893);
        map.put("lightgreen", -7278960);
        map.put("lightgrey", -2894893);
        ColorParser$$ExternalSyntheticOutline0.m(-18751, map, "lightpink", -24454, "lightsalmon");
        ColorParser$$ExternalSyntheticOutline0.m(-14634326, map, "lightseagreen", -7876870, "lightskyblue");
        map.put("lightslategray", -8943463);
        map.put("lightslategrey", -8943463);
        ColorParser$$ExternalSyntheticOutline1.m(map, "lightsteelblue", -5192482, -32, "lightyellow");
        ColorParser$$ExternalSyntheticOutline0.m(-16711936, map, "lime", -13447886, "limegreen");
        map.put("linen", -331546);
        map.put("magenta", -65281);
        ColorParser$$ExternalSyntheticOutline0.m(-8388608, map, "maroon", -10039894, "mediumaquamarine");
        ColorParser$$ExternalSyntheticOutline0.m(-16777011, map, "mediumblue", -4565549, "mediumorchid");
        ColorParser$$ExternalSyntheticOutline0.m(-7114533, map, "mediumpurple", -12799119, "mediumseagreen");
        ColorParser$$ExternalSyntheticOutline0.m(-8689426, map, "mediumslateblue", -16713062, "mediumspringgreen");
        ColorParser$$ExternalSyntheticOutline0.m(-12004916, map, "mediumturquoise", -3730043, "mediumvioletred");
        ColorParser$$ExternalSyntheticOutline0.m(-15132304, map, "midnightblue", -655366, "mintcream");
        ColorParser$$ExternalSyntheticOutline0.m(-6943, map, "mistyrose", -6987, "moccasin");
        ColorParser$$ExternalSyntheticOutline0.m(-8531, map, "navajowhite", -16777088, "navy");
        ColorParser$$ExternalSyntheticOutline0.m(-133658, map, "oldlace", -8355840, "olive");
        ColorParser$$ExternalSyntheticOutline0.m(-9728477, map, "olivedrab", -23296, "orange");
        ColorParser$$ExternalSyntheticOutline0.m(-47872, map, "orangered", -2461482, "orchid");
        ColorParser$$ExternalSyntheticOutline0.m(-1120086, map, "palegoldenrod", -6751336, "palegreen");
        ColorParser$$ExternalSyntheticOutline0.m(-5247250, map, "paleturquoise", -2396013, "palevioletred");
        ColorParser$$ExternalSyntheticOutline0.m(-4139, map, "papayawhip", -9543, "peachpuff");
        ColorParser$$ExternalSyntheticOutline0.m(-3308225, map, "peru", -16181, "pink");
        ColorParser$$ExternalSyntheticOutline0.m(-2252579, map, "plum", -5185306, "powderblue");
        ColorParser$$ExternalSyntheticOutline0.m(-8388480, map, "purple", -10079335, "rebeccapurple");
        ColorParser$$ExternalSyntheticOutline0.m(-65536, map, "red", -4419697, "rosybrown");
        ColorParser$$ExternalSyntheticOutline0.m(-12490271, map, "royalblue", -7650029, "saddlebrown");
        ColorParser$$ExternalSyntheticOutline0.m(-360334, map, "salmon", -744352, "sandybrown");
        ColorParser$$ExternalSyntheticOutline0.m(-13726889, map, "seagreen", -2578, "seashell");
        ColorParser$$ExternalSyntheticOutline0.m(-6270419, map, "sienna", -4144960, "silver");
        ColorParser$$ExternalSyntheticOutline0.m(-7876885, map, "skyblue", -9807155, "slateblue");
        map.put("slategray", -9404272);
        map.put("slategrey", -9404272);
        ColorParser$$ExternalSyntheticOutline1.m(map, "snow", -1286, -16711809, "springgreen");
        ColorParser$$ExternalSyntheticOutline0.m(-12156236, map, "steelblue", -2968436, "tan");
        ColorParser$$ExternalSyntheticOutline0.m(-16744320, map, "teal", -2572328, "thistle");
        ColorParser$$ExternalSyntheticOutline0.m(-40121, map, "tomato", 0, "transparent");
        ColorParser$$ExternalSyntheticOutline0.m(-12525360, map, "turquoise", -1146130, "violet");
        ColorParser$$ExternalSyntheticOutline0.m(-663885, map, "wheat", -1, "white");
        ColorParser$$ExternalSyntheticOutline0.m(-657931, map, "whitesmoke", -256, "yellow");
        map.put("yellowgreen", -6632142);
    }

    @ColorInt
    public static int parseColorInternal(String str, boolean z) {
        int i;
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String strReplace = str.replace(StringUtils.SPACE, "");
        if (strReplace.charAt(0) == '#') {
            int i2 = (int) Long.parseLong(strReplace.substring(1), 16);
            if (strReplace.length() == 7) {
                return (-16777216) | i2;
            }
            if (strReplace.length() == 9) {
                return ((i2 & 255) << 24) | (i2 >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (strReplace.startsWith("rgba")) {
            Matcher matcher = (z ? RGBA_PATTERN_FLOAT_ALPHA : RGBA_PATTERN_INT_ALPHA).matcher(strReplace);
            if (matcher.matches()) {
                if (z) {
                    String strGroup = matcher.group(4);
                    strGroup.getClass();
                    i = (int) (Float.parseFloat(strGroup) * 255.0f);
                } else {
                    String strGroup2 = matcher.group(4);
                    strGroup2.getClass();
                    i = Integer.parseInt(strGroup2, 10);
                }
                String strGroup3 = matcher.group(1);
                strGroup3.getClass();
                int i3 = Integer.parseInt(strGroup3, 10);
                String strGroup4 = matcher.group(2);
                strGroup4.getClass();
                int i4 = Integer.parseInt(strGroup4, 10);
                String strGroup5 = matcher.group(3);
                strGroup5.getClass();
                return Color.argb(i, i3, i4, Integer.parseInt(strGroup5, 10));
            }
        } else if (strReplace.startsWith("rgb")) {
            Matcher matcher2 = RGB_PATTERN.matcher(strReplace);
            if (matcher2.matches()) {
                String strGroup6 = matcher2.group(1);
                strGroup6.getClass();
                int i5 = Integer.parseInt(strGroup6, 10);
                String strGroup7 = matcher2.group(2);
                strGroup7.getClass();
                int i6 = Integer.parseInt(strGroup7, 10);
                String strGroup8 = matcher2.group(3);
                strGroup8.getClass();
                return Color.rgb(i5, i6, Integer.parseInt(strGroup8, 10));
            }
        } else {
            Integer num = COLOR_MAP.get(Ascii.toLowerCase(strReplace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    @ColorInt
    public static int parseCssColor(String str) {
        return parseColorInternal(str, true);
    }

    @ColorInt
    public static int parseTtmlColor(String str) {
        return parseColorInternal(str, false);
    }
}
