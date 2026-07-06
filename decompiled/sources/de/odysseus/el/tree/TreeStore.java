package de.odysseus.el.tree;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TreeStore {
    public final TreeBuilder builder;
    public final TreeCache cache;

    public TreeStore(TreeBuilder treeBuilder, TreeCache treeCache) {
        this.builder = treeBuilder;
        this.cache = treeCache;
    }

    public Tree get(String str) throws TreeBuilderException {
        TreeCache treeCache = this.cache;
        if (treeCache == null) {
            return this.builder.build(str);
        }
        Tree tree = treeCache.get(str);
        if (tree != null) {
            return tree;
        }
        TreeCache treeCache2 = this.cache;
        Tree treeBuild = this.builder.build(str);
        treeCache2.put(str, treeBuild);
        return treeBuild;
    }

    public TreeBuilder getBuilder() {
        return this.builder;
    }
}
