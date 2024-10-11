package dasturlash.uz.controlller;

import dasturlash.uz.dto.StudentCourseMarkDTO;
import dasturlash.uz.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student_course")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    public ResponseEntity<StudentCourseMarkDTO> create(StudentCourseMarkDTO studentCourseMarkDTO) {

        return ResponseEntity.ok(studentCourseMarkService.createStudentCourseMark(studentCourseMarkDTO));
    }
}
