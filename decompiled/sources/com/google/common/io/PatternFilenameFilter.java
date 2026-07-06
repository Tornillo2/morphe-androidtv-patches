package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class PatternFilenameFilter implements FilenameFilter {
    public final Pattern pattern;

    public PatternFilenameFilter(String patternStr) {
        this(Pattern.compile(patternStr));
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File dir, String fileName) {
        return this.pattern.matcher(fileName).matches();
    }

    public PatternFilenameFilter(Pattern pattern) {
        pattern.getClass();
        this.pattern = pattern;
    }
}
