package app.morphe.patches.shared.compat

import app.morphe.patcher.patch.options.CompatiblePackages

val AppCompatibilities = object {
    val PEACOCK_TV = CompatiblePackages("com.peacocktv.peacockandroid")
    val PEACOCK_TV_ANDROID_TV = PEACOCK_TV
    val PARAMOUNT_TV = CompatiblePackages("com.cbs.ott")
    val DISNEY_PLUS_TV = CompatiblePackages("com.disney.disneyplus")
}
