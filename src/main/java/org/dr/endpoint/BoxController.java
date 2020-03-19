package org.dr.endpoint;

import lombok.RequiredArgsConstructor;
import org.dr.box.boundary.BoxService;
import org.dr.box.boundary.command.CreateNewBoxCommand;
import org.dr.box.boundary.vo.BoxVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/box")
public class BoxController {

    private final BoxService boxService;

    @GetMapping("/all")
    public List<BoxVO> getAllBoxes() {
        return boxService.getAllBoxes();
    }

    @PostMapping("/new")
    public ResponseEntity createNewBox(@RequestBody CreateNewBoxCommand command) {
        boxService.createBox(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
