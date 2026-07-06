package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.text.StringSubstitutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher(StringSubstitutor.DEFAULT_VAR_START);
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
    public static final StrMatcher DEFAULT_VALUE_DELIMITER = StrMatcher.stringMatcher(StringSubstitutor.DEFAULT_VAR_DEFAULT);
    public boolean enableSubstitutionInVariables;
    public char escapeChar;
    public StrMatcher prefixMatcher;
    public boolean preserveEscapes;
    public StrMatcher suffixMatcher;
    public StrMatcher valueDelimiterMatcher;
    public StrLookup<?> variableResolver;

    public <V> StrSubstitutor(Map<String, V> map) {
        this((StrLookup<?>) new StrLookup.MapStrLookup(map), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public static <V> String replace(Object obj, Map<String, V> map) {
        return new StrSubstitutor(map).replace(obj);
    }

    public static String replaceSystemProperties(Object obj) {
        return new StrSubstitutor(StrLookup.SYSTEM_PROPERTIES_LOOKUP).replace(obj);
    }

    public final void checkCyclicSubstitution(String str, List<String> list) {
        if (list.contains(str)) {
            StrBuilder strBuilder = new StrBuilder(256);
            strBuilder.append("Infinite loop in property interpolation of ");
            strBuilder.append(list.remove(0));
            strBuilder.append(": ");
            strBuilder.appendWithSeparators(list, "->");
            throw new IllegalStateException(strBuilder.toString());
        }
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public StrMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrLookup<?> getVariableResolver() {
        return this.variableResolver;
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public boolean isPreserveEscapes() {
        return this.preserveEscapes;
    }

    public boolean replaceIn(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return false;
        }
        return replaceIn(stringBuffer, 0, stringBuffer.length());
    }

    public String resolveVariable(String str, StrBuilder strBuilder, int i, int i2) {
        StrLookup<?> variableResolver = getVariableResolver();
        if (variableResolver == null) {
            return null;
        }
        return variableResolver.lookup(str);
    }

    public void setEnableSubstitutionInVariables(boolean z) {
        this.enableSubstitutionInVariables = z;
    }

    public void setEscapeChar(char c) {
        this.escapeChar = c;
    }

    public void setPreserveEscapes(boolean z) {
        this.preserveEscapes = z;
    }

    public StrSubstitutor setValueDelimiter(char c) {
        return setValueDelimiterMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setValueDelimiterMatcher(StrMatcher strMatcher) {
        this.valueDelimiterMatcher = strMatcher;
        return this;
    }

    public StrSubstitutor setVariablePrefix(char c) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable prefix matcher must not be null!");
        }
        this.prefixMatcher = strMatcher;
        return this;
    }

    public void setVariableResolver(StrLookup<?> strLookup) {
        this.variableResolver = strLookup;
    }

    public StrSubstitutor setVariableSuffix(char c) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable suffix matcher must not be null!");
        }
        this.suffixMatcher = strMatcher;
        return this;
    }

    public boolean substitute(StrBuilder strBuilder, int i, int i2) {
        return substitute(strBuilder, i, i2, null) > 0;
    }

    public static <V> String replace(Object obj, Map<String, V> map, String str, String str2) {
        return new StrSubstitutor(map, str, str2).replace(obj);
    }

    public boolean replaceIn(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer != null) {
            StrBuilder strBuilderAppend = new StrBuilder(i2).append(stringBuffer, i, i2);
            if (substitute(strBuilderAppend, 0, i2)) {
                stringBuffer.replace(i, i2 + i, strBuilderAppend.toString());
                return true;
            }
        }
        return false;
    }

    public StrSubstitutor setValueDelimiter(String str) {
        if (!StringUtils.isEmpty(str)) {
            return setValueDelimiterMatcher(StrMatcher.stringMatcher(str));
        }
        setValueDelimiterMatcher(null);
        return this;
    }

    public StrSubstitutor setVariablePrefix(String str) {
        if (str != null) {
            return setVariablePrefixMatcher(StrMatcher.stringMatcher(str));
        }
        throw new IllegalArgumentException("Variable prefix must not be null!");
    }

    public StrSubstitutor setVariableSuffix(String str) {
        if (str != null) {
            return setVariableSuffixMatcher(StrMatcher.stringMatcher(str));
        }
        throw new IllegalArgumentException("Variable suffix must not be null!");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r27v0, types: [org.apache.commons.lang3.text.StrSubstitutor] */
    public final int substitute(StrBuilder strBuilder, int i, int i2, List<String> list) {
        StrMatcher strMatcher;
        StrMatcher strMatcher2;
        char c;
        boolean z;
        String strSubstring;
        int iIsMatch;
        StrMatcher variablePrefixMatcher = getVariablePrefixMatcher();
        StrMatcher variableSuffixMatcher = getVariableSuffixMatcher();
        char escapeChar = getEscapeChar();
        StrMatcher valueDelimiterMatcher = getValueDelimiterMatcher();
        boolean zIsEnableSubstitutionInVariables = isEnableSubstitutionInVariables();
        boolean z2 = list == null;
        int i3 = i;
        int i4 = i + i2;
        int i5 = 0;
        int i6 = 0;
        char[] cArr = strBuilder.buffer;
        ?? arrayList = list;
        while (i3 < i4) {
            int iIsMatch2 = variablePrefixMatcher.isMatch(cArr, i3, i, i4);
            if (iIsMatch2 != 0) {
                if (i3 > i) {
                    int i7 = i3 - 1;
                    if (cArr[i7] == escapeChar) {
                        if (this.preserveEscapes) {
                            i3++;
                        } else {
                            strBuilder.deleteCharAt(i7);
                            i5--;
                            i4--;
                            strMatcher = variablePrefixMatcher;
                            strMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            z = zIsEnableSubstitutionInVariables;
                            cArr = strBuilder.buffer;
                            i6 = 1;
                            arrayList = arrayList;
                        }
                    }
                }
                int i8 = i3 + iIsMatch2;
                int i9 = i8;
                int i10 = 0;
                while (true) {
                    if (i9 >= i4) {
                        strMatcher = variablePrefixMatcher;
                        strMatcher2 = variableSuffixMatcher;
                        c = escapeChar;
                        z = zIsEnableSubstitutionInVariables;
                        i3 = i9;
                        arrayList = arrayList;
                        break;
                    }
                    if (!zIsEnableSubstitutionInVariables || (iIsMatch = variablePrefixMatcher.isMatch(cArr, i9, i, i4)) == 0) {
                        int iIsMatch3 = variableSuffixMatcher.isMatch(cArr, i9, i, i4);
                        if (iIsMatch3 == 0) {
                            i9++;
                        } else if (i10 == 0) {
                            strMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            String str = new String(cArr, i8, (i9 - i3) - iIsMatch2);
                            if (zIsEnableSubstitutionInVariables) {
                                StrBuilder strBuilder2 = new StrBuilder(str);
                                substitute(strBuilder2, 0, strBuilder2.size);
                                str = strBuilder2.toString();
                            }
                            int i11 = i9 + iIsMatch3;
                            if (valueDelimiterMatcher != null) {
                                char[] charArray = str.toCharArray();
                                z = zIsEnableSubstitutionInVariables;
                                int i12 = 0;
                                while (i12 < charArray.length && (z || variablePrefixMatcher.isMatch(charArray, i12, i12, charArray.length) == 0)) {
                                    int iIsMatch4 = valueDelimiterMatcher.isMatch(charArray, i12);
                                    if (iIsMatch4 != 0) {
                                        strMatcher = variablePrefixMatcher;
                                        String strSubstring2 = str.substring(0, i12);
                                        strSubstring = str.substring(i12 + iIsMatch4);
                                        str = strSubstring2;
                                        break;
                                    }
                                    i12++;
                                    variablePrefixMatcher = variablePrefixMatcher;
                                }
                                strMatcher = variablePrefixMatcher;
                            } else {
                                strMatcher = variablePrefixMatcher;
                                z = zIsEnableSubstitutionInVariables;
                            }
                            strSubstring = null;
                            if (arrayList == 0) {
                                arrayList = new ArrayList();
                                arrayList.add(new String(cArr, i, i2));
                            }
                            checkCyclicSubstitution(str, arrayList);
                            arrayList.add(str);
                            String strResolveVariable = resolveVariable(str, strBuilder, i3, i11);
                            if (strResolveVariable != null) {
                                strSubstring = strResolveVariable;
                            }
                            if (strSubstring != null) {
                                int length = strSubstring.length();
                                strBuilder.replace(i3, i11, strSubstring);
                                int iSubstitute = (substitute(strBuilder, i3, length, arrayList) + length) - (i11 - i3);
                                i11 += iSubstitute;
                                i4 += iSubstitute;
                                i5 += iSubstitute;
                                cArr = strBuilder.buffer;
                                i6 = 1;
                            }
                            i3 = i11;
                            arrayList.remove(arrayList.size() - 1);
                            arrayList = arrayList;
                        } else {
                            i10--;
                            i9 += iIsMatch3;
                            variablePrefixMatcher = variablePrefixMatcher;
                        }
                    } else {
                        i10++;
                        i9 += iIsMatch;
                    }
                }
            } else {
                i3++;
                strMatcher = variablePrefixMatcher;
                strMatcher2 = variableSuffixMatcher;
                c = escapeChar;
                z = zIsEnableSubstitutionInVariables;
                arrayList = arrayList;
            }
            variableSuffixMatcher = strMatcher2;
            escapeChar = c;
            zIsEnableSubstitutionInVariables = z;
            variablePrefixMatcher = strMatcher;
        }
        return z2 ? i6 : i5;
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2) {
        this((StrLookup<?>) new StrLookup.MapStrLookup(map), str, str2, '$');
    }

    public static String replace(Object obj, Properties properties) {
        if (properties == null) {
            return obj.toString();
        }
        HashMap map = new HashMap();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            map.put(str, properties.getProperty(str));
        }
        return replace(obj, map);
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c) {
        this(new StrLookup.MapStrLookup(map), str, str2, c);
    }

    public boolean replaceIn(StringBuilder sb) {
        if (sb == null) {
            return false;
        }
        return replaceIn(sb, 0, sb.length());
    }

    public boolean replaceIn(StringBuilder sb, int i, int i2) {
        if (sb != null) {
            StrBuilder strBuilderAppend = new StrBuilder(i2).append(sb, i, i2);
            if (substitute(strBuilderAppend, 0, i2)) {
                sb.replace(i, i2 + i, strBuilderAppend.toString());
                return true;
            }
        }
        return false;
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c, String str3) {
        this(new StrLookup.MapStrLookup(map), str, str2, c, str3);
    }

    public StrSubstitutor() {
        this((StrLookup<?>) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public boolean replaceIn(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, 0, strBuilder.length());
    }

    public StrSubstitutor(StrLookup<?> strLookup) {
        this(strLookup, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public boolean replaceIn(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, i, i2);
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
    }

    public String replace(String str) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilder = new StrBuilder(str);
        return !substitute(strBuilder, 0, str.length()) ? str : strBuilder.toString();
    }

    public String replace(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(str, i, i2);
        if (!substitute(strBuilderAppend, 0, i2)) {
            return str.substring(i, i2 + i);
        }
        return strBuilderAppend.toString();
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c, String str3) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiter(str3);
    }

    public String replace(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(cArr.length).append(cArr);
        substitute(strBuilderAppend, 0, cArr.length);
        return strBuilderAppend.toString();
    }

    public String replace(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(cArr, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(stringBuffer.length()).append(stringBuffer);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c) {
        this(strLookup, strMatcher, strMatcher2, c, DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c, StrMatcher strMatcher3) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefixMatcher(strMatcher);
        setVariableSuffixMatcher(strMatcher2);
        setEscapeChar(c);
        setValueDelimiterMatcher(strMatcher3);
    }

    public String replace(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(stringBuffer, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return replace(charSequence, 0, charSequence.length());
    }

    public String replace(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(charSequence, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(strBuilder.length()).append(strBuilder);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }

    public String replace(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder(i2).append(strBuilder, i, i2);
        substitute(strBuilderAppend, 0, i2);
        return strBuilderAppend.toString();
    }

    public String replace(Object obj) {
        if (obj == null) {
            return null;
        }
        StrBuilder strBuilderAppend = new StrBuilder().append(obj);
        substitute(strBuilderAppend, 0, strBuilderAppend.length());
        return strBuilderAppend.toString();
    }
}
