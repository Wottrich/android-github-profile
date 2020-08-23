# Github Profile Overview
Veja o seu perfil publico do github, ou de outros usuarios, por esse aplicativo simples e rapido.

### Resumo
O objetivo do aplicativo foi estudar mais sobre a prataforma Android. Utilizando metodos Agile como SOLID e Clean Code, foram implementados chamadas api utilizando **Retrofit** juntamente com **Coroutines** que serviu para lidar com a parte asyncrona do projeto e deixar mais leve e facil de entender. A arquiterura do projeto foi desenvolvido em cima do MVVM e para ajudar foi utilizado **Databinding** para desaclopar resposabilidades da activity e **LiveData**, para uma ambiente mais reativo e respeitoso, com **ViewModelProvider** para podemos criar um ciclo de vida proprio da nossa ViewModel podendo haver activity rotation sem perda de estado ou crashes indesejaveis. A aplicação é internacionalizada e está disponivel em inglês e português(dependendo da linguagem do dispositivo).

# Branches
➡️ [Adicionando Coroutines e DataBinding](https://github.com/Wottrich/android-github-profile/tree/feature/workshop#adicionando-coroutines-e-databinding)

# Projeto
Projeto inteiramente desenvolido em **Kotlin** tento em vista que é a linguagem oficial recomentada para o Android e também tendo em vista suas facilidades e tecnologias, dentro da IDEA **Android Studio** para uma melhor perfomace no desenvolvimento.

## Arquitetura e dependencias
**Arquitetura**

A arquitetura escolhida para o desenvolvimento foi a **Model View ViewModel(MVVM)**. Esta arquitetura foi escolhida para atuar em conjunto com o **DataBinding** facilitanto o código e beneficiando os testes.

Blueprint:
- Archive: Arquivos de ajuda. Onde ficam os arquivos de extension do kotlin
- Data: Serviços e chamadas de API. Onde ficam o datasource e o network do projeto.
- View: Screens, Adapters e ViewHolders. Apresentam as informações para o usuário.
- ViewModel: Tratam as regras de negócio do projeto.

**Dependencias**
- Projeto:
  - _Kotlin: 1.3.72_ -> Linguagem do projeto
  - _Gradle: 4.0.0_ -> Copilador
  - _Coroutines: 1.3.7_ -> Para tasks async
  - _Flow: 1.3.72_ -> Para fluxos no network
  - _RecyclerView: 1.2.0-alpha04_ -> Para listas do projeto
  - _LifecycleExtensions: 2.2.0_ -> Para os Providers da ViewModel
  
- Network digest:
  - _Retrofit: 2.8.1_
  - _OkHttp3 LoggingInterceptor: 4.4.0
  - _Glide: 4.11.0_

- DI
  - _Koin: 2.1.6_

# Aplicação
A aplicação consiste em apenas uma activity ([ProfileActivity](https://github.com/Wottrich/android-github-profile/blob/master/app/src/main/java/wottrich/github/io/githubprofile/view/ProfileActivity.kt)) onde você pode buscar um perfil.

**➡️ Inicio**

Step onde o aplicativo é inicializado e podemos ver a funcionalidade de buscar perfil.

<img src="https://github.com/Wottrich/android-github-profile/blob/master/information/initial_screen.png" alt="" data-canonical-src="https://github.com/Wottrich/android-github-profile/blob/master/information/initial_screen.png" height="400" />

**➡️ Busca**

Step que podemos ver como fica a funcionalidade de buscar e como utilizar ela.

<img src="https://github.com/Wottrich/android-github-profile/blob/master/information/search_before_result.png" alt="" data-canonical-src="https://github.com/Wottrich/android-github-profile/blob/master/information/search_before_result.png" height="400" />

**➡️ Perfil**

Step que mostra o resultado de uma busca.

<img src="https://github.com/Wottrich/android-github-profile/blob/master/information/search_after_result.png" alt="" data-canonical-src="https://github.com/Wottrich/android-github-profile/blob/master/information/search_after_result.png" height="400" />

**➡️ Perfil não encontrado**

Step que mostra o resultado não encontrado de uma busca.

<img src="https://github.com/Wottrich/android-github-profile/blob/master/information/profile_not_found.png" alt="" data-canonical-src="https://github.com/Wottrich/android-github-profile/blob/master/information/profile_not_found.png" height="400" />
