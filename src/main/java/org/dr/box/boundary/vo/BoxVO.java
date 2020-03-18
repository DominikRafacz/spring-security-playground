package org.dr.box.boundary.vo;

import lombok.*;
import org.dr.box.entity.BoxEntity;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxVO {
    private Long ID;

    @NonNull
    private Double length;

    @NonNull
    private Double width;

    @NonNull
    private Double height;

    public static BoxVO from(@NotNull BoxEntity box) {
        return BoxVO.builder()
                .ID(box.getID())
                .length(box.getLength())
                .width(box.getWidth())
                .height(box.getHeight())
                .build();
    }
}
