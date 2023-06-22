# TmaxSoft Application
Demo System 성능 및 기능 TEST를 위해 개발한 Web Application

## 개발 환경
|SW |Version|
|---|---|
|Spring Boot| v2.7.12 |
|JDK| v11.0.13 |
|Postgre| v15.2 |
|Tomcat| v9.0.76 |
|Build Tool| Gradle |

## 빌드 방법
Git Repository를 다운로드 후, OS 환경에 따라 아래 작업을 순차적으로 진행 <br>
<strong>현재 해당 Git Repository에는 WAR 파일로 Build되게 설정되어 있음</strong>
### 1. Window 환경
1. CMD 실행
2. ${PROJECT_HOME} 경로에서 'gradlew.bat build --exclude-task test' 실행
3. cd ${PROJECT_HOME}/build/libs 이동
4. java -jar 프로젝트.jar 실행

### 2. Linux 환경
1. 콘솔로 이동
2. ${PROJECT_HOME} 경로에서 './gradlew build --exclude-task test' 실행
3. cd ${PROJECT_HOME}/build/libs 이동
4. java -jar 프로젝트.jar 실행

### 3. WAR 파일로 Build
1. build.gradle 설정
```
  plugins {
	...(생략)
	  id 'war'
  }
  dependencies {
	...(생략)
	  providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
  }
```
2. Application의 SpringBootServletInitioalizer 상속 수정
```
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(DemoApplication.class);
	}

}
```
3. OS 환경에 따라 Window 및 Linux 빌드방법으로 빌드 진행 <br>
${PROJECT_HOME}/build/libs 디렉토리에서 WAR 파일이 생성된 것을 확인

