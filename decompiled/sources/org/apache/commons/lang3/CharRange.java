package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CharRange implements Iterable<Character>, Serializable {
    public static final long serialVersionUID = 8270183163158333422L;
    public final char end;
    public transient String iToString;
    public final boolean negated;
    public final char start;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CharacterIterator implements Iterator<Character> {
        public char current;
        public boolean hasNext;
        public final CharRange range;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        public final void prepareNext() {
            CharRange charRange = this.range;
            if (!charRange.negated) {
                char c = this.current;
                if (c < charRange.end) {
                    this.current = (char) (c + 1);
                    return;
                } else {
                    this.hasNext = false;
                    return;
                }
            }
            char c2 = this.current;
            if (c2 == 65535) {
                this.hasNext = false;
                return;
            }
            if (c2 + 1 != charRange.start) {
                this.current = (char) (c2 + 1);
                return;
            }
            char c3 = charRange.end;
            if (c3 == 65535) {
                this.hasNext = false;
            } else {
                this.current = (char) (c3 + 1);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public CharacterIterator(CharRange charRange) {
            this.range = charRange;
            this.hasNext = true;
            if (!charRange.negated) {
                this.current = charRange.start;
                return;
            }
            if (charRange.start != 0) {
                this.current = (char) 0;
                return;
            }
            char c = charRange.end;
            if (c == 65535) {
                this.hasNext = false;
            } else {
                this.current = (char) (c + 1);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Character next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            char c = this.current;
            prepareNext();
            return Character.valueOf(c);
        }
    }

    public CharRange(char c, char c2, boolean z) {
        if (c > c2) {
            c2 = c;
            c = c2;
        }
        this.start = c;
        this.end = c2;
        this.negated = z;
    }

    public static CharRange is(char c) {
        return new CharRange(c, c, false);
    }

    public static CharRange isIn(char c, char c2) {
        return new CharRange(c, c2, false);
    }

    public static CharRange isNot(char c) {
        return new CharRange(c, c, true);
    }

    public static CharRange isNotIn(char c, char c2) {
        return new CharRange(c, c2, true);
    }

    public boolean contains(char c) {
        return (c >= this.start && c <= this.end) != this.negated;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange charRange = (CharRange) obj;
        return this.start == charRange.start && this.end == charRange.end && this.negated == charRange.negated;
    }

    public char getEnd() {
        return this.end;
    }

    public char getStart() {
        return this.start;
    }

    public int hashCode() {
        return (this.end * 7) + this.start + 'S' + (this.negated ? 1 : 0);
    }

    public boolean isNegated() {
        return this.negated;
    }

    @Override // java.lang.Iterable
    public Iterator<Character> iterator() {
        return new CharacterIterator(this);
    }

    public String toString() {
        if (this.iToString == null) {
            StringBuilder sb = new StringBuilder(4);
            if (this.negated) {
                sb.append('^');
            }
            sb.append(this.start);
            if (this.start != this.end) {
                sb.append('-');
                sb.append(this.end);
            }
            this.iToString = sb.toString();
        }
        return this.iToString;
    }

    public boolean contains(CharRange charRange) {
        Validate.isTrue(charRange != null, "The Range must not be null", new Object[0]);
        return this.negated ? charRange.negated ? this.start >= charRange.start && this.end <= charRange.end : charRange.end < this.start || charRange.start > this.end : charRange.negated ? this.start == 0 && this.end == 65535 : this.start <= charRange.start && this.end >= charRange.end;
    }
}
