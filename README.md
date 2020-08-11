# Adicionando Coroutines e DataBinding
Esse branch não contem coroutines e databinding. 

Esse readme foi feito para auxiliar a migração desse projeto para começarmos a utilizar **Coroutines** e **DataBinding**

## Gráfico de refatoração que vamos seguir
<img src="https://github.com/Wottrich/android-github-profile/blob/feature/workshop/information/estrutura%20do%20projeto.png" alt="" data-canonical-src="https://github.com/Wottrich/android-github-profile/blob/feature/workshop/information/estrutura%20do%20projeto.png" height="500" />

### Antes de começarmos...
Se você quiser saber mais sobre o projeto, ou se você quiser o projeto com as ultimas funcionalidades é só mudar a branch pra [master](https://github.com/Wottrich/android-github-profile), lá você vai encontrar a ultima versão desse aplicativo.

# Iniciando com Coroutines
### **➡️ GithubDataSource.kt**

Seguindo nosso gráfico de refatoração, nós começamos no arquivo **GithubDataSource.kt** que você pode encontrar dentro de:
[data > datasource > GithubDataSource.kt](https://github.com/Wottrich/android-github-profile/blob/feature/workshop/app/src/main/java/wottrich/github/io/githubprofile/data/datasource/GithubDataSource.kt)

Lá dentro nós temos duas funções:
- `loadProfile`
- `loadRepositories`

e vai ser nelas que vamos mexer.

**Entendendo as mudanças**

Atualmente estamos utilizando o retorno por callback e isso acaba deixando nosso codigo muito verboso no lado da implementação. Ter que implementar `onSuccess` pra um lado `onFailure` para o outro cansa, não?

Um outro problema muito comum era estouros por falta de contexto na hora do retorno, LiveData praticamente matou esse problema mas a verbosidade continuou e aqui estamos nós para finalizar de uma vez por todas esse codigo sujo e, muitas vezes, dificil de entender.

**Mudanças**

#### 1. _Suspend Functions_

Como estaremos utilizando Coroutines primeiramente nós vamos mudar nossas duas funções para `suspend fun`, assim podemos utilizar [coroutines scopes](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) ou [suspend functions](https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html) dentro delas.

Nossas functions ficaram assim:
```kotlin
suspend fun loadProfile (...) {...}
suspend fun loadRepositories (...) {...}
```

#### 2. _awaitResponse()_

**awaitResponse()** é uma suspend function disponibilizada a partir de uma extensão de `Call<T>`, então sim, o retrofit nos disponibiliza uma função com `suspend` para integrarmos junto com nosso coroutines. (E isso é muito legal!!)

Sabendo disso conseguimos perceber que nosso awaitResponse faz jus ao nome pois ele nos retorna um `Response<T>`, isso não te deixa pensativo? 

Vamos mexer primeiro no `loadProfile`, então em cima do nosso `enqueue` faça o seguinte código: (se já não fez kkk)

```kotlin
api.loadProfile(profileLogin).awaitResponse()
```
Você vai perceber uma coisa diferente na caluna onde fica o numero da linha, uma setinha com uma linha no meio apareceu, mas o que é isso???? 

Isso siginica que a função naquela linha é uma suspend function e agora sua **função principal tem a responsabilidade de esperar essa suspend function terminar para reproduzir o codigo embaixo dela**, isso não te faz pensar em algo também?

Okay okay, nem todo mundo está ambientalizando com o mundo das coroutines e bla bla bla, então, ligando os pontos!!!

Como o nosso **awaitResponse** retorna um `Response<T>` e também é uma suspend funcion, a request dele precisa ser completada para o codigo continuar. Com isso nos podemos fazer uma `val` que recebe esse `Response<T>`. (que no caso vai ser `Response<Profile>`)
```kotlin
//Tipo implicido: Response<Profile>
val response = api.loadProfile(profileLogin).awaitResponse()
```

**OMG!!!**

#### 3. _Limpando..._

Antes de limparmos um pouco o código vamos fazer duas coisas:
- Uma val que pegue nosso result
- Uma val que pegue nosso status code
```kotlin
val result = response.body()
val statusCode = response.code()
```
Prontinho!! Agora podemos tirar o `api.loadProfile(profileLogin).enqueue(...)`, também podemos tirar os parametros `onSuccess` e `onFailure` da função.

Okay, ja temos um código mais limpo e facil de ler, e isso é muito bom!!! 🥳

Mas ele ainda ainda não retorna nada e isso é muito ruim!!! 😕

Vamos fazer ele retornar.

Como podemos ver o nosso `val result` retorna o nosso tão desejado `Profile`, então o que precisamos fazer é botar esse `result` como retorno, correto? SIM! Mas antes precisamos fazer nossa função retornar um `Profile?` com `?` pois não temos certeza se o `result` vai retorna.

E pra finalizar nossa função ficou assim:
```kotlin
suspend fun loadProfile (profileLogin: String): Profile? {

  val response = api.loadProfile(profileLogin).awaitResponse()
  val result = response.body()
  val statusCode = response.code()

  if (statusCode in 200..299) {
    return result
  } else {
    return null
  }

}
```

**Agora para fixar o que você viu aqui, faça a mesma coisa na função `loadRepositories`.**

### **➡️ ProfileViewModel.kt**
Em breve...

