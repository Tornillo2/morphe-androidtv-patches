package kotlin.enums;

import java.lang.Enum;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
public interface EnumEntries<E extends Enum<E>> extends List<E>, KMappedMarker {
}
