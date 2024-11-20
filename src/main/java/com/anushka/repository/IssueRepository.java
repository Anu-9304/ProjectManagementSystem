package com.anushka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.model.Issue;

public interface IssueRepository extends JpaRepository<Issue,Long>{

    public List<Issue>findByProjectId(Long ID);
}
