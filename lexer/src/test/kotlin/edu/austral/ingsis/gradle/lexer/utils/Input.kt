package edu.austral.ingsis.gradle.lexer.utils

val input_001 = """let a : string = 'hola'"""

val input_002_doubleQuote = """let a : string = "00000;;;;;;;;let 31313131312""""

val input_002_singleQuote = """let a : string = '00000;;;;;;;;let 31313131312'"""

val input_003 = """let n : number = 5; """

val input_004 = """ let n : number = 19; """

val input_005 = """ let n : number = (19 +5) ; """

val input_006 = """ let n : string = "hola"; """

val input_007 = """let n : number = 19;"""

val input_008 = "let a : number = 5; \n println(a)"

val input_009 = """let variable : string = + 5 "let" + 1 + "println" + "aaalet1"; """

val input_010 = "let name : string = \"Joe\";\n let lastName : string = \"Doe\" ;\n println(name + \" \" + lastName);"

val input_011 = "let a: number = 12;\n" + "let b: number = 4;\n" + "a = a / b;"