package lexer.matchers;

import lexer.token.Token;

public interface IMatcher {

    Token match(String text, int position);

}
