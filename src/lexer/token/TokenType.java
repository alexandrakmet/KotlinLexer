package lexer.token;

public enum TokenType {
    INT,
    FLOAT,
    DOUBLE,
    LONG,
    HEX,
    BIN,
    IDENTIFIER,
    HARD_KEYWORD,
    SOFT_KEYWORD,
    MODIFIER_KEYWORD,
    OPERATOR,
    SEPARATOR,
    DOT,
    BRACKET,
    CHAR,
    STRING,
    COMMENT,
    MULTILINE_COMMENT,
    WHITESPACE,
    SPECIAL_SYMBOL,
    ERROR
}
