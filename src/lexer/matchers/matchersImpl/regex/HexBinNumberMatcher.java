package lexer.matchers.matchersImpl.regex;

import lexer.matchers.IMatcher;
import lexer.token.Token;
import lexer.token.TokenType;

public class HexBinNumberMatcher implements IMatcher {

    private static final int MIN_LENGTH = 3;

    @Override
    public Token match(String text, int position) {
        int curPos = position;
        int resPosition = position;
        TokenType type;

        if (text.length() - position < MIN_LENGTH) return null;
        if (text.substring(position, position + MIN_LENGTH).matches("[-+]?0[xX].?")) type = TokenType.HEX;
        else if (text.substring(position, position + MIN_LENGTH).matches("[-+]?0[bB].?")) type = TokenType.BIN;
        else return null;

        while (curPos < text.length()) {
            curPos++;
            if (type == TokenType.HEX && text.substring(position, curPos).matches("[-+]?0[xX][0-9a-fA-F]+([eE]([-+]?[0-9]+)?)?")) {
                resPosition = curPos;
            }
            if (type == TokenType.BIN &&text.substring(position, curPos).matches("[-+]?0[bB][01]+([eE]([-+]?[0-9]+)?)?")) {
                resPosition = curPos;
            }
        }

        return resPosition != position ? new Token(type, text.substring(position, resPosition), position, resPosition) : null;
    }
}
