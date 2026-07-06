package com.google.common.graph;

import com.google.common.base.Supplier;
import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class InvalidatableSet<E> extends ForwardingSet<E> {
    public final Set<E> delegate;
    public final Supplier<String> errorMessage;
    public final Supplier<Boolean> validator;

    public InvalidatableSet(Set<E> delegate, Supplier<Boolean> validator, Supplier<String> errorMessage) {
        this.delegate = delegate;
        this.validator = validator;
        this.errorMessage = errorMessage;
    }

    public static <E> InvalidatableSet<E> of(Set<E> delegate, Supplier<Boolean> validator, Supplier<String> errorMessage) {
        delegate.getClass();
        validator.getClass();
        errorMessage.getClass();
        return new InvalidatableSet<>(delegate, validator, errorMessage);
    }

    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Object delegate() {
        validate();
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return this.delegate.hashCode();
    }

    public final void validate() {
        if (!this.validator.get().booleanValue()) {
            throw new IllegalStateException(this.errorMessage.get());
        }
    }

    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Collection delegate() {
        validate();
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Set<E> delegate() {
        validate();
        return this.delegate;
    }
}
