package com.fnkaya.projectmanagement.issue.entity;

import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IssueHistory {

    private Long id;

    private Long issue_id;

    private String description;

    private String details;

    private Date due_date;

    private IssueStatus status;

    private Long assignee_id;

    private Date updated_time;

    public static RowMapper<IssueHistory> rowMapper = (rs, rowNum) -> {
        IssueHistory response = new IssueHistory();
        response.setId(rs.getLong("ihistory_id"));
        response.setIssue_id(rs.getLong("issue_id"));
        response.setDescription(rs.getString("issue_description"));
        response.setDetails(rs.getString("issue_details"));
        response.setDue_date(rs.getDate("due_date"));
        response.setStatus(IssueStatus.valueOf(rs.getString("issue_status")));
        response.setAssignee_id(rs.getLong("assignee_id"));
        response.setUpdated_time(rs.getDate("updated_time"));

        return response;
    };
}
