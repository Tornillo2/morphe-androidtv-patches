package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DelimitedRangesSequence implements Sequence<IntRange> {

    @NotNull
    public final Function2<CharSequence, Integer, Pair<Integer, Integer>> getNextMatch;

    @NotNull
    public final CharSequence input;
    public final int limit;
    public final int startIndex;

    /* JADX INFO: renamed from: kotlin.text.DelimitedRangesSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<IntRange>, KMappedMarker {
        public int counter;
        public int currentStartIndex;
        public IntRange nextItem;
        public int nextSearchIndex;
        public int nextState = -1;

        public AnonymousClass1() {
            int iCoerceIn = RangesKt___RangesKt.coerceIn(DelimitedRangesSequence.this.startIndex, 0, DelimitedRangesSequence.this.input.length());
            this.currentStartIndex = iCoerceIn;
            this.nextSearchIndex = iCoerceIn;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void calcNext() {
            /*
                r7 = this;
                int r0 = r7.nextSearchIndex
                r1 = 0
                if (r0 >= 0) goto Lb
                r7.nextState = r1
                r0 = 0
                r7.nextItem = r0
                return
            Lb:
                kotlin.text.DelimitedRangesSequence r2 = kotlin.text.DelimitedRangesSequence.this
                int r3 = r2.limit
                r4 = -1
                r5 = 1
                if (r3 <= 0) goto L1a
                int r6 = r7.counter
                int r6 = r6 + r5
                r7.counter = r6
                if (r6 >= r3) goto L22
            L1a:
                java.lang.CharSequence r2 = r2.input
                int r2 = r2.length()
                if (r0 <= r2) goto L36
            L22:
                kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
                int r1 = r7.currentStartIndex
                kotlin.text.DelimitedRangesSequence r2 = kotlin.text.DelimitedRangesSequence.this
                java.lang.CharSequence r2 = r2.input
                int r2 = kotlin.text.StringsKt__StringsKt.getLastIndex(r2)
                r0.<init>(r1, r2, r5)
                r7.nextItem = r0
                r7.nextSearchIndex = r4
                goto L7f
            L36:
                kotlin.text.DelimitedRangesSequence r0 = kotlin.text.DelimitedRangesSequence.this
                kotlin.jvm.functions.Function2<java.lang.CharSequence, java.lang.Integer, kotlin.Pair<java.lang.Integer, java.lang.Integer>> r2 = r0.getNextMatch
                java.lang.CharSequence r0 = r0.input
                int r3 = r7.nextSearchIndex
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                java.lang.Object r0 = r2.invoke(r0, r3)
                kotlin.Pair r0 = (kotlin.Pair) r0
                if (r0 != 0) goto L5e
                kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
                int r1 = r7.currentStartIndex
                kotlin.text.DelimitedRangesSequence r2 = kotlin.text.DelimitedRangesSequence.this
                java.lang.CharSequence r2 = r2.input
                int r2 = kotlin.text.StringsKt__StringsKt.getLastIndex(r2)
                r0.<init>(r1, r2, r5)
                r7.nextItem = r0
                r7.nextSearchIndex = r4
                goto L7f
            L5e:
                A r2 = r0.first
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                B r0 = r0.second
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r3 = r7.currentStartIndex
                kotlin.ranges.IntRange r3 = kotlin.ranges.RangesKt___RangesKt.until(r3, r2)
                r7.nextItem = r3
                int r2 = r2 + r0
                r7.currentStartIndex = r2
                if (r0 != 0) goto L7c
                r1 = 1
            L7c:
                int r2 = r2 + r1
                r7.nextSearchIndex = r2
            L7f:
                r7.nextState = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence.AnonymousClass1.calcNext():void");
        }

        public final int getCounter() {
            return this.counter;
        }

        public final int getCurrentStartIndex() {
            return this.currentStartIndex;
        }

        public final IntRange getNextItem() {
            return this.nextItem;
        }

        public final int getNextSearchIndex() {
            return this.nextSearchIndex;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState == -1) {
                calcNext();
            }
            return this.nextState == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setCounter(int i) {
            this.counter = i;
        }

        public final void setCurrentStartIndex(int i) {
            this.currentStartIndex = i;
        }

        public final void setNextItem(IntRange intRange) {
            this.nextItem = intRange;
        }

        public final void setNextSearchIndex(int i) {
            this.nextSearchIndex = i;
        }

        public final void setNextState(int i) {
            this.nextState = i;
        }

        @Override // java.util.Iterator
        public IntRange next() {
            if (this.nextState == -1) {
                calcNext();
            }
            if (this.nextState == 0) {
                throw new NoSuchElementException();
            }
            IntRange intRange = this.nextItem;
            Intrinsics.checkNotNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.nextItem = null;
            this.nextState = -1;
            return intRange;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DelimitedRangesSequence(@NotNull CharSequence input, int i, int i2, @NotNull Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> getNextMatch) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(getNextMatch, "getNextMatch");
        this.input = input;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = getNextMatch;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<IntRange> iterator() {
        return new AnonymousClass1();
    }
}
