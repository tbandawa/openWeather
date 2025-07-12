# <b>openWeather</b>
A simple android weather app demonstrating Jetpack Compose. Data is provided by [OpenWeatherMap API](https://openweathermap.org/api)

<p align="center">
	<img src="/blob/openweather.png" width="500" alt="open Weather Screenshot">
</p>


#### App Architecture.
* [Unidirectional data flow (UDF)](https://developer.android.com/jetpack/compose/architecture#udf), where state flows down composables and events flow up from composables.
* A single-activity architecture, using the [Navigation-Compose](https://developer.android.com/jetpack/compose/navigation) dependency to manage navigation between composables.
* Pattern Model-View-ViewModel (MVVM) facilitating separation of development of the graphical user interface.
* Modular app architecture allows being developed features in isolation, independently from other features.

#### Modules:
* <b>app</b> - the main entry point of the application.
* <b>domain</b> - encapsulates business logic and improves testability of the data module.
* <b>data</b> - contains entities, repository, APIs and data manipulation util code.

#### App Specs
* Minimum SDK 24
* Target SDK 34
* Compile SDK 34

#### Libraries
* [Kotlin](https://kotlinlang.org/)
* Android Architecture Components (ViewModel, Navigation Component, ConstraintLayout)
* [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) preferences local storage
* [Coil](https://coil-kt.github.io/coil/) image loading library for Android Jetpack Compose support
* [Koin](https://insert-koin.io/) for dependency injection.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) libraries.
* [Accompanist](https://github.com/google/accompanist) utils for Jetpack Compose.
* [Ktor](https://ktor.io/) for fetching data from the OpenWeatherMap API.
* [Timber](https://github.com/JakeWharton/timber) for logging events.
