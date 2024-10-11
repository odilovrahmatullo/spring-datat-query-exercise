package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.enums.Gender;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentDTOList.add(toDto(studentEntity));
        }
        return studentDTOList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
        StudentEntity studentEntity = optional.get();
        return toDto(studentEntity);
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


    public StudentDTO update(Integer id, StudentDTO dto) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
        StudentEntity studentEntity = optional.get();
        if(dto.getName() != null) {
            studentEntity.setName(dto.getName());
        }
        if(dto.getSurname() != null) {
            studentEntity.setSurname(dto.getSurname());
        }
        if(dto.getAge() != null) {
            studentEntity.setAge(dto.getAge());
        }
        if(dto.getLevel() != null) {
            studentEntity.setLevel(dto.getLevel());
        }
        if(dto.getGender() != null) {
            studentEntity.setGender(dto.getGender());
        }
        if(dto.getDateTime() != null) {
            studentEntity.setDateTime(dto.getDateTime());
        }

        studentRepository.save(studentEntity);
        return dto;
    }

    public String delete(Integer id) {
        Optional<StudentEntity>optional = studentRepository.findById(id);
        if(optional.isPresent()){
            StudentEntity entity = optional.get();
            studentRepository.delete(entity);
            return "DELETED";
        }
        return ("ID NOT FOUND");
    }


    public List<StudentDTO>getByName(String name) {
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


    public List<StudentDTO> changerDTOList(List<StudentEntity> studentEntities){
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentDTOList.add(toDto(studentEntity));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getStudentsByDate(LocalDate createdDate) {
        LocalDateTime fromDateTime = LocalDateTime.of(createdDate, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(createdDate, LocalTime.MAX);
        return changerDTOList(studentRepository.findByDateTimeBetween(fromDateTime,toDateTime));
    }

    public List<StudentDTO> getStudentsByDates(LocalDate createdDate1, LocalDate createdDate2) {
        LocalDateTime fromDate = LocalDateTime.of(createdDate1,LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(createdDate2,LocalTime.MAX);
        return changerDTOList(studentRepository.findByDateTimeBetween(fromDate,toDate));
    }


}
