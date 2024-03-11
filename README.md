# Desarrollo

En este trabajo práctico, se espera que diseñen e implementen tres herramientas fundamentales para un nuevo lenguaje de programación llamado "PrintScript". Este lenguaje tiene como objetivo principal facilitar la impresión y el formateo de texto y números.

Las herramientas que se deben desarrollar son:

1. **Interpreter**: un intérprete para este lenguaje que pueda ejecutar programas escritos en este lenguaje. El intérprete debe ser capaz de leer el código fuente de un programa, analizarlo y ejecutarlo.

2. **Formatter**: una herramienta de formateo para este lenguaje que pueda tomar como entrada un programa escrito en este lenguaje y formatearlo de acuerdo a un estilo específico determinado por una serie de criterios. El formateador debe ser capaz de aplicar indentación, espacios en blanco, saltos de línea, etc. de manera consistente en todo el programa.

3. **Static code analyzer**: un analizador estático de código para el lenguaje "PrintScript" que pueda detectar errores y posibles problemas en el código fuente. El analizador debe ser capaz de identificar errores de sintaxis, uso incorrecto de variables, funciones o estructuras de control, entre otros.

La intención es lograr que estas herramientas sirvan como base de un ecosistema alrededor de dicho lenguaje en los próximos años. Esto quiere decir que no solo debe poder ejecutar el código sino proveer una interfaz para analizar sintácticamente y de esta manera poder luego operar sobre el "Abstract syntax tree" (AST) resultante. Este proceso se conoce como "Parsing" y consiste en analizar la estructura del programa.

Para poder desarrollar el analizador sintáctico se debe contar previamente con un analizador léxico o "Lexer". El análisis léxico se refiere a la identificación de los elementos léxicos del lenguaje, como palabras clave, identificadores, operadores, etc.

Al realizar los distintos componentes del sistema se debe tener en cuenta que el lenguaje deberá ser lo suficientemente flexible para poder incorporar dicha funcionalidad con el menor esfuerzo posible.

También se debe tener en cuenta que los códigos fuente pueden ser tan extensos que no es posible contenerlos de forma completa en memoria. Por lo tanto, los distintos componentes del sistema deben soportar mecanismos que permitan manejar este tipo de fuentes, entendiendo que la información entre los mismos va fluyendo. Esto quiere decir que no se puede primero hacer todo el análisis léxico para luego pasar al análisis semántico ya que el primero consumiría toda la memoria disponible.
