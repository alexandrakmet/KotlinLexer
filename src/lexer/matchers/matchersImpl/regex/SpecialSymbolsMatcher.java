package lexer.matchers.matchersImpl.regex;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class SpecialSymbolsMatcher implements IMatcher {
    private static final int MAX_LENGTH = 2;

    @Override
    public Token match(String text, int position) {
        Token result = null;
        for (int i = 1; i <= MAX_LENGTH && position + i <= text.length(); i++) {
            String substring = text.substring(position, position + i);
            if (substring.matches("(\\?[.:]?)|\\.\\.|(:){1,2}|->|!!|\\$|@"))
                result = new Token(TokenType.SPECIAL_SYMBOL, substring, position, position + i);
        }
        return result;
    }
}
