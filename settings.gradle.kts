rootProject.name = "GithubProfile"

val repositoryGroup = arrayListOf(
    ":repositorygroup:repository",
    ":repositorygroup:publicrepository",
    ":repositorygroup:dependenciesrepository"
)

val modules = mutableListOf<String>().apply {
    add(":app")
    add(":base")
    add(":datasource")
    add(":profile")
    add(":profilerepositorysharedcomponents")
    add(":resource")
    add(":screenstate")
    add(":ui")
    addAll(repositoryGroup)
}.toTypedArray()

include(*modules)