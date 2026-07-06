package kotlin.ranges;

import com.google.common.xml.XmlEscapers;
import java.util.Iterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CharProgression implements Iterable<Character>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion();
    public final char first;
    public final char last;
    public final int step;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final CharProgression fromClosedRange(char c, char c2, int i) {
            return new CharProgression(c, c2, i);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public CharProgression(char c, char c2, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.first = c;
        this.last = (char) ProgressionUtilKt.getProgressionLastElement((int) c, (int) c2, i);
        this.step = i;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CharProgression)) {
            return false;
        }
        if (isEmpty() && ((CharProgression) obj).isEmpty()) {
            return true;
        }
        CharProgression charProgression = (CharProgression) obj;
        return this.first == charProgression.first && this.last == charProgression.last && this.step == charProgression.step;
    }

    public final char getFirst() {
        return this.first;
    }

    public final char getLast() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.first * XmlEscapers.MAX_ASCII_CONTROL_CHAR) + this.last) * 31) + this.step;
    }

    public boolean isEmpty() {
        return this.step > 0 ? Intrinsics.compare((int) this.first, (int) this.last) > 0 : Intrinsics.compare((int) this.first, (int) this.last) < 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        int i;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append("..");
            sb.append(this.last);
            sb.append(" step ");
            i = this.step;
        } else {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append(" downTo ");
            sb.append(this.last);
            sb.append(" step ");
            i = -this.step;
        }
        sb.append(i);
        return sb.toString();
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Character> iterator() {
        return new CharProgressionIterator(this.first, this.last, this.step);
    }
}
