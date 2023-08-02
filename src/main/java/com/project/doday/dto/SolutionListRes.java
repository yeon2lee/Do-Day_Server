package com.project.doday.dto;

import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "해결 목록 모델")
public class SolutionListRes {
    @Schema(description = "해결 아이디" , example = "1")
    private Long id;

    @Schema(description = "신고 날짜" , example = "2023-07-31T09:03:23.924Z")
    private LocalDateTime reportDate;

    @Schema(description = "신고 위치" , example = "서울시 광진구 화양동 7")
    private String location;

    @Schema(description = "빗물받이를 청소한 사진" , example = "")
    private String photo;

    @Schema(description = "반려사유" , example = "허위 보고로 판단되어 반려되었습니다. (승인이 되어 반려 사유가 없을 때는 null)")
    private String content;

    @Schema(description = "해결 상태" , example = "UNRESOLVED")
    private SolutionState state;

    public Solution toEntity() {
        return Solution.builder()
                .reportDate(reportDate)
                .location(location)
                .photo(photo)
                .state(state)
                .build();
    }
}
