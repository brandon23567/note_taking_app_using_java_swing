# 📝 Java Swing Note-Taking Application

A **clean, modern, and functional note-taking desktop app** built using **Java Swing**, **JDBC**, and **PostgreSQL**.  
This project was developed as part of a school assignment to demonstrate the use of GUI design, database connectivity, and object-oriented programming concepts in Java.

---

## 🚀 Overview

The **Java Note-Taking App** provides users with a simple yet elegant interface to manage their personal notes.  
It includes multiple screens such as **Sign In**, **Sign Up**, **Home**, and **Add Note**, all connected together using a shared navigation system.

The project focuses on **core Java development skills**:
- GUI design with **Swing**
- Database connectivity with **JDBC**
- Clean, modular architecture
- Use of environment variables (`.env`) for configuration
- Object-oriented design principles

---

## 🧰 Tech Stack

| Layer | Technology | Description |
|-------|-------------|-------------|
| 🖥️ Frontend | **Java Swing** | For building the graphical user interface |
| ⚙️ Backend | **Core Java (OOP)** | Application logic and state management |
| 🗄️ Database | **PostgreSQL** | Stores all user notes and related data |
| 🔗 Database Access | **JDBC (Java Database Connectivity)** | Connects the Java app to the PostgreSQL database |
| 🔐 Environment Management | **dotenv-java** | Loads sensitive credentials (DB URL, username, password) securely from a `.env` file |

---

## 💡 Why We Built It

The main goal of this project was to:
- Learn how to build **desktop GUI applications** in Java.
- Understand how to **connect applications to databases** using JDBC.
- Practice **software design principles** such as modularity and reusability.
- Gain experience handling **environment variables** securely using `.env` files.
- Demonstrate how a Java application can be structured professionally — with separate files for each component and clean navigation.

---

## ✨ Features

### 👤 Authentication (UI only)
- **Sign In / Sign Up** screens (for demonstration)
- User interface only — no actual login verification
- Navigates to Home page on successful login

### 🏠 Home Page
- Displays a list of all existing notes
- Each note shows:
    - Note text
    - Completion status (`Completed` / `Not Completed`)

### ➕ Add Note Page
- Simple text input to create a new note
- Automatically updates the home page note list

### ✅ Note Management
- Notes can be **marked as completed** or **not completed**
- Clean card-style layout for better readability

### ⚙️ Database Connection
- Uses **PostgreSQL** for storing notes
- Connects securely using **JDBC**
- Credentials are loaded from a `.env` file to avoid hardcoding

### 🔐 Environment Variables
- `.env` file stores:
  ```bash
  DB_URL=jdbc:postgresql://your_neon_url/your_db
  DB_USER=your_user
  DB_PASSWORD=your_password
