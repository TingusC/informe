# Informe

**Repositorio GitHub:** https://github.com/TingusC/informe

### Creado por: Carlos Hermoso Delgado

## Enunciado: 

> Desarrollar un programa que muestre los recursos del sistema. Comandos en Linux:

- ps
- df
- free

> El informe será guardado en un fichero con formato markdown. En el futuro se plantea la posibilidad de guardarlo en xml, html...

## Análisis:

Se pide crear un programa que ejecute los procesos `ps`, `df` y `free` recogiendo su resultado y imprimirlo en un documento `.md`. El programa debe ser lo suficientemente generico para poder usarse para generar otro tipo de archivo en el cso deseado.

## Explicación:

La aplicacion (*Main*) implementa un bucle *for* que itera a lo largo de una constante con los tres comandos. por cada uno de ellos crea un nuevo objeto de tipo *LanzadorDocumentos* y guarda su salida en una `List<>`. Estos datos se envian posteriormente al metodo `formatear()` que le dara el formato de salida al *String* que recibira el *EscritorDocumentos*.

### Interfaces:

Se ahn creado dos interfaces para este ejercicio:

**Escritor:**

Tiene un solo metodo `escribir()` que recibe como parametro el texto que se quiere escribir en foma de String.

**Lanzador:**

También utiliza solamente un metodo, `ejecutar()` que devuelve en forma de String la salida de lo ejecutado.

### Clases:

Esta prorama cuenta con dos clases que implementan las dos interfaces, cada una implementa una:

**EscritorDocumentos:**

Implementa **Escritor**. Para utilizarse se debe crear un objeto donde por el constructor recibe la ruta del documento donde se va a escribir en forma de *String*.

El metodo `escribir()` utiliza la clase *File* creando un `new File` con la ruta del objeto y luego mediante un `BufferedWriter` escribe el texto que recibe el metodo en el documento.

**LanzadorProcesos:**

Implementa **Lanzador**. Pra usarse, igual que la anterior, creamos un objeto que reciba por constructor el comando que queremos lanzar, los parametros sobre los que queremos aplicar ese comando y los datos que queramos mandarle. 

El metodo `ejecutar()` crea un comando final en forma de *String[]* con el comando recibido por constructor. Despues comprueba si se han enviado parametros y si es asi have que el array creado cuente ahora con los dos datos, comando y parametro, posteriormente utiliza *Runtime* para lanzar el proceso. Si en los datos se recibio informacion el programa los manda al proceso para que este trabaje con ellos. Finalmente el metodo captura la salida del proceso usando *StringBuilder* y cuando toda la salida haya sido recogida, la devuelve transformada en *String*.

## Notas:

- `NOTA 1:` En las clases se ha utilizado el *@Data* y *@AllArgsConstructor* de lombock.

- `NOTA 2:` La funcion *ejecutar()* en *LanzadorProcesos* me dio warning al enviarle el comando unico sin los parametros porque recibia un *String* y no un array y daba error si enviaba un parametro en `null` o inicializado a `""`. Posteriormente se agrego una forma de guardar en array solamente el comando o el el comando y el parametro si este es diferente de null.