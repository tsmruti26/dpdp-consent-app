# 🔐 DPDP Consent Management System (Aadhaar OTP Verified)

A full-stack **DPDP-inspired Consent Management System** built using **Spring Boot + HTML/CSS/JavaScript + MySQL**, now upgraded with **Aadhaar OTP-based identity verification** before consent submission.

---

## 🚀 Latest Upgrade (NEW FEATURES)

### 🔥 Aadhaar OTP Authentication Added
- 12-digit Aadhaar validation
- 6-digit random OTP generation
- OTP verification before accessing consent form
- Session-based identity locking

### 🔐 Secure Consent Flow
- Consent form is locked until OTP verification
- Verified identity auto-filled (no manual name entry)
- Aadhaar linked to every consent record

### 🧠 Improved UX Flow
- Step-by-step onboarding (Auth → OTP → Consent)
- Cleaner UI transitions
- Prevents direct access to consent form without verification

---

## 🔄 System Flow (Updated)
Aadhaar Input
↓
OTP Generation (Backend)
↓
OTP Verification
↓
Identity Verified (Session Stored)
↓
Consent Form Unlocked
↓
Consent Submitted
↓
Stored in Database
↓
Admin Dashboard Review

---

## 🧩 Features

### 🔐 Authentication Module (NEW)
- Aadhaar number validation (12-digit check)
- Random 6-digit OTP generation
- OTP verification API
- Session-based Aadhaar storage
- One-time OTP usage (auto clear after success)

### 📄 Consent Management
- User consent capture (Self / Child / Dependant)
- Purpose-based data processing description
- Approval / Denial selection
- Aadhaar-linked consent records

### 📊 Admin Dashboard
- View all consent requests
- Track status: Pending / Approved / Rejected
- Update consent status manually
- Real-time statistics API

---

## 🛠️ Tech Stack

### Frontend
- HTML5
- CSS3
- JavaScript (Fetch API)

### Backend
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA

### Database
- MySQL

---

## 📁 Project Structure (Updated)
│
├── controller
│ ├── ConsentController.java
│ ├── AdminController.java
│ └── OtpController.java (NEW)
│
├── model
│ └── ConsentRequest.java
│
├── repository
│ └── ConsentRepository.java
│
└── DemoApplication.java

src/main/resources/static
│
├── index.html (OTP + Consent UI)
└── dashboard.html

---

## 🔐 OTP Workflow Logic (NEW)

1. User enters Aadhaar number
2. Backend generates 6-digit OTP
3. OTP stored temporarily in memory (Map)
4. User enters OTP
5. Backend validates OTP
6. On success:
   - Aadhaar stored in sessionStorage
   - Consent form unlocked
   - Identity marked as verified

---

## 📡 API Endpoints (UPDATED)

### 🔐 OTP APIs (NEW)
POST /api/generate-otp?aadhaar=XXXXXXXXXXXX
POST /api/verify-otp?aadhaar=XXXXXXXXXXXX&otp=XXXXXX


---

### 📄 Consent APIs
POST /api/create_consent
GET /api/get_consents
GET /api/dashboard
POST /api/update_status

---

### 👨‍💼 Admin API
POST /api/admin/login

---

## 🧪 Sample Consent Payload (Updated)

```json
{
  "username": "Verified Aadhaar Holder (XXXX1234)",
  "aadhaar": "123456789012",
  "consentFor": "Self",
  "dependantDetails": "None",
  "purpose": "Data processing for service usage under DPDP Act",
  "decision": "Approve"
}
``` id="payload_updated"

---

## 🔐 Security Improvements (NEW)

- OTP-based identity verification
- Session-based access control
- Prevents direct access to consent form
- One-time OTP usage
- Aadhaar masking in UI

---

## ⚙️ How to Run

mvn clean install
mvn spring-boot:run