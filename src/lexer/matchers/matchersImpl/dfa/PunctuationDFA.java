package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class PunctuationDFA implements IMatcher {


    @Override
    public Token match(String text, int position) {
        char curChar;
        curChar = text.charAt(position);

        if ("{}[]()".indexOf(curChar) != -1) {
            return new Token(TokenType.BRACKET, Character.toString(curChar), position, position + 1);
        }
        if (",;".indexOf(curChar) != -1) {
            return new Token(TokenType.SEPARATOR, Character.toString(curChar), position, position + 1);
        }
        if (curChar == '.') {
            return new Token(TokenType.DOT, Character.toString(curChar), position, position + 1);
        }
        return null;
    }
}
