package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class WhitespaceDFA implements IMatcher {
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
                    if (" \t\n\r".indexOf(curChar) != -1) {
                        state = 1;
                    } else return null;
                    continue;
                case 1:
                    if (" \t\n\r".indexOf(curChar) == -1) {
                        return new Token(TokenType.WHITESPACE, text.substring(position, curPosition - 1), position, curPosition - 1);
                    }
            }
        }
        if (state == 1)
            result = new Token(TokenType.WHITESPACE, text.substring(position, curPosition), position, curPosition);
        return result;
    }
}
