package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class MoreObjects {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ToStringHelper {
        public final String className;
        public final ValueHolder holderHead;
        public ValueHolder holderTail;
        public boolean omitEmptyValues;
        public boolean omitNullValues;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class UnconditionalValueHolder extends ValueHolder {
            public UnconditionalValueHolder() {
            }

            public UnconditionalValueHolder(AnonymousClass1 anonymousClass1) {
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class ValueHolder {
            public String name;
            public ValueHolder next;
            public Object value;
        }

        public static boolean isEmpty(Object value) {
            if (value instanceof CharSequence) {
                if (((CharSequence) value).length() != 0) {
                    return false;
                }
            } else {
                if (value instanceof Collection) {
                    return ((Collection) value).isEmpty();
                }
                if (value instanceof Map) {
                    return ((Map) value).isEmpty();
                }
                if (value instanceof Optional) {
                    return !((Optional) value).isPresent();
                }
                if (!value.getClass().isArray() || Array.getLength(value) != 0) {
                    return false;
                }
            }
            return true;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, Object value) {
            addHolder(name, value);
            return this;
        }

        public final ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        public final UnconditionalValueHolder addUnconditionalHolder() {
            UnconditionalValueHolder unconditionalValueHolder = new UnconditionalValueHolder();
            this.holderTail.next = unconditionalValueHolder;
            this.holderTail = unconditionalValueHolder;
            return unconditionalValueHolder;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitEmptyValues() {
            this.omitEmptyValues = true;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                r8 = this;
                boolean r0 = r8.omitNullValues
                boolean r1 = r8.omitEmptyValues
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = 32
                r2.<init>(r3)
                java.lang.String r3 = r8.className
                r2.append(r3)
                r3 = 123(0x7b, float:1.72E-43)
                r2.append(r3)
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r8.holderHead
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r3.next
                java.lang.String r4 = ""
            L1b:
                if (r3 == 0) goto L66
                java.lang.Object r5 = r3.value
                boolean r6 = r3 instanceof com.google.common.base.MoreObjects.ToStringHelper.UnconditionalValueHolder
                if (r6 != 0) goto L30
                if (r5 != 0) goto L28
                if (r0 != 0) goto L63
                goto L30
            L28:
                if (r1 == 0) goto L30
                boolean r6 = isEmpty(r5)
                if (r6 != 0) goto L63
            L30:
                r2.append(r4)
                java.lang.String r4 = r3.name
                if (r4 == 0) goto L3f
                r2.append(r4)
                r4 = 61
                r2.append(r4)
            L3f:
                if (r5 == 0) goto L5e
                java.lang.Class r4 = r5.getClass()
                boolean r4 = r4.isArray()
                if (r4 == 0) goto L5e
                r4 = 1
                java.lang.Object[] r6 = new java.lang.Object[r4]
                r7 = 0
                r6[r7] = r5
                java.lang.String r5 = java.util.Arrays.deepToString(r6)
                int r6 = r5.length()
                int r6 = r6 - r4
                r2.append(r5, r4, r6)
                goto L61
            L5e:
                r2.append(r5)
            L61:
                java.lang.String r4 = ", "
            L63:
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r3.next
                goto L1b
            L66:
                r0 = 125(0x7d, float:1.75E-43)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.MoreObjects.ToStringHelper.toString():java.lang.String");
        }

        public ToStringHelper(String className) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.omitEmptyValues = false;
            className.getClass();
            this.className = className;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, boolean value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, char value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, double value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public final ToStringHelper addHolder(Object value) {
            addHolder().value = value;
            return this;
        }

        @CanIgnoreReturnValue
        public final ToStringHelper addUnconditionalHolder(Object value) {
            addUnconditionalHolder().value = value;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, float value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, int value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public final ToStringHelper addHolder(String name, Object value) {
            ValueHolder valueHolderAddHolder = addHolder();
            valueHolderAddHolder.value = value;
            name.getClass();
            valueHolderAddHolder.name = name;
            return this;
        }

        @CanIgnoreReturnValue
        public final ToStringHelper addUnconditionalHolder(String name, Object value) {
            UnconditionalValueHolder unconditionalValueHolderAddUnconditionalHolder = addUnconditionalHolder();
            unconditionalValueHolderAddUnconditionalHolder.value = value;
            name.getClass();
            unconditionalValueHolderAddUnconditionalHolder.name = name;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, long value) {
            addUnconditionalHolder(name, String.valueOf(value));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long value) {
            addUnconditionalHolder().value = String.valueOf(value);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(Object value) {
            addHolder().value = value;
            return this;
        }
    }

    public static <T> T firstNonNull(T first, T second) {
        if (first != null) {
            return first;
        }
        if (second != null) {
            return second;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(self.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> clazz) {
        return new ToStringHelper(clazz.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String className) {
        return new ToStringHelper(className);
    }
}
