# LABORATORIO 3 (ARSW) 📚

## INTEGRANTES:📋
* Johan Stiven Guerrero Pineda
* Jonathan Fabian Paez Torres

## Instrucciones 📌
1. Ingrese a la ruta del proyecto, donde se encuentra el archivo POM.xml
2. Ejecute `mvn package` en la consola cmd
3. Para ejecutar la parte 1 del laboratorio **productores/consumidores ejecute** `mvn exec:java -Dexec.mainClass=edu.eci.arst.concprg.prodcons.StartProduction`
4. Para ejutar la parte 2 del laboratorio **highlander-simulator** ejecute `mvn exec:java -Dexec.mainClass=edu.eci.arst.concprg.prodcons.StartProduction`

## Parte I - Control de hilos con wait/notify. Productor/consumidor. 📌

### 1. Creación, puesta en marcha y coordinación de hilos

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte1-1.PNG)

El consumo se debe a que multiples consumidores siempre se mantienen activos, es decir el procesos de consumir sieempre esta activo.

### 2. 
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte1-2.PNG)

Al notificar y corregir la ejecucion de los procesos, se logro reducir considerablemente en consumo de CPU

### 3.

Agregamos un limite para el stock en el productor.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte1-3.PNG)

El consumo durante la ejecucion con un limite en el stock es minimo.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte1-3a.PNG)

## Parte II Buscador Listas Negras📜

Se realizo la implementacion de algunos de manera de que cada host finalice su ejecucion y notifique una vez halla 
detectado el numero de occurrencias para que se determine que este es confiable

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/Parte2-1.PNG)

## Parte II Sincronización y Dead-Locks📜

### 1.

Revise el programa “highlander-simulator”, dispuesto en el paquete edu.eci.arsw.highlandersim. Este es un juego en el que:
Se tienen N jugadores inmortales.
Cada jugador conoce a los N-1 jugador restantes.
Cada jugador, permanentemente, ataca a algún otro inmortal. El que primero ataca le resta M puntos de vida a su contrincante, y aumenta en esta misma cantidad sus propios puntos de vida.
El juego podría nunca tener un único ganador. Lo más probable es que al final sólo queden dos, peleando indefinidamente quitando y sumando puntos de vida.

### 2.

• Para que cada jugador n conozca a los jugadores n-1 restantes en la clase Immortal se define un campo final tipo List<Immortal> en la que se van agregando todos los jugadores inmortales.
• Dentro de la clase inmortal se llama el método run el cual corre el proceso asigna a la variable local health el luchador actual y este llama el método figth el cual valida que el contrincante tenga vidas activas, si es así se las resta y las asigna a health.
• Según lo anterior si tenemos para n(4)

HealthSum=(DEFAULT_IMMORTAL_HEALTH)*(#Immortals)
400=100*4
400=400

### 3.

Al pulsar el boton Pause and Check podemos que no se cumple el invariante ya que como resltuado de la sumatoria total deberia retornar 300 por sus sumatoria total.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/NoInv.png)

### 4.

Dentro de la clase Immortals se modifico el metodo **run()** para que antes de imprimir los resultados los demas hilos se pausaran.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte3-4.PNG)

Dentro de la clase ControlFrame se realizo la respectiva implementacion de los metodos PauseandCheck y Resume de la siguiente forma.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte3-4(Pause-resumen).PNG)

### 5.

Al correr de nuevo el programa con la implementacion del punto anterior se puede observar que el invariante no se cumple ya que este solo garantiza
que los hilos paren e impriman los resultados actuales, pero pueden atacar a varios hilos al mismo tiempo

Ejecucion 1
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte3-4ch(1).PNG)

Ejecucion 2
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte3-4ch.PNG)

### 6.

La region critica mas importante es figth ya que varios hilos pueden atacar a uno solo y mientras se cambia el valor de su salud otro puede estar escribiendo otra diferente
por eso se realiza un bloqueo dentro de la variable figth.

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/parte3-7.PNG)

### 8.

como el programa no se detiene durante su ejecucion, no se realiza esta implementacion.

### 9.

Verificamos que el invariante se cumpla

Para 100:
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/Para100-1.PNG)
Para 1000:
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/para1000-2.PNG)
Para 10000:
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/para1000-2.PNG)

10. 

Para este caso modificamos el metodo ChangeHealth para que se eliminen los immortales que se ya estan muertos
![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/10-1.PNG)
 
Tambien usamos la collecion CopyOnWriteArrayList en immortalsPopulation para que podamos tener concurrencia dentro de la lista

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/10-2.PNG)

11. Implemantacion del metodo Stop

![](https://github.com/Johannes26/ARSW-LAB03/blob/master/img/11.PNG)

