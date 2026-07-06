package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SpreadBuilder {
    public final ArrayList<Object> list;

    public SpreadBuilder(int i) {
        this.list = new ArrayList<>(i);
    }

    public void add(Object obj) {
        this.list.add(obj);
    }

    public void addSpread(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length > 0) {
                ArrayList<Object> arrayList = this.list;
                arrayList.ensureCapacity(arrayList.size() + objArr.length);
                Collections.addAll(this.list, objArr);
                return;
            }
            return;
        }
        if (obj instanceof Collection) {
            this.list.addAll((Collection) obj);
            return;
        }
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                this.list.add(it.next());
            }
            return;
        }
        if (!(obj instanceof Iterator)) {
            throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
        }
        Iterator it2 = (Iterator) obj;
        while (it2.hasNext()) {
            this.list.add(it2.next());
        }
    }

    public int size() {
        return this.list.size();
    }

    public Object[] toArray(Object[] objArr) {
        return this.list.toArray(objArr);
    }
}
