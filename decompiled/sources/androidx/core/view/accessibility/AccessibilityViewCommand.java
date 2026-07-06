package androidx.core.view.accessibility;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface AccessibilityViewCommand {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class CommandArguments {
        public Bundle mBundle;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void setBundle(@Nullable Bundle bundle) {
            this.mBundle = bundle;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MoveAtGranularityArguments extends CommandArguments {
        public boolean getExtendSelection() {
            return this.mBundle.getBoolean(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN);
        }

        public int getGranularity() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MoveHtmlArguments extends CommandArguments {
        @Nullable
        public String getHTMLElement() {
            return this.mBundle.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_HTML_ELEMENT_STRING);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MoveWindowArguments extends CommandArguments {
        public int getX() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVE_WINDOW_X);
        }

        public int getY() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVE_WINDOW_Y);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ScrollToPositionArguments extends CommandArguments {
        public int getColumn() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_COLUMN_INT);
        }

        public int getRow() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_ROW_INT);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SetProgressArguments extends CommandArguments {
        public float getProgress() {
            return this.mBundle.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SetSelectionArguments extends CommandArguments {
        public int getEnd() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT);
        }

        public int getStart() {
            return this.mBundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SetTextArguments extends CommandArguments {
        @Nullable
        public CharSequence getText() {
            return this.mBundle.getCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE);
        }
    }

    boolean perform(@NonNull View view, @Nullable CommandArguments commandArguments);
}
