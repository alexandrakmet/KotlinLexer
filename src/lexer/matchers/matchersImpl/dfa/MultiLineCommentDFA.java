package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class MultiLineCommentDFA implements IMatcher {
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
                    if (curChar == '/') {
                        state = 1;
                    } else return null;
                    continue;
                case 1:
                    if (curChar == '*') {
                        state = 2;
                    } else return null;
                    continue;
                case 2:
                    if (curChar == '*') {
                        state = 3;
                    }
                    continue;
                case 3:
                    if (curChar == '/') {
                        return new Token(TokenType.MULTILINE_COMMENT, text.substring(position, curPosition), position, curPosition);
                    } else {
                        state = 2;
                    }
            }
        }
        return null;
    }
}
