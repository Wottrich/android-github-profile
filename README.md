# Github Profile Overview
Veja o seu perfil publico do github, ou de outros usuarios, por esse aplicativo simples e rapido.

### Resumo
O objetivo do aplicativo foi estudar mais sobre a prataforma Android. Utilizando metodos Agile como SOLID e Clean Code, foram implementados chamadas api utilizando **Retrofit** juntamente com **Coroutines** que serviu para lidar com a parte asyncrona do projeto e deixar mais leve e facil de entender. A arquiterura do projeto foi desenvolvido em cima do MVVM e para ajudar foi utilizado **Databinding** para desaclopar resposabilidades da activity e **LiveData**, para uma ambiente mais reativo e respeitoso, com **ViewModelProvider** para podemos criar um ciclo de vida proprio da nossa ViewModel podendo haver activity rotation sem perda de estado ou crashes indesejaveis. 

# Projeto
Projeto inteiramente desenvolido em **Kotlin** tento em vista que é a linguagem oficial recomentada para o Android e também tendo em vista suas facilidade e tecnologias, dentro da IDEA **Android Studio** para uma melhor perfomace no desenvolvimento.

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
  - _RecyclerView: 1.2.0-alpha04_ -> Para listas do projeto
  - _LifecycleExtensions: 2.2.0_ -> Para os Providers da ViewModel
  
- Network digest:
  - _Retrofit: 2.8.1_
  - _OkHttp3 LoggingInterceptor: 4.4.0
  - _Glide: 4.11.0_

