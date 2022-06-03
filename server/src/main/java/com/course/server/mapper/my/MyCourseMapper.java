package com.course.server.mapper.my;

import com.course.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

public interface MyCourseMapper {
    int updateTime(@Param("courseId") String CourseId);

    int updateSort(SortDto sortDto);

    int moveSortBackward(SortDto sortDto);

    int moveSortForward(SortDto sortDto);
}
