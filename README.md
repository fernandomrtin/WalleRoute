# WalleRoute

Project to calculate the route that our Walle robot has to follow considering the input data we receive from the JSON file stored in `data/raw/input.json`.

## How to execute the project

The robot is represented by the arrow in the matrix. Once we press the start button, the robot will follow his route making a move every two seconds until the final position.

## Project considerations

This solution was developed following the MVI architecture pattern. We have divided our app into six modules:

- **app**: Application entry point
- **common/model**: Models we use in the app.
- **common/ui**: All the UI components we use + strings.
- **data**: Data layer stores the repositories and the data sources.
- **domain**: Domain layer stores the use cases.
- **presentation**: All the screens, viewmodels, and communications between them.

## Libraries:

- Compose for UI design.
- Hilt for dependency injection.
- Arrow for Either.
- Kotlinx for JSON serialization.
- Mockk and Turbine for unit tests.