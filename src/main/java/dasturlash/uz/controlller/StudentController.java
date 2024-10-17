package dasturlash.uz.controlller;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.FilterDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.dto.StudentUpdateDTO;
import dasturlash.uz.enums.Gender;
import dasturlash.uz.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.create(dto));
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> all() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudentUpdateDTO dto) {
        return ResponseEntity.ok(studentService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
         return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<StudentDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping("/by-surname/{surname}")
    public ResponseEntity<List<StudentDTO>>getBySurname(@PathVariable String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping("/by-level/{level}")
    public ResponseEntity<List<StudentDTO>>getByLevel(@PathVariable String level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }

    @GetMapping("/by-gender/{gender}")
    public ResponseEntity<List<StudentDTO>>getByGender(@PathVariable Gender gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }
    @GetMapping("/by-age/{age}")
    public ResponseEntity<List<StudentDTO>>getByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }
    @GetMapping("/by-created_date/{created_date}")
    public ResponseEntity<List<StudentDTO>>getStudentsByDate(@PathVariable LocalDate created_date) {
        return ResponseEntity.ok(studentService.getStudentsByDate(created_date));
    }
    @GetMapping("/by-created_date/{created_date1}/{created_date2}")
    public ResponseEntity<List<StudentDTO>>getStudentsByDates(@PathVariable LocalDate created_date1,
                                                           @PathVariable LocalDate created_date2) {
        return ResponseEntity.ok(studentService.getStudentsByDates(created_date1, created_date2));

    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<StudentDTO>> all(@RequestParam(value = "page", defaultValue = "4" ) Integer page,
                                                @RequestParam(value = "size", defaultValue = "2") Integer size) {
        return ResponseEntity.ok(studentService.pagination((page-1),size));
    }

    @GetMapping("/pagLevel/{level}")
    public ResponseEntity<Page<StudentDTO>> allByLevel(@PathVariable String level,
                                                @RequestParam(defaultValue = "1" ) Integer page,
                                                @RequestParam(defaultValue = "2") Integer size) {
        return ResponseEntity.ok(studentService.paginationByLevel(level,(page-1),size));
    }
    @GetMapping("/pagGender/{gender}")
    public ResponseEntity<Page<StudentDTO>> paginationByGender(@PathVariable Gender gender,
                                                       @RequestParam(defaultValue = "2" ) Integer page,
                                                       @RequestParam(defaultValue = "3") Integer size) {
        return ResponseEntity.ok(studentService.paginationByGender(gender,(page-1),size));
    }

    @PostMapping("/filter")
    private ResponseEntity<Page<StudentDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                    @RequestBody FilterDTO filterDTO) {
        return ResponseEntity.ok(studentService.filter(filterDTO, page - 1, size));
    }



}
