package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class StrTokenizer implements ListIterator<String>, Cloneable {
    public static final StrTokenizer CSV_TOKENIZER_PROTOTYPE;
    public static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
    public char[] chars;
    public StrMatcher delimMatcher;
    public boolean emptyAsNull;
    public boolean ignoreEmptyTokens;
    public StrMatcher ignoredMatcher;
    public StrMatcher quoteMatcher;
    public int tokenPos;
    public String[] tokens;
    public StrMatcher trimmerMatcher;

    static {
        StrTokenizer strTokenizer = new StrTokenizer();
        CSV_TOKENIZER_PROTOTYPE = strTokenizer;
        strTokenizer.setDelimiterMatcher(StrMatcher.COMMA_MATCHER);
        StrMatcher strMatcher = StrMatcher.DOUBLE_QUOTE_MATCHER;
        if (strMatcher != null) {
            strTokenizer.quoteMatcher = strMatcher;
        }
        StrMatcher strMatcher2 = StrMatcher.NONE_MATCHER;
        if (strMatcher2 != null) {
            strTokenizer.ignoredMatcher = strMatcher2;
        }
        StrMatcher strMatcher3 = StrMatcher.TRIM_MATCHER;
        if (strMatcher3 != null) {
            strTokenizer.trimmerMatcher = strMatcher3;
        }
        strTokenizer.emptyAsNull = false;
        strTokenizer.ignoreEmptyTokens = false;
        StrTokenizer strTokenizer2 = new StrTokenizer();
        TSV_TOKENIZER_PROTOTYPE = strTokenizer2;
        strTokenizer2.setDelimiterMatcher(StrMatcher.TAB_MATCHER);
        if (strMatcher != null) {
            strTokenizer2.quoteMatcher = strMatcher;
        }
        if (strMatcher2 != null) {
            strTokenizer2.ignoredMatcher = strMatcher2;
        }
        if (strMatcher3 != null) {
            strTokenizer2.trimmerMatcher = strMatcher3;
        }
        strTokenizer2.emptyAsNull = false;
        strTokenizer2.ignoreEmptyTokens = false;
    }

    public StrTokenizer() {
        this.delimMatcher = StrMatcher.splitMatcher();
        StrMatcher strMatcher = StrMatcher.NONE_MATCHER;
        this.quoteMatcher = strMatcher;
        this.ignoredMatcher = strMatcher;
        this.trimmerMatcher = strMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = null;
    }

    public static StrTokenizer getCSVClone() {
        return (StrTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return getCSVClone();
    }

    public static StrTokenizer getTSVClone() {
        return (StrTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getTSVInstance() {
        return getTSVClone();
    }

    @Override // java.util.ListIterator
    public /* bridge */ /* synthetic */ void add(String str) {
        add2(str);
        throw null;
    }

    public final void addToken(List<String> list, String str) {
        if (StringUtils.isEmpty(str)) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                str = null;
            }
        }
        list.add(str);
    }

    public final void checkTokenized() {
        if (this.tokens == null) {
            char[] cArr = this.chars;
            if (cArr == null) {
                List<String> list = tokenize(null, 0, 0);
                this.tokens = (String[]) list.toArray(new String[list.size()]);
            } else {
                List<String> list2 = tokenize(cArr, 0, cArr.length);
                this.tokens = (String[]) list2.toArray(new String[list2.size()]);
            }
        }
    }

    public Object clone() {
        try {
            return cloneReset();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Object cloneReset() throws CloneNotSupportedException {
        StrTokenizer strTokenizer = (StrTokenizer) super.clone();
        char[] cArr = strTokenizer.chars;
        if (cArr != null) {
            strTokenizer.chars = (char[]) cArr.clone();
        }
        strTokenizer.reset();
        return strTokenizer;
    }

    public String getContent() {
        char[] cArr = this.chars;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StrMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) this.tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        ArrayList arrayList = new ArrayList(this.tokens.length);
        arrayList.addAll(Arrays.asList(this.tokens));
        return arrayList;
    }

    public StrMatcher getTrimmerMatcher() {
        return this.trimmerMatcher;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        checkTokenized();
        return this.tokenPos < this.tokens.length;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        checkTokenized();
        return this.tokenPos > 0;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public boolean isIgnoreEmptyTokens() {
        return this.ignoreEmptyTokens;
    }

    public final boolean isQuote(char[] cArr, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            if (i6 >= i2 || cArr[i6] != cArr[i3 + i5]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.tokenPos;
    }

    public String nextToken() {
        if (!hasNext()) {
            return null;
        }
        String[] strArr = this.tokens;
        int i = this.tokenPos;
        this.tokenPos = i + 1;
        return strArr[i];
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.tokenPos - 1;
    }

    public String previousToken() {
        if (!hasPrevious()) {
            return null;
        }
        String[] strArr = this.tokens;
        int i = this.tokenPos - 1;
        this.tokenPos = i;
        return strArr[i];
    }

    public final int readNextToken(char[] cArr, int i, int i2, StrBuilder strBuilder, List<String> list) {
        int i3 = i;
        while (i3 < i2) {
            int iMax = Math.max(getIgnoredMatcher().isMatch(cArr, i3, i3, i2), getTrimmerMatcher().isMatch(cArr, i3, i3, i2));
            if (iMax == 0 || getDelimiterMatcher().isMatch(cArr, i3, i3, i2) > 0 || getQuoteMatcher().isMatch(cArr, i3, i3, i2) > 0) {
                break;
            }
            i3 += iMax;
        }
        if (i3 >= i2) {
            addToken(list, "");
            return -1;
        }
        int iIsMatch = getDelimiterMatcher().isMatch(cArr, i3, i3, i2);
        if (iIsMatch > 0) {
            addToken(list, "");
            return i3 + iIsMatch;
        }
        int iIsMatch2 = getQuoteMatcher().isMatch(cArr, i3, i3, i2);
        if (iIsMatch2 <= 0) {
            return readWithQuotes(cArr, i3, i2, strBuilder, list, 0, 0);
        }
        int i4 = i3;
        return readWithQuotes(cArr, i4 + iIsMatch2, i2, strBuilder, list, i4, iIsMatch2);
    }

    public final int readWithQuotes(char[] cArr, int i, int i2, StrBuilder strBuilder, List<String> list, int i3, int i4) {
        int i5;
        int i6 = i2;
        strBuilder.clear();
        boolean z = i4 > 0;
        int i7 = i;
        int size = 0;
        while (i7 < i6) {
            if (z) {
                int i8 = i7;
                if (isQuote(cArr, i7, i6, i3, i4)) {
                    i7 = i8 + i4;
                    i6 = i2;
                    if (isQuote(cArr, i7, i6, i3, i4)) {
                        strBuilder.append(cArr, i8, i4);
                        i7 = (i4 * 2) + i8;
                        size = strBuilder.size();
                    } else {
                        z = false;
                    }
                } else {
                    i6 = i2;
                    i7 = i8 + 1;
                    strBuilder.append(cArr[i8]);
                    size = strBuilder.size();
                }
            } else {
                int i9 = i7;
                int iIsMatch = getDelimiterMatcher().isMatch(cArr, i9, i, i6);
                if (iIsMatch > 0) {
                    addToken(list, strBuilder.substring(0, size));
                    return iIsMatch + i9;
                }
                if (i4 > 0) {
                    i5 = i9;
                    if (isQuote(cArr, i5, i6, i3, i4)) {
                        i7 = i5 + i4;
                        z = true;
                    }
                } else {
                    i5 = i9;
                }
                int iIsMatch2 = getIgnoredMatcher().isMatch(cArr, i5, i, i6);
                if (iIsMatch2 <= 0) {
                    iIsMatch2 = getTrimmerMatcher().isMatch(cArr, i5, i, i6);
                    if (iIsMatch2 > 0) {
                        strBuilder.append(cArr, i5, iIsMatch2);
                    } else {
                        strBuilder.append(cArr[i5]);
                        size = strBuilder.size();
                        i7 = i5 + 1;
                    }
                }
                i7 = i5 + iIsMatch2;
            }
        }
        addToken(list, strBuilder.substring(0, size));
        return -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StrTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    @Override // java.util.ListIterator
    public /* bridge */ /* synthetic */ void set(String str) {
        set2(str);
        throw null;
    }

    public StrTokenizer setDelimiterChar(char c) {
        return setDelimiterMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            this.delimMatcher = StrMatcher.noneMatcher();
            return this;
        }
        this.delimMatcher = strMatcher;
        return this;
    }

    public StrTokenizer setDelimiterString(String str) {
        return setDelimiterMatcher(StrMatcher.stringMatcher(str));
    }

    public StrTokenizer setEmptyTokenAsNull(boolean z) {
        this.emptyAsNull = z;
        return this;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean z) {
        this.ignoreEmptyTokens = z;
        return this;
    }

    public StrTokenizer setIgnoredChar(char c) {
        return setIgnoredMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.ignoredMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setQuoteChar(char c) {
        return setQuoteMatcher(StrMatcher.charMatcher(c));
    }

    public StrTokenizer setQuoteMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.quoteMatcher = strMatcher;
        }
        return this;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.trimmerMatcher = strMatcher;
        }
        return this;
    }

    public int size() {
        checkTokenized();
        return this.tokens.length;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        return "StrTokenizer" + getTokenList();
    }

    public List<String> tokenize(char[] cArr, int i, int i2) {
        if (cArr == null || i2 == 0) {
            return Collections.EMPTY_LIST;
        }
        StrBuilder strBuilder = new StrBuilder();
        ArrayList arrayList = new ArrayList();
        int nextToken = i;
        while (nextToken >= 0 && nextToken < i2) {
            char[] cArr2 = cArr;
            int i3 = i2;
            nextToken = readNextToken(cArr2, nextToken, i3, strBuilder, arrayList);
            if (nextToken >= i3) {
                addToken(arrayList, "");
            }
            cArr = cArr2;
            i2 = i3;
        }
        return arrayList;
    }

    public static StrTokenizer getCSVInstance(String str) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(str);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(String str) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(str);
        return tSVClone;
    }

    /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
    public void add2(String str) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String[] strArr = this.tokens;
        int i = this.tokenPos;
        this.tokenPos = i + 1;
        return strArr[i];
    }

    @Override // java.util.ListIterator
    public String previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        String[] strArr = this.tokens;
        int i = this.tokenPos - 1;
        this.tokenPos = i;
        return strArr[i];
    }

    /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
    public void set2(String str) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public StrTokenizer reset(String str) {
        reset();
        if (str != null) {
            this.chars = str.toCharArray();
            return this;
        }
        this.chars = null;
        return this;
    }

    public static StrTokenizer getCSVInstance(char[] cArr) {
        StrTokenizer cSVClone = getCSVClone();
        cSVClone.reset(cArr);
        return cSVClone;
    }

    public static StrTokenizer getTSVInstance(char[] cArr) {
        StrTokenizer tSVClone = getTSVClone();
        tSVClone.reset(cArr);
        return tSVClone;
    }

    public StrTokenizer reset(char[] cArr) {
        reset();
        this.chars = ArrayUtils.clone(cArr);
        return this;
    }

    public StrTokenizer(String str) {
        this.delimMatcher = StrMatcher.splitMatcher();
        StrMatcher strMatcher = StrMatcher.NONE_MATCHER;
        this.quoteMatcher = strMatcher;
        this.ignoredMatcher = strMatcher;
        this.trimmerMatcher = strMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
    }

    public StrTokenizer(String str, char c) {
        this(str);
        setDelimiterChar(c);
    }

    public StrTokenizer(String str, String str2) {
        this(str);
        setDelimiterString(str2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher) {
        this(str);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(String str, char c, char c2) {
        this(str, c);
        setQuoteChar(c2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(str, strMatcher);
        setQuoteMatcher(strMatcher2);
    }

    public StrTokenizer(char[] cArr) {
        this.delimMatcher = StrMatcher.splitMatcher();
        StrMatcher strMatcher = StrMatcher.NONE_MATCHER;
        this.quoteMatcher = strMatcher;
        this.ignoredMatcher = strMatcher;
        this.trimmerMatcher = strMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = ArrayUtils.clone(cArr);
    }

    public StrTokenizer(char[] cArr, char c) {
        this(cArr);
        setDelimiterChar(c);
    }

    public StrTokenizer(char[] cArr, String str) {
        this(cArr);
        setDelimiterString(str);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher) {
        this(cArr);
        setDelimiterMatcher(strMatcher);
    }

    public StrTokenizer(char[] cArr, char c, char c2) {
        this(cArr, c);
        setQuoteChar(c2);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(cArr, strMatcher);
        setQuoteMatcher(strMatcher2);
    }
}
