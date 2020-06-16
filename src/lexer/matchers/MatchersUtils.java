package lexer.matchers;

import lexer.matchers.matchersImpl.dfa.*;
import lexer.matchers.matchersImpl.regex.HexBinNumberMatcher;
import lexer.matchers.matchersImpl.regex.SpecialSymbolsMatcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatchersUtils {
    public static final List<IMatcher> MATCHERS = List.of(
            new NumberDFA(),
            new HexBinNumberMatcher(),
            new OperatorDFA(),
            new SpecialSymbolsMatcher(),
            new PunctuationDFA(),
            new IdentifierKeywordDFA(),
            new WhitespaceDFA(),
            new StringDFA(),
            new SingleLineCommentDFA(),
            new MultiLineCommentDFA()
    );

    public static final Set<String> HARD_KEYWORDS = new HashSet<>(Arrays.asList(
            "as", "break", "class", "continue", "do", "else", "false", "for", "fun", "if", "in", "interface", "is",
            "null", "object", "package", "return", "super", "this", "throw", "true", "try", "typealias", "typeof",
            "val", "var", "when", "while"
    ));
    public static final Set<String> SOFT_KEYWORDS = new HashSet<>(Arrays.asList(
            "by", "catch", "constructor", "delegate", "dynamic", "field", "file", "finally", "get", "import", "init",
            "it", "param", "property", "receiver", "set", "setparam", "where"
    ));
    public static final Set<String> MODIFIER_KEYWORDS = new HashSet<>(Arrays.asList(
            "actual", "abstract", "annotation", "companion", "const", "crossinline", "data", "enum", "expect",
            "external", "final", "infix", "inline", "inner", "internal", "lateinit", "noinline", "open", "operator",
            "out", "override", "private", "protected", "public", "reified", "sealed", "suspend", "tailrec", "vararg"
    ));
}
