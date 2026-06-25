# 🏛️ Complaint Box of Municipality

<div align="center">

### 📱 Android Application for Smart Municipal Complaint Management

<p align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Android](https://img.shields.io/badge/Android-Studio-3DDC84?style=for-the-badge\&logo=android)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge\&logo=firebase\&logoColor=black)
![CameraX](https://img.shields.io/badge/CameraX-Android-blue?style=for-the-badge)
![Google Maps](https://img.shields.io/badge/Location-Google%20Services-green?style=for-the-badge)

*A smart Android application that enables citizens to report municipal issues while allowing municipal authorities to efficiently manage, monitor, and resolve complaints.*

</p>

</div>

---

# 📖 Overview

**Complaint Box of Municipality** is an Android application developed to bridge the communication gap between citizens and municipal authorities.

The application enables citizens to report civic issues by submitting complaints with descriptions, images, and location details. Municipal authorities can review complaints, update their status, and efficiently manage the grievance resolution process.

The project aims to improve transparency, streamline complaint management, and promote digital governance through an easy-to-use mobile application.

---

# ✨ Features

## 👤 Citizen Module

* User Registration & Login
* Submit Municipal Complaints
* Capture or Upload Complaint Images
* Automatic Location Detection
* Track Complaint Status
* View Complaint History
* Submit Feedback
* Contact Municipality

---

## 🏢 Municipality Module

* Secure Administrator Login
* View Submitted Complaints
* Review Complaint Details
* Accept or Reject Complaints
* Update Complaint Status
* Manage Citizen Feedback

---

## 📷 Smart Features

* 📸 Camera Integration
* 🖼️ Image Upload Support
* 📍 GPS Location Detection
* ☁️ Firebase Cloud Storage
* 🔄 Real-time Database Synchronization

---

# 🛠️ Tech Stack

| Category             | Technology                 |
| -------------------- | -------------------------- |
| Programming Language | Java                       |
| IDE                  | Android Studio             |
| Backend              | Firebase                   |
| Authentication       | Firebase Authentication    |
| Database             | Firebase Realtime Database |
| Storage              | Firebase Storage           |
| UI Design            | XML                        |
| Image Loading        | Picasso                    |
| Animations           | Lottie                     |
| Camera               | CameraX                    |
| Location             | Google Play Services       |

---

# 📱 Application Workflow

```text
Citizen
   │
   ▼
Login / Register
   │
   ▼
Submit Complaint
   │
   ├── Add Description
   ├── Upload / Capture Image
   └── Detect Location
   │
   ▼
Firebase Database
   │
   ▼
Municipality Dashboard
   │
   ▼
Review Complaint
   │
   ├── Accept
   ├── Reject
   └── Update Status
   │
   ▼
Citizen Views Updated Status
```

---

# 📂 Project Structure

```text
Complaint-Box-of-Municipality/

├── app/
│   ├── src/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── .gitignore
│
├── gradle/
│   └── wrapper/
│
├── build.gradle
├── gradle.properties
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

---

# 🚀 Key Functionalities

* Complaint Registration
* Complaint Tracking
* Municipality Dashboard
* Firebase Authentication
* Firebase Realtime Database
* Firebase Storage
* Camera Integration
* GPS Location Detection
* Feedback Management

---

# ⚙️ Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Harshal1804/Complaint-Box-of-Municipality.git
```

### 2. Open in Android Studio

Open the project using Android Studio and allow Gradle to sync.

### 3. Configure Firebase

This project uses:

* Firebase Authentication
* Firebase Realtime Database
* Firebase Storage

For security reasons, the **`google-services.json`** file has **not** been included in this repository.

To run the application:

1. Create a Firebase project in the Firebase Console.
2. Register your Android application.
3. Download the generated **`google-services.json`** file.
4. Place it inside:

```text
app/google-services.json
```

5. Sync the project and run the application.

---

# ▶️ Running the Application

1. Connect an Android device or start an emulator.
2. Build the project.
3. Run the application from Android Studio.

---

# 📸 Screenshots

Create a folder named:

```text
screenshots/
```

Example structure:

```text
screenshots/
├── splash-screen.png
├── login-screen.png
├── citizen-dashboard.png
├── complaint-form.png
├── complaint-details.png
├── municipality-dashboard.png
└── feedback-screen.png
```

Then display them in the README like this:

```md
## 🏠 Login Screen

![Login](screenshots/login-screen.png)
```

---

# 🔥 Firebase Services Used

* Firebase Authentication
* Firebase Realtime Database
* Firebase Storage

---

# 🚀 Future Enhancements

* Push Notifications
* Complaint Priority Classification
* Complaint Analytics Dashboard
* Google Maps Visualization
* AI-Based Complaint Categorization
* Multi-language Support
* Offline Complaint Submission

---

# 🎯 Applications

* Smart City Initiatives
* Municipal Governance
* Citizen Engagement
* Digital Public Services
* Urban Infrastructure Monitoring

---

# 👨‍💻 Author

**Harshal Shinde**

Computer Engineering Student

MIT ADT University, Pune

GitHub: https://github.com/Harshal1804

---

# ⭐ Show Your Support

If you found this project useful, consider giving it a ⭐ on GitHub.

---

# 📜 License

This project is developed for educational and learning purposes.

Feel free to use and modify it with proper attribution.
