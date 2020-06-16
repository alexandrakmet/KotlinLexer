package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class OperatorDFA implements IMatcher {

    @Override
    public Token match(String text, int position) {
        char curChar;
        int curPosition = position;
        int state = 0;
        Token result = null;
        while (curPosition < text.length()) {
            curChar = text.charAt(curPosition);
            curPosition++;
            switch (state) {
                case 0:
                    if (curChar == '&') {
                        state = 1;
                    } else if (curChar == '|') {
                        state = 2;
                    } else if ("<>*/%".indexOf(curChar) != -1) {
                        state = 3;
                        result = new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '!' || curChar == '=') {
                        state = 4;
                        result = new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '+') {
                        state = 5;
                        result = new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '-') {
                        state = 6;
                        result = new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return null;
                    }
                    continue;
                case 1:
                    if (curChar == '&') {
                        return new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return null;
                    }
                case 2:
                    if (curChar == '|') {
                        return new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return null;
                    }
                case 3:
                    if (curChar == '=') {
                        return new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return result;
                    }
                case 4:
                    if (curChar == '=') {
                        state = 3;
                        result = new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                        continue;
                    } else {
                        return result;
                    }
                case 5:
                    if (curChar == '+' || curChar == '=') {
                        return new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return result;
                    }
                case 6:
                    if (curChar == '-' || curChar == '=') {
                        return new Token(TokenType.OPERATOR, text.substring(position, curPosition), position, curPosition);
                    } else {
                        return result;
                    }
                default:
                    return result;
            }
        }
        return result;
    }
}
