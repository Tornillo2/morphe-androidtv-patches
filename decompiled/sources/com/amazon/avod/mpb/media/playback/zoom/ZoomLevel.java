package com.amazon.avod.mpb.media.playback.zoom;

import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ZoomLevel {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final String name;
    public final float zoomRatio;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @NotNull
        public final ZoomLevel fromString(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            String upperCase = value.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            switch (upperCase.hashCode()) {
                case -1999289321:
                    if (upperCase.equals("NATIVE")) {
                        return Native.INSTANCE;
                    }
                    break;
                case -1500080794:
                    if (upperCase.equals("WINDOW_BOXING")) {
                        return WindowBoxing.INSTANCE;
                    }
                    break;
                case -520198116:
                    if (upperCase.equals("FULL_SCREEN")) {
                        return FullScreen.INSTANCE;
                    }
                    break;
                case 884789133:
                    if (upperCase.equals("INVISIBLE")) {
                        return Invisible.INSTANCE;
                    }
                    break;
            }
            Float floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(value);
            return (floatOrNull == null || floatOrNull.floatValue() <= 0.0f) ? Native.INSTANCE : new Custom(floatOrNull.floatValue());
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Custom extends ZoomLevel {
        public final float ratio;

        public Custom(float f) {
            super("CUSTOM", f);
            this.ratio = f;
        }

        public static Custom copy$default(Custom custom, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = custom.ratio;
            }
            custom.getClass();
            return new Custom(f);
        }

        public final float component1() {
            return this.ratio;
        }

        @NotNull
        public final Custom copy(float f) {
            return new Custom(f);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Custom) && Float.compare(this.ratio, ((Custom) obj).ratio) == 0;
        }

        public final float getRatio() {
            return this.ratio;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.ratio);
        }

        @NotNull
        public String toString() {
            return "Custom(ratio=" + this.ratio + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FullScreen extends ZoomLevel {

        @NotNull
        public static final FullScreen INSTANCE = new FullScreen();

        public FullScreen() {
            super("FULL_SCREEN", 0.0f);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof FullScreen);
        }

        public int hashCode() {
            return -1528741600;
        }

        @NotNull
        public String toString() {
            return "FullScreen";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Invisible extends ZoomLevel {

        @NotNull
        public static final Invisible INSTANCE = new Invisible();

        public Invisible() {
            super("INVISIBLE", 0.0f);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof Invisible);
        }

        public int hashCode() {
            return -1648023096;
        }

        @NotNull
        public String toString() {
            return "Invisible";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Native extends ZoomLevel {

        @NotNull
        public static final Native INSTANCE = new Native();

        public Native() {
            super("NATIVE", 0.0f);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof Native);
        }

        public int hashCode() {
            return 36738012;
        }

        @NotNull
        public String toString() {
            return "Native";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WindowBoxing extends ZoomLevel {

        @NotNull
        public static final WindowBoxing INSTANCE = new WindowBoxing();

        public WindowBoxing() {
            super("WINDOW_BOXING", 0.85f);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || (obj instanceof WindowBoxing);
        }

        public int hashCode() {
            return -312282548;
        }

        @NotNull
        public String toString() {
            return "WindowBoxing";
        }
    }

    public /* synthetic */ ZoomLevel(String str, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, f);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final float getZoomRatio() {
        return this.zoomRatio;
    }

    public ZoomLevel(String str, float f) {
        this.name = str;
        this.zoomRatio = f;
    }
}
