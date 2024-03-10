package lexer.util

import common.token.Token
import common.token.TokenType
import common.token.calculatePosition

fun createToken(matchResult: MatchResult, code: String, tokenType:TokenType): Token {
    val value = matchResult.value
    val startIndex = matchResult.range.first
    val endIndex = matchResult.range.last + 1
    val startPosition = calculatePosition(code, startIndex)
    val endPosition = calculatePosition(code, endIndex)
    return Token(value, tokenType, startPosition, endPosition)
}

fun isInQuotes(matchResult: MatchResult, code:String):Boolean{
    //TODO Should contemplate the case of \n.
    val indexes=Pair( matchResult.range.first, matchResult.range.last+1)
    var index=indexes.first;
    while(indexes.first >=0){
        if (code[index]== '"' || code[index]=='\''){
            var secondIndex=indexes.second;
            while (secondIndex <= code.length){
                if (code[index]== '"' || code[index]=='\''){
                    return true;
                }
                secondIndex++;
            }
        }
        if (index==0) return false;
        index--;
    }
    return false;
}