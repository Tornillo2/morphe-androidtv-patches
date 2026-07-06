package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class InitializerViewModelFactoryKt {
    public static final <VM extends ViewModel> void initializer(InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder, Function1<? super CreationExtras, ? extends VM> initializer) {
        Intrinsics.checkNotNullParameter(initializerViewModelFactoryBuilder, "<this>");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final ViewModelProvider.Factory viewModelFactory(@NotNull Function1<? super InitializerViewModelFactoryBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        builder.invoke(initializerViewModelFactoryBuilder);
        return initializerViewModelFactoryBuilder.build();
    }
}
