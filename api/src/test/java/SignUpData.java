import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SignUpData {

    public static Stream<Arguments> positive() {
        return Stream.of(
                arguments("a@a", "Qwerty1"),
                arguments("X@X", "Qwerty1"),
                arguments("1@a", "Qwerty1"),
                arguments("a@1", "Qwerty1"),
                arguments("-@a", "Qwerty1"),
                arguments("a@-", "Qwerty1"),
                arguments("_@a", "Qwerty1"),
                arguments("a@_", "Qwerty1"),
                arguments("a.a@a", "Qwerty1"),
                arguments("a@a.a", "Qwerty1"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Qwerty1"),

                arguments("test@t", "test@t"),
                arguments("a@a1", "123456"),
                arguments("a@a2", "кириллица"),
                arguments("a@a3", "àáâãäåçèéêëìíîðñòôõöö"),
                arguments("a@a4", "\"[|]'~<!--@/*$%^&#*/()?>,.*/\\"),
                arguments("a@a5", "♣ ☺ ♂ ♪"),
                arguments("a@a6", "測試象形文字")
        );
    }

    public static Stream<Arguments> incorrectLogin() {
        return Stream.of(
                arguments("a", "Qwerty1"),
                arguments("a@", "Qwerty1"),
                arguments("@a", "Qwerty1"),
                arguments("a.@a", "Qwerty1"),
                arguments(".a@a", "Qwerty1"),
                arguments("a@a.", "Qwerty1"),
                arguments("a@.a", "Qwerty1"),
                arguments("a a@a", "Qwerty1"),
                arguments("a@a a", "Qwerty1"),
                arguments("@a", "Qwerty1"),
                arguments("a@", "Qwerty1"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Qwerty1")
        );
    }
}
