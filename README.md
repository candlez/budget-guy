# Budget Guy

A web application for tracking spending.

## Running Locally

1. Run the SpringBoot backend using an Intellij run configuration
2. Serve the frontend with ```npm serve``` and it will automatically forward API requests to the backend at port 8080.

Alternatively, you can do the following:
1. Build the frontend locally with ```npm run build```. It will build to the target directory.
2. Run the SpringBoot app normally and it will serve the static Angular files.

### Environment Variables

1. ```DB_USERNAME```
2. ```DB_PASSWORD```
3. ```DB_HOST```
4. ```DB_PORT```
5. ```SCHEMA_NAME```
6. ```SPRING_PROFILES_ACTIVE``` ('local' or 'prod')
7. ```JWT_SECRET``` (must be at least 32 bytes)
