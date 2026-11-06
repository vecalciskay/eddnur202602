# Preparacion

La estructura de la base de datos debería ser la siguiente:

```sql
CREATE TABLE `personas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `altura` float NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
Luego de que hemos creado esa tabla podemos hacer nuestra conexión a la base de datos.