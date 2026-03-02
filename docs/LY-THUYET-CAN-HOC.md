# 📚 LÝ THUYẾT THEO FLOW TỪ TỪNG FILE

> **Giải thích ngắn gọn từng file + method trong project**  
> **Format**: File → Methods → Giải thích 2 dòng

---

## 🎯 FLOW TRÌNH BÀY (Thứ tự presentation)

```
1. BaseTest.java      → Setup môi trường test (WebDriver, ChromeOptions)
2. HomePage.java      → Mở URL trang login
3. LoginPage.java     → Thực hiện login + lấy kết quả
4. LoginTest.java     → Viết test cases + assertions
5. BasePage.java      → Common methods cho tất cả pages
```

---

## 📄 1. BaseTest.java
**Mục đích**: Setup và teardown WebDriver cho mỗi test case

### Constructor
```java
protected WebDriver driver;
```
- Khai báo biến driver để điều khiển browser
- `protected` để subclass (LoginTest) có thể dùng

### Method: setUp()
```java
@BeforeMethod
public void setUp()
```
- Chạy trước MỖI test case để setup WebDriver + ChromeOptions
- Cấu hình implicit wait (10s) và page load timeout (30s)

### Method: tearDown()
```java
@AfterMethod
public void tearDown()
```
- Chạy sau MỖI test case để đóng browser (driver.quit())
- Giải phóng resources, tránh memory leak

---

## 📄 2. HomePage.java
**Mục đích**: Mở trang login (URL đầu tiên)

### Constructor
```java
public HomePage(WebDriver driver)
```
- Khởi tạo HomePage với driver từ BaseTest
- Gọi super(driver) để chạy constructor của BasePage

### Method: open()
```java
public void open()
```
- Navigate đến URL trang login: `driver.get(URL)`
- Đây là action đầu tiên khi bắt đầu test

---

## 📄 3. LoginPage.java
**Mục đích**: Chứa locators và actions liên quan đến login form

### Locators (Elements)
```java
@FindBy(id = "username")
private WebElement usernameInput;

@FindBy(id = "password")
private WebElement passwordInput;

@FindBy(id = "submit")
private WebElement submitButton;

@FindBy(className = "post-title")
private WebElement successMessage;

@FindBy(id = "error")
private WebElement errorMessage;
```
- Định nghĩa các elements trên trang login
- `@FindBy` annotation giúp PageFactory tự động khởi tạo

### Constructor
```java
public LoginPage(WebDriver driver)
```
- Khởi tạo LoginPage với driver từ BaseTest
- Gọi super(driver) để init locators qua PageFactory

### Method: login(String username, String password)
```java
public void login(String username, String password)
```
- Thực hiện login: nhập username → nhập password → click submit
- Dùng các methods từ BasePage (enterText, clickElement)

### Method: getSuccessMessage()
```java
public String getSuccessMessage()
```
- Lấy text từ successMessage element khi login thành công
- Return String để test class verify

### Method: getErrorMessage()
```java
public String getErrorMessage()
```
- Lấy text từ errorMessage element khi login fail
- Return String để test class verify

### Method: isLoginSuccessful()
```java
public boolean isLoginSuccessful()
```
- Check URL có chứa "logged-in-successfully" không
- Return boolean để dùng với Assert.assertTrue()

---

## 📄 4. LoginTest.java
**Mục đích**: Viết test cases và assertions để verify kết quả

### Variables
```java
private HomePage homePage;
private LoginPage loginPage;
```
- Khai báo page objects để sử dụng trong tests
- Được khởi tạo trong initPages()

### Method: initPages()
```java
@BeforeMethod
public void initPages()
```
- Chạy trước MỖI test để khởi tạo page objects
- Navigate đến URL qua homePage.open()

### Method: testLoginSuccess()
```java
@Test(priority = 1)
public void testLoginSuccess()
```
- Test case 1: Login với username/password đúng
- Verify URL chứa "logged-in-successfully" và message = "Logged In Successfully"

### Method: testWrongPassword()
```java
@Test(priority = 2)
public void testWrongPassword()
```
- Test case 2: Login với password sai
- Verify error message chứa "Your password is invalid!"

### Method: testWrongUsername()
```java
@Test(priority = 3)
public void testWrongUsername()
```
- Test case 3: Login với username sai
- Verify error message chứa "Your username is invalid!"

---

## 📄 5. BasePage.java
**Mục đích**: Chứa common methods dùng chung cho tất cả page classes

### Variables
```java
protected WebDriver driver;
protected WebDriverWait wait;
```
- driver: Để gọi Selenium commands (get, getCurrentUrl...)
- wait: Explicit wait để đợi elements ready

### Constructor
```java
public BasePage(WebDriver driver)
```
- Khởi tạo driver và WebDriverWait (timeout 10s)
- Gọi PageFactory.initElements() để init các @FindBy elements

### Method: waitForElementVisible(WebElement element)
```java
protected void waitForElementVisible(WebElement element)
```
- Đợi element hiển thị trên page (visible)
- Dùng ExpectedConditions.visibilityOf()

### Method: waitForElementClickable(WebElement element)
```java
protected void waitForElementClickable(WebElement element)
```
- Đợi element có thể click được (clickable)
- Dùng ExpectedConditions.elementToBeClickable()

### Method: delay(int milliseconds)
```java
protected void delay(int milliseconds)
```
- Dừng execution trong N milliseconds (Thread.sleep)
- Mục đích: Quan sát actions khi chạy test

### Method: clickElement(WebElement element)
```java
protected void clickElement(WebElement element)
```
- Đợi element clickable → click → delay 3 giây
- Dùng chung cho tất cả click actions

### Method: enterText(WebElement element, String text)
```java
protected void enterText(WebElement element, String text)
```
- Đợi element visible → clear → delay 1s → sendKeys → delay 2s
- Dùng chung cho tất cả nhập text actions

### Method: getElementText(WebElement element)
```java
protected String getElementText(WebElement element)
```
- Đợi element visible → lấy text (getText())
- Return String để verify trong test

### Method: isElementDisplayed(WebElement element)
```java
protected boolean isElementDisplayed(WebElement element)
```
- Check element có hiển thị không (isDisplayed())
- Return false nếu element không tồn tại (catch exception)

---

## 📊 BẢNG TỔNG HỢP NHANH

| File | Số Methods | Trách nhiệm chính |
|------|-----------|-------------------|
| **BaseTest.java** | 2 | Setup/teardown WebDriver |
| **HomePage.java** | 1 | Mở URL trang login |
| **LoginPage.java** | 4 | Login actions + getters |
| **LoginTest.java** | 3 | Test cases + assertions |
| **BasePage.java** | 7 | Common methods (wait, click, enterText) |

---

## 🔄 FLOW HOÀN CHỈNH CỦA 1 TEST

```
1. BaseTest.setUp()
   → Setup WebDriver, ChromeOptions
   → Set timeouts

2. LoginTest.initPages()
   → homePage = new HomePage(driver)
   → homePage.open()  → driver.get(URL)
   → loginPage = new LoginPage(driver)

3. LoginTest.testLoginSuccess()
   → loginPage.login("student", "Password123")
       → enterText(usernameInput, "student")  [BasePage method]
       → enterText(passwordInput, "Password123")  [BasePage method]
       → clickElement(submitButton)  [BasePage method]
   → Assert.assertTrue(loginPage.isLoginSuccessful())
   → Assert.assertEquals(loginPage.getSuccessMessage(), "Logged In Successfully")

4. BaseTest.tearDown()
   → driver.quit()
```

---

## 📝 KEYWORDS QUAN TRỌNG

### TestNG Annotations
- `@BeforeMethod` - Chạy trước mỗi @Test (setup môi trường)
- `@AfterMethod` - Chạy sau mỗi @Test (cleanup)
- `@Test(priority = 1)` - Đánh dấu test case (priority = thứ tự chạy)

### Selenium WebDriver
- `WebDriver driver` - Interface điều khiển browser
- `driver.get(URL)` - Navigate đến URL
- `driver.quit()` - Đóng browser + giải phóng session
- `driver.getCurrentUrl()` - Lấy URL hiện tại

### WebDriverWait
- `WebDriverWait wait` - Explicit wait object
- `ExpectedConditions.visibilityOf(element)` - Đợi element visible
- `ExpectedConditions.elementToBeClickable(element)` - Đợi element clickable

### Page Object Model
- `BasePage` - Parent class chứa common methods
- `HomePage, LoginPage extends BasePage` - Child classes
- `@FindBy(id = "username")` - Locator annotation
- `PageFactory.initElements(driver, this)` - Initialize locators

### WebDriverManager
- `WebDriverManager.chromedriver().setup()` - Auto download ChromeDriver
- Không cần manual download + set system property

### ChromeOptions
- `ChromeOptions options = new ChromeOptions()` - Cấu hình Chrome
- `options.addArguments("--start-maximized")` - Mở full screen
- `options.addArguments("--headless=new")` - Chạy không UI (CI/CD)

### Assertions
- `Assert.assertTrue(condition)` - Verify condition = true
- `Assert.assertEquals(actual, expected)` - Verify actual = expected

---

## 💡 TIPS KHI TRẢ LỜI

### Nếu hỏi về File:
"File này có trách nhiệm gì?"
→ Nhìn mục đích ở đầu mỗi file

### Nếu hỏi về Method:
"Method này làm gì?"
→ Nhìn giải thích 2 dòng dưới mỗi method

### Nếu hỏi về Flow:
"Test chạy như thế nào?"
→ Nhìn phần "FLOW HOÀN CHỈNH CỦA 1 TEST"

### Nếu hỏi về Keyword:
"@BeforeMethod là gì?"
→ Nhìn phần "KEYWORDS QUAN TRỌNG"

**Chúc bạn thành công! 🚀**
