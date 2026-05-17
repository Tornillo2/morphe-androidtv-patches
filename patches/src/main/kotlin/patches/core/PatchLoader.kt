package patches.core

import java.io.File

object PatchLoader {

    private const val TARGET_APP = "com.disney.disneyplus"
    // Change this to test Paramount:
    // private const val TARGET_APP = "com.paramountplus.android"

    fun loadPatches(): List<Patch> {
        val file = File("../patches-list.json")

        if (!file.exists()) {
            println("patches-list.json not found")
            return emptyList()
        }

        val content = file.readText()

        val idRegex = """"id"\s*:\s*"([^"]+)"""".toRegex()
        val enabledRegex = """"enabled"\s*:\s*(true|false)""".toRegex()
        val targetRegex = """"targetPackage"\s*:\s*"([^"]+)"""".toRegex()

        val ids = idRegex.findAll(content).map { it.groupValues[1] }.toList()
        val enabledFlags = enabledRegex.findAll(content).map { it.groupValues[1].toBoolean() }.toList()
        val targets = targetRegex.findAll(content).map { it.groupValues[1] }.toList()

        val patches = mutableListOf<Patch>()

        for (i in ids.indices) {

            val id = ids[i]
            val enabled = enabledFlags.getOrNull(i) ?: false
            val target = targets.getOrNull(i) ?: ""

            if (!enabled) {
                println("Skipping '$id' (disabled)")
                continue
            }

            if (target != TARGET_APP) {
                println("Skipping '$id' (not for target app)")
                continue
            }

            val patch = PatchRegistry.getPatchById(id)

            if (patch != null) {
                patches.add(patch)
            } else {
                println("Warning: No patch found for id '$id'")
            }
        }

        return patches
    }
}
