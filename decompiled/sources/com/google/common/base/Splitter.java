package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.CharMatcher;
import com.google.common.base.JdkPattern;
import com.google.common.base.Splitter;
import j$.lang.Iterable$EL;
import j$.util.DesugarCollections;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Splitter {
    public final int limit;
    public final boolean omitEmptyStrings;
    public final Strategy strategy;
    public final CharMatcher trimmer;

    /* JADX INFO: renamed from: com.google.common.base.Splitter$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends SplittingIterator {
        public final /* synthetic */ CharMatcher val$separatorMatcher;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Splitter splitter, CharSequence toSplit, final CharMatcher val$separatorMatcher) {
            super(splitter, toSplit);
            this.val$separatorMatcher = val$separatorMatcher;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorEnd(int separatorPosition) {
            return separatorPosition + 1;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorStart(int start) {
            return this.val$separatorMatcher.indexIn(this.toSplit, start);
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.Splitter$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends SplittingIterator {
        public final /* synthetic */ String val$separator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Splitter splitter, CharSequence toSplit, final String val$separator) {
            super(splitter, toSplit);
            this.val$separator = val$separator;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorEnd(int separatorPosition) {
            return this.val$separator.length() + separatorPosition;
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:
        
            r6 = r6 + 1;
         */
        @Override // com.google.common.base.Splitter.SplittingIterator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int separatorStart(int r6) {
            /*
                r5 = this;
                java.lang.String r0 = r5.val$separator
                int r0 = r0.length()
                java.lang.CharSequence r1 = r5.toSplit
                int r1 = r1.length()
                int r1 = r1 - r0
            Ld:
                if (r6 > r1) goto L29
                r2 = 0
            L10:
                if (r2 >= r0) goto L28
                java.lang.CharSequence r3 = r5.toSplit
                int r4 = r2 + r6
                char r3 = r3.charAt(r4)
                java.lang.String r4 = r5.val$separator
                char r4 = r4.charAt(r2)
                if (r3 == r4) goto L25
                int r6 = r6 + 1
                goto Ld
            L25:
                int r2 = r2 + 1
                goto L10
            L28:
                return r6
            L29:
                r6 = -1
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.AnonymousClass2.separatorStart(int):int");
        }
    }

    /* JADX INFO: renamed from: com.google.common.base.Splitter$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass5 implements Iterable<String> {
        public final /* synthetic */ Splitter this$0;
        public final /* synthetic */ CharSequence val$sequence;

        public AnonymousClass5(final Splitter this$0, final CharSequence val$sequence) {
            this.val$sequence = val$sequence;
            this.this$0 = this$0;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            Splitter splitter = this.this$0;
            return splitter.strategy.iterator(splitter, this.val$sequence);
        }

        public String toString() {
            Joiner joiner = new Joiner(", ");
            StringBuilder sb = new StringBuilder();
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            joiner.appendTo(sb, (Iterator<?>) iterator());
            sb.append(AbstractJsonLexerKt.END_LIST);
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MapSplitter {
        public static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        public final Splitter entrySplitter;
        public final Splitter outerSplitter;

        public /* synthetic */ MapSplitter(Splitter splitter, Splitter splitter2, AnonymousClass1 anonymousClass1) {
            this(splitter, splitter2);
        }

        public Map<String, String> split(CharSequence sequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : (AnonymousClass5) this.outerSplitter.split(sequence)) {
                Splitter splitter = this.entrySplitter;
                Iterator<String> it = splitter.strategy.iterator(splitter, str);
                Preconditions.checkArgument(it.hasNext(), INVALID_ENTRY_MESSAGE, str);
                String next = it.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(next), "Duplicate key [%s] found.", next);
                Preconditions.checkArgument(it.hasNext(), INVALID_ENTRY_MESSAGE, str);
                linkedHashMap.put(next, it.next());
                Preconditions.checkArgument(!it.hasNext(), INVALID_ENTRY_MESSAGE, str);
            }
            return DesugarCollections.unmodifiableMap(linkedHashMap);
        }

        public MapSplitter(Splitter outerSplitter, Splitter entrySplitter) {
            this.outerSplitter = outerSplitter;
            entrySplitter.getClass();
            this.entrySplitter = entrySplitter;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class SplittingIterator extends AbstractIterator<String> {
        public int limit;
        public int offset = 0;
        public final boolean omitEmptyStrings;
        public final CharSequence toSplit;
        public final CharMatcher trimmer;

        public SplittingIterator(Splitter splitter, CharSequence toSplit) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = toSplit;
        }

        public abstract int separatorEnd(int separatorPosition);

        public abstract int separatorStart(int start);

        @Override // com.google.common.base.AbstractIterator
        public String computeNext() {
            int iSeparatorStart;
            int i = this.offset;
            while (true) {
                int i2 = this.offset;
                if (i2 == -1) {
                    endOfData();
                    return null;
                }
                iSeparatorStart = separatorStart(i2);
                if (iSeparatorStart == -1) {
                    iSeparatorStart = this.toSplit.length();
                    this.offset = -1;
                } else {
                    this.offset = separatorEnd(iSeparatorStart);
                }
                int i3 = this.offset;
                if (i3 == i) {
                    int i4 = i3 + 1;
                    this.offset = i4;
                    if (i4 > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i < iSeparatorStart && this.trimmer.matches(this.toSplit.charAt(i))) {
                        i++;
                    }
                    while (iSeparatorStart > i && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                        iSeparatorStart--;
                    }
                    if (!this.omitEmptyStrings || i != iSeparatorStart) {
                        break;
                    }
                    i = this.offset;
                }
            }
            int i5 = this.limit;
            if (i5 == 1) {
                iSeparatorStart = this.toSplit.length();
                this.offset = -1;
                while (iSeparatorStart > i && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                    iSeparatorStart--;
                }
            } else {
                this.limit = i5 - 1;
            }
            return this.toSplit.subSequence(i, iSeparatorStart).toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence toSplit);
    }

    /* JADX INFO: renamed from: $r8$lambda$CK-_F_WMWUv6g4fX5TCs4Q17sbA, reason: not valid java name */
    public static /* synthetic */ Iterator m498$r8$lambda$CK_F_WMWUv6g4fX5TCs4Q17sbA(CharMatcher charMatcher, Splitter splitter, CharSequence charSequence) {
        return new AnonymousClass1(splitter, charSequence, charMatcher);
    }

    public static /* synthetic */ Iterator $r8$lambda$JAc39g2UtldtGz0a7UNUPHW3wwQ(int i, Splitter splitter, CharSequence charSequence) {
        return new AnonymousClass4(splitter, charSequence, i);
    }

    public static /* synthetic */ Iterator $r8$lambda$e00L1vhkadM2rBAapSaIfxSYJ7k(String str, Splitter splitter, CharSequence charSequence) {
        return new AnonymousClass2(splitter, charSequence, str);
    }

    /* JADX INFO: renamed from: $r8$lambda$xD_q1jEIZQEJQgY--B-GZ2PKM_E, reason: not valid java name */
    public static /* synthetic */ Iterator m499$r8$lambda$xD_q1jEIZQEJQgYBGZ2PKM_E(CommonPattern commonPattern, Splitter splitter, CharSequence charSequence) {
        final CommonMatcher commonMatcherMatcher = commonPattern.matcher(charSequence);
        return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.3
            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorEnd(int separatorPosition) {
                return commonMatcherMatcher.end();
            }

            @Override // com.google.common.base.Splitter.SplittingIterator
            public int separatorStart(int start) {
                if (commonMatcherMatcher.find(start)) {
                    return commonMatcherMatcher.start();
                }
                return -1;
            }
        };
    }

    public Splitter(Strategy strategy, boolean omitEmptyStrings, CharMatcher trimmer, int limit) {
        this.strategy = strategy;
        this.omitEmptyStrings = omitEmptyStrings;
        this.trimmer = trimmer;
        this.limit = limit;
    }

    public static Splitter fixedLength(final int length) {
        Preconditions.checkArgument(length > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Splitter.Strategy
            public final Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return new Splitter.AnonymousClass4(splitter, charSequence, length);
            }
        });
    }

    public static Splitter on(final String separator) {
        Preconditions.checkArgument(separator.length() != 0, "The separator may not be the empty string.");
        return separator.length() == 1 ? on(separator.charAt(0)) : new Splitter(new Strategy() { // from class: com.google.common.base.Splitter$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Splitter.Strategy
            public final Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return new Splitter.AnonymousClass2(splitter, charSequence, separator);
            }
        });
    }

    @GwtIncompatible
    public static Splitter onPattern(String separatorPattern) {
        return onPatternInternal(Platform.compilePattern(separatorPattern));
    }

    public static Splitter onPatternInternal(final CommonPattern separatorPattern) {
        Preconditions.checkArgument(!((JdkPattern.JdkMatcher) separatorPattern.matcher("")).matcher.matches(), "The pattern may not match the empty string: %s", separatorPattern);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter$$ExternalSyntheticLambda3
            @Override // com.google.common.base.Splitter.Strategy
            public final Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return Splitter.m499$r8$lambda$xD_q1jEIZQEJQgYBGZ2PKM_E(separatorPattern, splitter, charSequence);
            }
        });
    }

    public Splitter limit(int maxItems) {
        Preconditions.checkArgument(maxItems > 0, "must be greater than zero: %s", maxItems);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, maxItems);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(CharSequence sequence) {
        sequence.getClass();
        return new AnonymousClass5(this, sequence);
    }

    public List<String> splitToList(CharSequence sequence) {
        sequence.getClass();
        Iterator<String> it = this.strategy.iterator(this, sequence);
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }

    @IgnoreJRERequirement
    public Stream<String> splitToStream(CharSequence sequence) {
        return StreamSupport.stream(Iterable$EL.spliterator(split(sequence)), false);
    }

    public final Iterator<String> splittingIterator(CharSequence sequence) {
        return this.strategy.iterator(this, sequence);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.Whitespace.INSTANCE);
    }

    public MapSplitter withKeyValueSeparator(String separator) {
        return new MapSplitter(this, on(separator));
    }

    public Splitter trimResults(CharMatcher trimmer) {
        trimmer.getClass();
        return new Splitter(this.strategy, this.omitEmptyStrings, trimmer, this.limit);
    }

    public MapSplitter withKeyValueSeparator(char separator) {
        return new MapSplitter(this, on(separator));
    }

    @GwtIncompatible
    public static Splitter on(Pattern separatorPattern) {
        return onPatternInternal(new JdkPattern(separatorPattern));
    }

    public Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.None.INSTANCE, Integer.MAX_VALUE);
    }

    public static Splitter on(char separator) {
        return on(new CharMatcher.Is(separator));
    }

    public MapSplitter withKeyValueSeparator(Splitter keyValueSplitter) {
        return new MapSplitter(this, keyValueSplitter);
    }

    public static Splitter on(final CharMatcher separatorMatcher) {
        separatorMatcher.getClass();
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Splitter.Strategy
            public final Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return new Splitter.AnonymousClass1(splitter, charSequence, separatorMatcher);
            }
        });
    }

    /* JADX INFO: renamed from: com.google.common.base.Splitter$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends SplittingIterator {
        public final /* synthetic */ int val$length;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(Splitter splitter, CharSequence toSplit, final int val$length) {
            super(splitter, toSplit);
            this.val$length = val$length;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorStart(int start) {
            int i = start + this.val$length;
            if (i < this.toSplit.length()) {
                return i;
            }
            return -1;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorEnd(int separatorPosition) {
            return separatorPosition;
        }
    }
}
