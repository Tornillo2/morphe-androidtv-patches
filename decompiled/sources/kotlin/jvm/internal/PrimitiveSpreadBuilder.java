package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class PrimitiveSpreadBuilder<T> {
    public int position;
    public final int size;

    @NotNull
    public final T[] spreads;

    public PrimitiveSpreadBuilder(int i) {
        this.size = i;
        this.spreads = (T[]) new Object[i];
    }

    public final void addSpread(@NotNull T spreadArgument) {
        Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
        T[] tArr = this.spreads;
        int i = this.position;
        this.position = i + 1;
        tArr[i] = spreadArgument;
    }

    public final int getPosition() {
        return this.position;
    }

    public abstract int getSize(@NotNull T t);

    public final void setPosition(int i) {
        this.position = i;
    }

    public final int size() {
        int i = this.size - 1;
        int size = 0;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                T t = this.spreads[i2];
                size += t != null ? getSize(t) : 1;
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return size;
    }

    @NotNull
    public final T toArray(@NotNull T values, @NotNull T result) {
        int i;
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(result, "result");
        int i2 = this.size - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int i4 = 0;
            int i5 = 0;
            i = 0;
            while (true) {
                T t = this.spreads[i4];
                if (t != null) {
                    if (i5 < i4) {
                        int i6 = i4 - i5;
                        System.arraycopy(values, i5, result, i, i6);
                        i += i6;
                    }
                    int size = getSize(t);
                    System.arraycopy(t, 0, result, i, size);
                    i += size;
                    i5 = i4 + 1;
                }
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
            i3 = i5;
        } else {
            i = 0;
        }
        int i7 = this.size;
        if (i3 < i7) {
            System.arraycopy(values, i3, result, i, i7 - i3);
        }
        return result;
    }

    public static /* synthetic */ void getSpreads$annotations() {
    }
}
