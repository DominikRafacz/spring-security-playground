package org.dr.box.boundary.command;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewBoxCommand {
    private Double length;
    private Double width;
    private Double height;
}
