<div align="center">

# 🧮 Android Scientific Calculator App

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![XML](https://img.shields.io/badge/UI-XML-blue?style=for-the-badge)

Scientific calculator Android application developed with **Kotlin** and **XML**.  
The app performs basic arithmetic operations and includes additional scientific functions such as trigonometry, logarithms, and exponentiation.

</div>

---

## 📸 App Screenshot


<div align="center">

  <img width="273" height="598" alt="image" src="https://github.com/user-attachments/assets/cc1a433c-4fbc-41d8-b9e7-83717e790879" />
  <img width="273" height="603" alt="image" src="https://github.com/user-attachments/assets/d9235c0d-e064-49c2-ae67-e3f70437d0d8" />
  <img width="275" height="603" alt="image" src="https://github.com/user-attachments/assets/54b58f98-d2dd-4d21-8b15-9036fd7d13ad" />

</div>

---

## ⚙️ Features

- Basic operations:
  - Addition (+)
  - Subtraction (-)
  - Multiplication (*)
  - Division (/)

- Scientific functions:
  - Sine (sin)
  - Cosine (cos)
  - Tangent (tan)
  - Logarithm (log)
  - Exponentiation (xʸ)

- Prevents consecutive operators  
- Handles division by zero errors  
- Displays the full mathematical expression before calculating  
- Clean and organized UI using **GridLayout**

---

## 🧠 How It Works

The calculator stores the entire mathematical expression as the user types.

When the **equals (=)** button is pressed, the expression is parsed and evaluated respecting the order of operations:

1. Exponentiation (`^`)
2. Multiplication and Division (`*`, `/`)
3. Addition and Subtraction (`+`, `-`)

Scientific functions operate directly on the currently displayed value.

Error handling ensures that invalid operations (such as division by zero) do not crash the application.

---

## 📂 Project Structure

```text
app/
 └── src/
     └── main/
         ├── java/
         │    └── com/
         │         └── atividadeum/
         │              └── calculadora/
         │                   └── MainActivity.kt
         └── res/
              ├── layout/
              │    └── activity_main.xml
              ├── values/
              │    ├── colors.xml
              │    ├── strings.xml
              │    └── styles.xml
              └── drawable/
```

---

## ▶️ How to Run

1. Clone the repository
2. Open the project in **Android Studio**
3. Wait for **Gradle Sync**
4. Run the project on an **Android Emulator** or **physical device**

---

## 🚀 Technologies Used

- Kotlin
- Android SDK
- XML Layouts
- ConstraintLayout
- GridLayout

---

## 👨‍💻 Author - Lucas Amaral

Developed as a learning project for **Android development with Kotlin**, focusing on UI design with XML and implementing custom mathematical logic for a scientific calculator.
