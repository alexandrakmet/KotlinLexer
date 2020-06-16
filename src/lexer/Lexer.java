package lexer;

import lexer.matchers.IMatcher;
import lexer.matchers.MatchersUtils;
import lexer.token.Token;
import lexer.token.TokenType;

import java.util.ArrayList;

class Lexer {

    static ArrayList<Token> tokenize(String text) {
        ArrayList<Token> tokens = new ArrayList<>();
        int position = 0;
        Token error = null;
        Token toAdd;

        while (position < text.length()) {
            toAdd = null;

            for (IMatcher matcher : MatchersUtils.MATCHERS) {
                Token temp = matcher.match(text, position);
                if (toAdd == null || (temp != null && temp.getEnd() > toAdd.getEnd())) {
                    toAdd = temp;
                }
            }

            if (toAdd != null) {
                if (error != null) {
                    tokens.add(error);
                    error = null;
                }
                tokens.add(toAdd);
                position = toAdd.getEnd();
                continue;
            }
            if (error == null) {
                error = new Token(TokenType.ERROR, Character.toString(text.charAt(position)), position, position + 1);
            } else {
                error.appendValue(text.charAt(position));
            }
            position++;

        }
        return tokens;
    }
}
