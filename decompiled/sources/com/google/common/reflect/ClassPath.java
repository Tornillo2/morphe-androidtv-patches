package com.google.common.reflect;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.ion.util.JarInfo;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ClassPath {
    public static final String CLASS_FILE_NAME_EXTENSION = ".class";
    public final ImmutableSet<ResourceInfo> resources;
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    public static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(StringUtils.SPACE).omitEmptyStrings();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ClassInfo extends ResourceInfo {
        public final String className;

        public ClassInfo(File file, String resourceName, ClassLoader loader) {
            super(file, resourceName, loader);
            this.className = ClassPath.getClassName(resourceName);
        }

        public String getName() {
            return this.className;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int iLastIndexOf = this.className.lastIndexOf(36);
            if (iLastIndexOf != -1) {
                return new CharMatcher.InRange('0', '9').trimLeadingFrom(this.className.substring(iLastIndexOf + 1));
            }
            String packageName = Reflection.getPackageName(this.className);
            return packageName.isEmpty() ? this.className : this.className.substring(packageName.length() + 1);
        }

        public boolean isTopLevel() {
            return this.className.indexOf(36) == -1;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.reflect.ClassPath.ResourceInfo
        public String toString() {
            return this.className;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LocationInfo {
        public final ClassLoader classloader;
        public final File home;

        public LocationInfo(File home, ClassLoader classloader) {
            home.getClass();
            this.home = home;
            classloader.getClass();
            this.classloader = classloader;
        }

        public boolean equals(Object obj) {
            if (obj instanceof LocationInfo) {
                LocationInfo locationInfo = (LocationInfo) obj;
                if (this.home.equals(locationInfo.home) && this.classloader.equals(locationInfo.classloader)) {
                    return true;
                }
            }
            return false;
        }

        public final File file() {
            return this.home;
        }

        public int hashCode() {
            return this.home.hashCode();
        }

        public final void scan(File file, Set<File> scannedUris, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        scanDirectory(file, builder);
                    } else {
                        scanJar(file, scannedUris, builder);
                    }
                }
            } catch (SecurityException e) {
                ClassPath.logger.warning("Cannot access " + file + ": " + e);
            }
        }

        public final void scanDirectory(File directory, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(directory.getCanonicalFile());
            scanDirectory(directory, "", hashSet, builder);
        }

        public final void scanJar(File file, Set<File> scannedUris, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = ClassPath.getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        File next = it.next();
                        if (scannedUris.add(next.getCanonicalFile())) {
                            scan(next, scannedUris, builder);
                        }
                    }
                    scanJarFile(jarFile, builder);
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    try {
                        jarFile.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } catch (IOException unused3) {
            }
        }

        public final void scanJarFile(JarFile file, ImmutableSet.Builder<ResourceInfo> builder) {
            Enumeration<JarEntry> enumerationEntries = file.entries();
            while (enumerationEntries.hasMoreElements()) {
                JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                if (!jarEntryNextElement.isDirectory() && !jarEntryNextElement.getName().equals(JarInfo.MANIFEST_FILE)) {
                    builder.add(ResourceInfo.of(new File(file.getName()), jarEntryNextElement.getName(), this.classloader));
                }
            }
        }

        public ImmutableSet<ResourceInfo> scanResources() throws IOException {
            return scanResources(new HashSet());
        }

        public String toString() {
            return this.home.toString();
        }

        public ImmutableSet<ResourceInfo> scanResources(Set<File> scannedFiles) throws IOException {
            ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
            scannedFiles.add(this.home);
            scan(this.home, scannedFiles, builder);
            return builder.build();
        }

        public final void scanDirectory(File directory, String packagePrefix, Set<File> currentPath, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            File[] fileArrListFiles = directory.listFiles();
            if (fileArrListFiles == null) {
                ClassPath.logger.warning("Cannot read directory " + directory);
                return;
            }
            for (File file : fileArrListFiles) {
                String name = file.getName();
                if (file.isDirectory()) {
                    File canonicalFile = file.getCanonicalFile();
                    if (currentPath.add(canonicalFile)) {
                        scanDirectory(canonicalFile, AbstractResolvableFuture$$ExternalSyntheticOutline1.m(packagePrefix, name, SchemaId.METRIC_SCHEMA_ID_DELIMITER), currentPath, builder);
                        currentPath.remove(canonicalFile);
                    }
                } else {
                    String strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(packagePrefix, name);
                    if (!strM.equals(JarInfo.MANIFEST_FILE)) {
                        builder.add(ResourceInfo.of(file, strM, this.classloader));
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ResourceInfo {
        public final File file;
        public final ClassLoader loader;
        public final String resourceName;

        public ResourceInfo(File file, String resourceName, ClassLoader loader) {
            file.getClass();
            this.file = file;
            resourceName.getClass();
            this.resourceName = resourceName;
            loader.getClass();
            this.loader = loader;
        }

        public static ResourceInfo of(File file, String resourceName, ClassLoader loader) {
            return resourceName.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION) ? new ClassInfo(file, resourceName, loader) : new ResourceInfo(file, resourceName, loader);
        }

        public final ByteSource asByteSource() {
            return new Resources.UrlByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ResourceInfo) {
                ResourceInfo resourceInfo = (ResourceInfo) obj;
                if (this.resourceName.equals(resourceInfo.resourceName) && this.loader == resourceInfo.loader) {
                    return true;
                }
            }
            return false;
        }

        public final File getFile() {
            return this.file;
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }

        public final URL url() {
            URL resource = this.loader.getResource(this.resourceName);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.resourceName);
        }
    }

    public ClassPath(ImmutableSet<ResourceInfo> resources) {
        this.resources = resources;
    }

    public static ClassPath from(ClassLoader classloader) throws IOException {
        ImmutableSet<LocationInfo> immutableSetLocationsFrom = locationsFrom(classloader);
        HashSet hashSet = new HashSet();
        UnmodifiableIterator<LocationInfo> it = immutableSetLocationsFrom.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().home);
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<LocationInfo> it2 = immutableSetLocationsFrom.iterator();
        while (it2.hasNext()) {
            builder.addAll((Iterable) it2.next().scanResources(hashSet));
        }
        return new ClassPath(builder.build());
    }

    public static ImmutableList<URL> getClassLoaderUrls(ClassLoader classloader) {
        return classloader instanceof URLClassLoader ? ImmutableList.copyOf(((URLClassLoader) classloader).getURLs()) : classloader.equals(ClassLoader.getSystemClassLoader()) ? parseJavaClassPath() : ImmutableList.of();
    }

    @VisibleForTesting
    public static String getClassName(String filename) {
        return filename.substring(0, filename.length() - 6).replace('/', '.');
    }

    @VisibleForTesting
    public static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classloader) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ClassLoader parent = classloader.getParent();
        if (parent != null) {
            linkedHashMap.putAll(getClassPathEntries(parent));
        }
        UnmodifiableIterator<URL> it = getClassLoaderUrls(classloader).iterator();
        while (it.hasNext()) {
            URL next = it.next();
            if (next.getProtocol().equals(StringLookupFactory.KEY_FILE)) {
                File file = toFile(next);
                if (!linkedHashMap.containsKey(file)) {
                    linkedHashMap.put(file, classloader);
                }
            }
        }
        return ImmutableMap.copyOf((Map) linkedHashMap);
    }

    @VisibleForTesting
    public static URL getClassPathEntry(File jarFile, String path) throws MalformedURLException {
        return new URL(jarFile.toURI().toURL(), path);
    }

    @VisibleForTesting
    public static ImmutableSet<File> getClassPathFromManifest(File jarFile, Manifest manifest) {
        if (manifest == null) {
            return ImmutableSet.of();
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
        if (value != null) {
            Splitter splitter = CLASS_PATH_ATTRIBUTE_SEPARATOR;
            splitter.getClass();
            for (String str : new Splitter.AnonymousClass5(splitter, value)) {
                try {
                    URL classPathEntry = getClassPathEntry(jarFile, str);
                    if (classPathEntry.getProtocol().equals(StringLookupFactory.KEY_FILE)) {
                        builder.add(toFile(classPathEntry));
                    }
                } catch (MalformedURLException unused) {
                    logger.warning("Invalid Class-Path entry: " + str);
                }
            }
        }
        return builder.build();
    }

    public static ImmutableSet<LocationInfo> locationsFrom(ClassLoader classloader) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = getClassPathEntries(classloader).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<File, ClassLoader> next = it.next();
            builder.add(new LocationInfo(next.getKey(), next.getValue()));
        }
        return builder.build();
    }

    @VisibleForTesting
    public static ImmutableList<URL> parseJavaClassPath() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (String str : (Splitter.AnonymousClass5) Splitter.on(System.getProperty(StandardSystemProperty.PATH_SEPARATOR.key)).split(System.getProperty(StandardSystemProperty.JAVA_CLASS_PATH.key))) {
            try {
                try {
                    builder.add(new File(str).toURI().toURL());
                } catch (SecurityException unused) {
                    builder.add(new URL(StringLookupFactory.KEY_FILE, (String) null, new File(str).getAbsolutePath()));
                }
            } catch (MalformedURLException e) {
                logger.log(Level.WARNING, "malformed classpath entry: " + str, (Throwable) e);
            }
        }
        return builder.build();
    }

    @VisibleForTesting
    public static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals(StringLookupFactory.KEY_FILE));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(new ClassPath$$ExternalSyntheticLambda0()).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String packageName) {
        packageName.getClass();
        String strConcat = packageName.concat(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.className.startsWith(strConcat)) {
                builder.add(next);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String packageName) {
        packageName.getClass();
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (Reflection.getPackageName(next.className).equals(packageName)) {
                builder.add(next);
            }
        }
        return builder.build();
    }
}
