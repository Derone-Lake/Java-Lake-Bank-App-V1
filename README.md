# Java-Lake-Bank-App-V1
Initial version of Lake Bank App (V1) with multi-account support and PIN authentication

# 🏦 Lake Bank App (Java)

## 📌 Overview

The **Lake Bank App** is a console-based banking system developed in Java that simulates real-world banking operations. The application allows users to create accounts, securely access them using a PIN, and perform transactions such as deposits and withdrawals.

This project demonstrates core software development concepts including **object-oriented programming (OOP)**, **exception handling**, **input validation**, and **menu-driven application design**.

---

## 🚀 Features

### 🔐 Account Creation & Security

* Automatically generated **unique account numbers**
* **4-digit PIN creation with confirmation validation**
* Secure login system with **limited PIN attempts (3 max)**

### 💳 Banking Operations

* Deposit funds with validation
* Withdraw funds with overdraft protection
* View current balance
* Display full account information

### 🧠 System Design

* Supports **multiple accounts** using `ArrayList`
* Account lookup via account number
* Clean separation between:

  * `BankAccount` (data + logic)
  * `BankApp` (user interaction + control flow)

### ⚠️ Error Handling

* Robust **exception handling** for:

  * Invalid input types
  * Negative values
  * Insufficient funds
* Continuous input validation loops to prevent crashes

---

## 🛠️ Technologies Used

* **Java**
* Object-Oriented Programming (Classes, Methods, Encapsulation)
* Exception Handling (`try/catch`, custom validation)
* Collections (`ArrayList`)
* User Input Handling (`Scanner`)

---

## ▶️ How to Run

1. Compile the program:

```
javac BankAccount.java BankApp.java
```

2. Run the application:

```
java BankApp
```

---

## 🧠 Development Approach

This project was built using core Java principles with **AI-assisted guidance** for:

* refining program structure
* improving exception handling
* debugging logic and edge cases

All features were **manually implemented, tested, and validated**, including:

* invalid input handling
* PIN authentication flow
* account lookup behavior
* transaction validation

---

## 🔄 Version Updates



### V1 (Current)

* Multi-account support using `ArrayList`
* Secure login system with PIN authentication
* Randomized account number generation
* PIN confirmation during account creation
* Improved validation and user experience
* Updated branding to **"Lake Bank App"**

---

## 📌 Future Improvements

* 🔁 Transfer funds between accounts
* 📄 Transaction history tracking
* 💾 Save/load account data from file
* 🛡️ Custom exception classes
* 👨‍💼 Admin vs Customer role separation

---

## 🎯 Learning Outcomes

Through this project, I developed a stronger understanding of:

* Designing and structuring multi-class Java applications
* Implementing secure user input validation
* Managing collections of objects
* Handling and propagating exceptions effectively
* Building scalable, menu-driven systems

---

## 📷 Example Usage

```
===== WELCOME TO THE LAKE BANK APP =====

===== MAIN MENU =====
1. Create Account
2. Access Existing Account
3. View All Accounts
4. Exit

Enter your choice:
```

---

## 💼 Author

**Derone Lake**
Computer Science Student | Java Developer in Progress

---

## 📎 Notes

This project is part of my ongoing development as a software engineer. Future updates will continue to expand functionality and improve system realism.
