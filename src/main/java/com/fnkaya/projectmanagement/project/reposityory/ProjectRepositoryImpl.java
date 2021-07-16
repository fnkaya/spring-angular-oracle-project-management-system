package com.fnkaya.projectmanagement.project.reposityory;

import com.fnkaya.projectmanagement.project.dto.response.ProjectWithManagerResponse;
import com.fnkaya.projectmanagement.project.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    public ProjectRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Project project) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("insert_project");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_code", project.getCode())
                .addValue("p_description", project.getDescription())
                .addValue("p_details", project.getDetails())
                .addValue("p_is_active", project.getActive())
                .addValue("p_manager_id", project.getManager_id());

        simpleJdbcCall.execute(params);
    }

    @Override
    public void update(Project project) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("update_project");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_description", project.getDescription())
                .addValue("p_details", project.getDetails())
                .addValue("p_is_active", project.getActive())
                .addValue("p_manager_id", project.getManager_id())
                .addValue("p_project_id", project.getId());

        simpleJdbcCall.execute(params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Project WHERE project_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ProjectWithManagerResponse get(Long id) {
        String sql = "SELECT * FROM get_project_with_manager WHERE project_id = ?";
        ProjectWithManagerResponse project =
                jdbcTemplate.queryForObject(sql, ProjectWithManagerResponse.rowMapper, id);

        return project;
    }

    @Override
    public List<Project> getAll() {
        String sql = "SELECT * FROM Project";

        return jdbcTemplate.query(sql, Project.rowMapper);
    }

    @Override
    public List<ProjectWithManagerResponse> getAllWithManager(){
        String sql = "SELECT * FROM get_project_with_manager";

        return jdbcTemplate.query(sql, ProjectWithManagerResponse.rowMapper);
    }

    @Override
    public List<ProjectWithManagerResponse> search(String keyword) {
        String sql = "SELECT * FROM get_project_with_manager WHERE project_description LIKE UPPER(?)" +
                                                                "OR project_code LIKE UPPER(?)";
        Object[] params = {'%' + keyword + '%', '%' + keyword + '%'};

        return jdbcTemplate.query(sql, ProjectWithManagerResponse.rowMapper, params);
    }
}
