# Code Review CTF Challenge Java-Auth01: Authentication Bypass Bug

This repository contains a vulnerable login module in Java, designed for use in Capture The Flag (CTF) challenges. The module uses password hashing, but it contains a subtle logic flaw that participants need to exploit.

## Challenge Description

The login module authenticates users based on a hashed password stored in an SQLite database. However, there is a flaw in the authentication logic that allows an attacker to bypass authentication under certain conditions.

## Goal of the Challenge

The main objective of this challenge is to review the Java code and identify a **bug** that can be exploited to bypass the authentication mechanism. While **dynamic testing** (running the application and trying to break it) is allowed, the primary task is to perform **static code analysis** to find the flaw in the code.

Please note:
- **MFA (Multi-Factor Authentication)** and **Brute Force Protection** are not implemented, nor are they the focus of this challenge. 
- The code is purposefully light and straightforward to allow participants to focus on identifying the **authentication bypass vulnerability**.


### Files

- **`LoginService.java`**: Main Java application containing the login logic with a flaw.
- **`PasswordHasher.java`**: Utility class to hash passwords with SHA-256.
- **`setup.sql`**: SQL script to set up the database with pre-populated user data.
- **`users.db`**: SQLite database containing user credentials.

### How to Set Up and Run

1. **Generate the Password Hashes**  
   Use the `PasswordHasher` utility to generate the password hashes and insert them into the database.

2. **Set Up the Database**  
   Run the `setup.sql` script using SQLite to create the database and populate it with user credentials:
   
   ```bash
   sqlite3 users.db < setup.sql

3. Compile the Java files:
    ```bash
    javac LoginService.java PasswordHasher.java

4. Run the Java application:
    ```bash
    java -cp ".:sqlite-jdbc.jar" LoginService
    
    The application will prompt you for a username and password to attempt login.