package com.project.young.candidateservice.service;

import com.project.young.candidateservice.client.JobClient;
import com.project.young.candidateservice.dto.CandidateDetailsDto;
import com.project.young.candidateservice.dto.CandidateDto;
import com.project.young.candidateservice.repository.CandidateRepository;
import com.project.young.candidateservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {

    @Autowired
    private JobClient client;

    @Autowired
    private CandidateRepository repository;

    public Flux<CandidateDto> all(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDetailsDto> getById(String id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDetailsDto)
                .flatMap(this::addRecommendedJobs);
    }

    private Mono<CandidateDetailsDto> addRecommendedJobs(CandidateDetailsDto dto){
        return this.client.getRecommendedJobs(dto.getSkills())
                .doOnNext(dto::setRecommendedJobs)
                .thenReturn(dto);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> mono){
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

}
