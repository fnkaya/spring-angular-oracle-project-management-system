package com.fnkaya.projectmanagement.issue.dto.request;

import com.fnkaya.projectmanagement.common.dto.RequestDto;
import com.fnkaya.projectmanagement.issue.entity.Issue;
import com.fnkaya.projectmanagement.issue.entity.enumeration.IssueStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class AddIssueRequest implements RequestDto<Issue> {

    @NotBlank
    private String code;

    @NotBlank
    private String description;

    @NotBlank
    private String details;

    @NotNull
    private Date due_date;

    private IssueStatus status;

    @NotNull
    private Long project_id;

    @Override
    public Issue getDomainObject() {
        Issue issue = new Issue();
        issue.setCode(this.code);
        issue.setDescription(this.description);
        issue.setDetails(this.details);
        issue.setDue_date(this.due_date);
        issue.setStatus(this.status);
        issue.setProject_id(this.project_id);

        return issue;
    }
}
