package com.google.common.reflect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ImmutableTypeToInstanceMap or MutableTypeToInstanceMap")
public interface TypeToInstanceMap<B> extends Map<TypeToken<? extends B>, B> {
    <T extends B> T getInstance(TypeToken<T> type);

    <T extends B> T getInstance(Class<T> type);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(TypeToken<T> type, @ParametricNullness T value);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> type, @ParametricNullness T value);
}
