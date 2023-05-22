repositories{
    mavenLocal()
    mavenCentral()
    jcenter()
}

plugins{
    `kotlin-dsl`
}
dependencies {
    implementation(kotlin("script-runtime"))
}