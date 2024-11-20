package com.anushka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.config.request.IssueRequest;
import com.anushka.model.Issue;
import com.anushka.model.Project;
import com.anushka.model.User;
import com.anushka.repository.IssueRepository;

@Service
public class IssueServiceimpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService; 

    @Autowired
    private UserService userservice;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
        return issue.get();
        }
        throw new Exception("No issues found with issueid" +issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest,User user) throws Exception {
        Project project=projectService.getProjectById(issueRequest.getProjectId());

        Issue issue=new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        //issue.setProjectId(issueRequest.getProjectId());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());

        issue.setProject(project);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userid) throws Exception {
        getIssueById(issueId);

        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueld, Long userId) throws Exception {
        User user=userservice.findUserById(userId);
        Issue issue=getIssueById(issueld);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueld, String status) throws Exception {
        Issue issue=getIssueById(issueld);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

}
