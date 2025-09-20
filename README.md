# abstractClasses  
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)  
[![Maven](https://img.shields.io/badge/Maven-3.x+-blue.svg)](https://maven.apache.org/)  
[![TestNG](https://img.shields.io/badge/TestNG-Framework-brightgreen.svg)](https://testng.org/)  
[![Allure](https://img.shields.io/badge/Allure-Reporting-purple.svg)](https://docs.qameta.io/allure/)  
[![Log4j2](https://img.shields.io/badge/Logging-Log4j2-red.svg)](https://logging.apache.org/log4j/2.x/)  

---

A Java project demonstrating the **Bot Pattern with Page Object Model (POM)** and key Object-Oriented Programming (OOP) concepts.  
The project uses **Maven** as the build system, **TestNG** for test execution, **Allure** for reporting, and **Log4j2** for logging.  

---

## 📝 Project Structure

- **`src/`** — Source code
  - **`main/`**
    - `java/` → Contains page classes that interact with the application under test.  
      - Instead of using a traditional *Base Page*, the **Bot Pattern** is applied.  
      - Classes follow a **Fluent POM style** and use a **Bot wrapper** around the WebDriver.  
    - `resources/` → Contains:
      - `engine` → Implementation of the **Bot**.  
      - `application.properties` → Holds configuration (URL, target browser, etc.).  
      - `log4j2.json` → Logging configuration.  
  - **`test/`**
    - Each module has its own test class.  
    - A shared **Base Test** (abstract class) is used with **TestNG annotations**.  

- **`pom.xml`** — Maven configuration:
  - Manages all dependencies from **Maven Central Repository**.  
  - Uses the **Maven Surefire Plugin** to run tests from the terminal.  

- **`.gitignore`**, etc.  

---

## ⚙️ Prerequisites

Before running the project, make sure you have:

- **Java 11+**  
- **Maven 3.x+**  
