package com.amazon.ion.util;

import com.amazon.ion.IonException;
import com.amazon.ion.Timestamp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class JarInfo {
    public static final String BUILD_TIME_ATTRIBUTE = "Ion-Java-Build-Time";
    public static final String MANIFEST_FILE = "META-INF/MANIFEST.MF";
    public static final String PROJECT_VERSION_ATTRIBUTE = "Ion-Java-Project-Version";
    public Timestamp ourBuildTime;
    public String ourProjectVersion;

    public JarInfo() throws IonException {
        try {
            Enumeration<URL> resources = JarInfo.class.getClassLoader().getResources(MANIFEST_FILE);
            ArrayList arrayList = new ArrayList();
            while (resources.hasMoreElements()) {
                try {
                    arrayList.add(new Manifest(resources.nextElement().openStream()));
                } catch (IOException unused) {
                }
            }
            loadBuildProperties(arrayList);
        } catch (IOException e) {
            throw new IonException("Unable to load manifests.", e);
        }
    }

    public Timestamp getBuildTime() {
        return this.ourBuildTime;
    }

    public String getProjectVersion() {
        return this.ourProjectVersion;
    }

    public final void loadBuildProperties(List<Manifest> list) throws IonException {
        Iterator<Manifest> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            boolean zTryLoadBuildProperties = tryLoadBuildProperties(it.next());
            if (zTryLoadBuildProperties && z) {
                throw new IonException("Found multiple manifests with ion-java version info on the classpath.");
            }
            z |= zTryLoadBuildProperties;
        }
        if (!z) {
            throw new IonException("Unable to locate manifest with ion-java version info on the classpath.");
        }
    }

    public final boolean tryLoadBuildProperties(Manifest manifest) {
        Attributes mainAttributes = manifest.getMainAttributes();
        String value = mainAttributes.getValue(PROJECT_VERSION_ATTRIBUTE);
        String value2 = mainAttributes.getValue(BUILD_TIME_ATTRIBUTE);
        if (value == null || value2 == null) {
            return false;
        }
        this.ourProjectVersion = value;
        try {
            this.ourBuildTime = Timestamp.valueOf(value2);
            return true;
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    public JarInfo(List<Manifest> list) {
        loadBuildProperties(list);
    }
}
