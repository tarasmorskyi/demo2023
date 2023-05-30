### Prerequisites
You will need to have the following installed:

1. Kotlin Multiplatform Plugin


## Description
This is presentation of Clean architecture with MVVM and KMM.

### Structure
Demo is split into 3 module layers:
- base - shared configurations, shared classes, base implementations and possibly UseCase interfaces for accessing functionality between modules in case of gradle dependency cannot be provided.
- feature - feature specific business logic and UI. 
- app - application module which combines module as for requirements.

Features have main focus on the business logic to represent the possible ways to use the architecture:
- auctions - uses shared ViewModel for two Fragments which consume the same original data.
- distillers - each Fragment owns separate ViewModel, data needed for DistillersFragment is loaded on application start without even initialisation of fragment to have it available in advance. Error handling and loading progress is handled even being independent of the app flow.

Error handling is placed in BaseViewModel to avoid any possibility to miss exception.

## KMM Separation
Shared code is starting from the ViewModel layer allowing to focus on the platform specific features and UI if used on iOS. 
Separation of the shared modules into separate folder along side with gradle configuration is done to present how shared code can be consumed with git submodule and avoid having Android code in iOS repository.

## Unit tests
Main focus of unit tests is around UseCases as it is supposed to be starting point of the business logic. 
If some business logic is placed in ViewModel it is considered wrong and it should be moved elsewhere (usually UseCase) to avoid multiple responsibilities.
Instead of using Mockito or similar library to mock the external behaviour (API, Database etc) the power of dependency injection is used. 
This decision is the reason why tests are placed in separate modules as created configurations are desired to be reused if more modules exist and undesire to keep test configurations along side the actual ones.