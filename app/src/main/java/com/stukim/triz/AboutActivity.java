package com.stukim.triz;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // 버전 정보 표시
        TextView tvVersion = findViewById(R.id.tvVersion);
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            tvVersion.setText("버전 " + version);
        } catch (PackageManager.NameNotFoundException e) {
            tvVersion.setText("버전 1.0.0");
        }

        // 이용약관
        MaterialCardView cardTerms = findViewById(R.id.cardTerms);
        cardTerms.setOnClickListener(v -> showTermsDialog());

        // 개인정보 처리방침
        MaterialCardView cardPrivacy = findViewById(R.id.cardPrivacy);
        cardPrivacy.setOnClickListener(v -> openWebPage("https://stukim0221.github.io/triz/privacy-policy.html"));

        // 오픈소스 라이선스
        MaterialCardView cardLicense = findViewById(R.id.cardLicense);
        cardLicense.setOnClickListener(v -> showLicenseDialog());

        // GitHub 저장소
        MaterialCardView cardGithub = findViewById(R.id.cardGithub);
        cardGithub.setOnClickListener(v -> openWebPage("https://github.com/stuKim0221/triz"));
    }

    private void showTermsDialog() {
        String terms = "이용약관\n\n" +
                "본 앱은 교육 목적의 무료 앱입니다.\n\n" +
                "1. 서비스 제공\n" +
                "- 본 앱은 TRIZ 방법론 학습을 지원하는 무료 교육용 앱입니다.\n" +
                "- 모든 기능은 무료로 제공되며, 광고나 인앱 결제가 없습니다.\n\n" +
                "2. 사용자 책임\n" +
                "- 사용자는 본 앱을 교육 및 학습 목적으로만 사용해야 합니다.\n" +
                "- 앱에서 제공하는 정보는 참고용이며, 중요한 결정에는 전문가의 조언을 받으시기 바랍니다.\n\n" +
                "3. 지적 재산권\n" +
                "- TRIZ 방법론은 공개된 이론이며, 교육 목적으로 사용됩니다.\n" +
                "- 앱의 소스코드는 GitHub에서 확인할 수 있습니다.\n\n" +
                "4. 면책 조항\n" +
                "- 본 앱은 \"있는 그대로\" 제공되며, 특정 목적에 대한 적합성을 보증하지 않습니다.\n" +
                "- 앱 사용으로 발생하는 손해에 대해 개발자는 책임을 지지 않습니다.\n\n" +
                "5. 약관 변경\n" +
                "- 본 약관은 필요시 변경될 수 있으며, 변경 시 앱 업데이트를 통해 공지됩니다.\n\n" +
                "6. 문의\n" +
                "- GitHub Issues를 통해 문의하실 수 있습니다.\n" +
                "- https://github.com/stuKim0221/triz/issues\n\n" +
                "최종 업데이트: 2025년 10월 27일";

        new AlertDialog.Builder(this)
                .setTitle("이용약관")
                .setMessage(terms)
                .setPositiveButton("확인", null)
                .show();
    }

    private void showLicenseDialog() {
        String licenses = "오픈소스 라이선스\n\n" +
                "본 앱은 다음 오픈소스 라이브러리를 사용합니다:\n\n" +
                "1. AndroidX Libraries\n" +
                "- License: Apache License 2.0\n" +
                "- Copyright © The Android Open Source Project\n\n" +
                "2. Material Components for Android\n" +
                "- License: Apache License 2.0\n" +
                "- Copyright © Google Inc.\n\n" +
                "3. Gson\n" +
                "- License: Apache License 2.0\n" +
                "- Copyright © Google Inc.\n\n" +
                "Apache License 2.0\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.";

        new AlertDialog.Builder(this)
                .setTitle("오픈소스 라이선스")
                .setMessage(licenses)
                .setPositiveButton("확인", null)
                .show();
    }

    private void openWebPage(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "브라우저를 열 수 없습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
