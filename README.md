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

### 🔹 **Arquitectura**
- **MVVM (Model-View-ViewModel)** → Separación clara de responsabilidades.
- **Clean Architecture** → Código modular y mantenible.

### 🔹 **Otras Librerías**
- **Coil** → Carga optimizada de imágenes.
- **MockK** → Pruebas unitarias con mockeo de datos.
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

## 🧪 **Pruebas Unitarias y Automatizadas**

### **📌 Tipos de Pruebas Implementadas**

✅ **Pruebas Unitarias**
- **ViewModel:** Verificamos que `RecipeViewModel` carga correctamente las recetas.
- **Repositorios:** Probamos la integración de `RecipeRepositoryImpl` con datos simulados.
- **UseCases:** Testeamos la lógica de negocio en casos de uso específicos.

✅ **Pruebas de UI con Compose Testing**
- Validamos que `RecipeHomeScreen` muestra correctamente la lista de recetas.
- Probamos la navegación entre `RecipeHomeScreen` y `RecipeDetailScreen`.
- Verificamos que los tabs en `RecipeDetailTabScreen` funcionan correctamente.

✅ **Pruebas End-to-End (E2E) con Espresso y Compose UI Test**
- Simulamos una interacción completa desde la lista hasta los detalles de la receta.
- Probamos la integración de `Google Maps` y el marcador de ubicación.

### **📌 Comandos para Ejecutar las Pruebas**

Ejecutar **pruebas unitarias**:
```bash
./gradlew test
```

Ejecutar **pruebas UI e instrumentadas**:
```bash
./gradlew connectedAndroidTest
```

---

## 👨‍💻 **Autores**
- **[Tu Nombre]** - Desarrollador Android Senior 📱

Si tienes alguna pregunta o sugerencia, ¡abre un issue o PR! 🚀

