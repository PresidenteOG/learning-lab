Dato:
-Una representación informática que relata objetos del mundo real
+Una información

Que son los datos y que uso:
-Es una representación simbólica sobre un objeto 
-Deben ser tratados y organizados en el almacenamiento, utilizando herramientas de almacenaje/consultas/procesarlos

-----
Historia:
-Se utilizaba anteriormente un sistema basado en archivos

----
Archivos:
-Una forma de mantener la información en un Ordenador es almacenarlo en archivos pero tiene inconvenientes

1-Incovenientes
Redundancia de datos:
-Un problema muy común es cuando un dato se vaya a modificar desde varios lugares, a la cual sale los problemas como:

 1-Errores en procesar
 2-Duplicaciones en información
 3-Copias del mismo dato entran en conflicto, generando una confusión de cual es correcto

-Aislamiento de datos
-Problemas de integridad
-Problemas de seguridad
-Accesos coherentes

---
Base de datos:
Las dificultades que surgen al trabajar con sistemas de archivos, provocaron la aparición de un nuevo sistema para gestionar grandes cantidades de información

Que es un BD?
BD es un conjunto de datos que están organizados según su estructura coherente, que son mas accesibles desde uno o mas programas y aplicaciones, de manera que cualquier dato puede ser extraídos y actualizados sin afectar su estructura de datos

---

Propiedades de un BD

-Representa algunos aspectos del mundo real o una colección de datos que representan información del mundo real
-Están organizados de una manera coherente y no 
-Diseñado para un propósito, construir y emplenar los datos con un propósito especifico
-Un dato es un campo, cualquier dato se almacena en un campo
-Una combinación de datos forman una tabla

-----

Sistemas de gestores de base de datos

-Un SGBD es una collación de programas que permiten a los usuarios crear y mantener BD, controlando todos los accesos

Propiedades de un SGBD

Abstracción de la información:
Los datos deben ser transparente al usuario

Independencia:
Debe garantizar de que un cambio en el esquema, sea físico o lógico, no implique modificaciones en las aplicaciones que lo utilizan

Consiencia:
Debe garantizar que los datos sean consistentes, que esten actualizados cuando se consulten

Seguridad:
Deben estar garantizado que la información sea consultada,actualizada,inserida o eliminado por USUARIOS AUTORIZADOS

Integridad: en caso de un problema, se debe poder recuperar la información perdida 

Recuperación: Hacer copias de seguridad de forma eficiente para evitar perdida de datos

Control de concurrencia: Se debe garantizar el acceso simultaneo al SBGD

Información sobre los datos o metadatos: El sistema debe tener la información sobre el mismo sistema

----

ACID
Atomicidad: Garantece que todas las operaciones dentro de un transmicion, se completen correctamente o no

Consistencia: Asegura que la transición de un BD esten valido a otro

Aislamiento: Asegura que las operaciones dentro de un transición no interfieran con otras transiciones

Durabilidad: Asegura que una vez una transición se ha confirmado, los cambios quedan para siempre.

-



