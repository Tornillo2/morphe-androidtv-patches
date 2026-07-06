package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use ImmutableClassToInstanceMap or MutableClassToInstanceMap")
@GwtCompatible
public interface ClassToInstanceMap<B> extends Map<Class<? extends B>, B> {
    <T extends B> T getInstance(Class<T> type);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> type, @ParametricNullness T value);
}
