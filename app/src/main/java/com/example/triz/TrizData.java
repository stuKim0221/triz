package com.example.triz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrizData {

    // 39 Parameters
    public static List<TrizParameter> getParameters() {
        List<TrizParameter> parameters = new ArrayList<>();
        parameters.add(new TrizParameter(1, "움직이는 물체의 무게"));
        parameters.add(new TrizParameter(2, "고정된 물체의 무게"));
        parameters.add(new TrizParameter(3, "움직이는 물체의 길이"));
        parameters.add(new TrizParameter(4, "고정된 물체의 길이"));
        parameters.add(new TrizParameter(5, "움직이는 물체의 면적"));
        parameters.add(new TrizParameter(6, "고정된 물체의 면적"));
        parameters.add(new TrizParameter(7, "움직이는 물체의 부피"));
        parameters.add(new TrizParameter(8, "고정된 물체의 부피"));
        parameters.add(new TrizParameter(9, "속도"));
        parameters.add(new TrizParameter(10, "힘"));
        parameters.add(new TrizParameter(11, "장력, 압력"));
        parameters.add(new TrizParameter(12, "모양"));
        parameters.add(new TrizParameter(13, "물체의 안정성"));
        parameters.add(new TrizParameter(14, "강도"));
        parameters.add(new TrizParameter(15, "움직이는 물체의 내구성"));
        parameters.add(new TrizParameter(16, "고정된 물체의 내구성"));
        parameters.add(new TrizParameter(17, "온도"));
        parameters.add(new TrizParameter(18, "밝기"));
        parameters.add(new TrizParameter(19, "움직이는 물체가 소비하는 에너지"));
        parameters.add(new TrizParameter(20, "고정된 물체가 소비하는 에너지"));
        parameters.add(new TrizParameter(21, "동력"));
        parameters.add(new TrizParameter(22, "에너지 손실"));
        parameters.add(new TrizParameter(23, "물질 손실"));
        parameters.add(new TrizParameter(24, "정보 손실"));
        parameters.add(new TrizParameter(25, "시간 손실"));
        parameters.add(new TrizParameter(26, "물질의 양"));
        parameters.add(new TrizParameter(27, "신뢰성"));
        parameters.add(new TrizParameter(28, "측정의 정확성"));
        parameters.add(new TrizParameter(29, "제조의 정밀도"));
        parameters.add(new TrizParameter(30, "물체에 작용하는 유해한 요인"));
        parameters.add(new TrizParameter(31, "유해한 부작용"));
        parameters.add(new TrizParameter(32, "제조 용이성"));
        parameters.add(new TrizParameter(33, "사용 편의성"));
        parameters.add(new TrizParameter(34, "수리보수 편의성"));
        parameters.add(new TrizParameter(35, "적응성"));
        parameters.add(new TrizParameter(36, "장치의 복잡성"));
        parameters.add(new TrizParameter(37, "장치의 복잡성"));
        parameters.add(new TrizParameter(38, "자동화 정도"));
        parameters.add(new TrizParameter(39, "생산성"));
        return parameters;
    }

    // 40 Invention Principles
    public static List<InventionPrinciple> getPrinciples() {
        List<InventionPrinciple> principles = new ArrayList<>();

        principles.add(new InventionPrinciple(1, "분할(Segmentation)",
            "물체를 독립적인 부분으로 나눈다",
            "예: 모듈러 가구, 분리형 키보드"));

        principles.add(new InventionPrinciple(2, "추출(Extraction)",
            "물체에서 '방해되는' 부분이나 속성을 제거한다",
            "예: 무선 이어폰, 무선 충전"));

        principles.add(new InventionPrinciple(3, "국부적 품질(Local Quality)",
            "균일한 구조를 불균일한 구조로 바꾼다",
            "예: 양날 면도기, 차별화된 쿠션"));

        principles.add(new InventionPrinciple(4, "비대칭(Asymmetry)",
            "대칭 형태를 비대칭으로 바꾼다",
            "예: 인체공학 마우스, 비대칭 타이어"));

        principles.add(new InventionPrinciple(5, "통합(Consolidation)",
            "공간적으로 동일하거나 유사한 물체를 결합한다",
            "예: 스위스 아미 나이프, 복합기"));

        principles.add(new InventionPrinciple(6, "다용도(Universality)",
            "물체가 여러 기능을 수행하게 한다",
            "예: 소파 겸 침대, 멀티툴"));

        principles.add(new InventionPrinciple(7, "포개기(Nesting)",
            "물체를 다른 물체 안에 넣는다",
            "예: 접이식 텀블러, 러시아 인형"));

        principles.add(new InventionPrinciple(8, "평형추(Counterweight)",
            "물체의 무게를 다른 물체와 결합하여 상쇄한다",
            "예: 엘리베이터 평형추, 균형 램프"));

        principles.add(new InventionPrinciple(9, "사전 반대조치(Prior Counteraction)",
            "미리 반대 작용을 가한다",
            "예: 사전 응력 콘크리트, 백신"));

        principles.add(new InventionPrinciple(10, "사전 조치(Prior Action)",
            "필요한 변화를 미리 수행한다",
            "예: 미리 자른 야채, 사전 설치 소프트웨어"));

        principles.add(new InventionPrinciple(11, "사전 예방(Cushion in Advance)",
            "신뢰성이 낮은 물체를 미리 보호한다",
            "예: 에어백, 백업 시스템"));

        principles.add(new InventionPrinciple(12, "높이 맞추기(Equipotentiality)",
            "작업 조건을 변경하여 물체를 들어올리거나 내릴 필요를 없앤다",
            "예: 회전식 테이블, 수평 작업대"));

        principles.add(new InventionPrinciple(13, "반대로 하기(Inversion)",
            "문제 해결을 위해 반대로 행동한다",
            "예: 냉각 대신 가열, 물체를 움직이는 대신 환경 변경"));

        principles.add(new InventionPrinciple(14, "곡선화(Spheroidality)",
            "직선 부분을 곡선으로, 평면을 구면으로 바꾼다",
            "예: 곡면 모니터, 볼 베어링"));

        principles.add(new InventionPrinciple(15, "역동성(Dynamicity)",
            "물체의 특성을 최적화하기 위해 조정 가능하게 한다",
            "예: 조절식 의자, 가변 저항기"));

        principles.add(new InventionPrinciple(16, "부족/초과 조치(Partial or Excessive Action)",
            "100% 달성이 어려우면 조금 덜 하거나 더 많이 한다",
            "예: 오버페인팅, 여유 있게 재료 주문"));

        principles.add(new InventionPrinciple(17, "차원 바꾸기(Moving to a New Dimension)",
            "1차원에서 2차원으로, 2차원에서 3차원으로 이동한다",
            "예: 다층 회로 기판, 입체 주차장"));

        principles.add(new InventionPrinciple(18, "기계적 진동(Mechanical Vibration)",
            "물체를 진동시킨다",
            "예: 진동 마사지기, 초음파 세척기"));

        principles.add(new InventionPrinciple(19, "주기적 작동(Periodic Action)",
            "연속 작용을 주기적 또는 펄스 작용으로 바꾼다",
            "예: 펄스 레이저, 간헐적 와이퍼"));

        principles.add(new InventionPrinciple(20, "유용한 작용 지속(Continuity of Useful Action)",
            "작용을 중단 없이 수행한다",
            "예: 플라이휠, 연속 생산 라인"));

        principles.add(new InventionPrinciple(21, "고속 처리(Rushing Through)",
            "유해하거나 위험한 작업을 고속으로 수행한다",
            "예: 고속 드릴링, 급속 냉각"));

        principles.add(new InventionPrinciple(22, "전화위복(Convert Harm into Benefit)",
            "유해 요인을 이용하여 긍정적 효과를 얻는다",
            "예: 폐열 재활용, 소음을 에너지로 전환"));

        principles.add(new InventionPrinciple(23, "환류(Feedback)",
            "피드백을 도입하거나 개선한다",
            "예: 자동 온도 조절기, 자동 조종 장치"));

        principles.add(new InventionPrinciple(24, "매개체 활용(Mediator)",
            "중간 물체를 사용하여 작용을 전달한다",
            "예: 집게, 리모컨"));

        principles.add(new InventionPrinciple(25, "제 시중(Self Service)",
            "물체가 스스로를 서비스하게 한다",
            "예: 자가 윤활 베어링, 자가 청소 오븐"));

        principles.add(new InventionPrinciple(26, "복제(Copying)",
            "비싸거나 복잡한 물체를 단순하고 저렴한 복제품으로 대체한다",
            "예: 가상 프로토타입, 사진 대신 스캔"));

        principles.add(new InventionPrinciple(27, "일회용품 활용(Dispose)",
            "비싼 물체를 저렴한 일회용품으로 대체한다",
            "예: 일회용 컵, 일회용 장갑"));

        principles.add(new InventionPrinciple(28, "기계시스템 대체(Replacement of Mechanical System)",
            "기계적 시스템을 광학, 음향, 열, 화학 시스템으로 대체한다",
            "예: 터치스크린, 광센서"));

        principles.add(new InventionPrinciple(29, "공기압 및 유압활용(Pneumatic or Hydraulic Construction)",
            "고체 부분을 기체나 액체로 대체한다",
            "예: 에어 쿠션, 유압 완충기"));

        principles.add(new InventionPrinciple(30, "유연한 막 또는 얇은 막 활용(Flexible Membranes)",
            "일반 구조를 유연한 막이나 얇은 필름으로 대체한다",
            "예: 풍선, 비닐하우스"));

        principles.add(new InventionPrinciple(31, "다공성 물질 사용(Porous Material)",
            "물체를 다공성으로 만들거나 다공성 요소를 추가한다",
            "예: 스펀지, 여과 필터"));

        principles.add(new InventionPrinciple(32, "색 변경(Change the Color)",
            "물체나 환경의 색상을 변경한다",
            "예: 열변색 잉크, 착색 유리"));

        principles.add(new InventionPrinciple(33, "동질화(Homogeneity)",
            "동일한 재료로 물체를 만든다",
            "예: 플라스틱 일체형 구조, 단일 금속 합금"));

        principles.add(new InventionPrinciple(34, "폐기 혹은 재생(Rejecting and Regenerating Parts)",
            "사용한 부분을 폐기하거나 재생한다",
            "예: 교체용 카트리지, 재생 가능한 배터리"));

        principles.add(new InventionPrinciple(35, "속성 변환(Transformation of Properties)",
            "물체의 물리적 화학적 상태를 변경한다",
            "예: 액체 질소 냉각, 상변화 재료"));

        principles.add(new InventionPrinciple(36, "상전이(Phase Transition)",
            "상전이 중 발생하는 현상을 이용한다",
            "예: 얼음팩, 증발 냉각"));

        principles.add(new InventionPrinciple(37, "열팽창(Thermal Expansion)",
            "열팽창이나 수축을 이용한다",
            "예: 바이메탈 온도계, 열 스위치"));

        principles.add(new InventionPrinciple(38, "활성화(Accelerated Oxidation)",
            "산소의 농도를 높인다",
            "예: 산소 부화 연소, 고압 산소 치료"));

        principles.add(new InventionPrinciple(39, "비활성화/불활성 환경(Inert Environment)",
            "일반 환경을 불활성 환경으로 대체한다",
            "예: 질소 충전 포장, 진공 포장"));

        principles.add(new InventionPrinciple(40, "복합재료 사용(Composite Materials)",
            "균질 재료를 복합 재료로 대체한다",
            "예: 탄소섬유 복합재, 강화 콘크리트"));

        return principles;
    }

    // Contradiction Matrix loaded from CSV
    // Key: "improving_worsening" (e.g., "1_2"), Value: list of principle numbers
    public static Map<String, int[]> getMatrix(Context context) {
        Map<String, int[]> matrix = new HashMap<>();

        try {
            InputStream is = context.getAssets().open("triz_matrix.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                rowIndex++;

                // Skip first 2 header rows
                if (rowIndex <= 2) {
                    continue;
                }

                // Parse CSV line
                String[] cells = parseCSVLine(line);

                if (cells.length < 3) {
                    continue;
                }

                // Get improving parameter number (column 0)
                String improvingStr = cells[0].trim();
                if (improvingStr.isEmpty()) {
                    continue;
                }

                int improving;
                try {
                    improving = Integer.parseInt(improvingStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                // Process each worsening parameter (columns 2 onwards, skip column 1 which is name)
                // Column 2 = worsening parameter 1, Column 3 = worsening parameter 2, etc.
                for (int colIndex = 2; colIndex < cells.length && colIndex <= 41; colIndex++) {
                    int worsening = colIndex - 1; // Column 2 = parameter 1, Column 3 = parameter 2, etc.

                    if (worsening > 39) {
                        break; // Only 39 parameters
                    }

                    // Skip diagonal (improving == worsening)
                    if (improving == worsening) {
                        continue;
                    }

                    String cell = cells[colIndex].trim();

                    // Skip empty cells or "+" marker
                    if (cell.isEmpty() || cell.equals("+")) {
                        continue;
                    }

                    // Parse principle numbers
                    int[] principles = parsePrinciples(cell);
                    if (principles.length > 0) {
                        matrix.put(improving + "_" + worsening, principles);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    // Parse CSV line considering quoted fields
    private static String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        result.add(currentField.toString());

        return result.toArray(new String[0]);
    }

    // Parse principle numbers from cell (e.g., "15, 8, 29, 34" -> [15, 8, 29, 34])
    private static int[] parsePrinciples(String cell) {
        List<Integer> principles = new ArrayList<>();

        // Remove quotes and extra spaces
        cell = cell.replace("\"", "").trim();

        // Split by comma
        String[] parts = cell.split(",");
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                try {
                    int principle = Integer.parseInt(part);
                    if (principle >= 1 && principle <= 40) {
                        principles.add(principle);
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid numbers
                }
            }
        }

        // Convert to int array
        int[] result = new int[principles.size()];
        for (int i = 0; i < principles.size(); i++) {
            result[i] = principles.get(i);
        }
        return result;
    }
}
