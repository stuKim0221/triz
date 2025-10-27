package com.stukim.triz;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoManager {
    private static final String PREFS_NAME = "TrizMemos";
    private static final String KEY_MEMOS = "memos";

    private SharedPreferences prefs;
    private Gson gson;

    public MemoManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<UserMemo> getAllMemos() {
        String json = prefs.getString(KEY_MEMOS, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<UserMemo>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void saveMemo(UserMemo memo) {
        List<UserMemo> memos = getAllMemos();

        if (memo.getId() == null || memo.getId().isEmpty()) {
            memo.setId(UUID.randomUUID().toString());
            memo.setTimestamp(System.currentTimeMillis());
            memos.add(0, memo); // 최신 메모를 맨 앞에 추가
        } else {
            // 기존 메모 업데이트
            for (int i = 0; i < memos.size(); i++) {
                if (memos.get(i).getId().equals(memo.getId())) {
                    memos.set(i, memo);
                    break;
                }
            }
        }

        String json = gson.toJson(memos);
        prefs.edit().putString(KEY_MEMOS, json).apply();
    }

    public void deleteMemo(String id) {
        List<UserMemo> memos = getAllMemos();
        memos.removeIf(memo -> memo.getId().equals(id));
        String json = gson.toJson(memos);
        prefs.edit().putString(KEY_MEMOS, json).apply();
    }

    public UserMemo getMemoById(String id) {
        List<UserMemo> memos = getAllMemos();
        for (UserMemo memo : memos) {
            if (memo.getId().equals(id)) {
                return memo;
            }
        }
        return null;
    }
}
