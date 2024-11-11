package com.anushka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.model.Chat;
import com.anushka.model.Project;
import com.anushka.model.User;
import com.anushka.repository.ProjectRepository;


@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    private

    @Override
    public Project createProject(Project project, User user) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'createProject'");
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'getProjectByTeam'");
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'getProjectById'");
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
       
        throw new UnsupportedOperationException("Unimplemented method 'deleteProject'");
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateProject'");
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'addUserToProject'");
    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'removeUserFromProject'");
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        
        throw new UnsupportedOperationException("Unimplemented method 'getChatByProjectId'");
    }

}
