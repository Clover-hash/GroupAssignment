# Group Assignment – Final User Guide

## 1. Overview

This application is a **JavaFX-based Intelligent System** designed as part of a university group assignment. The system provides a **knowledge‑based chatbot** experience for end users, while offering **administrative tools** for managing knowledge base content, reviewing changes, and maintaining system quality.

The project demonstrates core concepts in **intelligent systems, information retrieval, and user interface design**, including a simple Retrieval‑Augmented Generation (RAG) workflow.

---

## 2. System Features

### 2.1 User Features

* Interactive chatbot interface
* Ability to ask questions related to the stored knowledge base
* Fast, text‑based responses generated from relevant documents
* Simple and intuitive JavaFX user interface

### 2.2 Admin Features

* Secure admin access
* Upload and manage knowledge base articles
* Review submitted changes
* Approve or reject updates to the knowledge base
* Admin dashboard for system management

---

## 3. System Requirements

### 3.1 Software Requirements

* **Java Development Kit (JDK)**: Version 17 or later
* **JavaFX SDK** (matching JDK version)
* **IDE (recommended)**: IntelliJ IDEA or Eclipse
* **Operating System**: Windows, macOS, or Linux

### 3.2 Hardware Requirements

* Minimum 4 GB RAM (8 GB recommended)
* Any modern CPU capable of running Java applications

---

## 4. Installation & Setup

### **Quick Start Guide**

1. **Clone or Download the Project**
   ```
   git clone https://github.com/Clover-hash/GroupAssignment.git
   ```

2. **Install JDK and JavaFX**
   - Download and install JDK 17 or later from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [OpenJDK](https://openjdk.org/).
     - Download JavaFX SDK from [Gluon](https://gluonhq.com/products/javafx/).  
     - Extract the JavaFX SDK to a known location.
     - Set the `PATH_TO_FX` environment variable to the JavaFX SDK `lib` directory.

3. **Open the Project in an IDE**
   - Open IntelliJ IDEA or Eclipse.
   - Select **Open** and navigate to the cloned project directory.
   - Open the `GroupAssignment` folder.
   
4. **Build the Project**
Allow the IDE to download dependencies and build the project.

---

## Running the Application

1. Locate the main application called `UserApplication` class in the `User` directory.
2. Run the application from the IDE.
3. The JavaFX interface will launch automatically.

---

## 6. How to Use the System (Step-by-Step)

This section provides **exact steps** for using every major function of the system. Follow the steps in order to avoid errors.

### 6.1 Chatbot (User Mode)

**Purpose:** Allows end users to query the knowledge base and receive intelligent responses.

**Steps:**

1. Launch the application.
2. You will be presented with the **Login Screen**.
3. If you do not have an account, click **Register** to create one. Otherwise, enter your credentials and click **Login**.
4. Type a question related to the knowledge base or Click Start Chat
5. Click to open the article

**Expected Behavior:**

* The system searches the `Knowledgebase` folder.
* Relevant documents are retrieved.
* A response is generated based on matched content.

**User Constraints:**

* Only questions related to stored articles will produce meaningful answers.
* Typing random or unrelated questions may result in vague or empty responses.

---

### 6.2 Admin Mode

**Purpose:** Allows administrators to manage knowledge base content and system data.

**Admin Login Steps:**

1. From the main screen, click **Admin Login**.
2. Enter the admin credentials:

    * **Username:** CLover
    * **Password:** 123456
   
3. Click **Login**.

**Expected Behavior:**

* On successful login, the **Admin Dashboard** will be displayed.
* Incorrect credentials will prevent access.

**Admin Dashboard Functions:**

* Upload new knowledge base articles
* Review pending content
* Approve or reject updates

---

## 7. Knowledge Base Management

**Storage Location:**

* `Knowledgebase/` directory

**Supported File Type:**

* `.txt` (plain text only)

### Adding a New Article

1. Log in as admin.
2. Navigate to **Knowledge Base Management**.
3. Click **Upload Article**.
4. Select a `.txt` file.
5. Click **Confirm Upload**.

### Article Content Rules

* One topic per file
* Use clear headings
* Avoid slang or informal language
* Keep paragraphs short

### Removing or Updating Articles

1. Select an existing article from the list.
2. Choose **Edit** or **Delete**.
3. Confirm the action.

**Effect on Chatbot:**

* Newly approved articles are immediately available for retrieval.
* Deleted articles will no longer influence responses.

---

## 8. Project Structure (High-Level)

```
GroupAssignment/
├── Knowledgebase/        # Text-based knowledge articles
├── src/
│   └── main/java/
│       └── com/example/groupassignment/
│           ├── Admin/   # Admin-related controllers and logic
│           ├── SimpleRAG/ # Chatbot and retrieval logic
│           └── UI logic
```

---

## 9. Limitations

* The chatbot relies only on the provided knowledge base
* No external internet data is used
* Responses depend on document quality and coverage
* Not designed for large-scale production use

---

## 10. Troubleshooting (Common Issues & Fixes)

### Issue 1: Application Does Not Launch

**Cause:** JavaFX not configured correctly.

**Solution:**

* Verify JDK version is 17 or higher
* Ensure VM options include:

  ```
  --module-path <javafx_lib_path> --add-modules javafx.controls,javafx.fxml
  ```

---

### Issue 2: Chatbot Returns Empty or Irrelevant Responses

**Cause:** Knowledge base does not contain relevant information.

**Solution:**

* Add more articles
* Improve article clarity
* Avoid overlapping or duplicated content

---

### Issue 3: Admin Login Fails

**Cause:** Incorrect credentials.

**Solution:**

* Ensure username and password are typed exactly
* Check that admin logic files are unchanged

---

**Application does not start**

* Verify JavaFX configuration
* Ensure correct JDK version

**Chatbot returns irrelevant answers**

* Check knowledge base content
* Ensure articles are well‑written and relevant

**Admin features unavailable**

* Confirm correct admin login
* Verify controller files are not modified

---

## 11. Academic Use Disclaimer

This system was developed **strictly for educational purposes** as part of a university assignment. It is not intended for commercial deployment.

---

## 12. Authors

Developed by the Group Assignment Team

---

**End of Final User Guide**
