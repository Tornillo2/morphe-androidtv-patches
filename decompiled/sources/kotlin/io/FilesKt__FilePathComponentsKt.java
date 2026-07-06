package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nFilePathComponents.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,149:1\n1563#2:150\n1634#2,3:151\n*S KotlinDebug\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n*L\n134#1:150\n134#1:151,3\n*E\n"})
public class FilesKt__FilePathComponentsKt {
    @NotNull
    public static final File getRoot(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return new File(getRootName(file));
    }

    public static final int getRootLength$FilesKt__FilePathComponentsKt(String str) {
        int iIndexOf$default;
        char c = File.separatorChar;
        int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, c, 0, false, 4, (Object) null);
        if (iIndexOf$default2 == 0) {
            if (str.length() <= 1 || str.charAt(1) != c || (iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, c, 2, false, 4, (Object) null)) < 0) {
                return 1;
            }
            int iIndexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, c, iIndexOf$default + 1, false, 4, (Object) null);
            return iIndexOf$default3 >= 0 ? iIndexOf$default3 + 1 : str.length();
        }
        if (iIndexOf$default2 > 0 && str.charAt(iIndexOf$default2 - 1) == ':') {
            return iIndexOf$default2 + 1;
        }
        if (iIndexOf$default2 == -1 && StringsKt__StringsKt.endsWith$default((CharSequence) str, ':', false, 2, (Object) null)) {
            return str.length();
        }
        return 0;
    }

    @NotNull
    public static final String getRootName(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        String path2 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
        String strSubstring = path.substring(0, getRootLength$FilesKt__FilePathComponentsKt(path2));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final boolean isRooted(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        return getRootLength$FilesKt__FilePathComponentsKt(path) > 0;
    }

    @NotNull
    public static final File subPath(@NotNull File file, int i, int i2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return toComponents(file).subPath(i, i2);
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File file) {
        List list;
        Intrinsics.checkNotNullParameter(file, "<this>");
        String path = file.getPath();
        Intrinsics.checkNotNull(path);
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path);
        String strSubstring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strSubstring2 = path.substring(rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        if (strSubstring2.length() == 0) {
            list = EmptyList.INSTANCE;
        } else {
            List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strSubstring2, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            list = arrayList;
        }
        return new FilePathComponents(new File(strSubstring), list);
    }
}
