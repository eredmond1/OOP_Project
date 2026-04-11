# Edmonton Real Estate Map Viewer

A JavaFX desktop application that loads Edmonton property data from a CSV file and displays property locations on an ArcGIS map. The app supports filtering by year built, lot size, gross area, assessed value, zoning, and neighborhood.

## Features

- Load property records from `Edmonton_Property_Merged_2025.csv`
- Display property locations as markers on an ArcGIS imagery basemap
- Filter results via a sidebar UI
- Show selected property details in a details panel
- Automatically adjust marker size with zoom level

## Project Structure

- `java-maven-starter-project-main/pom.xml` - Maven build configuration
- `java-maven-starter-project-main/src/main/java/com/mycompany/app/App.java` - application entry point and UI logic
- `java-maven-starter-project-main/src/main/java/com/mycompany/app/Backend/` - backend data model and filtering logic
- `java-maven-starter-project-main/Edmonton_Property_Merged_2025.csv` - sample dataset used by the application
- `java-maven-starter-project-main/README.md` - nested project documentation
- `java-maven-starter-project-main/LICENSE` - project license

## Requirements

- Java 11 or newer
- Maven or the bundled Maven wrapper
- ArcGIS Java Runtime dependencies
- ArcGIS API key

## Setup

1. Open the project in your IDE.
2. In `java-maven-starter-project-main/src/main/java/com/mycompany/app/App.java`, set your ArcGIS API key:

3. Ensure the ArcGIS native assets are available locally in your home directory, typically under:

   ```text
   ~/.arcgis/200.6.0
   ```

4. Use the Maven wrapper to download dependencies and compile the project.

## Build and Run

From the root project folder:

```bash
cd java-maven-starter-project-main
./mvnw clean package
./mvnw javafx:run
```

On Windows PowerShell:

```powershell
cd java-maven-starter-project-main
.\mvnw.cmd clean package
.\mvnw.cmd javafx:run
```

## Notes

- The project displays up to 10,000 properties at a time to avoid overloading the map.
- The top-level `README.md` is the main project overview; the nested `java-maven-starter-project-main/README.md` contains more specific instructions for the Maven subproject.
- Do not commit API keys to source control.
