package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class Joiner {
    public final String separator;

    /* JADX INFO: renamed from: com.google.common.base.Joiner$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends Joiner {
        public final /* synthetic */ Joiner this$0;
        public final /* synthetic */ String val$nullText;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(final Joiner this$0, Joiner prototype, final String val$nullText) {
            super(prototype);
            this.val$nullText = val$nullText;
            this.this$0 = this$0;
        }

        @Override // com.google.common.base.Joiner
        public Joiner skipNulls() {
            throw new UnsupportedOperationException("already specified useForNull");
        }

        @Override // com.google.common.base.Joiner
        public CharSequence toString(Object part) {
            return part == null ? this.val$nullText : this.this$0.toString(part);
        }

        @Override // com.google.common.base.Joiner
        public Joiner useForNull(String nullText) {
            throw new UnsupportedOperationException("already specified useForNull");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MapJoiner {
        public final Joiner joiner;
        public final String keyValueSeparator;

        public /* synthetic */ MapJoiner(Joiner joiner, String str, AnonymousClass1 anonymousClass1) {
            this(joiner, str);
        }

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
            appendTo(appendable, map.entrySet());
            return appendable;
        }

        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        public MapJoiner useForNull(String nullText) {
            return new MapJoiner(this.joiner.useForNull(nullText), this.keyValueSeparator);
        }

        public MapJoiner(Joiner joiner, String keyValueSeparator) {
            this.joiner = joiner;
            keyValueSeparator.getClass();
            this.keyValueSeparator = keyValueSeparator;
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
            appendTo(builder, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
            return builder;
        }

        public String join(Iterable<? extends Map.Entry<?, ?>> entries) {
            return join(entries.iterator());
        }

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?, ?>> entries) throws IOException {
            appendTo(appendable, entries.iterator());
            return appendable;
        }

        public String join(Iterator<? extends Map.Entry<?, ?>> entries) {
            StringBuilder sb = new StringBuilder();
            appendTo(sb, entries);
            return sb.toString();
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Map.Entry<?, ?>> entries) {
            appendTo(builder, entries.iterator());
            return builder;
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Map.Entry<?, ?>> entries) {
            try {
                appendTo(builder, entries);
                return builder;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?, ?>> parts) throws IOException {
            appendable.getClass();
            if (parts.hasNext()) {
                Map.Entry<?, ?> next = parts.next();
                appendable.append(this.joiner.toString(next.getKey()));
                appendable.append(this.keyValueSeparator);
                appendable.append(this.joiner.toString(next.getValue()));
                while (parts.hasNext()) {
                    appendable.append(this.joiner.separator);
                    Map.Entry<?, ?> next2 = parts.next();
                    appendable.append(this.joiner.toString(next2.getKey()));
                    appendable.append(this.keyValueSeparator);
                    appendable.append(this.joiner.toString(next2.getValue()));
                }
            }
            return appendable;
        }
    }

    public /* synthetic */ Joiner(Joiner joiner, AnonymousClass1 anonymousClass1) {
        this(joiner);
    }

    public static int expandedCapacity(int oldCapacity, int minCapacity) {
        if (minCapacity < 0) {
            throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
        }
        if (minCapacity <= oldCapacity) {
            return oldCapacity;
        }
        int iHighestOneBit = oldCapacity + (oldCapacity >> 1) + 1;
        if (iHighestOneBit < minCapacity) {
            iHighestOneBit = Integer.highestOneBit(minCapacity - 1) << 1;
        }
        if (iHighestOneBit < 0) {
            return Integer.MAX_VALUE;
        }
        return iHighestOneBit;
    }

    public static Iterable<Object> iterable(final Object first, final Object second, final Object[] rest) {
        rest.getClass();
        return new AbstractList<Object>() { // from class: com.google.common.base.Joiner.3
            @Override // java.util.AbstractList, java.util.List
            public Object get(int index) {
                return index != 0 ? index != 1 ? rest[index - 2] : second : first;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return rest.length + 2;
            }
        };
    }

    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a, Iterable<?> iterable) throws IOException {
        return (A) appendTo(a, iterable.iterator());
    }

    public String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    public Joiner skipNulls() {
        return new Joiner(this) { // from class: com.google.common.base.Joiner.2
            @Override // com.google.common.base.Joiner
            public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
                Preconditions.checkNotNull(appendable, "appendable");
                Preconditions.checkNotNull(parts, "parts");
                while (true) {
                    if (!parts.hasNext()) {
                        break;
                    }
                    Object next = parts.next();
                    if (next != null) {
                        appendable.append(Joiner.this.toString(next));
                        break;
                    }
                }
                while (parts.hasNext()) {
                    Object next2 = parts.next();
                    if (next2 != null) {
                        appendable.append(Joiner.this.separator);
                        appendable.append(Joiner.this.toString(next2));
                    }
                }
                return appendable;
            }

            @Override // com.google.common.base.Joiner
            public String join(Iterable<?> parts) {
                return join(parts.iterator());
            }

            @Override // com.google.common.base.Joiner
            public Joiner useForNull(String nullText) {
                throw new UnsupportedOperationException("already specified skipNulls");
            }

            @Override // com.google.common.base.Joiner
            public MapJoiner withKeyValueSeparator(String kvs) {
                throw new UnsupportedOperationException("can't use .skipNulls() with maps");
            }
        };
    }

    public CharSequence toString(Object part) {
        j$.util.Objects.requireNonNull(part);
        return part instanceof CharSequence ? (CharSequence) part : part.toString();
    }

    public Joiner useForNull(String nullText) {
        nullText.getClass();
        return new AnonymousClass1(this, this, nullText);
    }

    public MapJoiner withKeyValueSeparator(char keyValueSeparator) {
        return withKeyValueSeparator(String.valueOf(keyValueSeparator));
    }

    public Joiner(String separator) {
        separator.getClass();
        this.separator = separator;
    }

    public static Joiner on(char separator) {
        return new Joiner(String.valueOf(separator));
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a, Object[] objArr) throws IOException {
        return (A) appendTo(a, Arrays.asList(objArr));
    }

    public final String join(Iterator<?> parts) {
        StringBuilder sb = new StringBuilder();
        appendTo(sb, parts);
        return sb.toString();
    }

    public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
        return new MapJoiner(this, keyValueSeparator);
    }

    public final String join(Object[] parts) {
        return join(Arrays.asList(parts));
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a, Object obj, Object obj2, Object... objArr) throws IOException {
        return (A) appendTo(a, iterable(obj, obj2, objArr));
    }

    public Joiner(Joiner prototype) {
        this.separator = prototype.separator;
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
        appendTo(builder, parts.iterator());
        return builder;
    }

    public final String join(Object first, Object second, Object... rest) {
        return join(iterable(first, second, rest));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo(builder, parts);
            return builder;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
        appendTo(builder, (Iterable<?>) Arrays.asList(parts));
        return builder;
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder builder, Object first, Object second, Object... rest) {
        appendTo(builder, ((AbstractList) iterable(first, second, rest)).iterator());
        return builder;
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        appendable.getClass();
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(this.separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }
}
