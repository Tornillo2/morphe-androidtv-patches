package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import j$.util.Optional;
import j$.util.stream.Collector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
@IgnoreJRERequirement
public final class MoreCollectors {
    public static final Object NULL_PLACEHOLDER;
    public static final Collector<Object, ?, Object> ONLY_ELEMENT;
    public static final Collector<Object, ?, Optional<Object>> TO_OPTIONAL;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ToOptionalState {
        public static final int MAX_EXTRAS = 4;
        public Object element = null;
        public List<Object> extras = Collections.EMPTY_LIST;

        public void add(Object o) {
            o.getClass();
            if (this.element == null) {
                this.element = o;
                return;
            }
            if (this.extras.isEmpty()) {
                ArrayList arrayList = new ArrayList(4);
                this.extras = arrayList;
                arrayList.add(o);
            } else if (this.extras.size() < 4) {
                this.extras.add(o);
            } else {
                multiples(true);
                throw null;
            }
        }

        public ToOptionalState combine(ToOptionalState other) {
            if (this.element == null) {
                return other;
            }
            if (other.element != null) {
                if (this.extras.isEmpty()) {
                    this.extras = new ArrayList();
                }
                this.extras.add(other.element);
                this.extras.addAll(other.extras);
                if (this.extras.size() > 4) {
                    List<Object> list = this.extras;
                    list.subList(4, list.size()).clear();
                    multiples(true);
                    throw null;
                }
            }
            return this;
        }

        public Object getElement() {
            if (this.element == null) {
                throw new NoSuchElementException();
            }
            if (this.extras.isEmpty()) {
                return this.element;
            }
            multiples(false);
            throw null;
        }

        @IgnoreJRERequirement
        public Optional<Object> getOptional() {
            if (this.extras.isEmpty()) {
                return Optional.ofNullable(this.element);
            }
            multiples(false);
            throw null;
        }

        public IllegalArgumentException multiples(boolean overflow) {
            StringBuilder sb = new StringBuilder("expected one element but was: <");
            sb.append(this.element);
            for (Object obj : this.extras) {
                sb.append(", ");
                sb.append(obj);
            }
            if (overflow) {
                sb.append(", ...");
            }
            sb.append('>');
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$9X5rX0l5R2Beg_-YE-Mtov6cqU8, reason: not valid java name */
    public static /* synthetic */ void m520$r8$lambda$9X5rX0l5R2Beg_YEMtov6cqU8(ToOptionalState toOptionalState, Object obj) {
        if (obj == null) {
            obj = NULL_PLACEHOLDER;
        }
        toOptionalState.add(obj);
    }

    /* JADX INFO: renamed from: $r8$lambda$T8w15ZuYonXg5iTgVE-TfpFy7SQ, reason: not valid java name */
    public static /* synthetic */ Object m521$r8$lambda$T8w15ZuYonXg5iTgVETfpFy7SQ(ToOptionalState toOptionalState) {
        Object element = toOptionalState.getElement();
        if (element == NULL_PLACEHOLDER) {
            return null;
        }
        return element;
    }

    static {
        MoreCollectors$$ExternalSyntheticLambda0 moreCollectors$$ExternalSyntheticLambda0 = new MoreCollectors$$ExternalSyntheticLambda0();
        MoreCollectors$$ExternalSyntheticLambda1 moreCollectors$$ExternalSyntheticLambda1 = new MoreCollectors$$ExternalSyntheticLambda1();
        MoreCollectors$$ExternalSyntheticLambda2 moreCollectors$$ExternalSyntheticLambda2 = new MoreCollectors$$ExternalSyntheticLambda2();
        MoreCollectors$$ExternalSyntheticLambda3 moreCollectors$$ExternalSyntheticLambda3 = new MoreCollectors$$ExternalSyntheticLambda3();
        Collector.Characteristics characteristics = Collector.Characteristics.UNORDERED;
        TO_OPTIONAL = Collector.CC.of(moreCollectors$$ExternalSyntheticLambda0, moreCollectors$$ExternalSyntheticLambda1, moreCollectors$$ExternalSyntheticLambda2, moreCollectors$$ExternalSyntheticLambda3, characteristics);
        NULL_PLACEHOLDER = new Object();
        ONLY_ELEMENT = Collector.CC.of(new MoreCollectors$$ExternalSyntheticLambda0(), new MoreCollectors$$ExternalSyntheticLambda4(), new MoreCollectors$$ExternalSyntheticLambda2(), new MoreCollectors$$ExternalSyntheticLambda5(), characteristics);
    }

    public static <T> Collector<T, ?, T> onlyElement() {
        return (Collector<T, ?, T>) ONLY_ELEMENT;
    }

    public static <T> Collector<T, ?, Optional<T>> toOptional() {
        return (Collector<T, ?, Optional<T>>) TO_OPTIONAL;
    }
}
