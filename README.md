Overview
This is an Android application that demonstrates the use of MVVM architecture, Hilt for dependency injection, Retrofit for network calls, and RecyclerView adapters.
The application interacts with several APIs to perform authentication and display data.

Features
MVVM Architecture: Utilizes the Model-View-ViewModel (MVVM) design pattern to separate concerns and improve maintainability.
Hilt for Dependency Injection: Simplifies dependency management and promotes clean architecture.
Retrofit for API Calls: Handles network requests and responses efficiently.
Data Passing Between Activities: Uses Intents to pass data between activities.
RecyclerView Adapter: Displays lists of data in a user-friendly manner.

Technologies Used
Kotlin: The primary programming language for development.
Android Jetpack Components: ViewModel, LiveData, and more.
Hilt: Dependency injection library.
Retrofit: Networking library for API calls.

API Endpoints
Authentication

URL: /footscray/auth, /sydney/auth, /ort/auth (based on location)
Method: POST
Request Body:
{
  "username": "Pranjal",
  "password": "s4667717"
}

Dashboard

URL: /dashboard/{keypass}
Method: GET
Successful Response (200 OK):
{
  "entities": [
    {
      "description": "Sample Entity"
    }
  ],
  "entityTotal": 1
}
Note: The API response did not meet the requirements, so instead of displaying all properties, a single description line is shown in the UI.

How to Use
Authentication
Enter your username and password to authenticate using the provided API.

Dashboard
Navigate to the dashboard to view the list of entities.

Data Passing
Click on items in the RecyclerView to see details in another activity.
