package com.project.doday.dto;

import com.project.doday.domain.Solution;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "해결 등록 모델")
public class SolutionReq {

    @Schema(description = "사용자의 현재 위도" , example = "37.5460")
    private Double latitude;

    @Schema(description = "사용자의 현재 경도" , example = "127.1039")
    private Double longitude;

    @Schema(description = "사용자의 현재 위치" , example = "서울시 광진구 화양동 7")
    private String location;

    @Schema(description = "빗물받이를 청소한 사진" , example = "")
    private MultipartFile photo;

    @Schema(description = "허위 신고 제보" , example = "허위 신고인 것 같습니다.")
    private String falseReport;

}
