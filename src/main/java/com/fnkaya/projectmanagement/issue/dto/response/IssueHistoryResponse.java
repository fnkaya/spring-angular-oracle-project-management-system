package com.fnkaya.projectmanagement.issue.dto.response;

import com.fnkaya.projectmanagement.issue.entity.IssueHistory;
import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

@Data
public class IssueHistoryResponse {

    private String description;

    private String details;

    private Date due_date;

    private IssueStatus status;

    private String assignee_name;

    private String assignee_email;

    private Date updated_time;

    public static RowMapper<IssueHistoryResponse> rowMapper = (rs, rowNum) -> {
        IssueHistoryResponse response = new IssueHistoryResponse();
        response.setDescription(rs.getString("issue_description"));
        response.setDetails(rs.getString("issue_details"));
        response.setDue_date(rs.getDate("due_date"));
        response.setStatus(IssueStatus.valueOf(rs.getString("issue_status")));
        response.setAssignee_name(rs.getString("assignee_name"));
        response.setAssignee_email(rs.getString("assignee_email"));
        response.setUpdated_time(rs.getDate("updated_time"));

        return response;
    };
}
