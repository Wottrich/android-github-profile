# Github Profile Overview
Veja o seu perfil publico do github, ou de outros usuarios, por esse aplicativo simples e rapido.

### Resumo
O objetivo do aplicativo foi estudar mais sobre a prataforma Android. Utilizando metodos Agile como SOLID e Clean Code, foram implementados chamadas api utilizando **Retrofit** juntamente com **Coroutines** que serviu para lidar com a parte asyncrona do projeto e deixar mais leve e facil de entender. A arquiterura escolhida para o projeto foi a MVVM utilizando Compose para lidar com os estados das UIs, **Resource** para lidar com o status da Network, **StateFlow** para comunicação com o compose e **ViewModelProvider** para podemos criar um ciclo de vida proprio da nossa ViewModel podendo haver activity rotation sem perda de estado ou crashes indesejaveis. A aplicação é internacionalizada e está disponivel em inglês e português(dependendo da linguagem do dispositivo).

# Projeto
Projeto inteiramente desenvolido em **Kotlin** tento em vista que é a linguagem oficial recomentada para o Android e também tendo em vista suas facilidades e tecnologias, dentro da IDEA **Android Studio** para uma melhor perfomace no desenvolvimento.

## Arquitetura e dependencias
**Arquitetura**

A arquitetura escolhida para o desenvolvimento foi a **Model View ViewModel(MVVM)**. Esta arquitetura foi escolhida para atuar em conjunto com o **Compose** e **Architecture Components**, facilitanto o código e beneficiando os testes

# Blueprint:
### ➡️ Módulo App - Principal módulo do projeto
- archive: Onde ficam os arquivos de extension do kotlin.
- injection: Onde ficam os arquivo para Dependency injection. 
- ui: Onde fica os fluxos, features e ViewModels do aplicativo.
- util: Onde ficam os arquivos de apoio. (Base)

### ➡️ Módulo Datasource - Módulo onde é concentrado a fonte de dados do projeto
- adapters: Onde ficam os network adapters.
- api: Onde ficam as interfaces de chamada da api.
- dispatchers: Onde fica a representação de CoroutineDispatcher do projeto.
- models: Onde ficam os modelos para mapeamento dos services.
- network: Onde fica a principal configuração da api do projeto.
- resource: Onde ficam os responsáveis por digerir a resposta da api e devolver de uma forma fácil.

### ➡️ Módulo UI - Módulo onde ficam os principais recursos de UI do projeto
- components: Onde fica os componentes base do projeto.
- state: Onde fica o State, principal responsável por contruir as tela em Compose nesse projeto.
- value: Colors, Text. Onde ficam os Conjutos que formam um Desing System do projeto.
- widget: Onde ficam alguns componentes legados do projeto.


**Dependencias**
```kotlin
object Versions {

    const val kotlinVersion = "1.5.21"
    const val coreKtxVersion = "1.6.0"
    const val coroutinesVersion = "1.5.2"

    //AppCompat and UI things
    const val appCompatVersion = "1.4.0-alpha01"

    //Koin
    const val koinVersion = "3.1.2"

    //Compose
    const val composeVersion = "1.0.2"
    const val composeActivityVersion = "1.3.1"
    const val composeNavigationVersion = "2.4.0-alpha08"

    //Coil
    const val coilVersion = "1.3.0"

    //Retrofit and api things
    const val retrofitVersion = "2.9.0"
    const val loggingInterceptorVersion = "4.9.0"

    //Gradle
    const val gradleVersion = "7.0.0"

    //Test
    const val mockKVersion = "1.12.0"
    const val coreTestingVersion = "2.1.0"
    const val junitVersion = "4.13.2"
    const val junitExtVersion = "1.1.3-beta01"
    const val espressoCoreVersion = "3.4.0-beta01"
}
```

# Aplicação
**➡️ Video da aplicação em uso**

https://user-images.githubusercontent.com/24254062/134838966-a72deace-d97b-4a10-b57a-414ad7ee44c0.mp4


A aplicação consiste em um activity onde você pode pesquisar por um perfil do github e também acessar os repositórios desse perfil pesquisado.

**➡️ Inicio**

Step onde o aplicativo é inicializado e podemos ver a funcionalidade de buscar perfil.

<img src="https://user-images.githubusercontent.com/24254062/134838589-d0a39197-f1ac-4397-a290-7d5cd0036c17.jpg" height="400" />

**➡️ Busca**

Step que podemos ver como fica a funcionalidade de buscar e como utilizar ela.

<img src="https://user-images.githubusercontent.com/24254062/134838886-437470ec-18aa-49de-8c2c-d2790aa285ec.png" height="400" />

**➡️ Perfil**

Step que mostra o resultado de uma busca.

<img src="https://user-images.githubusercontent.com/24254062/134838737-83a63e44-7750-432e-afad-d85ec0b00d5a.jpg" height="400" />

**➡️ Perfil não encontrado**

Step que mostra o resultado não encontrado de uma busca.

<img src="https://user-images.githubusercontent.com/24254062/134838768-82f1583f-b025-433a-9bed-7521307333cd.jpg" height="400" />

**➡️ Detalhes do repositório**

Step que mostra o detalhe de um repositório.

<img src="https://user-images.githubusercontent.com/24254062/134838847-b8181bdc-cdbe-412a-be79-3f62588a5605.jpg" height="400" />
