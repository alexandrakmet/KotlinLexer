package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class StringDFA implements IMatcher {

    @Override
    public Token match(String text, int position) {
        char curChar;
        int curPosition = position;
        int state = 0;
        while (curPosition < text.length()) {
            curChar = text.charAt(curPosition);
            curPosition++;
            switch (state) {
                case 0:
                    if (curChar == '\"') {
                        state = 1;
                        continue;
                    } else if (curChar == '\'') {
                        state = 2;
                        continue;
                    } else return null;
                case 1:
                    if (curChar == '\"') {
                        return new Token(TokenType.STRING, text.substring(position + 1, curPosition - 1), position, curPosition);
                    }
                    continue;
                case 2:
                    if (curChar != '\'') {
                        state = 3;
                        continue;
                    } else return null;
                case 3:
                    if (curChar == '\'') {
                        return new Token(TokenType.CHAR, text.substring(position + 1, curPosition - 1), position, curPosition);
                    } else return null;
            }
        }
        return state == 2 ? new Token(TokenType.COMMENT, text.substring(position, curPosition), position, curPosition) : null;
    }
}
