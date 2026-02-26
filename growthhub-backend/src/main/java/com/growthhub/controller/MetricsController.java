package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    /** Basic system metrics (optional). Secured - requires auth. */
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> metrics() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        Runtime r = Runtime.getRuntime();
        return ResponseEntity.ok(ApiResponse.ok(Map.of(
                "jvmUptimeMs", runtime.getUptime(),
                "maxMemory", r.maxMemory(),
                "totalMemory", r.totalMemory(),
                "freeMemory", r.freeMemory()
        )));
    }
}
