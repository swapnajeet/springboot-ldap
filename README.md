# Springboot LDAP
This is a Springboot application with LDAP authentication.

## About
The application used embedded ApacheDS LDAP server to authenticate the users. The LDAP server uses a file named
**'users.ldif'** from the classpath which contains the user-id(s) and their password(s) in Bcrypt encoded format.

## Tools and technologies
* Springboot
* Spring security
* Spring security LDAP
* ApacheDS embedded
* Gradle build
* Java8

## Getting Started
* Run the command './gradlew bootRun'
* Go to 'http://localhost:8080'. This will present a login page.
* Enter the user name as 'iamsingh' and password as 'password'
* This should redirect to a page where you should see a welcome message to indicate successful login.

### Adding a new user
* Add a dn(distinguishedName) for the user. For ex. 
    > 'dn: uid=iamsingh,ou=people,dc=ssingh,dc=com'
* Add the Bcrypt encoded password for the user. This application uses Bcrypt encryption with strength 10.
* Add the user to the desired groups(admin, users)

### Encoding password with Bcrypt
Enter the following command from a terminal
> $ htpasswd -nbBC &lt;strenght&gt; &lt;user-id&gt; &lt;pasword&gt;

### Reference Documentation
For further reference, please consider the following sections:
* [Spring LDAP](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-ldap)

### Additional Links
These additional references should also help you:
* [htpasswd - Manage user files for basic authentication](https://httpd.apache.org/docs/2.4/programs/htpasswd.html)

