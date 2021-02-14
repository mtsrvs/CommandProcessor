# CommandProcessor
Command Processor - Mulesoft

## Comandos
- cd [dirName|path]
- mkdir [dirName]
- touch [fileName]
- ls [-r] [path]
- quit

## Guardar estado
Al ingresar el comando `quit`, se guarda todo lo realizado hasta el momento en el archivo `state.save` ubicado en la raiz del proyecto.

## Cargar estado
Ejecutar el proyecto con el argumento `-loadstate`. Esto cargará el archivo `state.save`. En caso de no existir el archivo, se iniciará con un estado nuevo.

## Herramientas utilizadas
- Java 8
- Junit 5
- Maven 3.6.3

## Sobre
Proyecto realizado por Leandro Matias Rivas
