package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.dto.StudentUpdateDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.enums.Gender;
import dasturlash.uz.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentDTO.getName());
        studentEntity.setSurname(studentDTO.getSurname());
        studentEntity.setAge(studentDTO.getAge());
        studentEntity.setLevel(studentDTO.getLevel());
        studentEntity.setGender(studentDTO.getGender());
        studentRepository.save(studentEntity);

        studentDTO.setId(studentEntity.getId());
        studentDTO.setDateTime(studentEntity.getDateTime());

        return studentDTO;
    }

    @Transactional
    public String delete(Integer id) {
        if (studentRepository.deleteId(id) == 0) {
            return "NOT DELETED";
        }
        return "DELETED SUCCESSFULLY";
    }

    public String update(Integer id, StudentUpdateDTO dto) {
        String gender = (dto.getGender() != null) ? dto.getGender().name() : null;

        int result = studentRepository.updateById(id,
                dto.getName(),
                dto.getSurname(),
                dto.getAge(),
                dto.getLevel(),
                gender,
                dto.getDateTime());
        if (result == 1) {
            return "UPDATED";
        }
        return "NOT UPDATED";
    }

    public List<StudentDTO> getAll() {
        return changerDTOList(studentRepository.getAll());
    }

    public StudentDTO getById(Integer id) {
        return toDto(studentRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND")));
    }

    public List<StudentDTO> getByName(String name) {
        return changerDTOList(studentRepository.findByName(name));
    }

    public List<StudentDTO> getBySurname(String surname) {
        return changerDTOList(studentRepository.findBySurname(surname));
    }

    public List<StudentDTO> getByLevel(String level) {
        return changerDTOList(studentRepository.findByLevel(level));
    }

    public List<StudentDTO> getByGender(Gender gender) {
        return changerDTOList(studentRepository.findByGender(gender));
    }

    public List<StudentDTO> getByAge(Integer age) {
        return changerDTOList(studentRepository.findByAge(age));
    }

    public List<StudentDTO> getStudentsByDate(LocalDate createdDate) {
        return changerDTOList(studentRepository.findByDateTimeBetween(LocalDateTime.of(createdDate, LocalTime.MIN),
                LocalDateTime.of(createdDate, LocalTime.MAX)));
    }

    public List<StudentDTO> getStudentsByDates(LocalDate createdDate1, LocalDate createdDate2) {
        return changerDTOList(studentRepository.findByDateTimeBetween(LocalDateTime.of(createdDate1, LocalTime.MIN),
                LocalDateTime.of(createdDate2, LocalTime.MAX)));
    }

    public List<StudentDTO> changerDTOList(List<StudentEntity> studentEntities) {
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentDTOList.add(toDto(studentEntity));
        }
        return studentDTOList;
    }

    private StudentDTO toDto(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        studentDTO.setAge(studentEntity.getAge());
        studentDTO.setLevel(studentEntity.getLevel());
        studentDTO.setGender(studentEntity.getGender());
        studentDTO.setDateTime(studentEntity.getDateTime());
        return studentDTO;
    }

    public PageImpl<StudentDTO> pagination(int page, int size) {
        //  Pageable pageable = PageRequest.of(page, size);
        return helper(PageRequest.of(page, size), studentRepository.getAllStudents(PageRequest.of(page, size)));
    }

    public PageImpl<StudentDTO> paginationByLevel(String level, int page, int size) {
        return helper(PageRequest.of(page, size), studentRepository.paginationByLevel(level, PageRequest.of(page, size)));
    }

    public PageImpl<StudentDTO> paginationByGender(Gender gender, int page, int size) {
        // Pageable pageable = PageRequest.of(page, size);
        return helper(PageRequest.of(page, size), studentRepository.paginationByGender(gender, PageRequest.of(page, size)));
    }


    public PageImpl<StudentDTO> helper(Pageable pageable, Page<StudentEntity> studentEntityPage) {
        List<StudentDTO> studentDTOList = changerDTOList(studentEntityPage.getContent());
        long total = studentEntityPage.getTotalElements();
        return new PageImpl<StudentDTO>(studentDTOList, pageable, total);
    }


}
