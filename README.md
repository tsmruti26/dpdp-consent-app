# DPDP Consent Application

A comprehensive Spring Boot application for managing Digital Personal Data Protection (DPDP) consent requests with admin dashboard, authentication, and load testing capabilities.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Load Testing](#load-testing)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [Admin Dashboard](#admin-dashboard)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Overview

The DPDP Consent Application is a full-stack web application built with Spring Boot that manages user consent requests for data processing under the Digital Personal Data Protection Act. It provides:

- **User Portal**: Submit consent requests with detailed information
- **Admin Dashboard**: Review, approve, and manage all consent requests
- **Secure Authentication**: Admin login with session management
- **State Token Tracking**: Unique identification for each consent request
- **Load Testing**: Built-in performance testing capabilities

## Features

✅ **User Consent Form**
- Submit consent requests with user details
- Specify consent type (Self, Child, Dependant)
- Provide processing purpose and decision intent
- Real-time validation and feedback

✅ **Admin Authentication**
- Secure login system with database validation
- Session management via localStorage
- Protected dashboard access

✅ **Admin Dashboard**
- View all consent requests in real-time
- Dashboard statistics (Total, Approved, Rejected, Pending)
- Detailed consent request modal with full information
- Approve/Reject functionality with status updates
- Auto-refresh every 10 seconds

✅ **State Token Management**
- Unique UUID generated for each consent request
- Visible to admins for tracking and compliance
- Timestamp recording for audit trails

✅ **Load Testing**
- 10,000 concurrent request testing
- Real-time progress monitoring
- Comprehensive performance reports (text & JSON)
- Percentile analysis (P50, P95, P99)
- Success rate and error tracking

## Tech Stack

### Backend
- **Java 11+** - Programming language
- **Spring Boot 3.x** - Application framework
- **Spring Data JPA** - ORM and database access
- **Spring Web** - REST API development

### Frontend
- **HTML5** - Markup
- **CSS3** - Styling
- **JavaScript (ES6+)** - Client-side logic
- **Fetch API** - HTTP communication

### Database
- **MySQL/MariaDB 10.6+** - Relational database

### Build & Deployment
- **Maven 3.6.3+** - Build automation
- **Git** - Version control

### Testing
- **Node.js** - Load testing script execution

## Prerequisites

Before you begin, ensure you have the following installed:

```bash
# Java
java --version
# Should output: OpenJDK 11 or higher

# Maven
mvn --version
# Should output: Maven 3.6.3 or higher

# MySQL/MariaDB
mysql --version
# Should output: MySQL 8.0+ or MariaDB 10.6+

# Git
git --version
# Should output: git version 2.x or higher

# Node.js (for load testing)
node --version
# Should output: Node 14+ or higher
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/tsmruti26/dpdp-consent-app.git
cd dpdp-consent-app/demo
```

### 2. Set Up the Database

Create a new MySQL database and user:

```sql
CREATE DATABASE dpdp_db;
CREATE USER 'dpdp_user'@'localhost' IDENTIFIED BY 'dpdp_password';
GRANT ALL PRIVILEGES ON dpdp_db.* TO 'dpdp_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dpdp_db
spring.datasource.username=dpdp_user
spring.datasource.password=dpdp_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
```

### 4. Build and Run

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Or run the JAR directly
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Configuration

### Database Configuration

Update `application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://your-host:3306/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### Server Port

Change the server port in `application.properties`:

```properties
server.port=8080
```

### Hibernate Auto-DDL

For development, use `update` to auto-create tables:

```properties
spring.jpa.hibernate.ddl-auto=update
```

For production, use `validate`:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

## Usage

### User Flow

1. **Access User Portal**
   ```
   http://localhost:8080
   ```

2. **Fill Consent Form**
   - Enter username
   - Select consent type (Self/Child/Dependant)
   - Add dependant details if applicable
   - Enter processing purpose
   - Select decision (Approve/Deny)

3. **Submit**
   - Click "Submit Legal Consent"
   - Receive success confirmation
   - Form clears automatically

### Admin Flow

1. **Login to Admin**
   ```
   http://localhost:8080/login.html
   ```
   - Username: `admin`
   - Password: `root`

2. **View Dashboard**
   - See statistics cards (Total, Approved, Rejected, Pending)
   - Browse all consent requests in table

3. **Review Requests**
   - Click "View" button to see full details
   - See state token and submission timestamp
   - View approval status

4. **Approve/Reject**
   - Click "Approve" or "Reject" button
   - Status updates instantly
   - Statistics refresh automatically

5. **Logout**
   - Click "Logout" button in top-right corner
   - Returns to login page

## API Endpoints

### User Endpoints

#### Submit Consent Request
```
POST /api/create_consent
Content-Type: application/json

Body:
{
  "username": "john_doe",
  "consentFor": "Self",
  "taylorDetails": "None",
  "purpose": "Data collection for analytics",
  "decision": "Approve"
}

Response:
{
  "id": 1,
  "username": "john_doe",
  "consentFor": "Self",
  "taylorDetails": "None",
  "purpose": "Data collection for analytics",
  "decision": "Approve",
  "approvalStatus": "Pending",
  "stateToken": "uuid-string-here",
  "consentDate": "2026-06-10T14:30:00"
}
```

### Admin Endpoints

#### Get All Consents
```
GET /api/get_consents

Response:
[
  {
    "id": 1,
    "username": "john_doe",
    ...
  },
  {
    "id": 2,
    "username": "jane_smith",
    ...
  }
]
```

#### Get Dashboard Statistics
```
GET /api/dashboard

Response:
{
  "total": 100,
  "approved": 45,
  "rejected": 20,
  "pending": 35
}
```

#### Update Consent Status
```
POST /api/update_status
Content-Type: application/json

Body:
{
  "id": 1,
  "status": "Approved"
}

Response:
{
  "id": 1,
  "approvalStatus": "Approved",
  ...
}
```

#### Admin Login
```
POST /api/admin/login
Content-Type: application/json

Body:
{
  "username": "admin",
  "password": "root"
}

Response:
{
  "success": true,
  "message": "Login successful",
  "adminId": 1
}
```

## Load Testing

### Running Load Test Locally

The `load_test.js` script tests the application with 10,000 concurrent requests.

#### Prerequisites
```bash
npm install
# or Node.js comes pre-installed
```

#### Configuration

Edit `load_test.js` to change target:

```javascript
// For local testing
hostname: 'localhost',
port: 8080,

// For remote server testing
hostname: '192.168.24.168',
port: 8080,
```

#### Run Test

```bash
node load_test.js
```

#### Output Example

```
╔════════════════════════════════════════════════════════════════════════════════╗
║                   DPDP CONSENT APPLICATION - LOAD TEST REPORT                  ║
║                          Generated: 6/10/2026, 2:45:30 PM                       ║
╚════════════════════════════════════════════════════════════════════════════════╝

┌─── TEST CONFIGURATION ───────────────────────────────────────────────────────┐
│ Total Requests:        10000                                                 │
│ Test Duration:         125.45 seconds                                        │
│ Target Endpoint:       http://localhost:8080/api/create_consent              │
└──────────────────────────────────────────────────────────────────────────────┘

┌─── RESULTS SUMMARY ──────────────────────────────────────────────────────────┐
│ Successful Requests:   9850 (98.50%)                                         │
│ Failed Requests:       150                                                   │
│ Completed Requests:    10000                                                 │
└──────────────────────────────────────────────────────────────────────────────┘

┌─── PERFORMANCE METRICS ──────────────────────────────────────────────────────┐
│ Requests Per Second:   79.68                                                 │
│ Average Response Time: 12.54 ms                                              │
│ Min Response Time:     2 ms                                                  │
│ Max Response Time:     245 ms                                                │
│ Median (P50):          10 ms                                                 │
│ 95th Percentile (P95): 35 ms                                                 │
│ 99th Percentile (P99): 85 ms                                                 │
└──────────────────────────────────────────────────────────────────────────────┘
```

#### Generated Reports

Two files are created after each test:

1. **Text Report**: `load_test_report_TIMESTAMP.txt`
   - Human-readable format
   - All metrics and recommendations

2. **JSON Report**: `load_test_report_TIMESTAMP.json`
   - Machine-readable format
   - Suitable for data analysis and graphing

## Project Structure

```
dpdp-consent-app/
├── demo/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/dpdp_consent/demo/
│   │   │   │   ├── DemoApplication.java          # Spring Boot entry point
│   │   │   │   ├── HomeController.java           # Route configuration
│   │   │   │   ├── controller/
│   │   │   │   │   ├── ConsentController.java    # Consent API endpoints
│   │   │   │   │   └── AdminController.java      # Admin authentication
│   │   │   │   ├── model/
│   │   │   │   │   ├── ConsentRequest.java       # Consent entity
│   │   │   │   │   └── Admin.java                # Admin entity
│   │   │   │   └── repository/
│   │   │   │       ├── ConsentRepository.java    # Consent DB operations
│   │   │   │       └── AdminRepository.java      # Admin DB operations
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       │   ├── index.html                # User consent form
│   │   │       │   ├── login.html                # Admin login page
│   │   │       │   └── admin.html                # Admin dashboard
│   │   │       └── application.properties        # Configuration
│   │   └── test/
│   │       └── java/com/dpdp_consent/demo/
│   │           └── DemoApplicationTests.java
│   ├── pom.xml                                   # Maven configuration
│   └── mvnw                                      # Maven wrapper
├── load_test.js                                  # Load testing script
├── README.md                                     # This file
└── .gitignore
```

## Database Schema

### consent_requests Table

```sql
CREATE TABLE consent_requests (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  consent_for VARCHAR(255) NOT NULL,
  dependant_details VARCHAR(255),
  purpose TEXT NOT NULL,
  decision VARCHAR(50) NOT NULL,
  approval_status VARCHAR(50) NOT NULL DEFAULT 'Pending',
  state_token VARCHAR(255) UNIQUE NOT NULL,
  consent_date DATETIME NOT NULL
);
```

### admins Table

```sql
CREATE TABLE admins (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);
```

### Insert Default Admin

```sql
INSERT INTO admins (username, password) VALUES ('admin', 'root');
```

## Admin Dashboard

### Features

| Feature | Description |
|---------|-------------|
| **Statistics Cards** | Real-time counts of total, approved, rejected, and pending requests |
| **Request Table** | Sortable table with all consent requests and their details |
| **View Details** | Modal popup showing complete request information including state token |
| **Approve/Reject** | Bulk actions to change request status |
| **Auto-Refresh** | Dashboard updates every 10 seconds automatically |
| **Session Management** | Login/logout with localStorage session tracking |
| **Responsive Design** | Works on desktop and tablet devices |

### State Token

Every consent request receives a unique UUID-based state token for:
- **Tracking**: Follow individual requests through the system
- **Compliance**: Maintain audit trail for DPDP regulations
- **Integration**: Use as external reference ID in other systems

## Troubleshooting

### Issue: 404 Error on Form Submission

**Problem**: "Error recording application consent infrastructure"

**Solution**:
1. Check if backend is running: `mvn spring-boot:run`
2. Verify database connection in `application.properties`
3. Check browser console (F12) for network errors
4. Ensure ConsentController has all required endpoints

### Issue: Login Fails

**Problem**: Admin credentials not working

**Solution**:
1. Verify admin user exists in database:
   ```sql
   SELECT * FROM admins WHERE username='admin';
   ```
2. Check password is correct (default: 'root')
3. Verify AdminRepository is properly autowired
4. Check browser console for API errors

### Issue: Dashboard Shows "undefined"

**Problem**: Statistics cards show undefined instead of numbers

**Solution**:
1. Check if `/api/dashboard` endpoint exists
2. Verify database has consent records
3. Open browser console to see error messages
4. Ensure admin is logged in (check localStorage for session token)

### Issue: Load Test Fails to Connect

**Problem**: Cannot connect to server

**Solution**:
1. Verify server is running on port 8080
2. Check firewall allows port 8080
3. Verify correct IP address in `load_test.js`
4. Try `ping` or `curl` to test connectivity

## Performance Recommendations

Based on load test results:

- **Success Rate > 95%**: ✅ Application is production-ready
- **Average Response Time < 50ms**: ✅ Performance is excellent
- **Response Time > 100ms**: ⚠️ Consider database optimization
- **Error Rate > 5%**: ⚠️ Check server capacity and connection pooling

## Future Enhancements

- [ ] Email notifications for consent status changes
- [ ] Advanced filtering and search in dashboard
- [ ] Bulk export to CSV/Excel
- [ ] Role-based access control (RBAC)
- [ ] Multi-language support
- [ ] API rate limiting
- [ ] Request audit logs with detailed history
- [ ] Webhook notifications for external systems
- [ ] Mobile app integration
- [ ] Analytics and reporting dashboard

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Support

For issues, questions, or suggestions:
- Open an Issue on GitHub
- Check existing documentation
- Review load test reports for performance insights

## Changelog

### Version 1.0.0 (2026-06-10)
- ✅ Initial release
- ✅ User consent form with validation
- ✅ Admin authentication and dashboard
- ✅ Consent request management (CRUD operations)
- ✅ State token generation and tracking
- ✅ Load testing with 10,000 concurrent requests
- ✅ Real-time statistics and auto-refresh
- ✅ Comprehensive API documentation

---

**Last Updated**: June 10, 2026  
**Maintainer**: DPDP Development Team
