package org.apache.commons.text.matcher;

import org.apache.commons.text.matcher.AbstractStringMatcher;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StringMatcherFactory {
    public static final StringMatcherFactory INSTANCE = new StringMatcherFactory();
    public static final AbstractStringMatcher.CharSetMatcher SPLIT_MATCHER = new AbstractStringMatcher.CharSetMatcher(" \t\n\r\f".toCharArray());
    public static final AbstractStringMatcher.CharMatcher COMMA_MATCHER = new AbstractStringMatcher.CharMatcher(',');
    public static final AbstractStringMatcher.CharMatcher TAB_MATCHER = new AbstractStringMatcher.CharMatcher('\t');
    public static final AbstractStringMatcher.CharMatcher SPACE_MATCHER = new AbstractStringMatcher.CharMatcher(' ');
    public static final AbstractStringMatcher.TrimMatcher TRIM_MATCHER = new AbstractStringMatcher.TrimMatcher();
    public static final AbstractStringMatcher.CharMatcher SINGLE_QUOTE_MATCHER = new AbstractStringMatcher.CharMatcher('\'');
    public static final AbstractStringMatcher.CharMatcher DOUBLE_QUOTE_MATCHER = new AbstractStringMatcher.CharMatcher('\"');
    public static final AbstractStringMatcher.CharSetMatcher QUOTE_MATCHER = new AbstractStringMatcher.CharSetMatcher("'\"".toCharArray());
    public static final AbstractStringMatcher.NoMatcher NONE_MATCHER = new AbstractStringMatcher.NoMatcher();

    public StringMatcher charMatcher(char c) {
        return new AbstractStringMatcher.CharMatcher(c);
    }

    public StringMatcher charSetMatcher(char... cArr) {
        return (cArr == null || cArr.length == 0) ? NONE_MATCHER : cArr.length == 1 ? new AbstractStringMatcher.CharMatcher(cArr[0]) : new AbstractStringMatcher.CharSetMatcher(cArr);
    }

    public StringMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    public StringMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    public StringMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    public StringMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    public StringMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    public StringMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    public StringMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    public StringMatcher stringMatcher(String str) {
        return (str == null || str.length() == 0) ? NONE_MATCHER : new AbstractStringMatcher.StringMatcher(str);
    }

    public StringMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    public StringMatcher trimMatcher() {
        return TRIM_MATCHER;
    }

    public StringMatcher charSetMatcher(String str) {
        if (str != null && str.length() != 0) {
            if (str.length() == 1) {
                return new AbstractStringMatcher.CharMatcher(str.charAt(0));
            }
            return new AbstractStringMatcher.CharSetMatcher(str.toCharArray());
        }
        return NONE_MATCHER;
    }
}
