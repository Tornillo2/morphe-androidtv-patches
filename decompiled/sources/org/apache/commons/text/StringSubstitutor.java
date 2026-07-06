package org.apache.commons.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.lookup.InterpolatorStringLookup;
import org.apache.commons.text.lookup.MapStringLookup;
import org.apache.commons.text.lookup.StringLookup;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.apache.commons.text.lookup.SystemPropertyStringLookup;
import org.apache.commons.text.matcher.AbstractStringMatcher;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class StringSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StringMatcher DEFAULT_PREFIX;
    public static final StringMatcher DEFAULT_SUFFIX;
    public static final StringMatcher DEFAULT_VALUE_DELIMITER;
    public static final String DEFAULT_VAR_DEFAULT = ":-";
    public static final String DEFAULT_VAR_END = "}";
    public static final String DEFAULT_VAR_START = "${";
    public boolean disableSubstitutionInValues;
    public boolean enableSubstitutionInVariables;
    public boolean enableUndefinedVariableException;
    public char escapeChar;
    public StringMatcher prefixMatcher;
    public boolean preserveEscapes;
    public StringMatcher suffixMatcher;
    public StringMatcher valueDelimiterMatcher;
    public StringLookup variableResolver;

    static {
        StringMatcherFactory stringMatcherFactory = StringMatcherFactory.INSTANCE;
        DEFAULT_PREFIX = stringMatcherFactory.stringMatcher(DEFAULT_VAR_START);
        DEFAULT_SUFFIX = stringMatcherFactory.stringMatcher("}");
        DEFAULT_VALUE_DELIMITER = stringMatcherFactory.stringMatcher(DEFAULT_VAR_DEFAULT);
    }

    public StringSubstitutor() {
        this((StringLookup) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public static StringSubstitutor createInterpolator() {
        StringLookupFactory.INSTANCE.getClass();
        return new StringSubstitutor(InterpolatorStringLookup.INSTANCE);
    }

    public static <V> String replace(Object obj, Map<String, V> map) {
        return new StringSubstitutor(map).replace(obj);
    }

    public static String replaceSystemProperties(Object obj) {
        StringLookupFactory.INSTANCE.getClass();
        return new StringSubstitutor(SystemPropertyStringLookup.INSTANCE).replace(obj);
    }

    public final void checkCyclicSubstitution(String str, List<String> list) {
        if (list.contains(str)) {
            TextStringBuilder textStringBuilder = new TextStringBuilder(256);
            textStringBuilder.append("Infinite loop in property interpolation of ");
            textStringBuilder.append(list.remove(0));
            textStringBuilder.append(": ");
            textStringBuilder.appendWithSeparators(list, "->");
            throw new IllegalStateException(textStringBuilder.toString());
        }
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public StringLookup getStringLookup() {
        return this.variableResolver;
    }

    public StringMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StringMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StringMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public boolean isDisableSubstitutionInValues() {
        return this.disableSubstitutionInValues;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public boolean isEnableUndefinedVariableException() {
        return this.enableUndefinedVariableException;
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

    public String resolveVariable(String str, TextStringBuilder textStringBuilder, int i, int i2) {
        StringLookup stringLookup = getStringLookup();
        if (stringLookup == null) {
            return null;
        }
        return stringLookup.lookup(str);
    }

    public StringSubstitutor setDisableSubstitutionInValues(boolean z) {
        this.disableSubstitutionInValues = z;
        return this;
    }

    public StringSubstitutor setEnableSubstitutionInVariables(boolean z) {
        this.enableSubstitutionInVariables = z;
        return this;
    }

    public StringSubstitutor setEnableUndefinedVariableException(boolean z) {
        this.enableUndefinedVariableException = z;
        return this;
    }

    public StringSubstitutor setEscapeChar(char c) {
        this.escapeChar = c;
        return this;
    }

    public StringSubstitutor setPreserveEscapes(boolean z) {
        this.preserveEscapes = z;
        return this;
    }

    public StringSubstitutor setValueDelimiter(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setValueDelimiterMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringSubstitutor setValueDelimiterMatcher(StringMatcher stringMatcher) {
        this.valueDelimiterMatcher = stringMatcher;
        return this;
    }

    public StringSubstitutor setVariablePrefix(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setVariablePrefixMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringSubstitutor setVariablePrefixMatcher(StringMatcher stringMatcher) {
        Validate.isTrue(stringMatcher != null, "Variable prefix matcher must not be null!", new Object[0]);
        this.prefixMatcher = stringMatcher;
        return this;
    }

    public StringSubstitutor setVariableResolver(StringLookup stringLookup) {
        this.variableResolver = stringLookup;
        return this;
    }

    public StringSubstitutor setVariableSuffix(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setVariableSuffixMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringSubstitutor setVariableSuffixMatcher(StringMatcher stringMatcher) {
        Validate.isTrue(stringMatcher != null, "Variable suffix matcher must not be null!", new Object[0]);
        this.suffixMatcher = stringMatcher;
        return this;
    }

    public boolean substitute(TextStringBuilder textStringBuilder, int i, int i2) {
        return substitute(textStringBuilder, i, i2, null) > 0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public <V> StringSubstitutor(Map<String, V> map) {
        this((StringLookup) new MapStringLookup(map), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
        StringLookupFactory.INSTANCE.getClass();
    }

    public static <V> String replace(Object obj, Map<String, V> map, String str, String str2) {
        return new StringSubstitutor(map, str, str2).replace(obj);
    }

    public boolean replaceIn(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer != null) {
            TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(stringBuffer, i, i2);
            if (substitute(textStringBuilderAppend, 0, i2)) {
                stringBuffer.replace(i, i2 + i, textStringBuilderAppend.toString());
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r14v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r29v0, types: [org.apache.commons.text.StringSubstitutor] */
    public final int substitute(TextStringBuilder textStringBuilder, int i, int i2, List<String> list) {
        StringMatcher stringMatcher;
        StringMatcher stringMatcher2;
        char c;
        boolean z;
        boolean z2;
        boolean z3;
        ?? r14;
        int iIsMatch;
        String strSubstring;
        StringMatcher variablePrefixMatcher = getVariablePrefixMatcher();
        StringMatcher variableSuffixMatcher = getVariableSuffixMatcher();
        char escapeChar = getEscapeChar();
        StringMatcher valueDelimiterMatcher = getValueDelimiterMatcher();
        boolean zIsEnableSubstitutionInVariables = isEnableSubstitutionInVariables();
        boolean zIsDisableSubstitutionInValues = isDisableSubstitutionInValues();
        boolean zIsEnableUndefinedVariableException = isEnableUndefinedVariableException();
        boolean z4 = list == null;
        int i3 = i;
        int i4 = i + i2;
        int i5 = 0;
        int i6 = 0;
        char[] cArr = textStringBuilder.buffer;
        ?? arrayList = list;
        while (i3 < i4) {
            int iIsMatch2 = variablePrefixMatcher.isMatch(cArr, i3, i, i4);
            if (iIsMatch2 == 0) {
                i3++;
                stringMatcher = variablePrefixMatcher;
                stringMatcher2 = variableSuffixMatcher;
                c = escapeChar;
                z = zIsEnableSubstitutionInVariables;
                z2 = zIsDisableSubstitutionInValues;
                z3 = zIsEnableUndefinedVariableException;
                r14 = arrayList;
            } else {
                if (i3 > i) {
                    z = zIsEnableSubstitutionInVariables;
                    int i7 = i3 - 1;
                    z2 = zIsDisableSubstitutionInValues;
                    if (cArr[i7] == escapeChar) {
                        if (this.preserveEscapes) {
                            i3++;
                            zIsEnableSubstitutionInVariables = z;
                            zIsDisableSubstitutionInValues = z2;
                        } else {
                            textStringBuilder.deleteCharAt(i7);
                            i5--;
                            i4--;
                            stringMatcher = variablePrefixMatcher;
                            stringMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            cArr = textStringBuilder.buffer;
                            z3 = zIsEnableUndefinedVariableException;
                            i6 = 1;
                            arrayList = arrayList;
                            zIsEnableSubstitutionInVariables = z;
                            zIsDisableSubstitutionInValues = z2;
                            variableSuffixMatcher = stringMatcher2;
                            escapeChar = c;
                            zIsEnableUndefinedVariableException = z3;
                            variablePrefixMatcher = stringMatcher;
                        }
                    }
                } else {
                    z = zIsEnableSubstitutionInVariables;
                    z2 = zIsDisableSubstitutionInValues;
                }
                int i8 = i3 + iIsMatch2;
                int i9 = i8;
                int i10 = 0;
                while (i9 < i4) {
                    if (!z || variablePrefixMatcher.isMatch(cArr, i9, i, i4) == 0) {
                        iIsMatch = variableSuffixMatcher.isMatch(cArr, i9, i, i4);
                        if (iIsMatch == 0) {
                            i9++;
                        } else if (i10 == 0) {
                            stringMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            String str = new String(cArr, i8, (i9 - i3) - iIsMatch2);
                            if (z) {
                                TextStringBuilder textStringBuilder2 = new TextStringBuilder(str);
                                substitute(textStringBuilder2, 0, textStringBuilder2.size);
                                str = textStringBuilder2.toString();
                            }
                            int i11 = i9 + iIsMatch;
                            if (valueDelimiterMatcher != null) {
                                char[] charArray = str.toCharArray();
                                z3 = zIsEnableUndefinedVariableException;
                                int i12 = 0;
                                while (i12 < charArray.length && (z || variablePrefixMatcher.isMatch(charArray, i12, i12, charArray.length) == 0)) {
                                    stringMatcher = variablePrefixMatcher;
                                    if (valueDelimiterMatcher.isMatch(charArray, i12, 0, charArray.length) != 0) {
                                        int iIsMatch3 = valueDelimiterMatcher.isMatch(charArray, i12, 0, charArray.length);
                                        String strSubstring2 = str.substring(0, i12);
                                        strSubstring = str.substring(i12 + iIsMatch3);
                                        str = strSubstring2;
                                        break;
                                    }
                                    i12++;
                                    variablePrefixMatcher = stringMatcher;
                                }
                                stringMatcher = variablePrefixMatcher;
                            } else {
                                stringMatcher = variablePrefixMatcher;
                                z3 = zIsEnableUndefinedVariableException;
                            }
                            strSubstring = null;
                            if (arrayList == 0) {
                                arrayList = new ArrayList();
                                arrayList.add(new String(cArr, i, i2));
                            }
                            checkCyclicSubstitution(str, arrayList);
                            arrayList.add(str);
                            String strResolveVariable = resolveVariable(str, textStringBuilder, i3, i11);
                            if (strResolveVariable != null) {
                                strSubstring = strResolveVariable;
                            }
                            if (strSubstring != null) {
                                int length = strSubstring.length();
                                textStringBuilder.replace(i3, i11, strSubstring);
                                int iSubstitute = ((!z2 ? substitute(textStringBuilder, i3, length, arrayList) : 0) + length) - (i11 - i3);
                                i11 += iSubstitute;
                                i4 += iSubstitute;
                                i5 += iSubstitute;
                                cArr = textStringBuilder.buffer;
                                i6 = 1;
                            } else if (z3) {
                                throw new IllegalArgumentException(String.format("Cannot resolve variable '%s' (enableSubstitutionInVariables=%s).", str, Boolean.valueOf(this.enableSubstitutionInVariables)));
                            }
                            i3 = i11;
                            arrayList.remove(arrayList.size() - 1);
                            r14 = arrayList;
                        } else {
                            i10--;
                        }
                    } else {
                        iIsMatch = variablePrefixMatcher.isMatch(cArr, i9, i, i4);
                        i10++;
                    }
                    i9 += iIsMatch;
                }
                stringMatcher = variablePrefixMatcher;
                stringMatcher2 = variableSuffixMatcher;
                c = escapeChar;
                z3 = zIsEnableUndefinedVariableException;
                i3 = i9;
                arrayList = arrayList;
                zIsEnableSubstitutionInVariables = z;
                zIsDisableSubstitutionInValues = z2;
                variableSuffixMatcher = stringMatcher2;
                escapeChar = c;
                zIsEnableUndefinedVariableException = z3;
                variablePrefixMatcher = stringMatcher;
            }
            arrayList = r14;
            zIsEnableSubstitutionInVariables = z;
            zIsDisableSubstitutionInValues = z2;
            variableSuffixMatcher = stringMatcher2;
            escapeChar = c;
            zIsEnableUndefinedVariableException = z3;
            variablePrefixMatcher = stringMatcher;
        }
        return z4 ? i6 : i5;
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

    public StringSubstitutor setValueDelimiter(String str) {
        if (str != null && str.length() != 0) {
            return setValueDelimiterMatcher(StringMatcherFactory.INSTANCE.stringMatcher(str));
        }
        setValueDelimiterMatcher(null);
        return this;
    }

    public StringSubstitutor setVariablePrefix(String str) {
        Validate.isTrue(str != null, "Variable prefix must not be null!", new Object[0]);
        return setVariablePrefixMatcher(StringMatcherFactory.INSTANCE.stringMatcher(str));
    }

    public StringSubstitutor setVariableSuffix(String str) {
        Validate.isTrue(str != null, "Variable suffix must not be null!", new Object[0]);
        return setVariableSuffixMatcher(StringMatcherFactory.INSTANCE.stringMatcher(str));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public <V> StringSubstitutor(Map<String, V> map, String str, String str2) {
        this((StringLookup) new MapStringLookup(map), str, str2, '$');
        StringLookupFactory.INSTANCE.getClass();
    }

    public boolean replaceIn(StringBuilder sb) {
        if (sb == null) {
            return false;
        }
        return replaceIn(sb, 0, sb.length());
    }

    public boolean replaceIn(StringBuilder sb, int i, int i2) {
        if (sb != null) {
            TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(sb, i, i2);
            if (substitute(textStringBuilderAppend, 0, i2)) {
                sb.replace(i, i2 + i, textStringBuilderAppend.toString());
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public <V> StringSubstitutor(Map<String, V> map, String str, String str2, char c) {
        this(new MapStringLookup(map), str, str2, c);
        StringLookupFactory.INSTANCE.getClass();
    }

    public boolean replaceIn(TextStringBuilder textStringBuilder) {
        if (textStringBuilder == null) {
            return false;
        }
        return substitute(textStringBuilder, 0, textStringBuilder.length());
    }

    public boolean replaceIn(TextStringBuilder textStringBuilder, int i, int i2) {
        if (textStringBuilder == null) {
            return false;
        }
        return substitute(textStringBuilder, i, i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public <V> StringSubstitutor(Map<String, V> map, String str, String str2, char c, String str3) {
        this(new MapStringLookup(map), str, str2, c, str3);
        StringLookupFactory.INSTANCE.getClass();
    }

    public String replace(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(cArr.length).append(cArr);
        substitute(textStringBuilderAppend, 0, cArr.length);
        return textStringBuilderAppend.toString();
    }

    public StringSubstitutor(StringLookup stringLookup) {
        this(stringLookup, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public String replace(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(cArr, i, i2);
        substitute(textStringBuilderAppend, 0, i2);
        return textStringBuilderAppend.toString();
    }

    public StringSubstitutor(StringLookup stringLookup, String str, String str2, char c) {
        setVariableResolver(stringLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
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
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(charSequence.toString(), i, i2);
        substitute(textStringBuilderAppend, 0, i2);
        return textStringBuilderAppend.toString();
    }

    public StringSubstitutor(StringLookup stringLookup, String str, String str2, char c, String str3) {
        setVariableResolver(stringLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiter(str3);
    }

    public String replace(Object obj) {
        if (obj == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder().append(obj);
        substitute(textStringBuilderAppend, 0, textStringBuilderAppend.length());
        return textStringBuilderAppend.toString();
    }

    public String replace(String str) {
        if (str == null) {
            return null;
        }
        TextStringBuilder textStringBuilder = new TextStringBuilder(str);
        return !substitute(textStringBuilder, 0, str.length()) ? str : textStringBuilder.toString();
    }

    public StringSubstitutor(StringLookup stringLookup, StringMatcher stringMatcher, StringMatcher stringMatcher2, char c) {
        this(stringLookup, stringMatcher, stringMatcher2, c, DEFAULT_VALUE_DELIMITER);
    }

    public String replace(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(str, i, i2);
        if (!substitute(textStringBuilderAppend, 0, i2)) {
            return str.substring(i, i2 + i);
        }
        return textStringBuilderAppend.toString();
    }

    public StringSubstitutor(StringLookup stringLookup, StringMatcher stringMatcher, StringMatcher stringMatcher2, char c, StringMatcher stringMatcher3) {
        setVariableResolver(stringLookup);
        setVariablePrefixMatcher(stringMatcher);
        setVariableSuffixMatcher(stringMatcher2);
        setEscapeChar(c);
        setValueDelimiterMatcher(stringMatcher3);
    }

    public String replace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(stringBuffer.length()).append(stringBuffer);
        substitute(textStringBuilderAppend, 0, textStringBuilderAppend.length());
        return textStringBuilderAppend.toString();
    }

    public String replace(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(stringBuffer, i, i2);
        substitute(textStringBuilderAppend, 0, i2);
        return textStringBuilderAppend.toString();
    }

    public String replace(TextStringBuilder textStringBuilder) {
        if (textStringBuilder == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(textStringBuilder.length()).append(textStringBuilder);
        substitute(textStringBuilderAppend, 0, textStringBuilderAppend.length());
        return textStringBuilderAppend.toString();
    }

    public String replace(TextStringBuilder textStringBuilder, int i, int i2) {
        if (textStringBuilder == null) {
            return null;
        }
        TextStringBuilder textStringBuilderAppend = new TextStringBuilder(i2).append(textStringBuilder, i, i2);
        substitute(textStringBuilderAppend, 0, i2);
        return textStringBuilderAppend.toString();
    }
}
