# Pong Game

Este es un sencillo juego de Pong desarrollado en Java utilizando la biblioteca Swing para la interfaz gráfica. El juego permite que dos jugadores controlen paletas en una ventana de 800x600 píxeles, tratando de golpear una pelota que rebota en la pantalla. ¡El primer jugador en alcanzar 5 puntos gana el juego!

## Características
- Control del jugador 1 con teclas `W` (subir) y `S` (bajar).
- Control del jugador 2 con las teclas de flecha `↑` (subir) y `↓` (bajar).
- Puntuación y detección de colisiones con las paletas y los bordes de la ventana.
- Mensaje emergente al final del juego anunciando al ganador.
- Interfaz minimalista en blanco y negro con un fondo negro, paletas y pelota blancas.

## Requisitos
- Java JDK 8 o superior.

## Instalación
1. Clona este repositorio:
    ```bash
    git clone https://github.com/tuusuario/pong-game.git
    ```
2. Navega al directorio del proyecto:
    ```bash
    cd pong-game
    ```
3. Compila el código fuente:
    ```bash
    javac App.java
    ```
4. Ejecuta el juego:
    ```bash
    java App
    ```

## Controles
- **Jugador 1**: Usa `W` para mover la paleta hacia arriba y `S` para moverla hacia abajo.
- **Jugador 2**: Usa las flechas `↑` para mover la paleta hacia arriba y `↓` para moverla hacia abajo.

## Cómo Jugar
1. Cada jugador controla una paleta con el objetivo de evitar que la pelota pase por su lado de la pantalla.
2. La pelota rebota en los bordes superior e inferior y cambia de dirección al golpear una paleta.
3. Cada vez que un jugador falla y permite que la pelota cruce su lado, el oponente recibe un punto.
4. El primer jugador en alcanzar 5 puntos gana la partida y se muestra un mensaje con el ganador.

## Estructura del Código
- **`App`**: Clase principal que extiende `JPanel` e implementa `Runnable`. Incluye la lógica del juego y la interfaz gráfica.
- **Hilos**: Se utilizan hilos separados para el movimiento de la pelota y las paletas, garantizando una experiencia de juego fluida.
- **Métodos principales**:
    - `moveBall()`: Controla el movimiento de la pelota y verifica colisiones.
    - `movePaddles()`: Controla el movimiento de las paletas basado en las teclas presionadas.
    - `checkCollisions()`: Detecta colisiones entre la pelota y las paletas, así como los bordes de la pantalla.
    - `checkWinner()`: Comprueba si algún jugador ha alcanzado la puntuación máxima.
    - `showWinner(String message)`: Muestra un mensaje indicando el ganador y reinicia la puntuación.

## Personalización
- Puedes cambiar el tamaño de las paletas, la velocidad de la pelota y las paletas, o la puntuación máxima modificando las variables al inicio de la clase `App`.
- Modifica los colores y el tamaño de la ventana en el método `paintComponent()`.

## Capturas de Pantalla
*Incluye aquí capturas de pantalla del juego si lo deseas.*

## Licencia
Este proyecto está bajo la licencia MIT. Puedes usar, modificar y distribuir el código libremente.

## Créditos
Desarrollado por [Tu Nombre].

---

¡Disfruta jugando al Pong!
