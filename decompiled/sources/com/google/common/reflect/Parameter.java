package com.google.common.reflect;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Parameter implements AnnotatedElement {
    public final Object annotatedType;
    public final ImmutableList<Annotation> annotations;
    public final Invokable<?, ?> declaration;
    public final int position;
    public final TypeToken<?> type;

    public Parameter(Invokable<?, ?> declaration, int position, TypeToken<?> type, Annotation[] annotations, Object annotatedType) {
        this.declaration = declaration;
        this.position = position;
        this.type = type;
        this.annotations = ImmutableList.copyOf(annotations);
        this.annotatedType = annotatedType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Parameter) {
            Parameter parameter = (Parameter) obj;
            if (this.position == parameter.position && this.declaration.equals(parameter.declaration)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        annotationType.getClass();
        UnmodifiableIterator<Annotation> it = this.annotations.iterator();
        while (it.hasNext()) {
            Annotation next = it.next();
            if (annotationType.isInstance(next)) {
                return annotationType.cast(next);
            }
        }
        return null;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        return (A[]) getDeclaredAnnotationsByType(cls);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationType) {
        annotationType.getClass();
        return (A) FluentIterable.from(this.annotations).filter(annotationType).first().orNull();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return (Annotation[]) this.annotations.toArray(new Annotation[0]);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> cls) {
        return (A[]) ((Annotation[]) FluentIterable.from(this.annotations).filter(cls).toArray(cls));
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.position;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return getAnnotation(annotationType) != null;
    }

    public String toString() {
        return this.type + " arg" + this.position;
    }
}
