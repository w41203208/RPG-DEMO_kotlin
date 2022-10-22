# RPGDEMO
以 RPG 為主題，製作 API 來提供前端串接，實現 Spring Boot 所提供的功能，並且使用 Flyway 來對資料庫進行初始化以及變更，並使用 JPA 來做到 ORM 的方式來操作資料庫。當然其中較為複雜的 SQL 指令還是會使用原來的 sql 方式來進行 CRUD。

## Use Skill

- Backend：kotlin use spring boot framework

## Skill Share

### application.yml

- 可以設定很多有的沒的，需要使用再找即可

### JPA

- 可定義 DTO 來間接解析 JSON 格式
- Model 將自動產生的屬性放到{}裡，而不是在建構子中
- 關聯方式
  - Model 中的外鍵直接使用要關聯 Model 的屬性 (詳情請看 User and Bag Models)
  - OneToMany 在 Model 中會是多加的一個屬性不會放在資料庫中，此欄方便我們在 ORM 查詢的時候使用，會使用到 MappedBy。通常與 ManyToOne 做搭配。
  - ManyToMany 通常直接寫 JoinTable 來存取第三張表，但在此範例中是直接建立三張表來做關聯。
  - FetchType：(待研究) LAZY / EAGER
- 更新資料
  - 可直接修改原查詢的資料在以 repo.save 方式存取回去。( 此部分與 dotnet EF6 相近)
  
### Flyway

- application.yml 裡面可以設置 migration 的資料夾要放在哪裡
- 當 Flyway 偵測到 migration 資料夾，會依序執行內部的 sql 檔案
- Format：V1__init.sql，**V[x]__[name].sql**


### Kotlin Reflection

- 懶的說明請直接參考 UserController
- KProperty1 and KMutableProperty
  - **KMutableProperty** has getter / setter
  - **KProperty1** only getter

### Spring boot test

- **MockMvc** 要自己創建資料，並不會使用已建置好的後端服務
- **TestRestTemplate** 則會去要求已建置好的後端服務

- 以下這篇是我看過有講怎麼配置的最好的
  - https://matthung0807.blogspot.com/2020/04/spring-boot-junit-mockmvc.html

## Schedule

1. Docker
2. EntityManager Learning

## Learn Step

- spring boot api
- Http Client 測試用類似 postman

## How to Learn

- Http Client：https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html
- FlyWay：https://flywaydb.org/documentation/
- SpringBoot：https://ithelp.ithome.com.tw/articles/10245979
- JPA：https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
- PostgreSQL：https://docs.postgresql.tw/the-sql-language/ddl/constraints
