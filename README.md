# Test Automation Framework - REST API Testing

This project is a REST API automation framework built with **Java, RestAssured, and TestNG**, with **Allure Reports** for reporting.

## Prerequisites

- Java JDK 8 or higher installed
- Maven installed
- Scoop installed (using for intall allure report via command line)
- Git (for cloning the repo)
- Node.js (optional, for certain Allure plugins)
- IDE: IntelliJ IDEA, Eclipse, or other Java IDE

---

## Installing Allure on Windows

Follow these steps to install Allure and integrate it with TestNG:

### 1. Download and Install Allure

1. Please refer to this link for install `Allure` -  `https://allurereport.org/docs/install-for-windows/`.
2. Verify installation by opening `cmd` and running `allure --version`

---

## Build & Run Project
Follow these steps to build, run tests, and generate reports for this project.

### 1. Clone the project
```bash
git clone <your-repo-url>
cd <project-folder>
```

### 2. Build the Project

Run Maven to clean and build the project, and download dependencies:

```bash
mvn clean install -DskipTests=true 
```

### 3. Run TestNG Tests

Run all tests defined in `testng.xml`:

```bash
mvn test
```

### 4. Generate Allure Report

After tests complete, generate and open the Allure report:

```bash
allure serve target/allure-report
```

### 5. Run Tests on Different Environments (Optional)

Framework supports multiple environments (Test/Stage), you can pass the environment as a system property:

```bash
mvn test -Denv=stage
```

or you can direct edit the enviroment via `src/test/resources/suites/test-suite.xml`

```
<parameter name="env" value="test" />
```
