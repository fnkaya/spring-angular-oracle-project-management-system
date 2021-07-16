package com.fnkaya.projectmanagement.issue.dto.response;

import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

@Getter
@Setter
public class IssueWithAssigneeResponse {

    private Long id;

    private String code;

    private String description;

    private String details;

    private Date due_date;

    private IssueStatus status;

    private Long assignee_id;

    private String assignee_name;

    private String assignee_email;

    public static RowMapper<IssueWithAssigneeResponse> rowMapper = (rs, rowNum) -> {
        IssueWithAssigneeResponse response = new IssueWithAssigneeResponse();
        response.setId(rs.getLong("issue_id"));
        response.setCode(rs.getString("issue_code"));
        response.setDescription(rs.getString("issue_description"));
        response.setDetails(rs.getString("issue_details"));
        response.setDue_date(rs.getDate("due_date"));
        response.setStatus(IssueStatus.valueOf(rs.getString("issue_status")));
        response.setAssignee_id(rs.getLong("assignee_id"));
        response.setAssignee_name(rs.getString("assignee_name"));
        response.setAssignee_email(rs.getString("assignee_email"));

        return response;
    };
}
