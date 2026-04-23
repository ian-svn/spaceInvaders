### Ejecutar y compilar desde el código fuente (Eclipse IDE)

Si eres desarrollador y deseas ver el código o modificar el juego utilizando **Eclipse**, sigue estos pasos:

1. Descarga el código fuente o clona el repositorio en tu computadora.
2. Abre **Eclipse IDE** y selecciona tu *Workspace* de preferencia.
3. Ve a la barra de menú superior y selecciona `File` > `Import...`.
4. En la ventana que se abre, despliega la carpeta `General`, selecciona **`Existing Projects into Workspace`** y haz clic en `Next`.
5. En la opción `Select root directory`, pulsa `Browse...` para buscar y seleccionar la carpeta donde descargaste el juego. Asegúrate de que el proyecto aparezca marcado con una tilde en la lista de abajo y dale a `Finish`.
6. En el panel lateral (*Package Explorer* o *Project Explorer*), despliega las carpetas del proyecto hasta encontrar el archivo principal (el que contiene el método de arranque o `main` el cual esta en `juego` > `Juego`).
7. Haz clic derecho sobre ese archivo, selecciona **`Run As`** > **`Java Application`** (o `Local C/C++ Application`, dependiendo del lenguaje en el que tengan configurado el entorno) para compilar e iniciar el juego.

link del manual de usuario: https://docs.google.com/document/d/1YiwUIw-P0S8g2E1FK6TJ1EKqSzR1N8YzLgKJDr8ZvW0/edit?usp=sharing

# 🌌 Star Invaders

**¡Conviértete en Han Solo y defiende Tatooine a bordo del Halcón Milenario!**

En este videojuego arcade basado en la saga *Star Wars*, tu misión es enfrentarte a oleadas de naves enemigas y a la temible Estrella de la Muerte. El Imperio y los Sith planean destruir el planeta Tatooine, el refugio de varios rebeldes. ¡Tu deber es acabar con ellos y poner fin a la Primera Orden!

---

## 🕹️ Controles

Dirige el Halcón Milenario y destruye a las naves invasoras con los siguientes comandos:

* **[ A ]** - Moverse hacia la izquierda
* **[ D ]** - Moverse hacia la derecha
* **[ W ]** o **[ ESPACIO ]** - Disparar
* **[ R ]** - Volver a intentar (Reiniciar partida)
* **[ ESC ]** - Rendirse (Salir del juego)

---

## 🚀 Personajes y Mecánicas

### 🟢 El Jugador: Halcón Milenario
Nuestra nave favorita. Cuenta con un **máximo de 5 escudos**. Cada ataque recibido restará tus defensas; si los escudos llegan a 0, la nave será destruida.

### 🔴 Enemigos del Imperio
Las naves enemigas aparecerán moviéndose de izquierda a derecha y se acercarán progresivamente al tocar los límites del campo de batalla.

* **Caza TIE:** Su único método de ataque es el choque frontal. Se destruirá al impactar contigo, pero dañará tus escudos.
* **TIE Striker:** Además de poder chocar contra ti, dispara proyectiles de forma constante que irán drenando tus escudos progresivamente.
* **Caza TIE de los Sith:** Tiene la capacidad de realizar una embestida letal. De un momento a otro, se lanzará a toda velocidad contra tu nave para acabarte.

### 🌑 Jefe Final: La Estrella de la Muerte
El último impedimento para ganar la guerra. Esta estación espacial gigante se mueve lateralmente y presenta dos peligros principales:
1.  **Lanzadores de proyectiles:** Cuenta con 4 cañones que disparan con cadencia aleatoria.
2.  **Rayo destructor de planetas:** Un ataque devastador que requiere tiempo de carga. El mínimo contacto con este rayo te pulverizará al instante, ignorando tus escudos.

---

## 🎁 Habilidades (Perks)

Al derribar **15 naves enemigas**, caerán restos tecnológicos que puedes recoger (o esquivar) para alterar tu nave:

* 💚 **Perk de Reparación:** Recupera o añade 1 escudo a tu nave.
* 🔫 **Perk de Doble Disparo:** Mejora tus armas para disparar 2 láseres a la vez durante el resto de la batalla.
* ⚠️ **Perk de Bomba:** ¡Cuidado con este! Si lo recoges, penalizará a tu nave restándole 2 escudos.

---

## 🎨 Ambiente y Diseño

El juego está diseñado y ambientado totalmente en la galaxia de *Star Wars*. Todos los *sprites*, menús y mecánicas fueron creados con el objetivo de plasmar fielmente la esencia de la saga en un formato arcade clásico.

---

## 👥 Créditos del Equipo

Este proyecto fue desarrollado por:

* **Ian Vila** - Programación principal, líder del proyecto y redacción del manual.
* **Joaquin Sulca** - Diseño de sprites y redacción del manual.
* **Giuliano Santangelo** - Diseño de sprites, creación del menú y redacción del manual.

> *"Esperamos que les guste el juego 😄. ¡Que la Fuerza te acompañe!"*
