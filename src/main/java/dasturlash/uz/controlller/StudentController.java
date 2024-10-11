package dasturlash.uz.controlller;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.enums.Gender;
import dasturlash.uz.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
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

}
