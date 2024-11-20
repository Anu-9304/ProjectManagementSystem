package com.anushka.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anushka.config.request.IssueRequest;
//import com.anushka.config.response.AuthResponse;
import com.anushka.model.Issue;
import com.anushka.model.IssueDTO;
//import com.anushka.model.Message;
import com.anushka.model.User;
import com.anushka.service.IssueService;
import com.anushka.service.UserService;

import com.anushka.config.response.MessageResponse;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) 
        throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) 
    throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue (@RequestBody IssueRequest issue, 
                                                @RequestHeader("Authorization") String token)
        throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        

    

            Issue createdIssue = issueService.createIssue(issue, tokenUser);
            IssueDTO issueDTO=new IssueDTO();
            issueDTO.setDescription(createdIssue.getDescription());
            issueDTO.setDueDate(createdIssue.getDueDate());
            issueDTO.setId(createdIssue.getId());
            issueDTO.setPriority(createdIssue.getPriority());
            issueDTO.setProject(createdIssue.getProject());
            //issueDTO.setProjectID(createdIssue.getProjectId());
            issueDTO.setStatus(createdIssue.getStatus());
            issueDTO.setTitle(createdIssue.getTitle());
            issueDTO.setTags(createdIssue.getTags());
            issueDTO.setAssignee (createdIssue.getAssignee());

            return ResponseEntity.ok(issueDTO);
    }        

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueld,
                                                    @RequestHeader("Authorization") String token)
            throws Exception {
                User user = userService.findUserProfileByJwt(token);
                issueService.deleteIssue(issueld, user.getId());

                MessageResponse res=new MessageResponse();
                res.setMessage("Issue deleted");

                return ResponseEntity.ok(res);

}

@PutMapping("/{issueId}/assignee/{userId}")

public ResponseEntity<Issue> addUserToIssue (@PathVariable Long issueld,@PathVariable Long userId)

throws Exception {
    
    Issue issue = issueService.addUserToIssue(issueld, userId);
    return ResponseEntity.ok(issue);
}

@PutMapping("/{issueId}/status/{status}")

public ResponseEntity<Issue>updateIssueStatus(@PathVariable String status,@PathVariable Long issueId) 

throws Exception {
    Issue issue = issueService.updateStatus(issueId, status);
    return ResponseEntity.ok(issue);
}
}


