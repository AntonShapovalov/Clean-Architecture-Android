# Clean Architecture for Android
> ### _Work in progress ..._
> The project is going to rebuild [OpenMovieDB](https://github.com/AntonShapovalov/Open-Movie-DB) using Clean Architecture, Kotlin Coroutines and JetPack Compose.

Project demonstrates `how to` build domain driven clean architecture using Android MVVM approach. 

#### Key points
 * independent domain module - all the business logic is independent from the framework and encapsulated in the domain layer.
 * fast and easy testing - all the business logic can covered with fast unit tests.
 * unidirectional data flow - data layer is a single source of truth, app layer passes events to the data layer and observes data streams with result.
 * single responsibility - all layers and classes have clearly defined purpose and responsibility, e.g. view only displays data, view-model only keeps UI state and so on.
 * dependencies inversion - domain does not depend on data or app layer, data and app depend on domain instead.

#### Test coverage
![link](screenshots/kover-report.png)

As the Kover report indicates, all the business logic is encapsulated inside domain use cases and fully covered with unit tests.
