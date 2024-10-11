package dasturlash.uz.service;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseDTO.getName());
        courseEntity.setPrice(courseDTO.getPrice());
        courseEntity.setDuration(courseDTO.getDuration());
        courseEntity.setDateTime(LocalDateTime.now());

        courseRepository.save(courseEntity);

        courseDTO.setId(courseEntity.getId());
        courseDTO.setDateTime(courseEntity.getDateTime());

        return courseDTO;
    }

    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
        CourseEntity courseEntity = optional.get();
        return toDto(courseEntity);
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> courseEntities = courseRepository.findAll();
        List<CourseDTO> courseDTOList = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntities) {
            courseDTOList.add(toDto(courseEntity));
        }
        return courseDTOList;
    }

    private CourseDTO toDto(CourseEntity courseEntity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(courseEntity.getId());
        courseDTO.setName(courseEntity.getName());
        courseDTO.setPrice(courseEntity.getPrice());
        courseDTO.setDuration(courseEntity.getDuration());
        courseDTO.setDateTime(courseEntity.getDateTime());
        return courseDTO;
    }


    public CourseDTO update(Integer id, CourseDTO dto) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
        CourseEntity courseEntity= optional.get();
        if(dto.getName() != null) {
            courseEntity.setName(dto.getName());
        }
       if(dto.getPrice() != null) {
           courseEntity.setPrice(dto.getPrice());
       }
       if(dto.getDuration() != null) {
           courseEntity.setDuration(dto.getDuration());
       }
       courseRepository.save(courseEntity);
        return dto;
    }

    public String delete(Integer id) {
        Optional<CourseEntity>optional = courseRepository.findById(id);
        if(optional.isPresent()){
            CourseEntity entity = optional.get();
            courseRepository.delete(entity);
            return "DELETED";
        }
        return ("ID NOT FOUND");
    }

    public List<CourseDTO> getByName(String name) {
         return changerDTO(courseRepository.findByName(name));
    }

    public List<CourseDTO> getByPrice(Double price) {
        return changerDTO(courseRepository.findByPrice(price));
    }

    public List<CourseDTO> getByDuration(Integer duration) {
        return changerDTO(courseRepository.findByDuration(duration));
    }


    public List<CourseDTO> changerDTO(List<CourseEntity> courseEntities) {
        List<CourseDTO> courseDTOList = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntities) {
            courseDTOList.add(toDto(courseEntity));
        }
        return courseDTOList;
    }

    public List<CourseDTO> getCourseByPrices(Double price1, Double price2) {
      return changerDTO(courseRepository.findByPriceBetween(price1,price2));
    }

    public List<CourseDTO> getCourseByDates(LocalDate createdDate1, LocalDate createdDate2) {
        LocalDateTime fromDate = LocalDateTime.of(createdDate1, LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(createdDate2, LocalTime.MAX);
       return changerDTO(courseRepository.findByDateTimeBetween(fromDate,toDate));
    }
}
