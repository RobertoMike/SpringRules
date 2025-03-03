package io.github.robertomike.jakidate.utils

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repeatable
annotation class YamlFileSource(
    val file: String
)
