package dasturlash.uz.service;

import dasturlash.uz.dto.CourseDTO;
import dasturlash.uz.dto.CourseUpdateDTO;
import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.CourseEntity;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.enums.Gender;
import dasturlash.uz.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
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
        return toDto(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND")));
    }

    public List<CourseDTO> getAll() {
        return changerDTOList(courseRepository.getAll());
    }

    public String update(Integer id, CourseUpdateDTO dto) {
        if (courseRepository.updateById(id,
                dto.getName(),
                dto.getPrice(),
                dto.getDuration(),
                dto.getDateTime()
        ) == 1) {
            return "UPDATED";
        }
        return "NON UPDATED";
    }

    public String delete(Integer id) {
        if (courseRepository.deleteId(id) == 1) {
            return "DELETED";
        }
        return ("NOT DELETED");
    }

    public List<CourseDTO> getByName(String name) {
        return changerDTOList(courseRepository.findByName(name));
    }

    public List<CourseDTO> getByPrice(Double price) {
        return changerDTOList(courseRepository.findByPrice(price));
    }

    public List<CourseDTO> getByDuration(Integer duration) {
        return changerDTOList(courseRepository.getByDuration(duration));
    }


    public List<CourseDTO> getCourseByPrices(Double price1, Double price2) {
        return changerDTOList(courseRepository.findByPriceBetween(price1, price2));
    }

    public List<CourseDTO> getCourseByDates(LocalDate createdDate1, LocalDate createdDate2) {
        LocalDateTime fromDate = LocalDateTime.of(createdDate1, LocalTime.MIN);
        LocalDateTime toDate = LocalDateTime.of(createdDate2, LocalTime.MAX);
        return changerDTOList(courseRepository.findByDateTimeBetween(fromDate, toDate));
    }

    public List<CourseDTO> changerDTOList(List<CourseEntity> courseEntities) {
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

    public PageImpl<CourseDTO> pagination(int page, int size) {
        //  Pageable pageable = PageRequest.of(page, size);
        return helper(PageRequest.of(page, size), courseRepository.getAllCourses(PageRequest.of(page, size)));
    }

    public PageImpl<CourseDTO> paginationByPrice(Double price, int page, int size) {
        return helper(PageRequest.of(page, size), courseRepository.paginationByPrice(price, PageRequest.of(page, size)));
    }


    public PageImpl<CourseDTO> helper(Pageable pageable, Page<CourseEntity> courseEntityPage) {
//        List<CourseDTO> courseDTOList = changerDTOList(courseEntityPage.getContent());
//        long total = courseEntityPage.getTotalElements();
        return new PageImpl<CourseDTO>(changerDTOList(courseEntityPage.getContent()), pageable, courseEntityPage.getTotalElements());
    }

    public Page<CourseDTO> paginationByPriceBetween(Double price1, Double price2, int page, Integer size) {
        return helper(PageRequest.of(page, size),
                courseRepository.byPrices(price1, price2, PageRequest.of(page, size)));
    }
}
