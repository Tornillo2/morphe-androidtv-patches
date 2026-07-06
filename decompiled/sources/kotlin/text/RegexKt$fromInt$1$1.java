package kotlin.text;

import kotlin.jvm.functions.Function1;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class RegexKt$fromInt$1$1<T> implements Function1<T, Boolean> {
    public final /* synthetic */ int $value;

    public RegexKt$fromInt$1$1(int i) {
        this.$value = i;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/Boolean; */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(Enum r3) {
        FlagEnum flagEnum = (FlagEnum) r3;
        return Boolean.valueOf((this.$value & flagEnum.getMask()) == flagEnum.getValue());
    }
}
