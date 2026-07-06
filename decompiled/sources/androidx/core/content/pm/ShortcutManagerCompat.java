package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import androidx.core.graphics.drawable.IconCompat;
import j$.util.Objects;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ShortcutManagerCompat {

    @VisibleForTesting
    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static final int DEFAULT_MAX_ICON_DIMENSION_DP = 96;
    public static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 48;
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    public static final int FLAG_MATCH_CACHED = 8;
    public static final int FLAG_MATCH_DYNAMIC = 2;
    public static final int FLAG_MATCH_MANIFEST = 1;
    public static final int FLAG_MATCH_PINNED = 4;

    @VisibleForTesting
    public static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    public static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
    public static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
    public static volatile List<ShortcutInfoChangeListener> sShortcutInfoChangeListeners;
    public static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(25)
    public static class Api25Impl {
        public static String getShortcutInfoWithLowestRank(@NonNull List<ShortcutInfo> list) {
            int rank = -1;
            String id = null;
            for (ShortcutInfo shortcutInfo : list) {
                if (shortcutInfo.getRank() > rank) {
                    id = shortcutInfo.getId();
                    rank = shortcutInfo.getRank();
                }
            }
            return id;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ShortcutMatchFlags {
    }

    public static boolean addDynamicShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> listRemoveShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        int i = Build.VERSION.SDK_INT;
        if (i <= 29) {
            convertUriIconsToBitmapIcons(context, listRemoveShortcutsExcludedFromSurface);
        }
        if (i >= 25) {
            ArrayList arrayList = new ArrayList();
            Iterator<ShortcutInfoCompat> it = listRemoveShortcutsExcludedFromSurface.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toShortcutInfo());
            }
            if (!ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).addDynamicShortcuts(arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(listRemoveShortcutsExcludedFromSurface);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().getClass();
        }
        return true;
    }

    @VisibleForTesting
    public static boolean convertUriIconToBitmapIcon(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Bitmap bitmapDecodeStream;
        IconCompat iconCompat = shortcutInfoCompat.mIcon;
        if (iconCompat == null) {
            return false;
        }
        int i = iconCompat.mType;
        if (i != 6 && i != 4) {
            return true;
        }
        InputStream uriInputStream = iconCompat.getUriInputStream(context);
        if (uriInputStream == null || (bitmapDecodeStream = BitmapFactory.decodeStream(uriInputStream)) == null) {
            return false;
        }
        shortcutInfoCompat.mIcon = i == 6 ? IconCompat.createWithAdaptiveBitmap(bitmapDecodeStream) : IconCompat.createWithBitmap(bitmapDecodeStream);
        return true;
    }

    @VisibleForTesting
    public static void convertUriIconsToBitmapIcons(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        ArrayList arrayList = new ArrayList(list);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ShortcutInfoCompat shortcutInfoCompat = (ShortcutInfoCompat) obj;
            if (!convertUriIconToBitmapIcon(context, shortcutInfoCompat)) {
                list.remove(shortcutInfoCompat);
            }
        }
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Intent intentCreateShortcutResultIntent = Build.VERSION.SDK_INT >= 26 ? ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).createShortcutResultIntent(shortcutInfoCompat.toShortcutInfo()) : null;
        if (intentCreateShortcutResultIntent == null) {
            intentCreateShortcutResultIntent = new Intent();
        }
        return shortcutInfoCompat.addToIntent(intentCreateShortcutResultIntent);
    }

    public static void disableShortcuts(@NonNull Context context, @NonNull List<String> list, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).disableShortcuts(list, charSequence);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public static void enableShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> listRemoveShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<ShortcutInfoCompat> it = listRemoveShortcutsExcludedFromSurface.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().mId);
            }
            ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).enableShortcuts(arrayList);
        }
        getShortcutInfoSaverInstance(context).addShortcuts(listRemoveShortcutsExcludedFromSurface);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().getClass();
        }
    }

    @NonNull
    public static List<ShortcutInfoCompat> getDynamicShortcuts(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < 25) {
            try {
                return getShortcutInfoSaverInstance(context).getShortcuts();
            } catch (Exception unused) {
                return new ArrayList();
            }
        }
        List<ShortcutInfo> dynamicShortcuts = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getDynamicShortcuts();
        ArrayList arrayList = new ArrayList(dynamicShortcuts.size());
        Iterator<ShortcutInfo> it = dynamicShortcuts.iterator();
        while (it.hasNext()) {
            arrayList.add(new ShortcutInfoCompat.Builder(context, ShortcutInfoCompat$$ExternalSyntheticApiModelOutline2.m(it.next())).build());
        }
        return arrayList;
    }

    public static int getIconDimensionInternal(@NonNull Context context, boolean z) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        int iMax = Math.max(1, activityManager == null || activityManager.isLowRamDevice() ? 48 : 96);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (iMax * ((z ? displayMetrics.xdpi : displayMetrics.ydpi) / 160.0f));
    }

    public static int getIconMaxHeight(@NonNull Context context) {
        context.getClass();
        return Build.VERSION.SDK_INT >= 25 ? ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getIconMaxHeight() : getIconDimensionInternal(context, false);
    }

    public static int getIconMaxWidth(@NonNull Context context) {
        context.getClass();
        return Build.VERSION.SDK_INT >= 25 ? ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getIconMaxWidth() : getIconDimensionInternal(context, true);
    }

    public static int getMaxShortcutCountPerActivity(@NonNull Context context) {
        context.getClass();
        if (Build.VERSION.SDK_INT >= 25) {
            return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getMaxShortcutCountPerActivity();
        }
        return 5;
    }

    @VisibleForTesting
    public static List<ShortcutInfoChangeListener> getShortcutInfoChangeListeners() {
        return sShortcutInfoChangeListeners;
    }

    public static String getShortcutInfoCompatWithLowestRank(@NonNull List<ShortcutInfoCompat> list) {
        int rank = -1;
        String id = null;
        for (ShortcutInfoCompat shortcutInfoCompat : list) {
            if (shortcutInfoCompat.getRank() > rank) {
                id = shortcutInfoCompat.getId();
                rank = shortcutInfoCompat.getRank();
            }
        }
        return id;
    }

    public static List<ShortcutInfoChangeListener> getShortcutInfoListeners(Context context) {
        Bundle bundle;
        String string;
        if (sShortcutInfoChangeListeners == null) {
            ArrayList arrayList = new ArrayList();
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(SHORTCUT_LISTENER_INTENT_FILTER_ACTION);
            intent.setPackage(context.getPackageName());
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 128).iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null && (bundle = activityInfo.metaData) != null && (string = bundle.getString(SHORTCUT_LISTENER_META_DATA_KEY)) != null) {
                    try {
                        arrayList.add((ShortcutInfoChangeListener) Class.forName(string, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context));
                    } catch (Exception unused) {
                    }
                }
            }
            if (sShortcutInfoChangeListeners == null) {
                sShortcutInfoChangeListeners = arrayList;
            }
        }
        return sShortcutInfoChangeListeners;
    }

    public static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context);
                } catch (Exception unused) {
                }
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }

    @NonNull
    public static List<ShortcutInfoCompat> getShortcuts(@NonNull Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            return ShortcutInfoCompat.fromShortcuts(context, ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getShortcuts(i));
        }
        if (i2 < 25) {
            if ((i & 2) != 0) {
                try {
                    return getShortcutInfoSaverInstance(context).getShortcuts();
                } catch (Exception unused) {
                }
            }
            return Collections.EMPTY_LIST;
        }
        ShortcutManager shortcutManagerM = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m()));
        ArrayList arrayList = new ArrayList();
        if ((i & 1) != 0) {
            arrayList.addAll(shortcutManagerM.getManifestShortcuts());
        }
        if ((i & 2) != 0) {
            arrayList.addAll(shortcutManagerM.getDynamicShortcuts());
        }
        if ((i & 4) != 0) {
            arrayList.addAll(shortcutManagerM.getPinnedShortcuts());
        }
        return ShortcutInfoCompat.fromShortcuts(context, arrayList);
    }

    public static boolean isRateLimitingActive(@NonNull Context context) {
        context.getClass();
        return Build.VERSION.SDK_INT >= 25 ? ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).isRateLimitingActive() : getShortcuts(context, 3).size() == getMaxShortcutCountPerActivity(context);
    }

    public static boolean isRequestPinShortcutSupported(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).isRequestPinShortcutSupported();
        }
        if (ContextCompat.checkSelfPermission(context, INSTALL_SHORTCUT_PERMISSION) != 0) {
            return false;
        }
        Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(new Intent(ACTION_INSTALL_SHORTCUT), 0).iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.permission;
            if (TextUtils.isEmpty(str) || INSTALL_SHORTCUT_PERMISSION.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean pushDynamicShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        context.getClass();
        shortcutInfoCompat.getClass();
        int i = Build.VERSION.SDK_INT;
        if (i <= 32 && shortcutInfoCompat.isExcludedFromSurfaces(1)) {
            for (ShortcutInfoChangeListener shortcutInfoChangeListener : getShortcutInfoListeners(context)) {
                Collections.singletonList(shortcutInfoCompat);
                shortcutInfoChangeListener.getClass();
            }
            return true;
        }
        int maxShortcutCountPerActivity = getMaxShortcutCountPerActivity(context);
        if (maxShortcutCountPerActivity != 0) {
            if (i <= 29) {
                convertUriIconToBitmapIcon(context, shortcutInfoCompat);
            }
            if (i >= 30) {
                ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).pushDynamicShortcut(shortcutInfoCompat.toShortcutInfo());
            } else if (i >= 25) {
                ShortcutManager shortcutManagerM = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m()));
                if (!shortcutManagerM.isRateLimitingActive()) {
                    List<ShortcutInfo> dynamicShortcuts = shortcutManagerM.getDynamicShortcuts();
                    if (dynamicShortcuts.size() >= maxShortcutCountPerActivity) {
                        shortcutManagerM.removeDynamicShortcuts(Arrays.asList(Api25Impl.getShortcutInfoWithLowestRank(dynamicShortcuts)));
                    }
                    shortcutManagerM.addDynamicShortcuts(Arrays.asList(shortcutInfoCompat.toShortcutInfo()));
                }
            }
            ShortcutInfoCompatSaver<?> shortcutInfoSaverInstance = getShortcutInfoSaverInstance(context);
            try {
                List<ShortcutInfoCompat> shortcuts = shortcutInfoSaverInstance.getShortcuts();
                if (((ArrayList) shortcuts).size() >= maxShortcutCountPerActivity) {
                    shortcutInfoSaverInstance.removeShortcuts(Arrays.asList(getShortcutInfoCompatWithLowestRank(shortcuts)));
                }
                shortcutInfoSaverInstance.addShortcuts(Arrays.asList(shortcutInfoCompat));
                for (ShortcutInfoChangeListener shortcutInfoChangeListener2 : getShortcutInfoListeners(context)) {
                    Collections.singletonList(shortcutInfoCompat);
                    shortcutInfoChangeListener2.getClass();
                }
                reportShortcutUsed(context, shortcutInfoCompat.getId());
                return true;
            } catch (Exception unused) {
                for (ShortcutInfoChangeListener shortcutInfoChangeListener3 : getShortcutInfoListeners(context)) {
                    Collections.singletonList(shortcutInfoCompat);
                    shortcutInfoChangeListener3.getClass();
                }
                reportShortcutUsed(context, shortcutInfoCompat.getId());
                return false;
            } catch (Throwable th) {
                for (ShortcutInfoChangeListener shortcutInfoChangeListener4 : getShortcutInfoListeners(context)) {
                    Collections.singletonList(shortcutInfoCompat);
                    shortcutInfoChangeListener4.getClass();
                }
                reportShortcutUsed(context, shortcutInfoCompat.getId());
                throw th;
            }
        }
        return false;
    }

    public static void removeAllDynamicShortcuts(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeAllDynamicShortcuts();
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public static void removeDynamicShortcuts(@NonNull Context context, @NonNull List<String> list) {
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeDynamicShortcuts(list);
        }
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public static void removeLongLivedShortcuts(@NonNull Context context, @NonNull List<String> list) {
        if (Build.VERSION.SDK_INT < 30) {
            removeDynamicShortcuts(context, list);
            return;
        }
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeLongLivedShortcuts(list);
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    @NonNull
    public static List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface(@NonNull List<ShortcutInfoCompat> list, int i) {
        Objects.requireNonNull(list);
        if (Build.VERSION.SDK_INT > 32) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        for (ShortcutInfoCompat shortcutInfoCompat : list) {
            if (shortcutInfoCompat.isExcludedFromSurfaces(i)) {
                arrayList.remove(shortcutInfoCompat);
            }
        }
        return arrayList;
    }

    public static void reportShortcutUsed(@NonNull Context context, @NonNull String str) {
        context.getClass();
        str.getClass();
        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).reportShortcutUsed(str);
        }
        for (ShortcutInfoChangeListener shortcutInfoChangeListener : getShortcutInfoListeners(context)) {
            Collections.singletonList(str);
            shortcutInfoChangeListener.getClass();
        }
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat, @Nullable final IntentSender intentSender) {
        int i = Build.VERSION.SDK_INT;
        if (i <= 32 && shortcutInfoCompat.isExcludedFromSurfaces(1)) {
            return false;
        }
        if (i >= 26) {
            return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
        }
        if (!isRequestPinShortcutSupported(context)) {
            return false;
        }
        Intent intentAddToIntent = shortcutInfoCompat.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
        if (intentSender == null) {
            context.sendBroadcast(intentAddToIntent);
            return true;
        }
        context.sendOrderedBroadcast(intentAddToIntent, null, new BroadcastReceiver() { // from class: androidx.core.content.pm.ShortcutManagerCompat.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                try {
                    intentSender.sendIntent(context2, 0, null, null, null);
                } catch (IntentSender.SendIntentException unused) {
                }
            }
        }, null, -1, null, null);
        return true;
    }

    public static boolean setDynamicShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        context.getClass();
        list.getClass();
        List<ShortcutInfoCompat> listRemoveShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        if (Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList = new ArrayList(listRemoveShortcutsExcludedFromSurface.size());
            Iterator<ShortcutInfoCompat> it = listRemoveShortcutsExcludedFromSurface.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toShortcutInfo());
            }
            if (!ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).setDynamicShortcuts(arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        getShortcutInfoSaverInstance(context).addShortcuts(listRemoveShortcutsExcludedFromSurface);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().getClass();
        }
        return true;
    }

    @VisibleForTesting
    public static void setShortcutInfoChangeListeners(List<ShortcutInfoChangeListener> list) {
        sShortcutInfoChangeListeners = list;
    }

    @VisibleForTesting
    public static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver<Void> shortcutInfoCompatSaver) {
        sShortcutInfoCompatSaver = shortcutInfoCompatSaver;
    }

    public static boolean updateShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        List<ShortcutInfoCompat> listRemoveShortcutsExcludedFromSurface = removeShortcutsExcludedFromSurface(list, 1);
        int i = Build.VERSION.SDK_INT;
        if (i <= 29) {
            convertUriIconsToBitmapIcons(context, listRemoveShortcutsExcludedFromSurface);
        }
        if (i >= 25) {
            ArrayList arrayList = new ArrayList();
            Iterator<ShortcutInfoCompat> it = listRemoveShortcutsExcludedFromSurface.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toShortcutInfo());
            }
            if (!ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(context.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).updateShortcuts(arrayList)) {
                return false;
            }
        }
        getShortcutInfoSaverInstance(context).addShortcuts(listRemoveShortcutsExcludedFromSurface);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().getClass();
        }
        return true;
    }
}
