package com.green.jobdone.serviceType.detail;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/detail")
@Slf4j
@Tag(name = "카페 관련")
public class DetailTypeController {
    private final DetailTypeService detailTypeService;


}
