package ru.zankov.api.data;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AuthorizationData {

    public static Stream<Arguments> correctLogin() {
        return Stream.of(
                arguments("a@a"),
                arguments("X@X"),
                arguments("1@a"),
                arguments("a@1"),
                arguments("-@a"),
                arguments("a@-"),
                arguments("_@a"),
                arguments("a@_"),
                arguments("a.a@a"),
                arguments("a@a.a"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        );
    }

    public static Stream<Arguments> correctPassword() {
        return Stream.of(
                arguments("test@t"),
                arguments("123456"),
                arguments("кириллица"),
                arguments("àáâãäåçèéêëìíîðñòôõöö"),
                arguments("\"[|]'~<!--@/*$%^&#*/()?>,.*/\\"),
                arguments("♣ ☺ ♂ ♪"),
                arguments("測試象形文字")
        );
    }

    public static Stream<Arguments> incorrectLogin() {
        return Stream.of(
                arguments("a"),
                arguments("a@"),
                arguments("@a"),
                arguments("a.@a"),
                arguments(".a@a"),
                arguments("a@a."),
                arguments("a@.a"),
                arguments("a a@a"),
                arguments("a@a a"),
                arguments("@a"),
                arguments("a@"),
                arguments("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        );
    }
}
