package com.cibertec.examen_T2.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticeFormSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Given("el usuario navega al formulario de práctica")
    public void elUsuarioNavegaAlFormularioDePractica() {
        driver.get("https://demoqa.com/automation-practice-form");
        // Cerrar posibles anuncios
        try {
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
        } catch (Exception e) {
            // Ignorar si no hay anuncios
        }
    }

    @When("el usuario ingresa {string} como nombre")
    public void elUsuarioIngresaComoNombre(String nombre) {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(nombre);
    }

    @And("el usuario ingresa {string} como apellido")
    public void elUsuarioIngresaComoApellido(String apellido) {
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", lastNameField);
        lastNameField.clear();
        lastNameField.sendKeys(apellido);
    }

    @And("el usuario ingresa {string} como su email")
    public void elUsuarioIngresaComoEmail(String email) {
        WebElement emailField = driver.findElement(By.id("userEmail"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    @And("el usuario selecciona el género {string}")
    public void elUsuarioSeleccionaElGenero(String genero) {
        String genderLabelXpath = String.format("//label[contains(text(),'%s')]", genero);
        WebElement genderLabel = driver.findElement(By.xpath(genderLabelXpath));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", genderLabel);
        js.executeScript("arguments[0].click();", genderLabel);
    }

    @And("el usuario ingresa {string} como su número móvil")
    public void elUsuarioIngresaComoNumeroMovil(String numero) {
        WebElement mobileField = driver.findElement(By.id("userNumber"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", mobileField);
        mobileField.clear();
        mobileField.sendKeys(numero);
    }

    @And("el usuario selecciona el hobby {string}")
    public void elUsuarioSeleccionaElHobby(String hobby) {
        String hobbyLabelXpath = String.format("//label[contains(text(),'%s')]", hobby);
        WebElement hobbyLabel = driver.findElement(By.xpath(hobbyLabelXpath));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", hobbyLabel);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].click();", hobbyLabel);
    }

    @And("el usuario ingresa {string} como dirección")
    public void elUsuarioIngresaComoDireccion(String direccion) {
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addressField);
        addressField.clear();
        addressField.sendKeys(direccion);
    }

    @And("el usuario hace clic en Submit")
    public void elUsuarioHaceClicEnSubmit() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        // Scroll hasta el botón
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", submitButton);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Click usando JavaScript para evitar anuncios
        js.executeScript("arguments[0].click();", submitButton);
    }

    @Then("se debe mostrar el modal de confirmación")
    public void seDebeMostrarElModalDeConfirmacion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        WebElement modal = driver.findElement(By.className("modal-content"));
        assertTrue(modal.isDisplayed(), "El modal de confirmación no se mostró");
    }

    @And("el título del modal debe ser {string}")
    public void elTituloDelModalDebeSer(String tituloEsperado) {
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assertEquals(tituloEsperado, modalTitle.getText(), "El título del modal no coincide");
    }

    @And("el modal debe contener {string}")
    public void elModalDebeContener(String contenido) {
        WebElement modalBody = driver.findElement(By.className("modal-body"));
        assertTrue(modalBody.getText().contains(contenido),
                "El modal no contiene el texto esperado: " + contenido);
    }

    @Then("el campo {string} debe tener borde rojo")
    public void elCampoDebeTenerBordeRojo(String fieldId) {
        try {
            Thread.sleep(500); // Esperar a que se aplique la validación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement field = driver.findElement(By.id(fieldId));
        String borderColor = field.getCssValue("border-color");

        // Verificar si el borde es rojo (puede venir en diferentes formatos)
        boolean isRed = borderColor.contains("rgb(220, 53, 69)") ||
                borderColor.contains("220, 53, 69") ||
                borderColor.contains("#dc3545") ||
                borderColor.contains("dc3545");

        assertTrue(isRed,
                String.format("El campo '%s' debería tener borde rojo. Color actual: %s", fieldId, borderColor));
    }

    @And("no se debe mostrar el modal de confirmación")
    public void noSeDebeMostrarElModalDeConfirmacion() {
        try {
            // Esperar un poco para asegurarse de que no aparece
            Thread.sleep(1000);
            WebElement modal = driver.findElement(By.className("modal-content"));
            assertFalse(modal.isDisplayed(), "El modal no debería estar visible");
        } catch (Exception e) {
            // El modal no está presente, como se esperaba
            assertTrue(true, "El modal no se mostró correctamente");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                Thread.sleep(2000); // Pausa para ver el resultado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
