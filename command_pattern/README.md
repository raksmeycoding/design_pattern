# Command Pattern vs Strategy Pattern in Plain Java

This document provides a **deep and practical understanding** of two powerful design patterns: **Command** and **Strategy**, implemented in **pure Java (no Spring Boot)** with **real-life analogies**, use cases, and complete runnable examples.

---

## 🧱 Command Pattern

### ✅ Intent

Encapsulate an **action** or **request** as an object so you can:

* Execute it later
* Store it
* Queue it
* Log it

### 📦 Real-World Analogy

> A **remote control** with buttons like "TV Power" or "Volume Up". Each button sends a **command**. You can press it now or later, and it knows what to do when activated.

---

### 💡 Use Cases

* Workflow systems (like user approval)
* Task queues (e.g. background jobs)
* Undo/Redo systems
* Macro recording

---

### 🛠 Components

* **Command interface**: defines `execute()`
* **ConcreteCommand**: implements a specific action
* **Invoker**: triggers the command
* **Receiver**: the real logic

---

### 🧪 Example: Workflow Approval Engine

#### 1. `Command.java`

```java
public interface Command {
    void execute(String payload);
}
```

#### 2. `CreateUserCommand.java`

```java
public class CreateUserCommand implements Command {
    public void execute(String payload) {
        System.out.println("Creating user with payload: " + payload);
    }
}
```

#### 3. `CreateCustomerCommand.java`

```java
public class CreateCustomerCommand implements Command {
    public void execute(String payload) {
        System.out.println("Creating customer with payload: " + payload);
    }
}
```

#### 4. `CommandRegistry.java`

```java
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> registry = new HashMap<>();

    public void register(String name, Command command) {
        registry.put(name, command);
    }

    public Command getCommand(String name) {
        return registry.get(name);
    }
}
```

#### 5. `WorkflowEngine.java`

```java
public class WorkflowEngine {
    private final CommandRegistry registry;

    public WorkflowEngine(CommandRegistry registry) {
        this.registry = registry;
    }

    public void approve(String moduleType, String payload) {
        Command cmd = registry.getCommand(moduleType);
        if (cmd != null) {
            cmd.execute(payload);
        } else {
            System.out.println("No command registered for: " + moduleType);
        }
    }
}
```

#### 6. `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        CommandRegistry registry = new CommandRegistry();
        registry.register("user.create", new CreateUserCommand());
        registry.register("customer.create", new CreateCustomerCommand());

        WorkflowEngine engine = new WorkflowEngine(registry);

        engine.approve("user.create", "{\"name\": \"Raksmey\"}");
        engine.approve("customer.create", "{\"name\": \"TechCorp\"}");
    }
}
```

---

## 🔄 Strategy Pattern

### ✅ Intent

Encapsulate **algorithms** or **policies** in separate objects so they can be **interchanged** at runtime.

### 📦 Real-World Analogy

> Cooking a chicken: you can **grill**, **boil**, or **fry** it. The method changes, but the input (chicken) and goal (cook it) are the same. That method is the **strategy**.

---

### 💡 Use Cases

* Pricing rules
* Tax calculations
* Filtering logic
* Sorting strategies
* Fee policy by country or user type

---

### 🛠 Components

* **Strategy interface**: defines the algorithm
* **ConcreteStrategy**: implements one variant
* **Context**: uses the strategy

---

### 🧪 Example: Discount Calculation Engine

#### 1. `DiscountStrategy.java`

```java
public interface DiscountStrategy {
    double apply(double amount);
}
```

#### 2. `GoldMemberDiscount.java`

```java
public class GoldMemberDiscount implements DiscountStrategy {
    public double apply(double amount) {
        return amount * 0.80; // 20% off
    }
}
```

#### 3. `NewUserDiscount.java`

```java
public class NewUserDiscount implements DiscountStrategy {
    public double apply(double amount) {
        return amount - 10.0; // Flat $10 off
    }
}
```

#### 4. `CheckoutService.java`

```java
public class CheckoutService {
    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double checkout(double amount) {
        return strategy.apply(amount);
    }
}
```

#### 5. `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();

        checkout.setStrategy(new GoldMemberDiscount());
        System.out.println("Gold member pays: " + checkout.checkout(100));

        checkout.setStrategy(new NewUserDiscount());
        System.out.println("New user pays: " + checkout.checkout(100));
    }
}
```

---

## 🤝 Command + Strategy Together

You can also **combine** these:

```java
public class CreateOrderCommand implements Command {
    private DiscountStrategy discountStrategy;

    public CreateOrderCommand(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public void execute(String json) {
        double price = 100.0;
        double discounted = discountStrategy.apply(price);
        System.out.println("Creating order with final price: " + discounted);
    }
}
```

---

## ✅ Summary Table

| Feature    | Command Pattern          | Strategy Pattern                |
| ---------- | ------------------------ | ------------------------------- |
| Purpose    | Wrap and defer execution | Wrap and swap logic             |
| Timing     | Executes later           | Executes immediately            |
| Real-life  | Remote control button    | Cooking method                  |
| Used for   | Workflow, undo, logging  | Rules, pricing, logic switching |
| Key method | `execute()`              | `apply()`                       |
| Example    | `user.create` command    | `GoldDiscountStrategy`          |

---

## 💡 Want to Learn More?

* Try implementing your own command queue
* Try using Strategy for sorting (merge, bubble, quick)
* Combine both in a discount approval workflow

---

© Designed by \[Raksmey Koung] for deep understanding and reuse in enterprise Java.


---


✅ **Yes, it is absolutely possible** — and sometimes even *preferable* — to use the **plain Java Command Pattern style** in your Spring Boot project.

> 💬 You don't need to rely on Spring annotations like `@Component("user.create")` or dynamic bean resolution unless it **adds real benefit** to your use case.

---

## ✅ Summary Answer

| Aspect                          | Yes, You Can Use Plain Java Command Style in Spring Boot                |
| ------------------------------- | ----------------------------------------------------------------------- |
| Is it valid?                    | ✅ Yes                                                                   |
| Is it production-ready?         | ✅ Yes, if structured well                                               |
| Do you need Spring annotations? | ❌ Not required                                                          |
| When to prefer Spring DI style? | When you want to register commands automatically and reduce boilerplate |

---

## 🔧 When is Plain Java Command Style Better?

Use plain style if:

* ✅ You want **full control** over registration (`CommandRegistry`)
* ✅ You prefer **clarity over magic**
* ✅ You don’t need to **autowire beans dynamically**
* ✅ You want to **unit test easily** with no Spring context

---

## 🧱 Example — In Spring Boot, Plain Java Style

```java
@Configuration
public class WorkflowConfig {

    @Bean
    public CommandRegistry commandRegistry(
            CreateUserCommand createUserCommand,
            CreateCustomerCommand createCustomerCommand
    ) {
        CommandRegistry registry = new CommandRegistry();
        registry.register("user.create", createUserCommand);
        registry.register("customer.create", createCustomerCommand);
        return registry;
    }
}
```

You still **use Spring beans**, but **register them explicitly**.

```java
@Component
public class WorkflowService {

    private final CommandRegistry commandRegistry;

    public WorkflowService(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public void approve(String type, String payload) {
        Command command = commandRegistry.getCommand(type);
        if (command != null) {
            command.execute(payload);
        } else {
            throw new RuntimeException("Unknown command: " + type);
        }
    }
}
```

> This way you get the **benefits of Spring dependency injection**, but also retain the **explicitness and transparency** of plain Java.

---

## 🤔 So When to Use Spring’s `@Component("type")` and Dynamic Beans?

Only use this if you **need runtime dynamic resolution**:

```java
WorkflowCommand command = (WorkflowCommand) applicationContext.getBean(type);
```

That’s useful when:

* You have **dozens or hundreds** of command implementations
* You don’t want to manually register all of them
* You want to **fully decouple commands from registry**

But it's also:

* 🧠 Harder to trace
* 🧪 More difficult to test
* ⚠️ Riskier if type strings are wrong (e.g., typo in `"user.create"`)

---

## ✅ Conclusion

> 💡 **Plain Command Pattern inside Spring Boot = clean, controlled, and production-friendly**.

You can:

* Use `CommandRegistry` manually
* Autowire concrete command classes
* Test each command independently

✅ It's not just possible — it's **a clean architectural choice**, especially for your **workflow approval engine**.

---

You're right! The previous version was optimized for Java code structure but lacked formatting for easier reading in documentation style (e.g., syntax highlighting, clarity, color-coded blocks like in a `README.md`). Let me give you a **clean, readable, copy-pasteable version of your entire refactored Spring Boot `Command Pattern`-based workflow**, in plain Markdown — perfect for learning, sharing, or adding to your `README.md`.

---

# ✅ Command Pattern Workflow in Spring Boot (Plain Java Style)

This guide shows how to build a **workflow approval engine** in Spring Boot using a **plain Java-style Command Pattern** with a `CommandRegistry`, without relying on dynamic Spring bean lookup.

---

## 🧱 1. Domain Layer

### `WorkflowRequest.java`

```java
@Entity
public class WorkflowRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "user.register", "customer.create"

    @Lob
    private String payload;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String submittedBy;
    private String reviewedBy;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime reviewedAt;
    private boolean deleted = false;

    // Getters and setters...
}
```

### `Status.java`

```java
public enum Status {
    PENDING, APPROVED, REJECTED
}
```

---

## 📦 2. Repository Layer

### `WorkflowRequestRepository.java`

```java
public interface WorkflowRequestRepository extends JpaRepository<WorkflowRequest, Long> {
    List<WorkflowRequest> findBySubmittedByAndDeletedFalse(String submittedBy);
    Optional<WorkflowRequest> findByIdAndDeletedFalse(Long id);
}
```

---

## ⚙️ 3. Command Pattern

### `Command.java`

```java
public interface Command {
    void execute(String payload) throws Exception;
}
```

### `CommandRegistry.java`

```java
@Component
public class CommandRegistry {
    private final Map<String, Command> registry = new HashMap<>();

    public void register(String key, Command command) {
        registry.put(key, command);
    }

    public Command getCommand(String key) {
        return registry.get(key);
    }
}
```

### `CreateUserCommand.java`

```java
@Component
public class CreateUserCommand implements Command {
    @Autowired private UserService userService;
    @Autowired private ObjectMapper mapper;

    @Override
    public void execute(String payload) throws Exception {
        UserDTO dto = mapper.readValue(payload, UserDTO.class);
        userService.createUser(dto);
    }
}
```

### `WorkflowCommandConfig.java`

```java
@Configuration
public class WorkflowCommandConfig {
    @Bean
    public CommandRegistry commandRegistry(CreateUserCommand createUserCommand) {
        CommandRegistry registry = new CommandRegistry();
        registry.register("user.register", createUserCommand);
        return registry;
    }
}
```

---

## 🧠 4. Workflow Service Layer

### `WorkflowService.java`

```java
@Service
public class WorkflowService {
    @Autowired private WorkflowRequestRepository repo;
    @Autowired private ApplicationEventPublisher publisher;
    @Autowired private ObjectMapper mapper;

    public void submit(String type, Map<String, Object> data, String submittedBy) throws JsonProcessingException {
        String json = mapper.writeValueAsString(data);
        WorkflowRequest req = new WorkflowRequest();
        req.setType(type);
        req.setPayload(json);
        req.setSubmittedBy(submittedBy);
        repo.save(req);
    }

    public void approve(Long id, String reviewer) {
        WorkflowRequest req = repo.findByIdAndDeletedFalse(id).orElseThrow();
        if (req.getStatus() != Status.PENDING) throw new IllegalStateException("Already processed");

        req.setStatus(Status.APPROVED);
        req.setReviewedBy(reviewer);
        req.setReviewedAt(LocalDateTime.now());
        repo.save(req);

        publisher.publishEvent(new WorkflowApprovedEvent(req));
    }

    public void reject(Long id, String reviewer) {
        WorkflowRequest req = repo.findByIdAndDeletedFalse(id).orElseThrow();
        if (req.getStatus() != Status.PENDING) throw new IllegalStateException("Already processed");

        req.setStatus(Status.REJECTED);
        req.setReviewedBy(reviewer);
        req.setReviewedAt(LocalDateTime.now());
        repo.save(req);
    }

    public void modify(Long id, Map<String, Object> newData) throws JsonProcessingException {
        WorkflowRequest req = repo.findByIdAndDeletedFalse(id).orElseThrow();
        if (req.getStatus() != Status.PENDING) throw new IllegalStateException("Cannot modify approved/rejected request");

        req.setPayload(mapper.writeValueAsString(newData));
        repo.save(req);
    }

    public void softDelete(Long id) {
        WorkflowRequest req = repo.findByIdAndDeletedFalse(id).orElseThrow();
        req.setDeleted(true);
        repo.save(req);
    }

    public List<WorkflowRequest> getMyPending(String username) {
        return repo.findBySubmittedByAndDeletedFalse(username).stream()
            .filter(r -> r.getStatus() == Status.PENDING)
            .collect(Collectors.toList());
    }

    public WorkflowRequest getById(Long id) {
        return repo.findByIdAndDeletedFalse(id).orElseThrow();
    }
}
```

---

## 🌐 5. API Controller

### `WorkflowController.java`

```java
@RestController
@RequestMapping("/api/v2.0/workflow")
public class WorkflowController {
    @Autowired private WorkflowService service;

    @PostMapping
    public ResponseEntity<?> submit(@RequestParam String type, @RequestBody Map<String, Object> data) throws JsonProcessingException {
        service.submit(type, data, "currentUser");
        return ResponseEntity.ok("Submitted for approval");
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        service.approve(id, "adminUser");
        return ResponseEntity.ok("Approved");
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        service.reject(id, "adminUser");
        return ResponseEntity.ok("Rejected");
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody Map<String, Object> data) throws JsonProcessingException {
        service.modify(id, data);
        return ResponseEntity.ok("Modified");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/myTransaction")
    public ResponseEntity<?> myTx() {
        return ResponseEntity.ok(service.getMyPending("currentUser"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
```

---

## 🔔 6. Event Handling

### `WorkflowApprovedEvent.java`

```java
public class WorkflowApprovedEvent {
    private final WorkflowRequest request;

    public WorkflowApprovedEvent(WorkflowRequest request) {
        this.request = request;
    }

    public WorkflowRequest getRequest() {
        return request;
    }
}
```

### `WorkflowEventListener.java`

```java
@Component
public class WorkflowEventListener {
    @Autowired private CommandRegistry registry;

    @EventListener
    public void handle(WorkflowApprovedEvent event) throws Exception {
        String type = event.getRequest().getType();
        String payload = event.getRequest().getPayload();

        Command command = registry.getCommand(type);
        if (command != null) {
            command.execute(payload);
        } else {
            throw new IllegalArgumentException("No command registered for type: " + type);
        }
    }
}
```

---

## ✅ Benefits of This Architecture

* 🧠 **Explicit and predictable**
* 🧪 Easy to test (`CommandRegistry` is mockable)
* 🔌 Easily extensible (`register("x", new XCommand())`)
* ✅ Clean separation of responsibilities

---

Great question! This confusion is **very common** when learning the **Command Pattern** — especially when mixing it with frameworks like Spring Boot.

Let me clarify this **clearly** and **visually** for you.

---

## ✅ Core Roles in the Command Pattern

| Role         | Responsibility                                   | Your Workflow Code                         |
| ------------ | ------------------------------------------------ | ------------------------------------------ |
| **Invoker**  | Knows *when* to trigger the command (not how)    | `WorkflowEventListener`, `WorkflowService` |
| **Command**  | Encapsulates the action to perform               | `CreateUserCommand implements Command`     |
| **Receiver** | The real business logic (what to do)             | `UserService.createUser(...)`              |
| **Client**   | Configures which command to call for which event | `CommandRegistry` + `WorkflowConfig`       |

---

### 🎯 Breakdown in your project

```java
// Invoker: listens for events and triggers the command
@EventListener
public void handle(WorkflowApprovedEvent event) {
    Command command = registry.getCommand(event.getType());
    command.execute(event.getPayload());
}
```

```java
// Command: wraps a request to do something (decouples Invoker from Receiver)
public class CreateUserCommand implements Command {
    public void execute(String payload) {
        userService.createUser(...); // calls the receiver
    }
}
```

```java
// Receiver: the actual logic that performs the action
public class UserService {
    public void createUser(UserDTO dto) { ... }
}
```

```java
// Client: wires everything together
@Configuration
public class WorkflowCommandConfig {
    registry.register("user.register", createUserCommand);
}
```

---

## ❓ So is CommandRegistry the **Invoker**?

**No.**
The `CommandRegistry` is **not the Invoker** — it's just a **lookup map**. Think of it as the **phone book** — it tells you which command to use, but doesn’t execute anything itself.

### 🔁 Analogy:

| Concept         | Analogy                            |
| --------------- | ---------------------------------- |
| CommandRegistry | Phone book of available commands   |
| Command         | A contact who can do something     |
| Invoker         | The one who dials and speaks       |
| Receiver        | The one who actually does the work |

---

## ✅ Summary

| Role     | Code Example                               | Responsibility             |
| -------- | ------------------------------------------ | -------------------------- |
| Invoker  | `WorkflowEventListener`, `WorkflowService` | Calls `command.execute()`  |
| Command  | `CreateUserCommand`                        | Encapsulates the operation |
| Receiver | `UserService`, `CustomerService`           | Performs the real logic    |
| Registry | `CommandRegistry`                          | Stores and maps commands   |

---

