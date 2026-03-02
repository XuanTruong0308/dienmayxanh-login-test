# ⚡ CHEAT SHEET - Câu Hỏi & Trả Lời Nhanh

> **Mục đích**: Ôn tập nhanh 30 phút trước khi thi/bảo vệ  
> **Dành cho**: Học thuộc câu trả lời ngắn gọn

---

## 🎯 TOP 20 CÂU HỎI THƯỜNG GẶP

### 1. TestNG là gì? Tại sao dùng TestNG?

**Trả lời ngắn**:
> TestNG là test framework cho Java. Dùng vì có nhiều annotations linh hoạt (@BeforeMethod, @AfterMethod), hỗ trợ priority, grouping, và parallel execution.

**Code ví dụ**:
```java
@BeforeMethod  // Chạy trước mỗi test
@Test(priority = 1)  // Test case
@AfterMethod  // Chạy sau mỗi test
```

---

### 2. @BeforeMethod vs @BeforeClass khác gì?

**Trả lời ngắn**:
- **@BeforeMethod**: Chạy trước **MỖI** @Test (nhiều lần)
- **@BeforeClass**: Chạy **1 LẦN** trước tất cả @Test trong class

**Trong dự án**: Dùng @BeforeMethod để mỗi test có browser mới (môi trường sạch).

---

### 3. Selenium WebDriver là gì?

**Trả lời ngắn**:
> Selenium WebDriver là API để tự động hóa browser. Nó giao tiếp với browser thông qua driver (ChromeDriver, GeckoDriver...).

**Kiến trúc**:
```
Test Code → Selenium API → ChromeDriver → Chrome Browser
```

---

### 4. Page Object Model (POM) là gì? Tại sao dùng?

**Trả lời ngắn**:
> POM là design pattern, tách biệt test logic và page logic. 1 page = 1 class.

**Lợi ích**:
- **Maintainability**: Website đổi → chỉ sửa page class
- **Reusability**: Method dùng lại nhiều tests
- **Readability**: Code dễ đọc

**Cấu trúc dự án**:
```
BasePage (common methods)
  ├── HomePage
  └── LoginPage
```

---

### 5. @FindBy là gì?

**Trả lời ngắn**:
> @FindBy là annotation để khai báo locator cho WebElement. Được khởi tạo bởi PageFactory.

**Ví dụ**:
```java
@FindBy(id = "username")
private WebElement usernameInput;
```

---

### 6. Implicit Wait vs Explicit Wait?

**Trả lời ngắn**:

| | Implicit | Explicit |
|-|----------|----------|
| **Scope** | Toàn bộ driver | Element cụ thể |
| **Điều kiện** | Tìm element | Điều kiện cụ thể (visible, clickable...) |
| **Code** | `driver.manage().timeouts().implicitlyWait()` | `wait.until(ExpectedConditions...)` |

**Trong dự án**:
- Implicit: 10s trong BaseTest
- Explicit: waitForElementVisible, waitForElementClickable trong BasePage

---

### 7. 5 loại Locators quan trọng?

**Trả lời ngắn**:
1. **ID** (ưu tiên cao nhất) - `id="username"`
2. **Name** - `name="username"`
3. **ClassName** - `class="error"`
4. **CSS Selector** - `#username`, `.error`
5. **XPath** - `//input[@id='username']`

**Priority**: ID > Name > CSS > XPath

---

### 8. WebDriverManager làm gì?

**Trả lời ngắn**:
> Tự động download và setup browser driver (ChromeDriver) phù hợp với Chrome version.

**Code**:
```java
WebDriverManager.chromedriver().setup();  // Tự động setup
driver = new ChromeDriver();
```

**Thay vì**:
```java
System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
```

---

### 9. ChromeOptions dùng để làm gì?

**Trả lời ngắn**:
> Cấu hình Chrome browser trước khi khởi chạy (maximize, headless, disable extensions...).

**Code trong dự án**:
```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--start-maximized");  // Full screen
options.addArguments("--headless=new");  // Không UI (CI/CD)
```

---

### 10. Headless mode là gì? Khi nào dùng?

**Trả lời ngắn**:
> Headless = chạy browser không hiển thị giao diện.

**Khi nào dùng**:
- ✅ CI/CD (GitHub Actions, Jenkins)
- ✅ Server không có display

**Code**:
```java
boolean isCI = System.getenv("CI") != null;
if (isCI) {
    options.addArguments("--headless=new");
}
```

---

### 11. PageFactory.initElements() làm gì?

**Trả lời ngắn**:
> Khởi tạo tất cả elements được đánh dấu @FindBy trong page class.

**Code**:
```java
public BasePage(WebDriver driver) {
    PageFactory.initElements(driver, this);  // Init @FindBy elements
}
```

---

### 12. assertTrue vs assertEquals?

**Trả lời ngắn**:
- **assertTrue**: Check boolean condition
- **assertEquals**: So sánh 2 giá trị

**Ví dụ**:
```java
Assert.assertTrue(loginPage.isLoginSuccessful());  // Boolean
Assert.assertEquals(actual, "Expected text");  // Compare values
```

---

### 13. driver.quit() vs driver.close()?

**Trả lời ngắn**:
- **quit()**: Đóng **TẤT CẢ** windows + giải phóng driver session (✅ Nên dùng)
- **close()**: Chỉ đóng **current** window

---

### 14. Tại sao mỗi test lại mở browser mới?

**Trả lời ngắn**:
> Vì @BeforeMethod chạy trước MỖI test → setUp() khởi tạo driver mới → mỗi test có môi trường sạch, không bị ảnh hưởng test trước.

---

### 15. Maven là gì? Dùng để làm gì?

**Trả lời ngắn**:
> Maven là build tool và dependency management. Tự động download dependencies (Selenium, TestNG) từ Maven Central.

**Command thường dùng**:
```bash
mvn clean test  # Clean + compile + chạy tests
```

---

### 16. testng.xml là gì?

**Trả lời ngắn**:
> File config TestNG, chỉ định test classes nào sẽ chạy, parallel hay sequential.

**Code**:
```xml
<suite name="Test Suite" parallel="false">
    <test name="Login Tests">
        <classes>
            <class name="com.tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

---

### 17. Tại sao LoginPage extends BasePage?

**Trả lời ngắn**:
> Để kế thừa common methods (clickElement, enterText, waitForElementVisible...) từ BasePage. Không phải viết lại code.

---

### 18. Tại sao không để assertions trong Page class?

**Trả lời ngắn**:
> POM best practice: Page class chỉ chứa **UI actions**, Test class chứa **verification logic**. Tách bạch trách nhiệm.

---

### 19. ExpectedConditions là gì?

**Trả lời ngắn**:
> Class Selenium chứa các điều kiện để dùng với Explicit Wait.

**Thường dùng**:
```java
ExpectedConditions.visibilityOf(element)
ExpectedConditions.elementToBeClickable(element)
ExpectedConditions.urlContains("success")
```

---

### 20. Lifecycle 1 test case trong dự án?

**Trả lời ngắn**:
```
@BeforeMethod (BaseTest.setUp)
  → Setup WebDriver
  ↓
@BeforeMethod (LoginTest.initPages)
  → Init page objects, navigate URL
  ↓
@Test (testLoginSuccess)
  → Execute test logic, assertions
  ↓
@AfterMethod (BaseTest.tearDown)
  → driver.quit()
```

---

## 🎓 CÂU HỏI NÂNG CAO (Có thể hỏi)

### 21. Tại sao dùng protected trong BasePage?

**Trả lời**:
> `protected` cho phép subclasses (LoginPage, HomePage) access được methods/fields, nhưng external classes không access được. Encapsulation.

---

### 22. Implicit Wait + Explicit Wait cùng lúc có vấn đề gì?

**Trả lời**:
> Có thể dẫn đến timeout lâu hơn mong đợi (implicit + explicit cộng lại). Nhưng vẫn hoạt động được.

---

### 23. Absolute XPath vs Relative XPath?

**Trả lời**:
- **Absolute**: `/html/body/div[1]/form/input` → Dễ break khi DOM thay đổi (❌)
- **Relative**: `//input[@id='username']` → Flexible, stable hơn (✅)

---

### 24. Soft Assertion là gì?

**Trả lời**:
> Soft Assertion: Nếu 1 assertion fail → test vẫn tiếp tục, báo cáo tất cả fails cuối cùng.
> Hard Assertion (mặc định): Fail → dừng test ngay.

**Code**:
```java
SoftAssert softAssert = new SoftAssert();
softAssert.assertTrue(condition1);
softAssert.assertEquals(actual, expected);
softAssert.assertAll();  // Report all
```

---

### 25. CI/CD trong dự án hoạt động như thế nào?

**Trả lời**:
```
Developer push code → GitHub
  ↓
GitHub Actions detect → Trigger workflow
  ↓
Ubuntu VM: Install Java 21, Chrome
  ↓
Run: mvn test (headless mode)
  ↓
Upload test reports
  ↓
✅ PASS hoặc ❌ FAIL hiển thị trên GitHub
```

---

## 📊 BẢNG TÓM TẮT NHANH

### TestNG Annotations

| Annotation | Khi nào chạy | Trong dự án |
|-----------|-------------|-------------|
| @BeforeMethod | Trước MỖI @Test | setUp(), initPages() |
| @AfterMethod | Sau MỗI @Test | tearDown() |
| @Test | Test case | testLoginSuccess, testWrongPassword... |
| priority = N | Thứ tự thực thi | 1, 2, 3 |

### Wait Types

| Wait | Scope | Code |
|------|-------|------|
| Implicit | Global | `driver.manage().timeouts().implicitlyWait()` |
| Explicit | Element-specific | `wait.until(ExpectedConditions...)` |

### Locators Priority

```
1. ID          ★★★★★ (Best)
2. Name        ★★★★
3. CSS         ★★★
4. XPath       ★★
5. ClassName   ★
```

### Assertion Types

```java
Assert.assertTrue(boolean)     // Boolean check
Assert.assertEquals(a, b)      // Value comparison
Assert.assertNotNull(object)   // Null check
```

### Maven Commands

```bash
mvn clean       # Xóa target/
mvn test        # Chạy tests
mvn clean test  # Clean + test (thường dùng)
```

---

## 🎯 TEMPLATE TRẢ LỜI 3-PHẦN

**Khi được hỏi bất kỳ câu hỏi gì, dùng template này**:

### 1. DEFINE (Định nghĩa)
> "[Concept] là [brief definition]"

### 2. WHY (Tại sao/Lợi ích)
> "Dùng [concept] vì [benefit 1], [benefit 2]"

### 3. EXAMPLE (Ví dụ trong code)
> "Trong dự án em, [show code snippet và giải thích]"

**Ví dụ**:

**Q: Page Object Model là gì?**

**A**:
1. **DEFINE**: "POM là design pattern trong test automation, tách biệt test logic và page logic. Mỗi page của website được represent bằng 1 class."

2. **WHY**: "Dùng POM vì maintainability cao - khi website thay đổi chỉ cần sửa page class, không ảnh hưởng test code. Ngoài ra còn tăng reusability và readability."

3. **EXAMPLE**: "Trong dự án em có BasePage chứa common methods như clickElement, enterText. LoginPage extends BasePage, chứa locators (@FindBy) và actions (login method). Test class chỉ gọi loginPage.login() mà không cần biết chi tiết implementation."

---

## ⚡ GHI NHỚ NHANH

### Code Snippets Quan Trọng

**Setup WebDriver**:
```java
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver(options);
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

**Page Constructor**:
```java
public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
}
```

**Wait and Click**:
```java
protected void clickElement(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
}
```

**Test Structure**:
```java
@Test(priority = 1)
public void testLoginSuccess() {
    // Arrange + Act
    loginPage.login("student", "Password123");
    
    // Assert
    Assert.assertTrue(loginPage.isLoginSuccessful());
}
```

---

## 🎯 CHECKLIST 5 PHÚT TRƯỚC VÀO PHÒNG

- [ ] TestNG: @BeforeMethod, @AfterMethod, @Test, priority
- [ ] Selenium: WebDriver, WebElement, driver.quit()
- [ ] POM: BasePage → LoginPage, tại sao dùng POM
- [ ] Wait: Implicit vs Explicit
- [ ] Locators: ID > Name > CSS > XPath
- [ ] WebDriverManager: Tự động setup driver
- [ ] Assertions: assertTrue, assertEquals
- [ ] ChromeOptions: --headless, --start-maximized
- [ ] Maven: Dependency management, mvn test
- [ ] PageFactory: initElements, @FindBy

---

## 💡 TIP CUỐI

### Nếu không nhớ chi tiết:
1. **Nói chung chung trước**: "POM là design pattern..."
2. **Đưa ví dụ trong code**: "Trong dự án em có BasePage..."
3. **Giải thích lợi ích**: "Lợi ích là maintainability..."

### Nếu không biết:
- ❌ Đừng nói: "Em không biết"
- ✅ Nói: "Em chưa tìm hiểu kỹ phần này, nhưng em hiểu là..."

### Tự tin:
- Nói chậm, rõ ràng
- Nhìn vào người hỏi
- Đưa ví dụ code thật trong dự án

---

**In ra và mang theo vào phòng thi/bảo vệ! (Nếu được phép) 📄**

**Học thuộc 20 câu đầu → 80% câu hỏi! 🎯**

**Chúc bạn thành công! 🚀**
