package dasturlash.uz.controlller;


import dasturlash.uz.dto.*;
import dasturlash.uz.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("")
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.create(dto));
    }

    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> all() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CourseUpdateDTO dto) {
        return ResponseEntity.ok(courseService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<CourseDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/by-price/{price}")
    public ResponseEntity<List<CourseDTO>> getByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping("/by-duration/{duration}")
    public ResponseEntity<List<CourseDTO>> getByDuration(@PathVariable Integer duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }

    @GetMapping("/by-price/{price1}/{price2}")
    public ResponseEntity<List<CourseDTO>>getCourseByPrices(@PathVariable Double price1, @PathVariable Double price2) {
        return ResponseEntity.ok(courseService.getCourseByPrices(price1,price2));
    }

    @GetMapping("/by-created_date/{created_date1}/{created_date2}")
    public ResponseEntity<List<CourseDTO>>getCourseByDates(@PathVariable LocalDate created_date1, @PathVariable LocalDate created_date2) {
        return ResponseEntity.ok(courseService.getCourseByDates(created_date1,created_date2));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CourseDTO>> getPagination(@RequestParam Integer page,
                                                         @RequestParam Integer size) {
        return ResponseEntity.ok(courseService.pagination((page-1),size));
    }

    @GetMapping("/pagPrice")
    public ResponseEntity<Page<CourseDTO>> paginationByPrice(@RequestParam Double price,
                                                             @RequestParam Integer page,
                                                             @RequestParam Integer size) {
        return ResponseEntity.ok(courseService.paginationByPrice(price,(page-1),size));
    }


    @GetMapping("/pagPriceBetween")
    public ResponseEntity<Page<CourseDTO>> paginationByPrice(@RequestParam Double price1,
                                                             @RequestParam Double price2,
                                                             @RequestParam Integer page,
                                                             @RequestParam Integer size) {
        return ResponseEntity.ok(courseService.paginationByPriceBetween(price1,price2,(page-1),size));
    }

    @PostMapping("/filter")
    private ResponseEntity<Page<CourseDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                    @RequestBody FilterCourseDTO dto) {
        return ResponseEntity.ok(courseService.filter(dto, page - 1, size));
    }



}
