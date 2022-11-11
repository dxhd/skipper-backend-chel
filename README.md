# SKIPPER SERVER
## Prerequisites:
1. [JDK 17.0.5](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
## Building the server:
### Common steps
1. Clone the repo and go to the created folder
```
git clone https://github.com/dxhd/skipper-backend-chel.git
cd skipper-backend-chel
```
You can build and run the project using [Intellij IDEA](https://www.jetbrains.com/idea/download/#section=linux) simply launching it.
<br/>
If you wish to build it mannually or do not have Intellij IDEA installed you can follow the next steps:
### Windows
2. To build the server and intsall its denepdencies, simply launch a maven wrapper `mvnw.cmd` with an `install` option.
`.\mvnw.cmd install`
3. After successful build you can run your server with:
`.\mvnw.cmd spring-boot:run`
### Linux
2. Grant executable permission to the maven wrapper
`chmod +x mvnw`
3. Download dependencies and build the server
`./mvnw install`
4. Run
`./mvnw spring-boot:run`
## How to check endpoints?
To check endpoints, sipmly run the server with (use `mvnw` on Linux respectively)
```
.\mvnw.cmd spring-boot:run
```
Then, open your favourite borwser and follow the next link `localhost:8090/v3/swagger-ui.html`
