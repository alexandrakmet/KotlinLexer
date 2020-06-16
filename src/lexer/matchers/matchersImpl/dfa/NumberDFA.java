package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class NumberDFA implements IMatcher {

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
                    if (curChar == '+' || curChar == '-') {
                        state = 1;
                    } else if (curChar == '0') {
                        state = 2;
                        result = new Token(TokenType.INT, text.substring(position, curPosition), position, curPosition);
                    } else if (Character.isDigit(curChar)) {
                        state = 4;
                        result = new Token(TokenType.INT, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '.') {
                        state = 6;
                    } else return null;
                    continue;
                case 1:
                    if (curChar == '0') {
                        state = 2;
                        result = new Token(TokenType.INT, text.substring(position, curPosition), position, curPosition);
                    } else if (Character.isDigit(curChar)) {
                        state = 4;
                        result = new Token(TokenType.INT, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '.') {
                        state = 6;
                    } else return null;
                    continue;
                case 2:
                    if (Character.isDigit(curChar) || curChar == '_') {
                        state = 3;
                    } else if (curChar == 'L') {
                        return new Token(TokenType.LONG, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '.') {
                        state = 6;
                    } else if (curChar == 'F' || curChar == 'f') {
                        return new Token(TokenType.FLOAT, text.substring(position, curPosition), position, curPosition);
                    } else return result;
                    continue;
                case 3:
                    if (curChar == 'F' || curChar == 'f') {
                        return new Token(TokenType.FLOAT, text.substring(position, curPosition), position, curPosition);
                    } else if (Character.isDigit(curChar) || curChar == '_') {
                        continue;
                    } else return result;
                case 4:
                    if (Character.isDigit(curChar) || curChar == '_') {
                        result = new Token(TokenType.INT, text.substring(position, curPosition), position, curPosition);
                        continue;
                    } else if (curChar == 'L') {
                        return new Token(TokenType.LONG, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == 'F' || curChar == 'f') {
                        return new Token(TokenType.FLOAT, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == '.') {
                        state = 6;
                        continue;
                    } else return result;
                case 6:
                    if (Character.isDigit(curChar)) {
                        result = new Token(TokenType.DOUBLE, text.substring(position, curPosition), position, curPosition);
                        state = 7;
                        continue;
                    } else return result;
                case 7:
                    if (Character.isDigit(curChar) || curChar == '_') {
                        result = new Token(TokenType.DOUBLE, text.substring(position, curPosition), position, curPosition);
                    } else if (curChar == 'F' || curChar == 'f') {
                        return new Token(TokenType.FLOAT, text.substring(position, curPosition), position, curPosition);
                    } else return result;
            }
        }
        return result;
    }

}
