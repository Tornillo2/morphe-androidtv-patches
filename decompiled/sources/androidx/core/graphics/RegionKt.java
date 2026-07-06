package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRegion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Region.kt\nandroidx/core/graphics/RegionKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,117:1\n51#1:119\n31#1:121\n36#1:123\n1#2:118\n1#2:120\n1#2:122\n1#2:124\n*S KotlinDebug\n*F\n+ 1 Region.kt\nandroidx/core/graphics/RegionKt\n*L\n55#1:119\n58#1:121\n61#1:123\n55#1:120\n58#1:122\n61#1:124\n*E\n"})
public final class RegionKt {

    /* JADX INFO: renamed from: androidx.core.graphics.RegionKt$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<Rect>, KMappedMarker {
        public boolean hasMore;
        public final RegionIterator iterator;
        public final Rect rect;

        public AnonymousClass1(Region region) {
            RegionIterator regionIterator = new RegionIterator(region);
            this.iterator = regionIterator;
            Rect rect = new Rect();
            this.rect = rect;
            this.hasMore = regionIterator.next(rect);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasMore;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Rect next() {
            if (!this.hasMore) {
                throw new IndexOutOfBoundsException();
            }
            Rect rect = new Rect(this.rect);
            this.hasMore = this.iterator.next(this.rect);
            return rect;
        }
    }

    @NotNull
    public static final Region and(@NotNull Region region, @NotNull Rect rect) {
        Region region2 = new Region(region);
        region2.op(rect, Region.Op.INTERSECT);
        return region2;
    }

    public static final boolean contains(@NotNull Region region, @NotNull Point point) {
        return region.contains(point.x, point.y);
    }

    public static final void forEach(@NotNull Region region, @NotNull Function1<? super Rect, Unit> function1) {
        RegionIterator regionIterator = new RegionIterator(region);
        while (true) {
            Rect rect = new Rect();
            if (!regionIterator.next(rect)) {
                return;
            } else {
                function1.invoke(rect);
            }
        }
    }

    @NotNull
    public static final Iterator<Rect> iterator(@NotNull Region region) {
        return new AnonymousClass1(region);
    }

    @NotNull
    public static final Region minus(@NotNull Region region, @NotNull Rect rect) {
        Region region2 = new Region(region);
        region2.op(rect, Region.Op.DIFFERENCE);
        return region2;
    }

    @NotNull
    public static final Region not(@NotNull Region region) {
        Region region2 = new Region(region.getBounds());
        region2.op(region, Region.Op.DIFFERENCE);
        return region2;
    }

    @NotNull
    public static final Region or(@NotNull Region region, @NotNull Rect rect) {
        Region region2 = new Region(region);
        region2.union(rect);
        return region2;
    }

    @NotNull
    public static final Region plus(@NotNull Region region, @NotNull Rect rect) {
        Region region2 = new Region(region);
        region2.union(rect);
        return region2;
    }

    @NotNull
    public static final Region unaryMinus(@NotNull Region region) {
        Region region2 = new Region(region.getBounds());
        region2.op(region, Region.Op.DIFFERENCE);
        return region2;
    }

    @NotNull
    public static final Region xor(@NotNull Region region, @NotNull Rect rect) {
        Region region2 = new Region(region);
        region2.op(rect, Region.Op.XOR);
        return region2;
    }

    @NotNull
    public static final Region and(@NotNull Region region, @NotNull Region region2) {
        Region region3 = new Region(region);
        region3.op(region2, Region.Op.INTERSECT);
        return region3;
    }

    @NotNull
    public static final Region minus(@NotNull Region region, @NotNull Region region2) {
        Region region3 = new Region(region);
        region3.op(region2, Region.Op.DIFFERENCE);
        return region3;
    }

    @NotNull
    public static final Region or(@NotNull Region region, @NotNull Region region2) {
        Region region3 = new Region(region);
        region3.op(region2, Region.Op.UNION);
        return region3;
    }

    @NotNull
    public static final Region plus(@NotNull Region region, @NotNull Region region2) {
        Region region3 = new Region(region);
        region3.op(region2, Region.Op.UNION);
        return region3;
    }

    @NotNull
    public static final Region xor(@NotNull Region region, @NotNull Region region2) {
        Region region3 = new Region(region);
        region3.op(region2, Region.Op.XOR);
        return region3;
    }
}
