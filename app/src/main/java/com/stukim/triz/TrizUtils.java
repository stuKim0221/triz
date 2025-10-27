package com.stukim.triz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TRIZ 앱에서 자주 사용되는 유틸리티 메서드를 제공하는 클래스
 */
public class TrizUtils {

    // 파라미터 캐시 (성능 최적화)
    private static Map<Integer, TrizParameter> parameterCache = null;
    private static Map<Integer, InventionPrinciple> principleCache = null;

    /**
     * 파라미터 ID로 파라미터 객체를 찾는다
     * @param parameterId 파라미터 ID
     * @return TrizParameter 객체, 없으면 null
     */
    public static TrizParameter findParameterById(int parameterId) {
        if (parameterCache == null) {
            initializeParameterCache();
        }
        return parameterCache.get(parameterId);
    }

    /**
     * 원리 ID로 원리 객체를 찾는다
     * @param principleId 원리 ID
     * @return InventionPrinciple 객체, 없으면 null
     */
    public static InventionPrinciple findPrincipleById(int principleId) {
        if (principleCache == null) {
            initializePrincipleCache();
        }
        return principleCache.get(principleId);
    }

    /**
     * 파라미터 캐시를 초기화한다
     */
    private static void initializeParameterCache() {
        parameterCache = new HashMap<>();
        List<TrizParameter> parameters = TrizData.getParameters();
        for (TrizParameter param : parameters) {
            parameterCache.put(param.getId(), param);
        }
    }

    /**
     * 원리 캐시를 초기화한다
     */
    private static void initializePrincipleCache() {
        principleCache = new HashMap<>();
        List<InventionPrinciple> principles = TrizData.getPrinciples();
        for (InventionPrinciple principle : principles) {
            principleCache.put(principle.getId(), principle);
        }
    }

    /**
     * 파라미터 ID로 파라미터 이름을 반환한다
     * @param parameterId 파라미터 ID
     * @return 파라미터 이름, 없으면 빈 문자열
     */
    public static String getParameterName(int parameterId) {
        TrizParameter param = findParameterById(parameterId);
        return param != null ? param.getName() : "";
    }

    /**
     * 원리 ID로 원리 이름을 반환한다
     * @param principleId 원리 ID
     * @return 원리 이름, 없으면 빈 문자열
     */
    public static String getPrincipleName(int principleId) {
        InventionPrinciple principle = findPrincipleById(principleId);
        return principle != null ? principle.getName() : "";
    }

    /**
     * 캐시를 초기화한다 (테스트 용도 또는 데이터 갱신 시 사용)
     */
    public static void clearCache() {
        parameterCache = null;
        principleCache = null;
    }

    // Private constructor to prevent instantiation
    private TrizUtils() {
        throw new AssertionError("Cannot instantiate TrizUtils class");
    }
}
