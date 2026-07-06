package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.media3.common.util.ColorParser;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WebvttCssParser {
    public static final String PROPERTY_BGCOLOR = "background-color";
    public static final String PROPERTY_COLOR = "color";
    public static final String PROPERTY_FONT_FAMILY = "font-family";
    public static final String PROPERTY_FONT_SIZE = "font-size";
    public static final String PROPERTY_FONT_STYLE = "font-style";
    public static final String PROPERTY_FONT_WEIGHT = "font-weight";
    public static final String PROPERTY_RUBY_POSITION = "ruby-position";
    public static final String PROPERTY_TEXT_COMBINE_UPRIGHT = "text-combine-upright";
    public static final String PROPERTY_TEXT_DECORATION = "text-decoration";
    public static final String RULE_END = "}";
    public static final String RULE_START = "{";
    public static final String TAG = "WebvttCssParser";
    public static final String VALUE_ALL = "all";
    public static final String VALUE_BOLD = "bold";
    public static final String VALUE_DIGITS = "digits";
    public static final String VALUE_ITALIC = "italic";
    public static final String VALUE_OVER = "over";
    public static final String VALUE_UNDER = "under";
    public static final String VALUE_UNDERLINE = "underline";
    public static final Pattern VOICE_NAME_PATTERN = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    public static final Pattern FONT_SIZE_PATTERN = Pattern.compile("^((?:[0-9]*\\.)?[0-9]+)(px|em|%)$");
    public final ParsableByteArray styleInput = new ParsableByteArray();
    public final StringBuilder stringBuilder = new StringBuilder();

    public static boolean maybeSkipComment(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        byte[] bArr = parsableByteArray.data;
        if (i + 2 > i2) {
            return false;
        }
        int i3 = i + 1;
        if (bArr[i] != 47) {
            return false;
        }
        int i4 = i + 2;
        if (bArr[i3] != 42) {
            return false;
        }
        while (true) {
            int i5 = i4 + 1;
            if (i5 >= i2) {
                parsableByteArray.skipBytes(i2 - parsableByteArray.position);
                return true;
            }
            if (((char) bArr[i4]) == '*' && ((char) bArr[i5]) == '/') {
                i4 += 2;
                i2 = i4;
            } else {
                i4 = i5;
            }
        }
    }

    public static boolean maybeSkipWhitespace(ParsableByteArray parsableByteArray) {
        char c = (char) parsableByteArray.data[parsableByteArray.position];
        if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
            return false;
        }
        parsableByteArray.skipBytes(1);
        return true;
    }

    public static void parseFontSize(String str, WebvttCssStyle webvttCssStyle) {
        Matcher matcher = FONT_SIZE_PATTERN.matcher(Ascii.toLowerCase(str));
        if (!matcher.matches()) {
            Log.w("WebvttCssParser", "Invalid font-size: '" + str + "'.");
            return;
        }
        String strGroup = matcher.group(2);
        strGroup.getClass();
        switch (strGroup) {
            case "%":
                webvttCssStyle.fontSizeUnit = 3;
                break;
            case "em":
                webvttCssStyle.fontSizeUnit = 2;
                break;
            case "px":
                webvttCssStyle.fontSizeUnit = 1;
                break;
            default:
                throw new IllegalStateException();
        }
        String strGroup2 = matcher.group(1);
        strGroup2.getClass();
        webvttCssStyle.fontSize = Float.parseFloat(strGroup2);
    }

    public static String parseIdentifier(ParsableByteArray parsableByteArray, StringBuilder sb) {
        boolean z = false;
        sb.setLength(0);
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        while (i < i2 && !z) {
            char c = (char) parsableByteArray.data[i];
            if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !((c >= '0' && c <= '9') || c == '#' || c == '-' || c == '.' || c == '_'))) {
                z = true;
            } else {
                i++;
                sb.append(c);
            }
        }
        parsableByteArray.skipBytes(i - parsableByteArray.position);
        return sb.toString();
    }

    @Nullable
    public static String parseNextToken(ParsableByteArray parsableByteArray, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() == 0) {
            return null;
        }
        String identifier = parseIdentifier(parsableByteArray, sb);
        if (!"".equals(identifier)) {
            return identifier;
        }
        return "" + ((char) parsableByteArray.readUnsignedByte());
    }

    @Nullable
    public static String parsePropertyValue(ParsableByteArray parsableByteArray, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int i = parsableByteArray.position;
            String nextToken = parseNextToken(parsableByteArray, sb);
            if (nextToken == null) {
                return null;
            }
            if ("}".equals(nextToken) || ";".equals(nextToken)) {
                parsableByteArray.setPosition(i);
                z = true;
            } else {
                sb2.append(nextToken);
            }
        }
        return sb2.toString();
    }

    @Nullable
    public static String parseSelector(ParsableByteArray parsableByteArray, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() < 5 || !"::cue".equals(parsableByteArray.readString(5, Charsets.UTF_8))) {
            return null;
        }
        int i = parsableByteArray.position;
        String nextToken = parseNextToken(parsableByteArray, sb);
        if (nextToken == null) {
            return null;
        }
        if ("{".equals(nextToken)) {
            parsableByteArray.setPosition(i);
            return "";
        }
        String cueTarget = "(".equals(nextToken) ? readCueTarget(parsableByteArray) : null;
        if (")".equals(parseNextToken(parsableByteArray, sb))) {
            return cueTarget;
        }
        return null;
    }

    public static void parseStyleDeclaration(ParsableByteArray parsableByteArray, WebvttCssStyle webvttCssStyle, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        String identifier = parseIdentifier(parsableByteArray, sb);
        if (!"".equals(identifier) && ":".equals(parseNextToken(parsableByteArray, sb))) {
            skipWhitespaceAndComments(parsableByteArray);
            String propertyValue = parsePropertyValue(parsableByteArray, sb);
            if (propertyValue == null || "".equals(propertyValue)) {
                return;
            }
            int i = parsableByteArray.position;
            String nextToken = parseNextToken(parsableByteArray, sb);
            if (!";".equals(nextToken)) {
                if (!"}".equals(nextToken)) {
                    return;
                } else {
                    parsableByteArray.setPosition(i);
                }
            }
            if ("color".equals(identifier)) {
                webvttCssStyle.setFontColor(ColorParser.parseCssColor(propertyValue));
                return;
            }
            if ("background-color".equals(identifier)) {
                webvttCssStyle.setBackgroundColor(ColorParser.parseCssColor(propertyValue));
                return;
            }
            boolean z = true;
            if ("ruby-position".equals(identifier)) {
                if ("over".equals(propertyValue)) {
                    webvttCssStyle.rubyPosition = 1;
                    return;
                } else {
                    if ("under".equals(propertyValue)) {
                        webvttCssStyle.rubyPosition = 2;
                        return;
                    }
                    return;
                }
            }
            if ("text-combine-upright".equals(identifier)) {
                if (!"all".equals(propertyValue) && !propertyValue.startsWith("digits")) {
                    z = false;
                }
                webvttCssStyle.combineUpright = z;
                return;
            }
            if ("text-decoration".equals(identifier)) {
                if ("underline".equals(propertyValue)) {
                    webvttCssStyle.underline = 1;
                    return;
                }
                return;
            }
            if ("font-family".equals(identifier)) {
                webvttCssStyle.setFontFamily(propertyValue);
                return;
            }
            if ("font-weight".equals(identifier)) {
                if ("bold".equals(propertyValue)) {
                    webvttCssStyle.bold = 1;
                }
            } else if ("font-style".equals(identifier)) {
                if ("italic".equals(propertyValue)) {
                    webvttCssStyle.italic = 1;
                }
            } else if ("font-size".equals(identifier)) {
                parseFontSize(propertyValue, webvttCssStyle);
            }
        }
    }

    public static char peekCharAtPosition(ParsableByteArray parsableByteArray, int i) {
        return (char) parsableByteArray.data[i];
    }

    public static String readCueTarget(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        boolean z = false;
        while (i < i2 && !z) {
            int i3 = i + 1;
            z = ((char) parsableByteArray.data[i]) == ')';
            i = i3;
        }
        return parsableByteArray.readString((i - 1) - parsableByteArray.position, Charsets.UTF_8).trim();
    }

    public static void skipStyleBlock(ParsableByteArray parsableByteArray) {
        do {
            parsableByteArray.getClass();
        } while (!TextUtils.isEmpty(parsableByteArray.readLine(Charsets.UTF_8)));
    }

    public static void skipWhitespaceAndComments(ParsableByteArray parsableByteArray) {
        while (true) {
            for (boolean z = true; parsableByteArray.bytesLeft() > 0 && z; z = false) {
                if (maybeSkipWhitespace(parsableByteArray) || maybeSkipComment(parsableByteArray)) {
                    break;
                }
            }
            return;
        }
    }

    public final void applySelectorToStyle(WebvttCssStyle webvttCssStyle, String str) {
        if ("".equals(str)) {
            return;
        }
        int iIndexOf = str.indexOf(91);
        if (iIndexOf != -1) {
            Matcher matcher = VOICE_NAME_PATTERN.matcher(str.substring(iIndexOf));
            if (matcher.matches()) {
                String strGroup = matcher.group(1);
                strGroup.getClass();
                webvttCssStyle.targetVoice = strGroup;
            }
            str = str.substring(0, iIndexOf);
        }
        String[] strArrSplit = Util.split(str, "\\.");
        String str2 = strArrSplit[0];
        int iIndexOf2 = str2.indexOf(35);
        if (iIndexOf2 != -1) {
            webvttCssStyle.targetTag = str2.substring(0, iIndexOf2);
            webvttCssStyle.targetId = str2.substring(iIndexOf2 + 1);
        } else {
            webvttCssStyle.targetTag = str2;
        }
        if (strArrSplit.length > 1) {
            webvttCssStyle.setTargetClasses((String[]) Util.nullSafeArrayCopyOfRange(strArrSplit, 1, strArrSplit.length));
        }
    }

    public List<WebvttCssStyle> parseBlock(ParsableByteArray parsableByteArray) {
        this.stringBuilder.setLength(0);
        int i = parsableByteArray.position;
        skipStyleBlock(parsableByteArray);
        this.styleInput.reset(parsableByteArray.data, parsableByteArray.position);
        this.styleInput.setPosition(i);
        ArrayList arrayList = new ArrayList();
        while (true) {
            String selector = parseSelector(this.styleInput, this.stringBuilder);
            if (selector == null || !"{".equals(parseNextToken(this.styleInput, this.stringBuilder))) {
                break;
            }
            WebvttCssStyle webvttCssStyle = new WebvttCssStyle();
            applySelectorToStyle(webvttCssStyle, selector);
            String nextToken = null;
            boolean z = false;
            while (!z) {
                ParsableByteArray parsableByteArray2 = this.styleInput;
                int i2 = parsableByteArray2.position;
                nextToken = parseNextToken(parsableByteArray2, this.stringBuilder);
                boolean z2 = nextToken == null || "}".equals(nextToken);
                if (!z2) {
                    this.styleInput.setPosition(i2);
                    parseStyleDeclaration(this.styleInput, webvttCssStyle, this.stringBuilder);
                }
                z = z2;
            }
            if ("}".equals(nextToken)) {
                arrayList.add(webvttCssStyle);
            }
        }
        return arrayList;
    }
}
