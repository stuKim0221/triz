package com.example.triz;

/**
 * 앱 전체에서 사용되는 상수들을 정의하는 클래스
 */
public class Constants {

    // TRIZ 데이터 관련 상수
    public static final int TOTAL_PARAMETERS = 39;
    public static final int TOTAL_PRINCIPLES = 40;

    // UI 관련 상수
    public static final int SPLASH_SCREEN_DELAY_MS = 2000;

    // SharedPreferences 키
    public static final String PREF_MEMOS = "memos";

    // Intent 키
    public static final String EXTRA_MEMO_ID = "memo_id";

    // 기본값
    public static final int INVALID_PARAMETER_ID = -1;
    public static final String EMPTY_STRING = "";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }
}
