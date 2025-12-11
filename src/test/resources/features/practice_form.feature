Feature: Student Registration Form
  As a student
  I want to fill out the registration form
  So that I can register successfully

  Scenario: Registro exitoso con campos obligatorios
    Given el usuario navega al formulario de práctica
    When el usuario ingresa "Jose" como nombre
    And el usuario ingresa "Terrones" como apellido
    And el usuario selecciona el género "Male"
    And el usuario ingresa "9084668890" como número móvil
    And el usuario hace clic en Submit
    Then se debe mostrar el modal de confirmación
    And el título del modal debe ser "Thanks for submitting the form"
    And el modal debe contener "Jose Terrones"

  Scenario: Registro con campos adicionales
    Given el usuario navega al formulario de práctica
    When el usuario ingresa "Marta" como nombre
    And el usuario ingresa "González" como apellido
    And el usuario ingresa "marta.gonzalez@example.com" como email
    And el usuario selecciona el género "Female"
    And el usuario ingresa "9876753028" como número móvil
    And el usuario selecciona el hobby "Reading"
    And el usuario ingresa "123 Main Street, Apartment 5B" como dirección
    And el usuario hace clic en Submit
    Then se debe mostrar el modal de confirmación
    And el título del modal debe ser "Thanks for submitting the form"
    And el modal debe contener "Marta González"
    And el modal debe contener "marta.gonzalez@example.com"
    And el modal debe contener "Reading"

  Scenario: Validación de formulario incompleto
    Given el usuario navega al formulario de práctica
    When el usuario ingresa "Cristian" como nombre
    And el usuario hace clic en Submit
    Then el campo "lastName" debe tener borde rojo
    And el campo "userNumber" debe tener borde rojo
    And no se debe mostrar el modal de confirmación