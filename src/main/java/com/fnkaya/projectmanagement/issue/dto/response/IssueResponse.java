package com.fnkaya.projectmanagement.issue.dto.response;

import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

@Getter
@Setter
public class IssueResponse {

    private Long id;

    private String code;

    private String description;

    private String details;

    private Date due_date;

    private IssueStatus status;

    public static RowMapper<IssueResponse> rowMapper = (rs, rowNum) -> {
        IssueResponse response = new IssueResponse();
        response.setId(rs.getLong("issue_id"));
        response.setCode(rs.getString("issue_code"));
        response.setDescription(rs.getString("issue_description"));
        response.setDetails(rs.getString("issue_details"));
        response.setDue_date(rs.getDate("due_date"));
        response.setStatus(IssueStatus.valueOf(rs.getString("issue_status")));

        return response;
    };
}
