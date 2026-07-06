package androidx.appcompat.app;

import android.os.LocaleList;
import androidx.annotation.RequiresApi;
import androidx.core.os.LocaleListCompat;
import java.util.LinkedHashSet;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
public final class LocaleOverlayHelper {
    public static LocaleListCompat combineLocales(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < localeListCompat2.mImpl.size() + localeListCompat.mImpl.size(); i++) {
            Locale locale = i < localeListCompat.mImpl.size() ? localeListCompat.mImpl.get(i) : localeListCompat2.mImpl.get(i - localeListCompat.mImpl.size());
            if (locale != null) {
                linkedHashSet.add(locale);
            }
        }
        return LocaleListCompat.create((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
    }

    public static LocaleListCompat combineLocalesIfOverlayExists(LocaleList localeList, LocaleList localeList2) {
        return (localeList == null || localeList.isEmpty()) ? LocaleListCompat.getEmptyLocaleList() : combineLocales(LocaleListCompat.wrap(localeList), LocaleListCompat.wrap(localeList2));
    }

    public static LocaleListCompat combineLocalesIfOverlayExists(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        if (localeListCompat != null && !localeListCompat.mImpl.isEmpty()) {
            return combineLocales(localeListCompat, localeListCompat2);
        }
        return LocaleListCompat.getEmptyLocaleList();
    }
}
