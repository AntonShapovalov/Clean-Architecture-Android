# Clean Architecture for Android
> [!NOTE]
> Work in progress... The project is going to rebuild [OpenMovieDB](https://github.com/AntonShapovalov/Open-Movie-DB) using Clean Architecture, Kotlin Coroutines and JetPack Compose.

Project demonstrates `how to` build domain driven clean architecture using Android MVVM approach. 

### Key points
Overall, the application architecture provides a solid foundation for building a clean and maintainable code. It emphasizes the importance of separating concerns, isolating the core business logic, and making the code testable and reusable.

#### Independent Business Logic in Domain Layer
* This is a core principle of clean architecture. The domain layer encapsulates the core business rules and logic, independent of any external frameworks or technologies. This makes it easier to test and maintain.

#### Unit Testability
* The absence of external dependencies in the domain layer makes it easier to write [unit tests](#test-coverage) that quickly and reliably verify the correctness of the business logic.

#### Data Layer as a Single Source of Truth
* The data layer provides and manages data, acting as a single source of truth. This ensures data consistency and unidirectional data flow.

#### App Layer's Role
* The app layer primarily focus on presenting data to the user and passing events to the data layer. It also observes data streams to update the UI as needed. This separation of concerns helps to keep the app layer clean and focused.

#### Single Responsibility Principle
* Each layer and class has a well-defined purpose and [structure](#modules-structure). Data layer only provides data, domain layer applies data transformations and app layer only displays data. This makes the code easier to understand, maintain, and extend.
* The single responsibility principle is applied to each class, meaning that a class has only one reason to change. This helps to keep classes focused and manageable. For example, view only displays data, view-model only keeps UI state and so on.

#### Dependency Inversion
* The dependency inversion principle ensures that the domain layer does not depend on the data or app layer, but rather the other layers depend on the domain layer. This makes the code more flexible and testable.

### Test coverage
![link](screenshots/kover-report.png)

As the Kover report indicates, all the business logic is encapsulated inside domain use cases and fully covered with unit tests.

### Modules structure
Application contains three modules:
- **app** - Android module with UI and framework dependencies, e.g. JetPack Compose to display views
- **data** - Android module with data dependencies, e.g. Retrofit to get data from the remote API
- **domain** - pure Java/Kotlin module without any external dependencies

```mermaid
graph TD
    subgraph APP[App module]
        direction LR
        subgraph AppDependencies[Dependencies]
            direction TB
            AndroidDependencies(Android Dependencies):::red
            UiDependencies(Ui Dependencies):::red
        end
        subgraph AppTests[Tests]
            direction TB
            AppUnitTests(Unit Tests):::green
            IntegrationTests(Integration Tests):::green
            E2ETests(End-To-End Tests):::green
        end
        subgraph AppClasses[Classes]
            direction TB
            Views(Views)
            ViewModels(View-Models)
            UiModels(Ui Models)
            DI(DI Modules)
            Android(Android Classes)
        end
    end

    subgraph DOMAIN[Domain module]
        direction LR
        subgraph DomainClasses[Classes]
            direction TB
            UseCases(Use Cases)
            Repositories(Repositories Interfaces)
            DomainModels(Domain Models)
        end
        DomainUnitTests(Unit Tests):::green
        DomainDependencies(No exrernal dependencies):::red
    end

    subgraph DATA[Data module]
        direction LR
        DataClassesTests(Unit Tests):::green
        DataDependencies(Data Dependencies):::red
        subgraph DataClasses[Classes]
            direction TB
            RepositoriesImpl(Repositories Implementaions)
            DataSources(Data Sources)
            RemoteModels(Remote Models)
            LocalModels(Local Entities)
            DAO(DAO)
        end
    end

    APP--depends on-->DOMAIN
    DATA--depends on-->DOMAIN
    
    classDef green fill:#90EE90
    classDef red fill:#FFB6C1
```
