package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class SingleLineCommentDFA implements IMatcher {
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
                        continue;
                    } else return null;
                case 1:
                    if (curChar == '/') {
                        state = 2;
                        continue;
                    } else return null;
                case 2:
                    if (curChar == '\n') {
                        return new Token(TokenType.COMMENT, text.substring(position, curPosition), position, curPosition);
                    }
            }
        }
        return state == 2? new Token(TokenType.COMMENT, text.substring(position, curPosition), position, curPosition) : null;
    }
}
