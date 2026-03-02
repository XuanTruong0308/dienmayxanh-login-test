# Câu Hỏi & Trả Lời: Kiểm Thử Tự Động (Automation Testing) với Java

> Dự án thực tế tham chiếu: **Practice Test Automation Login** — [practicetestautomation.com/practice-test-login/](https://practicetestautomation.com/practice-test-login/)  
> Stack: Java 21, Selenium 4.16.1, TestNG 7.8.0, WebDriverManager 5.6.3, Maven

---

## Câu 1. Sinh viên hiểu như thế nào về kiểm thử tự động?

**Kiểm thử tự động (Automation Testing)** là việc sử dụng các công cụ, phần mềm hoặc kịch bản (scripts) do con người viết ra để thực thi các bài kiểm tra phần mềm một cách tự động, thay vì phải thao tác thủ công (Manual Testing).

- **Mục tiêu:** So sánh kết quả thực tế (*Actual Result*) với kết quả mong đợi (*Expected Result*).
- **Giá trị mang lại:** Giảm thiểu sai sót của con người, tiết kiệm thời gian, đặc biệt hữu ích cho các bài kiểm tra lặp đi lặp lại (Regression Testing).

---

## Câu 2. Quy trình các bước chính trong kiểm thử tự động

Một quy trình chuẩn gồm **6 bước**:

| Bước | Tên | Mô tả |
|------|-----|--------|
| 1 | **Xác định phạm vi** (Scope) | Quyết định những chức năng nào cần/có thể tự động hóa. Không phải mọi thứ đều nên tự động. |
| 2 | **Lựa chọn công cụ** (Tool Selection) | Chọn framework, ngôn ngữ và công cụ phù hợp (VD: Selenium, TestNG, Java). |
| 3 | **Lên kế hoạch & Thiết kế** (Planning & Design) | Xây dựng chiến lược kiểm thử, kiến trúc framework (VD: Page Object Model). |
| 4 | **Thiết lập môi trường** (Environment Setup) | Cài đặt phần cứng, phần mềm, mạng để chạy script. |
| 5 | **Phát triển kịch bản** (Test Script Development) | Viết code tự động hóa các test case. |
| 6 | **Thực thi & Báo cáo** (Execution & Reporting) | Chạy script, phân tích lỗi và duy trì (maintain) bộ test khi phần mềm thay đổi. |

---

## Câu 3. Kiểm thử đơn vị tự động & Phân biệt với Kiểm thử tự động nói chung

**Kiểm thử đơn vị tự động (Automated Unit Testing)** là việc viết code (thường do lập trình viên thực hiện) để kiểm tra các thành phần nhỏ nhất của phần mềm (một hàm, một method, một class) một cách độc lập.

**Bảng so sánh:**

| Tiêu chí | Unit Testing | UI / E2E Testing |
|----------|-------------|-----------------|
| **Phạm vi** | Hẹp — chỉ kiểm tra một hàm/method cụ thể | Rộng — kiểm tra luồng nghiệp vụ, giao diện người dùng |
| **Người thực hiện** | Lập trình viên (Developer) | Kỹ sư kiểm thử (QA / Automation Tester) |
| **Tốc độ chạy** | Rất nhanh (vài mili-giây) | Chậm hơn (phút/giờ) do thao tác trên trình duyệt |
| **Công cụ Java** | JUnit, TestNG, Mockito | Selenium, Appium, Cucumber |

---

## Câu 4. Quy trình kiểm thử đơn vị tự động

Quy trình tuân theo mô hình **AAA (Arrange – Act – Assert)**:

1. **Phân tích yêu cầu:** Hiểu rõ hàm cần test nhận đầu vào gì và trả ra kết quả gì.
2. **Arrange (Chuẩn bị):** Khởi tạo đối tượng, thiết lập dữ liệu đầu vào (mock data).
3. **Act (Thực thi):** Gọi hàm hoặc method cần kiểm thử.
4. **Assert (Xác nhận):** Kiểm tra kết quả trả về có khớp với kết quả mong đợi không.
5. **Refactor (Tối ưu):** Chỉnh sửa lại code test hoặc source code nếu cần thiết.

**Ví dụ thực tế trong dự án** (`LoginTest.java`):
```java
@Test(priority = 1)
public void testLoginSuccess() {
    // Arrange + Act: gọi hàm login với thông tin hợp lệ
    loginPage.login("student", "Password123");

    // Assert: xác nhận đăng nhập thành công
    Assert.assertTrue(loginPage.isLoginSuccessful());
    Assert.assertEquals(loginPage.getSuccessMessage(), "Logged In Successfully");
}
```

---

## Câu 5. Các công cụ, framework kiểm thử tự động cho Java

| Mục đích | Công cụ / Framework |
|----------|---------------------|
| **Unit Testing** | JUnit, TestNG |
| **Mocking (giả lập dữ liệu)** | Mockito, PowerMock |
| **Web UI Testing** | Selenium WebDriver, Playwright for Java, Selenide |
| **API Testing** | REST Assured |
| **BDD (Behavior Driven Development)** | Cucumber |

**Dự án này sử dụng** (`pom.xml`): Selenium `4.16.1` + TestNG `7.8.0` + WebDriverManager `5.6.3`.

> **Trang được kiểm thử:** `https://practicetestautomation.com/practice-test-login/`

---

## Câu 6. Tổng quan về framework JUnit

**JUnit** là framework kiểm thử đơn vị phổ biến nhất dành cho Java, giúp lập trình viên viết và chạy bài test có tổ chức.

**Các Annotation chính (JUnit 5):**

| Annotation | Mô tả |
|-----------|--------|
| `@Test` | Đánh dấu một method là một test case |
| `@BeforeAll` / `@AfterAll` | Chạy **1 lần** trước/sau **tất cả** test case trong class |
| `@BeforeEach` / `@AfterEach` | Chạy trước/sau **mỗi** test case |
| `@Disabled` | Bỏ qua, không chạy test case này |

**Các Assertion thường dùng:** `assertEquals()`, `assertTrue()`, `assertFalse()`, `assertNull()`, `assertThrows()`

**Vòng đời (Lifecycle):**
```
Bắt đầu class
  → @BeforeAll
    → @BeforeEach → @Test → @AfterEach   (lặp lại cho mỗi test)
  → @AfterAll
Kết thúc
```

---

## Câu 7. Triển khai JUnit trong Visual Studio Code (VS Code)

1. **Cài đặt tiện ích:** Tải **Extension Pack for Java** và **Test Runner for Java** trong VS Code.
2. **Tạo dự án:** Tạo project Java dùng Maven hoặc Gradle.
3. **Thêm thư viện:** Thêm dependency JUnit 5 vào `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.junit.jupiter</groupId>
       <artifactId>junit-jupiter</artifactId>
       <version>5.10.0</version>
       <scope>test</scope>
   </dependency>
   ```
4. **Viết Test:** Tạo file trong `src/test/java/`, viết các method và gắn `@Test`.
5. **Chạy Test:** Nhấn vào biểu tượng **▶ Run Test** (Code Lens) ngay trên method `@Test`, hoặc xem trong thẻ **Testing** trên thanh công cụ trái.

---

## Câu 8. Tổng quan về framework TestNG

**TestNG** (*Test Next Generation*) ra đời sau JUnit, được thiết kế để khắc phục hạn chế của JUnit cũ và hỗ trợ mạnh hơn cho Integration Test và End-to-End Test.

**Các Annotation đặc trưng:**

| Annotation | Phạm vi |
|-----------|---------|
| `@Test` | Đánh dấu một method là test case |
| `@BeforeSuite` / `@AfterSuite` | Chạy 1 lần cho toàn bộ Test Suite |
| `@BeforeTest` / `@AfterTest` | Chạy trước/sau mỗi thẻ `<test>` trong `testng.xml` |
| `@BeforeClass` / `@AfterClass` | Chạy 1 lần cho mỗi class |
| `@BeforeMethod` / `@AfterMethod` | Chạy trước/sau mỗi method `@Test` |
| `@DataProvider` | Cung cấp dữ liệu cho Data-Driven Testing |

**Các Assertion:** `Assert.assertEquals()`, `Assert.assertTrue()`. Đặc biệt có **`SoftAssert`**: nếu 1 lỗi xảy ra, test vẫn tiếp tục đến hết rồi mới báo lỗi (thay vì dừng ngay).

**Vòng đời:** `Suite → Test → Class → Method`

**File cấu hình `testng.xml` trong dự án:**
```xml
<suite name="Login Test Suite" parallel="false">
    <test name="Login Phone Number Validation Tests">
        <classes>
            <class name="com.practicetestautomation.tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

---

## Câu 9. *(Không có câu hỏi — bỏ qua)*

---

## Câu 10. Triển khai TestNG trong Eclipse

1. **Cài plugin:** Vào `Help → Eclipse Marketplace`, tìm và cài **TestNG for Eclipse**.
2. **Tạo dự án & Thêm thư viện:** Tạo Java Project → Chuột phải → `Build Path → Add Libraries → TestNG`.
3. **Tạo Test Class:** Chuột phải thư mục `src → New → Other → TestNG → TestNG Class`. Chọn các annotation muốn sinh sẵn (`BeforeMethod`, `AfterMethod`...).
4. **Chạy Test:** Chuột phải file class → `Run As → TestNG Test`. Kết quả hiển thị ở tab **TestNG** chuyên biệt.

---

## Câu 11. Bộ công cụ Selenium

**Selenium** là bộ công cụ mã nguồn mở dùng để **tự động hóa thao tác trên trình duyệt web** (Browser Automation).

- **Hiểu đơn giản:** Như một con robot bắt chước mọi thao tác người dùng (click chuột, gõ phím, cuộn trang...).
- **Sử dụng để làm gì:**
  - Kiểm thử chức năng giao diện web (UI / E2E Testing).
  - Đảm bảo web hoạt động đúng trên nhiều trình duyệt (Chrome, Firefox, Edge) — *Cross-browser Testing*.
  - Thu thập dữ liệu (Web Scraping).

**Các thành phần Selenium:**

| Thành phần | Vai trò |
|-----------|---------|
| **Selenium WebDriver** | API để điều khiển trình duyệt trực tiếp |
| **Selenium Grid** | Chạy test song song trên nhiều máy/trình duyệt |
| **Selenium IDE** | Công cụ record & playback (tiện ích trình duyệt) |

---

## Câu 12. Triển khai TestNG + Selenium kiểm thử chức năng đăng nhập (Login)

Dự án áp dụng **Page Object Model (POM)** — tách biệt logic test và logic thao tác UI.

### Cấu trúc dự án
```
src/
├── main/java/com/practicetestautomation/pages/
│   ├── BasePage.java       ← Các method dùng chung (click, sendKeys, getText...)
│   ├── LoginPage.java      ← Chứa locator và action của trang Login
│   └── HomePage.java       ← Mở URL trang chủ
└── test/java/com/practicetestautomation/tests/
    ├── BaseTest.java        ← Khởi tạo và đóng ChromeDriver
    └── LoginTest.java       ← Các test case thực tế
```

### @BeforeMethod — Cấu hình (BaseTest.java)
```java
@BeforeMethod
public void setUp() {
    WebDriverManager.chromedriver().setup();   // tự tải đúng phiên bản ChromeDriver

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--disable-blink-features=AutomationControlled");

    // Chạy Headless khi ở môi trường CI, có giao diện khi chạy Local
    boolean isCI = System.getenv("CI") != null;
    if (isCI) {
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
    }

    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
}
```

### @Test — Thực hiện Test (LoginTest.java)
```java
@Test(priority = 1)
public void testLoginSuccess() {
    loginPage.login("student", "Password123");          // Nhập và click đăng nhập
    Assert.assertTrue(loginPage.isLoginSuccessful());   // URL có chứa "logged-in-successfully"
    Assert.assertEquals(loginPage.getSuccessMessage(), "Logged In Successfully");
}

@Test(priority = 2)
public void testWrongPassword() {
    loginPage.login("student", "wrongpass");
    Assert.assertTrue(loginPage.getErrorMessage().contains("Your password is invalid!"));
}

@Test(priority = 3)
public void testWrongUsername() {
    loginPage.login("wronguser", "Password123");
    Assert.assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"));
}
```

### Page Object — LoginPage.java (Locators & Actions)
```java
@FindBy(id = "username")  private WebElement usernameInput;
@FindBy(id = "password")  private WebElement passwordInput;
@FindBy(id = "submit")    private WebElement submitButton;
@FindBy(className = "post-title") private WebElement successMessage;
@FindBy(id = "error")     private WebElement errorMessage;

public void login(String username, String password) {
    enterText(usernameInput, username);
    enterText(passwordInput, password);
    clickElement(submitButton);
}
```

### @AfterMethod — Dọn dẹp (BaseTest.java)
```java
@AfterMethod
public void tearDown() {
    if (driver != null) {
        driver.quit();   // Đóng trình duyệt, giải phóng tài nguyên
    }
}
```

---

## Câu 13. Tổng quan CI/CD trong kiểm thử tự động

| Khái niệm | Đầy đủ | Mô tả |
|-----------|--------|--------|
| **CI** | Continuous Integration (Tích hợp liên tục) | Tự động gộp code từ nhiều lập trình viên, tự động build và chạy unit test sau mỗi lần push. |
| **CD** | Continuous Delivery/Deployment (Chuyển giao/Triển khai liên tục) | Tự động đẩy phần mềm đã qua test lên môi trường Staging/Production. |

**Lợi ích khi tích hợp kiểm thử vào CI/CD:**
- Phát hiện lỗi **cực sớm** ngay khi dev vừa đẩy code lên.
- Giảm tải việc chạy test thủ công trên máy cá nhân.
- Đảm bảo code luôn ở trạng thái **"có thể release"** bất cứ lúc nào.

---

## Câu 14. Triển khai CI/CD với GitHub Actions

GitHub Actions giúp tự động hóa luồng CI/CD ngay trên GitHub.

**File cấu hình thực tế trong dự án** (`.github/workflows/test.yml`):

```yaml
name: Dien May Xanh Login Test - CI/CD

on:
  push:
    branches: [ main, master, develop ]
  pull_request:
    branches: [ main, master, develop ]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      # Bước 1: Lấy source code về máy ảo
      - name: Checkout code
        uses: actions/checkout@v4

      # Bước 2: Cài đặt Java 21
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven

      # Bước 3: Cài Google Chrome (để chạy Selenium Headless)
      - name: Install Google Chrome
        run: |
          sudo apt-get update
          sudo apt-get install -y google-chrome-stable

      # Bước 4: Cài dependencies
      - name: Install dependencies
        run: mvn clean install -DskipTests

      # Bước 5: Chạy toàn bộ test (Headless mode)
      - name: Run tests
        run: mvn test -Dheadless=true

      # Bước 6: Upload báo cáo test
      - name: Upload Test Reports
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: test-reports
          path: |
            target/surefire-reports/
            test-output/
          retention-days: 30

      # Bước 7: Hiển thị kết quả test trực tiếp trên GitHub
      - name: Publish Test Results
        if: always()
        uses: dorny/test-reporter@v1
        with:
          name: TestNG Results
          path: target/surefire-reports/TEST-*.xml
          reporter: java-junit
          fail-on-error: false
```

**Luồng hoạt động:**
```
Developer push code lên GitHub
        ↓
GitHub Actions tự động kích hoạt máy ảo (ubuntu-latest)
        ↓
Checkout code → Cài Java 21 → Cài Chrome → Build Maven
        ↓
Chạy mvn test (TestNG + Selenium Headless)
        ↓
Báo cáo kết quả: ✅ PASS (xanh) hoặc ❌ FAIL (đỏ) trực tiếp trên GitHub
        ↓
Upload surefire-reports để xem chi tiết
```