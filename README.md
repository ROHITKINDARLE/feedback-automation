# 🚀 Feedback Form Automation using Selenium & Jenkins

## 📌 Project Overview

This project demonstrates automation testing of a student feedback form using **Selenium WebDriver** and integrates it with **Jenkins** to implement a **Continuous Integration (CI) pipeline**.

---

## 🛠️ Technologies Used

* Java
* Selenium WebDriver
* Maven
* Jenkins
* HTML, CSS

---

## 📂 Project Structure

```
feedback-automation/
│
├── feedback.html
├── style.css
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── rohit/
                    └── Parth.java
```

---

## ⚙️ Features

* Automated testing of form validation
* Multiple test cases:

  * Valid submission
  * Empty form
  * Invalid email
  * Invalid mobile number
  * Short feedback
  * Department not selected
  * Reset button functionality

---

## 🔄 CI Pipeline Workflow

1. Code pushed to GitHub
2. Jenkins pulls the latest code
3. Maven builds the project
4. Selenium executes test cases
5. Results displayed in Jenkins console

---

## ▶️ How to Run

1. Clone the repository
2. Open project in IDE
3. Run using Maven:

```
mvn clean compile exec:java
```

---

## 🎯 Learning Outcome

* Hands-on experience with Selenium automation
* Understanding of Maven build lifecycle
* Implementation of Jenkins CI pipeline
* Integration of GitHub with Jenkins

---

## 👨‍💻 Author

Rohit Kindarle
