package kotlin.text;

import java.util.Iterator;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.sequences.TransformingSequence;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {
    public final /* synthetic */ MatcherMatchResult this$0;

    public MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof MatchGroup) {
            return super.contains(obj);
        }
        return false;
    }

    @Override // kotlin.text.MatchGroupCollection
    public MatchGroup get(int i) {
        IntRange intRangeRange = RegexKt.range(this.this$0.matcher, i);
        if (intRangeRange.first < 0) {
            return null;
        }
        String strGroup = this.this$0.matcher.group(i);
        Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
        return new MatchGroup(strGroup, intRangeRange);
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.this$0.matcher.groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<MatchGroup> iterator() {
        return new TransformingSequence.AnonymousClass1((TransformingSequence) SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(CollectionsKt__CollectionsKt.getIndices(this)), new Function1() { // from class: kotlin.text.MatcherMatchResult$groups$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.get(((Integer) obj).intValue());
            }
        }));
    }

    public /* bridge */ boolean contains(MatchGroup matchGroup) {
        return super.contains((Object) matchGroup);
    }

    @Override // kotlin.text.MatchNamedGroupCollection
    public MatchGroup get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return PlatformImplementationsKt.IMPLEMENTATIONS.getMatchResultNamedGroup(this.this$0.matcher, name);
    }
}
