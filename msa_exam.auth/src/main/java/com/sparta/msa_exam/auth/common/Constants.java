package com.sparta.msa_exam.auth.common;

import java.util.List;

public class Constants {
    public static final List<String> NO_NEED_AUTH_URLS = List.of(
            "/auth/signIn",
            "/auth/signUp"
    );
}
