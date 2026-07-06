package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class EmojiInputFilter implements InputFilter {
    public EmojiCompat.InitCallback mInitCallback;
    public final TextView mTextView;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(19)
    public static class InitCallbackImpl extends EmojiCompat.InitCallback {
        public final Reference<EmojiInputFilter> mEmojiInputFilterReference;
        public final Reference<TextView> mViewRef;

        public InitCallbackImpl(TextView textView, EmojiInputFilter emojiInputFilter) {
            this.mViewRef = new WeakReference(textView);
            this.mEmojiInputFilterReference = new WeakReference(emojiInputFilter);
        }

        public final boolean isInputFilterCurrentlyRegisteredOnTextView(@Nullable TextView textView, @Nullable InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            CharSequence text;
            CharSequence charSequenceProcess;
            TextView textView = this.mViewRef.get();
            if (isInputFilterCurrentlyRegisteredOnTextView(textView, this.mEmojiInputFilterReference.get()) && textView.isAttachedToWindow() && text != (charSequenceProcess = EmojiCompat.get().process((text = textView.getText())))) {
                int selectionStart = Selection.getSelectionStart(charSequenceProcess);
                int selectionEnd = Selection.getSelectionEnd(charSequenceProcess);
                textView.setText(charSequenceProcess);
                if (charSequenceProcess instanceof Spannable) {
                    EmojiInputFilter.updateSelection((Spannable) charSequenceProcess, selectionStart, selectionEnd);
                }
            }
        }
    }

    public EmojiInputFilter(@NonNull TextView textView) {
        this.mTextView = textView;
    }

    public static void updateSelection(Spannable spannable, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            Selection.setSelection(spannable, i, i2);
        } else if (i >= 0) {
            Selection.setSelection(spannable, i);
        } else if (i2 >= 0) {
            Selection.setSelection(spannable, i2);
        }
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.mTextView.isInEditMode()) {
            return charSequence;
        }
        int loadState = EmojiCompat.get().getLoadState();
        if (loadState != 0) {
            if (loadState == 1) {
                if ((i4 == 0 && i3 == 0 && spanned.length() == 0 && charSequence == this.mTextView.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i != 0 || i2 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i, i2);
                }
                return EmojiCompat.get().process(charSequence, 0, charSequence.length());
            }
            if (loadState != 3) {
                return charSequence;
            }
        }
        EmojiCompat.get().registerInitCallback(getInitCallback());
        return charSequence;
    }

    public final EmojiCompat.InitCallback getInitCallback() {
        if (this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
        }
        return this.mInitCallback;
    }
}
