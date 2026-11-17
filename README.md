# demo-api

一个用于演示的 Spring Boot 项目，提供三个 REST 接口。构建工具使用 Maven Wrapper，无需在本地安装 Maven 或 Gradle，只要有 JDK 17+ 即可运行。

- **Spring Boot** 4.1.2
- **Java** 17
- **构建** Maven（通过 `mvnw` / `mvnw.cmd`）

## 接口一览

| 方法 | 路径 | 说明 | 是否需要请求体 |
| --- | --- | --- | --- |
| GET | `/api/hello?name=你的名字` | 返回问候语与服务器时间，`name` 可选，默认 `World` | 否 |
| GET | `/api/todos` | 返回全部待办事项（内存数据，启动时预置 3 条） | 否 |
| POST | `/api/todos` | 新增一条待办，成功返回 `201 Created` | 是，JSON `{"title": "..."}` |

## 如何运行

```bash
# 1. 启动应用（默认端口 8080）
./mvnw spring-boot:run
# Windows 命令行：mvnw.cmd spring-boot:run

# 2. 打包成可执行 jar
./mvnw clean package
java -jar target/demo-api-0.0.1-SNAPSHOT.jar
```

## 调用示例

```bash
# 接口一：问候
curl "http://localhost:8080/api/hello?name=GitHub"

# 接口二：查询待办
curl http://localhost:8080/api/todos

# 接口三：新增待办
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"部署到生产环境"}'
```

接口一返回示例：

```json
{
  "message": "Hello, GitHub!",
  "service": "demo-api",
  "timestamp": "2026-11-17T16:30:12.345"
}
```

接口二返回示例：

```json
{
  "total": 3,
  "items": [
    { "id": 1, "title": "学习 Spring Boot", "completed": false },
    { "id": 2, "title": "编写三个 REST 接口", "completed": false },
    { "id": 3, "title": "把仓库推送到 GitHub", "completed": false }
  ]
}
```

## 项目结构

```
src/main/java/com/example/demoapi
├── DemoApiApplication.java          # 启动类
└── todo
    ├── Todo.java                    # 待办模型（record）
    ├── CreateTodoRequest.java       # 新增待办的请求体
    ├── TodoService.java             # 内存存储的业务逻辑
    └── TodoController.java          # 三个 REST 接口
```

## 测试

```bash
./mvnw test
```

包含一个 Spring 上下文加载测试，以及针对 `TodoService` 的单元测试。

## 说明

待办数据保存在内存中（`CopyOnWriteArrayList`），应用重启后会重置。本项目仅用于演示接口定义与工程结构，如需持久化可替换为 Spring Data JPA + 数据库。
