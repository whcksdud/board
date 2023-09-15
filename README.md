# board
✌ Topic
: 스프링부트를 이용한 게시판

⚡ Key Features
- 메인페이지
- ![image](https://github.com/whcksdud/board/assets/67512185/8239d322-b46a-4ea6-b8ab-d9da9c4c3ace)

- 게시판 CRUD 기능
- ![image](https://github.com/whcksdud/board/assets/67512185/f61d4307-8a15-4a09-b593-bdab1459f592)

- 공지글 기능
- ![image](https://github.com/whcksdud/board/assets/67512185/f4d2746b-a7b8-44bd-9d95-4f75d5ff2a19)

- 답변형 게시글
- ![image](https://github.com/whcksdud/board/assets/67512185/04a08e27-00f2-4965-91c1-b7089578e33f)

- 댓글 작성 기능
- ![image](https://github.com/whcksdud/board/assets/67512185/82e07b49-8536-4ef5-ac5e-1df76126aeab)

- 투표 기능
- ![image](https://github.com/whcksdud/board/assets/67512185/54a2b3b7-3033-44dd-ae44-7f681ffac117)

- 웹 게임 기능
- ![image](https://github.com/whcksdud/board/assets/67512185/b6919678-8348-43db-b617-f374fb5f83de)

🎨 Structure
```
├─.gradle
│  ├─8.2.1
│  │  ├─checksums
│  │  ├─dependencies-accessors   
│  │  ├─executionHistory
│  │  ├─fileChanges
│  │  ├─fileHashes
│  │  └─vcsMetadata
│  ├─buildOutputCleanup
│  └─vcs-1
├─.idea
├─build
│  ├─classes
│  │  └─java
│  │      └─main
│  │          └─com
│  │              └─example
│  │                  └─demo
│  │                      ├─controller
│  │                      ├─dao
│  │                      └─domain
│  ├─generated
│  │  └─sources
│  │      ├─annotationProcessor
│  │      │  └─java
│  │      │      └─main
│  │      └─headers
│  │          └─java
│  │              └─main
│  ├─resources
│  │  └─main
│  │      ├─static
│  │      │  ├─css
│  │      │  ├─img
│  │      │  ├─js
│  │      │  ├─lib
│  │      │  │  ├─animate
│  │      │  │  ├─easing
│  │      │  │  ├─owlcarousel
│  │      │  │  │  └─assets
│  │      │  │  ├─waypoints
│  │      │  │  └─wow
│  │      │  └─scss
│  │      │      └─bootstrap
│  │      │          └─scss
│  │      │              ├─forms
│  │      │              ├─helpers
│  │      │              ├─mixins
│  │      │              ├─utilities
│  │      │              └─vendor
│  │      └─templates
│  └─tmp
│      └─compileJava
│          └─compileTransaction
│              ├─backup-dir
│              └─stash-dir
├─gradle
│  └─wrapper
    │  │  │              ├─helpers
    │  │  │              ├─mixins
    │  │  │              ├─utilities
    │  │  │              └─vendor
    │  │  └─templates
    │  └─webapp
    │      └─WEB-INF
    │          └─views
    └─test
        └─java
            └─com
                └─example
                    └─demo

```
👊 Dependence
```
#h2 console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

#h2 db
spring.datasource.url=jdbc:h2:~/test;
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.sql.init.mode=always
#hibernate ??
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.hibernate.ddl-auto=create

#실시간수정
server.servlet.jsp.init-parameters.development=true 

# 경로설정
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```


🐔 Languages & IDE
- SPIRNGBOOT : 3.1.3
- DB : H2 db


