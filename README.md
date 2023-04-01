# SampleWeather

- Sample application to display weather details based on user location using openweathermap api

# Project Characteristics 
- 100% Kotlin<br />
- Architecture (Clean Architecture, MVVM)<br />
- Jetpack Compose<br />
- Dependency Injection(Using HILT)<br />
- Coroutines<br />
- Kotlin Flow<br />
- Networking Libraries(Retrofit, OkHttp)<br /> 
- Navigation<br />
- Extension Functions
- Unit Testing(Junit, Mockito)<br />

# App Architecture Details:
![Untitled Diagram (1)](https://github.com/ankitsingh08/CakeShop/blob/master/app_architecture.png)

 # View
 This layer mainly deals with the UI of the application and has its own presentation models.
 
 Components
 - **Jetpack Compose**: Composables for displaying user interface
 - **ViewModel**: Executes use cases based on user interaction and updates ui state using stateflow
 
 # Domain
 Contains all the business logic for the application.Domain layers is independent of other layers, has its own models, so that changes in other layers will have no effect on domain layer.
 
 Components
 - **UseCase** : Handles business logic
 - **Domain Models**: Represents structure of response data
 - **Repository Interface**: To keep domain layer independent from data layer.
 
 # Data
 Manages application data and exposes data to domain layer
 
 components
 - **Repository Impl** : Gets the requested data using api service and exposes data to domain layer. 
 - **DataModel** : Structure for data retrieved from remote data source. 
 - **Service Api** : Interface handling remote api end points
 
 # Unit Test Cases
Unit test cases are included for the following:
- ViewModel 
- Repository 
