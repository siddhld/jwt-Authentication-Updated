# JWT-Authentication
Complete Configuration of JWT


### Secret key Generator website
https://asecuritysite.com/encryption/plain


# JWT Authentication Flow
### 1. Client Requests Access:
    - The client sends a login request to the server with user credentials (e.g., username and password).
    
### 2. Server Validates Credentials:
    - The server verifies the credentials against its user database.
    - If the credentials are valid, the server generates a JWT containing user information and possibly other claims (like roles or permissions).
    
### 3. Client Receives and Stores JWT:
    - The client receives the JWT in the server's response.
    - The client stores the JWT, typically in local storage or a cookie, ensuring it can be included in future requests.

### 4. Client Makes Authenticated Requests:
    - For subsequent API requests, the client includes the JWT in the Authorization header of the HTTP request.
    - The header format is usually Authorization: Bearer <token>.

### 5. Server Verifies JWT:
    - The server extracts the JWT from the Authorization header.
    - The server verifies the JWT's validity, which involves checking the signature, expiration time, and possibly other claims.
    - If the JWT is valid, the server extracts user information from it (e.g., username, roles).

### 6. Server Processes the Request:
    - If the JWT is valid, the server processes the authenticated request.
    - The server sends back the appropriate response to the client.



# JWT Structure

## A JSON Web Token (JWT) typically consists of three parts separated by dots (.):

- Header: Contains metadata about the token, such as the type of token and the algorithm used for signing.
- Payload: Contains the claims, which are the actual data being transmitted (e.g., user information).
- Signature: Ensures that the token hasn't been altered.

## Here's an example of a JWT:

eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

## Parts of a JWT
- Header: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
- Payload: eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ
- Signature: SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

## How the Signature is Created
### Combine the Header and Payload: The header and payload are base64-encoded and combined with a dot:

eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ

- Hashing with Secret Key: This combined string is then hashed using the specified algorithm (HS256 in this case) along with the secret key. This generates a unique signature.

- Signature: The signature ensures that the header and payload have not been altered. If any part of the token changes, the signature will no longer match.

## Verification Process

### When the system receives a token, it performs the following steps:

- Separate the Token: Split the token into its three parts (header, payload, and signature).

- Recreate the Signature: Using the header and payload, the system creates a new signature using the same algorithm and secret key.

- Compare Signatures: The newly created signature is compared to the signature in the token. If they match, the token is valid and has not been tampered with. If they don't match, the token is invalid.

## In Simple Terms

### Generating a Token:

Combine user information (payload) and metadata (header).
Use a secret key and a specific method to create a signature.
The token is the combination of the header, payload, and signature.
Verifying a Token:

Use the same secret key and method to recreate the signature from the received token's header and payload.
Check if this recreated signature matches the one in the token.
If they match, the token is valid; otherwise, it's not.
What Does the Signature Look Like?
The signature is a base64-encoded string of the hashed value created from the header, payload, and secret key. In our example JWT:


SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
This string is the signature. It ensures the integrity and authenticity of the token.

## Why It’s Important
- Integrity: Ensures the token's data hasn't been altered.
- Authenticity: Verifies that the token was issued by a trusted source (using the secret key).
### By signing the token with a secret key and verifying it on receipt, we ensure that the token is both secure and reliable.


# CSRF Attack in JWT Authentication

### Sessions and Cookies Explained

#### Comparison

| Feature        | Cookies                                    | Sessions                                     |
|----------------|--------------------------------------------|----------------------------------------------|
| Storage        | Client-side (browser)                      | Server-side                                  |
| Data           | Small amounts (name-value pairs)           | Larger amounts, complex objects              |
| Security       | Data can be exposed if not secured         | More secure as data is stored server-side    |
| Expiry         | Can be set to expire after a specific time | Typically expire after a period of inactivity|
| Use Case       | Storing user preferences, small data       | Storing authentication info, user state      |

