package org.apache.commons.lang3.builder;

import j$.util.DesugarCollections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DiffResult implements Iterable<Diff<?>> {
    public static final String DIFFERS_STRING = "differs from";
    public static final String OBJECTS_SAME_STRING = "";
    public final List<Diff<?>> diffs;
    public final Object lhs;
    public final Object rhs;
    public final ToStringStyle style;

    public DiffResult(Object obj, Object obj2, List<Diff<?>> list, ToStringStyle toStringStyle) {
        Validate.isTrue(obj != null, "Left hand object cannot be null", new Object[0]);
        Validate.isTrue(obj2 != null, "Right hand object cannot be null", new Object[0]);
        Validate.isTrue(list != null, "List of differences cannot be null", new Object[0]);
        this.diffs = list;
        this.lhs = obj;
        this.rhs = obj2;
        if (toStringStyle == null) {
            this.style = ToStringStyle.DEFAULT_STYLE;
        } else {
            this.style = toStringStyle;
        }
    }

    public List<Diff<?>> getDiffs() {
        return DesugarCollections.unmodifiableList(this.diffs);
    }

    public int getNumberOfDiffs() {
        return this.diffs.size();
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    @Override // java.lang.Iterable
    public Iterator<Diff<?>> iterator() {
        return this.diffs.iterator();
    }

    public String toString() {
        return toString(this.style);
    }

    public String toString(ToStringStyle toStringStyle) {
        if (this.diffs.isEmpty()) {
            return "";
        }
        ToStringBuilder toStringBuilder = new ToStringBuilder(this.lhs, toStringStyle, null);
        ToStringBuilder toStringBuilder2 = new ToStringBuilder(this.rhs, toStringStyle, null);
        for (Diff<?> diff : this.diffs) {
            toStringBuilder.append(diff.fieldName, diff.getLeft());
            toStringBuilder2.append(diff.fieldName, diff.getRight());
        }
        return String.format("%s %s %s", toStringBuilder.toString(), DIFFERS_STRING, toStringBuilder2.toString());
    }
}
