# 🍽️ RecipeApp - Android Challenge

## 📌 Descripción
RecipeApp es una aplicación Android desarrollada en **Kotlin** que permite a los usuarios explorar recetas de cocina, visualizar detalles de cada receta y su ubicación en un mapa. La app sigue un enfoque moderno con **Jetpack Compose**, **Ktor** y **Hilt** para garantizar una experiencia fluida y escalable.

---

## 🚀 Tecnologías y Librerías Utilizadas

### 🔹 **Lenguaje y Frameworks**
- **Kotlin 2.0.0** → Código moderno y seguro.
- **Jetpack Compose** → UI declarativa para Android.
- **Coroutines** → Manejo de concurrencia eficiente.
- **Hilt** → Inyección de dependencias simplificada.
- **Ktor** → Cliente HTTP liviano y eficiente para llamadas API.
- **Google Maps** → Integración con mapas y geolocalización.

## 🚀 Aprendizajes y Alternativas
- 💡 Este proyecto representa mi primera experiencia con Jetpack Compose y Ktor. Aunque nunca había trabajado con estas tecnologías, logré implementarlas de manera efectiva siguiendo buenas prácticas y principios de arquitectura moderna.
- 🔄 Además, existe una rama alternativa donde el cliente de red se implementa con Retrofit en lugar de Ktor. Este cambio debe hacerse con cuidado, asegurando que las dependencias y el flujo de datos se mantengan consistentes.
- 🔑 Esta aplicación no incluye una API Key de Google Maps por defecto. Para habilitar la funcionalidad de mapas, solo necesita agregar su propia clave en el archivo strings.xml, en el campo reservado para este propósito.

### 🔹 **Arquitectura**
- **MVVM (Model-View-ViewModel)** → Separación clara de responsabilidades.
- **Clean Architecture** → Código modular y mantenible.

### 🔹 **Otras Librerías**
- **Coil** → Carga optimizada de imágenes.
- **Mockito** → Pruebas unitarias con mockeo de datos.
- **Compose Navigation** → Manejo de rutas y pantallas.
- **JUnit & Espresso** → Pruebas automatizadas.

---

## 📂 Estructura del Proyecto

```
📦 recipeapp
 ┣ 📂 data             # Capa de datos (API, modelos, repositorios)
 ┣ 📂 di               # Capa de inyección (modulos)
 ┣ 📂 domain           # Lógica de negocio y casos de uso
 ┣ 📂 presentation     # la capa de manejo de vista y viewmodel
 ┣ 📂 ui               # UI con Jetpack Compose
 ┃ ┣ 📂 home           # Pantalla principal con lista de recetas
 ┃ ┣ 📂 detail         # Pantalla de detalles de la receta
 ┃ ┣ 📂 map            # Pantalla con Google Maps
 ┃ ┗ 📂 components     # Componentes reutilizables (AppBar, Tabs, etc.)
 ┣ 📜 build.gradle.kts # Configuración del proyecto
 ┗ 📜 README.md        # Este archivo 📄
```

---

## 🎨 **Decisiones de Diseño y Arquitectura**

### **1️⃣ Uso de MVVM + Clean Architecture**
🔹 Separamos la lógica de negocio de la UI para facilitar el mantenimiento y escalabilidad.
🔹 Los datos fluyen de `data` → `domain` → `presentation`, asegurando desacoplamiento.

### **2️⃣ Uso de Jetpack Compose**
✅ Diseño declarativo más intuitivo y menos propenso a errores.
✅ Manejo eficiente del estado con `remember` y `StateFlow`.
✅ Integración nativa con Material3 para un diseño moderno.

### **3️⃣ Uso de Ktor en lugar de Retrofit**
✅ Cliente HTTP más ligero y con mejor soporte para **coroutines**.
✅ Menos boilerplate comparado con Retrofit.
✅ Flexibilidad para cambios en el backend sin modificar demasiado el código.

### **4️⃣ Implementación de Google Maps con `MapView` en Compose**
✅ Uso de `GoogleMap` y `rememberCameraPositionState`.
✅ Manejo de `Marker` para ubicar la receta en el mapa.

---

## 🛠️ **Configuración y Ejecución**

### **📌 Requisitos**
- Android Studio **Giraffe | 2022.3.1+**
- Kotlin **2.0.0**
- SDK mínimo **24**

### **📌 Instalación**
```bash
git clone https://github.com/yeyo735/recipe-android.git
cd RecipeApp
gradlew build
```

### **📌 Ejecución**
Desde Android Studio, seleccionar un emulador o dispositivo físico y ejecutar:
```bash
Run > Run 'app'
```

---
## 🧪 Pruebas Unitarias y Automatizadas
📌 Este proyecto incluye pruebas unitarias y de UI para validar la funcionalidad y navegación de la aplicación.

### ✅ Pruebas Unitarias

ViewModel: Verificamos que RecipeViewModel carga correctamente las recetas.
Repositorios: Probamos la integración de RecipeRepositoryImpl con datos simulados.
UseCases: Testeamos la lógica de negocio en casos de uso específicos.

### ✅ Pruebas de UI con Compose Testing

Validamos que RecipeHomeScreen muestra correctamente la lista de recetas.
Probamos la navegación entre RecipeHomeScreen y RecipeDetailScreen.
Verificamos que los tabs en RecipeDetailTabScreen funcionan correctamente.

### ✅ Pruebas End-to-End (E2E) con Espresso y Compose UI Test

Simulamos una interacción completa desde la lista hasta los detalles de la receta.
Probamos la integración de Google Maps y el marcador de ubicación.
🔧 Configuración de Tests con Hilt
📌 Para ejecutar pruebas instrumentadas con Hilt, es necesario configurar correctamente el AndroidManifest.xml de androidTest.

### 1️⃣ Crear o modificar el archivo src/androidTest/AndroidManifest.xml

``` bash xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:name="dagger.hilt.android.testing.HiltTestApplication" />
</manifest>
```

### 2️⃣ Asegurar que RecipeApplication extiende HiltAndroidApp

``` kotlin
@HiltAndroidApp
class RecipeApplication : Application()
```

### 3️⃣ Agregar el testInstrumentationRunner en build.gradle.kts

``` kotlin
android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["android.app.testing"] = "true"
    }
}
```

### 4️⃣ Agregar las dependencias necesarias para pruebas con Hilt

``` kotlin
androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")
```
### 5️⃣ Asegurar que los tests usan HiltAndroidRule

``` kotlin
@HiltAndroidTest
class RecipeNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToDetailScreen() {
        onView(withText("Recipe App")).check(matches(isDisplayed()))
        onView(withText("Pasta")).perform(click()) // Simula clic en receta
        onView(withText("Description:")).check(matches(isDisplayed()))
    }
}
```

### 📌 Comandos para Ejecutar las Pruebas

- Ejecutar pruebas unitarias:

```
./gradlew test
```

- Ejecutar pruebas UI e instrumentadas:

```
./gradlew connectedAndroidTest
```

---

## 👨‍💻 **Autor**
- **Sergio Miranda** - Desarrollador Android Senior 📱

Si tienes alguna pregunta o sugerencia, ¡abre un issue o PR! 🚀

