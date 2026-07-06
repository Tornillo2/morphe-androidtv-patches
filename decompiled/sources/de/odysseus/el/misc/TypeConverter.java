package de.odysseus.el.misc;

import java.io.Serializable;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface TypeConverter extends Serializable {
    public static final TypeConverter DEFAULT = new TypeConverterImpl();

    <T> T convert(Object obj, Class<T> cls) throws ELException;
}
