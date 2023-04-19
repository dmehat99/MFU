1. HTTP requests
```
GET: retrieve details of a resource
POST: create a new resource
PUT: update an existing resource
PATCH: update part of a resource
DELETE: delete a resource
```

2. Annotations
```
@Entity 表示该类是一个JPA实体类

GenerationType.AUTO: 由JPA提供程序自动选择适合数据库的主键生成策略，
它可以在底层使用IDENTITY、SEQUENCE或TABLE等多种不同的策略，具体使用哪种策略取决于JPA提供程序的选择。
GenerationType.IDENTITY: 使用底层数据库的自增长策略来生成主键，这种策略主要适用于MySQL和PostgreSQL等数据库。
当我们将主键生成策略设置为GenerationType.IDENTITY时，我们需要保证数据库中该表的主键设置为自增长

@Id 表示实体类的主键字段，这里为 portfolioId。
@GeneratedValue(strategy = GenerationType.AUTO): 表示该字段的值自动生成，并且生成方式是自动选择一个适合数据库的生成策略。
@Column(name = "portfolio_id"): 表示该属性映射到数据库表中名为portfolio_id的列。
private Long portfolioId;

@ManyToOne(fetch = FetchType.LAZY): 表示该属性与另一个实体类之间是多对一的关系，即多个CustomerPortfolio实体类对应一个Customer实体类。
@JoinColumn(name = "customer_id"): 表示该属性映射到数据库表中名为customer_id的外键列，它将CustomerPortfolio实体类与Customer实体类关联起来。
fetch属性指定了在查询关联关系时应该采用的加载策略。在这里，FetchType.LAZY表示采用延迟加载策略，也就是只有在使用到关联关系时才会去加载关联的实体对象。
采用延迟加载可以提高系统性能，因为不必在查询时立即加载关联对象。相反，当需要使用到关联对象时，再去加载它们，可以减少不必要的数据库访问。
但是，在使用延迟加载时需要特别注意，因为如果在外键对象被垃圾回收之前使用了延迟加载的实体对象，可能会导致LazyInitializationException异常。
private Customer customer;



Lombok:
@Data 自动生成getter、setter方法
@NoArgsConstructor 自动生成无参构造函数
@AllArgsConstructor 自动生成全参构造函数


```


3. MySQL
```
MAC:
1. 如果出现 zsh: command not found: mysql，则需要将 MySQL 安装目录添加到系统路径中。
- echo 'export PATH="/usr/local/mysql/bin:$PATH"' >> ~/.zshrc
- source ~/.zshrc

2. 控制台输入 mysql -u root -p 后，再输入安装 MySQL 时设置的密码 123456789

3. 进入 MySQL shell 后，创建一个名为admin的用户（如果该用户已经存在，则可以跳过此步骤）。
- CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

4. 创建数据库 mfu_db。
- CREATE DATABASE mfu_db;

5. 授予 admin 用户访问 mfu_db 数据库的权限。
- GRANT ALL PRIVILEGES ON mfu_db.* TO 'admin'@'localhost';

6. 刷新MySQL权限表。
- FLUSH PRIVILEGES;

7. 退出 MySQL shell。
- exit

```

4. Spring
```
1. 在 Spring Boot 应用程序启动类上添加 @EnableJpaRepositories 注解，用于启用 JPA Repository 的自动扫描。
2. @Autowired 用于自动装配对象。当 Spring 容器中存在一个与需要装配的对象类型匹配的 Bean 时，会自动将该 Bean 注入到需要装配的对象中，
    从而避免了手动实例化Bean对象的繁琐和复杂性。使用 @Autowired 注解，可以大大提高 Spring 应用程序的可维护性和灵活性。
3.    
```

