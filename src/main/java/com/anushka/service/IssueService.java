package com.anushka.service;

import java.util.List;

import com.anushka.config.request.IssueRequest;
import com.anushka.model.Issue;
import com.anushka.model.User;

public interface IssueService {

Issue getIssueById(Long issueId) throws Exception;


List<Issue> getIssueByProjectId(Long projectId) throws Exception;


Issue createIssue (IssueRequest issue, User user) throws Exception;

void deleteIssue (Long issueId, Long userid) throws Exception;

Issue addUserToIssue (Long issueld, Long userId) throws Exception;

Issue updateStatus (Long issueld, String status) throws Exception;

}
