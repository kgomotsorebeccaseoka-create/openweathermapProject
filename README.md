# Java Maven REST API Tests

## Overview
This project contains automated integration tests for a REST API using Java and Maven. Tests use RestAssured and TestNG (or JUnit as configured). Example helpers include a lenient JSON deserializer to accept numeric strings and test utilities for station registration.

## Tech stack
- Java
- Maven
- RestAssured
- Jackson (JSON)
- TestNG / JUnit (as configured)

## Prerequisites
- Java 11+ installed
- Maven installed
- Windows OS (development environment)

## Build
To compile the project:
`mvn clean compile`

## Run tests
To execute the test suite:
`mvn test`

## Common tasks
- Run a single test class:
  `mvn -Dtest=YourTestClass test`
- Run with detailed logging:
  `mvn -DskipTests=false -Dlog.level=DEBUG test` (adjust according to your logging setup)

## Important notes / troubleshooting
- Numeric values encoded as JSON strings can cause deserialization errors such as:
  `unmarshal type error: expected=float64, got=string`
  Fixes:
    - Use a lenient deserializer (example: `LenientDoubleDeserializer`) and annotate model fields with `@JsonDeserialize`.
    - Or change the model field to `String` and parse later.

- Test assertion failing with `ExternalID` null:
    - Ensure the request builder sends the expected key (`external_id`, `externalId`, or `ExternalID`) that the API expects.
    - Tests can be made more robust by extracting the response and checking common key variants before asserting.

## Project layout
- `src/main/java` - application / helper code (deserializers, models)
- `src/test/java` - test classes and test utilities
- `pom.xml` - Maven configuration

## Contributing
- Open an issue or submit a pull request.
- Follow existing code style and add tests for bug fixes.

## License
Specify a license in `LICENSE` or update `pom.xml` as needed.
