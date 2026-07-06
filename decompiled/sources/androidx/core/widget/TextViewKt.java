package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nTextView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TextView.kt\nandroidx/core/widget/TextViewKt\n*L\n1#1,88:1\n55#1,12:89\n84#1,3:101\n55#1,12:104\n84#1,3:116\n55#1,12:119\n84#1,3:131\n*S KotlinDebug\n*F\n+ 1 TextView.kt\nandroidx/core/widget/TextViewKt\n*L\n30#1:89,12\n30#1:101,3\n39#1:104,12\n39#1:116,3\n48#1:119,12\n48#1:131,3\n*E\n"})
public final class TextViewKt {

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function4<CharSequence, Integer, Integer, Integer, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(4);

        public AnonymousClass1() {
            super(4);
        }

        public final void invoke(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(CharSequence charSequence, Integer num, Integer num2, Integer num3) {
            num.intValue();
            num2.intValue();
            num3.intValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 extends Lambda implements Function4<CharSequence, Integer, Integer, Integer, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2(4);

        public AnonymousClass2() {
            super(4);
        }

        public final void invoke(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(CharSequence charSequence, Integer num, Integer num2, Integer num3) {
            num.intValue();
            num2.intValue();
            num3.intValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.widget.TextViewKt$addTextChangedListener$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3 extends Lambda implements Function1<Editable, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3(1);

        public AnonymousClass3() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Editable editable) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Editable editable) {
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public static final TextWatcher addTextChangedListener(@NotNull TextView textView, @NotNull Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4, @NotNull Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function42, @NotNull Function1<? super Editable, Unit> function1) {
        TextViewKt$addTextChangedListener$textWatcher$1 textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, function4, function42);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    public static /* synthetic */ TextWatcher addTextChangedListener$default(TextView textView, Function4 function4, Function4 function42, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function4 = AnonymousClass1.INSTANCE;
        }
        if ((i & 2) != 0) {
            function42 = AnonymousClass2.INSTANCE;
        }
        if ((i & 4) != 0) {
            function1 = AnonymousClass3.INSTANCE;
        }
        TextViewKt$addTextChangedListener$textWatcher$1 textViewKt$addTextChangedListener$textWatcher$1 = new TextViewKt$addTextChangedListener$textWatcher$1(function1, function4, function42);
        textView.addTextChangedListener(textViewKt$addTextChangedListener$textWatcher$1);
        return textViewKt$addTextChangedListener$textWatcher$1;
    }

    @NotNull
    public static final TextWatcher doAfterTextChanged(@NotNull TextView textView, @NotNull final Function1<? super Editable, Unit> function1) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                function1.invoke(editable);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    @NotNull
    public static final TextWatcher doBeforeTextChanged(@NotNull TextView textView, @NotNull final Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                function4.invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    @NotNull
    public static final TextWatcher doOnTextChanged(@NotNull TextView textView, @NotNull final Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> function4) {
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.core.widget.TextViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                function4.invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }
}
