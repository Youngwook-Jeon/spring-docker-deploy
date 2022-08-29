package com.project.young.jobservice.util;

import com.project.young.jobservice.dto.JobDto;
import com.project.young.jobservice.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static JobDto toDto(Job job){
        JobDto dto = new JobDto();
        BeanUtils.copyProperties(job, dto);
        dto.setHostName(AppUtil.getHostname());
        return dto;
    }

    public static Job toEntity(JobDto dto){
        Job job = new Job();
        BeanUtils.copyProperties(dto, job);
        return job;
    }
}
