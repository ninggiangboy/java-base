package dev.ngb.issues_logging_app.common.model;

import lombok.Builder;

import java.util.List;

@Builder
public record PageData<T>(
        List<T> data,
        PageMetadata metadata
) {
    @Builder
    public record PageMetadata(
            int page,
            int size,
            int totalPage,
            long totalElement,
            boolean hasNext,
            boolean hasPrevious
    ) {
        public PageMetadata {
            hasNext = page < totalPage - 1;
            hasPrevious = page > 0;
        }
    }
}
