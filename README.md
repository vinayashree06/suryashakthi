# Surya Shakthi Solar Monitor 

An Android application designed to monitor solar energy generation and electricity consumption efficiently. The application helps users calculate net energy, efficiency, and savings while providing analytics visualization, AI-based smart suggestions, weather-based simulation, history management, and PDF report generation.

---

# Project Overview

Surya Shakthi Solar Monitor is a smart energy management application developed using Kotlin and Android Studio. The application focuses on helping users understand and optimize their solar energy usage through an easy-to-use Android interface.

The app allows users to:

* Track generated and consumed energy
* Calculate net energy and efficiency
* Analyze energy usage trends
* View graphical analytics
* Store historical energy records
* Generate downloadable PDF reports
* Receive AI-based energy optimization suggestions
* Simulate energy generation under different weather conditions

---

# Features

## Energy Monitoring

* Enter generated energy values
* Enter consumed energy values
* Automatic net energy calculation
* Efficiency and savings calculation

## Analytics Dashboard

* Visual representation of energy data
* Graphs and trend analysis using MPAndroidChart
* Dynamic analytics updates

## AI-Based Smart Suggestions

* Intelligent recommendations based on energy usage
* Efficiency improvement suggestions

## Weather-Based Simulation

* Simulate solar generation for:

  * Sunny
  * Cloudy
  * Rainy weather conditions

## History Management

* Save and retrieve historical energy records
* Local data persistence using Room Database

## PDF Report Generation

* Generate downloadable PDF reports
* Export energy summaries and analytics

## Responsive UI

* Simple and user-friendly Android interface
* Material Design components
* Optimized user experience

---

# Tech Stack

| Technology        | Purpose                      |
| ----------------- | ---------------------------- |
| Kotlin            | Primary programming language |
| XML Layouts       | UI Design                    |
| Android Studio    | Development Environment      |
| Room Database     | Local Data Storage           |
| MVVM Architecture | Application Architecture     |
| MPAndroidChart    | Analytics Visualization      |
| iText PDF         | PDF Report Generation        |
| RecyclerView      | Dynamic Data Display         |
| CardView          | UI Components                |
| Material Design   | Modern Android UI            |
| Gradle            | Dependency Management        |

---

# Architecture

The project follows the **MVVM (Model-View-ViewModel)** architecture pattern.

## Architecture Flow

UI Layer → ViewModel → Repository → Room Database

### Components

### View Layer

* Handles UI rendering
* Accepts user input
* Displays analytics and reports

### ViewModel Layer

* Handles business logic
* Performs calculations
* Manages UI state

### Repository Layer

* Acts as Single Source of Truth
* Handles Room Database communication

### Data Layer

* Stores energy history locally using Room Database

---

# Energy Calculations

## Net Energy

\text{Net Energy} = \text{Generated Energy} - \text{Consumed Energy}

## Efficiency

\text{Efficiency} = \frac{\text{Generated Energy}}{\text{Consumed Energy}} \times 100

## Savings

Savings are estimated based on reduced electricity dependency and energy efficiency.

---

# Project Structure

```plaintext
app/
│
├── activities/
├── adapters/
├── database/
├── models/
├── repository/
├── viewmodel/
├── utils/
├── analytics/
├── pdf/
└── res/
```

---

# Screens

* Splash Screen
* Main Dashboard
* Energy Input Screen
* Analytics Screen
* History Screen
* PDF Report Screen
* AI Suggestion Section

---

# ⚙️ Installation & Setup

## Prerequisites

* Android Studio
* Kotlin SDK
* Android Emulator / Physical Device
* Minimum SDK: Android 9 (API 28)

---

## Clone the Repository

```bash
git clone https://github.com/vinayashree06/suryashakthi.git
```

---

## Open Project

1. Open Android Studio
2. Select **Open Existing Project**
3. Choose the cloned repository folder

---

## Build the Project

```bash
Build → Rebuild Project
```

---

## Run the Application

* Connect Android device or emulator
* Click ▶ Run in Android Studio

---

#  Database Design

## Room Database Entities

### EnergyRecordEntity

Stores:

* Generated Energy
* Consumed Energy
* Net Energy
* Efficiency
* Savings
* Weather Condition
* Timestamp

---

# Analytics

The application uses **MPAndroidChart** for:

* Line Charts
* Bar Charts
* Trend Analysis
* Energy Comparison Visualization

---

# PDF Reporting

The application generates:

* Energy Summary Reports
* Efficiency Reports
* Savings Reports
* Historical Analytics Reports

Generated using:

* iText PDF Library

---

# Validation & Error Handling

The application validates:

* Empty input fields
* Invalid numeric values
* Negative energy values
* Incorrect calculations

---

# Testing

## Testing Performed

* UI Testing
* Calculation Testing
* Database Testing
* Analytics Validation
* PDF Generation Testing

---

# Future Enhancements

* IoT Solar Panel Integration
* Cloud Synchronization
* Real-Time Weather API
* Firebase Integration
* Advanced AI Prediction System
* Multi-user Support
* Notification System

---

# Expected Outcomes

* Better energy awareness
* Improved energy efficiency
* Simplified solar monitoring
* Digital energy record management
* Visual understanding of electricity usage

---

# Developer

**Vinaya Shree R**
Android App Developer Intern – MindMatrix

---

# License

This project is developed for educational and internship purposes.

---

# Acknowledgements

* MindMatrix Internship Program
* Android Developer Documentation
* Kotlin Documentation
* MPAndroidChart Library
* Room Database Documentation
* Material Design Guidelines
