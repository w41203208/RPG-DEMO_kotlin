# RPGDEMO

## Use Skill

- Backend：kotlin use spring boot framework

## Skill Share

### JPA

- 可定義 DTO 來間接解析 JSON 格式
- Model將自動產生的屬性放到{}裡，而不是在建構子中
- 關聯方式
  - Model 中的外鍵直接使用要關聯Model的屬性 (詳情請看 User and Bag Models)
  - OneToMany 在 Model 中會是多加的一個屬性不會放在資料庫中，此欄方便我們在 ORM 查詢的時候使用，會使用到 MappedBy。通常與 ManyToOne 做搭配。
  - ManyToMany 通常直接寫 JoinTable 來存取第三張表，但在此範例中是直接建立三張表來做關聯。
  - FetchType：(待研究) LAZY / EAGER
- 更新資料
  - 可直接修改原查詢的資料在以 repo.save 方式存取回去。( 此部分與 dotnet EF6 相近)

### Kotlin Reflection

- 懶的說明請直接參考 UserController
- KProperty1 and KMutableProperty
  - **KMutableProperty** has getter / setter
  - **KProperty1** only getter

## Schedule

1. Login API
2. Use Vue to do frontend with typescript
3. EntityManager Learning

## Learn Step

- kotlin syntax
- spring boot api
- Http Client

## How to Learn

- Http Client：https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html
- FlyWay：https://flywaydb.org/documentation/
- SpringBoot：https://ithelp.ithome.com.tw/articles/10245979
- JPA：https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
- PostgreSQL：https://docs.postgresql.tw/the-sql-language/ddl/constraints
