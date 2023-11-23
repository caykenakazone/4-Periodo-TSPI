package com.iftm.softwarehouse.feign;


import com.iftm.softwarehouse.models.dtos.AnnualWageDTO;
import com.iftm.softwarehouse.models.dtos.EmployeeDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "producer")
public interface FeignConsume {
    @GetMapping("/api/response")
    @CircuitBreaker(name = "consumer", fallbackMethod = "getResponse")
    ResponseEntity<AnnualWageDTO> getResponse(EmployeeDTO employee);
    default ResponseEntity<AnnualWageDTO> getResponse(Throwable error) {
        return ResponseEntity.internalServerError().build();
    }

}
