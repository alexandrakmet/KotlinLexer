package lexer.matchers.matchersImpl.dfa;

import lexer.matchers.IMatcher;
import lexer.matchers.MatchersUtils;
import lexer.token.Token;
import lexer.token.TokenType;

public class IdentifierKeywordDFA implements IMatcher {
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
                    if (curChar == '_' || Character.isLetter(curChar)) {
                        state = 1;
                    } else return null;
                    continue;
                case 1:
                    if (!(Character.isLetter(curChar) || Character.isDigit(curChar) || curChar == '_' || curChar == '$')) {
                        return new Token(getType(text.substring(position, curPosition - 1)), text.substring(position, curPosition - 1), position, curPosition - 1);
                    }
            }
        }
        if (state == 1)
            result = new Token(getType(text.substring(position, curPosition)), text.substring(position, curPosition), position, curPosition);
        return result;
    }

    private TokenType getType(String text) {
        if (MatchersUtils.HARD_KEYWORDS.contains(text)) return TokenType.HARD_KEYWORD;
        if (MatchersUtils.SOFT_KEYWORDS.contains(text)) return TokenType.SOFT_KEYWORD;
        if (MatchersUtils.MODIFIER_KEYWORDS.contains(text)) return TokenType.MODIFIER_KEYWORD;
        return TokenType.IDENTIFIER;
    }
}
