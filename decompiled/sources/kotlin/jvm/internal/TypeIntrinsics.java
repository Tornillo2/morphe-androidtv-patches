package kotlin.jvm.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.jvm.internal.markers.KMutableSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TypeIntrinsics {
    public static Collection asMutableCollection(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableCollection)) {
            return castToCollection(obj);
        }
        throwCce(obj, "kotlin.collections.MutableCollection");
        throw null;
    }

    public static Iterable asMutableIterable(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableIterable)) {
            return castToIterable(obj);
        }
        throwCce(obj, "kotlin.collections.MutableIterable");
        throw null;
    }

    public static Iterator asMutableIterator(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableIterator)) {
            return castToIterator(obj);
        }
        throwCce(obj, "kotlin.collections.MutableIterator");
        throw null;
    }

    public static List asMutableList(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableList)) {
            return castToList(obj);
        }
        throwCce(obj, "kotlin.collections.MutableList");
        throw null;
    }

    public static ListIterator asMutableListIterator(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableListIterator)) {
            return castToListIterator(obj);
        }
        throwCce(obj, "kotlin.collections.MutableListIterator");
        throw null;
    }

    public static Map asMutableMap(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableMap)) {
            return castToMap(obj);
        }
        throwCce(obj, "kotlin.collections.MutableMap");
        throw null;
    }

    public static Map.Entry asMutableMapEntry(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableMap.Entry)) {
            return castToMapEntry(obj);
        }
        throwCce(obj, "kotlin.collections.MutableMap.MutableEntry");
        throw null;
    }

    public static Set asMutableSet(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableSet)) {
            return castToSet(obj);
        }
        throwCce(obj, "kotlin.collections.MutableSet");
        throw null;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object obj, int i) {
        if (obj == null || isFunctionOfArity(obj, i)) {
            return obj;
        }
        throwCce(obj, "kotlin.jvm.functions.Function" + i);
        throw null;
    }

    public static Collection castToCollection(Object obj) {
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static Iterable castToIterable(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static Iterator castToIterator(Object obj) {
        try {
            return (Iterator) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static List castToList(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static ListIterator castToListIterator(Object obj) {
        try {
            return (ListIterator) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static Map castToMap(Object obj) {
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static Map.Entry castToMapEntry(Object obj) {
        try {
            return (Map.Entry) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static Set castToSet(Object obj) {
        try {
            return (Set) obj;
        } catch (ClassCastException e) {
            sanitizeStackTrace(e);
            throw e;
        }
    }

    public static int getFunctionArity(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase) obj).getArity();
        }
        if (obj instanceof Function0) {
            return 0;
        }
        if (obj instanceof Function1) {
            return 1;
        }
        if (obj instanceof Function2) {
            return 2;
        }
        if (obj instanceof Function3) {
            return 3;
        }
        if (obj instanceof Function4) {
            return 4;
        }
        if (obj instanceof Function5) {
            return 5;
        }
        if (obj instanceof Function6) {
            return 6;
        }
        if (obj instanceof Function7) {
            return 7;
        }
        if (obj instanceof Function8) {
            return 8;
        }
        if (obj instanceof Function9) {
            return 9;
        }
        if (obj instanceof Function10) {
            return 10;
        }
        if (obj instanceof Function11) {
            return 11;
        }
        if (obj instanceof Function12) {
            return 12;
        }
        if (obj instanceof Function13) {
            return 13;
        }
        if (obj instanceof Function14) {
            return 14;
        }
        if (obj instanceof Function15) {
            return 15;
        }
        if (obj instanceof Function16) {
            return 16;
        }
        if (obj instanceof Function17) {
            return 17;
        }
        if (obj instanceof Function18) {
            return 18;
        }
        if (obj instanceof Function19) {
            return 19;
        }
        if (obj instanceof Function20) {
            return 20;
        }
        if (obj instanceof Function21) {
            return 21;
        }
        return obj instanceof Function22 ? 22 : -1;
    }

    public static boolean isFunctionOfArity(Object obj, int i) {
        return (obj instanceof Function) && getFunctionArity(obj) == i;
    }

    public static boolean isMutableCollection(Object obj) {
        if (obj instanceof Collection) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableCollection);
        }
        return false;
    }

    public static boolean isMutableIterable(Object obj) {
        if (obj instanceof Iterable) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableIterable);
        }
        return false;
    }

    public static boolean isMutableIterator(Object obj) {
        if (obj instanceof Iterator) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableIterator);
        }
        return false;
    }

    public static boolean isMutableList(Object obj) {
        if (obj instanceof List) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableList);
        }
        return false;
    }

    public static boolean isMutableListIterator(Object obj) {
        if (obj instanceof ListIterator) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableListIterator);
        }
        return false;
    }

    public static boolean isMutableMap(Object obj) {
        if (obj instanceof Map) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableMap);
        }
        return false;
    }

    public static boolean isMutableMapEntry(Object obj) {
        if (obj instanceof Map.Entry) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableMap.Entry);
        }
        return false;
    }

    public static boolean isMutableSet(Object obj) {
        if (obj instanceof Set) {
            return !(obj instanceof KMappedMarker) || (obj instanceof KMutableSet);
        }
        return false;
    }

    public static <T extends Throwable> T sanitizeStackTrace(T t) {
        Intrinsics.sanitizeStackTrace(t, TypeIntrinsics.class.getName());
        return t;
    }

    public static void throwCce(Object obj, String str) {
        throwCce((obj == null ? AbstractJsonLexerKt.NULL : obj.getClass().getName()) + " cannot be cast to " + str);
        throw null;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object obj, int i, String str) {
        if (obj == null || isFunctionOfArity(obj, i)) {
            return obj;
        }
        throwCce(str);
        throw null;
    }

    public static void throwCce(String str) {
        ClassCastException classCastException = new ClassCastException(str);
        sanitizeStackTrace(classCastException);
        throw classCastException;
    }

    public static Collection asMutableCollection(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            throwCce(str);
            throw null;
        }
        return castToCollection(obj);
    }

    public static Iterable asMutableIterable(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterable)) {
            throwCce(str);
            throw null;
        }
        return castToIterable(obj);
    }

    public static Iterator asMutableIterator(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterator)) {
            throwCce(str);
            throw null;
        }
        return castToIterator(obj);
    }

    public static List asMutableList(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableList)) {
            throwCce(str);
            throw null;
        }
        return castToList(obj);
    }

    public static ListIterator asMutableListIterator(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableListIterator)) {
            throwCce(str);
            throw null;
        }
        return castToListIterator(obj);
    }

    public static Map asMutableMap(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            throwCce(str);
            throw null;
        }
        return castToMap(obj);
    }

    public static Map.Entry asMutableMapEntry(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap.Entry)) {
            throwCce(str);
            throw null;
        }
        return castToMapEntry(obj);
    }

    public static Set asMutableSet(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableSet)) {
            throwCce(str);
            throw null;
        }
        return castToSet(obj);
    }

    public static ClassCastException throwCce(ClassCastException classCastException) {
        sanitizeStackTrace(classCastException);
        throw classCastException;
    }
}
