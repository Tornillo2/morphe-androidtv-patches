package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.InlineMeValidationDisabled;
import java.util.Arrays;
import java.util.BitSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public abstract class CharMatcher implements Predicate<Character> {
    public static final int DISTINCT_CHARS = 65536;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class And extends CharMatcher {
        public final CharMatcher first;
        public final CharMatcher second;

        public And(CharMatcher a, CharMatcher b) {
            a.getClass();
            this.first = a;
            b.getClass();
            this.second = b;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) && this.second.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            BitSet bitSet = new BitSet();
            this.first.setBits(bitSet);
            BitSet bitSet2 = new BitSet();
            this.second.setBits(bitSet2);
            bitSet.and(bitSet2);
            table.or(bitSet);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.and(" + this.first + ", " + this.second + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Any extends NamedFastMatcher {
        public static final CharMatcher INSTANCE = new Any();

        public Any() {
            super("CharMatcher.any()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            other.getClass();
            return other;
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.length() == 0 ? "" : String.valueOf(replacement);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            return sequence.length();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence) {
            return sequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence sequence) {
            return sequence.length() - 1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            sequence.getClass();
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return None.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            other.getClass();
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence sequence) {
            sequence.getClass();
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, char replacement) {
            char[] cArr = new char[sequence.length()];
            Arrays.fill(cArr, replacement);
            return new String(cArr);
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence sequence) {
            sequence.getClass();
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence, int start) {
            int length = sequence.length();
            Preconditions.checkPositionIndex(start, length);
            if (start == length) {
                return -1;
            }
            return start;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            StringBuilder sb = new StringBuilder(replacement.length() * sequence.length());
            for (int i = 0; i < sequence.length(); i++) {
                sb.append(replacement);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnyOf extends CharMatcher {
        public final char[] chars;

        public AnyOf(CharSequence chars) {
            char[] charArray = chars.toString().toCharArray();
            this.chars = charArray;
            Arrays.sort(charArray);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Arrays.binarySearch(this.chars, c) >= 0;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            for (char c : this.chars) {
                table.set(c);
            }
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c : this.chars) {
                sb.append(CharMatcher.showCharacter(c));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Ascii extends NamedFastMatcher {
        public static final CharMatcher INSTANCE = new Ascii();

        public Ascii() {
            super("CharMatcher.ascii()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c <= 127;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static final class BitSetMatcher extends NamedFastMatcher {
        public final BitSet table;

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.table.get(c);
        }

        @Override // com.google.common.base.CharMatcher
        public void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }

        public BitSetMatcher(BitSet table, String description) {
            super(description);
            this.table = table.length() + 64 < table.size() ? (BitSet) table.clone() : table;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BreakingWhitespace extends CharMatcher {
        public static final CharMatcher INSTANCE = new BreakingWhitespace();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            if (c != ' ' && c != 133 && c != 5760) {
                if (c != 8199) {
                    if (c != 8287 && c != 12288 && c != 8232 && c != 8233) {
                        switch (c) {
                            case '\t':
                            case '\n':
                            case 11:
                            case '\f':
                            case '\r':
                                break;
                            default:
                                if (c >= 8192 && c <= 8202) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Digit extends RangesMatcher {
        public static final CharMatcher INSTANCE = new Digit();
        public static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦෦๐໐༠၀႐០᠐᥆᧐᪀᪐᭐᮰᱀᱐꘠꣐꤀꧐꧰꩐꯰０";

        public Digit() {
            super("CharMatcher.digit()", ZEROES.toCharArray(), nines());
        }

        public static char[] nines() {
            char[] cArr = new char[37];
            for (int i = 0; i < 37; i++) {
                cArr[i] = (char) (ZEROES.charAt(i) + '\t');
            }
            return cArr;
        }

        public static char[] zeroes() {
            return ZEROES.toCharArray();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ForPredicate extends CharMatcher {
        public final Predicate<? super Character> predicate;

        public ForPredicate(Predicate<? super Character> predicate) {
            predicate.getClass();
            this.predicate = predicate;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.predicate.apply(Character.valueOf(c));
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.forPredicate(" + this.predicate + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InRange extends FastMatcher {
        public final char endInclusive;
        public final char startInclusive;

        public InRange(char startInclusive, char endInclusive) {
            Preconditions.checkArgument(endInclusive >= startInclusive);
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.startInclusive <= c && c <= this.endInclusive;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            table.set(this.startInclusive, this.endInclusive + 1);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.inRange('" + CharMatcher.showCharacter(this.startInclusive) + "', '" + CharMatcher.showCharacter(this.endInclusive) + "')";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Invisible extends RangesMatcher {
        public static final CharMatcher INSTANCE = new Invisible();
        public static final String RANGE_ENDS = "  \u00ad\u0605\u061c\u06dd\u070f\u0891\u08e2\u1680\u180e\u200f \u2064\u206f\u3000\uf8ff\ufeff\ufffb";
        public static final String RANGE_STARTS = "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u0890\u08e2\u1680\u180e\u2000\u2028\u205f\u2066\u3000\ud800\ufeff\ufff9";

        public Invisible() {
            super("CharMatcher.invisible()", RANGE_STARTS.toCharArray(), RANGE_ENDS.toCharArray());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Is extends FastMatcher {
        public final char match;

        public Is(char match) {
            this.match = match;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            return other.matches(this.match) ? this : None.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new IsNot(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            return other.matches(this.match) ? other : new Or(this, other);
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString().replace(this.match, replacement);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            table.set(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.is('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IsEither extends FastMatcher {
        public final char match1;
        public final char match2;

        public IsEither(char match1, char match2) {
            this.match1 = match1;
            this.match2 = match2;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c == this.match1 || c == this.match2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            table.set(this.match1);
            table.set(this.match2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.anyOf(\"" + CharMatcher.showCharacter(this.match1) + CharMatcher.showCharacter(this.match2) + "\")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IsNot extends FastMatcher {
        public final char match;

        public IsNot(char match) {
            this.match = match;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            return other.matches(this.match) ? new And(this, other) : other;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return c != this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new Is(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            return other.matches(this.match) ? Any.INSTANCE : this;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            table.set(0, this.match);
            table.set(this.match + 1, 65536);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.isNot('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaDigit extends CharMatcher {
        public static final CharMatcher INSTANCE = new JavaDigit();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isDigit(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaIsoControl extends NamedFastMatcher {
        public static final CharMatcher INSTANCE = new JavaIsoControl();

        public JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            if (c > 31) {
                return c >= 127 && c <= 159;
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaLetter extends CharMatcher {
        public static final CharMatcher INSTANCE = new JavaLetter();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetter(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaLetterOrDigit extends CharMatcher {
        public static final CharMatcher INSTANCE = new JavaLetterOrDigit();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLetterOrDigit(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaLowerCase extends CharMatcher {
        public static final CharMatcher INSTANCE = new JavaLowerCase();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isLowerCase(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JavaUpperCase extends CharMatcher {
        public static final CharMatcher INSTANCE = new JavaUpperCase();

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return Character.isUpperCase(c);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class NamedFastMatcher extends FastMatcher {
        public final String description;

        public NamedFastMatcher(String description) {
            description.getClass();
            this.description = description;
        }

        @Override // com.google.common.base.CharMatcher
        public final String toString() {
            return this.description;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Negated extends CharMatcher {
        public final CharMatcher original;

        public Negated(CharMatcher original) {
            original.getClass();
            this.original = original;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return super.apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            return sequence.length() - this.original.countIn(sequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            return this.original.matchesNoneOf(sequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            return this.original.matchesAllOf(sequence);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            BitSet bitSet = new BitSet();
            this.original.setBits(bitSet);
            bitSet.flip(0, 65536);
            table.or(bitSet);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.original + ".negate()";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class None extends NamedFastMatcher {
        public static final CharMatcher INSTANCE = new None();

        public None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher other) {
            other.getClass();
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence sequence) {
            sequence.getClass();
            return 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence) {
            sequence.getClass();
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence sequence) {
            sequence.getClass();
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return false;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence sequence) {
            sequence.getClass();
            return true;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return Any.INSTANCE;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher or(CharMatcher other) {
            other.getClass();
            return other;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            replacement.getClass();
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimLeadingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimTrailingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence sequence, int start) {
            Preconditions.checkPositionIndex(start, sequence.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Or extends CharMatcher {
        public final CharMatcher first;
        public final CharMatcher second;

        public Or(CharMatcher a, CharMatcher b) {
            a.getClass();
            this.first = a;
            b.getClass();
            this.second = b;
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return this.first.matches(c) || this.second.matches(c);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            this.first.setBits(table);
            this.second.setBits(table);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.or(" + this.first + ", " + this.second + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RangesMatcher extends CharMatcher {
        public final String description;
        public final char[] rangeEnds;
        public final char[] rangeStarts;

        public RangesMatcher(String description, char[] rangeStarts, char[] rangeEnds) {
            this.description = description;
            this.rangeStarts = rangeStarts;
            this.rangeEnds = rangeEnds;
            Preconditions.checkArgument(rangeStarts.length == rangeEnds.length);
            int i = 0;
            while (i < rangeStarts.length) {
                Preconditions.checkArgument(rangeStarts[i] <= rangeEnds[i]);
                int i2 = i + 1;
                if (i2 < rangeStarts.length) {
                    Preconditions.checkArgument(rangeEnds[i] < rangeStarts[i2]);
                }
                i = i2;
            }
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return super.apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            int iBinarySearch = Arrays.binarySearch(this.rangeStarts, c);
            if (iBinarySearch >= 0) {
                return true;
            }
            int i = (~iBinarySearch) - 1;
            return i >= 0 && c <= this.rangeEnds[i];
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.description;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SingleWidth extends RangesMatcher {
        public static final CharMatcher INSTANCE = new SingleWidth();

        public SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000־א׳\u0600ݐ\u0e00Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ\u0e7f₯℺﷿\ufeffￜ".toCharArray());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static final class Whitespace extends NamedFastMatcher {
        public static final int MULTIPLIER = 1682554634;
        public static final String TABLE = "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000";
        public static final int SHIFT = Integer.numberOfLeadingZeros(31);
        public static final CharMatcher INSTANCE = new Whitespace();

        public Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return TABLE.charAt((MULTIPLIER * c) >>> SHIFT) == c;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet table) {
            for (int i = 0; i < 32; i++) {
                table.set(TABLE.charAt(i));
            }
        }
    }

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher anyOf(CharSequence sequence) {
        int length = sequence.length();
        return length != 0 ? length != 1 ? length != 2 ? new AnyOf(sequence) : new IsEither(sequence.charAt(0), sequence.charAt(1)) : new Is(sequence.charAt(0)) : None.INSTANCE;
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }

    @Deprecated
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher) predicate : new ForPredicate(predicate);
    }

    public static CharMatcher inRange(char startInclusive, char endInclusive) {
        return new InRange(startInclusive, endInclusive);
    }

    @Deprecated
    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }

    public static CharMatcher is(char match) {
        return new Is(match);
    }

    public static IsEither isEither(char c1, char c2) {
        return new IsEither(c1, c2);
    }

    public static CharMatcher isNot(char match) {
        return new IsNot(match);
    }

    @GwtIncompatible
    public static boolean isSmall(int totalCharacters, int tableLength) {
        return totalCharacters <= 1023 && tableLength > totalCharacters * 64;
    }

    @Deprecated
    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }

    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher noneOf(CharSequence sequence) {
        return anyOf(sequence).negate();
    }

    @GwtIncompatible
    public static CharMatcher precomputedPositive(int totalCharacters, BitSet table, String description) {
        if (totalCharacters == 0) {
            return None.INSTANCE;
        }
        if (totalCharacters == 1) {
            return new Is((char) table.nextSetBit(0));
        }
        if (totalCharacters != 2) {
            return isSmall(totalCharacters, table.length()) ? SmallCharMatcher.from(table, description) : new BitSetMatcher(table, description);
        }
        char cNextSetBit = (char) table.nextSetBit(0);
        return new IsEither(cNextSetBit, (char) table.nextSetBit(cNextSetBit + 1));
    }

    public static String showCharacter(char c) {
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = AbstractJsonLexerKt.UNICODE_ESC;
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    @Deprecated
    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public CharMatcher and(CharMatcher other) {
        return new And(this, other);
    }

    public String collapseFrom(CharSequence sequence, char replacement) {
        int length = sequence.length();
        int i = 0;
        while (i < length) {
            char cCharAt = sequence.charAt(i);
            if (matches(cCharAt)) {
                if (cCharAt != replacement || (i != length - 1 && matches(sequence.charAt(i + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(sequence, 0, i);
                    sb.append(replacement);
                    return finishCollapseFrom(sequence, i + 1, length, replacement, sb, true);
                }
                i++;
            }
            i++;
            replacement = replacement;
        }
        return sequence.toString();
    }

    public int countIn(CharSequence sequence) {
        int i = 0;
        for (int i2 = 0; i2 < sequence.length(); i2++) {
            if (matches(sequence.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    public final String finishCollapseFrom(CharSequence sequence, int start, int end, char replacement, StringBuilder builder, boolean inMatchingGroup) {
        while (start < end) {
            char cCharAt = sequence.charAt(start);
            if (!matches(cCharAt)) {
                builder.append(cCharAt);
                inMatchingGroup = false;
            } else if (!inMatchingGroup) {
                builder.append(replacement);
                inMatchingGroup = true;
            }
            start++;
        }
        return builder.toString();
    }

    public int indexIn(CharSequence sequence) {
        return indexIn(sequence, 0);
    }

    public int lastIndexIn(CharSequence sequence) {
        for (int length = sequence.length() - 1; length >= 0; length--) {
            if (matches(sequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public abstract boolean matches(char c);

    public boolean matchesAllOf(CharSequence sequence) {
        for (int length = sequence.length() - 1; length >= 0; length--) {
            if (!matches(sequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesAnyOf(CharSequence sequence) {
        return !matchesNoneOf(sequence);
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return indexIn(sequence) == -1;
    }

    public CharMatcher negate() {
        return new Negated(this);
    }

    public CharMatcher or(CharMatcher other) {
        return new Or(this, other);
    }

    public CharMatcher precomputed() {
        return precomputedInternal();
    }

    @GwtIncompatible
    public CharMatcher precomputedInternal() {
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int iCardinality = bitSet.cardinality();
        if (iCardinality * 2 <= 65536) {
            return precomputedPositive(iCardinality, bitSet, toString());
        }
        bitSet.flip(0, 65536);
        int i = 65536 - iCardinality;
        final String string = toString();
        return new NegatedFastMatcher(this, precomputedPositive(i, bitSet, string.endsWith(".negate()") ? string.substring(0, string.length() - 9) : string.concat(".negate()"))) { // from class: com.google.common.base.CharMatcher.1
            public final /* synthetic */ CharMatcher this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.base.CharMatcher.Negated, com.google.common.base.CharMatcher
            public String toString() {
                return string;
            }
        };
    }

    public String removeFrom(CharSequence sequence) {
        String string = sequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int i = 1;
        while (true) {
            iIndexIn++;
            while (iIndexIn != charArray.length) {
                if (matches(charArray[iIndexIn])) {
                    break;
                }
                charArray[iIndexIn - i] = charArray[iIndexIn];
                iIndexIn++;
            }
            return new String(charArray, 0, iIndexIn - i);
            i++;
        }
    }

    public String replaceFrom(CharSequence sequence, char replacement) {
        String string = sequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        charArray[iIndexIn] = replacement;
        while (true) {
            iIndexIn++;
            if (iIndexIn >= charArray.length) {
                return new String(charArray);
            }
            if (matches(charArray[iIndexIn])) {
                charArray[iIndexIn] = replacement;
            }
        }
    }

    public String retainFrom(CharSequence sequence) {
        return negate().removeFrom(sequence);
    }

    @GwtIncompatible
    public void setBits(BitSet table) {
        for (int i = 65535; i >= 0; i--) {
            if (matches((char) i)) {
                table.set(i);
            }
        }
    }

    public String toString() {
        return super.toString();
    }

    public String trimAndCollapseFrom(CharSequence sequence, char replacement) {
        int length = sequence.length();
        int i = length - 1;
        int i2 = 0;
        while (i2 < length && matches(sequence.charAt(i2))) {
            i2++;
        }
        int i3 = i;
        while (i3 > i2 && matches(sequence.charAt(i3))) {
            i3--;
        }
        if (i2 == 0 && i3 == i) {
            return collapseFrom(sequence, replacement);
        }
        int i4 = i3 + 1;
        return finishCollapseFrom(sequence, i2, i4, replacement, new StringBuilder(i4 - i2), false);
    }

    public String trimFrom(CharSequence sequence) {
        int length = sequence.length();
        int i = 0;
        while (i < length && matches(sequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        while (i2 > i && matches(sequence.charAt(i2))) {
            i2--;
        }
        return sequence.subSequence(i, i2 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence sequence) {
        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (!matches(sequence.charAt(i))) {
                return sequence.subSequence(i, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence sequence) {
        for (int length = sequence.length() - 1; length >= 0; length--) {
            if (!matches(sequence.charAt(length))) {
                return sequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @Override // com.google.common.base.Predicate
    @InlineMe(replacement = "this.matches(character)")
    @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
    @Deprecated
    public boolean apply(Character character) {
        return matches(character.charValue());
    }

    public int indexIn(CharSequence sequence, int start) {
        int length = sequence.length();
        Preconditions.checkPositionIndex(start, length);
        while (start < length) {
            if (matches(sequence.charAt(start))) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public String replaceFrom(CharSequence sequence, CharSequence replacement) {
        int length = replacement.length();
        if (length == 0) {
            return removeFrom(sequence);
        }
        int i = 0;
        if (length == 1) {
            return replaceFrom(sequence, replacement.charAt(0));
        }
        String string = sequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        int length2 = string.length();
        StringBuilder sb = new StringBuilder(((length2 * 3) / 2) + 16);
        do {
            sb.append((CharSequence) string, i, iIndexIn);
            sb.append(replacement);
            i = iIndexIn + 1;
            iIndexIn = indexIn(string, i);
        } while (iIndexIn != -1);
        sb.append((CharSequence) string, i, length2);
        return sb.toString();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class FastMatcher extends CharMatcher {
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @InlineMe(replacement = "this.matches(character)")
        @InlineMeValidationDisabled("While apply() is not final, the inlining is still safe because all known overrides of apply() call matches().")
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character character) {
            return super.apply(character);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NegatedFastMatcher extends Negated {
        public NegatedFastMatcher(CharMatcher original) {
            super(original);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }
}
