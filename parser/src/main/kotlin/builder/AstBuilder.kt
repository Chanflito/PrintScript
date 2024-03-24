package builder

import ast.ASTNode
import common.token.Token

interface AstBuilder <T: ASTNode > {
    fun build(tokens: List<Token>, index :Int) : Pair<T,Int>
}