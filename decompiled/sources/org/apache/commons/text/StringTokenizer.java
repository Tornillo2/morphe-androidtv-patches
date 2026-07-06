package org.apache.commons.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.text.matcher.AbstractStringMatcher;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class StringTokenizer implements ListIterator<String>, Cloneable {
    public static final StringTokenizer CSV_TOKENIZER_PROTOTYPE;
    public static final StringTokenizer TSV_TOKENIZER_PROTOTYPE;
    public char[] chars;
    public StringMatcher delimMatcher;
    public boolean emptyAsNull;
    public boolean ignoreEmptyTokens;
    public StringMatcher ignoredMatcher;
    public StringMatcher quoteMatcher;
    public int tokenPos;
    public String[] tokens;
    public StringMatcher trimmerMatcher;

    static {
        StringTokenizer stringTokenizer = new StringTokenizer();
        CSV_TOKENIZER_PROTOTYPE = stringTokenizer;
        StringMatcherFactory.INSTANCE.getClass();
        stringTokenizer.setDelimiterMatcher(StringMatcherFactory.COMMA_MATCHER);
        AbstractStringMatcher.CharMatcher charMatcher = StringMatcherFactory.DOUBLE_QUOTE_MATCHER;
        if (charMatcher != null) {
            stringTokenizer.quoteMatcher = charMatcher;
        }
        AbstractStringMatcher.NoMatcher noMatcher = StringMatcherFactory.NONE_MATCHER;
        if (noMatcher != null) {
            stringTokenizer.ignoredMatcher = noMatcher;
        }
        AbstractStringMatcher.TrimMatcher trimMatcher = StringMatcherFactory.TRIM_MATCHER;
        if (trimMatcher != null) {
            stringTokenizer.trimmerMatcher = trimMatcher;
        }
        stringTokenizer.emptyAsNull = false;
        stringTokenizer.ignoreEmptyTokens = false;
        StringTokenizer stringTokenizer2 = new StringTokenizer();
        TSV_TOKENIZER_PROTOTYPE = stringTokenizer2;
        stringTokenizer2.setDelimiterMatcher(StringMatcherFactory.TAB_MATCHER);
        if (charMatcher != null) {
            stringTokenizer2.quoteMatcher = charMatcher;
        }
        if (noMatcher != null) {
            stringTokenizer2.ignoredMatcher = noMatcher;
        }
        if (trimMatcher != null) {
            stringTokenizer2.trimmerMatcher = trimMatcher;
        }
        stringTokenizer2.emptyAsNull = false;
        stringTokenizer2.ignoreEmptyTokens = false;
    }

    public StringTokenizer() {
        StringMatcherFactory stringMatcherFactory = StringMatcherFactory.INSTANCE;
        stringMatcherFactory.getClass();
        this.delimMatcher = StringMatcherFactory.SPLIT_MATCHER;
        stringMatcherFactory.getClass();
        AbstractStringMatcher.NoMatcher noMatcher = StringMatcherFactory.NONE_MATCHER;
        this.quoteMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.ignoredMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.trimmerMatcher = noMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        this.chars = null;
    }

    public static StringTokenizer getCSVClone() {
        return (StringTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StringTokenizer getCSVInstance() {
        return getCSVClone();
    }

    public static StringTokenizer getTSVClone() {
        return (StringTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StringTokenizer getTSVInstance() {
        return getTSVClone();
    }

    @Override // java.util.ListIterator
    public /* bridge */ /* synthetic */ void add(String str) {
        add2(str);
        throw null;
    }

    public final void addToken(List<String> list, String str) {
        if (str == null || str.length() == 0) {
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
        StringTokenizer stringTokenizer = (StringTokenizer) super.clone();
        char[] cArr = stringTokenizer.chars;
        if (cArr != null) {
            stringTokenizer.chars = (char[]) cArr.clone();
        }
        stringTokenizer.reset();
        return stringTokenizer;
    }

    public String getContent() {
        char[] cArr = this.chars;
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public StringMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StringMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StringMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return (String[]) this.tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        ArrayList arrayList = new ArrayList(this.tokens.length);
        Collections.addAll(arrayList, this.tokens);
        return arrayList;
    }

    public StringMatcher getTrimmerMatcher() {
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

    public final int readNextToken(char[] cArr, int i, int i2, TextStringBuilder textStringBuilder, List<String> list) {
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
            return readWithQuotes(cArr, i3, i2, textStringBuilder, list, 0, 0);
        }
        int i4 = i3;
        return readWithQuotes(cArr, i4 + iIsMatch2, i2, textStringBuilder, list, i4, iIsMatch2);
    }

    public final int readWithQuotes(char[] cArr, int i, int i2, TextStringBuilder textStringBuilder, List<String> list, int i3, int i4) {
        int i5;
        int i6 = i2;
        textStringBuilder.clear();
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
                        textStringBuilder.append(cArr, i8, i4);
                        i7 = (i4 * 2) + i8;
                        size = textStringBuilder.size();
                    } else {
                        z = false;
                    }
                } else {
                    i6 = i2;
                    i7 = i8 + 1;
                    textStringBuilder.append(cArr[i8]);
                    size = textStringBuilder.size();
                }
            } else {
                int i9 = i7;
                int iIsMatch = getDelimiterMatcher().isMatch(cArr, i9, i, i6);
                if (iIsMatch > 0) {
                    addToken(list, textStringBuilder.substring(0, size));
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
                        textStringBuilder.append(cArr, i5, iIsMatch2);
                    } else {
                        textStringBuilder.append(cArr[i5]);
                        size = textStringBuilder.size();
                        i7 = i5 + 1;
                    }
                }
                i7 = i5 + iIsMatch2;
            }
        }
        addToken(list, textStringBuilder.substring(0, size));
        return -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StringTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    @Override // java.util.ListIterator
    public /* bridge */ /* synthetic */ void set(String str) {
        set2(str);
        throw null;
    }

    public StringTokenizer setDelimiterChar(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setDelimiterMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringTokenizer setDelimiterMatcher(StringMatcher stringMatcher) {
        if (stringMatcher != null) {
            this.delimMatcher = stringMatcher;
            return this;
        }
        StringMatcherFactory.INSTANCE.getClass();
        this.delimMatcher = StringMatcherFactory.NONE_MATCHER;
        return this;
    }

    public StringTokenizer setDelimiterString(String str) {
        return setDelimiterMatcher(StringMatcherFactory.INSTANCE.stringMatcher(str));
    }

    public StringTokenizer setEmptyTokenAsNull(boolean z) {
        this.emptyAsNull = z;
        return this;
    }

    public StringTokenizer setIgnoreEmptyTokens(boolean z) {
        this.ignoreEmptyTokens = z;
        return this;
    }

    public StringTokenizer setIgnoredChar(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setIgnoredMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringTokenizer setIgnoredMatcher(StringMatcher stringMatcher) {
        if (stringMatcher != null) {
            this.ignoredMatcher = stringMatcher;
        }
        return this;
    }

    public StringTokenizer setQuoteChar(char c) {
        StringMatcherFactory.INSTANCE.getClass();
        return setQuoteMatcher(new AbstractStringMatcher.CharMatcher(c));
    }

    public StringTokenizer setQuoteMatcher(StringMatcher stringMatcher) {
        if (stringMatcher != null) {
            this.quoteMatcher = stringMatcher;
        }
        return this;
    }

    public StringTokenizer setTrimmerMatcher(StringMatcher stringMatcher) {
        if (stringMatcher != null) {
            this.trimmerMatcher = stringMatcher;
        }
        return this;
    }

    public int size() {
        checkTokenized();
        return this.tokens.length;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StringTokenizer[not tokenized yet]";
        }
        return "StringTokenizer" + getTokenList();
    }

    public List<String> tokenize(char[] cArr, int i, int i2) {
        if (cArr == null || i2 == 0) {
            return Collections.EMPTY_LIST;
        }
        TextStringBuilder textStringBuilder = new TextStringBuilder();
        ArrayList arrayList = new ArrayList();
        int nextToken = i;
        while (nextToken >= 0 && nextToken < i2) {
            char[] cArr2 = cArr;
            int i3 = i2;
            nextToken = readNextToken(cArr2, nextToken, i3, textStringBuilder, arrayList);
            if (nextToken >= i3) {
                addToken(arrayList, "");
            }
            cArr = cArr2;
            i2 = i3;
        }
        return arrayList;
    }

    public static StringTokenizer getCSVInstance(String str) {
        StringTokenizer cSVClone = getCSVClone();
        cSVClone.reset(str);
        return cSVClone;
    }

    public static StringTokenizer getTSVInstance(String str) {
        StringTokenizer tSVClone = getTSVClone();
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

    public StringTokenizer reset(String str) {
        reset();
        if (str != null) {
            this.chars = str.toCharArray();
            return this;
        }
        this.chars = null;
        return this;
    }

    public static StringTokenizer getCSVInstance(char[] cArr) {
        StringTokenizer cSVClone = getCSVClone();
        cSVClone.reset(cArr);
        return cSVClone;
    }

    public static StringTokenizer getTSVInstance(char[] cArr) {
        StringTokenizer tSVClone = getTSVClone();
        tSVClone.reset(cArr);
        return tSVClone;
    }

    public StringTokenizer reset(char[] cArr) {
        reset();
        if (cArr != null) {
            this.chars = (char[]) cArr.clone();
            return this;
        }
        this.chars = null;
        return this;
    }

    public StringTokenizer(String str) {
        StringMatcherFactory stringMatcherFactory = StringMatcherFactory.INSTANCE;
        stringMatcherFactory.getClass();
        this.delimMatcher = StringMatcherFactory.SPLIT_MATCHER;
        stringMatcherFactory.getClass();
        AbstractStringMatcher.NoMatcher noMatcher = StringMatcherFactory.NONE_MATCHER;
        this.quoteMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.ignoredMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.trimmerMatcher = noMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        if (str != null) {
            this.chars = str.toCharArray();
        } else {
            this.chars = null;
        }
    }

    public StringTokenizer(String str, char c) {
        this(str);
        setDelimiterChar(c);
    }

    public StringTokenizer(String str, String str2) {
        this(str);
        setDelimiterString(str2);
    }

    public StringTokenizer(String str, StringMatcher stringMatcher) {
        this(str);
        setDelimiterMatcher(stringMatcher);
    }

    public StringTokenizer(String str, char c, char c2) {
        this(str, c);
        setQuoteChar(c2);
    }

    public StringTokenizer(String str, StringMatcher stringMatcher, StringMatcher stringMatcher2) {
        this(str, stringMatcher);
        setQuoteMatcher(stringMatcher2);
    }

    public StringTokenizer(char[] cArr) {
        StringMatcherFactory stringMatcherFactory = StringMatcherFactory.INSTANCE;
        stringMatcherFactory.getClass();
        this.delimMatcher = StringMatcherFactory.SPLIT_MATCHER;
        stringMatcherFactory.getClass();
        AbstractStringMatcher.NoMatcher noMatcher = StringMatcherFactory.NONE_MATCHER;
        this.quoteMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.ignoredMatcher = noMatcher;
        stringMatcherFactory.getClass();
        this.trimmerMatcher = noMatcher;
        this.emptyAsNull = false;
        this.ignoreEmptyTokens = true;
        if (cArr == null) {
            this.chars = null;
        } else {
            this.chars = (char[]) cArr.clone();
        }
    }

    public StringTokenizer(char[] cArr, char c) {
        this(cArr);
        setDelimiterChar(c);
    }

    public StringTokenizer(char[] cArr, String str) {
        this(cArr);
        setDelimiterString(str);
    }

    public StringTokenizer(char[] cArr, StringMatcher stringMatcher) {
        this(cArr);
        setDelimiterMatcher(stringMatcher);
    }

    public StringTokenizer(char[] cArr, char c, char c2) {
        this(cArr, c);
        setQuoteChar(c2);
    }

    public StringTokenizer(char[] cArr, StringMatcher stringMatcher, StringMatcher stringMatcher2) {
        this(cArr, stringMatcher);
        setQuoteMatcher(stringMatcher2);
    }
}
