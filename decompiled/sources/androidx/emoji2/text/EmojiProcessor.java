package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@AnyThread
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class EmojiProcessor {
    public static final int ACTION_ADVANCE_BOTH = 1;
    public static final int ACTION_ADVANCE_END = 2;
    public static final int ACTION_FLUSH = 3;
    public static final int MAX_LOOK_AROUND_CHARACTER = 16;

    @Nullable
    public final int[] mEmojiAsDefaultStyleExceptions;

    @NonNull
    public EmojiCompat.GlyphChecker mGlyphChecker;

    @NonNull
    public final MetadataRepo mMetadataRepo;

    @NonNull
    public final EmojiCompat.SpanFactory mSpanFactory;
    public final boolean mUseEmojiAsDefaultStyle;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(19)
    public static final class CodepointIndexFinder {
        public static final int INVALID_INDEX = -1;

        public static int findIndexBackward(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    i--;
                    if (i < 0) {
                        return z ? -1 : 0;
                    }
                    char cCharAt = charSequence.charAt(i);
                    if (z) {
                        if (!Character.isHighSurrogate(cCharAt)) {
                            return -1;
                        }
                        i2--;
                    } else if (!Character.isSurrogate(cCharAt)) {
                        i2--;
                    } else {
                        if (Character.isHighSurrogate(cCharAt)) {
                            return -1;
                        }
                        z = true;
                    }
                }
                return i;
            }
        }

        public static int findIndexForward(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    if (i >= length) {
                        if (z) {
                            return -1;
                        }
                        return length;
                    }
                    char cCharAt = charSequence.charAt(i);
                    if (z) {
                        if (!Character.isLowSurrogate(cCharAt)) {
                            return -1;
                        }
                        i2--;
                        i++;
                    } else if (!Character.isSurrogate(cCharAt)) {
                        i2--;
                        i++;
                    } else {
                        if (Character.isLowSurrogate(cCharAt)) {
                            return -1;
                        }
                        i++;
                        z = true;
                    }
                }
                return i;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EmojiProcessAddSpanCallback implements EmojiProcessCallback<UnprecomputeTextOnModificationSpannable> {
        public final EmojiCompat.SpanFactory mSpanFactory;

        @Nullable
        public UnprecomputeTextOnModificationSpannable spannable;

        public EmojiProcessAddSpanCallback(@Nullable UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.SpanFactory spanFactory) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = spanFactory;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean handleEmoji(@NonNull CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (typefaceEmojiRasterizer.isPreferredSystemRender()) {
                return true;
            }
            if (this.spannable == null) {
                this.spannable = new UnprecomputeTextOnModificationSpannable(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
            }
            this.spannable.setSpan(this.mSpanFactory.createSpan(typefaceEmojiRasterizer), i, i2, 33);
            return true;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.spannable;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface EmojiProcessCallback<T> {
        T getResult();

        boolean handleEmoji(@NonNull CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EmojiProcessLookupCallback implements EmojiProcessCallback<EmojiProcessLookupCallback> {
        public final int mOffset;
        public int start = -1;
        public int end = -1;

        public EmojiProcessLookupCallback(int i) {
            this.mOffset = i;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public EmojiProcessLookupCallback getResult() {
            return this;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean handleEmoji(@NonNull CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            int i3 = this.mOffset;
            if (i > i3 || i3 >= i2) {
                return i2 <= i3;
            }
            this.start = i;
            this.end = i2;
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MarkExclusionCallback implements EmojiProcessCallback<MarkExclusionCallback> {
        public final String mExclusion;

        public MarkExclusionCallback(String str) {
            this.mExclusion = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public MarkExclusionCallback getResult() {
            return this;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean handleEmoji(@NonNull CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (!TextUtils.equals(charSequence.subSequence(i, i2), this.mExclusion)) {
                return true;
            }
            typefaceEmojiRasterizer.setExclusion(true);
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ProcessorSm {
        public static final int STATE_DEFAULT = 1;
        public static final int STATE_WALKING = 2;
        public int mCurrentDepth;
        public MetadataRepo.Node mCurrentNode;
        public final int[] mEmojiAsDefaultStyleExceptions;
        public MetadataRepo.Node mFlushNode;
        public int mLastCodepoint;
        public final MetadataRepo.Node mRootNode;
        public int mState = 1;
        public final boolean mUseEmojiAsDefaultStyle;

        public ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.mRootNode = node;
            this.mCurrentNode = node;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = iArr;
        }

        public static boolean isEmojiStyle(int i) {
            return i == 65039;
        }

        public static boolean isTextStyle(int i) {
            return i == 65038;
        }

        public int check(int i) {
            MetadataRepo.Node node = this.mCurrentNode.get(i);
            int i2 = 1;
            if (this.mState == 2) {
                if (node != null) {
                    this.mCurrentNode = node;
                    this.mCurrentDepth++;
                } else if (isTextStyle(i)) {
                    reset();
                } else if (!isEmojiStyle(i)) {
                    MetadataRepo.Node node2 = this.mCurrentNode;
                    if (node2.mData != null) {
                        if (this.mCurrentDepth != 1) {
                            this.mFlushNode = node2;
                            reset();
                        } else if (shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                            this.mFlushNode = this.mCurrentNode;
                            reset();
                        } else {
                            reset();
                        }
                        i2 = 3;
                    } else {
                        reset();
                    }
                }
                i2 = 2;
            } else if (node == null) {
                reset();
            } else {
                this.mState = 2;
                this.mCurrentNode = node;
                this.mCurrentDepth = 1;
                i2 = 2;
            }
            this.mLastCodepoint = i;
            return i2;
        }

        public TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.mData;
        }

        public TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.mData;
        }

        public boolean isInFlushableState() {
            if (this.mState != 2 || this.mCurrentNode.mData == null) {
                return false;
            }
            return this.mCurrentDepth > 1 || shouldUseEmojiPresentationStyleForSingleCodepoint();
        }

        public final int reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
            return 1;
        }

        public final boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            if (this.mCurrentNode.mData.isDefaultEmoji() || isEmojiStyle(this.mLastCodepoint)) {
                return true;
            }
            if (this.mUseEmojiAsDefaultStyle) {
                if (this.mEmojiAsDefaultStyleExceptions == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, this.mCurrentNode.mData.getCodepointAt(0)) < 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public EmojiProcessor(@NonNull MetadataRepo metadataRepo, @NonNull EmojiCompat.SpanFactory spanFactory, @NonNull EmojiCompat.GlyphChecker glyphChecker, boolean z, @Nullable int[] iArr, @NonNull Set<int[]> set) {
        this.mSpanFactory = spanFactory;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = glyphChecker;
        this.mUseEmojiAsDefaultStyle = z;
        this.mEmojiAsDefaultStyleExceptions = iArr;
        initExclusions(set);
    }

    public static boolean delete(@NonNull Editable editable, @NonNull KeyEvent keyEvent, boolean z) {
        EmojiSpan[] emojiSpanArr;
        if (hasModifiers(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!hasInvalidSelection(selectionStart, selectionEnd) && (emojiSpanArr = (EmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, EmojiSpan.class)) != null && emojiSpanArr.length > 0) {
            for (EmojiSpan emojiSpan : emojiSpanArr) {
                int spanStart = editable.getSpanStart(emojiSpan);
                int spanEnd = editable.getSpanEnd(emojiSpan);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean handleDeleteSurroundingText(@NonNull InputConnection inputConnection, @NonNull Editable editable, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, boolean z) {
        int iMax;
        int iMin;
        if (editable != null && inputConnection != null && i >= 0 && i2 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (hasInvalidSelection(selectionStart, selectionEnd)) {
                return false;
            }
            if (z) {
                iMax = CodepointIndexFinder.findIndexBackward(editable, selectionStart, Math.max(i, 0));
                iMin = CodepointIndexFinder.findIndexForward(editable, selectionEnd, Math.max(i2, 0));
                if (iMax == -1 || iMin == -1) {
                    return false;
                }
            } else {
                iMax = Math.max(selectionStart - i, 0);
                iMin = Math.min(selectionEnd + i2, editable.length());
            }
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) editable.getSpans(iMax, iMin, EmojiSpan.class);
            if (emojiSpanArr != null && emojiSpanArr.length > 0) {
                for (EmojiSpan emojiSpan : emojiSpanArr) {
                    int spanStart = editable.getSpanStart(emojiSpan);
                    int spanEnd = editable.getSpanEnd(emojiSpan);
                    iMax = Math.min(spanStart, iMax);
                    iMin = Math.max(spanEnd, iMin);
                }
                int iMax2 = Math.max(iMax, 0);
                int iMin2 = Math.min(iMin, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(iMax2, iMin2);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    public static boolean handleOnKeyDown(@NonNull Editable editable, int i, @NonNull KeyEvent keyEvent) {
        if (!(i != 67 ? i != 112 ? false : delete(editable, keyEvent, true) : delete(editable, keyEvent, false))) {
            return false;
        }
        MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
        return true;
    }

    public static boolean hasInvalidSelection(int i, int i2) {
        return i == -1 || i2 == -1 || i != i2;
    }

    public static boolean hasModifiers(@NonNull KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    public int getEmojiEnd(@NonNull CharSequence charSequence, @IntRange(from = 0) int i) {
        if (i < 0 || i >= charSequence.length()) {
            return -1;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) spanned.getSpans(i, i + 1, EmojiSpan.class);
            if (emojiSpanArr.length > 0) {
                return spanned.getSpanEnd(emojiSpanArr[0]);
            }
        }
        return ((EmojiProcessLookupCallback) process(charSequence, Math.max(0, i - 16), Math.min(charSequence.length(), i + 16), Integer.MAX_VALUE, true, new EmojiProcessLookupCallback(i))).end;
    }

    public int getEmojiMatch(@NonNull CharSequence charSequence) {
        return getEmojiMatch(charSequence, this.mMetadataRepo.mMetadataList.version());
    }

    public int getEmojiStart(@NonNull CharSequence charSequence, @IntRange(from = 0) int i) {
        if (i < 0 || i >= charSequence.length()) {
            return -1;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) spanned.getSpans(i, i + 1, EmojiSpan.class);
            if (emojiSpanArr.length > 0) {
                return spanned.getSpanStart(emojiSpanArr[0]);
            }
        }
        return ((EmojiProcessLookupCallback) process(charSequence, Math.max(0, i - 16), Math.min(charSequence.length(), i + 16), Integer.MAX_VALUE, true, new EmojiProcessLookupCallback(i))).start;
    }

    public final boolean hasGlyph(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (typefaceEmojiRasterizer.getHasGlyph() == 0) {
            typefaceEmojiRasterizer.setHasGlyph(this.mGlyphChecker.hasGlyph(charSequence, i, i2, typefaceEmojiRasterizer.getSdkAdded()));
        }
        return typefaceEmojiRasterizer.getHasGlyph() == 2;
    }

    public final void initExclusions(@NonNull Set<int[]> set) {
        if (set.isEmpty()) {
            return;
        }
        for (int[] iArr : set) {
            String str = new String(iArr, 0, iArr.length);
            process(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c A[Catch: all -> 0x002a, TRY_ENTER, TryCatch #2 {all -> 0x002a, blocks: (B:7:0x000e, B:10:0x0013, B:12:0x0017, B:14:0x0024, B:22:0x003c, B:24:0x0044, B:26:0x0047, B:28:0x004b, B:30:0x0057, B:31:0x005a, B:41:0x0078), top: B:70:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b A[Catch: all -> 0x002a, TryCatch #2 {all -> 0x002a, blocks: (B:7:0x000e, B:10:0x0013, B:12:0x0017, B:14:0x0024, B:22:0x003c, B:24:0x0044, B:26:0x0047, B:28:0x004b, B:30:0x0057, B:31:0x005a, B:41:0x0078), top: B:70:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0069 A[Catch: all -> 0x00b0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00b0, blocks: (B:35:0x0069, B:44:0x0085, B:19:0x0031), top: B:66:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.CharSequence process(@androidx.annotation.NonNull java.lang.CharSequence r11, @androidx.annotation.IntRange(from = 0) int r12, @androidx.annotation.IntRange(from = 0) int r13, @androidx.annotation.IntRange(from = 0) int r14, boolean r15) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r1 = r11 instanceof androidx.emoji2.text.SpannableBuilder
            if (r1 == 0) goto La
            r0 = r11
            androidx.emoji2.text.SpannableBuilder r0 = (androidx.emoji2.text.SpannableBuilder) r0
            r0.blockWatchers()
        La:
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r0 = androidx.emoji2.text.EmojiSpan.class
            if (r1 != 0) goto L31
            boolean r2 = r11 instanceof android.text.Spannable     // Catch: java.lang.Throwable -> L2a
            if (r2 == 0) goto L13
            goto L31
        L13:
            boolean r2 = r11 instanceof android.text.Spanned     // Catch: java.lang.Throwable -> L2a
            if (r2 == 0) goto L2f
            r2 = r11
            android.text.Spanned r2 = (android.text.Spanned) r2     // Catch: java.lang.Throwable -> L2a
            int r3 = r12 + (-1)
            int r4 = r13 + 1
            int r2 = r2.nextSpanTransition(r3, r4, r0)     // Catch: java.lang.Throwable -> L2a
            if (r2 > r13) goto L2f
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch: java.lang.Throwable -> L2a
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L2a
            goto L39
        L2a:
            r0 = move-exception
            r12 = r0
            r3 = r11
            goto Lba
        L2f:
            r2 = 0
            goto L39
        L31:
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch: java.lang.Throwable -> Lb0
            r3 = r11
            android.text.Spannable r3 = (android.text.Spannable) r3     // Catch: java.lang.Throwable -> Lb0
            r2.<init>(r3)     // Catch: java.lang.Throwable -> Lb0
        L39:
            r3 = 0
            if (r2 == 0) goto L65
            java.lang.Object[] r4 = r2.getSpans(r12, r13, r0)     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.EmojiSpan[] r4 = (androidx.emoji2.text.EmojiSpan[]) r4     // Catch: java.lang.Throwable -> L2a
            if (r4 == 0) goto L65
            int r5 = r4.length     // Catch: java.lang.Throwable -> L2a
            if (r5 <= 0) goto L65
            int r5 = r4.length     // Catch: java.lang.Throwable -> L2a
            r6 = 0
        L49:
            if (r6 >= r5) goto L65
            r7 = r4[r6]     // Catch: java.lang.Throwable -> L2a
            int r8 = r2.getSpanStart(r7)     // Catch: java.lang.Throwable -> L2a
            int r9 = r2.getSpanEnd(r7)     // Catch: java.lang.Throwable -> L2a
            if (r8 == r13) goto L5a
            r2.removeSpan(r7)     // Catch: java.lang.Throwable -> L2a
        L5a:
            int r12 = java.lang.Math.min(r8, r12)     // Catch: java.lang.Throwable -> L2a
            int r13 = java.lang.Math.max(r9, r13)     // Catch: java.lang.Throwable -> L2a
            int r6 = r6 + 1
            goto L49
        L65:
            r4 = r12
            r5 = r13
            if (r4 == r5) goto L6f
            int r12 = r11.length()     // Catch: java.lang.Throwable -> Lb0
            if (r4 < r12) goto L71
        L6f:
            r3 = r11
            goto Lb3
        L71:
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r14 == r12) goto L84
            if (r2 == 0) goto L84
            int r12 = r2.length()     // Catch: java.lang.Throwable -> L2a
            java.lang.Object[] r12 = r2.getSpans(r3, r12, r0)     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.EmojiSpan[] r12 = (androidx.emoji2.text.EmojiSpan[]) r12     // Catch: java.lang.Throwable -> L2a
            int r12 = r12.length     // Catch: java.lang.Throwable -> L2a
            int r14 = r14 - r12
        L84:
            r6 = r14
            androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback r8 = new androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback     // Catch: java.lang.Throwable -> Lb0
            androidx.emoji2.text.EmojiCompat$SpanFactory r12 = r10.mSpanFactory     // Catch: java.lang.Throwable -> Lb0
            r8.<init>(r2, r12)     // Catch: java.lang.Throwable -> Lb0
            r2 = r10
            r3 = r11
            r7 = r15
            java.lang.Object r11 = r2.process(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> La4
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r11 = (androidx.emoji2.text.UnprecomputeTextOnModificationSpannable) r11     // Catch: java.lang.Throwable -> La4
            if (r11 == 0) goto La7
            android.text.Spannable r11 = r11.getUnwrappedSpannable()     // Catch: java.lang.Throwable -> La4
            if (r1 == 0) goto La3
            r12 = r3
            androidx.emoji2.text.SpannableBuilder r12 = (androidx.emoji2.text.SpannableBuilder) r12
            r12.endBatchEdit()
        La3:
            return r11
        La4:
            r0 = move-exception
        La5:
            r12 = r0
            goto Lba
        La7:
            if (r1 == 0) goto Laf
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
        Lac:
            r11.endBatchEdit()
        Laf:
            return r3
        Lb0:
            r0 = move-exception
            r3 = r11
            goto La5
        Lb3:
            if (r1 == 0) goto Lb9
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            goto Lac
        Lb9:
            return r3
        Lba:
            if (r1 == 0) goto Lc2
            r11 = r3
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.endBatchEdit()
        Lc2:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiProcessor.process(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }

    public int getEmojiMatch(@NonNull CharSequence charSequence, int i) {
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.mRootNode, this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int length = charSequence.length();
        int iCharCount = 0;
        int i2 = 0;
        int i3 = 0;
        while (iCharCount < length) {
            int iCodePointAt = Character.codePointAt(charSequence, iCharCount);
            int iCheck = processorSm.check(iCodePointAt);
            TypefaceEmojiRasterizer typefaceEmojiRasterizer = processorSm.mCurrentNode.mData;
            if (iCheck == 1) {
                iCharCount += Character.charCount(iCodePointAt);
                i3 = 0;
            } else if (iCheck == 2) {
                iCharCount += Character.charCount(iCodePointAt);
            } else if (iCheck == 3) {
                typefaceEmojiRasterizer = processorSm.mFlushNode.mData;
                if (typefaceEmojiRasterizer.getCompatAdded() <= i) {
                    i2++;
                }
            }
            if (typefaceEmojiRasterizer != null && typefaceEmojiRasterizer.getCompatAdded() <= i) {
                i3++;
            }
        }
        if (i2 != 0) {
            return 2;
        }
        if (!processorSm.isInFlushableState() || processorSm.mCurrentNode.mData.getCompatAdded() > i) {
            return i3 == 0 ? 0 : 2;
        }
        return 1;
    }

    public final <T> T process(@NonNull CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3, boolean z, EmojiProcessCallback<T> emojiProcessCallback) {
        int i4;
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.mRootNode, this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int iCodePointAt = Character.codePointAt(charSequence, i);
        int i5 = 0;
        boolean zHandleEmoji = true;
        loop0: while (true) {
            i4 = i;
            while (i < i2 && i5 < i3 && zHandleEmoji) {
                int iCheck = processorSm.check(iCodePointAt);
                if (iCheck == 1) {
                    i = Character.charCount(Character.codePointAt(charSequence, i4)) + i4;
                    if (i < i2) {
                        iCodePointAt = Character.codePointAt(charSequence, i);
                    }
                } else if (iCheck == 2) {
                    int iCharCount = Character.charCount(iCodePointAt) + i;
                    if (iCharCount < i2) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                    }
                    i = iCharCount;
                } else if (iCheck == 3) {
                    if (z || !hasGlyph(charSequence, i4, i, processorSm.mFlushNode.mData)) {
                        zHandleEmoji = emojiProcessCallback.handleEmoji(charSequence, i4, i, processorSm.mFlushNode.mData);
                        i5++;
                    }
                }
            }
            break loop0;
        }
        if (processorSm.isInFlushableState() && i5 < i3 && zHandleEmoji && (z || !hasGlyph(charSequence, i4, i, processorSm.mCurrentNode.mData))) {
            emojiProcessCallback.handleEmoji(charSequence, i4, i, processorSm.mCurrentNode.mData);
        }
        return emojiProcessCallback.getResult();
    }
}
