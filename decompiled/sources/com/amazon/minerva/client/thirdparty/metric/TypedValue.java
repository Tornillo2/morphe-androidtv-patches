package com.amazon.minerva.client.thirdparty.metric;

import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TypedValue<T> {
    public ValueType type;
    public T value;

    public TypedValue(ValueType valueType, T t) {
        Objects.requireNonNull(valueType, "type can not be null.");
        Objects.requireNonNull(t, "value can not be null.");
        if (valueType.getCompatibleClass().isInstance(t)) {
            this.type = valueType;
            this.value = t;
            return;
        }
        throw new IllegalArgumentException("value (" + t + ") is not compatible with the type, type:" + valueType + " (" + valueType.getCompatibleClass() + ")");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TypedValue typedValue = (TypedValue) obj;
            if (Objects.equals(this.type, typedValue.type) && Objects.equals(this.value, typedValue.value)) {
                return true;
            }
        }
        return false;
    }

    public int getSizeInBytes() {
        return this.type.getSizeInBytes(this.value);
    }

    public ValueType getType() {
        return this.type;
    }

    public T getValue() {
        return this.value;
    }

    public String getValueAsString() {
        return this.type.toString(this.value);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.value);
    }

    public boolean isEmpty() {
        return this.type.isEmpty(this.value);
    }

    public String toString() {
        return "<" + this.type + ">" + this.value;
    }
}
