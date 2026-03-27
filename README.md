# 📚 Library Management System

A console-based Library Management System built with Java, JDBC, and MySQL.

## 🛠️ Tech Stack
- Java
- JDBC
- MySQL

## ✨ Features
- Add, View, Remove Books
- Add, View, Remove Members
- Issue books to members
- Return books
- Fine calculation (₹5/day after due date)
- View all issued books with member details

## 🗄️ Database Design
- `books` — stores library books
- `users` — stores library members
- `issued_books` — tracks issued/returned books

## 🚀 How to Run
1. Clone the repo
2. Set up MySQL and run the SQL scripts
3. Update DB credentials in `DBConnection.java`
4. Run `Main.java`

## 📌 Coming Soon
- Spring Boot REST APIs
- React Frontend