package com.github.paicoding.forum.core.mdc;

import org.slf4j.MDC;

/**
 * MdcUtil全称Mapped Diagnostic Context，可译为上下文诊断映射
 * 主要用于在多线程环境中存储每个线程特定的诊断信息，比如traceId。
 * @date 2023/5/29
 */
public class MdcUtil {
    public static final String TRACE_ID_KEY = "traceId";

    public static void add(String key, String val) {
        MDC.put(key, val);
    }

    // 生成一个traceId并添加到MDC中
    public static void addTraceId() {
        // traceId的生成规则，技术派提供了两种生成策略，可以使用自定义的也可以使用SkyWalking; 实际项目中选择一种即可
        MDC.put(TRACE_ID_KEY, SelfTraceIdGenerator.generate());
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    // 清除MDC中的所有信息，然后把traceId添加回去。
    public static void reset() {
        String traceId = MDC.get(TRACE_ID_KEY);
        MDC.clear();
        MDC.put(TRACE_ID_KEY, traceId);
    }

    public static void clear() {
        MDC.clear();
    }
}
